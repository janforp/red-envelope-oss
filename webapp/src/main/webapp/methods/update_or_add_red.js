
/**
 * 点击保存
 */
$(document).on("click","#save",function () {

    var redId = $("#redId").text().trim();
    var redName = $("#redName").val();
    var merchantName = $("#merchantName").val();

    var redImg = $("#redImg").val().trim();
    if (redImg == ""|| redImg == null || redImg == undefined) {
        redImg = $("#oldImg").attr("src");
    }
    var merchantDetail = $("#merchantDetail").val();
    var redRewardDesc = $("#redRewardDesc").val().trim();
    var rewardMoney = $("#rewardMoney").val();//
    if (parseInt(rewardMoney).toString()!== rewardMoney.toString()){
        tips.err("奖励必须是整数");
        return;
    }
    if (isNaN(rewardMoney)){
        tips.err("奖励必须是整数");
        return;
    }
    var extraRewardDesc = $("#extraRewardDesc").val();
    var extraMoney = $("#extraMoney").val();//
    if (parseInt(extraMoney).toString()!== extraMoney.toString()){
        tips.err("额外奖励必须是整数");
        return;
    }
    if (isNaN(extraMoney)){
        tips.err("额外奖励必须是整数");
        return;
    }
    var redSort = $("#redSort").val();
    var redDesc = $("#redDesc").val();

    var detailUrl = $("#detailUrl").val();
    var detailDeitor = "暂无";
    var buttonDeitor = "暂无";
    var redOrder = $("#redOrder").val();//

    if (parseInt(redOrder).toString()!== redOrder.toString()){
        tips.err("排序必须是整数");
        return;
    }
    if (isNaN(redOrder)){
        tips.err("排序必须是整数");
        return;
    }
    if (redOrder > 999){
        tips.err("排序数值过大");
        return;
    }
    var redStatus = $("#redStatus").val();
    var showOrNot = $("#showOrNot").val();//
    var showStartTime = $("#startTime").val();
    var showEndTime = $("#endTime").val();
    var showCreateTime = $("#createTime").val();

    tips.loading();

    $.ajax({
        url:"/c/page/console/auth/red/saveOrUpdateRed",
        type:"POST",
        dataType:"JSON",
        data:{
            redId:redId,
            redName:redName,
            merchantName:merchantName,
            redImg:redImg,
            merchantDetail:merchantDetail,
            redRewardDesc:redRewardDesc,
            rewardMoney:rewardMoney,
            extraRewardDesc:extraRewardDesc,
            extraMoney:extraMoney,
            redSort:redSort,
            redDesc:redDesc,
            detailUrl:detailUrl,
            detailDeitor:detailDeitor,
            buttonDeitor:buttonDeitor,
            redOrder:redOrder,
            redStatus:redStatus,
            showOrNot:showOrNot,
            showStartTime:showStartTime,
            showEndTime:showEndTime,
            showCreateTime:showCreateTime
        },
        success:function (data) {
            if (data.success){
                tips.suc('操作成功',2000);
                window.open("/c/page/console/auth/red/redList");
            }else {
                tips.err(data.msg,2000);
            }
        },
        error:function () {

        }
    });
});
