$(function () {

    function categoryClick(obj) {
        // $(".hide_type_list").parent().parent().siblings().find("span:nth-child(2)").css({'display': 'none'})

        $(obj).find('span:first-child').css({'color': '#0BC6D7'})
        $(obj).find('span:nth-child(2)').css({'display': 'block'})
        $(obj).siblings().find('span:first-child').css({'color': '#666'})
        $(obj).siblings().find('span:nth-child(2)').css({'display': 'none'})
    }

    $('.hide_type_list').on('click', function () {
        categoryClick(this);
    })

    $('.add').on('click', function () {
        var categoryId = $(this).attr("categoryId");
        if ($(this).html() == '+') {
            $(this).parents('.type_list_title').siblings('.hide_type').css({'display': 'block'})
            $(this).html('-')
            getCategoryChild(categoryId);
        } else {
            $(this).parents('.type_list_title').siblings('.hide_type').css({'display': 'none'})
            $(this).html('+')
        }
    })

    function getCategoryChild(categoryId) {
        $.post("/deliver/getCategoryChlid", {"categoryId": categoryId}, function (data) {
            console.log(data)
            var status = data.status;
            var msg = data.msg;
            var categoryList = data.data;
            if (status == 200) {
                setHtml(categoryList, $(".type_list_item_" + categoryId), categoryId);
            } else {
                alert(msg)
            }
        })
    }

    function setHtml(categoryList, obj, categoryId) {
        $(".child_" + categoryId).remove();
        if (categoryList.length > 0) {
            var html = "<div class=\"hide_type child_" + categoryId + "\" style=\"display: block;\">";
            $(categoryList).each(function () {
                html += "<div class=\"hide_type_list\" categoryId='" + this.id + "' categoryName='" + this.categoryName + "' onclick='go(this)'>" +
                    "<span>" + this.categoryName + "</span>" +
                    "<span><i class=\"iconfont icontubiaozhizuomoban-10\"></i></span>" +
                    "</div>";
            })
            html += "</div>";
            $(obj).append(html);
        }
    }
})

function categoryClick2(obj) {

    $(obj).find('span:first-child').css({'color': '#0BC6D7'})
    $(obj).find('span:nth-child(2)').css({'display': 'block'})
    $(obj).siblings().find('span:first-child').css({'color': '#666'})
    $(obj).siblings().find('span:nth-child(2)').css({'display': 'none'})
}


function go(obj) {
    var categoryId = $(obj).attr("categoryId");
    var categoryName = $(obj).attr("categoryName");
    localStorage.setItem("categoryId", categoryId);
    localStorage.setItem("categoryName", categoryName);
    window.history.go(-1);
}