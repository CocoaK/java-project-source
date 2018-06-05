package com.biencloud.smarthome.web.rest.client;

import java.util.Map;

/**
 * 基于Rest服务的客服端接口。
 * @author kouy
 * @since 1.0 2013-8-20
 */
public interface RestService {
	
	/**
	 * 发送指定的URL请求（对应HTTP协议的GET方法）并返回响应结果。适用情况：<br/>
	 * 1、请求参数少，可以编码到URL中；<br/>
	 * 2、包括查询、更新（只更新个别字段）、删除数据等操作；<br/>
	 * @param url 请求URL
	 * @param entityTypeRef 返回的实体类型引用，创建匿名类即可
	 * @param urlVars 变量按顺序编码到URL的参数中
	 * @return
	 */
	public <T> RestResult<T> sendByGet(String url, 
			EntityTypeReference<T> entityTypeRef, Object... urlVars);
	
	/**
	 * 发送指定的URL请求（对应HTTP协议的GET方法）并返回响应结果。适用情况：<br/>
	 * 1、请求参数少，可以编码到URL中；<br/>
	 * 2、包括查询、更新（只更新个别字段）、删除数据等操作；<br/>
	 * @param url 请求URL
	 * @param entityTypeRef 返回的实体类型引用，创建匿名类即可
	 * @param urlVars 变量按名称编码到URL的参数中
	 * @return
	 */
	public <T> RestResult<T> sendByGet(String url, 
			EntityTypeReference<T> entityTypeRef, Map<String, ?> urlVars);
	
	/**
	 * 发送指定的URL请求（对应HTTP协议的POST方法）并返回响应结果。适用情况：<br/>
	 * 1、需要查询数据的操作，且请求参数多，不能或不方便编码到URL中；<br/>
	 * 2、需要新增、更新、删除多个数据等操作；<br/>
	 * @param url 请求URL
	 * @param request 请求数据
	 * @param entityTypeRef 返回的实体类型引用，创建匿名类即可
	 * @param urlVars 变量按顺序编码到URL的参数中
	 * @return
	 */
	public <T> RestResult<T> sendByPost(String url, Object request, 
			EntityTypeReference<T> entityTypeRef, Object... urlVars);
	
	/**
	 * 发送指定的URL请求（对应HTTP协议的POST方法）并返回响应结果。适用情况：<br/>
	 * 1、需要查询数据的操作，且请求参数多，不能或不方便编码到URL中；<br/>
	 * 2、需要新增、更新、删除多个数据等操作；<br/>
	 * @param url 请求URL
	 * @param request 请求数据
	 * @param entityTypeRef 返回的实体类型引用，创建匿名类即可
	 * @param urlVars 变量按名称编码到URL的参数中
	 * @return
	 */
	public <T> RestResult<T> sendByPost(String url, Object request, 
			EntityTypeReference<T> entityTypeRef, Map<String, ?> urlVars);
}
