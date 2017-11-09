/**
 * 若失修改,则要把有src的隐藏图片显示出来
 */
$(document).ready(
    
    function () {

        var $verifyDiv = $("#addVerifyImgDiv");

        var hidesDiv = $verifyDiv.find(".hide");

        for(var i=0;i<hidesDiv.length;i++){

            var $hideDiv = $(hidesDiv[i]);

            var hideImg = $hideDiv.find(".oldImg");

            var imgUrl = $(hideImg).attr("src");

            if (imgUrl != "" && imgUrl != null && imgUrl != undefined ){

                //则显示出来

                $hideDiv.removeClass("hide");
            }
        }

    }
);


$(document).on("click",".closeImg",function () {

    if (detail == '1'){

        return;
    }

    var parentEle =  $(this).parent();

    var oldImg = $(parentEle).find(".oldImg");

    var inputHidden = $(parentEle).find("input[type=hidden]");

    oldImg.attr("src","");

    inputHidden.val("");
});

/**
 * 点击添加审核图片
 */
function addVerifyImg() {

    var $imgDiv = $("#addVerifyImgDiv");

    var hide = $imgDiv.find(".hide");

    var firstChild = $(hide[0]);

    firstChild.removeClass("hide")
}

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

    var missionLabel = $("#missionLabel").val();
    if (missionLabel == "" || missionLabel == null || missionLabel == undefined) {

        //1.先看用户是否点击了'不使用标签' 按钮
        var userLabel = $("#userLabel").val();
        if (userLabel == '0') {

            missionLabel == "";
        }else{
            /**
             * labels:旧标签
             */
            missionLabel = labels;
        }

    }
    if (missionLabel == null || missionLabel == undefined) {

        missionLabel = "";
    }
    missionLabel = missionLabel.toString();

    var missionType = $("#missionType").val();

    var exchangeCode = $("#exchangeCode").val().trim();
    if (exchangeCode == ""||exchangeCode==null||exchangeCode==undefined){

        if (missionType == 1){//兑换码红包,必须要兑换码
            tips.err("请生成兑换码",3000);
            return;
        }
    }

    var minMoney = $("#minMoney").val().trim();
    if (minMoney == "" || minMoney == null || minMoney == undefined){
        minMoney = 1.00;
    }
    var maxMoney = $("#maxMoney").val().trim();
    if (maxMoney == "" || maxMoney == null || maxMoney == undefined){
        maxMoney = 1.00;
    }
    var startTime = $("#startTime").val().trim();
    var endTime = $("#endTime").val().trim();
    var totalNum = $("#totalNum").val().trim();
    if (totalNum == "" || totalNum == null || totalNum == undefined){
        totalNum = 200;
    }
    var leftNum = $("#leftNum").val().trim();
    if (leftNum == "" || leftNum == null || leftNum == undefined){
        leftNum = totalNum;
    }
    var missionOrder = $("#missionOrder").val();
    if (missionOrder == "" || missionOrder == null || missionOrder == undefined){
        missionOrder = 0;
    }
    var missionDesc = $("#missionDesc").val().trim();
    var missionStatus = $("#missionStatus").val();
    var isLimitTime = $("#isLimitTime").val();
    var limitMinute = $("#limitMinute").val();
    if (limitMinute == "" || limitMinute == null || limitMinute == undefined){
        limitMinute = 60;
    }

    var isVerify = $("#isVerify").val();
    var verifyText = $("#verifyText").val();
    var verifyImg = $("#verifyImg").val();

    //审核要求
    var requiresInput = $("input[name=verifyRequire]:checked");

    var json = "";
    for (var i=0;i<requiresInput.length;i++){

        var requireId = $(requiresInput[i]).val();
        var requireName=$(requiresInput[i]).attr('requireName');

        var map = {
            'requireId':requireId,
            'requireName':requireName,
        };
        var one = JSON.stringify(map);
        if (json==""){
            json ="["+one;
        }else{
            json=json+","+one;
        }
    }
    json = json+"]";
    if (requiresInput.length == 0){

        json="";
    }

    var $verifyDiv = $("#addVerifyImgDiv");
    //找到他下面的所有的图片的隐藏输入框
    var inputs = $verifyDiv.find("input[type=hidden]");
    //遍历出他的value,用';'链接
    var missionImgs="";

    for (var i=0;i<inputs.length;i++){

        //先去上传的图片
        var imgUrl = $(inputs[i]).val();

        if (imgUrl == "" || imgUrl == null || imgUrl == undefined){
            //若没有上传,则取旧图片
            imgUrl = $("#oldVerifyImg"+(i+1)).attr("src");
        }

        if (imgUrl != "" && imgUrl != null && imgUrl != undefined){

            if (missionImgs == "") {
                missionImgs = imgUrl;
            }else {
                missionImgs = missionImgs+";"+ imgUrl;
            }
        }
    }
    tips.loading();

    $.ajax({
        url:"/c/page/console/auth/recommend/saveOrUpdateMission",
        type:"POST",
        dataType:"JSON",
        data:{
            missionId:missionId,
            missionTitle:missionTitle,
            missionType:missionType,
            exchangeCode:exchangeCode,
            missionIcon:missionIcon,
            missionLabel:missionLabel,
            minMoney:minMoney,
            maxMoney:maxMoney,
            startTime:startTime,
            endTime:endTime,
            totalNum:totalNum,
            leftNum:leftNum,
            missionDesc:missionDesc,
            missionStatus:missionStatus,
            isLimitTime:isLimitTime,
            limitMinute:limitMinute,
            isVerify:isVerify,
            verifyText:verifyText,
            verifyImg:verifyImg,
            verifyRequire:json,
            missionImgs:missionImgs,
            missionOrder:missionOrder,
        },
        success:function (data) {
            if (data.success){
                tips.suc(data.msg,2000);
                window.location.href = ("/c/page/console/auth/recommend/recommendList");
            }else {
                tips.err(data.msg,2000);
            }
        },
        error:function () {

        }
    });
});