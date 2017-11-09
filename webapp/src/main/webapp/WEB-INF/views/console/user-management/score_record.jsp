<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page pageEncoding="UTF-8" %>
<tiles:insertTemplate template="/WEB-INF/views/base/layout/layout-default.jsp">
    <tiles:putAttribute name="title" value="金币记录"/>
    <tiles:putAttribute name="enableConsole" value="true"/>
    <tiles:putAttribute name="enableSidebar" value="true"/>
    <tiles:putAttribute name="styleArea">
        <link rel="stylesheet" type="text/css" href="/styles/mission_sort.css">
        <link rel="stylesheet" type="text/css" href="/client/css/template/base.css">
    </tiles:putAttribute>
    <tiles:putAttribute name="scriptArea">
        <script src="/methods/userManager/score_record.js" type="text/javascript"></script>
        <script src="/plugins/My97DatePicker/function.js"></script>
        <script src="/plugins/My97DatePicker/WdatePicker.js"></script>
    </tiles:putAttribute>
    <tiles:putAttribute name="mainArea">

        <input type="hidden" id="userId" value="${userId}">
        <!-- 顶部栏 end -->
        <div class="panel panel-default main-hd">
            <div class="panel-heading">
                <span class="pageTitle">金币记录</span>
            </div>
        </div>
        <%--主内容显示区 begin--%>
        <div class="main-bd">
            <span class="tableTitle">账户金币:${vo.score}</span>
            <span class="tableTitle">累积获得金币:${vo.totalGetScore}</span>
            <span class="tableTitle">累积兑换金币:${vo.totalExchangeScore}</span>
            <br>
            <div>
                <table class="table table-striped table-hover table-bordered table-navbar-no-bottom">
                    <thead>
                    <tr>
                        <th>选择</th>
                        <th>序号</th>
                        <th>时间</th>
                        <th>详情</th>
                        <th>金币</th>
                    </tr>
                    </thead>
                    <tbody id="redBody">

                    <tfoot>
                    <tr>
                        <td colspan="5">
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
        <%--主内容显示区 end --%>
    </tiles:putAttribute>
</tiles:insertTemplate>
