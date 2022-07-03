var countdown = 60
/* 60秒倒计时 */
function getCountDown (obj) {
  if (countdown == 0) { 
    obj.attr('disabled',false)
    obj.html("重新获取")
    countdown = 60
    return
  } else { 
    obj.attr('disabled',true)
    obj.html(countdown + "s")
    countdown--
  } 
  setTimeout(function() { 
    getCountDown(obj) 
  },1000)
}
/* 点击获取验证码 */
$('#get_code').on('click', function () {
  getCountDown($(this))
})
/* 忘记密码下一步 */
$('.forget_next').on('click', function () {
  window.location.href = 'setNewPassword.html'
})