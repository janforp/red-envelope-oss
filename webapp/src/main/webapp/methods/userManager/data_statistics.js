

var url = '/c/page/console/auth/register/getChart';

/**
 * 去服务器拿到数据
 * @param type
 * @param date
 */
function getRegisterString(type) {

    $(".selectFunDiv a").removeClass('active');
    $("#register").addClass('active');
    var date = getDate(new Date());

    ajaxDataRenderer(type,date);


    doAjax();
};








/**
 * 活跃用户
 * @param type
 */
function getActiveString(type) {

    $(".selectFunDiv a").removeClass('active');
    $("#active").addClass('active');
    var date = getDate(new Date());

    doAjax(type,date);
};






/**
 * 有效用户
 * @param type
 */
function getUsefulString(type) {
    $(".selectFunDiv a").removeClass('active');
    $("#useful").addClass('active');
    var date = getDate(new Date());

    doAjax(type,date);
};
/**
 * 忠实用户
 * @param type
 */
function getLoyaltyString(type) {
    $(".selectFunDiv a").removeClass('active');
    $("#loyalty").addClass('active');
    var date = getDate(new Date());

    doAjax(type,date);
};

/**
 * 获取某个DATE对象对应的年月日字符串
 * @param date
 * @returns {string}
 */
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

/**
 * 去服务器获取相应类型的数据
 * @param type
 * @param date
 */
function doAjax() {

    var plot = $.jqplot('chartDiv',[], {
        title:{
            show:false
        },
        dataRenderer: ajaxDataRenderer,
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
    plot.replot({
        resetAxes : true
    });
};

/**
 *
 */
function ajaxDataRenderer(type,date) {

    var ret = [[]];
    $.ajax({
        url: url,
        dataType: 'json',
        data:{type:type,date:date},
        success:    function (data) {

                    // ret = data.bean.jqPlotArray;
            ret.push(json);
                }
    });
    return ret;
};