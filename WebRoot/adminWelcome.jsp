<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE HTML >
<html>
  <head>
    <title>欢迎界面</title>
	<meta charset="utf-8">
    <meta name="keywords" content="keyword1,keyword2,keyword3">
    <meta name="description" content="this is my page">
    <meta name="content-type" content="text/html; charset=UTF-8">
	<script type="text/javascript" src="js/jquery-3.3.1.min.js"></script>
	<style type="text/css">
		*{margin:0;padding:0;}
		html,body{width:100%;height:100%;}
		body{background:url(img/admin.jpg) no-repeat center;overflow:hidden;}
		h3{text-align:center;margin:120px auto 0;font-size:50px;letter-spacing:10px;}
		span{color:red;font-weight:bold;font-size:80px;text-decoration:underline;}
	</style>
  </head>
  <body>
  	<%
  		String adusername=(String)session.getAttribute("username");
  		String adpassword=(String)session.getAttribute("username");
  		session.setAttribute("adusername",adusername);
  		session.setAttribute("adpassword",adpassword);
  	%>
    <h3>欢迎:&nbsp;<span><%=adusername %></span><br><br>正在跳往管理员界面......</h3>
  </body>
  	<script type="text/javascript">
		setTimeout(function(){
			window.location.href="${pageContext.request.contextPath }/FindAllServlet";
		},3000);
	</script>
</html>
