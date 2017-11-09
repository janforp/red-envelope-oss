<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page pageEncoding="UTF-8" %>
<tiles:insertTemplate template="/WEB-INF/views/base/layout/layout-default.jsp">
    <tiles:putAttribute name="title" value="修改发现"/>
    <tiles:putAttribute name="enableConsole" value="true"/>
    <tiles:putAttribute name="enableSidebar" value="true"/>
    <tiles:putAttribute name="styleArea">
        <link rel="stylesheet" type="text/css" href="/styles/update_mission.css">
    </tiles:putAttribute>
    <tiles:putAttribute name="scriptArea">

        <script src="/client/js/base/control-convert.js" type="text/javascript"></script>
        <script src="/client/js/base/validation.js" type="text/javascript"></script>
        <script src="/plugins/upload/image-upload/js/my.jquery-post-upload-styleX.js" type="text/javascript"></script>

        <script src="/methods/update_or_add_discover.js" type="text/javascript"></script>


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
                    <label style="margin-left: 60px;margin-right:160px;color: black;font-size:large;"> ID:</label>
                    <span id="discoverId">${discover.discoverId}</span>
                </span>
            </div>
            <br>
            <div class="collapse navbar-collapse">
                <span>
                    <label style="margin-right: 160px;color: black;font-size:large;">发现标题:</label>
                    <input type="text" id="discoverTitle"  value="${discover.discoverTitle}" style="width: 500px;"/>
                </span>
            </div>
            <br>
            <div class="collapse navbar-collapse">
                <span>
                    <label style="margin-right: 160px;color: black;font-size:large;"> 发现状态:</label>
                    <select style="width: 170px;height: 25px;" id="discoverStatus">
                        <option value="0" <c:if test="${discover.discoverStatus == '0'}">selected</c:if>  >IOS开启</option>
                        <option value="1" <c:if test="${discover.discoverStatus == '1'}">selected</c:if>  >Andriod开启</option>
                        <option value="2" <c:if test="${discover.discoverStatus == '2'}">selected</c:if>  >全部开启</option>
                        <option value="3" <c:if test="${discover.discoverStatus == '3'}">selected</c:if>  >全部关闭</option>
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
                                    filetypelimit="jpg,png,gif,jpeg" filenums="banner">发现图标
                            </button>
                            <img src="${discover.discoverImg}" class="banner_photo" id="oldImg" style="width: 80px;height: 80px;margin-left: 140px;">
                            <img class="loadingPic"  obj="banner_photo"  alt="" src="/client/img/loading.gif" style="width: 10px;height: 10px" />
                            <input type="hidden" id="discoverImg" name="img" obj="banner_photo" />
                        </span>
                    </div>
                </span>
            </div>

            <br>
            <div class="collapse navbar-collapse">
                <span>
                    <label style="margin-right: 160px;color: black;font-size:large;"> 发现描述:</label>
                    <textarea id="discoverDescription">${discover.discoverDescription}</textarea>
                </span>
            </div>

            <br>
            <div class="collapse navbar-collapse">
                <span>
                    <label style="margin-right: 160px;color: black;font-size:large;"> 跳转链接:</label>
                    <input type="text" id="discoverUrl" value="${discover.discoverUrl}"/>
                </span>
            </div>

            <br>
            <div class="collapse navbar-collapse">
                <span>
                    <label style="margin-right: 160px;color: black;font-size:large;"> 发现排序:</label>
                    <input type="text" id="discoverOrder" value="${discover.discoverOrder}"/>
                </span>
            </div>

        </div>
        <%--主内容显示区 end --%>
    </tiles:putAttribute>
</tiles:insertTemplate>
