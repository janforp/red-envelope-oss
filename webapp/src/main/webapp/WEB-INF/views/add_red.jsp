<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page pageEncoding="UTF-8" %>
<tiles:insertTemplate template="/WEB-INF/views/base/layout/layout-default.jsp">
    <tiles:putAttribute name="title" value="添加红包"/>
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
        </style>
    </tiles:putAttribute>
    <tiles:putAttribute name="scriptArea">
    <script src="/methods/add_red.js" type="text/javascript"></script>
    <script>
        function addRed(){


            var num = $("#num").val();
            if (parseInt(num) != num) {
                tips.err("红包数需填整数");
                return;
            }
            var big = $("#big").val();
            //需要正则表达式判断
            var extendId = "${vo.id}";

            if(extendId == null || extendId.trim().length == 0){
                tips.err("推广或该客户不存在",2000);
                return;
            }

            $.ajax({
                url     :       "/c/page/console/auth/red/addRed",
                type    :       "POST",
                dataType:       "JSON",
                data    :       {

                    num:num,
                    bigEnvelope:big,
                    id:extendId
                },
                success :       function (data) {

                    if (data.success) {
                        tips.suc(data.msg,2000);
                    }else {
                        tips.suc(data.msg,2000);
                    }
                },
                error   :       function () {

                }
            });
        }
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
                </div>
                <span>添加红包</span>
            </div>
        </div>
        <!-- 顶部栏 end -->
        <div class="panel panel-default main-hd">
            <div class="panel-heading">
                <div  class="fr mt-4">
                    <div class="fr pr5">
                        <div class="input-group input-group-sm w100 fl">
                        </div>
                    </div>
                </div>
                <span style="margin-right: 100px;font-size: large;">id:${vo.id}</span>
                <span style="margin-right: 100px;font-size: large;">名称:${vo.wxName}</span>
            </div>
        </div>
        <%--主内容显示区 begin --%>
        <div class="main-bd">
            <div class="separator_line">
            </div>
            <br>


            <div class="collapse navbar-collapse">
                <span>
                    <label style="margin-right: 40px;color: blueviolet"> 初始随机红包个数:</label>
                     <input type="text" id="num" />
                     <span style="color: red;margin-left: 10px;">需填写整数,若填写为 100.0 则不合法</span>
                </span>
            </div>
            <br>
            <div class="collapse navbar-collapse">
                <span>
                    <label style="margin-right: 90px;color: blueviolet"> 大金额红包:</label>
                    <input type="text" id="big" />
                    <span style="color: red;margin-left: 10px;">如:888|666|555,中间用<span style="color: black;font-size: larger">英文</span>状态下的 ' | ' 分开</span>
                </span>
            </div>
            <br>

            <div class="input-group input-group-sm w100 fl">
                <button class="btn btn-success" id ="save" onclick="addRed();" style="width: 90px">保存</button>
            </div>
        </div>
        <%--主内容显示区 end --%>
    </tiles:putAttribute>
</tiles:insertTemplate>
