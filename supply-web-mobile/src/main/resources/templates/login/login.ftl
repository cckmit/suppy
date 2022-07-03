<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0,user-scalable=no"/>
    <title>易订货-登录</title>
    <link rel="stylesheet" href="/css/global.css">
    <link rel="stylesheet" href="/css/login.css">
    <style>
        .btn {
            width: 100%;
            height: 0.88rem;
            margin-top: 0.2rem;
            border-radius: 0.44rem;
            background: #47b6ca;
            border: 0;
            outline: none;
            color: #fff;
            font-size: 0.36rem;
        }

        .logo {
            background-image: url(/image/logo1.png)
        }

        body.tz .logo {
            background-image: url(/image/logo2.png)
        }

        body.lhyh .logo {
            background-image: url(/image/logolh.png)
        }

        body.qj .logo {
            background-image: url(/image/logojj.png)
        }

        body.sm .logo {
            background-image: url(/image/smlogo.png);
            width: 5rem;
        }

        body.hy .logo {
            background-image: url(/image/hylogo.png)
        }

        body.lq .logo {
            background-image: url(/image/lqlogo.png)
        }

        body.wl .logo {
            background-image: url(/image/wllogo.png)
        }

        body.lh .logo {
            background-image: url(/image/lhlogo.png)
        }

        body.tt .logo {
            background-image: url(/image/ttlogo.png)
        }

        body.ykyh .logo {
          background-image: url(/image/ykyhlogo.png)
        }

        body.ykyh .logo {
            background-image: url(/image/ykyhlogo.png)
        }

        body {
            background: #fff;
        }

    </style>
</head>
<body>
<div class="login_page" style="background-image:url(../image/login_back1.png)">
    <div class="login_top">
        <span class="logo"></span>
        <#--<span style="background-image:url(/image/logo_text.png)"></span>-->
    </div>
    <form id="loginForm" action="/mbl/logon">
        <div class="login_main">
            <div>
                <span style="background-image:url(/image/tel.png)"></span>
                <input type="text" id="username" name="username"
                       placeholder="请输入手机号码/用户名" value="${username!}">
            </div>
            <div>
                <span style="background-image:url(/image/password.png)"></span>
                <input type="password" id="password" name="password"
                       placeholder="请输入密码" value="${password!}">
            </div>
            <input hidden id="openId" name="openId" value="${openId!}">
            <input hidden id="cls" name="cls" value="${cls!}">
            <input class="btn" type="button" onclick="btnLogin()" value="登录">
            <#--<span class="login_retrieve">-->
            <#--<a href="forget.html">找回密码</a>-->
            <#--</span>-->
        </div>
    </form>
    <div class="login_bottom">
        <#--<div>-->
        <#--<a href="register.html">-->
        <#--<span style="background-image:url(/image/register.png)"></span>-->
        <#--<p>注册</p>-->
        <#--</a>-->
        <#--</div>-->
        <#--<div>-->
        <#--<a href="index.html">-->
        <#--<span style="background-image:url(/image/experience.png)"></span>-->
        <#--<p>立即体验</p>-->
        <#--</a>-->
        <#--</div>-->
    </div>
</div>
<script src="/js/jquery.min.js"></script>
<script>
    $(function () {
        var msg = '${msg!}';
        if (msg != '') {
            alert(msg);
        }
    })
    var cls = "${cls!}";

    function btnLogin() {
        var openId = $("#openId").val();
        var username = $("#username").val();
        var password = $("#password").val();
        if (username == "") {
            alert("请输入用户名")
            return
        }
        if (password == "") {
            alert("请输入密码")
            return
        }
        $("#loginForm").submit();
    }


    if (cls == "1") {
        $('body').addClass('tz')
    } else if (cls == "2") {
        $('body').addClass('lhyh')
    } else if (cls == "3") {
        $('body').addClass('qj')
    } else if (cls == "4") {
        $('body').addClass('sm')
    } else if (cls == "5") {
        $('body').addClass('hy')
    } else if (cls == "6") {
        $('body').addClass('lq')
    } else if (cls == "7") {
        $('body').addClass('wl')
    } else if (cls == "8") {
        $('body').addClass('lh')
    } else if (cls == "9") {
        $('body').addClass('tt')
    } else if (cls == "10") {
        $('body').addClass('ykyh')
    }
</script>
</body>
</html>
