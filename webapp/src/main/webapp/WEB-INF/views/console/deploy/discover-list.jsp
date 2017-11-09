<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page pageEncoding="UTF-8" %>
<tiles:insertTemplate template="/WEB-INF/views/base/layout/layout-default.jsp">
    <tiles:putAttribute name="title" value="${CURRENT_FUNCTION.functionName}"/>
    <tiles:putAttribute name="enableConsole" value="true"/>
    <tiles:putAttribute name="enableSidebar" value="true"/>
    <tiles:putAttribute name="styleArea">
        <link rel="stylesheet" href="/plugins/bootstrap-select-1.11.2/css/bootstrap-select.min.css">
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
        <script src="/client/js/page/discover.js" type="text/javascript"></script>
        <script src="/client/js/base/control-convert.js" type="text/javascript"></script>
        <script src="/plugins/upload/image-upload/js/my.jquery-post-upload-styleX.js" type="text/javascript"></script>
        <script src="/plugins/bootstrap-select-1.11.2/js/bootstrap-select.min.js" type="text/javascript"></script>
        <script src="/plugins/bootstrap-select-1.11.2/js/i18n/defaults-zh_CN.min.js" type="text/javascript"></script>

        <script>
            /**
             * 默认添加的时候 渠道/包名  选择全部
             */
            $(document).ready(function () {

                $("#add_limit_channel_name").selectpicker('val', 0);
                $("#add_limit_package_name").selectpicker('val', 0);
            });
        </script>
    </tiles:putAttribute>
    <tiles:putAttribute name="mainArea">
        <!-- 顶部栏 begin -->
        <div class="panel panel-default main-hd">
            <div class="panel-heading">${CURRENT_FUNCTION.functionName}</div>
        </div>
        <!-- 顶部栏 end -->
        <%--主内容显示区 begin --%>
        <div class="main-bd">

            <nav class="navbar navbar-default sub-title-bar" role="navigation">
                <div class="container-fluid">
                    <div class="collapse navbar-collapse">
                        <button class="btn btn-default btn-sm navbar-btn"
                                data-toggle="modal"
                                data-target="#modal-add-discover"
                                data-backdrop="static"
                                data-keyboard="false"
                                role="button">
                            <i class="glyphicon glyphicon-plus-sign"></i><spring:message code="btn.add"/>
                        </button>
                        <button class="btn btn-default btn-sm navbar-btn row-edit-btn"
                                data-param-name="id"
                                data-param-selector=".id_checkbox.selected > :checkbox"
                                data-callback="pg.editCallback"
                                data-url="${BASE_PATH}/c/page/console/auth/discover/edit"
                                role="button">
                            <i class="glyphicon glyphicon-edit"></i><spring:message code="btn.modify"/>
                        </button>
                        <button class="btn btn-default btn-sm navbar-btn row-delete-btn"
                                data-param-name="ids"
                                data-param-selector=".id_checkbox.selected > :checkbox"
                                data-callback="pg.deleteCallback"
                                data-url="${BASE_PATH}/c/page/console/auth/discover/remove"
                                role="button">
                            <i class="glyphicon glyphicon-remove"></i><spring:message code="btn.remove"/>
                        </button>
                    </div>
                </div>
            </nav>

            <table class="table table-striped table-hover table-bordered table-row-check">
                <thead>
                <tr>
                    <th class="w36">
                        <label class="img_checkbox_label in-td-line">
                            <i class="icon_checkbox"></i>
                            <input type="checkbox" class="img_checkbox row-check-all">
                        </label>
                    </th>
                    <th class="w50"><spring:message code="oss.discover.order"/></th>
                    <th class="w100"><spring:message code="oss.discover.title"/></th>
                    <th class="w50"><spring:message code="oss.discover.img"/></th>
                    <th class="w100"><spring:message code="oss.discover.url"/></th>
                    <th class="w100"><spring:message code="oss.discover.status"/></th>
                    <th class="w100"><spring:message code="oss.discover.desc"/></th>
                    <th class="w100">最低显示版本</th>
                    <th class="w100">显示的渠道</th>
                    <th class="w100">显示的包名</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${pg.list}" var="v">
                    <tr data-id="${v.discoverId}">
                        <td class="td-vm">
                            <label class="id_checkbox img_checkbox_label in-td-line">
                                <i class="icon_checkbox"></i>
                                <input type="checkbox" class="img_checkbox row-checkbox" value="${v.discoverId}">
                            </label>
                        </td>
                        <td class="td-vm">${v.discoverOrder}</td>
                        <td class="td-vm">${v.discoverTitle}</td>
                        <td class="td-vm"><img src="${v.discoverImg}"></td>
                        <td class="td-vm">${v.discoverUrl}</td>
                        <td class="td-vm">
                            <c:if test="${v.discoverStatus == '0'}">IOS开启</c:if>
                            <c:if test="${v.discoverStatus == '1'}">Andriod开启</c:if>
                            <c:if test="${v.discoverStatus == '2'}">全部开启</c:if>
                            <c:if test="${v.discoverStatus == '3'}">全部关闭</c:if>
                        </td>
                        <td class="td-vm">${v.discoverDescription}</td>
                        <td class="td-vm">${v.limitVersion}</td>
                        <td class="td-vm">
                            <c:choose>
                                <c:when test="${v.limitChannelName == '0'}">
                                    全部
                                </c:when>
                                <c:otherwise>
                                    ${v.limitChannelName}
                                </c:otherwise>
                            </c:choose>
                        </td>
                        <td class="td-vm">
                            <c:choose>
                                <c:when test="${v.limitPackageName == '0'}">
                                    全部
                                </c:when>
                                <c:otherwise>
                                    ${v.limitPackageName}
                                </c:otherwise>
                            </c:choose>
                        </td>
                    </tr>
                </c:forEach>
                <c:forEach begin="1" end="${pg.blankRowCount}">
                    <tr>
                        <td>&nbsp;</td>
                        <td>&nbsp;</td>
                        <td>&nbsp;</td>
                        <td>&nbsp;</td>
                        <td>&nbsp;</td>
                        <td>&nbsp;</td>
                        <td>&nbsp;</td>
                        <td>&nbsp;</td>
                        <td>&nbsp;</td>
                        <td>&nbsp;</td>
                    </tr>
                </c:forEach>
                </tbody>
                <tfoot>
                <tr>
                    <td colspan="10"><bs:pagination url="${REQUEST_URI}" page="${pg}"/></td>
                </tr>
                </tfoot>
            </table>
        </div>
        <%--主内容显示区 end --%>
    </tiles:putAttribute>

    <tiles:putAttribute name="modalArea">

        <!-- 添加 begin -->
        <div id="modal-add-discover" aria-labelledby="modal-add-navigation-title" class="modal fade" tabindex="-1"
             role="dialog" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                        <h4 class="modal-title"><spring:message code="oss.title.discover.add"/></h4>
                    </div>
                    <div class="modal-body">
                        <div class="row">
                            <div class="form-group col-xs-6">
                                <label for="add_title" id="add_title-label">
                                    <spring:message code="oss.discover.title"/>
                                </label>
                                <input type="text" class="form-control" id="add_title" name="add_title">
                            </div>
                            <div class="form-group col-xs-6">
                                <label for="add_status" id="add_status-label">
                                    <spring:message code="oss.discover.status"/>
                                </label>
                                <select class="form-control" id="add_status" name="add_status">
                                    <option value="2">全部开启</option>
                                    <option value="0">IOS开启</option>
                                    <option value="1">andriod开启</option>
                                    <option value="3">全部关闭</option>
                                </select>
                            </div>
                        </div>
                        <div class="row">
                            <div class="form-group col-xs-6">
                                <label for="add_url" id="add_url-label">
                                    <spring:message code="oss.discover.url"/>
                                </label>
                                <input type="text" class="form-control" id="add_url" name="add_url">
                            </div>
                            <div class="form-group col-xs-6">
                                <label for="add_order" id="add_order-label">
                                    <spring:message code="oss.discover.order"/>
                                </label>
                                <input type="text" class="form-control" id="add_order" name="add_order">
                            </div>
                        </div>
                        <div class="row">
                            <div class="form-group col-xs-6">
                                <img src="" class="banner_photo" id="oldImg" style="width: 80px;height: 80px;">
                                <input type="hidden" id="add_img" name="add_img" obj="banner_photo" />
                                <button class="btn btn-success btn-sm" mode="button-upload-pic" uploadtype="image"
                                        style="margin-left: 10px;" action="/c/p/picture/upload" filesizelimit="128000"
                                        filetypelimit="jpg,png,gif,jpeg" filenums="banner">
                                    <spring:message code="oss.navigation.img.info"/>
                                </button>
                                <img class="loadingPic" obj="banner_photo" alt="" src="/client/img/loading.gif"
                                     style="margin-left: 10px; width: 20px; height: 20px;" />
                            </div>
                            <div class="form-group col-xs-6">
                                <label for="add_order" id="add_desc-label">
                                    <spring:message code="oss.discover.desc"/>
                                </label>
                                <input type="text" class="form-control" id="add_desc" name="add_desc">
                            </div>
                        </div>
                        <div class="row">
                            <div class="form-group col-xs-6">
                                <label for="add_limit_version" id="add_limit_version-label">
                                    最低显示版本
                                </label>
                                <input type="text" class="form-control" id="add_limit_version"
                                       name="add_limit_version">
                            </div>
                            <div class="form-group col-xs-6">
                                <label for="add_max_version" id="add_max_version-label">
                                    最高显示版本
                                </label>
                                <input type="text" class="form-control" id="add_max_version"
                                       name="add_max_version">
                            </div>
                        </div>
                        <div class="row">
                            <div class="form-group col-xs-6">
                                <label for="add_limit_channel_name" id="add_limit_channel_name-label">
                                    显示的渠道
                                </label>
                                <select id="add_limit_channel_name" name="add_limit_channel_name"
                                        class="selectpicker show-tick form-control" multiple data-live-search="false">
                                    <option value="0">全部</option>
                                    <c:forEach items="${channels}" var="v">
                                        <option value="${v}">
                                           ${v}
                                        </option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="form-group col-xs-6">
                                <label for="add_limit_package_name" id="add_limit_package_name-label">
                                    显示的包名
                                </label>
                                <select id="add_limit_package_name" name="add_limit_package_name"
                                        class="selectpicker show-tick form-control" multiple data-live-search="false">
                                    <option value="0">全部</option>
                                    <c:forEach items="${packages}" var="v">
                                        <option value="${v}">
                                           ${v}
                                        </option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default auto-focus-btn" data-dismiss="modal">
                            <spring:message code="btn.cancel"/></button>
                        <button type="button" class="btn btn-success add_submit_btn" id="add_discover">
                            <spring:message code="btn.save"/></button>
                    </div>
                </div>
            </div>
        </div>
        <!-- 添加 end -->

        <!-- 修改 start -->
        <div id="modal-edit-discover" aria-labelledby="modal-edit-discover-title" class="modal fade" tabindex="-1"
             role="dialog" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                        <h4 class="modal-title"><spring:message code="oss.title.discover.edit"/></h4>
                    </div>
                    <input type="hidden" id="edit_id" name="edit_id">
                    <div class="modal-body">
                        <div class="row">
                            <div class="form-group col-xs-6">
                                <label for="add_title" id="edit_title-label">
                                    <spring:message code="oss.navigation.title"/>
                                </label>
                                <input type="text" class="form-control" id="edit_title" name="edit_title">
                            </div>
                            <div class="form-group col-xs-6">
                                <label for="add_status" id="edit_status-label">
                                    <spring:message code="oss.navigation.status"/>
                                </label>
                                <select class="form-control" id="edit_status" name="edit_status">
                                    <option value="2">全部开启</option>
                                    <option value="0">IOS开启</option>
                                    <option value="1">andriod开启</option>
                                    <option value="3">全部关闭</option>
                                </select>
                            </div>
                        </div>
                        <div class="row">
                            <div class="form-group col-xs-6">
                                <label for="add_url" id="edit_url-label">
                                    <spring:message code="oss.navigation.url"/>
                                </label>
                                <input type="text" class="form-control" id="edit_url" name="edit_url">
                            </div>
                            <div class="form-group col-xs-6">
                                <label for="add_order" id="edit_order-label">
                                    <spring:message code="oss.navigation.order"/>
                                </label>
                                <input type="text" class="form-control" id="edit_order" name="edit_order">
                            </div>
                        </div>
                        <div class="row">
                            <div class="form-group col-xs-6">
                                <img src="" id="old_img" class="banner_photo" style="width: 80px;height: 80px;">
                                <input type="hidden" id="edit_img" name="edit_img" obj="banner_photo" />
                                <button class="btn btn-success btn-sm" mode="button-upload-pic" uploadtype="image"
                                        style="margin-left: 10px;" action="/c/p/picture/upload" filesizelimit="128000"
                                        filetypelimit="jpg,png,gif,jpeg" filenums="banner">
                                    <spring:message code="oss.navigation.img.info"/>
                                </button>
                                <img class="loadingPic" obj="banner_photo" alt="" src="/client/img/loading.gif"
                                     style="margin-left: 10px; width: 20px; height: 20px;" />
                            </div>
                            <div class="form-group col-xs-6">
                                <label for="add_order" id="edit_desc-label">
                                    <spring:message code="oss.discover.desc"/>
                                </label>
                                <input type="text" class="form-control" id="edit_desc" name="edit_desc">
                            </div>
                        </div>


                        <div class="row">
                            <div class="form-group col-xs-6">
                                <label for="edit_limit_version" id="edit_limit_version-label">
                                    最低显示版本
                                </label>
                                <input type="text" class="form-control" id="edit_limit_version"
                                       name="edit_limit_version">
                            </div>
                            <div class="form-group col-xs-6">
                                <label for="edit_max_version" id="edit_max_version-label">
                                    最高显示版本
                                </label>
                                <input type="text" class="form-control" id="edit_max_version"
                                       name="edit_max_version">
                            </div>
                        </div>
                        <div class="row">
                            <div class="form-group col-xs-6">
                                <label for="edit_limit_channel_name" id="edit_limit_channel_name-label">
                                    显示的渠道
                                </label>
                                <select id="edit_limit_channel_name" name="edit_limit_channel_name"
                                        class="selectpicker show-tick form-control" multiple data-live-search="false">
                                    <option value="0">全部</option>
                                    <c:forEach items="${channels}" var="v">
                                        <option value="${v}">
                                            ${v}
                                        </option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="form-group col-xs-6">
                                <label for="edit_limit_package_name" id="edit_limit_package_name-label">
                                    显示的包名
                                </label>
                                <select id="edit_limit_package_name" name="edit_limit_package_name"
                                        class="selectpicker show-tick form-control" multiple data-live-search="false">
                                    <option value="0">全部</option>
                                    <c:forEach items="${packages}" var="v">
                                        <option value="${v}">
                                            ${v}
                                        </option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default auto-focus-btn" data-dismiss="modal">
                            <spring:message code="btn.cancel"/></button>
                        <button type="button" class="btn btn-success add_submit_btn" id="edit_discover">
                            <spring:message code="btn.save"/></button>
                    </div>
                </div>
            </div>
        </div>
        <!-- 修改 end -->

    </tiles:putAttribute>

</tiles:insertTemplate>
