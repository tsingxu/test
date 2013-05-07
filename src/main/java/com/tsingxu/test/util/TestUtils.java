package com.tsingxu.test.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.SortedMap;
import java.util.TreeMap;

public class TestUtils
{

	public static String sendSMS(String msg, String mobile)
	{
		return SmsUtils.sendSms("http://api.dbank.com/rest.php",
				"KlmAHLmkhMYRUgyiu0QcHs6HPR52CCAO", "48669", mobile, msg);
	}

	static class SmsUtils
	{
		static char HEXDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B',
				'C', 'D', 'E', 'F' };

		public static String sendSms(String smshost, String secret, String appid, String mobile,
				String text)
		{
			String[][] kvs = new String[][] { new String[] { "message", text },
					new String[] { "mobile", mobile }, new String[] { "nsp_app", appid },
					new String[] { "nsp_fmt", "JSON" }, new String[] { "nsp_svc", "nsp.sms.send" },
					new String[] { "nsp_ts", String.valueOf(new Date().getTime() / 1000) },
					new String[] { "nsp_ver", "1.0" } };

			String resp = "";
			try
			{
				StringBuffer buf = new StringBuffer(secret);
				for (String[] kv : kvs)
				{
					buf.append(kv[0]).append(kv[1]);
				}

				String data = buf.toString();

				MessageDigest digest = MessageDigest.getInstance("MD5");
				digest.update(data.getBytes("UTF-8"));
				byte[] hash = digest.digest();

				String hex = byte2HEX(hash);

				StringBuffer urlstr = new StringBuffer(smshost).append("?nsp_key=").append(hex);

				for (String[] kv : kvs)
				{
					urlstr.append("&").append(kv[0]).append("=")
							.append(URLEncoder.encode(kv[1], "UTF-8"));
				}

				System.out.println(urlstr);

				URL url = new URL(urlstr.toString());
				InputStream is = url.openStream();
				BufferedReader br = new BufferedReader(new InputStreamReader(is));

				String line = null;
				while ((line = br.readLine()) != null)
				{
					resp += line;
				}

				is.close();
				br.close();
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
			return resp;
		}

		public static String byte2hex(byte[] data)
		{
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < data.length; i++)
			{
				String temp = Integer.toHexString(((int) data[i]) & 0xFF);
				for (int t = temp.length(); t < 2; t++)
				{
					sb.append("0");
				}
				sb.append(temp);
			}
			return sb.toString();
		}

		// byte数组转换为16进制字符串
		public static String byte2HEX(byte[] data)
		{
			char[] dest = new char[data.length * 2];

			for (int i = 0; i < data.length; i++)
			{
				dest[i * 2] = HEXDigits[(data[i] >> 4) & 0x0F];
				dest[i * 2 + 1] = HEXDigits[((int) data[i]) & 0x0F];
			}
			return new String(dest);
		}

		// 16进制转换为byte数组
		public static byte[] hex2byte(String hexStr)
		{
			byte[] bts = new byte[hexStr.length() / 2];
			for (int i = 0, j = 0; j < bts.length; j++)
			{
				bts[j] = (byte) Integer.parseInt(hexStr.substring(i, i + 2), 16);
				i += 2;
			}
			return bts;
		}

		public static String sendMail(String smshost, String secret, String appid, String mailto,
				String title, String text) throws NoSuchAlgorithmException,
				UnsupportedEncodingException, MalformedURLException, IOException
		{
			String[][] kvs = new String[][] { new String[] { "subject", title },
					new String[] { "body", text }, new String[] { "nsp_app", appid },
					new String[] { "nsp_fmt", "JSON" }, new String[] { "nsp_svc", "nsp.mail" },
					new String[] { "nsp_ts", String.valueOf(new Date().getTime() / 1000) },
					new String[] { "nsp_ver", "1.0" }, new String[] { "reciver", mailto } };

			SortedMap<String, String> map = new TreeMap<String, String>();
			for (String[] kv : kvs)
			{
				map.put(kv[0], kv[1]);
			}
			StringBuffer buf = new StringBuffer(secret);
			for (String k : map.keySet())
			{
				String v = map.get(k);
				System.out.println("k=" + k + ",v=" + v);
				buf.append(k).append(v);
			}

			String data = buf.toString();
			MessageDigest digest = java.security.MessageDigest.getInstance("MD5");
			digest.update(data.getBytes("UTF-8"));
			byte[] hash = digest.digest();

			String hex = byte2HEX(hash);
			System.out.println("hex=" + hex);

			StringBuffer urlstr = new StringBuffer(smshost).append("?nsp_key=").append(hex);
			for (String k : map.keySet())
			{
				String v = map.get(k);
				urlstr.append("&").append(k).append("=").append(URLEncoder.encode(v, "UTF-8"));
			}

			System.out.println("url=" + urlstr.toString());

			String resq = "";
			URL url = new URL(urlstr.toString());
			InputStream is = url.openStream();
			try
			{
				BufferedReader br = new BufferedReader(new InputStreamReader(is));

				String line = null;
				while ((line = br.readLine()) != null)
				{
					resq += line;
				}

				is.close();
				br.close();
			}
			catch (Exception e)
			{
			}

			System.out.println(resq);
			return resq;
		}

	}

}
