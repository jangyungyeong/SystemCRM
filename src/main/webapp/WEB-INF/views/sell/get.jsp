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
				    	<button id="regBtn" class="btn btn-xs btn-primary customInfo">회원정보</button>
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
			        	<c:if test="${fn:length(product)-2} == 0">
		        			<input class="form-control" name="productName" value="${product[0].productName }" readonly="readonly"/>
			        	</c:if>
		        			<input class="form-control" name="productName" value="${product[0].productName } 외${fn:length(product)-2}개" readonly="readonly"/>
			        	<button id="regBtn" class="btn btn-xs btn-primary sellRead">구매조회</button>
		        	</div>
			        <div class="input-group mb-3">
			        	<div class="input-group-prepend">
			          		<span class="input-group-text">판매합계</span>
			        	</div>
			        	<c:forEach items="${product }" var="prod" varStatus="status">
			        		<c:if test="${status.last }">
		        				<input class="form-control" name="sum" value="${prod.total }" readonly="readonly"/>
			      			</c:if>
			      		</c:forEach>
			      	</div>
			      	<div class="input-group mb-3 adviceContent">
			          	<div class="input-group-prepend">
			            	<span class="input-group-text">상담내용</span>
			          	</div>
			          	<input class="form-control" name="advice" value="${advice.content }" readonly="readonly"/>
			          	<button id="regBtn" class="btn btn-xs btn-primary adviceRead">상담조회</button>
			      	</div>
			      	<div class="input-group mb-3">
			          	<div class="input-group-prepend">
			            	<span class="input-group-text">담당사원</span>
			          	</div>
			          	<input class="form-control" name="staffName" value="${sell.staffName}" readonly="readonly"/>
			      	</div>
			      	<div class="getBtns">
						<button data-oper='list' class="btn btn-info list">목록</button>
			      		<sec:authorize access="isAuthenticated() and principal.username == #sell.staffId or hasRole('ROLE_MANAGER')">
						<button data-oper='remove' class="btn btn-light remove">삭제</button>
						</sec:authorize>
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

<!-- Modal -->
<!-- 회원정보 모달 -->
<div class="modal fade" id="customInfoModal">
	<div class="modal-dialog">
        <div class="modal-content">
            <!-- Modal Header -->
            <div class="modal-header">
            	<h4>회원 정보</h4>
                <button type="button" class="close" data-dismiss="modal">&times;</button>
            </div>
            <!-- Modal body -->
            <div class="modal-body">
                <h3>${sell.customerName} 님의 회원정보는</h3><br>
                <table class="table table-striped table-bordered table-hover">
                	<thead class="thead-light">
                		<tr>
                			<th>이름</th>
                			<th>등급</th>
                			<th>생년월일</th>
                			<th>전화번호</th>
                		</tr>
                	</thead>
                	<tbody>
                		<tr>
                			<td>${customer.customerName }</td>
                			<td>${customer.customerGrade }</td>
                			<td>${customer.birthYear }-${customer.birthMonth }-${customer.birthDate }</td>
                			<td>${customer.firPhoneNum }-${customer.midPhoneNum }-${customer.lastPhoneNum }</td>
                		<tr>
                	</tbody>
                </table>
                <h3>입니다.</h3>
            </div>
        </div>
    </div>
</div>
<!-- 구매조회 모달 -->
<div class="modal fade" id="sellReadModal">
	<div class="modal-dialog">
        <div class="modal-content">
            <!-- Modal Header -->
            <div class="modal-header">
            	<h4>구매 내역</h4>
                <button type="button" class="close" data-dismiss="modal">&times;</button>
            </div>
            <!-- Modal body -->
            <div class="modal-body">
                <table class="table table-striped table-bordered table-hover">
                	<thead class="thead-light">
                		<tr>
                			<th>상품명</th>
                			<th>품번</th>
                			<th>수량</th>
                			<th>단가</th>
                			<th>합계</th>
                		</tr>
                	</thead>
                	<tbody>
               			<c:forEach items="${product}" var="prod">
               				<c:if test="${prod.productName!='total'}">
	                		<tr>
	                			<td>${prod.productName}</td>
	                			<td>${prod.productNumber}</td>
	                			<td>${prod.amount }</td>
	                			<td>${prod.price }</td>
	                			<td>${prod.total}</td>
	                		<tr>
	                		</c:if> 
	                		<c:if test="${prod.productName=='total'}">
	                			<tr>
	                				<td colspan="4">합계</td>
	                				<td>${prod.total}</td>
	                			</tr>
	                		</c:if>
               			</c:forEach>
                	</tbody>
                </table>
            </div>
        </div>
    </div>
</div>
<!-- 상담조회 모달 -->
<div class="modal fade" id="adviceReadModal">
	<div class="modal-dialog">
        <div class="modal-content">
            <!-- Modal Header -->
            <div class="modal-header">
            	<h4>상담 내용</h4>
                <button type="button" class="close" data-dismiss="modal">&times;</button>
            </div>
            <!-- Modal body -->
            <div class="modal-body">
				<div class="card">
					<div class="card-body">
                        <div id="prevContent">${advice.content}</div> 
                        <button type="button" class="btn btn-danger float-right ml-2" id="dropAdviceButton">삭제</button>
                        <button type="button" class="btn btn-info float-right" id="editAdviceButton">수정</button>
					</div>
				</div>                
            </div>
        </div>
    </div>
</div>
<!-- 상담수정 모달 -->
<div class="modal fade" id="adviceEditModal">
    <div class="modal-dialog">
        <div class="modal-content">
            <!-- Modal Header -->
            <div class="modal-header">
                <h4>상담 수정</h4>
                <button type="button" class="close" data-dismiss="modal">&times;</button>
            </div>
            <!-- Modal body -->
            <div class="modal-body">
                <div class="card">
                    <div class="card-body">
                        <!-- 수정할 상담 내용을 입력할 폼 -->
                        <form id="editAdviceForm">
                            <textarea id="editedContent" cols="50">${advice.content}</textarea>
                        </form>
                    </div>
                </div>
            </div>
            <!-- Modal footer -->
            <div class="modal-footer">
                <button type="button" class="btn btn-info" id="saveEditedContent">저장</button>
            </div>
        </div>
    </div>
</div>

<script>

$(function(){
	// 회원정보 모달
	$('.customInfo').click(function(){
		$('#customInfoModal').modal('show');
	})
	// 구매조회 모달
	$('.sellRead').click(function(){
		$('#sellReadModal').modal('show');
	})
	
	// 상담조회 모달
	$('.adviceRead').click(function(){
		$('#adviceReadModal').modal('show');
	})
	
    // 수정 버튼을 클릭하면 상담 수정 모달을 띄우는 함수
    $("#editAdviceButton").click(function () {
        // 상담 조회 모달을 닫습니다.
        $("#adviceReadModal").modal("hide");
        // 상담 수정 모달을 띄웁니다.
        $("#adviceEditModal").modal("show");
    });

    // 수정된 상담 내용을 저장
    $("#saveEditedContent").click(function () {
        var editedContent = $("#editedContent").val();
        let cno = $('[name="cno"]').val();
        
        $.ajax({
            url: "${ctxPath}/sell/advice/"+cno,
            type: "POST",
            data: {
                editedContent: editedContent
            },
            success: function (message) {
                // 성공적인 응답을 받았을 때의 처리
                console.log(message);
                $("#adviceEditModal").modal("hide");
                $("#prevContent").text(editedContent);
                $('.adviceContent').find('[name="advice"]').val(editedContent);
            },
            error: function (xhr, status, error) {
                console.error(error);
            }
        });
        
    });
    
    // 상담내용 삭제
    $("#dropAdviceButton").click(function () {
        let cno = $('[name="cno"]').val();
        
        $.ajax({
            url: "${ctxPath}/sell/advice/"+cno,
            type: "delete",
            success: function (message) {
                // 성공적인 응답을 받았을 때의 처리
                console.log(message);
                $("#adviceReadModal").modal("hide");
                $("#prevContent").text("상담이력이 없습니다.");
                $('.adviceContent').find('[name="advice"]').val("상담이력이 없습니다.");
                $('#adviceReadModal').find('.button').hide();
            },
            error: function (xhr, status, error) {
                console.error(error);
            }
        });
        
    });
	
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
		} else if(operration=='remove'){
			
			form.attr('action','${ctxPath}/sell/remove')
				.attr('method','post');
		}
		form.submit();
	});
})

</script>