package com.tsingxu.file.decrypt;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class Decrypt
{
	public static void main(String[] args) throws Exception
	{
		File old = new File("file/output.tsingxu");
		File new_ = new File("file/input.pptx");

		BufferedInputStream bis = new BufferedInputStream(new FileInputStream(old));
		byte[] buff = new byte[1024];

		BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(new_));

		int cnt = 0;

		while ((cnt = bis.read(buff)) != -1)
		{
			for (int i = 0; i < cnt; i++)
			{
				bos.write(buff[i]);
				i++;
			}
		}

		bis.close();
		bos.flush();
		bos.close();
	}

}
