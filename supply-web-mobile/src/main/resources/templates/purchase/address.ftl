<!DOCTYPE html>
<html lang="en" xmlns="http://www.w3.org/1999/html">
<head>
    <meta charset="utf-8">
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0,user-scalable=no"/>
    <title>易订货-我的地址</title>
    <link rel="stylesheet" href="/css/global.css">
    <link rel="stylesheet" href="/css/personalCenter/harvestAddress.css">
    <link rel="stylesheet" href="/css/layer.css">
    <style>
        *{
            margin: 0px;
            padding: 0px;
        }
        a{
            color: white;
            text-decoration: none;
        }
        /*.ads_d1E{*/
        /*    border-bottom:1px solid gray;*/
        /*    background: whitesmoke;*/
        /*}*/
        /*.ads_topE{*/
        /*    border: 0px solid #FF807D;*/
        /*    padding: 5px auto;*/
        /*    height: 30px;*/
        /*    border-bottom: 3px solid #666666;*/
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
        /*.ads_d1E div{*/
        /*    padding: 3px 5px;*/
        /*}*/
        /*.ads_3d12E{*/
        /*    color:gray;*/
        /*}*/
        /*.ads_4d111_dftAdsE{*/
        /*    border: 1px solid #FF807D;*/
        /*    display: inline-block;*/
        /*    color: #FF807D;*/
        /*    padding: 0px 5px !important;*/
        /*    margin-top: -1px;*/
        /*    height: 21px;*/
        /*}*/
        /*.ads_2d2E{*/
        /*    color: #FF807D;*/
        /*    font-weight: bold;*/
        /*    text-align: center;*/
        /*    float: right;*/
        /*    padding: 10px;*/
        /*    margin-top: -50px;*/
        /*    margin-right: 10px;*/
        /*    padding: 10px !important;*/
        /*}*/
        /*.ads_addE{*/
        /*    color: white;*/
        /*    font-weight: bold;*/
        /*    background: #FF807D;*/
        /*    height: 40px;*/
        /*    text-align: center;*/
        /*    position: fixed;*/
        /*    bottom:0px;*/
        /*    width: 100%;*/
        /*    padding-top:10px;*/
        /*}*/
        /*.ads_2d1E{*/
        /*    width:80%;*/
        /*}*/
    </style>
</head>
<body>
<#--    <div class="ads_topE">-->
<#--        <div class="ads_top_d1E" onclick="window.location.href='myinfo';">〈返回</div>-->
<#--        <div class="ads_top_d2E">我的地址</div>-->
<#--    </div>-->
<#--    <#list adsList as ads>-->
<#--        <div class="ads_d1E">-->
<#--            <div class="ads_2d1E">-->
<#--                <div>-->
<#--                    <#if  ads.isDefault==1>-->
<#--                        <div class="ads_4d111_dftAdsE">默认</div>-->
<#--                        &lt;#&ndash;<#elseIf  ads.isDefault!=1>&ndash;&gt;-->
<#--                    </#if>-->
<#--                    ${ads.name!},${ads.tel!}-->
<#--                </div>-->
<#--                <div class="ads_3d12E">${ads.province!},${ads.city!},${ads.county!},${ads.addressDetail}</div>-->
<#--            </div>-->
<#--            <a href="toAddressEdit?id=${ads.id!}"><div class="ads_2d2E">编辑</div></a>-->
<#--        </div>-->
<#--    </#list>-->
<#--<div class="ads_addE" onclick="window.location.href='toAddressAdd'">-->
<#--    新增地址-->
<#--</div>-->
    <div class="harvest_address">


        <#list adsList as ads>
            <div class="address_list">
                <div class="harvest_address_content f">
                    <div>
                        <div class="harvest_address_content_name"> ${ads.name!}</div>
                        <span>${ads.tel!}</span>
                    </div>
                    <div>
                        <span class="harvest_address_content_name">地址</span>：
                        <span class="address_content">${ads.province!},${ads.city!},${ads.county!},${ads.addressDetail}</span>
                    </div>
                    <#if  ads.isDefault==1>
                        <span class="default_address">默认地址</span>
                    </#if>
                </div>
                <div class="address_list_bottom f">
                    <div class="harvest_address_btn">
                        <a href="toAddressEdit?id=${ads.id!}">
                            <span class="harvest_address_btn_edit" style="color: #FF807D;">编辑</span>
                        </a>
                        <span class="harvest_address_btn_delete"id="btDlt" datavalue="${ads.id!}"  onclick="dlt(${ads.id!});">删除</span>
                    </div>
                </div>
            </div>
        </#list>



    </div>
  <div class="harvest_address_bottom" onclick="window.location.href='toAddressAdd'">
    <a href="#" style="color:#FF807D;">新增地址</a>
  </div>

<#--<div class="harvest_address_modal">-->
<#--  <div class="harvest_address_modal_main">-->
<#--    <div class="harvest_address_modal_content">确定删除？</div>-->
<#--    <div class="harvest_address_modal_btn f">-->
<#--      <span class="harvest_address_modal_btn_cancel">取消</span>-->
<#--      <span class="harvest_address_modal_btn_notarize">确定</span>-->
<#--    </div>-->
<#--  </div>-->
<#--</div>-->

</body>
<script src="/js/jquery.min.js"></script>
<script src="/js/personalCenter/harvestAddress.js"></script>
<script src="/js/layer.js"></script>
<script>
    function dlt(adsId){
        // var id = document.getElementById("btDlt").getAttribute("datavalue");
        layer.open({
            content: '确定要删除该地址吗？'
            , btn: ['确定', '取消']
            , yes: function (index) {
                $.ajax({
                    url:"doAddressDlt",
                    method:"post",
                    data:{"id":adsId},
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
