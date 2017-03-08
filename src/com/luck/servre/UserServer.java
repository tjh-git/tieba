package com.luck.servre;
import java.sql.*;
import java.util.*;

import com.luck.model.*;
import com.luck.util.DataBase;
public class UserServer 
{
	public void add(User user)throws Exception
	{
		Connection conn=DataBase.creatConn();
		PreparedStatement ps=null;
		String sql=null;
		sql="insert into user values(?,?,\",\")";
		ps=DataBase.prepare(conn, sql);
		try   
		{
			ps.setString(1,user.getName());
			ps.setString(2, user.getPassword()); 
			ps.executeUpdate();
		}catch (SQLException e) 
		{
				
			System.out.println("×¢²áÊ§°Ü");
			throw(e);
		}
		DataBase.close(conn);
		DataBase.close(ps);
	}
	public String getPassword(String name) throws SQLException
	{
		Connection conn=DataBase.creatConn();
		PreparedStatement ps=null;
		String sql=null;
		ResultSet rs=null;
		sql="select * from user where name=?";
		ps=DataBase.prepare(conn, sql);
		try   
		{
			ps.setString(1,name);
			ps.executeQuery();
			rs=ps.getResultSet();
		}catch (SQLException e) 
		{
			System.out.println("×¢²áÊ§°Ü");
			throw(e);
		}
		while(rs.next())
		{  
			String string=rs.getString("password");
			DataBase.close(conn);
			DataBase.close(ps);
			DataBase.close(rs);
			return string;
		}
		DataBase.close(rs);
		DataBase.close(conn);
		DataBase.close(ps);
		return null;
	}
	public List<User> list() throws SQLException 
	{
		Connection conn=DataBase.creatConn();
		PreparedStatement ps=null;
		String sql=null;
		ResultSet rs=null;
		List<User> users=new java.util.ArrayList<User>();
		sql="select * from user";
		ps=DataBase.prepare(conn, sql);
		try   
		{
			ps.executeQuery();
			rs=ps.getResultSet();
		}catch (SQLException e) 
		{
			System.out.println("×¢²áÊ§°Ü");
			throw(e);
		}
		while(rs.next())
		{  
			String name=rs.getString("name");
			User user=new User();
			user.setName(name);
			users.add(user);
		}
		DataBase.close(conn);
		DataBase.close(ps);
		DataBase.close(rs);
		return users;
	}
	public static String getLoving(String name) throws SQLException 
	{
		Connection conn=DataBase.creatConn();
		PreparedStatement ps=null;
		String sql=null;
		ResultSet rs=null;
		String string=null;
		sql="select love from user where name=?";
		ps=DataBase.prepare(conn, sql);
		try   
		{
			ps.setString(1,name);
			ps.executeQuery();
			rs=ps.getResultSet();
		}catch (SQLException e) 
		{
		}
		while(rs.next())
		{  
			string=rs.getString("love");
		}
		DataBase.close(conn);
		DataBase.close(ps);
		DataBase.close(rs);
		return string;
	}
	public void addLove(String id,String name) throws SQLException 
	{
		Connection conn=DataBase.creatConn();
		PreparedStatement ps=null;
		String sql="update user set love=concat(love,?,\",\")  where name = ?";
		ps=DataBase.prepare(conn, sql);
		try 
		{
			ps.setString(1,id);
			ps.setString(2,name);
			ps.executeUpdate();
			}catch (SQLException e) 
			{
				e.printStackTrace();
				throw(e);
			}
			DataBase.close(conn);
			DataBase.close(ps);
	}
	public String check(String name) throws SQLException 
	{
		Connection conn=DataBase.creatConn();
		PreparedStatement ps=null;
		String sql=null;
		String string=null;
		ResultSet rs=null;
		sql="select * from user where name=?";
		ps=DataBase.prepare(conn, sql);
		try   
		{
			ps.setString(1,name);
			ps.executeQuery();
			rs=ps.getResultSet();
		}catch (SQLException e) 
		{
			throw(e);
		}
		while(rs.next())
		{  
			string=rs.getString("password");
		}
		DataBase.close(conn);
		DataBase.close(ps);
		DataBase.close(rs);
		if(string==null)return "ok";else return "no";
	}
}

