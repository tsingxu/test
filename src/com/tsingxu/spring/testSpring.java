package com.tsingxu.spring;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.FileSystemResource;

/**
 * <b>in_a_word_briefly</b>
 * 
 * <ol>
 * <li>...</li>
 * </ol>
 * 
 * @since 2012-10-15 上午10:10:22
 * @author x00199331
 */
public class testSpring
{
	private String msg;

	/**
	 * @param args
	 */
	@SuppressWarnings("deprecation")
	public static void main(String[] args)
	{
		BeanFactory bf = new XmlBeanFactory(new FileSystemResource(
				"src/com/tsingxu/spring/hello.xml"));
		testSpring t = (testSpring) bf.getBean("helloService");
		System.out.println(t);
	}

	public String getMsg()
	{
		return msg;
	}

	public void setMsg(String msg)
	{
		System.out.println(msg);
		this.msg = msg;
	}

	@Override
	public String toString()
	{
		return "testSpring [msg=" + msg + "]";
	}

}
