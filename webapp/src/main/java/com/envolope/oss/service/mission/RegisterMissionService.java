package com.envolope.oss.service.mission;

import com.alibaba.fastjson.JSON;
import com.envolope.oss.consts.ValueConsts;
import com.envolope.oss.dao.ReRecommendMissionDAO;
import com.envolope.oss.dao.ReRecommendMissionStepDAO;
import com.envolope.oss.model.ReRecommendMission;
import com.envolope.oss.model.ReRecommendMissionRequire;
import com.envolope.oss.model.ReRecommendMissionStep;
import com.envolope.oss.model.vo.recommend.RandomImgVo;
import com.envolope.oss.model.vo.recommend.RecommendStep;
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
 * Created by Jan on 16/11/21.
 * 高额任务表中的注册任务
 */
@Service
public class RegisterMissionService {
    @Autowired
    private ReRecommendMissionDAO reRecommendMissionDAO;
    @Autowired
    private ReRecommendMissionStepDAO reRecommendMissionStepDAO;

    /**
     * 总数,分页用
     * @return
     */
    public Integer getNum(HttpServletRequest request,String title){
        return reRecommendMissionDAO.getNum(2,title);
    }

    /**
     * 列表查询
     * @param request
     * @return
     */
    public List<ReRecommendMission> getList(HttpServletRequest request, Integer pageNum,String  title){
        int offset = (pageNum - 1) * ValueConsts.PAGE_SIZE;
        RowBounds bounds = new RowBounds(offset,ValueConsts.PAGE_SIZE);
        List<ReRecommendMission> details = reRecommendMissionDAO.getMissionList(bounds,2,title);
        return details;
    }


    /**
     * 添加或修改任务
     * @param request
     * @param mission
     * @return
     */
    public String save(HttpServletRequest request,ReRecommendMission mission){

        Long missionId = mission.getMissionId();
        if (StringUtil.isEmpty(missionId)){
            reRecommendMissionDAO.insertSelective(mission);
        }else {
            reRecommendMissionDAO.updateByPrimaryKeySelective(mission);
        }
        return JsonUtil.buildSuccess();
    }

    /**
     * 修改数据获取
     * @param request
     * @param missionId
     * @return
     */
    public ReRecommendMission modifyData(HttpServletRequest request,Long missionId){

        ReRecommendMission mission = reRecommendMissionDAO.selectLockByMissionId(missionId);
        return mission;
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
     * 查询任务详情
     * @param missionId
     * @return
     */
    public ReRecommendMission getMissionById(Long missionId) {
        return reRecommendMissionDAO.selectByPrimaryKey(missionId);
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

}
