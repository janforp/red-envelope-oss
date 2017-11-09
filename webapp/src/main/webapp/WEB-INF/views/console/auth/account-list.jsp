<%@page pageEncoding="UTF-8" %>
<tiles:insertTemplate template="/WEB-INF/views/base/layout/layout-default.jsp">
    <tiles:putAttribute name="title" value="${CURRENT_FUNCTION.functionName}"/>
    <tiles:putAttribute name="enableConsole" value="true"/>
    <tiles:putAttribute name="enableSidebar" value="true"/>
    <tiles:putAttribute name="scriptArea">
        <script src="${BASE_PATH}/client/js/account${RESOURCE_MIN}.js${RESOURCE_VERSION}"></script>

    </tiles:putAttribute>
    <tiles:putAttribute name="mainArea">
        <div class="panel panel-default main-hd">
            <div class="panel-heading">${CURRENT_FUNCTION.functionName}</div>
        </div>
        <div class="main-bd">
            <nav class="navbar navbar-default sub-title-bar" role="navigation">
                <div class="container-fluid">
                    <div class="collapse navbar-collapse">
                        <bs:pagination url="${REQUEST_URI}" page="${pg}"/>
                        <c:if test="${f:hasAuth('/c/page/console/auth/account/save')}">
                            <button class="btn btn-default btn-sm navbar-btn"
                                    data-toggle="modal" data-target="#modal-add-account"
                                    data-backdrop="static" data-keyboard="false"
                                    role="button">
                                <i class="glyphicon glyphicon-plus-sign"></i>
                                <spring:message code="btn.add"/></button>
                        </c:if>
                        <c:if test="${f:hasAuth('/c/page/console/auth/account/edit')}">
                            <button class="btn btn-default btn-sm navbar-btn row-edit-btn"
                                    data-param-name="id" data-row-name="<spring:message code='oss.account'/>"
                                    data-param-selector=".id_checkbox.selected > :checkbox"
                                    data-callback="pg.editCallback"
                                    data-url="${BASE_PATH}/c/page/console/auth/account/edit"
                                    role="button">
                                <i class="glyphicon glyphicon-edit"></i>
                                <spring:message code="btn.modify"/></button>
                        </c:if>
                        <c:if test="${f:hasAuth('/c/page/console/auth/account/remove')}">
                            <button class="btn btn-default btn-sm navbar-btn row-delete-btn"
                                    data-param-name="ids" data-row-name="<spring:message code='oss.account'/>"
                                    data-mask-selector=".main-bd"
                                    data-param-selector=".id_checkbox.selected > :checkbox"
                                    data-callback="pg.deleteCallback"
                                    data-url="${BASE_PATH}/c/page/console/auth/account/remove"
                                    role="button">
                                <i class="glyphicon glyphicon-remove"></i>
                                <spring:message code="btn.remove"/></button>
                        </c:if>
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
                    <th class="w60"><spring:message code="txt.id"/></th>
                    <th class="w100"><spring:message code="oss.account.name"/></th>
                    <th class="w100"><spring:message code="oss.role"/></th>
                    <th class="w100"><spring:message code="oss.account.loginName"/></th>
                    <th class="w100"><spring:message code="oss.account.cellphone"/></th>
                    <th><spring:message code="oss.account.email"/></th>
                    <th class="w-time-min"><spring:message code="txt.create.time"/></th>
                    <th class="w-time-min"><spring:message code="txt.update.time"/></th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${pg.list}" var="v">
                    <tr data-id="${v.adminId}">
                        <td class="td-vm">
                            <label class="id_checkbox img_checkbox_label in-td-line">
                                <i class="icon_checkbox"></i>
                                <input type="checkbox" class="img_checkbox row-checkbox" value="${v.adminId}">
                            </label>
                        </td>
                        <td class="td-vm">${v.adminId}</td>
                        <td class="td-vm">${f:htmlEscape(v.realName)}</td>
                        <td class="td-vm">${f:htmlEscape(v.roleName)}</td>
                        <td class="td-vm">${f:htmlEscape(v.loginName)}</td>
                        <td class="td-vm">${v.cellphone}</td>
                        <td class="td-vm">${v.email}</td>
                        <td class="td-vm">${f:fmtMinute(v.createTimeMs)}</td>
                        <td class="td-vm">${f:fmtMinute(v.updateTimeMs)}</td>
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
                    </tr>
                </c:forEach>
                </tbody>
                <tfoot>
                <tr>
                    <td colspan="9"><bs:pagination url="${REQUEST_URI}" page="${pg}"/></td>
                </tr>
                </tfoot>
            </table>
        </div>
    </tiles:putAttribute>
    <tiles:putAttribute name="modalArea">
        <c:if test="${f:hasAuth('/c/page/console/auth/account/save')}">
            <div id="modal-add-account" aria-labelledby="modal-add-account-title" class="modal fade" tabindex="-1"
                 role="dialog" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                                    aria-hidden="true">&times;</span></button>
                            <h4 class="modal-title"><spring:message code="oss.title.account.add"/></h4>
                        </div>
                        <div class="modal-body">
                            <form role="form" id="accountSaveForm" class="ajax-form v-form"
                                  ajax-callback="pg.saveCallback"
                                  data-submit-btn=".add_submit_btn"
                                  action="${BASE_PATH}/c/page/console/auth/account/save"
                                  method="post" enctype="multipart/form-data">
                                <div class="row">
                                    <div class="form-group col-xs-6">
                                        <label for="add_realName" id="add_realName-label"><spring:message
                                                code="oss.account.name"/></label>
                                        <input type="text" class="form-control" id="add_realName" name="realName"
                                               v-show-id="#add_realName-label">
                                    </div>
                                    <div class="form-group col-xs-6">
                                        <label for="add_roleId"><spring:message code="oss.role"/></label>
                                        <div>
                                            <bs:combo data="${allRole}" name="roleId" id="add_roleId" valName="roleId"
                                                      textName="roleName" css="combo-block"/>
                                        </div>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="form-group col-xs-6">
                                        <label for="add_loginName" id="add_loginName-label"><spring:message
                                                code="oss.account.loginName"/></label>
                                        <input type="text" class="form-control" id="add_loginName" name="loginName"
                                               v-show-id="#add_loginName-label">
                                    </div>
                                    <div class="form-group col-xs-6">
                                        <label for="add_loginPwd" id="add_loginPwd-label"><spring:message
                                                code="oss.account.loginPwd"/></label>
                                        <input type="password" class="form-control epwd" id="add_loginPwd"
                                               data-target="#add_loginPwd_hidden">
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="form-group col-xs-6">
                                        <label for="add_cellphone" id="add_cellphone-label"><spring:message
                                                code="oss.account.cellphone"/></label>
                                        <input type="text" class="form-control" id="add_cellphone" name="cellphone"
                                               v-show-id="#add_cellphone-label">
                                    </div>
                                    <div class="form-group col-xs-6">
                                        <label for="add_email" id="add_email-label"><spring:message
                                                code="oss.account.email"/></label>
                                        <input type="text" class="form-control" id="add_email" name="email"
                                               v-show-id="#add_email-label">
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="form-group col-xs-6" style="display: none">
                                        <label for="add_portrait"><spring:message code="oss.account.portrait"/></label>
                                        <bs:filebtn name="portrait" id="add_portrait" icon="glyphicon-picture"
                                                    text="${portraitInfo}"/>
                                    </div>
                                </div>
                                <div class="not-show">
                                    <input type="hidden" name="loginPwd" id="add_loginPwd_hidden"
                                           v-event-id="#add_loginPwd" v-show-id="#add_loginPwd-label"/>
                                    <input type="submit" class="add_submit_btn">
                                </div>
                            </form>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-default auto-focus-btn" data-dismiss="modal">
                                <spring:message code="btn.cancel"/></button>
                            <button type="submit" class="btn btn-success add_submit_btn">
                                <spring:message code="btn.save"/></button>
                        </div>
                    </div>
                </div>
            </div>
        </c:if>
        <c:if test="${f:hasAuth('/c/page/console/auth/account/update')}">
            <div id="modal-edit-account" aria-labelledby="modal-edit-account-title" class="modal fade" tabindex="-1"
                 role="dialog" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                                    aria-hidden="true">&times;</span></button>
                            <h4 class="modal-title"><spring:message code="oss.title.account.edit"/></h4>
                        </div>


                        <div class="modal-body">
                            <form role="form" id="accountUpdateForm" class="ajax-form v-form"
                                  ajax-callback="pg.updateCallback"
                                  data-submit-btn=".edit_submit_btn"
                                  action="${BASE_PATH}/c/page/console/auth/account/update"
                                  method="post" enctype="multipart/form-data">
                                <div class="row">
                                    <div class="form-group col-xs-6">
                                        <label for="edit_realName" id="edit_realName-label"><spring:message
                                                code="oss.account.name"/></label>
                                        <input type="text" class="form-control" id="edit_realName" name="realName"
                                               v-show-id="#edit_realName-label">
                                    </div>
                                    <div class="form-group col-xs-6">
                                        <label for="edit_roleId"><spring:message code="oss.role"/></label>

                                        <div>
                                            <bs:combo data="${allRole}" name="roleId" id="edit_roleId" valName="roleId"
                                                      textName="roleName" css="combo-block"/>
                                        </div>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="form-group col-xs-6">
                                        <label for="edit_loginName" id="edit_loginName-label"><spring:message
                                                code="oss.account.loginName"/></label>
                                        <input type="text" class="form-control" id="edit_loginName" name="loginName"
                                               v-show-id="#edit_loginName-label">
                                    </div>
                                    <div class="form-group col-xs-6">
                                        <label for="edit_loginPwd" id="edit_loginPwd-label"><spring:message
                                                code="oss.account.loginPwd"/></label>
                                        <input type="password" class="form-control epwd" id="edit_loginPwd"
                                               data-target="#edit_loginPwd_hidden" placeholder="<spring:message code="oss.account.loginPwd.update.info"/>">
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="form-group col-xs-6">
                                        <label for="edit_cellphone" id="edit_cellphone-label"><spring:message
                                                code="oss.account.cellphone"/></label>
                                        <input type="text" class="form-control" id="edit_cellphone" name="cellphone"
                                               v-show-id="#edit_cellphone-label">
                                    </div>
                                    <div class="form-group col-xs-6">
                                        <label for="edit_email" id="edit_email-label"><spring:message
                                                code="oss.account.email"/></label>
                                        <input type="text" class="form-control" id="edit_email" name="email"
                                               v-show-id="#edit_email-label">
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="form-group col-xs-6" style="display: none">
                                        <label for="edit_portrait"><spring:message code="oss.account.portrait"/></label>
                                        <bs:filebtn name="portrait" id="edit_portrait" icon="glyphicon-picture"
                                                    text="${portraitInfo}"/>
                                    </div>
                                    <div class="form-group col-xs-6">
                                        <label><spring:message code="oss.account.status"/></label>
                                        <div class="pt6">
                                            <label class="img_radio_label edit_status_label" for="edit_status_1">
                                                <i class="icon_radio"></i>
                                                <span><spring:message code="oss.account.status1"/></span>
                                                <input type="radio" id="edit_status_1" name="status" class="img_radio"
                                                       value="1">
                                            </label>
                                            <label class="img_radio_label edit_status_label" for="edit_status_0">
                                                <i class="icon_radio"></i>
                                                <span><spring:message code="oss.account.status0"/></span>
                                                <input type="radio" id="edit_status_0" name="status" class="img_radio"
                                                       value="0">
                                            </label>
                                        </div>
                                    </div>
                                </div>
                                <div class="not-show">
                                    <input type="hidden" name="loginPwd" id="edit_loginPwd_hidden"
                                           v-event-id="#edit_loginPwd" v-show-id="#edit_loginPwd-label"/>
                                    <input type="hidden" name="adminId"/>
                                    <input type="submit" class="edit_submit_btn">
                                </div>
                            </form>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-default auto-focus-btn" data-dismiss="modal">
                                <spring:message code="btn.cancel"/></button>
                            <button type="submit" class="btn btn-success edit_submit_btn">
                                <spring:message code="btn.update"/></button>
                        </div>
                    </div>
                </div>
            </div>
        </c:if>
    </tiles:putAttribute>
</tiles:insertTemplate>