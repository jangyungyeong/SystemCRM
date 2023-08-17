<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../includes/header.jsp" %>

<div class="container">

	<div class="row my-5">
		<div class="col-2">
			<ul class="list-group">
				<li class="list-group-item">이용약관</li>
				<li class="list-group-item">이메일 인증</li>
				<li class="list-group-item active">회원가입</li>
			</ul>
		</div>
	</div>
	<div class="w-50 mx-auto my-5">
		<h1 class="text-center py-3">회원가입</h1>
		<form:form action="${ctxPath}/staff/join" modelAttribute="staffVO">
			<div class="form-group row">
				<div class="col-9">
					<form:input class="form-control" path="staffId" placeholder="아이디"/>
				</div>
				<div class="col-3">
					<button type="button" class="idCheck btn btn-outline-info form-control">ID중복확인</button>
				</div>
			</div>
			<div class="form-group">
				<form:input class="form-control" path="staffName" placeholder="이름"/>
			</div>
			<div class="form-group">
				<form:input class="form-control" path="staffEmail" placeholder="이메일" readonly="true"/>
				
			</div>
			<div class="form-inline mb-3">
				<form:input class="form-control col-4" path="staFirPhoneNum" placeholder="연락처"/>
				<form:input class="form-control col-4" path="staMidPhoneNum" placeholder="연락처"/>
				<form:input class="form-control col-4" path="staLastPhoneNum" placeholder="연락처"/>
			</div>
			<div class="form-group">
				<form:password class="form-control" path="staffPwd" placeholder="비밀번호"/>
			</div>
			<button type="button" class="btn btn-outline-primary join">회원가입</button>
		</form:form>
	</div>	
</div>

<%@ include file="../includes/footer.jsp" %>

<script>
$(function(){
	let idCheckFlag = false;
	
	$('.idCheck').click(function(){
		let idInput = $('#staffId');
		let staffId = idInput.val();
		
		if(idInput.attr('readonly')){ // 이미 값이 입력된 경우
			idInput.attr('readonly',false);
			idInput.focus();
			$(this).html('ID중복확인');
			idCheckFlag = false;
			return;
		}
		
		if(staffId==''){
			alert('아이디를 입력하세요');
			return;
		}
		
		// 아이디 중복 검사 
		$.ajax({
			type : 'post', 
			url : '${ctxPath}/staff/idCheck',
			data : {staffId : staffId}, 
			success :function(result){
				if(result){ // 사용가능한 경우(True) 
					 alert('사용할 수 있는 아이디 입니다.');
					 idCheckFlag = true;
					 $('.idCheck').html('변경');
					 idInput.attr('readonly',true);
				} else { // 중복 되는 경우(False) 
					alert('사용할 수 없는 아이디 입니다.');
					idInput.focus();
				}
			}
		})
	});
	
	$('.join').click(function(){
		
		if(!idCheckFlag){
			alert('ID중복 확인바람!')
			return;
		}
		
		$('#staffVO').submit();			
	})
});
</script>