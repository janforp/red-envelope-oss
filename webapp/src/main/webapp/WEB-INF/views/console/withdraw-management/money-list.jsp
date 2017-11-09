<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page pageEncoding="UTF-8" %>
<tiles:insertTemplate template="/WEB-INF/views/base/layout/layout-default.jsp">
    <tiles:putAttribute name="title" value="资金明细"/>
    <tiles:putAttribute name="enableConsole" value="true"/>
    <tiles:putAttribute name="enableSidebar" value="true"/>
    <tiles:putAttribute name="styleArea">
    </tiles:putAttribute>
    <tiles:putAttribute name="scriptArea">
        <script src="/client/js/page/money-list.js" type="text/javascript"></script>
    </tiles:putAttribute>
    <tiles:putAttribute name="mainArea">
        <!-- 顶部栏 begin -->
        <div class="panel panel-default main-hd">
            <div class="panel-heading">
                <span>资金明细</span>
            </div>
        </div>
        <!-- 顶部栏 end -->

        <input type="hidden" id="userId" name="userId" value="${userId}">

        <%--主内容显示区 begin--%>
        <div class="main-bd">
            <div>
                <table class="table table-striped table-hover table-bordered table-row-check">
                    <thead>
                        <tr>
                            <th>用户ID</th>
                            <th>资金(元)</th>
                            <th>类型</th>
                            <th>详情</th>
                            <th>时间</th>
                        </tr>
                    </thead>
                    <tbody id="moneyBody"></tbody>
                    <tfoot>
                        <tr>
                            <td colspan="5" id="pagination">
                            </td>
                        </tr>
                    </tfoot>
                </table>
            </div>
        </div>
        <%--主内容显示区 end --%>
    </tiles:putAttribute>
</tiles:insertTemplate>
