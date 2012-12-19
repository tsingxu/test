package com.tsingxu.pitaya;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

public class Hello
{
	public static void main(String[] args) throws IOException
	{
		BufferedReader reader = new BufferedReader(new FileReader(new File("file/a.log")));

		HashMap<String, AtomicInteger> map = new HashMap<String, AtomicInteger>();
		String flag = null;
		String log = null;

		while ((log = reader.readLine()) != null)
		{
			flag = log.split("source address]:")[1];
			if (map.get(flag) == null)
			{
				map.put(flag, new AtomicInteger(0));
			}
			map.get(flag).incrementAndGet();
		}

		Set<String> keys = map.keySet();
		ArrayList<String> array = new ArrayList<String>(keys);
		Collections.sort(array);

		ArrayList<Integer> array_1 = new ArrayList<Integer>();
		for (String key : array)
		{
			array_1.add(map.get(key).get());
		}

		Collections.sort(array_1);

		for (Integer i : array_1)
		{
			System.out.println(i);
		}
	}
}
