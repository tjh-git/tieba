package com.luck.servre;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.luck.model.Message;
import com.luck.model.Page;
import com.luck.util.DataBase;
import com.luck.model.*;
public class Baserver 
{
	public void add(TieBa tieBa) throws SQLException
	{
		Connection conn=DataBase.creatConn();
		PreparedStatement ps=null;
		String sql=null;
		sql="insert into tieba values(?,\",\")";
		ps=DataBase.prepare(conn, sql);
		try   
		{
			ps.setString(1,tieBa.getName());
			ps.executeUpdate();
		}catch (SQLException e) 
		{
				
			e.printStackTrace();
			throw(e);
		}
		finally
		{
			DataBase.close(conn);
			DataBase.close(ps);
		}
	}
	
	public List<TieBa> list()throws SQLException
	{
		List<TieBa> tieBas=new ArrayList<TieBa>();
		Connection conn=DataBase.creatConn();
		String  sql=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		sql="select * from tieba";
		ps=DataBase.prepare(conn, sql);
		try 
		{
			rs=ps.executeQuery();
			while(rs.next())
			{  
				TieBa tieBa=new TieBa();
				tieBa.setName(rs.getString("name"));
				String idString=rs.getString("pageid");
				tieBa.setIds(idString);
				tieBas.add(tieBa);  
			}
		}catch(SQLException e) 
		{
			e.printStackTrace();
			throw(e);
		}finally
		{
			DataBase.close(conn);
			DataBase.close(ps);
			DataBase.close(rs);
			return tieBas;
		}
	}
	
	public void addPage(String name,String id) throws SQLException
	{
		Connection conn=DataBase.creatConn();
		PreparedStatement ps=null;
		String sql="update tieba set pageid=concat(pageid,?,\",\")  where name = ?";
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
		finally
		{
			DataBase.close(conn);
			DataBase.close(ps);
		}
	}

	public String getIDS(String baname)
	{
		Connection conn=DataBase.creatConn();
		String  sql=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		sql="select pageid from tieba where name=?";
		String result=null;
		ps=DataBase.prepare(conn, sql);
		try 
		{
			ps.setString(1,baname);
			rs=ps.executeQuery();
			while(rs.next())
			{  
				result=rs.getString("pageid");
			}
		}catch(SQLException e) 
		{
			e.printStackTrace();
			throw(e);
		}finally
		{
			DataBase.close(conn);
			DataBase.close(ps);
			DataBase.close(rs);
			return result;
		}
	}
}
