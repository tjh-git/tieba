package com.luck.action;
import java.io.IOException;

import com.luck.servre.*;
import com.luck.util.*;

import javax.servlet.ServletException;
import javax.servlet.http.*;
public class RedayCommunication extends HttpServlet 
{
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException 
	{
		doPost(req, resp);
	}
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException 
	{
		String name=(String) req.getSession().getAttribute("name");
		req.setCharacterEncoding("utf-8");
		String receiver=req.getParameter("receiver");
		String sender=req.getParameter("sender");
		if(!sender.equals(name))
		{
			resp.setContentType("text/html;charset=utf-8");
			resp.sendRedirect(req.getContextPath()+"/error.jsp");
			return;
		}
		String top=new MessageServer().getTop(sender,receiver);
		CookiesUtil.setCookies(resp, "top", top);
		req.setAttribute("userID", name);
		this.getServletConfig().getServletContext().getRequestDispatcher("/communication.jsp?receiver="+receiver+"&sender="+sender).forward(req, resp);
	}
	
}
