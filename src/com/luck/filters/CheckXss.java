package com.luck.filters;
import java.io.IOException;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
public class CheckXss implements Filter
{
	public void init(FilterConfig filterConfig) throws ServletException 
	{
		
	}
	public void doFilter(ServletRequest request, ServletResponse response,FilterChain chain) throws IOException, ServletException 
	{
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("gb2312");
		HttpServletRequest req=(HttpServletRequest)request;
		HttpServletResponse resp=(HttpServletResponse)response;
		String url=req.getServletPath();
		String name=(String) req.getSession().getAttribute("name");
		if(url.indexOf("images")==-1&&url.indexOf("register.jsp")==-1&&url.indexOf("judgeid")==-1&&!url.equals("/index.jsp")&&url.indexOf("jquery.js")==-1&&url.indexOf("register")==-1&&url.indexOf("login")==-1&&url.indexOf("readyLogin")==-1&&url.indexOf("registerready")==-1&&url.indexOf("judgeyanzhengma")==-1)
		{
			if(name==null)
			{
				resp.getWriter().write("不要尝试越过权限！");
				return;
			}
		}
		Map<String, String[]> parameters=req.getParameterMap();
		for(String s:parameters.keySet())
		{
			for(String ss:parameters.get(s))
			{
				if(ss.trim().equals("")||ss == null)
				{
					resp.getWriter().write("请按要求行事。不能为空");
					return;
				}
				if(!matchXSS(ss))
				{
					resp.getWriter().write("请不要恶意攻击本站！");
					return;
				}
			}
		}
		try 
		{
			chain.doFilter(request, response);
		} catch (Exception e) 
		{
		}
	}
	public boolean matchXSS(String string)
	{
		Pattern p;
	    Matcher m;
	    try
	    {
	    	String reg_script = "<[\\s]*?script[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?script[\\s]*?>";
	        String reg_style = "<[\\s]*?style[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?style[\\s]*?>"; 
	        String reg_html = "<[^>]+>"; // 定义HTML标签的正则表达式
	        String patternStr = "\\s+";
	        p = Pattern.compile(reg_script, Pattern.CASE_INSENSITIVE);
	        m = p.matcher(string);
	        if(m.find())return false;
            p = Pattern.compile(reg_style, Pattern.CASE_INSENSITIVE);
            m = p.matcher(string);
            if(m.find())return false;
	        p = Pattern.compile(reg_html, Pattern.CASE_INSENSITIVE);
            m = p.matcher(string);
            if(m.find())return false;
	        p = Pattern.compile(patternStr, Pattern.CASE_INSENSITIVE);
            m = p.matcher(string);
            if(m.find())return false;
    	}catch (Exception e)
	    {
            System.err.println("Html2Text: " + e.getMessage());
        }
        return true;
	}
	public void destroy() 
	{
		
	}
}
