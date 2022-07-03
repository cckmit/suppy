<head>
    <meta charset="utf-8">
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0,user-scalable=no"/>
    <title>易订货-首页</title>
    <link rel="stylesheet" href="/deliver/css/global.css">
    <link rel="stylesheet" href="/deliver/css/index.css">
    <link rel="stylesheet" href="http://at.alicdn.com/t/font_1100572_hmrfyv2jbmm.css">
    <link rel="stylesheet" href="http://at.alicdn.com/t/font_202335_gwbit3wv0zm.css">
    <link rel="stylesheet" href="http://at.alicdn.com/t/font_202335_fdrzzgm39vq.css">
</head>
<body>
<div class="index_page">
    <div class="index_top" style="background-image:url(image/index.png)"></div>
    <div class="index_type">
        <div>
            <a href="/deliver/productManager">
                <i class="iconfont icontubiao_huabanfuben1"></i>
                <p>我的商品</p>
            </a>
        </div>
        <div>
            <a href="/deliver/productStockList">
                <i class="iconfont icontubiao_huabanfuben"></i>
                <p>我的库存</p>
            </a>
        </div>
        <div>
            <a href="/deliver/purchaseFormInit">
                <i class="iconfont icontubiao_huabanfuben2"></i>
                <p>我的订货单</p>
            </a>
        </div>
        <div>
            <a href="/deliver/deliverFormInit">
                <i class="iconfont icontubiao_huabanfuben4"></i>
                <p>我的发货单</p>
            </a>
        </div>
        <#if type?? && type == 1>
            <div>
                <a href="/deliver/deliverFormCount">
                    <i class="iconfont icontubiao_huabanfuben3"></i>
                    <p>发货单统计</p>
                </a>
            </div>
        </#if>

        <div>
            <a href="/mbl/logout">
                <i class="iconfont icon-tuichu1"></i>
                <p>退出登录</p>
            <#--<span>22</span>-->
            </a>
        </div>
        <#--<div>-->
            <#--<a href="orderList.html">-->
                <#--<i class="iconfont icontubiao_huabanfuben3" style="font-size:0.84rem;"></i>-->
                <#--<p>待发货确认</p>-->
                <#--<span>8</span>-->
            <#--</a>-->
        <#--</div>-->
    </div>
    <!-- 底部导航栏 -->
    <div class="bottom_bar" id="bottom_bar" data-current="0"></div>
</div>
</body>
<script src="/deliver/js/tmpl.min.js"></script>
<script src="/js/jquery.min.js"></script>
<script src="/deliver/js/global.js"></script>
<script src="/deliver/js/index.js"></script>
<script type="text/x-tmpl" id="tmpl_bottom">
    {% for (var i=0; i<o.length; i++) { %}
      <div data-index="{%=i%}">
        <i class="iconfont {%=o[i].icon%}"></i>
        <p>{%=o[i].name%}</p>
      </div>
    {% } %}
</script>
