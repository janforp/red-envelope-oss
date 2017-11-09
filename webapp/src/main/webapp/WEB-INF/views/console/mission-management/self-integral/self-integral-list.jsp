<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page pageEncoding="UTF-8" %>
<tiles:insertTemplate template="/WEB-INF/views/base/layout/layout-default.jsp">
    <tiles:putAttribute name="title" value="积分墙"/>
    <tiles:putAttribute name="enableConsole" value="true"/>
    <tiles:putAttribute name="enableSidebar" value="true"/>
    <tiles:putAttribute name="styleArea">
        <style>
            tbody input{
                width: 30px;height: 30px;
            }
            .one-row{
                margin: 10px 0;
            }
            .market{
                height: 30px;
                width: auto;
            }
            .app{
                text-align: left;
            }
            .app img{
                width: 30px;
                height: 30px;
            }
            .wait{
                color: red;
            }
            .ing{
                color: blue;
            }
            .end{
                color: chartreuse;
            }
            td{
                text-align: center;
            }
            th{
                text-align: center;
            }
            #pagination{
                text-align: left;
            }
        </style>
    </tiles:putAttribute>
    <tiles:putAttribute name="scriptArea">
        <script src="/methods/util/util.js" type="text/javascript"></script>
        <script src="/client/js/base/control-convert.js" type="text/javascript"></script>
        <script src="/plugins/upload/image-upload/js/image-upload.js" type="text/javascript"></script>
        <script src="/plugins/My97DatePicker/function.js"></script>
        <script src="/plugins/My97DatePicker/WdatePicker.js"></script>
        <script>
            /**
             * 页面加载自动查询一次
             */
            $(document).ready(function(){
                turnPage(1);
            });
            /**
             * 翻页
             */
            function turnPage(pageNum) {
                var appName = $("#appName").val().trim();
                var appPackage = $("#appPackage").val().trim();
                var status = $("#status").val();
                var startTime = $("#startTime").val();

                $.ajax({
                    url: "/c/page/console/auth/selfIntegral/list",
                    type: "POST",
                    dataType: "JSON",
                    data: {
                        pageNum: pageNum,
                        appName: appName,
                        appPackage: appPackage,
                        status: status,
                        startTime: startTime
                    },
                    success: function (data) {

                        var details = data.bean.details;
                        var paginationHtml = data.bean.page;
                        selfIntegral.init(details,'pagination','tableBody',paginationHtml);
                        selfIntegral.reset();
                        selfIntegral.setPage();
                        selfIntegral.createHtml();
                        selfIntegral.after();
                    }
                })
            };



            /**
             * 上传apk
             */
            function uploadApk() {
                var $input = $("input[type=checkbox]:checked");
                if ($input == null ||$input.length == 0||$input.length>1 ) {
                    tips.err("必须且只能选择一条纪录",2000);
                    return;
                }
                $("#wallId").val($input.val());
                $("#alertFrame").removeClass("hide");
                //其他的复选框均不能选择
                $("input[type=checkbox]").prop("disabled",true);
            }

            /**
             * 关闭上传 弹出框
             */
            function closeFrame() {
                $("#alertFrame").addClass("hide");
                //恢复复选框选择功能
                $("input[type=checkbox]").prop("disabled",false);
            }

            pg.uploadCallback = function (json) {
                if (json.success) {
                    closeFrame();
                    if (json.msg) {
                        tips.suc(json.msg);
                    }
                    setTimeout(function () {
                        location.reload();
                    }, 1000);
                }
            };



            var selfIntegral = {

                data:null,
                paginationId:null,
                paginationHtml:null,
                tableId:null,

                init:function (data,paginationId,tableId,paginationHtml) {
                    this.data = data;
                    this.paginationId = paginationId;
                    this.tableId = tableId;
                    this.paginationHtml = paginationHtml;
                },
                reset:function () {
                    $("#"+this.tableId).empty();
                    tips.loading();
                },
                createHtml:function () {

                    var walls = this.data;
                    var newLists = "";
                    for (var i in walls){
                        var wall = walls[i];
                        var wallId = wall.wallId;
                        var appName = wall.appName;
                        var icon = wall.appIcon;
                        var label = wall.appLabel;
                        var totalMoney = wall.totalMoney;
                        var firstDayMoney = wall.stepOneMoney;
                        var stepTwoMoney = wall.stepTwoMoney;
                        var stepTwoDay = wall.stepTwoDay;
                        var totalNum = wall.totalNum;
                        if(totalNum == undefined || totalNum == null || totalNum == ""){
                            totalNum = '不限量'
                        }

                        var completeNum = wall.completeNum;
                        if (completeNum == undefined){
                            completeNum = 0;
                        }
                        var startTime = wall.startTime;
                        var endTime = wall.endTime;
                        var weight = wall.wallWeight;
                        var status = wall.status;
                        if (status == '进行中'){
                            status = "    <td class='ing'>"+status+"</td>";
                        }else if (status == '未开始'){
                            status = "    <td class='wait'>"+status+"</td>";
                        }else if (status == '已结束'){
                            status = "    <td class='end'>"+status+"</td>";
                        }

                        newLists +=
                                "<tr>"+
                                "    <td><input type='checkbox' value='"+wallId+"'></td>"+
                                "    <td>"+appName+"</td>"+
                                "    <td class='app'><img src='"+icon+"'></td>"+
                                "    <td>"+label+"</td>"+
                                "    <td>"+totalMoney+"</td>"+
                                "    <td>"+firstDayMoney+"</td>"+
                                "    <td>"+stepTwoMoney+"</td>"+
                                "    <td>"+stepTwoDay+"</td>"+
                                "    <td>"+totalNum+"</td>"+
                                "    <td>"+completeNum+"</td>"+
                                "    <td>"+startTime+"</td>"+
                                "    <td>"+endTime+"</td>"+
                                "    <td>"+weight+"</td>"+
                                            status+
                                "    <td><button class='btn btn-warning btn-xs' onclick='editWall("+wallId+");'>修改</button></td></tr>";
                    }
                    $("#"+this.tableId).append(newLists);
                },
                setPage:function () {

                    $("#"+this.paginationId).html(this.paginationHtml);
                },
                after:function () {
                    tips.hideLoading();
                }
            }

            function editWall(wallId) {
                window.location.href = "/c/page/console/auth/selfIntegral/edit?wallId="+wallId
            }
        </script>

    </tiles:putAttribute>
    <tiles:putAttribute name="mainArea">
        <!-- 顶部栏 begin -->
        <div class="panel panel-default main-hd">
            <div class="panel-heading">
                <span class="pageTitle">积分墙</span>
                <span class="searchTitle">app名称:</span>
                <input type="text" id = "appName"/>
                <span class="searchTitle">包名:</span>
                <input type="text" id = "appPackage"/>
                <span class="searchTitle">状态:</span>
                <select id="status" style="width: 100px;height: 40px;margin-left: 10px;">
                    <option value="">请选择</option>
                    <option value="0">未开始</option>
                    <option value="1">进行中</option>
                    <option value="2">已结束</option>
                </select>
                <span class="searchTitle">开始时间:</span>
                <input type="text" id = "releaseTime" style="height: 30px;"  class="Wdate" onclick="WdatePicker({lang:'zh-cn',dateFmt:'yyyy-MM-dd HH:mm:ss'});"/>
                <button class="btn btn-default" type="button" onclick="turnPage(1)">
                    <span class="glyphicon glyphicon-search"></span>
                    查询
                </button>
            </div>
        </div>

        <div class="panel panel-default main-hd">
            <div class="panel-heading">
                <a id="add" class="btn btn-default  navbar-btn" href="/c/page/console/auth/selfIntegral/edit"><span class="glyphicon glyphicon-plus"></span>添加</a>
                <a class="btn btn-default navbar-btn" onclick='deleteWord();'><span class="glyphicon glyphicon-trash"></span>删除</a>
                <button class="btn btn-default btn-sm navbar-btn" onclick="uploadApk();">
                    <i class="glyphicon glyphicon-plus-sign"></i>上传apk</button>
            </div>
        </div>
        <!-- 顶部栏 end -->
        <%--主内容显示区 begin--%>
        <div class="main-bd">
            <div>

                <div id="alertFrame"  class="alert alert-danger hide" style="margin: 20px 20px 10px 20px;" >

                    <form role="form" id="selfIntegralForm" class="ajax-form v-form"
                          ajax-callback="pg.uploadCallback"
                          data-submit-btn=".add_submit_btn"
                          action="${BASE_PATH}/c/page/console/auth/selfIntegral/uploadApk"
                          method="post" enctype="multipart/form-data" >

                        <div class="row">
                            <div class="form-group col-xs-6 hide">
                                <label>
                                    积分墙ID
                                </label>
                                <input type="text" class="form-control" name="wallId" id="wallId">
                            </div>
                            <div class="form-group col-xs-6">
                                <label>
                                    APK包
                                </label>
                                <bs:filebtn name="apk" id="apk" icon="glyphicon-file" text="请选择apk文件"/>
                            </div>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-default auto-focus-btn" onclick="closeFrame()">
                                取消</button>
                            <button type="submit" class="btn btn-success add_submit_btn" >
                                保存</button>
                        </div>
                    </form>

                </div>

                <table class="table table-striped table-hover table-bordered table-row-check table-condensed">
                    <thead>
                        <tr>
                            <th>选择</th>
                            <th>APP</th>
                            <th>图标</th>
                            <th>标签</th>
                            <th>总奖励(元)</th>
                            <th>第一天奖励(元)</th>
                            <th>深度任务奖励(元/天)</th>
                            <th>深度任务天数</th>
                            <th>总数量</th>
                            <th>第一步完成数量</th>
                            <th>开始时间</th>
                            <th>结束时间</th>
                            <th>权重</th>
                            <th>状态</th>
                            <th>修改</th>
                        </tr>
                    </thead>
                    <tbody id="tableBody">

                    </tbody>
                    <tfoot>
                        <tr>
                            <td colspan="15" id="pagination">
                            </td>
                        </tr>
                    </tfoot>
                </table>
            </div>
        </div>
        <%--主内容显示区 end --%>
    </tiles:putAttribute>
</tiles:insertTemplate>
