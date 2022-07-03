/* 加入购物车 */
$('.introduce_bottom').on('click', function () {
    $('.mask').show()
})
/* 步进器 */
$('.stepper span').on('click', function (e) {
    e.stopPropagation()
    var index = $(this).attr('data-index')
    var num = parseInt($('.stepper .amount').val())
    switch (Number(index)) {
        case 0:
            if (num <= productLimit) {
                return false
            }
            $('.stepper .amount').val(num - 1)
            break
        case 1:
            $('.stepper .amount').val(num + 1)
            break
    }
})

$('.stepper').on('click', function (e) {
    e.stopPropagation()

})
$(".amount").keyup(function(){
    var val = $(this).val();

    if(val>1000){
        $('.amount').val('1000')
    }
    /*if(val < productLimit){
         $('.amount').val(productLimit)
    }*/
    if(!(/^[0-9]+$/.test($(this).val()))){
        layer.open({
            type: 1,
            content: '只能输入数字',
            skin: 'msg',
            time: 2
        })
        $('.amount').val('')
    }

})
/*$(".amount").keydown(function(){
    var val = $(this).val();

     if(val < productLimit){
       $('.amount').val(productLimit)
    }
})*/

/* 关闭弹窗 */
function closePopup() {
    $('.mask').hide()
}

var oldCount = 0;
var changeCount = 0;

/*加入购物车*/
function addToCart(id) {
    var count = $("#count").val();
    if (count == oldCount) {
        return;
    }

    changeCount = count - oldCount;
    console.log(changeCount)
    oldCount = count;

    if(count < productLimit){
        layer.open({content: "少于起购数量不能添加购物车", btn: '确定'});
        return;
    }

    var model ="";
    var skuList = $('.sku_list').children()
    if(skuList.length >0){
        for (var i=0;i<skuList.length;i++) {
            if ($(skuList[i]).hasClass('active')) {
                model = $(skuList[i]).data('id')
                break;
            }
        }
    }

    console.log(model,skuList)
    $.ajax({
        type: "post",
        url: "/purchase/addToCart",
        data: {
            "productId": id,
            "count": changeCount,
            "model":model
        },
        success: function (data) {
            if (data.status != 200) {
                layer.open({
                    content: data.msg
                    , btn: '我知道了'
                });
            } else {
                var productCoupon = $("#productCoupon").html();
                layer.open({content: "添加成功", btn: '确定'});
                addCartStyle(changeCount);
            }
        }
    });
}

function addCartStyle(count) {
    var amount = parseInt($('#productCoupon').html())
    $('#productCoupon').html(amount + count)
}