//var path = "/static";
var path = ".";
// add id certainBtn

function getUrlArg(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
    var r = window.location.search.substr(1).match(reg);  //匹配目标参数
    if (r != null) return decodeURIComponent(r[2]); return null; //返回参数值
};

$(".foo img").addClass("carousel-inner img-responsive img-rounded");
$(".empty_seat").hover(function(){
    $(this).children("img").attr("src", path+"/img/green_seat.gif");
}, function(){
    if($(this).attr("state") != "selected") {
        $(this).children("img").attr("src", path+"/img/empty_seat.gif");
    }
});

var base = "/order-page?";
var seatArr = new Array();
var seatStr = "";
var path_str = base + seatStr;
$(".empty_seat").click(function(){
    if ($(this).attr("state") == "selected") {
        $(this).attr("state", "empty");
        $(this).children("img").attr("src", path+"/img/empty_seat.gif");
        var seat = $(this).children("span").html();
        var index = seatArr.indexOf(seat);
        if(index != -1) {
            seatArr.splice(index, 1);
        }
    } else {
        $(this).attr("state", "selected");
        $(this).children("img").attr("src", path+"/img/green_seat.gif");
        var seat = $(this).children("span").html();
        seatArr.push(seat);
    }
    make_path_str();
    console.log("path " + path_str);
});
function make_path_str() {
    seatStr = "";
    for(var i = 0 ; i < seatArr.length - 1; ++i) {
        seatStr += seatArr[i] + "-";
    }
    if(seatArr.length - 1 >= 0)
        seatStr += seatArr[seatArr.length - 1];
    path_str = base + seatStr;
    $("#certainBtn").attr("href", path_str);
}

$("#certainBtn").click(function() {
    if($("#certainBtn").attr("href") == "javascript:void(0)") {
        alert("You! should select a seat first.");
    }
});

$(document).ready(function() {
    var detailTime = $("#detailTime").html();
    base = base + "movieName=" + encodeURIComponent(getUrlArg("movieName"))
        + "&cinemaName=" + encodeURIComponent(getUrlArg("cinemaName"))
        + "&time=" + encodeURIComponent(getUrlArg("time"))
        + "&detailTime=" + encodeURIComponent(detailTime)
        + "&seat=";
    console.log("base " + base);
    $("#certainBtn").attr("href", "javascript:void(0)");

    
});

// $("selected_seat").click(function(){
//     $(this).removeClass("selected_seat");
//     $(this).addClass("empty_seat");
//     $(this).children("img").attr("src", path+"/img/empty_seat.gif");
// }); 