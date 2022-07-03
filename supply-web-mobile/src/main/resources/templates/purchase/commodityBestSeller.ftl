<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0,user-scalable=no"/>
    <title>易订货-列表</title>
    <link rel="stylesheet" href="/css/global.css">
    <link rel="stylesheet" href="/css/list.css">
    <link rel="stylesheet" href="/css/swiper.min.css">
    <link rel="stylesheet" href="/css/mescroll.css">
    <link rel="stylesheet" href="http://at.alicdn.com/t/font_1091326_sr48ma9pxf.css">
    <style type="text/css">
        .mescroll {
            padding-bottom: 0;
        }
        .shopcart_num a {
            background: rgba(0, 0, 0, 0.6);
            display: block;
            border-radius: 30px;
            width: 0.6rem;
            z-index: 999
        }
    </style>
</head>
<body>
<div id="mescroll" class="mescroll">
    <div class="index_top_search">
        <div style="opacity: 0">
            <i class="iconfont icontubiao-47"></i>
        </div>
        <div class="index_top_input" style="opacity: 0">
            <input type="text" placeholder="搜索商品">
            <i class="iconfont iconfangdajing"></i>
        </div>
        <div class="shopcart_num">
            <a href="myCart?purchaserId=${purchaserid!}">
                <i class="iconfont icontubiao-13"></i>
                <span>${count!0}</span>
            </a>
        </div>
    </div>
    <div class="index_swiper">
        <div class="swiper-container">
            <div class="swiper-wrapper">
                <div class="swiper-slide" style="background-image:url(/image/bannar.png)"></div>
                <div class="swiper-slide" style="background-image:url(/image/bannar.png)"></div>
            </div>

            <div class="swiper-pagination"></div>
        </div>
    </div>
    <div class="list">
        <div class="list_main" id="dataList">
        </div>
    </div>
</div>
<script>
    var type = "#{type!}";
</script>
<script src="/js/mescroll.js"></script>
<script src="/js/jquery.min.js"></script>
<script src="/js/pdlist1.js"></script>
<script src="/js/swiper.min.js"></script>
<script src="/js/list.js"></script>
<script>
    $(function () {
        var mySwiper = new Swiper('.swiper-container', {
            loop: true,
            autoplay: true,
            pagination: {
                el: '.swiper-pagination'
            }
        })
    })
</script>
</body>
</html>