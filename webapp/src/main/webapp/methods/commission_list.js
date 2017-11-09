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


    var status = $("#withdrawStatus").val();

    var startTime = $("#startTime").val().trim();

    var endTime = $("#endTime").val().trim();

    var cellphone = $("#cellphone").val().trim();

    var pageSize = $("#pageSize").val().trim();

    if (parseInt(pageSize) != pageSize && pageSize != ""){
        $("#pageSizeAlert").text("请输入整数");
        setTimeout(function () {
            $("#pageSizeAlert").text("");
        },2000);
        return;
    }

    $.ajax({
        url     :   "/c/page/console/auth/commission/list",
        type    :   "POST",
        dataType:   "JSON",
        data    :   {status:status,startTime:startTime,endTime:endTime,cellphone:cellphone,pageSize:pageSize},
        success :   function(data){

            if (data.success) {

                var details = data.bean.details;
                var pageNow = data.bean.pageNow ;
                var totalPage = data.bean.totalPage ;
                if (totalPage == '0'){
                    pageNow = 0;
                }
                $("#pageNow").text(pageNow);
                $("#totalPage").text(totalPage);

                var body = $("#body");
                body.empty();
                var newTbody = "";
                for (var i= 0;i<details.length ;i ++) {

                    newTbody +=
                        "<tr>"+
                        "    <td><input type='checkbox' value='"+details[i].id+"'></td>"+
                        "    <td>"+details[i].name+"</td>"+
                        "    <td>"+details[i].phone+"</td>"+
                        "    <td>"+details[i].applyMoney+"</td>"+
                        "    <td>"+details[i].applyTime+"</td>"+
                        "    <td>"+details[i].withdrawTime+"</td>";
                    if (details[i].withdrawStatus == '0'){
                        newTbody += "    <td>未处理</td>";
                    }else if(details[i].withdrawStatus == '1') {
                        newTbody += "    <td style='color: green'>已处理</td>";
                    }else if(details[i].withdrawStatus == '2') {
                        newTbody += "    <td style='color: red'>已作废</td>";
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


    var status = $("#withdrawStatus").val();

    var startTime = $("#startTime").val().trim();

    var endTime = $("#endTime").val().trim();

    var cellphone = $("#cellphone").val().trim();

    var pageSize = $("#pageSize").val().trim();

    if (parseInt(pageSize) != pageSize && pageSize != ""){
        $("#pageSizeAlert").text("请输入整数");
        setTimeout(function () {
            $("#pageSizeAlert").text("");
        },2000);
        return;
    }

    $.ajax({
        url     :   "/c/page/console/auth/commission/list",
        type    :   "POST",
        dataType:   "JSON",
        data    :   {status:status,startTime:startTime,endTime:endTime,cellphone:cellphone,pageSize:pageSize,pageNum:pageNum},
        success :   function(data){

            if (data.success) {

                var details = data.bean.details;
                var pageNow = data.bean.pageNow ;
                var totalPage = data.bean.totalPage ;
                if (totalPage == '0'){
                    pageNow = 0;
                }
                $("#pageNow").text(pageNow);
                $("#totalPage").text(totalPage);

                var body = $("#body");
                body.empty();
                var newTbody = "";
                for (var i= 0;i<details.length ;i ++) {

                    newTbody +=
                        "<tr>"+
                        "    <td><input type='checkbox' value='"+details[i].id+"'></td>"+
                        "    <td>"+details[i].name+"</td>"+
                        "    <td>"+details[i].phone+"</td>"+
                        "    <td>"+details[i].applyMoney+"</td>"+
                        "    <td>"+details[i].applyTime+"</td>"+
                        "    <td>"+details[i].withdrawTime+"</td>";
                    if (details[i].withdrawStatus == '0'){
                        newTbody += "    <td>未处理</td>";
                    }else if(details[i].withdrawStatus == '1') {
                        newTbody += "    <td style='color: green'>已处理</td>";
                    }else if(details[i].withdrawStatus == '2') {
                        newTbody += "    <td style='color: red'>已作废</td>";
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
 * 点击作废
 */
$(document).on("click","#invalid",function () {

    var $input = $("input[type=checkbox]:checked");
    if ($input == null ||$input.length == 0||$input.length>1 ) {
        tips.err("必须且只能选择一条纪录",2000);
        return;
    }
    var id = $input.val();

    tips.loading();

    $.ajax({
        url     :   "/c/page/console/auth/commission/invalid",
        type    :   "POST",
        dataType:   "JSON",
        data    :   {id:id},
        success :   function (data) {

            if (data.success){
                tips.suc(data.msg,2000);

                setTimeout(function () {
                    window.location.href = "/c/page/console/auth/commission/commissionWithdrawList";
                },2000);

            }else {
                tips.err(data.msg,2000);
            }
        },
        error   :   function () {

        }
    });
});

/**
 * 点击确认 提现
 */
$(document).on("click","#withdraw",function () {

    var $input = $("input[type=checkbox]:checked");
    if ($input == null ||$input.length == 0||$input.length>1 ) {
        tips.err("必须且只能选择一条纪录",2000);
        return;
    }
    var id = $input.val();

    tips.loading();

    $.ajax({
        url     :   "/c/page/console/auth/commission/withdraw",
        type    :   "POST",
        dataType:   "JSON",
        data    :   {id:id},
        success :   function (data) {

            if (data.success){
                tips.suc(data.msg,2000);

                setTimeout(function () {
                    window.location.href = "/c/page/console/auth/commission/commissionWithdrawList";
                },2000);

            }else {
                tips.err(data.msg,2000);
            }
        },
        error   :   function () {

        }
    });
});

