package com.wenxuan.zhihuspider.util;

import com.alibaba.fastjson2.JSON;
import com.wenxuan.zhihuspider.spider.pojo.zhihuuser.FollowersPage;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.util.EntityUtils;

import javax.net.ssl.SSLContext;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class HttpClientKit {

	/**
	 * 请求来自的网站
	 */
	private static final String REFERER = "https://aiqicha.baidu.com/qualificationcert/detail?pid=32320107668725&dataId=efad8bfab795a3d70572a744ff6375d9";

	/**
	 * 请求的用户token
	 */
	private static final String X_AUTH_TOKEN = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxOTgwNTE4MDA2MCIsImlhdCI6MTY4NjU0NTUzOSwiZXhwIjoxNjg5MTM3NTM5fQ.W4DZNuOU6WyPuAskgXGmonkKBkapzKo2M2dXTJurDNTpL35L4sr2hpc2QjTCBLUL_iYALARH1wMly_Bv71bc0w";

	/**
	 * 请求的用户id
	 */
	private static final String X_TYCID = "30e20a20feb111edb2a78beedfd1b75d";

	/**
	 * 请求的cookie
	 */
	private static final String AUTH_TOKEN = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxOTgwNTE4MDA2MCIsImlhdCI6MTY4NjU0NTUzOSwiZXhwIjoxNjg5MTM3NTM5fQ.W4DZNuOU6WyPuAskgXGmonkKBkapzKo2M2dXTJurDNTpL35L4sr2hpc2QjTCBLUL_iYALARH1wMly_Bv71bc0w";


	public static String doPost(String url,String context) throws InterruptedException, ExecutionException {
		String string=null;
		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpPost httppost = new HttpPost(url);
		// 创建参数队列
		List<NameValuePair> formparams = new ArrayList<NameValuePair>();
		formparams.add(new BasicNameValuePair("context", context));
		RequestConfig config = RequestConfig.custom()
				.setConnectTimeout(5000)
				.setConnectionRequestTimeout(5000)
				.setSocketTimeout(5000)
				.build();

		httppost.setConfig(config);
		try {
			CloseableHttpResponse response = httpclient.execute(httppost); // 执行http
			try {
				HttpEntity entity = response.getEntity(); // 获取返回实体
				if (entity != null) {
					string = EntityUtils.toString(entity, "UTF-8");
				}
			} finally {
				response.close();
			}
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			// 关闭连接,释放资源
			try {
				httpclient.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return string;
	}

	public static SSLConnectionSocketFactory createSSLConnSocketFactory() {
		SSLContext sslcontext = null;
		try {
			sslcontext = SSLContexts.custom().loadTrustMaterial(null, (x509Certificates, s) -> true).build();
		} catch (NoSuchAlgorithmException | KeyManagementException | KeyStoreException e) {
			e.printStackTrace();
		}
		assert sslcontext != null;
		return new SSLConnectionSocketFactory(sslcontext, new NoopHostnameVerifier());
	}

	public static String doGet(String url, Header[] headers) throws InterruptedException, ExecutionException {
		String string=null;
		CloseableHttpClient httpclient = HttpClients.custom().setSSLSocketFactory(createSSLConnSocketFactory()).build();
		HttpGet httpget = new HttpGet(url);
		RequestConfig config = RequestConfig.custom()
				.setCookieSpec(CookieSpecs.IGNORE_COOKIES)
				.setConnectTimeout(5000)
				.setConnectionRequestTimeout(5000)
				.setSocketTimeout(5000)

				//	        .setProxy(proxy)
				.build();
		httpget.setConfig(config);
		httpget.setHeader("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:109.0) Gecko/20100101 Firefox/117.0");
		httpget.setHeaders(headers);
		try {
			try (CloseableHttpResponse response = httpclient.execute(httpget)) {
				// 获取返回实体
				HttpEntity entity = response.getEntity();
				if (entity != null) {
					string = EntityUtils.toString(entity, "UTF-8");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			try {
				httpclient.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return string;
	}

//	public static String doGet(String url, SpiderHeader spiderHeader) throws InterruptedException, ExecutionException {
//		String string=null;
//		CloseableHttpClient httpclient = HttpClients.custom().setSSLSocketFactory(createSSLConnSocketFactory()).build();
//		HttpGet httpget = new HttpGet(url);
//		RequestConfig config = RequestConfig.custom()
//				.setCookieSpec(CookieSpecs.IGNORE_COOKIES)
//				.setConnectTimeout(300000)
//				.setConnectionRequestTimeout(300000)
//				.setSocketTimeout(300000)
//
//				//	        .setProxy(proxy)
//				.build();
//		httpget.setConfig(config);
//		httpget.setHeader("User-Agent","Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3218.0 Safari/537.36");
////		httpget.setHeader("User-Agent", USER_AGENT);
//		httpget.setHeader("Referer", spiderHeader.getReferer());
//		httpget.setHeader("X-Auth-Token", spiderHeader.getAuthToken());
//		httpget.setHeader("X-Tycid", spiderHeader.getTycId());
//		httpget.setHeader("Cookie", spiderHeader.getCookie());
//		try {
//			CloseableHttpResponse response = httpclient.execute(httpget);
//			try {
////				System.out.println(""+response.getStatusLine().getStatusCode());
//				HttpEntity entity = response.getEntity(); // 获取返回实体
//				if (entity != null) {
//					string = EntityUtils.toString(entity, "UTF-8");
//				}
//			} finally {
//				response.close();
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//
//		} finally {
//			try {
//				httpclient.close();
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		}
//		return string;
//	}

	public static String doGetForNoCookie(String url) throws InterruptedException, ExecutionException {
		String string=null;
		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpGet httpget = new HttpGet(url);
		RequestConfig config = RequestConfig.custom()
				.setConnectTimeout(5000)
				.setConnectionRequestTimeout(5000)
				.setSocketTimeout(5000)

				//	        .setProxy(proxy)
				.build();
		httpget.setConfig(config);
		httpget.setHeader("User-Agent","Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3218.0 Safari/537.36");
//		httpget.setHeader("User-Agent", USER_AGENT);
		httpget.setHeader("Referer", REFERER);
		httpget.setHeader("X-Auth-Token", X_AUTH_TOKEN);
		httpget.setHeader("X-Tycid", X_TYCID);
		httpget.setHeader("Cookie", AUTH_TOKEN);
		try {
			CloseableHttpResponse response = httpclient.execute(httpget);
			try {
//				System.out.println(""+response.getStatusLine().getStatusCode());
				HttpEntity entity = response.getEntity(); // 获取返回实体
				if (entity != null) {
					string = EntityUtils.toString(entity, "UTF-8");
				}
			} finally {
				response.close();
			}
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			try {
				httpclient.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return string;
	}


	public static String gethttpClinet(String url, String ipPort) throws Exception {

		CloseableHttpClient httpclient = HttpClients.createDefault();

		String string = null;
		// 创建httpget.
		HttpGet httpget = new HttpGet(url);

		RequestConfig config = null;
		// 判断是否有ip
		if (null != ipPort && !"".equals(ipPort)) {
			String[] strArr = ipPort.split(":");
			String ip = strArr[0].trim();
			Integer port = Integer.parseInt(strArr[1].trim());
			HttpHost proxy = new HttpHost(ip, port);
			config = RequestConfig.custom().setProxy(proxy).setConnectTimeout(10 * 1000).setSocketTimeout(20 * 1000)
					.build();
		} else {
			config = RequestConfig.custom().setConnectTimeout(10 * 1000).setSocketTimeout(20 * 1000).build();
		}

		httpget.setConfig(config);
		httpget.setHeader("User-Agent",
				"Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2661.102 Safari/537.36");

		// 执行get请求
		CloseableHttpResponse response = httpclient.execute(httpget);
		// 获取响应实体
		HttpEntity entity = response.getEntity();

		// 打印响应状态
		Integer statu = response.getStatusLine().getStatusCode();
		if (entity != null) {
			// 打印响应内容长度
			//System.out.println("Response content length: " + entity.getContentLength());

			// 打印响应内容
			string = EntityUtils.toString(entity, "gb2312");
			// System.out.println("打印响应内容"+string);
		}
		response.close();
		// 关闭连接,释放资源
		httpclient.close();

		return string;

	}


	public static String getBossJobListJson(String url, String ipPort, String cookie) throws Exception {

		CloseableHttpClient httpclient = HttpClients.createDefault();

		String string = null;
		// 创建httpget.
		HttpGet httpget = new HttpGet(url);

		RequestConfig config = null;
		// 判断是否有ip
		if (null != ipPort && !"".equals(ipPort)) {
			String[] strArr = ipPort.split(":");
			String ip = strArr[0].trim();
			Integer port = Integer.parseInt(strArr[1].trim());
			HttpHost proxy = new HttpHost(ip, port);
			config = RequestConfig.custom().setProxy(proxy).setConnectTimeout(10 * 1000).setSocketTimeout(20 * 1000)
					.build();
		} else {
			config = RequestConfig.custom().setConnectTimeout(10 * 1000).setSocketTimeout(20 * 1000).build();
		}

		httpget.setConfig(config);
		httpget.setHeader("User-Agent",
				"Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2661.102 Safari/537.36");
		httpget.setHeader("cookie",cookie);

		// 执行get请求
		CloseableHttpResponse response = httpclient.execute(httpget);
		// 获取响应实体
		HttpEntity entity = response.getEntity();

		// 打印响应状态
		Integer statu = response.getStatusLine().getStatusCode();
		if (entity != null) {
			// 打印响应内容长度
			//System.out.println("Response content length: " + entity.getContentLength());

			// 打印响应内容
			string = EntityUtils.toString(entity, "gb2312");
			// System.out.println("打印响应内容"+string);
		}
		response.close();
		// 关闭连接,释放资源
		httpclient.close();

		return string;

	}

	public static String getHttpClientUtf8(String url, String ipPort) throws Exception {

		CloseableHttpClient httpclient = HttpClients.createDefault();

		String string = null;
		// 创建httpget.
		HttpGet httpget = new HttpGet(url);

		RequestConfig config = null;
		// 判断是否有ip
		if (null != ipPort && !"".equals(ipPort)) {
			String[] strArr = ipPort.split(":");
			String ip = strArr[0].trim();
			Integer port = Integer.parseInt(strArr[1].trim());
			HttpHost proxy = new HttpHost(ip, port);
			config = RequestConfig.custom().setProxy(proxy).setConnectTimeout(10 * 1000).setSocketTimeout(20 * 1000)
					.build();
		} else {
			config = RequestConfig.custom().setConnectTimeout(10 * 1000).setSocketTimeout(20 * 1000).build();
		}

		httpget.setConfig(config);
		httpget.setHeader("User-Agent",
				"Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2661.102 Safari/537.36");

		// 执行get请求
		CloseableHttpResponse response = httpclient.execute(httpget);
		// 获取响应实体
		HttpEntity entity = response.getEntity();

		// 打印响应状态
		Integer statu = response.getStatusLine().getStatusCode();
		if (entity != null) {
			// 打印响应内容长度
			//System.out.println("Response content length: " + entity.getContentLength());

			// 打印响应内容
			string = EntityUtils.toString(entity, "utf-8");
			// System.out.println("打印响应内容"+string);
		}
		response.close();
		// 关闭连接,释放资源
		httpclient.close();

		return string;

	}

	public static String getHttpClientZL(String url, String ipPort) throws Exception {

		CloseableHttpClient httpclient = HttpClients.createDefault();

		String string = null;
		// 创建httpget.
		HttpGet httpget = new HttpGet(url);

		RequestConfig config = null;
		// 判断是否有ip
		if (null != ipPort && !"".equals(ipPort)) {
			String[] strArr = ipPort.split(":");
			String ip = strArr[0].trim();
			Integer port = Integer.parseInt(strArr[1].trim());
			HttpHost proxy = new HttpHost(ip, port);
			config = RequestConfig.custom().setProxy(proxy).setConnectTimeout(10 * 1000).setSocketTimeout(20 * 1000)
					.build();
		} else {
			config = RequestConfig.custom().setConnectTimeout(10 * 1000).setSocketTimeout(20 * 1000).build();
		}

		httpget.setConfig(config);
		httpget.setHeader("User-Agent",
				"Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2661.102 Safari/537.36");
		httpget.setHeader("Cookie","x-zp-client-id=528f1094-97f1-4301-8c12-b0560b2b7fea; sts_deviceid=182f6db0057162-0b1d8a755d54e1-26021851-2073600-182f6db00585fa; BEST_EMPLOYER_SHOW_TIME=[1668603189353]; FSSBBIl1UgzbN7NO=5xWbsQfN4AZW4RxjeSfswFXMPJnLxqBAOu8Y58DfSk3TNEvzxSFfj1zdk5dqd7E3lJFzZaiCw_S6_48TGExmzsa; locationInfo_search={%22code%22:%22635%22%2C%22name%22:%22%E5%8D%97%E4%BA%AC%22%2C%22message%22:%22%E5%8C%B9%E9%85%8D%E5%88%B0%E5%B8%82%E7%BA%A7%E7%BC%96%E7%A0%81%22}; _uab_collina=168360271191352575481684; adfcid2=none; adfbid2=0; LastCity=%E5%8D%97%E4%BA%AC; LastCity%5Fid=635; sensorsdata2015jssdkchannel=%7B%22prop%22%3A%7B%22_sa_channel_landing_url%22%3A%22%22%7D%7D; urlfrom2=121126445; ssxmod_itna2=iqmxuD9D2GDQ3BK40Lh8RDBQKOO9rg+xA=RxqqD/iYYDFrxAKpHnaGFmsp=+Hkl4H=Yf4tla2w03+iAXFffPQw37Yr7ZPaEkspcH0+TT9ajf0pLhW2DbDKLh3DLxijK4D===; ssxmod_itna=eqmxyDB7DQLreGHD8K2x0KnemqO02=07C1x0yDi=rDSxGKidDqxBWWleRbe24hfP3tPe0QHljYpipwbCbxITeIC4qddDU4i8DCLAm4rDeW=D5xGoDPxDeDAWqGaDb4Dru5qGP9R6wv6ODWKDKx0kDY5DwPYGDiPD729DdG24jayF5K7+DiHrc=0DjxG1DQ5DsEREF4DCAfS4Mayvh4i3v7FhP40OD0Kwuca+DBRkZ1y7c9TaWPxNOeh3EWnDY02edWeKTerr+Awx8jGPGQbqfWm3hgQMdYD=; acw_tc=276082a116859577642353990ed028ac28e94a58eb7a3cd62702a2a7c7aeab; acw_sc__v2=647dac8441cbec385b550f676a636e99221edd06; Hm_lvt_38ba284938d5eddca645bb5e02a02006=1684393194,1684736978,1684921956,1685957767; selectCity_search=635; zp_passport_deepknow_sessionId=8eeaaa4ds98e4b447a9c28ca3be23c301a3b; at=c11e9a4d3a5e4268ac04f7e33c217914; rt=12245d8017ab4264ab2433c2731dd019; sensorsdata2015jssdkcross=%7B%22distinct_id%22%3A%221171025129%22%2C%22first_id%22%3A%22182f6da66537ee-0c53ffb814c9fd8-26021851-2073600-182f6da6654c03%22%2C%22props%22%3A%7B%22%24latest_traffic_source_type%22%3A%22%E7%9B%B4%E6%8E%A5%E6%B5%81%E9%87%8F%22%2C%22%24latest_search_keyword%22%3A%22%E6%9C%AA%E5%8F%96%E5%88%B0%E5%80%BC_%E7%9B%B4%E6%8E%A5%E6%89%93%E5%BC%80%22%2C%22%24latest_referrer%22%3A%22%22%2C%22%24latest_utm_source%22%3A%22baidupcpz%22%2C%22%24latest_utm_medium%22%3A%22cpt%22%7D%2C%22identities%22%3A%22eyIkaWRlbnRpdHlfY29va2llX2lkIjoiMTgyZjZkYTY2NTM3ZWUtMGM1M2ZmYjgxNGM5ZmQ4LTI2MDIxODUxLTIwNzM2MDAtMTgyZjZkYTY2NTRjMDMiLCIkaWRlbnRpdHlfbG9naW5faWQiOiIxMTcxMDI1MTI5In0%3D%22%2C%22history_login_id%22%3A%7B%22name%22%3A%22%24identity_login_id%22%2C%22value%22%3A%221171025129%22%7D%2C%22%24device_id%22%3A%22182f6da66537ee-0c53ffb814c9fd8-26021851-2073600-182f6da6654c03%22%7D; ZP_OLD_FLAG=false; sts_sg=1; sts_sid=1888aef5c518ae-010c85363bcf0d-26031a51-2073600-1888aef5c52120c; sts_chnlsid=Unknown; zp_src_url=https%3A%2F%2Fpassport.zhaopin.com%2F; ZL_REPORT_GLOBAL={%22/resume/new%22:{%22actionid%22:%22c12b1bcf-42bd-410c-86ad-1ee83c141316%22%2C%22funczone%22:%22addrsm_ok_rcm%22}}; sts_evtseq=8; Hm_lpvt_38ba284938d5eddca645bb5e02a02006=1685958247; FSSBBIl1UgzbN7NP=5RoCXRKpe.bVqqqDEPBmh0G.J8vCidbzAGqu0PoogXzg3bIs6vGgGp_gxjYwLsykhclmsEdUSs5Almk_pcV4mLmo_3KCRCoYxDqSYPTTRYlyG1Q0X.YRMHnV8LXG3_DBO9gVY63qdOXU3WUc7VEscLCHG6UDxIKklp6agzH3VnbFt9EPeGwMQX3WFQOZln0op2o3FlNlIcGvyewAayzj..8DpKssrZbb.l2JnD6LKWniC7CIUcwJQAc9Z09seEDCwMRIZnJ7_AWf2a8bcUTNORDWwIKfCxoj5VmECnYLlFt5q");

		// 执行get请求
		CloseableHttpResponse response = httpclient.execute(httpget);
		// 获取响应实体
		HttpEntity entity = response.getEntity();

		// 打印响应状态
		Integer statu = response.getStatusLine().getStatusCode();
		if (entity != null) {
			// 打印响应内容长度
			//System.out.println("Response content length: " + entity.getContentLength());

			// 打印响应内容
			string = EntityUtils.toString(entity, "utf-8");
			// System.out.println("打印响应内容"+string);
		}
		response.close();
		// 关闭连接,释放资源
		httpclient.close();

		return string;

	}

	public static void main(String[] args) throws Exception {
		Header[] headers = new Header[5];
		headers[0] = new BasicHeader("Cookie","_zap=79ae751a-7d38-4aa6-b448-05a0691caf7d; d_c0=ACDXe9FMRBaPTkQJMFHFZEgxQ1NIOLgRNUE=|1675320662; Hm_lvt_98beee57fd2ef70ccdd5ca52b9740c49=1694338672,1694410073,1694445935,1694483047; YD00517437729195%3AWM_NI=PJ1Z3UwhI5J4V1LZSy7lOwW7LapM0lA5Uvx6ag8EhLc%2Bd54YjOefeyulOI5rtTbtS3Y9fmHvRMkzMh8HCBz%2B329YRDnk3%2F0DCtaQJkbjwBF%2Frc2QNPbpopJU%2FPGsxWAGZkg%3D; YD00517437729195%3AWM_NIKE=9ca17ae2e6ffcda170e2e6ee9ac25f8e8ea2cceb25b0968fb3c85b928a8fb0c13bb6968494dc4fabb78daeef2af0fea7c3b92a9496ffbbb25fb5b887d6c94096ef84bbd273f5b48198d63d90bfc0aeb36883eb9b97fb3ea3b9f8a6eb48a1f5acd6b753aa91ab87e873b7b481aab87ab88cae95d2399be7aa8ecb7e9293a59bd064fc988da3f921f1eef7bad150aeb5fdd0b35081acafb2c961a2b6b9a3c525b2bdfeb2cd5ca2b0a191c95fed908994fb5d8399968ce637e2a3; YD00517437729195%3AWM_TID=ho%2FV0HrQWAVFVEQVBBORKRJU7DSN1AFo; __snaker__id=KvCZTznQRfvO5gnm; z_c0=2|1:0|10:1694340186|4:z_c0|80:MS4xN0FQMURRQUFBQUFtQUFBQVlBSlZUUTNLNTJVR2RmWmVoUk5HeS1YbzBndEtBWnZUcUc0XzhBPT0=|583eed7d76bf4979c399cec6e9bd7f8c383c91799eef810412d1038ce0b1b523; q_c1=0045de7caf104e84a9b9538a7ebb40a5|1691480501000|1691480501000; _xsrf=FA6HuQZtM8mJlQpbA4wkk4Evf4ZHp2iu; KLBRSID=0a401b23e8a71b70de2f4b37f5b4e379|1694483124|1694483043; tst=f; Hm_lpvt_98beee57fd2ef70ccdd5ca52b9740c49=1694483122; SESSIONID=GwhEC2sfJ4jFLGROSbAcYs1cGghmOccWku6wGjhNVmq; JOID=VlsUCkiuXE69YcPLYKIM2JC3go9z7iku8iX2ug7jbybzJ4OeLcOUs9RhxsFiFLVgJTPoKn2X7xHr_aHh9O_MDUs=; osd=UF4UC0ioWU68YcXOYKMM3pW3g4916ykv8iPzug_jaSPzJoOYKMOVs9JkxsBiErBgJDPuL32W7xfu_aDh8urMDEs=");
		headers[1] = new BasicHeader("x-zse-93","101_3_3.0");
		headers[2] = new BasicHeader("x-zse-96", "2.0_kaq9f2IF1ibqZzy6fmqPNlrfAHxbOiiH0LmISySLSSKWdl/x6b5VBXJWX8tBKqyq");
		headers[3] = new BasicHeader("x-zst-81", "3_2.0VhnTj77m-qofgh3TxTP0Ee90r4nL-BOB8gN97Au0rRFxrRF0w4S1Fve97HnxEHt0miFGkDQye8FL7AtqM6O1VDQyQ6nxrRPCHukMoCXBEgOsiRP0XL2ZUBXmDDV9qhnyTXFMnXcTF_ntRueThLeBD9NmeLep9GSGUUFfEq9LDhLxf9w9iUwMzwxONwNOxcOLq9XfbrC9tbLPv0V9EcHMchu1XwOGcBxKGMg028OG_9X83hHM-gN_2hc1IgYOOvLYUgcY-uNK_qcfavcBsDpLDhUGWgpprHtqqq2CQg9YqJeMzUc82UFqCcomDuY1C9F95qfzWD9_xBVBJiSVUgFG27oq1qw90Jxf_JXMxBS93gFXjrx1yqV8rgefYGeMZBtYYgw1XccMbbO16MO_tB2Vr0N1J0w8r4eGZwxLsh9OjCSmVgOM-hXB68eC");
		headers[4] = new BasicHeader("Referer", "https://www.zhihu.com/people/xie-gong-62-15/following");
		String result = doGet("https://www.zhihu.com/api/v4/members/xie-gong-62-15/followees?include=data[*].answer_count,articles_count,gender,follower_count,is_followed,is_following,badge[?(type=best_answerer)].topics&offset=0&limit=20",headers);
		System.out.println(result);
		FollowersPage followers = JSON.parseObject(result, FollowersPage.class);
		System.out.println(followers);
	}


}
