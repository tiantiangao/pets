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
    map = new AMap.Map("mapDiv", {
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

    research(map, 1);
}

function research(map, page){
    var bounds = map.getBounds();
    $.ajax({
        url: "/ajax/city/hospital/search",
        type: "POST",
        data: {
            "southWestLat": bounds.getSouthWest().getLat(),
            "southWestLng": bounds.getSouthWest().getLng(),
            "northEastLat": bounds.getNorthEast().getLat(),
            "northEastLng": bounds.getNorthEast().getLng(),
            "page": page
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
        if(this.avgPrice>0){
            price.text("￥"+this.avgPrice);
        }else{
            price.text("-");
        }
        priceContainer.append(price);


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

        var review = $("<div></div>");
        shop.append(review);
        review.addClass("review");

        var reviewLink = $("<a></a>");
        reviewLink.attr({"target": "_blank", "href":this.dpShopUrl});
        if(this.reviewCount>0){
            reviewLink.text(this.reviewCount+"条评论");
        }else{
            reviewLink.text("暂无评论");
        }
        review.append(reviewLink);

        // 添加地图标记
        var marker = addMarker(this.lng, this.lat, index+1);

        shop.bind("mouseenter", function(){
            marker.setContent(getIcon(index+1, true));
        });
        shop.bind("mouseleave", function(){
            marker.setContent(getIcon(index+1, false));
        });

        // 添加标记对应的弹出信息框
        var info = [];
        info.push("<div class='parentContainer'>");
        info.push("<div class='shopContainer text-left infoContainer'>");

        info.push("<img class='close' src='http://webapi.amap.com/images/close2.gif'>");

        info.push("<h6>");
        info.push("<i class='order'>" + (index+1) + "</i>")
        info.push("<a target='_blank' class='name' href='" + this.dpShopUrl + "'>"+ this.shopName +"</a>");
        if(this.hasDeal){
            info.push("<a class='igroup' target='_blank' href='"+ this.dealList[0].dealUrl + "'></a>");
        }
        info.push("</h6>");

        info.push("<div class='remark'>");
        info.push("<span class='star'><img src='" + this.ratingUrl + "'></span>");
        info.push("<em class='sep'>|</em>");
        info.push("<span class='price'><span>人均</span><em class='avg'>");
        if(this.avgPrice>0){
            info.push("￥"+this.avgPrice);
        }else{
            info.push("-");
        }
        info.push("</em></span>");
        info.push("</div>");

        info.push("<div class='address'>");
        info.push("<span class='address-label'>地址: </span><span class='address-value'>" + this.address + "</span><div class='clear'></div>");
        info.push("</div>");

        info.push("<div class='address'>");
        info.push("<span class='address-label'>电话: </span><span class='address-value'>" + this.telephone + "</span><div class='clear'></div>");
        info.push("</div>");

        info.push("</div>");

        info.push("<div class='info-bottom'><img src='http://webapi.amap.com/images/sharp.png'></div>");
        info.push("</div>");

        var lng = this.lng;
        var lat = this.lat;
        var infoWindow = new AMap.InfoWindow({
            isCustom: true,
            autoMove: false,
            content: info.join(""),
            offset: new AMap.Pixel(65,-65)
        });

        shop.bind("click", function(){
            $("#shopList .selected").removeClass("selected");
            shop.addClass("selected");
            AMap.event.addListener(infoWindow, "open", function(){
                $(".infoContainer .close").on("click", function(){
                    infoWindow.close();
                });
            });
            infoWindow.open(map, new AMap.LngLat(lng, lat));
        });

        AMap.event.addListener(marker, "click", function(){
            $("#shopList .selected").removeClass("selected");
            shop.addClass("selected");
            AMap.event.addListener(infoWindow, "open", function(){
                $(".infoContainer .close").on("click", function(){
                    infoWindow.close();
                });
            });
            infoWindow.open(map, new AMap.LngLat(lng, lat));
        });
    });

    renderPage(data);
    $("#shopList").animate({ scrollTop: 0 }, "slow");
}

function renderPage(data){
    if(data.pageCount<=1){
        return;
    }

    if(data.page==0){
        data.page=1;
    }

    var page = $("<div class='list_page'></div>");
    var pageDetail = $("<div class='page_detail'></div>");
    pageDetail.text("第"+data.page+"页/共"+data.pageCount+"页");
    page.append(pageDetail);

    var control = $("<div class='control'></div>");
    page.append(control);

    if(data.page>1){
        var prePage = $("<a></a>");
        prePage.attr({"class":"page_btn", "href":"javascript:void();"});
        prePage.text("上一页");
        prePage.bind("click", function(){
            research(map, data.page-1);
        });
        control.append(prePage);
    }

    if(data.pageCount>data.page){
        var nextPage= $("<a></a>");
        nextPage.attr({"class":"page_btn", "href":"javascript:void();"});
        nextPage.text("下一页");
        nextPage.bind("click", function(){
            research(map, data.page+1);
        });
        control.append(nextPage);
    }

    var clear = $("<div class='clear'></div>");
    page.append(clear);

    $("#shopList").append(page);
}

//实例化点标记
function addMarker(lng, lat, index){
    var marker=new AMap.Marker({
        icon:"http://webapi.amap.com/images/marker_sprite.png",
        content: getIcon(index, false),
        position:new AMap.LngLat(lng, lat)
    });
    marker.setMap(map);  //在地图上添加点
    AMap.event.addListener(marker, "mouseover", function(e){
        marker.setContent(getIcon(index, true));
    });
    AMap.event.addListener(marker, "mouseout", function(e){
        marker.setContent(getIcon(index, false));
    });
    return marker;
}

function getIcon(index, hover){
    var hoverImg = "";
    if(hover){
        hoverImg = "-hover";
    }
    return "<div style='background-image: url(http://i2.dpfile.com/s/img/map/fancy/"+index+hoverImg+".png); width: 24px; height: 34px; position: absolute; left: 28px; top: -7px; background-repeat: no-repeat no-repeat; cursor: pointer;'></div>";
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
    script.src = "http://webapi.amap.com/maps?v=1.2&key=7b0661249c0b7a6c9062ae10ada2d0e3&callback=init";
    document.body.appendChild(script);
}
