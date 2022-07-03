/* 监听动画结束 */
$('body').on('webkitAnimationEnd', '.add_new_class', function () {
    $(this).remove()
});
$(function () {
    //创建MeScroll对象,内部已默认开启下拉刷新,自动执行up.callback,重置列表数据;
    var mescroll = new MeScroll("mescroll", {
        up: {
            clearEmptyId: "dataList", //1.下拉刷新时会自动先清空此列表,再加入数据; 2.无任何数据时会在此列表自动提示空
            callback: getListData, //上拉回调,此处可简写; 相当于 callback: function (page) { getListData(page); }
        }
    });

    /*联网加载列表数据  page = {num:1, size:10}; num:当前页 从1开始, size:每页数据条数 */
    function getListData(page) {
        //联网加载数据
        console.log("page.num==" + page.num);
        getListDataFromNet(page.num, page.size, function (data) {
            //联网成功的回调,隐藏下拉刷新和上拉加载的状态;
            mescroll.endSuccess(data.length);//传参:数据的总数; mescroll会自动判断列表如果无任何数据,则提示空;列表无下一页数据,则提示无更多数据;
            //设置列表数据,因为配置了emptyClearId,第一页会清空dataList的数据,所以setListData应该写在最后;
            setListData(data);
        }, function () {
            //联网失败的回调,隐藏下拉刷新和上拉加载的状态;
            mescroll.endErr();
        });
    }

    /*设置列表数据*/
    function setListData(data) {
        var listDom = document.getElementById("dataList");
        for (var i = 0; i < data.length; i++) {
            var pd = data[i];

            var str = '\<div class="list_item_image" ' +
                'style="background-image:url(' + pd.image + ')" onclick="productDetail(' + pd.id + ')"></div>\
                              <div class="list_item_main">\
                                <div class="list_item_title">' + pd.name + '</div>\
                                <div class="list_item_sku">\
                                  <span>库存' + pd.quantity + pd.unit + '</span>\
                                </div>\
                                <div class="list_item_price">\
                                  <div>' + pd.purchasePrice + '<span>/' + pd.unit + '</span></div>\
                                  <div class="num" pId="' + pd.id + '">\
                                    <div class="num_reduce"><i class="iconfont icontubiao-3"></i></div>\
                                    <div class="num_num">0</div>\
                                    <div class="num_add"><i class="iconfont iconjia"></i></div>\
                                  </div>\
                                </div>\
                              </div>\
                              ';
            var oDiv = document.createElement('div')
            oDiv.className = 'list_item'
            oDiv.innerHTML = str;
            listDom.appendChild(oDiv);
        }
    }

    /*联网加载列表数据*/
    function getListDataFromNet(pageNum, pageSize, successCallback, errorCallback) {
        //延时一秒,模拟联网
        $.post("/purchase/selectProdcut", {
            "type": type,
            "pageIndex": pageNum,
            "pageSize": pageSize
        }, function (data) {
            successCallback(data);
        })
    }
});
/* 步进器 */
$('body').on('click', '.num_add', function () {
    console.log("增加")
    var num = parseInt($(this).siblings('.num_num').text());
    var productId = $(this).parent().attr("pId");

    that = $(this)
    var left = $(this).offset().left * 2 / 100;
    var top = ($(this).offset().top - $(window).scrollTop()) * 2 / 100;
    var newDom = $("<span class='add_new_class' style='left:" + left + "rem;top:" + top + "rem'></span>")
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
                that.siblings('.num_num').text(num + 1);
            } else {
                alert(data.msg)
            }

        },
        error: function (xhr, type) {
            alert('网络异常');
        }
    });

})

$('body').on('click', '.num_reduce', function () {
    console.log("减少")
    var num = parseInt($(this).siblings('.num_num').text());
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
                that.siblings('.num_num').text(num - 1);
            } else {
                alert(data.msg)
            }
        },
        error: function (xhr, type) {
            alert('网络异常');
        }
    });
})

function productDetail(id) {
    window.location.href = "commodityDetail?proId=" + id;
}