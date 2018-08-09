<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE HTML >
<html>
  <head>
    <title>请求登录</title>
	<meta charset="utf-8">
    <meta name="keywords" content="keyword1,keyword2,keyword3">
    <meta name="description" content="this is my page">
    <meta name="content-type" content="text/html; charset=UTF-8">
	<script type="text/javascript" src="js/jquery-3.3.1.min.js"></script>
	<style type="text/css">
		*{margin:0;padding:0;}
		html,body{width:100%;height:100%;}
		body{background:url(img/index3.jpg) no-repeat center;overflow:hidden;}
		h3{width:600px;margin:250px 0 0 150px;color:red;text-align:center;font-size:50px;font-weight:bold;letter-spacing:10px;}
	</style>
  </head>
  <body>
  	<%
  		session.invalidate();
  		response.setHeader("refresh","3;URL=LoginByFace.html");
  	%>
    <h3>3s后返回登录页...</h3>
</html>
