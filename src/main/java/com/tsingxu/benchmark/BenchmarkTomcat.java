package com.tsingxu.benchmark;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

public class BenchmarkTomcat
{

	public static void main(String[] args) throws Exception
	{
		System.out.println("ID    CUR   AVG   MIN   MAX       LAT(ms)");
		ExecutorService pool = Executors.newCachedThreadPool();

		for (int i = 0; i < 100; i++)
		{
			pool.execute(new Post());
		}
		pool.execute(Statistics.getInstance());

		pool.shutdown();
	}

	private static class Statistics extends Thread
	{
		private HashMap<Long, AtomicLong> map = new HashMap<Long, AtomicLong>();
		private static Statistics instance = new Statistics();
		private AtomicLong sum = new AtomicLong(0);
		private AtomicLong count = new AtomicLong(0);

		private Statistics()
		{
		}

		public static Statistics getInstance()
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
			long sum = 0;
			long count = 0;
			long max_num = -1;
			long min_num = Long.MAX_VALUE;
			long currentSecond;
			long current;
			int index = 1;
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
				currentSecond = System.currentTimeMillis() / 1000;
				AtomicLong hitCount = map.get(currentSecond);
				if (hitCount == null)
				{
					current = 0;
				}
				else
				{
					current = map.get(currentSecond).get();
				}
				sum += current;
				count++;
				max_num = (max_num < current ? current : max_num);
				min_num = (min_num > current ? current : min_num);

				System.out.println(String.format("%-5d %-5d %-5d %-5d %-5d     %.3f", (index++),
						current, (count == 0 ? 0 : sum / count), min_num, max_num,
						(this.sum.get() * 1.0 / this.count.get())));
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
					url = new URL("http://www.baidu.com/" + getRandomString());
					time1 = System.currentTimeMillis();
					HttpURLConnection conn = (HttpURLConnection) url.openConnection();
					conn.setRequestProperty("User-Agent", "Chrome/?.?.?" + getRandomString());
					BufferedInputStream bis = new BufferedInputStream(conn.getInputStream());

					baos.reset();
					while ((count = bis.read(buff)) != -1)
					{
						baos.write(buff, 0, count);
					}

					baos.flush();
					// System.out.println(baos.toString());
					time2 = System.currentTimeMillis();
					Statistics.getInstance().update(time2 - time1);
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		}

		public String getRandomString()
		{
			return "" + (char) (Math.random() * 26 + 'A') + (char) (Math.random() * 26 + 'A')
					+ (char) (Math.random() * 26 + 'A') + (char) (Math.random() * 26 + 'A')
					+ (char) (Math.random() * 26 + 'A') + (char) (Math.random() * 26 + 'A');
		}

	}

}
