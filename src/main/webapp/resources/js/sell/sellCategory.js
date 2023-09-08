let productIdList = [];

$(function(){
	
	// 구매 변경
	let productTable = $('.productTable');
	let parentCategory = $('.parentCategory');
	let childCategory=null;
	let product= null;
	let productNum = $('.productNum');
	
	// 1차분류선택시
	parentCategory.change(function(){
		let parentId = $(this).val();
		$('.productNumber').remove();
		if (parentId=='') {
			$('.childCategory, .product').remove()
			return;
		};
		$.ajax({
			type : 'get',
			url : `${ctxPath}/product/childCategory/${parentId}`,
			success : function(result){
				let selectTag = `<select class="childCategory">
					<option value="">2차분류</option>`;
				$(result).each(function(i,e){
					selectTag += `<option value="${e.categoryId}">${e.categoryName}</option>`;
				});
			selectTag += `</select>`
			$('.childCategory, .product').remove()
			parentCategory.after(selectTag)
			childCategory = $('.childCategory')
			}
		});
	});	
	
	// 2차분류 선택시
	$('.selectList').on('change','.childCategory',function() {
		let categoryId = $(this).val();
		$('.productNumber, .product').remove();
		
		if (categoryId=='') {
			console.log('상품없음')
			return;
		};
		$.ajax({
			type : 'get',
			url : `${ctxPath}/product/productList/${categoryId}`,
			success : function(result){
				let selectTag = `<select class="product">
					<option value="">상품명</option>`;
				$(result).each(function(i,e){
					selectTag += `<option value="${e.productId}" data-pnum="${e.productNumber}">${e.productName}</option>`;
				});
				selectTag += `</select>`
				childCategory.after(selectTag);
				product = $('.product')
			}
		});
	});
	
	// 상품명 선택시
	$('.selectList').on('change','.product',function(){
		let productId = $(this).val();
		let pnum = $(this).children("option:selected").data('pnum');
		$('.productNumber').remove();
		if (productId=='') {
			console.log('품번없음')
		};
		if(pnum!=null){
			let inputTag = `<input type="text" class="productNumber" value="${pnum}">`;
			product.after(inputTag);
		}
		
		// 제품추가 이벤트 등록
		$.ajax({
			type : 'get', 
			url : `${ctxPath}/product/productInfo/${productId}`, 
			success : function(result){
				productListRendering(result)
			}
		});
	});
	
	// 제품목록 추가
	function productListRendering(result){
		productInfo = 
		`<tr>
			<td>${result.productName}</td>
			<td>${result.productNumber == null ? '' : result.productNumber}</td>
			<td class="price">${result.price}</td>
			<td><input type="number" value="1" min="1"></td>
			<td><button class="productRowDel" data-productid="${result.productId}">삭제</button></td>
		</tr>`

		// 제품이 있으면 추가하지 않음	
		if(productIdList.includes(result.productId)){
			return;
		}
		
		productIdList.push(result.productId);
		productTable.append(productInfo);

		calcSum();
	}
		
	
	productTable.on('click','.productRowDel',function(){
		let productId = $(this).data('productid');
		productIdList = productIdList.filter(e=> e != productId);
		$(this).closest('tr').remove();
		calcSum();
	})
	
});

// 이미 판매한 상품 변경처리
function editCategory(){
	let productTable = $('.productTable');
	let cnoValue = $('.readNum[name="cno"]').val()
	let param = {cno:cnoValue};
	
	sellEditService.ProductList(param,function(sellProduct){
		
		let editProduct=`
			<tr>
				<th>상품명</th>
				<th>품번</th>
				<th>단가</th>
				<th>수량</th>
				<th>삭제</th>
			</tr>
		`;
		
		$.each(sellProduct,function(idx,elem){
			if(elem.productName!='total'){
				productIdList.push(elem.productId);
				editProduct += `
					<tr>
						<td>${elem.productName}</td>
	        			<td>${elem.productNumber || ''}</td>
	        			<td class="price">${elem.price }</td>
	        			<td><input type="number" value="${elem.amount }" min="1"></td>
	        			<td><button class="productRowDel" data-productId="${elem.productId }">삭제</button></td><tr>
				    </tr>`
			} else {
				$('.total').html(elem.total);
			}
		})
		productTable.html(editProduct);
	});
	
	$('.productTable').on('change','[type="number"]',function(){
		calcSum();
	});
}

function calcSum(){
	console.log('합계계산');
	let result = 0;
	$('.price').each(function(i,elem){
		console.log($(elem).next().find('input').val());
		let price = $(elem).html();
		let amount = $(elem).next().find('input').val();
		result+=price*amount	
	});
	
	$('.total').html(result)
}
