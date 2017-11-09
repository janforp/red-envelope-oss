<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page pageEncoding="UTF-8" %>
<tiles:insertTemplate template="/WEB-INF/views/base/layout/layout-default.jsp">
    <tiles:putAttribute name="title" value="修改兑换码红包"/>
    <tiles:putAttribute name="enableConsole" value="true"/>
    <tiles:putAttribute name="enableSidebar" value="true"/>
    <tiles:putAttribute name="styleArea">
        <link rel="stylesheet" type="text/css" href="/styles/update_mission.css">
    </tiles:putAttribute>
    <tiles:putAttribute name="scriptArea">

        <script src="/client/js/base/control-convert.js" type="text/javascript"></script>
        <script src="/client/js/base/validation.js" type="text/javascript"></script>
        <script src="/plugins/upload/image-upload/js/my.jquery-post-upload-styleX.js" type="text/javascript"></script>
        <script src="/plugins/My97DatePicker/function.js"></script>
        <script src="/plugins/My97DatePicker/WdatePicker.js"></script>
        <script src="/methods/update_or_add_code_red.js" type="text/javascript"></script>
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
        <div class="main-bd">
            <div class="separator_line">
            </div>
            <br>
            <div class="collapse navbar-collapse">
                <span>
                    <label style="margin-left: 60px;margin-right:140px;color: black;font-size:large;"> ID:</label>
                    <span id="codeId">${red.codeId}</span>
                </span>
            </div>
            <br>
            <div class="collapse navbar-collapse">
                <span>
                    <label style="margin-right: 140px;color: black;font-size:large;"> 红包口令:</label>
                    <input type="text" id="redCode"  value="${red.redCode}" style="width: 500px;"/>
                    <button id="kouLin">随机生成口令</button>
                </span>
            </div>
            <br>
            <div class="collapse navbar-collapse">
                <span>
                    <label style="margin-right: 140px;color: black;font-size:large;"> 客户名称:</label>
                    <input type="text" id="customerName"  value="${red.customerName}" style="width: 500px;"/>
                </span>
            </div>
            <br>
            <div class="collapse navbar-collapse">
                <span>
                    <div  class="form-group col-xs-6">
                        <span style="display: inline;">
                            <button class="btn btn-success btn-sm" mode="button-upload-pic" uploadtype="image" style="margin-left: -10px;"
                                    action="/c/p/picture/upload" filesizelimit="128000"
                                    filetypelimit="jpg,png,gif,jpeg" filenums="photo">客户头像
                            </button>
                            <img src="${red.customerImg}" class="user_photo" id="oldCustomerImg" style="width: 80px;height: 80px;margin-left: 120px;">
                            <img class="loadingPic"  obj="user_photo"  alt="" src="/client/img/loading.gif" style="width: 10px;height: 10px" />
                            <input type="hidden" id="customerImg" name="img" obj="user_photo" />
                        </span>
                    </div>
                </span>
            </div>
            <br>
            <div class="collapse navbar-collapse">
                <span>
                    <label style="margin-right: 140px;color: black;font-size:large;"> 客户描述:</label>
                    <textarea id="customerDesc">${red.customerDesc}</textarea>
                </span>
            </div>
            <br>
            <div class="collapse navbar-collapse">
                <span>
                    <label style="margin-right: 140px;color: black;font-size:large;"> 奖励说明:</label>
                    <input type="text" id="awardDesc"  value="${red.awardDesc}" style="width: 500px;"/>
                </span>
            </div>
            <br>
            <div class="collapse navbar-collapse">
                <span>
                    <div  class="form-group col-xs-6">
                        <span style="display: inline;">
                            <button class="btn btn-success btn-sm" mode="button-upload-pic" uploadtype="image" style="margin-left: -10px;"
                                    action="/c/p/picture/upload" filesizelimit="128000"
                                    filetypelimit="jpg,png,gif,jpeg" filenums="banner">banner图
                            </button>
                            <img src="${red.customerBanner}" class="banner_photo" id="oldCustomerBanner" style="width: 80px;height: 80px;margin-left: 120px;">
                            <img class="loadingPic"  obj="banner_photo"  alt="" src="/client/img/loading.gif" style="width: 10px;height: 10px" />
                            <input type="hidden" id="customerBanner" name="img" obj="banner_photo" />
                        </span>
                    </div>
                </span>
            </div>
            <br>
            <div class="collapse navbar-collapse">
                <span>
                    <label style="margin-right: 140px;color: black;font-size:large;"> 红包链接:</label>
                    <input type="text" id="customerBannerUrl"  value="${red.customerBannerUrl}" style="width: 500px;"/>
                </span>
            </div>
            <br>
            <div class="collapse navbar-collapse">
                <span>
                    <label style="margin-right: 140px;color: black;font-size:large;"> 最大红包:</label>
                    <input type="text" id="redMax"  value="${red.redMax}" style="width: 500px;"/>
                </span>
            </div>
            <br>
            <div class="collapse navbar-collapse">
                <span>
                    <label style="margin-right: 140px;color: black;font-size:large;"> 领取规则:</label>
                    <textarea id="redDesc">${red.redDesc}</textarea>
                </span>
            </div>
            <br>
            <div class="collapse navbar-collapse">
                <span>
                    <label style="margin-right: 140px;color: black;font-size:large;"> 红包总数:</label>
                    <input type="text" id="redNumTotal"  value="${red.redNumTotal}" style="width: 500px;"/>
                    <span style="color: red;margin-left: 10px;">整数,若填写为100</span>
                </span>
            </div>
            <br>
            <div class="collapse navbar-collapse">
                <span>
                    <label style="margin-right: 140px;color: black;font-size:large;"> 总剩余数:</label>
                     <input type="text" id="redNumLeft"  value="${red.redNumLeft}" style="width: 500px;"/>
                </span>
            </div>
            <br>
            <div class="collapse navbar-collapse">
                <span>
                    <label style="margin-right: 140px;color: black;font-size:large;"> 大额红包:</label>
                     <input type="text" id="bigRed" style="width: 500px;"/>
                    <span style="color: red;margin-left: 10px;">如:888|666|555,中间用<span style="color: black;font-size: larger">英文</span>状态下的 ' | ' 分开</span>
                </span>
            </div>
            <br>
            <div class="collapse navbar-collapse">
                <span style="display: none;">
                    <label style="margin-right: 140px;color: black;font-size:large;"> 当天总数:</label>
                     <input type="text" id="redNumDayTotal"  value="${red.redNumDayTotal}" style="width: 500px;"/>
                </span>
            </div>
            <br>
            <div class="collapse navbar-collapse">
                <span style="display: none">
                    <label style="margin-right: 140px;color: black;font-size:large;"> 当天剩余:</label>
                     <input type="text" id="redNumDayLeft"  value="${red.redNumDayLeft}" style="width: 500px;"/>
                </span>
            </div>
            <br>
            <div class="collapse navbar-collapse">
                <span>
                    <label style="margin-right: 140px;color: black;font-size:large;"> 红包状态:</label>
                    <select style="width: 170px;height: 25px;" id="codeStatus">
                        <option value="0" <c:if test="${red.codeStatus == '0'}">selected</c:if>  >IOS开启</option>
                        <option value="1" <c:if test="${red.codeStatus == '1'}">selected</c:if>  >andriod开启</option>
                        <option value="2" <c:if test="${red.codeStatus == '2'}">selected</c:if>  >全部开启</option>
                        <option value="3" <c:if test="${red.codeStatus == '3'}">selected</c:if>  >全部关闭</option>
                    </select>
                </span>
            </div>

            <br>
            <div class="collapse navbar-collapse">
                <span>
                    <label style="margin-right: 140px;color: black;font-size:large;"> 注册时间:</label>
                    <input type="text" id = "createTime" value="${showCreateTime}"  class="Wdate" onclick="WdatePicker({lang:'zh-cn',dateFmt:'yyyy-MM-dd HH:mm:ss '});"/>
                </span>
            </div>
            <br>
            <div class="collapse navbar-collapse">
                <span>
                    <label style="margin-right: 140px;color: black;font-size:large;"> 修改时间:</label>
                    <input type="text" id = "updateTime" value="${showUpdateTime}"  class="Wdate" onclick="WdatePicker({lang:'zh-cn',dateFmt:'yyyy-MM-dd HH:mm:ss '});"/>
                </span>
            </div>

        </div>
        <%--主内容显示区 end --%>
    </tiles:putAttribute>
</tiles:insertTemplate>
