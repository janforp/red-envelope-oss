<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page pageEncoding="UTF-8" %>
<tiles:insertTemplate template="/WEB-INF/views/base/layout/layout-default.jsp">
    <tiles:putAttribute name="title" value="普通任务"/>
    <tiles:putAttribute name="enableConsole" value="true"/>
    <tiles:putAttribute name="enableSidebar" value="true"/>
    <tiles:putAttribute name="styleArea">
        <style>
            table img{
                width: 30px;
                height:30px;;
            }
        </style>
    </tiles:putAttribute>
    <tiles:putAttribute name="scriptArea">
        <script src="/methods/mission_list.js" type="text/javascript"></script>
    </tiles:putAttribute>
    <tiles:putAttribute name="mainArea">
        <!-- 顶部栏 begin -->
        <div class="panel panel-default main-hd">
            <div class="panel-heading">
                <span>普通任务&nbsp;&nbsp;&nbsp;&nbsp;</span>
                <label style="margin-left: 0px;font-size: large;">类别:</label>
                <select id="sort" style="width: 100px;height: 40px;margin-left: 10px;">
                    <option value="0">请选择</option>
                    <c:forEach items="${sorts}" var="sort">
                        <option value="${sort.sortId}">${sort.sortName}</option>
                    </c:forEach>
                </select>
                <label style="margin-left: 40px;font-size: large;">是否热门:</label>
                <select id="hot" style="width: 100px;height: 40px;margin-left: 10px;">
                    <option value="1">热门</option>
                    <option value="0">非热门</option>
                </select>
                <label style="margin-left: 40px;margin-right: 20px;font-size: large;">名称:</label>
                <input type="text" id="missionName" placeholder="输入任务名" style="height: 37px;width: 175px;">
                <input type="text" id="pageSize" style="margin-left: 25px;margin-right: 25px;height: 37px;width: 175px;" placeholder="输入数字,默认15,按回车">
                <button class="btn btn-default" type="button" id="listByPageSize">Go!</button>
                <span id="pageSizeAlert" style="color: red;margin-left: 20px;"></span>
            </div>
        </div>

        <div class="panel panel-default main-hd">
            <div class="panel-heading">
                <button class="btn btn-success" onclick="updateMission();">修改</button>
                <button class="btn btn-success" onclick="deleteSort();">删除</button>
                <button class="btn btn-success" onclick="addMission();">添加</button>
                <button class="btn btn-success" onclick="showDetail();">详情</button>
                <button class="btn btn-success" onclick="showInApp();">查看效果</button>
            </div>
        </div>
        <!-- 顶部栏 end -->

        <%--主内容显示区 begin --%>
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
                            <th>图标</th>
                            <th>奖励</th>
                            <th>额外奖励</th>
                            <th>分类</th>
                            <th>是否热门</th>
                            <th>排序</th>
                            <th>平台</th>
                        </tr>
                    </thead>
                    <tbody id="missionBody">

                    </tbody>
                    <tfoot>
                        <tr>
                            <td colspan="10">
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
