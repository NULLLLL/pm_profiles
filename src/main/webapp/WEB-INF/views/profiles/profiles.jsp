<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<html>
<head>
	<title></title>
	
</head>
<body>
	<div class="panel-body">
      	<div id="searchDiv" class="row">
				<div class="input-group col-xs-2">
					  <input type="text" id="p_number" placeholder="p_number" name="p_number" class="form-control floating-label col-xs-2"/>
				</div>
				<div class="input-group col-xs-2">
					  <input type="text" id="name" placeholder="name" name="name" class="form-control floating-label col-xs-2"/>
				</div>
				<div class="col-xs-2">
					<button type="submit" class="btn btn-primary btn-sm" id="searchProfilesData" >搜索</button>
				</div>		
		</div>
		<!-- <div>
			<div class="col-xs-2">
					<button type="button" class="btn btn-primary btn-sm" id="registerUser" >新建用户</button>
			</div>	
			<div class="col-xs-2">
					<button type="button" class="btn btn-primary btn-sm" id="export" >export</button>
			</div>
		</div> -->
  </div>
	<table id="ProfilestableId">
	</table>
<script type='text/javascript' src='${ctx}/dwr/common/engine.js'></script>
<script type='text/javascript' src='${ctx}/dwr/common/util.js'></script>
<script type="text/javascript" src="${ctx}/static/js/pm_profiles.js?2015.01.07"></script>
<script type="text/javascript">
$(document).ready(function() {
    $.material.init();
	 });
</script>
</body>
</html>
