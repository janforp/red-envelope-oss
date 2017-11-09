package com.envolope.oss.service.redPackageManager;

import com.alibaba.fastjson.JSON;
import com.envolope.oss.dao.*;
import com.envolope.oss.enums.MissionSubtype;
import com.envolope.oss.enums.MissionType;
import com.envolope.oss.model.*;
import com.envolope.oss.model.para.RecommendTaskSelectParamVo;
import com.envolope.oss.model.vo.recommend.TaskListVo;
import com.envolope.oss.model.vo.recommend.UserCommitLabelVo;
import com.envolope.oss.model.vo.recommend.VerifyPageVo;
import com.envolope.oss.util.EntityUtil;
import com.envolope.oss.util.JsonUtil;
import com.envolope.oss.util.RandomUtil;
import com.envolope.oss.util.StringUtil;
import com.envolope.oss.util.el.ElBase;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.*;

/**
 * Created by Jan on 16/11/7.
 * 推荐任务
 */
@Service
public class ReRecommendTaskService {

    @Autowired
    private ReRecommendTaskDAO reRecommendTaskDAO;
    @Autowired
    private ReRecommendMissionDAO reRecommendMissionDAO;
    @Autowired
    private ReUserDAO reUserDAO;
    @Autowired
    private ReAccountDetailDAO reAccountDetailDAO;
    @Autowired
    private ReRecommendMissionRequireDAO reRecommendMissionRequireDAO;

    /**
     * 处理查询参数
     * @return
     */
    public RecommendTaskSelectParamVo handleParamVo(RecommendTaskSelectParamVo paramVo){

        if (StringUtils.isEmpty(paramVo.getPageSize()) || paramVo.getPageSize() == 0){
            paramVo.setPageSize(10);
        }
        if (StringUtils.isEmpty(paramVo.getPageNum()) || paramVo.getPageNum() == 0) {
            paramVo.setPageNum(1);
        }
        String missionTitle = paramVo.getMissionTitle();
        if (StringUtil.isEmpty(missionTitle)){
            paramVo.setMissionTitle(null);
        }
        String userPhone = paramVo.getUserPhone();
        if (StringUtil.isEmpty(userPhone)){
            paramVo.setUserPhone(null);
        }
        Integer isLimitTime = paramVo.getIsLimitTime();
        if (StringUtil.isEmpty(isLimitTime)){
            paramVo.setIsLimitTime(null);
        }
        Integer isVerify = paramVo.getIsVerify();
        if (StringUtil.isEmpty(isVerify)){
            paramVo.setIsVerify(null);
        }
        Integer status = paramVo.getStatus();
        if (StringUtil.isEmpty(status)){
            paramVo.setStatus(null);
        }
        Integer type = paramVo.getType();
        if (StringUtil.isEmpty(type)){
            paramVo.setType(null);
        }
        String startDate = paramVo.getStartDate();
        if (StringUtil.isEmpty(startDate)){
            paramVo.setStartDate(null);
        }
        String endDate = paramVo.getEndDate();
        if (StringUtil.isEmpty(endDate)){
            paramVo.setEndDate(null);
        }

        return paramVo;
    }

    /**
     * 按条件查询  分页
     *
     * @param request
     * @param paramVo   查询参数对象
     * @return
     */
    public List<TaskListVo> doGetList(HttpServletRequest request, RecommendTaskSelectParamVo paramVo) {

        int offset = (paramVo.getPageNum() - 1) * paramVo.getPageSize();
        RowBounds bounds = new RowBounds(offset, paramVo.getPageSize());

        List<ReRecommendTask> missions = reRecommendTaskDAO.getList(paramVo,  bounds);

        List<TaskListVo> taskListVos = new ArrayList<>();

        for (ReRecommendTask vo : missions){

            TaskListVo taskListVo = new TaskListVo();

            taskListVo.setTaskId(vo.getTaskId());
            taskListVo.setStatus(vo.getTaskStatus());
            taskListVo.setTime(ElBase.fmtTime(vo.getUpdateTime()));

            Long missionId = vo.getMissionId();
            ReRecommendMission mission = reRecommendMissionDAO.selectByPrimaryKey(missionId);
            taskListVo.setTitle(mission.getMissionTitle());
            taskListVo.setMoney(mission.getMinMoney().toString());

            Integer userId = vo.getUserId();
            ReUser user = reUserDAO.selectByPrimaryKey(userId);
            taskListVo.setPhone(user.getMobile());

            taskListVo = (TaskListVo) EntityUtil.nullToEmptyString(taskListVo);

            taskListVos.add(taskListVo);
        }

        return taskListVos;
    }

    /**
     * 按条件查询  分页 总数
     *
     * @param request
     * @param paramVo
     * @return
     */
    public Integer getListNum(HttpServletRequest request,RecommendTaskSelectParamVo paramVo) {

        return reRecommendTaskDAO.getListNum(paramVo);
    }

    /**
     * 获取用户提交任务
     * @param taskId
     * @return
     */
    public ReRecommendTask getById(Long taskId) {

        return reRecommendTaskDAO.selectByPrimaryKey(taskId);
    }

    /**
     * 获取任务详情,用于审核页面
     * @param request
     * @param taskId
     * @return
     */
    public VerifyPageVo getVerifyDetail(HttpServletRequest request, Long taskId){

        ReRecommendTask task = getById(taskId);

        VerifyPageVo vo = new VerifyPageVo();
        ReRecommendMission mission = reRecommendMissionDAO.selectByPrimaryKey(task.getMissionId());
        vo.setTaskId(taskId);
        vo.setRemarks(mission.getVerifyText());
        vo.setImgText(mission.getVerifyImg());
        //需要的图片
        String  missionImgs = mission.getMissionImgs();
        if (!StringUtil.isEmpty(missionImgs)){
            String[] imgArr = missionImgs.split(";");
            vo.setExampleImgs(Arrays.asList(imgArr));
        }
        //必须要填的标签[{"requireId":1,"requireName":"jsj"},{"requireId":2,"requireName":"jdn"},{"requireId":3,"requireName":"bbb"}]
        String requires = task.getCommitRequire();
        if (!StringUtil.isEmpty(requires)){
            List<ReRecommendMissionRequire> allRequires = JSON.parseArray(requires,ReRecommendMissionRequire.class);

            List<UserCommitLabelVo> lableVos = new ArrayList<>();
            for (ReRecommendMissionRequire require:allRequires){

                UserCommitLabelVo lableVo = new UserCommitLabelVo();

                Long requireId = require.getRequireId();
                String requireName = reRecommendMissionRequireDAO.selectByPrimaryKey(requireId).getRequireName();

                lableVo.setRequireName(requireName);
                lableVo.setRequireContent(require.getRequireName());

                lableVos.add(lableVo);
            }
            vo.setRequires(lableVos);
        }
        //用户提交的图片:["http://dev.image.lswuyou.cn/i/10/1478573418528"],["http://dev.image.lswuyou.cn/i/26/1478582755000855.jpg","http://dev.image.lswuyou.cn/i/26/1478582756000260.jpg"]
        String imgs = task.getCommitImg();
        if (!StringUtil.isEmpty(imgs)){

            List<String> userImgs = JSON.parseArray(imgs,String.class);
            vo.setUserImgs(userImgs);
        }

        return vo;
    }


    /**
     * 删除任务
     * @param request
     * @param taskIds
     * @return
     */
    public String doDelete(HttpServletRequest request, String taskIds) {
        int n = 0;

        if (!StringUtils.isEmpty(taskIds)) {
            String[] tasks = taskIds.split("&");
            List<String> missionList = Arrays.asList(taskIds);

            for (String mission : missionList) {
                Long taskId = Long.valueOf(mission);
                ReRecommendTask task = reRecommendTaskDAO.selectByPrimaryKey(taskId);
                if (task != null) {
                    reRecommendTaskDAO.deleteByPrimaryKey(taskId);
                    n++;
                }
            }
        }
        return JsonUtil.buildSuccess("成功删除" + n + "条纪录");
    }

    /**
     * 审核通过
     * @param request
     * @return
     */
    public String verifySuccess(HttpServletRequest request){

        Long taskId = (Long) request.getSession().getAttribute("taskId");

        ReRecommendTask task = reRecommendTaskDAO.selectByPrimaryKey(taskId);
        if (task != null){
            //状态; 0-进行中 1-审核中 2-审核通过 3-未通过 4-已过期
            if (task.getTaskStatus()==2){
                return JsonUtil.buildError("改记录已经审核通过,不能再次通过");
            }
            if (task.getTaskStatus()==3){
                return JsonUtil.buildError("改记录审核未通过");
            }
            if (task.getTaskStatus() == 0){
                return JsonUtil.buildError("改记录仍然在进行中");
            }
            if (task.getTaskStatus() ==4){
                return JsonUtil.buildError("改记录已过期");
            }

            //更改任务表
            task.setUpdateTime(System.currentTimeMillis());
            task.setTaskStatus(2);
            reRecommendTaskDAO.updateByPrimaryKeySelective(task);

            Long missionId = task.getMissionId();
            ReRecommendMission mission = reRecommendMissionDAO.selectByPrimaryKey(missionId);

            // 随机生成一个红包
            BigDecimal multiply = new BigDecimal("100.00");
            int minMoney = mission.getMinMoney().multiply(multiply).intValue();
            int maxMoney = mission.getMaxMoney().multiply(multiply).intValue();
            int randomMoney = minMoney;
            if(maxMoney > minMoney) {
                randomMoney = RandomUtil.getRandomBetweenMaxAndMin(maxMoney, minMoney);
            }
            String s_money = String.format("%.2f", (double)(randomMoney/100.0));
            BigDecimal money = new BigDecimal(s_money);

            Integer userId = task.getUserId();
            ReUser user = reUserDAO.selectLockByUserId(userId);

            // 更新用户表,更改金额即可,不用更新时间
            user.setUserMoney(user.getUserMoney().add(money));
            reUserDAO.updateByPrimaryKeySelective(user);

            // 账户详情
            ReAccountDetail accountDetail = new ReAccountDetail();
            accountDetail.setAccountMoney(money);
            accountDetail.setUserId(userId);
            accountDetail.setAppId(user.getAppId());
            accountDetail.setDetailType(1);
            accountDetail.setMissionType(MissionType.great_mission.val);
            accountDetail.setMissionSubtype(MissionSubtype.other.val);
            accountDetail.setDetailContent("完成高额任务["+mission.getMissionTitle()+"]");
            accountDetail.setDetailTime(ElBase.fmtTime(System.currentTimeMillis()));
            reAccountDetailDAO.insert(accountDetail);

            return  JsonUtil.buildSuccess("审核通过,发放"+money+"元");
        }

        return JsonUtil.buildError("操作失败");
    }

    /**
     * 批量审核
     * @param request
     * @param taskIds  用&链接的id
     * @return
     */
    public String doBatchVerify(HttpServletRequest request, String taskIds, BigDecimal money){

        if (!StringUtil.isEmpty(taskIds)){
            List<String> taskIdList = Arrays.asList(taskIds.split("&"));
            int successTaskNum=0;
            BigDecimal totalMoney = new BigDecimal("0.00");
            int i=0;
            for (String  taskIdStr: taskIdList){
                i++;
                Long taskId = Long.parseLong(taskIdStr);
                ReRecommendTask task = reRecommendTaskDAO.selectByPrimaryKey(taskId);
                if (task != null){
                    //状态; 0-进行中 1-审核中 2-审核通过 3-未通过 4-已过期
                    if (task.getTaskStatus()==2){
                        return JsonUtil.buildError("第"+i+"条记录已经审核通过,不能再次通过");
                    }
                    if (task.getTaskStatus()==3){
                        return JsonUtil.buildError("第"+i+"条记录审核未通过");
                    }
                    if (task.getTaskStatus() == 0){
                        return JsonUtil.buildError("第"+i+"条记录仍然在进行中");
                    }
                    if (task.getTaskStatus() ==4){
                        return JsonUtil.buildError("第"+i+"条记录已过期");
                    }
                    //更改任务表
                    task.setUpdateTime(System.currentTimeMillis());
                    task.setTaskStatus(2);
                    reRecommendTaskDAO.updateByPrimaryKeySelective(task);

                    Long missionId = task.getMissionId();
                    ReRecommendMission mission = reRecommendMissionDAO.selectByPrimaryKey(missionId);

                    // 随机生成一个红包
//                    BigDecimal multiply = new BigDecimal("100.00");
//                    int minMoney = mission.getMinMoney().multiply(multiply).intValue();
//                    int maxMoney = mission.getMaxMoney().multiply(multiply).intValue();
//                    int randomMoney = minMoney;
//                    if(maxMoney > minMoney) {
//                        randomMoney = RandomUtil.getRandomBetweenMaxAndMin(maxMoney, minMoney);
//                    }
//                    String s_money = String.format("%.2f", (double)(randomMoney/100.0));
//                    BigDecimal money = new BigDecimal(s_money);

                    Integer userId = task.getUserId();
                    ReUser user = reUserDAO.selectLockByUserId(userId);

                    // 更新用户表,更改金额即可,不用更新时间
                    user.setUserMoney(user.getUserMoney().add(money));
                    reUserDAO.updateByPrimaryKeySelective(user);

                    // 账户详情
                    ReAccountDetail accountDetail = new ReAccountDetail();
                    accountDetail.setAccountMoney(money);
                    accountDetail.setUserId(userId);
                    accountDetail.setAppId(user.getAppId());
                    accountDetail.setDetailType(1);
                    accountDetail.setMissionType(MissionType.great_mission.val);
                    accountDetail.setMissionSubtype(MissionSubtype.other.val);
                    accountDetail.setDetailContent("完成高额任务["+mission.getMissionTitle()+"]");
                    accountDetail.setDetailTime(ElBase.fmtTime(System.currentTimeMillis()));
                    reAccountDetailDAO.insert(accountDetail);
                    successTaskNum ++;
                    totalMoney=totalMoney.add(money);
                }
            }
            return JsonUtil.buildSuccess("成功审核"+successTaskNum+"条记录,共发放"+totalMoney+"元");
        }

        return JsonUtil.buildError("操作失败");
    }


    /**
     * 批量审核未通过
     * @param request
     * @param taskIds
     * @param remarks
     * @return
     */
    public String doNotBatchVerify(HttpServletRequest request,String taskIds,String remarks){


        if (!StringUtil.isEmpty(taskIds)){
            List<String> taskIdList = Arrays.asList(taskIds.split("&"));
            int successTaskNum=0;
            int i=0;
            for (String  taskIdStr: taskIdList){
                i++;
                Long taskId = Long.parseLong(taskIdStr);
                ReRecommendTask task = reRecommendTaskDAO.selectByPrimaryKey(taskId);
                if (task != null){
                    //状态; 0-进行中 1-审核中 2-审核通过 3-未通过 4-已过期
                    if (task.getTaskStatus()==2){
                        return JsonUtil.buildError("第"+i+"条记录已经审核通过,请先确认");
                    }
                    if (task.getTaskStatus()==3){
                        return JsonUtil.buildError("第"+i+"条记录的状态早已是未通过");
                    }
                    if (task.getTaskStatus() == 0){
                        return JsonUtil.buildError("第"+i+"条记录仍然在进行中");
                    }
                    if (task.getTaskStatus() ==4){
                        return JsonUtil.buildError("第"+i+"条记录已过期");
                    }
                    //更改任务表
                    task.setUpdateTime(System.currentTimeMillis());
                    task.setTaskStatus(3);
                    task.setVerifyRemarks(remarks);
                    reRecommendTaskDAO.updateByPrimaryKeySelective(task);

                    //任务剩余次数+1
                    ReRecommendMission mission = reRecommendMissionDAO.selectLockByMissionId(task.getMissionId());
                    mission.setLeftNum(mission.getLeftNum()+1);
                    reRecommendMissionDAO.updateByPrimaryKeySelective(mission);

                    successTaskNum++;

                }
            }
            return JsonUtil.buildSuccess("有"+(successTaskNum)+"条记录审核未通过");
        }

        return JsonUtil.buildError("操作失败");
    }

    /**
     * 审核未通过
     * @param request
     * @return
     */
    public String verifyFail(HttpServletRequest request, String remarks){


        Long taskId = (Long) request.getSession().getAttribute("taskId");

        ReRecommendTask task = reRecommendTaskDAO.selectByPrimaryKey(taskId);
        if (task != null){
            //状态; 0-进行中 1-审核中 2-审核通过 3-未通过 4-已过期
            if (task.getTaskStatus()==2){
                return JsonUtil.buildError("改记录之前已经审核通过");
            }
            if (task.getTaskStatus()==3){
                return JsonUtil.buildError("改记录之前就已经审核未通过");
            }
            if (task.getTaskStatus() == 0){
                return JsonUtil.buildError("改记录仍然在进行中");
            }
            if (task.getTaskStatus() ==4){
                return JsonUtil.buildError("改记录已过期");
            }

            //更改任务表
            task.setUpdateTime(System.currentTimeMillis());
            task.setTaskStatus(3);
            task.setVerifyRemarks(remarks);
            reRecommendTaskDAO.updateByPrimaryKeySelective(task);

            //任务剩余次数+1
            ReRecommendMission mission = reRecommendMissionDAO.selectLockByMissionId(task.getMissionId());
            mission.setLeftNum(mission.getLeftNum()+1);
            reRecommendMissionDAO.updateByPrimaryKeySelective(mission);

            return  JsonUtil.buildSuccess("操作成功");
        }

        return JsonUtil.buildError("操作失败");
    }

}
