package com.luck.action;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import com.luck.util.CookiesUtil;
public class Zan extends HttpServlet
{
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException 
	{
		doPost(req, resp);
	}
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException 
	{
		String name=(String) req.getSession().getAttribute("name");
		req.setCharacterEncoding("utf-8");
		String zan=req.getParameter("ID");
		try 
		{
			new com.luck.servre.TBServer().zan(zan);
		} catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
}
