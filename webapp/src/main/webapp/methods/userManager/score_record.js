/**
 * 根据用户输入,觉得每页显示的行数
 */
$(document).ready(function(){


    var userId = $("#userId").val();

    $.ajax({
        url     :   "/c/page/console/auth/score/list",
        type    :   "POST",
        dataType:   "JSON",
        data    :   {userId:userId,pageNum:1},

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
                        "    <td><input type='checkbox' value='"+details[i].scoreId+"'></td>"+
                        "    <td>"+(i+1)+"</td>"+
                        "    <td>"+details[i].scoreTime+"</td>"+
                        "    <td>"+details[i].scoreContent+"</td>";
                    if (details[i].scoreType == '0'){

                        newTbody +=  "    <td>-"+details[i].score+"</td>";

                    }else {
                        newTbody +=  "    <td>+"+details[i].score+"</td>";
                    }

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

    var userId = $("#userId").val();

    $.ajax({
        url     :   "/c/page/console/auth/score/list",
        type    :   "POST",
        dataType:   "JSON",
        data    :   {userId:userId,pageNum:pageNum},

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
                        "    <td><input type='checkbox' value='"+details[i].scoreId+"'></td>"+
                        "    <td>"+(i+1)+"</td>"+
                        "    <td>"+details[i].scoreTime+"</td>"+
                        "    <td>"+details[i].scoreContent+"</td>";
                    if (details[i].scoreType == '0'){

                        newTbody +=  "    <td>-"+details[i].score+"</td>";

                    }else {
                        newTbody +=  "    <td>+"+details[i].score+"</td>";
                    }

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
 * 拉黑用户
 */
function pullBlack() {

    var $input = $("input[type=checkbox]:checked");
    if ($input == null ||$input.length == 0||$input.length>1 ) {
        tips.err("必须且只能选择一条纪录",2000);
        return;
    }
    var id = $input.val();

    if (confirm("是否将此用户拉至黑名单?")){

        $.ajax({
            url     :   "/c/page/console/auth/user/pullBlack",
            type    :   "POST",
            dataType:   "JSON",
            data    :   {id:id},
            success:function (data) {

                if (data.success){
                    tips.suc(data.msg);
                    window.location.reload()
                }else {
                    tips.err(data.msg,2000);
                }

            }
        });
    }
}


/**
 * 现金记录
 */
function moneyRecords() {

    var $input = $("input[type=checkbox]:checked");
    if ($input == null ||$input.length == 0||$input.length>1 ) {
        tips.err("必须且只能选择一条纪录",2000);
        return;
    }
    var id = $input.val();

    window.location.href = '/c/page/console/auth/money/moneyList?id='+id;
}


/**
 * 金币记录
 */
function scoreRecords() {

    var $input = $("input[type=checkbox]:checked");
    if ($input == null ||$input.length == 0||$input.length>1 ) {
        tips.err("必须且只能选择一条纪录",2000);
        return;
    }
    var id = $input.val();

    window.location.href = '/c/page/console/auth/score/scoreList?id='+id;

}







