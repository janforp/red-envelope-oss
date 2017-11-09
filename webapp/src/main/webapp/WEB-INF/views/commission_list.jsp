<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page pageEncoding="UTF-8" %>
<tiles:insertTemplate template="/WEB-INF/views/base/layout/layout-default.jsp">
    <tiles:putAttribute name="title" value="佣金管理"/>
    <tiles:putAttribute name="enableConsole" value="true"/>
    <tiles:putAttribute name="enableSidebar" value="true"/>
    <tiles:putAttribute name="styleArea">
        <link rel="stylesheet" type="text/css" href="/styles/mission_sort.css">
    </tiles:putAttribute>
    <tiles:putAttribute name="scriptArea">
        <script src="/plugins/My97DatePicker/function.js"></script>
        <script src="/plugins/My97DatePicker/WdatePicker.js"></script>
        <script src="/methods/commission_list.js" type="text/javascript"></script>
    </tiles:putAttribute>
    <tiles:putAttribute name="mainArea">
        <!-- 顶部栏 begin -->
        <div class="panel panel-default main-hd">
            <div class="panel-heading">
                <span>佣金提现管理&nbsp;&nbsp;&nbsp;&nbsp;</span>
                <label style="margin-left: 20px;font-size: large;">状态:</label>
                <select id="withdrawStatus" style="width: 100px;height: 40px;margin-left: 10px;">
                    <option value="">请选择</option>
                    <option value="0">未处理</option>
                    <option value="1">已处理</option>
                    <option value="2">已作废</option>
                </select>
                <label style="margin-left: 20px;margin-right: 20px;font-size: large;">开始时间:</label>
                <input type="text" id = "startTime" style="width: 193px;height: 33px;" class="Wdate" onclick="WdatePicker({lang:'zh-cn',dateFmt:'yyyy-MM-dd HH:mm:ss '});"/>
                <label style="margin-left: 20px;margin-right: 20px;font-size: large;">结束时间:</label>
                <input type="text" id = "endTime" style="width: 193px;height: 33px;" class="Wdate" onclick="WdatePicker({lang:'zh-cn',dateFmt:'yyyy-MM-dd HH:mm:ss '});"/>
                <label style="margin-left: 20px;margin-right: 20px;font-size: large;">手机:</label>
                <input type="text" id="cellphone" placeholder="输入手机号" style="height: 37px;width: 175px;">
                <input type="text" id="pageSize" style="margin-left: 25px;margin-right: 25px;height: 37px;width: 150px;" placeholder="每页行数,默认10">
                <button class="btn btn-default" type="button" id="listByPageSize">查询</button>
                <span id="pageSizeAlert" style="color: red;margin-left: 20px;"></span>
            </div>
        </div>
        <div class="panel panel-default main-hd">
            <div class="panel-heading">
                <button class="btn btn-success" id="withdraw">确认提现</button>
                <button class="btn btn-success" id="invalid">作废</button>
            </div>
        </div>
        <!-- 顶部栏 end -->

        <%--主内容显示区 begin--%>
        <div class="main-bd">
            <div class="separator_line">
            </div>
            <br>
            <div>
                <table class="table table-striped table-hover table-bordered table-navbar-no-bottom">
                    <thead>
                        <tr>
                            <th>选择</th>
                            <th>用户名字</th>
                            <th>手机</th>
                            <th>金额</th>
                            <th>申请时间</th>
                            <th>确认时间</th>
                            <th>状态</th>
                        </tr>
                    </thead>
                    <tbody id="body">
                    </tbody>
                    <tfoot>
                        <tr style="height: 40px;">
                            <td colspan="7" >
                                <button id="pageBefore" style="background-color: white;"><span style="color: blue;font-size: large;margin:10px 0;">上一页</span></button>
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
