package com.thinkgem.jeesite.modules.postManeger.baiduApi.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONObject;
import org.mozilla.javascript.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thinkgem.jeesite.modules.postManeger.cost.TokenType;
import com.thinkgem.jeesite.modules.wsp.token.TokenService;
import com.thinkgem.jeesite.modules.wsp.token.WToken;

@Service
public class AuthService {
	@Autowired
	private TokenService tokenService;

	/**
	 * 获取权限token
	 * 
	 * @return 返回示例： { "access_token":
	 *         "24.460da4889caad24cccdb1fea17221975.2592000.1491995545.282335-1234567",
	 *         "expires_in": 2592000 }
	 */
	public String getAuth() {
		// 官网获取的 API Key 更新为你注册的
		String clientId = "Xaz0vaGGSj5NT2gaVSG0cMPh";
		// 官网获取的 Secret Key 更新为你注册的
		String clientSecret = "iIjKREHxSHdI44sm5z6GX4C9f6OqLERV";
		return getAuth(clientId, clientSecret);
	}

	/**
	 * 获取API访问token 该token有一定的有效期，需要自行管理，当失效时需重新获取.
	 * 
	 * @param ak - 百度云官网获取的 API Key
	 * @param sk - 百度云官网获取的 Securet Key
	 * @return assess_token 示例：
	 *         "24.460da4889caad24cccdb1fea17221975.2592000.1491995545.282335-1234567"
	 */
	public String getAuth(String ak, String sk) {
		String tokenstr = tokenService.getToken(TokenType.BAIDU);
		if (StringUtils.isBlank(tokenstr)) {
			// 获取token地址
			String authHost = "https://aip.baidubce.com/oauth/2.0/token?";
			String getAccessTokenUrl = authHost
					// 1. grant_type为固定参数
					+ "grant_type=client_credentials"
					// 2. 官网获取的 API Key
					+ "&client_id=" + ak
					// 3. 官网获取的 Secret Key
					+ "&client_secret=" + sk;
			try {
				URL realUrl = new URL(getAccessTokenUrl);
				// 打开和URL之间的连接
				HttpURLConnection connection = (HttpURLConnection) realUrl.openConnection();
				connection.setRequestMethod("GET");
				connection.connect();
				// 获取所有响应头字段
				Map<String, List<String>> map = connection.getHeaderFields();
				// 遍历所有的响应头字段
				for (String key : map.keySet()) {
					System.err.println(key + "--->" + map.get(key));
				}
				// 定义 BufferedReader输入流来读取URL的响应
				BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
				String result = "";
				String line;
				while ((line = in.readLine()) != null) {
					result += line;
				}
				/**
				 * 返回结果示例
				 */
				System.err.println("result:" + result);
				JSONObject jsonObject = new JSONObject(result);
				String access_token = jsonObject.getString("access_token");
			    int timestr = jsonObject.getInt("expires_in");
				// save token to db

				WToken token = new WToken();
				token.setCode(TokenType.BAIDU);
				token.setCreateDate(new Date(System.currentTimeMillis()));
				token.setToken(access_token);
				token.setExpired(timestr);
				tokenService.save(token);
				return access_token;
			} catch (Exception e) {
				System.err.printf("获取token失败！");
				e.printStackTrace(System.err);
			}
		} else {
			return tokenstr;
		}
		return null;
	}
}