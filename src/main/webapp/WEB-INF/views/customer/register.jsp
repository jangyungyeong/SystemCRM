<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../includes/header.jsp" %>
<div class="container">
	<div class="row">
		<div class="col-12">
			<div class="card">
				<div class="card-header">
					<h1 class="float-left">고객등록</h1>
				</div>
				<div class="card-body">
					<form action="${ctxPath}/customer/register" method="post" class="registerForm">
				        <div class="input-group mb-3">
				        	<div class="input-group-prepend">
				          		<span class="input-group-text">회원이름</span>
				        	</div>
				         	<input class="form-control" name="customerName"/>
				      	</div>
				        <div class="input-group mb-3">
				        	<div class="input-group-prepend">
				          		<span class="input-group-text">성별</span>
				        	</div>
				        	<div class="form-control">
				         		<input type="radio" id="mail" class="float-left" name="sex" value="남">
					         	<label for="mail" class="float-left">남</label>
				         		<input type="radio" id="femail" class="float-left" name="sex" value="여">
					         	<label for="femail" class="float-left">여</label>
				         	</div>
				      	</div>
					    <div class="input-group mb-3">
					    	<div class="input-group-prepend">
					          <span class="input-group-text">회원등급</span>
					        </div>
					        <input class="form-control" name="customerGrade" value="신규"/>
					    </div>
				        <div class="input-group mb-3">
				        	<div class="input-group-prepend">
				          		<span class="input-group-text">생년월일</span>
				        	</div>
				        	<div class="form-control">
				        		<input name="birthYear"/>년
				        		<input name="birthMonth"/>월
				        		<input name="birthDate"/>일
				       		</div>
				        </div>
				        <div class="input-group mb-3">
				        	<div class="input-group-prepend">
				          		<span class="input-group-text">전화번호</span>
				        	</div>
				        	<div class="form-control">
				        		<input name="firPhoneNum"/>-
				        		<input name="midPhoneNum" placeholder="전화번호 앞 4자리"/>-
				        		<input name="lastPhoneNum" placeholder="전화번호 뒤 4자리"/>
				       		</div>
				      	</div>
				      	<div class="input-group mb-3">
				          	<div class="input-group-prepend">
				            	<span class="input-group-text">담당사원</span>
				          	</div>
				          	<input class="form-control" name="chargeStaff"/>
				      	</div>
				      	<button class="btn btn-light" data-oper='register'>등록</button>
						<button class="btn btn-info" data-oper='list'>취소</button>		
					</form>
				</div> <!-- card-body -->
			</div> <!-- end card --> 
		</div> <!-- end col-12 --> 
	</div> <!-- end row --> 
</div> <!-- end container -->

<script>
$(function(){
	let formObj = $('.registerForm')
	let addCriteria = function(){
		formObj.append($('<input/>',{type : 'hidden', name : 'pageNum', value : '${param.pageNum}'}))
		   .append($('<input/>',{type : 'hidden', name : 'amount', value : '${param.amount}'}))
		if(type&&keyword){
			formObj.append($('<input/>',{type : 'hidden', name : 'type', value : '${param.type}'}))
				.append($('<input/>',{type : 'hidden', name : 'keyword', value : '${param.keyword}'}))
		}
	}
	let type = '${criteria.type}'
	let keyword = '${criteria.keyword}'
	
	$('.registerForm button').click(function(){
		let operation =$(this).data('oper')
		addCriteria();
		if(operation=='list'){
			formObj.empty();
			addCriteria();
			formObj.attr('action','${ctxPath}/customer/list')
				   .attr('method','get');
		} 
		formObj.submit();
	});	

})
</script>

<%@ include file="../includes/footer.jsp" %>