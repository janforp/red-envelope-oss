
/**
 * 点击保存
 */
$(document).on("click","#save",function () {

    var discoverId = $("#discoverId").text().trim();
    var discoverTitle = $("#discoverTitle").val();
    var discoverStatus = $("#discoverStatus").val();

    var discoverImg = $("#discoverImg").val().trim();
    if (discoverImg == ""|| discoverImg == null || discoverImg == undefined) {
        discoverImg = $("#oldImg").attr("src");
    }

    var discoverDescription = $("#discoverDescription").val().trim();
    var discoverUrl = $("#discoverUrl").val().trim();
    var discoverOrder = $("#discoverOrder").val().trim();

    tips.loading();

    $.ajax({
        url:"/c/page/console/auth/discover/saveOrUpdateDiscover",
        type:"POST",
        dataType:"JSON",
        data:{
            discoverId:discoverId,
            discoverTitle:discoverTitle,
            discoverStatus:discoverStatus,
            discoverImg:discoverImg,
            discoverDescription:discoverDescription,
            discoverUrl:discoverUrl,
            discoverOrder:discoverOrder
        },
        success:function (data) {
            if (data.success){
                tips.suc('操作成功',2000);
                window.open("/c/page/console/auth/discover/discoverList");
            }else {
                tips.err(data.msg,2000);
            }
        },
        error:function () {

        }
    });
});
