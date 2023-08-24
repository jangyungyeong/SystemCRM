<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../includes/header.jsp" %>
<div class="container">
	<div class="row">
		<div class="col-12">
			<div class="card">
				<div class="card-header">
					<h1 class="float-left">판매조회</h1>
				</div>
				<div class="card-body">
			        <div class="input-group mb-3">
			        	<div class="input-group-prepend">
			          		<span class="input-group-text">No.</span>
			        	</div>
			        	<input class="form-control" name="cno" value="${sell.cno}" readonly="readonly">
			      	</div>
			        <div class="input-group mb-3">
			        	<div class="input-group-prepend">
			          		<span class="input-group-text">최근구매날짜</span>
			        	</div>
			         	<input class="form-control" name="regDate" value="${sell.regDate}" readonly="readonly">
			      	</div>
				    <div class="input-group mb-3">
				    	<div class="input-group-prepend">
				          <span class="input-group-text">회원이름</span>
				        </div>
				        <input class="form-control" name="customerName" value="${sell.customerName}" readonly="readonly"/>
				    	<button id="regBtn" class="btn btn-xs btn-primary">회원정보</button>
				    </div>
			      	<div class="input-group mb-3">
			          	<div class="input-group-prepend">
			            	<span class="input-group-text">회원등급</span>
			          	</div>
			          	<input class="form-control" name="customerGrade" value="${sell.customerGrade }" readonly="readonly"/>
			      	</div>
			        <div class="input-group mb-3">
			        	<div class="input-group-prepend">
			          		<span class="input-group-text">구매상품</span>
			        	</div>
	        			<input class="form-control" name="productName" value="상품이름" readonly="readonly"/>
			        	<button id="regBtn" class="btn btn-xs btn-primary">구매조회</button>
		        	</div>
			        <div class="input-group mb-3">
			        	<div class="input-group-prepend">
			          		<span class="input-group-text">판매합계</span>
			        	</div>
		        		<input class="form-control" name="sum" value="0000" readonly="readonly"/>
			      	</div>
			      	<div class="input-group mb-3">
			          	<div class="input-group-prepend">
			            	<span class="input-group-text">상담내용</span>
			          	</div>
			          	<input class="form-control" name="advice" value="상담내용" readonly="readonly"/>
			          	<button id="regBtn" class="btn btn-xs btn-primary">상담조회</button>
			      	</div>
			      	<div class="input-group mb-3">
			          	<div class="input-group-prepend">
			            	<span class="input-group-text">담당사원</span>
			          	</div>
			          	<input class="form-control" name="staffName" value="${sell.staffName}" readonly="readonly"/>
			      	</div>
			      	<div class="getBtns">
			      		<sec:authorize access="isAuthenticated() and principal.username == #sell.staffName or hasRole('ROLE_MANAGER')">
						<button data-oper='modify' class="btn btn-light modify">수정</button>
						</sec:authorize>
						<button data-oper='list' class="btn btn-info list">목록</button>
					</div>
				</div> <!-- card-body -->
			</div>
		</div>
	</div>
</div>

<form>
	<input type="hidden" name="cno" id="cno" value="${sell.cno}">	
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
			form.attr('action','${ctxPath}/')
		} else if(operration=='modify'){
			form.attr('action','${ctxPath}/sell/modify')
		}
		form.submit();
	});
})
</script>