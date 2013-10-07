<html>
<head>
    <title>${movie.name}<#if (movie.year)?? && movie.year gt 0>(${movie.year})</#if>-宠物影视</title>
    <meta name="description" content="${movie.name}-${(movie.desc)!""}-${projectName!""}" />
    <link href="/css/movie-detail.css" rel="stylesheet">
    <script src="/js/petspic.js"></script>
</head>
<body>
<div class="container">
    <div class="pets-movie-detail">
        <div class="pets-movie-return"><a href="<#if canReturnHistory>javascript:if(history.length>1){history.go(-1);}else{window.location.href='/media';}<#else>/media</#if>">&lt;&lt; 返回上一页</a></div>
        <div class="pets-movie-title">
            ${movie.name}<#if (movie.year)?? && movie.year gt 0> <span>(${movie.year})</span></#if>
        </div>
        <div class="pets-movie-desc">
            <div class="pets-movie-pic">
                <img width="225" height="317" data="${(movie.pic+"-media")!''}">
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
                <#if movie.alias??>
                <dl>
                    <dt>别&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名：</dt>
                    <dd>${movie.alias}</dd>
                </dl>
                </#if>
                <dl>
                    <dt>影片介绍：</dt>
                    <dd>
                        <#if movieInfoList?? && movieInfoList?size gt 0>
                        <#list movieInfoList as info>
                            <#if info.infoType ==1 >
                                <a href="http://movie.douban.com/subject/${info.refer}" target="_blank" class="douban">豆瓣链接</a>
                            </#if>
                            <#if info.infoType ==2 >
                                <a href="http://movie.mtime.com/${info.refer}" target="_blank" class="mtime">Mtime链接</a>
                            </#if>
                        </#list>
                        <#else>
                            暂无
                        </#if>
                    </dd>
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
            <#if moviePlayList?? && moviePlayList?size gt 0>
                <#list moviePlayList as play>
                <#if play?? && play.playType==1><a href="${play.address}" target="_blank" class="play"><i class="youku"></i>优酷</a></#if>
                <#if play?? && play.playType==2><a href="${play.address}" target="_blank" class="play"><i class="tudou"></i>土豆</a></#if>
                <#if play?? && play.playType==3><a href="${play.address}" target="_blank" class="play"><i class="iqiyi"></i>爱奇艺</a></#if>
                <#if play?? && play.playType==4><a href="${play.address}" target="_blank" class="play"><i class="qq"></i>腾讯</a></#if>
                <#if play?? && play.playType==5><a href="${play.address}" target="_blank" class="play"><i class="pptv"></i>PPTV</a></#if>
                <#if play?? && play.playType==6><a href="${play.address}" target="_blank" class="play"><i class="le"></i>乐视</a></#if>
                </#list>
            <#else>
                暂无播放信息
            </#if>
        </div>
    </div>
    <div class="pets-movie-other">
        <div class="tips">推荐您看：</div>
        <div class="other-list">
            <#if relatedMovieList??>
            <#list relatedMovieList as movie>
                <div class="other-movie">
                    <a href="/media/${movie.id}" target="_blank"><img data="${(movie.pic+"-mpic")!''}"></a>
                    <a href="/media/${movie.id}" target="_blank" class="name">${movie.name}</a>
                </div>
            </#list>
            </#if>
        </div>
    </div>
</div>
<script>
$(function(){
    $(".pets-movie-pic img").each(function(){
        $(this).loadpic({
            src: $(this).attr('data'),
            mw: 225,
            mh: 317
        });
    });

    $(".pets-movie-other img").each(function(){
        $(this).loadpic({
            src: $(this).attr('data'),
            mw: 150,
            mh: 220
        });
    });
});
</script>
</body>
</html>
