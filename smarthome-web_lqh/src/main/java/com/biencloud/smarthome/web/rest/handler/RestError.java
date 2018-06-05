package com.biencloud.smarthome.web.rest.handler;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.util.ObjectUtils;

/**
 * 基于Rest的错误信息类。
 * 
 * @since 1.0 2013-9-20
 * @since 1.0 2013-3-7
 */
public class RestError {

	private int status;
    private int code;
    private String message;
//    private String developerMessage;
//    private String moreInfoUrl;
    private Throwable throwable;

    
    /**
     * 获取响应失败时的HTTP状态码
     * @return
     */
    public int getStatus() {
        return status;
    }
    public void setStatus(int status) {
		this.status = status;
	}

    /**
     * 获取响应失败时的错误代码
     * @return
     */
	public void setCode(int code) {
		this.code = code;
	}
	public int getCode() {
        return code;
    }
	
	/**
     * 获取响应失败时的提示信息
     * @return
     */
	public void setMessage(String message) {
		this.message = message;
	}
	public String getMessage() {
        return message;
    }

	/**
     * 获取响应失败时的详细信息
     * @return
     */
//	public void setDeveloperMessage(String developerMessage) {
//		this.developerMessage = developerMessage;
//	}
//	public String getDeveloperMessage() {
//        return developerMessage;
//    }

	/**
     * 获取响应失败时可分析信息的地址
     * @return
     */
//	public void setMoreInfoUrl(String moreInfoUrl) {
//		this.moreInfoUrl = moreInfoUrl;
//	}
//	public String getMoreInfoUrl() {
//        return moreInfoUrl;
//    }

	/**
     * 获取响应失败时的异常信息
     * @return
     */
	public void setThrowable(Throwable throwable) {
		this.throwable = throwable;
	}
	public Throwable getThrowable() {
        return throwable;
    }
	

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o instanceof RestError) {
            RestError re = (RestError) o;
            return ObjectUtils.nullSafeEquals(getStatus(), re.getStatus()) &&
                    getCode() == re.getCode() &&
                    ObjectUtils.nullSafeEquals(getMessage(), re.getMessage()) &&
//                    ObjectUtils.nullSafeEquals(getDeveloperMessage(), re.getDeveloperMessage()) &&
//                    ObjectUtils.nullSafeEquals(getMoreInfoUrl(), re.getMoreInfoUrl()) &&
                    ObjectUtils.nullSafeEquals(getThrowable(), re.getThrowable());
        }

        return false;
    }

    @Override
    public int hashCode() {
        return ObjectUtils.nullSafeHashCode(new Object[]{
                getStatus(), getCode(), getMessage(), getThrowable()
        });
    }

    @Override
    public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
