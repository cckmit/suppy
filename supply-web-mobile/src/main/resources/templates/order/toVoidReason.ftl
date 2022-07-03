<!DOCTYPE html>
<html>
  <head>
      <meta charset="utf-8">
      <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0,user-scalable=no" />
      <title>易订货-订单详情</title>
      <link rel="stylesheet" href="/css/global.css">
      <link rel="stylesheet" href="/css/order/orderListDetail.css">
  </head>
  <body>
    <div class="toVoidReasonPage">
      <textarea placeholder="请输入作废原因" id="remark"></textarea>
      <button id="sure_reason" onclick="invalidOrder()">确定</button>
      <div class="toast">作废原因不能少于4个字</div>
    </div>
    <script>
        function invalidOrder(){
            var remark = $("#remark").val();
            $.ajax({
                url:"invalidOrder",
                method:"post",
                data:{"formId":'${formid!}',"remark":remark},
                success:function (data) {
                    console.log(data)
                    if(data.status=200){
                        window.location.href='index';
                    }
                }
            })
        }
    </script>
    <script src="http://www.jq22.com/jquery/jquery-1.10.2.js"></script>
    <script src="/js/order/orderListDetail.js"></script>
  </body>
</html>