//获取url中的参数
function getUrlArg(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
    var r = window.location.search.substr(1).match(reg);  //匹配目标参数
    if (r != null) return decodeURIComponent(r[2]); return null; //返回参数值
};

function print(obj, pad=0) {
    $.each(obj,function(key,val){
        var s = '';
        var i;
        for(i = 0; i < pad; ++i) {
            s += ' ';
        }
        if($.isPlainObject(val) || $.isArray(val)){
            console.log(s + "key: "+key)
            print(val, pad+4);
        }else{
            console.log(s + key+'='+val+", ");
        }
    });
};

var movieName = getUrlArg("movieName");
var cinemaName = "大光明电影院";
var time = "5月19日";
$(".cinema_name").click(function() {
    $(".cinemaholder").children("a").removeClass("cinema_active");
    $(".cinemaholder").children("a").addClass("cinema_noactive");
    $(this).removeClass("cinema_noactive");
    $(this).addClass("cinema_active");
    cinemaName = $(this).html();
    console.log("cinemaName " + cinemaName);
    getScreenList();
});
      //
$(".date").click(function() {
    $(".dateholder").children("a").removeClass("date_active");
    $(".dateholder").children("a").addClass("date_noactive");
    $(this).removeClass("date_noactive");
    $(this).addClass("date_active");
    time = $(this).html();
    console.log("time " + time);
    getScreenList();
});


function makeNode(time, room, price, index) {
    time = time.replace('-', '<br>')
    return "" +
        "<tr><td>"+time+"散场</td><td>"+room+"</td><td>"+price+"元</td>"+
        '<td><p><a class="btn btn-primary btn-md" href="/seat-page?'+arg+
        "&index="+index+'" role="button">选座购票</a></p></td></tr>';
}
var arg;
function getScreenList() {
	console.log("movie name "+movieName+ "  cinema name " + cinemaName + " time " + time);
    arg = "movieName=" + encodeURIComponent(movieName)
     + "&cinemaName=" + encodeURIComponent(cinemaName)
      + "&time=" + encodeURIComponent(time);
    console.log(arg);
    $.ajax({
        url:"/select-cinema/json?"+arg,
        async:true,
        success:function(data) {  
            //console.log("data: " + data.cinemaList + " " + data.timeList + " " + data.movie);
            console.log("json "+data);
        	movie_data = eval(data);
            // print(movie_data[0]);
            $("#screenList").children().remove();
            $.each(movie_data, function(index, value) {
                var node = makeNode(value.time, value.room, value.price, index);
                console.log(node);
                $("#screenList").append(node);
            });
        }
    });
}

    

 $(document).ready(function() {
	 getScreenList();
 });