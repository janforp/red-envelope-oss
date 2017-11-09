<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page pageEncoding="UTF-8" %>
<tiles:insertTemplate template="/WEB-INF/views/base/layout/layout-default.jsp">
    <tiles:putAttribute name="title" value="编辑试玩任务"/>
    <tiles:putAttribute name="enableConsole" value="true"/>
    <tiles:putAttribute name="enableSidebar" value="true"/>
    <tiles:putAttribute name="styleArea">
        <link rel="stylesheet" type="text/css" href="/client/css/template/base.css">
        <style>
            .loadingPic {
                display: none;
            }
            img {
                width: 60px;
                cursor: pointer;
            }
        </style>
    </tiles:putAttribute>
    <tiles:putAttribute name="scriptArea">
        <script src="/plugins/My97DatePicker/function.js"></script>
        <script src="/plugins/My97DatePicker/WdatePicker.js"></script>
        <script src="/client/js/base/control-convert.js" type="text/javascript"></script>
        <script src="/plugins/upload/image-upload/js/image-upload.js" type="text/javascript"></script>
        <script>

            var add = '${add}';
            var modify = '${update}';
            var clone = '${clone}';

            if (modify == 1 || clone == 1){
                var marketId = '${word.marketId}'
            }

            /**
             * 选择市场时,显示对应的网址
             */
            function showUrl() {

                var $option = $("#market option:selected");
                var marketName = $option.text();
                $("#marketName").val('去'+marketName);
            };
            //显示市场
            function gotoMarket() {
                var $option = $("#market option:selected");
                var marketName = $option.text();

                if (marketName.indexOf('应用宝')>=0) {
                    window.open('http://android.myapp.com/');
                }else if(marketName.indexOf('小米')>=0) {
                    window.open('http://app.xiaomi.com/');
                }else if(marketName.indexOf('360')>=0) {
                    window.open('http://app.so.com/');
                }else if(marketName.indexOf('OPPO')>=0) {
                    window.open('http://store.oppomobile.com/');
                }else if(marketName.indexOf('魅族')>=0) {
                    window.open('http://app.meizu.com/');
                }else if(marketName.indexOf('豌豆荚')>=0) {
                    window.open('http://www.wandoujia.com/apps');
                }else if(marketName.indexOf('安卓市场')>=0) {
                    window.open('http://apk.hiapk.com/');
                }else if(marketName.indexOf('华为')>=0) {
                    window.open('http://app.hicloud.com/');
                }else if (marketName.indexOf('百度手机助手') >= 0){
                    window.open('http://shouji.baidu.com/');
                }else if(marketName.indexOf('应用汇')>=0){
                    window.open('http://www.appchina.com/');
                }else if(marketName.indexOf('PP')>=0){
                    window.open('http://www.25pp.com/android/');
                }else if(marketName.indexOf('搜狗')>=0){
                    window.open('http://zhushou.sogou.com/apps/');
                }else if(marketName.indexOf('安智')>=0){
                    window.open('http://www.anzhi.com/');
                }
            }
            //url输入框值变化的时候自动清空其他输入框的值
            $("#appUrl").change(function () {
                var url = $("#appUrl").val();
                var $input = $("input[type=text]");
                $input.val("");
                $("#appUrl").val(url);
                $("#appIcon").attr("src","");
            });
            /**
             * 点击'抓内容'按钮
             */
            function getDataFromUrl() {

                tips.loading();

                var appUrl = $("#appUrl").val().trim();
                if(appUrl.length==0 || appUrl == ''){
                    $("#urlTips").text('请输入正确的链接');
                    tips.hide;
                    setTimeout(function () {$("#urlTips").text('');},2000);
                    return;
                }
                var marketId = $("#market").val();
                if(marketId == ""){
                    tips.err("请选择市场",2000);
                    return;
                }
                $.ajax({
                    url:'/c/page/console/auth/demo/pareUrl',
                    type:'post',
                    dataType:'json',
                    data:{url:appUrl,marketId:marketId},
                    success:function (data) {
                        tips.hide;
                        if (data.success){
                            var mission = data.bean.mission;
                            $("#appName").val(mission.appName);
                            $("#packageName").val(mission.appPackage);
                            $("#size").val(mission.size);
                            $("#appIcon").attr('src',mission.appIcon);

                            tips.suc("采集成功",2000);
                            $("#parse").val("采集");
                        }else {
                            tips.err("采集失败",2000);
                        }
                    }
                });
            };

            /**
             * 查看app详情页
             */
            function toDetailPage() {
                var appUrl = $("#appUrl").val().trim();
                if(appUrl.length==0 || appUrl == ''){
                    $("#urlTips").text('请输入正确的链接');
                    setTimeout(function () {$("#urlTips").text('');},3000);
                    return;
                }
                window.open(appUrl);
            }

            /**
             * 保存关键词任务
             */
            function saveKeyword() {
                var keywordId,marketId,appName,packageName,size,appIcon,
                    keyword,money,totalNum,leftNum,releaseTime,endTime,label,appDesc,keywordWeight;

                marketId = $("#market").val();
                if (add == 1){
                    if(marketId == "" || marketId.length == 0){
                        tips.err("请选择正确的市场",2000);
                        return;
                    }
                }
                if(clone == 1 || modify == 1){
                    marketId = window.marketId;
                }

                appName = $("#appName").val().trim();
                if(appName == "" || appName.length == 0){
                    tips.err("请输入APP名字",2000);
                    return;
                }
                packageName = $("#packageName").val().trim();
                if (packageName == "" || packageName.length == 0){
                    tips.err("请输入包名",2000);
                    return;
                }
                size = $("#size").val().trim();
                appIcon = $("#appIcon").attr('src');
                keyword = $("#keyword").val().trim();
                if(keyword == "" || keyword.length == 0){
                    tips.err("请输入关键词",2000);
                    return;
                }
                money = $("#money").val().trim();
                if (isNaN(money)){
                    $("#moneyTips").text('请输入正确的金额');
                    setTimeout(function () {$("#moneyTips").text('');},2000);
                    return;
                }
                if(modify != 1){

                    totalNum = $("#totalNum").val().trim();
                    if (isNaN(totalNum)){
                        tips.err("请输入总数量",2000);
                        return;
                    }
                    if (totalNum != parseInt(totalNum)){
                        tips.err("请输入总数量",2000);
                        return;
                    }
                    leftNum = $("#leftNum").val().trim();
                    if(isNaN(leftNum)){
                        tips.err("请输入剩余数量",2000);
                        return;
                    }
                    if (leftNum != parseInt(leftNum)){
                        tips.err("请输入剩余数量",2000);
                        return;
                    }
                }else{
                    totalNum = 0;
                    leftNum = 0;
                }

                releaseTime = $("#releaseTime").val().trim();
                if (releaseTime == "" || releaseTime.length == 0){
                    tips.err("请输入释放任务时间",2000);
                    return;
                }

                endTime = $("#endTime").val().trim();
                if (endTime == "" || endTime.length == 0){
                    tips.err("请输入结束时间",2000);
                    return;
                }
                label = $("#label").val();
                keywordWeight = $("#keywordWeight").val().trim();
                appDesc = $("#appDesc").val();

                if(add == '1' || clone == 1){//添加新关键词任务
                    keywordId = 0;
                }else{//修改关键词任务
                    marketId = window.marketId;
                    keywordId = $("#keywordId").val();
                }
                $.ajax({
                    url:'/c/page/console/auth/demo/save',
                    type:'post',
                    dataType:'json',
                    data:{
                        keywordId:keywordId,
                        marketId:marketId,
                        appName:appName,
                        appPackage:packageName,
                        size:size,
                        appIcon:appIcon,
                        keyword:keyword,
                        money:money,
                        totalNum:totalNum,
                        leftNum:leftNum,
                        releaseTime:releaseTime,
                        endTime:endTime,
                        label:label,
                        keywordWeight:keywordWeight,
                        appDesc:appDesc
                    },
                    success:function (data) {

                        if (data.success){

                            tips.suc("保存成功",2000);
                            setTimeout(function () {
                                window.location.href='/c/page/console/auth/demo/demoPage';
                            },2000);
                        }else {
                            tips.err("保存失败",2000);
                        }
                    }
                });
            }
            /**
             * 点击上传图片按钮
             */
            function imgClick() {
                $("#appIcon").click();
            }
        </script>

    </tiles:putAttribute>
    <tiles:putAttribute name="mainArea">
        <!-- 顶部栏 begin -->
        <div class="panel panel-default main-hd">
            <div class="panel-heading">
                <button class="btn btn-default" onclick='saveKeyword();'>
                    <span class="glyphicon glyphicon-ok"></span>
                    <c:if test="${add ==1}">添 加</c:if>
                    <c:if test="${update ==1}">修 改</c:if>
                    <c:if test="${clone ==1}">克 隆</c:if>
                </button>
                <input type="hidden" value="${word.keywordId}" id="keywordId">
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
                                <span class="glyphicon glyphicon-search"></span>
                                采集
                            </button>
                        </div>
                        <div class="col-sm-1">
                            <input class="form-control btn col-sm-2" value="查看详情页" type="button" onclick="toDetailPage();"/>
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
                        <input type="text" class="form-control" id="appName" value="${word.appName}">
                    </div>
                </div>
                <div class="form-group">
                    <label for="packageName" class="col-sm-1 control-label">包名</label>
                    <div class="col-sm-3">
                        <input type="text" class="form-control" id="packageName" value="${word.appPackage}">
                        <c:if test="${modify == '1'}">
                            <span class="help-block">若此APP已经有其他关键词任务，修改包名后，只会生产一个新的APP，生产一个新的任务，若要关闭其他关键词任务，则需要手动修改它们的剩余数量为0或者修改释放时间</span>
                        </c:if>
                    </div>
                </div>
                <div class="form-group">
                    <label for="size" class="col-sm-1 control-label">大小(兆)</label>
                    <div class="col-sm-3">
                        <input type="text" class="form-control" id="size" value="${word.size}">
                    </div>
                </div>
                <div class="form-group">
                    <label for="appDesc" class="col-sm-1 control-label">APP描述</label>
                    <div class="col-sm-3">
                        <textarea type="text" class="form-control" id="appDesc">${word.appDesc}</textarea>
                    </div>
                </div>
                <div class="form-group">
                    <label for="appIcon" class="col-sm-1 control-label">图片</label>
                    <div class="col-sm-3">
                        <img class='img-upload' src="${word.appIcon}" mode='button-upload-pic' id="appIcon" style="width: 100px;height: 100px;border: 1px solid #EFEFF4;"  action='/c/p/picture/upload' filesizelimit='128000' filetypelimit='jpg,png,gif,jpeg' obj='missionIcon'>
                    </div>
                    <div class="col-sm-1">
                        <button type="button" onclick="imgClick();"  class="form-control btn-info col-sm-2" >
                            <span class="glyphicon glyphicon-picture"></span>
                            上传
                        </button>
                    </div>
                </div>
                <hr>
                <div class="form-group">
                    <label for="keyword" class="col-sm-1 control-label">关键词</label>
                    <div class="col-sm-3">
                        <input type="text" class="form-control" id="keyword" value="${word.keyword}">
                    </div>
                </div>
                <div class="form-group">
                    <label for="money" class="col-sm-1 control-label">金额(元)</label>
                    <div class="col-sm-3">
                        <input type="text" class="form-control" id="money" value="${word.money}">
                        <span class="help-block" id="moneyTips"></span>
                    </div>
                </div>
                <c:if test="${update != 1}">
                    <div class="form-group">
                        <label for="totalNum" class="col-sm-1 control-label">总次数</label>
                        <div class="col-sm-3">
                            <input type="text" class="form-control" id="totalNum" value="${word.totalNum}">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="leftNum" class="col-sm-1 control-label">剩余次数</label>
                        <div class="col-sm-3">
                            <input type="text" class="form-control" id="leftNum" value="${word.leftNum}">
                        </div>
                    </div>
                </c:if>
                <div class="form-group">
                    <label for="leftNum" class="col-sm-1 control-label">释放时间</label>
                    <div class="col-sm-3">
                        <input type="text" id = "releaseTime" style="height: 30px;" value="${word.releaseTime}" class="form-control Wdate" onclick="WdatePicker({lang:'zh-cn',dateFmt:'yyyy-MM-dd HH:mm:ss '});"/>
                    </div>
                </div>
                <div class="form-group">
                    <label for="leftNum" class="col-sm-1 control-label">结束时间</label>
                    <div class="col-sm-3">
                        <input type="text" id = "endTime" style="height: 30px;" value="${word.endTime}" class="form-control Wdate" onclick="WdatePicker({lang:'zh-cn',dateFmt:'yyyy-MM-dd HH:mm:ss '});"/>
                    </div>
                </div>
                <div class="form-group">
                    <label for="label" class="col-sm-1 control-label">标签</label>
                    <div class="col-sm-3">
                        <select class="form-control" id="label" name="add_label">
                            <c:forEach items="${labels}" var="label">
                                <option value="${label.taskLabel}" <c:if test="${label.taskLabel == word.label}">selected</c:if> >
                                    ${label.taskLabel}
                                </option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
                <div class="form-group">
                    <label for="leftNum" class="col-sm-1 control-label">权重</label>
                    <div class="col-sm-3">
                        <input type="number" id = "keywordWeight" value="${word.keywordWeight}" class="form-control" placeholder="请填写0至100的整数"/>
                    </div>
                </div>
            </form>
        </div>
        <%--主内容显示区 end --%>

    </tiles:putAttribute>
</tiles:insertTemplate>
