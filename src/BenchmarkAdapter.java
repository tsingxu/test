import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

public class BenchmarkAdapter
{

	public static void main(String[] args) throws Exception
	{
		ExecutorService pool = Executors.newCachedThreadPool();

		for (int i = 0; i < 1000; i++)
		{
			pool.execute(new Post());
		}
		pool.execute(Statics.getInstance());

		pool.shutdown();
	}

	private static class Statics extends Thread
	{
		private HashMap<Long, AtomicLong> map = new HashMap<Long, AtomicLong>();
		private static Statics instance = new Statics();
		private AtomicLong sum = new AtomicLong(0);
		private AtomicLong count = new AtomicLong(0);

		private Statics()
		{
		}

		public static Statics getInstance()
		{
			return instance;
		}

		public void update(long time)
		{
			long key = System.currentTimeMillis() / 1000;
			sum.addAndGet(time);
			count.incrementAndGet();

			synchronized (map)
			{
				if (map.get(key) == null)
				{
					map.put(key, new AtomicLong(0));
				}
			}
			map.get(key).incrementAndGet();
		}

		public void run()
		{
			while (true)
			{
				try
				{
					TimeUnit.SECONDS.sleep(1);
				}
				catch (InterruptedException e)
				{
					e.printStackTrace();
				}
				long sum = 0;
				long count = 0;
				long max_num = -1;
				long min_num = Long.MAX_VALUE;
				long current = 0;
				long currentSecond = System.currentTimeMillis() / 1000;
				long tmp;

				for (Entry<Long, AtomicLong> ele : map.entrySet())
				{
					if (ele.getKey() >= currentSecond)
					{
						continue;
					}

					tmp = ele.getValue().get();
					if (ele.getKey() == currentSecond - 1)
					{
						current += tmp;
					}
					sum += tmp;
					count++;
					max_num = (max_num < tmp ? tmp : max_num);
					min_num = (min_num > tmp ? tmp : min_num);
				}

				System.out.println(map.size() + " " + current + " "
						+ (count == 0 ? 0 : sum / count) + " " + min_num + " " + max_num + " "
						+ (this.sum.get() * 1.0 / this.count.get()));
			}
		}
	}

	private static class Post extends Thread
	{
		public void run()
		{
			byte[] buff = new byte[100];
			int count;
			ByteArrayOutputStream baos = new ByteArrayOutputStream();

			long time1, time2;

			URL url;
			while (true)
			{
				try
				{
					url = new URL("http://192.168.9.134:18000/storage/getticket");
					time1 = System.currentTimeMillis();
					HttpURLConnection conn = (HttpURLConnection) url.openConnection();
					conn.setRequestMethod("POST");
					conn.setDoOutput(true);
					conn.setRequestProperty("Content-Type", "application/json");
					conn.getOutputStream()
							.write("{\"Version\": \"10\",\"OpType\": \"1\",\"OpID\": \"xkg\",\"Data\":{\"EnterpriseID\":\"21\",\"FlightType\":\"0\",\"DepartureDate\":\"20121215\",\"Carrier\":\"\",\"DepartureCity\":\"PEK\",\"ArrivalCity\":\"SHA\",\"ClassGrade\":[\"\"]}}"
									.getBytes());
					conn.getOutputStream().flush();
					conn.getOutputStream().close();

					BufferedInputStream bis = new BufferedInputStream(conn.getInputStream());

					baos.reset();
					while ((count = bis.read(buff)) != -1)
					{
						baos.write(buff, 0, count);
					}

					baos.flush();
					time2 = System.currentTimeMillis();
					Statics.getInstance().update(time2 - time1);
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		}
	}

}
