package com.luck.servre;
import java.sql.*;
import java.util.*;

import com.luck.model.*;
import com.luck.util.DataBase;
public class TBServer 
{
	public int add(Page page)throws Exception
	{
		Connection conn=DataBase.creatConn();
		PreparedStatement ps=null;
		String sql=null;
		sql="insert into page values(null,?,?,?,0,\",\",0)";
		ps=DataBase.prepare(conn, sql);
		try   
		{
			ps.setString(1,page.getName());
			ps.setString(2, page.getArticle()); 
			ps.setString(3, page.getTitle());
			ps.executeUpdate();
		}catch (SQLException e) 
		{
				
			e.printStackTrace();
			throw(e);
		}
		sql="select id from page";
		ps=DataBase.prepare(conn, sql);
		ResultSet rs=ps.executeQuery();
		rs.last();
		int aa=rs.getInt("id");
		DataBase.close(conn);
		DataBase.close(ps);
		DataBase.close(rs);
		return aa;
	}
	public List<Page> list()throws Exception
	{
		List<Page> pages=new ArrayList<Page>();
		Connection conn=DataBase.creatConn();
		String  sql=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		sql="select * from page";
		ps=DataBase.prepare(conn, sql);
		try 
		{
			rs=ps.executeQuery();
			while(rs.next())
			{  
				Page page=new Page();
				page.setId(rs.getInt("id"));
				page.setName(rs.getString("name"));
				page.setTitle(rs.getString("title"));
				page.setArticle(rs.getString("article"));
				page.setZan(rs.getInt("zan"));
				page.setReplyNumber(rs.getInt("replynumber"));
				pages.add(page);  
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
			return pages;
		}
	}
	public Page loadById(int id)throws Exception
	{
		Connection conn=DataBase.creatConn();
		PreparedStatement ps=null;
		String sql="select * from page where id=?";
		ResultSet rs=null;
		Page page=null;
		ps=DataBase.prepare(conn, sql);
		try 
		{
			ps.setInt(1, id);
			rs=ps.executeQuery();
			if(rs.next())
			{
				page=new Page();
				page.setName(rs.getString("name"));
				page.setArticle(rs.getString("article"));
				page.setTitle(rs.getString("title"));
				page.setId(rs.getInt("id"));
			}
		} catch (SQLException e) 
		{
			e.printStackTrace();
			throw e;
		}finally
		{
			DataBase.close(conn);
			DataBase.close(ps);
			DataBase.close(rs);
			return page;
		}
	}
	public void zan(String s)throws Exception
	{
		int a=Integer.parseInt(s);
		Connection conn=DataBase.creatConn();
		PreparedStatement ps=null;
		String sql="update page set zan=zan+1 where id=?";
		ps=DataBase.prepare(conn, sql);
		try 
		{
			ps.setInt(1,a);
			ps.executeUpdate();
		}catch (SQLException e) 
		{
			e.printStackTrace();
			throw(e);
		}
		DataBase.close(conn);
		DataBase.close(ps);
	}
	public Page getOneByID(String id)
	{
		Page page=new Page();
		Connection conn=DataBase.creatConn();
		String  sql=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		sql="select * from page where id=?";
		ps=DataBase.prepare(conn, sql);
		try 
		{
			ps.setInt(1,Integer.parseInt(id));
			rs=ps.executeQuery();
			while(rs.next())
			{  
				page.setId(rs.getInt("id"));
				page.setName(rs.getString("name"));
				page.setTitle(rs.getString("title"));
				page.setArticle(rs.getString("article"));
				page.setZan(rs.getInt("zan"));
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
			return page;
		}
	}
	public void addChildren(int cid,int id) throws SQLException 
	{
		Connection conn=DataBase.creatConn();
		PreparedStatement ps=null;
		String sql="update page set children=concat(children,?,\",\")  where id = ?";
		ps=DataBase.prepare(conn, sql);
		try 
		{
			ps.setString(1,String.valueOf(cid));
			ps.setInt(2, id);
			ps.executeUpdate();
			}catch (SQLException e) 
			{
				e.printStackTrace();
				throw(e);
			}
			DataBase.close(conn);
			DataBase.close(ps);
	}
	public List<Reply> getReplys(String id) 
	{
		Connection conn=DataBase.creatConn();
		PreparedStatement ps=null;
		ResultSet rs=null;
		String sql="select children from page where id=?";
		Reply reply=new Reply();
		List<Reply> replies=new ArrayList<Reply>();
		String children=null;
		ps=DataBase.prepare(conn, sql);
		try 
		{
			ps.setInt(1,Integer.parseInt(id));
			rs=ps.executeQuery();
			while(rs.next())
			{
				children=rs.getString("children");
			}
			String[] childrenID=children.split(",");
			ReplyServer replyServer=new ReplyServer();
			for(int i=0;i<childrenID.length;i++)
			{
				replyServer.getReplys(replies,childrenID[i],1,"Â¥Ö÷");
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
			return replies;
		}
	}
	public void addReply(int fid) throws SQLException
	{
		Connection conn=DataBase.creatConn();
		PreparedStatement ps=null;
		String sql="update page set replynumber=replynumber+1  where id = ?";
		ps=DataBase.prepare(conn, sql);
		try 
		{
			ps.setInt(1, fid);
			ps.executeUpdate();
		}catch (SQLException e) 
		{
			e.printStackTrace();
			throw(e);
		}
		DataBase.close(conn);
		DataBase.close(ps);
	}
}
