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

    var pageSize = $("#pageSize").val();

    if (parseInt(pageSize) != pageSize && pageSize != ""){
        $("#pageSizeAlert").text("请输入整数");
        setTimeout(function () {
            $("#pageSizeAlert").text("");
        },2000);
        return;
    }
    var customerWx = $("#customerWx").val();
    var customerName= $("#customerName").val();
    var body = $("#goodsBody");
    body.attr("pageSize",pageSize);
    $.ajax({
        url     :   "/c/page/console/auth/Customer/developModeListByPageSize",
        type    :   "POST",
        dataType:   "JSON",
        data    :   {pageSize:pageSize,customerWx:customerWx,customerName:customerName},
        success :   function(data){

            if (data.success) {

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

                $("#tbody").empty();
                $("#totalPage").text(total);
                $("#pageNow").text(1);
                $("#tbody").html(code);

            }else{
                tips.err("操作失败",2000);
            }

        },
        error   :   function () {

        }
    });
});
/**
 * 在页码输入框中按回车键 也可以执行上面的操作
 */
$("#pageSize").bind("keypress",function (event) {

    if (event.keyCode == "13") {
        var pageSize = $("#pageSize").val();

        if (parseInt(pageSize) != pageSize && pageSize != "") {
            $("#pageSizeAlert").text("请输入整数");
            setTimeout(function () {
                $("#pageSizeAlert").text("");
            }, 2000);
            return;
        }
        var customerWx = $("#customerWx").val();
        var customerName = $("#customerName").val();
        var body = $("#goodsBody");
        body.attr("pageSize", pageSize);
        $.ajax({
            url: "/c/page/console/auth/Customer/developModeListByPageSize",
            type: "POST",
            dataType: "JSON",
            data: {pageSize: pageSize, customerWx: customerWx, customerName: customerName},
            success: function (data) {

                if (data.success) {

                    var total = data.bean.totalPage;

                    var list = data.bean.list;

                    var code = ""

                    for (var i = 0; i < list.length; i++) {
                        code += "<tr>" +
                            "   <td><input type='checkbox' name='custome' value='" + list[i].customerWx + "' customerId='" + list[i].customerId + "' createTime = '" + list[i].createTime + "' showTime = '" + list[i].showTime + "'/></td>" +
                            "   <td>" + list[i].customerTypeShow + "</td>" +
                            "   <td>" + list[i].customerWx + "</td>" +
                            "   <td>" + list[i].customerName + "</td>" +
                            "   <td><img src ='" + list[i].customerImg + "' /></td>" +
                            "   <td>" + list[i].customerAppid + "</td>" +
                            "   <td>" + list[i].customerAppsecret + "</td>" +
                            "   <td>" + list[i].customerToken + "</td>" +
                            "   <td>" + list[i].customerAeskey + "</td>" +
                            "   <td>" + list[i].customerSecret + "</td>" +
                            "   <td>" + list[i].showTime + "</td>";

                        if (list[i].customerSendtype == '0') {
                            code += "<td>明文</td></tr>";
                        }
                        if (list[i].customerSendtype == '1') {
                            code += "<td>兼容</td></tr>";
                        }
                        if (list[i].customerSendtype == '2') {
                            code += "<td>安全</td></tr>";
                        }
                        if (list[i].customerSendtype == '' || list[i].customerSendtype == null) {
                            code += "<td></td></tr>";
                        }
                    }

                    $("#tbody").empty();
                    $("#totalPage").text(total);
                    $("#pageNow").text(1);
                    $("#tbody").html(code);

                } else {
                    tips.err("操作失败", 2000);
                }

            },
            error: function () {

            }
        });
    }
});

/**
 * 翻页
 * @param pageTo 点击之后的页码
 */
function turnPage(pageTo) {


    var pageSize = $("#pageSize").val();

    if (parseInt(pageSize) != pageSize && pageSize != "") {
        $("#pageSizeAlert").text("请输入整数");
        setTimeout(function () {
            $("#pageSizeAlert").text("");
        }, 2000);
        return;
    }
    var customerWx = $("#customerWx").val();
    var customerName = $("#customerName").val();


    $.ajax({
        url: "/c/page/console/auth/Customer/developModeListByPageSize",
        type: "POST",
        dataType: "JSON",
        data: {pageSize: pageSize, customerWx: customerWx, customerName: customerName,pageNum:pageTo},
        success: function (data) {

            if (data.success) {

                var total = data.bean.totalPage;

                var list = data.bean.list;

                var code = ""

                for (var i = 0; i < list.length; i++) {
                    code += "<tr>" +
                        "   <td><input type='checkbox' name='custome' value='" + list[i].customerWx + "' customerId='" + list[i].customerId + "' createTime = '" + list[i].createTime + "' showTime = '" + list[i].showTime + "'/></td>" +
                        "   <td>" + list[i].customerTypeShow + "</td>" +
                        "   <td>" + list[i].customerWx + "</td>" +
                        "   <td>" + list[i].customerName + "</td>" +
                        "   <td><img src ='" + list[i].customerImg + "' /></td>" +
                        "   <td>" + list[i].customerAppid + "</td>" +
                        "   <td>" + list[i].customerAppsecret + "</td>" +
                        "   <td>" + list[i].customerToken + "</td>" +
                        "   <td>" + list[i].customerAeskey + "</td>" +
                        "   <td>" + list[i].customerSecret + "</td>" +
                        "   <td>" + list[i].showTime + "</td>";

                    if (list[i].customerSendtype == '0') {
                        code += "<td>明文</td></tr>";
                    }
                    if (list[i].customerSendtype == '1') {
                        code += "<td>兼容</td></tr>";
                    }
                    if (list[i].customerSendtype == '2') {
                        code += "<td>安全</td></tr>";
                    }
                    if (list[i].customerSendtype == '' || list[i].customerSendtype == null) {
                        code += "<td></td></tr>";
                    }
                }

                $("#tbody").empty();
                $("#totalPage").text(total);
                $("#pageNow").text(pageTo);
                $("#tbody").html(code);

            } else {
                tips.err("操作失败", 2000);
            }

        },
        error: function () {

        }
    });
}

/**
 * 上一页
 */
$("#pageBefore").click(function () {

    var pageNow = $("#pageNow").text();
    if (pageNow == 1 || pageNow == 0) {
        tips.err("当前是第一页",2000);
        return;
    }
    var now = parseInt(pageNow);

    turnPage(now - 1);
});

/**
 * 下一页
 */
$("#pageAfter").click(function () {

    var totalPage = $("#totalPage").text();
    var pageNow = $("#pageNow").text();
    if (pageNow == totalPage || pageNow == 0) {
        tips.err("当前是最后一页",2000);
        return;
    }
    var now = parseInt(pageNow);

    turnPage(now + 1);
});


/**
 * 针对某个微信号 设置他的 菜单
 */
function setMenu() {

    var $input = $("input[type=checkbox]:checked");
    if ($input == null ||$input.length == 0||$input.length>1 ) {
        tips.err("必须且只能选择一条纪录",2000);
        return;
    }

    var customerId = $input.attr("customerId");

    tips.loading();
    
    $.ajax({
        url:"/c/page/console/auth/develop/setMenu",
        type:"POST",
        dataType:"JSON",
        data:{customerId:customerId},
        success:function (data) {
            if (data.success){
                tips.suc("操作成功");
            }else {
                tips.err("操作失败");
            }
        },
        complete: function() {
            tips.hideLoading();
        },
        error: function() {
            tips.hideLoading();
        }
    });
    

}


/**
 * 针对某个微信号 设置他的 自动回复
 */
function setAutoReply() {

    var $input = $("input[type=checkbox]:checked");
    if ($input == null ||$input.length == 0||$input.length>1 ) {
        tips.err("必须且只能选择一条纪录",2000);
        return;
    }

    var customerId = $input.attr("customerId");

    tips.loading();

    $.ajax({
        url:"/c/page/console/auth/develop/setAutoReply",
        type:"POST",
        dataType:"JSON",
        data:{customerId:customerId},
        success:function (data) {
            if (data.success){
                tips.suc("操作成功");
            }else {
                tips.err("操作失败");
            }
        },
        complete: function() {
            tips.hideLoading();
        },
        error: function() {
            tips.hideLoading();
        }
    });


}


/**
 * 针对某个微信号 清空自动回复
 */
function clearAutoReply() {

    var $input = $("input[type=checkbox]:checked");
    if ($input == null ||$input.length == 0||$input.length>1 ) {
        tips.err("必须且只能选择一条纪录",2000);
        return;
    }

    var customerId = $input.attr("customerId");

    tips.loading();

    $.ajax({
        url:"/c/page/console/auth/develop/clearAutoReply",
        type:"POST",
        dataType:"JSON",
        data:{customerId:customerId},
        success:function (data) {
            if (data.success){
                tips.suc("操作成功");
            }else {
                tips.err("操作失败");
            }
        },
        complete: function() {
            tips.hideLoading();
        },
        error: function() {
            tips.hideLoading();
        }
    });


}

/**
 * 针对某个微信号 清空自动回复
 */
function rebuildAutoReply() {

    tips.loading();

    $.ajax({
        url:"/c/page/console/auth/develop/rebuildAutoReply",
        type:"POST",
        dataType:"JSON",
        data:{},
        success:function (data) {
            if (data.success){
                tips.suc("操作成功");
            }else {
                tips.err("操作失败");
            }
        },
        complete: function() {
            tips.hideLoading();
        },
        error: function() {
            tips.hideLoading();
        }
    });


}

/**
 * 重新初始化微信公众号配置缓存Map
 */
function rebuildWxCache() {

    tips.loading();

    $.ajax({
        url:"/c/page/console/auth/develop/rebuildWxCache",
        type:"POST",
        dataType:"JSON",
        data:{},
        success:function (data) {
            if (data.success){
                tips.suc("操作成功");
            }else {
                tips.err("操作失败");
            }
        },
        complete: function() {
            tips.hideLoading();
        },
        error: function() {
            tips.hideLoading();
        }
    });


}