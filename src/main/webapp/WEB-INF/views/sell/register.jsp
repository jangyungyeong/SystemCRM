<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../includes/header.jsp" %>
<div class="container">
	<div class="row">
		<div class="col-12">
			<div class="card">
				<div class="card-header">
					<h1 class="float-left">판매등록</h1>
				</div>
				<div class="card-body">
					<form action="${ctxPath}/sell/register" method="post" class="registerForm">
			        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
				        <div class="input-group mb-3">
				        	<div class="input-group-prepend">
				          		<span class="input-group-text">회원이름</span>
				        	</div>
				         	<input class="form-control" name="customerName"/>
				         	<button id="regBtn" class="btn btn-xs btn-primary">회원검색</button>
				      	</div>
					    <div class="input-group mb-3">
					    	<div class="input-group-prepend">
					          <span class="input-group-text">회원등급</span>
					        </div>
					        <input class="form-control" name="customerGrade"/>
					    </div>
					    <div class="input-group mb-3">
					    	<div class="input-group-prepend">
					          <span class="input-group-text">구매상품</span>
					        </div>
					        <input class="form-control" name="productName"/>
					        <button id="regBtn" class="btn btn-xs btn-primary">구매등록</button>
					    </div>
					    <div class="input-group mb-3">
					    	<div class="input-group-prepend">
					          <span class="input-group-text">판매합계</span>
					        </div>
					        <input class="form-control" name="sum"/>
					    </div>
					    <div class="input-group mb-3">
					    	<div class="input-group-prepend">
					          <span class="input-group-text">상담내용</span>
					        </div>
					        <input class="form-control" name="advice"/>
					        <button id="regBtn" class="btn btn-xs btn-primary">상담등록</button>
					    </div>
				      	<div class="input-group mb-3">
				          	<div class="input-group-prepend">
				            	<span class="input-group-text">담당사원</span>
				          	</div>
				          	<input class="form-control" name="staffName" value="${authInfo.staffName }"/>
				      	</div>
				      	<button class="btn btn-light" data-oper='register'>등록</button>
						<button class="btn btn-info" data-oper='list'>취소</button>		
					</form>
				</div> <!-- card-body -->
			</div>
		</div>
	</div>
</div>

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
			formObj.attr('action','${ctxPath}/')
				   .attr('method','get');
		} 
		formObj.submit();
	});	

})
</script>

<%@ include file="../includes/footer.jsp" %>