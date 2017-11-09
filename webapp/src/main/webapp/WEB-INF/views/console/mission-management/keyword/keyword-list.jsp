<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page pageEncoding="UTF-8" %>
<tiles:insertTemplate template="/WEB-INF/views/base/layout/layout-default.jsp">
    <tiles:putAttribute name="title" value="试玩任务"/>
    <tiles:putAttribute name="enableConsole" value="true"/>
    <tiles:putAttribute name="enableSidebar" value="true"/>
    <tiles:putAttribute name="styleArea">
        <style>
            tbody input{
                width: 30px;height: 30px;
            }
            .one-row{
                margin: 10px 0;
            }
            .market{
                height: 30px;
                width: auto;
            }
            .app{
                text-align: left;
            }
            .app img{
                width: 30px;
                height: 30px;
            }
            .wait{
                color: red;
            }
            .ing{
                color: blue;
            }
            .end{
                color: chartreuse;
            }
            td{
                text-align: center;
            }
            th{
                text-align: center;
            }
            #pagination{
                text-align: left;
            }
        </style>
    </tiles:putAttribute>
    <tiles:putAttribute name="scriptArea">
        <script src="/methods/util/util.js" type="text/javascript"></script>
        <script src="/client/js/base/control-convert.js" type="text/javascript"></script>
        <script src="/plugins/upload/image-upload/js/image-upload.js" type="text/javascript"></script>
        <script src="/plugins/My97DatePicker/function.js"></script>
        <script src="/plugins/My97DatePicker/WdatePicker.js"></script>
        <script src="/client/js/page/mission/keyword-list.js"></script>

    </tiles:putAttribute>
    <tiles:putAttribute name="mainArea">
        <!-- 顶部栏 begin -->
        <div class="panel panel-default main-hd">
            <div class="panel-heading">
                <span class="pageTitle">试玩任务</span>
                <span class="searchTitle">app名称:</span>
                <input type="text" id = "appName"/>
                <span class="searchTitle">市场:</span>
                <select id="market" style="width: 100px;height: 40px;margin-left: 10px;">
                    <option value="">请选择</option>
                    <c:forEach items="${markets}" var="market">
                        <option value="${market.marketId}">${market.marketName}</option>
                    </c:forEach>
                </select>
                <span class="searchTitle">关键词:</span>
                <input type="text" id = "keyword"/>
                <span class="searchTitle">状态:</span>
                <select id="status" style="width: 100px;height: 40px;margin-left: 10px;">
                    <option value="">请选择</option>
                    <option value="0">未开始</option>
                    <option value="1">进行中</option>
                    <option value="2">已结束</option>
                </select>
                <span class="searchTitle">开始时间:</span>
                <input type="text" id = "releaseTime" style="height: 30px;"  class="Wdate" onclick="WdatePicker({lang:'zh-cn',dateFmt:'yyyy-MM-dd HH:mm:ss'});"/>
                <button class="btn btn-default" type="button" onclick="turnPage(1)">
                    <span class="glyphicon glyphicon-search"></span>
                    查询
                </button>
            </div>
        </div>

        <div class="panel panel-default main-hd">
            <div class="panel-heading">
                <a id="add" class="btn btn-default  navbar-btn" href="/c/page/console/auth/demo/toAddPage"><span class="glyphicon glyphicon-plus"></span>添加</a>
                <a id="copy" class="btn btn-default  navbar-btn" onclick='copyWord();'><span class="glyphicon glyphicon-plus-sign"></span>克隆</a>
                <a class="btn btn-default  navbar-btn" onclick='editWord();'><span class="glyphicon glyphicon-edit"></span>修改</a>
                <a class="btn btn-default navbar-btn" onclick='deleteWord();'><span class="glyphicon glyphicon-trash"></span>删除</a>
                <a class="btn btn-default navbar-btn" href="/c/page/console/auth/app/appListPage"><span class="glyphicon glyphicon-list"></span>APP列表</a>
            </div>
        </div>
        <!-- 顶部栏 end -->
        <%--主内容显示区 begin--%>
        <div class="main-bd">
            <div>
                <table class="table table-striped table-hover table-bordered table-row-check table-condensed">
                    <thead>
                    <tr>
                        <th>选择</th>
                        <th>APP</th>
                        <th>市场</th>
                        <th>关键词</th>
                        <th>金额(元)</th>
                        <th>次数</th>
                        <th>完成次数</th>
                        <th>投放时间</th>
                        <th>结束时间</th>
                        <th>状态</th>
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
