<%@page import="com.luck.servre.TBServer"%>
<%@page import="com.luck.util.DataBase"%>
<%@page import="com.luck.model.Page"%>
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
    
    <title>My JSP 'show.jsp' starting page</title>
    
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
  	<c:forEach var="page" items="${pages}">  
  	<c:if test="${page.show == true}">
  	${page.name}:&nbsp;&nbsp;&nbsp;
    <a href="detail?ID=${page.id}&baname=<%=request.getAttribute("baname") %>"><c:out value="${page.title}"/>&nbsp;</a>  
    <a onclick="zan('${page.id}','<%= request.getAttribute("baname")%>','${page.zan}')" >赞 </a><font id="zan${page.id}"  clicked="false">${page.zan}</font>
    &nbsp;&nbsp;&nbsp;&nbsp;
    回复数：${page.replyNumber}
    
	<c:if test="${page.show != true}">
		<a href="loveit?id=${page.id}">喜欢</a>
	</c:if>
	
    </br>
    </c:if>
 	</c:forEach>  
	<form  id="form" method="post"  action="add_success" onsubmit="return check();" >
	发帖子：</br>
   	标题：<input type="text" id="text" name="add_title"/>
   	  <input type="hidden" name="baname"   value=<%=request.getAttribute("baname") %> />
   	    内容:<textarea id="textarea"  name="add_article" ></textarea>
   	  </br>
   	  <input type="submit" name="submit"  value="提交"/>
   </form>
   <script type="text/javascript" src="js/jquery.js"></script>
   <script type="text/javascript">
   	function zan(a,b,c)
   	{
   		var id="zan"+a;
   		var req;
   		if($("#"+id).attr("clicked")=="true") return;
   		document.getElementById("zan"+a).firstChild.nodeValue=parseInt(c)+1;
   		$("#"+id).attr("clicked","true");
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
    		url="zan?ID="+a+"&baname="+b;
    		req.open("get", url,true);
    		req.send(null);
    		req.onreadystatechange=back;
   	}
   	function back()
   	{
   		alert(req.readyState);
   		if(req.readyState==4&&req.status==200)
    	{
    			alert("已赞");
    	}
   	}
   	function check()
   	{
   		var text=$('#text').val();
   		var textarea=$('#textarea').val(); 
   		if($.trim(text)=="")
   		{
   			alert("请输入标题");
   			return false;
   		}else
   		if($.trim(textarea)=="")
   		{
   			alert("请输入内容");
   			return false;
   		}
   		return true;
   	}
   </script>
  </body>
</html>
