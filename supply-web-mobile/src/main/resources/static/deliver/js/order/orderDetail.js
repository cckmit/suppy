
function toList () {
  window.location.href = 'detailedList.html'
}
/* 订单退回原因 */
$('.return_reason_page').on('click', '#sure_reason', function () {
  var len = $('.return_reason_page textarea').val().replace(/\n|\r/gi,"").length
  if (len < 4) {
    $('.return_reason_page .toast').addClass('block')
    setTimeout(function () {
      $('.return_reason_page .toast').removeClass('block')
    }, 2000)
  }
})