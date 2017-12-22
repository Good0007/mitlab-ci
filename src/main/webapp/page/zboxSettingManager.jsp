<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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
								<li class="active">
									<a href="zboxSettingManager">基础配置</a>
								</li>
								<li>
									<a href="zboxActionManager">Action配置</a>
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
							<li  class="active">
								<a href="zboxSettingManager"><i class="icon-white icon-home"></i> 基础配置</a>
							</li>
							<li>
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
						<form  class="form-horizontal" id="settingForm">
							<div class="alert alert-info" id="alertMsg">更新成功!</div>
							<fieldset>
							<legend>基础配置</legend>
							<div class="control-group">
								<label class="control-label" for="input01">禅道地址</label>
								<div class="controls">
									<input id="input01"  class="input-xlarge" type="text" name="zboxUrl" value="${baseSetting.zboxUrl}" placeholder="请输入禅道地址" />
								</div>
							</div>
							<div class="control-group">
								<label class="control-label" for="input02">禅道帐号</label>
								<div class="controls">
									<input id="input02"  class="input-xlarge" type="text" name="zboxUser" value="${baseSetting.zboxUser}" placeholder="请输入禅道帐号" />
								</div>
							</div>
							
							<div class="control-group">
								<label class="control-label" for="input03">禅道密码</label>
								<div class="controls">
									<input id="input03"  class="input-xlarge" type="text" name="zboxPassword" value="${baseSetting.zboxPassword}" placeholder="请输入禅道密码" />
								</div>
							</div>
							
							<div class="control-group">
								<label class="control-label" for="input04">GITLAB地址</label>
								<div class="controls">
									<input id="input04"  class="input-xlarge" type="text" name="gitlabUrl" value="${baseSetting.gitlabUrl}" placeholder="请输入GITLAB地址" />
								</div>
							</div>
							<div class="control-group">
								<label class="control-label" for="input05">GITLAB token</label>
								<div class="controls">
									<input id="input05"  class="input-xlarge" type="text" name="gitlabToken" value="${baseSetting.gitlabToken}" placeholder="请输入GITLAB token" />
								</div>
							</div>
							<div class="control-group">
								<label class="control-label" for="input06">禅道项目</label>
								<div class="controls">
									<input id="input06"  class="input-xlarge" type="text" name="zboxProject" value="${baseSetting.zboxProject}" placeholder="请输入禅道项目名称" />
								</div>
							</div>
						 	<div class="control-group">
								<label class="control-label" for="input07">GITLAB项目[组/项目]</label>
								<div class="controls">
									<input id="input07"  class="input-xlarge" type="text" name="gitProject" value="${baseSetting.gitProject}" placeholder="请输入GITLAB项目[组/项目]" />
								</div>
							</div>
						    <div class="form-actions">
			                     <button type="button" class="btn btn-primary" onclick="updateSetting(this)" data-loading-text="更新中..." >更新</button>
			                 	 <button type="button" class="btn btn-success" onclick="getProjects(this)" >项目</button>
			                 </div>
						</fieldset>
					<%-- 	<fieldset>
							<legend>项目配置</legend>
							<div class="control-group">
								<label class="control-label" for="input06">禅道项目</label>
								<div class="controls">
									<input id="input06"  class="input-xlarge" type="text" name="zboxProject" value="${baseSetting.zboxProject}" placeholder="请输入禅道项目名称" />
								</div>
							</div>
						 	<div class="control-group">
								<label class="control-label" for="input07">GITLAB项目[组/项目]</label>
								<div class="controls">
									<input id="input07"  class="input-xlarge" type="text" name="gitProject" value="${baseSetting.gitProject}" placeholder="请输入GITLAB项目[组/项目]" />
								</div>
							</div>
							<div class="form-actions">
		                        <button type="button" class="btn btn-primary" onclick="updateSetting(this)" data-loading-text="更新中..." >更新</button>
		                    </div>
						</fieldset> --%>
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
	function updateSetting(thisObj){
		var _url = "<%=path%>/zboxSettingManager?m=updateSetting";
		var btn=$(thisObj);
		$.ajax({
			url:_url,
			type:"post",
			data:$("#settingForm").serialize(),
			beforeSend:function(){
				btn.button('loading');
			},
			success:function(resp){
				if(resp=='0000'){
					$("#alertMsg").show(500);
					reLoginSession();
				}else{
					$("#alertMsg").html("更新失败!").show(500);
				}
				btn.button('reset');
		  	},
		  	error:function(){
		  		$("#alertMsg").html("更新失败!").show(500);
		  		btn.button('reset');
		  	}
	    });
	}
	
	function reLoginSession(){
		var _url = "<%=path%>/zbox.do?m=updateSession";
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
			$("#alertMsg").hide(500);
		},time);
	}
	
	function getProjects(thisObj){
		var _url = "http://192.168.60.50:26080/zentao/project.json";
		$.get(_url,function(resp){
			alert(resp);
		});
	}
	
	
	</script>
	</body>
</html>