<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0,user-scalable=no"/>
    <title>易订货-下单</title>
    <link rel="stylesheet" href="/css/global.css">
    <link rel="stylesheet" href="/css/commodity/shopCart.css">
    <link rel="stylesheet" href="/css/layer.css">
    <link rel="stylesheet" href="/css/personalCenter/harvestAddress.css">
    <link rel="stylesheet" href="http://at.alicdn.com/t/font_1091326_sg61r64lenh.css">
    <style>
        .shop_cart_bottom {
            bottom: 0rem;
        }
        .ads_2d1E{
            font-size: 0.26rem;
            background: white;
            padding: 5px;
            border-bottom: 1px solid gray;
            margin-bottom: 9px;
        }
        .harvest_address_content .icontubiao-22{
           position: absolute;
           right: 10px;
           transform: translate(0, -50%);
           top: 50%;
        }
        #ic{
            display: none;
        }
    </style>
</head>
<body>
<div class="shop_cart_page">
    <div class="shop_cart_top">
<#--        <span onclick="deleteCart(0)"><i class="iconfont icontubiao-30"></i></span>-->
<#--        <span onclick="deleteCart(1)">完成</span>-->
    </div>
    <div class="shop_cart_list" id="shop_cart_list"></div>

<#--    <div class="ads_2d1E" onclick="window.location.href='toChangeAds';">-->
<#--        <div>${ads.name!},${ads.tel!}</div>-->
<#--        <div class="ads_3d12E">${ads.province!},${ads.city!},${ads.county!},${ads.addressDetail!}</div>-->
<#--    </div>-->


        <#if ads.id?exists>
            <div class="address_list" id="address_select">
                <div class="harvest_address_content f">
                    <span style="display: none;" datavalue="${ads.id!}" class="adsIds">${ads.id!}</span>
                    <div>
                        <span class="harvest_address_content_name names">${ads.name!}</span><span class="tels">${ads.tel!}</span>
                    </div>
                    <div>
                        <span class="harvest_address_content_name">地址</span>：<span class="address_content address_contents">${ads.province!}${ads.city!}${ads.county!}${ads.addressDetail!}</span>
                    </div>
                    <i class="iconfont icontubiao-22"></i>
                </div>
            </div>
            <#else >
            <div class="address_list" id="address_add">
                <div class="harvest_address_content f">
                    你好像没有保存的地址,去增加
                    <input class = "ids" style="display: none" value="${ids!}">
                    <i class="iconfont icontubiao-22"></i>
                </div>
            </div>
        </#if>


    <#list cartList as cart>
        <div class="cart_list_item">
            <div id="ic" ><i class="iconfont iconquan"></i></div>
            <div class="chebox" id="ic"><i class="iconfont iconqueren singlered"></i></div>
            <div style="background-image:url(${cart.IMAGE!})">
                <div>1种货品</div>
            </div>
            <div>
                <span style="display: none" class="cartid">${cart.product_id!}</span>
                <span style="display: none" class="cid">${cart.id!}</span>
                <div class="item_title">${cart.name!}</div>
                <div class="item_code_num">编号：${cart.BARCODE!}</div>
                <#if cart.product_color?? && cart.product_color?length gt 0>
                    <div  class="item_code_num">颜色：${cart.product_color!}</div>
                </#if>
                <div class="item_choose_num">
                    <div>￥<span class="cart_price">${cart.product_price!}</span>/${cart.UNIT!}</div>
                    <div class="item_stepped">

                        <#--<span class="cart_amount">${cart.product_quality!}</span>-->
                        <input type="text" class="cart_amount" value="${cart.product_quality!}">

                    </div>
                </div>
            </div>
        </div>
    </#list>
    <div class="shop_cart_bottom">
        <div class="all_choose" id="ic">
            <i class="iconfont iconquan" ></i> 全选
        </div>
        <div class="all_chooses" id="ic">
            <span><i class="iconfont iconquan"></i> 全选</span>
        </div>
        <div class="submit">下单付账</div>
        <div class="total_price">
            <div id="totalprice"><span>合计：</span>￥0.00</div>
            <div id="totalcategory">共种件商品</div>
        </div>
    </div>
</div>
<div class="address_popup">
  <div>选择地址</div>
  <ul>
    <#list adsList as lads>
        <li>
            <div class="harvest_address_content f">
                <span style="display: none;" datavalue="${lads.id!}" class="adsId">${lads.id!}</span>
                <div>
                    <span class="harvest_address_content_name name">${lads.name!}</span><span class="tel">${lads.tel!}</span>
                </div>
                <div>
                    <span class="harvest_address_content_name">地址</span>：<span class="address_content address_contents">${lads.province!}${lads.city!}${lads.county!}${lads.addressDetail!}</span>
                </div>
            </div>
          <i class="iconfont iconquan"></i>
        </li>
    </#list>
  </ul>
</div>
<#--<div class="bottom_bar" id="bottom_bar" data-current="2"></div>-->
<script src="/js/jquery.min.js"></script>
<script src="/js/mui.js"></script>
<script src="/js/tmpl.min.js"></script>
<#--<script src="/js/global.js"></script>-->
<#--<script type="text/x-tmpl" id="tmpl_bottom">-->
<#--      {% for (var i=0; i<o.length; i++) { %}-->
<#--        <div class="{%=o[i].class%}" data-index="{%=i%}">-->
<#--          <i class="iconfont {%=o[i].icon%}"></i>-->
<#--          <p>{%=o[i].name%}</p>-->
<#--        </div>-->
<#--      {% } %}-->

<#--</script>-->
<#--<script src="https://cdn.bootcss.com/blueimp-JavaScript-Templates/3.11.0/js/tmpl.min.js"></script>-->
<script src="/js/jquery.min.js"></script>
<script src="/js/commodity/shopCart.js"></script>
<script src="/js/layer.js"></script>
<script>
    $('#address_select').click(function () {
        var mask = mui.createMask(function () {
             $('.address_popup').removeClass('active')
        });
        mask.show()
           $('.address_popup').addClass('active')
    })
    $('#address_add').click(function () {
        var ids = $(".ids").val();
        if(ids!=''&&ids!=null) {
            window.location.href = "toCartAddressAdd?ids=" + ids;
        }
    })
    $('body').on('click', '.address_popup>ul li', function () {
        var dom = $(this).find('i')
        if (dom.hasClass('iconquan')) {
            dom.addClass('iconqueren').removeClass('iconquan')
            dom.parent().siblings('li').find('i').removeClass('iconqueren').addClass('iconquan')
            var mask = mui.createMask(function () {
                $('.address_popup').removeClass('active')
                $('.mui-backdrop').remove()
            });
             mask.close()
            var id = dom.parent().find('.adsId').text()
            var name = dom.parent().find('.name').text()
            var tel = dom.parent().find('.tel').text()
            var address = dom.parent().find('.address_content').text()
            $('.adsIds').text(id)
            $('.names').text(name)
            $('.tels').text(tel)
            $('.address_contents').text(address)

        } else {
            // dom.addClass('iconquan').removeClass('iconqueren')
        }

    })


    $('.coupon_list li').click(function () {
        $(this).toggleClass('active').siblings().removeClass('active')
    })
    $(function () {
        var count = "${count!}";
        $('#bottom_bar div:eq(2)').append('<div class="flag">' + count + '</div>')
    })
    var purchaserId = parseInt('${purchaserId!}')
    caculator();
    $(".submit").click(function () {
        //下单
        var adsId = $('.adsIds').text();
        var productIds = ''
        var sum=''

        $('.cart_list_item').each(function () {
            // if($(this).find('.chebox i').attr('class') == 'iconfont iconqueren singlered'){
                productIds += $(this).find('.cid')[0].innerText + ',';
                sum += $(this).find('.cart_amount').val() + ','

            // }

        })
        if (productIds != '') {
            productIds = productIds.substr(0, productIds.length - 1);
        }
        if (sum != '') {
            sum = sum.substr(0, sum.length - 1);
        }
        if(adsId==''||adsId==null){
            layer.open({
                type: 1,
                content: '请选择地址',
                skin: 'msg',
                time: 2
            })
        }else{
        console.log(productIds);
        console.log(sum);
            layer.open({
                content: '即将下单,确认地址后请点确定'
                , btn: ['确定', '取消']
                , yes: function (index) {
                    $.ajax({
                        url: "cartToForm",
                        method: 'post',
                        data: {"purchaseId": purchaserId, "ids": productIds,"adsId":adsId},
                        success: function (data) {
                            console.log(data);
                            if (data.status == 200) {
                                window.location.href = "purchaseDetail?status=" + data.status + "&purchaseFormId=" + data.data
                            } else {
                                layer.open({
                                    content: data.msg
                                    , btn: '我知道了'
                                });
                            }
                        }
                    })
                    //调用删除接口
                    layer.close(index);
                    return;
                }
            });
        }
    });

</script>
</body>
</html>
