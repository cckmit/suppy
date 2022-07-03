<!DOCTYPE html>
<html>
  <head>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0,user-scalable=no" />
    <title>易订货-商品</title>
    <link rel="stylesheet" href="/css/global.css">
    <link rel="stylesheet" href="/css/commodity/commodityList.css">
    <link rel="stylesheet" href="/css/search.css">
    <link rel="stylesheet" href="http://at.alicdn.com/t/font_1091326_7ng348oax5r.css">
  </head>
  <body>
    <div class="commodity_page search_page">
      <div class="commodity_top">
        <div class="commodity_top_search">
          <div>
            <i class="iconfont icontubiao-40"></i>
          </div>
          <div class="search_input">
            <input type="text" placeholder="搜索商品"/>
            <i class="iconfont icontubiao-47"></i>
          </div>
        </div>
        <div class="commodity_top_sort dis_none">
          <div>综合排序</div>
          <div>价格<i class="iconfont iconxiangshangjiantou-copy-copy-copy-copy"></i><i class="iconfont iconxiangshangjiantou-copy-copy-copy"></i></div>
          <div>销量<i class="iconfont iconxiangshangjiantou-copy-copy-copy-copy"></i><i class="iconfont iconxiangshangjiantou-copy-copy-copy"></i></div>
        </div>
      </div>
      <div class="commodity_list" id="commodity_list"></div>
      <div class="search_history">
        <div class="search_his_title">
          <span>历史搜索</span>
          <span><i class="iconfont icontubiao-30"></i></span>
        </div>
        <div class="search_list" id="search_list"></div>
      </div>
    </div>
    <script src="https://cdn.bootcss.com/blueimp-JavaScript-Templates/3.11.0/js/tmpl.min.js"></script>
    <script type="text/x-tmpl" id="tmpl_list">
      {% for (var i=0; i<o.length; i++) { %}
        <div class="commodity_list_item">
          <div class="commodity_image" style="background-image:url(/image/img.png)"></div>
          <div class="commodity_main">
            <div class="main_title">{%=o[i].title%}</div>
            <div class="main_sku">
              <span>销量{%=o[i].sales_volume%}</span><span>{%=o[i].sku%}g</span>
            </div>
            <div>
              <div class="main_price">
                <span>￥{%=o[i].price%}<span>/袋</span></span>
                <span>库存{%=o[i].stock%}袋</span>
              </div>
              <div class="main_num">
                <div><i class="iconfont icontubiao-3" data-index="0"></i></div>
                <div class="add_num">{%=o[i].num%}</div>
                <div><i class="iconfont iconjia" data-index="1"></i></div>
              </div>
            </div>
          </div>
        </div>
      {% } %}
    </script>
    <script type="text/x-tmpl" id="tmpl_search">
      {% for (var i=0; i<o.length; i++) { %}
        <span>{%=o[i]%}</span>
      {% } %}
    </script>
    <script src="http://www.jq22.com/jquery/jquery-1.10.2.js"></script>
    <script src="/js/search.js"></script>
    <script src="/js/commodity/commodityList.js"></script>
  </body>
</html>