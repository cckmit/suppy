<head>
    <meta charset="utf-8">
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0,user-scalable=no"/>
    <title>易订货-订货单详情</title>
    <link rel="stylesheet" href="/css/global.css">
    <link rel="stylesheet" href="/css/order/orderListDetail.css">
    <link rel="stylesheet" href="http://at.alicdn.com/t/font_1091326_sr48ma9pxf.css">
</head>
<body>
<div class="order_detail_page">
    <div class="order_detail_status">
        <div class="order_detail_status_title">
            <span>订单状态</span>
            <span>
            <#if flag == 1>
                等待付款
            <#elseif flag == 2>
                等待审核
            <#elseif flag == 3>
                等待审核
            <#elseif flag == 4>
                已审核
            <#elseif flag == 5>
                已完成
            <#elseif flag == 6>
                已作废
            <#else >
                已超时
            </#if>
            </span>
        </div>
        <#--<div class="order_detail_status_main">-->
            <#--公司名称：米高机器培训-->
        <#--</div>-->
        <div class="order_detail_status_main">
            订货单号：${purchaseForm.purchaseBillno}
        </div>
        <div class="order_detail_status_main">
            下单时间：${purchaseForm.createDate?string('yyyy-MM-dd HH:mm:ss')}
        </div>
        <#--<button>催办</button>-->
    </div>
    <div class="order_detail_price">
        <div>
            <div>
                <span>订单金额</span>
                <span>${purchaseForm.totalPrice}</span>
            </div>
            <div>
                <span>商品金额</span>
                <span>${purchaseForm.totalPrice}</span>
            </div>
        </div>
        <div class="order_record" onclick="redirectToProductDetail()">
            <span>商品清单</span>
            <span>共${category!0}款，总数${purchaseForm.totalQuantity!0}<i class="iconfont icontubiao-22"></i></span>
        </div>
    </div>
    <div class="order_detail_info">
        <div>
            <span>审核记录</span>
            <span><#if purchaseForm.checkStatus?? && purchaseForm.checkStatus == 0>
                未审核
            <#else >
                已审核
            </#if></span>
        </div>
        <div>
            <span>审核日期</span>
            <span>
            <#if purchaseForm.checkDate??>
            ${purchaseForm.checkDate?string('yyyy-MM-dd HH:mm:ss')}
            <#else >
                无
            </#if>
            </span>
        </div>
        <div>
            <span>备注信息</span>
            <span><#if purchaseForm.checkRemark??>
            ${purchaseForm.checkRemark}
            <#else >
                无
            </#if></span>
        </div>
    </div>
    <#--<div class="order_detail_address">-->
        <#--<div>-->
            <#--<span>收货信息</span>-->
            <#--<span></span>-->
        <#--</div>-->
        <#--<div>客户名称：米高机器培训</div>-->
        <#--<div>-->
            <#--<span>收货人：李媛媛</span>-->
            <#--<span>15882668833</span>-->
        <#--</div>-->
    <#--</div>-->
    <div class="order_detail_bottom">
        <#if flag!=6>
            <div><a href="toVoidReason?formId=${purchaseForm.id!}">订单作废</a></div>
            <div><#if flag == 1>
            <a href="pay?purchaseFormId=${purchaseForm.id!}&totalprice=${purchaseForm.totalPrice}" style="color:white;">去付款</a>
            <#else >
            再次购买
            </#if></div>
        </#if>
    </div>
</div>
<script src="http://www.jq22.com/jquery/jquery-1.10.2.js"></script>
<script>
    function redirectToProductDetail() {
        window.location.href = "productList?purchaseFormId=${purchaseForm.id!}"
    }
</script>
</body>
