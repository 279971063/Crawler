<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"  %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<c:set var="path"  value="${pageContext.request.contextPath}"/>
<html>
<head>
<title>Crawler</title>
</head>
<%
request.setCharacterEncoding("utf-8");
response.setCharacterEncoding("utf-8");

 %>
<frameset rows="*" cols="180,*" frameborder="no" border="0" framespacing="0">
  <frame src="left.jsp" name="leftFrame" scrolling="auto" noresize="noresize" id="leftFrame" title="leftFrame" />
  <frameset rows="50,*" frameborder="no" border="0" framespacing="0">
    <frame src="head.jsp" name="topFrame" scrolling="0" noresize="noresize" id="topFrame" title="topFrame" />
    <frame src="right.jsp" name="mainFrame" id="mainFrame" title="mainFrame" />
  </frameset>	
</frameset>

</html>
