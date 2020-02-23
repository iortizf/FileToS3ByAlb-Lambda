package com.digitalizacion.indexadores.files.entities;

import java.util.HashMap;
import java.util.Map;

public class ResponseJson {
	
	private boolean isBase64Encoded;
    private Integer statusCode;
    private String statusDescription;
    private Map<String, String> headers;
    private String body;
        
    
	public ResponseJson(Integer statusCode, String statusDescription) {
		super();
		this.isBase64Encoded = false;
		this.statusCode = statusCode;
		this.statusDescription = statusDescription;
		this.headers = new HashMap<>();
		this.headers.put("Set-cookie", "cookies");
		this.headers.put("Content-Type", "application/json");
	}

	public ResponseJson(Integer statusCode, String statusDescription, String body) {
		super();
		this.isBase64Encoded = false;
		this.statusCode = statusCode;
		this.statusDescription = statusDescription;
		this.body = body;
		this.headers = new HashMap<>();
		this.headers.put("Set-cookie", "cookies");
		this.headers.put("Content-Type", "application/json");
	}

	public boolean isBase64Encoded() {
		return isBase64Encoded;
	}

	public void setBase64Encoded(boolean isBase64Encoded) {
		this.isBase64Encoded = isBase64Encoded;
	}

	public Integer getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(Integer statusCode) {
		this.statusCode = statusCode;
	}

	public String getStatusDescription() {
		return statusDescription;
	}

	public void setStatusDescription(String statusDescription) {
		this.statusDescription = statusDescription;
	}

	public Map<String, String> getHeaders() {
		return headers;
	}

	public void setHeaders(Map<String, String> headers) {
		this.headers = headers;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	@Override
	public String toString() {
		return "ResponseJson [isBase64Encoded=" + isBase64Encoded + ", statusCode=" + statusCode
				+ ", statusDescription=" + statusDescription + ", headers=" + headers + ", body=" + body + "]";
	}

	

}