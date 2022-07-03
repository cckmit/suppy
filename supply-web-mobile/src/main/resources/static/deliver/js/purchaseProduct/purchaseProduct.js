$(function () {
    selectProductBySupplyOrgId();
})

var cartList = [];
var proIds = [];
var proNums = [];
var purchaseFormId = $(".purchaseFormId").val();
var supplyOrgId = $(".supplyOrgId").val();

function selectProductBySupplyOrgId() {
    $.ajax({
        type: "post",
        url: "/deliver/deliverProductList",
        data: {
            "purchaseFormId": purchaseFormId,
            "supplyOrgId": supplyOrgId
        },
        success: function (data) {
            console.log(data);
            cartList = data;
            $("#shop_cart_list").html(tmpl("tmpl_list", cartList))
        }
    });
}

/* 购物车选中 */
$('body').on('click', '.cart_list_item>div:first-child', function () {
    var dom = $(this).find('i')
    var arr = ''
    var isAll = 0
    if(dom.parent().parent().find('.cart_amount').text()=='0'){
            return false
    }
    if (dom.hasClass('iconyuan')) {
        dom.addClass('iconqueren').removeClass('iconyuan')
    } else {
        dom.addClass('iconyuan').removeClass('iconqueren')
    }
    $('.cart_list_item').each(function () {
        if ($(this).find('div:first-child i').hasClass('iconyuan')) {
            arr += '0'
        } else {
            arr += '1'
        }
    })
    if (arr.indexOf(isAll.toString()) > -1) {
        $('.all_choose').find('i').addClass('iconyuan').removeClass('iconqueren')
    } else {
        $('.all_choose').find('i').addClass('iconqueren').removeClass('iconyuan')
    }
})


/* 购物车全选 */
$('.all_choose').on('click', function () {
    var dom = $(this).find('i')
    var count = $(".cart_amount");
    var a = 0
    count.each(function(idnex,item){
        if($(item).text()==='0'){
                   a =1
                   return false
        }

    })
    // console.log(count)
    // if(count=='0'){
    //     return false
    // }

        if(a==1){
            return false
        }
    if (dom.hasClass('iconyuan')) {
        dom.addClass('iconqueren').removeClass('iconyuan')
        $('.cart_list_item').each(function () {
            $(this).find('div:first-child i').addClass('iconqueren').removeClass('iconyuan')
        })
    } else {
        dom.addClass('iconyuan').removeClass('iconqueren')
        $('.cart_list_item').each(function () {
            $(this).find('div:first-child i').addClass('iconyuan').removeClass('iconqueren')
        })
    }
})


/* 步进器 */
$('body').on('click', '.item_stepped span', function () {
    var sum = $(this).parent().parent().parent().find('.item_choose_num .sum').text()
    var index = parseInt($(this).attr('data-index'))
    var dom = parseInt($(this).siblings('.cart_amount').html())
    var num = index == 0 ? dom - 1 : dom + 1
    if(num>sum){
        layer.open({
            content: '不能大于剩余数量'
            ,skin: 'msg'
            ,time: 2 //2秒后自动关闭
        });
        return false
    }
    var num = index == 0 ? dom - 1 : dom + 1

    if (num < 0 && index == 0) {
        layer.open({
            content: '数量不能小于0'
            ,skin: 'msg'
            ,time: 2 //2秒后自动关闭
        });
        return false
    }

    $(this).siblings('.cart_amount').html(num)

})
$('body').on('click', '.cart_amount', function (e) {
    e.stopPropagation()
    var html = '<input type="text" placeholder="请输入数量" class="input">'
    var sum = $(this).parent().parent().parent().find('.item_choose_num .sum').text()
    that = $(this)
    layer.open({
        anim: 'scale'
        , content: html
        , btn: ['确认', '取消']
        , yes: function (index) {
            var reg = /^[0-9]*$/;
            if (!reg.test($(".input").val()) || $(".input").val() == '') {
                layer.open({
                    type: 1,
                    content: '请输入数量',
                    skin: 'msg',
                    time: 2
                })
            } else {

                if($('.input').val()==0){
                    that.text($('.input').val())
                    layer.close(index);
                }

                if($('.input').val()>sum){
                    layer.open({
                        type: 1,
                        content: '不能大于剩余数量',
                        skin: 'msg',
                        time: 2
                    })
                    return false
                }

                that.text($('.input').val())
                layer.close(index);

            }


        }
    });

})

/* 入库 */
$('.submit').on('click', function () {
    var tableList = []
    $('.shop_cart_list .cart_list_item').each(function (index) {
        if ($(this).find('div:first-child i').hasClass('iconqueren')) {
            var item = {
                name: cartList[index].name,
                ID: cartList[index].id,
                num: parseInt($(this).find('.cart_amount').text())
            }
            tableList.push(item);
            proIds.push(item.ID);
            proNums.push(item.num);
        }
    })
    $("#t_body").html(tmpl("tmpl_table", tableList))
    $('.mask').show()
})


$('.t_bottom div').on('click', function () {
    $('.mask').hide()
})


function productDeliver() {
    console.log(proIds);
    if (proIds.length == 0) {
        alert("请选择要发货的商品");
        return false;
    }
    $.ajax({
        type: "post",
        url: "/deliver/deliverProduct",
        data: {
            "purchaseFormId": purchaseFormId,
            "supplyOrgId": supplyOrgId,
            "ids": JSON.stringify(proIds),
            "nums": JSON.stringify(proNums)
        },
        success: function (data) {
            console.log(data.msg);
            if(data.status == 200){
                layer.open({
                    content: '发货成功'
                    ,btn: ['确定']
                    ,yes: function(){
                        window.location.href = '/deliver/purchaseFormInit';
                    }
                });
            }else {
                alert(data.msg);
            }
        }
    });
}


function cancelProcuct() {
    proIds = [];
    proNums = [];
}