$(function () {
    refresh_heji();


    $("#submitButton").click(function () {
        var flag = checkCart();
        if(!flag){
            alertPopMsg("请选择商品！")
            return;
        }
        $(".submit-comfirm").show();
    });

    //生成订单提示框-确认生成订单
    $(".submit-comfirm .sure").click(function(){
        $(".submit-comfirm").hide();
        $("#form").ajaxSubmit(function (data) {
            if (data.status != 200) {
                alert(data.msg)
            } else if (data.status == 200) {
                window.location.href = "/order/" + data.data;
            }
        });
    });
    //生成订单提示框-取消
    $(".submit-comfirm .cancel").click(function(){
        $('.submit-comfirm').hide();
    });
    //消息提示框-关闭
    $(".pop-msg .cancel").click(function(){
        $('.pop-msg').hide();
    });

    $(".recent").click(function () {
        $.get("/cart/recent/1", function (data) {
            if (data.status != 200) {
                alert(data.msg)
            } else if (data.status == 200) {
                window.location.href = "/order/" + data.data.main.billno;
            }
        });

    });

    $(".cart-add-one").click(function () {

        var id = $(this).parent().find("[name='productIds']").val();
        var val = $(this).parent().find("[name='counts']").val();

        var that = this;
        $.post("/cart/add",{"productId":id,"count":1},function (data) {
            if(data.status == 200){
                $(that).parent().find("[name='counts']").val(parseInt(val)+1);
                $(that).parent().find(".result").html(parseInt(val)+1);
                refresh_xiaoji(that);
                refresh_heji();
            }
        });

    });
    $(".cart-reduce-one").click(function () {

        var id = $(this).parent().find("[name='productIds']").val();
        var val = $(this).parent().find("[name='counts']").val();
        if(val <= 1){
            return;
        }
        var that = this;
        $.post("/cart/add",{"productId":id,"count":-1},function (data) {
            if(data.status == 200) {
                $(that).parent().find("[name='counts']").val(parseInt(val) - 1);
                $(that).parent().find(".result").html(parseInt(val)-1);
                refresh_xiaoji(that);
                refresh_heji();
            }
        });

    });

    $(".cart-delete").click(function () {

        var id = $(this).parent().parent().find("[name='productIds']").val();
        var that = this;
        $.post("/cart/delete/"+id,function (data) {
            if(data.status == 200){
                $(that).parent().parent().remove();
                refresh_heji();
            }
        });

    });
});
//计算小计
function refresh_xiaoji(el) {
    var price = $(el).parent().parent().parent().parent().find(".danjia").html()
    var price_jf = $(el).parent().parent().parent().parent().find(".danjia-JF").html()
    var count = $(el).parent().find("[name=counts]").val();
    var result = parseFloat(count)*parseFloat(price);
    var result_jf = parseFloat(count)*parseFloat(price_jf);
    $(el).parent().parent().parent().parent().find(".xiaoji").html(result.toFixed(2));
    $(el).parent().parent().parent().parent().find(".xiaoji-JF").html(result_jf.toFixed(0));

}
//计算合计
function refresh_heji() {
    $(".heji").html(0);
    $(".xiaoji").each(function (data) {
        var xiaoji = $(this).html();
        var heji = $(".heji").html();
        var result = parseFloat(heji) + parseFloat(xiaoji);
        $(".heji").html(result.toFixed(2));
        $(".heji-JF").html((result*100).toFixed(0));
    });
    $(".totalcount").html(0);
    $("input[name=counts]").each(function (data) {
        var count = $(this).val();
        var totalcount = $(".totalcount").html();
        $(".totalcount").html(parseInt(totalcount) + parseInt(count));
    });
}

//弹窗方法；
function alertPopMsg(msg) {
    $(".pop-msg .des").html(msg);
    $(".pop-msg").show();
}
//检查是否为空
function checkCart() {
    if($("input[name=counts]").length > 0){
        return true;
    }else {
        return false;
    }
}