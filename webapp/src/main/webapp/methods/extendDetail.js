//点击 '今天' 按钮
function seeToday(customerName) {

    $("#tbody").attr("today","1");

    $.ajax({
        url     :       "/c/page/console/auth/client/today",
        type    :       "POST",
        dataType:       "JSON",
        data    :       {customerName:customerName},
        success :       function (data) {
            if (data.success){
                var list = data.bean.user;
                var totalPage = data.bean.totalPage;

                $("#tbody").empty();
                var newBody = "";
                for (var i = 0;i< list.length ; i++) {

                    newBody +=  "<tr>"+
                        "<td>"+(i+1)+"</td>"+
                        "<td>"+list[i].userNickname+"</td>"+
                        "<td><img src='"+list[i].userImg+"' /></td>"+
                        "<td>"+list[i].showTime+"</td>"+
                        "</tr>";

                }
                $("#tbody").append(newBody);
                $("#pageNow").text(1);
                $("#totalPage").text(totalPage);
            }else {
                tips.err("出错了",2000);
            }
        },
        error   :       function () {

        }
    });
}

//点击 '下一页' 按钮
$(document).on("click","#pageAfter",function () {

    var today = $("#tbody").attr("today");

    var pageNow = $("#pageNow").text();
    var totalPage = $("#totalPage").text();
    var now = parseInt(pageNow);
    if (pageNow == totalPage){
        tips.err("当前是最后一页");
        return;
    }
    if (today == "1") {
        pageToday(now+1);
        return;

    }
    page(now+1);
});

//点击 '上一页' 按钮
$(document).on("click","#pageBefore",function () {

    var today = $("#tbody").attr("today");
    var pageNow = $("#pageNow").text();
    var now = parseInt(pageNow);
    if (pageNow == 1){
        tips.err("当前是第一页");
        return;
    }
    if (today == "1") {
        pageToday(now-1);
        return;
    }
    page(now-1);
});



//今天列表 翻页
function pageToday(pageNum) {

    var pageNum = pageNum;

    $.ajax({
        url     :       "/c/page/console/auth/client/today",
        type    :       "POST",
        dataType:       "JSON",
        data    :       {customerName:customerName,pageNum:pageNum},
        success :       function (data) {

            if (data.success) {

                var list = data.bean.user;

                $("#tbody").empty();
                var newBody = "";
                for (var i = 0;i< list.length ; i++) {

                    newBody +=  "<tr>"+
                        "<td>"+(i+1)+"</td>"+
                        "<td>"+list[i].userNickname+"</td>"+
                        "<td><img src='"+list[i].userImg+"' /></td>"+
                        "<td>"+list[i].showTime+"</td>"+
                        "</tr>";

                }
                $("#tbody").append(newBody);
                var pageNow = $("#pageNow").text(pageNum);
            }else{
                tips.err("",2000);
            }
        },
        error   :       function () {

        }
    });
}

//全部列表 翻页
function page(pageNum) {

    var pageNum = pageNum;

    $.ajax({
        url     :       "/c/page/console/auth/client/next",
        type    :       "POST",
        dataType:       "JSON",
        data    :       {customerName:customerName,pageNum:pageNum},
        success :       function (data) {

            if (data.success) {

                var list = data.bean.user;

                $("#tbody").empty();
                var newBody = "";
                for (var i = 0;i< list.length ; i++) {

                    newBody +=  "<tr>"+
                        "<td>"+(i+1)+"</td>"+
                        "<td>"+list[i].userNickname+"</td>"+
                        "<td><img src='"+list[i].userImg+"' /></td>"+
                        "<td>"+list[i].showTime+"</td>"+
                        "</tr>";

                }
                $("#tbody").append(newBody);
                var pageNow = $("#pageNow").text(pageNum);
            }else{
                tips.err("出错了",2000);
            }
        },
        error   :       function () {

        }
    });
}
