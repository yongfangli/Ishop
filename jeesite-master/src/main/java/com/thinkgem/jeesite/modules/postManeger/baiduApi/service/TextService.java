package com.thinkgem.jeesite.modules.postManeger.baiduApi.service;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TextService {
	@Autowired
	private AuthService authService;

	public static String TEXT_URL = "https://aip.baidubce.com/rest/2.0/antispam/v2/spam";

	public boolean validateText(String content) {
		String tokenStr = authService.getAuth();
		try {
			URL realUrl = new URL(TEXT_URL);
			HttpURLConnection connection = (HttpURLConnection) realUrl.openConnection();
			connection.setDoOutput(true); // 需要输出
			connection.setDoInput(true); // 需要输入
			connection.setUseCaches(false); // 不允许缓存

			connection.setRequestMethod("POST");
			//connection.setRequestProperty("access_token", tokenStr);
			connection.setRequestProperty("Content-type", "application/x-www-form-urlencoded");
			connection.setRequestProperty("Connection", "Keep-Alive");// 维持长连接
			connection.connect();
			String param = "content=" + URLEncoder.encode(content, "UTF-8")+"&&access_token="+tokenStr;

			DataOutputStream dos = new DataOutputStream(connection.getOutputStream());
			dos.writeBytes(param);
			dos.flush();
			dos.close();

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
			if (jsonObject.getJSONObject("result").getInt("spam") == 0) {
				return true;
			} else
				return false;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
}
