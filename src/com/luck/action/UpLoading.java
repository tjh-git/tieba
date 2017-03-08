package com.luck.action;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.luck.servre.UserServer;
import com.luck.util.CookiesUtil;
public class UpLoading extends HttpServlet 
{
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException 
	{
		doPost(req, resp);
	}
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException 
	{
		String path=req.getServletContext().getRealPath("/");
		String name=(String) req.getSession().getAttribute("name");
		req.setCharacterEncoding("utf-8");
		boolean isM=ServletFileUpload.isMultipartContent(req);
		if(isM)
		{
			FileItemFactory fif=new DiskFileItemFactory();
			InputStream is=null;
			OutputStream os=null;
			ServletFileUpload sfu=new ServletFileUpload(fif);
			try
			{
				List<FileItem> files=sfu.parseRequest(req);
				is=files.get(0).getInputStream();
				path=path+"/photos/";
				File image=new File(path);
				if(!image.exists()){image.mkdirs();}
				path=path+name;
				image=new File(path);
				os=new FileOutputStream(image);
				byte[] b=new byte[1024];
				int n=0;
				while((n=is.read(b))!=-1)
				{
					os.write(b,0,n);
				}
			}catch (FileUploadException e) 
			{
				e.printStackTrace();
			}finally
			{
				if(os!=null)os.close();
				if(is!=null)is.close();
			}
		}
		req.setAttribute("name", name);
		this.getServletConfig().getServletContext().getRequestDispatcher("/login_success.jsp").forward(req, resp);
	}
}
