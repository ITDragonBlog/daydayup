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
	         	</ul>
         	</li>
         </ul>
    </div>
    <div id="content" region="center" title="ITDragon博客" style="padding:5px;">
    	<span>
    		<h3>创建静态树菜单</h3>
    		<ul id="" class="easyui-tree">
	         	<li>
	         		<span>父节点</span>
	         		<ul>
		         		<li>子节点一</li>
		         		<li>子节点二</li>
		         	</ul>
	         	</li>
	         </ul>
         <h4>使用方法</h4>
         <p>ul 标签 定义 class="easyui-tree"</p>
         <a href="http://www.jeasyui.net/plugins/185.html">EasyUI 树菜单教程 </a> <br/>
         <a href="http://www.jeasyui.net/plugins/180.html">EasyUI 窗口教程 </a>
    	</span>
    	<hr/>
        <span>
        	<h3>创建异步树菜单</h3>
        	<a href="javascript:void(0)" class="easyui-linkbutton selectCategory">创建异步树菜单</a>
        	<input type="hidden" name="categoryId" style="width: 280px;"></input>
        	<br/>
        	<h4>创建思路</h4>
         	<p>一：初始加载一级类目菜单，通过点击一级类目菜单再查询其子节点菜单</p>
         	<p>二：类目表设计实例，一级类目的parentId为0，子节点类目的parentId是父节点类目的id</p>
         	<p>三：返回数据结构类型只要满足EasyUI的规范即可</p>
        </span>
        <hr/>
        <span>
        	<h3>创建同步树多选菜单</h3>
        	<a href="javascript:void(0)" class="easyui-linkbutton selectMoreCategory">创建同步树多选菜单</a>
        	<input type="hidden" name="categoryIds" style="width: 280px;"></input>
        	<br/>
        	<h4>注意</h4>
        	<p>若采用异步树加载菜单，会出现勾选父节点。保存后只打印了父节点信息，未打印子节点(因为子节点都没有加载)</p>
        	<h4>解决思路</h4>
        	<p>让业务每个都点开(不合实际)；本章节采用同步加载的方式；你们有没有更好的办法？</p>
        	<a href="http://www.jeasyui.net/tutorial/57.html"> EasyUI 采用同步加载教程 </a>
        </span>
        <hr/>
        <span>
	        <h3>树菜单权限管理：</h3>
	        <p>业务逻辑:需要一张用户组管理表，设置当前登录用户所属组。</p>
	        <p>后台逻辑:树菜单表新增字段permission用来匹配用户所属组，说简单点就是多了一层查询条件。</p>
        </span>
	</div>
	<script type="text/javascript">
	$(function(){
		initAsyncCategory ();
		initMoreSyncCategory ();
	});
	// 异步加载树菜单
	function initAsyncCategory (){
    	$(".selectCategory").each(function(i,e){
    		var _ele = $(e);
			_ele.after("<span style='margin-left:10px;'></span>"); // 避免被按钮遮住
    		_ele.unbind('click').click(function(){
    			$("<div>").html("<ul>").window({ // 使用 javascript 创建窗口（window）
    				width:'500', height:"450", modal:true, closed:true, iconCls:'icon-save', title:'异步树菜单',
    			    onOpen : function(){ // 窗口打开后执行
    			    	var _win = this;
    			    	$("ul",_win).tree({
    			    		url:'/category/async', // 采用异步加载树节点，返回数据的格式要满足EasyUI Tree 的要求
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
	
	// 同步加载复选树菜单
	function initMoreSyncCategory (){
    	$(".selectMoreCategory").each(function(i,e){
    		var _ele = $(e);
			_ele.after("<span style='margin-left:10px;'></span>");
    		_ele.unbind('click').click(function(){
    			$("<div>").html("<ul id='moreItemCat'>").window({ // 使用 javascript 创建窗口（window）
    				width:'500', height:"450", modal:true, closed:true, iconCls:'icon-save', title:'多选树菜单，关闭窗口后保存数据',
    			    onOpen : function(){ // 窗口打开后执行
    			    	var _win = this;
    			    	$("ul",_win).tree({
    			    		url:'/category/sync', // 采用同步的方式加载所有树节点
    			    		animate:true,
    			    		checkbox:true,	// js 声明树菜单可以复选
    			    		loadFilter: function(rows){
    			    			return convert(rows);
    			    		}
    			    	});
    			    },
    			    onClose : function(){ // 窗口关闭后执行
    			    	var nodes = $("#moreItemCat").tree('getChecked');
    					var categoryIds = '';
    					var categoryTexts = '';
    					for(var i = 0; i < nodes.length; i++){
    						if ('' != categoryIds) {
    							categoryIds += ',';
    							categoryTexts += ' , ';
    						}
    						categoryIds += nodes[i].id;
    						categoryTexts += nodes[i].text;
    					}
    					_ele.parent().find("[name=categoryIds]").val(categoryIds);
	    				_ele.next().text(categoryTexts).attr("categoryId",categoryTexts);
    			    	$(this).window("destroy");
    			    }
    			}).window('open'); // 使用 javascript 打开窗口（window）
    		});
    	});
    }
	
	// 官方提供的 js 解析 json 代码
	function convert(rows){
		function exists(rows, parentId){
			for(var i=0; i<rows.length; i++){
				if (rows[i].id == parentId) return true;
			}
			return false;
		}
		var nodes = [];
		for(var i=0; i<rows.length; i++){	// get the top level nodes
			var row = rows[i];
			if (!exists(rows, row.parentId)){
				nodes.push({
					id:row.id,
					text:row.text,
					state:row.state
				});
			}
		}
		var toDo = [];
		for(var i=0; i<nodes.length; i++){
			toDo.push(nodes[i]);
		}
		while(toDo.length){
			var node = toDo.shift();	// the parent node
			for(var i=0; i<rows.length; i++){	// get the children nodes
				var row = rows[i];
				if (row.parentId == node.id){
					var child = {id:row.id,text:row.text,state:row.state};
					if (node.children){
						node.children.push(child);
					} else {
						node.children = [child];
					}
					toDo.push(child);
				}
			}
		}
		return nodes;
	}
	/* 
	EasyUI 相关教程
	1. 使用 javascript 创建窗口（window）
	div 标签 id="win"
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
