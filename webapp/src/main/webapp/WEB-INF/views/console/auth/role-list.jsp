<%@page pageEncoding="UTF-8" %>
<tiles:insertTemplate template="/WEB-INF/views/base/layout/layout-default.jsp">
    <tiles:putAttribute name="title" value="${CURRENT_FUNCTION.functionName}"/>
    <tiles:putAttribute name="enableConsole" value="true"/>
    <tiles:putAttribute name="enableSidebar" value="true"/>
    <tiles:putAttribute name="scriptArea">
        <script>
            pg.deleteCallback = function (json) {
                if (json.success) {
                    util.loading();
                    if (json.msg) {
                        tips.suc(json.msg);
                    }
                    setTimeout(function () {
                        location.href = "${BASE_PATH}/c/page/console/auth/role/list";
                    }, 1000);
                }
            };
        </script>
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
                        <c:if test="${f:hasAuth('/c/page/console/auth/role/add')}">
                            <a class="btn btn-default btn-sm navbar-btn"
                               href="${BASE_PATH}/c/page/console/auth/role/add" role="button">
                                <i class="glyphicon glyphicon-plus-sign"></i>
                                <spring:message code="btn.add"/>
                            </a>
                        </c:if>
                        <c:if test="${f:hasAuth('/c/page/console/auth/role/edit')}">
                            <button class="btn btn-default btn-sm navbar-btn cmp-link-with-param"
                                    data-param-name="id" data-row-name="<spring:message code='oss.role'/>"
                                    data-param-selector=".id_checkbox.selected > :checkbox"
                                    data-url="${BASE_PATH}/c/page/console/auth/role/edit"
                                    role="button">
                                <i class="glyphicon glyphicon-edit"></i>
                                <spring:message code="btn.modify"/></button>
                        </c:if>
                        <c:if test="${f:hasAuth('/c/page/console/auth/role/remove')}">
                            <button class="btn btn-default btn-sm navbar-btn row-delete-btn"
                                    data-param-name="ids" data-row-name="<spring:message code='oss.role'/>"
                                    data-mask-selector=".main-bd"
                                    data-param-selector=".id_checkbox.selected > :checkbox"
                                    data-callback="pg.deleteCallback"
                                    data-url="${BASE_PATH}/c/page/console/auth/role/remove"
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
                    <th class="w100"><spring:message code="oss.role.name"/></th>
                    <th class="w60"><spring:message code="oss.role.admin.count"/></th>
                    <th class="w60"><spring:message code="oss.role.function.count"/></th>
                    <th class="w-time-min"><spring:message code="txt.create.time"/></th>
                    <th class="w-time-min"><spring:message code="txt.update.time"/></th>
                    <th><spring:message code="oss.role.desc"/></th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${pg.list}" var="v">
                    <tr data-id="${v.roleId}">
                        <td class="td-vm">
                            <label class="id_checkbox img_checkbox_label in-td-line${v.roleId==SUPER_ADMINISTRATOR_ROLE_ID?' disabled':''}">
                                <i class="icon_checkbox"></i>
                                <input type="checkbox" class="img_checkbox row-checkbox" value="${v.roleId}"
                                    ${v.roleId==SUPER_ADMINISTRATOR_ROLE_ID?'disabled="disabled"':''}>
                            </label>
                        </td>
                        <td class="td-vm">${v.roleId}</td>
                        <td class="td-vm">${f:htmlEscape(v.roleName)}</td>
                        <td class="td-vm">${v.adminCount}</td>
                        <td class="td-vm">${v.authorityCount}</td>
                        <td class="td-vm">${f:fmtMinute(v.createTimeMs)}</td>
                        <td class="td-vm">${f:fmtMinute(v.updateTimeMs)}</td>
                        <td class="td-vm">${f:htmlEscape(v.roleDesc)}</td>
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
                    </tr>
                </c:forEach>
                </tbody>
                <tfoot>
                <tr>
                    <td colspan="8"><bs:pagination url="${REQUEST_URI}" page="${pg}"/></td>
                </tr>
                </tfoot>
            </table>
        </div>
    </tiles:putAttribute>
</tiles:insertTemplate>