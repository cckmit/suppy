<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0,user-scalable=no"/>
    <title>易订货-商品分类</title>
    <link rel="stylesheet" href="../css/global.css">
    <link rel="stylesheet" href="../css/commodity/commodityClassification.css">
    <link rel="stylesheet" href="http://at.alicdn.com/t/font_1091326_sr48ma9pxf.css">
</head>

<body>
<div class="commodity_classification">
    <div class="commodity_classification_top">
        <i class="iconfont icontubiao-13"></i>
    </div>
    <div class="commodity_classification_list f">
        <ul class="commodity_classification_list_left">
            <#--<li class="active">坚果邦</li>-->
            <#--<li>枣夹系列</li>-->
            <#--<li>枣枣国</li>-->
            <#--<li>果干部落</li>-->
            <#--<li>馋嘴零食</li>-->
            <#--<li>蜜语城</li>-->
            <#list categoryList as category>
                <li ppId="${category.id}">${category.categoryName}</li>
            </#list>
        </ul>
        <div class="commodity_classification_list_right selected">
            <div class="commodity_classification_right_name f">
                <div class="brand" id="categoryChildName">
                    坚果邦
                </div>
                <div class="whole">
                    全部
                    <i class="iconfont icontubiao-22"></i>
                </div>
            </div>
            <div class="commodity_classification_right_content f">
                <#--<dl>-->
                <#--<dt><img src="../image/img.png" alt=""></dt>-->
                <#--<dd>美味坚果</dd>-->
                <#--</dl>-->
                <#--<dl>-->
                <#--<dt><img src="../image/img.png" alt=""></dt>-->
                <#--<dd>无外果仁</dd>-->
                <#--</dl>-->
                <#--<dl>-->
                <#--<dt><img src="../image/img.png" alt=""></dt>-->
                <#--<dd>美味坚果</dd>-->
                <#--</dl>-->
                <#list categoryList as category>
                    <ul class="sublist" ppId="${category.id!}">
                        <#list category.childCategory as categoryChild>
                            <li><a href="/purchase/commodityList?categoryId=${categoryChild.id}&categoryName=${categoryChild.categoryName!}">${categoryChild.categoryName!}</a></li>
                        </#list >
                    </ul>
                </#list>

            </div>
        </div>
        <#--<div class="commodity_classification_list_right">-->
        <#--<div class="commodity_classification_right_name f">-->
        <#--<div class="brand">-->
        <#--坚果邦-->
        <#--</div>-->
        <#--<div class="whole">-->
        <#--全部-->
        <#--<i class="iconfont icontubiao-22"></i>-->
        <#--</div>-->
        <#--</div>-->
        <#--<!-- 有加减的 &ndash;&gt;-->
        <#--<ul class="commodity_classification_right_content2">-->
        <#--<li class="f">-->
        <#--<img src="../image/img.png" alt="">-->
        <#--<dl>-->
        <#--<dt>休闲孕妇零食坚果炒货干果货干果货干果</dt>-->
        <#--<dd class="sales">-->
        <#--销量669 <span>200g</span>-->
        <#--</dd>-->
        <#--<dd class="f">-->
        <#--<div class="repertory">-->
        <#--<div>￥36/袋</div>-->
        <#--<div>库存90袋</div>-->
        <#--</div>-->
        <#--<div class="commodity_classification_right_content2_num f">-->
        <#--<i class="iconfont icontubiao-3 jian" data-index="0"></i>-->
        <#--<input class="quantity" type="text" type="text" value="0" readonly unselectable="on">-->
        <#--<i class="iconfont iconjia" data-index="1"></i>-->
        <#--</div>-->
        <#--</dd>-->
        <#--</dl>-->
        <#--</li>-->
        <#--</ul>-->
        <#--<ul class="commodity_classification_right_content2">-->
        <#--<li class="f">-->
        <#--<img src="../image/img.png" alt="">-->
        <#--<dl>-->
        <#--<dt>休闲孕妇零食坚果炒货干果货干果货干果</dt>-->
        <#--<dd class="sales">-->
        <#--销量669 <span>200g</span>-->
        <#--</dd>-->
        <#--<dd class="f">-->
        <#--<div class="repertory">-->
        <#--<div>￥36/袋</div>-->
        <#--<div>库存90袋</div>-->
        <#--</div>-->
        <#--<div class="commodity_classification_right_content2_num f">-->
        <#--<i class="iconfont icontubiao-3 jian" data-index="0"></i>-->
        <#--<input class="quantity" type="text" type="text" value="0" readonly unselectable="on">-->
        <#--<i class="iconfont iconjia" data-index="1"></i>-->
        <#--</div>-->
        <#--</dd>-->
        <#--</dl>-->
        <#--</li>-->
        <#--</ul>-->
        <#--</div>-->
    </div>
</div>
<style>
    .sublist li {
        font-size: 0.26rem;
        line-height: 46px;
        border-bottom: 1px solid #e8e8e8;
    }

    .sublist li a {
        color: #666;
    }
</style>
</body>
<script src="http://www.jq22.com/jquery/jquery-1.10.2.js"></script>
<script src="../js/commodity/commodityClassification.js"></script>

</html>