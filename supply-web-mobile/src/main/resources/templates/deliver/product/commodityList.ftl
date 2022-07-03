<head>
    <meta charset="utf-8">
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0,user-scalable=no"/>
    <title>易订货-商品列表</title>
    <link rel="stylesheet" href="/deliver/css/global.css">
    <link rel="stylesheet" href="/deliver/css/commodity/commodityList.css">
    <link rel="stylesheet" href="/css/mescroll.css">
    <link rel="stylesheet" href="http://at.alicdn.com/t/font_1100572_hmrfyv2jbmm.css">
    <link rel="stylesheet" href="http://at.alicdn.com/t/font_202335_gwbit3wv0zm.css">
    <style>
        .hide_popup {
            height: auto !important;
        }

        .mescroll {
            position: fixed;
            top: 1.68rem;
            bottom: 1rem;
            height: auto;
        }

        .cpmmodity_top {
            z-index: 999;
        }

        .hide {
            display: none;
        }

        .item_main > div:nth-child(2) {
            margin-top: 0;
            font-size: .3rem;
            padding: .3rem 0;
            height: 1rem;
        }

        .search-input {
            background: transparent;
            padding: 5px 10px;
            font-size: .3rem;
            width: 90%;
        }
    </style>
</head>
<body>
<div class="commodity_page">
    <div class="cpmmodity_top">
        <div class="search">
            <div><i class="iconfont icontubiaozhizuomoban-17"></i>
                <input type="text" name="text" class="search-input" placeholder="请输入商品名称">
            </div>
            <span><i class="iconfont icontubiao_huaban"></i></span>
        </div>
        <div class="tab">
            <div><span data-status="1" data-index="0" class="tab_act">在售</span></div>
            <div><span data-status="2" data-index="1">已下架</span></div>
            <div><span data-index="2"><i class="iconfont icontubiaozhizuomoban-19"></i>商品分类</span></div>
        </div>
    </div>
    <div id="mescroll0" class="mescroll">
        <div class="commodity_list" id="dataList0">
        </div>
    </div>
    <div id="mescroll1" class="mescroll hide">
        <div class="commodity_list" id="dataList1"></div>
    </div>
    <!-- 底部导航栏 -->
    <input hidden id="productId">
    <input hidden id="productStatus">
    <div class="bottom_bar" id="bottom_bar" data-current="4"></div>
    <!-- 弹窗 -->
    <div class="lower_mask">
        <div class="lower_popup">
            <div class="text">是否下架本商品？</div>
            <div class="lower_btn">
                <div>取消</div>
                <div>确定</div>
            </div>
        </div>
    </div>
    <!-- 气泡 -->
    <div class="lower_toast">下架成功！</div>
    <!-- 抽屉 -->
    <div class="mask">
        <div class="mask_main">
            <div class="mask_title">
                <span></span>
                <span>筛选条件</span>
            </div>
            <div class="mask_line"></div>
            <div class="mask_type">
                <div class="mask_type_title" data-status="0">商品分类<i class="iconfont iconarrow-down-copy"></i></div>
                <div class="type_list">
                    <div class="type_list_item type_act">
                        <span>所有分类</span>
                        <span><i class="iconfont icontubiaozhizuomoban-10"></i></span>
                    </div>
                    <#if categoryList?exists && (categoryList?size > 0)>
                        <#list categoryList as c>
                            <div class="type_list_item" categoryId="${c.id}">
                                <span>${c.categoryName!}</span>
                                <span><i class="iconfont iconyuan"></i></span>
                            </div>
                        </#list>
                    </#if>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
<script src="/deliver/js/tmpl.min.js"></script>
<script src="/js/jquery.min.js"></script>
<script src="/deliver/js/global.js"></script>
<script src="/deliver/js/commodity/commodityList.js"></script>
<script src="/js/store2.min.js"></script>
<script src="/js/mescroll.js"></script>
<script src="/js/handlebars-1.0.0.beta.6.js"></script>
<script type="text/template" id="list">
    {{#each this}}
    <div class="commodity_list_item" productId="{{id}}">
        <div class="item_img" style="background-image:url({{image}})"></div>
        <div class="item_main">
            <div>{{name}}</div>
            <div>
                {{#equal checkStatus 1}}
                <span style="color:#12c7d7">(未审核)</span>
                {{/equal}}
                {{#equal checkStatus 2}}
                <span style="color:#a0a0a0">(审核通过)</span>
                {{/equal}}
                {{#equal checkStatus 3}}
                <span style="color:#ef2222;">(审核未通过)</span>
                {{/equal}}
            </div>
            <div style="position: relative">
                <div class="price"><span>￥</span><span>{{price}}</span></div>
                <div class="more" moreId="{{id}}" productStatus="{{productStatus}}">
                    <i class="iconfont icontubiaozhizuomoban-9"></i>
                </div>
                <div class="hide_popup yyy_{{id}}">
                    <div data-index="0" productId="{{id}}"><i class="iconfont"></i>编辑</div>
                    {{#equal productStatus 1}}
                    <div data-index="1" onclick="setProduct({{id}},{{productStatus}})">
                        <i class="iconfont">下架</i>
                    </div>
                    {{/equal}}
                    {{#equal productStatus 2}}
                    <div data-index="1" onclick="setProduct({{id}},{{productStatus}})">
                        <i class="iconfont">上架</i>
                    </div>
                    {{/equal}}
                </div>
                <#--<div data-index="2"><i class="iconfont"></i>删除</div>-->
            </div>
        </div>
    </div>
    </div>
    {{/each}}
</script>
<script>

    /**
     * 获取商品列表
     * @param productName 商品名称/模糊查询
     * @param status 1 在售/ 2 下架
     * @param categoryId 商品分类
     * @param page 第几页
     * @param size 一页显示多少条
     */
    var productName = "";
    var status = 1;
    var page = 1;
    var size = 10;
    var categoryId = $(".type_act").attr("categoryId");

    var curNavIndex = 0;

    var mescrollArr = new Array(2);

    var mescroll = null

    var flag = false

    //初始化首页
    mescrollArr[0] = initMescroll("mescroll0", "dataList0");


    function initMescroll(mescrollId, clearEmptyId) {

        //创建MeScroll对象,内部已默认开启下拉刷新,自动执行up.callback,刷新列表数据;
        mescroll = new MeScroll(mescrollId, {
            //上拉加载的配置项
            up: {
                callback: getListData, //上拉回调,此处可简写; 相当于 callback: function (page) { getListData(page); }
                noMoreSize: 4, //如果列表已无数据,可设置列表的总数量要大于半页才显示无更多数据;避免列表数据过少(比如只有一条数据),显示无更多数据会不好看; 默认5
                empty: {
                    tip: "暂无相关数据~"
                },
                clearEmptyId: clearEmptyId //相当于同时设置了clearId和empty.warpId; 简化写法;默认null
            }
        });
        return mescroll;
    }

    function getListData(page) {
        //联网加载数据
        console.log("status=" + status + ", page.num=" + page.num + ",curNavIndex=" + curNavIndex);
          if(flag){
              page.num=1
              flag=false
          }


        getProduct(productName, categoryId, status, curNavIndex, page.num, page.size, function (data) {
            console.log(data)
            nodata  = data.data.size
            //联网成功的回调,隐藏下拉刷新和上拉加载的状态;
            console.log("data.length=" + data.data.size + " " + "data.total="+ data.data.total);
            mescrollArr[curNavIndex].endSuccess(data.data.size,data.data.total);//传参:数据的总数; mescroll会自动判断列表如果无任何数据,则提示空;列表无下一页数据,则提示无更多数据;
            var tpl = $("#list").html();
            var template = Handlebars.compile(tpl);
            var html = template(data.data.list);
            $('#dataList' + curNavIndex).append(html);

        }, function () {
            //联网失败的回调,隐藏下拉刷新和上拉加载的状态;
            mescrollArr[curNavIndex].endErr();
        });
    }

    function getProduct(productName, categoryId, status, curNavIndex, page, size, successCallback) {
        $.post("/deliver/productListByOrg", {
            "productName": productName,
            "status": status,
            "categoryId": categoryId,
            "page": page,
            "size": size
        }, function (data) {

            successCallback(data);

        })

    }

    Handlebars.registerHelper("equal", function (v1, v2, options) {
        if (v1 == v2) {
            return options.fn(this);
        } else {
            return options.inverse(this);
        }
    });


</script>

<script type="text/x-tmpl" id="tmpl_bottom">
    {% for (var i=0; i<o.length; i++) { %}
      <div data-index="{%=i%}">
        <i class="iconfont {%=o[i].icon%}"></i>
        <p>{%=o[i].name%}</p>
      </div>
    {% } %}


</script>
