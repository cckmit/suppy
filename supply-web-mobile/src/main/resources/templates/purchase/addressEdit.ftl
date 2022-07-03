<!DOCTYPE html>
<html lang="en" xmlns="http://www.w3.org/1999/html">
<head>
    <meta charset="utf-8">
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0,user-scalable=no"/>
    <title>
        易订货-<#if ads.id??>编辑<#else >新增</#if>地址</title>
      <link rel="stylesheet" href="/css/global.css">
      <link rel="stylesheet" href="/css/personalCenter/newAddress.css">
      <link rel="stylesheet" href="http://at.alicdn.com/t/font_1091326_ne7rozzgza.css">
<#--    <link rel="stylesheet" href="/css/global.css">-->
<#--    <link rel="stylesheet" href="/css/login.css">-->
<#--    <link rel="stylesheet" href="/css/layer.css">-->
        <link rel="stylesheet" href="/css/layer.css">
    <link rel="stylesheet" href="/css/login.css">
    <style>
        /**{*/
        /*    margin: 0px;*/
        /*    padding: 0px;*/
        /*}*/
        /*body{*/
        /*    background: #f0f0f0;*/
        /*}*/
        /*.ads_topE{*/
        /*    border: 0px solid #FF807D;*/
        /*    padding: 5px auto;*/
        /*    height: 30px;*/
        /*    border-bottom: 1px solid gray;*/
        /*    padding-top: 10px;*/
        /*    text-align: center;*/
        /*}*/
        /*.ads_top_d1E{*/
        /*    color: #1E9FFF;*/
        /*    display: inline-block;*/
        /*    position: absolute;*/
        /*    left: 0px;*/
        /*}*/
        /*.ads_top_d2E{*/
        /*    display: inline-block;*/
        /*    padding: 0px 30%;*/
        /*    font-weight: bold;*/
        /*}*/
        /*.btn{*/
        /*    color: white;*/
        /*    font-weight: bold;*/
        /*    background: #FF807D;*/
        /*    height: 40px;*/
        /*    text-align: center;*/
        /*    padding-top:10px;*/
        /*}*/
        /*.adsEdit_d1>div{*/
        /*    padding: 15px;*/
        /*}*/
    </style>
</head>
<body>
<#--    <div class="ads_topE">-->
<#--        <div class="ads_top_d1E" onclick="window.location.href='toAddress';">〈返回</div>-->
<#--        <div class="ads_top_d2E"><#if ads.id??>编辑<#else >新增</#if>地址</div>-->
<#--    </div>-->
<#--    <div class="adsEdit_d1">-->
<#--        <div>-->
<#--        姓名:<input id="name" value="${ads.name!}">-->
<#--        </div>-->
<#--        <div>-->
<#--        电话:<input id="tel" value="${ads.tel!}">-->
<#--        </div>-->
<#--        <div>-->
<#--        省:<input id="province" value="${ads.province!}">-->
<#--        </div>-->
<#--        <div>-->
<#--        市:<input id="city" value="${ads.city!}">-->
<#--        </div>-->
<#--        <div>-->
<#--        县/区:<input id="county" value="${ads.county!}">-->
<#--        </div>-->
<#--        <div>-->
<#--        具体地址:<input id="addressDetail" value="${ads.addressDetail!}">-->
<#--        </div>-->
<#--        <div>-->
<#--        默认地址:<input type="checkbox"  id="de"  datavalue="${ads.isDefault!}">-->
<#--        </div>-->
<#--        <div>-->
<#--        <div class="btn" id="btSure" onclick="sure();" datavalue="${ads.id!}">确定</div>-->
<#--        </div>-->
<#--        <#if ads.id??>-->
<#--            <div>-->
<#--                <div class="btn" id="btDlt" onclick="dlt();" datavalue="${ads.id!}">删除</div>-->
<#--            </div>-->
<#--        </#if>-->
<#--    </div>-->
<div class="new_address">
  <input class = "ids" style="display: none" value="${ids!}">
  <div>
    <div class="new_address_list f">
      <span>客户名称</span> <input type="text" placeholder="请输入" value="${ads.name!}" id="name">
    </div>
<#--    <div class="new_address_list f">-->
<#--      <span>联系人</span> <input type="text" placeholder="姓名" value="${ads.name!}">-->
<#--    </div>-->
    <div class="new_address_list f">
      <span>联系方式</span> <input type="text" placeholder="手机号码" value="${ads.tel!}" id="tel">
    </div>
    <div class="new_address_list f">
      <span>省/市/区</span>
      <div class="new_address_list_select">
        <input type="text" id="picker" placeholder="选择省市区" value="${ads.province!} ${ads.city!} ${ads.county!}">
        <i class="iconfont icontubiao-22"></i>
      </div>
    </div>
    <div class="new_address_list f">
      <span>详细地址</span> <input type="text" placeholder="请输入" value="${ads.addressDetail!}" id="addressDetail">
    </div>
    <div class="new_address_list f" id="de"  datavalue="${ads.isDefault!}">
      <span>设为默认地址</span>
      <div class="new_address_list_default">
        <div class="new_address_list_circle"></div>
        <i class="iconfont iconqueren new_address_list_confirm"></i>
      </div>
    </div>
  </div>
  <div class="new_address_bottom" onclick="sure();" id="sure" datavalue="${ads.id!}">保存</div>
</div>
<script src="http://www.jq22.com/jquery/jquery-1.10.2.js"></script>
<script src="/js/address/picker.min.js"></script>
<script type="text/javascript" src="/js/address/city.js"></script>
<script type="text/javascript" src="/js/address/index.js"></script>
<script src="/js/personalCenter/newAddress.js"></script>
<script src="/js/layer.js"></script>
</body>
<script>
    window.onload=function(){
        var v = document.getElementById("de").getAttribute("datavalue");
        ckboxOL(v);
    }
    function sure() {
        var id = document.getElementById("sure").getAttribute("datavalue");
        var name = document.getElementById("name").value;
        var tel = document.getElementById("tel").value;
        var pcc = document.getElementById("picker").value;
        var addressDetail = document.getElementById("addressDetail").value;
        var isDefault = $(".new_address_list_confirm").css("display")=="none" ? 0:1;
        var url1 =  id==null||id==""?"doAddressAdd":"doAddressEdit";

        if(name==""||name==null){
          layer.open({
            content: '名称不得为空'
            ,btn: '我知道了'
          });
          return false;
        }
        if(tel==""||tel==null){
          layer.open({
            content: '联系方式不得为空'
            ,btn: '我知道了'
          });
          return false;
        }
        if(!(/^1[3456789]\d{9}$/.test(tel))){
          layer.open({
            content: '联系方式格式不正确'
            ,btn: '我知道了'
          });
          return false;
        }
      if(pcc==""||pcc==null){
          layer.open({
            content: '地址不得为空'
            ,btn: '我知道了'
          });
          return false;
        }
        $.ajax({
            url:url1,
            method:"post",
            //data:{"id":id,"name":name,"tel":tel,"province":province,"city":city,"county":county,"addressDetail":addressDetail,"isDefault":isDefault},
            data:{"id":id,"name":name,"tel":tel,"pcc":pcc,"addressDetail":addressDetail,"isDefault":isDefault},
            //data:{"id":99,"name":"name1"},
            success:function (data) {
                console.log(data);
                if(data==true){
                    console.log(1);
                    var ids = $(".ids").val();
                    if(ids!=''&&ids!=null){
                      window.location.href = "toShopCartOrder?ids="+ids;
                    }else{
                      window.location.href = 'toAddress';
                    }
                }
                if(data==false){
                    console.log(2);
                }
            }
        })
    }
    function ckboxOL(isDft){
        if(isDft==1){
            //document.getElementById("de").setAttribute("checked","checked");
            //console.log("ckboxOL"+document.getElementById("de").checked);
          $(".new_address_list_confirm").css("display","inline-block");
          $(".new_address_list_circle").css("display","none");
        }
    }
    function dlt(){
        // layer.confirm('确定要删除么？', {
        //     btn: ['确定','取消'] //按钮
        // }, function(){
        //
        // });
        var id = document.getElementById("btDlt").getAttribute("datavalue");
        layer.open({
            content: '确定要删除该地址吗？'
            , btn: ['确定', '取消']
            , yes: function (index) {
                $.ajax({
                    url:"doAddressDlt",
                    method:"post",
                    data:{"id":id},
                    success:function (data) {
                        console.log(data);
                        if(data==true){
                            console.log(1);
                            window.location.href = 'toAddress';
                        }
                        if(data==false){
                            console.log(2);
                        }
                    }
                })
                layer.close(index);
                return;
            }
        });
    }
</script>
</html>
