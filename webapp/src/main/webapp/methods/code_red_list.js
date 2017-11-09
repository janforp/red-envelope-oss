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

    var customerName = $("#customerName").val().trim();

    var codeStatus = $("#codeStatus").val();

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

    var body = $("#redBody");
    $("#pageNow").text(0);
    $("#totalPage").text(0);

    $.ajax({
        url     :   "/c/page/console/auth/red/listCodeRed",
        type    :   "POST",
        dataType:   "JSON",
        data    :   {customerName:customerName,codeStatus:codeStatus,pageSize:pageSize,pageNum:pageNum},
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

                body.empty();
                var newTbody = "";
                for (var i= 0;i<reds.length ;i ++) {
                    newTbody +=
                        "<tr>"+
                        "    <td><input type='checkbox' value='"+reds[i].codeId+"'></td>"+
                        "    <td>"+reds[i].codeId+"</td>"+
                        "    <td>"+reds[i].redCode+"</td>"+
                        "    <td>"+reds[i].customerName+"</td>"+
                        "    <td><img src='"+reds[i].customerImg+"'></td>"+
                        "    <td><img src='"+reds[i].customerBanner+"'></td>"+
                        "    <td>"+reds[i].customerBannerUrl+"</td>"+
                        "    <td>"+reds[i].redNumTotal+"</td>"+
                        "    <td>"+reds[i].redNumLeft+"</td>"+
                        "    <td>"+reds[i].redNumDayTotal+"</td>"+
                        "    <td>"+reds[i].redNumDayLeft+"</td>";

                    if (reds[i].codeStatus == '0'){
                        newTbody +=  "    <td>IOS开启</td>";
                    }else if(reds[i].codeStatus == '1'){
                        newTbody +=  "    <td>Andriod开启</td>";
                    }else if(reds[i].codeStatus == '2'){
                        newTbody +=  "    <td>全部开启</td>";
                    }else if(reds[i].codeStatus == '3'){
                        newTbody +=  "    <td>全部关闭</td>";
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


    var customerName = $("#customerName").val().trim();

    var codeStatus = $("#codeStatus").val();

    var pageSize = $("#pageSize").val().trim();

    if (parseInt(pageSize) != pageSize && pageSize != ""){
        $("#pageSizeAlert").text("请输入整数");
        setTimeout(function () {
            $("#pageSizeAlert").text("");
        },2000);
        return;
    }

    $.ajax({
        url     :   "/c/page/console/auth/red/listCodeRed",
        type    :   "POST",
        dataType:   "JSON",
        data    :   {customerName:customerName,codeStatus:codeStatus,pageSize:pageSize,pageNum:pageNum},
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
                        "    <td><input type='checkbox' value='"+reds[i].codeId+"'></td>"+
                        "    <td>"+reds[i].codeId+"</td>"+
                        "    <td>"+reds[i].redCode+"</td>"+
                        "    <td>"+reds[i].customerName+"</td>"+
                        "    <td><img src='"+reds[i].customerImg+"'></td>"+
                        "    <td><img src='"+reds[i].customerBanner+"'></td>"+
                        "    <td>"+reds[i].customerBannerUrl+"</td>"+
                        "    <td>"+reds[i].redNumTotal+"</td>"+
                        "    <td>"+reds[i].redNumLeft+"</td>"+
                        "    <td>"+reds[i].redNumDayTotal+"</td>"+
                        "    <td>"+reds[i].redNumDayLeft+"</td>";

                    if (reds[i].codeStatus == '0'){
                        newTbody +=  "    <td>IOS开启</td>";
                    }else if(reds[i].codeStatus == '1'){
                        newTbody +=  "    <td>Andriod开启</td>";
                    }else if(reds[i].codeStatus == '2'){
                        newTbody +=  "    <td>全部开启</td>";
                    }else if(reds[i].codeStatus == '3'){
                        newTbody +=  "    <td>全部关闭</td>";
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
function deleteCodeReds() {

    // // tips.err("暂时不开放此功能",2000);
    // // return;
    //
    // var $input = $("input[type=checkbox]:checked");
    // if ($input == null ||$input.length == 0 ) {
    //     tips.err("至少选择一条",2000);
    //     return;
    // }
    // var redIds = $input.map(function (index,elem) {
    //     return $(elem).val();
    // }).get().join('&');
    // $.ajax({
    //     url     :   "/c/page/console/auth/red/deleteSortRed",
    //     type    :   "POST",
    //     dataType:   "JSON",
    //     data    :   {redIds:redIds},
    //
    //     success :   function(data){
    //
    //         if (data.success) {
    //             $input.parent().parent().remove();
    //             tips.suc(data.msg,2000);
    //         }else{
    //             tips.err(data.msg,2000);
    //         }
    //     },
    //     error   :   function () {
    //
    //     }
    // });
}
/**
 * 跳转到修改页面
 */
function updateCodeRed() {

    var $input = $("input[type=checkbox]:checked");
    if ($input == null ||$input.length == 0||$input.length>1 ) {
        tips.err("必须且只能选择一条纪录",2000);
        return;
    }
    var codeId = $input.val();
    window.location.href=("/c/page/console/auth/red/updateCodeRedPage?codeId="+codeId);
}
/**
 * 添加
 */
function addCodeRed() {

    window.location.href = ("/c/page/console/auth/red/addCodeRedPage");
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
    var codeId = $input.val();
    window.location.href = ("/c/page/console/auth/red/codeRedDetailPage?codeId="+codeId);
}



/**
 * 添加红包
 */
function addTotalRedNum() {

    var $input = $("input[type=checkbox]:checked");
    if ($input == null ||$input.length == 0||$input.length>1 ) {
        tips.err("必须且只能选择一条纪录",2000);
        return;
    }
    var codeId = $input.val();
    window.location.href = ("/c/page/console/auth/red/addCodeRedNumPage?codeId="+codeId);
}

/**
 * 解锁红包
 */
function unlockRed() {

    var $input = $("input[type=checkbox]:checked");
    if ($input == null ||$input.length == 0||$input.length>1 ) {
        tips.err("必须且只能选择一条纪录",2000);
        return;
    }
    var codeId = $input.val();
    window.location.href = ("/c/page/console/auth/red/unlockCodeRedNumPage?codeId="+codeId);
}

/**
 * 查看效果
 */
function showCodeInApp() {

    var $input = $("input[type=checkbox]:checked");
    if ($input == null ||$input.length == 0||$input.length>1 ) {
        tips.err("必须且只能选择一条纪录",2000);
        return;
    }
    var codeId = $input.val();
    window.open(" http://hb.lswuyou.cn/c/p/codeRed/codeDetail/"+codeId);
}