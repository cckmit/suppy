<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0,user-scalable=no"/>
    <title>易订货-分类</title>
    <link rel="stylesheet" href="/css/global.css">
    <link rel="stylesheet" href="/css/classify.css">
    <link rel="stylesheet" href="http://at.alicdn.com/t/font_1091326_rksxatgoljf.css">
</head>
<body>
<div class="wrap">
    <div class="classify">
        <div class="scrolltab">
            <div class="scrolltab-nav" id="menuLeft">
                <#if categoryList?exists && (categoryList?size > 0)>
                    <#list categoryList as category>
                        <#if category_index == 0>
                            <a href="javascript:;" class="scrolltab-item cur" ppId="${category.id!}">
                                <div class="scrolltab-title">${category.categoryName!}</div>
                            </a>
                        <#else>
                            <a href="javascript:;" class="scrolltab-item" ppId="${category.id}">
                                <div class="scrolltab-title">${category.categoryName!}</div>
                            </a>
                        </#if>
                    </#list>
                </#if>
            </div>
            <div class="scrolltab-content" id="menuRight">
                <#list categoryList as category>
                    <div class="scrolltab-content-item">
                        <h2>${category.categoryName!}</h2>
                        <div class="list">
                            <#if category.childCategory?exists && (category.childCategory?size > 0)>
                                <#list category.childCategory as categoryChild>
                                    <a class="redirect" categoryId="${categoryChild.id!}"
                                       categoryName="${categoryChild.categoryName!}">
                                        <div class="img" style="background-image:url('${categoryChild.img!}')"></div>
                                        <div class="text">${categoryChild.categoryName!}</div>
                                    </a>
                                </#list >
                            </#if>
                        </div>
                    </div>
                </#list>
            </div>
        </div>
    </div>
</div>
<!-- 底部导航栏 -->
<div class="bottom_bar" id="bottom_bar" data-current="1"></div>
<script src="/js/tmpl.min.js"></script>
<script type="text/x-tmpl" id="tmpl_bottom">
      {% for (var i=0; i<o.length; i++) { %}
        <div class="{%=o[i].class%}" data-index="{%=i%}">
          <i class="iconfont {%=o[i].icon%}"></i>
          <p>{%=o[i].name%}</p>
        </div>
      {% } %}
</script>
<script src="/js/jquery.min.js"></script>
<script src="/js/global.js"></script>
<script type="text/javascript">
    $(function () {
        var count = "${count!}";
        $('#bottom_bar div:eq(2)').append('<div class="flag">' + count + '</div>')
    })

    $(".redirect").click(function () {
        var categoryId = $(this).attr("categoryId");
        var categoryName = $(this).attr("categoryName");
        categoryName = encodeURI(categoryName);
        console.log("categoryId=" + categoryId)
        console.log("categoryName=" + categoryName)
        var url = "/purchase/commodityList?categoryId=" + categoryId + "&categoryName=" + categoryName;
        window.location.href = url;
    });

    $('#menuRight .scrolltab-content-item').hide()
    $('#menuRight .scrolltab-content-item:eq(0)').show()
    $('#menuLeft .scrolltab-item').click(function (e) {
        var i = $(this).index();
        var t = $('#menuRight').scrollTop() - $(window).width() / 375 * 40 * 1;
        $('#menuLeft .scrolltab-item').eq(i).addClass('cur').siblings().removeClass('cur');
        $('#menuRight').scrollTop($('#menuRight .scrolltab-content-item').eq(i).offset().top + t);
        $('#menuRight .scrolltab-content-item').eq(i).show().siblings().hide();
        $('#menuRight').scrollTop(0)
    })

    // var heightArr = [];
    // for (var i = 0; i < $('#menuRight .scrolltab-content-item').length; i++) {
    //     heightArr.push($('#menuRight .scrolltab-content-item').eq(i).offset().top);
    // }

    // $('#menuRight').scroll(function () {
    //     var t = $(this).scrollTop();
    //     for (var i in heightArr) {
    //         if ((t + $(window).width() / 375 * 40 * 1) >= heightArr[i]) {
    //             $('#menuLeft .scrolltab-item').eq(i).addClass('cur').siblings().removeClass('cur');
    //         }
    //     }


    // $('#menuLeft').stop().animate({
    //   scrollTop: $('#menuLeft .scrolltab-item').height() * ($('#menuLeft .scrolltab-item.cur').index()+0.5) - ($('#menuLeft').height()/2)
    // }, 200)
    // })
    // var nScrollHight = 0; //滚动距离总长(注意不是滚动条的长度)
    // var nScrollTop = 0;   //滚动到的当前位置
    // var nDivHight = $("#menuRight").height();
    // $("#menuRight").scroll(function(){
    //     nScrollHight = $(this)[0].scrollHeight;
    //     nScrollTop = $(this)[0].scrollTop;
    //     var paddingBottom = parseInt( $(this).css('padding-bottom') ),paddingTop = parseInt( $(this).css('padding-top') );
    //     if(nScrollTop + paddingBottom + paddingTop + nDivHight + 10 >= nScrollHight)
    //         $('#menuLeft .scrolltab-item').eq(7).addClass('cur').siblings().removeClass('cur');
    // });
</script>
</body>
</html>