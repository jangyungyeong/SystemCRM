<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../includes/header.jsp" %>

<div class="login_area container">
	<div class="w-50">
		<h1>고객관리시스템</h1>
		<form action="${ctxPath}/staff/login" method="post">
		<div class="form-group">
		    <label for="staffId">ID:</label>
			<input type="text" class="form-control" placeholder="아이디" name="staffId" value="${staffId }">
		</div>
		<div class="form-group">
		    <label for="staffPwd">Password:</label>
		    <input type="password" class="form-control"  placeholder="비밀번호" name="staffPwd" value="${staffPwd }">
		</div>
		<c:if test="${not empty loginFail}">
				<p style="color:red;font-size: 10px;">${loginFail}</p>
		</c:if>
		<div class="form-group form-check">
		    <label class="form-check-label">
			    <input class="form-check-input mr-2" type="checkbox" name="remember-me"> 로그인 상태 유지
			</label>
		</div>
		<button class="form-control btn btn-primary mb-3">로그인</button>
		<a href="${ctxPath }/findStaffInfo">아이디찾기/비밀번호재발급</a>
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
		</form>
	</div>
</div>

<%@ include file="../includes/footer.jsp" %>
<style>
.login_area {
	margin-top: 100px;
	height: 50vh;
}
</style>