<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page pageEncoding="UTF-8" %>
<tiles:insertTemplate template="/WEB-INF/views/base/layout/layout-default.jsp">
    <tiles:putAttribute name="title" value="推广列表"/>
    <tiles:putAttribute name="enableConsole" value="true"/>
    <tiles:putAttribute name="enableSidebar" value="true"/>
    <tiles:putAttribute name="styleArea">
        <style>
            .separator_line {
                margin-top: 10px;
                height: 1px;
                background-color: #CCCCCC;
            }
            .table tbody td {
                text-align: center;
                white-space:nowrap;
            }
            .table tbody td img {
                width:60px;
                height:60px;
            }
            input[type='checkbox']{
                width:  40px;
                height: 40px;
            }

            tr th {
                text-align: center;
            }
        </style>
    </tiles:putAttribute>
    <tiles:putAttribute name="scriptArea">

        <script src="/client/js/base/control-convert.js" type="text/javascript"></script>
        <script src="/client/js/base/validation.js" type="text/javascript"></script>
        <script src="/plugins/upload/image-upload/js/my.jquery-post-upload-styleX.js" type="text/javascript"></script>
        <script src="/client/js/util.js" type="text/javascript"></script>
        <script src="/methods/extend.js" type="text/javascript"></script>

    </tiles:putAttribute>
    <tiles:putAttribute name="mainArea">

        <!-- 顶部栏 begin -->
        <div class="panel panel-default main-hd">
            <div class="panel-heading">
                 <span>推广列表&nbsp;&nbsp;&nbsp;&nbsp;</span>
                <label>类型:</label>
                <select id="type" style="width: 175px;height: 30px;">
                    <option value="">请选择</option>
                    <option value="1">公众号</option>
                    <option value="0">普通商户</option>
                </select>
                <label>客户名称:</label>
                <input type="text" id="customerName" placeholder="请输入客户名" >
                <label>微信号:</label>
                <input type="text" id="customerWx" placeholder="请输入微信号" >
                <label>状态</label>
                <select id="status" style="width: 172px;height: 30px;">
                    <option value="">请选择</option>
                    <option value="0">已结束</option>
                    <option value="1">进行中</option>
                </select>
                <button class="btn btn-success" id="search" onclick="search(1)">查询</button>
            </div>
        </div>

        <div class="panel panel-default main-hd">
            <div class="panel-heading">
                <div class="input-group input-group-sm w100 fl">
                    <button class="btn btn-success" id ="detail" style="width: 90px">详情</button>
                </div>
                <div class="input-group input-group-sm w100 fl">
                    <button class="btn btn-success" id ="addRed"  style="width: 90px">添加红包</button>
                </div>
                <div class="input-group input-group-sm w100 fl">
                    <button class="btn btn-success" id ="unlock" style="width: 90px">解锁红包</button>
                </div>
                <div class="input-group input-group-sm w100 fl">
                    <button class="btn btn-success" id ="delete" style="width: 90px">删除推广</button>
                </div>
                <div class="input-group input-group-sm w100 fl">
                    <button class="btn btn-success" id ="update" style="width: 90px">修改推广</button>
                </div>
                <div class="input-group input-group-sm w100 fl">
                    <button class="btn btn-success" id ="step" style="width: 90px">步骤</button>
                </div>
                <button class="btn btn-success" onclick="showExtendInApp();" style="width: 100px">查看效果</button>
            </div>
        </div>
        <!-- 顶部栏 end -->

        <%--主内容显示区 begin --%>
        <div class="main-bd">

                <%--分割线--%>
            <div class="separator_line">
            </div>

                <%--数据表格--%>
            <div style="width:100%; overflow:scroll;">
                <table class="table table-striped table-hover table-bordered table-navbar-no-bottom">
                    <thead>
                    <tr>
                        <th width='5%'>选择</th>
                        <th width='5%'>推广ID</th>
                        <%--<td width='10%'>客户ID</td>--%>
                        <th width='5%'>客户名</th>
                        <th width='5%'>是否跳转</th>
                        <%--<td width='10%'>跳转链接</td>--%>
                        <th width='5%'>等待时间</th>
                        <%--<td width='5%'>推广说明</td>--%>
                        <th width='5%'>banner图</th>
                        <%--<td width='5%'>banner链接</td>--%>
                        <th width='5%'>广告图</th>
                        <%--<td width='5%'>广告链接</td>--%>
                        <th width='5%'>红包总个数</th>
                        <th width='5%'>红包总剩余个数</th>
                        <%--<td width='5%'>当天红包总个数</td>--%>
                        <%--<td width='5%'>当天剩余个数</td>--%>
                        <th width='5%'>状态</th>
                        <th width='5%'>开始时间</th>
                        <th width='5%'>结束时间</th>
                        <th width='5%'>是否热门</th>
                        <th width='5%'>中奖概率(%)</th>
                    </tr>
                    </thead>
                    <tbody id="tbody">

                    </tbody>
                    <tfoot id="tfoot">

                    </tfoot>
                </table>
            </div>
        </div>
        <%--主内容显示区 end --%>
    </tiles:putAttribute>
</tiles:insertTemplate>
