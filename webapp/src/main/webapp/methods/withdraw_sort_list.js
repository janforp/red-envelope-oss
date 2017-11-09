/**
 * 删除
 */
function deleteWithdraws() {

    // tips.err("暂时不开放此功能",2000);
    // return;

    var $input = $("input[type=checkbox]:checked");
    if ($input == null ||$input.length == 0 ) {
        tips.err("至少选择一条",2000);
        return;
    }
    var withdrawIds = $input.map(function (index,elem) {
        return $(elem).val();
    }).get().join('&');

    $.ajax({
        url     :   "/c/page/console/auth/withdraw/deleteWithdrawSort",
        type    :   "POST",
        dataType:   "JSON",
        data    :   {withdrawIds:withdrawIds},

        success :   function(data){

            if (data.success) {
                $input.parent().parent().remove();
                tips.suc(data.msg,2000);
            }else{
                tips.err(data.msg,2000);
            }
        },
        error   :   function () {

        }
    });
}
/**
 * 跳转到修改页面
 */
function updateWithdraw() {

    var $input = $("input[type=checkbox]:checked");
    if ($input == null ||$input.length == 0||$input.length>1 ) {
        tips.err("必须且只能选择一条纪录",2000);
        return;
    }
    var withdrawId = $input.val();
    window.open("/c/page/console/auth/withdraw/updateWithdrawSortPage?withdrawId="+withdrawId);
}
/**
 * 添加
 */
function addWithdraw() {

    window.location.href = ("/c/page/console/auth/withdraw/addWithdrawSortPage");
}

/**
 * 悬浮
 */

var timeout;
function showDesc(node) {

        timeout = setTimeout(function () {
        var desc = $(node).val();
        $(".alertDesc").show();
        $(".desc").html(desc)
    },500);
}

/**
 * 鼠标离开
 */
function  closeDesc() {
    clearTimeout(timeout);
    $(".alertDesc").hide();
}
