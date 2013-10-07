<#include "/WEB-INF/pages/util/page.ftl">
<html>
<head>
    <title>宠物影视</title>
    <link href="/css/movie.css" rel="stylesheet">
    <script src="/js/petspic.js"></script>
</head>
<body>
<div class="container">
    <div class="container">
        <div class="row">
            <div class="span9">
                <div class="pets-movie-nav pets-movie-container">
                    <div class="nav-title">
                        <h3>宠物电影</h3>
                    </div>
                    <div class="nav-filter-list">
                        <div class="nav-filter">
                            <ul class="inline">
                                <span class="filter-name">地区：</span>
                                <a href="${buildUrl(0, -1, -1)}"><li <#if area==0>class="active"</#if>>全部</li></a>
                                <#list regionList as regionDTO>
                                    <a href="${buildUrl(regionDTO.id, -1, -1)}"><li <#if area==regionDTO.id>class="active"</#if>>${regionDTO.region}</li></a>
                                </#list>
                            </ul>
                        </div>
                        <div class="nav-filter last">
                            <ul class="inline">
                                <span class="filter-name">年代：</span>
                                <a href="${buildUrl(-1, 0, -1)}"><li <#if year==0>class="active"</#if>>全部</li></a>
                                <#list yearList as yearDTO>
                                    <a href="${buildUrl(-1, yearDTO.id, -1)}"><li <#if year==yearDTO.id>class="active"</#if>><#if yearDTO.year==1900>其他年代<#else>${yearDTO.year}</#if></li></a>
                                </#list>
                            </ul>
                        </div>
                    </div>
                </div>
                <div class="pets-movie-list pets-movie-container">
                    <div class="view-filter">
                        <div class="filter-order">
                            <a href="${buildUrl(-1, -1, 1)}" class="order<#if sortBy==1> active</#if>"><span>按时间排序</span></a>
                            <span class="slash">|</span>
                            <a href="${buildUrl(-1, -1, 2)}" class="order<#if sortBy==2> active</#if>"><span>按名称排序</span></a>
                        </div>
                        <div class="filter-view-mode">
                            <!--
                            <a href="#">
                                <i class="icon-th-large"></i>
                                <span class="mode-text">列表模式</span>
                            </a>
                            <a href="#" class="active">
                                <i class="icon-th-list"></i>
                                <span class="mode-text">图片模式</span>
                            </a>
                            -->
                        </div>
                    </div>
                    <#assign hasRecords = (movieModel.records)?? && movieModel.records?size gt 0 >
                    <div class="row-fluid">
                        <#assign hasEnd=false>
						<#list movieModel.records as movie>
						<#if movie_index%5==0>
                        <#assign hasEnd=false>
                        <div class="pets-movie-row">
						</#if>
                            <div class="pets-movie">
                                <a href="/media/${movie.id}"><img width="150" height="220" data="${(movie.pic+"-mpic")!''}"></a>
                                <div class="pets-movie-title">
                                    <a href="/media/${movie.id}">${movie.name}</a>
                                </div>
                                <div class="pets-movie-desc" >
                                    ${movie.desc}
                                </div>
                            </div>
						<#if movie_index%5==4>
                        <#assign hasEnd=true>
                        </div>
						</#if>
						</#list>
                        <#if hasRecords && !hasEnd>
                        </div>
                        </#if>
                        <#if hasRecords>
                        <@pageNavigation movieModel.page movieModel.pageCount buildUrl(-1, -1, -1) />
                        <#else>
                        <div class="norecords-container">
                           <div class="norecords">
                               抱歉，没有找到满足当前条件的宠物电影。<br/>
                               建议您：<br/>
                               <ul>
                                   <li>查看其他地区的宠物电影。</li>
                                   <li>查看其他年代的宠物电影。</li>
                               </ul>
                           </div>
                        </div>
                        </#if>
                    </div>
                </div>
            </div>
            <div class="span3">
                <#if recommendMovie??>
                <div class="pets-movie-recommend pets-movie-container">
                    <ul class="nav nav-list">
                        <li class="nav-header"><h5>优秀宠物电影推荐</h5></li>
                        <li>
                            <div class="recommend-movie">
                                <a href="/media/${recommendMovie.id}"><img width="228" height="334" data="${(recommendMovie.pic+"-mpic")!''}"></a>
                                <div class="recommend-movie-title">
                                    <a href="/media/${recommendMovie.id}">${recommendMovie.name}</a>
                                </div>
                                <div class="recommend-movie-desc" >
                                    ${(recommendMovie.desc)!""}
                                </div>
                            </div>
                        </li>
                    </ul>
                </div>
                </#if>
                <#if hotMovieList?? && hotMovieList?size gt 0>
                <div class="pets-movie-hot pets-movie-container">
                    <ul class="nav nav-list">
                        <li class="nav-header"><h5>热门宠物电影排行</h5></li>
                        <#list hotMovieList as movie>
                            <#assign index = movie_index +1/>
                            <li><a href="/media/${movie.id}"><em><#if index lt 10>0${index}<#else>${index}</#if>.</em> ${movie.name}</a></li>
                        </#list>
                    </ul>
                </div>
                </#if>
                <#if newMovieList?? && newMovieList?size gt 0>
                <div class="pets-movie-new pets-movie-container">
                    <ul class="nav nav-list">
                        <li class="nav-header"><h5>最新宠物电影排行</h5></li>
                        <#list newMovieList as movie>
                            <#assign index = movie_index +1/>
                            <li><a href="/media/${movie.id}"><em><#if index lt 10>0${index}<#else>${index}</#if>.</em> ${movie.name}</a></li>
                        </#list>
                    </ul>
                </div>
                </#if>
            </div>
        </div>
    </div>
</div>
<script>
    $(function(){
        $(".pets-movie-list img").each(function(){
            $(this).loadpic({
                src: $(this).attr('data'),
                mw: 150,
                mh: 220
            });
        });

        $(".recommend-movie img").each(function(){
            $(this).loadpic({
                src: $(this).attr('data'),
                mw: 228,
                mh: 318
            });
        });
    });
</script>
</body>
</html>
<#function buildUrl toArea=-1 toYear=-1 toSortBy=-1>
<#local url = "/media/">
<#if toArea!=-1>
    <#if toArea!=0>
        <#local url = url + "r" + toArea>
    </#if>
<#elseif area!=0>
    <#local url = url + "r" + area>
</#if>
<#if toYear!=-1>
    <#if toYear!=0>
        <#local url = url + "y" + toYear>
    </#if>
<#elseif year!=0>
    <#local url = url + "y" + year>
</#if>
<#if toSortBy!=-1>
    <#if toSortBy==2>
        <#local url = url + "o2">
    </#if>
<#elseif sortBy==2>
    <#local url = url + "o2">
</#if>
<#if url?ends_with("/")>
    <#local url = url?substring(0, url?length - 1)>
</#if>
<#return url>
</#function>
