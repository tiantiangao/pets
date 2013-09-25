jQuery.fn.loadpic = function(options){
    options = $.extend({
        src: "",
        mw: 0,
        mh: 0,
        mt: 0,
        ml: 0
    }, options);
    var _self = this;
    _self.attr("src", options.src);
//    _self.hide();
    var img = new Image();
    $(img).load(function(){
//        _self.attr("src", options.src);
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
    }).attr("src", options.src);
    return _self;
}