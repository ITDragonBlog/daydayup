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
						<li><i class="fa fa-dashboard"></i> <a href="index.html">Dashboard</a>
						</li>
						<li class="active"><i class="fa fa-table"></i> Employees</li>
					</ol>
				</div>
			</div>
			<!-- /.row -->
			<div class="row">
				<div class="col-lg-12">
					<h2>员工信息表</h2>
					<div class="table-responsive">
						<table class="table table-bordered table-hover table-striped">
							<thead>
								<tr>
									<th>Account</th>
									<th>Nick Name</th>
									<th>Iphone</th>
									<th>Email</th>
									<th>Platform</th>
									<th>CreatedDate</th>
									<th>UpdatedDate</th>
									<th>Operate</th>
								</tr>
							</thead>
							<tbody>
							<c:forEach items="${employees.content}" var="employee">
								<tr>
									<td><a href="${ctx}/task/update/${employee.account}">${employee.account}</a></td>
									<td>${employee.userName}</td>
									<td>${employee.iphone}</td>
									<td>${employee.email}</td>
									<td>${employee.platform}</td>
									<td>${employee.createdDate}</td>
									<td>${employee.updatedDate}</td>
									<td><a href="${ctx}/task/delete/${employee.id}">DELETE</a></td>
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