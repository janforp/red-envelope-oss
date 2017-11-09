<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page pageEncoding="UTF-8" %>
<tiles:insertTemplate template="/WEB-INF/views/base/layout/layout-default.jsp">
    <tiles:putAttribute name="title" value="审核任务"/>
    <tiles:putAttribute name="enableConsole" value="true"/>
    <tiles:putAttribute name="enableSidebar" value="true"/>
    <tiles:putAttribute name="styleArea">
    </tiles:putAttribute>
    <tiles:putAttribute name="scriptArea">
        <script src="/methods/redPackageManager/recommend_task.js" type="text/javascript"></script>
        <script src="/plugins/My97DatePicker/function.js"></script>
        <script src="/plugins/My97DatePicker/WdatePicker.js"></script>
        <script>
            var missionId = '${mission.missionId}';

            function selectAll() {

                var $selectAll = $("input[name=checkAll]:checked");
                var isOne = $selectAll.length;
                var input =  $('input[name=task]');
                if (isOne == 0){
                    $(input).removeAttr('checked');
                }else {
                    $(input).prop('checked',true);
                }
            };

            /**
             * 导入生成excel
             */
            function exportAllToExcel() {
                var startDate = $("#startDate").val().trim();
                var endDate = $("#endDate").val().trim();
                var status = $("#status").val();
                tips.loading();
                window.location.href='/c/page/console/auth/recommendTask/excel?missionId='+missionId+"&startDate="+startDate+"&endDate="+endDate+"&status="+status;
                tips.hideLoading();
            }
        </script>
    </tiles:putAttribute>
    <tiles:putAttribute name="mainArea">
        <!-- 顶部栏 begin -->
        <div class="panel panel-default main-hd">
            <div class="panel-heading">
                <span>用户任务&nbsp;&nbsp;&nbsp;&nbsp;</span>
                <label class="status" style="margin-left: 0px;font-size: large;">审核状态:</label>
                <select class="status" id="status" style="width: 100px;height: 40px;margin-left: 10px;">
                    <option value="1">审核中</option>
                    <option value="0">进行中</option>
                    <option value="2">审核通过</option>
                    <option value="3">未通过</option>
                    <option value="4">已过期</option>
                    <option value="5">打印全部</option>
                </select>
                <label style="margin-left: 40px;margin-right: 20px;font-size: large;">任务名称:</label>
                <input type="text" id="missionTitle" value="${mission.missionTitle}" disabled style="height: 37px;width: 175px;">
                <span class="searchTitle">开始日期:</span>
                <input type="text" id = "startDate"  class="time" onclick="WdatePicker({lang:'zh-cn',dateFmt:'yyyy-MM-dd HH:mm:ss'});"/>
                <span class="searchTitle">结束日期:</span>
                <input type="text" id = "endDate"  class="time" onclick="WdatePicker({lang:'zh-cn',dateFmt:'yyyy-MM-dd HH:mm:ss'});"/>
                <label style="margin-left: 40px;margin-right: 20px;font-size: large;">用户手机:</label>
                <input type="text" id="userPhone" placeholder="输入用户注册手机号" style="height: 37px;width: 175px;">
                <input type="text" id="pageSize" style="margin-left: 25px;margin-right: 25px;height: 37px;width: 175px;" placeholder="请输入每页个数">
                <button class="btn btn-default" type="button" id="listByPageSize">Go!</button>
                <span id="pageSizeAlert" style="color: red;margin-left: 20px;"></span>
            </div>
        </div>

        <div class="panel panel-default main-hd">
            <div class="panel-heading">
                <button class="btn btn-success" onclick="gotoVerify();">单个审核</button>
                <button class="btn btn-success" onclick="batchVerify();">审核通过</button>
                <button class="btn btn-danger" onclick="batchNotVerify();">审核未通过</button>
                <button class="btn btn-primary" onclick="exportAllToExcel();">导出为Excel</button>
            </div>
        </div>
        <!-- 顶部栏 end -->

        <%--主内容显示区 begin--%>
        <div class="main-bd">
            <div class="separator_line">
            </div>
            <div id="moneyDiv" style="padding-bottom: 2rem;display: none;">
                <div class="col-md-6">
                    <div class="input-group ">
                        <span class="input-group-addon">金额</span>
                        <input class="form-control" id="money">
                    </div>
                </div>
                <div class="col-md-6 col-md-inset-2">
                    <button onclick="sureBatchVerify();" class="btn btn-info btn-danger">确定通过</button>
                </div>
                <br>
            </div>
            <div id="remarksDiv" style="padding-bottom: 2rem;display: none;">
                <div class="col-md-6">
                    <div class="input-group ">
                        <span class="input-group-addon">作废原因</span>
                        <input class="form-control" id="remarks">
                    </div>
                </div>
                <div class="col-md-6 col-md-inset-2">
                    <button onclick="sureBatchNotVerify();" class="btn btn-info btn-danger">确定未通过</button>
                </div>
                <br>
             </div>
            <div>
                <table class="table table-striped table-hover table-bordered table-navbar-no-bottom">
                    <thead>
                    <tr class="">
                        <th><input type="checkbox" id="checkAll" name="checkAll" onclick="selectAll();"></th>
                        <th>序号</th>
                        <th>状态</th>
                        <th>任务名称</th>
                        <th>奖励</th>
                        <th>手机</th>
                        <th>提交时间</th>
                    </tr>
                    </thead>
                    <tbody id="missionBody">

                    </tbody>
                    <tfoot>
                    <tr>
                        <td colspan="7">
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
