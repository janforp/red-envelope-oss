<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page pageEncoding="UTF-8" %>
<tiles:insertTemplate template="/WEB-INF/views/base/layout/layout-default.jsp">
    <tiles:putAttribute name="title" value="分享任务列表"/>
    <tiles:putAttribute name="enableConsole" value="true"/>
    <tiles:putAttribute name="enableSidebar" value="true"/>
    <tiles:putAttribute name="styleArea">
        <link rel="stylesheet" type="text/css" href="/client/css/template/base.css">
        <style>
            tbody input{
                width: 30px;height: 30px;
            }
            .one-row{
                margin: 10px 0;
            }
            img{
                width: 50px;
                height: 50px;
            }
        </style>
    </tiles:putAttribute>
    <tiles:putAttribute name="scriptArea">
        <script src="/client/js/page/mission/app-share-list.js" type="text/javascript"></script>
        <script src="/client/js/base/control-convert.js" type="text/javascript"></script>
        <script src="/plugins/upload/image-upload/js/image-upload.js" type="text/javascript"></script>
        <script src="/plugins/My97DatePicker/function.js"></script>
        <script src="/plugins/My97DatePicker/WdatePicker.js"></script>
        <script>
            $(function(){

                $("#dBtn").dropdown()
                // dom加载完毕
                var $m_btn = $("#modalBtn");
                var $modal = $("#myModal");
                $m_btn.on("click", function(){
                    $modal.modal({backdrop: "static"});
                });
            });

            // 测试 bootstrap 居中:弹框出现之前绑定的方法
            var $modal = $("#myModal");
            $modal.on('show.bs.modal', function(){

                var $this = $(this);
                var $modal_dialog = $this.find('.modal-dialog');
                var m_top = ( $(document).height() - $modal_dialog.height() )/4;
                $modal_dialog.css({'margin': m_top + 'px auto'});


                //打开模态框之前,加载当前有多少个步骤
                var stepDiv = $("tr");
                for (var i=0;i<stepDiv.length;i++){

                    var option = new Option("序号:"+(i+1),i+1);
                    $("#stepSelect").append(option)
                }
            });

            $modal.on('hidden.bs.modal', function(){

                $("#type").val("");
                $("#stepInput").val("");
                $("#stepSelect").empty();
                $("#stepSelect").append("<option value='0'>添加步骤</option>");
                $("#title").val("");
                $("#param").val("");
            });

            $("#closeModal").click
        </script>
    </tiles:putAttribute>
    <tiles:putAttribute name="mainArea">
        <!-- 顶部栏 begin -->
        <div class="panel panel-default main-hd">
            <div class="panel-heading">
                <span>分享任务列表</span>
            </div>
        </div>
        <div class="functionButtonDiv">
            <button class="searchButton btn-blue" id="modalBtn">添加</button>
            <button class="searchButton btn-red" onclick="modify();">修改</button>
        </div>
        <!-- 顶部栏 end -->
        <%--主内容显示区 begin--%>
        <div class="main-bd">
            <div>
                <table class="table table-striped table-hover table-bordered table-row-check">
                    <thead>
                    <tr>
                        <th>选择</th>
                        <th>名称</th>
                        <th>奖励(元)</th>
                        <th>图片</th>
                        <th>链接</th>
                        <th>总需次数</th>
                        <th>剩余次数</th>
                        <th>开始时间</th>
                        <th>创建时间</th>
                    </tr>
                    </thead>
                    <tbody id="moneyBody"></tbody>

                    <tfoot>
                    <tr>
                        <td colspan="9" id="pagination">
                        </td>
                    </tr>
                    </tfoot>
                </table>
            </div>
        </div>
        <%--主内容显示区 end --%>

        <!--弹框 -->
        <div class="modal fade" id="myModal">
            <div class="modal-dialog" style="border: 5px solid blue">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                        <h4 class="modal-title">添加任务</h4><span style="color: red" id="warning"></span>
                    </div>
                    <input type="hidden" id="missionId">
                    <input type="hidden" id="modify">
                    <div class="modal-body">
                        <div class="input-group one-row">
                            <label class="input-group-addon">任务标题</label>
                            <input class="form-control" type="text" id="title">
                            <label class="input-group-addon">点击奖励</label>
                            <input class="form-control" type="text" id="money" placeholder="单位:元">
                        </div>
                        <div class="input-group one-row">
                            <label class="input-group-addon">跳转链接</label>
                            <input class="form-control" type="text" id="callbackUrl">
                        </div>
                        <div class="input-group one-row">
                            <label class="input-group-addon">总需次数</label>
                            <input class="form-control" type="text" id="totalTimes">
                            <label class="input-group-addon">剩余次数</label>
                            <input class="form-control" type="text" id="leftTimes" placeholder="默认与总次数相同">
                        </div>
                        <div class="input-group one-row">
                            <label class="input-group-addon">任务描述</label>
                            <textarea class="form-control" type="text" id="desc"></textarea>
                        </div>
                        <div class="input-group one-row">
                            <label class="input-group-addon">分享文字</label>
                            <textarea class="form-control" type="text" id="text"></textarea>
                        </div>
                        <div class="input-group one-row">
                            <label class="input-group-addon">进&nbsp; 行&nbsp; 中</label>
                            <input type="radio" class="form-control" name="isEnd" value="1">
                            <label class="input-group-addon">已 &nbsp;结&nbsp; 束</label>
                            <input type="radio"  class="form-control" name="isEnd" value="0">
                        </div>
                        <div class="input-group one-row">
                            <label class="input-group-addon">开始时间</label>
                            <input type="text" id = "startTime" style="height: 30px;"  class="form-control Wdate" onclick="WdatePicker({lang:'zh-cn',dateFmt:'yyyy-MM-dd HH:mm:ss '});"/>
                        </div>
                        <div class="input-group one-row">
                            <label class="input-group-addon" style="background-color: blue;color: white">任务图片</label>
                            <img class='img-upload' mode='button-upload-pic' id="missionIcon" style="width: 100px;height: 100px;border: 1px solid #EFEFF4"  action='/c/p/picture/upload' filesizelimit='128000' filetypelimit='jpg,png,gif,jpeg' obj='missionIcon'>
                        </div>
                        <div class="input-group one-row">
                            <label class="input-group-addon">示例图片</label>
                            <img class='img-upload' mode='button-upload-pic' id="exampleIcon" style="width: 100px;height: 100px;border: 1px solid #EFEFF4"  action='/c/p/picture/upload' filesizelimit='128000' filetypelimit='jpg,png,gif,jpeg' obj='exampleIcon'>
                            </div>

                    </div>
                    <div class="modal-footer">
                        <button type="button" id="closeModal" class="btn btn-default" data-dismiss="modal">取消</button>
                        <button type="button" onclick="add();" class="btn btn-primary">确定</button>
                    </div>
                </div><!-- /.modal-content -->
            </div><!-- /.modal-dialog -->
        </div><!-- /.modal -->



    </tiles:putAttribute>
</tiles:insertTemplate>
