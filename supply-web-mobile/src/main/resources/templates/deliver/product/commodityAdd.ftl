<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0,user-scalable=no"/>
    <title>易订货-商品新增</title>
    <link rel="stylesheet" href="/deliver/css/global.css">
    <link rel="stylesheet" href="/deliver/css/commodity/commodityEdit.css">
    <link rel="stylesheet" href="http://at.alicdn.com/t/font_1100572_z3zcw27b6fk.css">
    <link rel="stylesheet" href="/css/layer.css">
    <style>
        span[yes] {
            background: #0BC6D7 !important;
        }

        .category {
            border: 0;
            height: 100%;
            line-height: 0.87rem;
            font-size: 0.3rem;
            width: 100%;
            text-align: right;
            background: transparent;
            -webkit-appearance: none;
        }
    </style>
</head>
<body>
<div class="edit_page">
    <div class="edit_list">
        <div>
            <div>商品名称</div>
            <div><input type="text" id="name" placeholder="请输入商品名称"></div>
        </div>
    </div>
    <div class="edit_type">
        <span>分类</span>
        <span>
            <select id="category" class="category" dir="rtl">
                <#if categoryList?exists>
                    <#list categoryList as c>
                        <#if c_index == 0>
                            <option value="${c.id!}" selected>${c.categoryName!}</option>
                        <#else>
                            <option value="${c.id!}">${c.categoryName!}</option>
                        </#if>
                    </#list>
                </#if>
            </select>
        </span>
    </div>
    <div class="edit_type">
        <span>二级分类</span>
        <span>
            <select id="category2" class="category" dir="rtl">
                <#if categoryList2?exists>
                    <#list categoryList2 as c>
                        <#if c_index == 0>
                            <option value="${c.id!}" selected>${c.categoryName!}</option>
                        <#else>
                            <option value="${c.id!}">${c.categoryName!}</option>
                        </#if>
                    </#list>
                </#if>
            </select>>
        </span>
    </div>
    <div class="edit_big_box">
        <div class="edit_image imageIcon" id="imageIcon"></div>
        <div class="add_image">
            <span>添加</span>
            <span>细节图</span>
            <span>0/1</span>
            <input type="file" name="img" class="addImageInput"
                   accept="image/*" onclick="addPhoto(this, 'imageIcon', 1)">
        </div>
        <div class="add_text">缩略图一张</div>
    </div>
    <div class="edit_big_box">
        <div class="edit_image imageList" id="imageList"></div>
        <div class="add_image">
            <span>添加</span>
            <span>细节图</span>
            <span>0/5</span>
            <input type="file" name="img" class="addImageInput"
                   accept="image/*" onclick="addPhoto(this, 'imageList', 5)">
        </div>
        <div class="add_text">展示图最多五张</div>
    </div>
    <div class="edit_big_box">
        <div class="edit_image imageDetails" id="imageDetails"></div>
        <div class="add_image">
            <span>添加</span>
            <span>细节图</span>
            <span>0/100</span>
            <input type="file" name="img" class="addImageInput"
                   accept="image/*" onclick="addPhoto(this, 'imageDetails', 100)">
        </div>
        <#--<div class="add_text">详情图最多五张</div>-->
    </div>
    <div class="edit_list">
        <div>
            <div>商品编码</div>
            <div><input type="text" id="barcode" placeholder="请输入商品编码"></div>
        </div>
        <div>
            <div>品牌</div>
            <div><input type="text" id="brand" placeholder="请输入商品品牌"></div>
        </div>
        <div>
            <div>单位</div>
            <div><input type="text" id="unit" placeholder="请输入单位，例：箱"></div>
        </div>
        <div>
            <div>单位详解</div>
            <div><input type="text" id="unitExplan" placeholder="请输入单位详解，例：10瓶"></div>
        </div>
        <div>
            <div>订货价</div>
            <div><input type="text" id="price" placeholder="请输入订货价"></div>
        </div>
        <div>
            <div>市场参考价</div>
            <div><input type="text" id="referencePrice" placeholder="请输入市场参考价"></div>
        </div>

        <div >
            <div>兑换方式</div>
            <div >
                <select style="margin-top: 10px;height: 25px" onchange="changeType()"  id="exchangeType"  >
                    <option value="0">积分兑换</option>
                    <option value="1">现金兑换</option>
                    <option value="2">混合兑换</option>
                </select>
            </div>
        </div>
        <div id="showPrice"  >
            <div>积分兑换价格</div>
            <div >
                <input type="text" value="0" id="exchangePrice" autocomplete="off" placeholder="请输入积分兑换价格">
            </div>
        </div>
        <div id="showCash" style="display:none">
            <div>现金兑换价格</div>
            <div>
                <input type="text" value="0" id="exchangeCash" autocomplete="off"   placeholder="请输入现金兑换价格">
            </div>
        </div>
        <div >
            <div>兑换备注</div>
            <div >
                <input type="text" id="exchangeRemark" autocomplete="off" placeholder="请输入兑换备注">
            </div>
        </div>
        <#--<div>
            <div>清算价</div>
            <div><input type="text" id="settlePrice" placeholder="请输入清算价"></div>
        </div>-->
    </div>
    <div class="edit_list">
        <div>
            <div>上架</div>
            <div>
                <div class="flag" style="background-color: rgb(11, 198, 215);"><span></span></div>
            </div>
        </div>
    </div>
    <div class="edit_list">
        <div>
            <div>预购</div>
            <div>
                <div class="willProduct" style="background-color: #e0e0e0;"><span style="right: 0.54rem" ></span></div>
            </div>
        </div>
    </div>
    <div class="edit_list">
        <div>
            <div>商品专区</div>
            <div>
                <div class="shopSelf" style="background-color: #e0e0e0;"><span style="right: 0.54rem" ></span></div>
            </div>
        </div>
    </div>
    <#--<div class="edit_list">
        <div>
            <div>商品介绍</div>
            <div><input type="text" id="remark" placeholder="请输入商品介绍"></div>
        </div>
    </div>-->
    <div class="edit_bottom">
        <div>返回</div>
        <div onclick="productAdd()">保存</div>
    </div>
    <!-- 弹窗 -->
    <div class="lower_mask">
        <div class="lower_popup">
            <div>确认返回？返回后数据清空。</div>
            <div class="lower_btn">
                <div>取消</div>
                <div onclick="window.history.go(-1)">确定</div>
            </div>
        </div>
    </div>
    <div style="height: 100px;width:100%;"></div>
</div>
</body>
<script src="/js/jquery.min.js"></script>
<script src="/js/layer.js"></script>
<script src="/deliver/js/mobileBUGFix.mini.js"></script>
<script src="/deliver/js/Sortable.js"></script>
<script src="/js/localResizeIMG.js"></script>
<script src="/js/exif.js"></script>
<script src="/deliver/js/commodity/commodityEdit.js"></script>
<script>
    var gridDemo = document.getElementById('edit_image')
    new Sortable(gridDemo, {
        animation: 150,
        ghostClass: ''
    });
    var ua = navigator.userAgent.toLowerCase();//获取浏览器的userAgent,并转化为小写——注：userAgent是用户可以修改的
    var isIos = (ua.indexOf('iphone') != -1) || (ua.indexOf('ipad') != -1);//判断是否是苹果手机，是则是true
    if (isIos) {
        $("input:file").removeAttr("capture");
    }
    function changeType(){
        var checkValue=$("#exchangeType").val();
        changeExchange(checkValue);
    }

    function changeExchange(type) {
        if(type == 0){
            $("#showPrice").show();
            $("#showCash").hide();
        }else if(type == 1){
            $("#showPrice").hide();
            $("#showCash").show();
        }else if(type == 2){
            $("#showPrice").show();
            $("#showCash").show();
        }
    }
</script>
</html>