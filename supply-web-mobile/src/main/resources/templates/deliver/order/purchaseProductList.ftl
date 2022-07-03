<head>
    <meta charset="utf-8">
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0,user-scalable=no"/>
    <title>易订货-入库</title>
    <link rel="stylesheet" href="/deliver/css/global.css">
    <link rel="stylesheet" href="/deliver/css/warehousing/warehouseList.css">
    <link rel="stylesheet" href="/css/layer.css">
    <link rel="stylesheet" href="http://at.alicdn.com/t/font_1091326_sg61r64lenh.css">
    <link rel="stylesheet" href="http://at.alicdn.com/t/font_1100572_48zi1jd7rlt.css">
    <style>
        .input {
            color: #333;
            text-align: center;
            height: 30px;
            line-height: 30px;
            font-size: 14px;
            border: 1px solid #e0e0e0;
            border-radius: 5px;
        }
        .layui-m-layerbtn span[yes] {
            background:#0BC6D7;
        }

    </style>
</head>
<body>
<div class="shop_cart_page">
    <div class="shop_cart_list" id="shop_cart_list"></div>
    <div class="shop_cart_bottom">
        <div class="all_choose">
            <i class="iconfont iconyuan"></i> 全选
        </div>
        <div class="submit">发货</div>
    </div>
    <input class="purchaseFormId" hidden value="${purchaseFormId!}"/>
    <input class="supplyOrgId" hidden value="${supplyOrgId!}"/>
    <!-- 底部导航栏 -->
    <div class="bottom_bar" id="bottom_bar" data-current="1"></div>
    <!-- 弹窗 -->
    <div class="mask" style="display:none;">
        <div class="popup_box">
            <div class="t_head">
                <div>商品名称</div>
                <div>商品ID</div>
                <div>商品数量</div>
            </div>
            <div class="t_body" id="t_body"></div>
            <div class="t_bottom">
                <div onclick="cancelProcuct()">取消</div>
                <div onclick="productDeliver()">确定</div>
            </div>
        </div>
    </div>
</div>
<script src="/deliver/js/tmpl.min.js"></script>
<script src="http://www.jq22.com/jquery/jquery-1.10.2.js"></script>
<script src="/deliver/js/global.js"></script>
<script src="/deliver/js/purchaseProduct/purchaseProduct.js"></script>
<script src="/js/layer.js"></script>
<script type="text/x-tmpl" id="tmpl_list">
      {% for (var i=0; i<o.length; i++) { %}
        <div class="cart_list_item" proId="{%=o[i].id%}">
          <div><i class="iconfont iconyuan"></i></div>
          <div style="background-image:url({%=o[i].image%})"></div>
          <div>
            <div class="item_title"><span>{%=o[i].name%}</span><span>商品ID：{%=o[i].id%}</span></div>
            <div class="item_code_num">订货数量：<span>{%=o[i].purchaseQuantity%}</span></div>
            <div class="item_choose_num">
              <div>剩余数量：<span class="sum">{%=o[i].surplusQuantity%}</span></div>
              <div class="item_stepped">
                <span data-index="0"><i class="iconfont icontubiao-3"></i></span>
                <span class="cart_amount">{%=o[i].surplusQuantity%}</span>
                <span data-index="1"><i class="iconfont iconjia"></i></span>
              </div>
            </div>
          </div>
        </div>
      {% } %}

</script>
<script type="text/x-tmpl" id="tmpl_bottom">
      {% for (var i=0; i<o.length; i++) { %}
        <div data-index="{%=i%}">
          <i class="iconfont {%=o[i].icon%}"></i>
          <p>{%=o[i].name%}</p>
        </div>
      {% } %}

</script>
<script type="text/x-tmpl" id="tmpl_table">
      {% for (var i=0; i<o.length; i++) { %}
        <div>
          <div>{%=o[i].name%}</div>
          <div>{%=o[i].ID%}</div>
          <div>{%=o[i].num%}</div>
        </div>
      {% } %}

</script>
</body>
