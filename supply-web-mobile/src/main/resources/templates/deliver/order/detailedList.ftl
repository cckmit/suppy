<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0,user-scalable=no" />
    <title>易订货-商品清单</title>
    <link rel="stylesheet" href="/deliver/css/global.css">
    <link rel="stylesheet" href="/deliver/css/order/detailedList.css">
    <link rel="stylesheet" href="http://at.alicdn.com/t/font_1091326_sr48ma9pxf.css">
</head>
<body>
<div class="detail_list_page">
    <div class="detail_list" id="detail_list"></div>
</div>
<script src="/deliver/js/tmpl.min.js"></script>
<script src="http://www.jq22.com/jquery/jquery-1.10.2.js"></script>
<script src="/deliver/js/order/detailedList.js"></script>
<script type="text/x-tmpl" id="tmpl_list">
      {% for (var i=0; i<o.length; i++) { %}
        <div class="detail_list_item">
          <div class="detail_list_image" style="background-image:url(../image/img.png)"></div>
          <div class="detail_list_main">
            <div>{%=o[i].title%}</div>
            <div>{%=o[i].code%}</div>
            <div>发货数量：<span>{%=o[i].num%}</span></div>
          </div>
        </div>
      {% } %}
    </script>
</body>
