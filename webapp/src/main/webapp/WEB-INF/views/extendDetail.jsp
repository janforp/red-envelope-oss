<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page pageEncoding="UTF-8" %>
<tiles:insertTemplate template="/WEB-INF/views/base/layout/layout-default.jsp">
    <tiles:putAttribute name="title" value="推广效果"/>
    <tiles:putAttribute name="enableConsole" value="true"/>
    <tiles:putAttribute name="enableSidebar" value="true"/>
    <tiles:putAttribute name="styleArea">
        <style>
            .separator_line {
                margin-top: 10px;
                height: 1px;
                background-color: #CCCCCC;
            }
            .table tbody td {
                text-align: center;
                white-space:nowrap;
            }
            .table tbody td img {
                width:60px;
                height:60px;
            }
            .table thead th {
                text-align: center;
            }
        </style>
    </tiles:putAttribute>
    <tiles:putAttribute name="scriptArea">

        <script src="/client/js/base/control-convert.js" type="text/javascript"></script>
        <script src="/client/js/base/validation.js" type="text/javascript"></script>
        <script src="/plugins/upload/image-upload/js/my.jquery-post-upload-styleX.js" type="text/javascript"></script>
        <script src="/methods/extendDetail.js" type="text/javascript"></script>
        <script>
            var customerName = "${customerName}";
        </script>


    </tiles:putAttribute>
    <tiles:putAttribute name="mainArea">

        <!-- 顶部栏 begin -->
        <div class="panel panel-default main-hd">
            <div class="panel-heading">
                <div id="queryForm" class="fr mt-4">
                    <div class="fr pr5">
                        <div class="input-group input-group-sm w100 fl">
                            <button class="btn btn-success" id ="today" onclick="seeToday(customerName);" style="width: 90px">今天</button>
                        </div>
                    </div>
                </div>
                <span style="font-size: larger">推广详情</span>
            </div>
        </div>
        <!-- 顶部栏 end -->

        <%--主内容显示区 begin --%>
        <div class="main-bd">

                <%--查询--%>
            <div class="form-horizontal">
            </div>

                <%--分割线--%>
            <div class="separator_line">
            </div>

                <%--数据表格--%>
            <div style="width:100%; overflow:scroll;">
                <table class="table table-striped table-hover table-bordered table-navbar-no-bottom">
                    <thead>
                        <tr>
                            <th width='5%'>序号</th>
                            <th width='5%'>昵称</th>
                            <th width='5%'>头像</th>
                            <th width='5%'>时间</th>
                        </tr>
                    </thead>
                    <tbody id="tbody">
                        <c:forEach items="${user}" var="use" varStatus="status">
                            <tr>
                                <td>${status.index+1}</td>
                                <td>${use.userNickname}</td>
                                <td><img src="${use.userImg}" /></td>
                                <td>${use.showTime}</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                    <tfoot id="tfoot">
                        <tr>
                            <td colspan="4">
                                <button id="pageBefore" style="background-color: white;"><span style="color: blue;font-size: large">上一页</span></button>
                                <span id="pageNow">1</span>
                                <span style="margin-left: 5px;margin-right: 5px;">/</span>
                                <span id="totalPage">${totalPage}</span>
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
