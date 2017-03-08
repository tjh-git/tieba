<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'Communication.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  	<%  
  		if(!(request.getParameter("sender").equals(request.getAttribute("userID"))))
  		{
  			response.setContentType("text/html;charset=utf-8");
			response.sendRedirect(request.getContextPath()+"/error.jsp");
  		}
  	  %> 
    <input type="hidden" id="receiver" value="${param.receiver}"/>
    <input type="hidden" id="sender" value="${param.sender}"/>
    <textarea id="text" cols="100"  rows="25"></textarea>
    <textarea id="message" cols="100"  rows="5"></textarea>
    </br>
    <input type="submit" onclick="com()"   value="回复">
  </body>
  <script type="text/javascript" src="js/jquery.js"></script>
  <script type="text/javascript">
		var req1;
		var req2;
		var id=0;
		var receiver=document.getElementById("receiver").value;
    	var	sender=document.getElementById("sender").value;
	   	var message;
	   	var url;
    	function com()
    	{
    		message=document.getElementById("message").value;
    		if(message!="")document.getElementById("text").value+=(sender+"@"+receiver+":"+message+"\n");
    		url="communication?receiver="+receiver+"&sender="+sender+"&message="+message;
    		document.getElementById("message").value="";
    		if(window.XMLHttpRequest)
   			{
   				req1=new XMLHttpRequest();
   			}else if(window.ActiveXObject)
   			{
   				try
   				{
   					req1=new ActiveXObject("Msxml2.XMLHTTP");
   				}catch(e)
   				{
   					req1=new ActiveXObject("Microsoft.XMLHTTP");
   				}
   			}
    		req1.open("get", url,true);
    		req1.send(null);
    	}
    	setInterval(callback,1000);
    	function callback()
    	{
    		if(window.XMLHttpRequest)
   			{
   				req2=new XMLHttpRequest();
   			}else if(window.ActiveXObject)
   			{
   				try
   				{
   					req2=new ActiveXObject("Msxml2.XMLHTTP");
   				}catch(e)
   				{
   					req2=new ActiveXObject("Microsoft.XMLHTTP");
   				}
   			}
    		url="listen?receiver="+receiver+"&sender="+sender;
    		req2.open("get", url,true);
    		req2.send(null);
    		req2.onreadystatechange=back;
    	}  
    	function back()
    	{
    		if(req2.readyState==4)
    		{
    			if(req2.responseText!="null")document.getElementById("text").value+=(req2.responseText+"\n");
    		}
    	}
  </script>
  <body>
</html>
