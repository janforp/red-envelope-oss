/**
 * 点击保存
 */
$(document).on("click","button.btn-primary",function () {

    var sortId = $("#sortId").val();
    var sortName = $("#sortName").val();
    var sortOrder = $("#sortOrder").val();
    var sortImg = $("#sortImg").val();
    if (sortImg == "" || sortImg == null || sortImg == undefined){
        sortImg = $("#oldSortImg").attr("src");
    }

    tips.loading();

    $.ajax({
        url:"/c/page/console/auth/sort/updateOrSaveSort",
        type:"POST",
        dataType:"JSON",
        data:{sortId:sortId,sortName:sortName,sortOrder:sortOrder,sortImg:sortImg},
        success:function (data) {
            if (data.success){
                tips.suc("操作成功",2000);
                window.location.href = ("/c/page/console/auth/sort/sortList")
            }else {
                tips.err(data.msg,2000);
            }
        },
        error:function () {
            
        }
    });
});