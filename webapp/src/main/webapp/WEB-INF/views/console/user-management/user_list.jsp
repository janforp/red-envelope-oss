<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page pageEncoding="UTF-8" %>
<tiles:insertTemplate template="/WEB-INF/views/base/layout/layout-default.jsp">
    <tiles:putAttribute name="title" value="用户列表"/>
    <tiles:putAttribute name="enableConsole" value="true"/>
    <tiles:putAttribute name="enableSidebar" value="true"/>
    <tiles:putAttribute name="styleArea">
        <link rel="stylesheet" type="text/css" href="/styles/mission_sort.css">
        <link rel="stylesheet" type="text/css" href="/client/css/template/base.css">
    </tiles:putAttribute>
    <tiles:putAttribute name="scriptArea">
        <script src="/methods/userManager/user_list.js" type="text/javascript"></script>
        <script src="/plugins/My97DatePicker/function.js"></script>
        <script src="/plugins/My97DatePicker/WdatePicker.js"></script>
    </tiles:putAttribute>
    <tiles:putAttribute name="mainArea">
        <!-- 顶部栏 begin -->
        <div class="panel panel-default main-hd">
            <div class="panel-heading">
                <span class="pageTitle">用户数据</span>
                <span class="searchTitle">昵称:</span>
                <input type="text" id = "nickname"/>
                <span class="searchTitle">手机:</span>
                <input type="text" id = "phone"/>
                <span class="searchTitle">开始日期:</span>
                <input type="text" id = "startDate"  class="time" onclick="WdatePicker({lang:'zh-cn',dateFmt:'yyyy-MM-dd'});"/>
                <span class="searchTitle">结束日期:</span>
                <input type="text" id = "endDate"  class="time" onclick="WdatePicker({lang:'zh-cn',dateFmt:'yyyy-MM-dd'});"/>
                <input type="text" id="pageSize" style="margin-left: 25px;margin-right: 25px;height: 37px;width: 150px;" placeholder="每页行数,默认10">
                <button class="btn btn-default" type="button" id="listByPageSize">查询</button>
            </div>
        </div>
        <div class="panel panel-default main-hd">
            <div class="panel-heading">
                <button class="btn btn-success" onclick="moneyRecords();" >现金记录</button>
                <button class="btn btn-success" onclick="scoreRecords();" >金币记录</button>
                <button class="btn btn-success" onclick="pullBlack();">拉黑</button>
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
                        <th>昵称</th>
                        <th>手机</th>
                        <th>金币</th>
                        <th>今日获得金币</th>
                        <th>现金(元)</th>
                        <th>今日获得现金(元)</th>
                        <th>注册日期</th>
                    </tr>
                    </thead>
                    <tbody id="redBody">

                    <tfoot>
                    <tr>
                        <td colspan="9">
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
