
/**
 * 点击保存
 */
$(document).on("click","#save",function () {

    var codeId = $("#codeId").text();
    var redCode = $("#redCode").val();
    var customerName = $("#customerName").val();

    var customerImg = $("#customerImg").val().trim();
    if (customerImg == ""|| customerImg == null || customerImg == undefined) {
        customerImg = $("#oldCustomerImg").attr("src");
    }
    var customerDesc = $("#customerDesc").val();
    var awardDesc = $("#awardDesc").val().trim();

    var customerBanner = $("#customerBanner").val().trim();
    if (customerBanner == ""|| customerBanner == null || customerBanner == undefined) {
        customerBanner = $("#oldCustomerBanner").attr("src");
    }

    var customerBannerUrl = $("#customerBannerUrl").val().trim();
    var redMax = $("#redMax").val();
    var redDesc = $("#redDesc").val();

    var redNumTotal = $("#redNumTotal").val().trim();
    var redNumLeft = $("#redNumLeft").val();
    var redNumDayTotal = $("#redNumDayTotal").val();
    var redNumDayLeft = $("#redNumDayLeft").val().trim();

    var codeStatus = $("#codeStatus").val();
    var showCreateTime = $("#createTime").val();
    var showUpdateTime = $("#updateTime").val();


    if (parseInt(redNumTotal).toString()!== redNumTotal.toString()){
        tips.err("红包总数必须为整数");
        return;
    }
    if (isNaN(redNumTotal)){
        tips.err("红包总数必须为整数");
        return;
    }

    if (parseInt(redNumLeft).toString()!== redNumLeft.toString()){
        tips.err("红包总剩余数必须为整数");
        return;
    }
    if (isNaN(redNumTotal)){
        tips.err("红包总剩余数必须为整数");
        return;
    }

    if (parseInt(redNumDayTotal).toString()!== redNumDayTotal.toString()){
        tips.err("当天总数必须为整数");
        return;
    }
    if (isNaN(redNumDayTotal)){
        tips.err("当天总数必须为整数");
        return;
    }

    if (parseInt(redNumDayLeft).toString()!== redNumDayLeft.toString()){
        tips.err("当天剩余数必须为整数");
        return;
    }
    if (isNaN(redNumDayLeft)){
        tips.err("当天剩余数必须为整数");
        return;
    }


    tips.loading();

    $.ajax({
        url:"/c/page/console/auth/red/saveOrUpdateCodeRed",
        type:"POST",
        dataType:"JSON",
        data:{
            codeId:codeId,
            redCode:redCode,
            customerName:customerName,
            customerImg:customerImg,
            customerDesc:customerDesc,
            awardDesc:awardDesc,
            customerBanner:customerBanner,
            customerBannerUrl:customerBannerUrl,
            redMax:redMax,
            redDesc:redDesc,
            redNumTotal:redNumTotal,
            redNumLeft:redNumLeft,
            redNumDayTotal:redNumDayTotal,
            redNumDayLeft:redNumDayLeft,
            codeStatus:codeStatus,
            showCreateTime:showCreateTime,
            showUpdateTime:showUpdateTime
        },
        success:function (data) {
            if (data.success){
                tips.suc('操作成功',2000);
                window.open("/c/page/console/auth/red/codeRedList");
            }else {
                tips.err(data.msg,2000);
            }
        },
        error:function () {

        }
    });
});

/**
 * 点击生成随机 10 位口令
 */
$(document).on("click","#kouLin",function () {

    var kouLin = "";
    var arr = [ '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
        'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j',
        'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't',
        'u', 'v', 'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D',
        'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N',
        'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X',
        'Y', 'Z'];
    for (var i =0 ; i<10 ;i ++) {
        var random = Math.round(Math.random()*(arr.length - 1));
        kouLin += arr[random];
    }
    var redCode = $("#redCode");

    redCode.val(kouLin);
});
