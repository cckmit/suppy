$('.toVoidReasonPage').on('click', '#sure_reason', function () {
  var len = $('.toVoidReasonPage textarea').val().replace(/\n|\r/gi,"").length
  if (len < 4) {
    $('.toVoidReasonPage .toast').addClass('block')
    setTimeout(function () {
      $('.toVoidReasonPage .toast').removeClass('block')
    }, 2000)
  }
})