<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page pageEncoding="UTF-8" %>
<tiles:insertTemplate template="/WEB-INF/views/base/layout/layout-default.jsp">
    <tiles:putAttribute name="title" value="分享任务"/>
    <tiles:putAttribute name="enableConsole" value="true"/>
    <tiles:putAttribute name="enableSidebar" value="true"/>
    <tiles:putAttribute name="styleArea">
        <link rel="stylesheet" type="text/css" href="/styles/update_mission.css">
        <style>
            hr{

                border:2px solid black;

            }
        </style>
    </tiles:putAttribute>
    <tiles:putAttribute name="scriptArea">

        <script src="/client/js/base/control-convert.js" type="text/javascript"></script>
        <script src="/client/js/base/validation.js" type="text/javascript"></script>
        <script src="/plugins/upload/image-upload/js/my.jquery-post-upload-styleX.js" type="text/javascript"></script>
        <script src="/methods/redPackageManager/add_or_update_share_mission.js" type="text/javascript"></script>
        <script>
            var add = '${add}';
            var update = '${update}';
            var detail = '${detail}';
        </script>

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
                <c:if test="${add == '1'}">
                    <span>添加任务</span>
                </c:if>
                <c:if test="${update == '1'}">
                    <span>修改任务</span>
                </c:if>
                <c:if test="${detail != '1'}"><!--查看详情的时候隐藏次按钮即可 -->
                    <button id="save" class="btn btn-success"style="width: 90px">保存</button>
                </c:if>
                <c:if test="${detail == '1'}">
                    <span>任务详情</span>
                </c:if>

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
                    <label style="margin-right: 140px;color: black;font-size:large;"> 任务ID:</label>
                    <span id="missionId">${mission.missionId}</span>
                </span>
            </div>
            <div class="collapse navbar-collapse">
                <span>
                    <label style="margin-right: 140px;color: black;font-size:large;"> 任务名:</label>
                    <input type="text" id="missionTitle"  value="${mission.missionTitle}" style="width: 500px;" placeholder="必填"/>
                </span>
            </div>
            <br>
            <div class="collapse navbar-collapse">
                <span>
                    <label style="margin-right: 125px;color: black;font-size:large;"> 是否结束:</label>
                    <select style="width: 170px;height: 25px;" id="isEnd">
                        <option value="1" <c:if test="${mission.isEnd == '1'}">selected</c:if>  >没有结束</option>
                        <option value="0" <c:if test="${mission.isEnd == '0'}">selected</c:if>  >已经结束</option>
                    </select>
                </span>
            </div>
            <br>
            <div class="collapse navbar-collapse">
                <span  style="display: none;">
                    <div  class="form-group col-xs-6">
                        <span style="display: inline;">
                            <button class="btn btn-success btn-sm" mode="button-upload-pic" uploadtype="image" style="margin-left: -10px;"
                                    action="/c/p/picture/upload" filesizelimit="128000"
                                    filetypelimit="jpg,png,gif,jpeg" filenums="img1">任务图标
                            </button>
                            <img src="${mission.missionIcon}" class="img1" id="oldMissionIcon" style="width: 80px;height: 80px;margin-left: 100px;">
                            <img class="loadingPic"  obj="img1"  alt="" src="/client/img/loading.gif" style="width: 10px;height: 10px" />
                            <input type="hidden" id="missionIcon" name="img" obj="img1" />
                        </span>
                    </div>
                </span>
            </div>
            <hr>
            <div class="collapse navbar-collapse">
                <span style="display: none;">
                    <label style="margin-right: 125px;color: black;font-size:large;"> 任务描述:</label>
                    <input type="text" id="missionDesc"  value="${mission.missionDesc}" style="width: 500px;"/>
                </span>
            </div>
            <br>
            <div class="collapse navbar-collapse">
                <span>
                    <label style="margin-right: 125px;color: black;font-size:large;"> 中奖概率:</label>
                    <input type="text" id="prizeRate"  value="${mission.prizeRate}" placeholder="默认为50"/>%
                </span>
            </div>
            <br>
            <div class="collapse navbar-collapse">
                <span>
                    <label style="margin-right: 125px;color: black;font-size:large;"> 最小金额:</label>
                    <input type="text" id="minMoney"  value="${mission.minMoney}" <c:if test="${update == '1'}">disabled</c:if> placeholder="默认为100"/>分
                    <c:if test="${update == '1'}">,修改的时候不能添加红包总数</c:if>
                </span>
            </div>
            <br>
            <div class="collapse navbar-collapse">
                <span>
                    <label style="margin-right: 125px;color: black;font-size:large;"> 最大金额:</label>
                    <input type="text" id="maxMoney"  value="${mission.maxMoney}" placeholder="默认为100" <c:if test="${update == '1'}">disabled</c:if> />分
                    <c:if test="${update == '1'}">,修改的时候不能添加红包总数</c:if>
                </span>
            </div>
            <div class="collapse navbar-collapse">
                <span style="display: none;">
                    <label style="margin-right: 125px;color: black;font-size:large;"> 中奖次数:</label>
                    <input type="text" id="prizeTimes"  value="${mission.prizeTimes}" placeholder="默认为1次"/>
                </span>
            </div>
            <br>
            <div class="collapse navbar-collapse">
                <span>
                    <label style="margin-right: 125px;color: black;font-size:large;"> 抽奖次数:</label>
                    <input type="text" id="lotteryTimes"  value="${mission.lotteryTimes}"  placeholder="默认为5次"/>
                </span>
            </div>
            <br>
            <div class="collapse navbar-collapse">
                <span>
                    <label style="margin-right: 125px;color: black;font-size:large;"> 红包总数:</label>
                    <input type="text" id="totalRedNum"  value="${mission.totalRedNum}"  placeholder="必填" <c:if test="${update == '1'}">disabled</c:if> />
                    <c:if test="${update == '1'}">修改的时候不能添加红包总数</c:if>
                </span>
            </div>
            <hr>

            <br>
            <div class="collapse navbar-collapse">
                <span>
                    <label style="margin-right: 125px;color: black;font-size:large;"> 分享链接:</label>
                    <input type="text" id="shareUrl"  value="${mission.shareUrl}" placeholder="必填"/>
                </span>
            </div>
            <br>
            <div class="collapse navbar-collapse">
                <span>
                    <label style="margin-right: 125px;color: black;font-size:large;"> 分享标题:</label>
                    <input type="text" id="shareTitle"  value="${mission.shareTitle}" placeholder="必填"/>
                </span>
            </div>
            <br>
            <div class="collapse navbar-collapse">
                <span>
                    <div  class="form-group col-xs-6">
                        <span style="display: inline;">
                            <button class="btn btn-success btn-sm" mode="button-upload-pic" uploadtype="image" style="margin-left: -10px;"
                                    action="/c/p/picture/upload" filesizelimit="128000"
                                    filetypelimit="jpg,png,gif,jpeg" filenums="img2">分享图标
                            </button>
                            <img src="${mission.shareImg}" class="img2" id="oldShareImg" style="width: 80px;height: 80px;margin-left: 100px;">
                            <img class="loadingPic"  obj="img2"  alt="" src="/client/img/loading.gif" style="width: 10px;height: 10px" />
                            <input type="hidden" id="shareImg" name="img" obj="img2" />
                        </span>
                    </div>
                </span>
            </div>
            <br>
            <div class="collapse navbar-collapse">
                <span>
                    <label style="margin-right: 125px;color: black;font-size:large;"> 分享描述:</label>
                    <input type="text" id="shareDesc"  value="${mission.shareDesc}"/>
                </span>
            </div>
            <br>
            <div class="collapse navbar-collapse">
                <span style="display: none;">
                    <label style="margin-right: 125px;color: black;font-size:large;"> 分享类型:</label>
                    <input type="text" id="shareType"  value="link" disabled placeholder="默认为link"/>
                </span>
            </div>
            <br>
            <div class="collapse navbar-collapse">
                <span style="display: none;">
                    <label style="margin-right: 125px;color: black;font-size:large;"> 数据链接:</label>
                    <input type="text" id="shareDataurl"  value="" disabled placeholder="可不填"/>
                </span>
            </div>

            <br>
            <div class="collapse navbar-collapse">
                <span>
                    <div  class="form-group col-xs-6">
                        <span style="display: inline;">
                            <button class="btn btn-success btn-sm" mode="button-upload-pic" uploadtype="image"
                                    action="/c/p/picture/upload" filesizelimit="128000"
                                    filetypelimit="jpg,png,gif,jpeg" filenums="img3" style="margin-left: -10px;">打开图片
                            </button>
                            <img src="${mission.openImg}" class="img3" id="oldOpenImg" style="width: 80px;height: 80px;margin-left: 100px;">
                            <img class="loadingPic"  obj="img3"  alt="" src="/client/img/loading.gif" style="width: 10px;height: 10px" />
                            <input type="hidden" id="openImg" name="img" obj="img3" />
                        </span>
                    </div>
                </span>
            </div>
            <br>
            <div class="collapse navbar-collapse">
                <span>
                    <div  class="form-group col-xs-6">
                        <span style="display: inline;">
                            <button class="btn btn-success btn-sm" mode="button-upload-pic" uploadtype="image"
                                    action="/c/p/picture/upload" filesizelimit="128000"
                                    filetypelimit="jpg,png,gif,jpeg" filenums="img4" style="margin-left: -10px;">成功图片
                            </button>
                            <img src="${mission.openImg}" class="img4" id="oldSuccessImg"  style="width: 80px;height: 80px;margin-left: 100px;">
                            <img class="loadingPic"  obj="img4"  alt="" src="/client/img/loading.gif" style="width: 10px;height: 10px" />
                            <input type="hidden" id="successImg" name="img" obj="img4" />
                        </span>
                    </div>
                </span>
            </div>
            <br>
            <div class="collapse navbar-collapse">
                <span>
                    <div  class="form-group col-xs-6">
                        <span style="display: inline;">
                            <button class="btn btn-success btn-sm" mode="button-upload-pic" uploadtype="image"
                                    action="/c/p/picture/upload" filesizelimit="128000"
                                    filetypelimit="jpg,png,gif,jpeg" filenums="img5" style="margin-left: -10px;">失败图片
                            </button>
                            <img src="${mission.failImg}" class="img5" id="oldFailImg" style="width: 80px;height: 80px;margin-left: 100px;">
                            <img class="loadingPic"  obj="img5"  alt="" src="/client/img/loading.gif" style="width: 10px;height: 10px" />
                            <input type="hidden" id="failImg" name="img" obj="img5" />
                        </span>
                    </div>
                </span>
            </div>


            <hr>
            <br>
            <div class="collapse navbar-collapse">
                <span>
                    <label style="margin-right: 125px;color: black;font-size:large;"> 地区验证:</label>
                    <select style="width: 170px;height: 25px;" id="verifyIp">
                        <option value="0" <c:if test="${mission.verifyIp == '1'}">selected</c:if>  >不验证</option>
                        <option value="1" <c:if test="${mission.verifyIp == '0'}">selected</c:if>  >验证</option>
                    </select>
                </span>
            </div>
            <br>
            <div class="collapse navbar-collapse">
                <span>
                    <label style="margin-right: 125px;color: black;font-size:large;"> 验证省份:</label>
                    <input type="text" id="missionProvince"  value="${mission.missionProvince}"/>
                </span>
            </div>
            <br>
            <div class="collapse navbar-collapse">
                <span>
                    <label style="margin-right: 125px;color: black;font-size:large;"> 验证市县:</label>
                    <input type="text" id="missionCity"  value="${mission.missionCity}"/>
                </span>
            </div>
            <br>
            <div class="collapse navbar-collapse">
                <span style="display: none;">
                    <label style="margin-right: 125px;color: black;font-size:large;"> 是否加密:</label>
                    <select style="width: 170px;height: 25px;" id="isEncrypted">
                        <option value="0" <c:if test="${mission.isEncrypted == '1'}">selected</c:if>  >不加密</option>
                        <option value="1" <c:if test="${mission.isEncrypted == '0'}">selected</c:if>  >加密</option>
                    </select>
                </span>
            </div>
            <%--<br>--%>
            <div class="collapse navbar-collapse">
                <span style="display: none;">
                    <label style="margin-right: 125px;color: black;font-size:large;"> 加密密钥:</label>
                    <input type="text" id="merchantSecret"  value="${mission.merchantSecret}"/>
                    <button id="kouLin" class="btn btn-info">
                        <c:if test="${add=='1'}">添加密钥</c:if>
                        <c:if test="${update=='1'}">修改密钥</c:if>
                    </button>
                     <c:if test="${update=='1'}">
                         <button class="btn btn-danger" onclick="userOldSecret();">使用原密钥</button>
                         <input type="hidden" id="oldMerchantSecret"  value="${mission.merchantSecret}"/>
                     </c:if>
                </span>
            </div>
            <%--<br>--%>
            <div class="collapse navbar-collapse">
                <span>
                    <label style="margin-right: 125px;color: black;font-size:large;"> 商家名称:</label>
                    <input type="text" id="merchantName"  value="${mission.merchantName}"/>
                </span>
            </div>
        </div>
        <%--主内容显示区 end --%>
    </tiles:putAttribute>
</tiles:insertTemplate>
