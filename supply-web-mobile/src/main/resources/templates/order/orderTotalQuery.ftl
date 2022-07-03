<!DOCTYPE html>
<html>
  <head>
      <meta charset="utf-8">
      <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0,user-scalable=no" />
      <title>易订货-订单统计查询</title>
      <link rel="stylesheet" href="/css/global.css">
      <link rel="stylesheet" href="/css/order/orderTotalQuery.css">
      <link rel="stylesheet" href="/css/order/dataTime.css">
  </head>
  <body>
    <div class="orderTotalPage">
      <div class="order_tab">
        <div class="tabActive">一周内</div>
        <div>一个月内</div>
        <div>3个月内</div>
      </div>
      <div class="order_data">
        <div>开始时间</div>
        <div class="kinerDatePickerInput" id="kinerDatePickerInput1" title="" startYear="1970" default-val="2019-1-1">请选择开始时间</div>
      </div>
      <div class="order_data">
        <div>结束时间</div>
        <div class="kinerDatePickerInput" id="kinerDatePickerInput2" title="" startYear="1970" default-val="2019-1-1">请选择结束时间</div>
      </div>
      <button>
        <a href="totalResult.html">统计查询</a>
      </button>
    </div>
    <script src="http://www.jq22.com/jquery/jquery-1.10.2.js"></script>
    <script src="/js/dataTime/swiper.min.js"></script>
    <script src="/js/dataTime/kinerDatePicker.js"></script>
    <script src="/js/order/orderTotalQuery.js"></script>
    <script>
      /* 日期选择器 */
      $('#kinerDatePickerInput1').kinerDatePicker({
        clickMaskHide: true
      });
      $('#kinerDatePickerInput2').kinerDatePicker({
        clickMaskHide: true
      });
    </script>
  </body>
</html>