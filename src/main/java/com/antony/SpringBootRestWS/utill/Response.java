package com.antony.SpringBootRestWS.utill;

import java.io.Serializable;

public class Response implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String responseType;
	private Object responseValue;

	public String getResponseType() {
		return responseType;
	}

	public void setResponseType(String responseType) {
		this.responseType = responseType;
	}

	public Object getResponseValue() {
		return responseValue;
	}

	public void setResponseValue(Object responseValue) {
		this.responseValue = responseValue;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Response(String responseType, Object responseValue) {
		super();
		this.responseType = responseType;
		this.responseValue = responseValue;
	}

	public Response() {
		super();
	}

}
