<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page pageEncoding="UTF-8" %>
<tiles:insertTemplate template="/WEB-INF/views/base/layout/layout-default.jsp">
    <tiles:putAttribute name="title" value="用户任务列表"/>
    <tiles:putAttribute name="enableConsole" value="true"/>
    <tiles:putAttribute name="enableSidebar" value="true"/>
    <tiles:putAttribute name="styleArea">
        <link rel="stylesheet" type="text/css" href="/styles/mission_sort.css">
    </tiles:putAttribute>
    <tiles:putAttribute name="scriptArea">
        <script>
            var imgUrl;
            $(function(){

                // dom加载完毕
                var $m_btn = $(".zoomImg");
                var $modal = $("#myModal");
                $m_btn.on("click", function(){
                    $modal.modal({backdrop: "true"});
                });
            });

            // 测试 bootstrap 居中:弹框出现之前绑定的方法
            var $modal = $("#myModal");
            $modal.on('show.bs.modal', function(){

                var $this = $(this);
                var $modal_dialog = $this.find('.modal-dialog');
                var m_top = ( $(document).height() - $modal_dialog.height() )/4;
                $modal_dialog.css({'margin': m_top + 'px auto'});


            });

            $modal.on('hidden.bs.modal', function(){

                $("#zoomImg").attr("");

            });

            $(document).on('click','.zoomImg',function () {
                imgUrl = $(this).attr('src');
                $("#zoomImg").attr('src',imgUrl)
            });


            function successVerify() {

                $.ajax({
                    url:'/c/page/console/auth/recommendTask/success',
                    type:'POST',
                    dataType:'JSON',
                    success:function (data) {

                        if(data.success){
                            tips.suc(data.msg,2000);
                            setTimeout(function () {
                                window.history.go(-1);
                            },3000);
                        }else{
                            tips.err(data.msg,2000);
                        }
                    }
                })
            };

            /**
             * 未通过
             * */


            function failVerify() {
                $("#remarksDiv").show();

            };
            /**
             * 输入作废原因之后确认作废
             *
             */
            function sureInvalid() {

                var remarks=$("#remarks").val();

                $.ajax({
                    url:'/c/page/console/auth/recommendTask/fail',
                    type:'POST',
                    dataType:'JSON',
                    data:{remarks:remarks},
                    success:function (data) {

                        if(data.success){
                            tips.suc(data.msg,2000);
                            setTimeout(function () {
                                window.history.go(-1);
                            },3000);

                        }else{
                            tips.err(data.msg,2000);
                        }
                    }
                })
            }
        </script>
    </tiles:putAttribute>
    <tiles:putAttribute name="mainArea">
        <!-- 顶部栏 begin -->
        <div class="panel panel-default main-hd">
            <div class="panel-heading">
                <span>审核任务&nbsp;&nbsp;&nbsp;&nbsp;</span>
            </div>
        </div>

        <div class="panel panel-default main-hd">
            <div class="panel-heading">
                <button class="btn btn-success" onclick="successVerify();">审核通过</button>
                <button class="btn btn-success" onclick="failVerify();">审核未通过</button>
            </div>
        </div>
        <!-- 顶部栏 end -->
        <%--主内容显示区 begin--%>
        <div>
            <div id="remarksDiv" style="padding: 2rem;display: none;">
                <div class="col-md-6">
                    <div class="input-group ">
                        <span class="input-group-addon">作废原因</span>
                        <input class="form-control" id="remarks">
                    </div>
                </div>
                <div class="col-md-6 col-md-inset-2">
                    <button onclick="sureInvalid();" class="btn btn-info btn-danger">确定未通过</button>
                </div>
            </div>

            <br>
            <div class="collapse navbar-collapse">
                <span>
                    <label style="margin-right: 140px;color: black;font-size:4rem;"> 任务要求</label>
                </span>
            </div>
            <br>
            <div class="collapse navbar-collapse">
                <span>
                    <label style="margin-right: 140px;color: black;font-size:large;"> 备注要求:</label>
                    <input type="text" style="width: 500px;" value="${detail.remarks}" placeholder="手机号,用户名,验证码等"/>
                </span>
            </div>
            <br>
            <div class="collapse navbar-collapse">
                <span>
                    <label style="margin-right: 140px;color: black;font-size:large;"> 截图要求:</label>
                    <input type="text" style="width: 500px;" value="${detail.imgText}" placeholder="注册登录页面,个人资料页面等"/>
                </span>
            </div>
            <br>
            <div class="collapse navbar-collapse">
                <span>
                    <label style="margin-right: 140px;color: black;font-size:large;"> 审核图片:</label>
                    <c:forEach items="${detail.exampleImgs}" var="e">
                        <img src="${e}" class="zoomImg"  style="width:100px;height: 100px;border:1px solid red;margin-right: 2rem">
                    </c:forEach>
                </span>
            </div>
            <hr>
            <div class="collapse navbar-collapse">
                <span>
                    <label style="margin-right: 140px;color: black;font-size:4rem;"> 用户上传</label>
                </span>
            </div>
            <c:forEach items="${detail.requires}" var="require">
                <br>
                <div class="collapse navbar-collapse">
                <span>
                    <div style="display: flex">
                        <div style="width: 10%"><span style="margin-right: 140px;color: black;font-size:large;display: block"> ${require.requireName}:</span></div>
                        <input type="text" style="width: 500px;" value="${require.requireContent}" placeholder="手机号,用户名,验证码等"/>
                    </div>
                </span>
                </div>
            </c:forEach>
            <br>
            <div class="collapse navbar-collapse">
                <span>
                    <label style="margin-right: 140px;color: black;font-size:large;"> 审核图片:</label>
                    <c:forEach items="${detail.userImgs}" var="img">
                        <img src="${img}" class="zoomImg"  style="width:100px;height: 100px;border:1px solid red;margin-right: 2rem">
                    </c:forEach>
                </span>
            </div>
        </div>
        <%--主内容显示区 end --%>

        <!--弹框 -->
        <div class="modal fade" id="myModal">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                        <h4 class="modal-title"></h4><span style="color: red" id="warning"></span>
                    </div>

                    <div class="modal-body">
                        <img id="zoomImg" src="" height="300px;">
                    </div>
                    <div class="modal-footer">

                    </div>
                </div><!-- /.modal-content -->
            </div><!-- /.modal-dialog -->
        </div><!-- /.modal -->
    </tiles:putAttribute>
</tiles:insertTemplate>