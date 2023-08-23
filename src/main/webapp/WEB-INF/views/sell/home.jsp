<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../includes/header.jsp" %>
<div class="container">
	<div class="row">
		<div class="col-3">111</div>
		<div class="col-1">22</div>
		<div class="col-8">
			<div class="card">
				<div class="card-header">
					<h1 class="float-left">판매내역</h1>
					<button id="regBtn" class="btn btn-xs btn-primary float-right">등록</button>
				</div>
				<div class="card-body">
					<table class="table table-striped table-bordered table-hover">
						<thead class="thead-dark">
					      <tr>
					        <th>최근구매날짜</th>
					        <th>회원이름</th>
					        <th>구매상품</th>
					        <th>수량</th>
					        <th>합계</th>
					        <th>담당사원</th>
					      </tr>
					    </thead>
					    <tbody>
					    <c:if test="${not empty list}">
					    <c:forEach items="${list}" var="sell">
					      <tr>
					        <td>${sell.regdate }</td>
					        <td>
					        	<a class="move" href="${sell.cno }">${sell.customerName }</a>
					        </td>
					        <td>상품이름</td>
					        <td>1</td>
					        <td>0000</td>
					        <td>${sell.staffName }</td>
					      </tr>
					    </c:forEach>
					    </c:if>
					    <c:if test="${empty list}">
					    	<tr><td colspan="6">등록된 판매내역이 없습니다.<br>[HOME]-[판매내역]-[등록]을 통해서 판매내역을 등록해주세요</td></tr>
					    </c:if>
					    </tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
</div>

<%@ include file="../includes/footer.jsp" %>
