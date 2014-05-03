<#assign pets=JspTaglibs["/WEB-INF/tld/pets-tags.tld"]>
<html>
<head>
    <meta name="baidu-site-verification" content="OngVwAYyF8" />
    <meta name="google-site-verification" content="JPZLPomYLrdNJ88JQ-DYTxaoXB5X7rmG-RlujK5llIk" />
    <meta name="360-site-verification" content="08e09397d1ff642354112ff729c0580d" />
    <title>首页</title>
	<@pets.staticResource resource='/css/index.css' decorate='true'/>
</head>
<body>
<div class="container index-container container-baike">
    <div class="index-menu">
        <div class="index-mt">
            <span class="index-mt-icon icon-baike"></span>
            <span class="index-mt-label">热门宠物</span>
        </div>
        <div class="index-mc">
        <ul class="baike-list">
		<#list baikeList as baike>
		<li>
			<div class="p-img">
				<a href="/baike/${baike.id}">
					<img data="${baike.thumbPicUrl!''}">
				</a>
			</div>
			<div class="p-name">
				<a href="/baike/${baike.id}">${baike.name}</a>
			</div>
			<div class="p-name p-name-alias">
				${baike.desc}
			</div>
		</li>
		</#list>
        </ul>
        </div>
    </div>
</div>
<div class="container index-container container-media">
    <div class="index-menu">
        <div class="index-mt">
            <span class="index-mt-icon icon-media"></span>
            <span class="index-mt-label">热门影视</span>
        </div>
        <div class="index-mc">
            <div class="pets-movie-row">
				<#list movieList as movie>
				<div class="pets-movie">
					<a href="/media/${movie.id}"><img width="150" height="220" data="${(movie.pic+"-mpic.jpg")!''}"></a>
					<div class="pets-movie-title">
						<a href="/media/${movie.id}">${movie.name}</a>
					</div>
					<div class="pets-movie-desc">
						${movie.desc}
					</div>
				</div>
				</#list>
            </div>
        </div>
    </div>
</div>
<@pets.staticResource resource='/js/petspic.js' decorate='true'/>
<script>
    $(function(){
        $(".baike-list img").each(function(){
            $(this).loadpic({
                src: $(this).attr('data'),
                mw: 210,
                mh: 200
            });
        });
        $(".pets-movie-row img").each(function(){
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
