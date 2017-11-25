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
	         		<li>NoSql 简介</li>
	         		<li>NoSql 四大类</li>
	         	</ul>
         	</li>
         	<li>
         		<span>Jedis</span>
         		<ul>
	         		<li>Redis 简介</li>
	         		<li>Redis 安装</li>
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
				<hgroup>  
	                <h3>NoSql 简介</h3>  
	            </hgroup>  
	            <article>
	            	<p>NoSql (Not Only Sql) 非关系型数据库，是关系型数据库的补充。它是为了解决高并发、高可用、高可扩展，大数据存储等一系列问题而产生的数据库解决方案。</p>
	            	<p>为什么会用到NoSql？在以往的网站，访问量并不大，对数据库的性能要求不是特别高。当随着网站的访问量越来越大(特别是电商)，生活节奏越来越快(页面加载慢直接狗带)，缓存显得格外重要。</p>
	            	<p>与此同时，对于大数据的分析和挖掘的能力，非关系型数据库的表现更为突出(表的数据结构简单是原因之一)。</p>
	            	<h3>NoSql 数据库分四大类</h3>  
	            	<ul>
	            		<li>键值对存储数据库：形如 key-value，主要用于缓存，查询速度快， Redis</li>
	            		<li>文档型数据库：主要用于Web应用，数据结构要求不严格，Mongodb</li>
	            		<li>列存储数据库：主要用于分布式文件系统，查询快，扩展性强，HBase</li>
	            		<li>图形数据库：主要用于社交网络，InfoGrid</li>
	            	</ul>
	            </article> 
	            <article>
	                <h3>Redis 简介</h3>  
	            		Redis(REmote DIctionary Server(远程字典服务器))是使用c语言开发的一个高性能键值数据库。Redis可以通过一些键值类型来存储数据。支持数据的持久化。
	            		
	                <h3>Redis 安装</h3>  
	            		<a href="https://github.com/ITDragonBlog/daydayup/blob/master/Redis/Redis%E5%AE%89%E8%A3%85.md">Redis 安装教程</a>
	            	<h3>Redis 五大数据类型</h3>
	            	<ul>
	            		<li>String 类型 : set(赋值 set key value)，get(取值 get key)，mset(批量赋值)，mget(批量取值)，del(删除key)，incr(+1)，incrby(+制定整数)，decr(-1)，decrby(-制定整数)，append(字符串追加)，strlen(取长度)</li>
	            		<li>Hash 散列类型 : hset(单属性赋值 hset key field value)，hget(取值 hget key field)，hmset(多属性赋值)，gmset(多属性取值)，hgetall(获取 key 的所有值，包括field 和 value)，hdel(删除属性，可以是单个或者多个，返回删除属性的个数)，hexists(判断属性是否存在)，hkeys(获取key的所有field)，hvals(获取key的所有value)，hlen(获取field个数)</li>
	            		<li>List 类型 : lpush(向列表左侧加元素)，rpush(向列表右侧加元素)，lrange(根据下标范围取值，若结束下标为-1表示最后一位)，llen(获取List长度)，lindex(通过下标获取值)，lset(通过下标设置值)，lpop(列表左侧移除第一个元素)，rpop(列表右侧移除第一个元素)，ltrim(截取)，linsert(在制定元素插入值，linsert key after/before index value)，rpoplpush(把集合第一个元素移到另外一个集合中)</li>
	            		<li>Set 类型 : sadd(新增集合元素)，srem(删除集合元素)，smembers(获取集合所有元素)，sismember(判断集合元素是否存在)，sdiff(差集)，sinter(交集)，sunion(并集)，scard(集合长度)</li>
	            		<li>Sortedset 类型 : zadd(增加元素)，zscore(获取元素分数)，zrange(按照分数从小到大)，zrevrange(从大到小)，withscores(遍历时显示分数)，zrank(分数比它少的个数)，zrevrank(分数比它高的个数)，zrangebyscore(打印分数在制定值的元素)，zincrby(给元素加分)，zcard(获取元素个数)，zcount(统计分数内的个数，包含指定分数)，zremrangebyrank(删除制定排名内的元素)，zremrangebyscore(删除指定分数内的元素)，zrem(删除指定元素)</li>
	            	</ul>
	            </article>
				<p>
				</p>
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
	        	console.log("node : ", node);
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
	        		$.post("/category/update",{categoryId:node.id,name:node.text});
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
					$.post("/category/delete/",{parentId:node.parentId,categoryId:node.id},function(){
						tree.tree("remove",node.target);
					});	
				}
			});
		}
	}
	</script>
</body>
</html>
