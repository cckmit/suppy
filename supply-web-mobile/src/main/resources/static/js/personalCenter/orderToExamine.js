const orderList = [
  {
    pay_status: 0,
    title: '三梦食材',
    order_num: 'DH-O-20190308-421580',
    num: 1,
    date: '03-08 21:48',
    price: 685.45
  },
  {
    pay_status: 1,
    title: '三梦食材',
    order_num: 'DH-O-20190308-421580',
    num: 1,
    date: '03-08 21:48',
    price: 569.69
  },
  {
    pay_status: 0,
    title: '三梦食材',
    order_num: 'DH-O-20190308-421580',
    num: 1,
    date: '03-08 21:48',
    price: 685.45
  },
  {
    pay_status: 0,
    title: '三梦食材',
    order_num: 'DH-O-20190308-421580',
    num: 1,
    date: '03-08 21:48',
    price: 569.69
  }
]
$(function () {
  getList()
})
/* 获取列表 */
function getList () {
  $("#list").html(tmpl("tmpl_list", orderList))
}
/* 跳转详情 */
// $('.order_examine_list').on('click', '.order_examine_item', function () {
//   window.location.href = 'orderDetails.html'
// })