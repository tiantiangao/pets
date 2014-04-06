<#assign pets=JspTaglibs["/WEB-INF/tld/pets-tags.tld"]>
<html>
<head>
    <title>宠物百科</title>
	<@pets.staticResource resource='/css/baike-index.css' decorate='true'/>
	<@pets.staticResource resource='/js/petspic.js' decorate='true'/>
</head>
<body>
<div class="container">
	<div class="baike-filter">
		<#list categoryGroupList as group>
		<div class="group">
			<ul class="inline">
				<span class="filter-name">${(group.title)!""}：</span>
				<div class="filter-list">
					<#list group.categoryList as category>
					<a href="/baike/list/${category.category.id}">
						<li<#if category.checked?? && category.checked==true> class="active"</#if>>${category.category.name}</li>
					</a>
					</#list>
				</div>
				<div class="clear"></div>
			</ul>
		</div>
		</#list>
	</div>
	<div class="baike-content">
		<ul class="baike-list">
			<li>
				<div class="p-img">
					<a href="/baike/1">
						<img data="http://img2.ph.126.net/x5dGGiE4Bb-OloQch9b2aA==/713257591084919153.png">
					</a>
				</div>
				<div class="p-name">
					<a href="/baike/1" title="又名俾路支猎犬,喀布尔犬">阿富汗猎犬</a>
				</div>
			</li>
			<li>
				<div class="p-img">
					<a href="/baike/2">
						<img data="http://img0.ph.126.net/Id1sdQT5PxTScUAOXe9LFw==/147211412919962583.png">
					</a>
				</div>
				<div class="p-name">
					<a href="/baike/2" title="又名兔猫、埃塞俄比亚猫、阿比、红阿比">阿比西尼亚猫</a>
				</div>
			</li>
			<li>
				<div class="p-img">
					<a href="/baike/3">
						<img data="http://img1.ph.126.net/_50XsZmggQjQYZIgYvQUew==/2876111312129553622.png">
					</a>
				</div>
				<div class="p-name">
					<a href="/baike/3" title="又名兔猫、埃塞俄比亚猫、阿比、红阿比">皇冠珍珠</a>
				</div>
			</li>
			<li>
				<div class="p-img">
					<a href="/baike/1">
						<img data="http://img0.ph.126.net/sCkilHcnTbIdBdTmUPl-pg==/1992279885258236010.png">
					</a>
				</div>
				<div class="p-name">
					<a href="/baike/1" title="又名俾路支猎犬,喀布尔犬">阿富汗猎犬</a>
				</div>
			</li>
			<li>
				<div class="p-img">
					<a href="/baike/2">
						<img data="http://img0.ph.126.net/BPO3s_UmECXJ4FhCyomKDQ==/6597292967518389576.png">
					</a>
				</div>
				<div class="p-name">
					<a href="/baike/2" title="又名兔猫、埃塞俄比亚猫、阿比、红阿比">阿比西尼亚猫</a>
				</div>
			</li>
			<div class="hdivider"></div>
			<li>
				<div class="p-img">
					<a href="/baike/3">
						<img data="http://img0.ph.126.net/KedCcBoEkc2pr29uhyAK1Q==/2252644238715483876.png">
					</a>
				</div>
				<div class="p-name">
					<a href="/baike/3" title="又名兔猫、埃塞俄比亚猫、阿比、红阿比">皇冠珍珠</a>
				</div>
			</li>
			<li>
				<div class="p-img">
					<a href="/baike/1">
						<img data="http://img0.ph.126.net/rdOOy6weqkMSP8YRDJenpg==/3855925706059623036.png">
					</a>
				</div>
				<div class="p-name">
					<a href="/baike/1" title="又名俾路支猎犬,喀布尔犬">阿富汗猎犬</a>
				</div>
			</li>
			<li>
				<div class="p-img">
					<a href="/baike/1">
						<img data="http://img2.ph.126.net/x5dGGiE4Bb-OloQch9b2aA==/713257591084919153.png">
					</a>
				</div>
				<div class="p-name">
					<a href="/baike/1" title="又名俾路支猎犬,喀布尔犬">阿富汗猎犬</a>
				</div>
			</li>
			<li>
				<div class="p-img">
					<a href="/baike/2">
						<img data="http://img0.ph.126.net/Id1sdQT5PxTScUAOXe9LFw==/147211412919962583.png">
					</a>
				</div>
				<div class="p-name">
					<a href="/baike/2" title="又名兔猫、埃塞俄比亚猫、阿比、红阿比">阿比西尼亚猫</a>
				</div>
			</li>
			<li>
				<div class="p-img">
					<a href="/baike/3">
						<img data="http://img1.ph.126.net/_50XsZmggQjQYZIgYvQUew==/2876111312129553622.png">
					</a>
				</div>
				<div class="p-name">
					<a href="/baike/3" title="又名兔猫、埃塞俄比亚猫、阿比、红阿比">皇冠珍珠</a>
				</div>
			</li>
			<div class="hdivider"></div>
			<li>
				<div class="p-img">
					<a href="/baike/1">
						<img data="http://img0.ph.126.net/sCkilHcnTbIdBdTmUPl-pg==/1992279885258236010.png">
					</a>
				</div>
				<div class="p-name">
					<a href="/baike/1" title="又名俾路支猎犬,喀布尔犬">阿富汗猎犬</a>
				</div>
			</li>
			<li>
				<div class="p-img">
					<a href="/baike/2">
						<img data="http://img0.ph.126.net/BPO3s_UmECXJ4FhCyomKDQ==/6597292967518389576.png">
					</a>
				</div>
				<div class="p-name">
					<a href="/baike/2" title="又名兔猫、埃塞俄比亚猫、阿比、红阿比">阿比西尼亚猫</a>
				</div>
			</li>
			<li>
				<div class="p-img">
					<a href="/baike/3">
						<img data="http://img0.ph.126.net/KedCcBoEkc2pr29uhyAK1Q==/2252644238715483876.png">
					</a>
				</div>
				<div class="p-name">
					<a href="/baike/3" title="又名兔猫、埃塞俄比亚猫、阿比、红阿比">皇冠珍珠</a>
				</div>
			</li>
			<li>
				<div class="p-img">
					<a href="/baike/1">
						<img data="http://img0.ph.126.net/rdOOy6weqkMSP8YRDJenpg==/3855925706059623036.png">
					</a>
				</div>
				<div class="p-name">
					<a href="/baike/1" title="又名俾路支猎犬,喀布尔犬">阿富汗猎犬</a>
				</div>
			</li>
			<li>
				<div class="p-img">
					<a href="/baike/1">
						<img data="http://img2.ph.126.net/x5dGGiE4Bb-OloQch9b2aA==/713257591084919153.png">
					</a>
				</div>
				<div class="p-name">
					<a href="/baike/1" title="又名俾路支猎犬,喀布尔犬">阿富汗猎犬</a>
				</div>
			</li>
			<div class="hdivider"></div>
			<li>
				<div class="p-img">
					<a href="/baike/2">
						<img data="http://img0.ph.126.net/Id1sdQT5PxTScUAOXe9LFw==/147211412919962583.png">
					</a>
				</div>
				<div class="p-name">
					<a href="/baike/2" title="又名兔猫、埃塞俄比亚猫、阿比、红阿比">阿比西尼亚猫</a>
				</div>
			</li>
			<li>
				<div class="p-img">
					<a href="/baike/3">
						<img data="http://img1.ph.126.net/_50XsZmggQjQYZIgYvQUew==/2876111312129553622.png">
					</a>
				</div>
				<div class="p-name">
					<a href="/baike/3" title="又名兔猫、埃塞俄比亚猫、阿比、红阿比">皇冠珍珠</a>
				</div>
			</li>
			<li>
				<div class="p-img">
					<a href="/baike/1">
						<img data="http://img0.ph.126.net/sCkilHcnTbIdBdTmUPl-pg==/1992279885258236010.png">
					</a>
				</div>
				<div class="p-name">
					<a href="/baike/1" title="又名俾路支猎犬,喀布尔犬">阿富汗猎犬</a>
				</div>
			</li>
			<li>
				<div class="p-img">
					<a href="/baike/2">
						<img data="http://img0.ph.126.net/BPO3s_UmECXJ4FhCyomKDQ==/6597292967518389576.png">
					</a>
				</div>
				<div class="p-name">
					<a href="/baike/2" title="又名兔猫、埃塞俄比亚猫、阿比、红阿比">阿比西尼亚猫</a>
				</div>
			</li>
			<li>
				<div class="p-img">
					<a href="/baike/3">
						<img data="http://img0.ph.126.net/KedCcBoEkc2pr29uhyAK1Q==/2252644238715483876.png">
					</a>
				</div>
				<div class="p-name">
					<a href="/baike/3" title="又名兔猫、埃塞俄比亚猫、阿比、红阿比">皇冠珍珠</a>
				</div>
			</li>
			<div class="hdivider"></div>
			<li>
				<div class="p-img">
					<a href="/baike/1">
						<img data="http://img0.ph.126.net/rdOOy6weqkMSP8YRDJenpg==/3855925706059623036.png">
					</a>
				</div>
				<div class="p-name">
					<a href="/baike/1" title="又名俾路支猎犬,喀布尔犬">阿富汗猎犬</a>
				</div>
			</li>
			<li>
				<div class="p-img">
					<a href="/baike/1">
						<img data="http://img0.ph.126.net/sCkilHcnTbIdBdTmUPl-pg==/1992279885258236010.png">
					</a>
				</div>
				<div class="p-name">
					<a href="/baike/1" title="又名俾路支猎犬,喀布尔犬">阿富汗猎犬</a>
				</div>
			</li>
			<li>
				<div class="p-img">
					<a href="/baike/2">
						<img data="http://img0.ph.126.net/BPO3s_UmECXJ4FhCyomKDQ==/6597292967518389576.png">
					</a>
				</div>
				<div class="p-name">
					<a href="/baike/2" title="又名兔猫、埃塞俄比亚猫、阿比、红阿比">阿比西尼亚猫</a>
				</div>
			</li>
			<li>
				<div class="p-img">
					<a href="/baike/3">
						<img data="http://img0.ph.126.net/KedCcBoEkc2pr29uhyAK1Q==/2252644238715483876.png">
					</a>
				</div>
				<div class="p-name">
					<a href="/baike/3" title="又名兔猫、埃塞俄比亚猫、阿比、红阿比">皇冠珍珠</a>
				</div>
			</li>
			<li>
				<div class="p-img">
					<a href="/baike/1">
						<img data="http://img0.ph.126.net/rdOOy6weqkMSP8YRDJenpg==/3855925706059623036.png">
					</a>
				</div>
				<div class="p-name">
					<a href="/baike/1" title="又名俾路支猎犬,喀布尔犬">阿富汗猎犬</a>
				</div>
			</li>
		</ul>
		<div class="baike-pages">
			<span class="disable"><< 上一页</span>
			<a href="#" class="PageLink">1</a>
			<a href="#" class="PageLink">2</a>
			<span>3</span>
			<a href="#" class="PageLink">...</a>
			<a href="#" class="NextPage">下一页 >></a>
		</div>
	</div>
</div>
<script>
    $(function(){
        $(".baike-list img").each(function(){
            $(this).loadpic({
                src: $(this).attr('data'),
                mw: 210,
                mh: 200
            });
        });
    });
</script>
</body>
</html>
