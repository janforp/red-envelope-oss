<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page pageEncoding="UTF-8" %>
<tiles:insertTemplate template="/WEB-INF/views/base/layout/layout-default.jsp">
    <tiles:putAttribute name="title" value="文章列表"/>
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
            tr{
                cursor: pointer;
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
                var articleTitle = $("#articleTitle").val().trim();
                var articleType = $("#articleType").val().trim();
                var startMissionTime = $("#startMissionTime").val().trim();
                var status = $("#status").val();

                $.ajax({
                    url: "/c/page/console/auth/article/list",
                    type: "POST",
                    dataType: "JSON",
                    data: {
                        pageNum: pageNum,
                        articleTitle: articleTitle,
                        articleType: articleType,
                        status: status,
                    },
                    success: function (data) {

                        var details = data.bean.details;
                        var paginationHtml = data.bean.page;
                        articleList.init(details,'pagination','tableBody',paginationHtml);
                        articleList.reset();
                        articleList.setPage();
                        articleList.createHtml();
                        articleList.after();
                    }
                })
            };

            var articleList = {

                data:null,
                paginationId:null,
                paginationHtml:null,
                tableId:null,
                selectedArticleId:null,

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
                        var articleId = wall.articleId;
                        var articleTitle = wall.articleTitle;
                        var articleUrl = wall.articleUrl;
                        var articleIcon = wall.articleIcon;

                        var appReadMoney = wall.appReadMoney;
                        var wxClickMoney = wall.wxClickMoney;

                        var totalClickTimes = wall.totalClickTimes;
                        var leftClickTimes = wall.leftClickTimes;

                        var startMissionTime = wall.startMissionTime;
                        var endMissionTime = wall.endMissionTime;
                        var articleWeight = wall.articleWeight;

                        var status = wall.articleAuthor;

                        var articleType = wall.articleType;
                        var button;
                        if(articleType == 0){

                            button = "<td><button class='btn btn-warning btn-xs' style='margin-right: 5px;' onclick='editArticle("+articleId+");'>修改</button><button class='btn btn-info btn-xs' onclick='showAd("+articleId+");'>查看广告</button></td></tr>";
                        }else{
                            button = "<td><button class='btn btn-warning btn-xs' onclick='editArticle("+articleId+");'>修改</button></td></tr>";
                        }

                        if (status == '3'){
                            status = "    <td class='ing'>进行中</td>";
                        }else if (status == '2'){
                            status = "    <td class='wait'>未开始</td>";
                        }else if (status == '1'){
                            status = "    <td class='end'>已结束</td>";
                        }

                        newLists +=
                                "<tr onclick='articleList.selectLine(this);'>"+
                                "    <td><input type='checkbox' value='"+articleId+"'></td>"+
                                "    <td>"+articleTitle+"</td>"+
                                "    <td>"+articleUrl+"</td>"+
                                "    <td class='app'><img src='"+articleIcon+"'></td>"+
                                "    <td>"+appReadMoney+"</td>"+
                                "    <td>"+wxClickMoney+"</td>"+
                                "    <td>"+totalClickTimes+"</td>"+
                                "    <td>"+leftClickTimes+"</td>"+
                                "    <td>"+startMissionTime+"</td>"+
                                "    <td>"+endMissionTime+"</td>"+
                                "    <td>"+articleWeight+"</td>"+
                                            status + button;
                    }
                    $("#"+this.tableId).append(newLists);
                },
                setPage:function () {

                    $("#"+this.paginationId).html(this.paginationHtml);
                },
                after:function () {
                    tips.hideLoading();
                },
                /**
                 * 点击该tr的任意位置酒选定该
                 */
                selectLine:function(tr){
                    var input = $(tr).find("input[type=checkbox]");
                    if($(input).is(':checked')){
                        $(input).prop('checked',false);

                    }else{
                        $(input).prop('checked',true);
                    }
                },
                /**
                 * 添加点击次数
                 */
                addClickTime:function(){

                    var input = $("input[type=checkbox]:checked");
                    if($(input).length >1 || $(input).length == 0){
                        tips.err('只能选择一条',2000);
                        return;
                    }
                    var articleId = $(input).val();
                    $("input[type=checkbox]").prop('disabled',true);
                    $('tr').attr('onclick',null);

                    $("#alertFrame").removeClass('hide');
                }
            };
            /**
             * 点击修改,弹出修改框
             */
            function editArticle(articleId) {

                window.location.href = '/c/page/console/auth/article/toUpdatePage?articleId='+articleId;
            }
            /**
             * 查看广告
             * @param articleId
             */
            function showAd(articleId) {

                window.location.href = '/c/page/console/auth/article/adList?articleId='+articleId;
            }

            /**
             * 关闭添加红包弹出框
             */
            $(document).on("click","#close",function () {
                $("#alertFrame").addClass("hide");
                //恢复复选框选择功能
                $("input[type=checkbox]").prop("disabled",false);
            });


            /**
             * 点击弹框上的添加红包按钮
             */
            function doAddRed() {

                var $input = $("input[type=checkbox]:checked");
                if ($input == null ||$input.length == 0||$input.length>1 ) {
                    tips.err("请选择一条纪录",2000);
                    return;
                }
                var articleId = $input.val();

                var addNum = $("#addNum").val().trim();
                if (addNum == "" || addNum == null || addNum == undefined){
                    tips.err("请输入红包总数",4000);
                    return;
                }

                $.ajax({
                    url     :   "/c/page/console/auth/article/addNum",
                    type    :   "POST",
                    dataType:   "JSON",
                    data    :   {articleId:articleId,addNum:addNum},
                    success:function (data) {
                        if(data.success){
                            tips.suc(data.msg,3000);

                            $("#close").click();

                            setTimeout(
                                    function () {

                                        window.location.reload();

                                    },2000
                            );

                        }else{
                            tips.err("添加失败",3000);
                        }
                    },
                    error:function () {

                    }
                });
            }
        </script>

    </tiles:putAttribute>
    <tiles:putAttribute name="mainArea">
        <div class="container-fluid">
            <div class="row">

                <!-- 顶部栏 begin -->
                <div class="panel panel-default main-hd">
                    <div class="panel-heading">
                        <span class="pageTitle">转发文章</span>
                        <span class="searchTitle">文章名称:</span>
                        <input type="text" id = "articleTitle"/>
                        <span class="searchTitle">类型:</span>
                        <select id="articleType" style="width: 100px;height: 20px;margin-left: 10px;">
                            <option value="">请选择</option>
                            <option value="0">自己编辑的的文章</option>
                            <option value="1">用户提供的文章链接</option>
                        </select>
                        <span class="searchTitle">状态:</span>
                        <select id="status" style="width: 100px;height: 20px;margin-left: 10px;">
                            <option value="">请选择</option>
                            <option value="0">未开始</option>
                            <option value="1">进行中</option>
                            <option value="2">已结束</option>
                        </select>
                        <span class="searchTitle">开始时间:</span>
                        <input type="text" id = "startMissionTime" style="height: 30px;"  class="Wdate" onclick="WdatePicker({lang:'zh-cn',dateFmt:'yyyy-MM-dd HH:mm:ss'});"/>
                        <button class="btn btn-default" type="button" onclick="turnPage(1)">
                            <span class="glyphicon glyphicon-search"></span>
                            查询
                        </button>
                    </div>
                </div>

            </div>
        </div>

        <div class="panel panel-default main-hd">
            <div class="panel-heading">
                <a id="add" class="btn btn-default  navbar-btn" href="/c/page/console/auth/article/toAddPage"><span class="glyphicon glyphicon-plus"></span>添加</a>
                <a onclick="articleList.addClickTime();" class="btn btn-warning  navbar-btn"><span class="glyphicon glyphicon-plus"></span>添加点击次数</a>
            </div>

            <div id="alertFrame"  class="alert alert-danger hide" style="margin: 20px 20px 10px 20px;" >
                <a href="#" class="close" id="close" >
                    &times;
                </a>
                <label style="margin: 0px 10px;color: black;font-size:large;"> 增加次数:</label>
                <input type="text" id="addNum"  placeholder="必填"  />
                <button class="btn-danger btn" onclick="doAddRed();">确定添加</button>
            </div>


        </div>
        <!-- 顶部栏 end -->
        <%--主内容显示区 begin--%>
        <div class="main-bd">
            <div>
                <table class="table table-striped table-hover table-bordered table-row-check table-condensed">
                    <thead>
                    <tr>
                        <th>序号</th>
                        <th>文章标题</th>
                        <th>客户提供的链接</th>
                        <th>图片</th>
                        <th>阅读奖励(元)</th>
                        <th>点击奖励(元/次)</th>
                        <th>总次数</th>
                        <th>剩余次数</th>
                        <th>投放时间</th>
                        <th>结束时间</th>
                        <th>权重</th>
                        <th>状态</th>
                        <th>操作</th>
                    </tr>
                    </thead>
                    <tbody id="tableBody"></tbody>

                    <tfoot>
                    <tr>
                        <td colspan="13" id="pagination">
                        </td>
                    </tr>
                    </tfoot>
                </table>
            </div>
        </div>
        <%--主内容显示区 end --%>

    </tiles:putAttribute>
</tiles:insertTemplate>
