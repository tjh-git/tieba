package com.luck.util;
import java.sql.*;
public class DataBase 
{
	public static Connection creatConn()
	{
		Connection conn=null;
		try
		{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn=DriverManager.getConnection("jdbc:mysql://localhost/tieba?user=root&password=371525&useUnicode=true&characterEncoding=gbk");
		}catch(ClassNotFoundException e1)
		{
			e1.printStackTrace();
		}catch (SQLException e2) 
		{
			e2.printStackTrace();
		}catch (InstantiationException e3) 
		{
			e3.printStackTrace();
		}catch (IllegalAccessException e4) 
		{
			e4.printStackTrace();
		}finally
		{
			return conn;
		}
	}
	public static PreparedStatement prepare(Connection conn,String sql)
	{
		PreparedStatement ps=null;
		try 
		{
			ps=conn.prepareStatement(sql);
		}catch (SQLException e) 
		{
			e.printStackTrace();
		}finally
		{
			return ps;
		}
	}
	public static void close(Connection conn)
	{
		if(null!=conn)
		try 
		{
			conn.close();
		}catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}
	public static void close(Statement stmt)
	{
		if(null!=stmt)
		try 
		{
			stmt.close();
		}catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}
	public static void close(ResultSet rs)
	{
		if(null!=rs)
		try 
		{
			rs.close();
		}catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}
}