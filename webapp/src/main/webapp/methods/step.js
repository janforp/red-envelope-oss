//点击添加步骤
$(document).on("click","#addStep",function () {

    var $textare = $("textarea");
    var step = $textare.length;
    var newText =   "<tr>"+
        "<td>"+
        "<label style='position: relative; bottom:40px;margin-right: 20px'>"+(step+1)+" :</label>"+
        "<textarea  style='width: 450px;'></textarea>"+
        "<br>"+
        "</td>"+
        "</tr>";

    var $table = $("#step");
    $table.append(newText);
});
//点击移除最后一步
$(document).on("click","#removeLastStep",function () {

    var $tr = $("tr");
    var length = $tr.length;
    $tr[length-1].remove();

});

//保存步骤
function save(extendId) {

    var $text = $("textarea");
    var stepsContent = $text.map(function(index,elem) {
        var content = $(elem).val();
        if (content != null && content != undefined && content.trim().length != 0){

            return $(elem).val();
        }
    }).get().join('&');


    $.ajax({
        url     :       "/c/page/console/auth/step/save",
        type    :       "POST",
        dataType:       'JSON',
        data    :       {stepsContent:stepsContent,id:extendId},
        success :       function (data) {
            if(data.success) {
                tips.suc(data.msg,2000);
            }else{
                tips.err("操作失败",2000);
            }
        },
        error   :       function () {

        }
    });

}
