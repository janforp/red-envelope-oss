<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page pageEncoding="UTF-8" %>
<tiles:insertTemplate template="/WEB-INF/views/base/layout/layout-default.jsp">
    <tiles:putAttribute name="title" value="修改${sort.sortName}"/>
    <tiles:putAttribute name="enableConsole" value="true"/>
    <tiles:putAttribute name="enableSidebar" value="true"/>
    <tiles:putAttribute name="styleArea">
        <link rel="stylesheet" type="text/css" href="/styles/update_sort.css">
    </tiles:putAttribute>
    <tiles:putAttribute name="scriptArea">
        <script src="/methods/update_sort.js" type="text/javascript"></script>
        <script src="${BASE_PATH}/plugins/upload/image-upload/js/my.jquery-post-upload-styleX.js" type="text/javascript"></script>
        <script src="${BASE_PATH}/client/js/base/control-convert.js" type="text/javascript"></script>
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
                <button class="btn btn-primary">添加分类</button>
            </div>
        </div>
        <div class="main-bd">
            <div class="separator_line">
            </div>
            <br>
            <div class="collapse navbar-collapse">
                <span>
                    <span style="color: red;font-size: 25px;position:relative; top:7px;margin-right: 10px;">*</span>
                    <label style="margin-right: 20px">分类名称:</label>
                    <input type="text" id="sortName" value="${sort.sortName}"  style="height: 30px;width: 140px;margin-left: 40px;"/>
                </span>
            </div>
            <br>
            <div class="collapse navbar-collapse">
                <span>
                    <span style="color: red;font-size: 25px;position:relative; top:7px;margin-right: 10px;">*</span>
                    <label style="margin-right: 20px">分类排序:</label>
                    <input type="text" id="sortOrder" value="${sort.sortOrder}"  style="height: 30px;width: 140px;margin-left: 40px;"/>
                </span>
            </div>
            <br>
            <div  class="collapse navbar-collapse">
                <span style="display: inline;">
                    <span style="color: red;font-size: 25px;position:relative; top:7px;margin-right: 10px">*</span>
                    <label style=" margin-right: 25px">缩略图:</label>
                    <img src="${sort.sortImg}" class="user_photo" id="oldSortImg" style="width: 60px;height: 60px;margin-left: 50px;">

                    <button class="btn btn-success btn-sm" mode="button-upload-pic" uploadtype="image"
                            action="/c/p/picture/upload" filesizelimit="128000"
                            filetypelimit="jpg,png,gif,jpeg" filenums="photo">上传图片
                    </button>
                    <img class="loadingPic"  obj="user_photo" style="width:15px;height:15px;"  alt="" src="/client/img/loading.gif" />
                    <input type="hidden" id="sortImg" name="img" obj="user_photo" />
                </span>
            </div>
        </div>
        <%--主内容显示区 end --%>
    </tiles:putAttribute>
</tiles:insertTemplate>
