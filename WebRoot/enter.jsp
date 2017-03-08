<%@page import="com.luck.util.Judge"%>
<%@page import="com.luck.util.CookiesUtil"%>
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
    
    <title>My JSP 'login.jsp' starting page</title>
    
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
 	<c:forEach var="user" items="${users}">  
  	${user.name}&nbsp;&nbsp;&nbsp;
  	<a href="redaycommunication?receiver=${user.name}&sender=${sender}">
  	
  	<img alt="头像" width="50" height="50"  src="<%=request.getContextPath()%>/photos/${user.name}">
  	
  	</a>
    </br>
 	</c:forEach>  
    <c:forEach var="tieba" items="${tiebas}">
    <a href="show?name=${tieba.name}" >${tieba.name}</a>
   <c:if test="${tieba.show == true}">
		已关注
	</c:if>
	 <c:if test="${tieba.show != true}">
		<a href="loveit?id=${tieba.name}">关注</a>
	</c:if>
    </br>
    </c:forEach>
    <form action="create" method="post">
   	 贴吧名称：<input name="tiebaming" />   
	</br>
    <input type="submit" value="创建贴吧"> 
    </form>
  </body>
</html>
