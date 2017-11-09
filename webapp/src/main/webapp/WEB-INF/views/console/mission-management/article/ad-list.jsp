<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page pageEncoding="UTF-8" %>
<tiles:insertTemplate template="/WEB-INF/views/base/layout/layout-default.jsp">
    <tiles:putAttribute name="title" value="试玩任务"/>
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
            .modal img {
                width: 50px;
                height: 50px;
            }
            tbody img{
                width: 30px;
                height:30px;
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

        <script>
            $(function () {
                ad.init();
                $("#myModal").on('show.bs.modal',function () {
                    ad.beforeShow();
                });
                $("#myModal").on('shown.bs.modal',function () {
                    ad.afterShow();
                });
                $("#myModal").on('hide.bs.modal',function () {
                    ad.beforeHide();
                });
                $("#myModal").on('hidden.bs.modal',function () {
                    ad.afterHide();
                });
            });

            var ad = {
                articleId:null,
                adId:null,
                adIcon:null,
                adUrl:null,
                adOrder:null,
                isDisplay:null,

                init:function () {
                    this.articleId = '${articleId}';
                },
                update:function (adId) {
                    this.adId = adId ;
                },
                beforeShow:function () {

                    if(this.adId == null) {//添加
                        $("#myModalLabel").text('添加广告')
                    }else {//修改
                        $("#myModalLabel").text('修改广告')
                        this.setAdById();
                    }
                },
                setAdById:function () {
                    $.ajax({
                        url:'/c/page/console/auth/article/editAd',
                        type:'post',
                        dataType:'json',
                        data:{
                            adId:this.adId
                        },
                        success:function (data) {
                            if(data.success){
                                var ad = data.bean.ad;
                                $("#adUrl").val(ad.adUrl);
                                $("#adOrder").val(ad.adOrder);
                                $("#isDisplay").val(ad.isDisplay);
                                $("#adIcon").attr('src',ad.adIcon);

                            }else {
                                tips.err('操作失败',2000);
                            }
                        }
                    });
                },
                getAdProperties:function () {
                    this.adIcon = $("#adIcon").attr('src');
                    this.adUrl = $("#adUrl").val().trim();
                    this.adOrder = $("#adOrder").val().trim();
                    this.isDisplay = $("#isDisplay").val();
                },
                afterShow:function () {
                },
                beforeHide:function () {
                },
                afterHide:function () {
                },
                saveAd:function () {
                    this.getAdProperties();
                    this.sendAjax();
                },
                sendAjax:function () {

                    $.ajax({
                        url:'/c/page/console/auth/article/saveAd',
                        type:'post',
                        dataType:'json',
                        data:{
                            adId:this.adId,
                            articleId:this.articleId,
                            adIcon:this.adIcon,
                            adUrl:this.adUrl,
                            adOrder:this.adOrder,
                            isDisplay:this.isDisplay
                        },
                        success:function (data) {
                            if(data.success){

                                tips.suc("操作成功",2000);
                                setTimeout(function () {
                                    window.location.reload();
                                },2000);
                            }
                        }
                    });
                }
            };

        </script>




    </tiles:putAttribute>
    <tiles:putAttribute name="mainArea">
        <div class="panel panel-default main-hd">
            <div class="panel-heading">
                <a id="add" class="btn btn-default  navbar-btn" href="#myModal" data-toggle="modal"><span class="glyphicon glyphicon-plus"></span>添加</a>
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
                        <th>图片</th>
                        <th>链接</th>
                        <th>是否显示</th>
                        <th>权重</th>
                        <th>操作</th>
                    </tr>
                    </thead>
                    <tbody id="tableBody">
                        <c:forEach items="${ads}" var="ad" varStatus="status">
                            <tr>
                                <td>${status.index + 1}</td>
                                <td><img src="${ad.adIcon}"></td>
                                <td>${ad.adUrl}</td>
                                <td>
                                    <c:if test="${ad.isDisplay == 1}">显示</c:if>
                                    <c:if test="${ad.isDisplay == 0}">关闭</c:if>
                                </td>
                                <td>${ad.adOrder}</td>
                                <td><button class="btn btn-warning" onclick="ad.update('${ad.adId}')" data-toggle="modal" data-target="#myModal">修改</button></td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
        <%--主内容显示区 end --%>

        <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-keyboard="false">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                        <h4 class="modal-title" id="myModalLabel"></h4>
                    </div>
                    <div class="modal-body">
                        <div class="input-group one-row">
                            <label class="input-group-addon">广告链接</label>
                            <input class="form-control" type="text" id="adUrl">
                        </div>
                        <div class="input-group one-row">
                            <label class="input-group-addon">权&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;重</label>
                            <input class="form-control" type="number" id="adOrder">
                            <label class="input-group-addon">是否显示</label>
                            <select class="form-control" id="isDisplay">
                                <option value="1">显&nbsp;&nbsp;&nbsp;&nbsp;示</option>
                                <option value="0">关&nbsp;&nbsp;&nbsp;&nbsp;闭</option>
                            </select>
                        </div>
                        <div class="input-group one-row">
                            <button type="button" class="btn btn-info">广告图片</button>
                            <img class='img-upload' src="/client/img/grey.gif" mode='button-upload-pic' id="adIcon" style="width: 100px;height: 100px;border: 1px solid #EFEFF4;margin-left: 20px;"  action='/c/p/picture/upload' filesizelimit='128000' filetypelimit='jpg,png,gif,jpeg' obj='articleIcon'>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                        <button type="button" class="btn btn-primary" onclick="ad.saveAd();">提交更改</button>
                    </div>
                </div><!-- /.modal-content -->
            </div><!-- /.modal -->
        </div>




    </tiles:putAttribute>
</tiles:insertTemplate>
