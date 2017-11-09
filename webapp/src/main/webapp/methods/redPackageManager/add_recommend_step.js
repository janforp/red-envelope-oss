//点击添加步骤
$(document).on("click","#addStep",function () {

    var $textare = $("textarea");
    var step = $textare.length;
    var newText =
    "<tr>"+
    "<td  style='display:flex;justify-content: flex-start;align-items: center'>"+
    "<label style='position: relative; bottom:40px;margin-right: 25px'>"+(step+1)+":</label>"+

    "<div style='margin-right: 1rem'>"+
    "    <select id='step_status' name='step_status'>"+
    "       <option value='2'>全部开启</option>"+
    "       <option value='1'>andriod开启</option>"+
    "       <option value='0'>ios开启</option>"+
    "       <option value='3'>全部关闭</option>"+
    "</select>"+
    "</div>"+

    "步骤:<input type='number' style='margin-right: 1rem;margin-left: 1rem'>"+

    "<textarea  style='width: 450px;margin-right: 2rem;display: inline-block'></textarea>"+
    // "    <br>"+
    "    <div style='width: 100px;height: 100px; margin: 20px;display: inline-block'>"+
    "       <span class='closeImg'>&times;</span>"+
    "       <img class='img-upload' src='' mode='button-upload-pic' style='width: 100px;height: 100px; margin-top: -23px; margin-right: -10px' action='/c/p/picture/upload' filesizelimit='128000' filetypelimit='jpg,png,gif,jpeg' obj='"+Math.random().toString()+"' value='' >"+
    "    </div>"+

    "    <div style='width: 100px;height: 100px; margin: 20px;display: inline-block'>"+
    "       <span class='closeImg'>&times;</span>"+
    "       <img class='img-upload' src='' mode='button-upload-pic' style='width: 100px;height: 100px; margin-top: -23px; margin-right: -10px' action='/c/p/picture/upload' filesizelimit='128000' filetypelimit='jpg,png,gif,jpeg' obj='"+Math.random().toString()+"' value='' >"+
    "    </div>"+

    "    <div style='width: 100px;height: 100px; margin: 20px;display: inline-block'>"+
    "       <span class='closeImg'>&times;</span>"+
    "       <img class='img-upload' src='' mode='button-upload-pic' style='width: 100px;height: 100px; margin-top: -23px; margin-right: -10px' action='/c/p/picture/upload' filesizelimit='128000' filetypelimit='jpg,png,gif,jpeg' obj='"+Math.random().toString()+"' value='' >"+
    "    </div>"+

    "    <div style='width: 100px;height: 100px; margin: 20px;display: inline-block'>"+
    "       <span class='closeImg'>&times;</span>"+
    "       <img class='img-upload' src='' mode='button-upload-pic' style='width: 100px;height: 100px; margin-top: -23px; margin-right: -10px' action='/c/p/picture/upload' filesizelimit='128000' filetypelimit='jpg,png,gif,jpeg' obj='"+Math.random().toString()+"' value='' >"+
    "    </div>"+
    "   <div class='buttonDiv' style='display: inline-block'>"+
    // "       <button>复制关键词<span class='close deleteBtn'>&times;</span></button>"+
    "   </div>"+
    "    </td>"+
    "    </tr>";

    var $table = $("#step");
    $table.append(newText);
    //手动初始化一下,否则不能绑定方法
    initupload($(document));
});
//点击移除最后一步
$(document).on("click","#removeLastStep",function () {

    var $tr = $("tr");
    var length = $tr.length;
    $tr[length-1].remove();

});

//保存步骤
function save() {
    
    var $stepTrs = $("tr");

    var totalStep = "[";

    for (var i=0;i<$stepTrs.length;i++){

        //1.每一步的tr
        var oneStepTr = $stepTrs[i];
        //2.找到每一步的textarea
        var oneTextarea = $(oneStepTr).find("textarea");
        var content = $(oneTextarea).val();
        //3.找到每一步的btn
        var oneBtns = $(oneStepTr).find(".buttonDiv");
        var btnsHtml = $(oneBtns).html().trim();


        //3.找到imgs
        var imgs = $(oneStepTr).find("img");
        //每一步的4张图片的集合,用';'隔开
        var imgUrl ="";
        for (var j=0;j<imgs.length;j++){

            var aImg = imgs[j];
            //每一步的每一张图片
            var oneImgUrl = $(aImg).attr("value");
            if (oneImgUrl != "" && oneImgUrl != null && oneImgUrl != undefined) {

                if (imgUrl == ""){

                    imgUrl = oneImgUrl;
                }else {
                    imgUrl = imgUrl+";"+oneImgUrl;
                }
            }
        }
        //4.找到每一步的步骤<input type='number'>
        var stepNumInput = $(oneStepTr).find("input[type=number]");
        var stepNum = $(stepNumInput).val().trim();
        if(stepNum == "" ||stepNum == null || stepNum ==undefined){
           alert('请输入步骤序号');return;
        }

        //5.找打每一步是属于哪个平台的
        var statusSelect = $(oneStepTr).find("select[name=step_status]");
        var stepStatus = $(statusSelect).val();

        var map = {
            'stepNum':stepNum,
            'stepContent':content,
            'stepImgs':imgUrl,
            'stepBtn':btnsHtml,
            'stepStatus':stepStatus
        };
        var stepOne = JSON.stringify(map);
        totalStep = totalStep +stepOne;
    }
    totalStep = totalStep+"]";


    $.ajax({
        url     :   "/c/page/console/auth/recommend/addStep",
        type    :   "POST",
        dataType:   "JSON",
        data    :   {totalStep:totalStep},
        success:function (data) {
            if (data.success){
                // tips.suc("保存步骤成功",2000);

                if(missionType == '1') {
                    window.location.href = '/c/page/console/auth/exchange/exchangeList';
                }else if(missionType == '0'){
                    window.location.href = '/c/page/console/auth/recommend/recommendList';
                }else if(missionType == '2'){
                    window.location.href = '/c/page/console/auth/registerMission/registerMissionPage';
                }

            }
        },
        error:function () {
            
        }

    });
};


/**
 * 下拉框值改变监听
 */
function typeChange(){

    var type = $("#typeSelect").val();

    if (type == '0'){

        $("#type").val("请选择按钮类型")

        $("#param").attr("placeholder","请输入按钮参数");

    }else if (type == 'copy'){

        $("#type").val("复制关键词")

        $("#param").attr("placeholder","请输入要复制的'关键词'");
    }else if (type == 'download'){//3个参数

        $("#type").val("下载APP")

        $("#param").attr("placeholder","app名字&apk地址&标志(0-手动,1-自动打开)");
    }else if (type == 'openApp'){

        $("#type").val("打开APP")

        $("#param").attr("placeholder","请输入要打开应用的'包名'");
    }else if (type == 'link'){

        $("#type").val("超链接")

        $("#param").attr("placeholder","请输入要打开的'超链接'");
    }

}


function stepChange() {

    var step = $("#stepSelect").val();

    $("#stepInput").val("添加按钮至:第"+step+"步");
}





/**
 * 向步骤中添加按钮
 */
function addButtonToStep() {

    var type = $("#typeSelect").val();
    if (type == '0') {
        $("#warning").text("请选择按钮类型");
        setTimeout(function () {
            $("#warning").text("");
        },2000);
        return;
    }

    var step = $("#stepSelect").val();
    if (step == '0'){
        $("#warning").text("请选择添加步骤");
        setTimeout(function () {
            $("#warning").text("");
        },2000);
        return;
    }

    var title = $("#title").val().trim();
    if (title.trim() == ""|| title == null ||title==undefined){
        $("#warning").text("请输入按钮标题");
        setTimeout(function () {
            $("#warning").text("");
        },2000);
        return;
    }

    var param = $("#param").val().trim();
    if (param == ""|| param == null ||param==undefined){
        $("#warning").text("请输入按钮参数");
        setTimeout(function () {
            $("#warning").text("");
        },2000);
        return;
    }

    var newBtn;
    if (type == 'link'){//为打开一个链接,不用调app的原生代码
        newBtn = "<a href='javaScript:gotoAnotherUrl(\""+param+"\");' class='"+type+"'>"+title+"<span class='close deleteBtn'>&times;</span></a>"
    }else if(type == 'openApp'){//否则,把参数放入value中
        newBtn = "<a href='javaScript:openApp(\""+param+"\");' class='"+type+"'>"+title+"<span class='close deleteBtn'>&times;</span></a>"
    }else if(type == 'copy'){//否则,把参数放入value中
        newBtn = "<a href='javaScript:copyWord(\""+param+"\");' class='"+type+"'>"+title+"<span class='close deleteBtn'>&times;</span></a>"
    }else if(type == 'download'){//否则,把参数放入value中
        //若是下载app,则需要三个参数,用|分开
        var params = param.split("&");
        if (params.length<3){
            $("#warning").text("下载按钮需要三个参数");
            setTimeout(function () {
                $("#warning").text("");
            },2000);
            return;
        }
        var appName = params[0];
        if (appName == ""|| appName == null ||appName==undefined){
            $("#warning").text("下载按钮需要三个参数");
            setTimeout(function () {
                $("#warning").text("");
            },2000);
            return;
        }

        var appUrl = params[1];
        if (appUrl == ""|| appUrl == null ||appUrl==undefined){
            $("#warning").text("下载按钮需要三个参数");
            setTimeout(function () {
                $("#warning").text("");
            },2000);
            return;
        }
        var flag = params[2];
        if (flag == ""|| flag == null ||flag==undefined){
            $("#warning").text("下载按钮需要三个参数");
            setTimeout(function () {
                $("#warning").text("");
            },2000);
            return;
        }
        newBtn = "<a href='javaScript:downloadApp(\""+appName+"\",\""+appUrl+"\",\""+flag+"\");' class='"+type+"'>"+title+"<span class='close deleteBtn'>&times;</span></a>"
    }

    var totalStep = $("tr");

    $(totalStep[step-1]).find(".buttonDiv").append(newBtn);

    $("#closeModal").click();

};
