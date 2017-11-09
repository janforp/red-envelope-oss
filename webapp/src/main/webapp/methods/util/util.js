/**
 * created by Jan on 2016/11
 */

+function (win, $) {
    var utils = {
        toastSelector: "#global-toast",
        loadingToastSelector: "#global-toast-loading",
        $toast: null,
        $loadingToast: null,
        defaultTimeout: 2000,
        tipsIntervalId: null,

        /**
         * 初始化方法
         */
        init: function () {

        },
        /**
         * 生成随机码
         * @param length    长度
         * @param element   生成码所放的输入框的ID
         */
        createCode: function (length,element) {
            var input = $("#"+element);
            var code = "";
            for (var i =0 ; i<length ;i++) {
                var random = Math.round(Math.random()*(this.arr.length - 1));
                code += this.arr[random];
            }
            $(input).val(code);
        },
        /**
         * 倒计时方法
         * @param leftTime      剩余时间戳
         * @param element       需要限时倒计时的span的ID
         * @param endCallback   倒计时结束时的回调
         */
        countdown: function (leftTime,element,endCallback) {

            var interval = setInterval(function () {

                if (leftTime <= 1000){
                    $("#"+element).text("00:00");
                    clearInterval(interval);
                    endCallback;
                    return;
                }
                leftTime = leftTime - 1000;
                var min = Math.floor(leftTime/1000/60%60);
                var sec = Math.floor(leftTime/1000%60);
                if (min<10){
                    min = "0"+min;
                }
                if (sec<10){
                    sec = "0"+sec;
                }
                var countDownTime = min+":"+sec;
                $("#"+element).text(countDownTime);
            },1000);
        },
        
        /**
         * 一个公共的数组
         */
        arr : [ '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j',
                'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't',
                'u', 'v', 'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D',
                'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N',
                'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X',
                'Y', 'Z']
    };
    win.utils = utils;

    // 以下内容是document ready以后
    $(function () {
        // document ready
        utils.init();
    });

}(window, $);