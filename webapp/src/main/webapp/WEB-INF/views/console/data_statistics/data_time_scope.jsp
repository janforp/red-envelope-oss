<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page pageEncoding="UTF-8" %>
<tiles:insertTemplate template="/WEB-INF/views/base/layout/layout-default.jsp">
    <tiles:putAttribute name="title" value="${data.modelTitle}统计"/>
    <tiles:putAttribute name="enableConsole" value="true"/>
    <tiles:putAttribute name="enableSidebar" value="true"/>
    <tiles:putAttribute name="styleArea">
        <style>
            table{
                text-align: center;
            }
        </style>
    </tiles:putAttribute>
    <tiles:putAttribute name="scriptArea">
        <script>
            function searchDay(oneDate) {

                var module = '${vos.model}';
                window.location.href='/c/page/console/auth/data/dayDetail?module='+module+"&oneDate="+oneDate;
            }
        </script>
    </tiles:putAttribute>
    <tiles:putAttribute name="mainArea">
        <!-- 顶部栏 begin -->
        <div class="panel panel-default main-hd">
            <div class="panel-heading">
                <span class="pageTitle">${vos.modelTitle}</span>
                <span class="searchTitle">${vos.startDate}</span>
                <span class="searchTitle">至</span>
                <span class="searchTitle">${vos.endDate}</span>
            </div>
        </div>

        <div class="panel panel-default main-hd">
            <div class="panel-heading">
                <a id="add" class="btn btn-default  navbar-btn" href="/c/page/console/auth/demo/toAddPage"><span class="glyphicon glyphicon-list-alt"></span>导至Excel</a>
            </div>
        </div>
        <!-- 顶部栏 end -->
        <%--主内容显示区 begin--%>
        <div class="main-bd">
            <div>
                <table class="table table-striped table-hover table-bordered table-row-check table-condensed table-responsive">
                    <thead>
                    <tr>
                        <th>序号</th>
                        <th>日期</th>
                        <th>参与任务总用户数</th>
                        <th>完成任务总用户数</th>
                        <th>用户完成任务的总次数</th>
                        <th>应发金额(元)</th>
                        <th>实发金额(元)</th>
                        <th>查看详情</th>
                    </tr>
                    </thead>
                    <tbody id="moneyBody"></tbody>
                        <c:forEach items="${vos.vos}" var="v" varStatus="status">
                                <tr>
                                    <td>${status.index+1}</td>
                                    <td>${v.oneDate}</td>
                                    <td>${v.disPartAmount}</td>
                                    <td>${v.disCompAmount}</td>
                                    <td>${v.totalCompAmount}</td>
                                    <td>${v.shouldPayMoney}</td>
                                    <td>${v.finalPayMoney}</td>
                                    <td class="success">
                                        <button class="btn btn-info" onclick="searchDay('${v.oneDate}');">
                                            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span class="glyphicon glyphicon-search"></span>&nbsp;&nbsp;&nbsp;&nbsp;
                                        </button>
                                    </td>
                                </tr>
                        </c:forEach>
                </table>
            </div>
        </div>
        <%--主内容显示区 end --%>

    </tiles:putAttribute>
</tiles:insertTemplate>
