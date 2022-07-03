$(function () {
  $('.unit_list_item').on('click', function () {
    $(this).css({'color': '#0BC6D7'}).siblings().css({'color': '#666'})
    $(this).find('i').show()
    $(this).siblings('.unit_list_item').find('i').hide()
  })
})