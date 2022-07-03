$(function () {

    // // 加减器
    // $('body').on('click', '.commodity_classification_right_content2_num i', function (e) {
    //   var index = parseInt($(this).attr('data-index'));
    //   var t = $(this).siblings(".quantity");
    //   if(index==0){
    //     if (parseInt(t.val()) > 1) {  //判断数量值大于1时才可以减少
    //           t.val(parseInt(t.val()) - 1)
    //         } else {
    //           $(this).css("color", "#E6E6E6")
    //           t.val(0)
    //         }
    //   }else{
    //     $(this).siblings('.jian').css("color", "#FF807D")
    //     t.val(parseInt(t.val()) + 1);
    //   }
    // })

    var parentId;
    var name;

    // tab切换
    $('.commodity_classification_list').on('click', '.commodity_classification_list_left li', function (e) {
        $(this).addClass("active").siblings().removeClass("active");
        // 改变下面显示的类样式  添加一个selected
        $(".commodity_classification_list_right").eq($(this).index()).addClass("selected").siblings().removeClass("selected");

        parentId = $(this).attr("ppId");
        name = $(this).html();

        $("#categoryChildName").html(name);

        $(".commodity_classification_right_content").find("ul").each(function () {
            var ppId = $(this).attr("ppId");
            if (parentId === ppId) {
                $(this).show();
            } else {
                $(this).hide();
            }
        })

    })

    $('.whole').on("click", function (e) {
        window.location.href = "/purchase/commodityList?categoryId=" + parentId + "&categoryName=" + name;
    })


    $('.commodity_classification_list_left').find("li").get(0).click();

    // $('.commodity_classification_right_content').on('click', '.sublist li', function (e) {
    //     console.log(this);
    //     window.location.href = '/purchase/commodityList?categoryId=' + proId;
    // })

    // console.log($("#categoryChildName").html());


})