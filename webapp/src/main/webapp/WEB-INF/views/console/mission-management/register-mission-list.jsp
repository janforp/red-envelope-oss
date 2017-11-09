<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page pageEncoding="UTF-8" %>
<tiles:insertTemplate template="/WEB-INF/views/base/layout/layout-default.jsp">
    <tiles:putAttribute name="title" value="注册任务"/>
    <tiles:putAttribute name="enableConsole" value="true"/>
    <tiles:putAttribute name="enableSidebar" value="true"/>
    <tiles:putAttribute name="styleArea">
        <style>
           table img {
                width: 30px;
                height: 30px;
            }
            input[type=checkbox]{
                width: 20px;
                height: 20px;
            }
        </style>

    </tiles:putAttribute>
    <tiles:putAttribute name="scriptArea">
        <script src="/client/js/page/mission/register-mission-list.js" type="text/javascript"></script>
    </tiles:putAttribute>
    <tiles:putAttribute name="mainArea">
        <!-- 顶部栏 begin -->
        <div class="panel panel-default main-hd">
            <div class="panel-heading">
                <span>注册任务&nbsp;&nbsp;&nbsp;&nbsp;</span>
                <label style="margin-left: 40px;margin-right: 20px;font-size: large;">任务名称:</label>
                <input type="text" id="missionTitle" placeholder="输入任务名称" style="height: 37px;width: 175px;">
                <button class="btn btn-default" type="button">Go!</button>
            </div>
        </div>
        <div class="panel panel-default main-hd">
            <div class="panel-heading">
                <button class="btn btn-success" onclick="updateMission();">修改</button>
                <button class="btn btn-success" onclick="deleteMission();">删除</button>
                <button class="btn btn-success" onclick="addMission();">添加</button>
                <button class="btn btn-success" onclick="showDetail();">详情</button>
                <button class="btn btn-success" id="addRed" onclick="addRecommendMissionRed();">增加任务个数</button>
                <button class="btn btn-success" id="addStep" onclick="addStep();">添加或修改步骤</button>
            </div>
        </div>
        <div id="alertFrame"  class="alert alert-danger hide" style="margin: 20px 20px 10px 20px;" >
            <a href="#" class="close" id="close" >
                &times;
            </a>
            <label style="margin: 0px 10px;color: black;font-size:large;"> 增加个数:</label>
            <input type="text" id="totalRedNum"  placeholder="必填"  />
            <button class="btn-danger btn" onclick="doAddRed();">确定添加</button>
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
                        <th>任务名字</th>
                        <th>任务图标</th>
                        <th>任务标签</th>
                        <th>最小金额(元)</th>
                        <th>最大金额(元)</th>
                        <th>开始时间</th>
                        <th>结束时间</th>
                        <th>总数量</th>
                        <th>剩余数量</th>
                    </tr>
                    </thead>
                    <tbody id="moneyBody"></tbody>

                    <tfoot>
                    <tr>
                        <td colspan="10" id="pagination">
                        </td>
                    </tr>
                    </tfoot>
                </table>
            </div>
        </div>
        <%--主内容显示区 end --%>
    </tiles:putAttribute>
</tiles:insertTemplate>
