<head>
    <meta charset="utf-8">
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0,user-scalable=no"/>
    <title>易订货-订货单详情</title>
    <link rel="stylesheet" href="/css/global.css">
    <link rel="stylesheet" href="/css/order/orderListDetail.css">
    <link rel="stylesheet" href="/css/commodity/shopCart.css">
    <link rel="stylesheet" href="/css/layer.css">
    <link rel="stylesheet" href="http://at.alicdn.com/t/font_1091326_sr48ma9pxf.css">
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
                <span>￥${purchaseForm.totalPrice}</span>
            </div>
            <div>
                <span>商品金额</span>
                <span>￥${purchaseForm.totalPrice}</span>
            </div>
        </div>
        <div class="order_record" id="showMoreOrder" <#--onclick="redirectToProductDetail()"-->>
            <span>商品清单</span>
            <span>共${category!0}款，总数${purchaseForm.totalQuantity!0}<i class="iconfont icontubiao-22"></i></span>
        </div>
    </div>
    <div class="order_record_new" style="display: block" id="isshow"  >
            <div class="shop_cart_list" id="shop_cart_list"></div>
            <#list productList as product>
                <div class="cart_list_item cart_list_items">
                    <div><i class="iconfont iconquan"></i></div>
                    <div style="background-image:url(${product.image})">
                    </div>
                    <div style="margin-left: 15px" >
                        <div class="item_title">${product.name}</div>
                        <div class="item_code_num">编号：${product.barcode}</div>
                        <div class="item_choose_num">
                            <div>￥<span class="cart_price">${product.price}</span>/${product.unit!}</div>
                        </div>

                        <div class="indent_num">订货数量:<span>${product.purchaseQuantity}</span></div>
                        <div class="residue_num">剩余发货数量:<span>${product.surplusQuantity}</span></div>
                    </div>
                </div>
            </#list>
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
            <#if flag!=5>
                <div>
                    <#--<a href="toVoidReason?formId=${purchaseForm.id!}">订单作废</a>-->
                    <span onclick="invalidInit()">订单作废</span>
                </div>
            </#if>
            <#if flag == 1>
                <div>
                    <#--<a href="pay?purchaseFormId=${purchaseForm.id!}" style="color:white;">去付款</a>-->
                    <span onclick="payInit()" style="color:white;">去付款</span>
                </div>
            </#if>
        </#if>
    </div>
</div>
<script src="/js/jquery.min.js"></script>
<script src="/js/layer.js"></script>
<script>
    $("#showMoreOrder").click(function () {
        $("#isshow").toggle();
    })

    function redirectToProductDetail() {
        window.location.href = "productList?purchaseFormId=${purchaseForm.id!}"
    }
    function invalidInit() {
        var createtime = new Date('${purchaseForm.createDate?string('yyyy-MM-dd HH:mm:ss')}')
        var now = new Date()
        var timeout = parseInt('${timeout!'0'}')
       /* var flag = (now.getTime()-createtime.getTime())>(timeout*3600*1000)*/
        layer.open({
            title: "确认作废",
            content: "确定要作废本订单么？"
            ,btn: ['确定','取消']
            ,yes: function(index){
                layer.close(index);
                //判断是否 超时
               /* if(flag){
                    layer.open({
                        title: "提示",
                        content: "超时"+timeout+"小时不能作废"
                        , btn: ['确定', '取消']
                        , yes: function(index){
                        layer.close(index);
                    }
                })
                }else {*/
                    window.location.href = "toVoidReason?formId=${purchaseForm.id!}";
               /* }*/
            }
        });
    }

    function payInit() {
        layer.open({
            title: "确认付款",
            content: "确认付款么？"
            ,btn: ['确定','取消']
            ,yes: function(index){
                window.location.href =  "pay?purchaseFormId=${purchaseForm.id!}";
                layer.close(index);
            }
        });
    }
</script>
</body>
