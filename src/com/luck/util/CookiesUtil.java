package com.luck.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import javax.servlet.http.*;

public class CookiesUtil 
{
	public static String judge(HttpServletRequest req)
	{
		Cookie[] cookies=req.getCookies();
		if(cookies==null)return null;
		String name=null;
		for(int i=0;i<cookies.length;i++)
		{
			if(cookies[i].getName().equals("name"))
			{	
				name=cookies[i].getValue();
				try {
					name=URLDecoder.decode(name,"UTF-8");
					return name;
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}
	public static int getTop(HttpServletRequest req)
	{
		Cookie[] cookies=req.getCookies();
		int top=-1;
		for(int i=0;i<cookies.length;i++)
		{
			if(cookies[i].getName().equals("top"))
			{
				top=Integer.parseInt(cookies[i].getValue());
				return top;
			}
		}
		return top;
	}
	public static void setCookies(HttpServletResponse resp,String name,String value)
	{
		Cookie cookie=new Cookie(name, value);
		resp.addCookie(cookie);
	}
}
