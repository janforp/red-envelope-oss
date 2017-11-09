$(document).on("click","#save",function () {

    var $custom = $("#customId :selected");
    var customId = $custom.val();
    if (customId == null || customId == "请选择" || customId.trim().length == 0) {
        tips.err("请选择一个客户",2000);
        return;
    }
    var isDirect = $("#isDirect").val();
    var directUrl = $("#redirectUrl").val();
    var waitTime = $("#waitTime").val();
    var extendDesc = $("#extendDesc").val();

    var bannerImg = $("#add_banner").val();
    var bannerUrl = $("#bannerUrl").val();
    var advertImg = $("#add_adverst").val();
    var adverstUrl = $("#adverstUrl").val();
    var num = $("#num").val();
    if (parseInt(num) != num) {
        tips.err("红包数需填整数");
        return;
    }
    var big = $("#big").val();
    //需要正则表达式判断
    var status = 1;
    var startTime = $("#startTime").val();
    if (startTime == null || startTime.trim().length ==0){
        tips.err("请填写开始时间");
        return;
    }
    var endTime = $("#endTime").val();
    if (endTime == null || endTime.trim().length == 0) {
        tips.err("请填写结束时间");
        return;
    }
    var isHot = $("#hot").val();
    var chance = $("#chance").val();
    if (chance == null || chance.trim().length == 0) {
        tips.err("请填写中奖概率");
        return;
    }

    var stepRule = ue.getContent();
    $.ajax({
        url     :       "/c/page/console/auth/extend/save",
        type    :       "POST",
        dataType:       "JSON",
        data    :       {
            customerId:customId,
            isRedirect:isDirect,
            redirectUrl:directUrl,
            waitTime:waitTime,
            extendDesc:extendDesc,
            bannerImg:bannerImg,
            bannerUrl:bannerUrl,
            advertImg:advertImg,
            adverstUrl:adverstUrl,
            num:num,
            big:big,
            status:status,
            startTime:startTime,
            endTime:endTime,
            isHot:isHot,
            chance:chance,
            stepRule:stepRule
        },
        success :       function (data) {

            if (data.success) {
                tips.suc(data.msg,2000);
                $("#showInApp").attr("extendId",data.bean);
            }else {
                tips.suc(data.msg,2000);
            }
        },
        error   :       function () {

        }
    });

});


/**
 * 查看效果
 */
function  showExtendInApp() {

    var extendId = $("#showInApp").attr("extendId");
    if (extendId == "" || extendId == null || extendId == undefined) {
        tips.err("请先保存",2000);
        return;
    }
    window.location.href = (" http://hb.lswuyou.cn/c/p/extend/extendDetail/"+extendId);
}



