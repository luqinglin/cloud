<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="./common/taglibs.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8"/>
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache, must-revalidate">
    <meta http-equiv="expires" content="0">
    <meta name="keywords" content=""/>
    <meta name="description" content=""/>
    <meta name="viewport"
          content="width=device-width,initial-scale=1.0,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no">
    <meta name="apple-mobile-web-app-capable" content="yes"/>
    <meta name="apple-mobile-web-app-status-bar-style" content="black-translucent"/>
    <meta name="format-detection" content="telephone=no"/>
    <meta name="msapplication-tap-highlight" content="no"/>
    <title>登录</title>

    <link rel="stylesheet" href="${ctx}/resources/css/reset.css" type="text/css">
    <link rel="stylesheet" href="${ctx}/resources/css/register.css" type="text/css">
    <link rel="stylesheet" href="${ctx}/resources/css/sweetAlert.css" type="text/css">
    <style>
        .login-text {
            height: 4rem;
            line-height: 4rem;
            text-align: right;
        }

        .login-text a {
            color: #333;
        }

        .fast-text {
            height: 4rem;
            line-height: 4rem;
            text-align: center;
        }

        .fast-text a {
            color: #6aa9ff;
        }
    </style>
</head>
<body>

<div class="main-register">
    <div class="logo-img"><img src="${ctx}/resources/images/logo.png" alt=""></div>
    <div class="pwdBox">
        <label for="">
            <img src="${ctx}/resources/images/icon-40.png" alt=""/>
            <input type="text" id="userName" placeholder="请输入手机号"/>
        </label>
        <label for="">
            <img src="${ctx}/resources/images/icon-42.png" alt=""/>
            <input type="password" id="password" placeholder="请输入密码"/>
        </label>
    </div>
    <div class="p-btn">
        <button class="btn-num" id="per-btn">登录</button>
    </div>
</div>
<form id="form" action="${ctx}/indexPage" method="post">
	<input type="hidden" id="access_token" name="access_token"/>
</form>

<script src="${ctx}/resources/js/jquery-1.11.0.min.js"></script>
<script src="${ctx}/resources/js/sweetalert.js"></script>
<script src="${ctx}/resources/js/public.js"></script>
<script type="text/javascript">

    $(function () {
        $("#per-btn").on("click", function () {
            var userName = $("#userName").val();
            if (userName == "") {
                swal('请输入手机号！', '', 'warning');
                return;
            }
            var password = $("#password").val();
            if (password == "") {
                swal('请您输入密码！', '', 'warning');
                return;
            }

            $.ajax({
                url: "${ctx}/login",
                type: "post",
                data: {"userName": userName, "password": password},
                success: function (data) {
                    if (data.jwt.access_token != null) {
                    	saveAuth(data.jwt);
                        //window.location.href = '${ctx}/indexPage?access_token='+data.jwt.access_token;
                        $("#access_token").val(data.jwt.access_token);
                        alert($("#access_token").val());
                        $("#form").submit();
                    } else {
                        swal('登录失败', '', 'warning');

                    }
                }
            });
        });


    });


</script>
</body>
</html>
