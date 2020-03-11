<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<!DOCTYPE html>
<html>
<head>
	<meta name="viewport" content="initial-scale=1.0, width=device-width, user-scalable=no" />
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta http-equiv="X-UA-Compatible" content="IE=edge,Chrome=1" />
	<meta http-equiv="X-UA-Compatible" content="IE=8" />
	<title>角色管理</title>
</head>
<body>
	<div id="page-wrapper">
		<div class="container-fluid">
			<!-- Page Heading -->
			<div class="row">
				<div class="col-lg-12">
					<h1 class="page-header">Roles</h1>
					<ol class="breadcrumb">
						<li><i class="fa fa-dashboard"></i> <a href="/dashboard">Dashboard</a>
						</li>
						<li class="active"><i class="fa fa-table"></i> Roles</li>
					</ol>
				</div>
			</div>
			<c:if test="${not empty message}">
			<div class="row">
				<div class="col-lg-12">
					<div class="alert alert-info alert-dismissable">
						<button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
						<i class="fa fa-info-circle"></i> <strong>${message}</strong>
					</div>
				</div>
			</div>
			</c:if>
			<!-- /.row -->
			<div class="row">
				<div class="col-lg-12">
					<h2>角色管理表</h2>
					<div class="table-responsive">
						<table class="table table-bordered table-hover table-striped">
							<thead>
								<tr>
									<th>用户级别</th>
									<th>描述说明</th>
									<th>是否可用</th>
									<th>操作</th>
								</tr>
							</thead>
							<tbody>
							<c:forEach items="${sysRoles.content}" var="sysRole">
								<tr>
									<td><a href="${ctx}/roles/update/${sysRole.role}">${sysRole.role}</a></td>
									<td>${sysRole.description}</td>
									<td>${sysRole.available}</td>
									<td><a href="${ctx}/roles/delete/${sysRole.id}">DELETE</a></td>
								</tr>
							</c:forEach>
							</tbody>
						</table>
						<tags:pagination page="${sysRoles}" paginationSize="10"/>
					</div>
				</div>
			</div>
			<!-- /.row -->
		</div>
	</div>
</body>
</html>