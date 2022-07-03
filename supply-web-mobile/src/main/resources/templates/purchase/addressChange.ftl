<!DOCTYPE html>
<html lang="en" xmlns="http://www.w3.org/1999/html">
<head>
    <meta charset="utf-8">
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0,user-scalable=no"/>
    <title>改变收货地址</title>
    <style>
        *{
            margin: 0px;
            padding: 0px;
        }
        body{
            background: #f0f0f0;
        }
        .ads_d1E{
            border-bottom:1px solid gray;
            background: white;
        }
        .ads_topE{
            border: 0px solid #FF807D;
            padding: 5px auto;
            height: 30px;
            border-bottom: 1px solid gray;
            padding-top: 10px;
            text-align: center;
        }
        .ads_top_d1E{
            color: #1E9FFF;
            display: inline-block;
            position: absolute;
            left: 0px;
        }
        .ads_top_d2E{
            display: inline-block;
            padding: 0px 30%;
            font-weight: bold;
        }
        .ads_d1E div{
            padding: 3px 5px;
        }
        .ads_3d12E{
            color:gray;
        }
        .ads_4d111_dftAdsE{
            border: 1px solid #FF807D;
            display: inline-block;
            color: #FF807D;
            padding: 0px 5px !important;
            margin-top: -1px;
            height: 21px;
        }
        .ads_2d2E{
            color: #FF807D;
            font-weight: bold;
            text-align: center;
            float: right;
            padding: 10px;
            margin-top: -50px;
            margin-right: 10px;
            padding: 10px !important;
        }
        a{
            color: white;
            text-decoration: none;
        }
        .ads_addE{
            color: white;
            font-weight: bold;
            background: #FF807D;
            height: 40px;
            text-align: center;
            position: fixed;
            bottom:0px;
            width: 100%;
            padding-top:10px;
        }
        .ads_2d1E{
            width:80%;
        }
    </style>
</head>
<body>
    <div class="ads_topE">
<#--        <div class="ads_top_d1E" onclick="window.location.href='myinfo';">〈返回</div>-->
        <div class="ads_top_d2E">我的地址</div>
    </div>
    <#list adsList as ads>
        <div class="ads_d1E">
            <div class="ads_2d1E">
                <div>
                    <#if  ads.isDefault==1>
                        <div class="ads_4d111_dftAdsE">默认</div>
                        <#--<#elseIf  ads.isDefault!=1>-->
                    </#if>
                    ${ads.name!},${ads.tel!}
                </div>
                <div class="ads_3d12E">${ads.province!},${ads.city!},${ads.county!},${ads.addressDetail!}</div>
            </div>
<#--            <a href="toAddressEdit?id=${ads.id!}"><div class="ads_2d2E">编辑</div></a>-->
        </div>
    </#list>
<#--<div class="ads_addE" onclick="window.location.href='toAddressAdd'">-->
<#--    新增地址-->
<#--</div>-->
</body>
<script src="/js/jquery.min.js"></script>
<script>

</script>
</html>