var flag = 0;
var willProduct = 0;
var shopSelf = 0;
$(function () {
    $('body').on('click', '.edit_item i', function (index) {
        $(this).parents('.edit_item').remove()
        var length = $(this).attr("length");
        var divId = $(this).attr("divId");
        $('#' + divId).parent().find('.add_image span:nth-child(3)').html($('#' + divId + ' .edit_item').length + '/' + length)
    })
    /* 开关*/
    $('.willProduct').on('click', function () {

        if (willProduct == 0) {
            $(this).find('span').animate({right: '0.03rem'}, 300, function () {
                $(this).parents('.willProduct').css({'background-color': '#0BC6D7'})
            })
            willProduct = 1
        } else {
            $(this).find('span').animate({right: '0.54rem'}, 300, function () {
                $(this).parents('.willProduct').css({'background-color': '#e0e0e0'})
            })
            willProduct = 0
        }
    })

    $('.flag').on('click', function () {
        console.log(flag)
        if (flag == 0) {
            $(this).find('span').animate({right: '0.54rem'}, 300, function () {
                $(this).parents('.flag').css({'background-color': '#e0e0e0'})
            })
            flag = 1
        } else {
            $(this).find('span').animate({right: '0.03rem'}, 300, function () {
                $(this).parents('.flag').css({'background-color': '#0BC6D7'})
            })
            flag = 0
        }
    })
    $('.shopSelf').on('click', function () {

        if (shopSelf == 0) {
            $(this).find('span').animate({right: '0.03rem'}, 300, function () {
                $(this).parents('.shopSelf').css({'background-color': '#0BC6D7'})
            })
            shopSelf = 1
        } else {
            $(this).find('span').animate({right: '0.54rem'}, 300, function () {
                $(this).parents('.shopSelf').css({'background-color': '#e0e0e0'})
            })
            shopSelf = 0
        }
    })
    $('.edit_bottom div:first-child').on('click', function () {
        $('.lower_mask').show()
    })
    $('.lower_btn div').on('click', function () {
        $('.lower_mask').hide()
    })
})


$(function () {
    var categoryId = localStorage.getItem("categoryId");
    var categoryName = localStorage.getItem("categoryName");
    localStorage.setItem("categoryName", "")
    localStorage.setItem("categoryId", "")
    if (categoryName != '' && categoryName != undefined) {
        $("#category").html(categoryName);
    }
    if (categoryId != '' && categoryId != undefined) {
        $("#category").attr("categoryId", categoryId);
    }


})

function addPhoto(obj, label, length) {
    $(obj).localResizeIMG({
        width: 800,// 宽度
        quality: 0.5, // 压缩参数 1 不压缩 越小清晰度越低
        success: function (result, file) {
            var base64Img = new Image();
            base64Img = result.base64;
            var fileName = file.name
            console.log(fileName)
            console.log(base64Img)
            if ($('.' + label + ' .edit_item').length == length) {
                layAlert("只能添加" + length + "张图片");
                return;
            }
            $.post('/deliver/uploadImg', {
                "base64Img": base64Img,
                "fileName": fileName
            }, function (data) {
                var status = data.status;
                var msg = data.msg;
                var d = data.data;
                if (status == 200) {
                    var html = '<div class="edit_item" itemImg="' + d + '" style="background-image:url(' + base64Img + ')">' +
                        '<i length="' + length + '" divId="' + label + '" class="iconfont icontubiaozhizuomoban-11"></i>' +
                        '<div>' + (length == 1 ? "缩列图" : "详情图") +
                        '</div>' +
                        '</div>';
                    $('#' + label).append(html);

                } else {
                    layAlert(msg);
                }
            })
            $('#' + label).parent().find('.add_image span:nth-child(3)').html($('.' + label + ' .edit_item').length + 1 + '/' + length)
        }
    });
}

function productAdd() {
    var image = "";//缩略图
    var imageList = "";//展示图
    var imgDetails = "";//详情图
    var productStatus = "1";
    var productLimit = "1";
    var productPrefecture = "0";
    var name = $("#name").val();
    var unit = $("#unit").val();
    var price = $("#price").val();
    var brand = $("#brand").val();
    var remark = $("#remark").val();
    var barcode = $("#barcode").val();
    var unitExplan = $("#unitExplan").val();
    var referencePrice = $("#referencePrice").val();
    var categoryId = $("#category2 option:selected").val();

    var exchangeCash = $("#exchangeCash").val();
    var exchangePrice = $("#exchangePrice").val();
    var exchangeType = $("#exchangeType option:selected").val();
    var exchangeRemark = $("#exchangeRemark").val();

    if (name == "") {
        layAlert("请输入商品名称");
        return;
    }
    var count1 = $("#imageIcon .edit_item").length;
    if (count1 <= 0) {
        layAlert("请选择商品缩略图图片");
        return;
    } else {
        image = $("#imageIcon .edit_item").attr("itemImg");
    }
    var index = 0;
    var count2 = $("#imageList .edit_item").length;
    if (count2 <= 0) {
        layAlert("请选择商品展示图图片");
        return;
    } else {
        $("#imageList .edit_item").each(function () {
            if (index == 0) {
                imageList += $(this).attr("itemImg");
            } else {
                imageList += ",," + $(this).attr("itemImg");
            }
            index++;
        })
    }
    index = 0;
    var count3 = $("#imageDetails .edit_item").length;
    if (count3 <= 0) {
        layAlert("请选择商品详情图图片");
        return;
    } else {
        $("#imageDetails .edit_item").each(function () {
            if (index == 0) {
                imgDetails += $(this).attr("itemImg");
            } else {
                imgDetails += ",," + $(this).attr("itemImg");
            }
            index++;
        })
    }
    if (categoryId == "") {
        layAlert("请选择商品分类");
        return;
    }
    if (barcode == "") {
        layAlert("请输入商品编号");
        return;
    }
    if (brand == "") {
        layAlert("请输入商品品牌");
        return;
    }
    if (unit == "") {
        layAlert("请输入商品单位");
        return;
    }
    if (price == "" || !checkNumber(price)) {
        layAlert("请输入正确的商品订货价");
        return;
    }
    if (referencePrice == "" || !checkNumber(referencePrice)) {
        layAlert("请输入正确的商品市场参考价");
        return;
    }
    /*if (settlePrice == "" || !checkNumber(settlePrice)) {
        layAlert("请输入正确的商品清算价");
        return;
    }*/
    /*if (remark == "") {
        layAlert("请输入商品介绍");
        return;
    }*/


    $.post("/deliver/productAdd", {
        "name": name,
        "unit": unit,
        "image": image,
        "brand": brand,
        "price": price,
        "remark": remark,
        "barcode": barcode,
        "imageList": imageList,
        "imgDetails": imgDetails,
        "unitexplan": unitExplan,
        "categoryId": categoryId,
        "referencePrice":referencePrice,
        "productStatus": flag==1?1:2,
        "productPrefecture":shopSelf,
        "purchaseOrnot": willProduct==1?0:1,
        "exchangeCash":exchangeCash,
        "exchangePrice":exchangePrice,
        "exchangeType":exchangeType,
        "exchangeRemark":exchangeRemark
    }, function (data) {
        var status = data.status;
        var msg = data.msg;
        if (status == 200) {
            layAlert("新增成功");
            setTimeout(function () {
                self.location = document.referrer;
            }, 1200)
        } else {
            layAlert(msg);
        }
    })
}

function productModify() {
    var image = "";
    var imageList = "";
    var imgDetails = "";
    var productStatus = "1";
    var name = $("#name").val();
    var unit = $("#unit").val();
    var price = $("#price").val();
    var brand = $("#brand").val();
    var remark = $("#remark").val();
    var barcode = $("#barcode").val();
    var productId = $("#productId").val();
    var unitExplan = $("#unitExplan").val();
    var referencePrice = $("#referencePrice").val();
    var categoryId = $("#category2 option:selected").val();

    var exchangeCash = $("#exchangeCash").val();
    var exchangePrice = $("#exchangePrice").val();
    var exchangeType = $("#exchangeType option:selected").val();
    var exchangeRemark = $("#exchangeRemark").val();


    if (name == "") {
        layAlert("请输入商品名称");
        return;
    }
    var count1 = $("#imageIcon .edit_item").length;
    if (count1 <= 0) {
        layAlert("请选择商品缩略图图片");
        return;
    } else {
        image = $("#imageIcon .edit_item").attr("itemImg");
    }
    var index = 0;
    var count2 = $("#imageList .edit_item").length;
    if (count2 <= 0) {
        layAlert("请选择商品展示图图片");
        return;
    } else {
        $("#imageList .edit_item").each(function () {
            if (index == 0) {
                imageList += $(this).attr("itemImg");
            } else {
                imageList += ",," + $(this).attr("itemImg");
            }
            index++;
        })
    }
    var count3 = $("#imageDetails .edit_item").length;
    if (count3 <= 0) {
        layAlert("请选择商品详情图图片");
        return;
    } else {
        $("#imageDetails .edit_item").each(function () {
            if (index == 0) {
                imgDetails += $(this).attr("itemImg");
            } else {
                imgDetails += ",," + $(this).attr("itemImg");
            }
            index++;
        })
    }
    index = 0;
    if (categoryId == "") {
        layAlert("请选择商品分类");
        return;
    }
    if (barcode == "") {
        layAlert("请选择商品编码");
        return;
    }
    if (brand == "") {
        layAlert("请输入商品品牌");
        return;
    }
    if (unit == "") {
        layAlert("请输入商品单位");
        return;
    }
    if (price == "" || !checkNumber(price)) {
        layAlert("请输入正确的商品订货价");
        return;
    }
    if (referencePrice == "" || !checkNumber(referencePrice)) {
        layAlert("请输入正确的商品市场参考价");
        return;
    }

    $.post("/deliver/productUpdate", {
        "name": name,
        "unit": unit,
        "image": image,
        "brand": brand,
        "price": price,
        "id": productId,
        "remark": remark,
        "barcode": barcode,
        "imageList": imageList,
        "imgDetails": imgDetails,
        "categoryId": categoryId,
        "unitexplan": unitExplan,
        "productStatus": productStatus,
        "referencePrice": referencePrice,
        "productStatus": flag==1?1:2,
        "productPrefecture":shopSelf,
        "purchaseOrnot": willProduct==1?0:1,
        "exchangeCash":exchangeCash,
        "exchangePrice":exchangePrice,
        "exchangeType":exchangeType,
        "exchangeRemark":exchangeRemark
    }, function (data) {
        var status = data.status;
        var msg = data.msg;
        if (status == 200) {
            layAlert("修改成功");
            setTimeout(function () {
                self.location = document.referrer;
            }, 1200)
        } else {
            layAlert(msg);
        }
    })
}

function checkNumber(text) {
    var reg = /^[0-9]+([.]{1}[0-9]{1,2})?$/;
    if (!reg.test(text)) {
        return false;
    } else {
        return true;
    }
}

function layAlert(msg) {
    layer.open({content: msg, btn: '确定'});
};

$("#category").change(function () {
    var categoryId = $(this).val();
    $.post('/deliver/getTwoCategory', {
        "categoryId": categoryId
    }, function (data) {
        var status = data.status;
        if (status == 200) {
            var html = "";
            var categoryList = data.data;
            if (categoryList.length > 0) {
                for (var i = 0; i < categoryList.length; i++) {
                    var c = categoryList[i];
                    html += "<option value=\"" + c.id + "\">" + c.categoryName + "</option>";
                }
                $("#category2").html(html);
            }
        } else {
            layAlert("程序异常")
        }
    })
})


