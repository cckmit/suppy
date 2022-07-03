<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0,user-scalable=no"/>
    <title>易订货-发货单</title>
    <link rel="stylesheet" href="/deliver/css/global.css">
    <link rel="stylesheet" href="/css/order/orderGoods.css">
    <link rel="stylesheet" href="/css/mui.css">
    <link rel="stylesheet" href="fonts/mui.ttf"/>
    <link rel="stylesheet" href="http://at.alicdn.com/t/font_1100572_hmrfyv2jbmm.css">
    <link rel="stylesheet" href="http://at.alicdn.com/t/font_202335_gwbit3wv0zm.css">
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
        .order_goods_top {
            background: #fff;
            text-align: center;
            color:#0BC6D7;
            font-size: 0.28rem;
            line-height: 0.79rem;
            border-bottom: 0.04rem inset #0BC6D7;
        }
       .order_goods_list{
            padding-top: 1.3rem;
       }
        .cpmmodity_top {
            z-index: 999;
        }
        .cpmmodity_top{
            width: 100%;
            height: 1.68rem;
            position: fixed;
            background: #fff;
            top: 0;
        }
        .search{
            width: 100%;
            height: 0.88rem;
            padding: 0.16rem 0.3rem;
            position: relative;
        }
        .search>div{
            width: 6.9rem;
            height: 0.56rem;
            border-radius: 0.28rem;
            border: 0.01rem solid #F0F0F0;
            padding: 0 0.33rem;
            position: relative;
            font-size: 0.28rem;
            color: #ccc;
            line-height: 0.54rem;
        }
        .search>span{
            position: absolute;
            width: 0.38rem;
            height: 0.38rem;
            line-height: 0.38rem;
            text-align: center;
            top: 0.25rem;
            right: 0.3rem;
        }
        .search>span i {
            display: block;
            color: #666666;
            font-size: 0.44rem;
        }
        .search>div i{
            font-size: 0.32rem;
            color: #666666;
        }
        .tab{
            width: 100%;
            height: 0.8rem;
            display: flex;
            justify-content: space-between;
            border-bottom: 0.01rem solid #E0E0E0
        }
        .tab>div{
            width: 33.33%;
            height: 100%;
            text-align: center;
            line-height: 0.79rem;
            font-size: 0.28rem;
            color: #666;
        }
        .tab>div i{
            font-size: 0.34rem;
            position: relative;
            top: 0.03rem;
        }
        .tab>div span{
            display: block;
            height: 0.76rem;
        }
        .tab>div:first-child,.tab>div:nth-child(2){
            padding: 0 0.65rem;
        }
        .tab_act{
            color: #0BC6D7;
            border-bottom: 0.04rem solid #0BC6D7;
        }
        .search-input {
            background: transparent;
            padding: 5px 10px!important;
            font-size: 0.3rem!important;
            color: #0C0C0C!important;
            height: 30px!important;
            width: 90%!important;
            border: 0!important;
            background-color: transparent!important;
        }
        .tab>div:first-child,.tab>div:nth-child(2){
             padding: 0rem;
        }
    </style>
</head>
<body>
<div style="display: none;" id="purchaseId">${purchaserId!}</div>
<div class="orderGoodsPage">
    <#--<div class="order_goods_top">
        发货单
    </div>-->
    <div class="cpmmodity_top">
        <div class="search">
            <div><i class="iconfont icontubiaozhizuomoban-17"></i>
                <input type="text" onblur="searchProductByDeliverNo()" id="searchInput"  name="text" class="search-input" placeholder="请输入发货单号">
            </div>
        </div>
        <div class="tab" >
            <div onclick="getDatas(0)" ><span data-status="1" data-index="0" class="tab_act" >待确认</span></div>
            <div onclick="getDatas(1)" ><span data-status="2" data-index="1" >已确认</span></div>
            <div onclick="getDatas(2)" ><span data-index="2">已拒绝</span></div>
        </div>
    </div>

    <div class="content mui-scroll-wrapper" id="pullrefresh" style="margin-top: 8px">
        <div class="mui-scroll">
            <div class="order_goods_list">
            </div>

        </div>

    </div>
    <!-- 底部导航栏 -->

    <div class="bottom_bar" id="bottom_bar" data-current="2"></div>
</div>
<script src="/js/tmpl.min.js"></script>
<script src="/js/jquery.min.js"></script>
<script src="/js/order/orderGoods.js"></script>
<script src="/deliver/js/global.js"></script>
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
    Handlebars.registerHelper("exportmark", function (status) {
        switch (status) {
            case 0:
                return new Handlebars.SafeString('<button>未导出</button>');
                break;
            case 1:
                return new Handlebars.SafeString('<button>已导出</button>');
                break;
        }
    });
</script>
<script type="text/template" id="order-list">
    {{#each this}}
    <div class="order_goods_list_item"  >
            <div class="order_item_top" style="height: 175px" >
                <a  href="deliverFormDetail?deliverFormId={{id}}">
                <div class="order_item_top_title">
                    <span>{{deliverBillno}}</span>
                    <span>
                            <#--待收货确认-->
                                {{Status status}}
                        </span>
                </div>
                <div class="order_item_detail">
                    <span>发货单号：</span>{{deliver_billno}}
                </div>
                <div class="order_item_detail">
                    <span>采购方：</span>{{org_name}}
                </div>
                <div class="order_item_detail">
                    <span>数量：</span>{{total_quantity}}
                </div>
                <div class="order_item_detail">
                    <span>时间：</span>{{time create_date}}
                </div>
                <div class="order_item_detail">
                    <span>金额：</span>￥{{total_price}}（{{Status status}}）
                </div>
                </a>

            </div>
            <div class="order_item_bottom">
            <#--<div class="order_logo">代</div>-->
                <a  href="/deliver/deliverOrderProductExport?id={{id}}">

                <div class="order_item_btn">
                    <#--<button>收货确认</button>-->
                <#--<button>立即付款</button>-->
                <#--<button>再次购买</button>-->
                    <button>导出商品</button>
                    {{exportmark export_mark}}
                </div>
                </a>
            </div>
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

    mui('body').on('tap', 'a', function () {
        document.location.href = this.href;
        this.getElementsByTagName("button")[1].innerText="已导出"
       // $("#export"+this.id).text("已导出");
    });
    var isPageOk = true;
    var pageNo = 1;
    var pageSize = 10;
    var pages = null;
    var sta=0;

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
                getData(pageNo, pageSize,null);
            }
        }, 1000)

    }

    function upPullfresh() {
        setTimeout(function () {
            pageNo++;
            getData(pageNo, pageSize,null);
            if (pageNo == pages) {
                isPageOk = false;
            }
            mui('#pullrefresh').pullRefresh().endPullupToRefresh((pageNo - 1) == pages);

        }, 1000)
    }

    function getData(pageNo1, pageSize1,status) {
        if(null!=status){
            sta=status;
        }
        var url = '/deliver/selectDeliverForm';
        $.getJSON(url, {
            "supplyOrgId": '${supplyOrgId}',
            "startDate": '${startDate!}',
            "endDate": '${endDate!}',
            "status": sta,
            "pageNo": pageNo1,
            "pageSize": pageSize1
        }, function (data) {
            console.log(data)
            pageSize = data.pageSize;
            pages = data.pages;
            //$('.order_goods_list').html("");
            if (data.list.length) {
                var tpl = $("#order-list").html();
                var template = Handlebars.compile(tpl);
                var html = template(data.list);
                $('.order_goods_list').append(html);
            }
        })

    }

    function getDatas(status) {
        if(null!=status){
            sta=status;
        }
        pageNo = 1;
        pageSize = 10;
        mui('#pullrefresh').pullRefresh().refresh(true);

        var url = '/deliver/selectDeliverForm';
        $.getJSON(url, {
            "supplyOrgId": '${supplyOrgId}',
            "startDate": '${startDate!}',
            "endDate": '${endDate!}',
            "status": sta,
            "pageNo": pageNo,
            "pageSize": pageSize
        }, function (data) {
            console.log(data)
            pageSize = data.pageSize;
            pages = data.pages;
            $('.order_goods_list').html("");
            if (data.list.length) {
                var tpl = $("#order-list").html();
                var template = Handlebars.compile(tpl);
                var html = template(data.list);
                $('.order_goods_list').append(html);
            }
        })

    }
    getData(pageNo, pageSize,0);

     function searchProductByDeliverNo(){
         console.log($("#searchInput").val())
         $.getJSON('/deliver/deliverFormList', {
            'id':$("#searchInput").val()
         }, function (data) {
             console.log(data)
             pageSize = data.pageSize;
             pages = data.pages;
             $('.order_goods_list').html("");
             if (data.length) {
                 var tpl = $("#order-list").html();
                 var template = Handlebars.compile(tpl);
                 var html = template(data);
                 $('.order_goods_list').append(html);
             }
         })



    };
    $('.tab div span').on('click', function () {
        var index = $(this).attr('data-index')
            $(this).addClass('tab_act').parent('div').siblings('div').find('span').removeClass('tab_act')
            status = Number($(this).attr("data-status"));
            $('.type_list_item span i').removeClass('icontubiaozhizuomoban-10').addClass('iconyuan')
            $('.type_list_item span i').eq(0).removeClass('iconyuan').addClass('icontubiaozhizuomoban-10')
            $('.type_list_item').eq(0).addClass('type_act').siblings().removeClass('type_act')
    })

</script>

</body>
</html>
