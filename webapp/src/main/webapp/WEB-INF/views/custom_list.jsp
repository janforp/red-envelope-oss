<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page pageEncoding="UTF-8" %>
<tiles:insertTemplate template="/WEB-INF/views/base/layout/layout-default.jsp">
    <tiles:putAttribute name="title" value="客户列表"/>
    <tiles:putAttribute name="enableConsole" value="true"/>
    <tiles:putAttribute name="enableSidebar" value="true"/>
    <tiles:putAttribute name="styleArea">
        <style>
            .separator_line {
                margin-top: 10px;
                height: 1px;
                background-color: #CCCCCC;
            }
            .table tbody td {
                text-align: center;
                white-space:nowrap;
            }
            .table tbody td img {
                width:60px;
                height:60px;
            }

            .table th {
                text-align: center;
            }

            input[type='checkbox']{
                width:  40px;
                height: 40px;
            }

        </style>
    </tiles:putAttribute>
    <tiles:putAttribute name="scriptArea">

        <script src="/client/js/base/control-convert.js" type="text/javascript"></script>
        <script src="/client/js/base/validation.js" type="text/javascript"></script>
        <script src="/plugins/upload/image-upload/js/my.jquery-post-upload-styleX.js" type="text/javascript"></script>
        <script src="/methods/custom_list.js" type="text/javascript"></script>
    </tiles:putAttribute>
    <tiles:putAttribute name="mainArea">

        <!-- 顶部栏 begin -->
        <div class="panel panel-default main-hd">
            <div class="panel-heading">
                <span>客户列表&nbsp;&nbsp;&nbsp;&nbsp;</span>
                <label>类型:</label>
                <select id="type" style="width: 175px;height: 30px;">
                    <option value="1">公众号</option>
                    <option value="0">普通商户</option>
                </select>
                <label>微信号:</label>
                <input type="text" id="customerWx" placeholder="请输入微信号" >
                <label>客户名称:</label>
                <input type="text" id="customerName"  placeholder="请输入客户名称">
                <button class="btn btn-success" id="search" onclick="search(1)">查询</button>
            </div>
        </div>
        <div class="panel panel-default main-hd">
            <div class="panel-heading">
                <button class="btn btn-success" id ="update" style="width: 80px">修改</button>
                <button class="btn btn-success" id ="delete"  style="width: 80px">删除</button>
                <button class="btn btn-success" id ="add" style="width: 80px">添加</button>
            </div>
        </div>
        <!-- 顶部栏 end -->

        <%--主内容显示区 begin --%>
        <div class="main-bd">

                <%--查询--%>
                <%--分割线--%>
            <div class="separator_line">
            </div>

                <%--数据表格--%>
            <div style="width:100%; overflow:scroll;">
                <table class="table table-striped table-hover table-bordered table-navbar-no-bottom">
                    <thead>
                        <tr>
                            <th width='10%'>选择</th>
                            <th width='10%'>类型</th>
                            <th width='10%'>微信号</th>
                            <th width='10%'>名称</th>
                            <th width='10%'>头像</th>
                            <th width='10%'>appid</th>
                            <th width='10%'>appsecret</th>
                            <th width='10%'>token</th>
                            <th width='10%'>AESKey</th>
                            <th width='10%'>客户秘钥</th>
                            <th width='10%'>创建时间</th>
                            <th width='10%'>加密</th>

                        </tr>
                    </thead>
                    <tbody id="tbody">

                    </tbody>
                    <tfoot id="tfoot">

                    </tfoot>
                </table>
            </div>
                <%--添加隐藏框 start --%>
            <div id="addDiv" style="display : none;margin-top: 50px; position: absolute;top: 50px;left: 45%;">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close"  data-dismiss="modal" aria-label="Close"><span
                                    aria-hidden="true">&times;</span></button>
                            <h4 class="modal-title">添加客户</h4>
                        </div>
                        <div class="modal-body">
                            <div class="row">
                                <div class="form-group col-xs-6">
                                    <span style="color: red;font-size: 25px;position:relative; top:7px;margin-right: 10px">*</span>
                                    <label >选择类型</label><span style="color: red;"></span>
                                    <select id="add_type" style="width: 175px;height: 30px;">
                                        <option value="1">公众号</option>
                                        <option value="0">普通商户</option>
                                    </select>
                                </div>
                                <div class="form-group col-xs-6">
                                    <span style="color: red;font-size: 25px;position:relative; top:7px;margin-right: 10px">*</span>
                                    <label >客户秘钥</label><span style="color: red;" id="add_secret_alert"></span>
                                    <button type="button" class="btn btn-success add_submit_btn" data-dismiss="modal" onclick="getSecret();">获取</button>
                                    <input type="text" id="add_secret" class="form-control" />
                                </div>
                            </div>

                            <div class="row">
                                <div class="form-group col-xs-6" id="add_show_wx">
                                    <span style="color: red;font-size: 25px;position:relative; top:7px;margin-right: 10px">*</span>
                                    <label >公众微信号</label><span style="color: red;" id="add_wx_alert"></span>
                                    <input type="text" id="add_wx" class="form-control" />
                                </div>
                                <div class="form-group col-xs-6">
                                    <span style="color: red;font-size: 25px;position:relative; top:7px;margin-right: 10px">*</span>
                                    <label>客户名称</label><span style="color: red;" id="add_name_alert"></span>
                                    <input type="text" id="add_name" class="form-control"/>
                                </div>
                            </div>
                            <div class="row" id="add_show_token_key">
                                <div class="form-group col-xs-6">
                                    <label >token</label>
                                    <input type="text" id="add_token" class="form-control" />
                                </div>
                                <div class="form-group col-xs-6">
                                    <label>AESKey</label>
                                    <input type="text" id="add_aeskey" class="form-control"/>
                                </div>
                            </div>
                            <div class="row"  id="add_show_appid">
                                <div class="form-group col-xs-6">
                                    <label>appid</label>
                                    <input type="text" id="add_appid" class="form-control"/>
                                </div>
                                <div class="form-group col-xs-6">
                                        <label>appsecret</label>
                                        <input type="text" id="add_appsecret" class="form-control"/>
                                </div>
                            </div>
                            <br>
                            <div class="row">
                                <div  class="form-group col-xs-6">
                                    <span style="display: inline;">
                                        <img src="/client/img/plz_upload_img.png" class="user_photo" id="addthumb2" style="width: 80px;height: 80px">
                                        <button class="btn btn-success btn-sm" mode="button-upload-pic" uploadtype="image"
                                                id = "updateuploadThumb"
                                                action="/c/p/picture/upload" filesizelimit="128000"
                                                filetypelimit="jpg,png,gif,jpeg" filenums="photo">上传图片
                                        </button>
                                        <img class="loadingPic"  obj="user_photo"  alt="" src="/client/img/loading.gif" style="width: 10px;height: 10px" />
                                        <input type="hidden" id="add_customerImg" name="img" obj="user_photo" />
                                    </span>
                                </div>
                                <div class="form-group col-xs-6"  id="add_show_sendType">
                                    <label>消息加密方式:</label>
                                    <select id="add_customerSendtype" style="width: 150px;height: 30px" >
                                        <option  value="">请选择</option>
                                        <option  value="0">明文模式</option>
                                        <option  value="1">兼容模式</option>
                                        <option  value="2">安全模式</option>
                                    </select>
                                </div>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-default auto-focus-btn" data-dismiss="modal" onclick="closeDiv();">取消</button>
                                <button type="submit" id="saveCustom"  class="btn btn-success add_submit_btn">保存</button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
                <%--添加隐藏框 end --%>

                        <%--修改隐藏框 start --%>
                    <div id="updateDiv" style="display : none;margin-top: 50px; position: absolute;top: 50px;left: 45%;">
                        <div class="modal-dialog">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <button type="button" class="close"  data-dismiss="modal" aria-label="Close"><span
                                            aria-hidden="true">&times;</span></button>
                                    <h4 class="modal-title">修改客户信息</h4>
                                </div>
                                <div class="modal-body">
                                    <div class="row">
                                        <div class="form-group col-xs-6">
                                            <span style="color: red;font-size: 25px;position:relative; top:7px;margin-right: 10px">*</span>
                                            <label >选择类型</label><span style="color: red;"></span>
                                            <select id="update_type" disabled style="width: 175px;height: 30px;">
                                                <option value="1">公众号</option>
                                                <option value="0">普通商户</option>
                                            </select>
                                        </div>
                                        <div class="form-group col-xs-6">
                                            <span style="color: red;font-size: 25px;position:relative; top:7px;margin-right: 10px">*</span>
                                            <label >客户秘钥</label><span style="color: red;" id="update_secret_alert"></span>
                                            <button type="button" class="btn btn-success add_submit_btn" data-dismiss="modal" onclick="getSecret();">修改</button>
                                            <input type="text" id="update_secret" class="form-control" />
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="form-group col-xs-6" id="update_show_wx">
                                            <span style="color: red;font-size: 25px;position:relative; top:7px;margin-right: 10px">*</span>
                                            <label >公众微信号</label><span style="color: red;" id="update_wx_alert"></span>
                                            <input type="text" readonly id="update_wx" class="form-control" />
                                        </div>
                                        <div class="form-group col-xs-6">
                                            <span style="color: red;font-size: 25px;position:relative; top:7px;margin-right: 10px">*</span>
                                            <label>客户名称</label><span style="color: red;" id="update_name_alert"></span>
                                            <input type="text" id="update_name" class="form-control"/>
                                        </div>
                                    </div>
                                    <div class="row" id="update_show_token_aeskey">
                                        <div class="form-group col-xs-6">
                                            <label >token</label>
                                            <input type="text" id="update_token" class="form-control" />
                                        </div>
                                        <div class="form-group col-xs-6">
                                            <label>AESKey</label>
                                            <input type="text" id="update_aeskey" class="form-control"/>
                                        </div>
                                    </div>
                                    <div class="row" id="update_show_appid">
                                        <div class="form-group col-xs-6">
                                            <label>appid</label>
                                            <input type="text" id="update_appid" class="form-control"/>
                                        </div>
                                        <div class="form-group col-xs-6">
                                            <label>appsecret</label>
                                            <input type="text" id="update_appsecret" class="form-control"/>
                                        </div>
                                    </div>
                                    <br>
                                    <div class="row">
                                        <div  class="form-group col-xs-6">
                                            <span style="display: inline;">
                                                <img src="/client/img/plz_upload_img.png" class="user_photo" id="updatethumb2" style="width: 80px;height: 80px">
                                                <button class="btn btn-success btn-sm" mode="button-upload-pic" uploadtype="image"
                                                        id = "uploadThumb"
                                                        action="/c/p/picture/upload" filesizelimit="128000"
                                                        filetypelimit="jpg,png,gif,jpeg" filenums="photo">上传图片
                                                </button>
                                                <img class="loadingPic"  obj="user_photo"  alt="" src="/client/img/loading.gif" />
                                                <input type="hidden" id="update_customerImg" name="img" obj="user_photo" />
                                            </span>
                                        </div>
                                        <div class="form-group col-xs-6"  id="update_show_sendType">
                                            <label>消息加密方式:</label>
                                            <select id="update_customerSendtype" style="width: 150px;height: 30px" >
                                                <option  value="">请选择</option>
                                                <option  value="0">明文模式</option>
                                                <option  value="1">兼容模式</option>
                                                <option  value="2">安全模式</option>
                                            </select>
                                        </div>
                                    </div>
                                    <br>
                                    <div class="row" id="update_mode_div">
                                        <div class="form-group col-xs-6">
                                            <label>模式切换:</label>
                                            <select id="update_mode" style="width: 150px;height: 30px" >
                                                <option  value="0">普通模式</option>
                                                <option  value="1">开发模式</option>
                                            </select>
                                        </div>
                                    </div>

                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-default auto-focus-btn" data-dismiss="modal" onclick="closeDiv();">取消</button>
                                        <button type="submit" id="updateCustom"  class="btn btn-success add_submit_btn">保存</button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                        <%--修改隐藏框 end --%>
        </div>
        <%--主内容显示区 end --%>
    </tiles:putAttribute>
</tiles:insertTemplate>
