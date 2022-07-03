<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0,user-scalable=no" />
    <title>易订货-商品清单</title>
    <link rel="stylesheet" href="/css/global.css">
    <link rel="stylesheet" href="/css/commodity/shopCart.css">
    <link rel="stylesheet" href="http://at.alicdn.com/t/font_1091326_sg61r64lenh.css">
</head>
<body>
<div class="shop_cart_page">
    <div class="shop_cart_top">

    </div>
    <div class="shop_cart_list" id="shop_cart_list"></div>
<#list productList as product>
    <div class="cart_list_item">
        <div><i class="iconfont iconquan"></i></div>
        <div style="background-image:url(${product.image})">
        </div>
        <div>
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
</body>