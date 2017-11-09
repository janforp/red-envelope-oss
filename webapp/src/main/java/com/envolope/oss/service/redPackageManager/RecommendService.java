package com.envolope.oss.service.redPackageManager;

import com.alibaba.fastjson.JSON;
import com.envolope.oss.dao.ReRecommendMissionDAO;
import com.envolope.oss.dao.ReRecommendMissionRequireDAO;
import com.envolope.oss.dao.ReRecommendMissionStepDAO;
import com.envolope.oss.dao.ReRecommendMissionTypeDAO;
import com.envolope.oss.model.*;
import com.envolope.oss.model.para.RecommendSelectParamVo;
import com.envolope.oss.model.vo.recommend.RandomImgVo;
import com.envolope.oss.model.vo.recommend.RecommendStep;
import com.envolope.oss.util.EntityUtil;
import com.envolope.oss.util.JsonUtil;
import com.envolope.oss.util.RandomUtil;
import com.envolope.oss.util.StringUtil;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Jan on 16/10/31.
 * 推荐任务
 */
@Service
public class RecommendService {

    @Autowired
    private ReRecommendMissionDAO reRecommendMissionDAO;
    @Autowired
    private ReRecommendMissionStepDAO reRecommendMissionStepDAO;
    @Autowired
    private ReRecommendMissionRequireDAO reRecommendMissionRequireDAO;
    @Autowired
    private ReRecommendMissionTypeDAO reRecommendMissionTypeDAO;

    /**
     * 处理查询参数
     * @return
     */
    public RecommendSelectParamVo handleParamVo(RecommendSelectParamVo paramVo) {

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
        Integer isLimitTime = paramVo.getIsLimitTime();
        if (StringUtil.isEmpty(isLimitTime)){
            paramVo.setIsLimitTime(null);
        }
        Integer isVerify = paramVo.getIsVerify();
        if (StringUtil.isEmpty(isVerify)){
            paramVo.setIsVerify(null);
        }
        Integer type = paramVo.getType();
        if (StringUtil.isEmpty(type)){
            paramVo.setType(null);
        }

        return paramVo;
    }

    /**
     * 按条件查询  分页
     *
     * @param paramVo   查询参数对象
     * @return
     */
    public List<ReRecommendMission> doGetList(RecommendSelectParamVo paramVo) {

        int offset = (paramVo.getPageNum() - 1) * paramVo.getPageSize();
        RowBounds bounds = new RowBounds(offset, paramVo.getPageSize());

        List<ReRecommendMission> missions = reRecommendMissionDAO.getList(paramVo,  bounds);

        List<ReRecommendMission> missionList = new ArrayList<>(missions.size());

        for (ReRecommendMission mission : missions) {
            ReRecommendMission reRecommendMission = (ReRecommendMission) EntityUtil.nullToEmptyString(mission);
            missionList.add(reRecommendMission);
        }

        return missionList;
    }

    /**
     * 按条件查询  分页 总数
     *
     * @param paramVo
     * @return
     */
    public Integer getListNum(RecommendSelectParamVo paramVo) {
        return reRecommendMissionDAO.getListNum(paramVo);
    }

    /**
     * 查询任务详情
     * @param missionId
     * @return
     */
    public ReRecommendMission getMissionById(Long missionId) {
        return reRecommendMissionDAO.selectByPrimaryKey(missionId);
    }

    /**
     * 修改 或者 添加 任务
     *
     * @param mission
     * @return
     * @throws Exception
     */
    public String doSaveOrUpdateMission(ReRecommendMission mission) throws Exception {

        Long missionId = mission.getMissionId();

        if (StringUtils.isEmpty(missionId)) { //ID空,则为添加新任务
            reRecommendMissionDAO.insertSelective(mission);
            return JsonUtil.buildSuccess();
        } else { //有ID,则为修改任务
            mission.setTotalNum(null);
            mission.setLeftNum(null);
            reRecommendMissionDAO.updateByPrimaryKeySelective(mission);
            return JsonUtil.buildSuccess(missionId.toString());
        }
    }

    /**
     * 删除任务
     *
     * @param missionIds
     * @return
     */
    public String doDeleteMissions(String missionIds) {

        int n = 0;

        if (!StringUtils.isEmpty(missionIds)) {
            String[] missions = missionIds.split("&");
            List<String> missionList = Arrays.asList(missions);

            for (String mission : missionList) {
                Long missionId = Long.valueOf(mission);
                ReRecommendMission mission1 = reRecommendMissionDAO.selectByPrimaryKey(missionId);
                if (mission1 != null) {
                    reRecommendMissionDAO.deleteByPrimaryKey(missionId);
                    n++;
                }
            }
        }

        return JsonUtil.buildSuccess("成功删除" + n + "条纪录");

    }


    /**
     * 获取审核图片 集合
     * @return
     */
    public List<String> getMissionImgs(Long missionId){

        ReRecommendMission mission = reRecommendMissionDAO.selectByPrimaryKey(missionId);

        String missionImgs = mission.getMissionImgs();

        List<String> imgList = null;

        if (!StringUtil.isEmpty(missionImgs)){
            String[] imgs = missionImgs.split(";");
            if (!StringUtil.isEmpty(imgs)){
                imgList = Arrays.asList(imgs);
            }
        }
        return imgList;

    }

    /**
     * 增加任务
     *
     * @param missionId
     * @return
     */
    public String doAddRed(Long missionId, Integer num){
        ReRecommendMission mission = reRecommendMissionDAO.selectLockByMissionId(missionId);
        mission.setTotalNum(mission.getTotalNum() + num);
        mission.setLeftNum(mission.getLeftNum() + num);
        reRecommendMissionDAO.updateByPrimaryKeySelective(mission);
        return JsonUtil.buildSuccess("成功生成添加"+num+"个任务");
    }

    /**
     * 获取任务的步骤
     * @param missionId
     * @return
     */
    public List<ReRecommendMissionStep> getStepsByMissionId(Long missionId){
        return reRecommendMissionStepDAO.getAllByMissionId(missionId);
    }


    /**
     * 图片分开的步骤
     * @param missionId
     * @return
     */
    public List<RecommendStep> getSteps(Long missionId){

        //1.ID对应的步骤
        List<ReRecommendMissionStep> steps = getStepsByMissionId(missionId);

        List<RecommendStep> recommendSteps = new ArrayList<>(steps.size());

        for (ReRecommendMissionStep step : steps){

            RecommendStep recommendStep = new RecommendStep();

            recommendStep.setStepId(step.getStepId());
            recommendStep.setMissionId(step.getMissionId());
            recommendStep.setStepNum(step.getStepNum());
            recommendStep.setStepStatus(step.getStepStatus());
            recommendStep.setStepContent(step.getStepContent());
            recommendStep.setBtnHtml(step.getStepBtn());
            recommendStep.setStepStatus(step.getStepStatus());

            //2.每一步的图片
            String imgs = step.getStepImgs();

            List<String> imgList = new ArrayList<>();

            if (imgs != null) {
                imgList = Arrays.asList(imgs.split(";"));
            }

            List<RandomImgVo> randomImgVos = new ArrayList<>();

            //若原来没有图片,则生成的空图片,则没有obj,所以,要保证,每一步里面有4张图片,图片为空时,也要生成随机字符串过去页面
            for (String img : imgList) {
                RandomImgVo vo = new RandomImgVo();
                if (!StringUtil.isEmpty(img)){
                    vo.setImgUrl(img);
                    vo.setRandomString(RandomUtil.getRandomString(10));
                    randomImgVos.add(vo);
                }
            }

            randomImgVos = getFourRandomString(randomImgVos);
            recommendStep.setRandomImgVos(randomImgVos);
            recommendSteps.add(recommendStep);
        }

        return recommendSteps;
    }

    /**
     * 若传进来的集合,没有4个元素,则生成4个元素,
     * 生成的元素,图片为空,但是随机字符串必须有
     * @param vos
     * @return
     */
    public List<RandomImgVo> getFourRandomString(List<RandomImgVo> vos){

        int size = vos.size();

        if (size == 0){

            for (int i=0;i<4;i++){

                RandomImgVo randomImgVo = new RandomImgVo();
                randomImgVo.setImgUrl("");
                randomImgVo.setRandomString(RandomUtil.getRandomString(10));
                vos.add(randomImgVo);
            }

            return vos;

        }else if (size == 1) {
            for (int i=0;i<3;i++){

                RandomImgVo randomImgVo = new RandomImgVo();
                randomImgVo.setImgUrl("");
                randomImgVo.setRandomString(RandomUtil.getRandomString(10));
                vos.add(randomImgVo);
            }

            return vos;
        }else if (size == 2) {
            for (int i=0;i<2;i++){

                RandomImgVo randomImgVo = new RandomImgVo();
                randomImgVo.setImgUrl("");
                randomImgVo.setRandomString(RandomUtil.getRandomString(10));
                vos.add(randomImgVo);
            }

            return vos;
        }else if (size == 3) {
            for (int i=0;i<1;i++){

                RandomImgVo randomImgVo = new RandomImgVo();
                randomImgVo.setImgUrl("");
                randomImgVo.setRandomString(RandomUtil.getRandomString(10));
                vos.add(randomImgVo);
            }

            return vos;
        }else{

            return vos;
        }

    }

    /**
     * 添加步骤
     *
     * @param request
     * @param totalStep 很多步组成的json
     * @return
     */
    public String doAddStep(HttpServletRequest request, String totalStep){

        Long missionId = (Long) request.getSession().getAttribute("missionId");
        //1.每次修改之前,先把他的所有步骤都删除
        reRecommendMissionStepDAO.deleteByMissionId(missionId);

        List<ReRecommendMissionStep> steps = JSON.parseArray(totalStep,ReRecommendMissionStep.class);

        List<ReRecommendMissionStep> filterSteps = new ArrayList<>();

        for (ReRecommendMissionStep step : steps){

            String content = step.getStepContent();
            String img = step.getStepImgs();
            String btn = step.getStepBtn();

            if (StringUtil.isEmpty(content) && StringUtil.isEmpty(img) && StringUtil.isEmpty(btn)){
                continue;
            }else{
                step.setMissionId(missionId);
                filterSteps.add(step);
            }

        }
        reRecommendMissionStepDAO.insertBatch(filterSteps);

        return JsonUtil.buildSuccess();
    }


    /**
     * 获取所以要求列表
     *
     * @return
     */
    public List<ReRecommendMissionRequire> getAllRequires(){
        return reRecommendMissionRequireDAO.getAll();
    }

    /**
     * 获取任务已有的审核要求的ID
     * @param missionId
     * @return
     */
    public List<ReRecommendMissionRequire> getAlreadyRequire(Long missionId){

        ReRecommendMission mission = reRecommendMissionDAO.selectByPrimaryKey(missionId);
        List<ReRecommendMissionRequire> ids = null;

        if (mission!=null){
            String verifyRequire = mission.getVerifyRequire();//json
            if (!StringUtil.isEmpty(verifyRequire)){
                ids = JSON.parseArray(verifyRequire,ReRecommendMissionRequire.class);
            }
        }
        return ids;
    }

    /**
     * 获取标签
     * @param missionId
     * @return
     */
    public String getAlreadyLabel(Long missionId){

        ReRecommendMission mission = reRecommendMissionDAO.selectByPrimaryKey(missionId);

        return mission.getMissionLabel();
    }


    /**
     * 获取审核任务的父类型
     * @return
     */
    public List<ReRecommendMissionType> getTypes(){

        return reRecommendMissionTypeDAO.getAllFatherTypes();
    }

    /**
     * 获取子类型
     * @param id
     * @return
     */
    public String getSubTypeById(int id){
        ReRecommendMissionType type = reRecommendMissionTypeDAO.selectByPrimaryKey(id);
        if(type == null) {
            return null;
        }
        return type.getSubTypeName();
    }

}
