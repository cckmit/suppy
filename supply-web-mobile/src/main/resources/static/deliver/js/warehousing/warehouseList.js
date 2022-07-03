$(function () {
    selectProductBySupplyOrgId();
})

var cartList = [];
var proIds = [];
var proNums = [];
var pageNo = 1;
var pageSize = 1000;

function selectProductBySupplyOrgId() {
    $("#shop_cart_list").html("");
    var content = $(".search-input").val();
    $.ajax({
        type: "post",
        url: "/deliver/selectProductBySupplyOrgId",
        data: {
            "content": content,
            "pageNo": pageNo,
            "pageSize": pageSize
        }, success: function (data) {
            console.log(data);
            cartList = data.list;
            $("#shop_cart_list").html(tmpl("tmpl_list", data.list))
        }
    });
}

/* 购物车选中 */
$('body').on('click', '.cart_list_item>div:first-child', function () {
    var dom = $(this).find('i')
    var arr = ''
    var isAll = 0
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
    var index = parseInt($(this).attr('data-index'))
    var dom = parseInt($(this).siblings('.cart_amount').html())
    var num = index == 0 ? dom - 1 : dom + 1
    if (num < 1 && index == 0) {
        return false
    }
    $(this).siblings('.cart_amount').html(num)
})

/* 入库 */
$('.submit').on('click', function () {
    var tableList = []
    $('.shop_cart_list .cart_list_item').each(function (index) {
        if ($(this).find('div:first-child i').hasClass('iconqueren')) {
            var item = {
                name: cartList[index].name,
                ID: cartList[index].id,
                num: $(this).find('.cart_amount').html()
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

function addRepository() {
    console.log(proIds);
    if (proIds.length == 0) {
        alert("请选择要入库的商品");
        return false;
    }

    $.ajax({
        type: "post",
        url: "/deliver/addProductRepository",
        data: {
            "ids": proIds,
            "nums": proNums
        },
        success: function (data) {
            alert(data.msg);
            window.location.reload();
        }
    });
}

function cancelAddRepository() {
    proIds = [];
    proNums = [];
}

$('body').on('click', '.cart_amount', function (e) {
    e.stopPropagation()
    var html = '<input type="text" placeholder="请输入数量" class="input">'
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
                that.text($('.input').val())
                layer.close(index);
            }


        }
    });

})

$('.search-input').bind('keyup', function (event) {
    var content = $(this).val();
    if (event.keyCode == "13") {
        selectProductBySupplyOrgId();
    }
});