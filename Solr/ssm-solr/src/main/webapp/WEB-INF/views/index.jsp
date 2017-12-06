<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta content="utf-8" http-equiv="charset">
<link rel="stylesheet" type="text/css" href="<c:url value='/resource'/>/base.css" media="all">
<link rel="stylesheet" type="text/css" href="<c:url value='/resource'/>/plist20131112.css" media="all">
<%-- <link rel="stylesheet" type="text/css" href="<c:url value='/resource'/>/list-page-20141009.css" media="all"> --%>
<%-- <link rel="stylesheet" type="text/css" href="<c:url value='/resource'/>/pop_compare.css" media="all"> --%>
<link rel="shortcut icon" type="image/ico" href="favicon.ico">
<script type="text/javascript" src="<c:url value='/resource'/>/jquery-1.2.6.pack.js"></script>
<script type="text/javascript">
	function query() {
		//执行关键词查询时清空过滤条件
		document.getElementById("catalog_name").value="";
		document.getElementById("price").value="";
		document.getElementById("page").value="";
		//执行查询
		queryList();
	}
	function queryList() {
		//提交表单
		document.getElementById("actionForm").submit();
	}
	function filter(key, value) {
		document.getElementById(key).value=value;
		queryList();
	}
	function sort() {
		var s = document.getElementById("sort").value;
		if (s != "1") {
			s = "1";
		} else {
			s = "0";
		}
		document.getElementById("sort").value = s;
		queryList();
	}
	function changePage(p) {
		var curpage = Number(document.getElementById("page").value);
		curpage = curpage + p;
		document.getElementById("page").value = curpage;
		queryList();
	}
</script>
</head>
<body class="root61">
<div id="shortcut-2013">
	<div class="w">
		<ul class="fl lh">
			<li class="fore1 ld"><b></b><a href="#" rel="nofollow">ITDragon博客! 该页面来源网络，仅供学习使用！</a></li>
		</ul>
		<ul class="fr lh">
			<li class="fore1" id="loginbar">您好，欢迎来到Solr7教程！<span><a href="#">[登录]</a> <a href="#" class="link-regist">[免费注册]</a></span></li>
			<li class="fore5 ld menu" id="site-nav" data-widget="dropdown">
				<s></s>
				<span class="outline"></span>
				<span class="blank"></span>
				菜单
				<b></b>
			</li>
		</ul>
		<span class="clr"></span>
	</div>
</div><!--shortcut end-->
<div id="o-header-2013">
	<div class="w" id="header-2013">
		<div id="logo-2013" class="ld"><a href="#" hidefocus="true"><b></b><img src="" width="270" height="60" alt="Logo"></a></div>
		<!--logo end-->
		<div id="search-2013">
			<div class="i-search ld">
				<ul id="shelper" class="hide"></ul>
				<form id="actionForm" action="list.action" method="POST">
				<div class="form">
					<input type="text" class="text" accesskey="s" name="queryString" id="key" value="${queryString }"
						autocomplete="off" onkeydown="javascript:if(event.keyCode==13) {query()}">
					<input type="button" value="搜索" class="button" onclick="query()">
				</div>
				<input type="hidden" name="catalog_name" id="catalog_name" value="${catalog_name }"/> 
				<input type="hidden" name="price" id="price" value="${price }"/> 
				<input type="hidden" name="page" id="page" value="${result.curPage }"/> 
				<input type="hidden" name="sort" id="sort" value="${sort }"/> 
				</form>
			</div>
			<div id="hotwords"></div>
		</div>
		<!--search end-->
	</div>
	<!--header end-->
	<div class="w">
		<div id="nav-2013">
			<div id="categorys-2013" class="categorys-2014">
				<div class="mt ld">
					<h2><a href="">全部商品分类<b></b></a></h2>
				</div>
			</div>
			<div id="treasure"></div>
			<ul id="navitems-2013">
				<li class="fore1" id="nav-home"><a href="javascript:void(0)">导航一</a></li>
				<li class="fore2" id="nav-fashion"><a href="javascript:void(0)">导航二</a></li>
				<li class="fore3" id="nav-chaoshi"><a href="javascript:void(0)">导航三</a></li>
				<li class="fore4" id="nav-tuan"><a href="javascript:void(0)">导航四</a></li>
				<li class="fore5" id="nav-auction"><a href="javascript:void(0)">导航五</a></li>
			</ul>
		</div>
	</div>
</div>
<div class="w">
	<div class="breadcrumb">
		<strong><a href="#">服饰内衣</a></strong><span>&nbsp;&gt;&nbsp;<a
			href="#">女装</a>&nbsp;&gt;&nbsp;<a href="#">T恤</a></span>
	</div>
</div>
<div class="w main">
<div class="right-extra">
<div id="select" clstag="thirdtype|keycount|thirdtype|select" class="m">
	<div class="mt">
		<h1>
			T恤 -<strong>&nbsp;商品筛选</strong>
		</h1>
	</div>
	<div class="mc attrs">
		<div data-id="100001" class="brand-attr">
			<div class="attr">
				<div class="a-key">商品类别：</div>
				<div class="a-values">
					<div class="v-tabs">
						<div class="tabcon">
							<div>
								<a href="javascript:filter('catalog_name', '幽默杂货')" >幽默杂货</a>
							</div>
							<div>
								<a href="javascript:filter('catalog_name', '时尚卫浴')">时尚卫浴</a>
							</div>
							<div>
								<a href="javascript:filter('catalog_name', '另类文体')">另类文体</a>
							</div>
							<div>
								<a href="javascript:filter('catalog_name', '创意相架')">创意相架</a>
							</div>
							<div>
								<a href="javascript:filter('catalog_name', '巧妙收纳')">巧妙收纳</a>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div data-id="100002" class="prop-attrs">
			<div class="attr">
				<div class="a-key">价格：</div>
				<div class="a-values">
					<div class="v-fold">
						<ul class="f-list">
							<li><a href="javascript:filter('price','0-9')">0-9</a></li>
							<li><a href="javascript:filter('price','10-29')">10-29</a></li>
							<li><a href="javascript:filter('price','30-49')">30-49</a></li>
							<li><a href="javascript:filter('price','50-*')">50以上</a></li>
						</ul>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<div id="filter">
	<div class="cls"></div>
	<div class="fore1">
		<dl class="order">
			<dt>排序：</dt>
			<dd>
				<a href="javascript:sort()">价格</a><b></b>
			</dd>
		</dl>
		<dl class="activity">
			<dd></dd>
		</dl>
		<div class="pagin pagin-m">
			<span class="text"><i>${result.curPage }</i>/${result.pageCount }</span>
			<a href="javascript:changePage(-1)" class="prev">上一页<b></b></a>
			<a href="javascript:changePage(1)" class="next">下一页<b></b></a>
		</div>
		<div class="total">
			<span>共<strong>${result.recordCount }</strong>个商品
			</span>
		</div>
		<span class="clr"></span>
	</div>
</div>
<!--商品列表开始-->
<div id="plist" class="m plist-n7 plist-n8 prebuy">
	<ul class="list-h">
		<c:forEach var="item" items="${result.productList }">
		<li pid="${item.pid }">
			<div class="lh-wrap">
				<div class="p-img">
					<a target="_blank" href="#">
						<img width="220" height="282" class="err-product" src="/images/${item.picture}">
					</a>
				</div>
				<div class="p-name">
					<a target="_blank" href="#">${item.name }</a>
				</div>
				<div class="p-price">
					<strong>￥<fmt:formatNumber value="${item.price}" maxFractionDigits="2"/></strong><span id="p1269191543"></span>
				</div>
			</div>
		</li>
		</c:forEach>
	</ul>
</div>
<!--商品列表结束-->
</div>
<div class="left">
	<div id="sortlist" clstag="thirdtype|keycount|thirdtype|sortlist"
		class="m">
		<div class="mt">
			<h2>服饰内衣</h2>
		</div>
		<div class="mc">
			<div class="item current">
				<h3>
					<b></b>女装
				</h3>
			</div>
			<div class="item">
				<h3>
					<b></b>男装
				</h3>
			</div>
			<div class="item">
				<h3>
					<b></b>内衣
				</h3>
			</div>
			<div class="item">
				<h3>
					<b></b>服饰配件
				</h3>
			</div>
		</div>
	</div>
</div>

<span class="clr"></span>
<div id="Collect_Tip" class="Tip360 w260"></div>

</div>
</body>
</html>