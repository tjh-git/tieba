package com.luck.action;
import java.io.IOException;
import java.sql.SQLException;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import com.luck.model.*;
import com.luck.util.CookiesUtil;
public class Enter extends HttpServlet 
{
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException 
	{
		doPost(req, resp);
	}
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException 
	{
		String name=(String) req.getSession().getAttribute("name");
		req.setCharacterEncoding("utf-8");
		String loves=null;
		try 
		{
			loves=new com.luck.servre.UserServer().getLoving(name);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String[] loveStrings=null;
		if(loves!=null)loveStrings=loves.split(",");
		List<User> users=null;
		List<TieBa> tieBas=null;
		try 
		{
			users=new com.luck.servre.UserServer().list();
			tieBas=new com.luck.servre.Baserver().list();
		} catch (SQLException e) 
		{
			e.printStackTrace();
		}
		for(int i=0;i<tieBas.size();i++)
		for(int j=1;j<loveStrings.length;j++)
		{
			if(tieBas.get(i).getName().equals(loveStrings[j]))
			{
				tieBas.get(i).setShow(true);
			}
		}
		req.setAttribute("users",users);
		req.setAttribute("sender",name);
		req.setAttribute("tiebas", tieBas);
		this.getServletConfig().getServletContext().getRequestDispatcher("/enter.jsp").forward(req, resp);
	}
}
