<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tf" tagdir="/WEB-INF/tags" %>

<c:set var="ctxPath" value="${pageContext.request.contextPath}"/>

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
      <a class="nav-link" href="${ctxPath}/customer/list"">고객관리</a>
    </li>
    <li class="nav-item">
      <a class="nav-link" href="#">매출관리</a>
    </li>
    <li class="nav-item">
      <a class="nav-link" href="#">관리자</a>
    </li>
  </ul>
  
  <!-- Brand/logo -->
  <a class="navbar-brand" href="#">CRM</a>
  
</nav>

