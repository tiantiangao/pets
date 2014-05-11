<#assign pets=JspTaglibs["/WEB-INF/tld/pets-tags.tld"]>
<html>
<head>
	<title>${(type.name)!""}-宠物百科</title>
	<@pets.staticResource resource='/css/baike-detail.css' decorate='true'/>
</head>
<body>
<div class="container">
    <div class="baike-container">
        <div class="baike-nav">
            <ul class="breadcrumb">
                <li><a href="/baike">宠物百科</a><span class="divider">&gt;</span> </li>
                <#list categoryPath as category>
                    <#if !category_has_next>
                        <#assign currentCategory = category>
                    </#if>
                    <li><a href="/baike/list/${category.id}">${(category.name)!""}</a><span class="divider">&gt;</span></li>
                </#list>
                <li class="active">${(type.name)!""}</li>
            </ul>
        </div>

        <#if (type.picUrl)??>
        <div class="baike-pic">
            <div><img src="${type.picUrl}" ></div>
        </div>
        </#if>

        <div class="baike-desc">
            <div class="baike-title"><h4>${(type.name)!""}</h4></div>
            <div class="baike-story">
                <div class="baike-story-container">
                    ${type.desc}
                </div>
            </div>
        </div>

        <div class="baike-detail">
            <div class="baike-detail-row baike-basic">
                <div class="baike-detail-title">基本信息</div>
                <div class="baike-detail-body">
                    <#list attrList as attr>
                    <dl <#if attr.singleLine>class="single"</#if>>
                        <dt>${attr.name}:</dt>
						<#if attr.attrName=="dogFCIGroup">
                            <dd><a href="/baike/FCI#G${extraInfo['dogFCIGroupID']!1}">${attr.value}</a></dd>
						<#elseif attr.attrName=="dogFCISection">
                            <dd><a href="/baike/FCI/section/${extraInfo['dogFCISectionID']!1}#${type.id}">${attr.value}</a></dd>
						<#elseif attr.attrName="catCFA">
                            <dd><a href="${extraInfo['catCFALink']!''}" target="_blank" rel="nofollow">CFA ${attr.value}</a><span class="memo"><a href="/baike/CFA">什么是CFA?</a></span></dd>
						<#else>
                            <dd>${attr.value}</dd>
						</#if>
                    </dl>
					<#if attr.singleLine>
                    <div class="clear"></div>
					</#if>
                    </#list>
                    <div class="clear"></div>
                    <#if foodAD??>
					<div class="goods-box">
						<div class="goods-main">
                            <a href="${foodAD.adwordsUrl}" target="_blank">
							<div class="goods-dt-img">
								<img src="${foodAD.picUrl}">
							</div>
                            </a>
							<h2 class="goods-title">
								<a href="${foodAD.adwordsUrl}" target="_blank">${foodAD.adwordsTitle}</a>
							</h2>
						</div>
					</div>
                    </#if>
                </div>
            </div>
			<#if featureList?? && featureList?size gt 0 >
            <div class="baike-detail-row baike-feature">
                <div class="baike-detail-title">特征标准</div>
                <div class="baike-detail-body">
					<#list featureList as feature>
					<dl>
						<dt>${feature.name}:</dt>
						<dd><span class="rank rank-star${feature.value}" ></span></dd>
					</dl>
					</#list>
                    <div class="clear"></div>
                        <#if otherAD??>
                        <div class="goods-box">
                            <div class="goods-main">
                                <a href="${otherAD.adwordsUrl}" target="_blank">
                                    <div class="goods-dt-img">
                                        <img src="${otherAD.picUrl}">
                                    </div>
                                </a>
                                <h2 class="goods-title">
                                    <a href="${otherAD.adwordsUrl}" target="_blank">${otherAD.adwordsTitle}</a>
                                </h2>
                            </div>
                        </div>
                        </#if>
                </div>
            </div>
			</#if>
            <div class="baike-detail-row baike-characteristics">
                <div class="baike-detail-title">其他信息</div>
                <div class="baike-detail-body">
					${desc!"暂无"}
                </div>
            </div>
        </div>
    </div>
</div>
</body>