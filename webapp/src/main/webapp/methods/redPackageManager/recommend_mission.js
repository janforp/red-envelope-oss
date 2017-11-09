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
    var missionTitle = $("#missionTitle").val().trim();
    var type = $("#type").val();
    var isVerify = $("#isVerify").val();
    var isLimitTime = $("#isLimitTime").val();

    if (parseInt(pageSize) != pageSize && pageSize != ""){
        $("#pageSizeAlert").text("请输入整数");
        setTimeout(function () {
            $("#pageSizeAlert").text("");
        },2000);
        return;
    }


    $.ajax({
        url     :   "/c/page/console/auth/recommend/listByPageSize",
        type    :   "POST",
        dataType:   "JSON",
        data    :   {pageSize:pageSize,missionTitle:missionTitle,type:type,isVerify:isVerify,isLimitTime:isLimitTime},
        success :   function(data){

            if (data.success) {

                var vos = data.bean.missions;
                $("#pageNow").text(data.bean.pageNow);
                $("#totalPage").text(data.bean.totalPage);

                var body = $("#missionBody");
                body.empty();
                var newTbody = "";
                for (var i= 0;i<vos.length ;i ++) {
                    var timeLimit;
                    var isLimitTime = vos[i].isLimitTime;
                    if (isLimitTime == '1'){
                        timeLimit = "不限时";
                    }else {
                        timeLimit = "限时";
                    }

                    var verify;
                    var isVerify = vos[i].isVerify;
                    if (isVerify == '1'){
                        verify = "不审核";
                    }else {
                        verify = "审核";
                    }

                    var status = vos[i].missionStatus;
                    if (status == 0){
                        status = 'ios开启';
                    }else if (status == 1){
                        status = 'andriod开启';
                    }else if(status == 2){
                        status = '全部开启';
                    }else if (status == 3){
                        status = '全部关闭';
                    }

                    newTbody +=
                        "<tr>"+
                            "<td><input type='checkbox' value='"+vos[i].missionId+"'></td>"+
                            "<td>"+vos[i].missionTitle+"</td>"+
                            "<td><img src='"+vos[i].missionIcon+"'></td>"+
                            "<td>"+vos[i].missionLabel+"</td>"+
                            "<td>"+vos[i].minMoney+"</td>"+
                            "<td>"+vos[i].maxMoney+"</td>"+
                            "<td>"+vos[i].endTime+"</td>"+
                            "<td>"+vos[i].totalNum+"</td>"+
                            "<td>"+vos[i].leftNum+"</td>"+
                            "<td>"+timeLimit+"</td>"+
                            "<td>"+verify+"</td>"+
                            "<td>"+vos[i].missionOrder+"</td>"+
                             "<td>"+status+"</td>"+
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
    var missionTitle = $("#missionTitle").val().trim();
    var type = $("#type").val();
    var isVerify = $("#isVerify").val();
    var isLimitTime = $("#isLimitTime").val();

    if (parseInt(pageSize) != pageSize && pageSize != ""){
        $("#pageSizeAlert").text("请输入整数");
        setTimeout(function () {
            $("#pageSizeAlert").text("");
        },2000);
        return;
    }

    $.ajax({
        url     :   "/c/page/console/auth/recommend/listByPageSize",
        type    :   "POST",
        dataType:   "JSON",
        data    :   {pageSize:pageSize,missionTitle:missionTitle,type:type,isVerify:isVerify,isLimitTime:isLimitTime,pageNum:pageNum},
        success :   function(data){

            if (data.success) {

                var vos = data.bean.missions;
                $("#pageNow").text(data.bean.pageNow);
                $("#totalPage").text(data.bean.totalPage);

                var body = $("#missionBody");
                body.empty();
                var newTbody = "";
                for (var i= 0;i<vos.length ;i ++) {

                    var timeLimit;
                    var isLimitTime = vos[i].isLimitTime;
                    if (isLimitTime == '1'){
                        timeLimit = "不限时";
                    }else {
                        timeLimit = "限时";
                    }

                    var verify;
                    var isVerify = vos[i].isVerify;
                    if (isVerify == '1'){
                        verify = "不审核";
                    }else {
                        verify = "审核";
                    }
                    var status = vos[i].missionStatus;
                    if (status == 0){
                        status = 'ios开启';
                    }else if (status == 1){
                        status = 'andriod开启';
                    }else if(status == 2){
                        status = '全部开启';
                    }else if (status == 3){
                        status = '全部关闭';
                    }

                    newTbody +=
                        "<tr>"+
                        "<td><input type='checkbox' value='"+vos[i].missionId+"'></td>"+
                        "<td>"+vos[i].missionTitle+"</td>"+
                        "<td><img src='"+vos[i].missionIcon+"'></td>"+
                        "<td>"+vos[i].missionLabel+"</td>"+
                        "<td>"+vos[i].minMoney+"</td>"+
                        "<td>"+vos[i].maxMoney+"</td>"+
                        "<td>"+vos[i].endTime+"</td>"+
                        "<td>"+vos[i].totalNum+"</td>"+
                        "<td>"+vos[i].leftNum+"</td>"+
                        "<td>"+timeLimit+"</td>"+
                        "<td>"+verify+"</td>"+
                        "<td>"+vos[i].missionOrder+"</td>"+
                        "<td>"+status+"</td>"+
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

    // tips.err("暂时不开放此功能",2000);
    // return;

    var $input = $("input[type=checkbox]:checked");
    if ($input == null ||$input.length == 0 ) {
        tips.err("至少选择一条",2000);
        return;
    }
    var missionIds = $input.map(function (index,elem) {
        return $(elem).val();
    }).get().join('&');

    $.ajax({
        url     :   "/c/page/console/auth/recommend/deleteMission",
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
    window.location.href = ("/c/page/console/auth/recommend/toUpdatePage?missionId="+missionId);
}
/**
 * 添加
 */
function addMission() {

    window.location.href = ("/c/page/console/auth/recommend/toAddPage");
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
    window.location.href = ("/c/page/console/auth/recommend/missionDetailPage?missionId="+missionId);
}

/**
 * 添加红包:弹框
 */
function addRecommendMissionRed() {

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

    var totalRedNum = $("#totalRedNum").val().trim();
    if (totalRedNum == "" || totalRedNum == null || totalRedNum == undefined){
        tips.err("请输入红包总数",4000);
        return;
    }

    $.ajax({
        url     :   "/c/page/console/auth/recommend/addRed",
        type    :   "POST",
        dataType:   "JSON",
        data    :   {missionId:missionId,num:totalRedNum},
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
 * 添加步骤
 */
function addStep() {

    var $input = $("input[type=checkbox]:checked");
    if ($input == null ||$input.length == 0||$input.length>1 ) {
        tips.err("必须且只能选择一条纪录",2000);
        return;
    }
    var missionId = $input.val();

    window.location.href = "/c/page/console/auth/recommend/addStepPage?missionId="+missionId;
}


/**
 * 查看用户提交任务列表
 */
function showTaskList() {

    var $input = $("input[type=checkbox]:checked");
    if ($input == null ||$input.length == 0||$input.length>1 ) {
        tips.err("必须且只能选择一条纪录",2000);
        return;
    }
    var missionId = $input.val();

    window.location.href = "/c/page/console/auth/recommendTask/recommendTaskList?missionId="+missionId;
}
