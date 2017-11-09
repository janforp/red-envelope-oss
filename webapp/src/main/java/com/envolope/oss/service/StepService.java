package com.envolope.oss.service;

import com.envolope.oss.dao.ReCustomerExtendStepDAO;
import com.envolope.oss.model.ReCustomerExtendStep;
import com.envolope.oss.util.JsonUtil;
import com.envolope.oss.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jan on 16/7/15.
 * 步骤
 */
@Service
public class StepService {

    @Autowired
    private ReCustomerExtendStepDAO reCustomerExtendStepDAO;


    /**
     * 取到id 是extendId的推广步骤
     * @param request
     * @param extendId  推广步骤
     * @return
     */
    public List<ReCustomerExtendStep> getStepsByExtendId (HttpServletRequest request,Integer extendId) {

        return reCustomerExtendStepDAO.getStepsByExtendId(extendId);
    }


    /**
     * 保存或者添加步骤
     * @param request
     * @param stepsContent  各步骤的内容,个步骤用&分开
     * @param id            推广id
     * @return
     */
    public String saveSteps(HttpServletRequest request,String stepsContent,Integer id){

        List<ReCustomerExtendStep> old = getStepsByExtendId(request,id);
        for (ReCustomerExtendStep step : old) {
            reCustomerExtendStepDAO.deleteByPrimaryKey(step.getId());
        }

        if (!StringUtil.isEmpty(stepsContent)) {
            String[] news = stepsContent.split("&");
            if (news != null && news.length != 0) {
                List<ReCustomerExtendStep> newSteps = new ArrayList<>(news.length);
                for (int i = 0;i< news.length ;i++) {
                    ReCustomerExtendStep extendStep = new ReCustomerExtendStep();
                    extendStep.setExtendId(id);
                    extendStep.setStepNum(i+1);
                    extendStep.setStepContent(news[i]);
                    newSteps.add(extendStep);
                }
                reCustomerExtendStepDAO.insertBatch(newSteps);

                return JsonUtil.buildSuccess("保存了"+newSteps.size()+"条步骤");
            }

        }
        return JsonUtil.buildError();
    }

}
