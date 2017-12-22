<%@ page language="java" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<nav class="navbar navbar-default navbar-fixed-top">
	<div class="container">
		<div class="navbar-header">
                 <button class="navbar-toggle" data-toggle="collapse" data-target="#navbarCollapse">
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
                 </button>
                <a href="index.jsp" class="navbar-brand">Mitlab-Manager</a>
          </div>
          <div class="collapse navbar-collapse" id="navbarCollapse">
			<ul class="nav navbar-nav" style="margin-top:0">
				<li><a href="zboxManager">管理面板</a></li>
				<li><a href="#">其他配置</a></li>
				<li><a href="#">关于</a></li>
			</ul>
			<!-- 
			<form action="?" class="navbar-form navbar-right">
				<div class="input-group">
	 				<input type="text" class="form-control">
		 			<div class="input-group-btn">
		 				<a class="btn btn-default">提交</a>
		 			</div>
	 			</div>
			</form>
				<p class="navbar-text navbar-right">我是文本p</p>
				<a href="#" class="navbar-text navbar-link">超链接</a>
			 -->
				<!-- <button class="btn btn-danger navbar-btn navbar-right" onclick="promCommand('shutdown')">关机</button> -->
				<button class="btn btn-info navbar-btn navbar-right" onclick="promCommand('reboot')" style="margin-right:10px">重新加载</button>
		</div>
	</div>
</nav>

<!-- 警告框  -->
<div class="modal  fade" data-backdrop="static"  id="alertInfo">
      <div class="modal-dialog">
	      <div class="modal-content">
	      <div class="modal-header" >
	        <h3 class="fui-alert-circle" style="font-size:20px;margin:8px auto;"> 提示</h3>
	      </div>
	      <div class="modal-body">
	        <span class="alertContent"></span>
	      </div>
	      <div class="modal-footer">
	        <button class="btn btn-sm btn-danger" data-dismiss="modal" aria-hidden="true">关闭</button>
	      </div>
	      </div>
      </div>
 </div>
 
<script>
/**
 * 美化的alert提示框
 * 
 */
function alertInfo(msg){
	$(".alertContent").html(msg);
	$("#alertInfo").modal("show");
}

</script>