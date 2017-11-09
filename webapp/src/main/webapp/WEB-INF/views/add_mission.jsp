<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page pageEncoding="UTF-8" %>
<tiles:insertTemplate template="/WEB-INF/views/base/layout/layout-default.jsp">
    <tiles:putAttribute name="title" value="添加任务"/>
    <tiles:putAttribute name="enableConsole" value="true"/>
    <tiles:putAttribute name="enableSidebar" value="true"/>
    <tiles:putAttribute name="styleArea">
        <link rel="stylesheet" type="text/css" href="/styles/update_mission.css">
    </tiles:putAttribute>
    <tiles:putAttribute name="scriptArea">

        <script src="/client/js/base/control-convert.js" type="text/javascript"></script>
        <script src="/client/js/base/validation.js" type="text/javascript"></script>
        <script src="/plugins/upload/image-upload/js/my.jquery-post-upload-styleX.js" type="text/javascript"></script>
        <script src="/plugins/My97DatePicker/function.js"></script>
        <script src="/plugins/My97DatePicker/WdatePicker.js"></script>
        <script src="/methods/update_mission.js" type="text/javascript"></script>

        <script src="/plugins/ueditor/ueditor.config.js"></script>
        <script src="/plugins/ueditor/ueditor.all.js"></script>

        <script>
            var ue;
            $(function() {

                //初始化部化页面信息
                ue = UE.getEditor('content', {
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
                        'link', //超链接
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

                // 显示图片
                $(document).on("click", ".active_img", function () {
                    var imageUrl = $(this).attr("src");
                    var arrayImage = imageUrl.split(";");
                    var arrayStr = "";
                    for (var k = 0; k < arrayImage.length; k++) {
                        arrayStr += "<img src='" + arrayImage[k] + "' style='width:100%; margin-top:20px;' />";
                    }
                    $("#image-detail").html(arrayStr);
                    $("#modal-showImage").modal();
                });

                // 删除图片
                $(document).on("click", ".delete_pic[obj='reImg']", function () {
                    $(this).parent().remove();
                    var pathArray = "";
                    $(".muti_box[obj='reImg']").find("img").each(function () {
                        var pathX = $(this).attr("src");
                        pathArray += pathArray ? ";" + pathX : pathX;
                    });
                    $("#txtReleaseImg").val(pathArray);
                });
            });
        </script>

    </tiles:putAttribute>
    <tiles:putAttribute name="mainArea">

        <!-- 顶部栏 begin -->
        <div class="panel panel-default main-hd">
            <div class="panel-heading">
                <div id="queryForm" class="fr mt-4">
                    <div class="fr pr5">
                        <div class="input-group input-group-sm w100 fl">
                        </div>
                    </div>
                </div>
                <button id="save" class="btn btn-success"style="width: 90px">保存</button>
                <button onclick="showInApp();" class="btn btn-success"style="width: 100px">查询效果</button>
            </div>
        </div>
        <!-- 顶部栏 end -->

        <%--主内容显示区 begin --%>
        <div class="main-bd">
            <div class="separator_line">
            </div>
            <br>
            <div class="collapse navbar-collapse">
                <span>
                    <label style="margin-right: 140px;color: black;font-size:large;"> 推广ID:</label>
                    <span id="missionId">${mission.missionId}</span>
                </span>
            </div>
            <br>
            <div class="collapse navbar-collapse">
                <span>
                    <label style="margin-right: 140px;color: black;font-size:large;"> 任务名:</label>
                    <input type="text" id="missionName"  value="${mission.missionName}" style="width: 500px;"/>
                </span>
            </div>
            <br>
            <div class="collapse navbar-collapse">
                <span>
                    <label style="margin-right: 125px;color: black;font-size:large;"> 任务状态:</label>
                    <select style="width: 170px;height: 25px;" id="missionStatus">
                        <option value="1" <c:if test="${mission.missionStatus == '1'}">selected</c:if>  >进行中</option>
                        <option value="0" <c:if test="${mission.missionStatus == '0'}">selected</c:if>  >已结束</option>
                    </select>
                </span>
            </div>
            <br>
            <div class="collapse navbar-collapse">
                <span>
                    <label style="margin-right: 125px;color: black;font-size:large;"> 是否显示:</label>
                    <select style="width: 170px;height: 25px;" id="showOrNot">
                        <option value="0" <c:if test="${mission.showOrNot == '0'}">selected</c:if>  >ios开启</option>
                        <option value="1" <c:if test="${mission.showOrNot == '1'}">selected</c:if>  >andriod开启</option>
                        <option value="2" <c:if test="${mission.showOrNot == '2'}">selected</c:if>  >全部开启</option>
                        <option value="3" <c:if test="${mission.showOrNot == '3'}">selected</c:if>  >全部关闭</option>
                    </select>
                </span>
            </div>
            <br>
            <div class="collapse navbar-collapse">
                <span>
                    <label style="margin-right: 125px;color: black;font-size:large;"> 商家名字:</label>
                    <input type="text" id="merchantName"  value="${mission.merchantName}"/>
                </span>
            </div>
            <br>
            <div class="collapse navbar-collapse">
                <span>
                    <label style="margin-right: 125px;color: black;font-size:large;"> 任务简介:</label>
                    <textarea id="merchantDetail" style="width: 500px;">${mission.merchantDetail}</textarea>
                </span>
            </div>
            <br>
            <div class="collapse navbar-collapse">
                <span>
                    <label style="margin-right: 125px;color: black;font-size:large;"> 任务奖励:</label>
                    <input type="text" id="missionReward"  value="${mission.missionReward}"/>
                </span>
            </div>
            <br>
            <div class="collapse navbar-collapse">
                <span>
                    <label style="margin-right: 125px;color: black;font-size:large;"> 额外奖励:</label>
                    <input type="text" id="missionExtraReward"  value="${mission.missionExtraReward}"/>
                </span>
            </div>
            <br>
            <div class="collapse navbar-collapse">
                <span>
                    <label style="margin-right: 125px;color: black;font-size:large;"> 参与人数:</label>
                    <input type="text" id="participantsNum"  value="${mission.participantsNum}"/>
                </span>
            </div>
            <br>
            <div class="collapse navbar-collapse">
                <span>
                    <label style="margin-right: 125px;color: black;font-size:large;"> 奖励人数:</label>
                    <input type="text" id="gainRewardNum"  value="${mission.gainRewardNum}"/>
                </span>
            </div>
            <br>
            <div class="collapse navbar-collapse">
                <span>
                    <label style="margin-right: 125px;color: black;font-size:large;"> 是否热门:</label>
                    <select style="width: 170px;height: 25px;" id="missionHot">
                        <option value="1" <c:if test="${mission.missionHot == '1'}">selected</c:if>  >热门</option>
                        <option value="0" <c:if test="${mission.missionHot == '0'}">selected</c:if>  >非热门</option>
                    </select>
                </span>
            </div>
            <br>
            <div class="collapse navbar-collapse">
                <span>
                    <label style="margin-right: 125px;color: black;font-size:large;"> 任务分类:</label>
                    <select style="width: 170px;height: 25px;" id="missionSort">
                        <c:forEach items="${sorts}" var="sort">
                            <option value="${sort.sortId}" <c:if test="${mission.missionSort == sort.sortId}">selected</c:if> >${sort.sortName}</option>
                        </c:forEach>
                    </select>
                </span>
            </div>
            <br>
            <div class="collapse navbar-collapse">
                <span>
                    <div  class="form-group col-xs-6">
                        <span style="display: inline;">
                            <button class="btn btn-success btn-sm" mode="button-upload-pic" uploadtype="image" style="margin-left: -10px;"
                                    action="/c/p/picture/upload" filesizelimit="128000"
                                    filetypelimit="jpg,png,gif,jpeg" filenums="banner">任务图标
                            </button>
                            <img src="${mission.missionImg}" class="banner_photo" id="oldImg" style="width: 80px;height: 80px;margin-left: 100px;">
                            <img class="loadingPic"  obj="banner_photo"  alt="" src="/client/img/loading.gif" style="width: 10px;height: 10px" />
                            <input type="hidden" id="missionImg" name="img" obj="banner_photo" />
                        </span>
                    </div>
                </span>
            </div>
            <br>
            <div class="collapse navbar-collapse">
                <span>
                    <div  class="form-group col-xs-6">
                        <span style="display: inline;">
                            <button class="btn btn-success btn-sm" mode="button-upload-pic" uploadtype="image"
                                    action="/c/p/picture/upload" filesizelimit="128000"
                                    filetypelimit="jpg,png,gif,jpeg" filenums="photo" style="margin-left: -10px;">广告图片
                            </button>
                            <img src="${mission.missionAdImg}" class="user_photo" id="oldAdImg" style="width: 80px;height: 80px;margin-left: 100px;">
                            <img class="loadingPic"  obj="user_photo"  alt="" src="/client/img/loading.gif" style="width: 10px;height: 10px" />
                            <input type="hidden" id="missionAdImg" name="img" obj="user_photo" />
                        </span>
                    </div>
                </span>
            </div>
            <br>
            <div class="collapse navbar-collapse">
                <span>
                    <label style="margin-right: 125px;color: black;font-size:large;"> 广告链接:</label>
                    <input type="text" id="missionUrl" value="${mission.missionUrl}"/>
                </span>
            </div>
            <br>
            <div class="collapse navbar-collapse">
                <span>
                    <label style="margin-right: 115px;color: black;font-size:large;"> 开始时间:</label>
                    <input type="text" style="margin-left: 10px" id = "startTime" value="${start}"  class="Wdate" onclick="WdatePicker({lang:'zh-cn',dateFmt:'yyyy-MM-dd HH:mm:ss '});"/>
                </span>
            </div>
            <br>
            <div class="collapse navbar-collapse">
                <span>
                    <label style="margin-right: 115px;color: black;font-size:large;"> 结束时间:</label>
                    <input type="text" style="margin-left: 10px" id = "endTime" value="${end}"  class="Wdate" onclick="WdatePicker({lang:'zh-cn',dateFmt:'yyyy-MM-dd HH:mm:ss '});"/>
                </span>
            </div>
            <br>
            <div class="collapse navbar-collapse">
                <span>
                    <label style="margin-right: 125px;color: black;font-size:large;"> 任务排序:</label>
                    <input type="text" id="missionOrder" value="${mission.missionOrder}"/>
                </span>
            </div>
            <br>
            <div class="collapse navbar-collapse">
                <span>
                    <label style="margin-right: 125px;color: black;font-size:large;"> 规则说明:</label>
                    <span>
                        <div  style="height: 700px;width: 900px;">
                            <script id="content" name="content"></script>
                        </div>
                    </span>
                </span>
            </div>
        </div>
        <%--主内容显示区 end --%>
    </tiles:putAttribute>
</tiles:insertTemplate>
