<html>
<head>
    <title>宠物影视</title>
    <link href="/css/movie.css" rel="stylesheet">
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
                                <li class="active"><a href="">全部</a></li>
                                <#list regionList as region>
                                    <li><a href="">${region}</a></li>
                                </#list>
                                <li><a href="">其他地区</a></li>
                            </ul>
                        </div>
                        <div class="nav-filter last">
                            <ul class="inline">
                                <span class="filter-name">年代：</span>
                                <li class="active">全部</li>
                                <#list yearList as year>
                                    <li><a href="">${year}</a></li>
                                </#list>
                                <li><a href="">其他</a></li>
                            </ul>
                        </div>
                    </div>
                </div>
                <div class="pets-movie-list pets-movie-container">
                    <div class="view-filter">
                        <div class="filter-order">
                            <a href="#" class="order active"><span>按时间排序</span></a>
                            <span class="slash">|</span>
                            <a href="#" class="order"><span>按名称排序</span></a>
                        </div>
                        <div class="filter-view-mode">
                            <a href="#">
                                <i class="icon-th-large"></i>
                                <span class="mode-text">列表模式</span>
                            </a>
                            <a href="#" class="active">
                                <i class="icon-th-list"></i>
                                <span class="mode-text">图片模式</span>
                            </a>
                        </div>
                    </div>
                    <div class="row-fluid">
						<#list movieModel.records as movie>
						<#if movie_index%5==0>
                        <div class="pets-movie-row">
						</#if>
                            <div class="pets-movie">
                                <a href="/media/${movie.id}"><img style="width: 150px; height: 220px;" src="${(movie.pic)!''}"></a>
                                <div class="pets-movie-title">
                                    <a href="/media/${movie.id}">${movie.name}</a>
                                </div>
                                <div class="pets-movie-desc" >
                                    ${movie.desc}
                                </div>
                            </div>
						<#if movie_index%5==4>
                        </div>
						</#if>
						</#list>
                        <div class="pets-pages">
                            <span class="disable"><< 上一页</span>
                            <a href="#" class="PageLink">1</a>
                            <a href="#" class="PageLink">2</a>
                            <span>3</span>
                            <a href="#" class="PageLink">...</a>
                            <a href="#" class="NextPage">下一页 >></a>
                        </div>
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
                                <a href="/media/${recommendMovie.id}"><img style="width: 228px; height: 334;" src="${(recommendMovie.pic)!''}"></a>
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
</body>
</html>
