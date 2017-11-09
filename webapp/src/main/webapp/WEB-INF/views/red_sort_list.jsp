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
        <script src="/methods/red_sort_list.js" type="text/javascript"></script>
    </tiles:putAttribute>
    <tiles:putAttribute name="mainArea">
        <!-- 顶部栏 begin -->
        <div class="panel panel-default main-hd">
            <div class="panel-heading">
                <button class="btn btn-success" onclick="updateRedSort();">修改</button>
                <button class="btn btn-success" onclick="deleteRedSorts();">删除</button>
                <button class="btn btn-success" onclick="addRedSort();">添加</button>
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
                        <th>标题</th>
                        <th>状态</th>
                        <th>排序</th>
                    </tr>
                    </thead>
                    <tbody id="bannerBody">
                    <c:forEach items="${sorts}" var="sort">
                        <tr>
                            <td><input type='checkbox' value=${sort.sortId}></td>
                            <td>${sort.sortId}</td>
                            <td>${sort.sortTitle}</td>
                            <td>
                                <c:if test="${sort.sortStatus == '0'}">IOS开启</c:if>
                                <c:if test="${sort.sortStatus == '1'}">Andriod开启</c:if>
                                <c:if test="${sort.sortStatus == '2'}">全部开启</c:if>
                                <c:if test="${sort.sortStatus == '3'}">全部关闭</c:if>
                            </td>
                            <td>${sort.sortOrder}</td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
        <%--主内容显示区 end --%>
    </tiles:putAttribute>
</tiles:insertTemplate>
