<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page pageEncoding="UTF-8" %>
<tiles:insertTemplate template="/WEB-INF/views/base/layout/layout-default.jsp">
    <tiles:putAttribute name="title" value="编辑审核任务"/>
    <tiles:putAttribute name="enableConsole" value="true"/>
    <tiles:putAttribute name="enableSidebar" value="true"/>
    <tiles:putAttribute name="styleArea">
        <style>
            .loadingPic {
                display: none;
            }
            table img {
                width: 50px;
                cursor: pointer;
            }
            .l{
                margin-right: 5px;
            }
        </style>
    </tiles:putAttribute>
    <tiles:putAttribute name="scriptArea">
        <script src="/plugins/My97DatePicker/function.js"></script>
        <script src="/plugins/My97DatePicker/WdatePicker.js"></script>
        <script src="/client/js/base/control-convert.js" type="text/javascript"></script>
        <script src="/plugins/upload/image-upload/js/image-upload.js" type="text/javascript"></script>
        <script src="/client/js/base/control-convert.js" type="text/javascript"></script>
        <script src="/plugins/upload/image-upload/js/image-upload.js" type="text/javascript"></script>
        <script src="/plugins/ueditor/ueditor.config.js"></script>
        <script src="/plugins/ueditor/ueditor.all.js"></script>
        <script src="/plugins/ueditor/custom/addCustomizeButton.js"></script>
        <script src="/plugins/ueditor/custom/addCustomizeDialog.js"></script>


        <script>

            $(function () {
                object.init();
            });

            /**
             * ajax请求是异常操作，使用全局变量
             */
            var ue;
            var allFatherTypes;

            var object = {

                missionId:null,
                missionType:0,
                typeId:null,
                verifyRequire:null,
                missionOrder:null,
                missionIcon:null,
                subTypeName:null,
                missionTitle:null,
                missionLabel:null,
                startTime:null,
                endTime:null,
                totalNum:null,
                leftNum:null,
                missionStatus:null,
                isLimitTime:null,
                limitMinute:null,
                isVerify:null,
                isEnd:null,
                participantsNum:null,
                missionReward:null,
                missionDetail:null,
                missionBanner:null,
                merchantName:null,
                merchantIcon:null,
                minMoney:null,
                imgNum:null,

                isAdd:false,
                isUpdate:false,
                ue:null,
                fatherTypes:null,
                allLabels:null,
                allRequires:null,

                /**
                 * 初始化
                 */
                init:function () {
                    this.createUEditor();
                    this.getAllFatherTypes();
                    var add = '${add}';
                    var update = '${update}';
                    this.typeId = '${object.typeId}';
                    if(this.typeId == 0){
                        $("#subDiv").hide();
                    }

                    <%--this.setCheckedSubType('${object.subTypeName}');--%>
                    if(add == 1){
                        //若是添加，则把所有可以选择的标签，分类都类出来
                        this.getAllLabels();
                        this.getAllRequires();
                    }
                    if(update == 1){
                        this.isUpdate = true;
                        this.missionId = '${object.missionId}';
                        //若是修改，则把所有的标签，分类列出，且要选定之前已经选择的选项
                        this.setLabels();
                        this.setRequires();
                        this.setTypes(this.missionId);
                    }
                },
                /**
                 * 获取高额任务的审核要qiu
                 */
                getAllRequires:function () {

                    $.ajax({
                        url:'/c/page/console/auth/recommend/require',
                        type:'post',
                        dataType:'json',
                        success:function (data) {
                            var allRequires = data.bean.requires;

                            for (var i in allRequires){
                                var requireName = allRequires[i].requireName;
                                var requireId = allRequires[i].requireId;
                                var newInput = "<label class='l'>"+requireName+"<input type='checkbox' name='requireName'  value='"+requireId+"&"+requireName+"'></label>";
                                $("#requireDiv").append(newInput);
                            }
                        },
                    });
                },

                /**
                 * 若是修改,则把所有的标签，分类列出，且要选定之前已经选择的选项
                 */
                setRequires:function () {

                    var missionId = this.missionId;
                    $.ajax({
                        url:'/c/page/console/auth/recommend/setRequire',
                        type:'post',
                        dataType:'json',
                        data:{missionId:missionId},
                        success:function (data) {
                            var old = data.bean.old;
                            var all = data.bean.all;

                            for (var i in all){
                                var requireId = all[i].requireId;
                                var requireName = all[i].requireName;
                                var isChecked = false;
                                for(var j in old){
                                    var oldRequireId = old[j].requireId;
                                    if(oldRequireId == requireId){
                                        isChecked = true;
                                    }
                                }
                                if (isChecked){
                                    var newInput = "<label class='l'>"+requireName+"<input type='checkbox' name='requireName' checked value='"+requireId+"&"+requireName+"'></label>";
                                    $("#requireDiv").append(newInput);
                                }else{
                                    var newInput = "<label class='l'>"+requireName+"<input type='checkbox' name='requireName'  value='"+requireId+"&"+requireName+"'></label>";
                                    $("#requireDiv").append(newInput);
                                }
                            }
                        },
                    });
                },

                /**
                 * 若是修改,则把所有的标签，分类列出，且要选定之前已经选择的选项
                 */
                setLabels:function () {
                    var missionId = this.missionId;
                    $.ajax({
                        url:'/c/page/console/auth/recommend/setLabel',
                        type:'post',
                        dataType:'json',
                        data:{missionId:missionId},
                        success:function (data) {
                            var oldLabels = data.bean.old;
                            var all = data.bean.all;
                            oldLabels = oldLabels.split(",");

                            for (var i in all){
                                var labelName = all[i].taskLabel;
                                var isChecked = false;
                                for(var j in oldLabels){
                                    if(labelName == oldLabels[j]){
                                        isChecked = true;
                                    }
                                }
                                var newInput;
                                if (isChecked){
                                    newInput = "<label class='l'>"+labelName+"<input type='checkbox' name='labelName' checked value='"+labelName+"'></label>";
                                }else{
                                    newInput = "<label class='l'>"+labelName+"<input type='checkbox' name='labelName' value='"+labelName+"'></label>";
                                }
                                $("#labelDiv").append(newInput);
                            }
                        },
                    });
                },

                /**
                 * 设置类型
                 */
                setTypes:function () {
                    $("#typeId").val(this.typeId);
                    var missionId = this.missionId;
                    $("#subTypeDiv").empty();
                    $.ajax({
                        url:'/c/page/console/auth/recommend/setType',
                        type:'post',
                        dataType:'json',
                        data:{missionId:missionId},
                        success:function (data) {
                            var old = data.bean.old;
                            var all = data.bean.all;
                            all = all.split(",");
                            old = old.split(",");
                            for(var i in all){

                                var typeName = all[i];
                                var isChecked = false;
                                if(old != null && old != ''){
                                    for(var j in old){

                                        var oldTypeName = old[j];

                                        if(oldTypeName.trim() == typeName.trim()){

                                            isChecked = true;
                                        }
                                    }
                                }

                                if (isChecked){
                                    var newInput = "<label class='l'>"+typeName+"<input type='checkbox' name='subTypeName' checked value='"+typeName+"'></label>";
                                    $("#subTypeDiv").append(newInput);
                                }else{
                                    var newInput = "<label class='l'>"+typeName+"<input type='checkbox' name='subTypeName' value='"+typeName+"'></label>";
                                    $("#subTypeDiv").append(newInput);
                                }
                            }
                        },
                    });
                },

                /**
                 * 获取高额任务的可选标签
                 */
                getAllLabels:function () {

                    $.ajax({
                        url:'/c/page/console/auth/recommend/label',
                        type:'post',
                        dataType:'json',
                        success:function (data) {
                            var allLabels = data.bean.labels;
                            for (var i in allLabels){

                                var sub = allLabels[i].taskLabel;
                                var newInput = "<label class='l'>"+sub+"<input type='checkbox' name='labelName' value='"+sub+"'></label>";
                                $("#labelDiv").append(newInput);
                            }
                        },
                    });
                },
                /**
                 * 根据类型id生成对应的子类型复选框
                 */
                setSubTypeOfSTypeId:function (typeId) {
                    $("#typeId").val(typeId);
                    $.ajax({
                        url:'/c/page/console/auth/recommend/getSubTypeById',
                        type:'post',
                        dataType:'json',
                        data:{id:typeId},
                        success:function (data) {
                            var subTypeName = data.bean;
                            subTypeName = subTypeName.split(",")
                            $("#subTypeDiv").empty();
                            for(var i in subTypeName){
                                var sub = subTypeName[i];
                                var newInput = "<label class='l'>"+sub+"<input type='checkbox' name='subTypeName' value='"+sub+"'></label>";
                                $("#subTypeDiv").append(newInput);
                            }

                        },
                    });
                },
                /**
                 * 初始化类型
                 */
                getAllFatherTypes:function () {
                    $.ajax({
                        url:'/c/page/console/auth/recommend/getAllFatherTypes',
                        type:'post',
                        dataType:'json',
                        success:function (data) {
                            allFatherTypes = data.bean.types;
                        },
                    });
                },

                /**
                 * 自动生产复选框
                 */
                createSubTypeInput:function (subTypeArray) {
                    $("#subTypeDiv").empty();
                    for(var i in subTypeArray){
                        var sub = subTypeArray[i];
                        var newInput = "<label class='l'>"+sub+"<input type='checkbox' name='subTypeName' value='"+sub+"'></label>";
                        $("#subTypeDiv").append(newInput);
                    }
                },
                /**
                 * 改变类型时，对应的子类型需要改变
                 */
                changeSubType:function () {

                    $("#subTypeDiv").empty();
                    var typeId = $("#typeId").val();
                    if(typeId == 0){
                        $("#subTypeDiv").empty();
                        $("#subDiv").hide();
                        return;
                    }
                    $("#subDiv").show();
                    var subTypeName;
                    for (var i in allFatherTypes){
                        if (typeId == allFatherTypes[i].typeId){
                            subTypeName = allFatherTypes[i].subTypeName;
                            break;
                        }
                    }
                    if(subTypeName != null && subTypeName != ""){
                        this.createSubTypeInput(subTypeName.split(","));
                    }
                },
                /**
                 * 设置子类型
                 */
                setCheckedSubType:function(subTypeNameString){

                    subTypeNameString = subTypeNameString.split(",");
                    if(subTypeNameString == ''){
                        return;
                    }
                    $("#subTypeDiv").empty();
                    for(var i in subTypeNameString){
                        var sub = subTypeNameString[i];
                        var newInput = "<label class='l'>"+sub+"<input type='checkbox' name='subTypeName' checked value='"+sub+"'></label>";
                        $("#subTypeDiv").append(newInput);
                    }
                },
                /**
                 * 文章任务的类型改变的时候
                 */
                changeType:function () {
                },
                /**
                 * 保存修改／添加
                 */
                save:function () {

                    tips.loading();
                    //用户提供的链接
                    this.getProperties();

                    this.sendAjaxReq();
                },

                /**
                 * 点击上传图片按钮
                 */
                imgClick:function () {

                    $("#articleIcon").click();
                },

                /**
                 * 创建编辑器
                 */
                createUEditor:function () {

                    //初始化部化页面信息
                    ue = UE.getEditor('missionDetail', {
                        toolbars: [[
                            'source', //源代码
                            'fontfamily', //字体
                            'fontsize', //字号
                            'bold', //加粗
                            'forecolor', //字体颜色
                            'backcolor', //背景色
                            'justifyleft', //居左对齐
                            'justifyright', //居右对齐
                            'justifycenter', //居中对齐
                            'justifyjustify', //两端对齐
                            'insertorderedlist', //有序列表
                            'insertunorderedlist', //无序列表
                            'simpleupload', //单图上传
                            'link' //超链接
                        ]],
                        autoHeightEnabled: true,
                        autoFloatEnabled: true
                    });

                    UE.Editor.prototype._bkGetActionUrl = UE.Editor.prototype.getActionUrl;
                    UE.Editor.prototype.getActionUrl = function(action) {
                        if (action == 'uploadimage') {
                            return "${BASE_PATH}/c/p/picture/ue";
                        } else {
                            return this._bkGetActionUrl.call(this, action);
                        }
                    };
                },
                /**
                 * 发送请求
                 */
                sendAjaxReq:function () {

                    $.ajax({
                        url:'/c/page/console/auth/recommend/save',
                        type:'post',
                        dataType:'json',
                        data:{
                            missionId:this.missionId,
                            missionType:0,
                            typeId:this.typeId,
                            verifyRequire:"["+this.verifyRequire+"]",
                            subTypeName:this.subTypeName,
                            missionIcon:this.missionIcon,
                            missionTitle:this.missionTitle,
                            missionLabel:this.missionLabel,
                            missionOrder:this.missionOrder,
                            startTime:this.startTime,
                            endTime:this.endTime,
                            totalNum:this.totalNum,
                            leftNum:this.leftNum,
                            missionStatus:this.missionStatus,
                            isLimitTime:this.isLimitTime,
                            limitMinute:this.limitMinute,
                            isVerify:this.isVerify,
                            isEnd:this.isEnd,
                            participantsNum:this.participantsNum,
                            missionReward:this.missionReward,
                            missionDetail:this.missionDetail,
                            missionBanner:this.missionBanner,
                            merchantName:this.merchantName,
                            merchantIcon:this.merchantIcon,
                            minMoney:this.minMoney,
                            imgNum:this.imgNum
                        },
                        success:function (data) {

                            if (data.success){

                                tips.suc("保存成功",2000);
                                setTimeout(function () {
                                    window.location.href='/c/page/console/auth/recommend/recommendList';
                                },2000);
                            }else {
                                tips.err("保存失败",2000);
                            }
                        }
                    });
                },

                /**
                 * 获取各项的输入值
                 * @params articleType 文章类型，0：自己编辑的的文章，1：用户提供的文章链接
                 */
                getProperties:function () {
                    this.typeId = $("#typeId").val().trim();
                    this.missionIcon = $("#missionIcon").attr('src');
                    this.missionTitle = $("#missionTitle").val().trim();

                    this.missionLabel = $("input[name=labelName]:checked").map(function (index,elem) {
                        return $(elem).val();
                    }).get().join(',');
                    //[{"requireId":"1","requireName":"手机号"},{"requireId":"2","requireName":"姓名"},{"requireId":"3","requireName":"身份证"}]
                    this.verifyRequire = $("input[name=requireName]:checked").map(function (index,elem) {
                                var value = $(elem).val().split('&');
                                var requireName = value[1];
                                var requireId = value[0];
                                var json = '{"requireId":"'+requireId+'","requireName":"'+requireName+'"}';
                                return json;
                            }).get().join(',');
                    this.missionOrder = $("#missionOrder").val().trim();
                    this.startTime = $("#startTime").val().trim();
                    this.endTime = $("#endTime").val().trim();
                    this.totalNum = $("#totalNum").val().trim();
                    this.leftNum = $("#leftNum").val().trim();
                    this.missionDetail = ue.getContent();
                    this.missionStatus = $("#missionStatus").val();
                    this.isLimitTime = $("#isLimitTime").val().trim();
                    this.limitMinute = $("#limitMinute").val().trim();
                    this.isVerify = $("#isVerify").val().trim();

                    this.isEnd = $("#isEnd").val();
                    this.participantsNum = $("#participantsNum").val().trim();
                    this.missionReward = $("#missionReward").val().trim();
                    this.missionBanner = $("#missionBanner").attr('src');
                    this.merchantName = $("#merchantName").val().trim();
                    this.merchantIcon = $("#merchantIcon").attr('src');
                    this.minMoney = $("#minMoney").val().trim();
                    this.imgNum = $("#imgNum").val().trim();
                    if(this.typeId != 0){
                        this.subTypeName = $("input[name=subTypeName]:checked").map(function (index,elem) {
                            return $(elem).val();
                        }).get().join(',');
                    }
                }
            }
        </script>

    </tiles:putAttribute>
    <tiles:putAttribute name="mainArea">
        <!-- 顶部栏 begin -->
        <div class="panel panel-default main-hd">
            <div class="panel-heading">
                <button class="btn btn-default" onclick='object.save();'>
                    <span class="glyphicon glyphicon-ok"></span>
                    <c:if test="${add ==1}">保存添加</c:if>
                    <c:if test="${update ==1}">保存修改</c:if>
                </button>
            </div>
        </div>
        <!-- 顶部栏 end -->
        <%--主内容显示区 begin--%>
        <div class="main-bd">
            <form class="form-horizontal" role="form">
                <div class="form-group has-error">
                    <label for="missionTitle" class="col-sm-1 control-label">标题</label>
                    <div class="col-sm-3">
                        <input type="text" class="form-control" id="missionTitle" value="${object.missionTitle}">
                    </div>

                    <label for="missionOrder" class="col-sm-1 control-label">任务权重</label>
                    <div class="col-sm-3">
                        <input type="text" class="form-control" id="missionOrder" value="${object.missionOrder}">
                    </div>
                </div>

                <div class="form-group has-error">
                    <label for="missionReward" class="col-sm-1 control-label">任务奖励</label>
                    <div class="col-sm-3">
                        <input type="text" class="form-control" id="missionReward" value="${object.missionReward}">
                    </div>

                    <label for="isEnd" class="col-sm-1 control-label">是否结束</label>
                    <div class="col-sm-3">
                        <select class="form-control" id="isEnd" name="isEnd">
                            <option value="0" <c:if test="${object.isEnd == 0}">selected</c:if> >结束</option>
                            <option value="1" <c:if test="${object.isEnd == 1}">selected</c:if> >进行中</option>
                        </select>
                    </div>
                </div>

                <div class="form-group has-error">
                    <label for="labelDiv" class="col-sm-1 control-label">任务标签</label>
                    <div class="col-sm-3" id="labelDiv" style="position: relative;top:6px;">

                    </div>

                    <label for="labelDiv" class="col-sm-1 control-label">审核要求</label>
                    <div class="col-sm-3" id="requireDiv" style="position: relative;top:6px;">

                    </div>
                </div>
                <div class="form-group has-success">
                    <label for="typeId" class="col-sm-1 control-label">分类</label>
                    <div class="col-sm-3">
                        <select class="form-control" id="typeId" name="typeId" onchange="object.changeSubType();">
                            <option value="0">未分类</option>
                            <c:forEach items="${types}" var="type">
                                <option value="${type.typeId}"
                                        <c:if test="${object.typeId == type.typeId}">selected</c:if> >
                                        ${type.typeName}
                                </option>
                            </c:forEach>
                        </select>
                    </div>
                    <div id="subDiv">
                        <label class="col-sm-1 control-label">子分类</label>
                        <div class="col-sm-3" id="subTypeDiv" style="position: relative;top: 6px;">

                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <label for="startTime" class="col-sm-1 control-label">开始时间</label>
                    <div class="col-sm-3">
                        <input type="text" id = "startTime" style="height: 30px;" value="${object.startTime}" class="form-control Wdate" onclick="WdatePicker({lang:'zh-cn',dateFmt:'yyyy-MM-dd'});"/>
                    </div>

                    <label for="endTime" class="col-sm-1 control-label">结束时间</label>
                    <div class="col-sm-3">
                        <input type="text" id = "endTime" style="height: 30px;" value="${object.endTime}" class="form-control Wdate" onclick="WdatePicker({lang:'zh-cn',dateFmt:'yyyy-MM-dd'});"/>
                    </div>
                </div>

                <div class="form-group has-error">
                    <label for="totalNum" class="col-sm-1 control-label">任务总数</label>
                    <div class="col-sm-3">
                        <input type="text" class="form-control" id="totalNum" <c:if test="${update == 1}">disabled</c:if> value="${object.totalNum}">
                    </div>

                    <label for="leftNum" class="col-sm-1 control-label">剩余数量</label>
                    <div class="col-sm-3">
                        <input type="text" class="form-control" id="leftNum"  <c:if test="${update == 1}">disabled</c:if> value="${object.leftNum}">
                    </div>
                </div>

                <div class="form-group has-error">

                </div>
                <div class="form-group has-success">
                    <label for="missionStatus" class="col-sm-1 control-label">平台</label>
                    <div class="col-sm-3">
                        <select class="form-control" id="missionStatus" name="missionStatus">
                            <option value="2"
                                    <c:if test="${object.missionStatus == 2}">selected</c:if> >
                                全部开启
                            </option>
                            <option value="0"
                                    <c:if test="${object.missionStatus == 0}">selected</c:if> >
                                ios开启
                            </option>
                            <option value="1"
                                    <c:if test="${object.missionStatus == 1}">selected</c:if> >
                                andriod开启
                            </option>
                            <option value="3"
                                    <c:if test="${object.missionStatus == 3}">selected</c:if> >
                                全部关闭
                            </option>
                        </select>
                    </div>

                    <label for="isLimitTime" class="col-sm-1 control-label">是否限时</label>
                    <div class="col-sm-3">
                        <select class="form-control" id="isLimitTime" name="isLimitTime">
                            <option value="0">限时</option>
                            <option value="1">不限时</option>
                        </select>
                    </div>
                </div>

                <div class="form-group has-error">
                    <label for="limitMinute" class="col-sm-1 control-label">限时时间</label>
                    <div class="col-sm-3">
                        <input type="text" class="form-control" id="limitMinute" value="${object.limitMinute}">
                    </div>

                    <label for="isVerify" class="col-sm-1 control-label">是否审核</label>
                    <div class="col-sm-3">
                        <select class="form-control" id="isVerify" name="isVerify">
                            <option value="0">审核</option>
                            <option value="1">不审核</option>
                        </select>
                    </div>
                </div>
                <div class="form-group">
                    <label for="merchantName" class="col-sm-1 control-label">商家名称</label>
                    <div class="col-sm-3">
                        <input type="text" class="form-control" id="merchantName" value="${object.merchantName}">
                    </div>

                    <label for="participantsNum" class="col-sm-1 control-label">参与人数</label>
                    <div class="col-sm-3">
                        <input type="number" class="form-control" id="participantsNum" value="${object.participantsNum}">
                    </div>
                </div>

                <div class="form-group">
                    <label for="minMoney" class="col-sm-1 control-label">最小金额</label>
                    <div class="col-sm-3">
                        <input type="number" class="form-control" id="minMoney" value="${object.minMoney}">
                    </div>

                    <label for="imgNum" class="col-sm-1 control-label">上传图片数</label>
                    <div class="col-sm-3">
                        <input type="number" class="form-control" id="imgNum" value="${object.imgNum}">
                    </div>
                </div>

                <div class="form-group">
                    <label for="missionIcon" class="col-sm-1 control-label">任务图标</label>
                    <div class="col-sm-2">
                        <img class='img-upload' src="${object.missionIcon}" mode='button-upload-pic' id="missionIcon" style="width: 100px;height: 100px;border: 1px solid #EFEFF4;"  action='/c/p/picture/upload' filesizelimit='128000' filetypelimit='jpg,png,gif,jpeg' obj='missionIcon'>
                    </div>

                    <label for="merchantIcon" class="col-sm-1 control-label">商家图标</label>
                    <div class="col-sm-2">
                        <img class='img-upload' src="${object.merchantIcon}" mode='button-upload-pic' id="merchantIcon" style="width: 100px;height: 100px;border: 1px solid #EFEFF4;"  action='/c/p/picture/upload' filesizelimit='128000' filetypelimit='jpg,png,gif,jpeg' obj='merchantIcon'>
                    </div>

                    <label for="missionBanner" class="col-sm-1 control-label">banner</label>
                    <div class="col-sm-2">
                        <img class='img-upload' src="${object.missionBanner}" mode='button-upload-pic' id="missionBanner" style="width: 100px;height: 100px;border: 1px solid #EFEFF4;"  action='/c/p/picture/upload' filesizelimit='128000' filetypelimit='jpg,png,gif,jpeg' obj='missionBanner'>
                    </div>
                </div>

                <div class="form-group">
                    <label for="missionDetail" class="col-sm-1 control-label">任务详情</label>
                    <div class="col-sm-3">
                        <div  style="width: 900px;">
                            <script id="missionDetail" name="missionDetail" type="text/plain">${object.missionDetail}</script>
                        </div>
                    </div>
                </div>

            </form>
        </div>
        <%--主内容显示区 end --%>


    </tiles:putAttribute>
</tiles:insertTemplate>
