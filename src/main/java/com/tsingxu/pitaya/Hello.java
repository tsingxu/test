package com.tsingxu.pitaya;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;

import org.apache.log4j.Logger;

public class Hello
{
	@SuppressWarnings("unused")
	private static final Logger logger = Logger.getLogger(Hello.class);

	public static void testPriorityQueue()
	{
		PriorityQueue<Integer> q = new PriorityQueue<Integer>();

		for (int i = 0; i < 1E5; i++)
		{
			q.add(i);
		}

		System.out.println(Runtime.getRuntime().freeMemory());

		for (int i = 0; i < 1E5; i++)
		{
			q.poll();
		}

		System.out.println(Runtime.getRuntime().freeMemory());
	}

	public static void testArrayListAndLinkedList()
	{
		LinkedList<Integer> queue = new LinkedList<Integer>();
		ArrayList<Integer> array = new ArrayList<Integer>();

		System.out.println(System.currentTimeMillis());

		for (int i = 0; i < 1E5; i++)
		{
			queue.addFirst(i);
		}

		for (int i = 0; i < 1E5; i++)
		{
			queue.removeFirst();
		}

		System.out.println(System.currentTimeMillis());
		for (int i = 0; i < 1E5; i++)
		{
			array.add(i);
		}

		for (int i = 0; i < 1E5; i++)
		{
			array.remove(new Integer(i));
		}

		System.out.println(System.currentTimeMillis());

	}

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException
	{
	}
}
