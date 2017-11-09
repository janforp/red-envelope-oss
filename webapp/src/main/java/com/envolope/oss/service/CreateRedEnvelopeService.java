package com.envolope.oss.service;

import com.envolope.oss.consts.ValueConsts;
import com.envolope.oss.dao.ReCodeRedDetailDAO;
import com.envolope.oss.dao.ReCustomerEnvelopeDAO;
import com.envolope.oss.model.ReCodeRedDetail;
import com.envolope.oss.model.ReCustomerEnvelope;
import com.envolope.oss.util.RandomUtil;
import com.envolope.oss.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.spel.ast.LongLiteral;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jan on 16/7/14.
 *
 * 生成红包
 */
@Service
public class CreateRedEnvelopeService {


    @Autowired
    private ReCustomerEnvelopeDAO reCustomerEnvelopeDAO;
    @Autowired
    private ReCodeRedDetailDAO reCodeRedDetailDAO ;


    /**
     * 生成红包
     * @param request
     * @param customerId    客户id
     * @param num           随机红包数量(1-1.1元)
     * @param bigEnvelope   大额红包(888|666|555)
     * @return
     */
    public Integer createEnvelope (HttpServletRequest request,
                                   Integer customerId,
                                   Integer num,
                                   String bigEnvelope) {
        if (num == null) {
            num = 0;
        }
        if (bigEnvelope == null) {
            bigEnvelope = "";
        }


        List<ReCustomerEnvelope> list = new ArrayList<>();
        for (int i =0;i<num;i++) {

            ReCustomerEnvelope ev = new ReCustomerEnvelope();
            int money = RandomUtil.getRandomBetweenMinAndMax(ValueConsts.MIN_MONEY,ValueConsts.MAX_MONEY);
            ev.setCustomerId(customerId);
            ev.setEnvelopeMoney(money);
            ev.setEnvelopeStatus(0);
            list.add(ev);
        }
        if (!StringUtil.isEmpty(bigEnvelope)) {

            if (bigEnvelope.startsWith("\\|")){
                bigEnvelope = bigEnvelope.replaceFirst("\\|","");
            }
            if (bigEnvelope.endsWith("\\|")) {
                bigEnvelope = bigEnvelope.substring(0,bigEnvelope.length()-1);
            }

            String[] bigs = bigEnvelope.split("\\|");
            for (String big : bigs) {
                if ("".equals(big.trim())){
                    continue;
                }
                int bigRed = Integer.valueOf(big.trim());

                ReCustomerEnvelope ev = new ReCustomerEnvelope();

                ev.setCustomerId(customerId);
                ev.setEnvelopeMoney(bigRed);
                ev.setEnvelopeStatus(0);
                list.add(ev);
            }
        }

        reCustomerEnvelopeDAO.insertBatch(list);

        return list.size();
    }

    /**
     * 生成 验证码 红包
     * @param request
     * @param max       最大金额    如:1100          单位:分
     * @param min       最小金额    如:10            单位:分
     * @param codeId    兑换码红包ID
     * @param num       随机生成红包数量
     * @param bigEnvelope   大额红包    如:8888|9999|10000 单位:分
     * @return
     */
    public Integer createCodeRedEnvelope (HttpServletRequest request,
                                          String max,
                                          String min,
                                          Integer codeId,
                                          Integer num,
                                          String bigEnvelope) {


        if (num == null) {
            num = 0;
        }
        if (bigEnvelope == null) {
            bigEnvelope = "";
        }


        List<ReCodeRedDetail> list = new ArrayList<>();

        for (int i =0;i<num;i++) {

            ReCodeRedDetail detail = new ReCodeRedDetail();
            double money = RandomUtil.getRandomBetweenMinAndMax(Integer.valueOf(min),Integer.valueOf(max));

            BigDecimal redMoney = new BigDecimal(money / 100) ;
            detail.setCodeId(codeId);
            detail.setCodeMoney(redMoney);
            detail.setCodeStatus(0);

            list.add(detail);
        }
        if (!StringUtil.isEmpty(bigEnvelope)) {

            if (bigEnvelope.startsWith("\\|")){
                bigEnvelope = bigEnvelope.replaceFirst("\\|","");
            }
            if (bigEnvelope.endsWith("\\|")) {
                bigEnvelope = bigEnvelope.substring(0,bigEnvelope.length()-1);
            }

            String[] bigs = bigEnvelope.split("\\|");
            for (String big : bigs) {
                if ("".equals(big.trim())){
                    continue;
                }
                Double bigRed = Double.valueOf(big.trim());

                ReCodeRedDetail bigDetail = new ReCodeRedDetail();

                bigDetail.setCodeId(codeId);
                bigDetail.setCodeMoney(new BigDecimal( bigRed / 100L ));
                bigDetail.setCodeStatus(0);

                list.add(bigDetail);
            }
        }

        reCodeRedDetailDAO.insertBatch(list);

        return list.size();
    }
}
