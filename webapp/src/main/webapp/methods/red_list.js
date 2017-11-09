/**
 * 页面加载自动查询一次
 */
$(document).ready(function(){
    $("#listByPageSize").click();
});

/**
 * 根据用户输入,觉得每页显示的行数
 */
$(document).on("click","#listByPageSize",function(){

    var redName = $("#redName").val().trim();
    var merchantName = $("#merchantName").val().trim();
    var redSort = $("#redSort").val();
    var redStatus = $("#redStatus").val();
    var showOrNot = $("#showOrNot").val();
    var startTime = $("#startTime").val().trim();
    var endTime = $("#endTime").val().trim();
    var pageSize = $("#pageSize").val().trim();
    var pageNum = $("#pageNow").text();
    if (pageNum == 0) {
        pageNum = 1
    }

    if (parseInt(pageSize) != pageSize && pageSize != ""){
        $("#pageSizeAlert").text("请输入整数");
        setTimeout(function () {
            $("#pageSizeAlert").text("");
        },2000);
        return;
    }

    $.ajax({
        url     :   "/c/page/console/auth/red/list",
        type    :   "POST",
        dataType:   "JSON",
        data    :   {redName:redName,merchantName:merchantName,redSort:redSort,redStatus:redStatus,showOrNot:showOrNot,startTime:startTime,endTime:endTime,pageSize:pageSize,pageNum:pageNum},
        success :   function(data){

            if (data.success) {

                var reds = data.bean.reds;
                var pageNow = data.bean.pageNow ;
                var totalPage = data.bean.totalPage ;
                if (totalPage == '0'){
                    pageNow = 0;
                }
                $("#pageNow").text(pageNow);
                $("#totalPage").text(totalPage);

                var body = $("#redBody");
                body.empty();
                var newTbody = "";
                for (var i= 0;i<reds.length ;i ++) {
                    newTbody +=
                                "<tr>"+
                                "    <td><input type='checkbox' value='"+reds[i].redId+"'></td>"+
                                "    <td>"+reds[i].redId+"</td>"+
                                "    <td>"+reds[i].redName+"</td>"+
                                "    <td><img src='"+reds[i].redImg+"'></td>"+
                                "    <td>"+reds[i].merchantName+"</td>"+
                                "    <td>"+reds[i].redRewardDesc+"</td>";
                    if (reds[i].redStatus == '0'){
                        newTbody +=  "    <td>IOS开启</td>";
                    }else if(reds[i].redStatus == '1'){
                        newTbody +=  "    <td>Andriod开启</td>";
                    }else if(reds[i].redStatus == '2'){
                        newTbody +=  "    <td>全部开启</td>";
                    }else if(reds[i].redStatus == '3'){
                        newTbody +=  "    <td>全部关闭</td>";
                    }

                    if (reds[i].showOrNot == '0'){
                        newTbody += "    <td>不显示</td>";
                    }else {
                        newTbody += "    <td>显示</td>";
                    }

                    newTbody += "</tr>";

                }
                body.append(newTbody);
            }else{
                tips.err("操作失败",2000);
            }

        },
        error   :   function () {

        }
    });
});

/**
 * 翻页
 */
function turnPage(pageNum) {


    var redName = $("#redName").val().trim();
    var merchantName = $("#merchantName").val().trim();
    var redSort = $("#redSort").val();
    var redStatus = $("#redStatus").val();
    var showOrNot = $("#showOrNot").val();
    var startTime = $("#startTime").val().trim();
    var endTime = $("#endTime").val().trim();
    var pageSize = $("#pageSize").val().trim();

    if (parseInt(pageSize) != pageSize && pageSize != ""){
        $("#pageSizeAlert").text("请输入整数");
        setTimeout(function () {
            $("#pageSizeAlert").text("");
        },2000);
        return;
    }

    $.ajax({
        url     :   "/c/page/console/auth/red/list",
        type    :   "POST",
        dataType:   "JSON",
        data    :   {redName:redName,merchantName:merchantName,redSort:redSort,redStatus:redStatus,showOrNot:showOrNot,startTime:startTime,endTime:endTime,pageSize:pageSize,pageNum:pageNum},
        success :   function(data){

            if (data.success) {

                var reds = data.bean.reds;
                var pageNow = data.bean.pageNow ;
                var totalPage = data.bean.totalPage ;
                if (totalPage == '0'){
                    pageNow = 0;
                }
                $("#pageNow").text(pageNow);
                $("#totalPage").text(totalPage);

                var body = $("#redBody");
                body.empty();
                var newTbody = "";
                for (var i= 0;i<reds.length ;i ++) {
                    newTbody +=
                        "<tr>"+
                        "    <td><input type='checkbox' value='"+reds[i].redId+"'></td>"+
                        "    <td>"+reds[i].redId+"</td>"+
                        "    <td>"+reds[i].redName+"</td>"+
                        "    <td><img src='"+reds[i].redImg+"'></td>"+
                        "    <td>"+reds[i].merchantName+"</td>"+
                        "    <td>"+reds[i].redRewardDesc+"</td>";
                    if (reds[i].redStatus == '0'){
                        newTbody +=  "    <td>IOS开启</td>";
                    }else if(reds[i].redStatus == '1'){
                        newTbody +=  "    <td>Andriod开启</td>";
                    }else if(reds[i].redStatus == '2'){
                        newTbody +=  "    <td>全部开启</td>";
                    }else if(reds[i].redStatus == '3'){
                        newTbody +=  "    <td>全部关闭</td>";
                    }

                    if (reds[i].showOrNot == '0'){
                        newTbody += "    <td>不显示</td>";
                    }else {
                        newTbody += "    <td>显示</td>";
                    }

                    newTbody += "</tr>";

                }
                body.append(newTbody);
            }else{
                tips.err("操作失败",2000);
            }

        },
        error   :   function () {

        }
    });
}

/**
 * 下一页
 */
$(document).on("click","#pageAfter",function () {

    var pageNow = parseInt($("#pageNow").text());
    var totalPage = parseInt($("#totalPage").text());
    if (pageNow == totalPage) {
        tips.err("当前是最后一页",2000);
        return;
    }
    turnPage(pageNow+1);


});
/**
 * 上一页
 */
$(document).on("click","#pageBefore",function () {

    var pageNow = parseInt($("#pageNow").text());
    if (pageNow == 1) {
        tips.err("当前是第一页",2000);
        return;
    }
    turnPage(pageNow-1);
});


/**
 * 删除
 */
function deleteSortReds() {

    // tips.err("暂时不开放此功能",2000);
    // return;

    var $input = $("input[type=checkbox]:checked");
    if ($input == null ||$input.length == 0 ) {
        tips.err("至少选择一条",2000);
        return;
    }
    var redIds = $input.map(function (index,elem) {
        return $(elem).val();
    }).get().join('&');
    $.ajax({
        url     :   "/c/page/console/auth/red/deleteSortRed",
        type    :   "POST",
        dataType:   "JSON",
        data    :   {redIds:redIds},

        success :   function(data){

            if (data.success) {
                $input.parent().parent().remove();
                tips.suc(data.msg,2000);
            }else{
                tips.err(data.msg,2000);
            }
        },
        error   :   function () {

        }
    });
}
/**
 * 跳转到修改页面
 */
function updateSortRed() {

    var $input = $("input[type=checkbox]:checked");
    if ($input == null ||$input.length == 0||$input.length>1 ) {
        tips.err("必须且只能选择一条纪录",2000);
        return;
    }
    var redId = $input.val();
    window.open("/c/page/console/auth/red/updateRedPage?redId="+redId);
}
/**
 * 添加
 */
function addSortRed() {

    window.location.href = ("/c/page/console/auth/red/addSortRedPage");
}

/**
 * 详情
 */
function showDetail() {

    var $input = $("input[type=checkbox]:checked");
    if ($input == null ||$input.length == 0||$input.length>1 ) {
        tips.err("必须且只能选择一条纪录",2000);
        return;
    }
    var redId = $input.val();
    window.location.href = ("/c/page/console/auth/red/redDetailPage?redId="+redId);
}