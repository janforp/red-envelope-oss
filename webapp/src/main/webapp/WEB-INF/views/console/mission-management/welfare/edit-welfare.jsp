<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page pageEncoding="UTF-8" %>
<tiles:insertTemplate template="/WEB-INF/views/base/layout/layout-default.jsp">
    <tiles:putAttribute name="title" value="编辑福利"/>
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

                welfareId:null,
                welfareTitle:null,
                welfareIcon:null,
                welfareReward:null,
                welfareType:null,
                subTypeName:null,
                isSelection:null,
                merchantIcon:null,
                merchantName:null,
                participantsNum:null,
                welfareBanner:null,
                welfareDetail:null,
                welfareStatus:null,
                buttonName:null,
                buttonUrl:null,
                endTime:null,

                isAdd:false,
                isUpdate:false,
                ue:null,
                fatherTypes:null,
                typeId:null,

                /**
                 * 初始化
                 */
                init:function () {
                    this.createUEditor();
                    this.getAllFatherTypes();
                    var add = '${add}';
                    var update = '${update}';
                    this.typeId = '${object.welfareType}';
                    if(this.typeId == 0){
                        $("#subDiv").hide();
                    }
                    if (update == 1){
                        this.isUpdate = true;
                        this.welfareId = '${object.welfareId}';
                    }
                    <%--this.setSubTypeOfSTypeId('${object.welfareType}');--%>
                    this.setCheckedSubType('${object.subTypeName}');
                },
                /**
                 * 根据类型id生成对应的子类型复选框
                 */
                setSubTypeOfSTypeId:function (typeId) {
                    $.ajax({
                        url:'/c/page/console/auth/welfare/getSubTypeById',
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
                        url:'/c/page/console/auth/welfare/getAllFatherTypes',
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

                    var typeId = $("#welfareType").val();
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
                    this.createSubTypeInput(subTypeName.split(","));
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
                    ue = UE.getEditor('welfareDetail', {
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
                        url:'/c/page/console/auth/welfare/save',
                        type:'post',
                        dataType:'json',
                        data:{
                            welfareId:this.welfareId,
                            welfareTitle:this.welfareTitle,
                            welfareReward:this.welfareReward,
                            welfareIcon:this.welfareIcon,
                            welfareType:this.welfareType,
                            subTypeName:this.subTypeName,
                            isSelection:this.isSelection,
                            merchantIcon:this.merchantIcon,
                            merchantName:this.merchantName,
                            participantsNum:this.participantsNum,
                            welfareBanner:this.welfareBanner,
                            welfareDetail:this.welfareDetail,
                            welfareStatus:this.welfareStatus,
                            buttonName:this.buttonName,
                            buttonUrl:this.buttonUrl,
                            endTime:this.endTime
                        },
                        success:function (data) {

                            if (data.success){

                                tips.suc("保存成功",2000);
                                setTimeout(function () {
                                    window.location.href='/c/page/console/auth/welfare/welfarePage';
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
                    this.welfareTitle = $("#welfareTitle").val().trim();
                    this.welfareReward = $("#welfareReward").val().trim();
                    this.welfareIcon = $("#welfareIcon").attr('src');
                    this.welfareType = $("#welfareType").val().trim();
                    if(this.welfareType != 0){
                        this.subTypeName = $("input[name=subTypeName]:checked").map(function (index,elem) {
                            return $(elem).val();
                        }).get().join(',');
                    }
                    this.isSelection = $("#isSelection").val().trim();
                    this.merchantIcon = $("#merchantIcon").attr('src');
                    this.merchantName = $("#merchantName").val().trim();
                    this.participantsNum = $("#participantsNum").val().trim();
                    this.welfareBanner = $("#welfareBanner").attr('src');
                    this.welfareDetail = ue.getContent();
                    this.welfareStatus = $("#welfareStatus").val();
                    this.buttonName = $("#buttonName").val().trim();
                    this.buttonUrl = $("#buttonUrl").val().trim();
                    this.endTime = $("#endTime").val().trim();
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
                <input type="hidden" value="${object.welfareId}" id="id">
            </div>
        </div>
        <!-- 顶部栏 end -->
        <%--主内容显示区 begin--%>
        <div class="main-bd">
            <form class="form-horizontal" role="form">
                <div class="form-group has-error">
                    <label for="welfareTitle" class="col-sm-1 control-label">标题</label>
                    <div class="col-sm-3">
                        <input type="text" class="form-control" id="welfareTitle" value="${object.welfareTitle}">
                    </div>
                </div>

                <div class="form-group has-error">
                    <label for="welfareReward" class="col-sm-1 control-label">福利</label>
                    <div class="col-sm-3">
                        <input type="text" class="form-control" id="welfareReward" value="${object.welfareReward}">
                        <span class="help-block" id="urlTips"></span>
                    </div>
                </div>

                <div class="form-group has-success">
                    <label for="welfareType" class="col-sm-1 control-label">分类</label>
                    <div class="col-sm-3">
                        <select class="form-control" id="welfareType" name="welfareType" onchange="object.changeSubType();">
                            <option value="0">未分类</option>
                            <c:forEach items="${types}" var="type">
                                <option value="${type.typeId}"
                                        <c:if test="${object.welfareType == type.typeId}">selected</c:if> >
                                    ${type.typeName}
                                </option>
                            </c:forEach>
                        </select>
                    </div>
                </div>

                <div class="form-group" id="subDiv">
                    <label class="col-sm-1 control-label">子分类</label>
                    <div class="col-sm-3" id="subTypeDiv">

                    </div>
                </div>

                <div class="form-group has-success">
                    <label for="welfareStatus" class="col-sm-1 control-label">平台</label>
                    <div class="col-sm-3">
                        <select class="form-control" id="welfareStatus" name="welfareStatus">
                            <option value="2"
                                    <c:if test="${object.welfareStatus == 2}">selected</c:if> >
                                全部开启
                            </option>
                            <option value="0"
                                    <c:if test="${object.welfareStatus == 0}">selected</c:if> >
                                ios开启
                            </option>
                            <option value="1"
                                    <c:if test="${object.welfareStatus == 1}">selected</c:if> >
                                andriod开启
                            </option>
                            <option value="3"
                                    <c:if test="${object.welfareStatus == 3}">selected</c:if> >
                                全部关闭
                            </option>
                        </select>
                    </div>
                </div>
                <div class="form-group has-success">
                    <label for="isSelection" class="col-sm-1 control-label">是否精选</label>
                    <div class="col-sm-3">
                        <select class="form-control" id="isSelection" name="isSelection">
                            <option value="1">精选</option>
                            <option value="0">非精选</option>
                        </select>
                    </div>
                </div>

                <div class="form-group">
                    <label for="merchantName" class="col-sm-1 control-label">商家名称</label>
                    <div class="col-sm-3">
                        <input type="text" class="form-control" id="merchantName" value="${object.merchantName}">
                    </div>
                </div>

                <div class="form-group">
                    <label for="participantsNum" class="col-sm-1 control-label">参与人数</label>
                    <div class="col-sm-3">
                        <input type="text" class="form-control" id="participantsNum" value="${object.participantsNum}">
                    </div>
                </div>

                <div class="form-group">
                    <label for="welfareDetail" class="col-sm-1 control-label">福利详情</label>
                    <div class="col-sm-3">
                        <div  style="width: 900px;">
                            <script id="welfareDetail" name="welfareDetail" type="text/plain">${object.welfareDetail}</script>
                        </div>
                    </div>
                </div>

                <div class="form-group">
                    <label for="buttonName" class="col-sm-1 control-label">按钮名称</label>
                    <div class="col-sm-3">
                        <input type="text" class="form-control" id="buttonName" value="${object.buttonName}">
                    </div>
                </div>
                <div class="form-group">
                    <label for="buttonUrl" class="col-sm-1 control-label">按钮链接</label>
                    <div class="col-sm-3">
                        <input type="text" class="form-control" id="buttonUrl" value="${object.buttonUrl}">
                    </div>
                </div>
                <div class="form-group">
                    <label for="endTime" class="col-sm-1 control-label">结束时间</label>
                    <div class="col-sm-3">
                        <input type="text" id = "endTime" style="height: 30px;" value="${object.endTime}" class="form-control Wdate" onclick="WdatePicker({lang:'zh-cn',dateFmt:'yyyy-MM-dd HH:mm:ss '});"/>
                    </div>
                </div>

                <div class="form-group">
                    <label for="welfareIcon" class="col-sm-1 control-label">福利图标</label>
                    <div class="col-sm-3">
                        <img class='img-upload' src="${object.welfareIcon}" mode='button-upload-pic' id="welfareIcon" style="width: 100px;height: 100px;border: 1px solid #EFEFF4;"  action='/c/p/picture/upload' filesizelimit='128000' filetypelimit='jpg,png,gif,jpeg' obj='welfareIcon'>
                    </div>
                </div>

                <div class="form-group">
                    <label for="welfareIcon" class="col-sm-1 control-label">商家图标</label>
                    <div class="col-sm-3">
                        <img class='img-upload' src="${object.merchantIcon}" mode='button-upload-pic' id="merchantIcon" style="width: 100px;height: 100px;border: 1px solid #EFEFF4;"  action='/c/p/picture/upload' filesizelimit='128000' filetypelimit='jpg,png,gif,jpeg' obj='merchantIcon'>
                    </div>
                </div>

                <div class="form-group">
                    <label for="welfareBanner" class="col-sm-1 control-label">福利banner</label>
                    <div class="col-sm-3">
                        <img class='img-upload' src="${object.welfareBanner}" mode='button-upload-pic' id="welfareBanner" style="width: 100px;height: 100px;border: 1px solid #EFEFF4;"  action='/c/p/picture/upload' filesizelimit='128000' filetypelimit='jpg,png,gif,jpeg' obj='welfareBanner'>
                    </div>
                </div>

            </form>
        </div>
        <%--主内容显示区 end --%>


    </tiles:putAttribute>
</tiles:insertTemplate>
