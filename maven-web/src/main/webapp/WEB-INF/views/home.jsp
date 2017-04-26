<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/config.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<title>Demo</title>
	<link rel='stylesheet' type="text/css" href='<%=basePath%>/css/base.css'/>
	<script type="text/javascript" src="<%=basePath%>/js/home/home.js"></script>
</head>
<body>
	<div class="main">
		<h2>Welcome Home!</h2>
		names:${requestScope.names }<br>
		time:${requestScope.time}<br>
		city:${requestScope.city }<br>
		gender:${requestScope.gender }
		
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
		
		<div>
			<a id="table1" onclick="doTable()">Chick</a>
		
		</div>
		<div>
				<div class="search_div">
			        <div class="search_label">共<span id="total_count" class="search_label_num"></span>条信息</div>
			    </div>
			    <div class="info_div">
			        <div class="info_list">
			            <div class="info_list_title">
			                
			                <div class="info_list_title_s6">ID</div>
			                <div class="info_list_title_s4">NickName</div>
			                <div class="info_list_title_s3">Email</div>
			                <div class="info_list_title_s1">创建时间</div>
			                <div class="info_list_title_s2">最挂带 </div>
			                <div class="info_list_title_s5">发布时间</div>
			            </div>
			            <div class="info_list_content" id="infoList">
			            </div>
			            <div id="paging"></div>
			        </div>
	             </div>
		</div>
	
	</div>
	<script type="text/javascript" src="<%=basePath%>/js/home/home.js"></script>
</body>
</html>
