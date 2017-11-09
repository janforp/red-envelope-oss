<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page pageEncoding="UTF-8" %>
<tiles:insertTemplate template="/WEB-INF/views/base/layout/layout-default.jsp">
    <tiles:putAttribute name="title" value="现金红包"/>
    <tiles:putAttribute name="enableConsole" value="true"/>
    <tiles:putAttribute name="enableSidebar" value="true"/>
    <tiles:putAttribute name="styleArea">
    </tiles:putAttribute>
    <tiles:putAttribute name="scriptArea">
    </tiles:putAttribute>
    <tiles:putAttribute name="mainArea">
        <!-- 顶部栏 begin -->
        <div class="panel panel-default main-hd">
            <div class="panel-heading">
                <a href="/c/page/console/auth/extend/extendPage"><button class="btn btn-success" id="withdraw">推广列表</button></a>
                <a href="/c/page/console/auth/extend/addExtend"><button class="btn btn-success" id="invalid">新增推广</button></a>
                <a href="/c/page/console/auth/Customer/customerList"><button class="btn btn-success" id="">客户列表</button></a>
                <a href="/c/page/console/auth/Customer/developModeList"><button class="btn btn-success" >开发模式</button></a>
                <a href="/c/page/console/auth/red/codeRedList"><button class="btn btn-success" >兑换码红包</button></a>
            </div>
        </div>
        <!-- 顶部栏 end -->

        <%--主内容显示区 begin--%>
        <div class="main-bd">
        </div>
        <%--主内容显示区 end --%>
    </tiles:putAttribute>
</tiles:insertTemplate>
