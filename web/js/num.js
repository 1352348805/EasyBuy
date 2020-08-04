// JavaScript Document


function addUpdate(jia){		
	var c = jia.parent().find(".n_ipt").val();
	c=parseInt(c)+1;	
	jia.parent().find(".n_ipt").val(c);
}

function jianUpdate(jian){    
	var c = jian.parent().find(".n_ipt").val();
	if(c==1){    
		c=1;    
	}else{    
		c=parseInt(c)-1;    
		jian.parent().find(".n_ipt").val(c);
	}
}    




function addUpdate1(jia){		
	var c = jia.parent().find(".car_ipt").val();
	var pid = jia.parent().find(".car_pid").val();
	var data = {
		action : "addUpdate",
		pid : pid,
		num : parseInt(c)+1
	}
	$.post("/EasyBuy/Cart",data,function (result) {
		if (result.code == 200) {
			var cartItems = result.data.cartItems;
			for (var i=0;i<cartItems.length;i++) {
				if (cartItems[i].pid == pid) {
					jia.parent().parent().parent().find(".car_total_price").html("￥"+cartItems[i].totalPrice.toFixed(1));
					c=parseInt(c)+1;
					jia.parent().find(".car_ipt").val(c);
					$("#totalPrice").html("￥"+result.data.totalPrice.toFixed(1));
				}
			}
		} else {
			alert(result.message);
		}
	},"json");

}

function jianUpdate1(jian){    
	var c = jian.parent().find(".car_ipt").val();
	var pid = jian.parent().find(".car_pid").val();
	if(c==1){    
		c=1;    
	}else{
		var data = {
			action : "jianUpdate",
			pid : pid
		}
		$.post("/EasyBuy/Cart",data,function (result) {
			if (result.code == 200) {
				var cartItems = result.data.cartItems;
				for (var i=0;i<cartItems.length;i++) {
					if (cartItems[i].pid == pid) {
						jian.parent().parent().parent().find(".car_total_price").html("￥"+cartItems[i].totalPrice.toFixed(1));
						c=parseInt(c)-1;
						jian.parent().find(".car_ipt").val(c);
						$("#totalPrice").html("￥"+result.data.totalPrice.toFixed(1));
					}
				}
			} else {
				alert(result.message);
			}
		},"json");
	}
}    
