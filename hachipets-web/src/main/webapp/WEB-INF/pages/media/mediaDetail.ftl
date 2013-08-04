<html>
<head>
    <title>${movie.name}-宠物影视</title>
    <link href="/css/movie-detail.css" rel="stylesheet">
</head>
<body>
<div class="container">
    <div class="pets-movie-detail">
        <div class="pets-movie-title">
            ${movie.name}<#if (movie.year)?? && movie.year gt 0><span>(${movie.year})</span></#if>
        </div>
        <div class="pets-movie-desc">
            <div class="pets-movie-pic">
                <img width="225" height="300" src="${(movie.pic)!''}">
            </div>
            <div class="pets-movie-info">
                <dl>
                    <dt>导&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;演：</dt>
                    <dd><#if (movie.director)?? && movie.director!="">${movie.director}<#else>未知</#if></dd>
                </dl>
                <dl>
                    <dt>主&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;演：</dt>
                    <dd><#if (movie.actor)?? && movie.actor!="">${movie.actor}<#else>未知</#if></dd>
                </dl>
                <dl>
                    <dt>年&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;代：</dt>
                    <dd><#if (movie.year)?? && movie.year gt 0>${movie.year}<#else>--</#if></dd>
                </dl>
                <dl>
                    <dt>地&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;区：</dt>
                    <dd><#if (movie.region)?? && movie.region!="">${movie.region}<#else>未知</#if></dd>
                </dl>
                <dl>
                    <dt>上映日期：</dt>
                    <dd><#if (movie.release)??>${movie.release?string("yyyy-MM-dd")}<#else>未知</#if></dd>
                </dl>
                <dl>
                    <dt>片&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;长：</dt>
                    <dd><#if (movie.length)?? && movie.length gt 0>${movie.length}分钟<#else>--</#if></dd>
                </dl>
                <dl>
                    <dt>影片介绍：</dt>
                    <dd><a href="#" target="_blank" class="douban">豆瓣链接</a> <a href="#" target="_blank" class="mtime">Mtime链接</a></dd>
                </dl>
                <dl>
                    <dt>影片详情：</dt>
                    <dd class="pets-movie-info-desc"><#if (movie.desc)?? && movie.desc!="">${movie.desc}<#else>暂无</#if></dd>
                </dl>
            </div>
        </div>
    </div>
    <div class="pets-movie-play">
        <div class="tips">在线观看</div>
        <div class="play-list">
            <a class="play"><i class="youku"></i>优酷</a>
            <a class="play"><i class="tudou"></i>土豆</a>
            <a class="play"><i class="iqiyi"></i>爱奇艺</a>
            <a class="play"><i class="qq"></i>腾讯</a>
            <a class="play"><i class="pptv"></i>PPTV</a>
            <a class="play"><i class="le"></i>乐视</a>
        </div>
    </div>
    <div class="pets-movie-other">
        <div class="tips">推荐您看：</div>
        <div class="other-list">
            <div class="other-movie">
                <a href="#" target="_blank"><img src="http://pic.qire123.com/myupload/10am30PRC2010-04-10/4bbf87a3a9a52.jpg"></a>
                <a href="#" target="_blank" class="name">加菲猫</a>
            </div>
            <div class="other-movie">
                <a href="#" target="_blank"><img src="http://pic.qire123.com/myupload/newpic/201301/16/50f681c5e6112.jpg"></a>
                <a href="#" target="_blank" class="name">快乐到家</a>
            </div>
            <div class="other-movie">
                <a href="#" target="_blank"><img src="http://blog.5d.cn/user21/cindymoon/upload/2006-09/28_943.jpg"></a>
                <a href="#" target="_blank" class="name">加菲猫2</a>
            </div>
            <div class="other-movie">
                <a href="#" target="_blank"><img src="http://pic.qire123.com/myupload/10am30PRC2010-04-10/4bbf7579526c9.jpg"></a>
                <a href="#" target="_blank" class="name">南极大冒险</a>
            </div>
            <div class="other-movie">
                <a href="#" target="_blank"><img src="http://pic.qire123.com/myupload/10am30PRC2010-04-10/4bbf87a3a9a52.jpg"></a>
                <a href="#" target="_blank" class="name">加菲猫</a>
            </div>
            <div class="other-movie">
                <a href="#" target="_blank"><img src="http://pic.qire123.com/myupload/newpic/201301/16/50f681c5e6112.jpg"></a>
                <a href="#" target="_blank" class="name">快乐到家</a>
            </div>
        </div>
    </div>
</div>
</body>
</html>
