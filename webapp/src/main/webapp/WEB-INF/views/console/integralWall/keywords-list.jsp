<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page pageEncoding="UTF-8" %>
<tiles:insertTemplate template="/WEB-INF/views/base/layout/layout-default.jsp">
    <tiles:putAttribute name="title" value="关键词列表"/>
    <tiles:putAttribute name="enableConsole" value="true"/>
    <tiles:putAttribute name="enableSidebar" value="true"/>
    <tiles:putAttribute name="styleArea">
        <link rel="stylesheet" type="text/css" href="/styles/mission_sort.css">
        <link rel="stylesheet" type="text/css" href="/client/css/template/base.css">
    </tiles:putAttribute>
    <tiles:putAttribute name="scriptArea">
        <script src="/methods/integralWall/keywords-list.js" type="text/javascript"></script>
        <script src="/plugins/My97DatePicker/function.js"></script>
        <script src="/plugins/My97DatePicker/WdatePicker.js"></script>
    </tiles:putAttribute>
    <tiles:putAttribute name="mainArea">
        <!-- 顶部栏 begin -->
        <div class="panel panel-default main-hd">
            <div class="panel-heading">
                <span class="pageTitle">关键词列表</span>
                <span class="searchTitle">app名称:</span>
                <span class="searchTitle">${app.appName}</span>
                <span class="searchTitle">市场:</span>
                <span class="searchTitle">${app.marketName}</span>
                <span class="searchTitle">包名:</span>
                <span class="searchTitle">${app.appPackage}</span>
                <input type="hidden" id="appId" value="${app.appId}">
            </div>
        </div>
        <div class="panel panel-default main-hd">
            <div class="panel-heading">
                <button id="add"
                        class="btn btn-default btn-sm navbar-btn"
                        data-toggle="modal"
                        data-target="#modal-add"
                        data-backdrop="static"
                        data-keyboard="false"
                        role="button">
                    添加
                </button>
                <button id="edit" style="display:none;"
                        class="btn btn-default btn-sm navbar-btn"
                        data-toggle="modal"
                        data-target="#modal-edit-banner"
                        data-backdrop="static"
                        data-keyboard="false"
                        role="button">
                    隐藏修改
                </button>
                <button class="btn btn-default btn-sm navbar-btn" onclick='editWord();'>修改</button>
                <button class="btn btn-default btn-sm navbar-btn" onclick='deleteWord();'>删除</button>
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
                        <th>序号</th>
                        <th>关键词</th>
                        <th>总次数</th>
                        <th>剩余次数</th>
                        <th>金额</th>
                        <th>标签</th>
                        <th>投放时间</th>
                    </tr>
                    </thead>
                    <tbody id="redBody">
                        <c:forEach items="${words}" var="word" varStatus="status">
                        <tr>
                            <th><input type='checkbox' value=${word.keywordId}></th>
                            <th>${status.index+1}</th>
                            <th>${word.keyword}</th>
                            <th>${word.totalNum}</th>
                            <th>${word.leftNum}</th>
                            <th>${word.money}</th>
                            <th>${word.taskLabel}</th>
                            <th>${word.releaseTime}</th>
                        </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>

        <!-- 添加 begin -->
        <div id="modal-add" aria-labelledby="modal-add-banner-title" class="modal fade" tabindex="-1"
             role="dialog" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                        <h4 class="modal-title">添加关键词</h4>
                    </div>
                    <div class="modal-body">
                        <div class="row">
                            <div class="form-group col-xs-6">
                                <label>
                                    关键词
                                </label>
                                <input type="text" class="form-control" id="add_word">
                            </div>
                            <div class="form-group col-xs-6">
                                <label>
                                    投放时间
                                </label>
                                <input type="text" class="form-control" id="add_release_time" class="time" onclick="WdatePicker({lang:'zh-cn',dateFmt:'yyyy-MM-dd HH:mm:ss'});">
                            </div>
                        </div>
                        <div class="row">
                            <div class="form-group col-xs-6">
                                <label>
                                    总次数
                                </label>
                                <input type="text" class="form-control" id="add_total_num">
                            </div>
                            <div class="form-group col-xs-6">
                                <label>
                                    剩余次数
                                </label>
                                <input type="text" class="form-control" id="add_left_num">
                            </div>
                        </div>
                        <div class="row">
                            <div class="form-group col-xs-6">
                                <label for="add_label">
                                    标签
                                </label>
                                <select class="form-control" id="add_label" name="add_label">
                                    <c:forEach items="${labels}" var="label">
                                        <option value="${label.taskLabel}">${label.taskLabel}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="form-group col-xs-6">
                                <label for="add_money">
                                    金额(元)
                                </label>
                                <input type="text" class="form-control" id="add_money">
                            </div>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default auto-focus-btn" data-dismiss="modal" >
                            取消</button>
                        <button type="button" class="btn btn-success add_submit_btn"  onclick="addKeyword();">
                            确定</button>
                    </div>
                </div>
            </div>
        </div>
        <!-- 添加 end -->

        <!-- 修改 begin -->
        <div id="modal-edit-banner" aria-labelledby="modal-add-banner-title" class="modal fade" tabindex="-1"
             role="dialog" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                        <h4 class="modal-title">修改关键词</h4>
                    </div>
                    <div class="modal-body">
                        <div class="row">
                            <div class="form-group col-xs-6">
                                <label>
                                    关键词
                                </label>
                                <input type="text" class="form-control" id="edit_word">
                            </div>
                            <div class="form-group col-xs-6">
                                <label>
                                    投放时间
                                </label>
                                <input type="text" class="form-control" id="edit_release_time" class="time" onclick="WdatePicker({lang:'zh-cn',dateFmt:'yyyy-MM-dd HH:mm:ss'});">
                            </div>
                        </div>
                        <div class="row">
                            <div class="form-group col-xs-6">
                                <label>
                                    总次数
                                </label>
                                <input type="text" class="form-control" id="edit_total_num">
                            </div>
                            <div class="form-group col-xs-6">
                                <label>
                                    剩余次数
                                </label>
                                <input type="text" class="form-control" id="edit_left_num">
                            </div>
                        </div>
                        <div class="row">
                            <div class="form-group col-xs-6">
                                <label>
                                    标签
                                </label>
                                <select class="form-control" id="edit_label" name="edit_label">
                                    <c:forEach items="${labels}" var="label">
                                        <option value="${label.taskLabel}">${label.taskLabel}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="form-group col-xs-6">
                                <label>
                                    金额
                                </label>
                                <input type="text" class="form-control" id="edit_money">
                                <input type="hidden" class="form-control" id="keywordId">
                            </div>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default auto-focus-btn" data-dismiss="modal" >
                            取消</button>
                        <button type="button" class="btn btn-success add_submit_btn" id="add_banner" onclick="doEditWord();">
                            确定</button>
                    </div>
                </div>
            </div>
        </div>
        <!-- 修改 end -->

        <%--主内容显示区 end --%>
    </tiles:putAttribute>
</tiles:insertTemplate>
