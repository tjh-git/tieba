package com.luck.action;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import com.luck.model.Page;
import com.luck.servre.*;
import com.luck.util.CookiesUtil;

import javax.servlet.ServletException;
import javax.servlet.http.*;
public class Show extends HttpServlet 
{
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException 
	{
		doPost(req, resp);
	}
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException 
	{

		String name=(String) req.getSession().getAttribute("name");
		req.setCharacterEncoding("utf-8");
		String baname=req.getParameter("name");
		String ids=new com.luck.servre.Baserver().getIDS(baname);
		String[] idStrings=null;
		if(ids!=null)
		idStrings=ids.split(",");
		TBServer tbServer=new TBServer();
		List<Page> pages=null;
		try 
		{
			pages=tbServer.list();
			for(int i=0;i<pages.size();i++)
			{
				for(int j=1;j<idStrings.length;j++)
				{
					if(pages.get(i).getId()==Integer.parseInt(idStrings[j]))
					{
						pages.get(i).setShow(true);
					}
				}
			}
		} catch (Exception e) 
		{
		}
		req.setAttribute("ids",ids);
		req.setAttribute("baname",baname);
		req.setAttribute("pages",pages);
		req.setAttribute("userID", name);
		this.getServletConfig().getServletContext().getRequestDispatcher("/show.jsp").forward(req, resp);
	}
}
