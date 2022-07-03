<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0,user-scalable=no"/>
    <title>易订货-订货单</title>
    <link rel="stylesheet" href="/css/global.css">
    <link rel="stylesheet" href="/css/mui.css">
    <link rel="stylesheet" href="fonts/mui.ttf"/>
    <link rel="stylesheet" href="/css/order/orderGoods.css">
    <link rel="stylesheet" href="http://at.alicdn.com/t/font_1091326_sr48ma9pxf.css">
    <style>
      .order_goods_list{padding-top:0}
      .mui-scroll{padding-top: 1.9rem;}
      .order_item_top {
          width: 100%;
          height: 3.01rem;
          padding-top: 0.36rem;
      }
      .order_item_detail_ads{

      }
    </style>
</head>
<body>
<div style="display: none;" id="purchaseId">${purchaserId!}</div>
  <div class="tab">
    <div class="active" type=" ">全部</div>
    <div type="0">待付款</div>
    <div type="1">待收货</div>
    <div type="2">已完成</div>
    <div type="3">已取消</div>
  </div>
<div class="orderGoodsPage">
    <div class="order_goods_top">
        <#--<div class="order_search">-->
        <#--<a href="/purchase/orderQueryInit">-->
        <#--<i class="iconfont icontubiao-47"></i>-->
        <#--</a>-->
        <#--</div>-->
        <div class="order_screen">
            <a href="myCart?purchaserId=${purchaserId!}" class="cart">
                <i class="iconfont icontubiao-13"></i>
                <span class="cart_num">${count!0}</span>
            </a>
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

    <#--<div class="bottom_bar" id="bottom_bar" data-current="2"></div>-->
</div>
<script src="/js/tmpl.min.js"></script>
<script src="/js/jquery.min.js"></script>
<script src="/js/order/orderGoods.js"></script>
<#--<script src="/js/global.js"></script>-->
<script src="/js/handlebars-1.0.0.beta.6.js"></script>
<script src="/js/mui.js"></script>
<#--<script type="text/x-tmpl" id="tmpl_bottom">
      {% for (var i=0; i<o.length; i++) { %}
        <div class="{%=o[i].class%}" data-index="{%=i%}">
          <i class="iconfont {%=o[i].icon%}"></i>
          <p>{%=o[i].name%}</p>
        </div>
      {% } %}

</script>-->
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
            case 4:
                return new Handlebars.SafeString('订货单超时未付款');
                break;
            case 3:
                return new Handlebars.SafeString('订货方自己关闭');
                break;
            case 2:
                return new Handlebars.SafeString('已完成');
                break;
            case 1:
                return new Handlebars.SafeString('未完成');
                break;
            case 0:
                return new Handlebars.SafeString('未付款');
                break;
        }
    });
    Handlebars.registerHelper("button", function (status, uid) {
        switch (status) {
            case 4:
                return new Handlebars.SafeString('<button>订货单超时未付款</button>');
                break;
            case 3:
                return new Handlebars.SafeString('<button>订货方自己关闭</button>');
                break;
            case 2:
                return new Handlebars.SafeString('');
                break;
            //<button>再次购买</button>
            case 1:
                return new Handlebars.SafeString('');
                break;
            case 0:
                return new Handlebars.SafeString('<button onclick="pay(' + uid + ');">立即付款</button>');
                break;
        }
    });

    function pay(id) {
        window.location.href = "pay?purchaseFormId=" + id;
    }
</script>
<script type="text/template" id="order-list">
    {{#each this}}
    <div class="order_goods_list_item" purchaseBillno="{{purchaseBillno}}">
        <a href="purchaseDetail?purchaseFormId={{id}}">
            <div class="order_item_top">
                <div class="order_item_top_title">
                    <span>{{purchaseBillno}}</span>
                    <span>
                            <#--待收货确认状态-->
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
                    <span>金额：</span>￥{{totalPrice}}（{{Status status}}）
                </div>
                <div class="order_item_detail">
                    {{#if rcvAddress}}
                    <span datavalue="{{rcvAddressId}}">收货地址：</span>{{rcvAddress}}
                    {{/if}}
                </div>
            </div>
        </a>
        <div class="order_item_bottom">
            <#--<div class="order_logo">代</div>-->
            <div class="order_item_btn">
                <#--<button>收货确认</button>-->
                <#--<button>立即付款</button>-->
                <#--<button>再次购买</button>-->
                {{button status id}}
            </div>
            <div class="order_item_btn_query">
                <button>查看</button>
            </div>
        </div>

        <div class="wrap_detail">
        </div>
    </div>
    {{/each}}
</script>
<script>
    var listType;
    mui('body').on('tap', '.order_item_btn_query', function () {
        $(this).toggleClass('active')
        var obj = $(this).parents('.order_goods_list_item');
        if ($(this).hasClass('active')) {
            var purchaseBillno = $(obj).attr("purchaseBillno");
            getDeliverData(purchaseBillno, $(obj).find('.wrap_detail'))
            $(this).find('button').text('收起')
        } else {
            $(obj).find('.wrap_detail').hide();
            $(this).find('button').text('查看')
        }
    })

    function getDeliverData(pBillno, obj) {
        var html = "";
        console.log(pBillno)
        $.post("/purchase/queryPurchaseForm", {"pBillno": pBillno}, function (data) {
            console.log(data)
            if (data != null && data.length > 0) {
                for (var i = 0; i < data.length; i++) {
                    var product = data[i];
                    html += "<div class=\"order_item_details\">\n" +
                        "<div class=\"order_item_tops\">\n" +
                        "<div class=\"order_item_top_titles\" style='border-top: 0.01rem solid #E0E0E0;'>\n" +
                        "<span>发货单号：" + product.deliverBillno + "</span>\n" +
                        "<span>\n" +
                        "</span>\n" +
                        "</div>\n" +
                        "<div class=\"order_item_con\">\n" +
                        "<div class=\"img\" style=\"background-image:url(" + product.image + ") \"></div>\n" +
                        "<div class=\"text\">" + product.name + "</div>\n" +
                        "</div>\n" +
                        "</div>\n" +
                        "<div class=\"order_item_bottoms\">\n" +
                        "<div class=\"order_item_des\">\n" +
                        "<span class=\"time\">" + product.createDate + "</span>\n" +
                        "<span class=\"price\">发货数量 共" + product.deliverQuantity + "件</span>\n" +
                        "</div>\n" +
                        "</div>\n" +
                        "</div>";
                }
                $(obj).html(html);
            } else {
                $(obj).html("<div class=\"order_item_details zanwu\">暂无发货单信息<div>");
            }
            $(obj).show();
        })
    }



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
    var type = 0;

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
                getDatabyType(pageNo, pageSize);
            }
        }, 1000)

    }

    function upPullfresh() {
        setTimeout(function () {
            pageNo++;
            getDatabyType(pageNo, pageSize);
            if (pageNo == pages) {
                isPageOk = false;
            }
            mui('#pullrefresh').pullRefresh().endPullupToRefresh((pageNo - 1) == pages);

        }, 1000)
    }

    <#--function getData(pageNo1, pageSize1) {-->
    <#--    var url = '/purchase/purchaseFormList';-->
    <#--    $.getJSON(url, {-->
    <#--        "purchaserId": '${purchaserId}',-->
    <#--        "startDate": '${startDate!}',-->
    <#--        "endDate": '${endDate!}',-->
    <#--        "status": listType,-->
    <#--        "pageNo": pageNo1,-->
    <#--        "pageSize": pageSize1-->
    <#--    }, function (data) {-->
    <#--        console.log(data)-->
    <#--        pageSize = data.pageSize;-->
    <#--        pages = data.pages;-->
    <#--        if (data.list.length) {-->
    <#--            var tpl = $("#order-list").html();-->
    <#--            var template = Handlebars.compile(tpl);-->
    <#--            var html = template(data.list);-->
    <#--            $('.order_goods_list').append(html);-->
    <#--            $('.nodata').remove()-->
    <#--        }-->

    <#--        if(data.list.length==0){-->
    <#--            $('body').append('<div class="nodata">暂无相关数据</div>')-->
    <#--        }-->


    <#--    })-->
    <#--    mui('body').on('tap', 'a', function () {-->
    <#--        document.location.href = this.href;-->
    <#--    });-->
    <#--}-->

    function getDatabyType(pageNo1, pageSize1) {
        var url = '/purchase/purchaseFormList';
        $.getJSON(url, {
            "purchaserId": '${purchaserId}',
            "startDate": '${startDate!}',
            "endDate": '${endDate!}',
            "status": listType,
            "pageNo": pageNo1,
            "pageSize": pageSize1
        }, function (data) {
            console.log(data.list.length)
            pageSize = data.pageSize;
            pages = data.pages;
            $('.nodata').remove()
            if (data.list.length) {
                console.log(data)
                var tpl = $("#order-list").html();
                var template = Handlebars.compile(tpl);
                var html = template(data.list);
                $('.order_goods_list').append(html);
            }else {
                $('.order_goods_list').after('<div class="nodata">暂无相关数据</div>')
            }
            if($('.order_goods_list div').length){
                $('.nodata').remove()
            }


        })
        mui('body').on('tap', 'a', function () {
            document.location.href = this.href;
        });
    }

    getDatabyType(pageNo, pageSize);

    mui('body').on('tap','.tab div',function(){
        $('.nodata').remove()
        $('.order_goods_list').html('')
        pageNo=1
        type = $(this).attr("type");
        $(".tab .active").removeClass("active");
        $(this).addClass("active");
        console.log(type)
        listType=type;
        getDatabyType(pageNo, pageSize);
        mui('.mui-scroll-wrapper').scroll().scrollTo(0, 0, 0);
    })

</script>
</body>
</html>
