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
								<li  class="active">
									<a href="zboxSettingManager">基础配置</a>
								</li>
								<li>
									<a href="zboxProjectManager">项目配置</a>
								</li>
								<li>
									<a href="zboxActionManager">Action配置</a>
								</li>
								<li>
									<a href="#">帮助</a>
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
								<a href="zboxProjectManager"><i class="icon-check"></i> 项目配置</a>
							</li>
							<li>
								<a href="zboxActionManager"><i class="icon-folder-open"></i> Action配置</a>
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
							<div class="alert alert-success" id="alertMsg">
								更新成功!
							</div>
							<fieldset>
							<legend>基础配置</legend>
							<div class="control-group">
								<label class="control-label" for="input01"><span class="red">*</span> 禅道地址</label>
								<div class="controls">
									<input id="input01"  class="input-xlarge" type="text" name="zboxUrl" value="${baseSetting.zboxUrl}" 
									placeholder="请输入禅道地址  例:http://hostname:port/zentao" />
								</div>
							</div>
							<div class="control-group">
								<label class="control-label" for="input02"><span class="red">*</span> 禅道帐号</label>
								<div class="controls">
									<input id="input02"  class="input-xlarge" type="text" name="zboxUser" value="${baseSetting.zboxUser}" 
									placeholder="请输入禅道管理员帐号" />
								</div>
							</div>
							
							<div class="control-group">
								<label class="control-label" for="input03"><span class="red">*</span> 禅道密码</label>
								<div class="controls">
									<input id="input03"  class="input-xlarge" type="text" name="zboxPassword" value="${baseSetting.zboxPassword}" 
									placeholder="请输入禅道管理员密码" />
								</div>
							</div>
							
							<div class="control-group">
								<label class="control-label" for="input04"><span class="red">*</span> gitlab地址</label>
								<div class="controls">
									<input id="input04"  class="input-xlarge" type="text" name="gitlabUrl" value="${baseSetting.gitlabUrl}" 
									placeholder="请输入gitlab地址 例:http://hostname:port" />
								</div>
							</div>
							<div class="control-group">
								<label class="control-label" for="input05"><span class="red">*</span> gitlab令牌</label>
								<div class="controls">
									<input id="input05"  class="input-xlarge" type="text" name="gitlabToken" value="${baseSetting.gitlabToken}" 
									placeholder="请输入gitlab令牌" />
								</div>
							</div>
						    <div class="form-actions">
			                     <button type="button" class="btn btn-primary" onclick="updateSetting(this)" data-loading-text="更新中..." >更新</button>
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
	function updateSetting(thisObj){
		//校验必填项
		var form = $("#settingForm")[0];
		if((form.zboxUrl.value).trim() == ""){
			$("#alertMsg").html("请填写禅道地址！").removeClass("alert-success").addClass("alert-danger").show(500);
			return false;
		}
		if((form.zboxUser.value).trim() == ""){
			$("#alertMsg").html( "请填写禅道管理员登录名！").removeClass("alert-success").addClass("alert-danger").show(500);
			return false;
		}
		if((form.zboxPassword.value).trim() == ""){
			$("#alertMsg").html("请填写禅道管理员密码！").removeClass("alert-success").addClass("alert-danger").show(500);
			return false;
		}
		if((form.gitlabUrl.value).trim() == ""){
			$("#alertMsg").html("请填写gitlab地址！").removeClass("alert-success").addClass("alert-danger").show(500);
			return false;
		}
		if((form.gitlabToken.value).trim() == ""){
			$("#alertMsg").html("请填写gitlab令牌！").removeClass("alert-success").addClass("alert-danger").show(500);
			return false;
		}
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
					$("#alertMsg").removeClass("alert-success").addClass("alert-danger").html("更新配置：失败!").show(500);
				}
				btn.button('reset');
		  	},
		  	error:function(){
		  		$("#alertMsg").removeClass("alert-success").addClass("alert-danger").html("更新配置：失败!").show(500);
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
					$("#alertMsg").removeClass("alert-danger").addClass("alert-success").html("更新配置：成功！<br/> 重新登录：成功！").show(500);
					setTimeCloseMsg(1500);
				}else{
					$("#alertMsg").removeClass("alert-success").addClass("alert-danger").html("重新登录失败!").show(500);
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
	
	function validateForm(field , errMsg){
		
		return true;
	}
	
	</script>
	</body>
</html>