function caculator(){
    var checks = $('.chebox i')
    var carts = $('.cart_amount')
    var prices = $('.cart_price')
    var num = 0;
    var totalprice = 0;
    for(var i=0;i<carts.length;i++){
        if(checks[i].className=='iconfont iconqueren singlered'){
            num += parseInt(carts[i].value)
            totalprice+= parseInt(carts[i].value)*parseFloat(prices[i].innerText)
        }
    }

    document.getElementById('totalprice').innerHTML = '<span>合计：</span>￥'+totalprice.toFixed(2);
    document.getElementById('totalcategory').innerHTML = '共'+$('.cart_list_item').length+'种'+num+'件商品';
}

var check = $('.chebox i')
var nums = 0
var totalprice = 0;
for(var i=0;i<check.length;i++){
    check[i].index = i;
    check[i].onclick = function(e){
        if(this.className!='iconfont iconqueren singlered'){
            nums += parseInt(this.parentNode.parentNode.getElementsByClassName('cart_amount')[0].value)
            var num = this.parentNode.parentNode.getElementsByClassName('cart_amount')[0].value
            var price = this.parentNode.parentNode.getElementsByClassName('cart_price')[0].innerText

            totalprice += parseInt(num)*parseFloat(price)

            document.getElementById('totalcategory').innerHTML = '共'+$('.cart_list_item').length+'种'+nums+'件商品';
            document.getElementById('totalprice').innerHTML = '<span>合计：</span>￥'+totalprice.toFixed(2);
        }else {
            nums -= parseInt(this.parentNode.parentNode.getElementsByClassName('cart_amount')[0].value)
            var num = this.parentNode.parentNode.getElementsByClassName('cart_amount')[0].value
            var price = this.parentNode.parentNode.getElementsByClassName('cart_price')[0].innerText

            totalprice -= parseInt(num)*parseFloat(price)

            document.getElementById('totalcategory').innerHTML = '共'+$('.cart_list_item').length+'种'+nums+'件商品';
            document.getElementById('totalprice').innerHTML = '<span>合计：</span>￥'+totalprice.toFixed(2);
        }
    }
}

/* 点击顶部删除按钮 */
function deleteCart (status) {
  /* @status: 0-点击删除 1-点击完成 */
  switch (status) {
    case 0:
      $('.shop_cart_top span:first-child').hide().siblings().show()
      $('.chebox').hide()
      break
    case 1:
      $('.shop_cart_top span:first-child').show().siblings().hide()
      $('.chebox').show()
      caculator()
      break
  }
  changeItem(status)
}
$(".cart_amount").keyup(function(){
    var val = $(this).val()
    if(val>99999){
        // layer.open({
        //     type: 1,
        //     content: '不能大于1000',
        //     skin: 'msg',
        //     time: 2
        // })
        $(this).val('99999')
    }
  var checkValue = $(this).val();
  if(!(/^[\d\s]+$/.test(checkValue))){
    if(checkValue.length == 0){

    }else{
      layer.open({
        type: 1,
        content: '只能输入数字',
        skin: 'msg',
        time: 2
      })
      $(this).val('1')
    }
  }

})

$(".cart_amount").change(function(){

    var productid = $(this).parentsUntil('.cart_list_item').find('.cartid')[0].innerText;
    console.log(productid);
    $.ajax({
        url: "modifyCarts",
        method: 'post',
        data: {"productid": productid,"count":$(this).val()},
        success: function (data) {
            console.log(data);
            if(data.status==200){
                caculator()
            }else{
                layer.open({
                    content: data.msg
                    , btn: '我知道了'
                });
            }

        }
    })
})


/* 改变列表样式 */
function changeItem (status) {
  var divWidth = status == 0 ? '4.72rem' : '5.4rem'
  var text = status == 0 ? '删除' : '下单'
  if (status == 0) {
    $('.cart_list_item>div:first-child').show()
    $('.all_choose').show().siblings('.total_price').hide()
    $('.all_chooses').hide()
  } else {
    $('.cart_list_item>div:first-child').hide()
    $('.all_choose').hide().siblings('.total_price').show()
    $('.all_chooses').show()
  }
  $('.cart_list_item>div:nth-child(4)').css("width",divWidth)
  $('.submit').html(text)
}
/* 购物车选中 */
$('body').on('click', '.cart_list_item>div:first-child', function () {
  var dom = $(this).find('i')
  var arr = ''
  var isAll = 0
  if (dom.hasClass('iconquan')) {
    dom.addClass('iconqueren').addClass('singlered').removeClass('iconquan')
  } else {
    dom.addClass('iconquan').removeClass('singlered').removeClass('iconqueren')
  }
  $('.cart_list_item').each(function () {
    if ($(this).find('div:first-child i').hasClass('iconquan')) {
      arr += '0'
    } else {
      arr += '1'
    }
  })
  if (arr.indexOf(isAll.toString()) > -1) {
    $('.all_choose').find('i').addClass('iconquan').removeClass('iconqueren')
  } else {
    $('.all_choose').find('i').addClass('iconqueren').removeClass('iconquan')
  }
})

/* 购物车单选中 */
$('body').on('click', '.cart_list_item>div:nth-child(2)', function () {
    var dom = $(this).find('i')
    var arr = ''
    var isAll = 0
    if (dom.hasClass('iconquan')) {
        dom.addClass('iconqueren').addClass('singlered').removeClass('iconquan')
    } else {
        dom.addClass('iconquan').removeClass('singlered').removeClass('iconqueren')
    }
    $('.cart_list_item').each(function () {
        if ($(this).find('div:nth-child(2) i').hasClass('iconquan')) {
            arr += '0'
        } else {
            arr += '1'
        }
    })
    if (arr.indexOf(isAll.toString()) > -1) {
        $('.all_chooses').find('i').addClass('iconquan').removeClass('iconqueren')
    } else {
        $('.all_chooses').find('i').addClass('iconqueren').removeClass('iconquan')
    }
    caculator()
})
$('.all_chooses').on('click',function () {
    var dom = $(this).find('i')
    if (dom.hasClass('iconquan')) {
        dom.addClass('iconqueren').removeClass('iconquan')
        $('.cart_list_item').each(function () {
            $(this).find('.chebox i').addClass('iconqueren').addClass("singlered").removeClass('iconquan')
        })

        caculator()
    } else {
        dom.addClass('iconquan').removeClass('iconqueren')
        $('.cart_list_item').each(function () {
            $(this).find('.chebox i').addClass('iconquan').removeClass('iconqueren').removeClass("singlered")
        })
        caculator()
    }
})
/* 购物车全选 */
$('.all_choose').on('click', function () {
  var dom = $(this).find('i')
  if (dom.hasClass('iconquan')) {
    dom.addClass('iconqueren').removeClass('iconquan')
    $('.cart_list_item').each(function () {
      $(this).find('div:first-child i').addClass('iconqueren').addClass("singlered").removeClass('iconquan')
    })
  } else {
    dom.addClass('iconquan').removeClass('iconqueren')
    $('.cart_list_item').each(function () {
      $(this).find('div:first-child i').addClass('iconquan').removeClass('iconqueren').removeClass("singlered")
    })
  }
})
/* 步进器 */
$('body').on('click', '.item_stepped span', function () {
    // var index = parseInt(that.attr('data-index'))
    // alert(index);
    // var dom = parseInt(that.siblings('.cart_amount').val())
    //
    // var num = index == 0 ? dom - 1 : dom + 1
    //
    // if (num < 1 && index == 0) {
    //     return false
    // }
    //
    // that.siblings('.cart_amount').val(num)
    // var val = that.siblings('.cart_amount').val()
    //
    // if(val>99999){
    //     that.siblings().val('99999')
    //
    // }
  caculator();
})
