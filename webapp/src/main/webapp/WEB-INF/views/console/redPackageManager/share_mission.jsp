<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page pageEncoding="UTF-8" %>
<tiles:insertTemplate template="/WEB-INF/views/base/layout/layout-default.jsp">
    <tiles:putAttribute name="title" value="分享任务"/>
    <tiles:putAttribute name="enableConsole" value="true"/>
    <tiles:putAttribute name="enableSidebar" value="true"/>
    <tiles:putAttribute name="styleArea">
         <style>
             table img {
                 width: 30px;
                 height: 30px;
             }
         </style>
    </tiles:putAttribute>
    <tiles:putAttribute name="scriptArea">
        <script src="/methods/redPackageManager/share_mission.js" type="text/javascript"></script>
    </tiles:putAttribute>
    <tiles:putAttribute name="mainArea">
        <!-- 顶部栏 begin -->
        <div class="panel panel-default main-hd">
            <div class="panel-heading">
                <span>分享任务&nbsp;&nbsp;&nbsp;&nbsp;</span>
                <label style="margin-left: 0px;font-size: large;">是否结束:</label>
                <select id="isEnd" style="width: 100px;height: 40px;margin-left: 10px;">
                    <option value="1">进行中</option>
                    <option value="0">已结束</option>
                </select>
                <label style="margin-left: 40px;margin-right: 20px;font-size: large;">商家名称:</label>
                <input type="text" id="merchantName" placeholder="输入商家名称" style="height: 37px;width: 175px;">
                <label style="margin-left: 40px;margin-right: 20px;font-size: large;">任务名称:</label>
                <input type="text" id="missionTitle" placeholder="输入任务名称" style="height: 37px;width: 175px;">
                <input type="text" id="pageSize" style="margin-left: 25px;margin-right: 25px;height: 37px;width: 175px;" placeholder="输入数字,默认15,按回车">
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
                <button class="btn btn-success" id="addRed" onclick="addShareMissionRed();">增加红包</button>
                <button class="btn btn-success" onclick="closeShareMission();">关闭任务</button>
                <button class="btn btn-success" onclick="openShareMission();">打开任务</button>
            </div>
        </div>
        <div id="alertFrame"  class="alert alert-danger hide" style="margin: 20px 20px 10px 20px;" >
            <a href="#" class="close" id="close" >
                &times;
            </a>
            <label style="margin-right: 10px;color: black;font-size:large;"> 最大金额:</label>
            <input type="text" id="maxMoney" placeholder="默认为100" />分
            <label style="margin: 0px 10px;color: black;font-size:large;"> 最小金额:</label>
            <input type="text" id="minMoney"  value="${mission.maxMoney}" placeholder="默认为100" />分
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
                        <th>中奖概率</th>
                        <th>红包总数量</th>
                        <th>剩余数量</th>
                        <th>分享标题</th>
                        <th>地区</th>
                        <th>打开图片</th>
                        <th>成功图片</th>
                        <th>失败图片</th>
                    </tr>
                    </thead>
                    <tbody id="missionBody">

                    </tbody>
                    <tfoot>
                    <tr>
                        <td colspan="10">
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
