<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<div id="header">
<div class="navbar navbar-default">
  <div class="navbar-header">
    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-responsive-collapse">
      <span class="icon-bar"></span>
      <span class="icon-bar"></span>
      <span class="icon-bar"></span>
    </button>
    <a class="navbar-brand" href="javascript:void(0)">Brand</a>
  </div>
  <div class="navbar-collapse collapse navbar-responsive-collapse">
    <ul class="nav navbar-nav">
      <li class="active"><a href="javascript:void(0)">Active</a></li>
      <li><a href="javascript:void(0)">Link</a></li>
    </ul>
    <ul class="nav navbar-nav navbar-right">
      <li><a href="javascript:void(0)">Link</a></li>
      <li class="dropdown">
      		<shiro:user>
					<a href="javascript:void(0)" class="dropdown-toggle" data-toggle="dropdown">Hello，<shiro:principal property="name"/><b class="caret"></b></a>
					<ul class="dropdown-menu">
						<li><a href="${ctx}/profile">修改密码</a></li>
						<li><a href="${ctx}/logout">注销</a></li>
					</ul>
        </shiro:user>
      </li>
    </ul>
  </div>
</div>
</div>