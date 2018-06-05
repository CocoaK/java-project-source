package com.biencloud.smarthome.service.common.interceptor;

public class AuthorizationException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8967556836372150728L;

	public AuthorizationException() {
		super("authorization error");
	}
}
