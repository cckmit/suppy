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
  var id=''
  // 删除按钮
  $(".harvest_address_btn_delete").click(function () {
    $(".harvest_address_modal").css("display", "block")
    id = $(this).parents('.address_list').attr('id')
    console.log(id)
    noSlide();
  })
    // 取消按钮
    $(".harvest_address_modal_btn_cancel ").click(function () {
      $(".harvest_address_modal").css("display", "none")
      slide();
    })
    // 确认按钮
    $(".harvest_address_modal_btn_notarize ").click(function () {
      $(".harvest_address_modal").css("display", "none")
      slide();
    })
    // 不是默认地址按钮
    $(".harvest_address_list_circle").click(function () {
      $(".harvest_address_list_circle").css("display", "none")
      $(".harvest_address_list_confirm").css("display", "inline-block")
    })
    // 默认地址按钮
    $(".harvest_address_list_confirm").click(function () {
    $(".harvest_address_list_confirm").css("display", "none")
    $(".harvest_address_list_circle").css("display", "inline-block")
  })
   $('.harvest_address_modal_btn_notarize').click(function(){
        $('#'+ id).remove()
    })
})
