package org.feh.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

public class UrlUtils {
	
	/**
	 * 将传入的参数进行urlEncode拼接成 属性1=值&属性2=值&....
	 * @param params 参数格式: "属性=值", 用=分割
	 * @return
	 */
	public static String getParams(String ... params) {
		StringBuffer sb = new StringBuffer();
		for (String param : params) {
			if(param.indexOf("=") != -1) {
				try {
					String _params[] = param.split("=");
					sb.append(URLEncoder.encode(_params[0], "UTF-8")).append("=").append(URLEncoder.encode(_params[1], "UTF-8")).append("&");
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
			}
		}
		return sb.substring(0, sb.length() - 1);
	}

	public static String sendPost(String _url, String param, String cookie, String referer) {
		String result = "";
        OutputStreamWriter outputStreamWriter = null;
        BufferedReader reader = null;
		try {
			URL url = new URL(_url);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			connection.setRequestProperty("Accept-Charset", "utf-8");
			connection.setRequestMethod("POST");
			connection.setRequestProperty("Cookie", cookie);
			connection.setRequestProperty("Referer", referer);
			connection.setDoInput(true);
			connection.setDoOutput(true);
			connection.connect();
	        
            outputStreamWriter = new OutputStreamWriter(connection.getOutputStream());
            outputStreamWriter.write(param.toString());
            outputStreamWriter.flush();
            
            try {
            	reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            	System.out.println("inputStream");
            } catch (Exception e) {
				reader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
				System.out.println("errorStream");
			}
            
            String tempLine = null;
            StringBuffer resultBuffer = new StringBuffer();
            
            while ((tempLine = reader.readLine()) != null) {
                resultBuffer.append(tempLine);
            }
			result = resultBuffer.toString();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if(reader != null) {
					reader.close();
				}
				if(outputStreamWriter != null) {
					outputStreamWriter.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
	public static String sendGet(String _url, String cookie, String referer) {
		String result = "";
        BufferedReader reader = null;
		try {
			URL url = new URL(_url);
			URLConnection connection = url.openConnection();
			connection.connect();

            reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String line = null;
			StringBuffer stringBuffer = new StringBuffer();
            while ((line = reader.readLine()) != null) {
                //转化为UTF-8的编码格式
                line = new String(line.getBytes("UTF-8"));
                stringBuffer.append(line);
            }
            result = stringBuffer.toString();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if(reader != null) {
					reader.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

}
