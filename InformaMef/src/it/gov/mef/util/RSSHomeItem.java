package it.gov.mef.util;

import java.util.Date;

public class RSSHomeItem {
	private String nome;
	private String totali;
	private String image;
	private Date ultimoAgg;

	public RSSHomeItem(String nome, String totali, String image,Date ultimoAgg ) {
		super();
		this.nome = nome;
		this.totali = totali;
		this.image = image;
		this.ultimoAgg = ultimoAgg;
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
