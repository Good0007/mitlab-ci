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
							<li>
								<a href="zboxSettingManager"><i class="icon-white icon-home"></i> 基础配置</a>
							</li>
							<li   class="active">
								<a href="zboxProjectManager"><i class="icon-check"></i> 项目配置</a>
							</li>
							<li>
								<a href="zboxActionManager"><i class="icon-folder-open"></i> Action配置</a>
							</li>
							<!-- <li>
								<a href="#"><i class="icon-envelope"></i> 消息</a>
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
						<table class="table table-bordered table-striped">
							<thead>
								<tr>
									<!-- <th>
										ID
									</th> -->
									<th>
										禅道项目
									</th>
									<th>
										GITLAB项目[组/项目]
									</th>
									<th>
										操作
									</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${projectList}" var="obj">
									<tr>
										<%-- <td>
											${obj.pid }
										</td> --%>
										<td class="ztProject">
											${obj.zboxProject }
										</td>
										<td>
											${obj.gitlabProject }
										</td>
										<td>
											<a href="zboxActionManager?project=${obj.zboxProject }"  >配置Action</a>
											|
											<a href="#" val="<%=path%>/zboxProjectManager?m=removeProject&pid=${obj.pid}" 
											onclick="removeProject(this)">移除</a>
										</td>
									</tr>
								</c:forEach>
						</tbody>
					</table>          
					
					<a class="toggle-link" href="#actionForm"><i class="icon-plus"></i> 新增</a>
					<form id="actionForm" class="form-horizontal hidden">
					<div id="zboxProjectAlert" class="alert alert-danger fade in">
					 	<button class="close" >
					 		<span>&times;</span>
					 	</button>
					 	<p>获取禅道项目失败，请检查基础配置是否正确！</p>
					 </div>	
						<fieldset>
							<legend>New Project Mapping</legend>
							<!-- <div class="control-group">
								<label class="control-label" for="input01">禅道项目</label>
								<div class="controls">
									<input type="text" class="input-xlarge" name="zboxProject" id="input01" />
								</div>
							</div> -->
							<div class="control-group">
								<label class="control-label" for="zboxProject">禅道项目</label>
								<div class="controls">
									<select id="zboxProject" name="zboxProject">
									</select>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label" for="input02"><span class="red">*</span> gitlab项目[组/项目]</label>
								<div class="controls">
									<input type="text" class="input-xlarge" name="gitlabProject"  id="input02" placeholder="请输入 gitlab项目[组/项目]"/>
								</div>
							</div>
							<div class="form-actions">
								<button type="button" data-loading-text="提交中..."  class="btn btn-primary" onclick="addProject(this)">提交</button> 
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
		$("#zboxProjectAlert").hide();
		getProjects();
	});
	function addProject(thisObj){
		var form = $("#actionForm")[0];
		var flag = false;
		if((form.gitlabProject.value).trim() == ""){
			$("#alertMsg").html("请填写gitlab项目！").removeClass("alert-info").addClass("alert-danger").show(500);
			return false;
		}
		$(".ztProject").each(function(index , domEle){
			if($(domEle).html().trim() == form.zboxProject.value){
				$("#alertMsg").html("该禅道项目已存在，请不要重复添加！").removeClass("alert-info").addClass("alert-danger").show(500);
				flag = true;
				return;
			}
		})
		if(flag) return false;
		var _url = "<%=path%>/zboxProjectManager?m=addProject";
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
	
	function removeProject(thisObj){
		var flag = confirm("确定要删除该项目么，这将同时删除该项目所有action配置！");
		if(flag){
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
	}
	
	
	function getProjects(){
		var _url = "<%=path%>/zbox.do?m=getProjects";
		$.ajax({
			url:_url,
			type:"post",
			success:function(resp){
				resp = resp.replace(/\\"/g,"\"");
				resp = resp.replace(/"{/g,"{");
				resp = resp.replace(/}"/g,"}");
				//console.log(resp);
				obj  = $.parseJSON(resp);
				var options = "";
				for (var Key in obj.data.projects){
					project = obj.data.projects[Key];
					if(project.indexOf("\\u")!=-1){
						//中文转码
						project = reconvert(project);
					}
					options += "<option value='"+project+"'>"+project+"</option>\n";
			    }
				if(options == ""){
					options = "<option value=''>暂无项目，请登录禅道创建项目</option>\n";
				}
				$("#zboxProject").html(options);
		  	},
		  	error:function(){
		  		$("#zboxProjectAlert").show();
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
	
	
	
	function unicode(str){
        var value='';
        for (var i = 0; i < str.length; i++) {
            value += '\\u' + left_zero_4(parseInt(str.charCodeAt(i)).toString(16));
        }
        return value;
    }
    function left_zero_4(str) {
        if (str != null && str != '' && str != 'undefined') {
            if (str.length == 2) {
                return '00' + str;
            }
        }
        return str;
    }
    
    //Unicode转中文汉字、ASCII转换Unicode
    function reconvert(str){ 
        str = str.replace(/(\\u)(\w{1,4})/gi,function($0){ 
            return (String.fromCharCode(parseInt((escape($0).replace(/(%5Cu)(\w{1,4})/g,"$2")),16))); 
        }); 
        str = str.replace(/(&#x)(\w{1,4});/gi,function($0){ 
            return String.fromCharCode(parseInt(escape($0).replace(/(%26%23x)(\w{1,4})(%3B)/g,"$2"),16)); 
        }); 
        str = str.replace(/(&#)(\d{1,6});/gi,function($0){ 
            return String.fromCharCode(parseInt(escape($0).replace(/(%26%23)(\d{1,6})(%3B)/g,"$2"))); 
        }); 
        return str; 
    }
	
	</script>
	</body>
</html>