package com.tsingxu.ehcache;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

public class test
{

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		CacheManager manager = CacheManager.create();

		Cache cache = new Cache("testCache", 50000, false, false, 8, 2);
		manager.addCache(cache);
		cache.put(new Element("name", "tsingxu"));

		System.out.println(cache.get("name").getObjectValue());
		System.out.println(cache.get("name").getObjectValue());
		System.out.println(cache.get("name").getObjectValue());
		System.out.println(cache.get("name").getObjectValue());
		System.out.println(cache.get("name").getObjectValue());
		System.out.println(cache.get("name").getHitCount());

	}

}
