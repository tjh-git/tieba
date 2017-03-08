package com.luck.action;
import java.io.IOException;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.luck.util.Judge;
public class ReadyLogin extends HttpServlet 
{
	protected void service(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException 
	{
		String path=req.getServletPath();
		UUID uuid=UUID.randomUUID();
		String stand=new Judge().get(req.getRealPath("/")+"images/"+uuid.toString());
		req.getSession().setAttribute("stand", stand);
		req.setAttribute("path", req.getContextPath()+"/images/"+uuid.toString());
		if(path.indexOf("readyLogin")!=-1)
		this.getServletConfig().getServletContext().getRequestDispatcher("/index.jsp").forward(req, resp);//else 
		{
			this.getServletConfig().getServletContext().getRequestDispatcher("/register.jsp").forward(req, resp);
		}
	}
	
}
