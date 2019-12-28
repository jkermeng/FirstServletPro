function deleteCart(cid) {
    var contP = $("#contextPath").val();
    alert(cid);
    $.ajax({
        url: contP + "/user/cart",// 请求的servlet地址
        type: "GET",// 请求方式
        data: {
            "cid": cid,
            "method": "deleteCart"
        },// 发送到服务器的数据
        dataType: "text",// 设置返回数据类型
        success: function () {
            //window.location.href=contP+"/shopping.jsp";
        },// 响应成功后执行的回调方法data响应文本
        complete: function (XMLHttpRequest, statusText) {

        },// 响应完成后执行的回调方法
        error: function (XMLHttpRequest, statusText) {
            alert("操作失败!")
        }// 响应失败后执行的回调方法
    })
}


function alterCount(cid) {

//	window.location.href="alterQuantity?"+cid+"_"+$("#"+cid).val()
    var contextPath = $("#contextPath").val();
    $.ajax({
        url: contextPath + "/user/cart",// 请求的servlet地址
        type: "GET",// 请求方式
        data: {
            "method": "alterCartNum",
            "cid": cid,
            "cartNum": $("#" + cid).val()
        },// 发送到服务器的数据
        dataType: "text",// 设置返回数据类型
        success: function (total) {
            $("#cartCount").html(total);
        },// 响应成功后执行的回调方法data响应文本
        complete: function (XMLHttpRequest, statusText) {

        },// 响应完成后执行的回调方法
        error: function (XMLHttpRequest, statusText) {
            alert("操作失败!")
        }// 响应失败后执行的回调方法
    })
}


//-按钮事件
function reduce(id) {

    if ($("#" + id).val() == 1) {
        $("#" + id).val(1)
    } else if ($("#" + id).val() >= 2) {
        var old = $("#" + id).val()
        $("#" + id).val(parseInt(old) - 1)
    }
    alterCount(id)
}

//+按钮事件
function increase(id) {
    var stock = $("#hpStock" + id).val()//获得库存
    var old = $("#" + id).val()//获得原来的数量
    if (parseInt(old) < parseInt(stock)) {
        $("#" + id).val(parseInt(old) + 1)
        alterCount(id)
    } else {
        alert("您选择的数量超过库存!")
    }
}


function checkStock(id) {
    var stock = $("#hpStock" + id).val()//获得库存
    var old = $("#" + id).val()//获得原来的数量
    if (parseInt(old) > parseInt(stock)) {
        alert("您选择的数量超过库存!")
    } else {
        alterCount(id)
    }
}


function buttonTest(id) {
    var contextPath = $("#contextPath").val();
    console.log("结算功能！！");
    //var contextPath=$(".button input:eq(0)").val();
    if (contextPath != null && contextPath != "") {
        //window.location.href=contextPath+"/shopping-result.jsp"
        $.post(contextPath + "/user/cart", {
            "method": "settleAccounts",
            "uid": id
        }, function () {
            // window.location.href = contextPath + "/shopping-result.jsp";
        })
    }

}











