<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page pageEncoding="UTF-8" %>
<tiles:insertTemplate template="/WEB-INF/views/base/layout/layout-default.jsp">
    <tiles:putAttribute name="title" value="提现管理new"/>
    <tiles:putAttribute name="enableConsole" value="true"/>
    <tiles:putAttribute name="enableSidebar" value="true"/>
    <tiles:putAttribute name="styleArea">
    </tiles:putAttribute>
    <tiles:putAttribute name="scriptArea">
        <script src="/plugins/My97DatePicker/function.js"></script>
        <script src="/plugins/My97DatePicker/WdatePicker.js"></script>
        <script src="/client/js/page/withdraw-list.js" type="text/javascript"></script>
    </tiles:putAttribute>
    <tiles:putAttribute name="mainArea">
        <!-- 顶部栏 begin -->
        <div class="panel panel-default main-hd">
            <div class="panel-heading">
                <span>提现列表&nbsp;&nbsp;&nbsp;&nbsp;</span>

                <label style="margin-left: 20px;font-size: large;">类别:</label>
                <select id="withdrawType" style="width: 100px;height: 40px;margin-left: 10px;">
                    <option value="">请选择</option>
                    <option value="zhifubao">支付宝</option>
                    <option value="weixin">微信</option>
                    <option value="huafei">话费</option>
                </select>
                <label style="margin-left: 20px;font-size: large;">状态:</label>
                <select id="withdrawStatus" style="width: 100px;height: 40px;margin-left: 10px;">
                    <option value="">请选择</option>
                    <option value="0">未处理</option>
                    <option value="1">已处理</option>
                    <option value="2">已作废</option>
                </select>
                <label style="margin-left: 20px;margin-right: 20px;font-size: large;">开始:</label>
                <input type="text" id = "startTime" style="width: 193px;height: 33px;" class="Wdate" onclick="WdatePicker({lang:'zh-cn',dateFmt:'yyyy-MM-dd HH:mm:ss '});"/>
                <label style="margin-left: 20px;margin-right: 20px;font-size: large;">结束:</label>
                <input type="text" id = "endTime" style="width: 193px;height: 33px;" class="Wdate" onclick="WdatePicker({lang:'zh-cn',dateFmt:'yyyy-MM-dd HH:mm:ss '});"/>
                <label style="margin-left: 20px;margin-right: 20px;font-size: large;">手机:</label>
                <input type="text" id="cellphone" style="height: 37px;width: 175px;">
                <input type="text" id="pageSize" style="margin-left: 25px;margin-right: 25px;height: 37px;width: 150px;" placeholder="每页行数,默认10">
                <button class="btn btn-default" type="button" onclick="turnPage(1)">查询</button>
                <span id="pageSizeAlert" style="color: red;margin-left: 20px;"></span>
            </div>
        </div>
        <div class="panel panel-default main-hd">
            <div class="panel-heading">
                <button class="btn btn-success" onclick="moneyRecords()">资金明细</button>
                <button class="btn btn-success" id="withdraw">确认</button>
                <button class="btn btn-success" id="invalid">作废</button>
            </div>
        </div>
        <!-- 顶部栏 end -->

        <%--主内容显示区 begin--%>
        <div class="main-bd">

            <div id="remarksDiv" style="padding: 2rem;display: none;">
                <div class="col-md-6">
                    <div class="input-group ">
                        <span class="input-group-addon">作废原因</span>
                        <input class="form-control" id="remarks">
                    </div>
                </div>
                <div class="col-md-6 col-md-inset-2">
                    <button onclick="sureInvalid();" class="btn btn-info btn-danger">确定作废</button>
                </div>
            </div>

            <br>

            <div>
                <table class="table table-striped table-hover table-bordered table-row-check">
                    <thead>
                        <tr>
                            <th>选择</th>
                            <th>编号</th>
                            <th>用户ID</th>
                            <th>账号</th>
                            <th>账户名</th>
                            <th>申请金额</th>
                            <th>到账金额</th>
                            <th>申请时间</th>
                            <th>确认时间</th>
                            <th>类型</th>
                            <th>状态</th>
                            <th>备注</th>
                        </tr>
                    </thead>
                    <tbody id="redBody"></tbody>
                    <tfoot>
                        <tr>
                            <td colspan="12" id="pagination"></td>
                        </tr>
                    </tfoot>
                </table>
            </div>
        </div>
        <%--主内容显示区 end --%>
    </tiles:putAttribute>
</tiles:insertTemplate>
