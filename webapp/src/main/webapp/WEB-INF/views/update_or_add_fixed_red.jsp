<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page pageEncoding="UTF-8" %>
<tiles:insertTemplate template="/WEB-INF/views/base/layout/layout-default.jsp">
    <tiles:putAttribute name="title" value="修改定时红包"/>
    <tiles:putAttribute name="enableConsole" value="true"/>
    <tiles:putAttribute name="enableSidebar" value="true"/>
    <tiles:putAttribute name="styleArea">
        <link rel="stylesheet" type="text/css" href="/styles/update_mission.css">
    </tiles:putAttribute>
    <tiles:putAttribute name="scriptArea">

        <script src="/client/js/base/control-convert.js" type="text/javascript"></script>
        <script src="/client/js/base/validation.js" type="text/javascript"></script>
        <script src="/plugins/upload/image-upload/js/my.jquery-post-upload-styleX.js" type="text/javascript"></script>

        <script src="/methods/update_or_add_fixed_red.js" type="text/javascript"></script>


    </tiles:putAttribute>
    <tiles:putAttribute name="mainArea">

        <!-- 顶部栏 begin -->
        <div class="panel panel-default main-hd">
            <div class="panel-heading">
                <div id="queryForm" class="fr mt-4">
                    <div class="fr pr5">
                        <div class="input-group input-group-sm w100 fl">
                        </div>
                    </div>
                </div>
                <button id="save" class="btn btn-success"style="width: 90px">保存</button>
            </div>
        </div>
        <!-- 顶部栏 end -->

        <%--主内容显示区 begin --%>
        <div class="main-bd">
            <div class="separator_line">
            </div>
            <br>
            <div class="collapse navbar-collapse">
                <span>
                    <label style="margin-left: 20px;margin-right:140px;color: black;font-size:large;"> ID:</label>
                    <span id="fixedId">${red.fixedId}</span>
                </span>
            </div>
            <br>
            <div class="collapse navbar-collapse">
                <span>
                    <label style="margin-right: 140px;color: black;font-size:large;">红包标题:</label>
                    <input type="text" id="fixedTitle"  value="${red.fixedTitle}" style="width: 500px;"/>
                </span>
            </div>
            <br>
            <div class="collapse navbar-collapse">
                <span>
                    <label style="margin-right: 140px;color: black;font-size:large;">开始时间:</label>
                    <input type="text" id="fixedHour"  value="${red.fixedHour}" style="width: 50px;"/> :
                    <input type="text" id="fixedMinute"  value="${red.fixedMinute}" style="width: 50px;"/> :
                    <input type="text" id="fixedSecond"  value="${red.fixedSecond}" style="width: 50px;"/>
                    <span style="color: red;">如: 12 : 34 : 20</span>
                </span>
            </div>
            <br>
            <div class="collapse navbar-collapse">
                <span>
                    <label style="margin-right: 140px;color: black;font-size:large;">红包数量:</label>
                    <input type="text" id="fixedAmount"  value="${red.fixedAmount}" style="width: 500px;"/>
                </span>
            </div>
            <br>
            <div class="collapse navbar-collapse">
                <span>
                    <label style="margin-right: 140px;color: black;font-size:large;">剩余数量:</label>
                    <input type="text" id="fixedRemainder"  value="${red.fixedRemainder}" style="width: 500px;"/>
                </span>
            </div>
            <br>
            <div class="collapse navbar-collapse">
                <span>
                    <label style="margin-right: 140px;color: black;font-size:large;">详情链接:</label>
                    <input type="text" id="fixedUrl"  value="${red.fixedUrl}" style="width: 500px;"/>
                </span>
            </div>
            <br>
            <div class="collapse navbar-collapse">
                <span>
                    <label style="margin-right: 140px;color: black;font-size:large;">跳转链接:</label>
                    <input type="text" id="adUrl"  value="${red.adUrl}" style="width: 500px;"/>
                </span>
            </div>
            <br>
            <div class="collapse navbar-collapse">
                <span>
                    <label style="margin-right: 140px;color: black;font-size:large;">红包状态:</label>
                    <select style="width: 170px;height: 30px;" id="fixedStatus">
                        <option value="0" <c:if test="${red.fixedStatus == '0'}">selected</c:if>  >IOS开启</option>
                        <option value="1" <c:if test="${red.fixedStatus == '1'}">selected</c:if>  >Andriod开启</option>
                        <option value="2" <c:if test="${red.fixedStatus == '2'}">selected</c:if>  >全部开启</option>
                        <option value="3" <c:if test="${red.fixedStatus == '3'}">selected</c:if>  >全部关闭</option>
                    </select>
                </span>
            </div>
        </div>
        <%--主内容显示区 end --%>
    </tiles:putAttribute>
</tiles:insertTemplate>
