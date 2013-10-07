<html>
<head>
    <title>FCI分组之${(section.sectionName)!""}(${(section.sectionEnName)!""})-国际犬种FCI标准-宠物百科</title>
    <link href="/css/baike-fci.css" rel="stylesheet">
</head>
<body>
<div class="container">
<div class="baike-container">
<div class="baike-nav">
    <ul class="breadcrumb">
        <li><a href="/baike">宠物百科</a><span class="divider">&gt;</span> </li>
        <li class="active">国际犬种FCI标准</li>
    </ul>
</div>
<div class="baike-fci">
    <h4 class="title"><a href="/baike/FCI#G${(group.groupId)!0}">${(group.groupName)!""}</a></h4>
    <h4 class="titleen">${(group.groupEnName)!""}</h4>
    <div class="sub">
        <#if prevSection??>
        <#if prevGroup??>
            <#assign prevTitle = prevGroup.groupName + "(" + prevSection.sectionName + ")">
        <#else>
            <#assign prevTitle = prevSection.sectionName>
        </#if>
        <a href="/baike/FCI/section/${prevSection.sectionId}" title="${prevTitle}">
            <div class="arrow left">
                <div class="subtitleen">上一组</div>
            </div>
        </a>
        </#if>
        <div class="subtitle">${(section.sectionName)!""}</div>
        <div class="subtitleen">${(section.sectionEnName)!""}</div>
        <#if nextSection??>
            <#if nextGroup??>
                <#assign nextTitle = nextGroup.groupName + "(" + nextSection.sectionName + ")">
            <#else>
                <#assign nextTitle = nextSection.sectionName>
            </#if>
        <a href="/baike/FCI/section/${nextSection.sectionId}" title="${nextTitle}">
            <div class="arrow right">
                <div class="subtitleen">下一组</div>
            </div>
        </a>
        </#if>
    </div>
    <div class="content">
        <table cellspacing="0">
            <colgroup>
                <col style="width: 20%;">
                <col style="width: 30%;">
                <col style="width: 15%;">
                <col style="width: 15%;">
                <col style="width: 20%;">
            </colgroup>
            <tr>
                <th scope="col">现代犬种名</th>
                <th scope="col">英文名</th>
                <th scope="col">起源地</th>
                <th scope="col">FCI编号</th>
                <th scope="col">图片</th>
            </tr>
            <#list dogList as dog>
            <tr<#if dog_index%2 != 0> class="odd"</#if>>
                <td class="first">
                    <#if (dog.petId)??>
                        <a href="/baike/${dog.petId}">${(dog.name)!""}</a>
                    <#else>
                        ${(dog.name)!""}
                    </#if>
                </td>
                <td>
                    <#if (dog.petId)??>
                        <a href="/baike/${dog.petId}">${(dog.enName)!""}</a>
                    <#else>
                        ${(dog.enName)!""}
                    </#if>
                </td>
                <td class="center">${(dog.origin)!""}</td>
                <td class="center">${(dog.fciNo)!""}</td>
                <td class="center">
                    <#if (dog.picUrl)??>
                        <#if (dog.petId)??>
                            <a href="/baike/1"><img src="${dog.picUrl}" width="120" height="98"></a>
                        <#else>
                            <img src="${dog.picUrl}" width="120" height="98">
                        </#if>
                    <#else>
                        &nbsp;
                    </#if>
                </td>
            </tr>
            </#list>
            <#if dogList?size lt 1>
            <tr>
                <td colspan="5" class="first center empty">对不起，暂时没有相关记录!</td>
            </tr>
            </#if>
        </table>
    </div>
</div>
</div>
</div>
</body>