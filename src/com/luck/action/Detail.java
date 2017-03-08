package com.luck.action;
import java.io.IOException;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import com.luck.model.Page;
import com.luck.model.Reply;
import com.luck.servre.TBServer;
import com.luck.util.CookiesUtil;
public class Detail extends HttpServlet
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
		String id=req.getParameter("ID");
		TBServer tbServer=new TBServer();
		Page page=tbServer.getOneByID(id);
		req.setAttribute("page", page);
		List<Reply> replies=tbServer.getReplys(id);
		req.setAttribute("replies", replies);
		req.setAttribute("userID",name);
		req.setAttribute("baname",baname);
		this.getServletConfig().getServletContext().getRequestDispatcher("/showOne.jsp").forward(req, resp);
	}
}
