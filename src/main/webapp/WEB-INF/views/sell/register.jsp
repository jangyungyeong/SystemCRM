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
				        <div class="input-group mb-3">
				        	<div class="input-group-prepend">
				          		<span class="input-group-text">회원이름</span>
				        	</div>
				         	<input class="form-control" name="customerName"/>
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
					    </div>
				      	<div class="input-group mb-3">
				          	<div class="input-group-prepend">
				            	<span class="input-group-text">담당사원</span>
				          	</div>
				          	<input class="form-control" name="staffName"/>
				      	</div>
				      	<button class="btn btn-light" data-oper='register'>등록</button>
						<button class="btn btn-info" data-oper='list'>취소</button>		
					</form>
				</div> <!-- card-body -->
			</div>
		</div>
	</div>
</div>
<%@ include file="../includes/footer.jsp" %>