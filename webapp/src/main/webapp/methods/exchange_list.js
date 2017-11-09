/**
 * 页面加载自动查询一次
 */
$(document).ready(function(){
    $("#listByPageSize").click();
});

/**
 * 根据用户输入,觉得每页显示的行数
 */
$(document).on("click","#listByPageSize",function(){


    var exchangeStatus = $("#exchangeStatus").val();

    var pageSize = $("#pageSize").val().trim();

    if (parseInt(pageSize) != pageSize && pageSize != ""){
        $("#pageSizeAlert").text("请输入整数");
        setTimeout(function () {
            $("#pageSizeAlert").text("");
        },2000);
        return;
    }

    $.ajax({
        url     :   "/c/page/console/auth/coinMall/exchangeList",
        type    :   "POST",
        dataType:   "JSON",
        data    :   {exchangeStatus:exchangeStatus,pageSize:pageSize,pageNum:1},
        success :   function(data){

            if (data.success) {

                var details = data.bean.details;
                var pageNow = data.bean.pageNow ;
                var totalPage = data.bean.totalPage ;
                if (totalPage == '0'){
                    pageNow = 0;
                }
                $("#pageNow").text(pageNow);
                $("#totalPage").text(totalPage);

                var body = $("#redBody");
                body.empty();
                var newTbody = "";
                for (var i= 0;i<details.length ;i ++) {



                    newTbody +=
                        "<tr>"+
                        "    <td><input type='checkbox' value='"+details[i].id+"'></td>"+
                        "    <td>"+details[i].userName+"</td>"+
                        "    <td>"+details[i].phone+"</td>"+
                        "    <td>"+details[i].goodsName+"</td>"+
                        "    <td>"+details[i].address+"</td>"+
                        "    <td>"+details[i].exchangeTime+"</td>"+
                        "    <td>"+details[i].sendTime+"</td>";

                    if (details[i].exchangeStatus == '0'){
                        newTbody += "    <td style='color: green;'>未发货</td>";
                    }else if(details[i].exchangeStatus == '1') {
                        newTbody += "    <td style='color: #00a0e9;'>已发货</td>";
                    }else if(details[i].exchangeStatus == '2') {
                        newTbody += "    <td style='color: red;'>已作废</td>";
                    }
                    newTbody += "</tr>";
                }
                body.append(newTbody);
            }else{
                tips.err("操作失败",2000);
            }

        },
        error   :   function () {

        }
    });
});

/**
 * 翻页
 */
function turnPage(pageNum) {


    var exchangeStatus = $("#exchangeStatus").val();

    var pageSize = $("#pageSize").val().trim();

    if (parseInt(pageSize) != pageSize && pageSize != ""){
        $("#pageSizeAlert").text("请输入整数");
        setTimeout(function () {
            $("#pageSizeAlert").text("");
        },2000);
        return;
    }

    $.ajax({
        url     :   "/c/page/console/auth/coinMall/exchangeList",
        type    :   "POST",
        dataType:   "JSON",
        data    :   {exchangeStatus:exchangeStatus,pageSize:pageSize,pageNum:pageNum},
        success :   function(data){

            if (data.success) {

                var details = data.bean.details;
                var pageNow = data.bean.pageNow ;
                var totalPage = data.bean.totalPage ;
                if (totalPage == '0'){
                    pageNow = 0;
                }
                $("#pageNow").text(pageNow);
                $("#totalPage").text(totalPage);

                var body = $("#redBody");
                body.empty();
                var newTbody = "";
                for (var i= 0;i<details.length ;i ++) {

                    newTbody +=
                        "<tr>"+
                        "    <td><input type='checkbox' value='"+details[i].id+"'></td>"+
                        "    <td>"+details[i].userName+"</td>"+
                        "    <td>"+details[i].phone+"</td>"+
                        "    <td>"+details[i].goodsName+"</td>"+
                        "    <td>"+details[i].address+"</td>"+
                        "    <td>"+details[i].exchangeTime+"</td>"+
                        "    <td>"+details[i].sendTime+"</td>";

                    if (details[i].exchangeStatus == '0'){
                        newTbody += "    <td style='color: green;'>未发货</td>";
                    }else if(details[i].exchangeStatus == '1') {
                        newTbody += "    <td style='color: #00a0e9;'>已发货</td>";
                    }else if(details[i].exchangeStatus == '2') {
                        newTbody += "    <td style='color: red;'>已作废</td>";
                    }
                    newTbody += "</tr>";
                }
                body.append(newTbody);
            }else{
                tips.err("操作失败",2000);
            }

        },
        error   :   function () {

        }
    });
}

/**
 * 下一页
 */
$(document).on("click","#pageAfter",function () {

    var pageNow = parseInt($("#pageNow").text());
    var totalPage = parseInt($("#totalPage").text());
    if (pageNow == totalPage) {
        tips.err("当前是最后一页",2000);
        return;
    }
    turnPage(pageNow+1);


});
/**
 * 上一页
 */
$(document).on("click","#pageBefore",function () {

    var pageNow = parseInt($("#pageNow").text());
    if (pageNow == 1) {
        tips.err("当前是第一页",2000);
        return;
    }
    turnPage(pageNow-1);
});

/**
 * 点击作废
 */
$(document).on("click","#invalid",function () {

    var $input = $("input[type=checkbox]:checked");
    if ($input == null ||$input.length == 0||$input.length>1 ) {
        tips.err("必须且只能选择一条纪录",2000);
        return;
    }
    var id = $input.val();

    tips.loading();

    $.ajax({
        url     :   "/c/page/console/auth/coinMall/invalid",
        type    :   "POST",
        dataType:   "JSON",
        data    :   {id:id},
        success :   function (data) {

            if (data.success){
                tips.suc("操作成功",2000);

                setTimeout(function () {
                    window.location.href = "/c/page/console/auth/coinMall/exchangeListPage";
                },2000);

            }else {
                tips.err(data.msg,2000);
            }
        },
        error   :   function () {

        }
    });
});

/**
 * 弹出兑换输入框
 */
function showExchangeDetail() {

    var $input = $("input[type=checkbox]:checked");
    if ($input == null ||$input.length == 0||$input.length>1 ) {
        tips.err("必须且只能选择一条纪录",2000);
        return;
    }
    var id = $input.val();

    $.ajax({
        url     :   "/c/page/console/auth/coinMall/getDetail",
        type    :   "POST",
        dataType:   "JSON",
        data    :   {id:id},
        success :   function (data) {

            if (data.success){

                var detail = data.bean.vo;
                var goodName = detail.goodsName;
                var userName = detail.userName;
                var address = detail.address;
                var phone = detail.phone;
                var time = detail.exchangeTime;
                var score = detail.score;
                var cardId = detail.cardId;
                var cardPW = detail.cardPassword;
                var expressNum = detail.expressNumber;
                var detailId = detail.id;
                var type = detail.type;
                var exchangeStatus = detail.exchangeStatus;
                var status = detail.exchangeStatus;

                if (status == 1) {
                    tips.err("此兑换记录已经发货",5000);
                    return;
                }
                if (status == 2) {
                    tips.err("此兑换记录已经作废",5000);
                    return;
                }
                
                $("#goods_name").val(goodName);
                $("#user_name").val(userName);
                $("#exchange_time").val(time);
                $("#score").val(score);
                $("#card_id").val(cardId);
                $("#card_password").val(cardPW);
                $("#express_number").val(expressNum);
                $("#detail_id").val(detailId);
                $("#address").val(address);
                $("#phone").val(phone);


                $("#type").val(type);
                $("#status").val(exchangeStatus);

                if (type == '0'){ //实物,没有卡号跟卡密

                    $("#card_id").attr("disabled",true);
                    $("#card_password").attr("disabled",true);
                    $("#express_number").attr("disabled",false);

                }else if (type == '1'){//虚拟,没有快递单号

                    $("#express_number").attr("disabled",true);
                    $("#card_id").attr("disabled",false);
                    $("#card_password").attr("disabled",false);
                }


                $("#add").click();
                

            }else {
                tips.err(data.msg,2000);
            }
        },
        error   :   function () {

        }
    });
}

/**
 * 确认兑换
 */
function exchange() {

    var id = $("#detail_id").val();
    var cardId = $("#card_id").val();
    var cardPW = $("#card_password").val();
    var expressNum = $("#express_number").val();
    var invalidTime = $("#invalid_time").val();

    $.ajax({
        url     :   "/c/page/console/auth/coinMall/exchange",
        type    :   "POST",
        dataType:   "JSON",

        data    :   {id:id,cardId:cardId,cardPassword:cardPW,expressNumber:expressNum,invalidTime:invalidTime},
        success :   function (data) {

            if (data.success){
                tips.suc(data.msg,2000);

                setTimeout(function () {
                    window.location.href = "/c/page/console/auth/coinMall/exchangeListPage";
                },2000);

            }else {
                tips.err(data.msg,2000);
            }
        },
        error   :   function () {

        }
    });









}

