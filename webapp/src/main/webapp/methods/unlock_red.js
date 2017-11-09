function saveRed(extendId) {


    var num = $("#unlock").val();
    if (parseInt(num) != num) {
        tips.err("红包数需填整数");
        return;
    }
    if(extendId == null || extendId.length == 0){
        tips.err("推广或该客户不存在",2000);
        return;
    }

    $.ajax({
        url     :       "/c/page/console/auth/red/unlockRed",
        type    :       "POST",
        dataType:       "JSON",
        data    :       {

            num:num,
            id:extendId
        },
        success :       function (data) {

            if (data.success) {
                tips.suc(data.msg,2000);
            }else {
                tips.suc(data.msg,2000);
            }
        },
        error   :       function () {

        }
    });

}