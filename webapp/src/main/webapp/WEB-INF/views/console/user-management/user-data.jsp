<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page pageEncoding="UTF-8" %>
<tiles:insertTemplate template="/WEB-INF/views/base/layout/layout-default.jsp">
    <tiles:putAttribute name="title" value="用户数据"/>
    <tiles:putAttribute name="enableConsole" value="true"/>
    <tiles:putAttribute name="enableSidebar" value="true"/>
    <tiles:putAttribute name="styleArea">
    </tiles:putAttribute>
    <tiles:putAttribute name="scriptArea">
        <script src="/plugins/My97DatePicker/function.js"></script>
        <script src="/plugins/My97DatePicker/WdatePicker.js"></script>
        <script type="text/javascript" src="/client/js/page/user/user-data.js"></script>
    </tiles:putAttribute>
    <tiles:putAttribute name="mainArea">
        <!-- 顶部栏 begin -->
        <div class="panel panel-default main-hd">
            <div class="panel-heading">
                <span class="pageTitle">用户数据</span>
            </div>
        </div>
        <!-- 顶部栏 end -->

        <%--主内容显示区 begin--%>
        <div class="main-bd">

            <span class="searchTitle">开始日期:</span>
            <input type="text" id="startDate" class="time" onclick="WdatePicker({lang:'zh-cn', dateFmt:'yyyy-MM-dd', maxDate:'%y-%M-{%d-1}'});"/>
            <span class="searchTitle">结束日期:</span>
            <input type="text" id="endDate" class="time" onclick="WdatePicker({lang:'zh-cn', dateFmt:'yyyy-MM-dd', maxDate:'%y-%M-{%d-1}'});"/>
            &nbsp;&nbsp;
            <button class="btn btn-default btn-sm navbar-btn" onclick="selectDataByTime();">
                <i class="glyphicon glyphicon-search"></i>查询
            </button>
            &nbsp;&nbsp;
            <button class="btn btn-default btn-sm navbar-btn" onclick="window.location.reload()" >
                返回
            </button>
            <c:if test="${ADMIN.loginName == 'summer'}">
                &nbsp;&nbsp;
                <button class="btn btn-default btn-sm navbar-btn" onclick="statistics();">
                    生成某一天数据
                </button>
            </c:if>

            <div id="dataDiv">
                <span class="tableTitle">&nbsp;今天:&nbsp;${today.startTime}</span>
                &nbsp;&nbsp;
                <button class="btn btn-default btn-sm navbar-btn" onclick="selectChannelDataByDateScope('${today.startTime}','${today.startTime}');"><i class="glyphicon glyphicon-search"></i>渠道详情</button>
                <table class="table table-striped table-hover table-bordered table-navbar-no-bottom">
                    <tr align="center">
                        <td colspan="2">用户</td>
                        <td colspan="4">金额</td>
                        <td colspan="4">金币</td>
                    </tr>
                    <tr align="center">
                        <td>今日新增(人)</td>
                        <td>累计(人)</td>
                        <td>新增用户发放金额(元)</td>
                        <td>今日总发放金额(元)</td>
                        <td>新增用户消耗金额(元)</td>
                        <td>今日总消耗金额(元)</td>
                        <td>新增用户发放金币(个)</td>
                        <td>今日总发放金币(个)</td>
                        <td>新增用户消耗金币(个)</td>
                        <td>今日总消耗金币(个)</td>
                    </tr>
                    <tr align="center">
                        <td>${today.todayUser}</td>
                        <td>${today.totalUser}</td>
                        <td>${today.todayUserGiveMoney}</td>
                        <td>${today.todayTotalGiveMoney}</td>
                        <td>${today.todayUserExpendMoney}</td>
                        <td>${today.todayTotalExpendMoney}</td>
                        <td>${today.todayUserGiveCoin}</td>
                        <td>${today.todayTotalGiveCoin}</td>
                        <td>${today.todayUserExpendCoin}</td>
                        <td>${today.todayTotalExpendCoin}</td>
                    </tr>
                </table>

                <span class="tableTitle">&nbsp;昨日:&nbsp;${yesterday.startTime}</span>
                &nbsp;&nbsp;
                <button class="btn btn-default btn-sm navbar-btn"  onclick="selectChannelDataByDateScope('${yesterday.startTime}','${yesterday.startTime}');"><i class="glyphicon glyphicon-search"></i>渠道详情</button>
                <table class="table table-striped table-hover table-bordered table-navbar-no-bottom">
                    <tr align="center">
                        <td colspan="2">用户</td>
                        <td colspan="4">金额</td>
                        <td colspan="4">金币</td>
                    </tr>
                    <tr align="center">
                        <td>昨日新增(人)</td>
                        <td>累计(人)</td>
                        <td>新增用户发放金额(元)</td>
                        <td>昨日总发放金额(元)</td>
                        <td>新增用户消耗金额(元)</td>
                        <td>昨日总消耗金额(元)</td>
                        <td>新增用户发放金币(个)</td>
                        <td>昨日总发放金币(个)</td>
                        <td>新增用户消耗金币(个)</td>
                        <td>昨日总消耗金币(个)</td>
                    </tr>
                    <tr align="center">
                        <td>${yesterday.todayUser}</td>
                        <td>${yesterday.totalUser}</td>
                        <td>${yesterday.todayUserGiveMoney}</td>
                        <td>${yesterday.todayTotalGiveMoney}</td>
                        <td>${yesterday.todayUserExpendMoney}</td>
                        <td>${yesterday.todayTotalExpendMoney}</td>
                        <td>${yesterday.todayUserGiveCoin}</td>
                        <td>${yesterday.todayTotalGiveCoin}</td>
                        <td>${yesterday.todayUserExpendCoin}</td>
                        <td>${yesterday.todayTotalExpendCoin}</td>
                    </tr>
                </table>

                <span class="tableTitle">&nbsp;近一周:&nbsp;${week.startTime}&nbsp;到&nbsp;${week.endTime}</span>
                &nbsp;&nbsp;
                <button class="btn btn-default btn-sm navbar-btn" onclick="selectChannelDataByDateScope('${week.startTime}','${week.endTime}');"><i class="glyphicon glyphicon-search"></i>渠道详情</button>
                <table class="table table-striped table-hover table-bordered table-navbar-no-bottom">
                    <tr align="center">
                        <td colspan="2">用户</td>
                        <td colspan="4">金额</td>
                        <td colspan="4">金币</td>
                    </tr>
                    <tr align="center">
                        <td>近一周新增(人)</td>
                        <td>累计(人)</td>
                        <td>新增用户发放金额(元)</td>
                        <td>近一周总发放金额(元)</td>
                        <td>新增用户消耗金额(元)</td>
                        <td>近一周总消耗金额(元)</td>
                        <td>新增用户发放金币(个)</td>
                        <td>近一周总发放金币(个)</td>
                        <td>新增用户消耗金币(个)</td>
                        <td>近一周总消耗金币(个)</td>
                    </tr>
                    <tr align="center">
                        <td>${week.todayUser}</td>
                        <td>${week.totalUser}</td>
                        <td>${week.todayUserGiveMoney}</td>
                        <td>${week.todayTotalGiveMoney}</td>
                        <td>${week.todayUserExpendMoney}</td>
                        <td>${week.todayTotalExpendMoney}</td>
                        <td>${week.todayUserGiveCoin}</td>
                        <td>${week.todayTotalGiveCoin}</td>
                        <td>${week.todayUserExpendCoin}</td>
                        <td>${week.todayTotalExpendCoin}</td>
                    </tr>
                </table>

                <span class="tableTitle">&nbsp;近一月:&nbsp;${month.startTime}&nbsp;到&nbsp;${month.endTime}</span>
                &nbsp;&nbsp;
                <button class="btn btn-default btn-sm navbar-btn" onclick="selectChannelDataByDateScope('${month.startTime}','${month.endTime}');"><i class="glyphicon glyphicon-search"></i>渠道详情</button>
                <table class="table table-striped table-hover table-bordered table-navbar-no-bottom">
                    <tr align="center">
                        <td colspan="2">用户</td>
                        <td colspan="4">金额</td>
                        <td colspan="4">金币</td>
                    </tr>
                    <tr align="center">
                        <td>近一月新增(人)</td>
                        <td>累计(人)</td>
                        <td>新增用户发放金额(元)</td>
                        <td>近一月总发放金额(元)</td>
                        <td>新增用户消耗金额(元)</td>
                        <td>近一月总消耗金额(元)</td>
                        <td>新增用户发放金币(个)</td>
                        <td>近一月总发放金币(个)</td>
                        <td>新增用户消耗金币(个)</td>
                        <td>近一月总消耗金币(个)</td>
                    </tr>
                    <tr align="center">
                        <td>${month.todayUser}</td>
                        <td>${month.totalUser}</td>
                        <td>${month.todayUserGiveMoney}</td>
                        <td>${month.todayTotalGiveMoney}</td>
                        <td>${month.todayUserExpendMoney}</td>
                        <td>${month.todayTotalExpendMoney}</td>
                        <td>${month.todayUserGiveCoin}</td>
                        <td>${month.todayTotalGiveCoin}</td>
                        <td>${month.todayUserExpendCoin}</td>
                        <td>${month.todayTotalExpendCoin}</td>
                    </tr>
                </table>
            </div>
        </div>
        <%--主内容显示区 end --%>
    </tiles:putAttribute>
</tiles:insertTemplate>
