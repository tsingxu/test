import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;

public class Client implements Runnable
{

	Selector sel;
	SocketChannel sc;

	public Client()
	{

		try
		{
			sel = Selector.open();
			sc = SocketChannel.open();
			sc.connect(new InetSocketAddress(InetAddress.getByName("192.168.7.102"), 3333));
			sc.configureBlocking(false);
			sc.register(sel, SelectionKey.OP_READ | SelectionKey.OP_WRITE);
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException
	{
		// TODO Auto-generated method stub
		new Thread(new Client()).start();
	}

	@Override
	public void run()
	{
		// TODO Auto-generated method stub
		while (true)
		{
			try
			{
				sel.select(10000);
			}
			catch (IOException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			for (SelectionKey key : sel.selectedKeys())
			{
				if (!key.isConnectable())
				{
					System.out.println("error");
					try
					{
						((SocketChannel) key.channel()).socket()
								.connect(
										new InetSocketAddress(InetAddress
												.getByName("192.168.7.102"), 3333));
					}
					catch (UnknownHostException e)
					{
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					catch (IOException e)
					{
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				// System.out.println(((SocketChannel)
				// key.channel()).socket().toString());
			}
		}
	}
}
