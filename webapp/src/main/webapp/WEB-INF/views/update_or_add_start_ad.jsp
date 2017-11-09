<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page pageEncoding="UTF-8" %>
<tiles:insertTemplate template="/WEB-INF/views/base/layout/layout-default.jsp">
    <tiles:putAttribute name="title" value="修改启动广告"/>
    <tiles:putAttribute name="enableConsole" value="true"/>
    <tiles:putAttribute name="enableSidebar" value="true"/>
    <tiles:putAttribute name="styleArea">
        <link rel="stylesheet" type="text/css" href="/styles/update_mission.css">
    </tiles:putAttribute>
    <tiles:putAttribute name="scriptArea">

        <script src="/client/js/base/control-convert.js" type="text/javascript"></script>
        <script src="/client/js/base/validation.js" type="text/javascript"></script>
        <script src="/plugins/upload/image-upload/js/my.jquery-post-upload-styleX.js" type="text/javascript"></script>

        <script src="/methods/update_or_add_start_ad.js" type="text/javascript"></script>


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
                    <label style="margin-left: 20px;margin-right:140px;color: black;font-size:large;"> ID:</label>
                    <span id="adId">${ad.adId}</span>
                </span>
            </div>
            <br>
            <div class="collapse navbar-collapse">
                <span>
                    <label style="margin-right: 140px;color: black;font-size:large;">标题:</label>
                    <input type="text" id="adTitle"  value="${ad.adTitle}" style="width: 500px;"/>
                </span>
            </div>
            <br>
            <div class="collapse navbar-collapse">
                <span>
                    <div  class="form-group col-xs-6">
                        <span style="display: inline;">
                            <button class="btn btn-success btn-sm" mode="button-upload-pic" uploadtype="image" style="margin-left: -30px;"
                                    action="/c/p/picture/upload" filesizelimit="128000"
                                    filetypelimit="jpg,png,gif,jpeg" filenums="banner">图片
                            </button>
                            <img src="${ad.adImg}" class="banner_photo" id="oldImg" style="width: 80px;height: 80px;margin-left: 140px;">
                            <img class="loadingPic"  obj="banner_photo"  alt="" src="/client/img/loading.gif" style="width: 10px;height: 10px" />
                            <input type="hidden" id="adImg" name="img" obj="banner_photo" />
                        </span>
                    </div>
                </span>
            </div>
            <br>
            <div class="collapse navbar-collapse">
                <span>
                    <label style="margin-right: 140px;color: black;font-size:large;"> 链接:</label>
                    <input type="text" id="adUrl" value="${ad.adUrl}"/>
                </span>
            </div>
            <br>
            <div class="collapse navbar-collapse">
                <span>
                    <label style="margin-right: 140px;color: black;font-size:large;">状态:</label>
                    <select style="width: 170px;height: 30px;" id="adStatus">
                        <option value="0" <c:if test="${ad.adStatus == '0'}">selected</c:if>  >IOS开启</option>
                        <option value="1" <c:if test="${ad.adStatus == '1'}">selected</c:if>  >andriod开启</option>
                        <option value="2" <c:if test="${ad.adStatus == '2'}">selected</c:if>  >全部开启</option>
                        <option value="3" <c:if test="${ad.adStatus == '3'}">selected</c:if>  >全部关闭</option>
                    </select>
                </span>
            </div>

            <br>
            <div class="collapse navbar-collapse">
                <span>
                    <label style="margin-right: 140px;color: black;font-size:large;">跳过:</label>
                    <select style="width: 170px;height: 25px;" id="adSkip">
                        <option value="0" <c:if test="${ad.adSkip == '0'}">selected</c:if>  >能</option>
                        <option value="1" <c:if test="${ad.adSkip == '1'}">selected</c:if>  >不能</option>
                    </select>
                </span>
            </div>
            <br>
            <div class="collapse navbar-collapse">
                <span>
                    <label style="margin-right: 140px;color: black;font-size:large;"> 时间:</label>
                    <input type="text" id="adDuration" value="${ad.adDuration}"/>秒
                </span>
            </div>
        </div>
        <%--主内容显示区 end --%>
    </tiles:putAttribute>
</tiles:insertTemplate>
