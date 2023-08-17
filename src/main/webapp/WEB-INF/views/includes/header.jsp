<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tf" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:set var="ctxPath" value="${pageContext.request.contextPath}"/>
<sec:authorize access="isAuthenticated()">
	<sec:authentication property="principal.staffVO" var="authInfo"/>
	<sec:authentication property="principal.staffVO.authList" var="authList"/>
</sec:authorize>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>고객 관리 시스템_CRM</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.7.0/jquery.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
<script>
	let ctxPath = '${ctxPath}'
	let duplicationLogin = '${duplicationLogin}'
	
	let staffId = "${authInfo.staffId}";
	let auth = "${authList}";
	
	let csrfHeaderName = "${_csrf.headerName}"; 
	let csrfTokenValue = "${_csrf.token}"
	
	if(duplicationLogin) {
		alert(duplicationLogin);
	}
	
	$(document).ajaxSend(function(e, xhr, options){
		xhr.setRequestHeader(csrfHeaderName,csrfTokenValue);
	});
</script>
</head>
<body>
<nav class="navbar navbar-expand-sm bg-dark navbar-dark justify-content-between">

  <!-- Links -->
  <ul class="navbar-nav">
    <li class="nav-item">
      <a class="nav-link" href="${ctxPath == '' ? '/': ctxPath}">HOME</a>
    </li>
    <li class="nav-item">
      <a class="nav-link" href="${ctxPath}/customer/list">고객관리</a>
    </li>
    <li class="nav-item">
      <a class="nav-link" href="#">매출관리</a>
    </li>
  </ul>
  <ul class="navbar-nav">
  	<sec:authorize access="isAnonymous()">
	    <li class="nav-item float-right">
	        <a class="nav-link" href="${ctxPath}/login">로그인</a>
	    </li>
	    <li class="nav-item float-right mr-2">
	        <a class="nav-link" href="${ctxPath}/join/step1">회원가입</a>
	    </li>        
    </sec:authorize>
    <sec:authorize access="isAuthenticated()">
	    <li class="nav-item">
	        <a class="nav-link" href="${ctxPath}/mypage">관리자</a>
	    </li>
	    <li class="nav-item mr-2">
	        <a class="nav-link logout" href="${ctxPath}/logout">로그아웃</a>
	    </li>    
    </sec:authorize>
	<!-- Brand/logo -->
	<a class="navbar-brand float-right" href="#">CRM</a>
  </ul>
</nav>

<script>
$(function(){
	$('.logout').click(function(e){
		e.preventDefault();
		let form = $('<form/>',{action : $(this).attr('href'), method : 'post'});
		form.append($('<input/>',{type:'hidden', name : '${_csrf.parameterName}', value : '${_csrf.token}'}))
			.appendTo('body')
			.submit();
	});
});
</script>

