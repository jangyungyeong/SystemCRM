<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../includes/header.jsp" %>
<div class="container">
	<div class="row">
		<div class="col-4">
			<table class="table table-bordered table-hover">
				<thead class="thead-light">
					<tr>
						<th colspan="2">관리자</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>성함</td>
						<td>나의매출</td>
					</tr>
					<tr>
						<td>${authInfo.staffName }</td>
						<td></td>
					</tr>
				</tbody>
			</table>
		</div>
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
					        <td>${sell.regDate }</td>
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
					<!-- 검색창 -->
					<form id="searchForm" class="my-3" action="${ctxPath}/">
						<div class="d-inline-block">
							<select name="type" class="form-control">
								<option value="" ${p.criteria.type eq null ? 'selected':''}>----------------</option>
								<option value="C" ${p.criteria.type eq 'C' ? 'selected':''}>회원이름</option>
								<option value="S" ${p.criteria.type eq 'S' ? 'selected':''}>담당사원</option>
							</select>
						</div>
						<div class="d-inline-block col-2">
							<input type="text" name="keyword" value="${p.criteria.keyword}" class="form-control">
						</div>
						<div class="d-inline-block">
							<button class="btn btn-outline-primary">검색</button>
						</div>
						<input type="hidden" name="pageNum" value="${criteria.pageNum}">
						<input type="hidden" name="amount" value="${criteria.amount}">
					</form>
					<!-- 페이지 네이션 -->
					<ul class="pagination justify-content-center">
						<c:if test="${p.prev }">
							<li class="page-item">
								<a class="page-link" href="${p.startPage-p.displayPageNum}"><</a>
							</li>
						</c:if>
						<c:forEach begin="${p.startPage}" end="${p.endPage }" var="pagelink">
							<li class="page-item ${pagelink == p.criteria.pageNum ? 'active' : ''}" >
								<a href="${pagelink}" class="page-link ">${pagelink}</a>
							</li>
						</c:forEach>
						<c:if test="${p.next }">
							<li class="page-item">
								<a class="page-link" href="${p.endPage+1}">></a>
							</li>
						</c:if>
					</ul>
					<form id="listForm" action="${ctxPath}/">
						<input type="hidden" name="pageNum" value="${p.criteria.pageNum}">
						<input type="hidden" name="amount" value="${p.criteria.amount}">
					</form>
				</div> <!-- card-body -->
			</div> <!-- end card -->
		</div> <!-- end col-12 -->
	</div> <!-- end row --> 
</div> <!-- end container -->

<%@ include file="../includes/footer.jsp" %>

<!-- Modal -->
<div class="modal fade" id="listModal">
    <div class="modal-dialog">
        <div class="modal-content">
            <!-- Modal Header -->
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
            </div>
            <!-- Modal body -->
            <div class="modal-body">
                처리가 완료되었습니다.
            </div>
            <!-- Modal footer -->
            <div class="modal-footer">
                <button type="button" class="btn btn-light" data-dismiss="modal">확인</button>
                <button type="button" class="btn btn-danger" data-dismiss="modal">닫기</button>
            </div>
        </div>
    </div>
</div>

<script>

$(function(){
	let result = "${result}"
	let searchForm = $('#searchForm');
	let listForm = $('#listForm');
	let searchCondition = function(){
		if(searchForm.find('option:selected').val() && searchForm.find('[name="keyword"]')){
			listForm.append($('<input/>',{type : 'hidden', name : 'type', value : '${criteria.type}'}))
					.append($('<input/>',{type : 'hidden', name : 'keyword', value : '${criteria.keyword}'}))
		}
	}
	checkModal(result);
	
	function checkModal(result){
		if(result=='') return;
		let operation = "${operation}"
		if(operation=='register'){
			$('.modal-body').html('구매내역이 등록되었습니다');
		} else if(operation=='modify'){
			$('.modal-body').html(result + '번 판매내역이 수정되었습니다.');
		} else if(operation=='remove') {
			$('.modal-body').html(result + '번 판매내역이 삭제처리 되었습니다.');
		}
		$('#listModal').modal('show');
	}
	
	// 고객등록 페이지로 이동
	$('#regBtn').click(function(){
		searchCondition();
		listForm.attr('action','${ctxPath}/sell/register')
				.submit();
	});
	
	// 고객조회 페이지로 이동 
	$('.move').click(function(e){
		e.preventDefault();
		let cnoValue = $(this).attr('href');
		searchCondition();	
		listForm.append($('<input/>',{type : 'hidden', name : 'cno', value : cnoValue}))
				.attr('action','${ctxPath}/sell/get')
				.submit();
	});
	
	// 페이지 이동 
	$('.pagination a').click(function(e){
		e.preventDefault();
		let pageNum = $(this).attr('href');
		listForm.find('input[name="pageNum"]').val(pageNum)
		searchCondition();
		listForm.submit();
	});	
	
	// 검색 이벤트 처리 
	$('#searchForm button').click(function(e){
		e.preventDefault();
		if(!searchForm.find('option:selected').val()){
			alert('카테고리를 선택하세요');
			return; 
		}
		if(!searchForm.find('[name="keyword"]').val()){
			alert('키워드를 입력하세요');
			return; 
		}
		searchForm.find('[name="pageNum"]').val(1); 
		searchForm.submit();
	});
});
</script>
