package com.luck.action;
import java.io.IOException;
import java.net.URLEncoder;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import com.luck.servre.UserServer;
import com.luck.util.*;
public class LogIn extends HttpServlet 
{
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException 
	{
		doPost(req, resp);
	}
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException 
	{
		req.setCharacterEncoding("utf-8");
		if(!req.getParameter("yanzhengma").toLowerCase().equals((String)req.getSession().getAttribute("stand").toString().toLowerCase()))
		{
			resp.getWriter().write("请输入正确的验证码");  
			return;
		}
		String name=req.getParameter("login_name");
		try 
		{
			String password=new UserServer().getPassword(name);
			if(password==null||!password.equals(req.getParameter("login_password")))
			{
				resp.setContentType("text/html;charset=gb2312");
				resp.sendRedirect(req.getContextPath()+"/login_error.jsp");
				return;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		HttpSession session=req.getSession(true);
		session.setAttribute("name",name);
		this.getServletConfig().getServletContext().getRequestDispatcher("/login_success.jsp").forward(req, resp);
	}
}
