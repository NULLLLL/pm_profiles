<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<html>
<head>
	<title>用户管理</title>
	
</head>
<body>
	<div class="panel-body">
      	<div id="searchDiv" class="row" style="margin-top:10px;margin-bottom:10px;">
				<div class="input-group col-xs-2">
					  <input type="text" id="name" placeholder="姓名" name="name" class="form-control floating-label"/>
				</div>
				<div class="input-group col-xs-2">
					  <input type="text" id="loginName" placeholder="登录名" name="loginName" class="form-control floating-label"/>
				</div>
				<div class="input-group col-xs-4">
					  <span class="input-group-addon">注册时间</span>
					  <input type="text" id="startregisterDate" name="startregisterDate" class="form-control" onfocus="WdatePicker({skin:'whyGreen',isShowClear:true,readOnly:true})"/>
					  <span class="input-group-addon">到</span>
					  <input type="text" id="endregisterDate" name="endregisterDate" class="form-control" onfocus="WdatePicker({skin:'whyGreen',isShowClear:true,readOnly:true})"/>
				</div>
				<div class="col-xs-2">
					<button type="submit" class="btn btn-primary btn-sm" id="searchData" >搜索</button>
				</div>		
		</div>
		<div>
			<div class="col-xs-2">
					<button type="button" class="btn btn-primary btn-sm" id="registerUser" >新建用户</button>
			</div>	
			<div class="col-xs-2">
					<button type="button" class="btn btn-primary btn-sm" id="export" >export</button>
			</div>
			<div class="col-xs-2">
					<button type="button" class="btn btn-primary btn-sm" id="testAsync" >test</button>
			</div>
		</div>
  </div>
  	<div id="userInfo" class="modal fade" tabindex="-1">
		  <div class="modal-dialog">
		    <div class="modal-content" >
		      <div class="modal-header">
		        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
		        <h4 class="modal-title">用户信息</h4>
		      </div>
		      <div class="modal-body" style="height: 30%;">
		      			<div class="input-group col-xs-10">
								  <input type="text" id="editName" name="name" placeholder="姓名" class="form-control floating-label"/>
							</div>
							<div class="input-group col-xs-10" style="margin-top: 20px;">
								  <input type="text" id="editLoginName" name="loginName" placeholder="用户名" class="form-control floating-label"/>
							</div>
		      </div>
		      <div class="modal-footer">
		      		<button class="btn btn-default btn-flat" data-dismiss="modal"><i class="mdi-navigation-close"></i> Cancel</button>
	      			<button id="saveUserInfo" class="btn btn-primary btn-flat"><i class="mdi-navigation-check"></i> Ok</button>
		      </div>
		    </div>
		  </div>
	</div>
	<div id="registerUserDiv" class="modal fade" tabindex="-1">
		  <div class="modal-dialog">
		    <div class="modal-content" >
		      <div class="modal-header">
		        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
		        <h4 class="modal-title">注册用户</h4>
		      </div>
		      <div class="modal-body" style="height: 30%;">
		      			<div class="input-group col-xs-5">
								  <input type="text" id="newName" name="name" placeholder="姓名"class="form-control floating-label"/>
							</div>
							<div class="input-group col-xs-5">
								  <input type="text" id="newLoginName"name="loginName" placeholder="用户名" class="form-control floating-label"/>
							</div>
							<div class="input-group col-xs-5" style="margin-top: 20px;">
								  <input type="password" id="newPassword" name="password" value="111111" placeholder="密码" class="form-control floating-label" disabled="disabled"/>
								  <span style="color: red">注：密码默认为111111</span>
							</div>
		      </div>
		      <div class="modal-footer">
		      		<button class="btn btn-default btn-flat" data-dismiss="modal"><i class="mdi-navigation-close"></i> Cancel</button>
	      			<button id="registerUserBtn" class="btn btn-primary btn-flat"><i class="mdi-navigation-check"></i> Ok</button>
		      </div>
		    </div>
		  </div>
	</div>
	<table id="tableId">
	</table>
<script type='text/javascript' src='${ctx}/dwr/common/engine.js'></script>
<script type='text/javascript' src='${ctx}/dwr/common/util.js'></script>
<script type='text/javascript' src='${ctx}/dwr/common/interface/userAjax.js'></script>
<script type="text/javascript" src="${ctx}/static/js/adminUserList.js?2015.01.07"></script>
<script type="text/javascript">
$(document).ready(function() {
    $.material.init();
	 });
</script>
</body>
</html>
