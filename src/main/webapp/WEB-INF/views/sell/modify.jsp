<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../includes/header.jsp" %>
<div class="container">
	<div class="row">
		<div class="col-12">
			<div class="card">
				<div class="card-header">
					<h1 class="float-left">판매 정보 수정</h1>
				</div>
				<div class="card-body">
					<form action="${ctxPath }/sell/modify" method="post" class="modifyForm">
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
			         	<input class="form-control" name="updateDate" value="${sell.updateDate}" readonly="readonly">
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
			        	<button id="regBtn" class="btn btn-xs btn-primary">구매변경</button>
		        	</div>
			        <div class="input-group mb-3">
			        	<div class="input-group-prepend">
			          		<span class="input-group-text">판매합계</span>
			        	</div>
		        		<input class="form-control" name="firPhoneNum" value="0000" readonly="readonly"/>
			      	</div>
			      	<div class="input-group mb-3">
			          	<div class="input-group-prepend">
			            	<span class="input-group-text">상담내용</span>
			          	</div>
			          	<input class="form-control" name="advice" value="상담내용" readonly="readonly"/>
			          	<button id="regBtn" class="btn btn-xs btn-primary">상담변경</button>
			      	</div>
			      	<div class="input-group mb-3">
			          	<div class="input-group-prepend">
			            	<span class="input-group-text">담당사원</span>
			          	</div>
			          	<input class="form-control" name="staffName" value="${sell.staffName}" readonly="readonly"/>
			      	</div>
			      	<div class="getBtns">
						<button data-oper='modify' class="btn btn-light">수정</button>
						<button data-oper='remove' class="btn btn-danger">삭제</button>
						<button data-oper='list' class="btn btn-info">목록</button>
					</div>
					</form>	
				</div> <!-- card-body -->
			</div>
		</div>
	</div>
</div>
<%@ include file="../includes/footer.jsp" %>