
/**
 * 点击保存
 */
$(document).on("click","#save",function () {

    var adId = $("#adId").text().trim();
    var adTitle = $("#adTitle").val();
    if (adTitle == ""|| adTitle == null || adTitle == undefined) {
        tips.err("标题不能空");
        return;
    }
    var adStatus = $("#adStatus").val();

    var adImg = $("#adImg").val().trim();
    if (adImg == ""|| adImg == null || adImg == undefined) {
        adImg = $("#oldImg").attr("src");
    }

    var adUrl = $("#adUrl").val().trim();
    var adSkip = $("#adSkip").val().trim();

    var adDuration = $("#adDuration").val().trim();

    if (parseInt(adDuration).toString()!== adDuration.toString()){
        tips.err("时间必须是整数");
        return;
    }
    if (parseInt(adDuration) > 99) {

        tips.err("时间不能长于99秒");
        return;
    }
    tips.loading();

    $.ajax({
        url:"/c/page/console/auth/startAd/saveOrUpdateStartAd",
        type:"POST",
        dataType:"JSON",
        data:{
            adId:adId,
            adTitle:adTitle,
            adStatus:adStatus,
            adImg:adImg,
            adUrl:adUrl,
            adSkip:adSkip,
            adDuration:adDuration
        },
        success:function (data) {
            if (data.success){
                tips.suc('操作成功',2000);
                window.open("/c/page/console/auth/startAd/startAdList");
            }else {
                tips.err(data.msg,2000);
            }
        },
        error:function () {

        }
    });
});
