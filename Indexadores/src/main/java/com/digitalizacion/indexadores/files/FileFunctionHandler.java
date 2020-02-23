package com.digitalizacion.indexadores.files;

import java.io.IOException;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.digitalizacion.indexadores.files.entities.DownloadRequest;
import com.digitalizacion.indexadores.files.entities.DownloadResponse;
import com.digitalizacion.indexadores.files.entities.RequestJson;
import com.digitalizacion.indexadores.files.entities.ResponseJson;
import com.digitalizacion.indexadores.files.entities.UploadRequest;
import com.digitalizacion.indexadores.files.entities.UploadResponse;
import com.digitalizacion.indexadores.files.services.FileService;
import com.fasterxml.jackson.databind.ObjectMapper;

public class FileFunctionHandler implements RequestHandler<RequestJson, ResponseJson> {

	//Operaciones aceptadas (/download= GET y /upload = POST)
	private static final String DOWNLOAD_PATH = "/download";
	private static final String UPLOAD_PATH = "/upload";
	
    @Override
    public ResponseJson handleRequest(RequestJson request, Context context) {
        context.getLogger().log("Download request: " + request.toString());
        
        if((request.getHttpMethod().equals(HttpGet.METHOD_NAME) ||
        		request.getHttpMethod().equals(HttpPost.METHOD_NAME))
        		&& (request.getPath().equals(DOWNLOAD_PATH) ||
        				request.getPath().equals(UPLOAD_PATH) )) {
        	
        	FileService fileService = new FileService();
        	ResponseJson response = new ResponseJson(200, "200 OK");
        	ObjectMapper mapper = new ObjectMapper();
        	 
        	if(request.getHttpMethod().equals(HttpGet.METHOD_NAME)
        			&& request.getPath().equals(DOWNLOAD_PATH)){
        		context.getLogger().log("El path es /download y el metodo es GET- OK");
        		try {
					
        			DownloadRequest downloadReq = mapper.readValue((String)request.getBody(), DownloadRequest.class);
					DownloadResponse downloadResp= fileService.download(downloadReq);
					context.getLogger().log("Download Request Object ="+downloadResp.toString());
					response.setBody(mapper.writeValueAsString(downloadResp));
					
				} catch (IOException e) {
					context.getLogger().log("Valores no permitidos GET, "+(String)request.getBody());
					response.setStatusCode(500);
					response.setStatusDescription("500 Valores no permitidos");
				}
        		
        	}else {
        		context.getLogger().log("El path es /upload y el metodo es POST - OK");
        		try {
        			
        			UploadRequest uploadRequest = mapper.readValue((String)request.getBody(), UploadRequest.class);
        			UploadResponse uploadResponse = fileService.upload(uploadRequest);
        			context.getLogger().log("Upload Request Object ="+uploadRequest.toString());
        			response.setBody(mapper.writeValueAsString(uploadResponse));
        			
				} catch (IOException e) {
					context.getLogger().log("Valores no permitidos POST, "+(String)request.getBody());
					response.setStatusCode(500);
					response.setStatusDescription("500 Valores no permitidos");
				}
        	}
        	
        	context.getLogger().log("Response = " + response);
        	
        	return response;
        }        
        ResponseJson response = new ResponseJson(405, "405 Operación no permitida");
                
        return response;
    }
}
