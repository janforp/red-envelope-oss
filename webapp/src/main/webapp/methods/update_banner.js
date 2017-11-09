
/**
 * 点击保存
 */
$(document).on("click","#save",function () {

    var bannerId = $("#bannerId").text().trim();
    var bannerTitle = $("#bannerTitle").val();
    var bannerStatus = $("#bannerStatus").val();

    var bannerImg = $("#bannerImg").val().trim();
    if (bannerImg == ""|| bannerImg == null || bannerImg == undefined) {
        bannerImg = $("#oldImg").attr("src");
    }

    var bannerUrl = $("#bannerUrl").val().trim();
    var bannerOrder = $("#bannerOrder").val().trim();

    tips.loading();


    $.ajax({
        url:"/c/page/console/auth/banner/saveOrUpdateBanner",
        type:"POST",
        dataType:"JSON",
        data:{
            bannerId:bannerId,
            bannerTitle:bannerTitle,
            bannerStatus:bannerStatus,
            bannerImg:bannerImg,
            bannerUrl:bannerUrl,
            bannerOrder:bannerOrder
        },
        success:function (data) {
            if (data.success){
                tips.suc('操作成功',2000);
                window.open("/c/page/console/auth/banner/bannerList");
            }else {
                tips.err(data.msg,2000);
            }
        },
        error:function () {

        }
    });



});
