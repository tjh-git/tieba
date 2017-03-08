package com.luck.action;
import java.io.IOException;

import com.luck.model.Message;
import com.luck.servre.*;

import java.io.*;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import com.luck.util.*;
public class Listen extends HttpServlet
{
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException 
	{
		doPost(req, resp);
	}
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException 
	{
		String name=(String) req.getSession().getAttribute("name");
		req.setCharacterEncoding("utf-8");
		resp.setCharacterEncoding("utf-8");
		String receiver=req.getParameter("receiver");
		String sender=req.getParameter("sender");
		int top=CookiesUtil.getTop(req);
		Message newmessage=null;
		if(!sender.equals("undefined"))
		newmessage=new MessageServer().getNewMessage(receiver,sender,top,resp);
		Writer writer=resp.getWriter();
		if(newmessage==null||newmessage.getMessage()==null)writer.write("null");else
		{
			String mesString=newmessage.getSender()+"@"+newmessage.getReceiver()+":"+newmessage.getMessage();
			writer.write(mesString);
		}
	}
}
