<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!doctype html>
<html class="no-js fixed-layout">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>数米管理后台</title>
    <meta name="description" content="客户关系管理系统">
    <meta name="keywords" content="index">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="renderer" content="webkit">
    <meta http-equiv="Cache-Control" content="no-siteapp"/>
    <link rel="icon" type="image/png" href="../../statics/amazeui/i/favicon.png">
    <link rel="apple-touch-icon-precomposed" href="../../statics/amazeui/i/app-icon72x72@2x.png">
    <link rel="stylesheet" href="../../statics/amazeui/css/amazeui.min.css"/>
    <link rel="stylesheet" href="../../statics/amazeui/css/admin.css">
    <link rel="stylesheet" href="../../statics/css/common-base.css">
    <script src="../../statics/js/jquery-1.11.3.js"></script>
    <script src="../../statics/js/common-base.js"></script>
</head>
<body>
<!--[if lte IE 9]>
<p class="browsehappy">你正在使用<strong>过时</strong>的浏览器。 请 <a href="http://browsehappy.com/" target="_blank">升级浏览器</a>
    以获得更好的体验！</p>
<![endif]-->
<input type="hidden" id="loginUserId" value="<sec:authentication property="principal.userId"></sec:authentication>">
<input type="hidden" id="loginUserName" value="<sec:authentication property="principal.username"></sec:authentication>">
<sec:authorize access="hasRole('ROLE_MANAGER')">
<input type="hidden" id="loginUserRole" value="ROLE_MANAGER">
</sec:authorize>
<header class="am-topbar am-topbar-inverse admin-header">
    <div class="am-topbar-brand">
        <strong>客户关系</strong>
        <small>管理系统</small>
    </div>

    <button class="am-topbar-btn am-topbar-toggle am-btn am-btn-sm am-btn-success am-show-sm-only"
            data-am-collapse="{target: '#topbar-collapse'}"><span class="am-sr-only">导航切换</span> <span
            class="am-icon-bars"></span></button>

    <div class="am-collapse am-topbar-collapse" id="topbar-collapse">

        <ul class="am-nav am-nav-pills am-topbar-nav am-topbar-right admin-header-list">
            <li class="am-dropdown" data-am-dropdown>
                <a class="am-dropdown-toggle" data-am-dropdown-toggle href="javascript:;">
                    <span class="am-icon-user"></span> ${pageContext.request.userPrincipal.name} <span
                        class="am-icon-caret-down"></span>
                </a>
                <ul class="am-dropdown-content">
                    <li><a href="#"><span class="am-icon-cog"></span> 资料</a></li>
                    <li><a href="/logout"><span class="am-icon-power-off"></span> 退出</a></li>
                </ul>
            </li>
            <li class="am-hide-sm-only"><a href="javascript:;" id="admin-fullscreen"><span
                    class="am-icon-arrows-alt"></span> <span class="admin-fullText">开启全屏</span></a></li>
        </ul>
    </div>
</header>

<div class="am-cf admin-main">
    <!-- sidebar start -->
    <div class="admin-sidebar am-offcanvas" id="admin-offcanvas">
        <div class="am-offcanvas-bar admin-offcanvas-bar">
            <ul class="am-list admin-sidebar-list">
                <li><a href="/"><span class="am-icon-home"></span><span class="admin-nav-title">首页</span></a></li>
                <sec:authorize access="hasRole('ROLE_MANAGER')">
                    <li><a href="/salesman/list"><span class="am-icon-user-secret"></span><span
                            class="admin-nav-title">业务员</span></a></li>
                </sec:authorize>
                <li><a href="/customer/list"><span class="am-icon-users"></span><span class="admin-nav-title">客户管理</span></a>
                </li>
                <li><a href="/customerTraceLog/list"><span class="am-icon-book"></span><span class="admin-nav-title">客户笔记</span></a>
                </li>
            </ul>
        </div>
    </div>
    <!-- sidebar end -->

    <!-- content start -->
    <div class="admin-content">
        <div class="admin-content-body">