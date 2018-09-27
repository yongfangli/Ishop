package com.thinkgem.jeesite.common.config;

import java.util.Map;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.google.common.collect.Maps;
import com.thinkgem.jeesite.common.utils.PropertiesLoader;
import com.thinkgem.jeesite.common.utils.StringUtils;

public class EmailConfig {
	private EmailConfig() {
	}

	/**
	 * 当前对象实例
	 */
	private static EmailConfig global = null;

	/**
	 * 静态工厂方法 获取当前对象实例 多线程安全单例模式(使用双重同步锁)
	 */

	public static synchronized EmailConfig getInstance() {

		if (global == null) {
			synchronized (Global.class) {
				if (global == null)
					global = new EmailConfig();
			}
		}
		return global;
	}

	/**
	 * 保存全局属性值
	 */
	private static Map<String, String> map = Maps.newHashMap();

	/**
	 * 属性文件加载对象
	 */
	private static PropertiesLoader loader = new PropertiesLoader("emailServer.properties");

	public final static String SERVER_ADDRESS_KEY = "mail.host", PROTOCAL_NAME_KEY = "mail.transport.protocol",
			AUTH_SET_KEY = "mail.smtp.auth", USERNAME_KEY = "username", PAS_KEY = "password",
			ENABLE_PSW = "mail.smtp.starttls.enable";

	/**
	 * 获取配置
	 * 
	 * @see ${fns:getConfig('adminPath')}
	 */
	public static String getConfig(String key) {
		String value = map.get(key);
		if (value == null) {
			value = loader.getProperty(key);
			map.put(key, value != null ? value : StringUtils.EMPTY);
		}
		return value;
	}

	public static String getServerAddress() {
		return getConfig(SERVER_ADDRESS_KEY);
	}

	public static String getProtocalName() {
		return getConfig(PROTOCAL_NAME_KEY);
	}

	public static String getAuthSetting() {
		return getConfig(AUTH_SET_KEY);
	}

	public static String getUserName() {
		return getConfig(USERNAME_KEY);
	}

	public static String getPassword() {
		return getConfig(PAS_KEY);
	}

	public static String getEnablepsw() {
		return getConfig(ENABLE_PSW);
	}

}
