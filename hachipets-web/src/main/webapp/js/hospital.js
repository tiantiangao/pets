var map, currentCity, lng, lat, level;

function setCurrentCity(city) {
    currentCity = city;
}

function setDefaultMap(lng, lat, level){
    this.lng = lng;
    this.lat = lat;
    this.level = level;
}

function init() {
    map = new AMap.Map(mapDiv, {
        center: new AMap.LngLat(lng, lat),
        level: level
    });

    map.plugin(["AMap.ToolBar", "AMap.OverView", "AMap.Scale"], function () {
        //加载工具条
        map.addControl(new AMap.ToolBar());

        //加载鹰眼
        map.addControl(new AMap.OverView());

        //加载比例尺
        map.addControl(new AMap.Scale());
    });

    autoCityInfo();
}

function autoCityInfo() {
    var autoCity = remote_ip_info['city'];
    if (autoCity && autoCity != currentCity) {
        showCityTips(autoCity);
    }
}

function showCityTips(city){
    $.ajax({
        url: "/ajax/city/hospital/switchCheck",
        type: 'POST',
        data: {'city': city},
        success: function(data){
            if(data.code == 200){
                $(".autoCity").html("系统定位到您现在位于<a href='javascript:void(0);'>" + data.cityGaode.cityName + "</a>, 点击切换");
                $(".autoCity a").on("click", function(){
                    changeCity(data.cityGaode);
                });
            }
        }
    });
}

function changeCity(city){
    $.ajax({
        url: "/ajax/city/hospital/switch",
        type: "POST",
        data: {'city': city.cityName},
        success: function(data){
            if(data.code == 200){
                window.location.href = "/hospital";
            }
        }
    });
}

function loadMap() {
    var script = document.createElement("script");
    script.type = "text/javascript";
    script.src = "http://webapi.amap.com/maps?v=2.0&key=7b0661249c0b7a6c9062ae10ada2d0e3&callback=init";
    document.body.appendChild(script);
}
