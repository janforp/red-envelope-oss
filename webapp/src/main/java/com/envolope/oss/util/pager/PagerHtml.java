package com.envolope.oss.util.pager;

/**
 * Created by Summer on 2016/11/9.
 */
public class PagerHtml {

    public static String buildHtml(int pageCount, int pageNo) {

        /**
         * 控件大小：lg/""(默认大小)/sm/xs
         */
        String cssSize = "sm";
        String cssFloat = "left";


        if (pageCount <= 0) {
            pageCount = 1;
        }

        String realCssSize = cssSize.trim();

        StringBuilder bf = new StringBuilder(1000);
        bf.append("<div class=\"pagination-box");
        bf.append(" pagination-").append(cssFloat.trim());
        bf.append("\">");
        bf.append("<ul class=\"pagination");
        if (realCssSize != null) {
            bf.append(" pagination-").append(realCssSize);
        }
        bf.append("\">");

        if (pageNo <= 1) {
            // 第一页（没有前一页）
            bf.append("<li class=\"disabled\"><a href=\"javascript:void(0);\" onclick=\"return false;\">")
                    .append("<i class=\"glyphicon glyphicon-chevron-left\"></i></a></li>");
        } else {
            bf.append("<li><a href=\"")
                    .append("javascript:turnPage(")
                    .append(pageNo - 1)
                    .append("); ")
                    .append("\">")
                    .append("<i class=\"glyphicon glyphicon-chevron-left\"></i></a></li>");
        }
        bf.append("<li><span class=\"pagination-status\">")
                .append(pageNo).append("/").append(pageCount)
                .append("</span></li>");
        if (pageNo >= pageCount) {
            // 已经在最后一页了
            bf.append("<li class=\"disabled\"><a href=\"javascript:void(0);\" onclick=\"return false;\">")
                    .append("<i class=\"glyphicon glyphicon-chevron-right\"></i></a></li>");
        } else {
            bf.append("<li><a href=\"")
                    .append("javascript:turnPage(")
                    .append(pageNo + 1)
                    .append("); ")
                    .append("\">")
                    .append("<i class=\"glyphicon glyphicon-chevron-right\"></i></a></li>");
        }
        bf.append("</ul>");// ul.pagination end
        bf.append("&nbsp;");
        bf.append("<div class=\"go-page-box\">");
        bf.append("<div class=\"input-group");
        if (realCssSize != null) {
            bf.append(" input-group-").append(realCssSize);
        }
        bf.append("\">");
        bf.append("<input type=\"text\" onkeydown=\"(function(_this){_this.onkeypress=function(_event){var eve=_event;if(window.event){eve=window.event;}eve=$.event.fix(eve);if(eve.keyCode===13){$(_this).next().find('.btn').click();return false;}}})(this);\" class=\"form-control\"");
        bf.append(" value=\"").append(pageNo).append("\">");
        bf.append("<span class=\"input-group-btn\">");
        bf.append("<button class=\"btn btn-default\" type=\"button\"");
        bf.append(" onclick=\"var $this=$(this);var $numInput=$this.parent().prev();var num=$numInput.val()*1;if((!isNaN(num))&&num>0&&num<=")
                .append(pageCount).append("){ turnPage(num);}else{$numInput.val('');}").append("\"");
        bf.append(">Go</button>");
        bf.append("</span>");
        bf.append("</div>");// div.input-group end
        bf.append("</div>");// div.go-page-box end
        bf.append("</div>");// div.pagination-box end
        return bf.toString();
    }

}
