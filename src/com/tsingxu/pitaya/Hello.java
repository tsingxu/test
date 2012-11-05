package com.tsingxu.pitaya;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.log4j.Logger;

import com.sun.xml.internal.ws.util.ByteArrayBuffer;

/**
 * <b>in_a_word_briefly</b>
 * 
 * <ol>
 * <li>Say Hello</li>
 * </ol>
 * 
 * @since Oct 29, 2012 6:50:59 PM
 * @author xuhuiqing
 */
public class Hello
{
	private static final Logger logger = Logger.getLogger(Hello.class);

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException
	{
		FileInputStream fis = new FileInputStream(new File("input"));

		ByteArrayBuffer bab = new ByteArrayBuffer();
		byte[] buff = new byte[1024];
		int cnt;

		while ((cnt = fis.read(buff)) != -1)
		{
			bab.write(buff, 0, cnt);
		}
		bab.flush();
		logger.info(bab.toString());

		bab.close();
		fis.close();
	}
}
