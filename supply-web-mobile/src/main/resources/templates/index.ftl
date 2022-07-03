<head>
    <meta charset="utf-8">
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0,user-scalable=no"/>
    <title>易订货-首页</title>
    <link rel="stylesheet" href="/css/global.css">
    <link rel="stylesheet" href="/css/index.css">
    <link rel="stylesheet" href="/css/swiper.min.css">
    <link rel="stylesheet" href="http://at.alicdn.com/t/font_1091326_sr48ma9pxf.css">
</head>
<body>
<div class="index_page">
    <!-- 顶部搜索框 -->
    <div class="index_top_search">
        <div>
            <i class="iconfont icontubiao-47"></i>
        </div>
        <div class="index_top_input">
            <#--<a href="/purchase/commodityList">搜索商品</a>-->
            <input type="text" placeholder="搜索商品" readonly="readonly" class="search">
            <i class="iconfont iconfangdajing"></i>
        </div>
        <div class="shopcart_num">
            <a href="myCart?purchaserId=${purchaserid!}">
                <i class="iconfont icontubiao-13"></i>
                <span>${count!0}</span>
            </a>
        </div>
    </div>
    <!-- 轮播图 -->
    <div class="index_swiper">
        <div class="swiper-container">
            <div class="swiper-wrapper">
                <div class="swiper-slide" style="background-image:url(/image/bannar.png)"></div>
                <div class="swiper-slide" style="background-image:url(/image/bannar.png)"></div>
            </div>
            <!-- Add Pagination -->
            <div class="swiper-pagination"></div>
        </div>
    </div>
    <!-- 分类 -->
    <div class="index_type">
        <div class="redirect" data-url="bestSeller?type=1">
            <span style="background: #F6B75E"><i class="iconfont icontubiao-18"></i></span>
            <p>新品上架</p>
        </div>
        <div class="redirect" data-url="bestSeller?type=2">
            <span style="background: #FF807D"><i class="iconfont icontubiao-17"></i></span>
            <p>商品专区</p>
        </div>
        <div class="redirect" data-url="bestSeller?type=3">
            <span style="background: #A58FFB"><i class="iconfont icontubiao-19"></i></span>
            <p>特惠快购</p>
        </div>
        <div class="redirect" data-url="commodityList">
            <span style="background: #65D0C7"><i class="iconfont icontubiao-20"></i></span>
            <p>更多</p>
        </div>
    </div>
    <!-- 推荐 -->
    <div class="index_recommend">
        <div style="background-image:url(/image/recom.png)"></div>
        <div style="background-image:url(/image/recom1.png)"></div>
    </div>
    <!-- 爆品直降 -->
    <#if (news?size>0)>
        <div class="index_list">
            <div class="index_list_title">
                <span><i class="iconfont icontubiao-4"></i>新品上架</span>
            </div>
            <div class="index_list_main" id="act">
                <#list news as pro>
                    <div class="index_list_item">
                        <div class="index_list_item_image"
                             onclick="window.location.href='commodityDetail?proId=${pro.id}'"
                             style="background-image:url(${pro.image!})"></div>
                        <div class="index_list_item_main">
                            <div class="index_list_item_title">${pro.name!}</div>
                            <div class="index_list_item_sku">
                                <#--<span>${pro.unit!}</span>-->
                                <span>库存${pro.quantity!}${pro.unit!}</span>
                            </div>
                            <div class="index_list_item_price">
                                <div>￥${pro.purchasePrice!}<span>/${pro.unit!}</span></div>
                                <div class="index_num" pId="${pro.id!}">
                                    <div class="index_num_reduce"><i class="iconfont icontubiao-3"></i></div>
                                    <div class="index_num_num">0</div>
                                    <div class="index_num_add"><i class="iconfont iconjia"></i></div>
                                </div>
                            </div>
                        </div>
                    </div>
                </#list>
            </div>
        </div>
    </#if>
    <!-- 满减 -->
    <#if (hots?size>0)>
        <div class="index_list">
            <div class="index_list_title">
                <span><i class="iconfont icontubiao-4"></i>商品专区</span>
            </div>
            <div class="index_list_main" id="full">
                <#list hots as pro>
                    <div class="index_list_item">
                        <div class="index_list_item_image"
                             onclick="window.location.href='commodityDetail?proId=${pro.id}'"
                             style="background-image:url(${pro.image!})"></div>
                        <div class="index_list_item_main">
                            <div class="index_list_item_title">${pro.name!}</div>
                            <div class="index_list_item_sku">
                                <#--<span>${pro.unit!}</span>-->
                                <span>库存${pro.quantity!}${pro.unit!}</span>
                            </div>
                            <div class="index_list_item_price">
                                <div>￥${pro.price!}<span>/${pro.unit!}</span></div>
                                <div class="index_num" pId="${pro.id!}">
                                    <div class="index_num_reduce"><i class="iconfont icontubiao-3"></i></div>
                                    <div class="index_num_num">0</div>
                                    <div class="index_num_add"><i class="iconfont iconjia"></i></div>
                                </div>
                            </div>
                        </div>
                    </div>
                </#list>
            </div>
        </div>
    </#if>
    <div style="height: 60px;"></div>
    <!-- 底部导航栏 -->
    <div class="bottom_bar" id="bottom_bar" data-current="0"></div>
</div>
<script type="text/x-tmpl" id="tmpl_bottom">
      {% for (var i=0; i<o.length; i++) { %}
        <div class="{%=o[i].class%}" data-index="{%=i%}">
          <i class="iconfont {%=o[i].icon%}"></i>
          <p>{%=o[i].name%}</p>
        </div>
      {% } %}
</script>
<script src="/js/jquery.min.js"></script>
<script src="/js/tmpl.min.js"></script>
<script src="/js/index.js"></script>
<script src="/js/global.js"></script>
<script src="/js/swiper.min.js"></script>
<script>
    $(function () {
        var mySwiper = new Swiper('.swiper-container', {
            loop: true,
            autoplay: true,
            pagination: {
                el: '.swiper-pagination'
            }
        })
        $('.search').on('click', function () {
            window.location.href = "/purchase/commodityList"
        })

        $(".redirect").click(function () {
            var url = $(this).data("url");
            if (url != "") {
                window.location.href = url;
            }
        })
    })
</script>
</body>
