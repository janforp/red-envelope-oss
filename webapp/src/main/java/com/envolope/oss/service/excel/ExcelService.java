package com.envolope.oss.service.excel;

import com.alibaba.fastjson.JSON;
import com.envolope.oss.dao.ReRecommendMissionDAO;
import com.envolope.oss.dao.ReRecommendMissionRequireDAO;
import com.envolope.oss.dao.ReRecommendTaskDAO;
import com.envolope.oss.model.ReRecommendMission;
import com.envolope.oss.model.ReRecommendMissionRequire;
import com.envolope.oss.model.ReRecommendTask;
import com.envolope.oss.model.vo.recommend.UserCommitLabelVo;
import com.envolope.oss.util.StringUtil;
import com.envolope.oss.util.el.ElBase;
import jxl.write.*;
import org.apache.poi.hssf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Jan on 16/11/9.
 * java---excel
 */
@Service
public class ExcelService {
    @Autowired
    private ReRecommendMissionRequireDAO reRecommendMissionRequireDAO;
    @Autowired
    private ReRecommendMissionDAO reRecommendMissionDAO;
    @Autowired
    private ReRecommendTaskDAO reRecommendTaskDAO;

    /**
     * 取得生成的excel的所有内容及信息
     *
     * @param request
     * @param missionId
     * @return
     */
    public String exportToExcel(HttpServletRequest request,
                                HttpServletResponse response,
                                Long missionId,
                                String startDate,
                                String endDate,
                                Integer status) throws WriteException, InstantiationException, IllegalAccessException, IOException {

        ReRecommendMission mission = reRecommendMissionDAO.selectByPrimaryKey(missionId);
        //找到改任务对应的所有提交过的task(包括:审核中,已通过,未通过的)
        List<ReRecommendTask> tasks = reRecommendTaskDAO.getSubmitTasksByMissionId(missionId, startDate, endDate,status);
        if (tasks == null) {
            tasks = new ArrayList<>(0);
        }
        String tableName = mission.getMissionTitle();
        String verifyRequire = mission.getVerifyRequire();
        List<ReRecommendMissionRequire> requires = JSON.parseArray(verifyRequire, ReRecommendMissionRequire.class);
        List<String> tableHeaders = new ArrayList<>(requires.size());
        for (ReRecommendMissionRequire require : requires) {
            tableHeaders.add(require.getRequireName());
        }
        Map<Integer, Object> data = new HashMap<>();
        int i = 0;
        for (ReRecommendTask task : tasks) {
            Integer taskStatus = task.getTaskStatus();
            String strStatus = "";
            if (taskStatus == 0){
                strStatus = "进行中";
            }else if (taskStatus == 1){
                strStatus = "审核中";
            }else if (taskStatus == 2){
                strStatus = "审核通过";
            }else if (taskStatus == 3){
                strStatus = "未通过";
            }else if (taskStatus == 4){
                strStatus = "已过期";
            }

            String commitRequire = task.getCommitRequire();
            List<UserCommitLabelVo> labelVos = new ArrayList<>();
            if (!StringUtil.isEmpty(commitRequire)) {
                List<ReRecommendMissionRequire> allRequires = JSON.parseArray(commitRequire, ReRecommendMissionRequire.class);
                for (ReRecommendMissionRequire require : allRequires) {
                    UserCommitLabelVo labelVo = new UserCommitLabelVo();
                    Long requireId = require.getRequireId();
                    String requireName = reRecommendMissionRequireDAO.selectByPrimaryKey(requireId).getRequireName();
                    labelVo.setRequireContent(require.getRequireName());
                    labelVo.setRequireName(requireName);
                    labelVo.setSubmitTime(ElBase.fmtTime(task.getUpdateTime()));
                    labelVo.setStatus(strStatus);
                    labelVos.add(labelVo);
                }
            }
            data.put(i++, labelVos);
        }
        return createWorkBookByPOI(request,response,tableName,tableHeaders,data);
    }

    /**
     * 生成需要写入的excel 数据
     *
     * @param request
     * @param tableName    文件名
     * @param tableHeaders 表头
     * @param data         内容
     * @return
     */
    public String createWorkBookByPOI(HttpServletRequest request,
                                      HttpServletResponse response,
                                      String tableName,
                                      List<String> tableHeaders,
                                      Map<Integer, Object> data) throws IOException, WriteException, IllegalAccessException, InstantiationException {

        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet = wb.createSheet(tableName);
        HSSFCellStyle style = wb.createCellStyle();
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        //表头
        HSSFRow headRow = sheet.createRow(0);
        for (int i = 0; i < tableHeaders.size(); i++) {
            HSSFCell cell = headRow.createCell(i);
            cell.setCellValue(tableHeaders.get(i));
            cell.setCellStyle(style);
        }
        HSSFCell cell = headRow.createCell(tableHeaders.size());
        cell.setCellValue("提交日期");
        cell.setCellStyle(style);
        HSSFCell cell1 = headRow.createCell(tableHeaders.size()+1);
        cell1.setCellValue("状态");
        cell1.setCellStyle(style);
        //内容
        for (int i = 0; i < data.size(); i++) {

            HSSFRow contentRow = sheet.createRow(i + 1);
            Object column = data.get(i);
            if (column instanceof List) {    //每一行是一个List(0,List<>)
                String submitTime = "";
                String status = "";
                int totalColumn = ((List) column).size();
                for (int j = 0; j < ((List) column).size(); j++) {
                    Object oneBox = ((List) column).get(j);//List的每个元素是UserCommitLabelVo,内容是requireContent
                    if (oneBox instanceof UserCommitLabelVo) {
                        String content = ((UserCommitLabelVo) oneBox).getRequireContent();
                        submitTime = ((UserCommitLabelVo) oneBox).getSubmitTime();
                        contentRow.createCell(j).setCellValue(content);
                        status = ((UserCommitLabelVo) oneBox).getStatus();
                    }
                }
                contentRow.createCell(totalColumn).setCellValue(submitTime);
                contentRow.createCell(totalColumn+1).setCellValue(status);
            }
        }
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        wb.write(os);
        byte[] content = os.toByteArray();
        InputStream is = new ByteArrayInputStream(content);
        response.reset();
        response.setContentType("application/vnd.ms-excel;charset=utf-8");
        response.setHeader("Content-Disposition", "attachment;filename=" + new String((tableName + ".xls").getBytes(), "iso-8859-1"));
        ServletOutputStream out = response.getOutputStream();
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        try {
            bis = new BufferedInputStream(is);
            bos = new BufferedOutputStream(out);
            byte[] buff = new byte[2048];
            int bytesRead;
            while (-1 != (bytesRead = bis.read(buff,0,buff.length))){
                bos.write(buff,0,bytesRead);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if (bis != null){
                bis.close();
            }
            if (bos != null){
                bos.close();
            }
        }
        return null;
    }
}
