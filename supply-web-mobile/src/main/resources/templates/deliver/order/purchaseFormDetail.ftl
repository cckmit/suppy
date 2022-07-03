<head>
    <meta charset="utf-8">
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0,user-scalable=no"/>
    <title>易订货-订单详情</title>
    <link rel="stylesheet" href="/deliver/css/global.css">
    <link rel="stylesheet" href="/deliver/css/order/orderGoodsDetail.css">
    <link rel="stylesheet" href="http://at.alicdn.com/t/font_1091326_sr48ma9pxf.css">
    <style>
        .btn{
            width: 100%!important;
            background: #0BC6D7!important;
            color: #fff!important;
        }
    </style>
</head>
<body>
<div class="order_detail">
    <div class="order_detail_top">
    <#--<div class="order_detail_await order_detail_padding">-->
    <#--<div class="order_detail_await_number order_detail_left">${billno!}</div>-->
    <#--<div class="order_detail_await_detail order_detail_right">-->
    <#--<i class="iconfont icontubiao-25 order_detail_left"></i>-->
    <#--<span>-->
    <#--待审核-->
    <#--</span>-->
    <#--</div>-->
    <#--</div>-->
        <div>
            <div>

            <#list productDtoList as item>
                <div class="order_detail_order">
                    <img src="${item.image!}" alt="" class="order_detail_left">
                    <div class="order_detail_order_name order_detail_right">
                        <div class="order_detail_order_title">
                        ${item.name!}
                        </div>
                        <div class="order_detail_order_number">
                            ￥${item.price!0} x${item.purchaseQuantity!0}${item.unit!}
                        </div>
                    </div>
                </div>
            </#list>

            <#--<div class="order_detail_look">-->
            <#--<a href="/deliver/deliverProductList?purchaseFormId=${form.id!}">-->
            <#--商品清单（商品共${form.totalQuantity!0}）-->
            <#--</a>-->
            <#--</div>-->
            </div>
        </div>
        <div class="order_detail_money">
            <div class="order_money">
                <span class="order_money_name">订单金额</span>
                <span class="order_money_number order_detail_right">￥${totalPrice!0}</span>
            </div>
            <div class="actual_money">
                <span class="actual_money_name">应付金额</span>
                <span class="actual_money_number order_detail_right">￥${totalPrice!0}</span>
            </div>
        </div>
    </div>

    <div class="order_detail_center">
        <div class="reap">
            <div class="reap_title">
                收货信息
            </div>
            <div class="reap_name f">
                <span class="order_detail_left"><span>收货人</span>：${(plist.name)!""}</span>
                <span class="order_detail_right reap_mobile">${(plist.telephone)!""}</span>
            </div>
            <div class="reap_address">
                <span>机&nbsp;&nbsp;&nbsp;构</span>：${(plist.org_name)!""}
            </div>
        <#--<div class="reap_address">-->
        <#--<span>地&nbsp;&nbsp;&nbsp;址</span>：${plist.address!}-->
        <#--</div>-->
        </div>
    </div>
<#--<div class="order_detail_global  order_detail_beizhu">-->
<#--<span>备注信息</span>-->
<#--<input type="text" placeholder="请输入" readonly>-->
<#--</div>-->
    <#if deliverStatus == 0>
        <div class="order_detail_bottom">
            <a  class="btn" href="/deliver/deliverProductInit?purchaseFormId=${form.id!}">发货</a>
        </div>
    </#if>
</div>
<!-- 模态框 -->
<div class="order_detail_modal">
    <div class="order_detail_modal_main">
        <div class="order_detail_modal_content">
            <div class="title">备注信息</div>
            <input type="text" placeholder="请输入备注信息">
        </div>
        <div class="order_detail_modal_btn f">
            <span class="order_detail_modal_btn_cancel">取消</span>
            <span class="order_detail_modal_btn_notarize">确定</span>
        </div>
    </div>
</div>
</body>
<script src="http://www.jq22.com/jquery/jquery-1.10.2.js"></script>
<script src="/deliver/js/clipboard.min.js"></script>
<script src="/deliver/js/order/orderGoodsDetail.js"></script>
<script>
    var content = $(".reap_name .order_detail_left").html() + $('.reap_name .reap_mobile').html() + $('.reap_address').html()
    var clipboard = new ClipboardJS('.copy', {
        text: function () {
            return content
        }
    })
    clipboard.on('success', function (e) {
        console.log('复制成功')
    })
</script>
