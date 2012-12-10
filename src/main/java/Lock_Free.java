import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.LinkedList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class push extends Thread
{
	public void run()
	{
		while (true)
		{
			if (!QQueue.getInstance().push(Lock_Free.count++))
			{
				break;
			}
		}
	}
}

class pop extends Thread
{
	public void run()
	{
		Integer a;
		while (true)
		{
			if ((a = QQueue.getInstance().pop()) != null)
			{
				log._log(String.valueOf(a));
			}
			else
			{
				break;
			}
		}
	}
}

class log extends Thread
{
	public static LinkedList<String> logs = new LinkedList<String>();
	private BufferedOutputStream fos = null;

	public log()
	{
		try
		{
			fos = new BufferedOutputStream(new FileOutputStream("a.log"));
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
	}

	public static void _log(String str)
	{
		synchronized (logs)
		{
			logs.offer(str);
			if (logs.size() <= 1)
			{
				logs.notify();
			}
		}
	}

	public void run()
	{
		while (true)
		{
			String str = null;
			synchronized (logs)
			{
				if (logs.isEmpty())
				{
					try
					{
						logs.wait();
					}
					catch (InterruptedException e)
					{
						e.printStackTrace();
					}
				}
				str = logs.poll();
			}

			str += "\n";

			try
			{
				fos.write(str.getBytes("UTF-8"));
			}
			catch (UnsupportedEncodingException e)
			{
				e.printStackTrace();
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}
	}
}

class QQueue
{
	public LinkedList<Integer> queue = new LinkedList<Integer>();
	private static QQueue instance = new QQueue();

	private QQueue()
	{
	}

	public static QQueue getInstance()
	{
		return instance;
	}

	public boolean push(int e)
	{
		if (e > 1E8)
		{
			return false;
		}
		synchronized (queue)
		{
			queue.offer(e);
			if (queue.size() <= 1)
			{
				queue.notifyAll();
			}
			return true;
		}
	}

	public Integer pop()
	{
		synchronized (queue)
		{
			if (queue.isEmpty())
			{
				try
				{
					queue.wait();
				}
				catch (InterruptedException e)
				{
					e.printStackTrace();
				}
			}
			return queue.poll();
		}
	}
}

public class Lock_Free
{
	public static volatile int count = 0;

	public static void main(String[] args)
	{
		ExecutorService es = Executors.newCachedThreadPool();

		es.execute(new log());

		for (int i = 0; i < 100; i++)
		{
			es.execute(new pop());
		}

		for (int i = 0; i < 10; i++)
		{
			es.execute(new push());
		}

		es.shutdown();
	}
}
