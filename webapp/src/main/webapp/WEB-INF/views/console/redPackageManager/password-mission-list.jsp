<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page pageEncoding="UTF-8" %>
<tiles:insertTemplate template="/WEB-INF/views/base/layout/layout-default.jsp">
    <tiles:putAttribute name="title" value="口令红包"/>
    <tiles:putAttribute name="enableConsole" value="true"/>
    <tiles:putAttribute name="enableSidebar" value="true"/>
    <tiles:putAttribute name="styleArea">
        <style>
            tbody input{
                width: 30px;height: 30px;
            }

            img{
                width: 30px;
                height: 30px;
            }
        </style>
    </tiles:putAttribute>
    <tiles:putAttribute name="scriptArea">
        <script src="/client/js/page/mission/password-mission-list.js" type="text/javascript"></script>
        <script src="/methods/util/util.js" type="text/javascript"></script>
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
        <div class="functionButtonDiv" style="margin-left: 20px;margin-top: 5px;">
            <button class="searchButton btn-primary" id="modalBtn">添加</button>
            <button class="searchButton btn-warning" onclick="modify();">修改</button>
        </div>
        <!-- 顶部栏 end -->
        <%--主内容显示区 begin--%>
        <div class="main-bd">
            <div>
                <table class="table table-striped table-hover table-bordered table-row-check">
                    <thead>
                    <tr>
                        <th>选择</th>
                        <th>口令</th>
                        <th>总数</th>
                        <th>剩余</th>
                        <th>最小金额</th>
                        <th>最大金额</th>
                        <th>结束时间</th>
                    </tr>
                    </thead>
                    <tbody id="moneyBody"></tbody>

                    <tfoot>
                    <tr>
                        <td colspan="7" id="pagination">
                        </td>
                    </tr>
                    </tfoot>
                </table>
            </div>
        </div>
        <%--主内容显示区 end --%>

        <!--弹框 -->
        <div class="modal fade" id="myModal">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                        <h4 class="modal-title">口令红包</h4><span style="color: red" id="warning"></span>
                    </div>
                    <input type="hidden" id="id">
                    <input type="hidden" id="modify">
                    <div class="modal-body">
                        <div class="input-group one-row">
                            <label class="input-group-addon">红包口令</label>
                            <input class="form-control" type="text" id="password">
                            <input class="form-control btn-blue" type="button" onclick="utils.createCode(8,'password')" value="生成口令">
                        </div>
                        <div class="input-group one-row">
                            <label class="input-group-addon">红包总数</label>
                            <input class="form-control" type="text" id="totalNum" placeholder="红包总数">
                            <label class="input-group-addon">剩余次数</label>
                            <input class="form-control" type="text" id="leftNum" placeholder="需要输入">
                        </div>
                        <div class="input-group one-row">
                            <label class="input-group-addon">最大金额</label>
                            <input class="form-control" type="text" id="maxMoney">
                            <label class="input-group-addon">最小金额</label>
                            <input class="form-control" type="text" id="minMoney">
                        </div>
                        <div class="input-group one-row">
                            <label class="input-group-addon">结束时间</label>
                            <input type="text" id = "endTime" style="height: 30px;"  class="form-control Wdate" onclick="WdatePicker({lang:'zh-cn',dateFmt:'yyyy-MM-dd HH:mm:ss '});"/>
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
