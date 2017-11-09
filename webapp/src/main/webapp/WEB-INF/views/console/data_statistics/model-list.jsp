<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page pageEncoding="UTF-8" %>
<tiles:insertTemplate template="/WEB-INF/views/base/layout/layout-default.jsp">
    <tiles:putAttribute name="title" value="数据统计"/>
    <tiles:putAttribute name="enableConsole" value="true"/>
    <tiles:putAttribute name="enableSidebar" value="true"/>
    <tiles:putAttribute name="styleArea">
        <%--<link rel="stylesheet" type="text/css" href="/client/css/template/base.css">--%>
        <style>
            th,td{
                text-align: center;
            }
            #startDate,#endDate{
                height: 30px;
            }
        </style>
    </tiles:putAttribute>
    <tiles:putAttribute name="scriptArea">
        <script src="/plugins/My97DatePicker/function.js"></script>
        <script src="/plugins/My97DatePicker/WdatePicker.js"></script>
        <script>



            function executeDate() {

                var oneDate = $("#startDate").val().trim();
                $.ajax({
                    url:"/c/page/console/auth/data/execute",
                    type:"post",
                    dataType:"json",
                    data:{oneDate:oneDate}
                });
            }
            /**
             * 安模块查看数据
             * @param model
             */
            function searchModule(module) {

                var startDate = $("#startDate").val().trim();
                var endDate = $("#endDate").val().trim();
                if (startDate == "" || startDate.length==0 || endDate == ''||endDate.length==0){
                    tips.err('请输入正确的查询时间',2000);
                    return;
                }
                window.location.href='/c/page/console/auth/data/model?module='+module+"&startDate="+startDate+"&endDate="+endDate;
            }
        </script>
    </tiles:putAttribute>
    <tiles:putAttribute name="mainArea">
        <!-- 顶部栏 begin -->
        <div class="panel panel-default main-hd">
            <div class="panel-heading">
                <label>起始时间</label>
                <input type="text" id = "startDate" class="btn Wdate" onclick="WdatePicker({lang:'zh-cn',dateFmt:'yyyy-MM-dd'});" value="${weekAgo}"/>
                <label>结束时间</label>
                <input type="text" id = "endDate" class="btn Wdate" onclick="WdatePicker({lang:'zh-cn',dateFmt:'yyyy-MM-dd'});" value="${today}"/>
            </div>
        </div>
        <div class="panel panel-default main-hd">
            <div class="panel-heading">
                <c:if test="${ADMIN.loginName == 'summer'}">
                    <a id="execute" class="btn btn-default  navbar-btn" href="javaScript:executeDate();" data-toggle="tooltip" title="请在起始时间框中选择要手动统计的日期" data-placement="right top">
                        <span class="glyphicon glyphicon-hand-up"></span>
                        手动统计
                    </a>
                </c:if>
            </div>

        </div>
        <!-- 顶部栏 end -->
        <%--主内容显示区 begin--%>
        <div class="main-bd">
            <table class="table table-bordered table-striped table-hover table-condensed table-responsive" style="width: 60%">
                <thead>
                    <tr>
                        <th style="width: 20%">序号</th>
                        <th style="width: 60%">模块名称</th>
                        <th style="width: 20%">操作</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>1</td>
                        <td class="info">高额任务</td>
                        <td class="success">
                            <button class="btn btn-info tip" onclick="searchModule(5);" href="#" data-toggle="tooltip" title="请选择查询时间范围" data-placement="right top">
                                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span class="glyphicon glyphicon-search"></span>&nbsp;&nbsp;&nbsp;&nbsp;
                            </button>
                        </td>
                    </tr>
                    <tr>
                        <td>2</td>
                        <td class="info">关注任务</td>
                        <td class="success">
                            <button class="btn btn-info tip" onclick="searchModule(3);" href="#" data-toggle="tooltip" title="请选择查询时间范围" data-placement="right top">
                                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span class="glyphicon glyphicon-search"></span>&nbsp;&nbsp;&nbsp;&nbsp;
                            </button>
                        </td>
                    </tr>
                    <tr>
                        <td>3</td>
                        <td class="info">试玩任务</td>
                        <td class="success">
                            <button class="btn btn-info tip" onclick="searchModule(1);" href="#" data-toggle="tooltip" title="请选择查询时间范围" data-placement="right top">
                                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span class="glyphicon glyphicon-search"></span>&nbsp;&nbsp;&nbsp;&nbsp;
                            </button>
                        </td>
                    </tr>
                    <tr>
                        <td>3</td>
                        <td class="info">新手任务</td>
                        <td class="success">
                            <button class="btn btn-info tip" onclick="searchModule(11);" href="#" data-toggle="tooltip" title="请选择查询时间范围" data-placement="right top">
                                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span class="glyphicon glyphicon-search"></span>&nbsp;&nbsp;&nbsp;&nbsp;
                            </button>
                        </td>
                    </tr>
                    <tr>
                        <td>4</td>
                        <td class="info">分享任务</td>
                        <td class="success">
                            <button class="btn btn-info tip" onclick="searchModule(2);" href="#" data-toggle="tooltip" title="请选择查询时间范围" data-placement="right top">
                                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span class="glyphicon glyphicon-search"></span>&nbsp;&nbsp;&nbsp;&nbsp;
                            </button>
                        </td>
                    </tr>
                    <tr>
                        <td>5</td>
                        <td class="info">口令红包</td>
                        <td class="success">
                            <button class="btn btn-info tip" onclick="searchModule(9);" href="#" data-toggle="tooltip" title="请选择查询时间范围" data-placement="right top">
                                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span class="glyphicon glyphicon-search"></span>&nbsp;&nbsp;&nbsp;&nbsp;
                            </button>
                        </td>
                    </tr>
                    <tr>
                        <td>6</td>
                        <td class="info">定时红包</td>
                        <td class="success">
                            <button class="btn btn-info tip" onclick="searchModule(7);" href="#" data-toggle="tooltip" title="请选择查询时间范围" data-placement="right top">
                                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span class="glyphicon glyphicon-search"></span>&nbsp;&nbsp;&nbsp;&nbsp;
                            </button>
                        </td>
                    </tr>
                    <tr>
                        <td>7</td>
                        <td class="info">联盟任务</td>
                        <td class="success">
                            <button class="btn btn-info tip" onclick="searchModule(4);" href="#" data-toggle="tooltip" title="请选择查询时间范围" data-placement="right top">
                                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span class="glyphicon glyphicon-search"></span>&nbsp;&nbsp;&nbsp;&nbsp;
                            </button>
                        </td>
                    </tr>
                </tbody>
            </table>
        </div>
        <%--主内容显示区 end --%>

    </tiles:putAttribute>
</tiles:insertTemplate>
