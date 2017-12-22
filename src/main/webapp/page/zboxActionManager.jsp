<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	String path = request.getContextPath();
%>
<!DOCTYPE html>
<!--[if lt IE 7 ]><html lang="en" class="ie6 ielt7 ielt8 ielt9"><![endif]--><!--[if IE 7 ]><html lang="en" class="ie7 ielt8 ielt9"><![endif]--><!--[if IE 8 ]><html lang="en" class="ie8 ielt9"><![endif]--><!--[if IE 9 ]><html lang="en" class="ie9"> <![endif]--><!--[if (gt IE 9)|!(IE)]><!--> 
<html lang="en"><!--<![endif]--> 
	<head>
		<meta charset="utf-8">
		<title>Mitlab - Manager</title>
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<link href="<%=path%>/assest/theme/css/bootstrap.min.css" rel="stylesheet">
		<link href="<%=path%>/assest/theme/css/bootstrap-responsive.min.css" rel="stylesheet">
		<link href="<%=path%>/assest/theme/css/site.css" rel="stylesheet">
		<!--[if lt IE 9]><script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script><![endif]-->
	</head>
	<body>
		<div class="container">
			<div class="navbar">
				<div class="navbar-inner">
					<div class="container">
						<a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse"> <span class="icon-bar"></span> <span class="icon-bar"></span> <span class="icon-bar"></span> </a> 
						<a class="brand" href="#">MitlabManager</a>
						<div class="nav-collapse">
							<ul class="nav">
								<li>
									<a href="zboxSettingManager">基础配置</a>
								</li>
								<li  class="active">
									<a href="#">Action配置</a>
								</li>
								<li>
									<a href="#">帮助</a>
								</li>
								<li class="dropdown">
									<a href="help.htm" class="dropdown-toggle" data-toggle="dropdown">Tours <b class="caret"></b></a>
									<ul class="dropdown-menu">
										<li>
											<a href="#">Introduction Tour</a>
										</li>
										<li>
											<a href="#">Project Organisation</a>
										</li>
										<li>
											<a href="#">Task Assignment</a>
										</li>
										<li>
											<a href="#">Access Permissions</a>
										</li>
										<li class="divider">
										</li>
										<li class="nav-header">
											Files
										</li>
										<li>
											<a href="#">How to upload multiple files</a>
										</li>
										<li>
											<a href="#">Using file version</a>
										</li>
									</ul>
								</li>
							</ul>
							<form class="navbar-search pull-left" action="">
								<input type="text" class="search-query span2" placeholder="Search" />
							</form>
							<ul class="nav pull-right">
								<li>
									<a href="profile.htm">admin</a>
								</li>
								<li>
									<a href="login.htm">Logout</a>
								</li>
							</ul>
						</div>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="span3">
					<div class="well" style="padding: 8px 0;">
						<ul class="nav nav-list">
							<li class="nav-header">
								Mitlab-Manager
							</li>
							<li>
								<a href="zboxSettingManager"><i class="icon-white icon-home"></i> 基础配置</a>
							</li>
							<li class="active">
								<a href="zboxActionManager"><i class="icon-folder-open"></i> Action配置</a>
							</li>
							<li>
								<a href="#"><i class="icon-check"></i> 任务</a>
							</li>
							<li>
								<a href="#"><i class="icon-envelope"></i> 消息</a>
							</li>
							<li>
								<a href="#"><i class="icon-file"></i> 文件</a>
							</li>
							<li>
								<a href="#"><i class="icon-list-alt"></i> 工作流</a>
							</li>
							<li class="nav-header">
								Your Account
							</li>
							<li>
								<a href="#"><i class="icon-user"></i> Profile</a>
							</li>
							<li>
								<a href="#"><i class="icon-cog"></i> Settings</a>
							</li>
							<li class="divider">
							</li>
							<li>
								<a href="#"><i class="icon-info-sign"></i> Help</a>
							</li>
						</ul>
					</div>
				</div>
				<div class="span9">
						<div class="alert alert-info" id="alertMsg">添加成功!</div>
						<table class="table table-bordered table-striped">
							<thead>
								<tr>
									<th>
										ID
									</th>
									<th>
										禅道 Action
									</th>
									<th>
										Gitlab Action
									</th>
									<th>
										操作
									</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${actionMappingList}" var="obj">
									<tr>
										<td>
											${obj.aid }
										</td>
										<td>
											${obj.zboxAction }
										</td>
										<td>
											${obj.gitlabAction }
										</td>
										<td>
											<a href="#" val="<%=path%>/zboxActionManager?m=removeMapping&aid=${obj.aid}" 
											onclick="removeAction(this)">移除</a>
										</td>
									</tr>
								</c:forEach>
						</tbody>
					</table>          
					
					<a class="toggle-link" href="#actionForm"><i class="icon-plus"></i> 新增</a>
					<form id="actionForm" class="form-horizontal hidden">
						<fieldset>
							<legend>New Action Mapping</legend>
							<div class="control-group">
								<label class="control-label" for="input01">禅道操作</label>
								<div class="controls">
									<input type="text" class="input-xlarge" name="zboxAction" id="input01" />
								</div>
							</div>
							<div class="control-group">
								<label class="control-label" for="input02">Gitlab操作</label>
								<div class="controls">
									<input type="text" class="input-xlarge" name="gitlabAction"  id="input02" />
								</div>
							</div>
							<div class="form-actions">
								<button type="button" data-loading-text="提交中..."  class="btn btn-primary" onclick="addActionMapping(this)">提交</button> 
								<button type="button" class="btn" onclick="hideActionForm()">取消</button>
							</div>
						</fieldset>
					</form>  
				</div>
			</div>
		</div>
		<script src="<%=path%>/assest/theme/js/jquery.min.js"></script>
		<script src="<%=path%>/assest/theme/js/bootstrap.min.js"></script>
		<script src="<%=path%>/assest/theme/js/site.js"></script>
		<script>
	
	$(function(){
		$("#alertMsg").hide();
	});
	function addActionMapping(thisObj){
		var _url = "<%=path%>/zboxActionManager?m=addMapping";
		var btn=$(thisObj);
		$.ajax({
			url:_url,
			type:"post",
			data:$("#actionForm").serialize(),
			beforeSend:function(){
				btn.button('loading');
			},
			success:function(resp){
				if(resp=='0000'){
					$("#alertMsg").show(500);
					setTimeCloseMsg(1000);
				}else{
					$("#alertMsg").html("添加失败!").show(500);
				}
				btn.button('reset');
		  	},
		  	error:function(){
		  		$("#alertMsg").html("添加失败!").show(500);
		  		btn.button('reset');
		  	}
	    });
	}
	
	function removeAction(thisObj){
		$.ajax({
			url:$(thisObj).attr("val"),
			type:"post",
			success:function(resp){
				if(resp=='0000'){
					$("#alertMsg").html("移除成功!").show(500);
					setTimeCloseMsg(1000);
				}else{
					$("#alertMsg").html("移除失败!").show(500);
				}
		  	},
		  	error:function(){
		  		$("#alertMsg").html("移除失败!").show(500);
		  	}
	    });
	}
	
	function reLoginSession(){
		var _url = "<%=path%>/zbox.do?m=updateSession";
		location.replace(location.href);
		$.ajax({
			url:_url,
			type:"post",
			success:function(resp){
				if(resp=='0000'){
					$("#alertMsg").html("成功更新session！").show(500);
					setTimeCloseMsg(1000);
				}else{
					$("#alertMsg").html("重新登录失败!").show(500);
				}
		  	},
		  	error:function(){
		  	}
	    });
	}
	
	function setTimeCloseMsg(time){
		setTimeout(function(){
			//$("#alertMsg").hide(500);
			location.reload();
		},time);
	}
	
	function hideActionForm(){
		$("#actionForm").addClass("hidden");
	}
	</script>
	</body>
</html>