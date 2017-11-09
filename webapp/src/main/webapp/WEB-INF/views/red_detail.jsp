<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page pageEncoding="UTF-8" %>
<tiles:insertTemplate template="/WEB-INF/views/base/layout/layout-default.jsp">
    <tiles:putAttribute name="title" value="修改分类红包"/>
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
                    <label style="margin-left: 60px;margin-right:140px;color: black;font-size:large;"> ID:</label>
                    <span id="redId">${red.redId}</span>
                </span>
            </div>
            <br>
            <div class="collapse navbar-collapse">
                <span>
                    <label style="margin-right: 140px;color: black;font-size:large;"> 红包名称:</label>
                    <input type="text" id="redName"  value="${red.redName}" style="width: 500px;"/>
                </span>
            </div>
            <br>
            <div class="collapse navbar-collapse">
                <span>
                    <div  class="form-group col-xs-6">
                        <span style="display: inline;">
                            <button class="btn btn-success btn-sm" mode="button-upload-pic" uploadtype="image" style="margin-left: -10px;"
                                    action="/c/p/picture/upload" filesizelimit="128000"
                                    filetypelimit="jpg,png,gif,jpeg" filenums="banner">红包图片
                            </button>
                            <img src="${red.redImg}" class="banner_photo" id="oldImg" style="width: 80px;height: 80px;margin-left: 120px;">
                            <img class="loadingPic"  obj="banner_photo"  alt="" src="/client/img/loading.gif" style="width: 10px;height: 10px" />
                            <input type="hidden" id="redImg" name="img" obj="banner_photo" />
                        </span>
                    </div>
                </span>
            </div>
            <br>
            <div class="collapse navbar-collapse">
                <span>
                    <label style="margin-right: 140px;color: black;font-size:large;"> 商户名称:</label>
                    <input type="text" id="merchantName"  value="${red.merchantName}" style="width: 500px;"/>
                </span>
            </div>
            <br>
            <div class="collapse navbar-collapse">
                <span>
                    <label style="margin-right: 140px;color: black;font-size:large;"> 商家详情:</label>
                    <textarea id="merchantDetail">${red.merchantDetail}</textarea>
                </span>
            </div>
            <br>
            <div class="collapse navbar-collapse">
                <span>
                    <label style="margin-right: 140px;color: black;font-size:large;"> 红包奖励:</label>
                    <input type="text" id="redRewardDesc"  value="${red.redRewardDesc}" style="width: 500px;"/>
                </span>
            </div>
            <br>
            <div class="collapse navbar-collapse">
                <span>
                    <label style="margin-right: 140px;color: black;font-size:large;"> 红包金额:</label>
                   <input type="text" id="rewardMoney"  value="${red.rewardMoney}" style="width: 500px;"/>分
                </span>
            </div>
            <br>
            <div class="collapse navbar-collapse">
                <span>
                    <label style="margin-right: 140px;color: black;font-size:large;"> 额外奖励:</label>
                    <input type="text" id="extraRewardDesc"  value="${red.extraRewardDesc}" style="width: 500px;"/>
                </span>
            </div>
            <br>
            <div class="collapse navbar-collapse">
                <span>
                    <label style="margin-right: 140px;color: black;font-size:large;"> 额外金额:</label>
                   <input type="text" id="extraMoney"  value="${red.extraMoney}" style="width: 500px;"/>分
                </span>
            </div>
            <br>
            <div class="collapse navbar-collapse">
                <span>
                    <label style="margin-right: 140px;color: black;font-size:large;"> 红包分类:</label>
                    <select style="width: 170px;height: 25px;" id="redSort">
                        <c:forEach items="${sorts}" var="sort">
                            <option value="${sort.sortId}"  <c:if test="${red.redSort == sort.sortId}">selected</c:if>   >${sort.sortTitle}</option>
                        </c:forEach>
                    </select>
                </span>
            </div>
            <br>
            <div class="collapse navbar-collapse">
                <span>
                    <label style="margin-right: 140px;color: black;font-size:large;"> 红包说明:</label>
                    <textarea id="redDesc">${red.redDesc}</textarea>
                </span>
            </div>
            <br>
            <div class="collapse navbar-collapse">
                <span>
                    <label style="margin-right: 140px;color: black;font-size:large;"> 详情链接:</label>
                   <input type="text" id="detailUrl"  value="${red.detailUrl}" style="width: 500px;"/>
                </span>
            </div>
            <br>
            <div class="collapse navbar-collapse">
                <span>
                    <label style="margin-right: 140px;color: black;font-size:large;"> 红包排序:</label>
                   <input type="text" id="redOrder"  value="${red.redOrder}" style="width: 500px;"/>
                </span>
            </div>
            <br>
            <div class="collapse navbar-collapse">
                <span>
                    <label style="margin-right: 140px;color: black;font-size:large;"> 红包状态:</label>
                    <select style="width: 170px;height: 25px;" id="redStatus">
                        <option value="0" <c:if test="${red.redStatus == '0'}">selected</c:if>  >IOS开启</option>
                        <option value="1" <c:if test="${red.redStatus == '1'}">selected</c:if>  >andriod开启</option>
                        <option value="2" <c:if test="${red.redStatus == '2'}">selected</c:if>  >全部开启</option>
                        <option value="3" <c:if test="${red.redStatus == '3'}">selected</c:if>  >全部关闭</option>
                    </select>
                </span>
            </div>
            <br>
            <div class="collapse navbar-collapse">
                <span>
                    <label style="margin-right: 140px;color: black;font-size:large;"> 红包显示:</label>
                    <select style="width: 170px;height: 25px;" id="showOrNot">
                        <option value="0" <c:if test="${red.showOrNot == '0'}">selected</c:if>  >不显示</option>
                        <option value="1" <c:if test="${red.showOrNot == '1'}">selected</c:if>  >显示</option>
                    </select>
                </span>
            </div>

            <br>
            <div class="collapse navbar-collapse">
                <span>
                    <label style="margin-right: 140px;color: black;font-size:large;"> 开始时间:</label>
                    <input type="text" id = "startTime" value="${showStartTime}"  class="Wdate" onclick="WdatePicker({lang:'zh-cn',dateFmt:'yyyy-MM-dd HH:mm:ss '});"/>
                </span>
            </div>
            <br>
            <div class="collapse navbar-collapse">
                <span>
                    <label style="margin-right: 140px;color: black;font-size:large;"> 结束时间:</label>
                    <input type="text" id = "endTime" value="${showEndTime}"  class="Wdate" onclick="WdatePicker({lang:'zh-cn',dateFmt:'yyyy-MM-dd HH:mm:ss '});"/>
                </span>
            </div>
            <br>
            <div class="collapse navbar-collapse">
                <span>
                    <label style="margin-right: 140px;color: black;font-size:large;"> 创建时间:</label>
                    <input type="text" id = "createTime" value="${showCreateTime}"  class="Wdate" onclick="WdatePicker({lang:'zh-cn',dateFmt:'yyyy-MM-dd HH:mm:ss '});"/>
                </span>
            </div>

        </div>
        <%--主内容显示区 end --%>
    </tiles:putAttribute>
</tiles:insertTemplate>
