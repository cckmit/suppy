<head>
    <meta charset="utf-8">
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0,user-scalable=no"/>
    <title>易订货-商品详情</title>
    <link rel="stylesheet" href="/deliver/css/global.css">
    <link rel="stylesheet" href="/deliver/css/commodity/commodityDetail.css">
    <link rel="stylesheet" href="/deliver/css/swiper.min.css">
    <link rel="stylesheet" href="http://at.alicdn.com/t/font_1091326_ne7rozzgza.css">
</head>
<body>
<div class="comm_detail_page">
    <div class="swiper-container">
        <div class="swiper-wrapper">
            <#--<div class="swiper-slide" style="background-image:url(${product.image!})"></div>-->
            <#if imgs?exists && (imgs?size > 0)>
                <#list imgs as i>
                    <div class="swiper-slide" style="background-image:url(${i.imgPath!})"></div>
                </#list>
            </#if>
        </div>
        <!-- Add Pagination -->
        <div class="swiper-pagination">
            <span class="swiper-pagination-current">1</span>
            /
            <span class="swiper-pagination-total">${imgs?size}</span>
        </div>
    </div>
    <div class="detail_main">
        <div class="detail_main_title">${product.name!}</div>
        <div class="detail_main_price">
            <span>￥${product.price!}<span>/${product.unit!}</span></span>
            <span>商品编码：${product.barcode!}</span>
        </div>
    </div>
    <div class="introduce">
        <div class="introduce_title">
            <div><span class="border_act"></span>商品介绍</div>
        </div>
        <div class="introduce_detail">
            <#--${product.remark!}-->
            <#if imgs2?exists && (imgs2?size > 0)>
                <#list imgs2 as i>
                <#--<div class="swiper-wrapper swiper-slide" style="background-image:url(${i.imgPath!});height: 7.5rem!important;"></div>-->
                    <img src="${i.imgPath!}" alt="">
                </#list>
            <#else>
                无
            </#if>
        </div>
        <div class="introduce_bottom">
            <div><a href="/deliver/commodityEdit?id=${product.id!}">编辑</a></div>
            <div>
                <#if product.productStatus==1>
                    下架
                <#elseif product.productStatus==2>
                    上架
                <#else>
                    无
                </#if>
            </div>
        </div>
    </div>
    <!-- 弹窗 -->
    <div class="lower_mask">
        <div class="lower_popup">
            <div>
                <#if product.productStatus==1>
                    是否下架本商品？
                <#else>
                    是否上架本商品？
                </#if>
            </div>
            <div class="lower_btn">
                <div>取消</div>
                <div onclick="updateProductStatus(${product.id},${product.productStatus!})">确定</div>
            </div>
        </div>
    </div>
</div>
<style>
    .introduce_detail {
        font-size: 17px !important;
    }
</style>
<script src="http://www.jq22.com/jquery/jquery-1.10.2.js"></script>
<script src="/deliver/js/commodity/commodityDetail.js"></script>
<script src="/deliver/js/swiper.min.js"></script>
<script>
    var swiper = new Swiper('.swiper-container', {
        loop: true,
        autoplay: true,
        pagination: {
            el: '.swiper-pagination',
            type: 'fraction'
        }
    });
</script>
</body>
