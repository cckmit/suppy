$(function () {
    $('.introduce_bottom div:nth-child(2)').on('click', function () {
        $('.lower_mask').show()
    })
    $('.lower_btn div').on('click', function () {
        $('.lower_mask').hide()
    })
})

function updateProductStatus(id, productStatus) {
    $.post("/deliver/updateProductStatus", {"id": id}, function (data) {
        console.log(data)
        var status = data.status;
        var msg = data.msg;
        if (status == 200) {
            if (productStatus == 1) {
                alert("下架成功");
            } else {
                alert("上架成功");
            }
            window.location.reload();
        } else {
            alert(msg)
        }
    })
}