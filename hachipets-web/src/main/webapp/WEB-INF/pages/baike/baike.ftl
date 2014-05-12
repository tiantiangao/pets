<#include "/WEB-INF/pages/util/page.ftl">
<#assign pets=JspTaglibs["/WEB-INF/tld/pets-tags.tld"]>
<html>
<head>
    <title><#if path?? && path?size gt 0><#list path as category>${category.name}-</#list></#if>宠物百科</title>
	<@pets.staticResource resource='/css/baike-index.css' decorate='true'/>
</head>
<body>
<div class="container">
	<div class="baike-filter">
		<#list categoryGroupList as group>
		<div class="group">
			<div class="group-filter">
				<span class="filter-name">${(group.title)!""}：</span>
				<div class="filter-list">
					<#list group.categoryList as category>
					<a href="/baike/list/${category.category.id}">
						<div <#if category.checked?? && category.checked==true>class="active item"<#else>class="item"</#if>>${category.category.name}</div>
					</a>
					</#list>
				</div>
				<div class="clear"></div>
			</div>
		</div>
		</#list>
	</div>
	<div class="baike-content">
		<#assign hasRecords = (typeModel.records)?? && typeModel.records?size gt 0 >
		<ul class="baike-list">
			<#list typeModel.records as type>
			<li>
				<div class="p-img">
					<a href="/baike/${type.id}">
						<img data="${type.thumbPicUrl}">
					</a>
				</div>
				<div class="p-name">
					<a href="/baike/${type.id}">${type.name}</a>
				</div>
                <div class="p-name p-name-alias">
                    ${type.alias}
                </div>
			</li>
			</#list>
		</ul>
		<#if hasRecords>
			<div class="baike-pages">
				<@pageNavigation typeModel.page typeModel.pageCount "/baike/list/"+categoryId />
			</div>
		<#else>
            <div class="norecords-container">
                <div class="norecords">
                    抱歉，没有找到当前类型的宠物。<br/>
                    建议您：<br/>
                    <ul>
                        <li>查看其他类型的宠物。</li>
                    </ul>
                </div>
            </div>
		</#if>
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
    });
</script>
</body>
</html>
