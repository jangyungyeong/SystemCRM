<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../includes/header.jsp" %>
<div class="container">
	<div class="row">
		<div class="col-12">
			<div class="card">
				<div class="card-header">
					<h1 class="float-left">고객조회</h1>
				</div>
				<div class="card-body">
			        <div class="input-group mb-3">
			        	<div class="input-group-prepend">
			          		<span class="input-group-text">No.</span>
			        	</div>
			        	<input class="form-control" name="cno" value="${customer.cno}" readonly="readonly">
			      	</div>
			        <div class="input-group mb-3">
			        	<div class="input-group-prepend">
			          		<span class="input-group-text">회원이름</span>
			        	</div>
			         	<input class="form-control" name="customerName" value="${customer.customerName}" readonly="readonly">
			      	</div>
				    <div class="input-group mb-3">
				    	<div class="input-group-prepend">
				          <span class="input-group-text">회원등급</span>
				        </div>
				        <input class="form-control" name="customerGrade" value="${customer.customerGrade}" readonly="readonly"/>
			        	<button id="regBtn" class="btn btn-xs btn-primary">등급현황</button>
				    </div>
			        <div class="input-group mb-3">
			        	<div class="input-group-prepend">
			          		<span class="input-group-text">생년월일</span>
			        	</div>
			        	<div class="form-control">
			        		<input name="birthYear" value="${customer.birthYear}" readonly="readonly"/>-
			        		<input name="birthMonth" value="${customer.birthMonth}" readonly="readonly"/>-
			        		<input name="birthDate" value="${customer.birthDate}" readonly="readonly"/>
			       		</div>
			        </div>
			        <div class="input-group mb-3">
			        	<div class="input-group-prepend">
			          		<span class="input-group-text">전화번호</span>
			        	</div>
			        	<div class="form-control">
			        		<input name="firPhoneNum" value="${customer.firPhoneNum}" readonly="readonly"/>-
			        		<input name="midPhoneNum" value="${customer.midPhoneNum}" readonly="readonly"/>-
			        		<input name="lastPhoneNum" value="${customer.lastPhoneNum}" readonly="readonly"/>
			       		</div>
			      	</div>
			      	<div class="input-group mb-3">
			          	<div class="input-group-prepend">
			            	<span class="input-group-text">구매목록</span>
			          	</div>
			          	<input class="form-control" readonly="readonly"/>
			          	<button id="regBtn" class="btn btn-xs btn-primary">구매조회</button>
			      	</div>
			      	<div class="input-group mb-3">
			          	<div class="input-group-prepend">
			            	<span class="input-group-text">상담조회</span>
			          	</div>
			          	<input class="form-control" readonly="readonly"/>
			          	<button id="regBtn" class="btn btn-xs btn-primary">상담조회</button>
			      	</div>
			      	<div class="input-group mb-3">
			          	<div class="input-group-prepend">
			            	<span class="input-group-text">담당사원</span>
			          	</div>
			          	<input class="form-control" name="chargeStaff" value="${customer.chargeStaff}" readonly="readonly"/>
			      	</div>
			      	<div class="getBtns">
			      		<sec:authorize access="isAuthenticated() and principal.username == #customer.chargeStaff or hasRole('ROLE_MANAGER')">
						<button data-oper='modify' class="btn btn-light modify">수정</button>
						</sec:authorize>
						<button data-oper='list' class="btn btn-info list">목록</button>
					</div>
				</div> <!-- card-body -->
			</div> <!-- end card --> 
		</div> <!-- end col-12 --> 
	</div> <!-- end row --> 
</div> <!-- end container -->

<form>
	<input type="hidden" name="cno" id="cno" value="${customer.cno}">	
</form>

<%@ include file="../includes/footer.jsp" %>

<script>
$(function(){
	// 목록 or 수정 페이지로
	let form = $('form')
	$('.getBtns button').click(function(){
		let operration = $(this).data('oper');
		let type = '${criteria.type}'
		let keyword = '${criteria.keyword}'
		
		form.append($('<input/>',{type : 'hidden', name : 'pageNum', value : '${criteria.pageNum}'}))
			.append($('<input/>',{type : 'hidden', name : 'amount', value : '${criteria.amount}'}))
			.attr('method','get')
			
		if(type&&keyword){
			form.append($('<input/>',{type : 'hidden', name : 'type', value : '${criteria.type}'}))
				.append($('<input/>',{type : 'hidden', name : 'keyword', value : '${criteria.keyword}'}))
		}
			
		if(operration=='list'){
			form.find('#cno').remove();
			form.attr('action','${ctxPath}/customer/list')
		} else if(operration=='modify'){
			form.attr('action','${ctxPath}/customer/modify')
		}
		form.submit();
	});
})
</script>
