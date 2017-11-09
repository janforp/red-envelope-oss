
/**
 * 点击保存
 */
$(document).on("click","#save",function () {

    var sortId = $("#sortId").text().trim();
    var sortTitle = $("#sortTitle").val();
    if (sortTitle == ""|| sortTitle == null || sortTitle == undefined) {
        tips.err("标题不能空");
        return;
    }
    var sortStatus = $("#sortStatus").val();

    var sortOrder = $("#sortOrder").val().trim();

    if (parseInt(sortOrder).toString()!== sortOrder.toString()){
        tips.err("排序必须是整数");
        return;
    }
    if (parseInt(sortOrder) > 99) {

        tips.err("排序不能长于99秒");
        return;
    }

    tips.loading();

    $.ajax({
        url:"/c/page/console/auth/redSort/saveOrUpdateRedSort",
        type:"POST",
        dataType:"JSON",
        data:{
            sortId:sortId,
            sortTitle:sortTitle,
            sortStatus:sortStatus,
            sortOrder:sortOrder
        },
        success:function (data) {
            if (data.success){
                tips.suc('操作成功',2000);
                window.open("/c/page/console/auth/redSort/redSortList");
            }else {
                tips.err(data.msg,2000);
            }
        },
        error:function () {

        }
    });
});
