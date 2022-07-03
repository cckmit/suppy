<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0,user-scalable=no"/>
    <title>商品详情</title>
    <link rel="stylesheet" href="/css/global.css">
    <link rel="stylesheet" href="/css/commodity/commodityDetail.css">
    <link rel="stylesheet" href="/css/swiper.min.css">
    <link rel="stylesheet" href="/css/layer.css">
    <link rel="stylesheet" href="http://at.alicdn.com/t/font_1091326_ne7rozzgza.css">
</head>
<style>
    .sku_list .sku_item{
        display: inline-block;
        margin-right: 0.1rem;
        font-size: 0.24rem;
        text-align: center;
        padding: 0 0.1rem;
        min-width: 0.8rem;
        line-height: 0.4rem;
        border-radius: 0.1rem;
        border: 1px solid #cccccc;
    }
    .sku_item.active{
        background-color: #FF807D;
        border: none;
        color: #ffffff;
    }
</style>
<body>
<div class="comm_detail_page">
    <div class="cart">
        <a href="myCart?purchaserId=${purchaserid!}">
            <i class="iconfont icontubiao-13"></i>
            <span id="productCoupon">${count?c!0}</span>
        </a>
    </div>
    <div class="swiper-container">
        <div class="swiper-wrapper">
            <#if pd.imgs?exists && (pd.imgs?size > 0)>
                <#list pd.imgs as img >
                   <div class="swiper-slide" style="background-image:url(${img!})"></div>
               </#list>
            </#if>
        </div>
        <!-- Add Pagination -->
        <div class="swiper-pagination"></div>
    </div>
    <div class="detail_main">
        <div class="detail_main_title">${pd.name!}</div>
        <div class="detail_main_price">
            <span>
                ￥${pd.price!}<span>/${pd.unit!}</span>
                <#if pd.unitExplan?exists><span>&nbsp;&nbsp;${pd.unitExplan!}</span></#if>
            </span>
            <span>销量${pd.salesVolume!}</span>
        </div>
    </div>
    <div class="introduce">
        <div class="introduce_title">
            <div><span class="border_act"></span>商品介绍</div>
        </div>
        <div class="introduce_detail">
            <#--${pd.remark!}-->
            <#if pd.imgs2?exists && (pd.imgs2?size > 0)>
                <#list pd.imgs2 as img>
                    <#--<div class="swiper-wrapper swiper-slide" style="background-image:url(${img!});height: 7.5rem!important;"></div>-->
                    <img src="${img!}">
                </#list>
            <#else>
                无
            </#if>
        </div>
        <div class="introduce_bottom">加入购物车</div>

    </div>
    <!-- 规格弹窗 -->
    <div class="mask" >
        <div class="sku_popup">
            <div class="sku_popup_header">
                <div class="sku_image" style="background-image:url(${pd.image!})"></div>
                <div class="sku_main">
                    <div onclick="closePopup()"><i class="iconfont icontubiao-35"></i></div>
                    <div>${pd.name!}</div>
                    <div>销量${pd.salesVolume!}</div>
                    <#if pd.model?exists && pd.model?trim?length gt 0 >
                        <div class="sku_list" style="font-size: 10px;margin: 15px auto" >
                            <#if pd.model?contains(",")>
                                    <#list pd.model?split(",") as model>
                                        <div class="sku_item" data-id="${model!}">${model!}</div>
<#--                                        <input type="radio" name="model" style="margin-right: 3px"  value="${model!}" >${model!}-->
                                    </#list>
                            <#else >
                                <div class="sku_item active" data-id="${pd.model!}">${pd.model!}</div>
<#--                                <input type="radio" name="model"  value="${pd.model!}" checked >${pd.model!}-->
                            </#if >
                        </div>
                        <#else >
                        <input hidden type="radio" name="model"  value="" checked >
                    </#if>
                </div>
            </div>

            <div class="sku_choose_num" onclick="closePopup()">
                <div class="sku_choose_top">
                    <div >
                       <#-- <span class="layui-bg-red layui-text" >最低限购数量</span>-->
                    </div>
                    <div class="stepper">

                        <span data-index="0"><i class="iconfont icontubiao-3"></i></span>
                        <#--<span class="amount" id="count">1</span>-->

                        <input type="text" value="${pd.productLimit}" class="amount" id="count">
                        <span data-index="1"><i class="iconfont iconjia"></i></span>
                    </div>
                </div>
                <div class="sku_market_price">
                    <#--<div>市场价￥${pd.price!}/${pd.unit!}</div>-->
                    <div>库存${pd.quantity!}${pd.unit!}</div>
                </div>
                <div class="sku_price">￥${pd.price!}<span>/${pd.unit!}</span></div>


            </div>
            <button onclick="addToCart(${pd.id!})">加入购物车</button>

        </div>
    </div>
</div>
<script src="/js/jquery.min.js"></script>
<script src="/js/commodity/commodityDetail.js"></script>
<script src="/js/swiper.min.js"></script>
<script src="/js/layer.js"></script>

<script>
    var swiper = new Swiper('.swiper-container', {
        loop: true,
        autoplay: true,
        pagination: {
            el: '.swiper-pagination',
            type: 'fraction'
        }
    });
    var productLimit = '${pd.productLimit!}';
    addClickRate();
    function addClickRate() {
        $.ajax({
            method:"post",
            url: "/deliver/addProductClick",
            data: {
                "id":'${pd.id!}'
            },
            success:function () {
            }
        });
    }
    $('.sku_item').on('click', function () {
        $(this).addClass('active').siblings().removeClass('active')
    })
</script>
</body>
</html>