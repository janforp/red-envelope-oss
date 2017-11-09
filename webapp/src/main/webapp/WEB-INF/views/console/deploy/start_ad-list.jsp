<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page pageEncoding="UTF-8" %>
<tiles:insertTemplate template="/WEB-INF/views/base/layout/layout-default.jsp">
    <tiles:putAttribute name="title" value="${CURRENT_FUNCTION.functionName}"/>
    <tiles:putAttribute name="enableConsole" value="true"/>
    <tiles:putAttribute name="enableSidebar" value="true"/>
    <tiles:putAttribute name="styleArea">
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
        <script src="/client/js/page/start_ad-list.js" type="text/javascript"></script>
        <script src="/client/js/base/control-convert.js" type="text/javascript"></script>
        <script src="/plugins/upload/image-upload/js/my.jquery-post-upload-styleX.js" type="text/javascript"></script>
    </tiles:putAttribute>
    <tiles:putAttribute name="mainArea">
        <!-- 顶部栏 begin -->
        <div class="panel panel-default main-hd">
            <div class="panel-heading">${CURRENT_FUNCTION.functionName}</div>
        </div>
        <!-- 顶部栏 end -->
        <%--主内容显示区 begin --%>
        <div class="main-bd">

            <nav class="navbar navbar-default sub-title-bar" role="ad">
                <div class="container-fluid">
                    <div class="collapse navbar-collapse">
                        <button class="btn btn-default btn-sm navbar-btn"
                                data-toggle="modal"
                                data-target="#modal-add-ad"
                                data-backdrop="static"
                                data-keyboard="false"
                                role="button">
                            <i class="glyphicon glyphicon-plus-sign"></i><spring:message code="btn.add"/>
                        </button>
                        <button class="btn btn-default btn-sm navbar-btn row-edit-btn"
                                data-param-name="id"
                                data-param-selector=".id_checkbox.selected > :checkbox"
                                data-callback="pg.editCallback"
                                data-url="${BASE_PATH}/c/page/console/auth/startAd/edit"
                                role="button">
                            <i class="glyphicon glyphicon-edit"></i><spring:message code="btn.modify"/>
                        </button>
                        <button class="btn btn-default btn-sm navbar-btn row-delete-btn"
                                data-param-name="ids"
                                data-param-selector=".id_checkbox.selected > :checkbox"
                                data-callback="pg.deleteCallback"
                                data-url="${BASE_PATH}/c/page/console/auth/startAd/remove"
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
                    <th class="w50"><spring:message code="oss.startAd.title"/></th>
                    <th class="w100"><spring:message code="oss.startAd.img"/></th>
                    <th class="w50"><spring:message code="oss.startAd.status"/></th>
                    <th class="w100"><spring:message code="oss.startAd.url"/></th>
                    <th class="w100"><spring:message code="oss.startAd.skip"/></th>
                    <th class="w100"><spring:message code="oss.startAd.time"/></th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${pg.list}" var="v">
                    <tr data-id="${v.adId}">
                        <td class="td-vm">
                            <label class="id_checkbox img_checkbox_label in-td-line">
                                <i class="icon_checkbox"></i>
                                <input type="checkbox" class="img_checkbox row-checkbox" value="${v.adId}">
                            </label>
                        </td>
                        <td class="td-vm">${v.adTitle}</td>
                        <td class="td-vm"><img src="${v.adImg}"></td>
                        <td class="td-vm">
                            <c:if test="${v.adStatus == '0'}">IOS开启</c:if>
                            <c:if test="${v.adStatus == '1'}">Andriod开启</c:if>
                            <c:if test="${v.adStatus == '2'}">全部开启</c:if>
                            <c:if test="${v.adStatus == '3'}">全部关闭</c:if>
                        </td>
                        <td class="td-vm">${v.adUrl}</td>
                        <td class="td-vm">
                            <c:if test="${v.adSkip == '0'}">能</c:if>
                            <c:if test="${v.adSkip == '1'}">不能</c:if>
                        </td>
                        <td class="td-vm">${v.adDuration}</td>
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
                    </tr>
                </c:forEach>
                </tbody>
                <tfoot>
                <tr>
                    <td colspan="7"><bs:pagination url="${REQUEST_URI}" page="${pg}"/></td>
                </tr>
                </tfoot>
            </table>
        </div>
        <%--主内容显示区 end --%>
    </tiles:putAttribute>

    <tiles:putAttribute name="modalArea">

        <!-- 添加 begin -->
        <div id="modal-add-ad" aria-labelledby="modal-add-ad-title" class="modal fade" tabindex="-1"
             role="dialog" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                        <h4 class="modal-title"><spring:message code="oss.title.startAd.add"/></h4>
                    </div>
                    <div class="modal-body">
                        <div class="row">
                            <div class="form-group col-xs-6">
                                <label for="add_title" id="add_title-label">
                                    <spring:message code="oss.startAd.title"/>
                                </label>
                                <input type="text" class="form-control" id="add_title" name="add_title">
                            </div>
                            <div class="form-group col-xs-6">
                                <label for="add_status" id="add_status-label">
                                    <spring:message code="oss.startAd.status"/>
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
                                <label for="add_skip" id="add_desc-label">
                                    <spring:message code="oss.startAd.skip"/>
                                </label>
                                <select class="form-control" id="add_skip" name="add_skip">
                                    <option value="0">能</option>
                                    <option value="1">不能</option>
                                </select>
                            </div>
                            <div class="form-group col-xs-6">
                                <label for="add_time" id="add_order-label">
                                    <spring:message code="oss.startAd.time"/>
                                </label>
                                <input type="text" class="form-control" id="add_time" name="add_time">
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
                                <label for="add_url" id="add_url-label">
                                    <spring:message code="oss.startAd.url"/>
                                </label>
                                <input type="text" class="form-control" id="add_url" name="add_url">
                            </div>

                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default auto-focus-btn" data-dismiss="modal">
                            <spring:message code="btn.cancel"/></button>
                        <button type="button" class="btn btn-success add_submit_btn" id="add_ad">
                            <spring:message code="btn.save"/></button>
                    </div>
                </div>
            </div>
        </div>
        <!-- 添加 end -->

        <!-- 修改 start -->
        <div id="modal-edit-ad" aria-labelledby="modal-edit-ad-title" class="modal fade" tabindex="-1"
             role="dialog" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                        <h4 class="modal-title"><spring:message code="oss.title.startAd.edit"/></h4>
                    </div>
                    <input type="hidden" id="edit_id" name="edit_id">
                    <div class="modal-body">
                        <div class="row">
                            <div class="form-group col-xs-6">
                                <label for="add_title" id="edit_title-label">
                                    <spring:message code="oss.startAd.title"/>
                                </label>
                                <input type="text" class="form-control" id="edit_title" name="edit_title">
                            </div>
                            <div class="form-group col-xs-6">
                                <label for="add_status" id="edit_status-label">
                                    <spring:message code="oss.startAd.status"/>
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
                                <label for="edit_skip" id="edit_url-label">
                                    <spring:message code="oss.startAd.skip"/>
                                </label>
                                <select class="form-control" id="edit_skip" name="edit_skip">
                                    <option value="0">能</option>
                                    <option value="1">不能</option>
                                </select>
                            </div>
                            <div class="form-group col-xs-6">
                                <label for="edit_time" id="edit_order-label">
                                    <spring:message code="oss.startAd.time"/>
                                </label>
                                <input type="text" class="form-control" id="edit_time" name="edit_time">
                            </div>
                        </div>
                        <div class="row">
                            <div class="form-group col-xs-6">
                                <img src="" id="old_img" class="banner_photo" style="width: 80px;height: 80px;">
                                <input type="hidden" id="edit_img" name="edit_img" obj="banner_photo" />
                                <button class="btn btn-success btn-sm" mode="button-upload-pic" uploadtype="image"
                                        style="margin-left: 10px;" action="/c/p/picture/upload" filesizelimit="128000"
                                        filetypelimit="jpg,png,gif,jpeg" filenums="banner">
                                    <spring:message code="oss.startAd.img.info"/>
                                </button>
                                <img class="loadingPic" obj="banner_photo" alt="" src="/client/img/loading.gif"
                                     style="margin-left: 10px; width: 20px; height: 20px;" />
                            </div>
                            <div class="form-group col-xs-6">
                                <label for="edit_url" id="edit_desc-label">
                                    <spring:message code="oss.startAd.url"/>
                                </label>
                                <input type="text" class="form-control" id="edit_url" name="edit_url">
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
