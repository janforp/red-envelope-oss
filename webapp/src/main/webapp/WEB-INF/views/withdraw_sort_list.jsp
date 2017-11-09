<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page pageEncoding="UTF-8" %>
<tiles:insertTemplate template="/WEB-INF/views/base/layout/layout-default.jsp">
    <tiles:putAttribute name="title" value="支付列表"/>
    <tiles:putAttribute name="enableConsole" value="true"/>
    <tiles:putAttribute name="enableSidebar" value="true"/>
    <tiles:putAttribute name="styleArea">
        <link rel="stylesheet" type="text/css" href="/styles/mission_sort.css">
    </tiles:putAttribute>
    <tiles:putAttribute name="scriptArea">
        <script src="/methods/withdraw_sort_list.js" type="text/javascript"></script>
    </tiles:putAttribute>
    <tiles:putAttribute name="mainArea">
        <!-- 顶部栏 begin -->
        <div class="panel panel-default main-hd">
            <div class="panel-heading">
                <span>支付列表&nbsp;&nbsp;&nbsp;&nbsp;</span>
                <button class="btn btn-success" onclick="updateWithdraw();">修改</button>
                <button class="btn btn-success" onclick="deleteWithdraws();">删除</button>
                <button class="btn btn-success" onclick="addWithdraw();">添加</button>
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
                        <th>类型</th>
                        <th>名称</th>
                        <th>图片</th>
                        <th>提现金额</th>
                        <th>到账金额</th>
                        <th>说明(鼠标悬浮查看)</th>
                        <th>次数</th>
                        <th>状态</th>
                        <th>排序</th>
                    </tr>
                    </thead>
                    <tbody id="bannerBody">
                    <c:forEach items="${sorts}" var="sort">
                        <tr>
                            <td><input type='checkbox' value=${sort.withdrawId}></td>
                            <td>${sort.withdrawId}</td>
                            <td>${sort.withdrawType}</td>
                            <td>${sort.withdrawName}</td>
                            <td><img src="${sort.withdrawImg}"></td>
                            <td>${sort.withdrawMoney}</td>
                            <td>${sort.toAccountMoney}</td>
                            <td><textarea readonly  onmouseover="showDesc(this);" onmouseout="closeDesc();" style="width: 100%;height: 100%;border: none;resize: none;">${sort.withdrawDesc}</textarea></td>
                            <td>${sort.withdrawTimes}</td>
                            <td>
                                <c:if test="${sort.withdrawStatus == '0'}">IOS开启</c:if>
                                <c:if test="${sort.withdrawStatus == '1'}">Andriod开启</c:if>
                                <c:if test="${sort.withdrawStatus == '2'}">全部开启</c:if>
                                <c:if test="${sort.withdrawStatus == '3'}">全部关闭</c:if>
                            </td>
                            <td>${sort.withdrawOrder}</td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>

        <div class="alertDesc">
            <span class="title">支付说明</span>
            <div class="desc">

            </div>
        </div>
        <%--主内容显示区 end --%>
    </tiles:putAttribute>
</tiles:insertTemplate>
