<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport"
      content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0,user-scalable=no" />
    <title>易订货-新增地址</title>
    <link rel="stylesheet" href="/css/global.css">
    <link rel="stylesheet" href="/css/personalCenter/newAddress.css">
    <link rel="stylesheet" href="http://at.alicdn.com/t/font_1091326_ne7rozzgza.css">
  </head>
  <body>
    <div class="new_address">
      <div>
        <div class="new_address_list f">
          <span>客户名称</span> <input type="text" placeholder="请输入">
        </div>
        <div class="new_address_list f">
          <span>联系人</span> <input type="text" placeholder="姓名">
        </div>
        <div class="new_address_list f">
          <span>联系方式</span> <input type="text" placeholder="手机号码">
        </div>
        <div class="new_address_list f">
          <span>省/市/区</span>
          <div class="new_address_list_select">
            <input type="text" id="picker" placeholder="选择省市区">
            <i class="iconfont icontubiao-22"></i>
          </div>
        </div>
        <div class="new_address_list f">
          <span>详细地址</span> <input type="text" placeholder="请输入">
        </div>
        <div class="new_address_list f">
          <span>设为默认地址</span>
          <div class="new_address_list_default">
            <div class="new_address_list_circle"></div>
            <i class="iconfont iconqueren new_address_list_confirm"></i>
          </div>
        </div>
      </div>
      <div class="new_address_bottom">保存</div>
    </div>
  </body>
  <script src="http://www.jq22.com/jquery/jquery-1.10.2.js"></script>
  <script src="/js/address/picker.min.js"></script>
  <script type="text/javascript" src="/js/address/city.js"></script>
  <script type="text/javascript" src="/js/address/index.js"></script>
  <script src="/js/personalCenter/newAddress.js"></script>
</html>