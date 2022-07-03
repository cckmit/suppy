<!DOCTYPE html>
<html>
  <head>
      <meta charset="utf-8">
      <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0,user-scalable=no" />
      <title>易订货-订单付款</title>
      <link rel="stylesheet" href="/css/global.css">
      <link rel="stylesheet" href="/css/order/orderPay.css">
      <link rel="stylesheet" href="/css/layer.css">
      <link rel="stylesheet" href="http://at.alicdn.com/t/font_1091326_sr48ma9pxf.css">
  </head>
  <body>
    <div class="order_pay_page">
      <div class="order_pay_price">
        <div>
          <span>订单金额</span>
          <span>￥${totalprice!0}</span>
        </div>
        <#--<div>-->
          <#--<span>代付金额</span>-->
          <#--<span>￥66.06</span>-->
        <#--</div>-->
      </div>
      <#--<div class="order_pay_sec">-->
        <#--<div>-->
          <#--<div>预付款账户</div>-->
          <#--<div>-->
            <#--<span class="order_pay_money">-->
              <#--<span>￥0.00</span>-->
              <#--<span class="order_money_bottom"></span>-->
            <#--</span>-->
          <#--</div>-->
        <#--</div>-->
        <#--<div>余额：￥0.00</div>-->
      <#--</div>-->
      <div class="order_pay_date">
        <div>
          <span>支付金额</span>
          <span>￥${totalprice!0}</span>
        </div>
        <#--<div>-->
          <#--<span>支付日期</span>-->
          <#--<span>${totalprice}</span>-->
        <#--</div>-->
      </div>
      <#--<div class="order_pay_account">-->
        <#--<div>收款账号<i class="iconfont icontubiao-22"></i></div>-->
        <#--<div class="account_detail">开户银行：招商银行深圳源兴支行</div>-->
        <#--<div class="account_detail">账户名称：深圳市计算有限公司</div>-->
        <#--<div class="account_detail">开户账号：7559 2477 7810 803</div>-->
      <#--</div>-->
      <div class="order_pay_remarks">
        <div>
          <span>备注</span>
          <input type="text" placeholder="请输入">
        </div>
        <#--<div>-->
          <#--<span>附件</span>-->
          <#--<input type="text" placeholder="请添加" readonly>-->
        <#--</div>-->
      </div>
      <button onclick="pay()">确认支付</button>
    </div>

    <script src="/js/jquery.min.js"></script>
    <script src="/js/layer.js"></script>
    <script>
        function pay() {
            var pid = parseInt('${purchaseFormId?c!'0'}');
            var totalprice = '${totalprice?c!'0'}'
            $.ajax({
                url:"payJson",
                method:"post",
                data:{"purchaseFormId":pid,"totalprice":totalprice},
                success:function (data) {
                    console.log(data)
                    //信息框
                    layer.open({
                        content: data.msg
                        ,btn: '我知道了'
                        ,yes: function(index){
                            if(data.status=200){
                                window.location.href =  "index";
                            }
                            layer.close(index);
                        }
                    });
                }
            })
        }
    </script>
  </body>
</html>