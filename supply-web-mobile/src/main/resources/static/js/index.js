var indexType = [
    {
        title: '新品上架',
        icon: 'icontubiao-18',
        background: '#F6B75E'
    },
    {
        // title: '热卖推荐',
        title: '活动专区',
        icon: 'icontubiao-17',
        background: '#FF807D'
    },
    {
        title: '休闲零售',
        icon: 'icontubiao-19',
        background: '#A58FFB'
    },
    {
        title: '更多',
        icon: 'icontubiao-20',
        background: '#65D0C7'
    }
]
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
    },
    {
        title: '休闲孕妇零食坚果炒货干果无漂白',
        image: 'image/img.png',
        skuweight: 150,
        sku_num: 90,
        price: 40,
        num: 2
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
var fullList = [
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
/* 添加分类 */
$(function () {
    // getType()
})

/* 获取分类列表 */
function getType() {
    var dom = $('.index_type')
    dom.empty()
    for (var i in indexType) {
        dom.append('<div>' +
            '<span style="background: ' + indexType[i].background + '">' +
            '<i class="iconfont ' + indexType[i].icon + '"></i></span>' +
            '<p>' + indexType[i].title + '</p>' +
            '</div>')
    }
}


/* 步进器 */
$('body').on('click', '.index_num_add', function () {
    var num = parseInt($(this).siblings('.index_num_num').text());
    var productId = $(this).parent().attr("pId");

    that = $(this)
    var left = $(this).offset().left * 2 / 100;
    var top = ($(this).offset().top - $(window).scrollTop()) * 2 / 100;
    var newDom = $("<span class='add_new_class' style='left:"+ left +"rem;top:"+ top +"rem'></span>")
    var amount = parseInt($('.shopcart_num span').html())
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
            }else{
                layer.open({
                    content: data.msg
                    ,btn: '我知道了'
                });
            }

        },
        error: function (xhr, type) {
            alert('网络异常');
        }
    });
})

$('body').on('webkitAnimationEnd', '.add_new_class', function(){
    $(this).remove()
});

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
            }else{
                layer.open({
                    content: data.msg
                    ,btn: '我知道了'
                });
            }

        },
        error: function (xhr, type) {
            alert('网络异常');
        }
    });

})
