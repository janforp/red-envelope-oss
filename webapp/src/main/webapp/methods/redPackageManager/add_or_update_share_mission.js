
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
 * 若是添加,则加载页面之后就点击口令按钮
 */
$(document).ready(

    function () {

        if(add == 1){

            $("#kouLin").click();
        }
    }
);

/**
 * 点击保存
 */
$(document).on("click","#save",function () {

    var missionId = $("#missionId").text().trim();
    var missionTitle = $("#missionTitle").val().trim();
    if (missionTitle == "" || missionTitle == null || missionTitle == undefined){
        tips.err("请输入任务标题",4000);
        return;
    }
    var missionIcon = $("#missionIcon").val().trim();
    if (missionIcon == "" || missionIcon == null || missionIcon == undefined){
        missionIcon = $("#oldMissionIcon").attr("src");
    }
    var missionDesc = $("#missionDesc").val().trim();
    var prizeRate = $("#prizeRate").val().trim();
    if (prizeRate == "" || prizeRate == null || prizeRate == undefined){
        prizeRate = 50;
    }
    var minMoney = $("#minMoney").val().trim();
    if (minMoney == "" || minMoney == null || minMoney == undefined){
        minMoney = 100;
    }
    var maxMoney = $("#maxMoney").val().trim();
    if (maxMoney == "" || maxMoney == null || maxMoney == undefined){
        maxMoney = 100;
    }
    var prizeTimes = $("#prizeTimes").val().trim();
    if (prizeTimes == "" || prizeTimes == null || prizeTimes == undefined){
        prizeTimes = 1;
    }
    var lotteryTimes = $("#lotteryTimes").val().trim();
    if (lotteryTimes == "" || lotteryTimes == null || lotteryTimes == undefined){
        lotteryTimes = 5;
    }
    var totalRedNum = $("#totalRedNum").val().trim();
    if (totalRedNum == "" || totalRedNum == null || totalRedNum == undefined){
        tips.err("请输入红包总数",4000);
        return;
    }
    var verifyIp = $("#verifyIp").val();
    var missionProvince = $("#missionProvince").val().trim();
    var missionCity = $("#missionCity").val().trim();
    var isEncrypted = $("#isEncrypted").val();
    var merchantSecret = $("#merchantSecret").val().trim();
    var merchantName = $("#merchantName").val().trim();
    var shareUrl = $("#shareUrl").val().trim();
    if (shareUrl == "" || shareUrl == null || shareUrl == undefined){
        tips.err("请输入分享链接",4000);
        return;
    }
    var shareTitle = $("#shareTitle").val().trim();
    if (shareTitle == "" || shareTitle == null || shareTitle == undefined){
        tips.err("请输入分享标题",4000);
        return;
    }
    var shareImg = $("#shareImg").val().trim();
    if (shareImg == "" || shareImg == null || shareImg == undefined){
        shareImg = $("#oldShareImg").attr("src");
    }
    if (shareImg == "" || shareImg == null || shareImg == undefined){
        tips.err("请上传分享图片",4000);
        return;
    }
    var shareDesc = $("#shareDesc").val().trim();
    var shareType = $("#shareType").val().trim();
    if (shareType == "" || shareType == null || shareType == undefined){
        shareType = 'link';
    }
    var shareDataurl = $("#shareDataurl").val().trim();
    var isEnd = $("#isEnd").val();
    var openImg = $("#openImg").val().trim();
    if (openImg == "" || openImg == null || openImg == undefined){
        openImg = $("#oldOpenImg").attr("src");
    }
    if (openImg == "" || openImg == null || openImg == undefined){
        tips.err("请上传打开红包图片",4000);
        return;
    }
    var successImg = $("#successImg").val().trim();
    if (successImg == "" || successImg == null || successImg == undefined){
        successImg = $("#oldSuccessImg").attr("src");
    }
    if (successImg == "" || successImg == null || successImg == undefined){
        tips.err("请上传成功图片",4000);
        return;
    }
    var failImg = $("#failImg").val().trim();
    if (failImg == "" || failImg == null || failImg == undefined){
        failImg = $("#oldFailImg").attr("src");
    }
    if (failImg == "" || failImg == null || failImg == undefined){
        tips.err("请上传失败图片",4000);
        return;
    }

    tips.loading();


    $.ajax({
        url:"/c/page/console/auth/share/saveOrUpdateMission",
        type:"POST",
        dataType:"JSON",
        data:{
            missionId:missionId,
            missionTitle:missionTitle,
            missionIcon:missionIcon,
            missionDesc:missionDesc,
            prizeRate:prizeRate,
            minMoney:minMoney,
            maxMoney:maxMoney,
            prizeTimes:prizeTimes,
            lotteryTimes:lotteryTimes,
            totalRedNum:totalRedNum,
            verifyIp:verifyIp,
            isEncrypted:isEncrypted,
            missionProvince:missionProvince,
            missionCity:missionCity,
            merchantName:merchantName,
            shareUrl:shareUrl,
            shareTitle:shareTitle,
            shareImg:shareImg,
            shareDesc:shareDesc,
            shareType:shareType,
            shareDataurl:shareDataurl,
            successImg:successImg,
            failImg:failImg,
            openImg:openImg,
            merchantSecret:merchantSecret,
            isEnd:isEnd
        },
        success:function (data) {
            if (data.success){
                tips.suc(data.msg,2000);
                window.location.href = ("/c/page/console/auth/share/shareList");
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
    var merchantSecret = $("#merchantSecret");

    merchantSecret.val(kouLin);
});


/**
 * 使用旧密钥
 */
function userOldSecret() {

    var oldSecret = $("#oldMerchantSecret").val();

    $("#merchantSecret").val(oldSecret);
}
