<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0,user-scalable=no"/>
    <title>易订货-首页</title>
    <link rel="stylesheet" href="/css/global.css">
    <link rel="stylesheet" href="/css/index-new.css">
    <link rel="stylesheet" href="/css/swiper.min.css">
    <link rel="stylesheet" href="/css/layer.css">
    <link rel="stylesheet" href="http://at.alicdn.com/t/font_1091326_sr48ma9pxf.css">
</head>
<body>
<div class="index_page">
    <!-- 顶部搜索框 -->
    <div class="search-wrap active">
        <div class="index_top_search">
            <div class="index_top_input">
                <input type="text" class="search" placeholder="搜索商品" readonly>
                <i class="iconfont iconfangdajing"></i>
            </div>
            <div class="shopcart_num" id="posBtnR">
                <a href="myCart?purchaserId=${purchaserid!}">
                    <i class="iconfont icontubiao-13"></i>
                    <span>${count!0}</span>
                </a>
            </div>
        </div>
    </div>
    <#--<div class="bg"></div>-->
    <!-- 轮播图 -->
    <div class="index_swiper">
        <div class="swiper-container">
            <div class="swiper-wrapper">
                <#if ads?exists && (ads?size > 0)>
                    <#list ads as ad>
                        <span datavalue="${ad.name!}"></span>
<#--                        <div class="swiper-slide" style="background-image:url(${adsUrl!}${ad.img})" ></div>-->
                        <div class="swiper-slide" style="background-image:url(${ad.img})" ></div>
                    </#list>
                <#else>
                    <div class="swiper-slide" style="background-image:url(/image/bb1.jpg)"></div>
<#--                    <div class="swiper-slide" style="background-image:url(/image/bb2.jpg)"></div>-->
                </#if>

            </div>
            <!-- Add Pagination -->
            <div class="swiper-pagination"></div>
        </div>
    </div>

    <#if textads?exists && (textads?size > 0)>
        <div class="notice" style="margin-top:0px!important;">
            <span class="title">公告</span>
            <div class="autoscroll">
                <ul>
                    <#list textads as advtsmt>
                        <li class="redirect" id="advtsmtHref"  <#--data-url="${advtsmt.href}"-->>${advtsmt.name}</li>
                    </#list>
                    <#--               <li class="redirect" data-url="bestSeller?type=2">热销排行，尽在其中！</li>-->
                </ul>
            </div>
            <#if textItfc?exists>
                <#if textItfc[0]?exists>
                    <div class="mr"><a class="redirect" data-url="${textItfc[0].href!}">${textItfc[0].name!}</a></div>
                </#if>
            </#if>
        </div>
    </#if>
    <!-- 分类 -->
    <#--<div class="index_type">-->
    <#--<div class="redirect" data-url="bestSeller?type=1">-->
    <#--<span style="background: #F6B75E"><i class="iconfont icontubiao-18"></i></span>-->
    <#--<p>新品上架</p>-->
    <#--</div>-->
    <#--<div class="redirect" data-url="bestSeller?type=2">-->
    <#--<span style="background: #FF807D"><i class="iconfont icontubiao-17"></i></span>-->
    <#--<p>热卖推荐</p>-->
    <#--</div>-->
    <#--<div class="redirect" data-url="bestSeller?type=3">-->
    <#--<span style="background: #A58FFB"><i class="iconfont icontubiao-19"></i></span>-->
    <#--<p>休闲零售</p>-->
    <#--</div>-->
    <#--<div class="redirect" data-url="/purchase/commodityClassification">-->
    <#--<span style="background: #65D0C7"><i class="iconfont icontubiao-20"></i></span>-->
    <#--<p>更多</p>-->
    <#--</div>-->
    <#--</div>-->

    <!-- 推荐 -->
    <#if textads3?exists && (textads3?size > 0)>
        <div class="index_recommend">
            <#list textads3 as advtsmt>
                <div style="background-image:url(${advtsmt.img})" class="redirect"
                     data-url="${advtsmt.href}"></div>
            </#list>
        </div>
    </#if>
    <div class="top-nav">
        <div class="find_nav">
            <div class="find_nav_left">
                <div class="find_nav_list">
                    <ul id="nav_list_ul">
                        <#if eventsList?exists && (eventsList?size > 0)>
                            <#list eventsList as evt>
                                <li  id="ev${evt.id!}" data-id="${evt.id!}" datavalue="ev#{evt.id!}">${evt.name!}</li>
                            </#list>
                        </#if>
                        <#--<li>特惠商品</li>-->
                        <#if categoryList?exists && (categoryList?size > 0)>
                            <#list categoryList as category>
                                <li  id="pr${category.id!}" data-id="${category.id!}" datavalue="#{category.ctgrClass!}">${category.categoryName!}</li>
                            </#list>
                        </#if>
                    </ul>
                </div>
            </div>
        </div>
        <#if textItfc?exists && (textItfc?size > 0)>
            <#if textItfc[1]?exists>
                <div class="more redirect" data-url="${textItfc[1].href!}">${textItfc[1].name!}</div>
            </#if>
        </#if>
    </div>

    <div class="content">
        <#if eventsList?exists && (eventsList?size > 0)>
            <#list eventsList as evt>
                <div class="index_list" id="type#{evt.id!}" style="display:none;">
                    <div class="index_list_main" id="dataEv#{evt.id!}"></div>
                </div>
            </#list>
        </#if>
        <#--<div class="index_list" id="type2" style="display:none;">
            <div class="index_list_main" id="data3"></div>
        </div>-->
        <#if categoryList?exists && (categoryList?size > 0)>
            <#list categoryList as category>

                <div class="index_list" id="type#{category.id!}" style="display:none;">

                    <div class="index_list_main" id="data#{category.id!}"></div>
                </div>
            </#list>
            <#else>
<#--                <div class="swiper-slide" style="background-image:url(/image/bb1.jpg)"></div>-->
<#--                <div class="swiper-slide" style="background-image:url(/image/bb1.jpg)"></div>-->
        </#if>
    </div>
</div>
<!-- 底部导航栏 -->
<div class="bottom_bar" id="bottom_bar" data-current="0"></div>
</div>
<script src="/js/tmpl.min.js"></script>
<script type="text/x-tmpl" id="tmpl_list">
      {% for (var i=0; i<o.length; i++) { %}
        <div class="index_list_item" proId="{%=o[i].id%}">
          <div class="index_list_item_image" style="background-image:url({%=o[i].image%})"></div>
          <div class="index_list_item_main">
            <div class="index_list_item_title">{%=o[i].name%}</div>
            <div class="index_list_item_sku">
              <span>库存{%=o[i].quantity%}{%=o[i].unit%}</span>
            </div>
            <div class="index_list_item_price">
              <div>￥{%=o[i].purchasePrice%}<span>/{%=o[i].unit%}</span></div>
              <div class="index_num" pId="{%=o[i].id%}">
                <div class="index_num_reduce" style="opacity: 0"><i class="iconfont icontubiao-3"></i></div>
                <div class="index_num_num" style="opacity: 0">0</div>
                <div class="index_num_add"><i class="iconfont iconjia"></i></div>
              </div>
            </div>
          </div>
        </div>
      {% } %}
</script>
<script type="text/x-tmpl" id="tmpl_bottom">
      {% for (var i=0; i<o.length; i++) { %}
        <div class="{%=o[i].class%}" data-index="{%=i%}">
          <i class="iconfont {%=o[i].icon%}"></i>
          <p>{%=o[i].name%}</p>
        </div>
      {% } %}
</script>
<script src="/js/jquery.min.js"></script>
<script src="/js/layer.js"></script>

<script src="/js/index-new.js"></script>
<script src="/js/global.js"></script>
<script src="/js/swiper.min.js"></script>
<script>
    $(function () {
        // $("#pr1").click();
        // $("#ev1").click();
        $("#nav_list_ul>li").eq(0).click();
        /*setTimeout(function(){
            getData("data123", 123);
        }, 3000);*/
        var mySwiper = new Swiper('.swiper-container', {
            loop: true,
            autoplay: true,
            pagination: {
                el: '.swiper-pagination'
            }
        })

        $(".redirect").click(function () {
           /* var url = $(this).data("url");
            if (url != "" && url != undefined) {
                window.location.href = url;
            }*/

            layer.open({
                content: "活动还未开始"
                , btn: '我知道了'
            });
        })

        var count = "${count!}";
        $('#bottom_bar div:eq(2)').append('<div class="flag">'+ count +'</div>')

        /* 跳转到详情 */
        $('body').on('click', '.index_list_item', function () {
            console.log(11111)
            var proId = $(this).attr("proId");
            window.location.href = '/purchase/commodityDetail?proId=' + proId;

            sessionStorage.setItem("scroll-y", $(window).scrollTop());
        })
    })

    $(".search").click(function () {
        window.location.href = "commodityList";
    });
</script>
</body>
</html>