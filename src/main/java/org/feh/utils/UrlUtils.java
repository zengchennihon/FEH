package org.feh.utils;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.stream.Stream;

public class UrlUtils {

	/**
	 * 将传入的参数进行urlEncode拼接成 属性1=值&属性2=值&....
	 *
	 * @param params 参数格式: "属性=值", 用=分割
	 * @return
	 */
	public static String getParams(String... params) {
		StringBuffer sb = new StringBuffer();
		for (String param : params) {
			if (param.indexOf("=") != -1) {
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
			connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/61.0.3163.100 Safari/537.36)");
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

			String tempLine;
			StringBuffer resultBuffer = new StringBuffer();

			while ((tempLine = reader.readLine()) != null) {
				resultBuffer.append(tempLine);
			}
			result = resultBuffer.toString();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			closeStream(reader, outputStreamWriter);
		}
		return result;
	}

	public static String sendGet(String _url, String cookie, String referer) {
		String result = "";
		InputStream in = null;
		ByteArrayOutputStream out = null;
		try {
			URL url = new URL(_url);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			connection.setRequestProperty("Accept-Charset", "utf-8");
			connection.setRequestMethod("GET");
			connection.setRequestProperty("Cookie", cookie);
			connection.setRequestProperty("Referer", referer);
			connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/61.0.3163.100 Safari/537.36)");
			connection.connect();

			in = connection.getInputStream();
			out = new ByteArrayOutputStream();
			byte temp[] = new byte[1024 * 1024];
			int len;
			while ((len = in.read(temp)) != -1) {
				out.write(temp, 0, len);
			}
			//转化为UTF-8的编码格式
			out.flush();
			result = out.toString("utf-8");
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			closeStream(out, in);
		}
		return result;
	}

	public static byte[] sendGetImg(String _url, String cookie, String referer) {
		byte[] imgArrays = new byte[1];
		InputStream in = null;
		ByteArrayOutputStream out = null;
		try {
			URL url = new URL(_url);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			connection.setRequestProperty("Accept-Charset", "utf-8");
			connection.setRequestMethod("GET");
			connection.setRequestProperty("Cookie", cookie);
			connection.setRequestProperty("Referer", referer);
			connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/61.0.3163.100 Safari/537.36)");
			connection.connect();

			in = connection.getInputStream();
			out = new ByteArrayOutputStream();
			byte temp[] = new byte[1024 * 1024];
			int len;
			while ((len = in.read(temp)) != -1) {
				out.write(temp, 0, len);
			}
			out.flush();
			return out.toByteArray();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeStream(out, in);
		}
		return imgArrays;
	}

	public static void closeStream(Object... objs) {
		if (objs != null && objs.length > 0) {
			Arrays.asList(objs).forEach(obj -> {
				try {
					if (obj instanceof Stream) {
						((Stream) obj).close();
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			});
		}
	}

}
