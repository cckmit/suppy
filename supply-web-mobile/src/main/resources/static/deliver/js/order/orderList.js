const orderList = [
  {
    pay_status: 0,
    title: '三梦食材',
    order_num: 'DH-O-20190308-421580',
    num: 1,
    date: '03-08 21:48',
    price: 685.45,
    is_collection: 1
  },
  {
    pay_status: 1,
    title: '三梦食材',
    order_num: 'DH-O-20190308-421580',
    num: 1,
    date: '03-08 21:48',
    price: 569.69,
    is_collection: 0
  },
  {
    pay_status: 2,
    title: '三梦食材',
    order_num: 'DH-O-20190308-421580',
    num: 1,
    date: '03-08 21:48',
    price: 685.45,
    is_collection: 0
  },
  {
    pay_status: 0,
    title: '三梦食材',
    order_num: 'DH-O-20190308-421580',
    num: 1,
    date: '03-08 21:48',
    price: 569.69,
    is_collection: 0
  }
]
var timeIndex = 0
var start = '' // 开始时间
var end = ''  // 结束时间
var tabIndex = 0
$(function () {
  if (window.location.href.indexOf('orderList') > -1) {
    getList(0)
    /* 日期选择器 */
    $('#kinerDatePickerInput1').kinerDatePicker({
      clickMaskHide: true,
      changeHandler: function(vals, ctx) {
        if (timeIndex == 0) {
          start = vals[0]+ "-" +vals[1]+ "-" +vals[2]
          if (end == '') {
            end = vals[0]+ "-" +vals[1]+ "-" +vals[2]
          } else {
            if (start.substr(0, 4) > end.substr(0, 4) || start.substr(0, 4) == end.substr(0, 4) && start.substr(5, 2) > end.substr(5, 2) || start.substr(0, 4) == end.substr(0, 4) && start.substr(5, 2) == end.substr(5, 2) && start.substr(8, 2) > end.substr(8, 2)) {
              end = start
            }
          }
        } else {
          if (start == '') {
            start = vals[0]+ "-" +vals[1]+ "-" +vals[2]
          }
          // console.log(start.substr(0, 4), start.substr(5, 2), start.substr(8, 2), vals[0], vals[1], vals[2])
          if (start.substr(0, 4) > vals[0] || start.substr(0, 4) == vals[0] && start.substr(5, 2) > vals[1] || start.substr(0, 4) == vals[0] && start.substr(5, 2) == vals[1] && start.substr(8, 2) > vals[2]) {
            end = start
          } else {
            end = vals[0]+ "-" +vals[1]+ "-" +vals[2]
          }
        }
        $('#kinerDatePickerInput1').html(start+" - "+end)
      }
    });
  }
})
/* 获取订单列表 */
function getList (type) {
  $("#order_list").html(tmpl("tmpl_list", orderList))
  if (Number(type) == 1) {
    $('.order_item_title span:nth-child(3)').hide()
    $('.status2').show().siblings('.status1').hide()
  } else {
    $('.status2').hide().siblings('.status1').show()
  }
  $('.order_examine_item').each(function (index) {
    if ($(this).find('.order_status').html() == '(已付款)') {
      $(this).find('.order_status').css('color', '#0FD55E')
    }
  })
}
/* 选项卡切换 */
$('.order_list_tab div').on('click', function () {
  var type = $(this).attr('data-type')
  $(this).addClass('tab_active').siblings().removeClass('tab_active')
  tabIndex = type
  getList(type)
})
/* 筛选 */
$('.screen').on('click', function () {
  $(".mask").css("display", "block");
  $(".mask").animate({opacity:'1'}, '500');
  $('.mask_popup').addClass('popup_ani').removeClass('popup_to_right')
})
$('body').on('click', '.mask', function () {
  $('.mask_popup').addClass('popup_to_right').removeClass('popup_ani')
  $(this).animate({opacity:'0'}, '1000', function () {
    $(this).css("display", "none")
  })
})
/* 时间选择 */
$('body').on('click', '.time_title span', function () {
  var index = $(this).attr('data-index')
  $(this).addClass('span_act').siblings().removeClass('span_act')
  timeIndex = index
})
/* 筛选按钮 */
$('.order_popup_btn div').on('click', function (e) {
  e.stopPropagation()
  $(this).addClass('order_btn_act').siblings().removeClass('order_btn_act')
})
/* 重置 */
$('.order_popup_bottm div:first-child').on('click', function (e) {
  e.stopPropagation()
  $('.kinerDatePickerInput').html('请选择')
  $('.order_popup_btn div:first-child').addClass('order_btn_act').siblings().removeClass('order_btn_act')
})
/* 跳转详情 */
// $('body').on('click', '.order_examine_item', function () {
//   if (tabIndex == 0) {
//     window.location.href = 'order/orderGoodsDetail.html'
//   } else {
//     window.location.href = 'order/orderDetail.html'
//   }
// })
/* 跳转搜索页 */
$('.order_list_search div:first-child').on('click', function () {
  window.location.href = 'search.html'
})