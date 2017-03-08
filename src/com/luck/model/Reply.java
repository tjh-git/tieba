package com.luck.model;

import java.util.ArrayList;
import java.util.List;

public class Reply 
{
	private int rank=0;
	private String article;
	private int id;
	private int fatherID=-1;
	private List<Integer> childID=new ArrayList<Integer>();
	private String name;
	private String fathername;
	public String getFathername() 
	{
		return fathername;
	}
	public void setFathername(String fathername) 
	{
		this.fathername = fathername;
	}
	public int getRank()
	{
		return rank;
	}
	public void setRank(int rank) 
	{
		this.rank = rank;
	}
	public String getName() 
	{
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Integer> getChildID() 
	{
		return childID;
	}
	public void add(int childID) 
	{
		this.childID.add(childID);
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
	public int getFatherID() 
	{
		return fatherID;
	}
	public void setFatherID(int fatherID) 
	{
		this.fatherID = fatherID;
	}
	
}
