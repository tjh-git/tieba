package com.luck.servre;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.http.HttpServletResponse;
import com.luck.model.*;
import com.luck.util.DataBase;
import com.luck.util.*;
public class MessageServer
{
	public int add(Message message) throws SQLException
	{
		Connection conn=DataBase.creatConn();
		PreparedStatement ps=null;
		String sql=null;
		sql="insert into messages values(null,?,?,?)";
		ps=DataBase.prepare(conn, sql);
		try   
		{
			ps.setString(1,message.getSender());
			ps.setString(2, message.getMessage()); 
			ps.setString(3, message.getReceiver());
			ps.executeUpdate();
		}catch (SQLException e) 
		{
				
			e.printStackTrace();
			throw(e);
		}
		sql="select id from messages";
		ps=DataBase.prepare(conn, sql);
		ResultSet rs=ps.executeQuery();
		rs.last();
		int retu=rs.getInt("id");
		DataBase.close(conn);
		DataBase.close(rs);
		DataBase.close(ps);
		return retu; 
	}

	public Message getNewMessage(String sender, String receiver, int id,HttpServletResponse resp)
	{
		Connection conn=DataBase.creatConn();
		PreparedStatement ps=null;
		String sql=null;
		boolean flag=false;
		Message message=new Message();
		sql="select * from messages where id>? and receiver=? and senter=?";
		ResultSet rs=null;
		ps=DataBase.prepare(conn, sql);
		try 
		{
			ps.setInt(1,id);
			ps.setString(2,receiver);
			ps.setString(3,sender);
			rs=ps.executeQuery();
			while(rs.next())
			{
				message.setMessage(rs.getString("message"));
				message.setReceiver(rs.getString("receiver"));
				message.setSender(rs.getString("senter"));
				message.setId(rs.getInt("id"));
			}
		if(message.getMessage()!=null)CookiesUtil.setCookies(resp,"top",String.valueOf(message.getId()));
		}catch(Exception e)
		{
		}
		DataBase.close(conn);
		DataBase.close(rs);
		DataBase.close(ps);
		return message;
	}
	public String getTop(String receiver, String sender)
	{
			
		Connection conn=DataBase.creatConn();
		PreparedStatement ps=null;
		String sql=null;
		int top=-1;
		sql="select * from messages where receiver=? and senter=?";
		ResultSet rs=null;
		ps=DataBase.prepare(conn, sql);
		try 
		{
			ps.setString(1, receiver);
			ps.setString(2, sender);
			rs=ps.executeQuery();
			while(rs.next())
			{
				top=rs.getInt("id");
			}
		}catch(Exception e)
		{
		}
		DataBase.close(conn);
		DataBase.close(rs);
		DataBase.close(ps);
		return String.valueOf(top);
	}
}
