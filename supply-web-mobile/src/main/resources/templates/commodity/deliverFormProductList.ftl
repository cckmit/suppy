<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0,user-scalable=no" />
    <title>易订货-商品清单</title>
    <link rel="stylesheet" href="/css/global.css">
    <link rel="stylesheet" href="/css/commodity/shopCart.css">
    <link rel="stylesheet" href="http://at.alicdn.com/t/font_1091326_sg61r64lenh.css">
    <style>
        .shop_cart_top {
            background: #0BC6D7;
        }
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
<div class="shop_cart_page">
    <div class="shop_cart_top">

    </div>
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
                <div>￥<span class="cart_price">${product.price!}</span>/${product.unit!}</div>
            </div>
          <div>发货数量<span>${product.deliverQuantity}</span></div>
        </div>
    </div>
</#list>
</div>
</body>
