package com.digitalizacion.indexadores.files.entities;

public class UploadResponse {
	
	private String nombreArchivo;
	private String urlS3;
	
	public String getNombreArchivo() {
		return nombreArchivo;
	}
	public void setNombreArchivo(String nombreArchivo) {
		this.nombreArchivo = nombreArchivo;
	}
	public String getUrlS3() {
		return urlS3;
	}
	public void setUrlS3(String urlS3) {
		this.urlS3 = urlS3;
	}
	
	@Override
	public String toString() {
		return "UploadResponse [nombreArchivo=" + nombreArchivo + ", urlS3=" + urlS3 + "]";
	}

	
	
}
