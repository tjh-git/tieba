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
    
    <title>My JSP 'register.jsp' starting page</title>
    
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
    UUID uuid=UUID.randomUUID();
	String stand=Judge.get(request.getRealPath("/")+"images\\"+uuid.toString());
   %>
  <body>
   <form name="register" method="post"  action="register" onsubmit="return checkagainp()" >
   	用户名:<input type="text" id="userid"  name="register_name"   onblur="checku()" /><div id="idinsert"></div>
   	</br>
   	  密码:<input type="password" id="password" name="register_password" />
   	  </br>
   	  重复密码:<input type="password" id="password2" name="register_password2"  onblur="checkp()" onfocus="checkp()"/><div id="insert"></div>
    <img alt="验证码" src="${requestScope.path}" >
   	  验证码：<input id="yanzhengma" name="yanzhengma" height="1"  onblur="check()"  onfocus="check()"/><div id="insert"></div>
   	  </br>
   	  <input type="submit" name="submit" value="注册" />
   </form>
</html>

<script type="text/javascript" src="js/jquery.js"></script>
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
    		req.open("get", url,true);
    		req.send(null);
    		req.onreadystatechange=backa;
  	}
  	function backa()
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
  	function checkp()
  	{
  		var password=$('#password').val();
  		var password2=$('#password2').val();
  		if($.trim(password)=="")
  		{
  			document.getElementById("insert").innerHTML = "<font color='red'>密码不能为空</font>";
  			return;
  		}
  		if(password2!=password)
  		{
  			document.getElementById("insert").innerHTML = "<font color='red'>密码不一致！</font>";
  		}else
  		{
  			document.getElementById("insert").innerHTML = "<font color='green'>√</font>";
  		}
  	}
  	var req;
  	function checku()
  	{
  		var id=$('#userid').val();
  		var url="judgeid"+"?id="+id;
  		if(window.XMLHttpRequest)
   		{
   			req=new XMLHttpRequest();
   		}else 
   		if(window.ActiveXObject)
   		{
   			try
   			{
   				req=new ActiveXObject("Msxml2.XMLHTTP");
   			}catch(e)
   			{
   				req=new ActiveXObject("Microsoft.XMLHTTP");
   			}
   		}
    	req.open("get", url,true);
    	req.send(null);
    	req.onreadystatechange=back;
  	}
  
  	function back()
   	{
   		if(req.readyState==4)
   		{
   			if(req.responseText!="ok")document.getElementById("idinsert").innerHTML = "<font color='red'>该用户名已被注册</font>";else
   			document.getElementById("idinsert").innerHTML = "<font color='green'>可以注册</font>";
   		}
   	}
    	
  	function checkagainp()
  	{
  		var password=$('#password').val();
  		var password2=$('#password2').val();
  		var userid=$('#userid').val();
   		if($.trim(userid)=="")
   		{
   			alert("请设置非空格的用户名！");
   			return false;
   		}else
   		if($.trim(password)=="")
   		{
   			alert("密码不能为空！");
   			return false;
   		}else
  		if(password!=password2)
  		{
  			alert("密码不一致")
  			return false;
  		}
		var stand=document.getElementById("stand").value.toLowerCase();
  		var userInput=document.getElementById("yanzhengma").value.toLowerCase();
  		if(stand!=userInput)
  		{
  			document.getElementById("insertyanzhengma").innerHTML = "<font color='red'>验证码错误</font>";
  			return false;
  		}else
  		{
  			document.getElementById("insertyanzhengma").innerHTML = "<font color='green'>√</font>";
  			return true;
  		}
  	}
    </script> 