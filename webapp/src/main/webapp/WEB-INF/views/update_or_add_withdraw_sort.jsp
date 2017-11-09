<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page pageEncoding="UTF-8" %>
<tiles:insertTemplate template="/WEB-INF/views/base/layout/layout-default.jsp">
    <tiles:putAttribute name="title" value="修改支付"/>
    <tiles:putAttribute name="enableConsole" value="true"/>
    <tiles:putAttribute name="enableSidebar" value="true"/>
    <tiles:putAttribute name="styleArea">
        <link rel="stylesheet" type="text/css" href="/styles/update_mission.css">
    </tiles:putAttribute>
    <tiles:putAttribute name="scriptArea">

        <script src="/client/js/base/control-convert.js" type="text/javascript"></script>
        <script src="/client/js/base/validation.js" type="text/javascript"></script>
        <script src="/plugins/upload/image-upload/js/my.jquery-post-upload-styleX.js" type="text/javascript"></script>

        <script src="/methods/update_or_add_withdraw_sort.js" type="text/javascript"></script>


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
                    <label style="margin-left: 50px;margin-right:140px;color: black;font-size:large;"> ID:</label>
                    <span id="withdrawId">${sort.withdrawId}</span>
                </span>
            </div>
            <br>
            <div class="collapse navbar-collapse">
                <span>
                    <label style="margin-right: 140px;color: black;font-size:large;">分类类型:</label>
                    <input type="text" id="withdrawType"  value="${sort.withdrawType}" style="width: 500px;"/>
                </span>
            </div>
            <br>
            <div class="collapse navbar-collapse">
                <span>
                    <label style="margin-right: 140px;color: black;font-size:large;">分类标题:</label>
                    <input type="text" id="withdrawName"  value="${sort.withdrawName}" style="width: 500px;"/>
                </span>
            </div>
            <br>
            <div class="collapse navbar-collapse">
                <span>
                    <label style="margin-right: 140px;color: black;font-size:large;">副&nbsp;&nbsp;标&nbsp;&nbsp;题:</label>
                    <input type="text" id="withdrawExplain"  value="${sort.withdrawExplain}" style="width: 500px;"/>
                </span>
            </div>
            <br>
            <div class="collapse navbar-collapse">
                <span>
                    <label style="margin-right: 140px;color: black;font-size:large;">跳转链接:</label>
                    <input type="text" id="withdrawUrl"  value="${sort.withdrawUrl}" style="width: 500px;"/>
                </span>
            </div>
            <br>
            <div class="collapse navbar-collapse">
                <span>
                    <div  class="form-group col-xs-6">
                        <span style="display: inline;">
                            <button class="btn btn-success btn-sm" mode="button-upload-pic" uploadtype="image" style="margin-left: -10px;"
                                    action="/c/p/picture/upload" filesizelimit="128000"
                                    filetypelimit="jpg,png,gif,jpeg" filenums="banner">图片
                            </button>
                            <img src="${sort.withdrawImg}" class="banner_photo" id="oldImg" style="width: 80px;height: 80px;margin-left: 140px;">
                            <img class="loadingPic"  obj="banner_photo"  alt="" src="/client/img/loading.gif" style="width: 10px;height: 10px" />
                            <input type="hidden" id="withdrawImg" name="img" obj="banner_photo" />
                        </span>
                    </div>
                </span>
            </div>
            <br>
            <div class="collapse navbar-collapse">
                <span>
                    <label style="margin-right: 140px;color: black;font-size:large;">提现金额:</label>
                    <input type="text" id="withdrawMoney"  value="${sort.withdrawMoney}" style="width: 500px;"/>
                    <span style="color: red;">如:10&30&50</span>
                </span>
            </div>
            <br>
            <div class="collapse navbar-collapse">
                <span>
                    <label style="margin-right: 140px;color: black;font-size:large;">到账金额:</label>
                    <input type="text" id="toAccountMoney"  value="${sort.toAccountMoney}" style="width: 500px;"/>
                     <span style="color: red;">如:10&30&50</span>
                </span>
            </div>
            <br>
            <div class="collapse navbar-collapse">
                <span>
                    <label style="margin-right: 140px;color: black;font-size:large;">提现说明:</label>
                    <textarea style="width: 500px;height: 200px;"  id="withdrawDesc">${sort.withdrawDesc}</textarea>
                </span>
            </div>
            <br>
            <div class="collapse navbar-collapse">
                <span>
                    <label style="margin-right: 140px;color: black;font-size:large;">限制次数:</label>
                    <input type="text" id="withdrawTimes"  value="${sort.withdrawTimes}" style="width: 500px;"/>
                </span>
            </div>
            <br>
            <div class="collapse navbar-collapse">
                <span>
                    <label style="margin-right: 140px;color: black;font-size:large;">红包状态:</label>
                    <select style="width: 170px;height: 30px;" id="withdrawStatus">
                        <option value="0" <c:if test="${sort.withdrawStatus == '0'}">selected</c:if>  >IOS开启</option>
                        <option value="1" <c:if test="${sort.withdrawStatus == '1'}">selected</c:if>  >Andriod开启</option>
                        <option value="2" <c:if test="${sort.withdrawStatus == '2'}">selected</c:if>  >全部开启</option>
                        <option value="3" <c:if test="${sort.withdrawStatus == '3'}">selected</c:if>  >全部关闭</option>
                    </select>
                </span>
            </div>
            <br>
            <div class="collapse navbar-collapse">
                <span>
                    <label style="margin-right: 140px;color: black;font-size:large;">支付排序:</label>
                    <input type="text" id="withdrawOrder"  value="${sort.withdrawOrder}" style="width: 500px;"/>
                </span>
            </div>
        </div>
        <%--主内容显示区 end --%>
    </tiles:putAttribute>
</tiles:insertTemplate>
