<#assign pets=JspTaglibs["/WEB-INF/tld/pets-tags.tld"]>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset='utf-8'>
    <title>${title}-${projectName}</title>
    <link rel="shortcut icon" href="<@pets.staticResource resource='/img/favicon.ico'/>" type="image/x-icon">
	<@pets.staticResource resource='/css/bootstrap.css' decorate='true'/>
	<@pets.staticResource resource='/css/header.css' decorate='true'/>
	<@pets.staticResource resource='/js/jquery.js' decorate='true'/>
	<@pets.staticResource resource='/js/bootstrap.js' decorate='true'/>
	<@pets.staticResource resource='/js/common.js' decorate='true'/>
    <script type="text/javascript" src="http://qzonestyle.gtimg.cn/qzone/openapi/qc_loader.js" data-appid="101081176" data-redirecturi="http://www.hachipets.com" charset="utf-8"></script>
    ${head}
</head>
<body>
<div class="container header">
    <div class="row-fluid">
        <div class="header-logo">
            <a href="/"><@pets.staticResource resource='/img/logo.png' decorate='true'/></a>
        </div>
        <div class="header-info">
            <span id="qqLoginBtn"></span>
            <#--<span class="divider"></span>-->
            <#--<span><a href="#" title="我的宠物">我的宠物</a></span>-->
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
                <li><a href="/hospital" <#if channel=="hospital">class="active"</#if>>宠物医院</a></li>
                <li><a href="/mall" <#if channel=="mall">class="active"</#if>>宠物商城</a></li>
                <li><a href="/news" <#if channel=="news">class="active"</#if>>宠物新鲜事</a></li>
                <li><a href="/show" <#if channel=="show">class="active"</#if>>宠物秀</a></li>
                <li><a href="/transfer" <#if channel=="transfer">class="active"</#if>>宠物领养</a></li>
                <#--<li><a href="/beauty" <#if channel=="beauty">class="active"</#if>>宠物美容</a></li>-->
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
<script>
    (function(i,s,o,g,r,a,m){i['GoogleAnalyticsObject']=r;i[r]=i[r]||function(){
        (i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();a=s.createElement(o),
            m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m)
    })(window,document,'script','//www.google-analytics.com/analytics.js','ga');

    ga('create', 'UA-50565641-1', 'hachipets.com');
    ga('send', 'pageview');
</script>
<script type="text/javascript">
    //调用QC.Login方法，指定btnId参数将按钮绑定在容器节点中
    QC.Login({
		//btnId：插入按钮的节点id，必选
		btnId:"qqLoginBtn",
		//用户需要确认的scope授权项，可选，默认all
		scope:"all",
		//按钮尺寸，可用值[A_XL| A_L| A_M| A_S|  B_M| B_S| C_S]，可选，默认B_S
		size: "A_M"
	}, function(reqData, opts){//登录成功
		//根据返回数据，更换按钮显示状态方法
		var dom = document.getElementById(opts['btnId']),
			_logoutTemplate=[
				//头像
				'<span><img src="{figureurl}" class="{size_key}"/></span>',
				//昵称
				'<span>{nickname}</span>',
				//退出
				'<span><a href="javascript:QC.Login.signOut();">退出</a></span>'
			].join("");
		dom && (dom.innerHTML = QC.String.format(_logoutTemplate, {
			nickname : QC.String.escHTML(reqData.nickname), //做xss过滤
			figureurl : reqData.figureurl
		}));
	}, function(opts){//注销成功
		alert('QQ登录 注销成功');
	});
</script>
</body>
</html>
