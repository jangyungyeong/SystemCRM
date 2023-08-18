<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../includes/header.jsp" %>
<div class="container">
	<div class="row">
		<div class="col-12">
			<div class="card">
				<div class="card-header">
					<h1 class="float-left">고객정보 수정</h1>
				</div>
				<div class="card-body">
					<form action="${ctxPath }/customer/modify" method="post" class="modifyForm">
					<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
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
					    </div>
				        <div class="input-group mb-3">
				        	<div class="input-group-prepend">
				          		<span class="input-group-text">생년월일</span>
				        	</div>
				        	<div class="form-control">
				        		<input name="birthYear" value="${customer.birthYear}"/>-
				        		<input name="birthMonth" value="${customer.birthMonth}"/>-
				        		<input name="birthDate" value="${customer.birthDate}"/>
				       		</div>
				        </div>
				        <div class="input-group mb-3">
				        	<div class="input-group-prepend">
				          		<span class="input-group-text">전화번호</span>
				        	</div>
				          	<div class="form-control">
				        		<input name="firPhoneNum" value="${customer.firPhoneNum}"/>-
				        		<input name="midPhoneNum" value="${customer.midPhoneNum}"/>-
				        		<input name="lastPhoneNum" value="${customer.lastPhoneNum}"/>
				       		</div>
				      	</div>
				      	<div class="input-group mb-3">
				          	<div class="input-group-prepend">
				            	<span class="input-group-text">구매목록</span>
				          	</div>
				          	<input class="form-control" readonly="readonly"/>
				          	<button id="regBtn" class="btn btn-xs btn-primary">구매변경</button>
				      	</div>
				      	<div class="input-group mb-3">
				          	<div class="input-group-prepend">
				            	<span class="input-group-text">상담조회</span>
				          	</div>
				          	<input class="form-control" readonly="readonly"/>
				          	<button id="regBtn" class="btn btn-xs btn-primary">상담수정</button>
				      	</div>
				      	<div class="input-group mb-3">
				          	<div class="input-group-prepend">
				            	<span class="input-group-text">담당사원</span>
				          	</div>
				          	<input class="form-control" name="chargeStaff" value="${customer.chargeStaff}"/>
				      	</div>
				      	<button type="button" data-oper='modify' class="btn btn-light">수정</button>
						<button type="button" data-oper='remove' class="btn btn-danger">삭제</button>	
						<button type="button" data-oper='list' class="btn btn-info">목록</button>	
					</form>	
				</div> <!-- card-body -->
			</div> <!-- end card --> 
		</div> <!-- end col-12 --> 
	</div> <!-- end row --> 
</div> <!-- end container -->
<%@ include file="../includes/footer.jsp" %>

<script>
$(function(){
	let formObj = $('.modifyForm')
	let addCriteria = function(){
		formObj.append($('<input/>',{type : 'hidden', name : 'pageNum', value : '${criteria.pageNum}'}))
		   .append($('<input/>',{type : 'hidden', name : 'amount', value : '${criteria.amount}'}))
		if(type&&keyword){
			formObj.append($('<input/>',{type : 'hidden', name : 'type', value : '${criteria.type}'}))
				.append($('<input/>',{type : 'hidden', name : 'keyword', value : '${criteria.keyword}'}))
		}
	}
	let type = '${criteria.type}'
	let keyword = '${criteria.keyword}'
		
	$('.modifyForm button').click(function(){
		let operation =$(this).data('oper')
		addCriteria();
		if(operation=='remove'){
			formObj.attr('action','${ctxPath}/customer/remove');
		} else if (operation=='list'){
			formObj.empty();
			addCriteria();
			formObj.attr('action','${ctxPath}/customer/list')
				   .attr('method','get');
		} 		
		formObj.submit();
	});	
})
</script>