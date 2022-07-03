var actList = [
    {
        title: '休闲孕妇零食坚果炒货干果无漂白',
        image: 'image/img.png',
        skuweight: 200,
        sku_num: 90,
        price: 36,
        num: 0
    },
    {
        title: '休闲孕妇零食坚果炒货干果无漂白',
        image: 'image/img.png',
        skuweight: 150,
        sku_num: 90,
        price: 40,
        num: 2
    }
]

$('.index-item1 .item').each(function (index, item) {
    var str = $(item).find('.text').text().split(".")[0];
    var str1 = $(item).find('.text').text().split(".")[1];
    $(item).find('.text').text('').append('<span>' + str + '</span>.' + str1)

})

$(".find_nav_list").css("left", 0);
var fl_w = $(".find_nav_list").width();
var flb_w = $(".find_nav_left").width();

$(".find_nav_list li").each(function () {
    $(".find_nav_list li").eq(0).addClass("find_nav_cur").siblings().removeClass("find_nav_cur");
});
var nav_w = $(".find_nav_list li").first().width();

//点击分类
$(".find_nav_list li").on('click', function (event) {

    event.preventDefault();
    var index = $(this).index()
    $(this).addClass("find_nav_cur").siblings().removeClass("find_nav_cur");
    $('.content .index_list').eq(index).show().siblings().hide()
    index += 1;
    if ($('.find_nav_list li').length > 5) {
        nav_w = $(this).width();
        var fn_w = ($(".find_nav").width() - nav_w) / 2;
        var fnl_l;
        var fnl_x = parseInt($(this).position().left);
        if (fnl_x <= fn_w) {
            fnl_l = 0;
        } else if (fn_w - fnl_x <= flb_w - fl_w) {
            fnl_l = flb_w - fl_w;
        } else {
            fnl_l = fn_w - fnl_x;
        }

        $(".find_nav_list").animate({
            "left": fnl_l
        }, 300);
    }

    // if (index > 2) {
    //     var id = $(this).data("id");
    //     getData("data" + index, id + 3);
    // } else {
    //     getData("data" + index, index);
    // }
    var id = $(this).attr("data-id");
    var type = $(this).attr("datavalue");
    var data = id.substring(0,2);
    if(data=="ev"){
        var dataid = id.substring(2);
        getDataEv("dataEv" + dataid, dataid);
    }else{
        getData("data" + id, id,type);
    }
});



function getData(obj,id,type) {
    $.post("/purchase/selectProdcutSp", {
        "id":id,
        "type": type,
        "pageIndex": 1,
        "pageSize": 1000
    }, function (data) {
        $('.nodata').remove()
        if(data.length){
            $('.nodata').remove()
        }else {
            $('.content').append('<p class="nodata">暂无相关数据</p>')
        }
        setData(obj, data);
    })
}

function setData(obj, data) {
    //console.log(obj)
    //console.log(data)
    $("#" + obj).html(tmpl("tmpl_list", data))
    //$("#" + obj).html(tmpl("tmpl_list", data))
}

function getDataEv(obj, type) {
    $.post("/purchase/selectProductByEv", {
        "type": type,
        "pageIndex": 1,
        "pageSize": 1000
    }, function (data) {
        $('.nodata').remove()
        if(data.length){
            $('.nodata').remove()
        }else {
            $('.content').append('<p class="nodata">暂无相关数据</p>')
        }
        setDataEv(obj, data);
    })
}

function setDataEv(obj, data) {
    console.log(obj)
    console.log(data)
    $("#" + obj).html(tmpl("tmpl_list", data))
    //$("#" + obj).html(tmpl("tmpl_list", data))
}

/* 步进器 */
$('body').on('click', '.index_num_add', function (e) {
    e.stopPropagation()
    var num = parseInt($(this).siblings('.index_num_num').text());
    var productId = $(this).parent().attr("pId");

    that = $(this)
    var left = $(this).offset().left * 2 / 100;
    var top = ($(this).offset().top - $(window).scrollTop()) * 2 / 100;
    var newDom = $("<span class='add_new_class' style='left:" + left + "rem;top:" + top + "rem'></span>")
    var amount = parseInt($('.shopcart_num span').html())
    var shopcart_num =  amount + 1

    $.ajax({
        url: "/purchase/addToCart",
        type: 'POST',
        data: {
            "productId": productId,
            "count": 1,
        },
        success: function (data) {
            if (data.status == 200) {
                that.append(newDom)
                $('.shopcart_num span').html(amount + 1)
                that.siblings('.index_num_num').text(num + 1);
                $('.flag').remove();
                $('#bottom_bar div:eq(2)').append('<div class="flag">'+ shopcart_num +'</div>');
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
})

$('body').on('click', '.index_num_reduce', function () {
    var num = parseInt($(this).siblings('.index_num_num').text());
    var amount = parseInt($('.shopcart_num span').html());

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

})
$(window).scroll(function () {
    var top = $(".index_swiper").offset().top;
    var scrollTop = $(window).scrollTop();
    if (scrollTop > top) {
        // $(".search-wrap").addClass("active");
    } else {
        // $(".search-wrap").removeClass("active");
    }
});
$(".find_nav_list").on('touchstart', function (e) {
    var touch1 = e.originalEvent.targetTouches[0];
    x1 = touch1.pageX;
    y1 = touch1.pageY;
    ty_left = parseInt($(this).css("left"));
});
$(".find_nav_list").on('touchmove', function (e) {
    var touch2 = e.originalEvent.targetTouches[0];
    var x2 = touch2.pageX;
    var y2 = touch2.pageY;
    if (ty_left + x2 - x1 >= 0) {
        $(this).css("left", 0);
    } else if (ty_left + x2 - x1 <= flb_w - fl_w) {
        $(this).css("left", flb_w - fl_w);
    } else {
        $(this).css("left", ty_left + x2 - x1);
    }
    if (Math.abs(y2 - y1) > 0) {
        e.preventDefault();
    }
});

var scrollDiv = $(".autoscroll"),
    $ul = scrollDiv.find("ul"),
    $li = scrollDiv.find("li"),
    $length = $li.length,
    $liHeight = $li.height(),
    num = 0;

if ($length > 1) {
    $ul.append($li.eq(0).clone());
    setInterval(
        function () {
            num++;
            $ul.addClass("animate").css("-webkit-transform", "translateY(-" + $liHeight * (num) + "px)");
            setTimeout(
                function () {
                    if (num == $length) {
                        $ul.removeClass("animate").css("-webkit-transform", "translateY(0)");
                        num = 0;
                    }
                }, 300);
        }, 2000);
}


/* 监听动画结束 */
$('body').on('webkitAnimationEnd', '.add_new_class', function () {
    $(this).remove()
});