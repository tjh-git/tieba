package com.luck.action;
import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.luck.model.Reply;
import com.luck.servre.ReplyServer;
import com.luck.servre.TBServer;
import com.luck.util.CookiesUtil;
public class DoReply extends HttpServlet 
{
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException 
	{
		doPost(req, resp);
	}
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException 
	{
		req.setCharacterEncoding("utf-8");
		String name=req.getParameter("reply_name");
		int fid=Integer.parseInt(req.getParameter("reply_id"));
		String article=req.getParameter("reply_article");
		int mainID=Integer.parseInt(req.getParameter("mainid"));
		try 
		{
			new TBServer().addReply(mainID);
		} catch (SQLException e1) 
		{
			e1.printStackTrace();
		}
		Reply reply=new Reply();
		int id=-1;
		reply.setArticle(article);
		reply.setFatherID(fid);
		reply.setName(name);
		ReplyServer rServer=new ReplyServer();
		try 
		{
			id=rServer.add(reply);
			rServer.addChildren(id,fid);
		} catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}
	
}
