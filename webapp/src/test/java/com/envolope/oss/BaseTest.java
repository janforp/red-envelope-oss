package com.envolope.oss;

import com.alibaba.fastjson.JSON;
import com.envolope.oss.model.vo.ResultInfo;
import jxl.Workbook;
import jxl.format.UnderlineStyle;
import jxl.write.*;
import jxl.write.Boolean;
import jxl.write.Label;
import jxl.write.Number;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.GetMethod;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.util.Calendar;
import java.util.Date;

/**
 * @author wwg
 *         测试基类
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
        "classpath*:/spring/spring-profile.xml"})
@ActiveProfiles(profiles = "development")
public class BaseTest {

    @Test
    public void testJsoup () throws IOException {

        Document document = Jsoup.parse(new URL("http://item.jd.com/1757283820.html"),5000);
        Element bod = document.getElementById("name");
        String name = bod.text();
        System.out.println("<<<<<<"+name);
    }


    @Test
    public void testGet() {

        HttpClient httpClient = new HttpClient();

        GetMethod getMethod = new GetMethod("http://127.0.0.1:8080/c/p/rebuild/rebuild");

        try{
            httpClient.executeMethod(getMethod);//发送请求

            byte[] responseBody = getMethod.getResponseBody();// 读取为字节数组
            String response = new String(responseBody, "utf-8");
            System.out.println("----------response:" + response);

            ResultInfo result = JSON.parseObject(response, ResultInfo.class);
            System.out.println("----------result:" + result.getCode());


        }catch (HttpException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }finally{
            getMethod.releaseConnection();//关闭连接
        }
    }

    @Test
    public void createExcel() throws WriteException,IOException{

        File file = new File("/Users/wujie5/Desktop/123/excel.xls");
        file.createNewFile();
        OutputStream os = new FileOutputStream(file);
        //创建工作薄
        WritableWorkbook workbook = Workbook.createWorkbook(os);
        //创建新的一页
        WritableSheet sheet = workbook.createSheet("First Sheet",0);
        //创建要显示的内容,创建一个单元格，第一个参数为列坐标，第二个参数为行坐标，第三个参数为内容
        Label xuexiao = new Label(0,0,"学校");
        sheet.addCell(xuexiao);
        Label zhuanye = new Label(1,0,"专业");
        sheet.addCell(zhuanye);
        Label jingzhengli = new Label(2,0,"专业竞争力");
        sheet.addCell(jingzhengli);

        Label qinghua = new Label(0,1,"清华大学");
        sheet.addCell(qinghua);
        Label jisuanji = new Label(1,1,"计算机专业");
        sheet.addCell(jisuanji);
        Label gao = new Label(2,1,"高");
        sheet.addCell(gao);

        Label beida = new Label(0,2,"北京大学");
        sheet.addCell(beida);
        Label falv = new Label(1,2,"法律专业");
        sheet.addCell(falv);
        Label zhong = new Label(2,2,"中");
        sheet.addCell(zhong);

        Label ligong = new Label(0,3,"北京理工大学");
        sheet.addCell(ligong);
        Label hangkong = new Label(1,3,"航空专业");
        sheet.addCell(hangkong);
        Label di = new Label(2,3,"低");
        sheet.addCell(di);

        //把创建的内容写入到输出流中，并关闭输出流
        workbook.write();
        workbook.close();
        os.close();
    }
    @Test
    public void createExcelWithBooleanAndDate() throws WriteException,IOException {

        File file = new File("/Users/wujie5/Desktop/123/excel2.xls");
        file.createNewFile();
        OutputStream os = new FileOutputStream(file);
        //创建工作薄
        WritableWorkbook workbook = Workbook.createWorkbook(os);
        //创建新的一页
        WritableSheet sheet = workbook.createSheet("First Sheet", 0);
        //创建要显示的具体内容
        Label formate = new Label(0,0,"数据格式");
        sheet.addCell(formate);
        Label floats = new Label(1,0,"浮点型");
        sheet.addCell(floats);
        Label integers = new Label(2,0,"整型");
        sheet.addCell(integers);
        Label booleans = new Label(3,0,"布尔型");
        sheet.addCell(booleans);
        Label dates = new Label(4,0,"日期格式");
        sheet.addCell(dates);

        Label example = new Label(0,1,"数据示例");
        sheet.addCell(example);
        //浮点数据
        Number number = new Number(1,1,3.1415926535);
        sheet.addCell(number);
        //整形数据
        Number ints = new Number(2,1,15042699);
        sheet.addCell(ints);
        Boolean bools = new Boolean(3,1,true);
        sheet.addCell(bools);
        //日期型数据
        Calendar c = Calendar.getInstance();
        Date date = c.getTime();
        WritableCellFormat cf1 = new WritableCellFormat(DateFormats.FORMAT1);
        DateTime dt = new DateTime(4,1,date,cf1);
        sheet.addCell(dt);
        //把创建的内容写入到输出流中，并关闭输出流
        workbook.write();
        workbook.close();
        os.close();
    }

    @Test
    public void createExcelWithMergeCells() throws WriteException,IOException {

        File file = new File("/Users/wujie5/Desktop/123/excel3.xls");
        file.createNewFile();
        OutputStream os = new FileOutputStream(file);
        //创建工作薄
        WritableWorkbook workbook = Workbook.createWorkbook(os);
        //创建新的一页
        WritableSheet sheet = workbook.createSheet("First Sheet", 0);
        //构造表头
        sheet.mergeCells(0, 0, 4, 0);//添加合并单元格，第一个参数是起始列，第二个参数是起始行，第三个参数是终止列，第四个参数是终止行
        WritableFont bold = new WritableFont(WritableFont.ARIAL,10,WritableFont.BOLD);//设置字体种类和黑体显示,字体为Arial,字号大小为10,采用黑体显示
        WritableCellFormat titleFormate = new WritableCellFormat(bold);//生成一个单元格样式控制对象
        titleFormate.setAlignment(jxl.format.Alignment.CENTRE);//单元格中的内容水平方向居中
        titleFormate.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);//单元格的内容垂直方向居中
        Label title = new Label(0,0,"JExcelApi支持数据类型详细说明",titleFormate);
        sheet.setRowView(0, 600, false);//设置第一行的高度
        sheet.addCell(title);

        //创建要显示的具体内容
        WritableFont color = new WritableFont(WritableFont.ARIAL);//选择字体
        color.setColour(Colour.GOLD);//设置字体颜色为金黄色
        WritableCellFormat colorFormat = new WritableCellFormat(color);
        Label formate = new Label(0,1,"数据格式",colorFormat);
        sheet.addCell(formate);
        Label floats = new Label(1,1,"浮点型");
        sheet.addCell(floats);
        Label integers = new Label(2,1,"整型");
        sheet.addCell(integers);
        Label booleans = new Label(3,1,"布尔型");
        sheet.addCell(booleans);
        Label dates = new Label(4,1,"日期格式");
        sheet.addCell(dates);

        Label example = new Label(0,2,"数据示例",colorFormat);
        sheet.addCell(example);
        //浮点数据
        //设置下划线
        WritableFont underline= new WritableFont(WritableFont.ARIAL,WritableFont.DEFAULT_POINT_SIZE,WritableFont.NO_BOLD,false, UnderlineStyle.SINGLE);
        WritableCellFormat greyBackground = new WritableCellFormat(underline);
        greyBackground.setBackground(Colour.GRAY_25);//设置背景颜色为灰色
        Number number = new Number(1,2,3.1415926535,greyBackground);
        sheet.addCell(number);
        //整形数据
        WritableFont boldNumber = new WritableFont(WritableFont.ARIAL,10,WritableFont.BOLD);//黑体
        WritableCellFormat boldNumberFormate = new WritableCellFormat(boldNumber);
        Number ints = new Number(2,2,15042699,boldNumberFormate);
        sheet.addCell(ints);
        //布尔型数据
        Boolean bools = new Boolean(3,2,true);
        sheet.addCell(bools);
        //日期型数据
        //设置黑体和下划线
        WritableFont boldDate = new WritableFont(WritableFont.ARIAL,WritableFont.DEFAULT_POINT_SIZE,WritableFont.BOLD,false,UnderlineStyle.SINGLE);
        WritableCellFormat boldDateFormate = new WritableCellFormat(boldDate,DateFormats.FORMAT1);
        Calendar c = Calendar.getInstance();
        Date date = c.getTime();
        DateTime dt = new DateTime(4,2,date,boldDateFormate);
        sheet.addCell(dt);
        //把创建的内容写入到输出流中，并关闭输出流
        workbook.write();
        workbook.close();
        os.close();
    }

    @Test
    public void line(){

    }



}
