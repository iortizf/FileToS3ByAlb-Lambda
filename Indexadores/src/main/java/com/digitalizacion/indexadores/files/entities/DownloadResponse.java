package com.digitalizacion.indexadores.files.entities;

public class DownloadResponse {
	
	private String urls3;
	private String archivo;
	
	public String getUrls3() {
		return urls3;
	}
	public void setUrls3(String urls3) {
		this.urls3 = urls3;
	}
	public String getArchivo() {
		return archivo;
	}
	public void setArchivo(String archivo) {
		this.archivo = archivo;
	}
	
	@Override
	public String toString() {
		return "DownloadResponse [urls3=" + urls3 + ", archivo=" + archivo + "]";
	}	
	
}
