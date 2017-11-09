<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page pageEncoding="UTF-8" %>
<tiles:insertTemplate template="/WEB-INF/views/base/layout/layout-default.jsp">
    <tiles:putAttribute name="title" value="修改推广"/>
    <tiles:putAttribute name="enableConsole" value="true"/>
    <tiles:putAttribute name="enableSidebar" value="true"/>
    <tiles:putAttribute name="styleArea">
        <style>
            .separator_line {
                margin-top: 10px;
                height: 1px;
                background-color: #CCCCCC;
            }
            .table tbody td {
                text-align: center;
                white-space:nowrap;
            }
            .loadingPic {
                display: none;
            }

            .user_photo {
                border:solid 1px #ccc;
                width: 80px;
                height: 80px;
            }
        </style>
    </tiles:putAttribute>
    <tiles:putAttribute name="scriptArea">

        <script src="/client/js/base/control-convert.js" type="text/javascript"></script>
        <script src="/client/js/base/validation.js" type="text/javascript"></script>
        <script src="/plugins/upload/image-upload/js/my.jquery-post-upload-styleX.js" type="text/javascript"></script>
        <script src="/plugins/My97DatePicker/function.js"></script>
        <script src="/plugins/My97DatePicker/WdatePicker.js"></script>
        <script src="/methods/update_extend.js" type="text/javascript"></script>

        <script>
            var oldbannerImg = "${detail.customerBanner}";
            var oldadverstImg = "${detail.customerAdvert}";
            var extendId = "${detail.id}";
        </script>

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
                <button class="btn btn-success" onclick="saveRed(extendId,oldbannerImg,oldadverstImg)" style="width: 90px">保存</button>
                <button class="btn btn-success" id="showInApp" onclick="showExtendInApp();" style="width: 100px">查看效果</button>
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
                    <span>${detail.id}</span>
                </span>
            </div>
            <br>
            <div class="collapse navbar-collapse">
                <span>
                    <label style="margin-right: 140px;color: black;font-size:large;"> 客户ID:</label>
                    <span>${detail.customerId}</span>
                </span>
            </div>
            <br>
            <div class="collapse navbar-collapse">
                <span>
                    <label style="margin-right: 140px;color: black;font-size:large;"> 客户名:</label>
                    <input type="text" id="customerName" readonly value="${detail.customerName}"/>
                </span>
            </div>
            <br>
            <div class="collapse navbar-collapse">
                <span>
                    <label style="margin-right: 125px;color: black;font-size:large;"> 是否跳转:</label>
                    <select style="width: 170px;height: 25px;" id="isRedirect">
                        <option value="1" <c:if test="${detail.isRedirect == '1'}">selected</c:if>  >跳转</option>
                        <option value="0" <c:if test="${detail.isRedirect == '0'}">selected</c:if>  >不跳转</option>
                    </select>
                </span>
            </div>
            <br>
            <div class="collapse navbar-collapse">
                <span>
                    <label style="margin-right: 125px;color: black;font-size:large;"> 跳转链接:</label>
                    <input type="text" id="redirectUrl" value="${detail.redirectUrl}"/>
                </span>
            </div>
            <br>
            <div class="collapse navbar-collapse">
                <span>
                    <label style="margin-right: 125px;color: black;font-size:large;"> 等待时间:</label>
                    <input type="text" id="waitTime" value="${detail.waitTime}"/>
                </span>
            </div>
            <br>
            <div class="collapse navbar-collapse">
                <span>
                    <label style="margin-right: 125px;color: black;font-size:large;"> 推广说明:</label>
                    <input type="text" id="extendDesc" value="${detail.extendDesc}"/>
                </span>
            </div>
            <br>
            <div class="collapse navbar-collapse">
                <span>
                    <div  class="form-group col-xs-6">
                        <span style="display: inline;">
                            <button class="btn btn-success btn-sm" mode="button-upload-pic" uploadtype="image"
                                    id = "updateuploadThumb"
                                    action="/c/p/picture/upload" filesizelimit="128000"
                                    filetypelimit="jpg,png,gif,jpeg" filenums="banner">banner图
                            </button>
                            <img src="${detail.customerBanner}" class="banner_photo" id="addthumb2" style="width: 80px;height: 80px">
                            <img class="loadingPic"  obj="banner_photo"  alt="" src="/client/img/loading.gif" style="width: 10px;height: 10px" />
                            <input type="hidden" id="add_banner" name="img" obj="banner_photo" />
                        </span>
                    </div>
                </span>
            </div>
            <br>
            <div class="collapse navbar-collapse">
                <span>
                    <label style="margin-right: 90px;color: black;font-size:large;"> banner链接:</label>
                    <input type="text" id="bannerUrl" value="${detail.customerBannerUrl}">
                </span>
            </div>
            <br>
            <div class="collapse navbar-collapse">
                <span>
                    <div  class="form-group col-xs-6">
                        <span style="display: inline;">
                            <button class="btn btn-success btn-sm" mode="button-upload-pic" uploadtype="image"
                                    action="/c/p/picture/upload" filesizelimit="128000"
                                    filetypelimit="jpg,png,gif,jpeg" filenums="photo">广  告  图
                            </button>
                            <img src="${detail.customerAdvert}" class="user_photo" id="" style="width: 80px;height: 80px">
                            <img class="loadingPic"  obj="user_photo"  alt="" src="/client/img/loading.gif" style="width: 10px;height: 10px" />
                            <input type="hidden" id="add_adverst" name="img" obj="user_photo" />
                        </span>
                    </div>
                </span>
            </div>
            <br>
            <div class="collapse navbar-collapse">
                <span>
                    <label style="margin-right: 110px;color: black;font-size:large;"> 广告链接:</label>
                    <input type="text" id="adverstUrl" value="${detail.customerAdvertUrl}">
                </span>
            </div>
            <br>
            <div class="collapse navbar-collapse">
                <span>
                    <label style="margin-right: 150px;color: black;font-size:large;"> 状态:</label>
                    <select id="status" style="width: 170px;height: 25px;">
                        <option value="1" <c:if test="${detail.customStatusDesc == '进行中'}">selected</c:if> >进行中</option>
                        <option value="0" <c:if test="${detail.customStatusDesc == '已结束'}">selected</c:if> >已结束</option>
                    </select>
                </span>
            </div>
            <br>
            <div class="collapse navbar-collapse">
                <span>
                    <label style="margin-right: 115px;color: black;font-size:large;"> 开始时间:</label>
                    <input type="text" id = "startTime" value="${detail.showStartTime}"  class="Wdate" onclick="WdatePicker({lang:'zh-cn',dateFmt:'yyyy-MM-dd HH:mm:ss '});"/>
                </span>
            </div>
            <br>
            <div class="collapse navbar-collapse">
                <span>
                    <label style="margin-right: 115px;color: black;font-size:large;"> 结束时间:</label>
                    <input type="text" id = "endTime" value="${detail.showEndTime}"  class="Wdate" onclick="WdatePicker({lang:'zh-cn',dateFmt:'yyyy-MM-dd HH:mm:ss '});"/>
                </span>
            </div>
            <br>
            <div class="collapse navbar-collapse">
                <span>
                    <label style="margin-right: 115px;color: black;font-size:large;"> 是否热门:</label>
                    <select style="width: 172px;height: 25px;" id="hot">
                        <option value="1" <c:if test="${detail.isHot == '1'}">selected</c:if> >热门</option>
                        <option value="0" <c:if test="${detail.isHot == '0'}">selected</c:if> >非热门</option>
                    </select>
                </span>
            </div>
            <br>
            <div class="collapse navbar-collapse">
                <span>
                    <label style="margin-right: 115px;color: black;font-size:large;"> 中奖概率:</label>
                    <input type="text" id="chance" value="${detail.redChance}"  /><span style="color: red;margin-left: 10px;">%,请输入 0-100 之间的整数</span>
                </span>
            </div>
            <br>
            <div class="collapse navbar-collapse">
                <span>
                    <div style="width: 900px;">
                        <label style="margin-right: 140px;color: black;font-size:large;"> 步骤说明:</label>
                    <script id="content" name="content">${detail.stepRule}</script>
                    </div>
                </span>
            </div>
        </div>
        <%--主内容显示区 end --%>
    </tiles:putAttribute>
</tiles:insertTemplate>
