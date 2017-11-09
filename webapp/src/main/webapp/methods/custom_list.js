
$(document).ready(function () {
    $("#search").click();
});

//选择类型
$("#type").change(function () {
    $("#tbody").empty();
    var type = $("#type").val();
    if (type == 1) {    //公众号
        $("#wxDiv").show();
        $("#nameDiv").show();
    }
    if (type == 0) {    //普通商户
        $("#wxDiv").hide();
    }
});

//获取32位随机字符串
function getSecret() {

    var secret = "";
    var arr = [ '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j',
                'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't',
                'u', 'v', 'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D',
                'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N',
                'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X',
                'Y', 'Z'];
    for (var i =0 ; i<32 ;i ++) {
        var random = Math.round(Math.random()*(arr.length - 1));
        secret += arr[random];
    }
    var $add = $("#add_secret");
    var $update = $("#update_secret");
    if ($add.is(':hidden')){
        $("#update_secret").val(secret);
    }
    if ($update.is(':hidden')){
        $("#add_secret").val(secret);
    }
}

//选择类型
$("#add_type").change(function () {

    var type = $("#add_type").val();
    if (type == 1) {    //公众号
        $("#add_show_wx").show();
        $("#add_show_token_key").show();
        $("#add_show_appid").show();
        $("#add_show_sendType").show();
        $("#update_mode_div").show();
    }
    if (type == 0) {    //普通商户
        $("#add_show_wx").hide();
        $("#add_show_token_key").hide();
        $("#add_show_appid").hide();
        $("#add_show_sendType").hide();
        $("#update_mode_div").hide();
    }
});

function closeDiv() {
    $("#addDiv").hide();
    $("#updateDiv").hide();
};
function closeDiv2() {
    $("#updateDiv").hide();
};
//点击'添加'按钮,弹出添加框
$(document).on("click","#add",function () {

    $("#addDiv").show();
});

//点击 'x'关闭添加/修改框
$(document).on("click",".close",function () {

    $("#addDiv").hide();
    $("#updateDiv").hide();
});


//删除
$(document).on("click","#delete",function () {

    var $input = $("input[type = 'checkbox']:checked");
    if($input.length == 0 || $input == null) {
        tips.err("请选择要删除的纪录",2000);
        return;
    }
    var customerIds = $("input[type='checkbox']:checked").map(function(index,elem) {
        return $(elem).attr("customerId");
    }).get().join('&');

    $.ajax({
        url     :       "/c/page/console/auth/Customer/delete",
        type    :       "POST",
        dataType:       "JSON",
        data    :       {
            customerIds:customerIds,
        },
        success :       function(data){

            $input.parent().parent().remove();
            tips.suc(data.msg,2000);
        },
        error   :       function () {
            tips.err("操作失败",2000);
        }
    });
});

//点击添加框上的保存按钮,把数据保存到表中
$(document).on("click","#saveCustom",function () {

    var type = $("#add_type").val();
    var secret = $("#add_secret").val();
    var wx = $("#add_wx").val();
    var name=$("#add_name").val();
    if (type == 1) {

        if (wx ==null ||wx.trim().length == 0){
            $("#add_wx_alert").text("微信号必须填写!");
            setTimeout(function(){$("#add_wx_alert").text("");},2000);
            return;
        }
    }

    //客户名必须填
    if (name ==null ||name.trim().length == 0){
        $("#add_name_alert").text("公众号名称必须填写!");
        setTimeout(function(){$("#add_name_alert").text("");},2000);
        return;
    }
    var token = $("#add_token").val().trim();
    var aeskey= $("#add_aeskey").val().trim();
    var appid = $("#add_appid").val().trim();
    var appsecret= $("#add_appsecret").val().trim();
    var customerImg = $("#add_customerImg").val().trim();
    var customerSendtype= $("#add_customerSendtype").val().trim();
    if (customerSendtype ==null ||customerSendtype.trim().length == 0){
        customerSendtype = "";
    }

    $.ajax({
        url     :       "/c/page/console/auth/Customer/save",
        type    :       "POST",
        dataType:       "JSON",
        data    :       {
            customerType:type,
            customerSecret:secret,
            customerWx:wx,
            customerName:name,
            customerImg:customerImg,
            customerAppid:appid,
            customerAppsecret:appsecret,
            customerToken:token,
            customerAeskey:aeskey,
            customerSendtype:customerSendtype
        },
        success :       function (data) {

            if(data.success) {

                var typeShow = "公众号";
                if(type == 0){
                    typeShow = "普通商户";
                }

                newTr = "<tr>" +
                    "   <td><input type='checkbox' name='custome' value='"+wx+"' customerWx = '"+wx+"'/></td>" +
                    "   <td>"+typeShow+"</td>" +
                    "   <td>"+wx+"</td>" +
                    "   <td>"+name+"</td>" +
                    "   <td><img src ='"+customerImg+"' /></td>" +
                    "   <td>"+appid+"</td>" +
                    "   <td>"+appsecret+"</td>" +
                    "   <td>"+token+"</td>"+
                    "   <td>"+aeskey+"</td>"+
                    "   <td>"+secret+"</td>"+
                    "   <td>刚才</td>";

                if(customerSendtype == '0') {
                    newTr += "<td>明文</td></tr>";
                }
                if(customerSendtype == '1') {
                    newTr += "<td>兼容</td></tr>";
                }
                if(customerSendtype == '2') {
                    newTr += "<td>安全</td></tr>";
                }
                if(customerSendtype == null || customerSendtype == "") {
                    newTr += "<td></td></tr>";
                }

                $("#tbody").append(newTr);

                $("#add_secret").val("");


                $("#add_wx").val("");
                $("#add_name").val("");


                $("#add_token").val("");
                $("#add_aeskey").val("");
                $("#add_appid").val("");
                $("#add_appsecret").val("");
                $("#add_customerImg").val("");
                $("#addthumb2").attr("src","");

                $("#addDiv").hide(1000);
                tips.suc("操作成功",2000);
            }else {
                tips.err(data.msg,2000);
            }
        },
        error   :       function () {


        }
    });
});

//点击修改按钮,弹出修改框
$(document).on("click","#update",function () {

    var $input = $("input[type = 'checkbox']:checked");
    if ($input == null || $input.length == 0) {
        tips.err("请选择一条纪录修改",2000);
        return;
    }
    if ($input.length > 1) {
        tips.err("只能修改一条纪录",2000);
        return;
    }
    var customerId = $input.attr("customerId");

    $.ajax({

        url     :   "/c/page/console/auth/Customer/getUpdateData",
        type    :   "POST",
        dataType:   "JSON",
        data    :   {
            customerId: customerId
        },
        success :   function (data) {

            if(data.success) {
                var vo = data.bean.vo;
                var customerType = vo.customerType;
                $("#updateDiv").attr("customerId",customerId);

                $("#updateDiv").attr("createTime",vo.createTime);

                if(customerType == '0') {  //普通商户

                    $("#updateDiv").show();

                    $("#update_show_wx").hide();
                    $("#update_show_token_aeskey").hide();
                    $("#update_show_appid").hide();
                    $("#update_show_sendType").hide();
                    $("#update_mode_div").hide();

                    $("#update_wx").val("");
                    $("#update_name").val("");
                    $("#update_token").val("");
                    $("#update_aeskey").val("");
                    $("#update_appid").val("");
                    $("#update_appsecret").val("");
                    $("#updatethumb2").attr("src",vo.customerImg);
                    $("#update_type").val(vo.customerType);
                    $("#update_secret").val(vo.customerSecret);
                    $("#update_name").val(vo.customerName);
                }
                if(customerType == '1') {  //公众号

                    $("#update_secret").val(vo.customerSecret);
                    $("#update_wx").val(vo.customerWx);
                    $("#update_name").val(vo.customerName);
                    $("#update_token").val(vo.customerToken);
                    $("#update_aeskey").val(vo.customerAeskey);
                    $("#update_appid").val(vo.customerAppid);
                    $("#update_appsecret").val(vo.customerAppsecret);
                    $("#updatethumb2").attr("src",vo.customerImg);
                    $("#update_mode").val(vo.mode);

                    $("#updateDiv").show();
                }

            }else{
                tips.err(data.msg,2000);
            }
        },
        error   :   function () {
            tips.err("异常",2000);
        }
    });
});


//点击修改框上的保存按钮
$(document).on("click","#updateCustom",function () {

    var type = $("#update_type").val();
    var customerId = $("#updateDiv").attr("customerId");
    var secret = $("#update_secret").val();


    var name=$("#update_name").val();
    var mode = $("#update_mode").val();

    var customerImg = $("#update_customerImg").val().trim();
    if(customerImg == null || customerImg.trim().length ==0 || customerImg == undefined) {
        customerImg = $("#updatethumb2").attr("src");
    }
    
    if(type == 1) {

        var wx = $("#update_wx").val();
        if (wx ==null ||wx.trim().length == 0){
            $("#update_wx_alert").text("微信号必须填写!");
            setTimeout(function(){$("#update_wx_alert").text("");},2000);
            return;
        }

        var token = $("#update_token").val().trim();
        var aeskey= $("#update_aeskey").val().trim();
        var appid = $("#update_appid").val().trim();
        var appsecret= $("#update_appsecret").val().trim();
        var customerImg = $("#update_customerImg").val().trim();
        if(customerImg == null || customerImg.trim().length ==0 || customerImg == undefined) {
            customerImg = $("#updatethumb2").attr("src");
        }

        var customerSendtype = $("#update_customerSendtype").val();

    }


    if (name ==null ||name.trim().length == 0){
        $("#update_name_alert").text("客户名称必须填写!");
        setTimeout(function(){$("#update_name_alert").text("");},2000);
        return;
    }



    //在弹出修改框饰,绑定的createTime,showTime在这里取到
    var showTime = $("#updateDiv").attr("showTime");
    var createTime = $("#updateDiv").attr("createTime");


    $.ajax({
        url     :       "/c/page/console/auth/Customer/update",
        type    :       "POST",
        dataType:       "JSON",
        data    :       {
            customerId:customerId,
            customerSecret:secret,
            customerType:type,
            customerWx:wx,
            customerName:name,
            customerImg:customerImg,
            customerAppid:appid,
            customerAppsecret:appsecret,
            customerToken:token,
            customerAeskey:aeskey,
            customerSendtype:customerSendtype,
            createTime:createTime,
            mode:mode
        },
        success :       function (data) {

            if(data.success) {

                $("#updateDiv").hide(1000);
                tips.suc(data.msg,2000);
            }else {
                tips.err(data.msg,2000);
            }
        },
        error   :       function () {


        }
    });
});


var pageNum = 1;

function search(page) {
    if(page != null){
        pageNum = page;
    }
    var type = $("#type").val();
    var customerWx = $("#customerWx").val();
    var customerName=$("#customerName").val();
    if (customerWx != null && customerWx.trim().length == 0) {
        customerWx = "";
    }
    if (customerName != null && customerName.trim().length == 0) {
        customerName = "";
    }

    tips.loading();

    $.ajax({
        url     :   "/c/page/console/auth/Customer/getData",
        type    :   "POST",
        dataType:   "JSON",
        data    :   {
            pageNum: pageNum,
            customerWx: customerWx,
            customerName: customerName,
            type:type
        },
        success :   function (data) {

            if (data.success == true) {

                var total = data.bean.totalPage;

                var list = data.bean.list;

                var code =  ""

                for(var i=0; i<list.length; i++) {
                    code += "<tr>" +
                        "   <td><input type='checkbox' name='custome' value='"+list[i].customerWx+"' customerId='"+list[i].customerId+"' createTime = '"+list[i].createTime+"' showTime = '"+list[i].showTime+"'/></td>" +
                        "   <td>"+list[i].customerTypeShow+"</td>"+
                        "   <td>"+list[i].customerWx+"</td>" +
                        "   <td>"+list[i].customerName+"</td>" +
                        "   <td><img src ='"+list[i].customerImg+"' /></td>" +
                        "   <td>"+list[i].customerAppid+"</td>" +
                        "   <td>"+list[i].customerAppsecret+"</td>" +
                        "   <td>"+list[i].customerToken+"</td>"+
                        "   <td>"+list[i].customerAeskey+"</td>"+
                        "   <td>"+list[i].customerSecret+"</td>"+
                        "   <td>"+list[i].showTime+"</td>";

                    if(list[i].customerSendtype == '0'){
                        code += "<td>明文</td></tr>";
                    }
                    if(list[i].customerSendtype == '1'){
                        code += "<td>兼容</td></tr>";
                    }
                    if(list[i].customerSendtype == '2'){
                        code += "<td>安全</td></tr>";
                    }
                    if(list[i].customerSendtype == '' || list[i].customerSendtype == null){
                        code += "<td></td></tr>";
                    }
                }

                $("#tbody").html(code);

                var foot = ""

                foot += "<tr>" +
                    "   <td colspan='12'>" +
                    "       <div class='pagination-box pagination-right'>" +
                    "           <ul class='pagination pagination-sm'>" +
                    "               <li " + (pageNum == 1 ? "class='disabled'" : "") + ">" +
                    "                   <a href='" + (pageNum == 1 ? "javascript:;" : "javascript:search("+(pageNum-1)+");") + "' class='btn_prev'>" +
                    "                       <i class='glyphicon glyphicon-chevron-left'></i>" +
                    "                   </a>" +
                    "               </li>" +
                    "               <li>" +
                    "                   <span class='pagination-status'>" + pageNum + "/" + total + "</span>" +
                    "               </li>" +
                    "               <li " + (pageNum == total ? "class='disabled'" : "") + ">" +
                    "                   <a href='" + (pageNum == total ? "javascript:;" : "javascript:search("+(pageNum+1)+");") + "' class='btn_next'>" +
                    "                       <i class='glyphicon glyphicon-chevron-right'></i>" +
                    "                   </a>" +
                    "               </li>" +
                    "           </ul>" +
                    "       </div>" +
                    "   </td>" +
                    "</tr>";

                $("#tfoot").html(foot);

            } else {
                alert(data.msg);
            }
        },

        complete:   function() {
            tips.hideLoading();
        },
        error:      function() {
            tips.hideLoading();
        }

    });
}

