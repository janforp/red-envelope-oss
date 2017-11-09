<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
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

    <script>
        //一个生成随机数据的方法
        function yourRenderer() {
            var data = [[]];
            for (var i=0; i<13; i+=0.5) {
                data[0].push([i, Math.round(Math.random()*(40-1)+1)]);
            }
            return data;
        }

        //
        function test(){
            //设置dataRenderer属性为yourRenderer函数
            plot3c = $.jqplot('chart3c',[],{
                title: '随机生成的数据',
                legend:{
                    show: true,
                    location: 'nw'
                },
                dataRenderer: yourRenderer
            });
            //重绘图表
            plot3c.replot({
                resetAxes : true
            });
        }

        $(function(){
            //调用显示动态数据加载
            setInterval("test()",5000);
        });
    </script>
</head>
<body>
<div id="chart3c" style="height:800px;width:100%;margin-top:1rem"></div>
</body>
</html>
