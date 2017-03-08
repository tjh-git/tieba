package com.luck.model;
import java.lang.reflect.*;
public class Check 
{
	private int uuuu;
	public boolean check()
	{
		Field[] fields=this.getClass().getDeclaredFields();
		for(Field f:fields)
		{
			System.out.println(f.toString());
		}
		return false;
	}
	
}
