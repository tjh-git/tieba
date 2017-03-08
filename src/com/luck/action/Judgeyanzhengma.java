package com.luck.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Judgeyanzhengma extends HttpServlet 
{
	protected void service(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException 
	{
		resp.setCharacterEncoding("utf-8");
		String yanzhengma=req.getParameter("yanzhengma");
		if(yanzhengma.toLowerCase().equals((String)req.getSession().getAttribute("stand").toString().toLowerCase()))
		{
			resp.getWriter().write("ok");
		}else resp.getWriter().write("no");
	}
	
}
