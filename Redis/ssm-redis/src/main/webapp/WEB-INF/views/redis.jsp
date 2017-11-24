<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Redis 教程</title>
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
         		<span>Redis</span>
         		<ul>
	         		<li>NoSql 非关系数据库</li>
	         		<li>键值(Key-Value)存储数据库</li>
	         	</ul>
         	</li>
         	<li>
         		<span>Jedis</span>
         		<ul>
	         		<li>Redis 简介</li>
	         		<li>Redis 五大数据类型</li>
	         		<li>Redis 单例实战</li>
	         	</ul>
         	</li>
         </ul>
    </div>
    <div id="content" region="center" title="ITDragon博客" style="padding:5px;">
    	<section >  
	       	<h3>Redis 单例实战</h3>
	       	<div style="width:20%;float:left">
				<ul id="productCategory" class="easyui-tree">
		    	</ul>
			</div>
			<div style="width:80%;float:left">
				xxxx
			</div>
			<div id="productCategoryMenu" class="easyui-menu" style="width:120px;" data-options="onClick:menuHandler">
			    <div data-options="iconCls:'icon-add',name:'add'">新增菜单</div>
			    <div data-options="iconCls:'icon-remove',name:'rename'">重命名</div>
			    <div class="menu-sep"></div>
			    <div data-options="iconCls:'icon-remove',name:'delete'">删除菜单</div>
			</div>
    	</section>
	</div>
	<script type="text/javascript">
	$(function(){
		$("#productCategory").tree({
			url : '/category',
			animate: true,
			method : "GET",
			onContextMenu: function(e,node){
	            e.preventDefault();
	            $(this).tree('select',node.target);
	            $('#productCategoryMenu').menu('show',{
	                left: e.pageX,
	                top: e.pageY
	            });
	        },
	        onAfterEdit : function(node){
	        	var _tree = $(this);
	        	if(node.id == 0){
	        		$.post("/category/create",{parentId:node.parentId,name:node.text},function(data){
	        			if(data.status == 200){
	        				_tree.tree("update",{
	            				target : node.target,
	            				id : data.data.id
	            			});
	        			}else{
	        				$.messager.alert('提示','创建'+node.text+' 分类失败!');
	        			}
	        		});
	        	}else{
	        		$.post("/category/update",{id:node.id,name:node.text});
	        	}
	        }
		});
	});
	function menuHandler(item){
		var tree = $("#productCategory");
		var node = tree.tree("getSelected");
		if(item.name === "add"){
			tree.tree('append', {
	            parent: (node?node.target:null),
	            data: [{
	                text: '新建分类',
	                id : 0,
	                parentId : node.id
	            }]
	        }); 
			var _node = tree.tree('find',0);
			tree.tree("select",_node.target).tree('beginEdit',_node.target);
		}else if(item.name === "rename"){
			tree.tree('beginEdit',node.target);
		}else if(item.name === "delete"){
			$.messager.confirm('确认','确定删除名为 '+node.text+' 的分类吗？',function(r){
				if(r){
					$.post("/category/delete/",{parentId:node.parentId,id:node.id},function(){
						tree.tree("remove",node.target);
					});	
				}
			});
		}
	}
	</script>
</body>
</html>
