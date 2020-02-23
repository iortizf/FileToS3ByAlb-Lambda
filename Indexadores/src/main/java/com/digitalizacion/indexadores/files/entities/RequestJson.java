package com.digitalizacion.indexadores.files.entities;

public class RequestJson {
	
	private Object requestContext;
	private String httpMethod;
	private String path;
	private Object queryStringParameters;
	private Object headers;
	private Boolean isBase64Encoded;
	private Object body;
	
	public Object getRequestContext() {
		return requestContext;
	}
	public void setRequestContext(Object requestContext) {
		this.requestContext = requestContext;
	}
	public String getHttpMethod() {
		return httpMethod;
	}
	public void setHttpMethod(String httpMethod) {
		this.httpMethod = httpMethod;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public Object getQueryStringParameters() {
		return queryStringParameters;
	}
	public void setQueryStringParameters(Object queryStringParameters) {
		this.queryStringParameters = queryStringParameters;
	}
	public Object getHeaders() {
		return headers;
	}
	public void setHeaders(Object headers) {
		this.headers = headers;
	}
	public Boolean getIsBase64Encoded() {
		return isBase64Encoded;
	}
	public void setIsBase64Encoded(Boolean isBase64Encoded) {
		this.isBase64Encoded = isBase64Encoded;
	}
	public Object getBody() {
		return body;
	}
	public void setBody(Object body) {
		this.body = body;
	}
	@Override
	public String toString() {
		return "RequestJson [requestContext=" + requestContext + ", httpMethod=" + httpMethod + ", path=" + path
				+ ", queryStringParameters=" + queryStringParameters + ", headers=" + headers + ", isBase64Encoded="
				+ isBase64Encoded + ", body=" + body + "]";
	}
	
	
	
}
