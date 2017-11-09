<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page pageEncoding="UTF-8" %>
<tiles:insertTemplate template="/WEB-INF/views/base/layout/layout-default.jsp">
    <tiles:putAttribute name="title" value="步骤详情"/>
    <tiles:putAttribute name="enableConsole" value="true"/>
    <tiles:putAttribute name="enableSidebar" value="true"/>
    <tiles:putAttribute name="styleArea">
        <link href="/plugins/bootstrap-3.1.1/dist/css/bootstrap.min.css" rel="stylesheet">
        <style>
            .closeImg {
                margin-left: 83px;
                font-size: 21px;
                font-weight: bold;
                line-height: 1;
                color: #000;
                text-shadow: 0 1px 0 #fff;
                filter: alpha(opacity=20);
                opacity: .2;
                cursor:pointer;
            }

            tr{

                padding: 2rem;
            }
            .buttonDiv a{
                display: inline-block;
                margin: 1rem;
                padding: 0.5rem 1rem;
                border: 1px solid #EFEFEF;
                color: whitesmoke;
                font-size: 1rem;
                border-radius: 4px;
                text-decoration: none;
            }
            .copy{
                background-color: blue;
            }
            .download{
                background-color: red;
            }
            .openApp{
                background-color: fuchsia;
            }
            .link{
                background-color: green;
            }
        </style>
    </tiles:putAttribute>
    <tiles:putAttribute name="scriptArea">
        <script src="/client/js/base/control-convert.js" type="text/javascript"></script>
        <script src="/plugins/upload/image-upload/js/image-upload.js" type="text/javascript"></script>
        <script src="/methods/redPackageManager/add_recommend_step.js" type="text/javascript"></script>
        <script src="/plugins/jquery/jquery-1.9.1.min.js"></script>
        <script src="/plugins/bootstrap-3.1.1/dist/js/bootstrap.js"></script>
        <script>

            var missionType = '${missionType}';

            $(document).on("click","span.closeImg",function () {
                var img = $(this).parent().find(".img-upload").attr("src", "/client/img/add_img.png");
                img.attr("value", "");
            });
            
            $(document).on('click','.deleteBtn',function () {
                $(this).parent().remove();
                return false;
            });


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
                    <div class="fr pr5">
                        <div class="input-group input-group-sm w100 fl">
                            <label style="margin-right: 20px;"></label><span></span>
                        </div>
                    </div>
                </div>
                <span>${title}</span>
                <button class="btn btn-info" id="addStep" style="margin-left: 20px">添加步骤</button>
                <button class="btn btn-danger" id="removeLastStep" style="margin-left: 20px">移除最后</button>
                <button class="btn btn-primary" id="modalBtn"  style="margin-left: 20px">添加按钮</button>
                <button class="btn btn-success" id="saveStep" onclick="save();" style="margin-left: 20px">保存步骤</button>
            </div>
        </div>
        <!-- 顶部栏 end -->

        <%--主内容显示区 begin --%>
        <div class="main-bd">
            <%--<div  class="collapse navbar-collapse">--%>
                <table id="step"class="table table-striped table-hover table-bordered table-navbar-no-bottom">
                    <c:forEach items="${steps}" var="step" varStatus="status">
                        <tr>
                            <td style='display:flex;justify-content: flex-start;align-items: center'>
                                <label style='position: relative; bottom:40px;margin-right: 20px'>${status.index+1} :</label>

                                <div style='margin-right: 1rem'>
                                    <select  name='step_status'>
                                        <option value='2' <c:if test="${step.stepStatus == '2'}">selected</c:if>  >全部开启</option>
                                        <option value='1' <c:if test="${step.stepStatus == '1'}">selected</c:if>  >andriod开启</option>
                                        <option value='0' <c:if test="${step.stepStatus == '0'}">selected</c:if>  >ios开启</option>
                                        <option value='3' <c:if test="${step.stepStatus == '3'}">selected</c:if>  >全部关闭</option>
                                    </select>
                                </div>

                                步骤:<input type='number' style='margin-right: 1rem;margin-left: 1rem' value="${step.stepNum}">

                                <textarea  style='width: 450px;margin-right: 2rem;display: inline-block'>${step.stepContent}</textarea>
                                <%--<br>--%>
                                <div style='width: 100px;height: 100px; margin: 20px;display: inline-block'>
                                    <span class='closeImg'>&times;</span>
                                    <img class='img-upload' src='<c:forEach items="${step.randomImgVos}" var="img" begin="0" end="0">${img.imgUrl}</c:forEach>' mode='button-upload-pic' style='width: 100px;height: 100px; margin-top: -23px; margin-right: -10px' action='/c/p/picture/upload' filesizelimit='128000' filetypelimit='jpg,png,gif,jpeg' obj='<c:forEach items="${step.randomImgVos}" var="img" begin="0" end="0">${img.randomString}</c:forEach>' value='<c:forEach items="${step.randomImgVos}" var="img" begin="0" end="0">${img.imgUrl}</c:forEach>' >
                                </div>

                                <div style='width: 100px;height: 100px; margin: 20px;display: inline-block'>
                                    <span class='closeImg'>&times;</span>
                                    <img class='img-upload' src='<c:forEach items="${step.randomImgVos}" var="img" begin="1" end="1">${img.imgUrl}</c:forEach>' mode='button-upload-pic' style='width: 100px;height: 100px; margin-top: -23px; margin-right: -10px' action='/c/p/picture/upload' filesizelimit='128000' filetypelimit='jpg,png,gif,jpeg' obj='<c:forEach items="${step.randomImgVos}" var="img" begin="1" end="1">${img.randomString}</c:forEach>' value='<c:forEach items="${step.randomImgVos}" var="img" begin="1" end="1">${img.imgUrl}</c:forEach>' >
                                </div>

                                <div style='width: 100px;height: 100px; margin: 20px;display: inline-block'>
                                    <span class='closeImg'>&times;</span>
                                    <img class='img-upload' src='<c:forEach items="${step.randomImgVos}" var="img" begin="2" end="2">${img.imgUrl}</c:forEach>' mode='button-upload-pic' style='width: 100px;height: 100px; margin-top: -23px; margin-right: -10px' action='/c/p/picture/upload' filesizelimit='128000' filetypelimit='jpg,png,gif,jpeg' obj='<c:forEach items="${step.randomImgVos}" var="img" begin="2" end="2">${img.randomString}</c:forEach>' value='<c:forEach items="${step.randomImgVos}" var="img" begin="2" end="2">${img.imgUrl}</c:forEach>' >
                                </div>

                                <div style='width: 100px;height: 100px; margin: 20px;display: inline-block'>
                                    <span class='closeImg'>&times;</span>
                                    <img class='img-upload' src='<c:forEach items="${step.randomImgVos}" var="img" begin="3" end="3">${img.imgUrl}</c:forEach>' mode='button-upload-pic' style='width: 100px;height: 100px; margin-top: -23px; margin-right: -10px' action='/c/p/picture/upload' filesizelimit='128000' filetypelimit='jpg,png,gif,jpeg' obj='<c:forEach items="${step.randomImgVos}" var="img" begin="3" end="3">${img.randomString}</c:forEach>' value='<c:forEach items="${step.randomImgVos}" var="img" begin="3" end="3">${img.imgUrl}</c:forEach>' >
                                </div>
                                <div class='buttonDiv' style='display: inline-block'>
                                    ${step.btnHtml}
                                </div>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
            <%--</div>--%>
        </div>

        <!--弹框 -->
        <div class="modal fade" id="myModal">
            <div class="modal-dialog" style="border: 5px solid blue">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                        <h4 class="modal-title">添加按钮</h4><span style="color: red" id="warning"></span>
                    </div>

                    <div class="modal-body">
                        <div class="input-group">
                            <label class="input-group-addon">
                                <select id="typeSelect" onchange="typeChange();">按钮类型
                                    <option value="0">选择类型</option>
                                    <option value="copy">复制文字</option>
                                    <option value="download">下载应用</option>
                                    <option value="openApp">打开应用</option>
                                    <option value="link">打开链接</option>
                                </select>
                            </label>
                            <input class="form-control" type="text" id="type" placeholder="请选择按钮类型">
                        </div>

                        <div class="input-group" style="margin-top: 1rem">
                            <label class="input-group-addon">
                                <select id="stepSelect" onchange="stepChange();">添加步骤
                                    <option value="0">添加步骤</option>
                                </select>
                            </label>
                            <input class="form-control" type="text" id="stepInput" placeholder="请选择按钮类型">
                        </div>
                        <div class="input-group"  style="margin-top: 1rem">
                            <label class="input-group-addon"> 按 钮 标 题 </label>
                            <input class="form-control" type="text" placeholder="请输入按钮标题" id="title">
                        </div>
                        <div class="input-group"  style="margin-top: 1rem">
                            <label class="input-group-addon"> 按 钮 参 数 </label>
                            <input class="form-control" type="text" placeholder="请输入按钮标题" id="param">
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" id="closeModal" class="btn btn-default" data-dismiss="modal">取消</button>
                        <button type="button" onclick="addButtonToStep();" class="btn btn-primary">确定</button>
                    </div>
                </div><!-- /.modal-content -->
            </div><!-- /.modal-dialog -->
        </div><!-- /.modal -->

        <script type="text/javascript">

        </script>
        <%--主内容显示区 end --%>
    </tiles:putAttribute>
</tiles:insertTemplate>

