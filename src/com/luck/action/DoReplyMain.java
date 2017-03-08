package com.luck.action;
import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import com.luck.model.Reply;
import com.luck.servre.ReplyServer;
import com.luck.servre.TBServer;
import com.luck.util.CookiesUtil;
public class DoReplyMain extends HttpServlet 
{
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException 
	{
		doPost(req, resp);
	}
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException 
	{
		String name=(String) req.getSession().getAttribute("name");
		req.setCharacterEncoding("utf-8");
		String s=req.getParameter("replay_article");
		int fid=Integer.parseInt(req.getParameter("fatherID"));
		int id=-1;
		Reply reply=new Reply();
		reply.setArticle(s);
		reply.setFatherID(fid);
		reply.setName(name);
		try 
		{
			new TBServer().addReply(fid);
		} catch (SQLException e1) 
		{
			e1.printStackTrace();
		}
		ReplyServer rServer=new ReplyServer();
		try 
		{
			id=rServer.add(reply);
			new TBServer().addChildren(id,fid);
		} catch (SQLException e) 
		{
			e.printStackTrace();
		}
		String baname=req.getParameter("baname");
		this.getServletConfig().getServletContext().getRequestDispatcher("/detail?ID="+fid+"&baname="+baname).forward(req, resp);
	}
}
