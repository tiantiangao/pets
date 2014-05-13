<#assign pets=JspTaglibs["/WEB-INF/tld/pets-tags.tld"]>
<!DOCTYPE html>
<html>
<head>
    <title>404-页面未找到-Hachi宠物网</title>
</head>
<body>
<!-- 主体 -->
<div class="container">
    <div class="error-container">
        <div class="error error-warning">
            <div class="error-title">非常抱歉，您访问的页面未找到</div>
            <div>
                <ul>
                    <li>
                        <a href="/"><span id="tS"></span>返回首页</a>
                    </li>
                </ul>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript">
    function gid(id) { return document.getElementById ? document.getElementById(id) : null; }
    function timeDesc() {
        if (all <= 0) {
            self.location = "/";
        }
        var obj = gid("tS");
        if (obj) obj.innerHTML = all + " 秒后";
        all--;
    }
    var all = 5;
    if (all > 0) window.setInterval("timeDesc();", 1000);
</script>
</body>
</html>

