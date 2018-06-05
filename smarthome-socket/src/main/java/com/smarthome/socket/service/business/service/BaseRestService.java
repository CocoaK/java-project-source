package com.smarthome.socket.service.business.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import com.smarthome.socket.common.model.ResultEntity;

public abstract class BaseRestService<T> implements IBaseRestService<T>{

	public abstract RestTemplate getRestTemplate();
	
	/** 服务地址 */
	protected static String restServiceUrl;
	
	/** 添加的URL */
	protected String createUrl = "create";
	/** 删除的URL */
	protected String deleteUrl = "delete";
	/** 修改的URL */
	protected String updateUrl = "update";
	/** 查询的URL */
	protected String queryUrl = "query";
	
	/**
	 * POST提交，根据指定clazz反序列化
	 * @param url
	 * @param params
	 * @param clazz
	 * @return
	 */
	@Override
	public <A> A postForResultObject(String url,Map params){
		MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>(params);	
		List<MediaType> acceptableMediaTypes = new ArrayList<MediaType>();
		acceptableMediaTypes.add(MediaType.ALL);

		// Prepare header
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(acceptableMediaTypes);
		headers.setContentType(MediaType.MULTIPART_FORM_DATA);
		HttpEntity<MultiValueMap<String,Object>> httpEntity = new HttpEntity<MultiValueMap<String,Object>>(map,headers);
		ResponseEntity<A> res = getRestTemplate().exchange(url,
		        HttpMethod.POST,
		        httpEntity,
		        new ParameterizedTypeReference<A>() {});
		A re = res.getBody();
		return re;	
		
	}
	
	/**
	 * post提交键值对参数
	 * @param url
	 * @param params
	 * @param ptr
	 * @return 根据指定类型，反序列化
	 */
	public <A> A postForResultObject(String url,Map params,ParameterizedTypeReference<A> ptr){
		MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>(params);	
		List<MediaType> acceptableMediaTypes = new ArrayList<MediaType>();
		acceptableMediaTypes.add(MediaType.ALL);
		// Prepare header
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(acceptableMediaTypes);
		headers.setContentType(MediaType.MULTIPART_FORM_DATA);
		HttpEntity<MultiValueMap<String,Object>> httpEntity = new HttpEntity<MultiValueMap<String,Object>>(map,headers);
		ResponseEntity<A> res = getRestTemplate().exchange(url,
		        HttpMethod.POST,
		        httpEntity,
		        ptr);
		A re = res.getBody();
		return re;
		
	}
	
	/**
	 * POST提交，返回对象集合
	 * @param url
	 * @param params
	 * @return
	 */
	@Override
	public Object postForResultMap(String url,Map params){
		int code = 0;
		String msg = "unckown error";
		try {
			return postForResultObject(url, params);
		} catch (Exception e) {
			e.printStackTrace();
			msg = e.getMessage();
		}		
		return proccessResultEntity(code, msg, "");
	}
	
	/**
	 * POST提交，返回对象集合
	 * @param url
	 * @param params
	 * @return
	 */
	public ResultEntity postForResultEntity(String url,Map params){
		int code = 0;
		String msg = "unckown error";
		try {
			ResultEntity<String> ret = postForResultObject(url, params);
			return ret;
		} catch (Exception e) {
			e.printStackTrace();
			msg = e.getMessage();
		}		
		return proccessResultEntity(code, msg, "");
	}
	
	@Override
	public <A> A postForObject(String url,Object params,ParameterizedTypeReference<A> ptr){
	 List<MediaType> acceptableMediaTypes = new ArrayList<MediaType>();
		acceptableMediaTypes.add(MediaType.ALL);
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(acceptableMediaTypes);
		headers.setContentType(MediaType.MULTIPART_FORM_DATA);
		  RestTemplate template = getRestTemplate();
		  HttpEntity<Object> entity = new HttpEntity<Object>(params);
			ResponseEntity<A> res = template.exchange(
					url,
			        HttpMethod.POST,
			        entity,
			        ptr);		
			return res.getBody(); 
		
	}

	/**
	 * 传递常用结果消息
	 * @param code
	 * @param message
	 * @param data
	 * @return
	 */
	@Override
	public <A> ResultEntity<A> proccessResultEntity(int code,String message,A data){
		return new ResultEntity<A>(code,message,data);
	}

	public String getRestServiceUrl() {
		return restServiceUrl;
	}

	@Value("${rest.service.url}")
	public void setRestServiceUrl(String restServiceUrl) {
		this.restServiceUrl = restServiceUrl;
	}
	
	@Override
	public ResultEntity<String> addForResultEntity(T record) throws Exception {
		return postForResultObject(getRestServiceUrl()+"/"+createUrl, BeanUtils.describe(record), new ParameterizedTypeReference<ResultEntity<String>>() {});
	}
	@Override
	public <A> A addForResultEntity(Map map) throws Exception {
		return postForResultObject(getRestServiceUrl()+"/"+createUrl, map, new ParameterizedTypeReference<A>() {});
	}
	
	@Override
	public ResultEntity<String> deleteByIdForResultEntity(final Integer id) throws Exception {
		return postForResultObject(getRestServiceUrl()+"/"+deleteUrl, new HashMap<String, Integer>(){
			{
				put("id", id);
			}
		},new ParameterizedTypeReference<ResultEntity<String>>() {});
	}
	
	@Override
	public ResultEntity<String> deleteByIdForResultEntity(Map map) throws Exception {
		return postForResultObject(getRestServiceUrl()+"/"+deleteUrl, map,new ParameterizedTypeReference<ResultEntity<String>>() {});
	}
	
	@Override
	public T getEntity(T record) throws Exception {
		return postForResultObject(getRestServiceUrl()+"/"+queryUrl, BeanUtils.describe(record), new ParameterizedTypeReference<T>() {});
	}
	
	@Override
	public ResultEntity<String> updateForResultEntity(T record) throws Exception {
		return postForResultObject(getRestServiceUrl()+"/"+updateUrl, BeanUtils.describe(record), new ParameterizedTypeReference<ResultEntity<String>>() {});
	}
	
	@Override
	public ResultEntity<String> updateForResultEntity(Map map) throws Exception {
		return postForResultObject(getRestServiceUrl()+"/"+updateUrl, map, new ParameterizedTypeReference<ResultEntity<String>>() {});
	}

	public String getCreateUrl() {
		return createUrl;
	}

	public void setCreateUrl(String createUrl) {
		this.createUrl = createUrl;
	}

	public String getDeleteUrl() {
		return deleteUrl;
	}

	public void setDeleteUrl(String deleteUrl) {
		this.deleteUrl = deleteUrl;
	}

	public String getUpdateUrl() {
		return updateUrl;
	}

	public void setUpdateUrl(String updateUrl) {
		this.updateUrl = updateUrl;
	}

	public String getQueryUrl() {
		return queryUrl;
	}

	public void setQueryUrl(String queryUrl) {
		this.queryUrl = queryUrl;
	}
	
}
