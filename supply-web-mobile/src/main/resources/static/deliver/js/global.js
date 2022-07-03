var bottomList = [
  {
    icon: 'iconshouye1',
    name: '首页',
    url: '/deliver/index',
    changeIcon: 'iconshouye'
  },
  {
    icon: 'icontubiaozhizuomoban-6',
    name: '订单',
    url: '/deliver/purchaseFormInit',
    changeIcon: 'icontubiaozhizuomoban-4'
  },
   {
        icon: 'icon-fahuodan-copy-copy',
        name: '发货单',
        url: '/deliver/deliverFormInit',
        changeIcon: 'icon-fahuodan-copy'
   },
  {
    icon: 'icontubiaozhizuomoban-1',
    name: '入库',
    url: '/deliver/productStockList',
    changeIcon: 'icontubiaozhizuomoban-'
  },
  {
    icon: 'icontubiaozhizuomoban-2',
    name: '商品',
    url: '/deliver/productManager',
    changeIcon: 'icontubiaozhizuomoban-3'
  }
]
var tabChangeIndex = 0
$(function() {
  getBottom()
})
/* 底部导航栏 */
function getBottom() {
  var index = $("#bottom_bar").attr('data-current')
  tabChangeIndex = index
  $("#bottom_bar").html(tmpl("tmpl_bottom", bottomList))
  $("#bottom_bar div").eq(index).addClass('bottom_bar_active')
  $("#bottom_bar div").eq(index).find('i').addClass(bottomList[index].changeIcon).removeClass(bottomList[index].icon)
}
/* 导航栏切换 */
$("#bottom_bar").on('click', 'div', function() {
  var index = $(this).attr('data-index')
  if (index == tabChangeIndex) {
      return false
  }
  window.location.href = bottomList[index].url
})