package com.luck.action;
import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import com.luck.servre.*;
import com.luck.util.CookiesUtil;
public class LoveIt extends HttpServlet 
{
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException 
	{
		doPost(req, resp);
	}
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException 
	{
		String name=(String) req.getSession().getAttribute("name");
		req.setCharacterEncoding("utf-8");
		String id=req.getParameter("id");
		try
		{
			new UserServer().addLove(id,name);
		} catch (SQLException e) 
		{
			e.printStackTrace();
		}
		resp.setContentType("text/html;charset=gb2312");
		resp.sendRedirect(req.getContextPath()+"/enter");
	}

}
