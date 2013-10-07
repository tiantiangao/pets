<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset='utf-8'>
    <title>${title}-${projectName}</title>
    <link rel="shortcut icon" href="/img/favicon.ico" type="image/x-icon">
    <script src="/js/jquery.js"></script>
    <script src="/js/bootstrap.js"></script>
    <script src="/js/common.js"></script>
    <link href="/css/bootstrap.css" rel="stylesheet">
    <link href="/css/header.css" rel="stylesheet">
    ${head}
</head>
<body>
<!-- 顶部状态栏 -->
<div class="container">
    <div class="row-fluid pets-header">
        <div class="span4">
            <span class="pets-header-tips">爱生活，爱宠物！</span>
            <a href="/login">请登录</a>
            <a href="/reg">快速注册</a>
        </div>
        <div class="span4 offset4 text-right">
            <a href="/help">我的宠物</a>
            <span class="pets-divider-vertical">|</span>
            <a href="/help">收藏Hachi宠物网</a>
        </div>
    </div>
</div>
<div class="container">
    <div class="row-fluid">
        <img src="/img/logo.png">
    </div>
</div>
<!-- 导航栏 -->
<div class="container">
    <div class="navbar">
        <div class="navbar-inner">
            <#--<a class="brand pets-brand" href="#"></a>-->
            <ul class="nav pets-nav">
                <li><a href="/" <#if channel=="index">class="active"</#if>>首页</a></li>
                <li><a href="/baike" <#if channel=="baike">class="active"</#if>>宠物百科</a></li>
                <li><a href="#">宠物商城</a></li>
                <li><a href="/news" <#if channel=="news">class="active"</#if>>宠物新鲜事</a></li>
                <li><a href="/show" <#if channel=="show">class="active"</#if>>宠物秀</a></li>
                <li><a href="/transfer" <#if channel=="transfer">class="active"</#if>>宠物领养</a></li>
                <li><a href="/hospital" <#if channel=="hospital">class="active"</#if>>宠物医院</a></li>
                <li><a href="/beauty" <#if channel=="beauty">class="active"</#if>>宠物美容</a></li>
                <li><a href="/media" <#if channel=="media">class="active"</#if>>宠物影视</a></li>
                <li><a href="/bbs" <#if channel=="bbs">class="active"</#if>>宠物社区</a></li>
            </ul>
        </div>
    </div>
</div>
<!-- 主体 -->
${body}
<!-- 页脚 -->
<div class="container pets-footer">
    <div class="row-fluid text-center">
        <div class="copyright">©2013-2023 hachipets.com All Rights Reserved. Hachi宠物网 版权所有</div>
    </div>
</div>
<div class="returnTop" title="返回顶部"><span class="l"></span><span class="s"></span><span class="b"></span></div>
</body>
</html>
