<!DOCTYPE html>
<html lang="en" xmlns="http://www.w3.org/1999/html">
<head>
    <meta charset="utf-8">
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0,user-scalable=no"/>
    <title>易订货-订单详情</title>
    <link rel="stylesheet" href="/css/global.css">
    <link rel="stylesheet" href="/css/layer.css">
    <link rel="stylesheet" href="/css/personalCenter/orderDetail.css">
    <link rel="stylesheet" href="http://at.alicdn.com/t/font_1091326_sr48ma9pxf.css">
</head>
<body>
<div class="order_detail">
    <div class="order_detail_top">
        <div class="order_detail_await order_detail_padding">
            <div class="order_detail_await_number order_detail_left">${billno!}</div>
            <div class="order_detail_await_detail order_detail_right">
                <i class="iconfont icontubiao-25 order_detail_left"></i>
                <span>
                    待审核
            </span>
            </div>
        </div>
        <div>
            <div>
                <#list orgnames as orgname>
                    <div class="order_detail_global order_detail_padding">
                        <span>${orgname!}</span>
                        <a href="javascript:;" class="order_detail_right">
                            <i class="iconfont icontubiao-22 order_detail_right"></i>
                        </a>
                    </div>
                    <#list list as item>
                        <#if item.org_name==orgname>
                            <div class="order_detail_order">
                                <img src="${item.IMAGE!}" alt="" class="order_detail_left">
                                <div class="order_detail_order_name order_detail_right">
                                    <div class="order_detail_order_title">
                                        ${item.NAME!}
                                    </div>
                                    <div class="order_detail_order_number">
                                        ￥${item.purchase_price!0} x${item.purchase_quantity!0}袋
                                    </div>
                                </div>
                            </div>
                        </#if>
                    </#list>
                </#list>
                <div class="order_detail_look">
                    <a href="productList?purchaseFormId=${form.id!}">
                        商品清单（商品共${form.totalQuantity!0}）
                    </a>
                </div>
            </div>
        </div>
        <div class="order_detail_money">
            <div class="order_money">
                <span class="order_money_name">订单金额</span>
                <span class="order_money_number order_detail_right">￥${form.totalPrice!0}</span>
            </div>
            <div class="actual_money">
                <span class="actual_money_name">应付金额</span>
                <span class="actual_money_number order_detail_right">￥${form.totalPrice!0}</span>
            </div>
        </div>
    </div>
    <div class="order_detail_center">
        <#--<div class="order_detail_data order_detail_global">-->
            <#--<span>交货日期</span>-->
            <#--<i>无</i>-->
        <#--</div>-->
        <div class="reap">
            <div class="reap_title">
                收货信息
            </div>
            <div class="reap_name f">
                <span class="order_detail_left"><span>收货人</span>：${plist.name!}</span>
                <span class="order_detail_right reap_mobile">${plist.telephone!}</span>
            </div>
            <div class="reap_address">
                <span>机&nbsp;&nbsp;&nbsp;构</span>：${plist.org_name!}
            </div>
            <#--<div class="reap_address">-->
                <#--<span>地&nbsp;&nbsp;&nbsp;址</span>：${plist.address!}-->
            <#--</div>-->
        </div>
    </div>
    <div class="order_detail_global  order_detail_beizhu">
        <span>备注信息</span>
        <input style="width:79%;
    float: right;
    height: 0.88rem;
    padding-left: 12px;" type="text" id="remark" placeholder="请输入备注"/>
    </div>
    <div class="order_detail_bottom">
        <a href="toVoidReason?formId=${form.id!}">作废</a>
        <a onclick="checkOrder();" class="order_detail_submit">订单审核</a>
    </div>
</div>
</body>

<script>
    function  checkOrder() {
        layer.open({
            content: '确定要审核该订货单么？'
            ,btn: ['确定', '不要']
            ,yes: function(index){
                var remark = $('#remark').val();
                $.ajax({
                    url:"checkOrder",
                    method:"post",
                    data:{"formId":'${form.id!}',"remark":remark},
                    success:function (data) {
                        console.log(data)
                        layer.open({
                            content: data.msg
                            ,btn: '我知道了'
                            ,yes: function (index) {
                                if(data.status=200){
                                    window.location.href='myinfo';
                                }
                                layer.close(index);
                            }
                        });
                    }
                })
                layer.close(index);
            }
        });
    }
</script>
<script src="/js/jquery.min.js"></script>
<script src="/js/layer.js"></script>
<script src="/js/personalCenter/orderDetails.js"></script>
</html>