var history_list = []
$(function () {
  $('#search_list').html(tmpl("tmpl_search", JSON.parse(localStorage.getItem('history_list'))))
})
/* 监听输入 */
$('.search_input input').on('input propertychange', function () {
  if ($(this).val() == '') {
    $(this).parent().find('i:nth-child(3)').hide()
  } else {
    $(this).parent().find('i:nth-child(3)').show()
  }
})
/* 清除搜索框内容 */
$('body').on('click', '.iconcha', function () {
  $(this).hide()
  $('.order_list').hide()
  $('.search_history').show()
  $(this).parent().find('input').val('')
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
    $('.order_list').show()
    getList()
  }
})

/* 搜索 */
$('.search_text').on('click', function () {

  if ($(this).siblings('.search_input').find('input').val() == '') {
    return false
  }
  history_list.push($(this).siblings('.search_input').find('input').val())
  localStorage.setItem('history_list', JSON.stringify(history_list))
  $('.search_history').hide()
  $('.order_list').show()
  getList()
})

/* 清除历史记录 */
$('body').on('click', '.search_his_title i', function () {
  localStorage.clear('history_list')
  $('#search_list').html(tmpl("tmpl_search", JSON.parse(localStorage.getItem('history_list'))))
})