<#assign pets=JspTaglibs["/WEB-INF/tld/pets-tags.tld"]>
<html>
<head>
    <title>登录</title>
	<@pets.staticResource resource='/css/reg.css' decorate='true'/>
</head>
<body>
<div class="container container-reg">
    <div class="bind-cont">
        <h2>只差一步，即可完成登录设置</h2>
        <form id="bindForm" method="post" action="/tlogin">
            <input type="hidden" name="postBack" value="true">
            <input type="hidden" name="type" value="${type!''}">
            <input type="hidden" name="uid" value="${uid!''}">
            <input type="hidden" name="token" value="${token!''}">
            <div id="cont-log" class="main-cont">
                <div class="title">
                    <strong>10秒完成Hachi宠物网帐号创建</strong>
                    <p>完成帐号创建后，即可直接使用您的第三方网站帐号登录Hachi宠物网</p>
                </div>
                <div class="block">
                    <div class="row">
                        <label for="txtUserNickName">昵&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;称：</label>
                        <input type="text" class="text usernickname" id="txtUserNickName" maxlength="20" name="userNickname" value="${userNickname!''}">
						<#if err?? && err="nickname_empty">
                            <span class="errmsg" id="errMsgUserNickName">请输入昵称!</span>
						</#if>
						<#if err?? && err="nickname_exist">
							<span class="errmsg" id="errMsgUserNickName">昵称已存在，请重新输入!</span>
						</#if>
						<#if err?? && err="system_err">
							<span class="errmsg" id="errMsgUserNickName">系统异常，请重新尝试!</span>
						</#if>
                        <span id="errMsgUserNickName"></span>
                    </div>
                    <div class="row">
                        <label for="txtEmail">电子邮箱：</label>
                        <input type="text" class="text email err-input" id="txtEmail" name="email" value="${email!''}">
						<#if err?? && err="email_invalid">
							<span class="errmsg" id="errMsgEmail">请输入正确的电子邮箱!</span>
						</#if>
                        <span id="errMsgEmail"></span>
                    </div>
                    <div class="row btnRow">
						<span class="btn-type-o">
                        	<input type="button" class="btn-submit" id="btnNewUser" value="确认提交" name="btnNewUser">
						</span>
                    </div>
                </div>
            </div>
        </form>
    </div>
</div>
<@pets.staticResource resource='/js/reg.js' decorate='true'/>
<script type="text/javascript">
    $(function(){
        initCheck();
    });
</script>
</body>
</html>
