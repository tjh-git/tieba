package com.luck.action;
import java.io.IOException;
import java.io.Writer;
import java.sql.SQLException;

import com.luck.servre.*;
import com.luck.util.CookiesUtil;

import javax.servlet.ServletException;
import javax.servlet.http.*;
public class JudgeID extends HttpServlet
{
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException 
	{
		doPost(req, resp);
	}
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException 
	{
		req.setCharacterEncoding("utf-8");
		String name=req.getParameter("id");
		String result="ok";
		try 
		{
			result=new UserServer().check(name);
		} catch (SQLException e) 
		{
			e.printStackTrace();
		}
		resp.getWriter().write(result);
	}

}
