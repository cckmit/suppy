<head>
    <meta charset="utf-8">
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0,user-scalable=no"/>
    <title>易订货-发货单详情</title>
    <link rel="stylesheet" href="/css/global.css">
    <link rel="stylesheet" href="/css/order/orderListDetail.css">
    <link rel="stylesheet" href="/css/commodity/shopCart.css">
    <link rel="stylesheet" href="http://at.alicdn.com/t/font_1091326_sr48ma9pxf.css">
    <link rel="stylesheet" href="/css/layer.css">
    <style>
        .cart_list_items>div:nth-child(2) {
            width: 1.5rem;
            height: 1.5rem;
            background-position: center;
            background-repeat: no-repeat;
            background-size: cover;
            position: relative;
        }
        .cart_list_items>div:nth-child(3) {
            width: 75%;
            height: auto;
        }
        .cart_list_items>div:nth-child(3) div {
            position: relative;
            position: relative;
            background: none;
            height: auto;
            font-size: small;
            color: none;
            text-align: left;
            height: inherit;
            width: auto;
            line-height: auto;
            color:#333;
        }
        .item_code_num {
            width: 100%!important;
            height: 0.2rem!important;
            font-size: 0.2rem!important;
            line-height: 0.2rem!important;
            color: #B3B3B3!important;
            margin-top: 0.18rem!important;
        }
        .item_choose_num>div:first-child {
            font-size: 0.26rem!important;
            color: #F95B57!important;
            float: left!important;
            line-height: 0.5rem!important;
        }
        .item_title {
            margin-top:0;
        }
    </style>
</head>
<body>
<div class="order_detail_page">
    <div class="order_detail_status">
        <div class="order_detail_status_title">
            <span>发货单状态</span>
            <span>
            <#if deliverForm.status == 0>
                未确认
            <#elseif deliverForm.status == 1>
                已确认
            <#elseif deliverForm.status == 2>
                已拒绝
            </#if>
            </span>
        </div>
    <#--<div class="order_detail_status_main">-->
    <#--公司名称：米高机器培训-->
    <#--</div>-->
        <div class="order_detail_status_main">
            发货单号：${deliverForm.deliverBillno}
        </div>
        <div class="order_detail_status_main">
            发货时间：${deliverForm.createDate?string('yyyy-MM-dd HH:mm:ss')}
        </div>
    <#--<button>催办</button>-->
    </div>
    <div class="order_detail_price">
        <div>
            <div>
                <span>订单金额</span>
                <span>${deliverForm.totalPriceBank}</span>
            </div>
            <div>
                <span>商品金额</span>
                <span>${deliverForm.totalPriceBank}</span>
            </div>
        </div>
        <div class="order_record" id="showMoreOrder" <#--onclick="redirectToProductDetail()"-->>
            <span>商品清单</span>
            <span>总数${deliverForm.totalQuantity!0}<i class="iconfont icontubiao-22"></i></span>
        </div>
    </div>
    <div class="order_record_new" style="display: block" id="isshow"  >
        <div class="shop_cart_list" id="shop_cart_list"></div>
        <#list productList as product>
            <div class="cart_list_item cart_list_items">
                <div><i class="iconfont iconquan"></i></div>
                <div style="background-image:url(${product.image})">
                </div>
                <div>
                    <div class="item_title">${product.name}</div>
                    <div class="item_code_num">编号：${product.barcode}</div>
                    <div class="item_choose_num">
                        <div>￥<span class="cart_price">${product.purchasePrice}</span>/${product.unit!}</div>
                    </div>
                    <div class="indent_num">发货数量:<span>${product.deliverQuantity}</span></div>
                </div>
            </div>
        </#list>

    </div>
    <div class="order_detail_info">
        <div>
            <span>清算记录</span>
            <span><#if deliverForm.settleStatus == 0>
                未清算
            <#elseif deliverForm.settleStatus == 1 >
                已发起
            <#elseif deliverForm.settleStatus == 2 >
                已清算
            </#if></span>
        </div>
        <div>
            <span>清算记录</span>
            <span>
            <#if deliverForm.updateDate??>
            ${deliverForm.updateDate?string('yyyy-MM-dd HH:mm:ss')}
            <#else >
                无
            </#if>
            </span>
        </div>
        <#--<div>-->
            <#--<span>备注信息</span>-->
            <#--<span><#if purchaseForm.checkRemark??>-->
            <#--${purchaseForm.checkRemark}-->
            <#--<#else >-->
                <#--无-->
            <#--</#if></span>-->
        <#--</div>-->
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
    <#if deliverForm.status == 0>
        <div><a onclick="reject();" >拒绝</a></div>
        <div><a onclick="confirm();" style="color:white;">确认</a></div>
    </#if>

    </div>
</div>
<script src="/js/jquery.min.js"></script>
<script src="/js/layer.js"></script>
<script>

    var isRejectClick = true;
    function reject(){
        if(isRejectClick){
            isRejectClick= false;
            layer.open({
                content: '您确定要拒绝该发货单吗？'
                ,btn: ['确定', '不要']
                ,yes: function(index){
                    $.ajax({
                        url:"deliverFormRejected",
                        method:"post",
                        data:{"deliverFormId":'${deliverForm.id!}'},
                        success:function (data) {
                            //信息框
                            layer.open({
                                content: data.msg
                                ,btn: '我知道了'
                                ,yes: function (index) {
                                    if(data.status==200){
                                        location.reload();
                                    }
                                    layer.close(index);
                                }
                            });

                        }
                    })
                    layer.close(index);
                }
            });
            setTimeout(function(){
                isRejectClick = true;
            }, 3000);
        }
    }

    var isConfirmClick = true;
    function confirm() {
        if(isConfirmClick){
            isConfirmClick= false;
            //询问框
            layer.open({
                content: '您确定要确认收货此发货单吗？'
                ,btn: ['确定', '不要']
                ,yes: function(index){
                    $.ajax({
                        url:"deliverFormConfirmed",
                        method:"post",
                        data:{"purchaseFormId":'${deliverForm.purchaseFormId!}',"deliverFormId":'${deliverForm.id!}'},
                        success:function (data) {
                            //信息框
                            layer.open({
                                content: data.msg
                                ,btn: '我知道了'
                                ,yes: function (index) {
                                    if(data.status==200){
                                        location.reload();
                                    }
                                    layer.close(index);
                                }
                            });

                        }
                    })
                    layer.close(index);
                }
            });
            setTimeout(function(){
                isConfirmClick = true;
            }, 3000);
        }

    }
    $("#showMoreOrder").click(function () {
        $("#isshow").toggle();
    })
    function redirectToProductDetail() {
        window.location.href = "/purchase/deliverFormProductList?deliverFormId=${deliverForm.id!}"
    }
</script>
</body>
