package com.luck.action;
import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import com.luck.util.CookiesUtil;
public class Create extends HttpServlet
{
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException 
	{
		doPost(req, resp);
	}
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException 
	{
		String uname=(String) req.getSession().getAttribute("name");
		req.setCharacterEncoding("utf-8");
		String name=req.getParameter("tiebaming");
		com.luck.model.TieBa tieBa=new com.luck.model.TieBa();
		tieBa.setName(name);
		try {
			new com.luck.servre.Baserver().add(tieBa);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		resp.setContentType("text/html;charset=gb2312");
		resp.sendRedirect(req.getContextPath()+"/enter");
	}
	
}
