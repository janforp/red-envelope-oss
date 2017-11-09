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
    if (type == "") {
        $("#wxDiv").show();
        $("#nameDiv").show();
    }
});


var pageNum = 1;
//查询推广列表
function search(page) {
    if(page != null){
        pageNum = page;
    }
    var customerType= $("#type").val();
    var customerWx = $("#customerWx").val();
    var customerName=$("#customerName").val();
    if (customerWx != null && customerWx.trim().length == 0) {
        customerWx = "";
    }
    if (customerName != null && customerName.trim().length == 0) {
        customerName = "";
    }
    var status = $("#status").val();

    tips.loading();

    $.ajax({
        url     :   "/c/page/console/auth/extend/getData",
        type    :   "POST",
        dataType:   "JSON",
        data    :   {
            pageNum: pageNum,
            type:customerType,
            customerWx: customerWx,
            customerName: customerName,
            status:status
        },
        success :   function (data) {

            if (data.success == true) {

                var total = data.bean.totalPage;
                var list = data.bean.list;

                var code =  ""

                for(var i=0; i<list.length; i++) {
                    code += "<tr>" +
                        "   <td><input type='checkbox' value='"+list[i].id+"' customerId='"+list[i].customerId+"' status = '"+list[i].customStatusDesc+"' showTime = '"+list[i].showStartTime+"'/></td>" +
                        "   <td>"+list[i].id+"</td>" +
                        // "   <td>"+list[i].customerId+"</td>" +
                        "   <td>"+list[i].customerName+"</td>" +
                        "   <td>"+list[i].isRedirectShow+"</td>" +
                        // "   <td>"+list[i].redirectUrl+"</td>" +
                        "   <td>"+list[i].waitTime+"</td>" +
                        // "   <td>"+list[i].extendDesc+"</td>" +
                        "   <td><img src ='"+list[i].customerBanner+"' /></td>" +
                        // "   <td>"+list[i].customerBannerUrl+"</td>" +
                        "   <td><img src ='"+list[i].customerAdvert+"' /></td>" +
                        // "   <td>"+list[i].customerAdvertUrl+"</td>" +
                        "   <td>"+list[i].redNumTotal+"</td>" +
                        "   <td>"+list[i].redNumLeft+"</td>"+
                        // "   <td>"+list[i].redNumDayTotal+"</td>" +
                        // "   <td>"+list[i].redNumDayLeft+"</td>"+
                        "   <td>"+list[i].customStatusDesc+"</td>" +
                        "   <td>"+list[i].showStartTime+"</td>"+
                        "   <td>"+list[i].showEndTime+"</td>"+
                        "   <td>"+list[i].isHotDesc+"</td>"+
                        "   <td>"+list[i].redChance+"</td>"+
                        "</tr>"
                }
                $("#tbody").html(code);

                var foot = ""

                foot += "<tr>" +
                    "   <td colspan='14'>" +
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


//针对某个推广 添加 红
$(document).on("click","#addRed",function () {

    var $input = $("input[type='checkbox']:checked");
    if ($input == null || $input.length == 0) {
        tips.err("请选择一个推广",2000);
        return;
    }
    if ($input.length >1){
        tips.err("只能选择一个推广",2000);
        return;
    }

    var id = $input.val();
    var status = $input.attr("status");
    if (status != '进行中'){
        tips.err("只有进行中的推广才能添加红包",2000);
        return;
    }
    window.open("/c/page/console/auth/red/addRedPage?id="+id);
});

//点击某个推广查看详情
$(document).on("click","#detail",function () {

    var $input = $("input[type = 'checkbox']:checked");
    if($input == null || $input.length ==0){
        tips.err("请选择一条",2000);
        return;
    }
    if ($input.length >1){
        tips.err("只能请选择一条",2000);
        return;
    }
    var id = $input.val();

    window.open("/c/page/console/auth/extend/detail?id="+id);
});


//针对某个推广  修改添加或修改步骤
$(document).on("click","#step",function () {

    var $input = $("input[type='checkbox']:checked");
    if ($input == null || $input.length == 0) {
        tips.err("请选择一个推广",2000);
        return;
    }
    if ($input.length >1){
        tips.err("只能选择一个推广",2000);
        return;
    }

    var id = $input.val();

    window.location.href = ("/c/page/console/auth/step/stepPage?id="+id);
});


//针对某个推广  解锁 红包
$(document).on("click","#unlock",function () {

    var $input = $("input[type='checkbox']:checked");
    if ($input == null || $input.length == 0) {
        tips.err("请选择一个推广",2000);
        return;
    }
    if ($input.length >1){
        tips.err("只能选择一个推广",2000);
        return;
    }

    var id = $input.val();
    var status = $input.attr("status");

    if (status != '进行中'){
        tips.err("只有进行中的推广才能解锁红包",2000);
        return;
    }

    window.open("/c/page/console/auth/red/unlockRedPage?id="+id);
});

//针对某个推广  删除
$(document).on("click","#delete",function () {

    var $input = $("input[type='checkbox']:checked");
    if ($input == null || $input.length == 0) {
        tips.err("请选择一个推广",2000);
        return;
    }
    var ids = $("input[type='checkbox']:checked").map(function(index,elem) {
        return $(elem).val();
    }).get().join('&');
    tips.err("删除功能暂时不开放",2000);
    return;
    $.ajax({

        url     :   "/c/page/console/auth/extend/delete",
        type    :   "POST",
        dataType:   "JSON",
        data    :   {
            idsStr:ids
        },
        success :   function (data) {
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
});




//针对某个推广  修改
$(document).on("click","#update",function () {

    var $input = $("input[type='checkbox']:checked");
    if ($input == null || $input.length == 0) {
        tips.err("请选择一个推广",2000);
        return;
    }
    if ($input.length >1){
        tips.err("只能选择一个推广",2000);
        return;
    }

    var id = $input.val();

    window.location.href = ("/c/page/console/auth/extend/updateExtendPage?id="+id);
});


/**
 * 查看效果
 */
function  showExtendInApp() {

    var $input = $("input[type='checkbox']:checked");
    if ($input == null || $input.length == 0) {
        tips.err("请选择一个推广",2000);
        return;
    }
    if ($input.length >1){
        tips.err("只能选择一个推广",2000);
        return;
    }

    var id = $input.val();

    window.open(" http://hb.lswuyou.cn/c/p/extend/extendDetail/"+id);
}

