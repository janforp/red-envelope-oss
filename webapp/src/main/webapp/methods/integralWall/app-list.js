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


    var appName = $("#appName").val();
    var marketId = $("#market").val();
    var packageName = $("#packageName").val();

    var pageSize = $("#pageSize").val().trim();

    if (parseInt(pageSize) != pageSize && pageSize != ""){

        pageSize = 10 ;
    }

    $.ajax({
        url     :   "/c/page/console/auth/app/list",
        type    :   "POST",
        dataType:   "JSON",
        data    :   {name:appName,marketId:marketId,packageName:packageName,pageNum:1,pageSize:pageSize},
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
                        "    <td><input type='checkbox' value='"+details[i].appId+"'></td>"+
                        "    <td>"+(i+1)+"</td>"+
                        "    <td>"+details[i].appName+"</td>"+
                        "    <td><img src='"+details[i].appIcon+"'></td>"+
                        "    <td>"+details[i].marketName+"</td>"+
                        "    <td>"+details[i].appPackage+"</td>"+
                        "    <td>"+details[i].appSize+"</td></tr>";
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


    var appName = $("#appName").val();
    var marketId = $("#market").val();
    var packageName = $("#packageName").val();

    var pageSize = $("#pageSize").val().trim();

    if (parseInt(pageSize) != pageSize && pageSize != ""){

        pageSize = 10 ;
    }

    $.ajax({
        url     :   "/c/page/console/auth/app/list",
        type    :   "POST",
        dataType:   "JSON",
        data    :   {name:appName,marketId:marketId,packageName:packageName,pageNum:pageNum,pageSize:pageSize},
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
                        "    <td><input type='checkbox' value='"+details[i].appId+"'></td>"+
                        "    <td>"+(i+1)+"</td>"+
                        "    <td>"+details[i].appName+"</td>"+
                        "    <td><img src='"+details[i].appIcon+"'></td>"+
                        "    <td>"+details[i].marketName+"</td>"+
                        "    <td>"+details[i].appPackage+"</td>"+
                        "    <td>"+details[i].appSize+"</td></tr>";
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
 * 点击保存,添加
 */
function addApp() {

    var appName = $("#add_name").val().trim();
    var marketId = $("#add_market").val().trim();
    var appPackage = $("#add_package").val().trim();
    var appDesc = $("#add_desc").val().trim();
    var appSize = $("#add_size").val().trim();

    var appIcon = $("#add_img").val().trim();

    $.ajax({
        url     :   "/c/page/console/auth/app/save",
        type    :   "POST",
        dataType:   "JSON",
        data    :   {appName:appName,marketId:marketId,appPackage:appPackage,appIcon:appIcon,appDesc:appDesc,appSize:appSize},
        success :   function (data) {

            if (data.success){
                tips.suc("保存成功");

                $("#add_name").val("");
                $("#add_market").val("");
                $("#add_package").val("");
                $("#add_icon").val("");
                $("#add_desc").val("");
                $("#add_size").val("");
                $("#appId").val("");

                window.location.reload();

            }else {
                tips.err(data.msg,2000);
            }
        },
        error   :   function () {

        }
    });
}





/**
 * 点击修改,弹出修改框
 */
function editApp() {

    var $input = $("input[type=checkbox]:checked");
    if ($input == null ||$input.length == 0||$input.length>1 ) {
        tips.err("必须且只能选择一条纪录",2000);
        return;
    }
    var id = $input.val();

    $.ajax({
        url     :   "/c/page/console/auth/app/edit",
        type    :   "POST",
        dataType:   "JSON",
        data    :   {id:id},
        success :   function (data) {

            if (data.success){
                var detail = data.bean;

                var appId = detail.appId;
                var appName = detail.appName;
                var appIcon = detail.appIcon;
                var packageName = detail.appPackage;
                var market = detail.appMarket;
                var size = detail.appSize;
                var desc = detail.appDesc;

                $("#edit_name").val(appName);
                $("#edit_market").val(market);
                $("#edit_package").val(packageName);
                $("#edit_desc").val(desc);
                $("#edit_size").val(size);
                $("#appId").val(appId);

                $("#oldImg").attr("src",appIcon);

                $("#edit").click();

            }else {
                tips.err(data.msg,2000);
            }
        },
        error   :   function () {

        }
    });
}

/**
 * 点击 修改框下的保存 按钮
 */
function doEditApp() {

    var appName = $("#edit_name").val().trim();
    var marketId = $("#edit_market").val().trim();
    var appPackage = $("#edit_package").val().trim();
    
    var appDesc = $("#edit_desc").val().trim();
    var appSize = $("#edit_size").val().trim();
    var appId=$("#appId").val().trim();


    var appIcon = $("#edit_img").val().trim();
    if (appIcon==""||appIcon==null||appIcon==undefined){
        appIcon = $("#oldImg").attr("src");
    }



    $.ajax({
        url     :   "/c/page/console/auth/app/save",
        type    :   "POST",
        dataType:   "JSON",
        data    :   {appId:appId,appName:appName,marketId:marketId,appPackage:appPackage,appIcon:appIcon,appDesc:appDesc,appSize:appSize},
        success :   function (data) {

            if (data.success){
                tips.suc("保存成功");
                window.location.reload();

            }else {
                tips.err(data.msg,2000);
            }
        },
        error   :   function () {

        }
    });
}


/**
 * 点击删除
 */
function deleteApp() {


    var $input = $("input[type=checkbox]:checked");
    if ($input == null ||$input.length == 0||$input.length>1 ) {
        tips.err("必须且只能选择一条纪录",2000);
        return;
    }
    var id = $input.val();
    if (confirm("是否要删除此记录?")){

        $.ajax({
            url     :   "/c/page/console/auth/app/remove",
            type    :   "POST",
            dataType:   "JSON",
            data    :   {ids:id},
            success :   function (data) {

                if (data.success){
                    tips.suc("删除成功");
                    window.location.reload();

                }else {
                    tips.err(data.msg,2000);
                }
            },
            error   :   function () {

            }
        });
    }
}

/**
 * 显示此appde关键词记录
 */
function showKeywords() {

    var $input = $("input[type=checkbox]:checked");
    if ($input == null ||$input.length == 0||$input.length>1 ) {
        tips.err("必须且只能选择一条纪录",2000);
        return;
    }
    var id = $input.val();

    window.location.href = "/c/page/console/auth/keyword/keywordList?id="+id;
}






