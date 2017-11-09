function saveRed(codeId) {


    var num = $("#unlock").val();
    if (parseInt(num) != num) {
        tips.err("红包数需填整数");
        return;
    }
    if(codeId == null || codeId.length == 0){
        tips.err("推广或该客户不存在",2000);
        return;
    }

    $.ajax({
        url     :       "/c/page/console/auth/red/unlockCodeRed",
        type    :       "POST",
        dataType:       "JSON",
        data    :       {

            num:num,
            codeId:codeId
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