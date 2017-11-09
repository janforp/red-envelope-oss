package com.envolope.oss.weixin.api;

import com.envolope.oss.weixin.bean.Transfers;
import com.envolope.oss.weixin.bean.TransfersResult;
import com.envolope.oss.weixin.client.LocalHttpClient;
import com.envolope.oss.weixin.util.MapUtil;
import com.envolope.oss.weixin.util.SignatureUtil;
import com.envolope.oss.weixin.util.XMLConverUtil;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.entity.StringEntity;

import java.nio.charset.Charset;
import java.util.Map;

/**
 * 微信支付 基于V3.X 版本
 * @author Yi
 *
 */
public class PayMchAPI extends BaseAPI{

	/**
	 * 企业付款
	 * @param transfers
	 * @param key
	 * @return
	 */
	public static TransfersResult mmpaymkttransfersPromotionTransfers(Transfers transfers, String key){
		Map<String,String> map = MapUtil.objectToMap(transfers);
		String sign = SignatureUtil.generateSign(map, key);
		transfers.setSign(sign);
		String secapiPayRefundXML = XMLConverUtil.convertToXML(transfers);
		HttpUriRequest httpUriRequest = RequestBuilder.post()
				.setHeader(xmlHeader)
				.setUri(MCH_URI + "/mmpaymkttransfers/promotion/transfers")
				.setEntity(new StringEntity(secapiPayRefundXML,Charset.forName("utf-8")))
				.build();
		return LocalHttpClient.keyStoreExecuteXmlResult(transfers.getMchid(),httpUriRequest,TransfersResult.class);
	}

}
