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

    var pageSize = $("#pageSize").val();

    var isVerify = 0;
    var status = $("#status").val();
    var missionTitle = $("#missionTitle").val().trim();
    var userPhone = $("#userPhone").val().trim();
    var startDate = $("#startDate").val().trim();
    var endDate = $("#endDate").val().trim();

    if (parseInt(pageSize) != pageSize && pageSize != ""){
        $("#pageSizeAlert").text("请输入整数");
        setTimeout(function () {
            $("#pageSizeAlert").text("");
        },2000);
        return;
    }

    $.ajax({
        url     :   "/c/page/console/auth/recommendTask/listByPageSize",
        type    :   "POST",
        dataType:   "JSON",
        data    :   {pageSize:pageSize,missionId:missionId,isVerify:isVerify,status:status,missionTitle:missionTitle,userPhone:userPhone,startDate:startDate,endDate:endDate},
        success :   function(data){

            if (data.success) {

                var vos = data.bean.tasks;
                $("#pageNow").text(data.bean.pageNow);
                $("#totalPage").text(data.bean.totalPage);

                var body = $("#missionBody");
                body.empty();
                var newTbody = "";
                for (var i= 0;i<vos.length ;i ++) {

                    var status;
                    var taskStatus = vos[i].status;
                    if (taskStatus == '0'){
                        status = "进行中";
                    }else if(taskStatus == '1'){
                        status = "审核中";
                    }else if(taskStatus == '2'){
                        status = "审核通过";
                    }else if(taskStatus == '3'){
                        status = "未通过";
                    }else if(taskStatus == '4'){
                        status = "已过期";
                    }else {
                        status = "不用审核";
                    }

                    newTbody +=
                        "<tr>"+
                        "<td><input type='checkbox' name='task' value='"+vos[i].taskId+"'></td>"+
                        "<td>"+(i+1)+"</td>"+
                        "<td>"+status+"</td>"+
                        "<td>"+vos[i].title+"</td>"+
                        "<td>"+vos[i].money+"</td>"+
                        "<td>"+vos[i].phone+"</td>"+
                        "<td>"+vos[i].time+"</td>"+
                        "</tr>";
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

    var pageSize = $("#pageSize").val();

    var isVerify = 0;
    var status = $("#status").val();
    var missionTitle = $("#missionTitle").val().trim();
    var userPhone = $("#userPhone").val().trim();
    var startDate = $("#startDate").val().trim();
    var endDate = $("#endDate").val().trim();

    if (parseInt(pageSize) != pageSize && pageSize != ""){
        $("#pageSizeAlert").text("请输入整数");
        setTimeout(function () {
            $("#pageSizeAlert").text("");
        },2000);
        return;
    }

    $.ajax({
        url     :   "/c/page/console/auth/recommendTask/listByPageSize",
        type    :   "POST",
        dataType:   "JSON",
        data    :   {pageSize:pageSize,missionId:missionId,isVerify:isVerify,status:status,missionTitle:missionTitle,userPhone:userPhone,pageNum:pageNum,startDate:startDate,endDate:endDate},
        success :   function(data){

            if (data.success) {

                var vos = data.bean.tasks;
                $("#pageNow").text(data.bean.pageNow);
                $("#totalPage").text(data.bean.totalPage);

                var body = $("#missionBody");
                body.empty();
                var newTbody = "";
                for (var i= 0;i<vos.length ;i ++) {

                    var status;
                    var taskStatus = vos[i].status;
                    if (taskStatus == '0'){
                        status = "进行中";
                    }else if(taskStatus == '1'){
                        status = "审核中";
                    }else if(taskStatus == '2'){
                        status = "审核通过";
                    }else if(taskStatus == '3'){
                        status = "未通过";
                    }else if(taskStatus == '4'){
                        status = "已过期";
                    }else {
                        status = "不用审核";
                    }

                    newTbody +=
                        "<tr>"+
                        "<td><input type='checkbox' name='task'  value='"+vos[i].taskId+"'></td>"+
                        "<td>"+(i+1)+"</td>"+
                        "<td>"+status+"</td>"+
                        "<td>"+vos[i].title+"</td>"+
                        "<td>"+vos[i].money+"</td>"+
                        "<td>"+vos[i].phone+"</td>"+
                        "<td>"+vos[i].time+"</td>"+
                        "</tr>";
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
    $('thead input').removeAttr('checked');
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
    $('thead input').removeAttr('checked');
    turnPage(pageNow-1);
});


/**
 * 点击去审核
 */
function gotoVerify() {

    var $input = $("input[type=checkbox]:checked");
    if ($input == null ||$input.length == 0||$input.length>1 ) {
        tips.err("必须且只能选择一条纪录",2000);
        return;
    }
    var taskId = $input.val();
    window.location.href='/c/page/console/auth/recommendTask/verifyPage?taskId='+taskId;
}

/**
 * 通过
 */
function batchVerify() {

    var $input = $("input[name=task]:checked");
    if ($input.length == 0) {
        tips.err("至少选择一条纪录",2000);
        return;
    }
    $("input[name=task]:checked").prop('disabled',true);

    $("#moneyDiv").show();

};


/**
 * 批量审核通过
 */
function sureBatchVerify() {
    var $input = $("input[name=task]:checked");
    if ($input.length == 0) {
        tips.err("至少选择一条纪录",2000);
        return;
    }

    var taskIds = $input.map(function (index,elem) {
        return $(elem).val();
    }).get().join('&');

    var money = $("#money").val();

    $.ajax({
        url     :   "/c/page/console/auth/recommendTask/batchVerify",
        type    :   "POST",
        dataType:   "JSON",
        data    :   {taskIds:taskIds, money:money},

        success :   function(data){

            if (data.success) {
                tips.suc(data.msg,2000);
                setTimeout(function () {
                    window.location.reload();
                },3000);
            }else{
                tips.err(data.msg,2000);
            }
        },
        error   :   function () {

        }
    });
};


/**
 * 未通过
 */
function batchNotVerify() {

    var $input = $("input[name=task]:checked");
    if ($input.length == 0) {
        tips.err("至少选择一条纪录",2000);
        return;
    }
    $("input[name=task]:checked").prop('disabled',true);

    $("#remarksDiv").show();

};

/**
 * 批量审核未通过
 */
function sureBatchNotVerify() {

    var $input = $("input[name=task]:checked");
    if ($input.length == 0) {
        tips.err("至少选择一条纪录",2000);
        return;
    }

    var taskIds = $input.map(function (index,elem) {
        return $(elem).val();
    }).get().join('&');

    var remarks=$("#remarks").val();

    $.ajax({
        url     :   "/c/page/console/auth/recommendTask/batchNot",
        type    :   "POST",
        dataType:   "JSON",
        data    :   {taskIds:taskIds,remarks:remarks},

        success :   function(data){

            if (data.success) {
                tips.suc(data.msg,2000);
                $("input[name=task]:checked").prop('disabled',false);
                setTimeout(function () {
                    window.location.reload();
                },3000);
            }else{
                tips.err(data.msg,2000);
            }
        },
        error   :   function () {

        }
    });
}