$(function () {
  $('.order_tab div').on('click', function () {
    $(this).addClass('tabActive').siblings('.order_tab div').removeClass('tabActive')
  })
})