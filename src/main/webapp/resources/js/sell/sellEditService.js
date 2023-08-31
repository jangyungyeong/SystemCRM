var sellEditService = {

	getEditProduct : function(param, callback, error){
		let cno = param.cno;
		
		$.ajax({
			type : 'get',
			url : `${ctxPath}/product/getEditProduct/${cno}`,
			success : function(SellProduct){
				if(callback) callback(
					SellProduct.cno,
					SellProduct.productId,
					SellProduct.productName,
					SellProduct.productNumber,
					SellProduct.amount,
					SellProduct.price,
					SellProduct.total
				);
			},
			error:function(xhr, status, er){
				if(error) error(er);
			}
		})
		
	}
		
}