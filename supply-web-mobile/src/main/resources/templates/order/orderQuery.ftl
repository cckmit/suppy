<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0,user-scalable=no"/>
    <title>易订货-订单查询</title>
    <link rel="stylesheet" href="/css/global.css">
    <link rel="stylesheet" href="/css/order/orderQuery.css">
    <link rel="stylesheet" href="/css/order/dataTime.css">
</head>
<body>
<div class="orderQueryPage">
    <div class="order_query_data">
        <div>开始时间</div>
        <div class="kinerDatePickerInput" id="kinerDatePickerInput1" title="" startYear="1970" default-val="2019-1-1">
            请选择
        </div>
    </div>
    <div class="order_query_data">
        <div>结束时间</div>
        <div class="kinerDatePickerInput" id="kinerDatePickerInput2" title="" startYear="1970" default-val="2019-1-1">
            请选择
        </div>
    </div>
    <div class="order_query_data">
        <div>订货状态</div>
        <div>全部</div>
    </div>
    <button class="orderQuery">
        订单查询
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


    var status = 0;
    $('.orderQuery').on('click', function () {
        var startDate = $("#kinerDatePickerInput1").html();
        var endDate = $("#kinerDatePickerInput2").html();
        var status = 0;
        window.location.href = "/purchase/purchaseFormInit?startDate="+startDate+"&endDate="+endDate+"&status="+status;
    })
</script>
</body>
</html>