$(function () {
    $("#btn-query").click(selectExchangeOrder);
    $("#btn-query-recent").click(selectRecentExchangeOrder);
    $("#order-info").on("click", "#btn-out", function () {
        let billno = $("#billno-out").val();
        layer.open({
            content: '您确定要对该自助兑换单进行[发放吗]？发放后，您的库存将减少！'
            , btn: ['确认', '再想想']
            , yes: function (index) {
                $.ajax({
                    type: "post",
                    url: "/purchase/exchangeOut/" + billno,
                    success: function (data) {
                        console.log(data);
                        layer.open({
                            title: [
                                '发放结果',
                                'background-color: #FF4351; color:#fff;'
                            ]
                            , content: data.msg
                        });
                    }

                });
            }
        });
    })

    $('#dataList').on('click','.order-billno',function(){
        var billno = $(this).html();
        $("#billno").val(billno);
        selectExchangeOrder();
        $('body,html').scrollTop(0)
        //清空最近列表。


    });
    //创建MeScroll对象,内部已默认开启下拉刷新,自动执行up.callback,重置列表数据;
    var mescroll = new MeScroll("mescroll", {
        up: {
            clearEmptyId: "dataList", //1.下拉刷新时会自动先清空此列表,再加入数据; 2.无任何数据时会在此列表自动提示空
            callback: getListData, //上拉回调,此处可简写; 相当于 callback: function (page) { getListData(page); }
        }
    });

    /*联网加载列表数据  page = {num:1, size:10}; num:当前页 从1开始, size:每页数据条数 */
    function getListData(page) {
        //联网加载数据
        console.log("page.num==" + page.num);
        getListDataFromNet(page.num, page.size, function (data) {
            //联网成功的回调,隐藏下拉刷新和上拉加载的状态;
            mescroll.endSuccess(data.data.list.length);//传参:数据的总数; mescroll会自动判断列表如果无任何数据,则提示空;列表无下一页数据,则提示无更多数据;
            //设置列表数据,因为配置了emptyClearId,第一页会清空dataList的数据,所以setListData应该写在最后;
            setListData(data.data.list);

        }, function () {
            //联网失败的回调,隐藏下拉刷新和上拉加载的状态;
            mescroll.endErr();
        });
    }

    /*设置列表数据*/
    function setListData(list) {
        let html = tmpl("recent-order",list);
        // console.log(html);
        $("#dataList").append(html);
    }

    /*联网加载列表数据*/
    function getListDataFromNet(pageNo, pageSize, successCallback, errorCallback) {
        //延时一秒,模拟联网
        $.post("/purchase/exchangeCheck/recent", {
            "pageNo": pageNo,
            "pageSize": pageSize
        }, function (data) {
            console.log(data);
            successCallback(data);
        })
    }

});

/**快速查询 索引标记标记*/
let recent = 1;

function selectRecentExchangeOrder() {
    let recenNo = recent;
    recent++;
    $.ajax({
        type: "get",
        url: "/purchase/exchangeCheck/recent/" + recenNo,
        success: function (data) {
            console.log(data);
            $("#order-info").html(tmpl("order-info-tmpl", data))
        }
    });
}

function selectExchangeOrder() {
    let billno = $("#billno").val().replace(/ /g, "");
    if (billno == "") {
        layer.open({
            title: [
                '提示',
                'background-color: #FF4351; color:#fff;'
            ]
            , content: '请务必输入【自助兑换订单号】或【本网点POS凭证号】'
        });
    }
    $.ajax({
        type: "get",
        url: "/purchase/exchangeCheck/" + billno,
        success: function (data) {
            console.log(data);
            $("#order-info").html(tmpl("order-info-tmpl", data))
        }
    });
}






