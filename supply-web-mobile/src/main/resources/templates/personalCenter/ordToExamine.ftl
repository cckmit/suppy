<!DOCTYPE html>
<html>
  <head>
      <meta charset="utf-8">
      <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0,user-scalable=no" />
      <title>易订货-待订单审核</title>
      <link rel="stylesheet" href="/css/global.css">
      <link rel="stylesheet" href="/css/personalCenter/ordToErexamine.css">
      <link rel="stylesheet" href="/css/mui.css">
      <link rel="stylesheet" href="/fonts/mui.ttf" />
  </head>
  <body>

    <div class="content mui-scroll-wrapper" id="pullrefresh">
        <div class="mui-scroll">
            <div class="order_examine_page">
                <div class="order_examine_list"></div>
            </div>
        </div>
    </div>
    <div style="display: none;" id="purchaseId">${purchaseId!}</div>
    <script src="/js/tmpl.min.js"></script>
    <script src="/js/jquery.min.js"></script>
    <script src="/js/order/orderGoods.js"></script>
    <script src="/js/personalCenter/orderToExamine.js"></script>
    <script src="/js/handlebars-1.0.0.beta.6.js"></script>
    <script src="/js/mui.js"></script>
    <script type="text/template" id="list-tmp">
        {{#each this}}
        <div class="order_examine_item">
            <a href="checkOrderDetails?purchaseFormId={{id}}">
            <div class="order_item_title">
                <span>订</span>
                <span>待订单审核</span>
            </div>
            <div class="order_item_main">
                <div>{{orgName}}</div>
                <div>{{purchaseBillno}}</div>
                <div>数量：<span>{{totalQuantity}}</span></div>
                <div>
                    金额：<span>￥{{totalPrice}}</span>
                    <span class="order_status">({{Status status}})</span>
                </div>
                <div>{{time createDate}}</div>
            </div>
            </a>
        </div>
        {{/each}}
    </script>
    <script>
        Handlebars.registerHelper("time",function(v1){
            //var v1 = v1.substring(0, 19)
            var date = new Date(v1.replace(/-/g, "/").replace(/T/g," ").split(".")[0])
            y = date.getFullYear()
            m = date.getMonth()+1
            d = date.getDate(),
                    h = date.getHours(),
                    i = date.getMinutes(),
                    s = date.getSeconds();
            if (m < 10) {
                m = '0' + m;
            }
            if (d < 10) {
                d = '0' + d;
            }
            if (h < 10) {
                h = '0' + h;
            }
            if (i < 10) {
                i = '0' + i;
            }
            if (s < 10) {
                s = '0' + s;
            }
            v1 = y + "-" + m + "-" + d + ' ' + h + ':' + i + ':' + s;

            return v1;
        });
        Handlebars.registerHelper("Status", function(status,text,id) {
            switch (status) {
                case 1:
                    return new Handlebars.SafeString('已付款待审核');
                    break;
                case 0:
                    return new Handlebars.SafeString('未付款');
                    break;
            }
        });
    </script>
    <script>
        mui.init({
            pullRefresh: {
                container: "#pullrefresh",
                down: {
                    callback: downPullfresh
                },
                up: {
                    callback: upPullfresh

                }
            }
        });

        var isPageOk = true;
        var pageNo = 1;
        var pageSize = 10;
        var pages = null;

        function downPullfresh() {
            setTimeout(function () {
                pageNo=1
                $('.order_examine_list').html('');
                mui('#pullrefresh').pullRefresh().endPulldownToRefresh();
                if(!isPageOk) {
                    pageNo=0
                    isPageOk=true;
                    mui('#pullrefresh').pullRefresh().refresh(true);
                }else {
                    getData(pageNo, pageSize);
                }
            }, 1000)

        }

        function upPullfresh() {
            setTimeout(function () {
                pageNo++;
                getData(pageNo, pageSize);
                if(pageNo == pages){isPageOk = false;}
                mui('#pullrefresh').pullRefresh().endPullupToRefresh((pageNo-1)== pages);

            }, 1000)
        }

        function getData(pageNo1, pageSize1) {
            var url = '/purchase/ordToExamineJson';
            $.getJSON(url, {
                "purchaseId": '${purchaseId}',
                "pageNo": pageNo1,
                "pageSize": pageSize1
            }, function (data) {
                console.log(data)
                pageSize = data.pageSize;
                pages = data.pages;
                if(data.list.length) {
                    var tpl = $("#list-tmp").html();
                    var template = Handlebars.compile(tpl);
                    var html = template(data.list);
                    $('.order_examine_list').append(html);
                }
            })
            mui('body').on('tap','a',function(){document.location.href=this.href;});
        }
        getData(pageNo, pageSize);
//        var myTemplate = Handlebars.compile($("#list-tmp").html());
//        $('.order_examine_list').html(myTemplate(data));
    </script>
    <style>
        .mui-pull-top-pocket{
            top:0px;
        }
        button{
            padding: 0;
        }
        body{
            background: transparent;
            line-height: normal;
        }
        .mui-scroll{
            padding-bottom: 60px;
        }
    </style>
  </body>
</html>