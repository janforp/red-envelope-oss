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
    var sortId = $("#sort").val();
    var hot = $("#hot").val();
    var missionName = $("#missionName").val().trim();
    if (parseInt(pageSize) != pageSize && pageSize != ""){
        $("#pageSizeAlert").text("请输入整数");
        setTimeout(function () {
            $("#pageSizeAlert").text("");
        },2000);
        return;
    }


    $.ajax({
        url     :   "/c/page/console/auth/mission/listByPageSize",
        type    :   "POST",
        dataType:   "JSON",
        data    :   {pageSize:pageSize,sortId:sortId,missionName:missionName,hot:hot},
        success :   function(data){

            if (data.success) {

                var vos = data.bean.missions;
                $("#pageNow").text(data.bean.pageNow);
                $("#totalPage").text(data.bean.totalPage);

                var body = $("#missionBody");
                body.empty();
                var newTbody = "";
                for (var i= 0;i<vos.length ;i ++) {

                    var status = vos[i].showOrNot;
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
                            "<td>"+vos[i].missionId+"</td>"+
                            "<td>"+vos[i].missionName+"</td>"+
                            "<td><img src='"+vos[i].missionImg+"'></td>"+
                            "<td>"+vos[i].missionReward+"</td>"+
                            "<td>"+vos[i].missionExtraReward+"</td>"+
                            "<td>"+vos[i].sortName+"</td>"+
                            "<td>"+vos[i].showHot+"</td>"+
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
    var sortId = $("#sort").val();
    var hot = $("#hot").val();
    var missionName = $("#missionName").val().trim();
    if (parseInt(pageSize) != pageSize && pageSize != ""){
        $("#pageSizeAlert").text("请输入整数");
        setTimeout(function () {
            $("#pageSizeAlert").text("");
        },2000);
        return;
    }

    $.ajax({
        url     :   "/c/page/console/auth/mission/listByPageSize",
        type    :   "POST",
        dataType:   "JSON",
        data    :   {pageSize:pageSize,sortId:sortId,missionName:missionName,hot:hot,pageNum:pageNum},
        success :   function(data){

            if (data.success) {

                var vos = data.bean.missions;
                $("#pageNow").text(data.bean.pageNow);
                $("#totalPage").text(data.bean.totalPage);

                var body = $("#missionBody");
                body.empty();
                var newTbody = "";
                for (var i= 0;i<vos.length ;i ++) {

                    var status = vos[i].showOrNot;
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
                            "<td>"+vos[i].missionId+"</td>"+
                            "<td>"+vos[i].missionName+"</td>"+
                            "<td><img src='"+vos[i].missionImg+"'></td>"+
                            "<td>"+vos[i].missionReward+"</td>"+
                            "<td>"+vos[i].missionExtraReward+"</td>"+
                            "<td>"+vos[i].sortName+"</td>"+
                            "<td>"+vos[i].showHot+"</td>"+
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
function deleteSort() {

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
        url     :   "/c/page/console/auth/mission/deleteMission",
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
    window.location.href = ("/c/page/console/auth/mission/updateMissionPage?missionId="+missionId);
}
/**
 * 添加
 */
function addMission() {

    window.location.href = ("/c/page/console/auth/mission/addMissionPage");
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
    window.location.href = ("/c/page/console/auth/mission/missionDetailPage?missionId="+missionId);
}

/**
 * 查看效果
 */
function  showInApp() {

    var $input = $("input[type=checkbox]:checked");
    if ($input == null ||$input.length == 0||$input.length>1 ) {
        tips.err("必须且只能选择一条纪录",2000);
        return;
    }
    var missionId = $input.val();
    window.open(" http://hb.lswuyou.cn/c/p/mission/missionDetail/"+missionId);
}
