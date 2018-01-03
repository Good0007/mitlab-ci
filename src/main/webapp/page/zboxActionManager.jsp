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
								<li>
									<a href="zboxProjectManager">项目配置</a>
								</li>
								<li  class="active">
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
							<li>
								<a href="zboxSettingManager"><i class="icon-home"></i> 基础配置</a>
							</li>
							<li>
								<a href="zboxProjectManager"><i class="icon-check"></i> 项目配置</a>
							</li>
							<li  class="active">
								<a href="zboxActionManager"><i class="icon-folder-open"></i> Action配置</a>
							</li>
							<li>
							<!-- 	<a href="#"><i class="icon-envelope"></i> 消息</a>
							</li>
							<li>
								<a href="#"><i class="icon-file"></i> 文件</a>
							</li>
							<li>
								<a href="#"><i class="icon-list-alt"></i> 工作流</a>
							</li> -->
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
						<div class="control-group">
							<label class="control-label" for="select01">选择项目</label>
							<div class="controls">
								<select id="select01" name="project" onchange="chooseProjectAction(this)"> 
									<option value="">-- 全部显示 --</option> 
									<c:forEach items="${projectList}" var="obj">
										<option value="${obj.zboxProject }">${obj.zboxProject }</option> 
									</c:forEach> 
								</select>
							</div>
						</div>
						<table class="table table-bordered table-striped">
							<thead>
								<tr>
									<!-- <th>
										ID
									</th> -->
									<th>
										禅道 Action
									</th>
									<th>
										Gitlab Action
									</th>
									<th>
										Gitlab Label
									</th>
									<th>
										所属项目
									</th>
									<th>
										操作
									</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${actionMappingList}" var="obj">
									<tr>
										<%-- <td>
											${obj.aid }
										</td> --%>
										<td>
											${obj.zboxAction }
										</td>
										<td>
											${obj.gitlabAction }
										</td>
										<td>
											${obj.gitlabLabel }
										</td>
										<td>
											${obj.project }
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
								<label class="control-label" for="select02">禅道项目</label>
								<div class="controls">
									<select id="select02" name="project" onchange="getGitlabLabels(this)"> 
										<c:forEach items="${projectList}" var="obj">
											<option value="${obj.zboxProject }" pid="${obj.zboxProjectId }">${obj.zboxProject }</option> 
										</c:forEach> 
									</select>
								</div>
							</div>
								<!-- 
								<div class="control-group">
								<label class="control-label" for="input01">禅道操作</label>
								<div class="controls">
									<input type="text" class="input-xlarge" name="zboxAction" id="input01" />
								</div>
								</div>
								 -->
								<div class="control-group">
								<label class="control-label" for="select03">禅道操作</label>
								<div class="controls">
									<select id="select03" name="zboxAction"> 
										<option value="zboxAction:opened">创建:opened</option> 
										<option value="zboxAction:started">开始:started</option> 
										<option value="zboxAction:restarted">重新开始:restarted</option> 
										<option value="zboxAction:paused">暂停:paused</option> 
										<option value="zboxAction:resolved">解决:resolved</option> 
										<option value="zboxAction:canceled">取消:canceled</option> 
										<option value="zboxAction:activated">激活:activated</option> 
										<option value="zboxAction:closed">关闭:closed</option> 
										<option value="zboxAction:finished">完成:finished</option> 
									</select>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label" for="select04">Gitlab操作</label>
								<div class="controls">
									<select id="select04" name="gitlabAction"> 
										<option value="">无操作</option> 
										<option value="reopen">打开 [reopen]</option> 
										<option value="close">关闭 [close]</option> 
									</select>
								</div>
							</div>
							<!-- <div class="control-group">
								<label class="control-label" for="input02"></label>
								<div class="controls">
									<input type="text" class="input-xlarge" name="gitlabAction"  id="input02" placeholder="请输入Gitlab操作 如：close,open..."/>
								</div>
							</div> -->
							
							<div class="control-group inputLabel" >
								<label class="control-label" for="input03">Gitlab Label</label>
								<div class="controls">
									<input type="text" class="input-xlarge" name="gitlabLabel"  id="input03" placeholder="请输入Gitlab Label 如：Doing,Done..."/>
									 <button type="button" class="btn btn-info" onclick="fromGitlab()">从Gitlab项目中选择</button>
								</div>
							</div>
							<div class="control-group selectLabel">
								<label class="control-label" for="labelSelect">选择 Gitlab Label</label>
								<div class="controls">
									<select id="labelSelect" name="gitlabLabel"></select>
									<button type="button" class="btn btn-info" onclick="fromInput()">自定义Label</button>
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
		$(".inputLabel").hide();
		$("#input03").attr("disabled",true);
		$("select[name=project]").val("${project}");
		getGitlabLabels($("#select02"));
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
	
	function chooseProjectAction(thisObj){
		location.replace("zboxActionManager?project="+thisObj.value);
	}
	
	
	//输入label
	function fromInput(){
		$(".selectLabel").hide();
		$("#labelSelect").attr("disabled",true);
		$(".inputLabel").show();
		$("#input03").removeAttr("disabled");
	}
	
	//从gitlabel 获取
	function fromGitlab(){
		$(".inputLabel").hide();
		$("#input03").attr("disabled",true)
		getGitlabLabels($("#select02"));
		$(".selectLabel").show();
		$("#labelSelect").removeAttr("disabled");
	}
	
	function getGitlabLabels(thisObj){
		var _url = "<%=path%>/gitlabLabelsManager?m=getLabels";
		$.ajax({
			url:_url,
			type:"post",
			data : {
				projectId:$(thisObj).find(":selected").attr("pid")
			},
			success:function(resp){
				labels = $.parseJSON(resp);
				options = "<option value=''>没有获取到数据，请选择自定义</option>";
				if(labels!=null && labels.length>0){
					options = "<option value=''>不定义</option>";
					for(var i = 0; i<labels.length; i++){
						options += "<option value='"+labels[i].name+"' style='color:"+labels[i].color+"' color='"+labels[i].color+"'>"+labels[i].name+"</option>\n"
					}
				}
				$("#labelSelect").html(options);
		  	},
		  	error:function(){
		  	}
	    });
	}
	
	</script>
	</body>
</html>