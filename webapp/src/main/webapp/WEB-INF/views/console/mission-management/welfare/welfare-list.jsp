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
                var welfareTitle = $("#welfareTitle").val().trim();
                var welfareType = $("#welfareType").val().trim();
                var merchantName = $("#merchantName").val().trim();
                var isSelection = $("#isSelection").val();

                $.ajax({
                    url: "/c/page/console/auth/welfare/list",
                    type: "POST",
                    dataType: "JSON",
                    data: {
                        pageNum: pageNum,
                        welfareTitle: welfareTitle,
                        welfareType: welfareType,
                        merchantName: merchantName,
                        isSelection:isSelection
                    },
                    success: function (data) {

                        var details = data.bean.details;
                        var paginationHtml = data.bean.page;
                        objectList.init(details,'pagination','tableBody',paginationHtml);
                        objectList.reset();
                        objectList.setPage();
                        objectList.createHtml();
                        objectList.after();
                    }
                })
            };

            var objectList = {
                //数据
                data:null,
                //翻译组件的div的ID
                paginationId:null,
                //翻页组件
                paginationHtml:null,
                //列表所在table的id
                tableId:null,

                //初始化这些数据
                init:function (data,paginationId,tableId,paginationHtml) {
                    this.data = data;
                    this.paginationId = paginationId;
                    this.tableId = tableId;
                    this.paginationHtml = paginationHtml;
                },
                //每次翻页的时候，清空列表，显示价值动画
                reset:function () {
                    $("#"+this.tableId).empty();
                    tips.loading();
                },
                //把新数据组装成html
                createHtml:function () {

                    var objects = this.data;
                    var newLists = "";
                    for (var i in objects){
                        var object = objects[i];

                        var id = object.welfareId;
                        var title = object.welfareTitle;
                        var icon = object.welfareIcon;
                        var reward = object.welfareReward;
                        var typeName = object.createTime;
                        var type = object.welfareType;
                        if(type==0){
                            typeName = '未分类';
                        }
                        var isSelection = object.isSelection;
                        if (isSelection == '1'){
                            isSelection = "<td style='text-info'>精选</td>";
                        }else {
                            isSelection = "<td>非精选</td>";
                        }

                        var merchantIcon = object.merchantIcon;
                        var merchantName = object.merchantName;

                        var participantsNum = object.participantsNum;
                        var platform = object.welfareStatus;
                        if (platform == '0'){
                            platform = 'ios开启'
                        }else if (platform == '1'){
                            platform = 'andriod开启'
                        }else if (platform == '2'){
                            platform = '全部开启'
                        }else if (platform == '3'){
                            platform = '全部关闭'
                        }

                        var button = "";
                        newLists +=
                                "<tr>"+
                                "    <td>"+(parseInt(i)+1)+"</td>"+
                                "    <td>"+title+"</td>"+
                                "    <td class='app'><img src='"+icon+"'></td>"+
                                "    <td>"+reward+"</td>"+
                                "    <td>"+typeName+"</td>"+
                                        isSelection+
                                "    <td class='app'><img src='"+merchantIcon+"'></td>"+
                                "    <td>"+merchantName+"</td>"+
                                "    <td>"+participantsNum+"</td>"+
                                "    <td>"+platform+"</td>"+
                                "    <td>" +
                                "       <button class='btn btn-warning btn-xs' style='margin-right: 5px;' onclick='objectList.goEditPage("+id+");'>修改</button>" +
                                "   </td></tr>";
                    }
                    $("#"+this.tableId).append(newLists);
                },
                //组装翻页组件
                setPage:function () {

                    $("#"+this.paginationId).html(this.paginationHtml);
                },
                //翻页之后隐藏动画
                after:function () {
                    tips.hideLoading();
                },
                /**
                 * 去修改页面
                 * @param id 要修改对象的ID
                 */
                goEditPage:function (id) {
                    window.location.href = '/c/page/console/auth/welfare/toUpdatePage?id='+id;
                },
                /**
                 * 去添加页面
                 */
                goAddPage:function () {

                }
            };
        </script>

    </tiles:putAttribute>
    <tiles:putAttribute name="mainArea">
        <div class="container-fluid">
            <div class="row">

                <!-- 顶部栏 begin -->
                <div class="panel panel-default main-hd">
                    <div class="panel-heading">
                        <span class="pageTitle">福利列表</span>
                        <span class="searchTitle">福利名称:</span>
                        <input type="text" id = "welfareTitle"/>
                        <span class="searchTitle">福利类型:</span>
                        <select id="welfareType" style="width: 100px;height: 20px;margin-left: 10px;">
                            <option value="">请选择</option>
                            <option value="0">未分类</option>
                            <c:forEach items="${types}" var="type">
                                <option value="${type.typeId}"
                                        <c:if test="${object.welfareType == type.typeId}">selected</c:if> >
                                        ${type.typeName}
                                </option>
                            </c:forEach>
                        </select>
                        <span class="searchTitle">是否精选:</span>
                        <select id="isSelection" style="width: 100px;height: 20px;margin-left: 10px;">
                            <option value="">请选择</option>
                            <option value="1">精选</option>
                        </select>
                        <span class="searchTitle">商家名称:</span>
                        <input type="text" id = "merchantName"/>
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
                <a id="add" class="btn btn-default  navbar-btn" href="/c/page/console/auth/welfare/toAddPage"><span class="glyphicon glyphicon-plus"></span>添加</a>
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
                        <th>名称</th>
                        <th>图标</th>
                        <th>奖励</th>
                        <th>类型</th>
                        <th>是否精选</th>
                        <th>商家图片</th>
                        <th>商家名称</th>
                        <th>参与人数</th>
                        <th>平台</th>
                        <th>操作</th>
                    </tr>
                    </thead>
                    <tbody id="tableBody"></tbody>

                    <tfoot>
                    <tr>
                        <td colspan="11" id="pagination">
                        </td>
                    </tr>
                    </tfoot>
                </table>
            </div>
        </div>
        <%--主内容显示区 end --%>

    </tiles:putAttribute>
</tiles:insertTemplate>
