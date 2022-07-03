$(function () {
  function noSlide() {
    $("body").css("height", "100%");
    $("body").css("overflow ", "hidden")
    $("html").css("height", "100%")
    $("html").css("overflow ", "hidden")
  }
  function slide() {
    $("body").css("height", "auto")
    $("body").css("overflow", "scroll")
    $("html").css("height", "auto")
    $("html").css("overflow", "scroll")
  }
  // 订单审核按钮
  $(".order_detail_submit").click(function () {
    $(".order_detail_modal").css("display", "block")
    noSlide();
  })
  // 取消按钮
  $(".order_detail_modal_btn_cancel ").click(function () {
    $(".order_detail_modal").css("display", "none")
    slide();
  })
  // 确认按钮
  $(".order_detail_modal_btn_notarize ").click(function () {
    $(".order_detail_modal").css("display", "none")
    slide();
  })
})