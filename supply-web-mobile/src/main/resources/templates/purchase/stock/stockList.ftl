<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0,user-scalable=no"/>
    <title>易订货-入库</title>
    <link rel="stylesheet" href="/css/global.css">
    <link rel="stylesheet" href="/deliver/css/warehousing/warehouseList.css">
    <link rel="stylesheet" href="/css/layer.css">
    <link rel="stylesheet" href="http://at.alicdn.com/t/font_1091326_sg61r64lenh.css">
    <link rel="stylesheet" href="http://at.alicdn.com/t/font_1100572_48zi1jd7rlt.css">
    <link rel="stylesheet" href="http://at.alicdn.com/t/font_202335_gwbit3wv0zm.css">
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
        .item_stepped span:first-child i,
        .item_stepped span:nth-child(3) i,
        .item_code_num span,
        .bottom_bar_active i, .bottom_bar_active p,
        .iconqueren{
            color: #FF807D !important;
        }
        .submit {
             background: #FF807D;
        }
        .shop_cart_bottom {
            bottom: 0;
        }
     /*   .item_title1{
            width: 4.8rem;
            line-height: 0.28rem;
            font-size: 0.26rem;
            overflow:hidden;
            text-overflow:ellipsis;
            white-space:nowrap;
            color: #333;
            margin-top: 0.1rem;
            -webkit-line-clamp: 2;
            -webkit-box-orient: vertical;
        }*/


    </style>
</head>
<body>
<div class="shop_cart_page">
    <div class="shop_cart_list" id="shop_cart_list"></div>
    <#--<div class="shop_cart_bottom">-->
        <#--<div class="all_choose">-->
            <#--<i class="iconfont iconyuan"></i> 全选-->
        <#--</div>-->
        <#--<div class="submit">入库</div>-->
    <#--</div>-->
    <!-- 底部导航栏 -->
    <#--<div class="bottom_bar" id="bottom_bar" data-current="3"></div>-->
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
                <div onclick="cancelAddRepository()">取消</div>
                <div onclick="addRepository()">确定</div>
            </div>
        </div>
    </div>
</div>
<script src="/deliver/js/tmpl.min.js"></script>
<script src="/js/jquery.min.js"></script>
<#--<script src="js/global.js"></script>-->
<script src="/purchase/js/stock/stockList.js"></script>
<script src="/js/layer.js"></script>
<script type="text/x-tmpl" id="tmpl_list">
      {% for (var i=0; i<o.length; i++) { %}
        <div class="cart_list_item" proId="{%=o[i].id%}">
          <div><i class="iconfont iconyuan"></i></div>
          <div style="background-image:url({%=o[i].image%})"></div>
          <div>
            <div class="item_title"><span style="width:100%" >{%=o[i].name%}</span></div>
            <div class="item_code_num">商品库存：<span>{%=o[i].quantity%}</span></div>
            <div class="item_choose_num">
              <div>历史总库存：{%=o[i].quantityTotal%} <span style="margin-left:25px" >商品ID：{%=o[i].id%}</span></div>
              <#--<div class="item_stepped">-->
                <#--<span data-index="0"><i class="iconfont icontubiao-3"></i></span>-->
                <#--<span class="cart_amount">1</span>-->
                <#--<span data-index="1"><i class="iconfont iconjia"></i></span>-->
              <#--</div>-->
            </div>
          </div>
        </div>
      {% } %}
</script>
<#--<script type="text/x-tmpl" id="tmpl_bottom">-->
      <#--{% for (var i=0; i<o.length; i++) { %}-->
        <#--<div class="{%=o[i].class%}" data-index="{%=i%}">-->
          <#--<i class="iconfont {%=o[i].icon%}"></i>-->
          <#--<p>{%=o[i].name%}</p>-->
        <#--</div>-->
      <#--{% } %}-->
<#--</script>-->
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
</html>