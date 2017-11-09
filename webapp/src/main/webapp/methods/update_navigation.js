
/**
 * 点击保存
 */
$(document).on("click","#save",function () {

    var navigationId = $("#navigationId").text().trim();
    var navigationTitle = $("#navigationTitle").val();
    var navigationStatus = $("#navigationStatus").val();

    var navigationImg = $("#navigationImg").val().trim();
    if (navigationImg == ""|| navigationImg == null || navigationImg == undefined) {
        navigationImg = $("#oldImg").attr("src");
    }

    var navigationUrl = $("#navigationUrl").val().trim();
    var navigationOrder = $("#navigationOrder").val().trim();

    tips.loading();
    
    $.ajax({
        url:"/c/page/console/auth/navigation/saveOrUpdateNavigation",
        type:"POST",
        dataType:"JSON",
        data:{
            navigationId:navigationId,
            navigationTitle:navigationTitle,
            navigationStatus:navigationStatus,
            navigationImg:navigationImg,
            navigationUrl:navigationUrl,
            navigationOrder:navigationOrder
        },
        success:function (data) {
            if (data.success){
                tips.suc('操作成功',2000);
                window.open("/c/page/console/auth/navigation/navigationList");
            }else {
                tips.err(data.msg,2000);
            }
        },
        error:function () {

        }
    });
});
