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
				<div class="input-group col-xs-1" style="margin-top:5px;">
					<a href="javascript:void(0)" class="dropdown-toggle" data-toggle="dropdown">lower_size<b class="caret"></b></a>
					<ul class="dropdown-menu">
						<li><input type="checkbox" /></li>
						<li><input type="checkbox" /></li>
					</ul>
				</div>
				<div class="input-group col-xs-1" style="margin-top:5px;">
					<a href="javascript:void(0)" class="dropdown-toggle" data-toggle="dropdown">upper_size<b class="caret"></b></a>
					<ul class="dropdown-menu">
						<li><input type="checkbox" /></li>
						<li><input type="checkbox" /></li>
					</ul>
				</div>

				<div class="col-xs-2">
					<button type="submit" class="btn btn-primary btn-sm" id="searchProfilesData" >搜索</button>
				</div>	
				<div>
					<input type="submit" value="Start" onclick="start()" />
				</div>
				<div id="messages"></div>
				
		</div>
		<!-- <div>
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
var webSocket = 
	new WebSocket('ws://localhost:8080'+ctx+'/websocket');

webSocket.onerror = function(event) {
	onError(event)
};

webSocket.onopen = function(event) {
	onOpen(event)
};

webSocket.onmessage = function(event) {
	onMessage(event)
};

function onMessage(event) {
	document.getElementById('messages').innerHTML 
		+= '<br />' + event.data;
}

function onOpen(event) {
	document.getElementById('messages').innerHTML 
		= 'Connection established';
}

function onError(event) {
	alert(event.data);
}

function start() {
	webSocket.send('hello');
	return false;
}
</script>
$(document).ready(function() {
    $.material.init();
	 });
</script>
</body>
</html>
