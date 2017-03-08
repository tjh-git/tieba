<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'showOne.jsp' starting page</title>
    
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
    ${page.title}</br>
    <hr>
   <input type="hidden" id="mainid" value="${page.id}" >
   楼主： ${page.article}</br>
   <div id="relies">
   <c:forEach var="reply" items="${replies}">
   <c:forEach begin="1" end="${reply.rank}">
   	 &nbsp;
   </c:forEach> 
   ${reply.name}@${reply.fathername}:
   ${reply.article}
    <a onclick="onc(${reply.id})"  onblur="onc(${reply.id})">回复</a>
		<textarea style='display:none;' name="reply_article" id="text${reply.id}" /></textarea> 
		<input type="submit" style="display:none;"  onclick="reply(${reply.id})"  value="回复" id='sub${reply.id}'/> 
		<input type="hidden" name="reply_id"   id="id${reply.id}"      value="${reply.id}">
		<input type="hidden" name="reply_name"   id="name${reply.id}"  value="${reply.name}" >
	</br>
 	</c:forEach>   
     <form name="replay" method="post" action="replyMain" onsubmit="return check();">
    	回复楼主<textarea id="textarea" name="replay_article"></textarea>
    	<input type="hidden" name="fatherID" value="${page.id}">
    	<input type="hidden" name="baname" value="<%= request.getAttribute("baname") %>" >
    	<input type="submit" value="回复" >
    </form>
    <a href="show?name=<%=request.getAttribute("baname") %>" >返回</a>
    </div>
    <script type="text/javascript" src="js/jquery.js"></script>
   <script type="text/javascript">
   	function check()
   	{
   		var textarea=$('#textarea').val(); 
   		if($.trim(textarea)=="")
   		{
   			alert("请输入内容");
   			return false;
   		}
   		return true;
   	}
   	</script>
   	
   	
    <script type="text/javascript">
	function onc(form)    
    {
    	var te="text"+form;
    	if (document.getElementById(te).style.display=='none')
			{
				document.getElementById(te).style.display='block'; 
			}else
			{
				document.getElementById(te).style.display='none'; 
		 	}	
		te="sub"+form; 	
		 	if (document.getElementById(te).style.display=='none')
			{
				document.getElementById(te).style.display='block'; 
			}else
			{
				document.getElementById(te).style.display='none'; 
		 	}	
    }
    </script>
    
    <script type="text/javascript">
    	var req;
    	function reply(n)
    	{
	   		var text=document.getElementById("text"+n).value;
   			if($.trim(text)=="")
   			{
   				alert("请输入内容");
   				return;
   			}
	   		var id=document.getElementById("id"+n).value;
	   		var name=document.getElementById("name"+n).value;
	   		var mainid=document.getElementById("mainid").value;
	   		var url="reply?reply_name="+name+"&reply_id="+id+"&reply_article="+text+"&mainid="+mainid;
    		if(window.XMLHttpRequest)
    		{
    			req=new XMLHttpRequest();
    		}else if(window.ActiveXObject("Microsoft.XMLHTTP"))
    		{
    			req=new ActiveXObject("Microsoft.XMLHTTP");
    		}
    		req.open("get", url,true);
    		req.onreadystatechange=callback;
    		req.send(null);
    		
    	}
    	function callback()
    	{
    		if(req.readyState==4&&req.status==200)
    		{
    			window.location.reload();
    		}
    	}
    </script>
  </body>
</html>