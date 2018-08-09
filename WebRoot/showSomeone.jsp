<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML >
<html>
  <head>
    <title>欢迎界面</title>
	<meta charset="utf-8">
    <meta name="keywords" content="keyword1,keyword2,keyword3">
    <meta name="description" content="this is my page">
    <meta name="content-type" content="text/html; charset=UTF-8">
	<script type="text/javascript" src="js/jquery.min.js"></script>
	<link href="js/bootstrap.min.css" rel="stylesheet">
	<script src="js/bootstrap.min.js"></script>
	<style type="text/css">
		*{margin:0;padding:0;}
		html,body{width:100%;height:100%;}
		body{background:url(img/select.jpg) repeat center;overflow:hidden;}
		#title{clear:both;color:red;text-align:center;font-weight:bold;font-size:40px;}
		#select{clear:both;color:red;text-align:center;font-weight:bold;font-size:25px;}
		#warn{position:absolute;clear:both;color:orange;text-align:center;font-weight:bold;font-size:40px;left:40%;top:40%;}
		thead {color:red;text-align:center;font-weight:bold;font-size:25px;}
		tbody {color:yellow;text-align:center;vertical-align:center;font-size:20px;font-weight:bold;}
		tfoot{font-size:20px;}
		a{text-decoration:none;color:red;text-decoration:underline;}
		a:hover {color: orange;} 
		a:visited {color: red;} 
		p{color:red;font-weight:bold;font-size:18px;line-height:35px;}
		#delall{background:orange;height:40px;width:100px;border-radius:50px;}
		#exit{background:orange;height:40px;width:100px;border-radius:50px;}
		#go{float:right;width:220px;}
		span{color:white;font-weight:bold;font-size:30px;}
		#adm{text-decoration:underline;color:yellow;}
	</style>
  </head>
  <body>
  		<%if(session.getAttribute("adusername")!=null){ %>
  		<div id="go"><span>管理员：</span><span id="adm">${adusername }&nbsp;</span></div>
  		<c:if test="${empty pb.u }"><div id="warn">无&nbsp;用&nbsp;户&nbsp;信&nbsp;息<br><a href="${pageContext.request.contextPath }/FindAllServlet?pageNum=1">返回</a></div></c:if>
  		<c:if test="${not empty pb.u }">
  			<div id="title">用&nbsp;户&nbsp;信&nbsp;息&nbsp;管&nbsp;理(条件查询)</div>
  			<div id="select"> 
  			<form action="${pageContext.request.contextPath }/SimpleFindServlet" method="post" id="sim">
  				<select name="field">
  					<option value="username">按用户名查询</option>
  					<option value="id">按id查询</option>
  				</select>
  				<input type="text" name="msg" id="content" value="${msg }">
  				<button type="button" onclick="simpleSelect()">查询</button>
  				<button type="button" onclick="back()">返回</button>
  			</form>
  			</div>
	  		<form action="${pageContext.request.contextPath }/DeleteAllServlet" method="post" id="f">
	  			<table class="table table-hover">
	  				<thead>
		  				<tr>
		  					<td>&nbsp;&nbsp;<input type="checkbox" id="main" onclick="change()">&nbsp;</td>
		  					<td>用户ID</td>
		  					<td>用户账号</td>
		  					<td>用户密码</td>
		  					<td>照片路径</td>
		  					<td>用户图片</td>
		  					<td>人脸特征</td>
		  					<td>操作</td>
		  				</tr>
	  				</thead>
	  				<tbody>
		  				<c:forEach items="${pb.u }" var="e">
		  					<tr>
			  					<td>&nbsp;&nbsp;<input type="checkbox" name="ck" value="${e.id }">&nbsp;</td>
			  					<td>${e.id }</td>
			  					<td>${e.username }</td>
			  					<td>${e.password }</td>
			  					<td>${e.imgsrc }</td>
			  					<td><img alt="${e.username }" src="${e.imgsrc }"></td>
			  					
			  					<td>
			  						<c:if test="${empty e.imgprint }">null</c:if>
			  						<c:if test="${not empty e.imgprint }">256字节(隐藏)</c:if>
			  					</td>
			  					<td>
			  						<a href="${pageContext.request.contextPath }/FindByIdServlet?id=${e.id}">编辑</a>
			  						&nbsp;&nbsp;<a href="javascript:void(0)" onclick="del(${e.id})">删除</a>
			  					</td>
		  					</tr>		
		  				</c:forEach>
		  				<tr>
		  					<td><button type="button" id="delall" onclick="delAll()"><p>删除选中</p></button></td>
		  					<td></td>
		  					<td><a href="${pageContext.request.contextPath }/SimpleFindServlet?pageNum=1&field=${field }&msg=${msg }">首&nbsp;页</a></td>
		  					<td>
		  						<c:if test="${pb.pageNum==1 }">上一页</c:if>
		  						<c:if test="${pb.pageNum!=1 }">
		  							<a href="${pageContext.request.contextPath }/SimpleFindServlet?pageNum=${pb.pageNum-1 }&field=${field }&msg=${msg }">上一页</a>
		  						</c:if>
		  					</td>
		  					<td>
		  						<c:if test="${pb.pageNum==pb.totalPage }">下一页</c:if>
		  						<c:if test="${pb.pageNum!=pb.totalPage }">
		  							<a href="${pageContext.request.contextPath }/SimpleFindServlet?pageNum=${pb.pageNum+1 }&field=${field }&msg=${msg }">下一页</a>
		  						</c:if>
		  					</td>
		  					<td><a href="${pageContext.request.contextPath }/SimpleFindServlet?pageNum=${pb.totalPage }&field=${field }&msg=${msg }">尾&nbsp;页</a></td>
		  					<td></td>
		  					<td><button type="button" id="exit" onclick="leave()"><p>注&nbsp;&nbsp;销</p></button></td>
		  				</tr>
	  				</tbody>
	  			</table>
			</form>
			
  		</c:if>
  		<%}else{ %>
  			<jsp:forward page="logout.jsp"></jsp:forward>
  		<%} %>
  </body>
  <script type="text/javascript">
  	function del(id){
  		if(id==1){
  			alert("管理员账户不能删除！");
  			return false;
  		}
  		var flag=window.confirm("确定删除吗？");
  		if(flag){
  			window.location.href="${pageContext.request.contextPath}/DeleteByIdServlet?id="+id;
  		}
  	}
  	function change(){
  		var main=document.getElementById("main");
  		var flag=main.checked;
  		var cks=document.getElementsByName("ck");
  		for(var i=0;i<cks.length;i++){
  			cks[i].checked=flag;
  		}
  	}
  	function delAll(){
  		var cks=document.getElementsByName("ck");
  		var n=false;
  		for(var i=0;i<cks.length;i++){
  			
  			if(cks[i].checked){
  				if(cks[i].value==1){
	  				alert("管理员账号不能删除");
	  				break;
	  				return false;
  				}
  				n=true;
  			}
  		}
  		if(n){
  			var flag=window.confirm("确定删除吗？");
	 		if(flag){
	 			document.getElementById("f").submit();
	 		}
		}
		
  	}
  	function simpleSelect(){
  		if($("#content").val().replace(/\s/ig,'').length>0){
  			document.getElementById("sim").submit();
  		}else{
  			alert("内容不能为空");
  			return false;
  		}
  	}
  	function leave(){
  		window.location.href="${pageContext.request.contextPath}/logout.jsp";
  	}
  	function back(){
  		window.location.href="${pageContext.request.contextPath}/FindAllServlet?pageNum=1";
  	}
  </script>
</html>
