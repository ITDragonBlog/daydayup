<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<!DOCTYPE html>
<html>
<head>
	<meta name="viewport" content="initial-scale=1.0, width=device-width, user-scalable=no" />
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta http-equiv="X-UA-Compatible" content="IE=edge,Chrome=1" />
	<meta http-equiv="X-UA-Compatible" content="IE=8" />
	<c:set var="ctx" value="${pageContext.request.contextPath}" />
	<title>控制台首页</title>
</head>
<body>
	<div id="page-wrapper">
		<div class="container-fluid">
			<!-- Page Heading -->
			<div class="row">
				<div class="col-lg-12">
					<h1 class="page-header">
						Dashboard <small>Statistics Overview</small>
					</h1>
					<ol class="breadcrumb">
						<li class="active"><i class="fa fa-dashboard"></i> Dashboard
						</li>
					</ol>
				</div>
			</div>
			<!-- /.row -->
			<div class="row">
				<div class="col-lg-12">
					<div class="alert alert-info alert-dismissable">
						<button type="button" class="close" data-dismiss="alert"
							aria-hidden="true">&times;</button>
						<i class="fa fa-info-circle"></i> <strong>Like SB Admin?</strong>
						Try out <a
							href="http://startbootstrap.com/template-overviews/sb-admin-2"
							class="alert-link">SB Admin 2</a> for additional features!
					</div>
				</div>
			</div>
			<!-- /.row -->

			<div class="row">
				<div class="col-lg-3 col-md-6">
					<div class="panel panel-primary">
						<div class="panel-heading">
							<div class="row">
								<div class="col-xs-3">
									<i class="fa fa-comments fa-5x"></i>
								</div>
								<div class="col-xs-9 text-right">
									<div class="huge">26</div>
									<div>New Comments!</div>
								</div>
							</div>
						</div>
						<a href="#">
							<div class="panel-footer">
								<span class="pull-left">View Details</span> <span
									class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
								<div class="clearfix"></div>
							</div>
						</a>
					</div>
				</div>
				<div class="col-lg-3 col-md-6">
					<div class="panel panel-green">
						<div class="panel-heading">
							<div class="row">
								<div class="col-xs-3">
									<i class="fa fa-tasks fa-5x"></i>
								</div>
								<div class="col-xs-9 text-right">
									<div class="huge">12</div>
									<div>New Tasks!</div>
								</div>
							</div>
						</div>
						<a href="#">
							<div class="panel-footer">
								<span class="pull-left">View Details</span> <span
									class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
								<div class="clearfix"></div>
							</div>
						</a>
					</div>
				</div>
				<div class="col-lg-3 col-md-6">
					<div class="panel panel-yellow">
						<div class="panel-heading">
							<div class="row">
								<div class="col-xs-3">
									<i class="fa fa-shopping-cart fa-5x"></i>
								</div>
								<div class="col-xs-9 text-right">
									<div class="huge">124</div>
									<div>New Orders!</div>
								</div>
							</div>
						</div>
						<a href="#">
							<div class="panel-footer">
								<span class="pull-left">View Details</span> <span
									class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
								<div class="clearfix"></div>
							</div>
						</a>
					</div>
				</div>
				<div class="col-lg-3 col-md-6">
					<div class="panel panel-red">
						<div class="panel-heading">
							<div class="row">
								<div class="col-xs-3">
									<i class="fa fa-support fa-5x"></i>
								</div>
								<div class="col-xs-9 text-right">
									<div class="huge">13</div>
									<div>Support Tickets!</div>
								</div>
							</div>
						</div>
						<a href="#">
							<div class="panel-footer">
								<span class="pull-left">View Details</span> <span
									class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
								<div class="clearfix"></div>
							</div>
						</a>
					</div>
				</div>
			</div>
			<!-- /.row -->

			<div class="row">
				<div class="col-lg-12">
					<div class="panel panel-default">
						<div class="panel-heading">
							<h3 class="panel-title">
								<i class="fa fa-bar-chart-o fa-fw"></i> Area Chart
							</h3>
						</div>
						<div class="panel-body">
							<div id="morris-area-chart"></div>
						</div>
					</div>
				</div>
			</div>
			<!-- /.row -->

			<div class="row">
				<div class="col-lg-4">
					<div class="panel panel-default">
						<div class="panel-heading">
							<h3 class="panel-title">
								<i class="fa fa-long-arrow-right fa-fw"></i> Donut Chart
							</h3>
						</div>
						<div class="panel-body">
							<div id="morris-donut-chart"></div>
							<div class="text-right">
								<a href="#">View Details <i class="fa fa-arrow-circle-right"></i></a>
							</div>
						</div>
					</div>
				</div>
				<div class="col-lg-4">
					<div class="panel panel-default">
						<div class="panel-heading">
							<h3 class="panel-title">
								<i class="fa fa-clock-o fa-fw"></i> Tasks Panel
							</h3>
						</div>
						<div class="panel-body">
							<div class="list-group">
								<a href="#" class="list-group-item"> 
									<span class="badge">just now</span> <i class="fa fa-fw fa-calendar"></i> Calendar updated
								</a> 
								<a href="#" class="list-group-item"> 
									<span class="badge">4 minutes ago</span> <i class="fa fa-fw fa-comment"></i> Commented on a post
								</a> 
								<a href="#" class="list-group-item"> 
									<span class="badge">23 minutes ago</span> <i class="fa fa-fw fa-truck"></i> Order 392 shipped
								</a> 
								<a href="#" class="list-group-item"> 
									<span class="badge">46 minutes ago</span> <i class="fa fa-fw fa-money"></i> Invoice 653 has been
								</a> 
								<a href="#" class="list-group-item"> 
									<span class="badge">1 hour ago</span> <i class="fa fa-fw fa-user"></i> A new user has been added
								</a>
							</div>
							<div class="text-right">
								<a href="#">View All Activity <i
									class="fa fa-arrow-circle-right"></i></a>
							</div>
						</div>
					</div>
				</div>
				<div class="col-lg-4">
					<div class="panel panel-default">
						<div class="panel-heading">
							<h3 class="panel-title">
								<i class="fa fa-money fa-fw"></i> Transactions Panel
							</h3>
						</div>
						<div class="panel-body">
							<div class="table-responsive">
								<table class="table table-bordered table-hover table-striped">
									<thead>
										<tr>
											<th>Order #</th>
											<th>Order Date</th>
											<th>Order Time</th>
											<th>Amount (USD)</th>
										</tr>
									</thead>
									<tbody>
										<tr>
											<td>3322</td>
											<td>10/21/2013</td>
											<td>2:49 PM</td>
											<td>$8345.23</td>
										</tr>
										<tr>
											<td>3321</td>
											<td>10/21/2013</td>
											<td>2:23 PM</td>
											<td>$245.12</td>
										</tr>
										<tr>
											<td>3320</td>
											<td>10/21/2013</td>
											<td>2:15 PM</td>
											<td>$5663.54</td>
										</tr>
										<tr>
											<td>3319</td>
											<td>10/21/2013</td>
											<td>2:13 PM</td>
											<td>$943.45</td>
										</tr>
									</tbody>
								</table>
							</div>
							<div class="text-right">
								<a href="#">View All Transactions <i
									class="fa fa-arrow-circle-right"></i></a>
							</div>
						</div>
					</div>
				</div>
			</div>
			<!-- /.row -->
		</div>
		<!-- /.container-fluid -->
	</div>
</body>
</html>