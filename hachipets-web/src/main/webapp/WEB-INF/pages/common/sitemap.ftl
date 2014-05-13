<#assign pets=JspTaglibs["/WEB-INF/tld/pets-tags.tld"]>
<?xml version="1.0" encoding="UTF-8"?>
<urlset xmlns="http://www.sitemaps.org/schemas/sitemap/0.9">
	<#list urlList as url>
	<url>
		<loc>${url.url}</loc>
		<lastmod>${url.updateTime?string("yyyy-MM-dd")}</lastmod>
	</url>
	</#list>
</urlset>