<#assign pets=JspTaglibs["/WEB-INF/tld/pets-tags.tld"]>
<html>
<head>
<title>宠物医院</title>
<@pets.staticResource resource='/css/hospital.css' decorate='true'/>
</head>
<body>
<div class="container text-center map-container">
	<div id="mapLeft">
        <div id="mapLeftContainer">
			<div id="shopListTitle">宠物医院列表</div>
			<div id="shopList" class="shopContainer text-left">
			</div>
			<div class="logo">
                <div class="dianping"><span>数据来源于</span></div>
			</div>
		</div>
	</div>
	<div id="mapRight">
		<div id="mapTool">
			<div id="mapCity">您当前所在城市：<span id="mapCityName">${hospitalCity.cityName}</span></div>
            <div class="autoCity"></div>
		</div>
		<div id="mapDiv" style="width: 839px; height: 624px;"></div>
	 </div>
	<div class="clear"></div>
</div>
<script type="text/javascript" src="http://int.dpool.sina.com.cn/iplookup/iplookup.php?format=js"></script>
<@pets.staticResource resource='/js/hospital.js' decorate='true'/>
<script type="text/javascript">
    $(function(){
		setCurrentCity("${hospitalCity.cityName}");
		setDefaultMap(${hospitalCity.lng?string("###.######")}, ${hospitalCity.lat?string("###.######")}, ${hospitalCity.level});
		loadMap();
    });
</script>
</body>
</html>
