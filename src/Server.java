import java.io.IOException;
import java.security.MessageDigest;
import java.security.Provider;
import java.security.Security;

public class Server
{

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws Exception
	{
		for (Provider pro : Security.getProviders())
		{
			System.out.println(pro.getName());
		}
		MessageDigest.getInstance("MD5");
		
	}

}
