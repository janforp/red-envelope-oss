//添加红包
function addRed(){


    var num = $("#num").val();
    if (parseInt(num) != num) {
        tips.err("红包数需填整数");
        return;
    }
    var big = $("#big").val();
    //需要正则表达式判断
    var extendId = "${vo.id}";

    if(extendId == null || extendId.trim().length == 0){
        tips.err("推广或该客户不存在",2000);
        return;
    }

    $.ajax({
        url     :       "/c/page/console/auth/red/addRed",
        type    :       "POST",
        dataType:       "JSON",
        data    :       {

            num:num,
            bigEnvelope:big,
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

