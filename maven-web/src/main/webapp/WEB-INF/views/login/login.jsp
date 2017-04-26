<%@ page import="java.nio.file.Path" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/config.jsp"%>
<!DOCTYPE html>
<html lang="zh" class="no-js">

<head>
    <meta charset="utf-8">
    <title>Demo后台登录</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">

    <!-- CSS -->
	<%--<link rel="stylesheet" href="${pageContext.request.contextPath }/css/reset.css"> --%>
    <link rel="stylesheet" href="<%=basePath%>/plugins/supersized/supersized.css">
    <link rel="stylesheet" href="<%=basePath%>/css/login/login.css">
    
    <!-- Javascript -->
	<script src="<%=basePath%>/plugins/supersized/supersized.3.2.7.min.js"></script>
	<script src="<%=basePath%>/plugins/supersized/supersized-init.js"></script>
	<script type="text/javascript" src="<%=basePath%>/js/login/login.js"></script>
	
	 <script src="http://open.itboy.net/common/MD5.js"></script>

</head>

<body oncontextmenu="return false">

	<div class="page-container">
	    <input type="hidden" id="error" value="${error}"/>
	    <h1>Login</h1>
<%-- 	    <form action="<%=request.getContextPath()%>/home/submitLogin.do" method="post"> --%>
	        <div>
	            <input type="text" name="email" class="username" placeholder="Username" autocomplete="off"/>
	        </div>
	        <div>
	            <input type="password" name="pswd" class="password" placeholder="Password" oncontextmenu="return false" onpaste="return false" />
	        </div>
	        <div style="text-align: left; margin-left: 10px;">
                <label><input type="checkbox" checked="checked" name="rememberMe" id="rememberMe" style="width: 10px; height: 10px;">记住我</label>
            </div>
             <button id="login" type="button">Sign in</button>
<!-- 	        <button id="submit" type="submit">Sign in</button> -->
<!-- 	    </form> -->
	    <div class="connect">
	        <p>You never know what you can do till you try.</p>
	        <p style="margin-top:20px;">除非你亲自尝试一下,否则你永远不知道你能做什么。</p>
	    </div>
	</div>
	
	<div class="alert" style="display:none">
	    <h2>消息</h2>
	    <div class="alert_con">
	        <p id="ts"></p>
	        <p style="line-height:70px"><a class="btn">确定</a></p>
	    </div>
	</div>

</body>
</html>
