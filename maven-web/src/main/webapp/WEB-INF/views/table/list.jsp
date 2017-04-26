<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/config.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<title>分页表格Demo</title>
	<link rel='stylesheet' type="text/css" href='<%=basePath%>/css/base.css'/>
</head>
<body>
	<div class="main">
		<h2>Welcome Home!</h2>
		
		<div class="login">
			<div class="login_center">
				<div class="longin_center_main">
					<span>用户名:</span><input id="username" type="text"/>
				</div>
				
				<div class="longin_center_main">
					<span>密&nbsp;&nbsp;码&nbsp;:</span><input id="password" type="password"/>
				</div>
				
				<div class="longin_center_button">
					<a id="login" onclick="doLogin()">立即登录</a>
				</div>
			</div>
		</div>
	
	</div>
	<script type="text/javascript" src="<%=basePath%>/js/home/home.js"></script>
</body>
</html>
