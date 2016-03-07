<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<%
	request.setCharacterEncoding("utf-8");
	response.setCharacterEncoding("utf-8");
%>
<script type="text/javascript" src="js/jquery-1.9.1.js"></script>
<script type="text/javascript">
	function checkId(field, url) {

		var idCard = field.value;
		if (idCard == "") {
			$("#myhidden").attr("value", "");
			document.getElementById("namemessage").innerHTML = "请输入关键词";
			return;
		} else {

			if (window.ActiveXObject) // IE浏览器
			{
				xmlHttpRequest = new ActiveXObject("Microsoft.XMLHTTP");
			} else if (window.XMLHttpRequest) //除IE外的其他浏览器实现
			{
				xmlHttpRequest = new XMLHttpRequest();
			}

			if (null != xmlHttpRequest) {

				//当利用get方法访问服务器端时带参数的话，直接在"AjaxServlet"后面加参数，                   下面send方法为参数null就可以，用post方法这必须在把参数加在send参数内，如下

				xmlHttpRequest.open("get", url + "?keyword=" + idCard, true);
				//关联好ajax的回调函数
				xmlHttpRequest.onreadystatechange = ajaxCallback;
				//真正向服务器端发送数据
				// 使用post方式提交，必须要加上如下一行,get方法就不必加此句

				xmlHttpRequest.setRequestHeader("Content-Type",
						"application/x-www-form-urlencoded");

				xmlHttpRequest.send(null);
			}

		}
	}

	function ajaxCallback() { //ajax一次请求会改变四次状态，所以我们在第四次(即一次请求结束)进行处理就OK,
		if (xmlHttpRequest.readyState == 4) { //请求成功
			if (xmlHttpRequest.status == 200) {
				var responseText = xmlHttpRequest.responseText;

				if (responseText == "*关键字可用*") {
					$("#myhidden").attr("value", "keyWord_add");
					
					document.getElementById("namemessage").innerHTML = responseText;
				} else if (responseText == "*关键字已存在*") {
					$("#myhidden").val('');
					document.getElementById("namemessage").innerHTML = responseText;
				} else if (responseText == "0") {
					alert("添加失败！");
				} else if (responseText == "1") {
					alert("添加成功！");
					$("#myhidden").val('');
					$("#keyword").val('');
					document.getElementById("namemessage").innerHTML = "请输入关键词";
				}

			}
		}
	}

	function addkey() {
		if ($("#myhidden").val() == "") {
			return;
		}
		var keyvalue = $("#keyword").val();
		var url = $("#myhidden").val();
		if (window.ActiveXObject) // IE浏览器
		{
			xmlHttpRequest = new ActiveXObject("Microsoft.XMLHTTP");
		} else if (window.XMLHttpRequest) //除IE外的其他浏览器实现
		{
			xmlHttpRequest = new XMLHttpRequest();
		}

		if (null != xmlHttpRequest) {

			//当利用get方法访问服务器端时带参数的话，直接在"AjaxServlet"后面加参数，                   下面send方法为参数null就可以，用post方法这必须在把参数加在send参数内，如下

			xmlHttpRequest.open("get", url + "?keyword=" + keyvalue, true);
			//关联好ajax的回调函数
			xmlHttpRequest.onreadystatechange = ajaxCallback;
			//真正向服务器端发送数据
			// 使用post方式提交，必须要加上如下一行,get方法就不必加此句

			xmlHttpRequest.setRequestHeader("Content-Type",
					"application/x-www-form-urlencoded");

			xmlHttpRequest.send(null);
		}
	}
</script>

<body>
	<center>
		<font size="5" style="color: #23ad07; font-family: 微软雅黑">学生管理</font>
	</center>
	<br />
	<br />
	<center>

		<table class="ta" width="800px">
			<tr bgColor="#6fdd0">
				<td>词条名</td>
				<td><input type="hidden" id="myhidden"> <input
					type="text" name="keyword" id="keyword"
					onblur="checkId(this,'keyWord_exists')" /></td>
				<td><span id="namemessage" style="color: black;" width="100px"></span></td>
			</tr>

			<tr bgColor="#6fdd0">
				<td colspan="3"><input type="button" value="添加" class="buStyle"
					id="btadd" onclick="addkey()" /></td>
			</tr>
		</table>

	</center>

</body>
</html>