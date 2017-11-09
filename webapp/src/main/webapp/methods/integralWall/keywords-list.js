/**
 * 点击保存,添加
 */
function addKeyword() {

    var word = $("#add_word").val().trim();
    var releaseTime = $("#add_release_time").val().trim();
    var totalNum = $("#add_total_num").val().trim();
    var leftNum = $("#add_left_num").val().trim();
    var label = $("#add_label").val().trim();
    var money = $("#add_money").val().trim();
    var appId = $("#appId").val().trim();


    $.ajax({
        url     :   "/c/page/console/auth/keyword/save",
        type    :   "POST",
        dataType:   "JSON",
        data    :   {appId:appId,keyword:word,totalNum:totalNum,leftNum:leftNum,taskLabel:label,releaseTime:releaseTime,keywordStatus:status,money:money},
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
 * 点击修改,弹出修改框
 */
function editWord() {

    var $input = $("input[type=checkbox]:checked");
    if ($input == null ||$input.length == 0||$input.length>1 ) {
        tips.err("必须且只能选择一条纪录",2000);
        return;
    }
    var id = $input.val();

    $.ajax({
        url     :   "/c/page/console/auth/keyword/edit",
        type    :   "POST",
        dataType:   "JSON",
        data    :   {id:id},
        success :   function (data) {

            if (data.success){
                var detail = data.bean;

                var keywordId = detail.keywordId;
                var keyword = detail.keyword;
                var totalNum = detail.totalNum;
                var leftNum = detail.leftNum;
                var label = detail.taskLabel;
                var releaseTime = detail.releaseTime;
                var money = detail.money;

                $("#edit_word").val(keyword);
                $("#edit_release_time").val(releaseTime);
                $("#edit_total_num").val(totalNum);
                $("#edit_left_num").val(leftNum);
                $("#edit_money").val(money);
                $("#edit_label").val(label);
                $("#keywordId").val(keywordId);

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
function doEditWord() {

    var word = $("#edit_word").val().trim();
    var releaseTime = $("#edit_release_time").val().trim();
    var totalNum = $("#edit_total_num").val().trim();
    var leftNum = $("#edit_left_num").val().trim();
    var money = $("#edit_money").val().trim();
    var label = $("#edit_label").val().trim();
    var keywordId = $("#keywordId").val().trim();
    
    $.ajax({
        url     :   "/c/page/console/auth/keyword/save",
        type    :   "POST",
        dataType:   "JSON",
        data    :   {keywordId:keywordId,keyword:word,totalNum:totalNum,leftNum:leftNum,money:money,releaseTime:releaseTime,taskLabel:label},
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
function deleteWord() {


    var $input = $("input[type=checkbox]:checked");
    if ($input == null ||$input.length == 0||$input.length>1 ) {
        tips.err("必须且只能选择一条纪录",2000);
        return;
    }
    var id = $input.val();
    if (confirm("是否要删除此记录?")){

        $.ajax({
            url     :   "/c/page/console/auth/keyword/remove",
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




