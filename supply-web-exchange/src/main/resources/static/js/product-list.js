$(function () {

    $('.goods-list li').hover(function(){
        $(this).addClass('hover')
        $(this).find('.btn').html('加入购物车');
        $(this).find('.btn').addClass('active');
    },function(){
        $(this).removeClass('hover')
        $(this).find('.btn').html('兑换');
        $(this).find('.btn').removeClass('active');

    });
    //只看有货
    //默认非选中状态
    $('#cbx').attr('checked',false).attr("value","false");
    $('#cbx').on('click',function(){
        var isChecked = $('#cbx').attr('checked');
        if(isChecked){
            $('#cbx').val("true");
        }else {
            $('#cbx').val("false");
        }
        //刷新礼品
        refreshList(1, $(".pageSize").html(),$(".classify .val").attr("value"),$("#price-order").html());
    });

    var flag = true;
    //礼品分类
    $('.classify span').click(function(e){
        // if($(".dropdown").is(':hidden')){
            // $('.dropdown').show()
            // $(".classifybgbt").show()
        // }else{
            // $('.dropdown').hide()
            // $(".classifybgbt").hide()

        // }
        if(flag){
            $('.goods-list').hide();
            $('.classify-pic').show();
            flag = false
        } else {
            $('.goods-list').show();
            $('.classify-pic').hide();
            flag = true
        }
        $(document).on('click', function () {
            // $(".classifybgbt").hide();
            // $('.dropdown').hide();
            $('.classify-pic').hide();
            $('.goods-list').show()

        });
        e.stopPropagation();
    });
    //分类点击事件
    $('.classify-pic a').click(function(){
        var txt = $(this).text();
        var val = $(this).attr("value");
        $('.val').text(txt)
        $('.val').attr("value",val);
        $('.classify-pic').hide();
        $('.goods-list').show();
        // $(".classifybgbt").hide()
        //请求新的分类数据
        refreshList(1, $(".pageSize").html(),$(".classify .val").attr("value"),$("#price-order").html());
    })


    //点击礼品 展开礼品详情
    $("#product-contain").on('click', '.img', function () {
        var pid = $(this).parent().find(".pid_none").html();
        $.post("/product/"+pid, {}, function (data) {
            if(data.status == 200) {
                $("#product-detail-contain").empty();
                $("#product-detail-template").tmpl(data.data).appendTo("#product-detail-contain");
                 // var top = ($(window).height() - $('.contents').height())/2 + 'px';
                 // var left = ($(window).width() - $('.contents').width())/2 + 'px';
                $(".gift_details").show()
                $('.gift_details').height($(window).height()+100)
                // $(".contents").css({'top':top,'left':left})
                $(".sm_container_con").css("left", 0);
                var fl_w = $(".sm_container_con").width();
                var flb_w = $(".sm_container").width();
                var nav_w = $(".sm_container_con .sm_images").first().width();
                //console.log(nav_w)

                if(data.data.stock <= 0){
                    var imsg = "该礼品库存为0，可以支付积分进行预购(积分使用后不予退还，礼品到货后给予发放)！请咨询柜员。";
                    alert(imsg);
                    // alertPopMsg(imsg,"makeSure");
                }

                $(".sm_container_con").on('click','.sm_images', function (event) {
                    event.preventDefault();
                    nav_w = $(this).width();
                    var fn_w = ($(".img_left").width() - nav_w) / 2;
                    var fnl_l;
                    var fnl_x = parseInt($(this).position().left);
                    if (fnl_x <= fn_w) {
                        fnl_l = 0;
                    } else if (fn_w - fnl_x <= flb_w - fl_w) {
                        fnl_l = flb_w - fl_w;
                    } else {
                        fnl_l = fn_w - fnl_x;
                    }

                    $(".sm_container_con").animate({
                        "left": fnl_l
                    }, 300);



                });

            }else {
                //异常
                alert("数据获取失败！请稍后重试。");
            }
        }, "json");

    });
    var winH = document.body.clientHeight+document.body.scrollHeight||document.documentElement.clientHeight+document.documentElement.scrollHeight
    //console.log(winH)
    //礼品详情，减图标，效果
    $("#product-detail-contain").on("click",".del_icon",function () {
        var count = parseInt($(this).parent().find(".result").html());
        if(count == 1){
            return;
        }else {
            $(this).parent().find(".result").html(count - 1)
        }
    });
    //礼品详情，加图标，效果
    $("#product-detail-contain").on("click",".add_icon",function () {
        var count = parseInt($(this).parent().find(".result").html());
        $(this).parent().find(".result").html(count + 1)
    });

    //点击礼品详情 的关闭按钮
    $(".gift_details").on('click', '.icon_close', function () {
        $(".gift_details").hide()
    });

    //点击礼品详情的小图
    $(".gift_details").on('click', '.sm_images', function () {
        $('.sm_container .sm_images').removeClass('selected');
        $(this).addClass("selected");
        var img_src = $(this).find('img').attr('src')
        $(".big_images").find('img').attr('src',img_src)
    })

    //点击用户信息提示
    $('.userbar .arrow-bt').click(function(){
        if($('.userbar .jf_tip').css('display')=='block'){
            $(".userbar .jf_tip").hide()
        }else{
            $(".userbar .jf_tip").show()
        }
    });

    //点击兑换,价格,积分产生的sort函数
    $(".sort .icon-sort").click(function(){
        if((!$(this).is('.desc')) && (!$(this).is('.asc'))){
            $(this).addClass('asc');
            $("#price-order").html("asc");
        }else if($(this).is('.desc')){
            $(this).removeClass('desc')
            $(this).addClass('asc')
            $("#price-order").html("asc");
        }else if($(this).is('.asc')){
            $(this).removeClass('asc');
            $(this).addClass('desc')
            $("#price-order").html("desc");
        }
        refreshList(1, $(".pageSize").html(),$(".classify .val").attr("value"),$("#price-order").html());
    });

    refreshList(1, $(".pageSize").html(),$(".classify .val").attr("value"),$("#price-order").html());

    //加入购物车
    $("body").on('click', '.add2cart', function () {
        var id = $(this).parent().find(".pid_none").html();
        var count = $("#product-detail-contain").find(".result").html();
        $.post("/cart/add", {"productId": id, "count": count}, function (data) {
            $(".gift_details").hide();
        })
    });
    //加入购物车and进入购物车
    $("body").on('click', '.add2cartAndGo', function () {
        var id = $(this).parent().find(".pid_none").html();
        var count = $("#product-detail-contain").find(".result").html();
        $.post("/cart/add", {"productId": id, "count": count}, function (data) {
            if(data.status == 200){
                window.location.href = "/cart/";
            }else {
                alert(data.msg);
            }
        })
    });
    $("body").on('click', '.add2cartAndGo-A', function () {
        var id = $(this).parent().find(".pid_none").html();
        var count = 1;
        $.post("/product/"+id, {}, function (data) {
            if(data.status == 200) {
                if(data.data.stock <= 0){
                    var imsg = "该礼品库存为0，可以支付积分进行预购(积分使用后不予退还，礼品到货后给予发放)！请咨询柜员。点击[确认]进行预购,点击[取消]退出兑换。";
                    if(confirm(imsg)){

                    }else {
                        return;
                    }
                }

                $.post("/cart/add", {"productId": id, "count": count}, function (data) {
                    if(data.status == 200){
                        window.location.href = "/cart/";
                    }else {
                        alert(data.msg);
                    }
                });

            }else {
                //异常
                alert("数据获取失败！请稍后重试。");
            }
        }, "json");

    });


    $(".prePage").click(function () {
        if ($(this).attr("hasPreviousPage") == "true") {
            refreshList($(this).attr("value"), $(".pageSize").html(),$(".classify .val").attr("value"),$("#price-order").html());
        }
    });
    $(".nextPage").click(function () {
        if ($(this).attr("hasNextPage") == "true") {
            refreshList($(this).attr("value"), $(".pageSize").html(),$(".classify .val").attr("value"),$("#price-order").html());
        }
    });
    $(".page-first").click(function () {
        if (!($(this).attr("isFirstPage") == "true")) {
            refreshList(1, $(".pageSize").html(),$(".classify .val").attr("value"),$("#price-order").html());
        }
    });
    $(".page-last").click(function () {
        if (!($(this).attr("isLastPage") == "true")) {
            refreshList($(".pages").html(), $(".pageSize").html(),$(".classify .val").attr("value"),$("#price-order").html());
        }
    });
    $(".logo img").click(function () {
        if(orgSettingCounter == 0){
            orgSettingCounter = 5;
            alertPopMsg("请输入[网点号]:","input");
            //弹出输入框
        }else {
            orgSettingCounter -= 1;
        }
    });
    $(".pop-msg img").click(function () {
        $(".pop-msg").hide();
    });
    $("#reset-branchno").click(function () {
        var branchno = $("#branchno").val().replace(/ /g,"");
        if(branchno.length > 0){
            $.post("/ip2branchno", {"branchno": branchno}, function (data) {
                if(data.status == 200){
                    alertPopMsg("设置成功","info");
                    setTimeout(function () {
                        window.location.reload();
                    },2000)
                }else {
                    alertPopMsg(data.msg,"input");
                }
            }, "json");
        }
    });



});

//网点设置，计数器
var orgSettingCounter = 5;

//弹窗方法；
function alertPopMsg(msg,stat) {
    switch (stat){
        case "info":
            $(".pop-msg img").attr("src","/images/duihao-green.png");
            $(".pop-msg .branchno-input").hide();
            $(".pop-msg .makeSure").hide();
            break;
        case "input":
            $(".pop-msg img").attr("src","/images/tanhao-blue.png");
            $(".pop-msg .branchno-input").show();
            $(".pop-msg .makeSure").hide();
            break;
        case "makeSure":
            $(".pop-msg img").attr("src","/images/tanhao-blue.png");
            $(".pop-msg .branchno-input").hide();
            $(".pop-msg .makeSure").show();
    }
    $(".pop-msg .des").html(msg);
    $(".pop-msg").show();
}

function refreshList(pageNo, pageSize, categoryId, priceOrder) {
    //只看有货
    var onlyStock = $('#cbx').val();
    $.post("/product", {"pageNo": pageNo, "pageSize": pageSize,"categoryId":categoryId,"priceOrder":priceOrder,"onlyStock":onlyStock}, function (data) {
        $("#product-contain").empty();
        refreshNavi(data.data)
        $("#product-template").tmpl(data.data.list).appendTo("#product-contain");
    }, "json");
}

function refreshNavi(pageInfo) {
    $(".prePage").attr("value", pageInfo.prePage);
    $(".prePage").attr("hasPreviousPage", pageInfo.hasPreviousPage);
    $(".nextPage").attr("value", pageInfo.nextPage);
    $(".nextPage").attr("hasNextPage", pageInfo.hasNextPage);
    $(".page-first").attr("isFirstPage", pageInfo.isFirstPage);
    $(".page-last").attr("isLastPage", pageInfo.isLastPage);
    $(".pageNum").html(pageInfo.pageNum);
    $(".pages").html(pageInfo.pages);

}

