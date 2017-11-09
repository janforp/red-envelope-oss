<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page pageEncoding="UTF-8" %>
<tiles:insertTemplate template="/WEB-INF/views/base/layout/layout-default.jsp">
    <tiles:putAttribute name="title" value="${title}详情"/>
    <tiles:putAttribute name="enableConsole" value="true"/>
    <tiles:putAttribute name="enableSidebar" value="true"/>
    <tiles:putAttribute name="styleArea">
        <%--<link rel="stylesheet" type="text/css" href="/client/css/template/base.css">--%>
        <style>
            /*table{*/
                /*text-align: center;*/
            /*}*/
        </style>
    </tiles:putAttribute>
    <tiles:putAttribute name="scriptArea">
        <script>
            /**
             * 翻页
             */
            function turnPage(pageNum) {

                var module = '${module}';
                var date = '${date}';

                tips.loading();
                $.ajax({
                    url: "/c/page/console/auth/data/dayDetailPage",
                    type: "POST",
                    dataType: "JSON",
                    data: {
                        module:module,
                        oneDate:date,
                        pageNum: pageNum
                    },
                    success: function(data){
                        if (data.success) {
                            var vos = data.bean.details;
                            var body = $("#moneyBody");
                            body.empty();
                            var newTbody = "";
                            for (var i = 0; i < vos.length; i++) {
                                newTbody +=
                                    "<tr>"+
                                        "<td>"+(i+1)+"</td>"+
                                        "<td>"+vos[i].detailDate+"</td>"+
                                        "<td>"+vos[i].userId+"</td>"+
                                        "<td>"+vos[i].userName+"</td>"+
                                        "<td>"+vos[i].missionName+"</td>"+
                                        "<td>"+vos[i].money+"</td>"+
                                        "<td>已领取</td>"+
                                    "</tr>";
                            }
                            body.append(newTbody);

                            // 分页条
                            $("#pagination").html(data.bean.page);

                            tips.hideLoading();

                        }else{
                            tips.err("操作失败",2000);
                        }

                    },
                    error: function () {
                    }
                });
            };

        </script>
    </tiles:putAttribute>
    <tiles:putAttribute name="mainArea">
        <!-- 顶部栏 begin -->
        <div class="panel panel-default main-hd">
            <div class="panel-heading">
                <span class="pageTitle">${title}&nbsp;${date}&nbsp;详情${date}</span>
            </div>
        </div>

        <div class="panel panel-default main-hd">
            <div class="panel-heading">
                <a id="add" class="btn btn-default  navbar-btn" href="/c/page/console/auth/demo/toAddPage"><span class="glyphicon glyphicon-list-alt"></span>导至Excel</a>
            </div>
        </div>
        <!-- 顶部栏 end -->
        <%--主内容显示区 begin--%>
        <div class="main-bd">
            <table class="table table-striped table-hover table-bordered table-row-check table-condensed table-responsive">
                <thead>
                <tr>
                    <th>序号</th>
                    <th>日期</th>
                    <th>用户ID</th>
                    <th>用户昵称</th>
                    <th>任务名称</th>
                    <th>应发金额(元)</th>
                    <th>领取状态</th>
                </tr>
                </thead>
                <tbody id="moneyBody">
                    <c:forEach items="${details}" var="v" varStatus="status">
                        <tr>
                            <td>${status.index+1}</td>
                            <td>${v.detailDate}</td>
                            <td>${v.userId}</td>
                            <td>${v.userName}</td>
                            <td>${v.missionName}</td>
                            <td>${v.money}</td>
                            <c:choose>
                                <c:when test="${module == 5}"><%--// 高额状态; 0-进行中 1-审核中 2-审核通过 3-未通过 4-已过期--%>
                                    <td>
                                        <c:if test="${v.drawStatus == 1}">审核中</c:if>
                                        <c:if test="${v.drawStatus == 3}">未通过</c:if>
                                        <c:if test="${v.drawStatus == 2}">已领取</c:if>
                                    </td>
                                </c:when>
                                <c:when test="${module == 3}"><%--//关注状态; 0-未领取 1-已领取--%>
                                    <td>
                                        <c:if test="${v.drawStatus == 1}">已领取</c:if>
                                    </td>
                                </c:when>
                                <c:when test="${module == 1}"><%--//试玩任务; 0:进行中,1:已完成,2:已放弃--%>
                                    <td>
                                        <c:if test="${v.drawStatus == 1}">已领取</c:if>
                                    </td>
                                </c:when>
                                <c:when test="${module == 11}"><%--//新手任务--%>
                                    <td>已领取</td>
                                </c:when>
                                <c:when test="${module == 2}"><%--//分享任务--%>
                                    <td>已领取</td>
                                </c:when>
                                <c:otherwise>
                                    <td>已领取</td>
                                </c:otherwise>
                            </c:choose>
                        </tr>
                    </c:forEach>
                </tbody>
                <tfoot>
                <tr>
                    <td colspan="7" id="pagination">
                        ${page}
                    </td>
                </tr>
                </tfoot>
            </table>
        </div>
        <%--主内容显示区 end --%>

    </tiles:putAttribute>
</tiles:insertTemplate>
