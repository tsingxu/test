import java.security.Key;
import java.security.SecureRandom;
import java.util.Arrays;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;

/**
* Created by IntelliJ IDEA.
* User: 马千里
* Date: 2008-2-19
* Time: 8:52:29
* To change this template use File | Settings | File Templates.
*/
public class testDES
{
	Key key;
	private static char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B',
			'C', 'D', 'E', 'F' };
	private static final testDES instance = new testDES();

	public static testDES getInstance()
	{
		return instance;
	}

	private testDES()
	{
		getKey();// 生成密匙
	}

	public void getKey()
	{
		try
		{
			KeyGenerator _generator = KeyGenerator.getInstance("DES");
			_generator.init(new SecureRandom("ttt".getBytes()));
			this.key = _generator.generateKey();
			System.out.println(Arrays.toString(this.key.getEncoded())); // 8*8 =
																		// 64
																		// 位的密钥
			_generator = null;
		}
		catch (Exception e)
		{
			throw new RuntimeException("Error initializing SqlMap class. Cause: " + e);
		}
	}

	public byte[] encrypt(byte[] input) throws Exception
	{
		Cipher cipher = Cipher.getInstance("DES");
		cipher.init(Cipher.ENCRYPT_MODE, this.key);

		for (byte b : cipher.update(input))
		{
			System.out.print(hexDigits[b >>> 4 & 0xF] + "" + hexDigits[b & 0xF] + " ");
		}

		System.out.println();
		return cipher.update(input);
	}

	public void decrypt(byte[] input) throws Exception
	{
		Cipher cipher = Cipher.getInstance("DES");
		cipher.init(Cipher.DECRYPT_MODE, this.key);

		System.out.println(new String(cipher.update(input)));
	}

	public static void main(String[] args) throws Exception
	{
		testDES.getInstance().decrypt(
				testDES.getInstance().encrypt("hekldfsdfdfslfdfdo".getBytes()));

	}
}