package com.tsingxu.tool;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public final class Tool
{
	public static final Tool TOOL = new Tool();

	private Tool()
	{
	}

	public static Tool getInstance()
	{
		return TOOL;
	}

	public String replaceFirstCharLowerCase(String json)
	{
		String[] jsons = json.split("[{|,]\"");

		String fieldName = null;

		for (String s : jsons)
		{
			if (-1 < s.indexOf("\":"))
			{
				fieldName = s.split("\":")[0];

				json = json.replace(
						"\"" + fieldName + "\":",
						"\"" + fieldName.substring(0, 1).toLowerCase()
								+ fieldName.substring(1, fieldName.length()) + "\":");
			}
		}

		return json;
	}

	public String replaceFirstCharUpperCase(String json)
	{
		String[] jsons = json.split("[{|,]\"");

		String fieldName = null;

		for (String s : jsons)
		{
			if (-1 < s.indexOf("\":"))
			{
				fieldName = s.split("\":")[0];

				json = json.replace(
						"\"" + fieldName + "\":",
						"\"" + fieldName.substring(0, 1).toUpperCase()
								+ fieldName.substring(1, fieldName.length()) + "\":");
			}
		}

		return json;
	}

	public void checkNull(Object object, String curValue, String value)
	{
		Field[] fields = object.getClass().getDeclaredFields();

		try
		{
			this.checkField(object, fields, curValue, value);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	@SuppressWarnings("rawtypes")
	private void checkField(Object object, Field[] fields, String curValue, String value)
			throws IllegalArgumentException, UnsupportedEncodingException, IllegalAccessException,
			SecurityException, InstantiationException
	{
		Class clazz = null;

		for (Field field : fields)
		{
			field.setAccessible(true);

			clazz = field.getType();

			if (clazz.isPrimitive() || Integer.class.equals(clazz))
			{
			}
			else if (String.class.equals(clazz))
			{
				this.setString(object, field, curValue, value);
			}
			else if (List.class.equals(clazz))
			{
				this.setList(object, field, curValue, value);
			}
			else
			{
				this.setObject(object, field, clazz, curValue, value);
			}
		}
	}

	private void setString(Object object, Field field, String curValue, String value)
			throws IllegalArgumentException, UnsupportedEncodingException, IllegalAccessException
	{
		Object obj = field.get(object);

		if (curValue == obj || (null != curValue && curValue.equals(obj))
				|| (null != obj && obj.equals(curValue)))
		{
			field.set(object, null == value ? null : new String(value.getBytes(), "UTF-8"));
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void setList(Object object, Field field, String curValue, String value)
			throws IllegalArgumentException, IllegalAccessException, InstantiationException,
			UnsupportedEncodingException, SecurityException
	{
		Object obj = field.get(object);

		List list = null;

		if (null == obj)
		{
			list = new ArrayList(1);

			ParameterizedType pType = (ParameterizedType) field.getGenericType();

			Class clazz = (Class) pType.getActualTypeArguments()[0];

			Object o = clazz.newInstance();

			list.add(o);

			field.set(object, list);

			checkField(o, o.getClass().getDeclaredFields(), curValue, value);
		}
		else
		{
			list = (List) obj;

			for (Object o : list)
			{
				checkField(o, o.getClass().getDeclaredFields(), curValue, value);
			}
		}
	}

	@SuppressWarnings("rawtypes")
	private void setObject(Object object, Field field, Class clazz, String curValue, String value)
			throws IllegalArgumentException, IllegalAccessException, InstantiationException,
			UnsupportedEncodingException, SecurityException
	{
		Object obj = field.get(object);

		if (null == obj)
		{
			obj = clazz.newInstance();

			field.set(object, obj);
		}

		checkField(obj, obj.getClass().getDeclaredFields(), curValue, value);
	}

	public Date getDate(Date date, int field, int amount)
	{
		Calendar calendar = Calendar.getInstance();

		calendar.setTime(date);

		calendar.add(field, amount);

		return calendar.getTime();
	}

	public String convertStringForDate(String pattern, Date date)
	{
		return new SimpleDateFormat(pattern).format(date);
	}

	public Date convertDateForString(String pattern, String source)
	{
		try
		{
			return new SimpleDateFormat(pattern).parse(source);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}

}
