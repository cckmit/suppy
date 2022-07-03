var history_list = []
$(function () {
  $('#search_list').html(tmpl("tmpl_search", JSON.parse(localStorage.getItem('history_list'))))
})
/* 监听输入 */
$('.search_input input').on('input propertychange', function () {
  if ($(this).val() == '') {
    $(this).parent().find('i').addClass('icontubiao-47').removeClass('iconcha')
  } else {
    $(this).parent().find('i').addClass('iconcha').removeClass('icontubiao-47')
  }
})
/* 清除搜索框内容 */
$('body').on('click', '.iconcha', function () {
  $(this).removeClass('iconcha').addClass('icontubiao-47')
  $(this).parent().find('input').val('')
  $('.commodity_top_sort').addClass('dis_none')
  $('.commodity_list').hide()
  $('.search_history').show()
  $('#search_list').html(tmpl("tmpl_search", JSON.parse(localStorage.getItem('history_list'))))
})

/* 搜索回车键 */
$('.search_input input').bind('keypress', function (e) {
  if (e.keyCode == '13') {
    if ($(this).val() == '') {
      return false
    }
    history_list.push($(this).val())
    localStorage.setItem('history_list', JSON.stringify(history_list))
    $('.search_history').hide()
    $('.commodity_top_sort').removeClass('dis_none')
    $('.commodity_list').show()
    getList()
  }
})
/* 清除历史记录 */
$('body').on('click', '.search_his_title i', function () {
  localStorage.clear('history_list')
  $('#search_list').html(tmpl("tmpl_search", JSON.parse(localStorage.getItem('history_list'))))
})
/* 返回上一页 */
$('.icontubiao-40').on('click', function () {
  window.history.go(-1)
})