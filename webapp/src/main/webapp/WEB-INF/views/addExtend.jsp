<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page pageEncoding="UTF-8" %>
<tiles:insertTemplate template="/WEB-INF/views/base/layout/layout-default.jsp">
    <tiles:putAttribute name="title" value="新增推广"/>
    <tiles:putAttribute name="enableConsole" value="true"/>
    <tiles:putAttribute name="enableSidebar" value="true"/>
    <tiles:putAttribute name="styleArea">
        <link rel="stylesheet" type="text/css" href="/styles/add_extend.css">
    </tiles:putAttribute>
    <tiles:putAttribute name="scriptArea">

        <script src="/client/js/base/control-convert.js" type="text/javascript"></script>
        <script src="/client/js/base/validation.js" type="text/javascript"></script>
        <script src="/plugins/upload/image-upload/js/my.jquery-post-upload-styleX.js" type="text/javascript"></script>
        <script src="/plugins/My97DatePicker/function.js"></script>
        <script src="/plugins/My97DatePicker/WdatePicker.js"></script>
        <script src="/methods/addExtend.js" type="text/javascript"></script>

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
                <button class="btn btn-success" id ="save" style="width: 90px">保存</button>
                <button class="btn btn-success" id="showInApp" onclick="showExtendInApp();" style="width: 100px">查看效果</button>
            </div>
        </div>
        <!-- 顶部栏 end -->

        <%--主内容显示区 begin --%>
        <div class="main-bd">
            <br>
            <div class="collapse navbar-collapse">
                <span>
                    <div  class="form-group col-xs-6">
                    <label style="margin-right: 90px;color: black;"> 客&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;户:</label>
                    <select style="width: 172px;height: 30px;" id="customId">
                        <option value="请选择">请选择</option>
                        <c:forEach items="${customs}" var="c">
                            <option value="${c.customerId}">${c.customerId}==${c.customerName}</option>
                        </c:forEach>
                    </select>
                        </div>
                </span>
            </div>
            <br>
            <div class="collapse navbar-collapse">
                <span>
                    <div  class="form-group col-xs-6">
                    <label style="margin-right: 90px;color: black;"> 是否跳转:</label>
                    <select style="width: 172px;height: 30px;" id="idDirect">
                        <option value="0">不跳转</option>
                        <option value="1">跳转</option>
                    </select>
                        </div>
                </span>
            </div>
            <br>
            <div class="collapse navbar-collapse">
                <span>
                    <div  class="form-group col-xs-6">
                    <label style="margin-right: 90px;color: black;"> 跳转链接:</label>
                     <input type="text" id="redirectUrl" />
                        </div>
                </span>
            </div>
            <br>
            <div class="collapse navbar-collapse">
                <span>
                    <div  class="form-group col-xs-6">
                    <label style="margin-right: 90px;color: black;"> 等待时间:</label>
                     <input type="text" id="waitTime" />
                        </div>
                </span>
            </div>
            <br>
            <div class="collapse navbar-collapse">
                    <span>
                        <div  class="form-group col-xs-6">
                        <label style="margin-right: 90px;color: black;"> 推广说明:</label>
                         <input type="text" id="extendDesc" />
                            </div>
                    </span>
            </div>
            <br>
            <div class="collapse navbar-collapse">
                <span>
                    <div  class="form-group col-xs-6">
                        <span style="display: inline;">
                            <label style="margin-right: 80px;color: black;"> banner图:</label>
                            <button class="btn btn-success btn-sm" mode="button-upload-pic" uploadtype="image"
                                    id = "updateuploadThumb"
                                    action="/c/p/picture/upload" filesizelimit="128000"
                                    filetypelimit="jpg,png,gif,jpeg" filenums="banner">banner图
                            </button>
                            <img src="/client/img/plz_upload_img.png" class="banner_photo" id="addthumb2" style="width: 80px;height: 80px;margin-left: 20px;">
                            <img class="loadingPic"  obj="banner_photo"  alt="" src="/client/img/loading.gif" style="width: 10px;height: 10px" />
                            <input type="hidden" id="add_banner" name="img" obj="banner_photo" />
                            <span style="color: red;margin-left: 60px;">建议尺寸:宽600像素,高200像素</span>
                        </span>
                    </div>
                </span>
            </div>
            <br>
            <div class="collapse navbar-collapse">
                <span>
                    <div  class="form-group col-xs-6">
                    <label style="margin-right: 70px;color: black;"> baner链接:</label>
                    <input type="text" id="bannerUrl" />
                        </div>
                </span>
            </div>
            <br>
            <div class="collapse navbar-collapse">
                <span>
                    <div  class="form-group col-xs-6">
                        <span style="display: inline;">
                            <label style="margin-right: 80px;color: black;"> 广&nbsp;&nbsp;告&nbsp;&nbsp;&nbsp;&nbsp;图:</label>
                            <button  class="btn btn-success btn-sm" mode="button-upload-pic" uploadtype="image"
                                    action="/c/p/picture/upload" filesizelimit="128000"
                                    filetypelimit="jpg,png,gif,jpeg" filenums="photo">广&nbsp;&nbsp;告&nbsp;&nbsp;图
                            </button>
                            <img src="/client/img/plz_upload_img.png" class="user_photo" style="width: 80px;height: 80px;margin-left: 20px;">
                            <img class="loadingPic"  obj="user_photo"  alt="" src="/client/img/loading.gif" style="width: 10px;height: 10px" />
                            <input type="hidden" id="add_adverst" name="img" obj="user_photo" />
                            <span style="color: red;margin-left: 60px;">建议尺寸:宽600像素,高200像素</span>
                        </span>
                    </div>
                </span>
            </div>
            <br>
            <div class="collapse navbar-collapse">
                <span>
                    <div  class="form-group col-xs-6">
                    <label style="margin-right: 90px;color: black;"> 广告链接:</label>
                     <input type="text" id="adverstUrl" />
                        </div>
                </span>
            </div>
            <br>
            <div class="collapse navbar-collapse">
                <span>
                    <div  class="form-group col-xs-6">
                    <label style="margin-right: 90px;color: black;"> 随机个数:</label>
                     <input type="text" id="num" />
                     <span style="color: red;margin-left: 10px;">整数,若填写为100</span>
                        </div>
                </span>
            </div>
            <br>
            <div class="collapse navbar-collapse">
                <span>
                    <div  class="form-group col-xs-6">
                    <label style="margin-right: 90px;color: black;"> 大额红包:</label>
                    <input type="text" id="big" />
                    <span style="color: red;margin-left: 10px;">如:888|666|555,中间用<span style="color: black;font-size: larger">英文</span>状态下的 ' | ' 分开</span>
                        </div>
                </span>
            </div>
            <div class="collapse navbar-collapse">
                <span>
                    <div  class="form-group col-xs-6">
                    <%--<label style="margin-right: 90px;color: black;"> 状&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;态:</label>--%>
                    <input type="hidden" id="status" readonly value="进行中" />
                        </div>
                </span>
            </div>
            <br>
            <div class="collapse navbar-collapse">
                <span>
                    <div  class="form-group col-xs-6">
                    <label style="margin-right: 90px;color: black;"> 开始时间:</label>
                    <input type="text" id = "startTime" style="width: 193px;height: 33px;" class="Wdate" onclick="WdatePicker({lang:'zh-cn',dateFmt:'yyyy-MM-dd HH:mm:ss '});"/>
                        </div>
                </span>
            </div>
            <br>
            <div class="collapse navbar-collapse">
                <span>
                    <div  class="form-group col-xs-6">
                    <label style="margin-right: 90px;color: black;"> 结束时间:</label>
                    <input type="text" id = "endTime" style="width: 193px;height: 33px;" class="Wdate" onclick="WdatePicker({lang:'zh-cn',dateFmt:'yyyy-MM-dd HH:mm:ss '});"/>
                        </div>
                </span>
            </div>
            <br>
            <div class="collapse navbar-collapse">
                <span>
                    <div  class="form-group col-xs-6">
                    <label style="margin-right: 90px;color: black;"> 是否热门:</label>
                    <select style="width: 193px;height: 33px;" id="hot">
                        <option value="1">热门</option>
                        <option value="0">非热门</option>
                    </select>
                        </div>
                </span>
            </div>
            <br>
            <div class="collapse navbar-collapse">
                <span>
                    <div  class="form-group col-xs-6">
                    <label style="margin-right: 90px;color: black;"> 中奖概率:</label>
                    <input type="text" id="chance"  /><span style="color: red;margin-left: 10px;">请输入 0-100 之间的整数</span>
                        </div>
                </span>
            </div>

            <br>
            <div class="collapse navbar-collapse">
                <span>
                    <div style="width: 900px;">
                        <label style="margin-right: 140px;color: black;font-size:large;"> 步骤说明:</label>
                    <script id="content" name="content"></script>
                    </div>
                </span>
            </div>
            </div>
        </div>
        <%--主内容显示区 end --%>
    </tiles:putAttribute>
</tiles:insertTemplate>
