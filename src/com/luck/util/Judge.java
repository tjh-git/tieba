package com.luck.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

import javax.imageio.*;
public class Judge 
{
	public static Color getRandColor(int fc,int bc)
	{
		Random random = new Random();
        if(fc>255) fc=255;
        if(bc>255) bc=255;
        int r=fc+random.nextInt(bc-fc);
        int g=fc+random.nextInt(bc-fc);
        int b=fc+random.nextInt(bc-fc);
        return new Color(r,g,b);
    }
	public static String get(String string)
	{
		int width=60, height=20;
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		Graphics g = image.getGraphics();
		Random random = new Random();
		g.setColor(getRandColor(200,250));
		g.fillRect(0, 0, width, height);
		g.setFont(new Font("Times New Roman",Font.PLAIN,18));
		g.setColor(getRandColor(160,200));
		for (int i=0;i<100;i++)
		{
			int x = random.nextInt(width);
			int y = random.nextInt(height);
			int xl = random.nextInt(12);
			int yl = random.nextInt(12);
			g.drawLine(x,y,x+xl,y+yl);
		}
		String codeList = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890";
		String sRand="";
		for (int i=0;i<4;i++)
		{
			int a=random.nextInt(codeList.length()-1);
			String rand=codeList.substring(a,a+1);
			sRand+=rand;
			g.setColor(new Color(20+random.nextInt(110),20+random.nextInt(110),20+random.nextInt(110)));
			g.drawString(rand,13*i+6,16);
		}
		System.out.println(string);
		File f=new File(string);
		try
		{
			if(!f.exists())
			{
				f.createNewFile();
			}
			FileOutputStream fos=new FileOutputStream(f);
			g.dispose();
			ImageIO	.write(image, "JPEG",fos);
		}catch(IOException e)
		{
		}
		return sRand;
	}
}
	