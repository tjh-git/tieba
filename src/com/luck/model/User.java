package com.luck.model;

public class User extends Check
{
	private int id;
	private String name;
	private String password;
	private String path;
	private String love=null;
	public String getLove() 
	{
		return love;
	}
	public void setLove(String love) 
	{
		this.love = love;
	}
	public String getPath() 
	{
		return path;
	}
	public void setPath(String path) 
	{
		this.path = path;
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
	public String getPassword() 
	{
		return password;
	}
	public void setPassword(String password) 
	{
		this.password = password;
	}
}
