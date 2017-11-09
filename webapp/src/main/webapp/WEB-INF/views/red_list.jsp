<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page pageEncoding="UTF-8" %>
<tiles:insertTemplate template="/WEB-INF/views/base/layout/layout-default.jsp">
    <tiles:putAttribute name="title" value="红包分类"/>
    <tiles:putAttribute name="enableConsole" value="true"/>
    <tiles:putAttribute name="enableSidebar" value="true"/>
    <tiles:putAttribute name="styleArea">
        <link rel="stylesheet" type="text/css" href="/styles/mission_sort.css">
    </tiles:putAttribute>
    <tiles:putAttribute name="scriptArea">
        <script src="/plugins/My97DatePicker/function.js"></script>
        <script src="/plugins/My97DatePicker/WdatePicker.js"></script>
        <script src="/methods/red_list.js" type="text/javascript"></script>
    </tiles:putAttribute>
    <tiles:putAttribute name="mainArea">
        <!-- 顶部栏 begin -->
        <div class="panel panel-default main-hd">
            <div class="panel-heading">
                <button class="btn btn-success" onclick="updateSortRed();">修改</button>
                <button class="btn btn-success" onclick="deleteSortReds();">删除</button>
                <button class="btn btn-success" onclick="addSortRed();">添加</button>
                <button class="btn btn-success" onclick="showDetail();">详情</button>

                <label style="margin-left: 20px;font-size: large;">类别:</label>
                <select id="redSort" style="width: 100px;height: 40px;margin-left: 10px;">
                    <option value="">请选择</option>
                    <c:forEach items="${sorts}" var="sort">
                        <option value="${sort.sortId}">${sort.sortTitle}</option>
                    </c:forEach>
                </select>
                <label style="margin-left: 20px;font-size: large;">显示:</label>
                <select id="showOrNot" style="width: 100px;height: 40px;margin-left: 10px;">
                    <option value="">请选择</option>
                    <option value="1">显示</option>
                    <option value="0">不显示</option>
                </select>
                <label style="margin-left: 20px;font-size: large;">状态:</label>
                <select id="redStatus" style="width: 100px;height: 40px;margin-left: 10px;">
                    <option value="">请选择</option>
                    <option value="0">IOS开启</option>
                    <option value="1">Andriod开启</option>
                    <option value="2">全部开启</option>
                    <option value="3">全部关闭</option>
                </select>
                <label style="margin-left: 20px;margin-right: 20px;font-size: large;">开始:</label>
                <input type="text" id = "startTime" style="width: 193px;height: 33px;" class="Wdate" onclick="WdatePicker({lang:'zh-cn',dateFmt:'yyyy-MM-dd HH:mm:ss '});"/>
                <label style="margin-left: 20px;margin-right: 20px;font-size: large;">结束:</label>
                <input type="text" id = "endTime" style="width: 193px;height: 33px;" class="Wdate" onclick="WdatePicker({lang:'zh-cn',dateFmt:'yyyy-MM-dd HH:mm:ss '});"/>
                <label style="margin-left: 20px;margin-right: 20px;font-size: large;">名称:</label>
                <input type="text" id="redName" placeholder="输入红包名" style="height: 37px;width: 175px;">
                <label style="margin-left: 20px;margin-right: 20px;font-size: large;">商户:</label>
                <input type="text" id="merchantName" placeholder="输入商户名" style="height: 37px;width: 175px;">
                <input type="text" id="pageSize" style="margin-left: 25px;margin-right: 25px;height: 37px;width: 150px;" placeholder="每页行数,默认10">
                <button class="btn btn-default" type="button" id="listByPageSize">Go!</button>
                <span id="pageSizeAlert" style="color: red;margin-left: 20px;"></span>
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
                        <th>ID</th>
                        <th>名称</th>
                        <th>图片</th>
                        <th>商户</th>
                        <th>奖励</th>
                        <th>状态</th>
                        <th>显示</th>
                    </tr>
                    </thead>
                    <tbody id="redBody">

                    <tfoot>
                    <tr>
                        <td colspan="8">
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
