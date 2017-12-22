<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
%>
<!DOCTYPE html>
<html lang="zh-cn">
  <head>
    <title>MitlabManager</title>
	<meta name="viewport" content="width=device-width,target-densitydpi=high-dpi,initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
	<jsp:include page="/comm/link.jsp" />
	
	<style>
		.baseTitle{
			font-size:18px;
			color:#333;
			padding-left:10px;
			border-left:5px solid #eee;
		}
		.table tr td,.table thead tr th{
			text-align: center;
		}
	</style>
  </head>
  <body>
	<jsp:include page="/comm/header.jsp" />

	<div class="container" id="main">
		<form class="form-controll" id="settingForm">
		<div class="row">
			<div class="alert alert-info" id="alertMsg">更新成功!</div>
			<div class="col-md-6">
			<h2 class="baseTitle">基础配置</h2>
			
			    <div class="form-group">
                        <label class="control-label">禅道地址</label>
                        <input class="form-control" type="text" name="zboxUrl" value="${baseSetting.zboxUrl}" placeholder="请输入禅道地址" />
                    </div>
                     <div class="form-group">
                        <label class="control-label">禅道帐号</label>
                        <input class="form-control" type="text" name="zboxUser" value="${baseSetting.zboxUser}" placeholder="请输入禅道帐号" />
                    </div>
                    <div class="form-group">
                        <label class="control-label">禅道密码</label>
                        <input class="form-control" type="text" name="zboxPassword" value="${baseSetting.zboxPassword}" placeholder="请输入禅道密码" />
                    </div>
                   
                   <div class="form-group">
                        <label class="control-label">GITLAB地址</label>
                        <input class="form-control" type="text" name="gitlabUrl"  value="${baseSetting.gitlabUrl}"  placeholder="请输入GITLAB地址" />
                    </div>
                    <div class="form-group">
                        <label class="control-label">GITLAB token</label>
                        <input class="form-control" type="text" name="gitlabToken" value="${baseSetting.gitlabToken}" placeholder="请输入GITLAB token" />
                    </div>
                    <div class="form-group">
                        <button type="button" class="btn btn-default" onclick="updateSetting(this)" data-loading-text="更新中..." >更新</button>
                    </div>
			</div>
			<div class="col-md-6">
				<h2 class="baseTitle">项目配置</h2>
				<div class="form-group">
                    <div class="form-group">
                        <label class="control-label">禅道项目</label>
                        <input class="form-control" type="text" name="zboxProject" value="${baseSetting.zboxProject}" placeholder="请输入禅道项目名称" />
                    </div>
                    <div class="form-group">
                        <label class="control-label">GITLAB项目[组/项目]</label>
                        <input class="form-control" type="text" name="gitProject" value="${baseSetting.gitProject}" placeholder="请输入GITLAB项目[组/项目]" />
                    </div>
                    <div class="form-group">
                        <button type="button" class="btn btn-default" onclick="updateSetting(this)" data-loading-text="更新中..." >更新</button>
                    </div>
				</div>
			</div>
		</div>
	</form>	
	</div>	
	<jsp:include page="/comm/footer.jsp" />
	
	<script>
	
	$(function(){
		$("#alertMsg").hide();
	});
	function updateSetting(thisObj){
		var _url = "<%=path%>/zboxManager?m=update";
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
		
	</script>
 
  </body>
</html>
