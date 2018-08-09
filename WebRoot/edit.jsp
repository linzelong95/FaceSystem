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
		body{background:url(img/adminEdit.jpg) repeat center;overflow:hidden;}
		input{height:30px;width:200px;}
		button{background:orange;height:40px;width:100px;border-radius:50px;margin:10px 60px 0 10px;}
		#f{position: absolute;left:50%;top:50%;margin-left:-100px;margin-top:-100px;}
		span{color:white;font-weight:bold;}
		p{color:blue;font-weight:bold;font-size:20px;}
	</style>
  </head>
  <body>
  	<%if(session.getAttribute("adusername")!=null){ %>
  	<div id="f">
    	<form action="${pageContext.request.contextPath }/EditServlet" method="post">
    		<input type="hidden" name="id" value="${u.id }"><br>
    		<span>用户账号：</span><input type="text" name="username" value="${u.username }"><br><br>
    		<span>用户密码：</span><input type="text" name="password" value="${u.password }"><br><br>
    		<span>图片路径：</span><input type="text" name="imgsrc" value="${u.imgsrc }"><br><br>
    		<button type="submit"><p>修&nbsp;&nbsp;改</p></button>
    		<button id="btn"><p>返&nbsp;&nbsp;回</p></button>
    	</form>
    </div>
    <%}else{ %>
  			<jsp:forward page="logout.jsp"></jsp:forward>
  	<%} %>
  </body>
  	<script type="text/javascript">
  		document.getElementById("btn").onclick=function(){
  			window.location.href="${pageContext.request.contextPath}/FindAllServlet";
  		}
  	</script>
</html>
