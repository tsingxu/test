import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;

public class localip
{

	public static void main(String[] args) throws MalformedURLException, UnknownHostException,
			SocketException
	{
		// TODO Auto-generated method stub
		// 根据网卡取本机配置的IP
		Enumeration<NetworkInterface> netInterfaces = NetworkInterface.getNetworkInterfaces();
		InetAddress ip = null;
		while (netInterfaces.hasMoreElements())
		{
			NetworkInterface ni = netInterfaces.nextElement();
			System.out.println(ni.getName());
			ip = (InetAddress) ni.getInetAddresses().nextElement();
			if (!ip.isSiteLocalAddress() && !ip.isLoopbackAddress()
					&& ip.getHostAddress().indexOf(":") == -1)
			{
				System.out.println("本机的ip=" + ip.getHostAddress());
				break;
			}
			else
			{
				ip = null;
			}
		}

	}
}
