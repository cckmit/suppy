var bottomList = [
    {
        icon: 'icontubiao-10',
        name: '首页',
        url: '/purchase/index',
        class: ''
    },
    {
        icon: 'icontubiao-12',
        name: '商品',
        url: '/purchase/commodityClassification',
        class: ''
    },
    // {
    //     icon: 'icontubiao-9',
    //     name: '订单',
    //     url: '/purchase/purchaseFormInit',
    //     class: ''
    // },
    {
        icon: 'icontubiao-13',
        name: '购物车',
        url: '/purchase/myCart',
        class: ''
    },
    {
        icon: 'icontubiao-',
        name: '我的',
        url: '/purchase/myinfo',
        class: ''
    }
]
var tabIndex = 0
$(function () {
    getBottom()
})

/* 底部导航栏 */
function getBottom() {
    var index = $("#bottom_bar").attr('data-current')
    console.log("index=" + index)
    tabIndex = index
    bottomList[index].class = 'bottom_bar_active'
    $("#bottom_bar").html(tmpl("tmpl_bottom", bottomList))
}

/* 导航栏切换 */
$("#bottom_bar").on('click', 'div', function () {
    var index = $(this).attr('data-index')
    if (index == tabIndex) {
        return false
    }
    window.location.href = bottomList[index].url
})