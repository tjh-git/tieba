package com.luck.servre;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.luck.model.Page;
import com.luck.model.Reply;
import com.luck.util.DataBase;
public class ReplyServer
{
	public int add(Reply reply) throws SQLException
	{
		Connection conn=DataBase.creatConn();
		PreparedStatement ps=null;
		String sql=null;
		sql="insert into reply values(null,?,?,\",\",?)";
		ps=DataBase.prepare(conn, sql);
		try   
		{
			ps.setString(1,reply.getArticle());
			ps.setInt(2, reply.getFatherID()); 
			ps.setString(3, reply.getName());
			ps.executeUpdate();
		}catch (SQLException e) 
		{
				
			e.printStackTrace();
			throw(e);
		}
		sql="select id from reply";
		ps=DataBase.prepare(conn, sql);
		ResultSet rs=ps.executeQuery();
		rs.last();
		int retu=rs.getInt("id");
		DataBase.close(conn);
		DataBase.close(rs);
		DataBase.close(ps);
		return retu; 
	}
	public void addChildren(int cid,int id) throws SQLException 
	{
		Connection conn=DataBase.creatConn();
		PreparedStatement ps=null;
		String sql="update reply set children=concat(children,?,\",\")  where id = ?";
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
	public void getReplys(List<Reply> replies, String id,int rank,String faname) throws SQLException
	{
		int ID=-1;
		try
		{
			ID=Integer.parseInt(id);
		}catch(Exception e)
		{
			return;
		}
		Connection conn=DataBase.creatConn();
		PreparedStatement ps=null;
		ResultSet rs=null;
		String sql=null;
		Reply reply=new Reply();
		String children=null;
		sql="select * from reply where id=?";
		ps=DataBase.prepare(conn, sql);
		try 
		{
			ps.setInt(1,Integer.parseInt(id));
			rs=ps.executeQuery();
			while(rs.next())
			{
				children=rs.getString("children");
				String name=rs.getString("name");
				reply.setArticle(rs.getString("article"));
				reply.setName(name);
				reply.setId(rs.getInt("id"));
				reply.setRank(rank);
				reply.setFathername(faname);
				replies.add(reply);
				String[] childrenID=children.split(",");
				ReplyServer replyServer=new ReplyServer();
				int _rank=rank+1;
				for(int i=0;i<childrenID.length;i++)
				{
					replyServer.getReplys(replies,childrenID[i],_rank,name);
				}
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
		}
	}
}

