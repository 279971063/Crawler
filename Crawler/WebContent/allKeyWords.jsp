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


<script type="text/javascript">
	function alertDelete(point) {
		var num = $(point).next(":hidden").val();
		//var num = $("#myhidden").val();

		var flag = confirm("确定要删除 " + num + " 的信息么？");
		if (flag) {
			window.location.href = "keyWord_delete?id=" + num;
		} else {

			return;
		}
	}

	function craw(point) {
		var keyvalue = $(point).next(":hidden").val();
		if ($(point).next(":hidden").val() == "") {
			return;
		}
		//var keyvalue = $("#myhidden2").val();
		if (window.ActiveXObject) // IE浏览器
		{
			xmlHttpRequest = new ActiveXObject("Microsoft.XMLHTTP");
		} else if (window.XMLHttpRequest) //除IE外的其他浏览器实现
		{
			xmlHttpRequest = new XMLHttpRequest();
		}

		if (null != xmlHttpRequest) {

			//当利用get方法访问服务器端时带参数的话，直接在"AjaxServlet"后面加参数，                   下面send方法为参数null就可以，用post方法这必须在把参数加在send参数内，如下

			xmlHttpRequest.open("get", "crawling_craw?keyvalue="
					+ encodeURI(encodeURI(keyvalue)), true);
			//关联好ajax的回调函数
			xmlHttpRequest.onreadystatechange = ajaxCallback;
			//真正向服务器端发送数据
			// 使用post方式提交，必须要加上如下一行,get方法就不必加此句

			xmlHttpRequest.setRequestHeader("Content-Type",
					"application/x-www-form-urlencoded");

			xmlHttpRequest.send(null);
		}

	}

	function ajaxCallback() { //ajax一次请求会改变四次状态，所以我们在第四次(即一次请求结束)进行处理就OK,
		if (xmlHttpRequest.readyState == 4) { //请求成功
			if (xmlHttpRequest.status == 200) {
				var responseText = xmlHttpRequest.responseText;

				if (responseText == "1") {
					alert("爬取成功！");
					window.location.href = "keyWord_seeAll";
				} else {
					alert("抓取失败！");
				}
			}
		}
	}
</script>
</head>
<center>
	<body>
		<!-- 综合查询操作层 -->
		<div id="selectDiv">
			<s:form action="keyWord_search" theme="simple" target="mainFrame">
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
				<th>关键词</th>
				<th>是否生成索引</th>
				<th>删除</th>
				<th>爬图</th>
			</tr>
			<c:forEach items="${requestScope.keywords }" var="key"
				varStatus="status">


				<tr bgColor="${status.index%2==0?'#e5fee2':'#d6fdd0' }">
					<td>${key.id }</td>
					<td>${key.value }</td>
					<td>${key.isadd }</td>
					<td><c:choose>
							<c:when test="${key.isadd==0 }">
								<a style="border-width: 0px" href="javascript:void(0)"
									onclick="alertDelete(this)" id="aid"><img
									src="${path }/images/delete.png" alt="删除" /></a>
							</c:when>
							<c:otherwise>
								<a style="border-width: 0px" href="#"><img
									src="${path }/images/delete.png" alt="已生成索引，无法删除" /></a>
							</c:otherwise>

						</c:choose> <input type="hidden" value="${key.id }" id="myhidden"></td>

					<td><c:choose>
							<c:when test="${key.isadd==0 }">
								<a style="border-width: 0px" href="javascript:void(0)"
									onclick="craw(this)" id="aid"><img
									src="${path }/images/edit.png" alt="开始" /></a>
							</c:when>
							<c:otherwise>
								<a style="border-width: 0px" href="#"><img
									src="${path }/images/edit.png" alt="已生成索引，无法重复爬图" /></a>
							</c:otherwise>

						</c:choose> <input type="hidden" value="${key.value }" id="myhidden2">
					</td>

				</tr>
			</c:forEach>
		</table>
		<br />
		<center>
			<div id="pageDir">
				<c:set var="pageCount" value="${(requestScope.count-1)/10+1 }" />
				<fmt:formatNumber var="lastIndex" value="${pageCount}" pattern="#" />
				<ul>
					<li style="margin-left: 25%;">第${requestScope.thisindex }/${lastIndex }页
					</li>
					<li style="margin-left: 40px;"><a href="keyWord_jump?index=0"
						target="mainFrame">首页</a> <c:forEach var="i" begin="1" step="1"
							end="${lastIndex }">
							<a href="keyWord_jump?index=${i } " target="mainFrame"><c:out
									value="${i }" /></a>
						</c:forEach> <a href="keyWord_jump?index=${lastIndex}" target="mainFrame">尾页</a>
					</li>
					<li style="margin-left: 40px;"><s:form action="keyWord_jump"
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