package com.jt.dubbo.common.service;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.druid.stat.TableStat.Name;
import com.alibaba.druid.util.StringUtils;

@Service
public class HttpClientService {

    private static final Logger LOGGER = LoggerFactory.getLogger(HttpClientService.class);

    @Autowired(required=false)
    private CloseableHttpClient httpClient;

    @Autowired(required=false)
    private RequestConfig requestConfig;

    public String doGet(String url,Map<String,String> params,String charset) throws ClientProtocolException, IOException{
    	String result=null;
    	if(StringUtils.isEmpty(charset))
    		charset="UTF-8";
    	if(params!=null){
    		URIBuilder builder=null;
			try {
				builder = new URIBuilder(url);
    		for(Map.Entry<String, String> entry:params.entrySet()){
    			builder.addParameter(entry.getKey(), entry.getValue());
    		}
    		//自动拼上参数的？和& 符号
    		url=builder.build().toString();
			} catch (URISyntaxException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    	System.out.println("请求路径： "+url);
    	//定义请求类型
    	HttpGet httpGet=new HttpGet(url);
    	httpGet.setConfig(requestConfig);
    	CloseableHttpResponse httpResponse=httpClient.execute(httpGet);
    	if(httpResponse.getStatusLine().getStatusCode()==200){
    		result=EntityUtils.toString(httpResponse.getEntity(),charset);
    	}
		return result;
		}
    public String doGet(String url,Map<String,String> params) throws ClientProtocolException, IOException{
    	return doGet(url,params,null);
    }
    public String doGet(String url) throws ClientProtocolException, IOException{
    	return doGet(url,null,null);
    }
    public String doPost(String url,Map<String ,String> param,String charset){
    	String result=null;
    	if(StringUtils.isEmpty(charset))
    	{
    		charset="UTF-8";
    	}
    	//先创建请求对象
    	HttpPost httpPost=new HttpPost(url);
    	httpPost.setConfig(requestConfig);
    	try {
			if (param != null) {
				List<NameValuePair> parameters = new ArrayList<NameValuePair>();
				for (Map.Entry<String, String> entry : param.entrySet()) {
					NameValuePair nameValuePair = new BasicNameValuePair(entry.getKey(), entry.getValue());
					parameters.add(nameValuePair);
				}
				UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(parameters, charset);
				httpPost.setEntity(formEntity);
			}//if 
			//实现post请求
			CloseableHttpResponse httpResponse=httpClient.execute(httpPost);
			if(httpResponse.getStatusLine().getStatusCode()==200){
				result=EntityUtils.toString(httpResponse.getEntity());
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
    	//
    	return result;
    }
    public String doPost(String url,Map<String ,String> param){
    	return  doPost(url,param,null);
    }
    public String doPost(String url){
    	return  doPost(url,null,null);
    }
}
