var sellEditService = {

	ProductList : function(param, callback, error){
		let cno = param.cno;
		
		$.ajax({
			type : 'get',
			url : `${ctxPath}/product/getEditProduct/${cno}`,
			success : function(sellProduct){
				if(callback) callback(sellProduct);
			},
			error:function(xhr, status, er){
				if(error) error(er);
			}
		})
		
	}
		
}