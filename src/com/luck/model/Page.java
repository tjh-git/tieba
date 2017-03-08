package com.luck.model;
import javax.servlet.http.*;
import java.util.*;
public class Page extends HttpServlet
{
	private int id;
	private String name;
	private String title;
	private String article;
	private int zan=0;
	private int replyNumber=0;
	private boolean show=false;
	public boolean isShow() 
	{
		return show;
	}
	public void setReplyNumber(int replyNumber)
	{
		this.replyNumber = replyNumber;
	}
	public int getReplyNumber() 
	{
		return replyNumber;
	}
	private List<Integer> childID=new ArrayList<Integer>();
	public List<Integer> getChildID() {
		
		return childID;
	}
	public void add(int childID) 
	{
		this.childID.add(childID);
	}
	public int getZan() 
	{
		return zan;
	}
	public void setZan(int zan) 
	{
		this.zan = zan;
	}
	public String getArticle() 
	{
		return article;
	}
	public void setArticle(String article) 
	{
		this.article = article;
	}
	public int getId() 
	{
		return id;
	}
	public void setId(int id) 
	{
		this.id = id;
	}
	public String getName() 
	{
		return name;
	}
	public void setName(String name) 
	{
		this.name = name;
	}
	public String getTitle() 
	{
		return title;
	}
	public void setTitle(String title) 
	{
		this.title = title;
	}
	public void setShow(boolean b)
	{
		this.show=b;
	}
}
