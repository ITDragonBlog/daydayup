<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta name="viewport" content="initial-scale=1.0, width=device-width, user-scalable=no" />
		<title>ITDragon系统-<sitemesh:write property='title'/></title>
		<link type="image/x-icon" href="images/favicon.ico" rel="shortcut icon">
		<c:set var="ctx" value="${pageContext.request.contextPath}" />
		<link href="${ctx}/static/sb-admin-1.0.4/css/bootstrap.min.css" rel="stylesheet">
	    <link href="${ctx}/static/sb-admin-1.0.4/css/sb-admin.css" rel="stylesheet">
	    <link href="${ctx}/static/sb-admin-1.0.4/css/plugins/morris.css" rel="stylesheet">
	    <link href="${ctx}/static/sb-admin-1.0.4/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
		<sitemesh:write property='head'/>
		<script src="${ctx}/static/sb-admin-1.0.4/js/jquery.js"></script>
		<script type="text/javascript">
			$(function() {
				$(".itdragon-nav").on('click', 'li', function() {
					sessionStorage.setItem("nav-index", $(this).index());
				});
				if (null != sessionStorage.getItem("nav-index")) {
					$(".itdragon-nav li").eq(sessionStorage.getItem("nav-index")).addClass("active").siblings().removeClass("active");
				}
			});
		</script>
	</head>
	<body>
		<div id="wrapper">
			<%@ include file="/WEB-INF/layouts/header.jsp"%>
			<div class='mainBody'>
		      <sitemesh:write property='body'/>
		    </div>
		</div>
	</body>
	<script src="${ctx}/static/sb-admin-1.0.4/js/bootstrap.min.js"></script>
	<script src="${ctx}/static/sb-admin-1.0.4/js/plugins/morris/raphael.min.js"></script>
	<script src="${ctx}/static/sb-admin-1.0.4/js/plugins/morris/morris.min.js"></script>
	<script src="${ctx}/static/sb-admin-1.0.4/js/plugins/morris/morris-data.js"></script>
</html>
