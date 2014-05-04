package com.gtt.pets.util;

import com.gtt.kenshin.log.KenshinLogger;
import com.gtt.kenshin.log.KenshinLoggerFactory;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.SimpleHttpConnectionManager;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.io.IOUtils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Map;

/**
 * @author tiantiangao
 */
public class HttpUtils {

	private static final KenshinLogger LOGGER = KenshinLoggerFactory.getLogger(HttpUtils.class);

	/**
	 * 发送请求到指定地址
	 */
	public static String executeGet(String url, Map<String, String> params) {
		GetMethod method = null;
		try {
			// 创建HttpClient
			// 确保每次请求发送完成后都会关闭连接，使用new SimpleHttpConnectionManager(true)
			HttpClient httpClient = new HttpClient(new SimpleHttpConnectionManager(true));
			httpClient.getHttpConnectionManager().getParams().setSoTimeout(5000);
			httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(5000);

			// 设置参数
			StringBuilder sb = new StringBuilder();
			sb.append(url);
			sb.append("?");
			int index = 0;
			for (String key : params.keySet()) {
				if (index != 0) {
					sb.append("&");
				}
				sb.append(key + "=" + params.get(key));
				index++;
			}
			method = new GetMethod(sb.toString());
			method.getParams().setContentCharset("UTF-8");
			method.getParams().setParameter(HttpMethodParams.USER_AGENT,
					"Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_0) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/34.0.1847.131 Safari/537.36");

			// 发送请求
			httpClient.executeMethod(method);

			// 处理响应
			if (method.getStatusCode() == HttpStatus.SC_OK) {
				InputStream inputStream = method.getResponseBodyAsStream();
				BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));

				String tempbf = null;
				StringBuffer response = new StringBuffer(100);
				while ((tempbf = br.readLine()) != null) {
					response.append(tempbf);
				}
				IOUtils.closeQuietly(inputStream);
				return response.toString();
			} else {
				return null;
			}
		} catch (Exception e) {
			LOGGER.error("execute post failed", e);
			return null;
		} finally {
			if (method != null) {
				try {
					method.releaseConnection();
				} catch (Exception e) {
					// nothing need to do
				}
			}
		}
	}
}
