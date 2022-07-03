<!DOCTYPE html>
<html>
  <head>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0,user-scalable=no" />
    <title>商品详情</title>
    <link rel="stylesheet" href="/css/global.css">
    <link rel="stylesheet" href="/css/commodity/commodityDetail.css">
    <link rel="stylesheet" href="/css/swiper.min.css">
    <link rel="stylesheet" href="http://at.alicdn.com/t/font_1091326_ne7rozzgza.css">
  </head>
  <body>
    <div class="comm_detail_page">
      <div class="cart">
        <a href="myCart?purchaserId=${purchaserid!}">
          <i class="iconfont icontubiao-13"></i>
          <span>${count?c!0}</span>
        </a>
      </div>
      <div class="swiper-container">
        <div class="swiper-wrapper">
          <#list pd.imgs as img>
            <div class="swiper-slide" style="background-image:url(${img!})"></div>
          </#list>
        </div>
        <!-- Add Pagination -->
        <div class="swiper-pagination"></div>
      </div>
      <div class="detail_main">
        <div class="detail_main_title">${pd.name!}</div>
        <div class="detail_main_price">
          <span>￥${pd.price!}<span>/${pd.unit!}</span></span>
          <span>销量${pd.salesVolume!}</span>
        </div>
      </div>
      <div class="introduce">
        <div class="introduce_title">
          <div><span class="border_act"></span>商品介绍</div>
        </div>
        <div class="introduce_detail">
          <#--<img src="/image/detail.png" />-->
          ${pd.remark!}
        </div>
        <div class="introduce_bottom">加入购物车</div>
      </div>
      <!-- 规格弹窗 -->
      <div class="mask" onclick="closePopup()">
        <div class="sku_popup">
          <div class="sku_popup_header">
            <div class="sku_image" style="background-image:url(${pd.image!})"></div>
            <div class="sku_main">
              <div onclick="closePopup()"><i class="iconfont icontubiao-35"></i></div>
              <div>${pd.name!}</div>
              <div>销量${pd.salesVolume!}</div>
            </div>
          </div>
          <div class="sku_choose_num">
            <div class="sku_choose_top">
              <div>
                <#--<span style="background-image:url(${pd.image!})"></span>-->
                <#--<span>200g<span>(PS221282870)</span></span>-->
              </div>
              <div class="stepper">
                <span data-index="0"><i class="iconfont icontubiao-3"></i></span>
                <span class="amount" id="count">1</span>
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
    <script src="http://www.jq22.com/jquery/jquery-1.10.2.js"></script>
    <script src="/js/commodity/commodityDetail.js"></script>
    <script src="/js/swiper.min.js"></script>
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
</html>