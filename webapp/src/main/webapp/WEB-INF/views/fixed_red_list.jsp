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
        <script src="/methods/fixed_red_list.js" type="text/javascript"></script>
    </tiles:putAttribute>
    <tiles:putAttribute name="mainArea">
        <!-- 顶部栏 begin -->
        <div class="panel panel-default main-hd">
            <div class="panel-heading">
                <button class="btn btn-success" onclick="updateFixedRed();">修改</button>
                <button class="btn btn-success" onclick="deleteFixedReds();">删除</button>
                <button class="btn btn-success" onclick="addFixedRed();">添加</button>
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
                        <th>数量</th>
                        <th>剩余数量</th>
                        <th>链接</th>
                        <th>小时</th>
                        <th>分钟</th>
                        <th>秒</th>
                        <th>状态</th>
                    </tr>
                    </thead>
                    <tbody id="bannerBody">
                    <c:forEach items="${reds}" var="red">
                        <tr>
                            <td><input type='checkbox' value=${red.fixedId}></td>
                            <td>${red.fixedId}</td>
                            <td>${red.fixedTitle}</td>
                            <td>${red.fixedAmount}</td>
                            <td>${red.fixedRemainder}</td>
                            <td>${red.fixedUrl}</td>
                            <td>${red.fixedHour}</td>
                            <td>${red.fixedMinute}</td>
                            <td>${red.fixedSecond}</td>
                            <td>
                                <c:if test="${red.fixedStatus == '0'}">IOS开启</c:if>
                                <c:if test="${red.fixedStatus == '1'}">Andriod开启</c:if>
                                <c:if test="${red.fixedStatus == '2'}">全部开启</c:if>
                                <c:if test="${red.fixedStatus == '3'}">全部关闭</c:if>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
        <%--主内容显示区 end --%>
    </tiles:putAttribute>
</tiles:insertTemplate>
