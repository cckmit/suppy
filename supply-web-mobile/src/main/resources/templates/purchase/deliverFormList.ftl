<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0,user-scalable=no"/>
    <title>易订货-发货单</title>
    <link rel="stylesheet" href="/css/global.css">
    <link rel="stylesheet" href="/css/order/orderGoods.css">
    <link rel="stylesheet" href="/css/mui.css">
    <link rel="stylesheet" href="fonts/mui.ttf"/>
    <link rel="stylesheet" href="http://at.alicdn.com/t/font_1091326_sr48ma9pxf.css">
    <style>
        /*.order_item_top {*/
        /*    width: 100%;*/
        /*    height: 3.01rem;*/
        /*    padding-top: 0.36rem;*/
        /*}*/
        .order_item_detail_ads{

        }
    </style>
</head>
<body>
<div style="display: none;" id="purchaseId">${purchaserId!}</div>
<div class="orderGoodsPage">
    <div class="order_goods_top">
        <div class="order_search">
            <a href="/purchase/orderQueryInit">
                <i class="iconfont icontubiao-47"></i>
            </a>
        </div>
        <div class="order_screen">
            <#--<a href="">-->
                <#--<i class="iconfont icontubiao-13"></i>-->
            <#--</a>-->
            <#--<a href="order/orderTotalQuery.html">-->
                <#--<i class="iconfont icontubiao-21"></i>-->
            <#--</a>-->
        </div>
    </div>
    <div class="content mui-scroll-wrapper" id="pullrefresh">
        <div class="mui-scroll">
            <div class="order_goods_list">
            </div>

        </div>

    </div>
    <!-- 底部导航栏 -->

    <div class="bottom_bar" id="bottom_bar" data-current="3"></div>
</div>
<script src="/js/tmpl.min.js"></script>
<script src="/js/jquery.min.js"></script>
<script src="/js/order/orderGoods.js"></script>
<script src="/js/global.js"></script>
<script src="/js/handlebars-1.0.0.beta.6.js"></script>
<script src="/js/mui.js"></script>
<script type="text/x-tmpl" id="tmpl_bottom">
      {% for (var i=0; i<o.length; i++) { %}
        <div class="{%=o[i].class%}" data-index="{%=i%}">
          <i class="iconfont {%=o[i].icon%}"></i>
          <p>{%=o[i].name%}</p>
        </div>
      {% } %}

</script>
<script>
    Handlebars.registerHelper("time", function (v1) {
        //var v1 = v1.substring(0, 19)
        var date = new Date(v1.replace(/-/g, "/").replace(/T/g," ").split(".")[0])
        y = date.getFullYear()
        m = date.getMonth() + 1
        d = date.getDate(),
                h = date.getHours(),
                i = date.getMinutes(),
                s = date.getSeconds();
        if (m < 10) {
            m = '0' + m;
        }
        if (d < 10) {
            d = '0' + d;
        }
        if (h < 10) {
            h = '0' + h;
        }
        if (i < 10) {
            i = '0' + i;
        }
        if (s < 10) {
            s = '0' + s;
        }
        v1 = y + "-" + m + "-" + d + ' ' + h + ':' + i + ':' + s;

        return v1;
    });

    Handlebars.registerHelper("Status", function (status, text, id) {
        switch (status) {
            case 0:
                return new Handlebars.SafeString('未确认');
                break;
            case 1:
                return new Handlebars.SafeString('已确认');
                break;
            case 2:
                return new Handlebars.SafeString('已拒绝');
                break;
        }
    });
    Handlebars.registerHelper("button", function (status) {
        switch (status) {
            case 0:
                return new Handlebars.SafeString('<button>未确认</button>');
                break;
            case 1:
                return new Handlebars.SafeString('<button>已确认</button>');
                break;
            case 2:
                return new Handlebars.SafeString('<button>已拒绝</button>');
                break;
        }
    });
</script>
<script type="text/template" id="order-list">
    {{#each this}}
    <div class="order_goods_list_item">
        <a href="deliverFormDetail?deliverFormId={{id}}">
            <div class="order_item_top">
                <div class="order_item_top_title">
                    <span>{{deliverBillno}}</span>
                    <span>
                            <#--待收货确认-->
                                {{Status status}}
                        </span>
                </div>
                <div class="order_item_detail">
                    <span>数量：</span>{{totalQuantity}}
                </div>
                <div class="order_item_detail">
                    <span>时间：</span>{{time createDate}}
                </div>
                <div class="order_item_detail">
                    <span>金额：</span>￥{{totalPriceBank}}（{{Status status}}）
                </div>

<#--                    <div class="order_item_detail order_item_detail_ads">-->
<#--                        <span datavalue="{{rcvAddressId}}">收货地址：</span>{{rcvAddress}}-->
<#--                    </div>-->
            </div>
            <div class="order_item_bottom">
            <#--<div class="order_logo">代</div>-->
                <div class="order_item_btn">
                <#--<button>收货确认</button>-->
                <#--<button>立即付款</button>-->
                <#--<button>再次购买</button>-->
                    {{button status}}
                </div>
            </div>
        </a>
    </div>

    {{/each}}
</script>
<script>
    mui.init({
        pullRefresh: {
            container: "#pullrefresh",
            down: {
                callback: downPullfresh
            },
            up: {
                callback: upPullfresh

            }
        }
    });

    var isPageOk = true;
    var pageNo = 1;
    var pageSize = 10;
    var pages = null;

    function downPullfresh() {
        setTimeout(function () {
            pageNo = 1
            $('.order_goods_list').html('');
            mui('#pullrefresh').pullRefresh().endPulldownToRefresh();
            if (!isPageOk) {
                pageNo = 0
                isPageOk = true;
                mui('#pullrefresh').pullRefresh().refresh(true);
            } else {
                getData(pageNo, pageSize);
            }
        }, 1000)

    }

    function upPullfresh() {
        setTimeout(function () {
            pageNo++;
            getData(pageNo, pageSize);
            if (pageNo == pages) {
                isPageOk = false;
            }
            mui('#pullrefresh').pullRefresh().endPullupToRefresh((pageNo - 1) == pages);

        }, 1000)
    }

    function getData(pageNo1, pageSize1) {
        var url = 'deliverFormList';

        $.getJSON(url, {
            "purchaserId": '${purchaserId}',
            "startDate": '${startDate!}',
            "endDate": '${endDate!}',
            "status": '${status!}',
            "pageNo": pageNo1,
            "pageSize": pageSize1
        }, function (data) {
            console.log(data)
            pageSize = data.pageSize;
            pages = data.pages;
            if (data.list.length) {
                var tpl = $("#order-list").html();
                var template = Handlebars.compile(tpl);
                var html = template(data.list);
                $('.order_goods_list').append(html);
            }

        })
        mui('body').on('tap', 'a', function () {
            document.location.href = this.href;
        });
    }

    getData(pageNo, pageSize);


</script>
<style>
    .mui-pull-top-pocket {
        top: 40px;
    }

    button {
        padding: 0;
    }

    body {
        background: transparent;
        line-height: normal;
    }

    .mui-scroll {
        padding-bottom: 60px;
    }
</style>
</body>
</html>
