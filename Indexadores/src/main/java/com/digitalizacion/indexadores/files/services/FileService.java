package com.digitalizacion.indexadores.files.services;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CreateBucketRequest;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.util.IOUtils;
import com.digitalizacion.indexadores.files.entities.DownloadRequest;
import com.digitalizacion.indexadores.files.entities.DownloadResponse;
import com.digitalizacion.indexadores.files.entities.UploadRequest;
import com.digitalizacion.indexadores.files.entities.UploadResponse;

public class FileService {
	
	private static String AWS_URL = "https://s3.amazonaws.com";
	
	private static String AWS_BUCKET_NAME = "1ndex4d0resbucket";
	
	private AmazonS3 s3Client = AmazonS3ClientBuilder.standard()
			.withRegion(Regions.US_EAST_1).build();
	
	public UploadResponse upload(UploadRequest uploadReq) {
		UploadResponse uploadResp = new UploadResponse();
		
		if(uploadReq!=null) {
			
			String key = buildS3Key(uploadReq.getAno(), 
					uploadReq.getMes(), uploadReq.getDia(), 
					uploadReq.getNombreArchivo());
			
			uploadFileToS3(uploadReq.getNombreArchivo(), key, uploadReq.getArchivo());
			
			uploadResp.setNombreArchivo(uploadReq.getNombreArchivo());
			uploadResp.setUrlS3(AWS_URL + "/" + AWS_BUCKET_NAME + "/" + key);
			
		}
		
		return uploadResp;
		
	}	
	
	public DownloadResponse download(DownloadRequest downloadReq) {
		
		DownloadResponse downloadResp = new DownloadResponse();
		
		String key = buildS3Key(downloadReq.getAno(), 
				downloadReq.getMes(), downloadReq.getDia(), 
				downloadReq.getNombreArchivo());
		
		 S3Object s3Object = s3Client.getObject(new GetObjectRequest(AWS_BUCKET_NAME, key));
		 downloadResp.setUrls3(AWS_URL + "/" + AWS_BUCKET_NAME + "/" + key);
		 
		 try {
			byte[] bytes = IOUtils.toByteArray(s3Object.getObjectContent());
			String archivoBase64 = Base64.getEncoder().encodeToString(bytes);
			downloadResp.setArchivo(archivoBase64);
		} catch (IOException e) {
			e.printStackTrace();
		}		 
		 
		return downloadResp;
	}
	
	private String uploadFileToS3(String nombreArchivo, String key, String archivoBase64) {
		String fileUrl = "";
		try {
			
			if (!s3Client.doesBucketExistV2(AWS_BUCKET_NAME)) {
				s3Client.createBucket(new CreateBucketRequest(AWS_BUCKET_NAME));
			}
			
			uploadFileToS3bucket(key, archivoBase64);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return fileUrl;
	}

	private void uploadFileToS3bucket(String key, String archivoBase64) throws IOException {
		
		byte[] decoder = Base64.getDecoder().decode(archivoBase64);		
		InputStream is = new ByteArrayInputStream(decoder);
		
		ObjectMetadata s3ObjectMetadata = new ObjectMetadata();
		s3ObjectMetadata.setContentLength(is.available());		
		
		s3Client.putObject(new PutObjectRequest(AWS_BUCKET_NAME, key, is, s3ObjectMetadata));
	}
	
	
	private String buildS3Key(String ano, String mes, String dia, String nombreArchivo) {
		return  new StringBuilder()
				.append(ano).append("/")
				.append(mes).append("/")
				.append(dia).append("/")
				.append(nombreArchivo)
				.toString();
	}

}
