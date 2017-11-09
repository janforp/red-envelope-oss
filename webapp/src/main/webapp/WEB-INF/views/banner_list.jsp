<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page pageEncoding="UTF-8" %>
<tiles:insertTemplate template="/WEB-INF/views/base/layout/layout-default.jsp">
    <tiles:putAttribute name="title" value="分类列表"/>
    <tiles:putAttribute name="enableConsole" value="true"/>
    <tiles:putAttribute name="enableSidebar" value="true"/>
    <tiles:putAttribute name="styleArea">
        <link rel="stylesheet" type="text/css" href="/styles/mission_sort.css">
    </tiles:putAttribute>
    <tiles:putAttribute name="scriptArea">
        <script src="/methods/banner_list.js" type="text/javascript"></script>
    </tiles:putAttribute>
    <tiles:putAttribute name="mainArea">
        <!-- 顶部栏 begin -->
        <div class="panel panel-default main-hd">
            <div class="panel-heading">
                <button class="btn btn-success" onclick="updateBanner();">修改</button>
                <button class="btn btn-success" onclick="deleteBanner();">删除</button>
                <button class="btn btn-success" onclick="addBanner();">添加</button>
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
                        <th>图标</th>
                        <th>状态</th>
                        <th>链接</th>
                        <th>排序</th>
                    </tr>
                    </thead>
                    <tbody id="bannerBody">
                        <c:forEach items="${banners}" var="banner">
                            <tr>
                                <td><input type='checkbox' value=${banner.bannerId}></td>
                                <td>${banner.bannerId}</td>
                                <td>${banner.bannerTitle}</td>
                                <td><img src="${banner.bannerImg}"></td>
                                <td>
                                    <c:if test="${banner.bannerStatus == '0'}">IOS开启</c:if>
                                    <c:if test="${banner.bannerStatus == '1'}">andriod开启</c:if>
                                    <c:if test="${banner.bannerStatus == '2'}">全部开启</c:if>
                                    <c:if test="${banner.bannerStatus == '3'}">全部关闭</c:if>
                                </td>
                                <td>${banner.bannerUrl}</td>
                                <td>${banner.bannerOrder}</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
        <%--主内容显示区 end --%>
    </tiles:putAttribute>
</tiles:insertTemplate>
