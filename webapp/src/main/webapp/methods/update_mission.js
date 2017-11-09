
// 显示图片
$(document).on("click", ".active_img", function () {
    var imageUrl = $(this).attr("src");
    var arrayImage = imageUrl.split(";");
    var arrayStr = "";
    for (var k = 0; k < arrayImage.length; k++) {
        arrayStr += "<img src='" + arrayImage[k] + "' style='width:100%; margin-top:20px;' />";
    }
    $("#image-detail").html(arrayStr);
    $("#modal-showImage").modal();
});

// 删除图片
$(document).on("click", ".delete_pic[obj='reImg']", function () {
    $(this).parent().remove();
    var pathArray = "";
    $(".muti_box[obj='reImg']").find("img").each(function () {
        var pathX = $(this).attr("src");
        pathArray += pathArray ? ";" + pathX : pathX;
    });
    $("#txtReleaseImg").val(pathArray);
});

/**
 * 点击保存
 */
$(document).on("click","#save",function () {

    var missionId = $("#missionId").text().trim();
    var missionName = $("#missionName").val();
    if (missionName == "" || missionName == null || missionName == undefined) {
        tips.err("任务名不能空");
        return;
    }
    var missionStatus = $("#missionStatus").val();
    var showOrNot = $("#showOrNot").val();
    var merchantName = $("#merchantName").val().trim();
    var merchantDetail = $("#merchantDetail").val().trim();
    var missionReward = $("#missionReward").val().trim();
    var missionExtraReward = $("#missionExtraReward").val().trim();
    var participantsNum = $("#participantsNum").val().trim();

    if (participantsNum == "" || participantsNum == null || participantsNum == undefined) {
        participantsNum = 0 ;
    }
    var gainRewardNum = $("#gainRewardNum").val().trim();

    if (gainRewardNum == "" || gainRewardNum == null || gainRewardNum == undefined) {
        gainRewardNum = 0 ;
    }

    var missionHot = $("#missionHot").val();
    var missionSort = $("#missionSort").val();
    var missionImg = $("#missionImg").val().trim();
    if (missionImg == ""|| missionImg == null || missionImg == undefined) {
        missionImg = $("#oldImg").attr("src");
    }
    var missionAdImg = $("#missionAdImg").val().trim();
    if (missionAdImg == "" || missionAdImg == null || missionAdImg == undefined){
        missionAdImg = $("#oldAdImg").attr("src");
    }
    var missionUrl = $("#missionUrl").val().trim();
    var startTime = $("#startTime").val().trim();

    if (startTime == "" || startTime == null || startTime == undefined) {
        tips.err("开始时间不能空");
        return;
    }

    var endTime = $("#endTime").val().trim();

    if (endTime == "" || endTime == null || endTime == undefined) {
        tips.err("结束时间不能空");
        return;
    }

    //任务规则
    var missionRule = ue.getContent();


    tips.loading();


    $.ajax({
        url:"/c/page/console/auth/mission/saveOrUpdateMission",
        type:"POST",
        dataType:"JSON",
        data:{
            missionId:missionId,
            missionName:missionName,
            missionStatus:missionStatus,
            showOrNot:showOrNot,
            merchantName:merchantName,
            merchantDetail:merchantDetail,
            missionReward:missionReward,
            missionExtraReward:missionExtraReward,
            participantsNum:participantsNum,
            gainRewardNum:gainRewardNum,
            missionHot:missionHot,
            missionSort:missionSort,
            missionImg:missionImg,
            missionAdImg:missionAdImg,
            missionUrl:missionUrl,
            showStartTime:startTime,
            showEndTime:endTime,
            missionRule:missionRule
        },
        success:function (data) {
            if (data.success){
                tips.suc('操作成功',2000);
                window.location.href = ("/c/page/console/auth/mission/missionList");
            }else {
                tips.err(data.msg,2000);
            }
        },
        error:function () {

        }
    });
});

/**
 * 查看效果
 */
function  showInApp() {


    var missionId = $("#missionId").text().trim();
    var missionName = $("#missionName").val();
    if (missionName == "" || missionName == null || missionName == undefined) {
        tips.err("任务名不能空");
        return;
    }
    var missionStatus = $("#missionStatus").val();
    var showOrNot = $("#showOrNot").val();
    var merchantName = $("#merchantName").val().trim();
    var merchantDetail = $("#merchantDetail").val().trim();
    var missionReward = $("#missionReward").val().trim();
    var missionExtraReward = $("#missionExtraReward").val().trim();
    var participantsNum = $("#participantsNum").val().trim();

    if (participantsNum == "" || participantsNum == null || participantsNum == undefined) {
        participantsNum = 0 ;
    }
    var gainRewardNum = $("#gainRewardNum").val().trim();

    if (gainRewardNum == "" || gainRewardNum == null || gainRewardNum == undefined) {
        gainRewardNum = 0 ;
    }

    var missionHot = $("#missionHot").val();
    var missionSort = $("#missionSort").val();
    var missionImg = $("#missionImg").val().trim();
    if (missionImg == ""|| missionImg == null || missionImg == undefined) {
        missionImg = $("#oldImg").attr("src");
    }
    var missionAdImg = $("#missionAdImg").val().trim();
    if (missionAdImg == "" || missionAdImg == null || missionAdImg == undefined){
        missionAdImg = $("#oldAdImg").attr("src");
    }
    var missionUrl = $("#missionUrl").val().trim();
    var startTime = $("#startTime").val().trim();

    if (startTime == "" || startTime == null || startTime == undefined) {
        tips.err("开始时间不能空");
        return;
    }

    var endTime = $("#endTime").val().trim();

    if (endTime == "" || endTime == null || endTime == undefined) {
        tips.err("结束时间不能空");
        return;
    }

    //任务规则
    var missionRule = ue.getContent();
    $.ajax({
        url:"/c/page/console/auth/mission/saveOrUpdateMission",
        type:"POST",
        dataType:"JSON",
        data:{
            missionId:missionId,
            missionName:missionName,
            missionStatus:missionStatus,
            showOrNot:showOrNot,
            merchantName:merchantName,
            merchantDetail:merchantDetail,
            missionReward:missionReward,
            missionExtraReward:missionExtraReward,
            participantsNum:participantsNum,
            gainRewardNum:gainRewardNum,
            missionHot:missionHot,
            missionSort:missionSort,
            missionImg:missionImg,
            missionAdImg:missionAdImg,
            missionUrl:missionUrl,
            showStartTime:startTime,
            showEndTime:endTime,
            missionRule:missionRule
        },
        success:function (data) {
            if (data.success){
                var missionId = data.msg;
                window.open(" http://hb.lswuyou.cn/c/p/mission/missionDetail/"+missionId);

            }else {
                tips.err(data.msg,2000);
            }
        },
        error:function () {

        }
    });
}
