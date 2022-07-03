<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta name="viewport"
        content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0,user-scalable=no"/>
  <title>易订货-购物车</title>
  <link rel="stylesheet" href="/css/global.css">
  <link rel="stylesheet" href="/css/commodity/shopCart.css">
  <link rel="stylesheet" href="/css/layer.css">
  <link rel="stylesheet" href="/css/datepicker.css">
  <link rel="stylesheet" href="/css/mescroll.css">
  <link rel="stylesheet" href="http://at.alicdn.com/t/font_1091326_sg61r64lenh.css">
  <style>
    .mescroll {
      position: fixed;
      top: 1rem;
      bottom: 1.9rem;
      height: auto;
    }
    .shop_cart_bottom {
      bottom: .98rem;
    }
    .selsect-data {
      display:flex;
      align-items: center;
      height: 100%;
      width: 100%;
    }
    .selsect-data input {
      height: .5rem;
      width: 100%;
      border-radius: 2px;
      margin-right: .2rem;
      text-indent: .2rem;
    }
    .selsect-data input::-webkit-input-placeholder {
      color: #999;
    }
    .selsect-data .search {
      font-size:.28rem;
    }
    .shop_cart_list {
      padding-top: 0;
    }
    .selsect-data .item {
        flex: 1;
        display: flex;
    }
  </style>
</head>
<body>
<div class="shop_cart_page">
  <div class="shop_cart_top">
    <div class="selsect-data">
      <div class="item">
        <input type="text" id="card-start" readonly="" placeholder="选择开始日期">
      </div>
      <div class="item">
        <input type="text" id="card-end" readonly="" placeholder="选择结束日期">
      </div>
        <div class="search">搜索</div>
    </div>
  </div>
  <div id="mescroll0" class="mescroll">
    <div class="shop_cart_list" id="shop_cart_list">
    </div>
  </div>

  <div class="shop_cart_bottom">
    <div class="all_choose">
      <i class="iconfont iconquan"></i> 全选
    </div>
    <div class="all_chooses">
      <i class="iconfont iconquan"></i> 全选
    </div>
    <div class="submit">下单</div>
    <div class="total_price">
      <div id="totalprice"><span>合计：</span>￥0.00</div>
      <div id="totalcategory">共种件商品</div>
    </div>
  </div>

</div>

<div class="bottom_bar" id="bottom_bar" data-current="3"></div>
<script src="/js/datePicker.js"></script>
<script src="/js/jquery.min.js"></script>
<script src="/js/tmpl.min.js"></script>
<script src="/js/global.js"></script>
<script src="/js/mescroll.js"></script>
<script src="/js/handlebars-1.0.0.beta.6.js"></script>
<script type="text/x-tmpl" id="tmpl_bottom">
      {% for (var i=0; i<o.length; i++) { %}
        <div class="{%=o[i].class%}" data-index="{%=i%}">
          <i class="iconfont {%=o[i].icon%}"></i>
          <p>{%=o[i].name%}</p>
        </div>
      {% } %}

</script>
<#--<script src="https://cdn.bootcss.com/blueimp-JavaScript-Templates/3.11.0/js/tmpl.min.js"></script>-->
<script src="/js/commodity/shopCart.js"></script>
<script src="/js/layer.js"></script>
<script src="/js/datePicker.js"></script>
<script>
  var start = '${start!}'
  var end = '${end!}'
  var mescrollArr = new Array(1);
  var curNavIndex = 0;
  var calendarstart = new datePicker();
  var calendarend = new datePicker();
  calendarstart.init({
    'trigger': '#card-start', /*选择器，触发弹出插件*/
    'type': 'date',/*date 调出日期选择 datetime 调出日期时间选择 time 调出时间选择 ym 调出年月选择*/
    'minDate':'1900-1-1',/*最小日期*/
    'maxDate':'2100-12-31',/*最大日期*/
    'onSubmit':function(){/*确认时触发事件*/
      start = calendarstart.value;
    },
    'onClose':function(){/*取消时触发事件*/
    }
  });
  calendarend.init({
    'trigger': '#card-end', /*选择器，触发弹出插件*/
    'type': 'date',/*date 调出日期选择 datetime 调出日期时间选择 time 调出时间选择 ym 调出年月选择*/
    'minDate':'1900-1-1',/*最小日期*/
    'maxDate':'2100-12-31',/*最大日期*/
    'onSubmit':function(){/*确认时触发事件*/
      end = calendarend.value;
    },
    'onClose':function(){/*取消时触发事件*/
    }
  });
  // mescrollArr[0] = initMescroll("mescroll0", "shop_cart_list");


  function initMescroll(mescrollId, clearEmptyId) {

    //创建MeScroll对象,内部已默认开启下拉刷新,自动执行up.callback,刷新列表数据;
    mescroll = new MeScroll(mescrollId, {
      //上拉加载的配置项
      up: {
        callback: getListData, //上拉回调,此处可简写; 相当于 callback: function (page) { getListData(page); }
        noMoreSize: 4, //如果列表已无数据,可设置列表的总数量要大于半页才显示无更多数据;避免列表数据过少(比如只有一条数据),显示无更多数据会不好看; 默认5
        empty: {
          tip: "暂无相关数据~"
        },
        clearEmptyId: clearEmptyId //相当于同时设置了clearId和empty.warpId; 简化写法;默认null
      }
    });
    return mescroll;
  }

  function getListData(page) {
    //联网加载数据

    getProduct(start, end, page.num, page.size, function (data) {
      nodata  = data.data.size
      //联网成功的回调,隐藏下拉刷新和上拉加载的状态;
      mescrollArr[curNavIndex].endSuccess(data.data.total);//传参:数据的总数; mescroll会自动判断列表如果无任何数据,则提示空;列表无下一页数据,则提示无更多数据;
      var tpl = $("#list").html();
      var template = Handlebars.compile(tpl);
      var html = template(data.data.list);
      $('#shop_cart_list').append(html);
      //减
      caculator();

      $('body').on('click', '.cart_list_item>div:nth-child(2)',function () {
        caculator()
      })
    }, function () {
      //联网失败的回调,隐藏下拉刷新和上拉加载的状态;
      mescrollArr[curNavIndex].endErr();
    });
  }

  function getProduct(start, end,page,size, successCallback) {
    $.post("willPurchaseListJson", {
      "startdate": start,
      "enddate": end,
      "page":page,
      "size":size
    }, function (data) {
      successCallback(data);

    })

  }
  $('.search').on('click',function(){

    mescrollArr[0] = initMescroll("mescroll0", "shop_cart_list")

  })
</script>
<script type="text/template" id="list">
  {{#each this}}
  <div class="cart_list_item">
    <div><i class="iconfont iconquan"></i></div>
    <div class="chebox"><i class="iconfont iconquan"></i></div>
    <div style="background-image:url({{image}})">
      <#--      <div>{{quantity}}种货品</div>-->
    </div>
    <div>
      <span style="display: none" class="cid">{{product_id}}</span>
      <div class="item_title">{{name}}</div>
      <div class="item_code_num">编号：{{barcode}}</div>
      <div class="item_choose_num">
        <div>￥<span class="cart_price">{{price}}</span>/瓶</div>
        <div class="item_stepped">
          <span data-index="0"><i class="iconfont icontubiao-3"></i></span>
          <input type="text" class="cart_amount" value="{{quantity}}" onchange="caculator();">
          <span data-index="1"><i class="iconfont iconjia"></i></span>
        </div>
      </div>
    </div>
  </div>
  {{/each}}
</script>

<script>

  var purchaserId = parseInt('${purchaserId!}')

  $(".submit").click(function () {
    //下单
    var productIds = ''
    var sum=''
    $('.cart_list_item').each(function () {
      if($(this).find('.chebox i').attr('class') == 'iconfont iconqueren singlered'){
        productIds += $(this).find('.cid')[0].innerText + ',';
        sum += $(this).find('.cart_amount').val() + ','
      }

    })
    if (productIds != '') {
      productIds = productIds.substr(0, productIds.length - 1);
    }
    if (sum != '') {
      sum = sum.substr(0, sum.length - 1);
    }
    if(productIds==''){
      layer.open({
        type: 1,
        content: '请选择商品',
        skin: 'msg',
        time: 2
      })
    }

    $.ajax({
      url: "purchaseToForm",
      method: 'post',
      data: {"purchaseId": purchaserId, "ids": productIds,"counts":sum},
      success: function (data) {
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
  })

</script>
</body>
</html>
