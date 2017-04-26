<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%
//     String contextPath = request.getContextPath();

	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<meta http-equiv="X-UA-Compatible" content="IE=edge" />

<script src="http://apps.bdimg.com/libs/jquery/1.6.4/jquery.min.js" type="text/javascript"></script>
<%-- <script type="text/javascript" src="<%=basePath%>/plugins/utils/jquery.min.js"></script> --%>
<%-- <script type="text/javascript" src="<%=basePath%>/plugins/utils/jquery-3.1.1.min.js"></script> --%>
<script type="text/javascript" src="<%=basePath%>/plugins/smartpaginator/smartpaginator.js"></script>

<script src="http://apps.bdimg.com/libs/jquery/1.6.4/jquery.min.js" type="text/javascript"></script>

<link rel='stylesheet' type="text/css" href='<%=basePath%>/plugins/smartpaginator/smartpaginator.css'/>

<script type="text/javascript">
	if (!$.xjt) {
		$.xjt = {};
	}
	$.xjt.contextPath = "<%=request.getContextPath()%>";
</script>