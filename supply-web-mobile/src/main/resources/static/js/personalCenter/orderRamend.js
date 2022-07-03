
$(function () {
  // 编辑
  $(".orde_ramend_btn_amend").click(function () {
    $(".orde_ramend_edit").css("display", "block")
    $(".orde_ramend_top i").css("color", "rgba(255,255,255,1)")
    $(".orde_ramend_top").css("backgroundColor", "rgba(255, 128, 125, 1)")
    $(".orde_ramend_list").css("display", "none")
  })
  // 完成编辑
  $(".orde_ramend_edit_right").click(function () {
    $(".orde_ramend_edit").css("display", "none");
    $(".orde_ramend_top i").css("color", "rgba(255, 128, 125, 1)");
    $(".orde_ramend_top").css("backgroundColor", "rgba(255,255,255,1)");
    $(".orde_ramend_list").css("display", "block")
  })

})