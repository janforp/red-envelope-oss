
/**
 * 点击保存
 */
$(document).on("click","#save",function () {

    var fixedId = $("#fixedId").text().trim();
    var fixedTitle = $("#fixedTitle").val();
    if (fixedTitle == ""|| fixedTitle == null || fixedTitle == undefined) {
        tips.err("标题不能空");
        return;
    }
    var fixedAmount = $("#fixedAmount").val();

    var fixedRemainder = $("#fixedRemainder").val();

    var fixedUrl = $("#fixedUrl").val();

    var adUrl = $("#adUrl").val();

    var fixedHour = $("#fixedHour").val();
    

    if (fixedHour < 0 || fixedHour > 24){
        tips.err("小时必须在0-24之间");
        return;
    }

    var fixedMinute = $("#fixedMinute").val();



    if (fixedMinute < 0 || fixedMinute > 60){
        tips.err("分钟必须在0-60之间");
        return;
    }
    var fixedSecond = $("#fixedSecond").val();


    if (fixedSecond < 0 || fixedSecond > 60){
        tips.err("秒必须在0-60之间");
        return;
    }
    var fixedStatus = $("#fixedStatus").val();

    if (parseInt(fixedAmount).toString()!== fixedAmount.toString()){
        tips.err("数量必须是整数");
        return;
    }

    tips.loading();

    $.ajax({
        url:"/c/page/console/auth/red/saveOrUpdateFixedRed",
        type:"POST",
        dataType:"JSON",
        data:{
            fixedId:fixedId,
            fixedTitle:fixedTitle,
            fixedAmount:fixedAmount,
            fixedRemainder:fixedRemainder,
            fixedUrl:fixedUrl,
            adUrl:adUrl,
            fixedHour:fixedHour,
            fixedMinute:fixedMinute,
            fixedSecond:fixedSecond,
            fixedStatus:fixedStatus
        },
        success:function (data) {
            if (data.success){
                tips.suc('操作成功',2000);
                window.open("/c/page/console/auth/red/fixedRedList");
            }else {
                tips.err(data.msg,2000);
            }
        },
        error:function () {

        }
    });
});
