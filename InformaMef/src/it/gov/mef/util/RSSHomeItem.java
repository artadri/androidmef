package it.gov.mef.util;

import java.util.Date;

public class RSSHomeItem {
	private String nome;
	private String totali;
	private String image;
	private Date ultimoAgg;
	private int idRSS = 0;

	public RSSHomeItem(String nome, String totali, String image,Date ultimoAgg, int idRSS ) {
		super();
		this.nome = nome;
		this.totali = totali;
		this.image = image;
		this.ultimoAgg = ultimoAgg;
		this.idRSS = idRSS;
	}

	
	
	public int getIdRSS() {
		return idRSS;
	}



	public void setIdRSS(int idRSS) {
		this.idRSS = idRSS;
	}



	public Date getUltimoAgg() {
		return ultimoAgg;
	}

	public void setUltimoAgg(Date ultimoAgg) {
		this.ultimoAgg = ultimoAgg;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTotali() {
		return totali;
	}

	public void setTotali(String totali) {
		this.totali = totali;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

}
