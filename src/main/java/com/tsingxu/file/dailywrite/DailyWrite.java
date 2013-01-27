package com.tsingxu.file.dailywrite;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;

public final class DailyWrite
{
	private static FileOutputStream fos;
	private static final Object fileLock = new Object();
	private static String shedulerFileName;
	private static final String fileName = "file/daily";
	private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	private static final Logger logger = Logger.getLogger("");

	private DailyWrite()
	{
		// na
	}

	public static void log(String msg)
	{
		try
		{
			write(msg);
		}
		catch (IOException e)
		{
			e.printStackTrace();
			logger.error("write flow statistics error", e);
		}
	}

	private static void write(String flag) throws IOException
	{
		synchronized (fileLock)
		{
			getFile(fileName);
			rollOver();
			fos.write((new Date() + "|" + flag + System.getProperty("line.separator")).getBytes());
		}
	}

	private static void getFile(String fileName) throws IOException
	{
		if (fos != null)
		{
			fos.flush();
			fos.close();
		}

		FileOutputStream ostream = null;
		File file = null;
		try
		{
			file = new File(fileName);
			ostream = new FileOutputStream(file, true);
		}
		catch (FileNotFoundException ex)
		{
			String parentName = file.getParent();
			if (parentName != null)
			{
				File parentDir = new File(parentName);
				if (!parentDir.exists() && parentDir.mkdirs())
					ostream = new FileOutputStream(file, true);
				else
					throw ex;
			}
			else
			{
				throw ex;
			}
		}
		fos = ostream;
		shedulerFileName = fileName + "_" + sdf.format(new Date(file.lastModified()));
	}

	private static void rollOver() throws IOException
	{
		long now = System.currentTimeMillis();
		String datedFilename = fileName + "_" + sdf.format(new Date(now));
		if (shedulerFileName.equals(datedFilename))
		{
			return;
		}
		closeFile();
		File target = new File(shedulerFileName);
		if (target.exists())
			target.delete();
		File file = new File(fileName);
		boolean result = file.renameTo(target);
		if (!result)
		{
			logger.error("rename failed " + shedulerFileName);
		}
		getFile(fileName);
	}

	private static void closeFile() throws IOException
	{
		fos.flush();
		fos.close();
	}
}
