jQuery.fn.loadpic = function(options){
    options = $.extend({
        src: "",
        mw: 0,
        mh: 0,
        mt: 0,
        ml: 0
    }, options);
    var _self = this;
    var container = $("<div></div>");
    container.width(options.mw);
    container.height(options.mh);
    container.css("text-align", "center");
    _self.wrap(container);
    var loading = $("<img width=\"32\" height=\"32\" alt=\"加载中...\" title=\"图片加载中...\" src=\"http://hachipets1.qiniudn.com/s/img/loading.gif\" />");
    loading.width(32);
    loading.height(32);
    loading.css("margin-top", (options.mh-32)/2+"px");
    $(_self).after(loading);  //添加loading图片
    _self.hide();
    _self.attr("src", options.src);
    var img = new Image();
    $(img).load(function(){
        var wp = options.mw/_self.width();
        var hp = options.mh/_self.height();
        var originWidth = _self.width();
        var originHeight = _self.height();
        if(wp < hp){
            _self.width(options.mw);
            _self.height(originHeight*wp);
            _self.css('margin', ((options.mh - _self.height()) / 2 + parseInt(options.mt)) + 'px ' + (parseInt(options.ml) + parseInt(options.mw - _self.width()) * 0.5) + 'px');
        }else{
            _self.width(originWidth*hp);
            _self.height(options.mh);
            _self.css('margin', options.mt + 'px ' + (parseInt(options.ml) + parseInt(options.mw - _self.width()) * 0.5) + 'px');
        }
        loading.remove();
        _self.show();
    }).attr("src", options.src);
    return _self;
}