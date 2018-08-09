<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE HTML >
<html>
  <head>
    <title>登录界面</title>
	<meta charset="utf-8">
    <meta name="keywords" content="keyword1,keyword2,keyword3">
    <meta name="description" content="this is my page">
    <meta name="content-type" content="text/html; charset=UTF-8">
	<script type="text/javascript" src="js/jquery-3.3.1.min.js"></script>
	<style type="text/css">
		*{margin:0;padding:0;}
		html,body{width:100%;height:100%;overflow:hidden;}
		body{position:relative;background:url(img/index2.jpg) no-repeat center;}
		h1{color:#EE1289;margin:120px 0 50px 400px;font-size:50px;letter-spacing:10px;}
		#login{float:left;cursor:pointer;width:150px;height:50px;background:blue;margin:30px 0 0 380px;text-align:center;line-height:48px;border-radius:50px;letter-spacing:5px;color:white;font-weight:bold;font-size:18px;}
		input{width:160px;height:25px;margin:10px 0 0 0;}
		span{color:white;margin:0 0 0 380px;}
		#RegisterAndFace{float:left;width:80px;height:50px;margin:30px 0 0 10px;}
		#LoginByFace{cursor:pointer;width:75px;height:23px;background:red;text-align:center;border-radius:50px;}
		#Register{cursor:pointer;width:75px;height:23px;background:red;text-align:center;margin:4px 0 0 0;border-radius:50px;}
		#RegisterAndFace a{text-decoration:none;color:white;font-size:13px;letter-spacing:1px;}
		#RegisterAndFace a:hover{color:yellow;}
		#message{display:none;cursor:pointer;width:400px;height:300px;position:absolute;z-index:999;background:url(img/warn1.jpg) center;top:230px;left:315px;text-align:center;border-radius:50px;color:red;font-size:25px;font-weight:bold;}
		#click{position:absolute;top:127px;left:-209px;color:red;letter-spacing:5px;font-size:25px;font-weight:bold;}
		#reset{position:absolute;top:160px;left:-209px;color:red;letter-spacing:5px;font-size:25px;font-weight:bold;}
		#msg{letter-spacing:5px;}		
	</style>
  </head>
  <body>
    <h1>账号登录</h1>
    <form action="LoginByIdServlet" method="post">
    	<span>账&nbsp;号：</span><input type="text" id="user" name="user" placeholder="请输入数字" required><br><br>
	    <span>密&nbsp;码：</span><input type="password" id="pwd" name="pwd" placeholder="字母数字符号组合" required><br><br>
	    <input type="submit" id="login" value="&nbsp;点击登录">
	</form>
	<div id="message"><div id="msg">${msg }</div><span id="click">点击</span><span id="reset">重登</span></div>
    <div>
	    <div id="RegisterAndFace">
	    	<div id="LoginByFace"><a href="LoginByFace.html">&nbsp;刷脸登录</a></div>
	    	<div id="Register"><a href="Register.html">&nbsp;注册账户</a></div>
	    </div>
    </div>
  </body>
  <script type="text/javascript">	
		var msg=$("#msg").text();
		if(msg.trim().length==0){
			$("#message").css({"display":"none"});
		}else{
			$("#message").fadeIn(1000);
			$("#message").click(function(){
				$("#message").fadeOut("slow");
			});
		}
	</script>
</html>
