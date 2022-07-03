var commodityList = [];
var order = "orderOne";

function selectProductAndStock(order, categoryId, name,prefecture) {
    $.ajax({
        type: "post",
        url: "/purchase/selectProductAndStock",
        data: {
            "order": order,
            "categoryId": categoryId,
            "name": name,
            "prefecture":prefecture
        },
        success: function (data) {
            commodityList = data;
            if(data.length){
                $('.nodata').remove()
            }else {
                $('body').append('<p class="nodata">暂无相关数据</p>')
            }
            getList();
        }
    });
}


$(function () {
    if(prefecture == "0"){
        selectProductAndStock(order, categoryId, null,1);
    }else{
        if (window.location.pathname =='/purchase/commodityList') {
            selectProductAndStock(order, categoryId, null,0);
        }
    }

})

/* tab选项卡切换 */
var a = 1
var b = 1

$('.price_sort').on('click', function () {
    if (a % 2) {
        $(this).children('.iconxiangshangjiantou-copy-copy-copy-copy').addClass('tab_active').siblings().removeClass('tab_active')
        a = 2;
        order = "orderFour";
        selectProductAndStock(order, categoryId, $(".search-input").val(),prefecture=="0"?1:0);
    } else {
        $(this).children('.iconxiangshangjiantou-copy-copy-copy').addClass('tab_active').siblings().removeClass('tab_active')
        a = 1;
        order = "orderFive";
        selectProductAndStock(order, categoryId, $(".search-input").val(),prefecture=="0"?1:0);
    }
    b = 1
    $(this).addClass('tab_active')
    $('.sales_sort').removeClass('tab_active')
    $('.sales_sort i').removeClass('tab_active')
})

$('.sales_sort').on('click', function () {
    if (b % 2) {
        $(this).children('.iconxiangshangjiantou-copy-copy-copy-copy').addClass('tab_active').siblings().removeClass('tab_active')
        b = 2
        order = "orderThree";
        selectProductAndStock(order, categoryId, $(".search-input").val(),prefecture=="0"?1:0);
    } else {
        $(this).children('.iconxiangshangjiantou-copy-copy-copy').addClass('tab_active').siblings().removeClass('tab_active')
        b = 1
        order = "orderTwo";
        selectProductAndStock(order, categoryId, $(".search-input").val(),prefecture=="0"?1:0);
    }
    a = 1
    $(this).addClass('tab_active')
    $('.price_sort').removeClass('tab_active')
    $('.price_sort i').removeClass('tab_active')
})

$('.synthesis_sort').on('click', function () {
    order = "orderOne";
    selectProductAndStock(order, categoryId, $(".search-input").val(),prefecture=="0"?1:0);
    $('.sales_sort').removeClass('tab_active')
    $('.sales_sort i').removeClass('tab_active')
    $('.price_sort').removeClass('tab_active')
    $('.price_sort i').removeClass('tab_active')
})

/* 筛选切换 */
// $('.commodity_top_sort div i').bind('click', function (e) {
//     e.stopPropagation()
//     $(this).addClass('tab_active').siblings().removeClass('tab_active')
//     $(this).parent().addClass('tab_active').siblings().removeClass('tab_active').find('i').removeClass('tab_active')
// })


/* 获取商品列表 */
function getList() {
    $("#commodity_list").html(tmpl("tmpl_list", commodityList));
}

$('body').on('webkitAnimationEnd', '.add_new_class', function () {
    $(this).remove()
});

/* 步进器 */
$('body').on('click', '.main_num div i', function (e) {
    e.stopPropagation()
    var index = parseInt($(this).attr('data-index'))
    var val = parseInt($(this).parent().siblings('.add_num').html())
    var amount = parseInt($('.shopcart_num span').html());
    var productId = $(this).parent().parent().attr("proId");
    var left = $(this).offset().left * 2 / 100;
    var top = ($(this).offset().top - $(window).scrollTop()) * 2 / 100;
    var newDom = $("<span class='add_new_class' style='left:" + left + "rem;top:" + top + "rem'></span>")
    that = $(this);
    if (index == 0) {
        if (val == 0) {
            return false
        }

        $.ajax({
            url: "/purchase/addToCart",
            type: 'POST',
            data: {
                "productId": productId,
                "count": -1,
            },
            success: function (data) {
                if (data.status == 200) {
                    if (amount > 0) {
                        $('.shopcart_num span').html(amount - 1)
                    }
                    that.parent().siblings('.add_num').html(val - 1)
                } else {
                    layer.open({
                        content: data.msg
                        , btn: '我知道了'
                    });
                }

            },
            error: function (xhr, type) {
                alert('网络异常');
            }
        });
    } else {
        $.ajax({
            url: "/purchase/addToCart",
            type: 'POST',
            data: {
                "productId": productId,
                "count": 1,
            },
            success: function (data) {
                if (data.status == 200) {
                    that.parent().siblings('.add_num').html(val + 1)
                    var amount = parseInt($('.shopcart_num span').html())
                    $('.shopcart_num span').html(amount + 1)
                    that.append(newDom);
                }

            },
            error: function (xhr, type) {
                alert('网络异常');
            }
        });


    }
})

/* 跳转到详情 */
$('body').on('click', '.commodity_list_item', function () {
    var proId = $(this).attr("proId");
    window.location.href = '/purchase/commodityDetail?proId=' + proId;

    sessionStorage.setItem("scroll-y", $(window).scrollTop());
})
var scrollY = sessionStorage.getItem("scroll-y");

if (scrollY != null) {
    $("html,body").animate({scrollTop:scrollY},2000);
}

$('body').on('click', '.main_num', function (e) {
    e.stopPropagation()
    var left = $(this).offset().left * 2 / 100;
    var top = ($(this).offset().top - $(window).scrollTop()) * 2 / 100;
    var newDom = $("<span class='add_new_class' style='left:" + left + "rem;top:" + top + "rem'></span>")
    var html = '<input type="text" placeholder="请输入数量" class="input">' //+
        // '<div class="iconDiv"><div><i class="iconfont icontubiao-3 iconSubtract" data-index="0"></i></div><div class="addNum">0</div><div><i class="iconfont iconjia iconAdd" data-index="1"></i></div></div>'
    that = $(this)
    layer.open({
        anim: 'scale'
        , content: html
        , btn: ['确认', '取消']
        , yes: function (index) {
            var reg = /^[0-9]*$/;
            var vle = $(".input").val();
            if (!reg.test(vle) || vle == '') {
                layer.open({
                    type: 1,
                    content: '输入有误',
                    skin: 'msg',
                    time: 2
                })
            } else {
                var store = eval(that.parent().find(".store_num").text());
                //var vle = $(".input").val();
                var evle = eval(vle);
                if(store<evle){
                    $(".input").val(store);
                    layer.open({
                        type: 1,
                        content: '输入数量过大',
                        skin: 'msg',
                        time: 2
                    })
                }else{
                    var productId = that.attr("proId");
                    //alert("productId="+productId+"store"+store);
                    $.ajax({
                        url: "/purchase/addToCart",
                        type: 'POST',
                        data: {
                            "productId": productId,
                            "count": evle,
                        },
                        success: function (data) {
                            if (data.status == 200) {
                                that.children('.add_num').text(evle)
                                var amount = parseInt($('.shopcart_num span').html())
                                $('.shopcart_num span').html(amount + parseInt(evle))
                                that.append(newDom);
                            }

                        },
                        error: function (xhr, type) {
                            alert('网络异常');
                        }
                    });
                    layer.close(index);
                }
            }


        }
    });

})

$('.category i').on('click', function () {
    $(this).parent().empty();
    $(".commodity_search ").addClass("none");
    categoryId = null;
    selectProductAndStock(order, categoryId, $(".search-input").val(),prefecture=="0"?1:0);
})


/* 跳转搜索页 */
$('#sousuo').on('click', function () {
    var name = $(".search-input").val();
    selectProductAndStock(order, categoryId, name,prefecture=="0"?1:0);
})


/*
$('body').on('click', '.iconjia', function () {
    var num = parseInt($(this).siblings('.add_num').text());
    var productId = $(this).parent().parent().attr("proId");

    that = $(this)

    $.ajax({
        url: "/purchase/addToCart",
        type: 'POST',
        data: {
            "productId": productId,
            "count": num + 1,
        },
        success: function (data) {
            if (data.status == 200) {
                /!*that.siblings('.add_num').text(num + 1);*!/
            }

        },
        error: function (xhr, type) {
            alert('网络异常');
        }
    });

})


$('body').on('click', '.icontubiao-3', function () {
    var num = parseInt($(this).siblings('.index_num_num').text());
    var amount = parseInt($('.shopcart_num span').html())
    if (num == 0) {
        return false
    }

    var productId = $(this).parent().attr("pId");

    that = $(this);
    $.ajax({
        url: "/purchase/addToCart",
        type: 'POST',
        data: {
            "productId": productId,
            "count": -1,
        },
        success: function (data) {
            if (data.status == 200) {
                if (amount > 0 && num > 0) {
                    $('.shopcart_num span').html(amount - 1)
                }
                that.siblings('.index_num_num').text(num - 1);
            }

        },
        error: function (xhr, type) {
            alert('网络异常');
        }
    });

})*/
