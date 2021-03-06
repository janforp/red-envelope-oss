/**
 * 删除
 */
function deleteBanner() {

    // tips.err("暂时不开放此功能",2000);
    // return;

    var $input = $("input[type=checkbox]:checked");
    if ($input == null ||$input.length == 0 ) {
        tips.err("至少选择一条",2000);
        return;
    }
    var bannerIds = $input.map(function (index,elem) {
        return $(elem).val();
    }).get().join('&');

    $.ajax({
        url     :   "/c/page/console/auth/banner/deleteBanner",
        type    :   "POST",
        dataType:   "JSON",
        data    :   {bannerIds:bannerIds},

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
function updateBanner() {

    var $input = $("input[type=checkbox]:checked");
    if ($input == null ||$input.length == 0||$input.length>1 ) {
        tips.err("必须且只能选择一条纪录",2000);
        return;
    }
    var bannerId = $input.val();
    window.open("/c/page/console/auth/banner/updateBannerPage?bannerId="+bannerId);
}
/**
 * 添加
 */
function addBanner() {

    window.location.href = ("/c/page/console/auth/banner/addBannerPage");
}
