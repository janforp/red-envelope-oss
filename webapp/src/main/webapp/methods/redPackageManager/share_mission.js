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
    var merchantName = $("#merchantName").val().trim();
    var missionTitle = $("#missionTitle").val().trim();
    var isEnd = $("#isEnd").val();
    if (parseInt(pageSize) != pageSize && pageSize != ""){
        $("#pageSizeAlert").text("请输入整数");
        setTimeout(function () {
            $("#pageSizeAlert").text("");
        },2000);
        return;
    }


    $.ajax({
        url     :   "/c/page/console/auth/share/listByPageSize",
        type    :   "POST",
        dataType:   "JSON",
        data    :   {pageSize:pageSize,missionTitle:missionTitle,merchantName:merchantName,isEnd:isEnd},
        success :   function(data){

            if (data.success) {

                var vos = data.bean.missions;
                $("#pageNow").text(data.bean.pageNow);
                $("#totalPage").text(data.bean.totalPage);

                var body = $("#missionBody");
                body.empty();
                var newTbody = "";
                for (var i= 0;i<vos.length ;i ++) {

                    newTbody +=
                        "<tr>"+
                            "<td><input type='checkbox' value='"+vos[i].missionId+"'></td>"+
                            "<td>"+vos[i].missionTitle+"</td>"+
                            "<td>"+vos[i].prizeRate+"</td>"+
                            "<td>"+vos[i].totalRedNum+"</td>"+
                            "<td>"+vos[i].leftRedNum+"</td>"+
                            "<td>"+vos[i].shareTitle+"</td>"+
                            "<td>"+vos[i].missionProvince+""+vos[i].missionCity+"</td>"+
                            "<td><img src='"+vos[i].openImg+"'></td>"+
                            "<td><img src='"+vos[i].successImg+"'></td>"+
                            "<td><img src='"+vos[i].failImg+"'></td>"+
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
    var merchantName = $("#merchantName").val().trim();
    var missionTitle = $("#missionTitle").val().trim();
    var isEnd = $("#isEnd").val();
    if (parseInt(pageSize) != pageSize && pageSize != ""){
        $("#pageSizeAlert").text("请输入整数");
        setTimeout(function () {
            $("#pageSizeAlert").text("");
        },2000);
        return;
    }

    $.ajax({
        url     :   "/c/page/console/auth/share/listByPageSize",
        type    :   "POST",
        dataType:   "JSON",
        data    :   {pageSize:pageSize,missionTitle:missionTitle,merchantName:merchantName,isEnd:isEnd,pageNum:pageNum},
        success :   function(data){

            if (data.success) {

                var vos = data.bean.missions;
                $("#pageNow").text(data.bean.pageNow);
                $("#totalPage").text(data.bean.totalPage);

                var body = $("#missionBody");
                body.empty();
                var newTbody = "";
                for (var i= 0;i<vos.length ;i ++) {

                    newTbody +=
                        "<tr>"+
                        "<td><input type='checkbox' value='"+vos[i].missionId+"'></td>"+
                        "<td>"+vos[i].missionTitle+"</td>"+
                        "<td>"+vos[i].prizeRate+"</td>"+
                        "<td>"+vos[i].totalRedNum+"</td>"+
                        "<td>"+vos[i].leftRedNum+"</td>"+
                        "<td>"+vos[i].shareTitle+"</td>"+
                        "<td>"+vos[i].missionProvince+""+vos[i].missionCity+"</td>"+
                        "<td><img src='"+vos[i].openImg+"'></td>"+
                        "<td><img src='"+vos[i].successImg+"'></td>"+
                        "<td><img src='"+vos[i].failImg+"'></td>"+
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
function deleteMission() {

    tips.err("暂时不开放此功能",2000);
    return;

    var $input = $("input[type=checkbox]:checked");
    if ($input == null ||$input.length == 0 ) {
        tips.err("至少选择一条",2000);
        return;
    }
    var missionIds = $input.map(function (index,elem) {
        return $(elem).val();
    }).get().join('&');

    $.ajax({
        url     :   "/c/page/console/auth/share/deleteMission",
        type    :   "POST",
        dataType:   "JSON",
        data    :   {missionIds:missionIds},

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
function updateMission() {

    var $input = $("input[type=checkbox]:checked");
    if ($input == null ||$input.length == 0||$input.length>1 ) {
        tips.err("必须且只能选择一条纪录",2000);
        return;
    }
    var missionId = $input.val();
    window.location.href = ("/c/page/console/auth/share/updateMissionPage?missionId="+missionId);
}
/**
 * 添加
 */
function addMission() {

    window.location.href = ("/c/page/console/auth/share/addMissionPage");
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
    var missionId = $input.val();
    window.location.href = ("/c/page/console/auth/share/missionDetailPage?missionId="+missionId);
}

/**
 * 添加红包:弹框
 */
function addShareMissionRed() {

    var $input = $("input[type=checkbox]:checked");
    if ($input == null ||$input.length == 0||$input.length>1 ) {
        tips.err("必须且只能选择一条纪录",2000);
        return;
    }
    $("#alertFrame").removeClass("hide");
    //其他的复选框均不能选择
    $("input[type=checkbox]").prop("disabled",true);


}

/**
 * 点击弹框上的添加红包按钮
 */
function doAddRed() {

    var $input = $("input[type=checkbox]:checked");
    if ($input == null ||$input.length == 0||$input.length>1 ) {
        tips.err("必须且只能选择一条纪录",2000);
        return;
    }
    var missionId = $input.val();

    var minMoney = $("#minMoney").val().trim();
    if (minMoney == "" || minMoney == null || minMoney == undefined){
        minMoney = 100;
    }
    var maxMoney = $("#maxMoney").val().trim();
    if (maxMoney == "" || maxMoney == null || maxMoney == undefined){
        maxMoney = 100;
    }
    var totalRedNum = $("#totalRedNum").val().trim();
    if (totalRedNum == "" || totalRedNum == null || totalRedNum == undefined){
        tips.err("请输入红包总数",4000);
        return;
    }

    $.ajax({
        url     :   "/c/page/console/auth/share/addRed",
        type    :   "POST",
        dataType:   "JSON",
        data    :   {missionId:missionId,minMoney:minMoney,maxMoney:maxMoney,totalRedNum:totalRedNum},
        success:function (data) {
            if(data.success){
                tips.suc(data.msg,3000);

                $("#close").click();


                setTimeout(
                    function () {

                        window.location.reload();

                    },2000
                );

            }else{
                tips.err("添加红包失败",3000);
            }
        },
        error:function () {
            
        }
    });
}

/**
 * 关闭添加红包弹出框
 */
$(document).on("click","#close",function () {
    $("#alertFrame").addClass("hide");
    //恢复复选框选择功能
    $("input[type=checkbox]").prop("disabled",false);
});

/**
 * 关闭任务
 */
function closeShareMission() {

    var $input = $("input[type=checkbox]:checked");
    if ($input == null ||$input.length == 0||$input.length>1 ) {
        tips.err("必须且只能选择一条纪录",2000);
        return;
    }
    var missionId = $input.val();

    $.ajax({
        url     :   "/c/page/console/auth/share/endMission",
        type    :   "POST",
        dataType:   "JSON",
        data    :   {missionId:missionId},
        success:function (data) {
            if (data.success){
                tips.suc("任务关闭成功",2000);

                setTimeout(function () {
                    window.location.href = ("/c/page/console/auth/share/shareList");
                },2000);

            }else {
                tips.err(data.msg,2000);
            }
        },
        error:function () {
            
        }
    });
};

/**
 *  打开任务
 */
function openShareMission() {

    var $input = $("input[type=checkbox]:checked");
    if ($input == null ||$input.length == 0||$input.length>1 ) {
        tips.err("必须且只能选择一条纪录",2000);
        return;
    }
    var missionId = $input.val();

    $.ajax({
        url     :   "/c/page/console/auth/share/openMission",
        type    :   "POST",
        dataType:   "JSON",
        data    :   {missionId:missionId},
        success:function (data) {
            if (data.success){
                tips.suc("任务打开成功",2000);
                setTimeout(function () {
                    window.location.href = ("/c/page/console/auth/share/shareList");
                },2000);

            }else {
                tips.err(data.msg,2000);
            }
        },
        error:function () {

        }
    });
}