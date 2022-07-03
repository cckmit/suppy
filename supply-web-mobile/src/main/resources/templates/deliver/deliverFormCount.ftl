<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0,user-scalable=no"/>
    <title>发货单-统计</title>
    <link rel="stylesheet" href="/css/global.css">
    <link rel="stylesheet" href="/deliver/css/warehousing/warehouseList.css">
    <#--    <link rel="stylesheet" href="/css/layer.css">-->
    <link rel="stylesheet" href="http://at.alicdn.com/t/font_1091326_sg61r64lenh.css">
    <link rel="stylesheet" href="http://at.alicdn.com/t/font_1100572_48zi1jd7rlt.css">
    <link rel="stylesheet" href="http://at.alicdn.com/t/font_202335_gwbit3wv0zm.css">
    <style>

        .item_stepped span:first-child i,
        .item_stepped span:nth-child(3) i,
        .item_code_num span,
        .bottom_bar_active i, .bottom_bar_active p,


        /* =================== */
        .item_title{
            white-space: nowrap;
            overflow: hidden;
            text-overflow: ellipsis;
            margin-top: 0;
            margin-bottom: 0.1rem;
        }
        .cart_list_item{
            height: auto;
            align-items: center;
            margin-bottom: 0.1rem;
        }
        .cart_list_item>div:first-child{
            width: auto;
        }
        .cart_list_item>div:nth-child(3){
            padding-left: 0.15rem;
            width: 4.8rem;
        }
        .dgq_flex{
            display: flex;
            color: #B3B3B3;
            font-size: 0.26rem;
        }
        .dgq_flex div{
            width: 50%;
        }
        .dgq_flex span{
            color: #FF807D;
        }

        /* =================== */
        .item_title{
            white-space: nowrap;
            overflow: hidden;
            text-overflow: ellipsis;
            margin-top: 0;
            margin-bottom: 0.1rem;
        }
        .cart_list_item{
            height: auto;
            align-items: center;
            margin-bottom: 0.1rem;
        }
        .cart_list_item>div:first-child{
            width: auto;
        }
        .cart_list_item>div:nth-child(3){
            padding-left: 0.15rem;
            width: 4.8rem;
        }
        .dgq_flex{
            display: flex;
            color: #B3B3B3;
            font-size: 0.26rem;
        }
        .dgq_flex div{
            width: 50%;
        }
        .dgq_flex span{
            color: #FF807D;
        }


    </style>
</head>
<body>
<div class="shop_cart_page">
    <#list productList as product>
        <div class="cart_list_item cart_list_items">    
            <div><i class="iconfont iconquan"></i></div>
            <div style="background-image:url(${product.image})">
            </div>
            <div>
                <div class="item_title">${product.name!}</div>
                <div class="dgq_flex">商品编号：<span style="color: #0BC6D7">${product.id}</span></div>
                <div class="dgq_flex">商品价格：<span>￥${product.price!}</span>/${product.unit!}</div>
                <div class="dgq_flex"><div>已发货数量：<span>${product.sureDeliver}</span></div><div>确定数量：<span>${product.sure}</span></div></div>
                <div class="dgq_flex"><div>未确定数量：<span>${product.waitSure}</span></div></div>
            </div>
        </div>
    </#list>
</div>
<#--<script src="/deliver/js/tmpl.min.js"></script>-->
<script src="/js/jquery.min.js"></script>
<#--<script src="/js/global.js"></script>-->
<#--<script src="/purchase/js/stock/stockList.js"></script>-->
<#--<script src="/js/layer.js"></script>-->

</body>
</html>