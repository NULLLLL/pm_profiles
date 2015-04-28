<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="sitemesh" uri="http://www.opensymphony.com/sitemesh/decorator" %>  
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<html>
<head>
<title>系统-<sitemesh:title/></title>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
<meta http-equiv="Cache-Control" content="no-store" />
<meta http-equiv="Pragma" content="no-cache" />
<meta http-equiv="Expires" content="0" />

<link type="image/x-icon" href="${ctx}/static/images/favicon.ico" rel="shortcut icon">
<link href="${ctx}/static/styles/default.css" type="text/css" rel="stylesheet" />
<link href="${ctx}/static/bootstrap/3.0.0/css/bootstrap.min.css" type="text/css" rel="stylesheet" />
<link href="${ctx}/static/jquery-validation/1.11.1/validate.css" type="text/css" rel="stylesheet" />
<link href="${ctx}/static/tool/bootstrap-table/bootstrap-table.css" type="text/css" rel="stylesheet" />
<link href="${ctx}/static/tool/My97DatePicker/skin/WdatePicker.css" type="text/css" rel="stylesheet" />
<link href="${ctx}/static/tool/bootstrap-material/css/ripples.min.css" rel="stylesheet">
<link href="${ctx}/static/tool/bootstrap-material/css/material-wfont.css" rel="stylesheet">
<link href="${ctx}/static/tool/messenger/css/messenger.css" rel="stylesheet">
<link href="${ctx}/static/tool/messenger/css/messenger-theme-future.css" rel="stylesheet">
<script src="${ctx}/static/jquery/jquery-1.9.1.min.js" type="text/javascript"></script>
<script src="${ctx}/static/bootstrap/3.0.0/js/bootstrap.min.js" type="text/javascript"></script>
<script src="${ctx}/static/jquery-validation/1.11.1/jquery.validate.min.js" type="text/javascript"></script>
<script src="${ctx}/static/jquery-validation/1.11.1/messages_bs_zh.js" type="text/javascript"></script>
<script src="${ctx}/static/tool/bootstrap-table/bootstrap-table.js"></script>
<script src="${ctx}/static/tool/jquery.placeholder.js"></script>
<script src="${ctx}/static/tool/My97DatePicker/WdatePicker.js"></script>
<script src="${ctx}/static/tool/bootstrap-material/js/ripples.min.js"></script>
<script src="${ctx}/static/tool/bootstrap-material/js/material.js"></script>
<script src="${ctx}/static/tool/messenger/js/messenger.min.js"></script>
<script type="text/javascript" src="${ctx}/static/tool/oop/constructor/constructor.js" ></script>
<script type="text/javascript" src="${ctx}/static/tool/oop/constructor/BaseClass.js" ></script>
<script type="text/javascript" src="${ctx}/static/tool/oop/class/Sysutil.js" ></script>
<script type="text/javascript" src="${ctx}/static/js/tool.js" ></script>
<script type="text/javascript">
	var sysutil = New(Sysutil,[]);
	var context = '${ctx}';
	var $ = jQuery.noConflict();
  $._messengerDefaults = {
		  extraClasses: 'messenger-fixed messenger-theme-future messenger-on-top'
		}
</script>
<style type="text/css">
	body {
		width:100%
	}
	.container{
		max-width:100%;
		padding-left:0px;
		padding-right:0px;
	}
	#content{
		padding: 5px 15px 5px 15px;
	}
</style>
<sitemesh:head/>
</head>
<body>
	<div class="container">
		<%@ include file="/WEB-INF/layouts/header.jsp"%>
		<div id="content">
			<sitemesh:body/>
		</div>
		<%@ include file="/WEB-INF/layouts/footer.jsp"%>
	</div>
</body>
</html>