<!DOCTYPE html>
<html>
  <head>
      <meta charset="utf-8">
      <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0,user-scalable=no" />
      <title>易订货-订单详情</title>
      <link rel="stylesheet" href="/css/global.css">
      <link rel="stylesheet" href="/css/layer.css">
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
            //询问框
            layer.open({
                content: '您确定要作废本订货单么？'
                ,btn: ['确定', '不要']
                ,yes: function(index){
                    $.ajax({
                        url:"invalidOrder",
                        method:"post",
                        data:{"formId":'${formid!}',"remark":remark},
                        success:function (data) {
                            //信息框
                            layer.open({
                                content: data.msg
                                ,btn: '我知道了'
                                ,yes: function (index) {
                                    if(data.status=200){
                                        window.location.href='index';
                                    }
                                }
                            });

                        }
                    })
                    layer.close(index);
                }
            });
        }
    </script>
    <script src="/js/jquery.min.js"></script>
    <script src="/js/layer.js"></script>
    <script src="/js/order/orderListDetail.js"></script>
  </body>
</html>