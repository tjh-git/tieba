package com.luck.action;
import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import com.luck.model.User;
import com.luck.servre.UserServer;
import com.luck.util.CleanCookies;
import com.luck.util.CookiesUtil;
public class Register extends HttpServlet
{
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException 
	{
		doPost(req, resp);
	}
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException 
	{
		if(!req.getParameter("yanzhengma").toLowerCase().equals((String)req.getSession().getAttribute("stand").toString().toLowerCase()))
		{
			resp.getWriter().write("请输入正确的验证码");  
			return;
		}
		if(!req.getParameter("register_password").equals(req.getParameter("register_password2")))
		{
			resp.getWriter().write("请按要求行事");
			return;
		}
		req.setCharacterEncoding("utf-8");
		User user=new User();
		
		user.setName(req.getParameter("register_name"));
		user.setPassword(req.getParameter("register_password"));
		
		req.setAttribute("user",user.getName());
		HttpSession session=req.getSession(true);
		session.setAttribute("name",user.getName());
		try 
		{
			new UserServer().add(user);
		} catch (Exception e) {
			System.out.println("注册失败");
			e.printStackTrace();
			resp.setContentType("text/html;charset=utf-8");
			resp.sendRedirect(req.getContextPath()+"/register_error.jsp");
			return;
		}
		this.getServletConfig().getServletContext().getRequestDispatcher("/register_success.jsp").forward(req, resp);
	}
}
