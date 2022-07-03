<!DOCTYPE html>
<html>
  <head>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0,user-scalable=no" />
    <title>易订货-设置新密码</title>
    <link rel="stylesheet" href="/css/global.css">
    <link rel="stylesheet" href="/css/login.css">
      <link rel="stylesheet" href="/css/layer.css">
  </head>
  <body>
    <div class="set_new_page">
      <div class="set_input">
          <input id="oldpassword" type="password" placeholder="请输入原密码">
      </div>
      <div class="set_input">
        <input id="password-one" type="password" placeholder="请输入新密码">
      </div>
      <div class="set_input">
        <input id="password-two" onblur="return checkPass();" type="password" placeholder="请再次输入新密码">
      </div>
      <button class="set_sure" onclick="changePwd();">确定</button>
    </div>
    <#--<script src="http://www.jq22.com/jquery/jquery-1.10.2.js"></script>-->
    <script src="/js/jquery.min.js"></script>
    <script src="/js/layer.js"></script>
    <script>
      function checkPass() {
          var pwd1 = document.getElementById("password-one").value;
          var pwd2 = document.getElementById("password-two").value;
          if (pwd1 != pwd2) {
              layer.open({
                  content: '两次密码输入不一致！'
                  ,btn: '我知道了'
              });
              return false;
          }
      }

      function changePwd() {
          checkPass();
          var oldpassword = document.getElementById("oldpassword").value;
          var newpassword = document.getElementById("password-one").value;
          $.ajax({
              url:"changePwdJson",
              method:"post",
              data:{"oldPassword":oldpassword,"newPassword":newpassword},
              success:function (data) {
                  console.log(data);
                  layer.open({
                      content: data.msg,
                      btn: '确定'
                  })
                  if(data.status==200){
                      window.location.href = 'index'
                  }
              }
          })
      }
    </script>
  </body>
</html>