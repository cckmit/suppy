$(function () {
    $("#pay").click(function () {
        $(".pay-wait").show();
        // ocx
        var def = "03";//交易渠道,'01'--银行卡 '03'--全民惠（积分）  '10'--扫一扫2
        var ghi = "07";//交易类型，'07'--积分消费
        var field3 = $("#totalPrice").html();//金额，最多精确到分。1.23;最大长度12，含小数点
        var field4 = "";//凭证号，最大长度6
        var field5 = "";//交易参考号，最大长度12
        var field6 = "";//交易时间 长度8或14=》20151216或20151216172210
        var field7 = "";//支付码，最大长度64
        var field8 = "";//操作员号，最大长度20
        var field9 = "";//第三方流水号，最大长度20
        var field10 = "";//授权号，最大长度6
        var field11 = "";//可打折金额，最大长度12，含小数点
        var field12 = "";//备注，最大长度?
        var abc = field3
            +"|"+field4
            +"|"+field5
            +"|"+field6
            +"|"+field7
            +"|"+field8
            +"|"+field9
            +"|"+field10
            +"|"+field11
            +"|"+field12;
        var response ;
        try {
            // todo 测试，直接通过
            // msubmit();

            response = trans.umsTraditionalPay(def,ghi,abc);
            // test todo remove
            // alert(response);
            var arr = response.split("|");
            // arr[5] --> 凭证号
            // arr[6] --> 商户号
            // arr[7] --> 终端号
            if(arr[0] == "00"){
                //pay yes
                $("#remark").val("OCX支付返回:"+arr[5]+"|"+arr[6]+"|"+arr[7]);
                msubmit();
            }else {
                alert(response);
                alertPopMsg(response,"warning")
            }
        } catch(err) {
            // alert(err);
            alertPopMsg(err,"warning");
        }

        $(".pay-wait").hide();
    });

    $("#rePay").click(function () {
        window.location.reload();
    });

    //消息提示框-关闭
    $(".pop-msg .alert_opt").click(function(){
        $('.pop-msg').hide();
        window.location.reload();
    });
});

function msubmit() {
    $("#form").ajaxSubmit(function (data) {
        if (data.status != 200) {
            alertPopMsg(data.msg,"warning");
        } else if (data.status == 200) {
            //暂时不要打印
            // print("兑换单号："+data.data);
            alertPopMsg("支付成功！请收取好POS小票！","info");
        }

    });
}

//弹窗方法；
function alertPopMsg(msg,stat) {
    switch (stat){
        case "info":
            $(".pop-msg img").attr("src","/images/duihao-green.png");
            break;
        case "warning":
            $(".pop-msg img").attr("src","/images/tanhao-blue.png");
            break;
    }
    $(".pop-msg .des").html(msg);
    $(".pop-msg").show();
}


function print(content) {
    var LODOP;
    LODOP = getLodop(document.getElementById('LODOP_OB'), document.getElementById('LODOP_EM'));
    LODOP.PRINT_INIT("");
    LODOP.ADD_PRINT_HTM(0, 0, "100%", "100%", content);
//            LODOP.SET_PRINT_PAGESIZE(3, 1000, 45, "");//小票打印机
    LODOP.SET_PRINT_PAGESIZE(1,0,0,"A4");//HP打印机
    LODOP.PRINT();
}