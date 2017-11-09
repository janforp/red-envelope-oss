<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page pageEncoding="UTF-8" %>
<tiles:insertTemplate template="/WEB-INF/views/base/layout/layout-default.jsp">
    <tiles:putAttribute name="title" value="推荐任务"/>
    <tiles:putAttribute name="enableConsole" value="true"/>
    <tiles:putAttribute name="enableSidebar" value="true"/>
    <tiles:putAttribute name="styleArea">
        <link rel="stylesheet" type="text/css" href="/styles/update_mission.css">

        <link rel="stylesheet" href="/plugins/bootstrap-select-1.11.2/css/bootstrap-select.min.css">
        <style>
            hr{
                border:2px solid black;
            }
            textarea{
                width: 300px;height: 100px;
            }

            .closeImg{

                cursor:pointer;
            }
        </style>
    </tiles:putAttribute>
    <tiles:putAttribute name="scriptArea">
        <script src="/client/js/base/control-convert.js" type="text/javascript"></script>
        <script src="/plugins/upload/image-upload/js/my.jquery-post-upload-styleX.js" type="text/javascript"></script>
        <script src="/plugins/My97DatePicker/function.js"></script>
        <script src="/plugins/My97DatePicker/WdatePicker.js"></script>
        <script src="/methods/redPackageManager/add_or_update_recommend_mission.js" type="text/javascript"></script>
        <script src="/plugins/bootstrap-select-1.11.2/js/bootstrap-select.min.js" type="text/javascript"></script>
        <script src="/plugins/bootstrap-select-1.11.2/js/i18n/defaults-zh_CN.min.js" type="text/javascript"></script>
        <script src="/methods/util/util.js" type="text/javascript"></script>
        <script>
            var add = '${add}';
            var update = '${update}';
            var detail = '${detail}';
            var labels = '${mission.missionLabel}';

            $(document).ready(function () {

            });

            /**
             * 点击'使用原标签'按钮
             */
            function setLabel() {

                if (labels != "" && labels != null && labels != undefined){

                    var labelArr = labels.split(',');

                    $("#missionLabel").selectpicker('val', labelArr);
                }
            };

            /**
             * 点击 '清除所有标签按钮' 按钮
             */
            function clearLabel() {
                /**
                 * 若此值为0了,则说明用户不需要使用标签
                 */
                $("#userLabel").val("0");
                $("#missionLabel").selectpicker('val', "");
            };

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
            <br>
            <div class="collapse navbar-collapse">
                <span>
                    <label style="margin-right: 125px;color: black;font-size:large;"> 任务类型:</label>
                    <select style="width: 170px;height: 25px;" id="missionType">
                        <option value="0" <c:if test="${mission.missionType == '0'}">selected</c:if>  >推荐任务</option>
                        <option value="1" <c:if test="${mission.missionType == '1'}">selected</c:if>  >兑换码红包</option>
                    </select>
                    <span>若是兑换码红包:则不需要审核</span>
                </span>
            </div>
            <br>
            <div class="collapse navbar-collapse">
                <span>
                    <label style="margin-right: 140px;color: black;font-size:large;"> 兑换码:</label>
                    <input type="text" id="exchangeCode"  value="${mission.exchangeCode}" style="width: 500px;" placeholder="若是兑换码红包,则必填"/>
                    <button class="btn btn-warning" onclick="utils.createCode(10,'exchangeCode')">生成兑换码</button>
                </span>
            </div>
            <br>
            <div class="collapse navbar-collapse">
                <span>
                    <label style="margin-right: 140px;color: black;font-size:large;"> 任务名:</label>
                    <input type="text" id="missionTitle"  value="${mission.missionTitle}" style="width: 500px;" placeholder="必填"/>
                </span>
            </div>
            <br>
            <div class="collapse navbar-collapse">
                <span>
                    <div  class="form-group col-xs-6">
                        <span style="display: inline;">
                            <button class="btn btn-success btn-sm" mode="button-upload-pic" uploadtype="image" style="margin-left: -10px;"
                                    action="/c/p/picture/upload" filesizelimit="128000"
                                    filetypelimit="jpg,png,gif,jpeg" filenums="photo">任务图标
                            </button>
                            <img src="${mission.missionIcon}" class="user_photo" id="oldMissionIcon" style="width: 80px;height: 80px;margin-left: 100px;">
                            <img class="loadingPic"  obj="user_photo"  alt="" src="/client/img/loading.gif" style="width: 10px;height: 10px" />
                            <input type="hidden" id="missionIcon" name="img" obj="user_photo" />
                        </span>
                    </div>
                </span>
            </div>
            <br>
            <div class="collapse navbar-collapse" style="margin-left: 200px;">
                <span>
                     <div  class="form-group col-xs-6">
                         <label style="margin-right: 125px;color: black;font-size:large;margin-left: -200px;"> 任务标签:</label>
                         <c:if test="${update == '1'}">(要查看修改之前的标签,请点击'使用原标签'按钮,若没有标签,则可点击'不使用标签'按钮)</c:if>
                        <select id="missionLabel" class="selectpicker show-tick form-control" multiple data-live-search="false">
                            <c:forEach items="${labels}" var="v">
                                <option value="${v.taskLabel}">${v.taskLabel}</option>
                            </c:forEach>
                        </select>
                     </div>
                     <c:if test="${update == '1'}">
                         <button id="setLabel" onclick="setLabel();" class="btn btn-success">使用原标签</button>
                         <button id="setLabel" onclick="clearLabel();" class="btn btn-danger">不使用标签</button>
                         <input type="hidden" id="userLabel" value="1">
                     </c:if>
                    <c:if test="${detail == '1'}">
                        <button id="setLabel" onclick="setLabel();" class="btn btn-success">查看标签</button>
                        <input type="hidden" id="userLabel" value="1">
                    </c:if>
                </span>
            </div>
            <br>
            <div class="collapse navbar-collapse">
                <span>
                    <label style="margin-right: 125px;color: black;font-size:large;"> 最小金额:</label>
                    <input type="text" id="minMoney"  value="${mission.minMoney}"  placeholder="默认为1.00"/>元
                </span>
            </div>
            <br>
            <div class="collapse navbar-collapse">
                <span>
                    <label style="margin-right: 125px;color: black;font-size:large;"> 最大金额:</label>
                    <input type="text" id="maxMoney"  value="${mission.maxMoney}" placeholder="默认为1.00" />元
                </span>
            </div>
            <br>
            <div class="collapse navbar-collapse">
                <span>
                <label style="margin-right:125px;color: black;font-size:large;">开始时间:</label>
                    <input type="text" id = "startTime" value="${mission.startTime}" style="width: 193px;height: 33px;" class="Wdate" onclick="WdatePicker({lang:'zh-cn',dateFmt:'yyyy-MM-dd'});"/>
                </span>
            </div>
            <br>
            <div class="collapse navbar-collapse">
                <span>
                    <label style="margin-right:125px;color: black;font-size:large;">结束时间:</label>
                    <input type="text" id = "endTime" value="${mission.endTime}"  style="width: 193px;height: 33px;" class="Wdate" onclick="WdatePicker({lang:'zh-cn',dateFmt:'yyyy-MM-dd'});"/>
                </span>
            </div>
            <br>
            <div class="collapse navbar-collapse">
                <span>
                    <label style="margin-right: 125px;color: black;font-size:large;"> 任务总数:</label>
                    <input type="text" id="totalNum"  value="${mission.totalNum}" placeholder="默认为200" <c:if test="${update == '1'}">disabled</c:if> />
                    <c:if test="${update == '1'}">在此不能修改任务总数,列表处有单独按钮</c:if>
                </span>
            </div>
            <br>
            <div class="collapse navbar-collapse">
                <span>
                    <label style="margin-right: 125px;color: black;font-size:large;"> 剩余数量:</label>
                    <input type="text" id="leftNum"  value="${mission.leftNum}" placeholder="默认与总数相同" <c:if test="${update == '1'}">disabled</c:if> />
                     <c:if test="${update == '1'}">在此不能修改剩余数量,列表处有单独按钮</c:if>
                </span>
            </div>
            <br>
            <div class="collapse navbar-collapse">
                <span>
                    <label style="margin-right: 125px;color: black;font-size:large;"> 任务简介:</label>
                    <textarea id="missionDesc">${mission.missionDesc}</textarea>
                </span>
            </div>
            <br>
            <div class="collapse navbar-collapse">
                <span>
                    <label style="margin-right: 125px;color: black;font-size:large;"> 任务排序:</label>
                    <input type="text" id="missionOrder"  value="${mission.missionOrder}" placeholder="默认为0"/>
                </span>
            </div>
            <br>
            <div class="collapse navbar-collapse">
                <span>
                    <label style="margin-right: 125px;color: black;font-size:large;"> 显示平台:</label>
                    <select style="width: 170px;height: 25px;" id="missionStatus">
                        <option value="1" <c:if test="${mission.missionStatus == '1'}">selected</c:if>  >andriod开启</option>
                        <option value="0" <c:if test="${mission.missionStatus == '0'}">selected</c:if>  >ios开启</option>
                        <option value="2" <c:if test="${mission.missionStatus == '2'}">selected</c:if>  >全部开启</option>
                        <option value="3" <c:if test="${mission.missionStatus == '3'}">selected</c:if>  >全部关闭</option>
                    </select>
                </span>
            </div>
            <br>
            <div class="collapse navbar-collapse">
                <span>
                    <label style="margin-right: 125px;color: black;font-size:large;"> 是否限时:</label>
                    <select style="width: 170px;height: 25px;" id="isLimitTime">
                        <option value="0" <c:if test="${mission.isLimitTime == '0'}">selected</c:if>  >限时</option>
                        <option value="1" <c:if test="${mission.isLimitTime == '1'}">selected</c:if>  >不限时</option>
                    </select>
                </span>
            </div>
            <br>
            <div class="collapse navbar-collapse">
                <span>
                    <label style="margin-right: 125px;color: black;font-size:large;"> 限时时间:</label>
                    <input type="text" id="limitMinute"  value="${mission.limitMinute}" placeholder="默认为60"/>分钟
                </span>
            </div>
            <br>
            <div class="collapse navbar-collapse">
                <span>
                    <label style="margin-right: 125px;color: black;font-size:large;"> 是否审核:</label>
                    <select style="width: 170px;height: 25px;" id="isVerify">
                        <option value="0" <c:if test="${mission.isVerify == '0'}">selected</c:if>  >审核</option>
                        <option value="1" <c:if test="${mission.isVerify == '1'}">selected</c:if>  >不审核</option>
                    </select>
                </span>
            </div>
            <br>
            <hr>
            <div class="collapse navbar-collapse">
                <span style="font-size: 2rem;color: black;font-weight: 500">审核要求</span>
            </div>
            <hr>
            <div class="collapse navbar-collapse">
                <label style="margin-right: 125px;color: black;font-size:large;"> 审核要求:</label>
                <c:forEach items="${requires}" var="require">
                    <label>${require.requireName}
                        <input type="checkbox" name="verifyRequire" <c:forEach items="${oldRequires}" var="oldRequire" ><c:if test="${oldRequire.requireId ==require.requireId }">checked</c:if></c:forEach> requireName="${require.requireName}" value="${require.requireId}" style="width: 30px;height: 30px;margin-right:20px;position:relative;top: 5px; ">
                    </label>
                </c:forEach>
            <hr>
            <div class="collapse navbar-collapse">
                <span>
                    <label style="margin-right: 125px;color: black;font-size:large;"> 文字要求:</label>
                    <textarea id="verifyText">${mission.verifyText}</textarea>
                </span>
            </div>
            <br>
            <div class="collapse navbar-collapse">
                <span>
                    <label style="margin-right: 125px;color: black;font-size:large;"> 图片要求:</label>
                    <textarea id="verifyImg">${mission.verifyImg}</textarea>
                </span>
            </div>
            <hr>
            <div class="collapse navbar-collapse">
                <span style="font-size: 2rem;color: black;font-weight: 500;margin-right: 50px;">审核图片</span>
                <button class="btn btn-info" onclick="addVerifyImg();">添加图片</button>(刷新前请保存)
            </div>
            <hr>
            <div class="collapse navbar-collapse" id="addVerifyImgDiv">
                <div  class="form-group col-xs-6">
                    <span style="display: inline;">
                        <button class="btn btn-success btn-sm" mode="button-upload-pic" uploadtype="image"
                                action="/c/p/picture/upload" filesizelimit="128000"
                                filetypelimit="jpg,png,gif,jpeg" filenums="img1" style="margin-left: -10px;">审核图片1
                        </button>
                        <img src="<c:forEach items="${missionImgs}" var="missionImg" begin="0" end="0">${missionImg}</c:forEach>" class="img1 oldImg" id="oldVerifyImg1" style="width: 80px;height: 80px;margin-left: 100px;">
                        <img class="loadingPic"  obj="img1"  alt="" src="/client/img/loading.gif" style="width: 10px;height: 10px" />
                        <c:if test="${detail != '1'}"><span class="closeImg">&times;</span></c:if>
                        <input type="hidden" id="verifyImg1" name="img" obj="img1" />
                    </span>
                </div>

                <div  class="form-group col-xs-6">
                    <span style="display: inline;">
                        <button class="btn btn-success btn-sm" mode="button-upload-pic" uploadtype="image"
                                action="/c/p/picture/upload" filesizelimit="128000"
                                filetypelimit="jpg,png,gif,jpeg" filenums="img2" style="margin-left: -10px;">审核图片2
                        </button>
                        <img src="<c:forEach items="${missionImgs}" var="missionImg" begin="1" end="1">${missionImg}</c:forEach>" class="img2 oldImg" id="oldVerifyImg2" style="width: 80px;height: 80px;margin-left: 100px;">
                        <img class="loadingPic"  obj="img2"  alt="" src="/client/img/loading.gif" style="width: 10px;height: 10px" />
                        <c:if test="${detail != '1'}"><span class="closeImg">&times;</span></c:if>
                        <input type="hidden" id="verifyImg2" name="img" obj="img2" />
                    </span>
                </div>

                <div  class="form-group col-xs-6">
                    <span style="display: inline;">
                        <button class="btn btn-success btn-sm" mode="button-upload-pic" uploadtype="image"
                                action="/c/p/picture/upload" filesizelimit="128000"
                                filetypelimit="jpg,png,gif,jpeg" filenums="img3" style="margin-left: -10px;">审核图片3
                        </button>
                        <img src="<c:forEach items="${missionImgs}" var="missionImg" begin="2" end="2">${missionImg}</c:forEach>" class="img3 oldImg" id="oldVerifyImg3" style="width: 80px;height: 80px;margin-left: 100px;">
                        <img class="loadingPic"  obj="img3"  alt="" src="/client/img/loading.gif" style="width: 10px;height: 10px" />
                        <c:if test="${detail != '1'}"><span class="closeImg">&times;</span></c:if>
                        <input type="hidden" id="verifyImg3" name="img" obj="img3" />
                    </span>
                </div>

                <div  class="form-group col-xs-6">
                    <span style="display: inline;">
                        <button class="btn btn-success btn-sm" mode="button-upload-pic" uploadtype="image"
                                action="/c/p/picture/upload" filesizelimit="128000"
                                filetypelimit="jpg,png,gif,jpeg" filenums="img4" style="margin-left: -10px;">审核图片4
                        </button>
                        <img src="<c:forEach items="${missionImgs}" var="missionImg" begin="3" end="3">${missionImg}</c:forEach>" class="img4 oldImg" id="oldVerifyImg4" style="width: 80px;height: 80px;margin-left: 100px;">
                        <c:if test="${detail != '1'}"><span class="closeImg">&times;</span></c:if>
                        <img class="loadingPic"  obj="img4"  alt="" src="/client/img/loading.gif" style="width: 10px;height: 10px" />
                        <input type="hidden" id="verifyImg4" name="img" obj="img4" />
                    </span>
                </div>

                <div  class="form-group col-xs-6 hide">
                    <span style="display: inline;">
                        <button class="btn btn-success btn-sm" mode="button-upload-pic" uploadtype="image"
                                action="/c/p/picture/upload" filesizelimit="128000"
                                filetypelimit="jpg,png,gif,jpeg" filenums="img5" style="margin-left: -10px;">审核图片5
                        </button>
                        <img src="<c:forEach items="${missionImgs}" var="missionImg" begin="4" end="4">${missionImg}</c:forEach>" class="img5 oldImg" id="oldVerifyImg5" style="width: 80px;height: 80px;margin-left: 100px;">
                        <img class="loadingPic"  obj="img5"  alt="" src="/client/img/loading.gif" style="width: 10px;height: 10px" />
                        <c:if test="${detail != '1'}"><span class="closeImg">&times;</span></c:if>
                        <input type="hidden" id="verifyImg5" name="img" obj="img5" />
                    </span>
                </div>

                <div  class="form-group col-xs-6 hide">
                    <span style="display: inline;">
                        <button class="btn btn-success btn-sm" mode="button-upload-pic" uploadtype="image"
                                action="/c/p/picture/upload" filesizelimit="128000"
                                filetypelimit="jpg,png,gif,jpeg" filenums="img6" style="margin-left: -10px;">审核图片6
                        </button>
                        <img src="<c:forEach items="${missionImgs}" var="missionImg" begin="5" end="5">${missionImg}</c:forEach>" class="img6 oldImg" id="oldVerifyImg6" style="width: 80px;height: 80px;margin-left: 100px;">
                        <img class="loadingPic"  obj="img6"  alt="" src="/client/img/loading.gif" style="width: 10px;height: 10px" />
                        <c:if test="${detail != '1'}"><span class="closeImg">&times;</span></c:if>
                        <input type="hidden" id="verifyImg6" name="img" obj="img6" />
                    </span>
                </div>

                <div  class="form-group col-xs-6 hide">
                    <span style="display: inline;">
                        <button class="btn btn-success btn-sm" mode="button-upload-pic" uploadtype="image"
                                action="/c/p/picture/upload" filesizelimit="128000"
                                filetypelimit="jpg,png,gif,jpeg" filenums="img7" style="margin-left: -10px;">审核图片7
                        </button>
                        <img src="<c:forEach items="${missionImgs}" var="missionImg" begin="6" end="6">${missionImg}</c:forEach>" class="img7 oldImg" id="oldVerifyImg7" style="width: 80px;height: 80px;margin-left: 100px;">
                        <img class="loadingPic"  obj="img7"  alt="" src="/client/img/loading.gif" style="width: 10px;height: 10px" />
                        <c:if test="${detail != '1'}"><span class="closeImg">&times;</span></c:if>
                        <input type="hidden" id="verifyImg7" name="img" obj="img7" />
                    </span>
                </div>

                <div  class="form-group col-xs-6 hide">
                    <span style="display: inline;">
                        <button class="btn btn-success btn-sm" mode="button-upload-pic" uploadtype="image"
                                action="/c/p/picture/upload" filesizelimit="128000"
                                filetypelimit="jpg,png,gif,jpeg" filenums="img8" style="margin-left: -10px;">审核图片8
                        </button>
                        <img src="<c:forEach items="${missionImgs}" var="missionImg" begin="7" end="7">${missionImg}</c:forEach>" class="img8 oldImg" id="oldVerifyImg8" style="width: 80px;height: 80px;margin-left: 100px;">
                        <img class="loadingPic"  obj="img8"  alt="" src="/client/img/loading.gif" style="width: 10px;height: 10px" />
                        <c:if test="${detail != '1'}"><span class="closeImg">&times;</span></c:if>
                        <input type="hidden" id="verifyImg8" name="img" obj="img8" />
                    </span>
                </div>
            </div>
        </div>
        <%--主内容显示区 end --%>
    </tiles:putAttribute>
</tiles:insertTemplate>
