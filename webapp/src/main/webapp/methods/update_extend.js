//修改推广
function saveRed(extendId,oldbannerImg,oldadverstImg) {

    var customerName = $("#customerName").val();
    if(customerName == null || customerName.trim().length == 0 || customerName == undefined) {
        tips.err("客户名不能空",2000);
        return;
    }
    var isRedirect = $("#isRedirect").val();
    var redirectUrl = $("#redirectUrl").val();
    var waitTime = $("#waitTime").val();
    if (parseInt(waitTime) != waitTime) {
        tips.err("等待时间处输入整数",2000);
        return;
    }

    var extendDesc = $("#extendDesc").val();

    var bannerImg = $("#add_banner").val();
    if (bannerImg == null || bannerImg == undefined || bannerImg == "") {
        bannerImg = oldbannerImg;
    }

    var bannerUrl = $("#bannerUrl").val();

    var advertImg = $("#add_adverst").val();
    if (advertImg == null || advertImg == undefined || advertImg == "") {
        advertImg = oldadverstImg;
    }

    var adverstUrl = $("#adverstUrl").val();

    var status = $("#status").val();
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
        url     :       "/c/page/console/auth/extend/update",
        type    :       "POST",
        dataType:       "JSON",
        data    :       {
            extendId:extendId,
            customerName:customerName,
            isRedirect:isRedirect,
            redirectUrl:redirectUrl,
            waitTime:waitTime,
            extendDesc:extendDesc,
            bannerImg:bannerImg,
            bannerUrl:bannerUrl,
            advertImg:advertImg,
            adverstUrl:adverstUrl,
            status:status,
            startTime:startTime,
            endTime:endTime,
            isHot:isHot,
            redChance:chance,
            stepRule:stepRule
        },
        success :       function (data) {

            if (data.success) {
                tips.suc("修改成功",3000);
                $("#showInApp").attr("extendId",data.msg);
                // window.location.href = "/c/page/console/auth/extend/detail?id="+extendId;
            }else {
                tips.suc(data.msg,2000);
            }
        },
        error   :       function () {

        }
    });

}


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


