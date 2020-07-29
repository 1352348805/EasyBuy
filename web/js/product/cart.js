var path = $("#path").val();

//添加到购物车
function addCartByParam(id,num) {
    var data = {
        action : "addToCart",
        pid : id,
        num : num
    };
    $.get(path + "/Cart",data,function (data) {
        if (data.code == 200) {
            refreshCart();
            showMessage("添加成功");
        } else {
            showMessage(data.message);
        }
    },"json");
}

//刷新购物车
function refreshCart() {
    var data = {
        action : "refreshCart"
    }
    $.get(path+"/Cart",data,function (data) {
        if (data != null) {
            $("#itemNum").html(data.count);
            var itemHtml = "";
            for (var i=0;i<data.cartItems.length;i++) {
                itemHtml +=
                    "<li>\n" +
                    "    <div class=\"img\"><a href=\"#\"><img src=\""+path+"/images/"+data.cartItems[i].fileName+"\" width=\"58\" height=\"58\" /></a></div>\n" +
                    "                    <div class=\"name\"><a href=\"#\">"+data.cartItems[i].name+"</a></div>\n" +
                    "    <div class=\"price\"><font color=\"#ff4e00\">"+data.cartItems[i].price+"</font> X"+data.cartItems[i].count+"</div>\n" +
                    "</li>";
            }
            $("#cars").html(itemHtml);
            $("#price_sum").html(data.totalPrice);
        }
    },"json");
}