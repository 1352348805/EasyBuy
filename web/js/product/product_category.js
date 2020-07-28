var path = $("#path").val();
function deleteProductCategory(id,type) {
    var bool=window.confirm("确认删除此分类信息么?");
    if(bool){
        $.ajax({
            url: path + "/admin/productCategory?id="+id,
            type: "delete",
            success: function (jsonStr) {
                var result = eval("(" + jsonStr + ")");
                if (result.code == 200) {
                    window.location.reload();
                }else{
                    showMessage(result.message);
                }
            }
        });
    }
}
function toUpdateProductCategoryList(obj) {
    var id = $(obj).val();
    var data = {
        action : "toUpdateProductCategory",
        id : id
    }
    $.ajax({
        url: path + "/admin/productCategory",
        type: "get",
        data: data,
        success: function (jsonStr) {
            $("#addProductCategory").html(jsonStr);

        }
    });
}
function addProductCategory() {
    var productCategoryLevel1 = $("#productCategoryLevel1").val();
    var productCategoryLevel2 = $("#productCategoryLevel2").val();
    var name = $("#name").val();
    var type = $("#type").val();
    $.ajax({
        url: path + "/admin/productCategory",
        type: "post",
        data: {
            action: "addProductCategory",
            name: name,
            type: type,
            productCategoryLevel1: (productCategoryLevel1 == null || productCategoryLevel1 == "") ? 0 : productCategoryLevel1,
            productCategoryLevel2: (productCategoryLevel2 == null || productCategoryLevel2 == "") ? 0 : productCategoryLevel2
        },
        success: function (jsonStr) {
            var result = eval("(" + jsonStr + ")");
            if (result.code == 200) {
                window.location.reload();
            } else {
                showMessage(result.message);
            }
        }
    });
}
function modifyProductCategory() {
    var id = $("#id").val();
    var productCategoryLevel1 = $("#productCategoryLevel1").val();
    var productCategoryLevel2 = $("#productCategoryLevel2").val();
    var name = $("#name").val();
    var type = $("#type").val();
    $.ajax({
        url: path + "/admin/productCategory",
        type: "post",
        data: {
            action: "modifyProductCategory",
            id: id,
            name: name,
            type: type,
            productCategoryLevel1: (productCategoryLevel1 == null || productCategoryLevel1 == "") ? 0 : productCategoryLevel1,
            productCategoryLevel2: (productCategoryLevel2 == null || productCategoryLevel2 == "") ? 0 : productCategoryLevel2
        },
        success: function (jsonStr) {
            var result = eval("(" + jsonStr + ")");
            if (result.code == 200) {
                window.location.reload();
            }
        }
    });
};
function saveOrUpdate() {
    var id = $("#id").val();
    if (id == null || id == "") {
        addProductCategory();
    } else {
        modifyProductCategory();
    }
}
function queryProductCategoryList(obj, selectId) {
    var parentId = $(obj).val();
    $.ajax({
        url: path + "/admin/productCategory",
        method: "post",
        data: {
            action: "queryProductCategoryList",
            parentId: parentId
        },
        success: function (jsonStr) {
            var result = eval("(" + jsonStr + ")");
            if (result.code == 200) {
                var options = "<option value=''>" + "请选择..." + "</option>";
                for (var i = 0; i < result.data.length; i++) {
                    var option = "<option value=" + result.data[i].id + ">" + result.data[i].name + "</option>";
                    options = options + option;
                }
                $("#" + selectId).html(options);
            }
        }
    });
}
function toAddProductCategory() {
    var data = {
        action: "toAddProductCategory"
    }
    var options = "";
    $.get(path + "/admin/productCategory", data, function (result) {
        for (var i = 0; i < result.data.length; i++) {
            var option = "<option value=" + result.data[i].id + ">" + result.data[i].name + "</option>";
            options = options + option;
        }
        var addHTML = "<table border=\"0\" class=\"add_tab\" style=\"width:930px;\" cellspacing=\"0\" cellpadding=\"0\">\n" +
            "    <tr>\n" +
            "        <td width=\"135\" align=\"right\">分类级别</td>\n" +
            "        <td colspan=\"3\" style=\"font-family:'宋体';\" >\n" +
            "            <select class=\"jj\" name=\"type\" style=\"background-color:#f6f6f6;\" id=\"type\"\n" +
            "                    onchange=\"selectProductCategoryLevel(this);\" >\n" +
            "                <option value=\"\" selected=\"selected\">请选择...</option>\n" +
            "                <option value=\"1\"\n" +
            "                         >一级分类\n" +
            "                </option>\n" +
            "                <option value=\"2\"\n" +
            "                         >二级分类\n" +
            "                </option>\n" +
            "                <option value=\"3\"\n" +
            "                         >三级分类\n" +
            "                </option>\n" +
            "            </select>\n" +
            "        </td>\n" +
            "    </tr>\n" +
            "        <tr  >\n" +
            "            <td width=\"135\" align=\"right\">一级分类</td>\n" +
            "            <td colspan=\"3\" style=\"font-family:'宋体';\">\n" +
            "                <select class=\"jj\" name=\"productCategoryLevel1\" style=\"background-color:#f6f6f6;\"  id=\"productCategoryLevel1\" onchange=\"queryProductCategoryList(this,'productCategoryLevel2');\">\n" +
            "                    <option value=\"0\" selected=\"selected\">请选择...</option>\n"
            + options
            +       "                </select>\n" +
            "            </td>\n" +
            "        </tr>\n" +
            "        <tr style=\"display:none\">\n" +
            "            <td width=\"135\" align=\"right\">二级分类</td>\n" +
            "            <td>\n" +
            "                <select class=\"jj\" name=\"productCategoryLevel2\" style=\"background-color:#f6f6f6;\"\n" +
            "                        id=\"productCategoryLevel2\">\n" +
            "                    <option value=\"0\" selected=\"selected\">请选择...</option>\n" +
            "                    \n" +
            "                </select>\n" +
            "            </td>\n" +
            "        </tr>\n" +
            "    <tr>\n" +
            "        <td align=\"right\">分类名称</td>\n" +
            "        <td style=\"font-family:'宋体';\">\n" +
            "            <input type=\"text\" value=\"\" class=\"add_ipt\" id=\"name\"/>（必填）\n" +
            "            <input type=\"hidden\" name=\"id\" value=\"\" id=\"id\">\n" +
            "        </td>\n" +
            "    </tr>\n" +
            "</table>\n" +
            "<p align=\"right\">\n" +
            "    <br>\n" +
            "    <a href=\"javascript:void(0);\" onclick=\"saveOrUpdate();\" class=\"add_b\">确认修改</a>\n" +
            "</p>";

        $("#addProductCategory").html(addHTML);
    }, "json");
}

function selectProductCategoryLevel(obj) {
    var level = $(obj).val();
    if (level == 1) {
        $('#productCategoryLevel1').parent().parent().hide();
        $('#productCategoryLevel2').parent().parent().hide();
    } else if (level == 2) {
        $('#productCategoryLevel1').parent().parent().show();
    } else {
        $('#productCategoryLevel1').parent().parent().show();
        $('#productCategoryLevel2').parent().parent().show();
    }
}