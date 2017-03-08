package com.luck.action;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.luck.model.Page;
import com.luck.util.CookiesUtil;

public class Add extends HttpServlet 
{
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException 
	{
		doPost(req, resp);
	}
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException 
	{
		String name=(String) req.getSession().getAttribute("name");
		req.setCharacterEncoding("utf-8");
		String baname=req.getParameter("baname");
		Page page=new Page();
		page.setName(name);
		page.setTitle(req.getParameter("add_title"));
		page.setArticle(req.getParameter("add_article"));
		int id=-1;
		try 
		{
			id=new com.luck.servre.TBServer().add(page);
			new com.luck.servre.Baserver().addPage(baname,String.valueOf(id));
		} catch (Exception e) 
		{
			e.printStackTrace();
		}
		System.out.println("789456");
		resp.setContentType("text/html;charset=gb2312");
		resp.sendRedirect(req.getContextPath()+"/show?name="+baname);
	}
	
}
