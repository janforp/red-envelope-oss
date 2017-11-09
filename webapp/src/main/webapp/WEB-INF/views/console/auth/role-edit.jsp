<%@page pageEncoding="UTF-8" %>
<tiles:insertTemplate template="/WEB-INF/views/base/layout/layout-default.jsp">
    <tiles:putAttribute name="title" value="${CURRENT_FUNCTION.functionName}"/>
    <tiles:putAttribute name="enableConsole" value="true"/>
    <tiles:putAttribute name="enableSidebar" value="true"/>
    <tiles:putAttribute name="mainArea">
        <div class="panel panel-default main-hd">
            <div class="panel-heading">${CURRENT_FUNCTION.functionName}</div>
        </div>
        <div class="main-bd">
            <form id="roleUpdateForm" class="ajax-form v-form" role="form"
                  action="${BASE_PATH}/c/page/console/auth/role/update" method="post">
                <div class="row">
                    <div class="form-group col-xs-6">
                        <label id="roleName-label" for="roleName"><spring:message code="oss.role.name"/></label>
                        <input type="text" class="form-control" name="name" id="roleName" value="${f:htmlEscape(role.roleName)}" v-show-id="#roleName-label">
                    </div>
                    <div class="form-group col-xs-6">
                        <label id="roleDesc-label" for="roleDesc"><spring:message code="oss.role.desc"/></label>
                        <input type="text" class="form-control" name="desc" id="roleDesc" value="${f:htmlEscape(role.roleDesc)}" v-show-id="#roleDesc-label">
                    </div>
                </div>
                <c:if test="${f:isNotEmpty(allFunctionMenuModuleDtoList)}">
                    <div class="form-group">
                        <label id="function-ids-label"><spring:message code="oss.role.function"/></label>
                        <c:forEach items="${allFunctionMenuModuleDtoList}" var="m" varStatus="loopStatus">
                            <div class="panel-group" id="module-${m.moduleId}">
                                <div class="panel panel-default">
                                    <div class="panel-heading">
                                            ${m.moduleName}
                                    </div>
                                    <div class="panel-body">
                                        <c:forEach items="${m.subMenuFunctionList}" var="func">
                                            <div class="btn-group btn-check mt3"
                                                 data-toggle="buttons">
                                                <label class="btn btn-default${f:contains(role.functionIds,func.functionId)?' active':''}">
                                                    <input type="checkbox" ${f:contains(role.functionIds,func.functionId)?' checked="checked"':''}
                                                           v-show-id="#function-ids-label"
                                                           name="functionIds"
                                                           value="${func.functionId}"
                                                           id="function-${func.functionId}"><i
                                                        class="glyphicon glyphicon-ok"></i> ${func.functionName}
                                                </label>
                                            </div>
                                        </c:forEach>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                </c:if>
                <div class="form-group">
                    <input type="hidden" name="roleId" value="${role.roleId}"/>
                    <button type="submit" class="btn btn-success"><spring:message code="btn.save"/></button>
                </div>
            </form>
        </div>
        </div>
    </tiles:putAttribute>
</tiles:insertTemplate>