package com.luck.model;

public class Message  
{
	private String sender=null;
	private String receiver=null;
	private String message=null;
	private int id=-1;
	public int getId() 
	{
		return id;
	}
	public void setId(int id) 
	{
		this.id = id;
	}
	public String getSender() 
	{
		return sender;
	}
	public void setSender(String sender) 
	{
		this.sender = sender;
	}
	public String getReceiver() 
	{
		return receiver;
	}
	public void setReceiver(String receiver) 
	{
		this.receiver = receiver;
	}
	public String getMessage() 
	{
		return message;
	}
	public void setMessage(String message) 
	{
		this.message = message;
	}
}
