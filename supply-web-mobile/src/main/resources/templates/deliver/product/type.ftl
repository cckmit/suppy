<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0,user-scalable=no"/>
    <title>易订货-分类</title>
    <link rel="stylesheet" href="css/global.css">
    <link rel="stylesheet" href="css/commodity/type.css">
    <link rel="stylesheet" href="http://at.alicdn.com/t/font_1100572_z3zcw27b6fk.css">
</head>
<body>
<div class="type_page">
    <div class="type_list">
        <#if categoryList?exists && ( categoryList?size > 0 )>
            <#list categoryList as c>
                <div class="type_list_item type_list_item_${c.id!}">
                    <div class="type_list_title">
                        <span class="add" categoryId="${c.id!}">+</span>
                        <span categoryId="${c.id!}" categoryName="${c.categoryName!}" onclick="go(this)">${c.categoryName!}</span>
                    </div>
                </div>
            </#list>
        </#if>
    </div>
</div>
</body>
<script src="/js/jquery.min.js"></script>
<script src="/deliver/js/commodity/type.js"></script>
</html>