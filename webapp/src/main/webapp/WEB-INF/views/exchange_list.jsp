<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page pageEncoding="UTF-8" %>
<tiles:insertTemplate template="/WEB-INF/views/base/layout/layout-default.jsp">
    <tiles:putAttribute name="title" value="兑换列表"/>
    <tiles:putAttribute name="enableConsole" value="true"/>
    <tiles:putAttribute name="enableSidebar" value="true"/>
    <tiles:putAttribute name="styleArea">
        <link rel="stylesheet" type="text/css" href="/styles/mission_sort.css">
    </tiles:putAttribute>
    <tiles:putAttribute name="scriptArea">
        <script src="/methods/exchange_list.js" type="text/javascript"></script>
    </tiles:putAttribute>
    <tiles:putAttribute name="mainArea">
        <!-- 顶部栏 begin -->
        <div class="panel panel-default main-hd">
            <div class="panel-heading">
               <span>兑换列表&nbsp;&nbsp;&nbsp;&nbsp;</span>
                <label style="margin-left: 20px;font-size: large;">状态:</label>
                <select id="exchangeStatus" style="width: 100px;height: 40px;margin-left: 10px;">
                    <option value="">请选择</option>
                    <option value="0">未发货</option>
                    <option value="1">已发货</option>
                    <option value="2">已作废</option>
                </select>
                <input type="text" id="pageSize" style="margin-left: 25px;margin-right: 25px;height: 37px;width: 150px;" placeholder="每页行数,默认10">
                <button class="btn btn-default" type="button" id="listByPageSize">查询</button>
                <span id="pageSizeAlert" style="color: red;margin-left: 20px;"></span>
            </div>
        </div>
        <div class="panel panel-default main-hd">
            <div class="panel-heading">
                <button id="add" style="display:none;"
                        class="btn btn-default btn-sm navbar-btn"
                        data-toggle="modal"
                        data-target="#modal-add-banner"
                        data-backdrop="static"
                        data-keyboard="false"
                        role="button">
                   确认兑换
                </button>
                <button class="btn btn-success" onclick="showExchangeDetail();">确认兑换</button>
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
                        <th>用户</th>
                        <th>手机</th>
                        <th>商品</th>
                        <th>地址</th>
                        <th>申请时间</th>
                        <th>确认时间</th>
                        <th>状态</th>
                    </tr>
                    </thead>
                    <tbody id="redBody">

                    <tfoot>
                    <tr>
                        <td colspan="8">
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

            <!-- 添加 begin -->
            <div id="modal-add-banner" aria-labelledby="modal-add-banner-title" class="modal fade" tabindex="-1"
                 role="dialog" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                            <h4 class="modal-title">确认兑换</h4>
                        </div>
                        <div class="modal-body">
                            <div class="row">
                                <div class="form-group col-xs-6">
                                    <label for="goods_name">
                                        商品名称
                                    </label>
                                    <input type="text" class="form-control" id="goods_name" disabled >
                                </div>
                                <div class="form-group col-xs-6">
                                    <label for="user_name" >
                                        用户名称
                                    </label>
                                    <input type="text" class="form-control" id="user_name" disabled>
                                </div>
                            </div>
                            <div class="row">
                                <div class="form-group col-xs-6">
                                    <label for="type">
                                        商品类型
                                    </label>
                                    <select class="form-control" id="type" name="add_status" disabled =true>
                                        <option value="0">实物商品</option>
                                        <option value="1">虚拟商品</option>
                                    </select>
                                </div>
                                <div class="form-group col-xs-6">
                                    <label for="status">
                                        状态
                                    </label>
                                    <select class="form-control" id="status" name="add_status" disabled>
                                        <option value="0">未发货</option>
                                        <option value="1">已发货</option>
                                        <option value="2">已作废</option>
                                    </select>
                                </div>
                            </div>
                            <div class="row">
                                <div class="form-group col-xs-6">
                                    <label for="address">
                                        收货地址
                                    </label>
                                    <input type="text" class="form-control" id="address" disabled>
                                </div>
                                <div class="form-group col-xs-6">
                                    <label for="phone">
                                        手机号码
                                    </label>
                                    <input type="text" class="form-control" id="phone" disabled>
                                </div>
                            </div>
                            <div class="row">
                                <div class="form-group col-xs-6">
                                    <label for="exchange_time">
                                        申请时间
                                    </label>
                                    <input type="text" class="form-control" id="exchange_time" disabled>
                                </div>
                                <div class="form-group col-xs-6">
                                    <label for="score">
                                       金币
                                    </label>
                                    <input type="text" class="form-control" id="score" disabled>
                                </div>
                            </div>
                            <div class="row">
                                <div class="form-group col-xs-6">
                                    <label for="card_id">
                                        虚拟商品的卡号
                                    </label>
                                    <input type="text" class="form-control" id="card_id" placeholder="请输入卡号">
                                </div>
                                <div class="form-group col-xs-6">
                                    <label for="card_password">
                                        虚拟商品卡密
                                    </label>
                                    <input type="text" class="form-control" id="card_password" placeholder="请输入卡密">
                                </div>
                            </div>
                            <div class="row">
                                <div class="form-group col-xs-6">
                                    <label for="express_number">
                                        逾期时间
                                    </label>
                                    <input type="text" class="form-control" id="invalid_time" placeholder="请输入失效时间">
                                </div>
                            </div>
                            <div class="row">
                                <div class="form-group col-xs-6">
                                    <label for="express_number">
                                        实物快递单号
                                    </label>
                                    <input type="text" class="form-control" id="express_number" placeholder="请输入快递公司名及单号">
                                </div>
                                <div class="form-group col-xs-6">
                                    <label for="detail_id">
                                        兑换ID
                                    </label>
                                    <input type="text" class="form-control" id="detail_id" disabled>
                                </div>
                            </div>


                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-default auto-focus-btn" data-dismiss="modal" >
                                取消</button>
                            <button type="button" class="btn btn-success add_submit_btn" id="add_banner" onclick="exchange();">
                                确定</button>
                        </div>
                    </div>
                </div>
            </div>
            <!-- 添加 end -->
        </div>
        <%--主内容显示区 end --%>
    </tiles:putAttribute>
</tiles:insertTemplate>
