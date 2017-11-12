<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>EasyUI Tree</title>
	<link rel="stylesheet" type="text/css" href="js/jquery-easyui-1.5.3/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="js/jquery-easyui-1.5.3/themes/icon.css">
	<script type="text/javascript" src="js/jquery-easyui-1.5.3/jquery.min.js"></script>
	<script type="text/javascript" src="js/jquery-easyui-1.5.3/jquery.easyui.min.js"></script>
</head>
<body>
	<h2>EasyUI Tree</h2>
	<p>初始化树菜单，给ul标签，class定义为 easyui-tree 即可</p>
	<div style="margin:20px 0;"></div>
	<div class="easyui-panel" style="padding:5px">
		<ul class="easyui-tree">
			<li>
				<span>根目录</span>
				<ul>
					<li data-options="state:'closed'">
						<span>关闭状态的子目录</span>
						<ul>
							<li>
								<span>ITDragon</span>
							</li>
							<li>
								<span>博客</span>
							</li>
						</ul>
					</li>
					<li>
						<span>默认展开的子目录</span>
						<ul>
							<li>
								<span>欢迎</span>
							</li>
							<li>You！</li>
						</ul>
					</li>
					<li>你是最棒的！</li>
				</ul>
			</li>
		</ul>
	</div>
	<hr />
	http://www.jeasyui.net/demo/573.html
	<a href="javascript:void(0)" class="easyui-linkbutton selectItemCat">选择类目</a> 
	<input type="hidden" name="cid" style="width: 280px;"></input>
</body>
</html>