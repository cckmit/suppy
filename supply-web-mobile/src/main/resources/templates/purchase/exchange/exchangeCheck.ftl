<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0,user-scalable=no" />
    <title>自助兑换单核销</title>
    <link rel="stylesheet" href="/deliver/css/global.css">
    <link rel="stylesheet" href="/deliver/css/warehousing/warehouseList.css">
    <link rel="stylesheet" href="/css/mescroll.css">
    <link rel="stylesheet" href="/css/layer.css">

    <style type="text/css">
        .mescroll{
            position: relative;
            height: auto;
        }
        .search {
            background: #fff;
            display: flex;
            padding: .4rem;
        }
        .input{
            border:1px solid #ccc;
            height:.4rem;
            border-radius: 3px;
            padding:.1rem;
            font-size: .28rem;
            -webkit-appearance:none;
            flex:1 0 35%;
        }
        .btn {
            border-radius: 3px;
            background: #ff807d;
            color: #fff;
            font-size: .26rem;
            flex:1;
            margin:0 0 0 .2rem;
        }
        .item {
            background: #fff;
            font-size: .26rem;
            margin-top:.2rem;
            color: #333
        }
        .item1 div {
            padding:.26rem .4rem;
            position: relative;
        }
        .item1 div span:first-child {
            color: #9c9c9c;
        }
        .item1 div:after,.item2 .bd:after,.item2 .hd:after {
            content: '';
            height:1px;
            background: #e2e2e2;
            position: absolute;
            bottom:0;
            left:4%;
            display: block;
            width: 92%;
            transform: scaleY(.5);

        }
        .item2 .hd ,.item2 .bd {
            display: flex;
            padding:.2rem 0;
            text-align: center;
            align-items: center;
            position: relative;
        }
        .item2 .hd span,.item2 .bd span {
            flex:1;
        }

        .item2 .bd:last-child:after,.item1 div:last-child:after {
            background:0;
        }
        .ok-btn {
            font-size: .3rem;
            padding: .2rem;
            width: 90%;
            margin:.4rem auto;
            text-align: center;
            display: block;
            background: #25bf83;
        }
        .red-color {
            color: #ff807d;
        }
        .green-bg {
            background: #25bf83;
        }
        .tip {
            font-size:.3rem;
            color: #a2a2a2;
            text-align: center;
            margin-top:1rem;
        }
        .item1 .title:after {
            background: 0;
        }
        .item1 .title {
            position: relative;
            font-size:.3rem;
            padding-bottom: 0;

        }
        .item1 .title:before {
            content: '';
            height: .4rem;
            width: 2px;
            display: block;
            position: absolute;
            left: .2rem;
            top: 70%;
            margin-top: -.2rem;
            background: #ff807d;
        }
        .list_main {margin-top:10px}
        .list_main > div {
            background:#fff;
            margin-bottom:10px;
            font-size: .26rem;
            line-height:.6rem

        }
        .list_main > div > div{
            background:#fff;
            font-size: .26rem;
            display:flex;
            justify-content: space-around;
            border-bottom:1px solid #efefef;
            text-align: left;
            align-items: center;
        }
        span.status-unpay {
            color: red;
        }
        span.status-done {
            color: gray;
        }
        span.status-paied {
            color: green;
        }
        .billno{
            background: #51a7f5;
            border-radius: 3px;
            color: #fff;
            margin: 7px;
            padding: 0px 11px;
            box-shadow: 1px 2px 3px #656565;
        }
    </style>
</head>
<body>
<div class="search">
    <input type="text" name="" class="input" id="billno" placeholder="自助兑换订单号或POS凭证号">
    <button class="btn" id="btn-query">查询</button>
    <button class="btn green-bg" id="btn-query-recent">快速查询</button>
</div>
<div id="order-info"></div>
<div id="mescroll" class="mescroll">

    <div class="list">
        <div class="list_main" id="dataList"></div>
    </div>
</div>



</body>

<script src="/deliver/js/tmpl.min.js"></script>
<script src="/js/jquery.min.js"></script>
<#--<script src="/deliver/js/global.js"></script>-->
<script src="/js/mescroll.js"></script>
<script src="/purchase/js/exchange/exchangeCheck.js"></script>
<script src="/js/layer.js"></script>
<script type="text/x-tmpl" id="order-info-tmpl">
{% if (o.data != null) { %}
    <div class="item item1">
    <div class="title">订单信息</div>
      <div>
        <span>订单号：</span>{%=o.data.main.billno%}
      </div>
      <div>
        <span>机构名称：</span>
        {% if(o.data.orgInfo != null){ %}
            {%=o.data.orgInfo.orgName%}
        {% } %}
      </div>
      <div>
        <span>操作柜员：</span>
        {% if(o.data.userInfo != null){ %}
            {%=o.data.userInfo.name%}
        {% } %}
      </div>
      <div>
        <span>总价：</span>{%=o.data.main.totalPrice%} ￥
      </div>
      <div class="red-color">
        <span >状态：</span>
        {% if(o.data.main.status == 0){ %}
            未支付
        {% } %}
        {% if(o.data.main.status == 1){ %}
            已支付
        {% } %}
        {% if(o.data.main.status == 2){ %}
            已发放
        {% } %}
      </div>
      <div>
        <span >订单类型：</span>
        {% if(o.data.main.type == 0){ %}
            积分支付兑换
        {% } %}
        {% if(o.data.main.type == 1){ %}
            配送
        {% } %}
        {% if(o.data.main.type == 2){ %}
            无消费兑换
        {% } %}
        {% if(o.data.main.type == 3){ %}
            现金支付兑换
        {% } %}
        {% if(o.data.main.type == 4){ %}
            现金积分混合支付兑换
        {% } %}
      </div>
      <div>
        <span>订单时间：</span>{%=o.data.main.createDate%}
      </div>
      <div>
        <span>备注：</span>{%=o.data.main.remark%}
      </div>
      <div>
        <span>商品总数：</span><span>{%=o.data.main.totalQuantity%}
      </div>
      <div>
        <span>发放时间：</span><span>{%=o.data.main.outTime%}
      </div>

    </div>

   {% if(o.data.extra != null ){ %}
    <div class="item item1">
    <div class="title">附加信息</div>
      <div>
        <span>客户称谓：</span>{%=o.data.extra.custName%}
      </div>
      <div>
        <span>联系电话：</span>{%=o.data.extra.custTel%}
      </div>
      <div>
        <span>地址：</span>{%=o.data.extra.custAddr%}
      </div>
    </div>
   {% } %}

    <div class="item item2">
      <div class="hd">
        <span>产品id</span>
        <span>名称</span>
        <span>单价</span>
        <span>数量</span>
        <span>小计</span>
      </div>
       {% for (var i=0; i<o.data.list.length; i++) { %}
        <div class="bd">
        <span>{%=o.data.list[i].product.id%}</span>
        <span>{%=o.data.list[i].product.name%}</span>
        <span>{%=o.data.list[i].detail.purchasePrice%}￥</span>
        <span>{%=o.data.list[i].detail.quantity%}</span>
        <span>{%=o.data.list[i].detail.point%}￥</span>
        </div>
       {% } %}

    </div>
    {% if(o.data.main.status == 1){ %}
    <input type="text" value="{%=o.data.main.billno%}" hidden="hidden" id="billno-out">
    <button class="btn ok-btn" id="btn-out">发放</button>
    {% } %}

{% } %}

{% if (o.data == null) { %}
       <div class="tip">{%=o.msg%}</div>
{% } %}

</script>
<script type="text/x-tmpl" id="recent-order">
    {% for (var i=0; i < o.length ; i++ ) { %}
        <div>
          <div>
            <span class="billno">订单号：<span class="order-billno">{%=o[i].billno%}</span></span>
            <span>￥{%=o[i].totalPrice%}</span>
            </div>
            <div>
            <span>{%=o[i].createDate%}</span>
            {% if(o[i].status == 0){ %}
                <span class="status-unpay">未支付</span>
            {% }else if(o[i].status == 1) { %}
                <span class="status-paied">已支付</span>
            {% } else if(o[i].status == 2) { %}
                <span class="status-done">已发放</span>
            {% } else { %}
                <span class="status-other">其他</span>
            {% } %}
            </div>
       </div>
    {% } %}
</script>
</html>