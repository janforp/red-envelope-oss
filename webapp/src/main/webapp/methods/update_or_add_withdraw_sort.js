
/**
 * 点击保存
 */
$(document).on("click","#save",function () {

    var withdrawId = $("#withdrawId").text().trim();
    var withdrawName = $("#withdrawName").val();
    if (withdrawName == ""|| withdrawName == null || withdrawName == undefined) {
        tips.err("标题不能空");
        return;
    }

    var withdrawType = $("#withdrawType").val();

    var withdrawExplain = $("#withdrawExplain").val();

    var withdrawUrl = $("#withdrawUrl").val();

    var withdrawImg = $("#withdrawImg").val().trim();
    if (withdrawImg == ""|| withdrawImg == null || withdrawImg == undefined) {
        withdrawImg = $("#oldImg").attr("src");
    }
    var withdrawMoney = $("#withdrawMoney").val();

    var toAccountMoney = $("#toAccountMoney").val();


    var withdrawDesc = $("#withdrawDesc").val();

    var withdrawTimes = $("#withdrawTimes").val();

    var withdrawStatus = $("#withdrawStatus").val();

    var withdrawOrder = $("#withdrawOrder").val();


    if (withdrawTimes < 0 || withdrawTimes > 999){
        tips.err("次数限制在0-999");
        return;
    }

    if (parseInt(withdrawTimes).toString()!== withdrawTimes.toString()){
        tips.err("次数必须是整数");
        return;
    }

    if (withdrawOrder < 0 || withdrawOrder > 99){
        tips.err("排序在0-99");
        return;
    }

    if (parseInt(withdrawOrder).toString()!== withdrawOrder.toString()){
        tips.err("排序必须是整数");
        return;
    }
    tips.loading();

    $.ajax({
        url:"/c/page/console/auth/withdraw/saveOrUpdateWithdrawSort",
        type:"POST",
        dataType:"JSON",
        data:{
            withdrawId:withdrawId,
            withdrawName:withdrawName,
            withdrawType:withdrawType,
            withdrawExplain:withdrawExplain,
            withdrawUrl:withdrawUrl,
            withdrawImg:withdrawImg,
            withdrawMoney:withdrawMoney,
            toAccountMoney:toAccountMoney,
            withdrawDesc:withdrawDesc,
            withdrawTimes:withdrawTimes,
            withdrawStatus:withdrawStatus,
            withdrawOrder:withdrawOrder
        },
        success:function (data) {
            if (data.success){
                tips.suc('操作成功',2000);
                window.open("/c/page/console/auth/withdraw/withdrawSortList");
            }else {
                tips.err(data.msg,2000);
            }
        },
        error:function () {

        }
    });
});
