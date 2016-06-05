
function getUrlArg(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
    var r = window.location.search.substr(1).match(reg);  //匹配目标参数
    if (r != null) return decodeURIComponent(r[2]); return null; //返回参数值
};


$(".foo img").addClass("carousel-inner img-responsive img-rounded");

$("#send_order_btn").click(function(){
    $(this).addClass("hidden");
    commitOrder();
    $("#pay_success_info").removeClass("hidden");
});

var base = "/order-page/commit?"
function commitOrder() {
    $.ajax({
        url: base,
        async:true,
        success:function(data) {
            console.log("ajax-return:" + data);
        }
    });
}

$(document).ready(function() {
    $("#pay_success_info").html("支付成功！请在影院终端机输入验证码取票。<a href=\"/\">点击回到首页</a>");
    base = base + "movieName=" + encodeURIComponent(getUrlArg("movieName"))
        + "&cinemaName=" + encodeURIComponent(getUrlArg("cinemaName"))
        + "&time=" + encodeURIComponent(getUrlArg("time"))
        + "&detailTime=" + encodeURIComponent(getUrlArg("detailTime"))
        + "&seat=" + encodeURIComponent(getUrlArg("seat"));
    console.log("base " + base);
});