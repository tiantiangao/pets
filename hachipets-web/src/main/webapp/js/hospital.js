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

    AMap.event.addListener(map, "moveend", function(){
        research(map);
    });
    AMap.event.addListener(map, "zoomend", function(){
        research(map);
    });
    AMap.event.addListener(map, "resize", function(){
        research(map);
    });

    autoCityInfo();

    research(map);
}

function research(map){
    var bounds = map.getBounds();
    $.ajax({
        url: "/ajax/city/hospital/search",
        type: "POST",
        data: {
            "southWestLat": bounds.getSouthWest().getLat(),
            "southWestLng": bounds.getSouthWest().getLng(),
            "northEastLat": bounds.getNorthEast().getLat(),
            "northEastLng": bounds.getNorthEast().getLng()
        },
        success: function(data){
            if(data.code==200){
                renderResult(data);
            }else{
                showErrTips();
            }
        }
    });
}

function shoErrTips(){
    console.log("error!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!")
}

function renderResult(data){
    console.log(data);
    map.clearMap();
    $("#shopList").empty();
    var ul = $("<ul></ul>");
    $("#shopList").append(ul);
    $.each(data.list, function(index, element){
        var shop = $("<li></li>");
        ul.append(shop);

        var title = $("<h6></h6>");
        shop.append(title);

        var order = $("<i></i>");
        order.addClass("order").text(index+1);
        title.append(order);

        var shopName = $("<a></a>");
        shopName.attr({"href":this.dpShopUrl, "target": "_blank", "class": "name"});
//        var shopNameValue = this.shopName.replace("(这是一条测试商户数据，仅用于测试开发，开发完成后请申请正式数据...)", "");
        var shopNameValue = this.shopName;
        shopName.text(shopNameValue);
        title.append(shopName);

        if(this.hasDeal){
            var deal = $("<a></a>");
            title.append(deal);
            deal.addClass("igroup");

            deal.attr({"target":"_blank", "href":this.dealList[0].dealUrl});
        }

        var remark = $("<div></div>");
        remark.addClass("remark");
        shop.append(remark);

        var star = $("<span></span>");
        star.addClass("star");
        remark.append(star);

        var img = $("<img>");
        img.attr("src", this.ratingUrl);
        star.append(img);

        if(this.avgPrice>-1){
            var split = $("<em></em>");
            remark.append(split);
            split.addClass("sep");
            split.text("|");

            var priceContainer = $("<span></span>");
            priceContainer.addClass("price");
            remark.append(priceContainer);

            var label = $("<span>人均</span>");
            priceContainer.append(label);

            var price = $("<em></em>");
            price.addClass("avg")
            price.text("￥"+this.avgPrice);
            priceContainer.append(price);

        }

        var address = $("<div></div>");
        address.addClass("address");
        shop.append(address);

        var label = $("<span></span>");
        label.addClass("address-label");
        label.text("地址: ");
        address.append(label);

        var addressValue = $("<span></span>");
        addressValue.addClass("address-value");
        addressValue.text(this.address);
        address.append(addressValue);

        address.append($("<div class='clear'></div>"));

        var phone = $("<div></div>");
        phone.addClass("address");
        shop.append(phone);

        var phone_label = $("<span></span>");
        phone_label.addClass("address-label");
        phone_label.text("电话: ");
        phone.append(phone_label);

        var phoneValue = $("<span></span>");
        phoneValue.addClass("address-value");
        phoneValue.text(this.telephone);
        phone.append(phoneValue);

        phone.append($("<div class='clear'></div>"));

        addMarker(this.lng, this.lat, index+1);
    });

}

//实例化点标记
function addMarker(lng, lat, index){
    marker=new AMap.Marker({
        icon:"http://webapi.amap.com/images/marker_sprite.png",
//        content: index+"",
        position:new AMap.LngLat(lng, lat)
    });
    marker.setMap(map);  //在地图上添加点
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
