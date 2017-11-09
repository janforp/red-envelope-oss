<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page pageEncoding="UTF-8" %>
<tiles:insertTemplate template="/WEB-INF/views/base/layout/layout-default.jsp">
    <tiles:putAttribute name="title" value="编辑文章"/>
    <tiles:putAttribute name="enableConsole" value="true"/>
    <tiles:putAttribute name="enableSidebar" value="true"/>
    <tiles:putAttribute name="styleArea">
        <link rel="stylesheet" type="text/css" href="/client/css/template/base.css">
        <style>
            .loadingPic {
                display: none;
            }
            table img {
                width: 50px;
                cursor: pointer;
            }
        </style>
    </tiles:putAttribute>
    <tiles:putAttribute name="scriptArea">
        <script src="/plugins/My97DatePicker/function.js"></script>
        <script src="/plugins/My97DatePicker/WdatePicker.js"></script>
        <script src="/client/js/base/control-convert.js" type="text/javascript"></script>
        <script src="/plugins/upload/image-upload/js/image-upload.js" type="text/javascript"></script>

        <script src="/plugins/ueditor/ueditor.config.js"></script>
        <script src="/plugins/ueditor/ueditor.all.js"></script>
        <script>

            $(function () {
                article.init();
            });

            var ue;

            var article = {

                articleId:null,
                articleUrl:null,
                articleType:null,
                articleTitle:null,
                isDirectlyGoAd:null,
                articleContent:null,
                articleDisplayTime:null,
                articleAuthor:null,
                articleIcon:null,
                appReadMoney:null,
                wxClickMoney:null,
                totalClickTimes:null,
                leftClickTimes:null,
                startMissionTime:null,
                endMissionTime:null,
                isEnd:null,
                articleWeight:null,
                readTimes:null,
                praiseTimes:null,
                originalUrl:null,

                isAdd:false,
                isUpdate:false,
                ue:null,

                /**
                 * 初始化
                 */
                init:function () {

                    var add = '${add}';
                    var update = '${update}';
                    var articleType = '${article.articleType}';

                    if(add == 1){
                        this.isAdd = true;
                        this.createUEditor();
                    }
                    if (update == 1){
                        this.isUpdate = true;
                        if(articleType == 0){
                            this.createUEditor();
                        }
                    }

                    if(update == 1){//修改的时候不允许修改类型
                        $("#articleType").val(articleType);
                        $("#articleType").prop('disabled',true);

                    }
                    if(articleType == 1){//若是修改，则根据不同的类型，显示不同的输入项
                        $("#otherTypeDiv").addClass('hide');
                    }else {

                    }
                },

                /**
                 * 文章任务的类型改变的时候
                 */
                changeType:function () {

                    var articleType = $("#articleType").val();
                    //用户提供的链接
                    if (articleType == 1){
                        $("#otherTypeDiv").addClass('hide');
                    }else {
                        $("#otherTypeDiv").removeClass('hide');
                    }
                },

                /**
                 * 点击'抓内容'按钮
                 */
                getDataFromUrl:function () {

                    tips.loading();

                    var articleUrl = $("#articleUrl").val();

                    $.ajax({
                        url: '/c/page/console/auth/article/getArticle',
                        type: 'POST',
                        dataType: 'json',
                        traditional: true,
                        data: {
                            articleUrl:articleUrl
                        },
                        success: function (data) {
                            if (data.success == true) {
                                var title = data.bean.title;
                                $("#articleTitle").val(title);
                                var text = data.bean.content;
                                ue.setContent(text);
                                $("#articleDisplayTime").val(data.bean.displayTime);
                                $("#articleAuthor").val(data.bean.articleAuthor);
                            } else {
                                alert(data.msg);
                            }
                        },
                        complete: function () {

                        },
                        error:function(o){
                        }
                    });
                },
                /**
                 * 保存修改／添加
                 */
                saveArticle:function () {

                    var articleType = $("#articleType").val();
                    tips.loading();
                    //用户提供的链接
                    this.getArticleProperties(articleType);

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
                   ue = UE.getEditor('articleContent', {
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
                        ]
                        ],
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
                        url:'/c/page/console/auth/article/save',
                        type:'post',
                        dataType:'json',
                        data:{
                            articleId:this.articleId,
                            articleUrl:this.articleUrl,
                            articleType:this.articleType,
                            articleTitle:this.articleTitle,
                            isDirectlyGoAd:this.isDirectlyGoAd,
                            articleContent:this.articleContent,
                            articleDisplayTime:this.articleDisplayTime,
                            articleAuthor:this.articleAuthor,
                            articleIcon:this.articleIcon,
                            appReadMoney:this.appReadMoney,
                            wxClickMoney:this.wxClickMoney,
                            totalClickTimes:this.totalClickTimes,
                            leftClickTimes:this.leftClickTimes,
                            startMissionTime:this.startMissionTime,
                            endMissionTime:this.endMissionTime,
                            isEnd:this.isEnd,
                            articleWeight:this.articleWeight,
                            originalUrl:this.originalUrl
                        },
                        success:function (data) {

                            if (data.success){

                                tips.suc("保存成功",2000);
                                setTimeout(function () {
                                    window.location.href='/c/page/console/auth/article/articlePage';
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
                getArticleProperties:function (articleType) {

                    //若是修改，则带上ID
                    if (this.isUpdate){
                        this.articleId = $("#articleId").val();
                    }

                    {   //不管类型，修改或添加都要初始化的值
                        this.articleUrl = $("#articleUrl").val().trim();
                        this.articleType = $("#articleType").val();
                        this.articleIcon = $("#articleIcon").attr('src');
                        this.articleTitle = $("#articleTitle").val().trim();
                        this.startMissionTime = $("#startMissionTime").val().trim();
                        this.endMissionTime = $("#endMissionTime").val().trim();
                        this.isEnd = $("#isEnd").val();
                        this.articleWeight = $("#articleWeight").val().trim();
                    }

                    {   //修改的时候，不允许修改金额及次数
                        if(this.isAdd){
                            this.appReadMoney = $("#appReadMoney").val().trim();
                            this.wxClickMoney = $("#wxClickMoney").val().trim();
                            this.totalClickTimes = $("#totalClickTimes").val().trim();
                            this.leftClickTimes = $("#leftClickTimes").val().trim();
                        }
                    }

                    {
                        //自己编辑的的文章
                        if(articleType == 0) {

                            this.isDirectlyGoAd = $("#isDirectlyGoAd").val();
                            this.articleContent = ue.getContent();
                            this.articleDisplayTime = $("#articleDisplayTime").val();
                            this.articleAuthor = $("#articleAuthor").val();
                            this.originalUrl = $("#originalUrl").val().trim();
                        }
                    }
                },
            }
        </script>

    </tiles:putAttribute>
    <tiles:putAttribute name="mainArea">
        <!-- 顶部栏 begin -->
        <div class="panel panel-default main-hd">
            <div class="panel-heading">
                <button class="btn btn-default" onclick='article.saveArticle();'>
                    <span class="glyphicon glyphicon-ok"></span>
                    <c:if test="${add ==1}">添 加</c:if>
                    <c:if test="${update ==1}">修 改</c:if>
                </button>
                <input type="hidden" value="${article.articleId}" id="articleId">
            </div>
        </div>
        <!-- 顶部栏 end -->
        <%--主内容显示区 begin--%>
        <div class="main-bd">
            <form class="form-horizontal" role="form">
                <div class="form-group has-error">
                    <label for="articleUrl" class="col-sm-1 control-label">文章链接</label>
                    <div class="col-sm-3">
                        <input type="text" class="form-control" id="articleUrl" placeholder="请输入链接" value="${article.articleUrl}">
                        <span class="help-block" id="urlTips"></span>
                    </div>
                    <div class="col-sm-1">
                        <button type="button" id="parse" class="form-control btn-warning col-sm-2" value="采取" onclick="article.getDataFromUrl();">
                            <span class="glyphicon glyphicon-search"></span>
                            采集
                        </button>
                    </div>
                </div>

                <div class="form-group has-success">
                    <label for="articleType" class="col-sm-1 control-label">类型</label>
                    <div class="col-sm-3">
                        <select class="form-control" id="articleType" name="edit_label" onchange="article.changeType();">
                            <option value="0">自己编辑的的文章</option>
                            <option value="1">用户提供的文章链接</option>
                        </select>
                    </div>
                </div>
                <hr>
                <div class="form-group">
                    <label for="articleTitle" class="col-sm-1 control-label">文章名称</label>
                    <div class="col-sm-3">
                        <input type="text" class="form-control" id="articleTitle" value="${article.articleTitle}">
                    </div>
                </div>

                <div id="otherTypeDiv"><!--hide start-->

                    <div class="form-group">
                        <label for="originalUrl" class="col-sm-1 control-label">原文链接</label>
                        <div class="col-sm-3">
                            <input type="text" class="form-control" id="originalUrl" value="${article.originalUrl}">
                        </div>
                    </div>
                    <div class="form-group has-success">
                        <label for="isDirectlyGoAd" class="col-sm-1 control-label">是否直接条广告</label>
                        <div class="col-sm-3">
                            <select class="form-control" id="isDirectlyGoAd" name="isDirectlyGoAd">
                                <option value="0">不直接跳转到广告</option>
                                <option value="1">直接跳转到广告</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="articleDisplayTime" class="col-sm-1 control-label">显示时间</label>
                        <div class="col-sm-3">
                            <input type="text" id = "articleDisplayTime" style="height: 30px;" value="${article.articleDisplayTime}" class="form-control Wdate" onclick="WdatePicker({lang:'zh-cn',dateFmt:'yyyy-MM-dd HH:mm:ss '});"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="articleAuthor" class="col-sm-1 control-label">文章作者</label>
                        <div class="col-sm-3">
                            <input type="text" class="form-control" id="articleAuthor" value="${article.articleAuthor}">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="articleType" class="col-sm-1 control-label">文章内容</label>
                        <div class="col-sm-3">
                            <div  style="width: 900px;">
                                <script id="articleContent" name="articleContent">${article.articleContent}</script>
                            </div>
                        </div>
                    </div>

                </div><!--hide over-->

                <div class="form-group">
                    <label for="articleIcon" class="col-sm-1 control-label">图片</label>
                    <div class="col-sm-3">
                        <img class='img-upload' src="${article.articleIcon}" mode='button-upload-pic' id="articleIcon" style="width: 100px;height: 100px;border: 1px solid #EFEFF4;"  action='/c/p/picture/upload' filesizelimit='128000' filetypelimit='jpg,png,gif,jpeg' obj='articleIcon'>
                    </div>
                    <div class="col-sm-1">
                        <button type="button" onclick="article.imgClick();"  class="form-control btn-info col-sm-2" >
                            <span class="glyphicon glyphicon-picture"></span>
                            上传
                        </button>
                    </div>
                </div>

                <c:if test="${update != 1}">
                    <div class="form-group">
                        <label for="appReadMoney" class="col-sm-1 control-label">APP阅读金额(元)</label>
                        <div class="col-sm-3">
                            <input type="number" class="form-control" id="appReadMoney" value="${article.appReadMoney}">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="appReadMoney" class="col-sm-1 control-label">点击奖励金额(元/次)</label>
                        <div class="col-sm-3">
                            <input type="number" class="form-control" id="wxClickMoney" value="${article.wxClickMoney}">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="totalClickTimes" class="col-sm-1 control-label">总次数</label>
                        <div class="col-sm-3">
                            <input type="number" class="form-control" id="totalClickTimes" value="${article.totalClickTimes}">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="leftClickTimes" class="col-sm-1 control-label">剩余次数</label>
                        <div class="col-sm-3">
                            <input type="number" class="form-control" id="leftClickTimes" value="${article.leftClickTimes}">
                        </div>
                    </div>
                </c:if>

                <div class="form-group">
                    <label for="startMissionTime" class="col-sm-1 control-label">任务开始时间</label>
                    <div class="col-sm-3">
                        <input type="text" id = "startMissionTime" style="height: 30px;" value="${article.startMissionTime}" class="form-control Wdate" onclick="WdatePicker({lang:'zh-cn',dateFmt:'yyyy-MM-dd HH:mm:ss '});"/>
                    </div>
                </div>
                <div class="form-group">
                    <label for="endMissionTime" class="col-sm-1 control-label">任务结束时间</label>
                    <div class="col-sm-3">
                        <input type="text" id = "endMissionTime" style="height: 30px;" value="${article.endMissionTime}" class="form-control Wdate" onclick="WdatePicker({lang:'zh-cn',dateFmt:'yyyy-MM-dd HH:mm:ss '});"/>
                    </div>
                </div>
                <div class="form-group">
                    <label for="articleWeight" class="col-sm-1 control-label">权重</label>
                    <div class="col-sm-3">
                        <input type="number" id = "articleWeight" value="${article.articleWeight}" class="form-control" placeholder="请填写0至100的整数"/>
                    </div>
                </div>
                <div class="form-group has-success">
                    <label for="isDirectlyGoAd" class="col-sm-1 control-label">是否结束</label>
                    <div class="col-sm-3">
                        <select class="form-control" id="isEnd" name="isEnd">
                            <option value="1">进行中</option>
                            <option value="0">已结束</option>
                        </select>
                    </div>
                </div>
            </form>
        </div>
        <%--主内容显示区 end --%>

    </tiles:putAttribute>
</tiles:insertTemplate>
