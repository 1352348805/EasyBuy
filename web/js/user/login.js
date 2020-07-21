function login(){
    var path = $("#path").val();
    var loginName = $("input[name='loginName']").val();
    var password = $("input[name='password']").val();
    if (loginName==null){
        showMessage("用户名不能为空.");
        return;
    }
    if(password==""){
        showMessage("密码不能为空.");
        return;
    }
    $.ajax({
        url: path + "/Login",
        method: "post",
        data: {
            loginName: loginName,
            password: password
        },
        success: function (jsonStr) {
            var result = eval("(" + jsonStr + ")");
            if (result.code == 200) {
                showMessage(result.message);
                setTimeout(function () {
                    window.location.href = path + "/Home?action=index";
                },2000);
            } else {
                showMessage(result.message);
            }
        }
    })
}