<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page pageEncoding="UTF-8" %>
<tiles:insertTemplate template="/WEB-INF/views/base/layout/layout-default.jsp">
    <tiles:putAttribute name="title" value="开发模式"/>
    <tiles:putAttribute name="enableConsole" value="true"/>
    <tiles:putAttribute name="enableSidebar" value="true"/>
    <tiles:putAttribute name="styleArea">
        <link rel="stylesheet" type="text/css" href="/styles/develop_mode_list.css">
    </tiles:putAttribute>
    <tiles:putAttribute name="scriptArea">
        <script src="/methods/develop_mode_list.js" type="text/javascript"></script>
    </tiles:putAttribute>
    <tiles:putAttribute name="mainArea">

        <!-- 顶部栏 begin -->
        <div class="panel panel-default main-hd">
            <div class="panel-heading">
                <span>客户列表&nbsp;&nbsp;&nbsp;&nbsp;</span>
                <label>类型:</label>
                <select id="type" style="width: 175px;height: 30px;">
                    <option value="1">公众号</option>
                    <option value="0">普通商户</option>
                </select>
                <label>微信号:</label>
                <input type="text" id="customerWx" placeholder="请输入微信号" >
                <label>客户名称:</label>
                <input type="text" id="customerName"  placeholder="请输入客户名称">
                <label>每页行数:</label>
                <input type="text" id="pageSize" style="height: 37px;" placeholder="输入数字,默认15,按回车">
                <button class="btn btn-default" type="button" id="listByPageSize">查询</button>
            </div>
        </div>


        <div class="panel panel-default main-hd">
            <div class="panel-heading">
                <button class="btn btn-success" onclick="setMenu();">设置菜单</button>
                <button class="btn btn-success" onclick="clearAutoReply();">清空自动回复</button>
                <button class="btn btn-success" onclick="setAutoReply();">设置自动回复</button>
                <button class="btn btn-success" onclick="rebuildAutoReply();">重置所有自动回复缓存</button>
                <button class="btn btn-success" onclick="rebuildWxCache();">重置所有公众号配置缓存</button>
                <span id="pageSizeAlert" style="color: red;margin-left: 20px;"></span>
            </div>
        </div>
        <!-- 顶部栏 end -->

        <%--主内容显示区 begin --%>
        <div class="main-bd">
                <%--查询--%>

            <div>
                <table class="table table-striped table-hover table-bordered table-navbar-no-bottom">
                    <thead>
                    <tr>
                        <th width='10%'>选择</th>
                        <th width='10%'>类型</th>
                        <th width='10%'>微信号</th>
                        <th width='10%'>名称</th>
                        <th width='10%'>头像</th>
                        <th width='10%'>appId</th>
                        <th width='10%'>appSecret</th>
                        <th width='10%'>token</th>
                        <th width='10%'>AESKey</th>
                        <th width='10%'>客户秘钥</th>
                        <th width='10%'>创建时间</th>
                        <th width='10%'>加密</th>
                    </tr>
                    </thead>
                    <tbody id="tbody">

                    </tbody>
                    <tfoot>
                    <tr>
                        <td colspan="13">
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

            <div id="deleteAlert">
                <h1>确定要删除吗?</h1>
                <button class="btn btn-info">取消</button>
                <button class="btn btn-danger">删除</button>
            </div>
        </div>
        <%--主内容显示区 end --%>
    </tiles:putAttribute>
</tiles:insertTemplate>
