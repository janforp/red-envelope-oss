/**
 * 删除
 */
function deleteDiscover() {

    var $input = $("input[type=checkbox]:checked");
    if ($input == null ||$input.length == 0 ) {
        tips.err("至少选择一条",2000);
        return;
    }
    var discoverIds = $input.map(function (index,elem) {
        return $(elem).val();
    }).get().join('&');

    $.ajax({
        url     :   "/c/page/console/auth/discover/deleteDiscover",
        type    :   "POST",
        dataType:   "JSON",
        data    :   {discoverIds:discoverIds},

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
function updateDiscover() {

    var $input = $("input[type=checkbox]:checked");
    if ($input == null ||$input.length == 0||$input.length>1 ) {
        tips.err("必须且只能选择一条纪录",2000);
        return;
    }
    var discoverId = $input.val();
    window.open("/c/page/console/auth/discover/updateDiscoverPage?discoverId="+discoverId);
}
/**
 * 添加
 */
function addDiscover() {

    window.location.href = ("/c/page/console/auth/discover/addDiscoverPage");
}
