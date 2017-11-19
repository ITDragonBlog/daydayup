<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>EasyUI-Tree</title>
<link rel="stylesheet" type="text/css" href="js/jquery-easyui-1.4.1/themes/default/easyui.css" />
<link rel="stylesheet" type="text/css" href="js/jquery-easyui-1.4.1/themes/icon.css" />
<script type="text/javascript" src="js/jquery-easyui-1.4.1/jquery.min.js"></script>
<script type="text/javascript" src="js/jquery-easyui-1.4.1/jquery.easyui.min.js"></script>
<script type="text/javascript" src="js/jquery-easyui-1.4.1/locale/easyui-lang-zh_CN.js"></script>
</head>
<body class="easyui-layout">
    <div data-options="region:'west',title:'EasyUI 树菜单',split:true" style="width:205px;">
    	<ul id="menu" class="easyui-tree" style="margin-top: 10px;margin-left: 5px;">
         	<li>
         		<span>EasyUI</span>
         		<ul>
	         		<li>静态树</li>
	         		<li>结构为ul li 标签</li>
	         		<li>ul定义class为easyui-tree</li>
	         	</ul>
         	</li>
         	<li>
         		<span>本章知识点</span>
         		<ul>
	         		<li>创建静态树菜单</li>
	         		<li>创建异步树菜单</li>
	         		<li>创建异步树多选菜单</li>
	         		<li>树菜单权限管理</li>
	         		<li>EasyUI-Tree与Mmenu</li>
	         	</ul>
         	</li>
         </ul>
    </div>
    <div id="content" region="center" title="ITDragon博客" style="padding:5px;">
	</div>
	<script type="text/javascript">
	$(function(){
		$('#menu').tree({
			onClick: function(node){
				console.log(node);
			}
		});
	});
	</script>
</body>
</html>