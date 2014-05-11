<#assign pets=JspTaglibs["/WEB-INF/tld/pets-tags.tld"]>
<!DOCTYPE html>
<html>
<head>
    <meta charset='utf-8'>
    <title>${title}-${projectName}</title>
    <link rel="shortcut icon" href="<@pets.staticResource resource='/img/favicon.ico'/>" type="image/x-icon">
	<@pets.staticResource resource='/css/bootstrap.css' decorate='true'/>
	<@pets.staticResource resource='/css/header.css' decorate='true'/>
	<@pets.staticResource resource='/js/jquery.js' decorate='true'/>
	<@pets.staticResource resource='/js/bootstrap.js' decorate='true'/>
	<@pets.staticResource resource='/js/common.js' decorate='true'/>
    ${head}
</head>
<body>
<div class="container header">
    <div class="header-logo">
        <a href="/"><@pets.staticResource resource='/img/logo.png' decorate='true'/></a>
    </div>
    <div class="header-info">
        <#if account??>
        <span>您好，<a href="#">${account.nickname}</a></span>
        <span class="divider"></span>
        <span><a href="/logout" title="退出" rel="nofollow">退出</a></span>
        <#else>
        <span id="qqLoginBtn">
            <a href="/authLogin?type=qq" rel="nofollow"><img src="<@pets.staticResource resource='/img/qq-login.png'/>" alt="QQ登录" border="0"></a>
        </span>
            &nbsp;&nbsp;
        <span id="sinaLoginBtn">
            <a href="/authLogin?type=sina" rel="nofollow"><img src="<@pets.staticResource resource='/img/sina-login.png'/>" alt="微博登录" border="0"></a>
        </span>
        </#if>
    </div>
    <div class="clear"></div>
</div>
<!-- 导航栏 -->
<div class="header-navbar">
    <div class="header-navbar-inner">
        <div class="nav-item"><a href="/" <#if channel=="index">class="active"</#if>>首页</a></div>
        <div class="nav-item"><a href="/baike" <#if channel=="baike">class="active"</#if>>宠物百科</a></div>
        <div class="nav-item"><a href="/media" <#if channel=="media">class="active"</#if>>宠物影视</a></div>
        <div class="nav-item"><a href="/hospital" <#if channel=="hospital">class="active"</#if>>宠物医院</a></div>
        <div class="nav-item"><a href="/mall" <#if channel=="mall">class="active"</#if>>宠物商城</a></div>
        <div class="nav-item"><a href="/news" <#if channel=="news">class="active"</#if>>宠物新鲜事</a></div>
        <div class="nav-item"><a href="/show" <#if channel=="show">class="active"</#if>>宠物秀</a></div>
        <div class="nav-item"><a href="/transfer" <#if channel=="transfer">class="active"</#if>>宠物领养</a></div>
        <div class="nav-item"><a href="/bbs" <#if channel=="bbs">class="active"</#if>>宠物社区</a></div>
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
<script>
    (function(i,s,o,g,r,a,m){i['GoogleAnalyticsObject']=r;i[r]=i[r]||function(){
        (i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();a=s.createElement(o),
            m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m)
    })(window,document,'script','//www.google-analytics.com/analytics.js','ga');

    ga('create', 'UA-50565641-1', 'hachipets.com');
    ga('send', 'pageview');
</script>
</body>
</html>
