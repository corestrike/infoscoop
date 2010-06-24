package org.infoscoop.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.infoscoop.dao.OAuthConsumerDAO;
import org.infoscoop.dao.OAuthTokenDAO;
import org.infoscoop.dao.model.OAUTH_CONSUMER_PK;
import org.infoscoop.dao.model.OAuthConsumerProp;
import org.infoscoop.util.Crypt;
import org.infoscoop.util.SpringUtil;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class OAuthService {
	private static Log log = LogFactory.getLog(OAuthService.class);

	private OAuthConsumerDAO oauthConsumerDAO;

	private OAuthTokenDAO oauthTokenDAO;

	public static OAuthService getHandle() {
		return (OAuthService) SpringUtil.getBean("OAuthService");
	}
	
	/**
	 * @param portalAdminsDAO
	 */
	public void setOauthConsumerDAO(OAuthConsumerDAO oauthConsumerDAO) {
		this.oauthConsumerDAO = oauthConsumerDAO;
	}
	
	public void setOauthTokenDAO(OAuthTokenDAO oauthTokenDAO){
		this.oauthTokenDAO = oauthTokenDAO;
	}
	
	public String getOAuthConsumerListJson() throws Exception{
		List<OAuthConsumerProp> consumerPropList = this.oauthConsumerDAO.getConsumers();
		JSONArray cunsumerList = new JSONArray();
		for(OAuthConsumerProp prop: consumerPropList){
			JSONObject obj = new JSONObject();
			obj.put("service_name", prop.getId().getServiceName());
			obj.put("gadget_url", prop.getGadgetUrl());
			obj.put("consumer_key", prop.getConsumerKey());
			obj.put("consumer_secret", prop.getConsumerSecret());
			//obj.put("private_key", prop.getPrivateKey());
			obj.put("signature_method", prop.getSignatureMethod());
			cunsumerList.put(obj);
		}

		return cunsumerList.toString();
	}

	public void saveOAuthConsumerList(String saveArray, String deleteArray) throws Exception{
		JSONArray consumerJsonList = new JSONArray(saveArray);

		List<OAuthConsumerProp> consumers = new ArrayList<OAuthConsumerProp>();
		for(int i = 0; i < consumerJsonList.length();i++){
			JSONObject obj = consumerJsonList.getJSONObject(i);
			String gadgetUrl = obj.getString("gadgetUrl");
			OAuthConsumerProp consumer = new OAuthConsumerProp(
					new OAUTH_CONSUMER_PK(Crypt.getHash(gadgetUrl), obj.getString("serviceName"))
			);
			consumer.setGadgetUrl(gadgetUrl);
			consumer.setConsumerKey(obj.getString("consumerKey"));
			consumer.setConsumerSecret(obj.getString("consumerSecret"));
			consumer.setSignatureMethod(obj.getString("signatureMethod"));
			consumers.add(consumer);
		}

		this.oauthConsumerDAO.saveConsumers(consumers);


		JSONArray deleteIdList = new JSONArray(deleteArray);
		for(int i = 0; i < deleteIdList.length();i++){
			JSONObject obj = deleteIdList.getJSONObject(i);
			this.oauthConsumerDAO.delete(obj.getString("gadgetUrl"), obj.getString("serviceName"));
		}
	}
	
	public void saveOAuthToken(String uid, String gadgetUrl, String serviceName, String accessToken, String tokenSecret){
		this.oauthTokenDAO.saveAccessToken(uid,gadgetUrl, serviceName, accessToken, tokenSecret);
		
	}
}