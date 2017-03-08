<%@page import="com.luck.util.Judge"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'index.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
  </head>
  <body>
   	 欢迎来到贴吧 <br>
         欢迎登陆<br>
   <form name="login" method="post"  action="login" onsubmit="return check()">
   	用户名:<input type="text" name="login_name"/>
   	</br>
   	  密码:<input type="password" name="login_password">
   	  </br>
   	  <div id="flush">
   	  <img alt="验证码" src="${requestScope.path}" >
   	  <br>
   	  验证码：<input id="yanzhengma" name="yanzhengma" height="1"  onblur="check()"  onfocus="check()"/><div id="insert"></div>
   	 </div>
   	 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <input type="submit" name="submit" value="登陆">
   </form>
    <a href="registerready">注册</a>
  </body>
  <script type="text/javascript">
  	var req;
  	function check()
  	{
  		var userInput=document.getElementById("yanzhengma").value.toLowerCase();
  		var url="judgeyanzhengma?yanzhengma="+userInput;
  		if(window.XMLHttpRequest)
   			{
   				req=new XMLHttpRequest();
   			}else if(window.ActiveXObject)
   			{
   				try
   				{
   					req=new ActiveXObject("Msxml2.XMLHTTP");
   				}catch(e)
   				{
   					req=new ActiveXObject("Microsoft.XMLHTTP");
   				}
   			}
    		req.open("get",url,true);
    		req.send(null);
    		req.onreadystatechange=back;
  	}
  	function back()
  	{
  		if(req.readyState==4)
    	{
    		if(req.responseText!="ok")
  			{
  				document.getElementById("insert").innerHTML = "<font color='red'>验证码错误</font>";
 	 		}else
 	 		{
 	 			document.getElementById("insert").innerHTML = "<font color='green'>√</font>";
 	 		}
    	}
  	}
  </script>
</html>
