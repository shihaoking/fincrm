<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>客户关系管理系统</title>
    <meta name="description" content="客户关系管理系统">
    <meta name="keywords" content="index">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="renderer" content="webkit">
    <meta http-equiv="Cache-Control" content="no-siteapp"/>
    <link rel="icon" type="image/png" href="../statics/amazeui/i/favicon.png">
    <link rel="apple-touch-icon-precomposed" href="../statics/amazeui/i/app-icon72x72@2x.png">
    <link rel="stylesheet" href="../statics/amazeui/css/amazeui.min.css"/>
    <link rel="stylesheet" href="../statics/amazeui/css/admin.css">
    <link rel="stylesheet" href="../statics/css/common-base.css">
    <script src="../statics/js/jquery-1.11.3.js"></script>
    <script src="../statics/js/common-base.js"></script>
</head>
<body>
<div class="am-g am-u-sm-4 am-u-sm-centered" style="margin-top:150px;">

    <c:url var="authUrl" value="/j_spring_security_check"></c:url>
    <form class="am-form am-form-horizontal" name="f" method="POST" action="${authUrl}">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        <div class="am-form-group am-form-group-sm">
            <label for="doc-ipt-username" class="am-u-sm-4 am-form-label">用户名</label>
            <div class="am-u-sm-8">
                <input type="text" id="doc-ipt-username" name="username" class="am-form-field" value="" placeholder="用户名">
            </div>
        </div>

        <div class="am-form-group am-form-group-sm">
            <label for="doc-ipt-password" class="am-u-sm-4 am-form-label">密码</label>
            <div class="am-u-sm-8">
                <input type="password" id="doc-ipt-password" name="password" class="am-form-field" value="" placeholder="密码">
            </div>
        </div>

        <div class="am-form-group am-form-group-sm">
            <div class="am-u-sm-8 am-u-sm-offset-4">
                <div class="am-checkbox">
                    <label>
                        <input type="checkbox" name="_spring_security_remember_me"> 记住我
                    </label>
                </div>
            </div>
        </div>

        <div class="am-form-group">
            <div class="am-u-sm-8 am-u-sm-offset-4">
                <button type="submit" class="am-btn am-btn-primary am-btn-default">登录</button>
            </div>
        </div>
    </form>
</div>
<script src="../statics/amazeui/js/amazeui.min.js"></script>
<script src="../statics/amazeui/js/app.js"></script>
</body>
</html>
