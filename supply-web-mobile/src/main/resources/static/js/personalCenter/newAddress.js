$(function () {
  // 不是默认地址按钮
  $(".new_address_list_circle").click(function () {
    $(".new_address_list_circle").css("display", "none")
    $(".new_address_list_confirm").css("display", "inline-block")
  })
  // 默认地址按钮
  $(".new_address_list_confirm").click(function () {
    $(".new_address_list_confirm").css("display", "none")
    $(".new_address_list_circle").css("display", "inline-block")
  })
})