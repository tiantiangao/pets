<#assign pets=JspTaglibs["/WEB-INF/tld/pets-tags.tld"]>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset='utf-8'>
    <title>${title}-${projectName}</title>
    <link rel="shortcut icon" href="<@pets.staticResource resource='/img/favicon.ico'/>" type="image/x-icon">
    <@pets.staticResource resource='/js/jquery.js' decorate='true'/>
	<@pets.staticResource resource='/js/bootstrap.js' decorate='true'/>
	<@pets.staticResource resource='/js/common.js' decorate='true'/>
	<@pets.staticResource resource='/css/bootstrap.css' decorate='true'/>
	<@pets.staticResource resource='/css/header.css' decorate='true'/>
    ${head}
</head>
<body>
<div class="container header">
    <div class="row-fluid">
        <div class="header-logo">
            <a href="/"><@pets.staticResource resource='/img/logo.png' decorate='true'/></a>
        </div>
        <div class="header-info">
            <span><a href="/login" title="登录">登录</a></span>
            <span class="divider"></span>
            <span><a href="/reg" title="注册">注册</a></span>
            <span class="divider"></span>
            <span><a href="/member/myinfo/pets" title="我的宠物">我的宠物</a></span>
        </div>
    </div>
</div>
<!-- 导航栏 -->
<div class="container header-nav">
    <div class="header-navbar">
        <div class="header-navbar-inner">
            <ul class="header-nav header-pets-nav">
                <li class="first"><a href="/" <#if channel=="index">class="active"</#if>>首页</a></li>
                <li><a href="/baike" <#if channel=="baike">class="active"</#if>>宠物百科</a></li>
                <li><a href="/media" <#if channel=="media">class="active"</#if>>宠物影视</a></li>
                <li><a href="/mall" <#if channel=="mall">class="active"</#if>>宠物商城</a></li>
                <li><a href="/news" <#if channel=="news">class="active"</#if>>宠物新鲜事</a></li>
                <li><a href="/show" <#if channel=="show">class="active"</#if>>宠物秀</a></li>
                <li><a href="/transfer" <#if channel=="transfer">class="active"</#if>>宠物领养</a></li>
                <li><a href="/hospital" <#if channel=="hospital">class="active"</#if>>宠物医院</a></li>
                <li><a href="/beauty" <#if channel=="beauty">class="active"</#if>>宠物美容</a></li>
                <li><a href="/bbs" <#if channel=="bbs">class="active"</#if>>宠物社区</a></li>
                <div class="clear"></div>
            </ul>
        </div>
    </div>
</div>
<!-- 主体 -->
${body}
<!-- 页脚 -->
<div class="container pets-footer">
    <div class="row-fluid text-center">
        <div class="copyright">©2014 hachipets.com All Rights Reserved. ${projectName} 版权所有</div>
        <div class="copyright"><a href="http://www.miitbeian.gov.cn" rel="nofollow" class="G" target="_blank">沪ICP备14014481号</a></div>
    </div>
</div>
<div class="returnTop" title="返回顶部"><span class="l"></span><span class="s"></span><span class="b"></span></div>
</body>
</html>
