<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<!DOCTYPE html>
<html>
<head>
	<meta name="viewport" content="initial-scale=1.0, width=device-width, user-scalable=no" />
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta http-equiv="X-UA-Compatible" content="IE=edge,Chrome=1" />
	<meta http-equiv="X-UA-Compatible" content="IE=8" />
	<title>员工列表</title>
</head>
<body>
	<div id="page-wrapper">
		<div class="container-fluid">
			<!-- Page Heading -->
			<div class="row">
				<div class="col-lg-12">
					<h1 class="page-header">Employees</h1>
					<ol class="breadcrumb">
						<li><i class="fa fa-dashboard"></i> <a href="/dashboard">Dashboard</a>
						</li>
						<li class="active"><i class="fa fa-table"></i> Employees</li>
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
					<h2>员工信息表</h2>
					<div class="table-responsive">
						<table class="table table-bordered table-hover table-striped">
							<thead>
								<tr>
									<th>登录账号</th>
									<th>账号昵称</th>
									<th>手机号码</th>
									<th>邮箱号码</th>
									<th>来源平台</th>
									<th>账号级别</th>
									<th>账号权限</th>
									<th>Operate</th>
								</tr>
							</thead>
							<tbody>
							<c:forEach items="${employees.content}" var="employee">
								<tr>
									<td><a href="${ctx}/employees/update/${employee.account}">${employee.account}</a></td>
									<td>${employee.userName}</td>
									<td>${employee.iphone}</td>
									<td>${employee.email}</td>
									<td>${employee.platform}</td>
									<td>
									<!-- 非常不喜欢这样写 -->
									<c:forEach begin="0" end="${fn:length(employee.roleList)}" var="i">
										<c:if test="${not empty employee.roleList[i].role}">
										<label>${employee.roleList[i].role}; </label>
										</c:if>
									</c:forEach>
									</td>
									<td>
									<c:forEach begin="0" end="${fn:length(employee.roleList)}" var="i">
										<c:forEach begin="0" end="${fn:length(employee.roleList[i].permissions)}" var="i">
											<c:if test="${not empty employee.roleList[i].permissions[i].name}">
											<label>${employee.roleList[i].permissions[i].name}; </label>
											</c:if>
										</c:forEach>
									</c:forEach>
									</td>
									<td><a href="${ctx}/employees/delete/${employee.id}">DELETE</a></td>
								</tr>
							</c:forEach>
							</tbody>
						</table>
						<tags:pagination page="${employees}" paginationSize="10"/>
					</div>
				</div>
			</div>
			<!-- /.row -->
		</div>
	</div>
</body>
</html>