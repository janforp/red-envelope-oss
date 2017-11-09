<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page pageEncoding="UTF-8" %>
<tiles:insertTemplate template="/WEB-INF/views/base/layout/layout-default.jsp">
    <tiles:putAttribute name="title" value="积分墙"/>
    <tiles:putAttribute name="enableConsole" value="true"/>
    <tiles:putAttribute name="enableSidebar" value="true"/>
    <tiles:putAttribute name="styleArea">
        <style>
            .loadingPic {
                display: none;
            }
            .form-group img {
                width: 60px;
                cursor: pointer;
            }
            input[type=checkbox]{
                width: 30px;
            }
        </style>
    </tiles:putAttribute>
    <tiles:putAttribute name="scriptArea">
        <script src="/plugins/My97DatePicker/function.js"></script>
        <script src="/plugins/My97DatePicker/WdatePicker.js"></script>
        <script src="/client/js/base/control-convert.js" type="text/javascript"></script>
        <script src="/plugins/upload/image-upload/js/image-upload.js" type="text/javascript"></script>
        <script src="/client/js/page/mission/edit-self-integral.js"></script>
        <script>

            var add = '${add}';
            var modify = '${update}';

            var appImg = '${wall.appImg}';
            var labels = '${wall.appLabel}'

        </script>

    </tiles:putAttribute>
    <tiles:putAttribute name="mainArea">
        <!-- 顶部栏 begin -->
        <div class="panel panel-default main-hd">
            <div class="panel-heading">
                <button class="btn btn-default" onclick='save();'>
                    <span class="glyphicon glyphicon-ok"></span>
                    <c:if test="${add ==1}">添 加</c:if>
                    <c:if test="${update ==1}">保 存 修 改</c:if>
                </button>
                <input type="hidden" value="${wall.wallId}" id="wallId">
            </div>
        </div>
        <!-- 顶部栏 end -->
        <%--主内容显示区 begin--%>
        <div class="main-bd">
            <form class="form-horizontal" role="form">
                <c:if test="${add == '1'}">
                    <div class="form-group has-error">
                        <label for="appUrl" class="col-sm-1 control-label">APP链接</label>
                        <div class="col-sm-3">
                            <input type="text" class="form-control" id="appUrl" placeholder="请输入链接">
                            <span class="help-block" id="urlTips"></span>
                        </div>
                        <div class="col-sm-1">
                            <button type="button" id="parse" class="form-control btn-warning col-sm-2" value="采取" onclick="getDataFromUrl();">
                                采集
                            </button>
                        </div>
                        <div class="col-sm-1">
                            <input class="form-control btn col-sm-2" value="详情页" type="button" onclick="toDetailPage();"/>
                        </div>
                    </div>

                    <div class="form-group has-success">
                        <label for="market" class="col-sm-1 control-label">市场</label>
                        <div class="col-sm-3">
                            <select class="form-control" id="market" name="edit_label" onchange="showUrl();">
                                <option value="">请选择</option>
                                <c:forEach items="${markets}" var="market">
                                    <option value="${market.marketId}" <c:if test="${market.marketId == word.marketId}">selected</c:if>>
                                            ${market.marketName}
                                    </option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="col-sm-2">
                            <input type="button"  class="form-control btn col-sm-2" id="marketName" value="请选择市场" onclick="gotoMarket();" ／>
                        </div>
                    </div>
                    <hr>
                </c:if>


                <div class="form-group">
                    <label for="appName" class="col-sm-1 control-label">APP名称</label>
                    <div class="col-sm-3">
                        <input type="text" class="form-control" id="appName" value="${wall.appName}">
                    </div>
                </div>
                <div class="form-group">
                    <label for="appPackage" class="col-sm-1 control-label">包名</label>
                    <div class="col-sm-3">
                        <input type="text" class="form-control" id="appPackage" value="${wall.appPackage}">
                        <c:if test="${modify == '1'}">
                            <span class="help-block">若此APP已经有其他关键词任务，修改包名后，只会生产一个新的APP，生产一个新的任务，若要关闭其他关键词任务，则需要手动修改它们的剩余数量为0或者修改释放时间</span>
                        </c:if>
                    </div>
                </div>
                <div class="form-group">
                    <label for="appSize" class="col-sm-1 control-label">大小(兆)</label>
                    <div class="col-sm-3">
                        <input type="text" class="form-control" id="appSize" value="${wall.appSize}">
                    </div>
                </div>
                <div class="form-group">
                    <label for="appDesc" class="col-sm-1 control-label">简短描述</label>
                    <div class="col-sm-3">
                        <input type="text" class="form-control" id="appDesc" value="${wall.appDesc}">
                    </div>
                </div>
                <div class="form-group">
                    <label for="appIntroduce" class="col-sm-1 control-label">APP描述</label>
                    <div class="col-sm-3">
                        <textarea type="text" class="form-control" id="appIntroduce">${wall.appIntroduce}</textarea>
                    </div>
                </div>
                <div class="form-group">
                    <label for="packageUrl" class="col-sm-1 control-label">下载链接</label>
                    <div class="col-sm-3">
                        <input type="text" class="form-control" value="${wall.appUrl}" id="packageUrl" disabled/>
                    </div>
                </div>
                <div class="form-group">
                    <label for="appIcon" class="col-sm-1 control-label">APP图标</label>
                    <div class="col-sm-3">
                        <img class='img-upload' src="${wall.appIcon}" mode='button-upload-pic' id="appIcon" style="width: 100px;height: 100px;border: 1px solid #EFEFF4;"  action='/c/p/picture/upload' filesizelimit='128000' filetypelimit='jpg,png,gif,jpeg' obj='missionIcon'>
                    </div>
                </div>
                <div class="form-group">
                    <label for="appImg1" class="col-sm-1 control-label">展示图1</label>
                    <div class="col-sm-3">
                        <img class='img-upload'  mode='button-upload-pic' id="appImg1" style="width: 100px;height: 100px;border: 1px solid #EFEFF4;"  action='/c/p/picture/upload' filesizelimit='128000' filetypelimit='jpg,png,gif,jpeg' obj='appImg1'>
                    </div>
                </div>
                <div class="form-group">
                    <label for="appImg2" class="col-sm-1 control-label">展示图2</label>
                    <div class="col-sm-3">
                        <img class='img-upload' mode='button-upload-pic' id="appImg2" style="width: 100px;height: 100px;border: 1px solid #EFEFF4;"  action='/c/p/picture/upload' filesizelimit='128000' filetypelimit='jpg,png,gif,jpeg' obj='appImg2'>
                    </div>
                </div>

                <c:if test="${update!=1}">
                    <div class="form-group">
                        <label for="isLimitNum" class="col-sm-1 control-label">是否限制数量</label>
                        <div class="col-sm-3">
                            <select class="form-control" id="isLimitNum" name="add_label" onchange="switchTotalLeftNum();">
                                <option value="1" <c:if test="${wall.isLimitNum == '1'}">selected</c:if> >限量</option>
                                <option value="0" <c:if test="${wall.isLimitNum == '0'}">selected</c:if> >不限量</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group" id="totalNumDiv">
                        <label for="totalNum" class="col-sm-1 control-label">总次数</label>
                        <div class="col-sm-3">
                            <input type="text" class="form-control" id="totalNum" value="${wall.totalNum}">
                        </div>
                    </div>
                    <div class="form-group" id="leftNumDiv">
                        <label for="leftNum" class="col-sm-1 control-label">剩余次数</label>
                        <div class="col-sm-3">
                            <input type="text" class="form-control" id="leftNum" value="${wall.leftNum}">
                        </div>
                    </div>
                </c:if>

                <div class="form-group">
                    <label for="totalMoney" class="col-sm-1 control-label">总奖励</label>
                    <div class="col-sm-3">
                        <input type="text" class="form-control" id="totalMoney" value="${wall.totalMoney}">
                    </div>
                </div>
                <div class="form-group">
                    <label for="stepOneMoney" class="col-sm-1 control-label">首次奖励</label>
                    <div class="col-sm-3">
                        <input type="text" class="form-control" id="stepOneMoney" value="${wall.stepOneMoney}">
                    </div>
                </div>
                <div class="form-group">
                    <label for="stepOneSecond" class="col-sm-1 control-label">首次时间(秒)</label>
                    <div class="col-sm-3">
                        <input type="text" class="form-control" id="stepOneSecond" value="${wall.stepOneSecond}">
                    </div>
                </div>
                <div class="form-group">
                    <label for="stepOneDesc" class="col-sm-1 control-label">首次描述</label>
                    <div class="col-sm-3">
                        <input type="text" class="form-control" id="stepOneDesc" value="${wall.stepOneDesc}">
                    </div>
                </div>
                <div class="form-group">
                    <label for="stepTwoMoney" class="col-sm-1 control-label">深度任务奖励</label>
                    <div class="col-sm-3">
                        <input type="text" class="form-control" id="stepTwoMoney" value="${wall.stepTwoMoney}">
                    </div>
                </div>
                <div class="form-group">
                    <label for="stepTwoSecond" class="col-sm-1 control-label">深度打开时间(秒)</label>
                    <div class="col-sm-3">
                        <input type="text" class="form-control" id="stepTwoSecond" value="${wall.stepTwoSecond}">
                    </div>
                </div>
                <div class="form-group">
                    <label for="stepTwoDay" class="col-sm-1 control-label">深度任务天数</label>
                    <div class="col-sm-3">
                        <input type="text" class="form-control" id="stepTwoDay" value="${wall.stepTwoDay}">
                    </div>
                </div>
                <div class="form-group">
                    <label for="startTime" class="col-sm-1 control-label">开始时间</label>
                    <div class="col-sm-3">
                        <input type="text" id = "startTime" style="height: 30px;" value="${wall.startTime}" class="form-control Wdate" onclick="WdatePicker({lang:'zh-cn',dateFmt:'yyyy-MM-dd HH:mm:ss '});"/>
                    </div>
                </div>
                <div class="form-group">
                    <label for="endTime" class="col-sm-1 control-label">结束时间</label>
                    <div class="col-sm-3">
                        <input type="text" id = "endTime" style="height: 30px;" value="${wall.endTime}" class="form-control Wdate" onclick="WdatePicker({lang:'zh-cn',dateFmt:'yyyy-MM-dd HH:mm:ss '});"/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-1 control-label">标签</label>
                    <div class="col-sm-3">
                        <c:forEach items="${labels}" var="l">
                            <label class="">${l.taskLabel}</label>
                            <input type="checkbox" name="label" value="${l.taskLabel}">
                        </c:forEach>
                    </div>
                </div>
                <div class="form-group">
                    <label for="wallWeight" class="col-sm-1 control-label">权重</label>
                    <div class="col-sm-3">
                        <input type="number" id = "wallWeight" value="${wall.wallWeight}" class="form-control" placeholder="请填写0至100的整数"/>
                    </div>
                </div>
                <div class="form-group">
                    <label for="isEnd" class="col-sm-1 control-label">是否结束</label>
                    <div class="col-sm-3">
                        <select class="form-control" id="isEnd" name="add_label">
                            <option value="1" <c:if test="${wall.isEnd == '1'}">selected</c:if> >进行中</option>
                            <option value="0" <c:if test="${wall.isEnd == '0'}">selected</c:if> >结束</option>
                        </select>
                    </div>
                </div>
            </form>
        </div>
        <%--主内容显示区 end --%>

    </tiles:putAttribute>
</tiles:insertTemplate>
