<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page pageEncoding="UTF-8" %>
<tiles:insertTemplate template="/WEB-INF/views/base/layout/layout-default.jsp">
    <tiles:putAttribute name="title" value="积分墙app列表"/>
    <tiles:putAttribute name="enableConsole" value="true"/>
    <tiles:putAttribute name="enableSidebar" value="true"/>
    <tiles:putAttribute name="styleArea">
        <link rel="stylesheet" type="text/css" href="/styles/mission_sort.css">
        <link rel="stylesheet" type="text/css" href="/client/css/template/base.css">
        <style>
            .loadingPic {
                display: none;
            }
            td img {
                width: 60px;
            }
        </style>
    </tiles:putAttribute>
    <tiles:putAttribute name="scriptArea">
        <script src="/methods/integralWall/app-list.js" type="text/javascript"></script>
        <script src="/plugins/My97DatePicker/function.js"></script>
        <script src="/plugins/My97DatePicker/WdatePicker.js"></script>

        <script src="/client/js/base/control-convert.js" type="text/javascript"></script>
        <script src="/plugins/upload/image-upload/js/my.jquery-post-upload-styleX.js" type="text/javascript"></script>
    </tiles:putAttribute>
    <tiles:putAttribute name="mainArea">
        <!-- 顶部栏 begin -->
        <div class="panel panel-default main-hd">
            <div class="panel-heading">
                <span class="pageTitle">积分墙app列表</span>
                <span class="searchTitle">app名称:</span>
                <input type="text" id = "appName"/>
                <span class="searchTitle">市场:</span>
                <select id="market" style="width: 100px;height: 40px;margin-left: 10px;">
                    <option value="">请选择</option>
                    <c:forEach items="${markets}" var="market">
                        <option value="${market.marketId}">${market.marketName}</option>
                    </c:forEach>
                </select>
                <span class="searchTitle">包名:</span>
                <input type="text" id = "packageName"/>

                <input type="text" id="pageSize" style="margin-left: 25px;margin-right: 25px;height: 37px;width: 150px;" placeholder="每页行数,默认10">
                <button class="btn btn-default" type="button" id="listByPageSize">查询</button>
            </div>
        </div>

        <div class="panel panel-default main-hd">
            <div class="panel-heading">
                <button id="add"
                        class="btn btn-default btn-sm navbar-btn"
                        data-toggle="modal"
                        data-target="#modal-add-banner"
                        data-backdrop="static"
                        data-keyboard="false"
                        role="button">
                    添加
                </button>
                <button id="edit" style="display:none;"
                        class="btn btn-default btn-sm navbar-btn"
                        data-toggle="modal"
                        data-target="#modal-edit-banner"
                        data-backdrop="static"
                        data-keyboard="false"
                        role="button">
                    隐藏修改
                </button>
                <button class="btn btn-default btn-sm navbar-btn" onclick='editApp();'>修改</button>
                <button class="btn btn-default btn-sm navbar-btn" onclick='deleteApp();'>删除</button>
                <button class="btn btn-default btn-sm navbar-btn" onclick='showKeywords();'>关键词</button>
            </div>
        </div>
        <!-- 顶部栏 end -->

        <%--主内容显示区 begin--%>
        <div class="main-bd">
            <div class="separator_line">
            </div>
            <br>
            <div>
                <table class="table table-striped table-hover table-bordered table-navbar-no-bottom">
                    <thead>

                    <tr>
                        <th>选择</th>
                        <th>序号</th>
                        <th>名称</th>
                        <th>图片</th>
                        <th>市场</th>
                        <th>包名</th>
                        <th>大小(M)</th>
                    </tr>
                    </thead>
                    <tbody id="redBody">

                    <tfoot>
                    <tr>
                        <td colspan="10">
                            <button id="pageBefore" style="background-color: white;"><span style="color: blue;font-size: large">上一页</span></button>
                            <span id="pageNow">0</span>
                            <span style="margin-left: 5px;margin-right: 5px;">/</span>
                            <span id="totalPage">0</span>
                            <button id="pageAfter" style="background-color: white;"><span style="color: blue;font-size: large">下一页</span></button>
                        </td>
                    </tr>
                    </tfoot>
                </table>
            </div>
        </div>


        <!-- 添加 begin -->
        <div id="modal-add-banner" aria-labelledby="modal-add-banner-title" class="modal fade" tabindex="-1"
             role="dialog" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                        <h4 class="modal-title">添加APP</h4>
                    </div>
                    <div class="modal-body">
                        <div class="row">
                            <div class="form-group col-xs-6">
                                <label for="add_name">
                                    app名称
                                </label>
                                <input type="text" class="form-control" id="add_name">
                            </div>
                        </div>
                        <div class="row">
                            <div class="form-group col-xs-6">
                                <img src="" class="banner_photo" id="addOldImg" style="width: 80px;height: 80px;">
                                <input type="hidden" id="add_img" name="add_img" obj="banner_photo" />
                                <button class="btn btn-success btn-sm" mode="button-upload-pic" uploadtype="image"
                                        style="margin-left: 10px;" action="/c/p/picture/upload" filesizelimit="128000"
                                        filetypelimit="jpg,png,gif,jpeg" filenums="banner">
                                    <spring:message code="oss.banner.img.info"/>
                                </button>
                                <img class="loadingPic" obj="banner_photo" alt="" src="/client/img/loading.gif"
                                     style="margin-left: 10px; width: 20px; height: 20px;" />
                            </div>
                        </div>
                        <div class="row">
                            <div class="form-group col-xs-6">
                                <label for="add_package">
                                    包名
                                </label>
                                <input type="text" class="form-control" id="add_package">
                            </div>
                            <div class="form-group col-xs-6">
                                <label for="add_market">
                                    市场
                                </label>
                                <select id="add_market" class="form-control">
                                    <c:forEach items="${markets}" var="market">
                                        <option value="${market.marketId}">${market.marketName}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                        <div class="row">
                            <div class="form-group col-xs-6">
                                <label for="add_size">
                                    大小(兆)
                                </label>
                                <input type="text" class="form-control" id="add_size">
                            </div>
                            <div class="form-group col-xs-6">
                                <label for="add_desc">
                                    备注
                                </label>
                                <input type="text" class="form-control" id="add_desc">
                            </div>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default auto-focus-btn" data-dismiss="modal" >
                            取消</button>
                        <button type="button" class="btn btn-success add_submit_btn"  onclick="addApp();">
                            确定</button>
                    </div>
                </div>
            </div>
        </div>
        <!-- 添加 end -->

        <!-- 修改 begin -->
        <div id="modal-edit-banner" aria-labelledby="modal-add-banner-title" class="modal fade" tabindex="-1"
             role="dialog" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                        <h4 class="modal-title">修改APP</h4>
                    </div>
                    <div class="modal-body">
                        <div class="row">
                            <div class="form-group col-xs-6">
                                <label for="edit_name">
                                    app名称
                                </label>
                                <input type="text" class="form-control" id="edit_name">
                            </div>
                        </div>
                        <div class="row">
                            <div class="form-group col-xs-6">
                                <img src="" class="banner_photo" id="oldImg" style="width: 80px;height: 80px;">
                                <input type="hidden" id="edit_img" obj="banner_photo" />
                                <button class="btn btn-success btn-sm" mode="button-upload-pic" uploadtype="image"
                                        style="margin-left: 10px;" action="/c/p/picture/upload" filesizelimit="128000"
                                        filetypelimit="jpg,png,gif,jpeg" filenums="banner">
                                    <spring:message code="oss.banner.img.info"/>
                                </button>
                                <img class="loadingPic" obj="banner_photo" alt="" src="/client/img/loading.gif"
                                     style="margin-left: 10px; width: 20px; height: 20px;" />
                            </div>
                        </div>
                        <div class="row">
                            <div class="form-group col-xs-6">
                                <label for="edit_package">
                                    包名
                                </label>
                                <input type="text" class="form-control" id="edit_package">
                            </div>
                            <div class="form-group col-xs-6">
                                <label for="edit_market">
                                    市场
                                </label>
                                <select id="edit_market" class="form-control">
                                    <c:forEach items="${markets}" var="market">
                                        <option value="${market.marketId}">${market.marketName}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                        <div class="row">
                            <div class="form-group col-xs-6">
                                <label for="edit_size">
                                    大小(兆)
                                </label>
                                <input type="text" class="form-control" id="edit_size">
                            </div>
                            <div class="form-group col-xs-6">
                                <label for="edit_desc">
                                    备注
                                </label>
                                <input type="text" class="form-control" id="edit_desc">
                                <input type="hidden" id="appId">
                            </div>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default auto-focus-btn" data-dismiss="modal" >
                            取消</button>
                        <button type="button" class="btn btn-success add_submit_btn" id="add_banner" onclick="doEditApp();">
                            确定</button>
                    </div>
                </div>
            </div>
        </div>
        <!-- 修改 end -->

        <%--主内容显示区 end --%>
    </tiles:putAttribute>
</tiles:insertTemplate>
