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
        <span>
        	<a href="javascript:void(0)" class="easyui-linkbutton selectItemCat">创建异步树菜单</a>
        	<input type="hidden" name="categoryId" style="width: 280px;"></input>
        </span>
        <hr/>
        <span>
        	<a href="javascript:void(0)" class="easyui-linkbutton selectMoreItemCat">创建异步树多选菜单</a>
        	<input type="hidden" name="categoryIds" style="width: 280px;"></input>
        </span>
	</div>
	<script type="text/javascript">
	$(function(){
		// 初始化选择类目组件
		initItemCat ();
		initMoreItemCat ();
	});
	function initItemCat (){
    	$(".selectItemCat").each(function(i,e){
    		var _ele = $(e);
			_ele.after("<span style='margin-left:10px;'></span>");
    		_ele.unbind('click').click(function(){
    			$("<div>").html("<ul>").window({ // 使用 javascript 创建窗口（window）
    				width:'500',
    			    height:"450",
    			    modal:true,
    			    closed:true,
    			    iconCls:'icon-save',
    			    title:'异步树菜单',
    			    onOpen : function(){ // 窗口打开后执行
    			    	var _win = this;
    			    	$("ul",_win).tree({
    			    		url:'/item/cat/list', // 返回数据的格式要满足EasyUI Tree 的要求
    			    		animate:true,
    			    		onClick:function(node){ // 树菜单点击后执行
    			    			if($(this).tree("isLeaf",node.target)){ // 如果该节点是叶节点就填写到categoryId中,并关闭窗口
    			    				_ele.parent().find("[name=categoryId]").val(node.id);
    			    				_ele.next().text(node.text).attr("categoryId",node.id);
    			    				$(_win).window('close');
    			    			}
    			    		}
    			    	});
    			    },
    			    onClose : function(){ // 窗口关闭后执行
    			    	$(this).window("destroy");
    			    }
    			}).window('open'); // 使用 javascript 打开窗口（window）
    		});
    	});
    }
	
	function initMoreItemCat (){
    	$(".selectMoreItemCat").each(function(i,e){
    		var _ele = $(e);
			_ele.after("<span style='margin-left:10px;'></span>");
    		_ele.unbind('click').click(function(){
    			$("<div>").html("<ul id='moreItemCat'>").window({ // 使用 javascript 创建窗口（window）
    				width:'500',
    			    height:"450",
    			    modal:true,
    			    closed:true,
    			    iconCls:'icon-save',
    			    title:'多选树菜单，关闭窗口后保存数据',
    			    onOpen : function(){ // 窗口打开后执行
    			    	var _win = this;
    			    	$("ul",_win).tree({
    			    		url:'/item/cat/list', // 返回数据的格式要满足EasyUI Tree 的要求
    			    		animate:true,
    			    		checkbox:true,
    			    	});
    			    },
    			    onClose : function(){ // 窗口关闭后执行
    			    	/*
    			    		获取多选菜单教程 http://www.jeasyui.net/demo/573.html 
    			    		存在的问题：如果树加载是异步的，会出现选择父菜单，子菜单没有加载，导致值没有获取的问题
    			    		解决方法：让业务每个都点开（不合实际）；不采用异步加载；你们有没有更好的办法？
    			    	*/ 
    			    	var nodes = $("#moreItemCat").tree('getChecked');
    					var categoryIds = '';
    					for(var i=0; i<nodes.length; i++){
    						if ('' != categoryIds) categoryIdss += ' , ';
    						categoryIds += nodes[i].text;
    					}
    					_ele.parent().find("[name=categoryIds]").val(categoryIds);
	    				_ele.next().text(categoryIds).attr("categoryId",categoryIds);
    			    	$(this).window("destroy");
    			    }
    			}).window('open'); // 使用 javascript 打开窗口（window）
    		});
    	});
    }
	
	/* 
		树教程 http://www.jeasyui.net/plugins/185.html
		窗口教程 http://www.jeasyui.net/plugins/180.html
		1. 使用 javascript 创建窗口（window）
		<div id="win"></div>
		$('#win').window({
		    width:600,
		    height:400,
		    modal:true
		});
		2. 打开和关闭窗口（window）
		$('#win').window('open'); // open a window
		$('#win').window('close'); // close a window
	*/
	</script>
</body>
</html>