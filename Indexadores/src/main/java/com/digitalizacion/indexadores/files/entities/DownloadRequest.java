package com.digitalizacion.indexadores.files.entities;

public class DownloadRequest {
	
	private String nombreArchivo;
	private String ano;
	private String mes;
	private String dia;
	
	public String getNombreArchivo() {
		return nombreArchivo;
	}
	public void setNombreArchivo(String nombreArchivo) {
		this.nombreArchivo = nombreArchivo;
	}
	public String getAno() {
		return ano;
	}
	public void setAno(String ano) {
		this.ano = ano;
	}
	public String getMes() {
		return mes;
	}
	public void setMes(String mes) {
		this.mes = mes;
	}
	public String getDia() {
		return dia;
	}
	public void setDia(String dia) {
		this.dia = dia;
	}
	@Override
	public String toString() {
		return "DownloadRequest [nombreArchivo=" + nombreArchivo + ", ano=" + ano + ", mes=" + mes + ", dia=" + dia
				+ "]";
	}
	
	

}
