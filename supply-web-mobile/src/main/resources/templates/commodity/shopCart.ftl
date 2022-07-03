<!DOCTYPE html>
<html>
  <head>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0,user-scalable=no" />
    <title>易订货-购物车</title>
    <link rel="stylesheet" href="/css/global.css">
    <link rel="stylesheet" href="/css/commodity/shopCart.css">
      <link rel="stylesheet" href="/css/layer.css">
    <link rel="stylesheet" href="http://at.alicdn.com/t/font_1091326_sg61r64lenh.css">
  </head>
  <body>
    <div class="shop_cart_page">
      <div class="shop_cart_top">
        <span onclick="deleteCart(0)"><i class="iconfont icontubiao-30"></i></span>
        <span onclick="deleteCart(1)">完成</span>
      </div>
      <div class="shop_cart_list" id="shop_cart_list"></div>
      <#list cartList as cart>
        <div class="cart_list_item">
            <div><i class="iconfont iconquan"></i></div>
            <div style="background-image:url(${cart.IMAGE})">
                <div>1种货品</div>
            </div>
            <div>
                <span style="display: none" class="cartid">${cart.product_id}</span>
                <span style="display: none" class="cid">${cart.id}</span>
                <div class="item_title">${cart.name}</div>
                <div class="item_code_num">编号：${cart.BARCODE}</div>
                <div class="item_choose_num">
                    <div>￥<span class="cart_price">${cart.product_price}</span>/袋</div>
                    <div class="item_stepped">
                        <span data-index="0"><i class="iconfont icontubiao-3"></i></span>
                        <span class="cart_amount">${cart.product_quality}</span>
                        <span data-index="1"><i class="iconfont iconjia"></i></span>
                    </div>
                </div>
            </div>
        </div>
      </#list>
      <div class="shop_cart_bottom">
        <div class="all_choose">
          <i class="iconfont iconquan"></i> 全选
        </div>
        <div class="submit">下单</div>
        <div class="total_price">
          <div id="totalprice"><span>合计：</span>￥</div>
          <div id="totalcategory">共种件商品</div>
        </div>
      </div>
    </div>
    <#--<script src="https://cdn.bootcss.com/blueimp-JavaScript-Templates/3.11.0/js/tmpl.min.js"></script>-->
    <script src="http://www.jq22.com/jquery/jquery-1.10.2.js"></script>
    <script src="/js/commodity/shopCart.js"></script>
    <script src="/js/layer.js"></script>
    <script>
        var purchaserId = parseInt('${purchaserId!}')
        caculator();

        //减
        $(".icontubiao-3").click(function () {
            //减到0时
            var count =  $(this).parentsUntil('.cart_list_item').find('.cart_amount')[0].innerText;
            var productid = $(this).parentsUntil('.cart_list_item').find('.cartid')[0].innerText;
            var clickitem = $(this)
            if(count==1){
                //询问框
                layer.open({
                    content: '确定要删除该商品么？'
                    ,btn: ['确定', '取消']
                    ,yes: function(index){
                        //删除商品
                        clickitem.parentsUntil('.cart_list_item').parent().remove()
                        //调用删除接口
                        $.ajax({
                            url:"removeCarts",
                            method: 'post',
                            data:{"purchaseId":purchaserId,"productIds":productid},
                            success:function(data){
                                console.log(data);
                                caculator()
                            }
                        })
                        layer.close(index);
                        return;
                    }
                });
            }
            $.ajax({
                url:"addToCart",
                method: 'post',
                data:{"productId":productid,"count":-1},
                success:function(data){
                    console.log(data);
                    if(data.status!=200){
                        layer.open({
                            content: data.msg
                            ,btn: '我知道了'
                        });
                    }else {
                        caculator()
                    }
                }
            })
        })

        //加
        $('.iconjia').click(function () {
            var productid = $(this).parentsUntil('.cart_list_item').find('.cartid')[0].innerText;
            $.ajax({
                url:"addToCart",
                method: 'post',
                data:{"productId":productid,"count":1},
                success:function(data){
                    if(data.status!=200){
                        layer.open({
                            content: data.msg
                            ,btn: '我知道了'
                        });
                    }else {
                        caculator()
                    }
                }
            })
        })

        
        $(".submit").click(function () {
            if(this.innerText=='删除'){
                //后台删除购物车数据
                var productIds = ''
                $('.iconqueren').each(function () {
                    if($(this).parent()[0].innerText!='全选') {
                        productIds +=$(this).parent().parent().find('.cartid')[0].innerText+',';
                    }
                })
                if(productIds!=''){
                    productIds = productIds.substr(0,productIds.length-1);
                }
                $.ajax({
                    url:"removeCarts",
                    method: 'post',
                    data:{"purchaseId":purchaserId,"productIds":productIds},
                    success:function(data){
                        console.log(data);
                    }
                })
                //删除选中框
                $('.singlered').parentsUntil('.cart_list_item').parent().remove()
            }else{
                //下单
                var productIds = ''
                $('.cart_list_item').each(function () {
                    productIds +=$(this).find('.cid')[0].innerText+',';
                })
                if(productIds!=''){
                    productIds = productIds.substr(0,productIds.length-1);
                }
                $.ajax({
                    url:"cartToForm",
                    method: 'post',
                    data:{"purchaseId":purchaserId,"ids":productIds},
                    success:function(data){
                        console.log(data);
                        if(data.status==200) {
                            window.location.href = "purchaseDetail?status=" + data.status + "&purchaseFormId=" + data.data
                        }else{
                            layer.open({
                                content: data.msg
                                ,btn: '我知道了'
                            });
                        }
                    }
                })
            }
        })

    </script>
  </body>
</html>