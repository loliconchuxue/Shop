$(function () {

});

/**
 * 导航分类栏显示及颜色变换
 */
$(function () {
    $('#J_navCategory').mouseover(function () {
        $('.site-category').css('display', 'block');
    })
    $('#J_navCategory').mouseout(function () {
        $('.site-category').css('display', 'none');
    })
});

/**
 * 图片介绍动画切换效果
 */
$(function () {
    $("#goodsPicList").on('click', 'li', function () {
        $("#goodsPicList li").removeClass("current");
        $(this).addClass("current");
        var largePath = $(this).children("img").attr("src");
        $("#J_BigPic").attr({
            src: largePath
        })
    });
    $("#goodsPicList li:first").click(); //第一张图片
})

/**
 * 导航悬浮
 */
$(window).scroll(function () {
    var $this = $(this);
    var targetTop = $(this).scrollTop();
    var height = $(window).height();

    //document.title=targetTop;
    //控制导航悬浮
    if (targetTop > 800) {
        $("#goodsSubBar").css('display', 'block');
    } else {
        $("#goodsSubBar").css('display', 'none');
    }

    if (targetTop > $("#goodsDesc").offset().top - 100 && targetTop < $("#goodsParam").offset().top - 100) {
        $(".goods-sub-bar .detail-list").find("li").removeClass("current");
        $(".goods-sub-bar .detail-list").find("li").eq(0).addClass("current");
    } else if (targetTop > $("#goodsParam").offset().top - 100 && targetTop < $("#goodsComment").offset().top - 100) {
        $(".goods-sub-bar .detail-list").find("li").removeClass("current");
        $(".goods-sub-bar .detail-list").find("li").eq(1).addClass("current");
    }
});


/**
 * 导航悬浮点击事件
 */
var subNav_active = $(".current");
var subNav_scroll = function (target) {
    subNav_active.removeClass("current");
    target.parent().addClass("current");
    subNav_active = target.parent();
};
$(".goods-sub-bar .detail-list a").click(function () {
    subNav_scroll($(this));
    if ($(this).parent().attr("id") != "join") {
        var target = $(this).attr("href");
        var targetScroll = $(target).offset().top - 40;
        $("html,body").animate({
            scrollTop: targetScroll
        }, 300);
        return false;
    }
});
$(".goods-detail-nav .detail-list a").click(function () {
    subNav_scroll($(this));
    if ($(this).parent().attr("id") != "join") {
        var target = $(this).attr("href");
        var targetScroll = $(target).offset().top - 40;
        $("html,body").animate({
            scrollTop: targetScroll
        }, 300);
        return false;
    }
});
$(".goods-info-head-userfaq .detail-list a").click(function () {
    if ($(this).parent().attr("id") != "join") {
        var target = $(this).attr("href");
        var targetScroll = $(target).offset().top - 40;
        $("html,body").animate({
            scrollTop: targetScroll
        }, 300);
        return false;
    }
});

/**
 * 商品规格选择
 */
$(function () {
    $(".goods-info-head .sys_item_specpara").each(function () {
        var i = $(this);
        var p = i.find("ul>li");
        p.click(function () {
            $(this).addClass("current").siblings("li").removeClass("current");
            i.attr("data-attrval", $(this).attr("data-aid"))
            getattrprice() //输出价格
        })
    })

    var $elements = $('.sys_item_specpara');
    $elements.each(function () {
        var $this = $(this);
        $this.children('ul').children('li:first').click(); //第一种规格
    })

    //获取对应属性的价格
    function getattrprice() {
        var defaultstats = true;
        var _val = '';
        var _resp = {
            score: ".sys_item_score",
            price: ".sys_item_price",
        } //输出对应的class
        $(".goods-info-head .sys_item_specpara").each(function () {
            var i = $(this);
            var v = i.attr("data-attrval");
            if (!v) {
                defaultstats = false;
                $('#goodsDetailBtnBox').css('display', 'block');
                $('#goodsDetailBtnBoxForInform').css('display', 'none');
            } else {
                _val += _val != "" ? "," : "";
                _val += v;
            }
        })
        if (!!defaultstats) {
            if (typeof (sys_item[_val]) == "undefined") {
                $('#goodsDetailBtnBox').css('display', 'none');
                $('#goodsDetailBtnBoxForInform').css('display', 'block');
            } else {
                //_score = sys_item[_val]['score'];
                _price = sys_item[_val]['price'];
                _productSpecNumber = sys_item[_val]['productSpecNumber'];
                $('#goodsDetailBtnBox').css('display', 'block');
                $('#goodsDetailBtnBoxForInform').css('display', 'none');
            }

        } else {
            // 默认商品规格
            //_score = default_score;
            _price = default_price;
            _productSpecNumber = sys_item['productSpecNumber'];
        }
        //输出价格
        //$(_resp.score).text(_score); //其中的math.round为截取小数点位数
        $(_resp.price).text(_price);
        $("#goodsDetailAddCartBtn").attr("data-product-spec-number", _productSpecNumber);
        $("#goodsSubBarAddCartBtn").attr("data-product-spec-number", _productSpecNumber);
    }
})

/**
 * 默认商品规格
 */
$(function () {
    if ($('#J_goodsInfoBlock').children('.sys_item_specpara').length <= 0) {
        // 默认商品规格
        //_score = sys_item['default']['score'];
        _price = sys_item['default']['price'];
        _productSpecNumber = sys_item['default']['productSpecNumber'];

        // 输出对应的class
        var _resp = {
            //score: ".sys_item_score",
            price: ".sys_item_price",
        }

        $(_resp.score).text(_score); // 其中的math.round为截取小数点位数
        $(_resp.price).text(_price);
        $("#goodsDetailAddCartBtn").attr("data-product-spec-number", _productSpecNumber);
        $("#goodsSubBarAddCartBtn").attr("data-product-spec-number", _productSpecNumber);
    }
})


/**
 * 加入购物车
 */
function add_cart(obj) {
    var productSpecNumber = $(obj).attr("data-product-spec-number");
    if(productSpecNumber == "" || productSpecNumber == undefined || productSpecNumber == null) {
        productSpecNumber = sys_item['default']['productSpecNumber'];
    }
    $.ajax({
        type: 'post',
        dataType: 'json',
        data: {
            'productSpecNumber': productSpecNumber
        },
        url: baselocation + '/cart',
        success: function (result) {
            console.log(result.message);
            if (result.code == 1) {
                window.location.href = baselocation + '/cart/' + result.message;
            } else if (result.code == 401) {
                window.location.href = baselocation + '/user/login';
            } else {
                layer.alert(result.message, {
                    icon: 2
                });
            }
        }
    })
}