
/**
 * 点击保存
 */
$(document).on("click","#save",function () {

    var redDesc = ue.getContent();

    var redCode = $("#redCode").val().trim();
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
    
    var redNumTotal = $("#redNumTotal").val().trim();

    var codeStatus = $("#codeStatus").val();

    var bigRed = $("#bigRed").val().trim();
    var max = $("#max").val();
    var min = $("#min").val();


    if (parseInt(redNumTotal).toString()!== redNumTotal.toString()){
        tips.err("红包总数必须为整数");
        return;
    }
    if (isNaN(redNumTotal)){
        tips.err("红包总数必须为整数");
        return;
    }

    tips.loading();

    $.ajax({
        url:"/c/page/console/auth/red/addCodeRed",
        type:"POST",
        dataType:"JSON",
        data:{
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
            codeStatus:codeStatus,
            bigRed:bigRed,
            max:max,
            min:min
        },
        success:function (data) {
            if (data.success){
                tips.suc('操作成功',2000);
                $("#showInApp").attr("codeId",data.msg);
                // window.location.href=("/c/page/console/auth/red/codeRedList");
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



function showCodeInApp() {

    var codeId = $("#showInApp").attr("codeId");
    if (codeId==""||codeId==null||codeId==undefined){
        tips.err("请先保存",2000);
        return;
    }
    window.open("http://hb.lswuyou.cn/c/p/codeRed/codeDetail/"+codeId);
}

