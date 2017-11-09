<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page pageEncoding="UTF-8" %>
<tiles:insertTemplate template="/WEB-INF/views/base/layout/layout-default.jsp">
    <tiles:putAttribute name="title" value="需审核任务"/>
    <tiles:putAttribute name="enableConsole" value="true"/>
    <tiles:putAttribute name="enableSidebar" value="true"/>
    <tiles:putAttribute name="styleArea">
        <style>
            table img{
                width: 30px;
                height: 30px;;
            }
        </style>
    </tiles:putAttribute>
    <tiles:putAttribute name="scriptArea">
        <script src="/methods/redPackageManager/recommend_mission.js" type="text/javascript"></script>
    </tiles:putAttribute>
    <tiles:putAttribute name="mainArea">
        <!-- 顶部栏 begin -->
        <div class="panel panel-default main-hd">
            <div class="panel-heading">
                <span>推荐任务&nbsp;&nbsp;&nbsp;&nbsp;</span>
                <label style="margin-left: 0px;font-size: large;display: none">任务分类:</label>
                <select id="type" style="width: 100px;height: 40px;margin-left: 10px;display: none">
                    <option value="0">推荐任务</option>
                </select>
                <label style="margin-left: 0px;font-size: large;">是否审核:</label>
                <select id="isVerify" style="width: 100px;height: 40px;margin-left: 10px;">
                    <option value="">请选择</option>
                    <option value="0">需审核</option>
                    <option value="1">不审核</option>
                </select>
                <label style="margin-left: 0px;font-size: large;">是否限时:</label>
                <select id="isLimitTime" style="width: 100px;height: 40px;margin-left: 10px;">
                    <option value="">请选择</option>
                    <option value="0">限时</option>
                    <option value="1">不限时</option>
                </select>
                <label style="margin-left: 40px;margin-right: 20px;font-size: large;">任务名称:</label>
                <input type="text" id="missionTitle" placeholder="输入任务名称" style="height: 37px;width: 175px;">
                <input type="text" id="pageSize" style="margin-left: 25px;margin-right: 25px;height: 37px;width: 175px;" placeholder="请输入每页个数">
                <button class="btn btn-default" type="button" id="listByPageSize">Go!</button>
                <span id="pageSizeAlert" style="color: red;margin-left: 20px;"></span>
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
                <button class="btn btn-success" onclick="showTaskList();">查看用户提交任务列表</button>
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
                        <th>最小金额</th>
                        <th>最大金额</th>
                        <th>结束时间</th>
                        <th>总数量</th>
                        <th>剩余数量</th>
                        <th>是否限时</th>
                        <th>是否审核</th>
                        <th>任务排序</th>
                        <th>平台</th>
                    </tr>
                    </thead>
                    <tbody id="missionBody">

                    </tbody>
                    <tfoot>
                    <tr>
                        <td colspan="13">
                            <button id="pageBefore" style="background-color: white;"><span style="color: blue;font-size: large">上一页</span></button>
                            <span id="pageNow">0</span>
                            <span style="margin-left: 5px;margin-right: 5px;">/</span>
                            <span id="totalPage">0</span>
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
