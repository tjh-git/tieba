package com.luck.action;
import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import com.luck.model.Message;
import com.luck.servre.*;
import com.luck.util.CookiesUtil;
public class Communication extends HttpServlet
{
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException 
	{
		doPost(req, resp);
	}
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException 
	{
		String name=(String) req.getSession().getAttribute("name");
		req.setCharacterEncoding("utf-8");
		String sender=req.getParameter("sender");
		if(!sender.equals(name))
		{
			resp.setContentType("text/html;charset=utf-8");
			resp.sendRedirect(req.getContextPath()+"/error.jsp");
		}
		String receiver=req.getParameter("receiver");
		String article=req.getParameter("message");
		Message message=new Message();
		message.setMessage(article);
		message.setReceiver(receiver);
		message.setSender(sender);
		try 
		{
			new MessageServer().add(message);
		} catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}
}
