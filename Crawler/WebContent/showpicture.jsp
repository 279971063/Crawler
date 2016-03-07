<%@ page language="java" contentType="text/html;charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<html>
<head>
<title></title>
<c:set var="path" value="${pageContext.request.contextPath }" />
<link rel="stylesheet" href="${path }/css/general.css" />
<link rel="stylesheet" href="${path }/css/jquery-ui-1.8.20.custom.css" />
<script type="text/javascript" src="${path }/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript"
	src="${path }/js/jquery-ui-1.8.20.custom.min.js"></script>

</head>
<center>
	<body>
		<!-- 综合查询操作层 -->
		<div id="selectDiv">
			<s:form action="picture_search" theme="simple" target="mainFrame">
				<fieldset style="width: 80%">
					<legend>查询条件</legend>
					关键词:
					<s:textfield name="searchName" cssClass="textStyle" />
					<s:submit value="查  询" cssClass="buStyle" />
				</fieldset>
			</s:form>
		</div>
		<table class="ta">
			<tr>
				<th>标号</th>
				<th>链接地址</th>
				<th>隶属关键词</th>
			</tr>
			<c:forEach items="${requestScope.pictures }" var="key"
				varStatus="status">


				<tr bgColor="${status.index%2==0?'#e5fee2':'#d6fdd0' }">
					<td>${key.id }</td>
					<td><a href="${key.name }"></a>${key.name }</td>
					<td>${key.keyWord.value }</td>
				</tr>
			</c:forEach>
		</table>
		<br />
		<center>
			<div id="pageDir">
				<c:set var="pageCount" value="${(requestScope.count-1)/20+1 }" />
				<fmt:formatNumber var="lastIndex" value="${pageCount}" pattern="#" />
				<ul>
					<li style="margin-left: 25%;">第${requestScope.thisindex }/${lastIndex }页
					</li>
					<li style="margin-left: 40px;"><a href="picture_jump?index=1"
						target="mainFrame">首页</a> <c:forEach var="i" begin="1" step="1"
							end="${lastIndex }">
							<a href="picture_jump?index=${i } " target="mainFrame"><c:out
									value="${i }" /></a>
						</c:forEach> <a href="picture_jump?index=${lastIndex}" target="mainFrame">尾页</a>
					</li>
					<li style="margin-left: 40px;"><s:form action="picture_jump"
							theme="simple" target="mainFrame">
							第<s:textfield name="index" cssStyle="width:25px;height:20px;" />页
							<s:submit value="Go" id="go" />
						</s:form></li>
				</ul>
			</div>
		</center>
	</body>
</center>
</html>