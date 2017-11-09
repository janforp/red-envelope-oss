<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page pageEncoding="UTF-8" %>
<tiles:insertTemplate template="/WEB-INF/views/base/layout/layout-default.jsp">
    <tiles:putAttribute name="title" value="添加兑换码红包"/>
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
        <script src="/methods/add_code_red.js" type="text/javascript"></script>

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
                <button class="btn btn-success" id="showInApp" onclick="showCodeInApp();" style="width: 100px">查看效果</button>
            </div>
        </div>
        <!-- 顶部栏 end -->
        <div class="main-bd">
            <div class="separator_line">
            </div>
            <br>
            <div class="collapse navbar-collapse">
                <span>
                    <label style="margin-right: 140px;color: black;font-size:large;"> 红包口令:</label>
                    <input type="text" id="redCode"  value="${red.redCode}" style="width: 500px;"/>
                    <button id="kouLin">随机生成口令</button>
                </span>
            </div>
            <br>
            <div class="collapse navbar-collapse">
                <span>
                    <label style="margin-right: 140px;color: black;font-size:large;"> 客户名称:</label>
                    <input type="text" id="customerName"  value="${red.customerName}" style="width: 500px;"/>
                </span>
            </div>
            <br>
            <div class="collapse navbar-collapse">
                <span>
                    <div  class="form-group col-xs-6">
                        <span style="display: inline;">
                            <button class="btn btn-success btn-sm" mode="button-upload-pic" uploadtype="image" style="margin-left: -10px;"
                                    action="/c/p/picture/upload" filesizelimit="128000"
                                    filetypelimit="jpg,png,gif,jpeg" filenums="photo">客户头像
                            </button>
                            <img src="${red.customerImg}" class="user_photo" id="oldCustomerImg" style="width: 80px;height: 80px;margin-left: 120px;">
                            <img class="loadingPic"  obj="user_photo"  alt="" src="/client/img/loading.gif" style="width: 10px;height: 10px" />
                            <input type="hidden" id="customerImg" name="img" obj="user_photo" />
                        </span>
                    </div>
                </span>
            </div>
            <br>
            <div class="collapse navbar-collapse">
                <span>
                    <label style="margin-right: 140px;color: black;font-size:large;"> 客户描述:</label>
                    <textarea id="customerDesc">${red.customerDesc}</textarea>
                </span>
            </div>
            <br>
            <div class="collapse navbar-collapse">
                <span>
                    <label style="margin-right: 140px;color: black;font-size:large;"> 奖励说明:</label>
                    <input type="text" id="awardDesc"  value="${red.awardDesc}" style="width: 500px;"/>
                </span>
            </div>
            <br>
            <div class="collapse navbar-collapse">
                <span>
                    <div  class="form-group col-xs-6">
                        <span style="display: inline;">
                            <button class="btn btn-success btn-sm" mode="button-upload-pic" uploadtype="image" style="margin-left: -10px;"
                                    action="/c/p/picture/upload" filesizelimit="128000"
                                    filetypelimit="jpg,png,gif,jpeg" filenums="banner">banner图
                            </button>
                            <img src="${red.customerBanner}" class="banner_photo" id="oldCustomerBanner" style="width: 80px;height: 80px;margin-left: 120px;">
                            <img class="loadingPic"  obj="banner_photo"  alt="" src="/client/img/loading.gif" style="width: 10px;height: 10px" />
                            <input type="hidden" id="customerBanner" name="img" obj="banner_photo" />
                        </span>
                    </div>
                </span>
            </div>
            <br>
            <div class="collapse navbar-collapse">
                <span>
                    <label style="margin-right: 140px;color: black;font-size:large;"> 红包链接:</label>
                    <input type="text" id="customerBannerUrl"  value="${red.customerBannerUrl}" style="width: 500px;"/>
                </span>
            </div>
            <br>
            <div class="collapse navbar-collapse">
                <span>
                    <label style="margin-right: 140px;color: black;font-size:large;"> 最大红包:</label>
                    <input type="text" id="redMax"  value="${red.redMax}" style="width: 500px;"/>
                    <span style="color: red;margin-left: 10px;">展示用</span>
                </span>
            </div>
            <br>
            <div class="collapse navbar-collapse">
                <span>
                    <label style="margin-right: 140px;color: black;font-size:large;"> 随机总数:</label>
                    <input type="text" id="redNumTotal"  value="${red.redNumTotal}" style="width: 500px;"/>
                    <span style="color: red;margin-left: 10px;">整数,若填写为100</span>
                </span>
            </div>
            <br>
            <div class="collapse navbar-collapse">
                <span>
                    <label style="margin-right: 140px;color: black;font-size:large;"> 金额上限:</label>
                    <input type="text" id="max"  style="width: 500px;"/>分
                    <span style="color: red;margin-left: 10px;">如:2000</span>
                </span>
            </div>
            <br>
            <div class="collapse navbar-collapse">
                <span>
                    <label style="margin-right: 140px;color: black;font-size:large;"> 金额下限:</label>
                    <input type="text" id="min"  style="width: 500px;"/>分
                    <span style="color: red;margin-left: 10px;">如:10</span>
                </span>
            </div>
            <br>
            <div class="collapse navbar-collapse">
                <span>
                    <label style="margin-right: 140px;color: black;font-size:large;"> 大额红包:</label>
                     <input type="text" id="bigRed" style="width: 500px;"/>
                    <span style="color: red;margin-left: 10px;">如:8888|60000|50000,中间用<span style="color: black;font-size: larger">英文</span>状态下的 ' | ' 分开</span>
                </span>
            </div>

            <br>
            <div class="collapse navbar-collapse">
                <span>
                    <label style="margin-right: 140px;color: black;font-size:large;"> 红包状态:</label>
                    <select style="width: 170px;height: 25px;" id="codeStatus">
                        <option value="0" <c:if test="${red.codeStatus == '0'}">selected</c:if>  >IOS开启</option>
                        <option value="1" <c:if test="${red.codeStatus == '1'}">selected</c:if>  >andriod开启</option>
                        <option value="2" <c:if test="${red.codeStatus == '2'}">selected</c:if>  >全部开启</option>
                        <option value="3" <c:if test="${red.codeStatus == '3'}">selected</c:if>  >全部关闭</option>
                    </select>
                </span>
            </div>
            <br>
            <div class="collapse navbar-collapse">
                <span>
                    <div style="width: 900px;">
                        <label style="margin-right: 140px;color: black;font-size:large;"> 领取规则:</label>
                    <script id="content" name="content"></script>
                    </div>
                </span>
            </div>
        </div>
        <%--主内容显示区 end --%>
    </tiles:putAttribute>
</tiles:insertTemplate>
