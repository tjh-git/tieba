package com.luck.util;

public class text 
{
	public static void main(String[] args) 
	{
		java.util.Map<String, Integer> map=new java.util.HashMap<String, Integer>();
		String a1=new String("vvv");
		String a2=new String("ccc");
		String a3=new String("xxx");
		map.put(a1, 7);
		map.put(a2, 8);
		map.put(a3, 9);
		map.put("xxx", 9);
		System.out.println(map.get("vvv"));
		System.out.println(map.get("xxx"));
		System.out.println(map.get("ccc"));
		for(Integer s:map.values())
		{
			System.out.println(s);
		}
	}
}
