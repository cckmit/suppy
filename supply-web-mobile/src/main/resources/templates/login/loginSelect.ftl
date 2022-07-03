<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>登入页</title>
    <#--<meta name="renderer" content="webkit">-->
    <#--<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">-->
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <link rel="stylesheet" href="/layui/css/layui.css" media="all">
    <link rel="stylesheet" href="/layui/css/admin.css" media="all">
    <link rel="stylesheet" href="/layui/css/login.css" media="all">
    <style>
        .layui-card-body {
            text-align: center;
        }

        .layui-card-body img {
            display: block;
            margin: 10px auto;
            width: 60px;
        }

        .layadmin-user-login-main {
            background: #fff;
            padding: 10px 15px;
            border-radius: 3px;
            position: absolute;
            box-sizing: content-box;
            left: 50%;
            top: 50%;
            transform: translate(-50%, 50%);
            z-index: 99;
            box-shadow: 0 0 2px #f5f5f5;
        }

        .layui-card-body {
            white-space: nowrap;
        }

        .banner img {
            width: 100%;
            display: block;
            border-radius: 3px;
        }

        .layadmin-user-login-header span {
            font-size: 22px;
            font-weight: bold;
            margin-left: 10px;
        }
    </style>
</head>
<body>
<div class="layui-fluid">
    <div class="layui-row layui-col-space10 port" style="display:block;">
        <div class="banner">
            <img src="/image/logbg.png">
        </div>
        <div class="layui-col-xs4 layui-col-sm7 layui-col-md4 redirect" data-url="login?cls=1">
            <div class="layui-card">
                <div class="layui-card-body">
                    <img src="/image/logotz.png">
                    <span class="title">台州银行</span>
                </div>
            </div>
        </div>
        <#--<div class="layui-col-xs4 layui-col-sm7 layui-col-md4 redirect" data-url="login?cls=2">
            <div class="layui-card">
                <div class="layui-card-body">
                    <img src="image/zjlogo.png">
                    <span class="title">杭州联合银行</span>
                </div>
            </div>
        </div>
        <div class="layui-col-xs4 layui-col-sm7 layui-col-md4 redirect" data-url="login?cls=3">
            <div class="layui-card">
                <div class="layui-card-body">
                    <img src="image/zjlogo.png">
                    <span class="title">椒江农商行</span>
                </div>
            </div>
        </div>-->
        <div class="layui-col-xs4 layui-col-sm7 layui-col-md4 redirect" data-url="login?cls=4">
            <div class="layui-card">
                <div class="layui-card-body">
                    <img src="/image/logotz.png">
                    <span class="title">三门银座银行</span>
                </div>
            </div>
        </div>
        <div class="layui-col-xs4 layui-col-sm7 layui-col-md4 redirect" data-url="login?cls=5">
            <div class="layui-card">
                <div class="layui-card-body">
                    <img src="/image/zjlogo.png">
                    <span class="title">黄岩农信</span>
                </div>
            </div>
        </div>
        <#--<div class="layui-col-xs4 layui-col-sm7 layui-col-md4 redirect" data-url="login?cls=5">
            <div class="layui-card">
                <div class="layui-card-body">
                    <img src="image/zjlogo.png">
                    <span class="title">黄岩农商行</span>
                </div>
            </div>
        </div>
        <div class="layui-col-xs4 layui-col-sm7 layui-col-md4 redirect" data-url="login?cls=6">
            <div class="layui-card">
                <div class="layui-card-body">
                    <img src="image/zjlogo.png">
                    <span class="title">路桥农商行</span>
                </div>
            </div>
        </div>
        <div class="layui-col-xs4 layui-col-sm7 layui-col-md4 redirect" data-url="login?cls=7">
            <div class="layui-card">
                <div class="layui-card-body">
                    <img src="image/zjlogo.png">
                    <span class="title">温岭农商行</span>
                </div>
            </div>
        </div>
        <div class="layui-col-xs4 layui-col-sm7 layui-col-md4 redirect" data-url="login?cls=8">
            <div class="layui-card">
                <div class="layui-card-body">
                    <img src="image/zjlogo.png">
                    <span class="title">临海农商行</span>
                </div>
            </div>
        </div>
        <div class="layui-col-xs4 layui-col-sm7 layui-col-md4 redirect" data-url="login?cls=9">
            <div class="layui-card">
                <div class="layui-card-body">
                    <img src="image/zjlogo.png">
                    <span class="title">天台农商行</span>
                </div>
            </div>
        </div>-->
    </div>

    <script src="/js/jquery.min.js"></script>
    <script>
        $('.redirect').on('click', function () {
            var url = $(this).data("url");
            if (url != "" && url != undefined) {
                window.location.href = url;
            }
        })
    </script>
</div>
</body>
</html>