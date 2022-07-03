<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0,user-scalable=no"/>
    <title>易订货-商品</title>
    <link rel="stylesheet" href="/css/global.css">
    <link rel="stylesheet" href="/css/commodity/commodityList.css">
    <link rel="stylesheet" href="/css/layer.css">
    <link rel="stylesheet" href="http://at.alicdn.com/t/font_1091326_rksxatgoljf.css">
    <style>
        .none {
            width: 5rem!important;
            left: 4px!important;
        }
    </style>
</head>
<body>
<div style="display: none;" id="purchaseId">${purchaserId!}</div>
<div class="commodity_page">
    <div class="commodity_top">
        <div class="commodity_top_search">
            <div>
                <a href="/purchase/commodityClassification">
                    <i class="iconfont icontubiao-16"></i>
                </a>
            </div>

            <div class="commodity_search <#if !categoryName?exists>none</#if>" >
                <span><input type="text" placeholder="搜索商品" class="search-input"></span>
                <i id="sousuo" class="iconfont icontubiao-47"></i>
            </div>
            <div class="shopcart_num">
                <a href="myCart?purchaserId=${purchaserId!}">
                    <i class="iconfont icontubiao-13"></i>
                    <span class="cart_num">${count!0}</span>
                </a>
            </div>
            <#if categoryName?exists>
                <div class="category">
                    <div class="text">${categoryName!}</div>
                    <i>X</i>
                </div>
            </#if>
        </div>
        <div class="commodity_top_sort">
            <div class="synthesis_sort">综合排序</div>
            <div class="price_sort">价格<i class="iconfont iconxiangshangjiantou-copy-copy-copy-copy"></i><i
                        class="iconfont iconxiangshangjiantou-copy-copy-copy"></i></div>
            <div class="sales_sort">销量<i class="iconfont iconxiangshangjiantou-copy-copy-copy-copy"></i><i
                        class="iconfont iconxiangshangjiantou-copy-copy-copy"></i></div>
        </div>
    </div>
    <div class="commodity_list" id="commodity_list"></div>

    <!-- 底部导航栏 -->
    <div class="bottom_bar" id="bottom_bar" data-current="1"></div>
</div>
<script type="text/x-tmpl" id="tmpl_bottom">
      {% for (var i=0; i<o.length; i++) { %}
        <div class="{%=o[i].class%}" data-index="{%=i%}">
          <i class="iconfont {%=o[i].icon%}"></i>
          <p>{%=o[i].name%}</p>
        </div>
      {% } %}
</script>
<script type="text/x-tmpl" id="tmpl_list">
      {% for (var i=0; i<o.length; i++) { %}
        <div class="commodity_list_item" proId="{%=o[i].id%}">
          <div class="commodity_image" style="background-image:url({%=o[i].image%})"></div>
          <div class="commodity_main">
            <div class="main_title">{%=o[i].name%}</div>
            <div class="main_sku">
              <div><span>销量</span><span>{%=o[i].salesVolume%}{%=o[i].unit%}</span></div>
              <div><span>库存<span class="store_num">{%=o[i].quantity%}</span>{%=o[i].unit%}</span></div>
            </div>
            <div>
              <div class="main_price">
                <span>￥{%=o[i].purchasePrice%}<span>/{%=o[i].unit%}</span></span>
                <span>库存<span class="store_num">{%=o[i].quantity%}</span>{%=o[i].unit%}</span>
              </div>
              <div class="main_num" proId="{%=o[i].id%}">
                <div><i class="iconfont icontubiao-3" data-index="0"></i></div>
                <div class="add_num">0</div>
                <div><i class="iconfont iconjia" data-index="1"></i></div>
<#--                <div><i class="iconfont iconjia iconOpen" data-index="2"></i></div>-->
              </div>
            </div>
          </div>
        </div>
      {% } %}
</script>
<script src="/js/jquery.min.js"></script>
<script src="/js/tmpl.min.js"></script>
<script src="/js/global.js"></script>
<script  >
    var prefecture ="${prefecture!}"
</script>
<script>
    $(function () {
        var count = "${count!}";
        $('#bottom_bar div:eq(2)').append('<div class="flag">'+ count +'</div>')
    })
    var categoryId = "${categoryId!}";
    $('.search-input').focus()
</script>
<script src="/js/commodity/commodityList.js"></script>
<script src="/js/layer.js"></script>
</body>
</html>
