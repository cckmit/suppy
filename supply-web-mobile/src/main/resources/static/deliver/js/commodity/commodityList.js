$(function () {
    /* 选项卡切换 */
    $('.tab div span').on('click', function () {
        var index = $(this).attr('data-index')
        if (index != 2) {
            $(this).addClass('tab_act').parent('div').siblings('div').find('span').removeClass('tab_act')
            status = Number($(this).attr("data-status"));
            $('.type_list_item span i').removeClass('icontubiaozhizuomoban-10').addClass('iconyuan')
            $('.type_list_item span i').eq(0).removeClass('iconyuan').addClass('icontubiaozhizuomoban-10')
            $('.type_list_item').eq(0).addClass('type_act').siblings().removeClass('type_act')
            if (curNavIndex != index) {
                categoryId = -1
                //隐藏当前列表
                $("#mescroll" + curNavIndex).hide();
                //显示对应的列表
                curNavIndex = index;

                $("#mescroll" + curNavIndex).show();

                //取出菜单所对应的mescroll对象,如果未初始化则初始化
                 if (mescrollArr[index] == null) mescrollArr[index] = initMescroll("mescroll" + index, "dataList" + index);
            }


        } else {
            $(".mask").css("display", "block");
            $(".mask").animate({opacity: '1'}, '500');
            $('.mask_main').addClass('popup_ani').removeClass('popup_to_right')
        }
    })
    $('.search>span').on('click', function () {
        window.location.href = '/deliver/commodityAddPage'
    })
    $('body').on('click', '.mask', function () {
        $('.mask_main').addClass('popup_to_right').removeClass('popup_ani')
        $(this).animate({opacity: '0'}, '1000', function () {
            $(this).css("display", "none")
        })
    })
    /**
     * yyy改
     */
    var id;
    /* 点击更多 */
    $('body').on('click', '.more', function (e) {
        e.stopPropagation();

        var moreId = $(this).attr("moreId");
        if (id != moreId) {
            $(".hide_popup").hide();
        }
        id = moreId;
        $('.yyy_' + id).toggle();
    })


    function updateProductStatus(id, productStatus) {
        $.post("/deliver/updateProductStatus", {"id": id}, function (data) {
            console.log(data)
            var status = data.status;
            var msg = data.msg;
            if (status == 200) {
                if (productStatus == 1) {
                    $('.lower_toast').html('下架成功')
                    mescrollArr[0] = initMescroll("mescroll0", "dataList0");
                    $('#mescroll0 div:nth-child(2),#mescroll0 div:nth-child(4)').remove()
                } else {
                    $('.lower_toast').html('上架成功')
                    mescrollArr[1] = initMescroll("mescroll1", "dataList1");
                    $('#mescroll1 div:nth-child(2),#mescroll1 div:nth-child(4)').remove()
                }
                $('.lower_toast').show()
                setTimeout(function () {
                    $('.lower_toast').hide()
                }, 1000);
            } else {
                alert(msg)
            }
        })
    }

    /* 下架商品 */
    $('body').on('click', '.hide_popup div', function (e) {
        e.stopPropagation()
        var index = $(this).attr('data-index');
        switch (Number(index)) {
            case 0:
                window.location.href = '/deliver/commodityEdit?id=' + $(this).attr("productId");
                break;
            case 1:
                $('.lower_mask').show()
                $(this).parents('.hide_popup').hide();
                break;
            case 2:
                $('.lower_mask').show();
                $('.lower_mask .lower_popup>div:first-child').html('是否删除本商品');
                $(this).parents('.hide_popup').hide();
                break;
        }
    })
    /* 确认下架 */
    $('.lower_btn div:nth-child(2)').on('click', function () {
        $(this).parents('.lower_mask').hide()

        var productId = $("#productId").val();
        var productStatus = $("#productStatus").val();

        updateProductStatus(productId, productStatus);
    })
    $('.lower_btn div:first-child').on('click', function () {
        $(this).parents('.lower_mask').hide();
    })
    /* 点击商品分类 */
    $('.mask_type_title').on('click', function (e) {
        e.stopPropagation()
        if ($(this).find('i').hasClass('retract')) {
            $(this).find('i').removeClass('retract')
            $('.type_list').animate({height: '7.67rem', opacity: 1}, 500)
        } else {
            $(this).find('i').addClass('retract')
            $('.type_list').animate({height: 0, opacity: 0}, 500)
        }
    })

    //商品分类筛选
    $('.type_list_item').on('click', function (e) {
        e.stopPropagation()
        $(this).addClass('type_act').siblings().removeClass('type_act')
        $(this).find('span:nth-child(2) i').removeClass('iconyuan').addClass('icontubiaozhizuomoban-10')
        $(this).siblings().find('span:nth-child(2) i').removeClass('icontubiaozhizuomoban-10').addClass('iconyuan')
        categoryId = $(".type_act").attr("categoryId")
        if (categoryId == undefined) {
            categoryId = -1
        }
        console.log(categoryId)
        if (status == 1) {
            mescrollArr[0] = initMescroll("mescroll0", "dataList0")
            $('#mescroll0 div:nth-child(2),#mescroll0 div:nth-child(4)').remove()
        }
        if (status == 2) {
            mescrollArr[1] = initMescroll("mescroll1", "dataList1")
            $('#mescroll1 div:nth-child(2),#mescroll1 div:nth-child(4)').remove()
        }

        $('.mask_main').addClass('popup_to_right').removeClass('popup_ani')
        $('.mask').animate({opacity: '0'}, '1000', function () {
            $(this).css("display", "none")
        })
    })
    $('.search-input').bind('keyup', function (event) {
        productName = $(this).val();
        if(productName==''){
            return false
        }
        if (event.keyCode == "13") {
            if (status == 1) {
                mescrollArr[0] = initMescroll("mescroll0", "dataList0")
                $('#dataList0').html('')
                $('#mescroll0 div:nth-child(2),#mescroll0 div:nth-child(4)').remove()
                flag = true
            }
            if (status == 2) {
                mescrollArr[1] = initMescroll("mescroll1", "dataList1")
                $('#dataList1').html('')
                $('#mescroll1 div:nth-child(2),#mescroll1 div:nth-child(4)').remove()
                flag = true
            }
        }
    });




    $('body').on('click', '.commodity_list_item', function () {
        var productId = $(this).attr("productId");
        window.location.href = '/deliver/commodityDetail?id=' + productId;
    })
})

function setProduct(productId, productStatus) {
    console.log(productId)
    $("#productId").val(productId);
    $("#productStatus").val(productStatus);
}
