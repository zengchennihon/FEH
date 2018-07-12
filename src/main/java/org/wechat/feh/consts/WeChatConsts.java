package org.wechat.feh.consts;

import java.io.IOException;
import java.util.Properties;

public class WeChatConsts {
	
	public static String APPID = "AppID";
	
	public static String APPSECRET = "AppSecret";
	
	static {
		Properties properties = new Properties();
		try {
			properties.load(WeChatConsts.class.getResourceAsStream("/wechat/apptoken.properties"));
			APPID = properties.getProperty(APPID, "wxdd5eb5ca2b82b719");
			APPSECRET = properties.getProperty(APPSECRET, "6f6f507cb8ce07ed6ba3b26f06223dd7");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
