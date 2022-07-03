<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0,user-scalable=no"/>
    <title>易订货-订单列表</title>
    <link rel="stylesheet" href="/deliver/css/global.css">
    <link rel="stylesheet" href="/deliver/css/order/orderList.css">
    <link rel="stylesheet" href="/deliver/css/order/dataTime.css">
    <link rel="stylesheet" href="/css/mui.css">
    <link rel="stylesheet" href="/css/mescroll.css">
    <link rel="stylesheet" href="http://at.alicdn.com/t/font_1100572_wwztrn9ewrh.css">
    <link rel="stylesheet" href="http://at.alicdn.com/t/font_202335_gwbit3wv0zm.css">

    <style>
        body {
            background: transparent;
            line-height: normal;
        }
        .mescroll{
            position: fixed;
            top: 10px;
            bottom: 0;
            height: auto;
            padding-bottom: 50px;
            margin-top: 35px;
        }
        .mescroll-downwarp {
            top:1.4rem
        }
        .order_list_tab div[data-type="0"]{
            width: 100%;
        }
        .order_list_top {
            height: auto;
        }
        .order_list{
            padding-top:1rem;
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
            width: 50%;
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
    <link rel="stylesheet" href="http://at.alicdn.com/t/font_1100572_hmrfyv2jbmm.css">
</head>
<body>
<div class="order_list_page">
        <#--<div class="order_list_search">-->
            <#--<div>-->
                <#--<i class="iconfont icontubiaozhizuomoban-17"></i>-->
                <#--请输入订单编号/客户名称-->
            <#--</div>-->
            <#--<div class="screen">-->
                <#--<i class="iconfont icontubiaozhizuomoban-18"></i>-->
            <#--</div>-->
        <#--</div>-->
      <#--  <div class="order_list_tab">
            <div class="tab_active" data-type="0">订货单</div>
            &lt;#&ndash;<div data-type="1">发货单</div>&ndash;&gt;
        </div>-->


    <div class="cpmmodity_top" >
        <div class="search">
            <div><i class="iconfont icontubiaozhizuomoban-17"></i>
                <input type="text"  id="searchInput" onblur="getDatas(status2)"  name="text" class="search-input" placeholder="请输入订货方名称">
            </div>
        </div>
        <div class="tab" >
            <#--<div  ><span data-status="1" data-index="0" class="tab_act" >待付款</span></div>-->
            <div  ><span data-status="2" data-index="1">未发货</span></div><span style="color: red;font-size: 14px;position: absolute;margin-top: 2.8%;margin-left: 32%" >${waitPurchaseNum!}</span>
            <div  ><span data-index="2">已发货</span></div>
        </div>
    </div>

    <div id="mescroll" class="mescroll" >
        <div class="order_list order_goods_list" id="order_list"></div>
    </div>

    <!-- 底部导航栏 -->
    <div class="bottom_bar" id="bottom_bar" data-current="1"></div>
    <!-- 抽屉 -->
    <div class="mask">
        <div class="mask_popup">
            <div class="popup_title">创建日期</div>
            <div class="popup_date">
                <div class="kinerDatePickerInput" id="kinerDatePickerInput1" title="" startYear="1970"
                     default-val="2019-1-1">请选择
                </div>
                <i class="iconfont icontubiaozhizuomoban-13"></i>
            </div>
            <div class="order_popup_status">订单状态</div>
            <div class="order_popup_btn">
                <div class="order_btn_act">全部</div>
                <div>待发货确认</div>
                <div>待收货确认</div>
                <div>已完成</div>
            </div>
            <div class="order_popup_bottm">
                <div>重置</div>
                <div>确定</div>
            </div>
        </div>
    </div>
</div>
</body>
<script src="/deliver/js/tmpl.min.js"></script>
<script src="/js/jquery.min.js"></script>
<script src="/deliver/js/dataTime/swiper.min.js"></script>
<script src="/deliver/js/dataTime/kinerDatePicker.js"></script>
<script src="/deliver/js/order/orderList.js"></script>
<script src="/deliver/js/global.js"></script>
<script src="/js/handlebars-1.0.0.beta.6.js"></script>
<script src="/js/mescroll.js"></script>
<script type="text/template" id="order-list">
    {{#each this}}
    <div class="order_examine_item">
        <a href="/deliver/purchaseFormDetail?purchaseFormId={{id}}&deliverStatus={{deliverStatus}}">
        <div class="order_item_title">
            <span>订</span>
            <span style="display:none">代</span>
            <span>{{Status deliverStatus}}</span>
        </div>
        <div class="order_item_main">
            <div>{{orgName}}</div>
            <div>{{purchaseBillno}}</div>
            <#--<div>数量：<span>{{id}}</span></div>-->
            <div>
                订货时间：<span>{{time createDate}}</span>
                <#--{{status2 deliverStatus}}-->
                <#--&lt;#&ndash;<span class="order_status status1">(未付款)</span>&ndash;&gt;-->
                <#--&lt;#&ndash;<span class="order_status status2" style="display: none;">(未发货)</span>&ndash;&gt;-->
            </div>

        </div>
        </a>
    </div>
    {{/each}}
</script>
<script src="/js/mui.js"></script>
<script>
    Handlebars.registerHelper("status2", function (status) {
        switch (status) {
            case 1:
                return new Handlebars.SafeString('<span class="order_status status1">(已完成)</span>');
                break;
            case 0:
                return new Handlebars.SafeString('<span class="order_status status2">(未完成)</span>');
                break;
        }
    });

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
            case 1:
                return new Handlebars.SafeString('已完成');
                break;
            case 0:
                return new Handlebars.SafeString('未完成');
                break;
        }
    });
    mui.init({
        pullRefresh: {
            container: "#mescroll",
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
            mui('#mescroll').pullRefresh().endPulldownToRefresh();
            if (!isPageOk) {
                pageNo = 0
                isPageOk = true;
                mui('#mescroll').pullRefresh().refresh(true);
            } else {
                getData(pageNo, pageSize,null);
            }
        }, 1000)
    }
    var status2 = 1;
    function upPullfresh() {
        setTimeout(function () {
            pageNo++;
            getData(pageNo, pageSize,null);
            if (pageNo == pages) {
                isPageOk = false;
            }
            mui('#mescroll').pullRefresh().endPullupToRefresh((pageNo - 1) == pages);
        }, 1000)
    }
    getDatainit(pageNo,pageSize,1);
    function getDatainit(pageNo1, pageSize1,status2) {
        var url = '/deliver/purchaseFormList';
        $.getJSON(url, {
            "supplyOrgId":'${supplyOrgId!}',
            "pageNum": pageNo1,
            "pageSize": pageSize1,
            "status":status2,
            "purchaseBillno":$("#searchInput").val()
        }, function (data) {
            pageSize = data.pageSize;
            pages = data.pages;
            $('.order_list').html("");
            if (data.list.length) {
                var tpl = $("#order-list").html();
                var template = Handlebars.compile(tpl);
                var html = template(data.list);
                $('.order_list').append(html);
            }
        })
        mui('body').on('tap', 'a', function () {
            document.location.href = this.href;
        });
    }
    function getData(pageNo1, pageSize1,sta) {
        if(null!=sta){
            status2=sta;
        }
        var url = '/deliver/purchaseFormList';
        $.getJSON(url, {
            "supplyOrgId":'${supplyOrgId!}',
            "pageNum": pageNo1,
            "pageSize": pageSize1,
            "status":status2,
            "purchaseBillno":$("#searchInput").val()
        }, function (data) {
            pageSize = data.pageSize;
            pages = data.pages;
            if (data.list.length) {
                var tpl = $("#order-list").html();
                var template = Handlebars.compile(tpl);
                var html = template(data.list);
                $('.order_list').append(html);
            }
        })
        mui('body').on('tap', 'a', function () {
            document.location.href = this.href;
        });
    }

    function getDatas(status) {
        if(null!=status){
            status2=status;
        }
        pageNo = 1;
        pageSize = 10;
        mui('#mescroll').pullRefresh().refresh(true);

        var url = '/deliver/purchaseFormList';
        $.getJSON(url, {
            "supplyOrgId":'${supplyOrgId!}',
            "pageNum": pageNo,
            "pageSize": pageSize,
            "status":status2,
            "purchaseBillno":$("#searchInput").val()
        }, function (data) {
            pageSize = data.pageSize;
            pages = data.pages;
            $('.order_list').html("");
            if (data.list.length) {
                var tpl = $("#order-list").html();
                var template = Handlebars.compile(tpl);
                var html = template(data.list);
                $('.order_list').append(html);
            }
        })
        mui('body').on('tap', 'a', function () {
            document.location.href = this.href;
        });
    }
    /*function getData(pageNo1, pageSize1,successCallback) {
        var url = '/deliver/purchaseFormList';
        $.getJSON(url, {
            "supplyOrgId":'${supplyOrgId!}',
            "pageNum": pageNo1,
            "pageSize": pageSize1,
        }, function (data) {
                var tpl = $("#order-list").html();
                var template = Handlebars.compile(tpl);
                var html = template(data.list);
                $('.order_goods_list').append(html);
                 successCallback(data);
        })
    }*/

    $('.tab div span').on('click', function () {
        var index = $(this).attr('data-index')
        $(this).addClass('tab_act').parent('div').siblings('div').find('span').removeClass('tab_act')
        status2 = Number($(this).attr("data-index"));

        getDatas(status2);
        $('.type_list_item span i').removeClass('icontubiaozhizuomoban-10').addClass('iconyuan')
        $('.type_list_item span i').eq(0).removeClass('iconyuan').addClass('icontubiaozhizuomoban-10')
        $('.type_list_item').eq(0).addClass('type_act').siblings().removeClass('type_act')
    })

</script>
<script type="text/x-tmpl" id="tmpl_bottom">
    {% for (var i=0; i<o.length; i++) { %}
      <div data-index="{%=i%}">
        <i class="iconfont {%=o[i].icon%}"></i>
        <p>{%=o[i].name%}</p>
      </div>
    {% } %}



</script>
</html>