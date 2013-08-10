<#macro pageNavigation currentPage pageCount url>
<#setting number_format="#">
    <#if pageCount gt 1>

    <#--
        开始页 : 求最大值(当前页和3的差值 , 1)
    -->
        <#if ((currentPage - 3) > 1)>
            <#assign startPage = (currentPage - 3)>
        <#else>
            <#assign startPage = 1>
        </#if>

    <#--
        结束页 : 求最小值(开始页+6, 总页数)
    -->
        <#if ((startPage + 6) < pageCount)>
            <#assign endPage = (startPage + 6)>
        <#else>
            <#assign endPage = pageCount>
        </#if>

    <div class="pets-pages">
    <#--如果当前页大于第一页，输出上一页导航-->
        <#if (currentPage > 1) >
            <a href="${url}?page=${currentPage-1}" class="PrevPage" title="上一页"><< 上一页</a>
        <#else>
            <span class="disable"><< 上一页</span>
        </#if>

    <#--开始输出页码导航-->
    <#--
        如果开始页大于1 (表示当前页和3的差值大于1)
            先输出"第一页的link"和"..."
        否则跳过
            然后由遍历的过程输出第一页的链接
    -->
        <#if (startPage > 1)>
            <a href="${url}?page=1" class="PageLink" title="1">1</a>
            <#--<a href="#" class="PageLink">...</a>-->
            <#if (1 < startPage - 1)>
                <a href="${url}?page=${1 + (startPage - 1)/2}" class="PageLink" title="${1 + (startPage - 1)/2}">...</a>
            </#if>
        </#if>

    <#--
        遍历输出开始页到结束页的链接
            如果是当前页，页码没有链接并且有自己的样式
    -->
        <#if (startPage <= endPage)>
            <#list startPage..endPage as page>
                <#if currentPage == page>
                    <span class="PageSel">${page}</span>
                <#else>
                    <a href="${url}?page=${page}" class="PageLink" title="${page}">${page}</a>
                </#if>
            </#list>
        </#if>

    <#--
        如果endPage < pageCount 	(表示结束页是startPage + 6，否则endPage = pageCount)
            1.先判断是否小于最后一页的前一页,如果是先输出"..."，否则跳过
            2.单独输出最后一页
        否则跳过
            实质上在上面的遍历的过程中已经输出了最后一页的链接
    -->
        <#if (endPage < pageCount)>
            <#if (endPage < pageCount - 1)>
                <a href="${url}?page=${endPage + (pageCount-endPage)/2}" class="PageLink" title="${endPage + (pageCount-endPage)/2}">...</a>
            </#if>
            <a href="${url}?page=${pageCount}" class="PageLink" title="${pageCount}">${pageCount}</a>
        </#if>

    <#--如果当前页小于总页数，输出下一页导航-->
        <#if (currentPage < pageCount)>
            <a href="${url}?page=${currentPage+1}" class="NextPage" title="下一页">下一页 >></a>
        <#else>
            <span class="disable">下一页 >></span>
        </#if>
    <#--结束输出页码导航-->
    </div>
    </#if>
</#macro>