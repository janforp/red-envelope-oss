<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page pageEncoding="UTF-8" %>
<tiles:insertTemplate template="/WEB-INF/views/base/layout/layout-default.jsp">
    <tiles:putAttribute name="title" value="步骤"/>
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
        </style>
    </tiles:putAttribute>
    <tiles:putAttribute name="scriptArea">
        <script src="/methods/step.js" type="text/javascript"></script>
    </tiles:putAttribute>
    <tiles:putAttribute name="mainArea">

        <!-- 顶部栏 begin -->
        <div class="panel panel-default main-hd">
            <div class="panel-heading">
                <div id="queryForm" class="fr mt-4">
                    <div class="fr pr5">
                        <div class="input-group input-group-sm w100 fl">
                        </div>
                    </div>
                    <div class="fr pr5">
                        <div class="input-group input-group-sm w100 fl">
                            <label style="margin-right: 20px;"></label><span></span>
                        </div>
                    </div>
                </div>
                <span>步骤</span>
                <label style="margin-left: 100px;">推广ID:</label>
                <span>${id}</span>
                <label style="margin-left: 100px;">客户名:</label>
                <span>${name}</span>
            </div>
        </div>
        <!-- 顶部栏 end -->

        <%--主内容显示区 begin --%>
        <div class="main-bd">
            <div class="separator_line">
            </div>
            <br>
            <button  class="btn btn-success" id ="addStep" style="width: 90px;margin-left: 50px">添加步骤</button>
            <button  class="btn btn-success" id ="removeLastStep" style="width: 90px;margin-left: 100px">移除最后</button>
            <button  class="btn btn-success" id ="saveStep" onclick="save(${id});" style="width: 90px;margin-left: 100px">保存步骤</button>
            <br>
                <%--分割线--%>
            <div class="separator_line">
            </div>

            <div  class="collapse navbar-collapse">
                <table id="step">
                    <c:forEach items="${list}" var="step">
                        <tr>
                            <td>
                                <label style='position: relative; bottom:40px;margin-right: 20px'>${step.stepNum} :</label>
                                <textarea  style='width: 450px;'>${step.stepContent}</textarea>
                                <br>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </div>

        </div>
        <%--主内容显示区 end --%>
    </tiles:putAttribute>
</tiles:insertTemplate>
