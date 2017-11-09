<%@page pageEncoding="UTF-8" %>
<tiles:insertTemplate template="/WEB-INF/views/base/layout/layout-default.jsp">
    <tiles:putAttribute name="title" value="${CURRENT_FUNCTION.functionName}"/>
    <tiles:putAttribute name="enableConsole" value="true"/>
    <tiles:putAttribute name="enableSidebar" value="true"/>
    <tiles:putAttribute name="styleArea">
        <style>
            td img {
                width: 60px;
            }
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
        </style>
    </tiles:putAttribute>
    <tiles:putAttribute name="scriptArea">
        <script src="/client/js/page/user/package-channel.js" type="text/javascript"></script>
        <script src="/client/js/base/control-convert.js" type="text/javascript"></script>
        <script src="/plugins/upload/image-upload/js/image-upload.js" type="text/javascript"></script>

        <script>

            $(document).on("click","span.closeImg",function () {
                var img = $(this).parent().find(".img-upload").attr("src", "/client/img/add_img.png");
                img.attr("value", "");
            });

        </script>

    </tiles:putAttribute>
    <tiles:putAttribute name="mainArea">
        <!-- 顶部栏 begin -->
        <div class="panel panel-default main-hd">
            <div class="panel-heading">${CURRENT_FUNCTION.functionName}</div>
        </div>
        <!-- 顶部栏 end -->
        <%--主内容显示区 begin --%>
        <div class="main-bd">

            <nav class="navbar navbar-default sub-title-bar" role="navigation">
                <div class="container-fluid">
                    <div class="collapse navbar-collapse">
                        <button class="btn btn-default btn-sm navbar-btn"
                                data-toggle="modal"
                                data-target="#modal-add-package"
                                data-backdrop="static"
                                data-keyboard="false"
                                role="button">
                            <i class="glyphicon glyphicon-plus-sign"></i><spring:message code="btn.add"/>
                        </button>
                        <button class="btn btn-default btn-sm navbar-btn row-edit-btn"
                                data-param-name="id"
                                data-param-selector=".id_checkbox.selected > :checkbox"
                                data-callback="pg.editCallback"
                                data-url="${BASE_PATH}/c/page/console/auth/packageChannel/edit"
                                role="button">
                            <i class="glyphicon glyphicon-edit"></i><spring:message code="btn.modify"/>
                        </button>
                        <%--<button class="btn btn-default btn-sm navbar-btn row-delete-btn"--%>
                                <%--data-param-name="ids"--%>
                                <%--data-param-selector=".id_checkbox.selected > :checkbox"--%>
                                <%--data-callback="pg.deleteCallback"--%>
                                <%--data-url="${BASE_PATH}/c/page/console/auth/packageChannel/remove"--%>
                                <%--role="button">--%>
                            <%--<i class="glyphicon glyphicon-remove"></i><spring:message code="btn.remove"/>--%>
                        <%--</button>--%>
                        <button class="btn btn-default btn-sm navbar-btn" onclick="uploadApk();">
                            <i class="glyphicon glyphicon-plus-sign"></i>上传apk</button>
                    </div>
                </div>
            </nav>

            <div id="alertFrame"  class="alert alert-danger hide" style="margin: 20px 20px 10px 20px;" >
                <form role="form" id="uploadApkForm" class="ajax-form v-form"
                      ajax-callback="pg.uploadCallback"
                      data-submit-btn=".add_submit_btn"
                      action="${BASE_PATH}/c/page/console/auth/packageChannel/uploadApk"
                      method="post" enctype="multipart/form-data" >

                    <div class="row">
                        <div class="form-group col-xs-6">
                            <label>
                                渠道-包名
                            </label>
                            <input type="text" class="form-control" name="channelPackage" id="channelPackage">
                        </div>
                        <div class="form-group col-xs-6">
                            <label>
                                渠道包
                            </label>
                            <bs:filebtn name="apk" id="apk" icon="glyphicon-file" text="请选择apk文件"/>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default auto-focus-btn" onclick="closeFrame()">
                            <spring:message code="btn.cancel"/></button>
                        <button type="submit" class="btn btn-success add_submit_btn" >
                            <spring:message code="btn.save"/></button>
                    </div>
                </form>
            </div>


            <table class="table table-striped table-hover table-bordered table-row-check">
                <thead>
                <tr>
                    <th class="w36">
                        <label class="img_checkbox_label in-td-line">
                            <i class="icon_checkbox"></i>
                            <input type="checkbox" class="img_checkbox row-check-all">
                        </label>
                    </th>
                    <th class="w50">名称</th>
                    <th class="w100">包名</th>
                    <th class="w50">渠道</th>
                    <th class="w50">版本名</th>
                    <th class="w50">版本号</th>
                    <th class="w100">图片</th>
                    <th class="w50">状态</th>
                    <th class="w100">时间</th>
                    <th class="w100">审核备注</th>
                    <th class="w100">apk链接</th>
                    <th class="w100">强制更新</th>
                    <th class="w100">更新备注</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${pg.list}" var="v">
                    <tr data-id="${v.channelName}-${v.packageName}">
                        <td class="td-vm">
                            <label class="id_checkbox img_checkbox_label in-td-line">
                                <i class="icon_checkbox"></i>
                                <input type="checkbox" class="img_checkbox row-checkbox" value="${v.channelName}-${v.packageName}">
                            </label>
                        </td>
                        <td class="td-vm">${v.appName}</td>
                        <td class="td-vm">${v.packageName}</td>
                        <td class="td-vm">${v.channelName}</td>
                        <td class="td-vm">${v.appVersion}</td>
                        <td class="td-vm">${v.versionCode}</td>
                        <td class="td-vm"><img src="${v.appIcon}"></td>
                        <td class="td-vm">
                            <c:if test="${v.status == '0'}">审核中</c:if>
                            <c:if test="${v.status == '1'}">已通过</c:if>
                            <c:if test="${v.status == '2'}">未通过</c:if>
                        </td>
                        <td class="td-vm">${v.uploadTime}</td>
                        <td class="td-vm">${v.notes}</td>
                        <td class="td-vm">${v.apkUrl}</td>
                        <td class="td-vm">
                            <c:if test="${v.isForce == '0'}">否</c:if>
                            <c:if test="${v.isForce == '1'}">是</c:if>
                        </td>
                        <td class="td-vm">${v.updateRemark}</td>
                    </tr>
                </c:forEach>
                <c:forEach begin="1" end="${pg.blankRowCount}">
                    <tr>
                        <td>&nbsp;</td>
                        <td>&nbsp;</td>
                        <td>&nbsp;</td>
                        <td>&nbsp;</td>
                        <td>&nbsp;</td>
                        <td>&nbsp;</td>
                        <td>&nbsp;</td>
                        <td>&nbsp;</td>
                        <td>&nbsp;</td>
                        <td>&nbsp;</td>
                        <td>&nbsp;</td>
                        <td>&nbsp;</td>
                        <td>&nbsp;</td>
                    </tr>
                </c:forEach>
                </tbody>
                <tfoot>
                    <tr>
                        <td colspan="13"><bs:pagination url="${REQUEST_URI}" page="${pg}"/></td>
                    </tr>
                </tfoot>
            </table>
        </div>
        <%--主内容显示区 end --%>
    </tiles:putAttribute>

    <tiles:putAttribute name="modalArea">

        <!-- 添加 begin -->
        <div id="modal-add-package" aria-labelledby="modal-add-pc-title" class="modal fade" tabindex="-1"
             role="dialog" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                        <h4 class="modal-title">添加渠道</h4>
                    </div>
                    <div class="modal-body">
                        <div class="row">
                            <div class="form-group col-xs-6">
                                <label for="add_appName" id="add_appName-label">
                                    app名称
                                </label>
                                <input type="text" class="form-control" id="add_appName" name="add_appName">
                            </div>
                            <div class="form-group col-xs-6">
                                <label for="add_channelName" id="add_channelName-label">
                                    渠道
                                </label>
                                <input type="text" class="form-control" id="add_channelName" name="add_channelName">
                            </div>
                        </div>
                        <div class="row">
                            <div class="form-group col-xs-6">
                                <label for="add_packageName" id="add_packageName-label">
                                    包名
                                </label>
                                <input type="text" class="form-control" id="add_packageName" name="add_packageName">
                            </div>
                            <div class="form-group col-xs-6">
                                <label for="add_status" id="add_status-label">
                                    状态
                                </label>
                                <select class="form-control" id="add_status" name="add_status">
                                    <option value="0">审核中</option>
                                    <option value="1">已通过</option>
                                    <option value="2">未通过</option>
                                </select>
                            </div>
                        </div>

                        <div class="row">
                            <div class="form-group col-xs-6">
                                <label for="add_appVersion" id="add_appVersion-label">
                                    版本名
                                </label>
                                <input type="text" class="form-control" id="add_appVersion" name="add_appVersion">
                            </div>
                            <div class="form-group col-xs-6">
                                <label for="add_versionCode" id="add_versionCode-label">
                                    版本号
                                </label>
                                <input type="text" class="form-control" id="add_versionCode" name="add_versionCode">
                            </div>
                        </div>

                        <div class="row">
                            <div class="form-group col-xs-6">
                                <label for="add_isForce" id="add_isForce-label">
                                    强制更新
                                </label>
                                <select class="form-control" id="add_isForce" name="add_isForce">
                                    <option value="0">否</option>
                                    <option value="1">是</option>
                                </select>
                            </div>
                            <div class="form-group col-xs-6">
                                <label for="add_img" id="add_img-label">
                                    图标
                                </label>
                                <span class='closeImg'>&times;</span>
                                <img class='img-upload' src='/client/img/add_img.png' mode='button-upload-pic'
                                     style='width: 60px;height: 60px; margin-top: 0px; margin-right: 0px'
                                     action='/c/p/picture/upload' filesizelimit='128000' filetypelimit='jpg,png,gif,jpeg'
                                     obj='1' value='' id="add_img" name="add_img" >
                            </div>
                        </div>
                        <div class="row">
                            <div class="form-group col-xs-6">
                                <label for="add_notes" id="add_notes-label">
                                    审核备注
                                </label>
                                <textarea class="form-control" id="add_notes" name="add_notes"></textarea>
                            </div>
                            <div class="form-group col-xs-6">
                                <label for="add_updateRemark" id="add_updateRemark-label">
                                    更新说明
                                </label>
                                <textarea class="form-control" id="add_updateRemark" name="add_updateRemark"></textarea>
                            </div>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default auto-focus-btn" data-dismiss="modal">
                            <spring:message code="btn.cancel"/></button>
                        <button type="button" class="btn btn-success add_submit_btn" id="add_package">
                            <spring:message code="btn.save"/></button>
                    </div>
                </div>
            </div>
        </div>
        <!-- 添加 end -->

        <!-- 修改 start -->
        <div id="modal-edit-package" aria-labelledby="modal-edit-discover-title" class="modal fade" tabindex="-1"
             role="dialog" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                        <h4 class="modal-title">修改渠道</h4>
                    </div>
                    <div class="modal-body">
                        <div class="row">
                            <div class="form-group col-xs-6">
                                <label for="edit_appName" id="edit_appName-label">
                                    app名称
                                </label>
                                <input type="text" class="form-control" id="edit_appName" name="edit_appName">
                            </div>
                            <div class="form-group col-xs-6">
                                <label for="edit_channelName" id="edit_channelName-label">
                                    渠道
                                </label>
                                <input type="text" class="form-control" id="edit_channelName" name="edit_channelName"
                                       readonly="readonly">
                            </div>
                        </div>
                        <div class="row">
                            <div class="form-group col-xs-6">
                                <label for="edit_packageName" id="edit_packageName-label">
                                    包名
                                </label>
                                <input type="text" class="form-control" id="edit_packageName" name="edit_packageName"
                                       readonly="readonly">
                            </div>
                            <div class="form-group col-xs-6">
                                <label for="edit_status" id="edit_status-label">
                                    状态
                                </label>
                                <select class="form-control" id="edit_status" name="edit_status">
                                    <option value="0">审核中</option>
                                    <option value="1">已通过</option>
                                    <option value="2">未通过</option>
                                </select>
                            </div>
                        </div>
                        <div class="row">
                            <div class="form-group col-xs-6">
                                <label for="edit_appVersion" id="edit_appVersion-label">
                                    版本名
                                </label>
                                <input type="text" class="form-control" id="edit_appVersion" name="edit_appVersion">
                            </div>
                            <div class="form-group col-xs-6">
                                <label for="edit_versionCode" id="edit_versionCode-label">
                                    版本号
                                </label>
                                <input type="text" class="form-control" id="edit_versionCode" name="edit_versionCode">
                            </div>
                        </div>
                        <div class="row">
                            <div class="form-group col-xs-6">
                                <label for="edit_isForce" id="edit_isForce-label">
                                    强制更新
                                </label>
                                <select class="form-control" id="edit_isForce" name="edit_isForce">
                                    <option value="0">否</option>
                                    <option value="1">是</option>
                                </select>
                            </div>
                            <div class="form-group col-xs-6">
                                <label for="edit_img" id="edit_img-label">
                                    图标
                                </label>
                                <span class='closeImg'>&times;</span>
                                <img class='img-upload' src='/client/img/add_img.png' mode='button-upload-pic'
                                     style='width: 60px;height: 60px; margin-top: 0px; margin-right: 0px'
                                     action='/c/p/picture/upload' filesizelimit='128000' filetypelimit='jpg,png,gif,jpeg'
                                     obj='1' value='' id="edit_img" name="edit_img" >
                            </div>
                        </div>

                        <div class="row">
                            <div class="form-group col-xs-6">
                                <label for="edit_notes" id="edit_notes-label">
                                    审核备注
                                </label>
                                <textarea class="form-control" id="edit_notes" name="edit_notes"></textarea>
                            </div>
                            <div class="form-group col-xs-6">
                                <label for="edit_updateRemark" id="edit_updateRemark-label">
                                    更新说明
                                </label>
                                <textarea class="form-control" id="edit_updateRemark" name="edit_updateRemark"></textarea>
                            </div>
                        </div>

                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default auto-focus-btn" data-dismiss="modal">
                            <spring:message code="btn.cancel"/></button>
                        <button type="button" class="btn btn-success add_submit_btn" id="edit_package">
                            <spring:message code="btn.save"/></button>
                    </div>
                </div>
            </div>
        </div>
        <!-- 修改 end -->

    </tiles:putAttribute>

</tiles:insertTemplate>
