<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page pageEncoding="UTF-8" %>
<tiles:insertTemplate template="/WEB-INF/views/base/layout/layout-default.jsp">
    <tiles:putAttribute name="title" value="测试"/>
    <tiles:putAttribute name="enableConsole" value="true"/>
    <tiles:putAttribute name="enableSidebar" value="true"/>
    <tiles:putAttribute name="styleArea">
<style>
    .closeImg {
        margin-left: 83px;
        font-size: 21px;
        font-weight: bold;
        line-height: 1;
        color: #000;
        text-shadow: 0 1px 0 #fff;
        filter: alpha(opacity=20);
        opacity: .2;
        cursor:pointer;
    }
</style>
    </tiles:putAttribute>
    <tiles:putAttribute name="scriptArea">
        <script src="/client/js/base/control-convert.js" type="text/javascript"></script>
        <script src="/plugins/upload/image-upload/js/image-upload.js" type="text/javascript"></script>
        <script>

            $(document).on("click","span.closeImg",function () {
                var img = $(this).parent().find(".img-upload").attr("src", "/client/img/add_img.png");
                img.attr("value", "");
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
            </div>
        </div>
        <!-- 顶部栏 end -->

        <%--主内容显示区 begin --%>
        <div class="main-bd">
            <div class="separator_line">
            </div>
            <br>

            <div class="row">
                <div style="width: 100px;height: 100px; margin: 20px;">
                    <span class="closeImg">&times;</span>
                    <img class="img-upload" src="/client/img/add_img.png" mode="button-upload-pic" style="width: 100px;height: 100px; margin-top: -23px; margin-right: -10px" action="/c/p/picture/upload" filesizelimit="128000"
                         filetypelimit="jpg,png,gif,jpeg" obj="1" value="" >
                </div>

                <div style="width: 100px;height: 100px; margin: 20px;">
                    <span class="closeImg">&times;</span>
                    <img class="img-upload" src="/client/img/add_img.png" mode="button-upload-pic" style="width: 100px;height: 100px; margin-top: -23px; margin-right: -10px" action="/c/p/picture/upload" filesizelimit="128000"
                         filetypelimit="jpg,png,gif,jpeg" obj="2" value="" >
                </div>
                <%--<div class="form-group col-xs-6">--%>
                    <%--<img class="img-upload" src="/client/img/add_img.png" mode="button-upload-pic" style="width: 100px;height: 100px;" action="/c/p/picture/upload" filesizelimit="128000"--%>
                         <%--filetypelimit="jpg,png,gif,jpeg" obj="2" >--%>
                <%--</div>--%>
            </div>

        </div>
        <%--主内容显示区 end --%>
    </tiles:putAttribute>
</tiles:insertTemplate>
