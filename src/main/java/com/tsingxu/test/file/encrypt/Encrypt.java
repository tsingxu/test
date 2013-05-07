package com.tsingxu.test.file.encrypt;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Encrypt
{
	public static void main(String[] args) throws IOException
	{
		File old = new File("file/input.pptx");
		File new_ = new File("file/output.tsingxu");

		BufferedInputStream bis = new BufferedInputStream(new FileInputStream(old));
		byte[] buff = new byte[1024];

		BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(new_));

		int cnt = 0;

		while ((cnt = bis.read(buff)) != -1)
		{
			for (int i = 0; i < cnt; i++)
			{
				bos.write(buff[i]);
				bos.write((int) (System.currentTimeMillis() % 1021));
			}
		}

		bis.close();
		bos.flush();
		bos.close();
	}
}
