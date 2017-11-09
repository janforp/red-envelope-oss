<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page pageEncoding="UTF-8" %>
<tiles:insertTemplate template="/WEB-INF/views/base/layout/layout-default.jsp">
    <tiles:putAttribute name="title" value="实时数据"/>
    <tiles:putAttribute name="enableConsole" value="true"/>
    <tiles:putAttribute name="enableSidebar" value="true"/>
    <tiles:putAttribute name="styleArea">
        <link rel="stylesheet" type="text/css" href="/client/css/template/base.css">
    </tiles:putAttribute>
    <tiles:putAttribute name="scriptArea">
        <script src="/plugins/jquery/jquery-1.9.1.min.js"></script>

        <link rel="stylesheet" type="text/css" href="/plugins/jqPlot-master/src/jquery.jqplot.css" />
        <script language="javascript" type="text/javascript" src="/plugins/jqPlot-master/src/jquery.jqplot.js"></script>
        <script language="javascript" type="text/javascript" src="/plugins/jqPlot-master/src/plugins/jqplot.logAxisRenderer.js"></script>
        <script src="/plugins/jqPlot-master/src/excanvas.js"></script>
        <script src="/plugins/jqPlot-master/src/plugins/jqplot.barRenderer.js"></script>
        <script src="/plugins/jqPlot-master/src/plugins/jqplot.pointLabels.js"></script>
        <script src="/plugins/jqPlot-master/src/plugins/jqplot.canvasAxisTickRenderer.js"></script>
        <script src="/plugins/jqPlot-master/src/plugins/jqplot.cursor.js"></script>
        <script src="/plugins/jqPlot-master/src/plugins/jqplot.highlighter.js"></script>
        <script src="/plugins/jqPlot-master/src/plugins/jqplot.dateAxisRenderer.js"></script>
        <script src="/plugins/jqPlot-master/src/plugins/jqplot.canvasTextRenderer.js"></script>
        <script src="/plugins/jqPlot-master/src/plugins/jqplot.categoryAxisRenderer.js"></script>

        <%--<script src="/methods/userManager/data_statistics.js"></script>--%>
        <style>
            .active{
                background-color: #EFEFEF;
            }
            .selectFunDiv{
                display: flex;width: 100%;height: 100px;
            }
            .selectFunDiv a{
                display: inline-block;height: 100px;width: 25%;border: 1px solid #EFEFEF;text-align: center;cursor: pointer;text-decoration: none;
            }
            .selectFunSpan{
               display: block;margin-top:20px;font-size:30px;
            }
            .scope{
                display: inline-block;padding: 0.5rem 1rem;margin: 0 1rem;width: 100px;text-align: center;text-decoration: none;color: white;
            }
            .today{
                background: blue;
            }
            .yesterday{
                background: forestgreen;
            }
            .week{
                background: indianred;
            }
            .month{
                background: darkcyan;
            }
        </style>
        <script>
            $.jqplot('chartDiv', [[${data.jqPlotArray}]] , {
                        title:{
                            show:false
                        },
                        axes:{
                            yaxis:{
                                tickInterval: 5,
                                min:0
                            },
                            xaxis:{
                                max:24,
                                min:0,
                                tickInterval: 1
                            }
                        },
                        highlighter: {
                            show: true,
                            sizeAdjust: 10,  // 当鼠标移动到数据点上时，数据点扩大的增量
                            fadeTooltip: true,// 设置提示信息栏出现和消失的方式（是否淡入淡出）
                            lineWidthAdjust: 2,   //当鼠标移动到放大的数据点上时，设置增大的数据点的宽度
                            tooltipOffset: 5,       // 提示信息栏据被高亮显示的数据点的偏移位置，以像素计
                            tooltipLocation: 'nw' // 提示信息显示位置（英文方向的首写字母）: n, ne, e, se, s, sw, w, nw.
                        },
            });
        </script>
        <script>
            var plot;
            function getRegisterString(type) {

                plot = $.jqplot('chartDiv', [] , {
                    title:{
                        show:false
                    },
                    axes:{
                        yaxis:{
                            tickInterval: 5,
                            min:0
                        },
                        xaxis:{
                            max:24,
                            min:0,
                            tickInterval: 1
                        }
                    },
                    dataRenderer: myRenderer,
                    highlighter: {
                        show: true,
                        sizeAdjust: 10,  // 当鼠标移动到数据点上时，数据点扩大的增量
                        fadeTooltip: true,// 设置提示信息栏出现和消失的方式（是否淡入淡出）
                        lineWidthAdjust: 2,   //当鼠标移动到放大的数据点上时，设置增大的数据点的宽度
                        tooltipOffset: 5,       // 提示信息栏据被高亮显示的数据点的偏移位置，以像素计
                        tooltipLocation: 'nw' // 提示信息显示位置（英文方向的首写字母）: n, ne, e, se, s, sw, w, nw.
                    },
                });
                plot.replot({
                    resetAxes : true
                });
            };


            function yourRenderer() {
                var data = [[]];
                for (var i=1; i<24; i+=1) {
                    data[0].push([i, Math.round(Math.random()*(40-1)+1)]);
                }
                return data;
            }

            function myRenderer() {
                var data = [[]];
                $.ajax({
                    url: '/c/page/console/auth/register/getChart',
                    type:'post',
                    dataType: 'json',
                    data:{type:1,date:getDate(new Date())},
                    success:    function (data) {

                        var vos = data.bean.vos;
                        for(var i=1;i<24;i++){
                            data[0].push([i, vos[i].y]);
                        }
                    }
                });
                alert(data)
                return data;
            }

            function getDate(date) {
                var year = date.getFullYear();
                var month = date.getMonth()+1;
                month = month<10?'0'+month:month;
                var day = date.getDate();
                if(day<10){
                    day ="0"+day
                }
                var now = year+"-"+month+"-"+day
                return now;
            };

        </script>
    </tiles:putAttribute>
    <tiles:putAttribute name="mainArea">
        <!-- 顶部栏 begin -->
        <div class="panel panel-default main-hd">
            <div class="panel-heading">
                <span class="pageTitle">用户数据</span>
            </div>
        </div>
        <!-- 顶部栏 end -->

        <%--主内容显示区 begin--%>
        <div class="main-bd" style="margin: 20px;border: 1px solid black;">

            <div class="selectFunDiv">
                <a href="javaScript:getRegisterString(1);" id="register" class="active" type="1">
                    <div class=" functionDiv" type="1">
                        <span class="selectFunSpan" type="1">注册用户(今日)</span>
                    </div>
                </a>
                <a href="javaScript:getActiveString(2);" id="active" type="2">
                    <div class=" functionDiv" type="2">
                        <span class="selectFunSpan" type="2">活跃用户(今日)</span>
                    </div>
                </a>
                <a href="javaScript:getUsefulString(3);" id="useful" type="3">
                    <div class=" functionDiv" type="3">
                        <span class="selectFunSpan" type="3">有效用户(今日)</span>
                    </div>
                </a>
                <a href="javaScript:getLoyaltyString(4);" id="loyalty" type="4">
                    <div class=" functionDiv" type="4">
                        <span class="selectFunSpan" type="4">忠实用户(今日)</span>
                    </div>
                </a>
            </div>

            <div id="chartDiv" style="height:800px;width:100%;margin-top:1rem"></div>

            <div style="display: flex;width: 100%;justify-content: center">
                <a class="scope today">今日</a>
                <a class="scope yesterday">昨日</a>
                <a class="scope week">7天前</a>
                <a class="scope month">30天前</a>
            </div>

        </div>
        <%--主内容显示区 end --%>
    </tiles:putAttribute>
</tiles:insertTemplate>
