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
  // 备注信息按钮
  $("#write_order_beizhu").click(function () {
    $(".write_order_modal").css("display", "block")
    noSlide();
    // $(".write_order_modal_main #beizu").val($("#write_order_beizhu .thin").text());
    $(".write_order_modal_main #beizu").val("");
  })
  // 取消按钮
  $(".write_order_modal_btn_cancel ").click(function () {
    $(".write_order_modal").css("display", "none")
    slide();
    $(".write_order_modal_main #beizu").val("");
  })

  // 确认按钮
  $(".write_order_modal_btn_notarize ").click(function () {
    $(".write_order_modal").css("display", "none")
    $("#write_order_beizhu .thin").css("color", '#666666')
    $("#write_order_beizhu .thin").text($(".write_order_modal_main #beizu").val())
    slide();
  })

  $("#datetime").shijian();
})