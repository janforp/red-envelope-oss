<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page pageEncoding="UTF-8" %>
<tiles:insertTemplate template="/WEB-INF/views/base/layout/layout-default.jsp">
    <tiles:putAttribute name="title" value="${CURRENT_FUNCTION.functionName}"/>
    <tiles:putAttribute name="enableConsole" value="true"/>
    <tiles:putAttribute name="enableSidebar" value="true"/>
    <tiles:putAttribute name="styleArea">
        <style>
            td img {
                width: 30px;
                height: 30px;
            }
        </style>
    </tiles:putAttribute>
    <tiles:putAttribute name="scriptArea">
        <script src="/client/js/page/exchange-mission.js" type="text/javascript"></script>
    </tiles:putAttribute>
    <tiles:putAttribute name="mainArea">
        <!-- 顶部栏 begin -->
        <div class="panel panel-default main-hd">
            <div class="panel-heading">
                <span>兑换码任务</span>
            </div>
        </div>
        <div class="panel panel-default main-hd">
            <div class="panel-heading">
                <button class="btn btn-success" onclick="addMission();">添加</button>
                <button class="btn btn-success" onclick="updateMission();">修改</button>
                <button class="btn btn-success" onclick="deleteMission();">删除</button>
                <button class="btn btn-success" onclick="showDetail();">详情</button>
                <button class="btn btn-success" onclick="addMissionNum();">增加任务个数</button>
                <button class="btn btn-success" onclick="addMissionStep();">添加或修改步骤</button>
            </div>
        </div>
        <div id="alertFrame"  class="alert alert-danger hide" style="margin: 20px 20px 10px 20px;" >
            <a href="#" class="close" id="close" >
                &times;
            </a>
            <label style="margin: 0px 10px;color: black;font-size:large;"> 增加个数:</label>
            <input type="text" id="totalRedNum"  placeholder="必填"  />
            <button class="btn-danger btn" onclick="addNum();">确定添加</button>
        </div>
        <!-- 顶部栏 end -->

        <%--主内容显示区 begin --%>
        <div class="main-bd">
            <div>
                <table class="table table-striped table-hover table-bordered table-row-check">
                    <thead>
                        <tr>
                            <th>选择</th>
                            <th>任务名字</th>
                            <th>任务图标</th>
                            <th>任务标签</th>
                            <th>最小金额(元)</th>
                            <th>最大金额(元)</th>
                            <th>结束时间</th>
                            <th>总数量</th>
                            <th>剩余数量</th>
                            <th>兑换链接</th>
                            <th>任务排序</th>
                        </tr>
                    </thead>
                    <tbody id="missionBody"></tbody>
                    <tfoot>
                        <tr>
                            <td colspan="11" id="pagination">
                            </td>
                        </tr>
                    </tfoot>
                </table>
            </div>
        </div>
        <%--主内容显示区 end --%>
    </tiles:putAttribute>
</tiles:insertTemplate>
