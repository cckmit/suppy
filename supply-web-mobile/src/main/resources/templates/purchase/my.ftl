<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0,user-scalable=no"/>
    <title>易订货-我的</title>
    <link rel="stylesheet" href="/css/global.css">
    <link rel="stylesheet" href="/css/my.css">
    <link rel="stylesheet" href="http://at.alicdn.com/t/font_1091326_sr48ma9pxf.css">
    <style>
        .dgq_tag_box{
            position: relative;
        }
        .dgq_tag{
            font-size: 0.24rem;
            min-width: 0.34rem;
            height: 0.34rem;
            line-height: 0.34rem;
            border-radius: 50%;
            background-color: red;
            color: #ffffff;
            position: absolute;
            right: 1.4rem;
            top: 0.05rem;
        }
        .my_info >div span{
            width: 1.6rem;
            white-space: nowrap;
        }
        .my_info >div input{
            width: 5.3rem;
            top: 0rem;
        }
    </style>
</head>
<body>
<div class="my_page">
    <div class="my_top" style="background-image:url(/image/my-back.png)">
        <div class="my_avatar">
            <i class="iconfont icontubiao-1"></i>
        </div>
        <div>${org.orgName!}</div>
    </div>
    <div class="my_sec">
        <div>${user.name!}</div>
        <div>
            <a href="javascript:void(0);">
                <div>预付款账户</div>
                <div>${money?c!'0'}</div>
            </a>
        </div>
    </div>
    <div class="my_third">
    <#--<div>-->
    <#--<i class="iconfont icontubiao-54"></i>-->
    <#--<p>收货地址</p>-->
    <#--</div>-->
    <#if permit>
        <div>
            <a href="ordToExamine">
                <i class="iconfont icontubiao-41"></i>
                <p>订单审核</p>
            </a>
        </div>
    </#if>
        <div class="dgq_tag_box">
            <a href="/purchase/deliverFormInit">
                <i class="iconfont icontubiao-43"></i>
                <p>收货单</p>
            </a>
            <div class="dgq_tag" >${total!}</div>
        </div>
        <div>
            <a href="/purchase/purchaseFormInit">
                <i class="iconfont order-icon"></i>
                <p>订单</p>
            </a>
        </div>
    </div>

    <div class="my_third">
    <#--<div>-->
    <#--<i class="iconfont icontubiao-54"></i>-->
    <#--<p>收货地址</p>-->
    <#--</div>-->

        <div>
            <a href="/purchase/stockListInit">
                <i class="iconfont">
                    <img src="../image/kc.png" alt="" width="40">
                </i>
                <p>我的库存</p>
            </a>
        </div>

       <#-- <div>
            <a href="/purchase/exchangeListInit">
                <i class="iconfont">
                    <img src="../image/ck.png" alt="" width="40">
                </i>
                <p>兑换出库</p>
            </a>
        </div>-->
        <div>
            <a href="/purchase/prefectureCommodityList">
                <i class="iconfont">
                    <img src="../image/ck.png" alt="" width="40">
                </i>
                <p>商品专区</p>
            </a>
        </div>
    </div>
    <div class="my_third">
        <div>
            <a href="/purchase/exchangeCheck">
                <i class="iconfont">
                    <img src="../image/experience.png" alt="" width="40">
                </i>
                <p>自助兑换核销</p>
            </a>
        </div>
        <div>
            <a href="/purchase/willPurchaseList">
                <i class="iconfont">
                    <img src="../image/experience1.png" alt="" width="40">
                </i>
                <p>预购单</p>
            </a>
        </div>
    </div>
    <div class="my_info">
        <div>
            <span>姓名</span>
            <input type="text" placeholder="${user.name!}" readonly>
            <i class="iconfont icontubiao-22"></i>
        </div>
        <div>
            <span>机构</span>
            <input type="text" placeholder="${org.orgName!}" readonly>
            <i class="iconfont icontubiao-22"></i>
        </div>
        <div onclick="window.location.href='toAddress'">
            <span>我的地址</span>
            <input type="text"  readonly>
            <i class="iconfont icontubiao-22"></i>
        </div>
        <div onclick="window.location.href='changePwd'">
            <span>登录密码</span>
            <input type="text" placeholder="修改密码" readonly>
            <i class="iconfont icontubiao-22"></i>
        </div>
    <#--<div class="my_tel">-->
    <#--<span>手机</span>-->
    <#--<input type="text" placeholder="未绑定" readonly>-->
    <#--<i class="iconfont icontubiao-22"></i>-->
    <#--</div>-->
    </div>
    <div class="sign_out" onclick="window.location.href='/mbl/logout'">退出</div>
    <!-- 底部导航栏 -->
    <div class="bottom_bar" id="bottom_bar" data-current="3"></div>
    <div style="display: none;" id="purchaseId">${purchaseId!}</div>
</div>
<script src="/js/tmpl.min.js"></script>
<script type="text/x-tmpl" id="tmpl_bottom">
      {% for (var i=0; i<o.length; i++) { %}
        <div class="{%=o[i].class%}" data-index="{%=i%}">
          <i class="iconfont {%=o[i].icon%}"></i>
          <p>{%=o[i].name%}</p>
        </div>
      {% } %}

</script>
<script src="/js/jquery.min.js"></script>
<script src="/js/global.js"></script>
<script>
    $(function () {
        var count = "${count!}";
        $('#bottom_bar div:eq(2)').append('<div class="flag">'+ count +'</div>')
    })
</script>
</body>
</html>