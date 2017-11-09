<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page pageEncoding="UTF-8" %>
<tiles:insertTemplate template="/WEB-INF/views/base/layout/layout-default.jsp">
    <tiles:putAttribute name="title" value="解锁红包"/>
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
        <script src="/methods/unlock_code_red.js" type="text/javascript"></script>
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
                </div>
                <span>解锁红包</span>
            </div>
        </div>
        <!-- 顶部栏 end -->
        <div class="panel panel-default main-hd">
            <div class="panel-heading">
                <div  class="fr mt-4">
                    <div class="fr pr5">
                        <div class="input-group input-group-sm w100 fl">
                        </div>
                    </div>
                </div>
                <span style="margin-right: 100px;">id:${red.codeId}</span>
                <span style="margin-right: 100px;">名称:${red.customerName}</span>
                <span style="margin-right: 100px;">剩余红包数:${red.redNumLeft}</span>
            </div>
        </div>
        <%--主内容显示区 begin --%>
        <div class="main-bd">
            <div class="separator_line">
            </div>
            <br>


            <div class="collapse navbar-collapse">
                <span>
                    <label style="margin-right: 40px;color: blueviolet"> 解锁个数:</label>
                     <input type="text" id="unlock" />
                     <span style="color: red;margin-left: 10px;">需填写整数,若填写为 100.0 则不合法</span>
                </span>
            </div>
            <br>
            <div class="input-group input-group-sm w100 fl">
                <button class="btn btn-success" onclick="saveRed(${red.codeId});"  style="width: 90px">保存</button>
            </div>
        </div>
        <%--主内容显示区 end --%>
    </tiles:putAttribute>
</tiles:insertTemplate>
