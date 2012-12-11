package com.tsingxu.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

class insert implements Runnable
{

	public void run()
	{
		Connection conn = null;
		try
		{
			conn = DriverManager
					.getConnection(
							"jdbc:mysql://192.168.8.220:3306/xhq?setUnicode=true&amp;characterEncoding=UTF-8",
							"root", "123456");
			conn.setAutoCommit(true);
			Statement state = conn.createStatement();

			for (int i = 0; i < 1; i++)
			{
				ResultSet result = state.executeQuery("select * from note");
				result.beforeFirst();
				result.next();
				System.out.println(result.getString(2));
			}

		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}

	}

}

public class DBTest
{

	public static void main(String[] args) throws ClassNotFoundException
	{
		Class.forName("com.mysql.jdbc.Driver");
		new Thread(new insert()).start();
	}
}
