<%@ page language="java" contentType="text/html; UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ITDragon 图片上传</title>
</head>
	<link href="/js/kindeditor-4.1.10/themes/default/default.css" type="text/css" rel="stylesheet">
	<script type="text/javascript" src="js/jquery.min.js"></script> 
	<script type="text/javascript" charset="utf-8" src="/js/kindeditor-4.1.10/kindeditor-all-min.js"></script>
	<script type="text/javascript" charset="utf-8" src="/js/kindeditor-4.1.10/lang/zh_CN.js"></script>
</head>  
<body>  
	<h3>测试上传图片功能接口的form表单</h3>
    <form action="pic/upload" method="post" enctype="multipart/form-data">
	    <input type="file" name="fileUpload" />
	    <input type="submit" value="上传文件" />
    </form>
    <hr />
    <h3>借用KindEditor富文本编辑器实现批量上传图片</h3>
    <textarea id="kindEditorDesc" style="width:800px;height:300px;visibility:hidden;"></textarea>
    <script type="text/javascript">
		$(function(){
			//初始化富文本编辑器
			KindEditor.create("#kindEditorDesc", {
				// name值，必须和Controller 的参数对应，不然会提示 400 的错误
				filePostName : "fileUpload",
				// action值，
				uploadJson : '/pic/upload',
				// 设置上传类型，分别为image、flash、media、file
				dir : "image"
			});
		});
	</script>   
</body>
</html>