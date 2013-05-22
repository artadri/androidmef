package it.gov.mef.util;


public class RSSHomeDipItem {

	private String dipartimento;
	private String descrizione;
	private int image;
	private int idDip = 0;
	
	
	
	
	public RSSHomeDipItem(String dipartimento, String descrizione,
			int image, int idDip) {
		super();
		this.dipartimento = dipartimento;
		this.descrizione = descrizione;
		this.image = image;
		this.idDip = idDip;
	}
	public String getDipartimento() {
		return dipartimento;
	}
	public void setDipartimento(String dipartimento) {
		this.dipartimento = dipartimento;
	}
	public String getDescrizione() {
		return descrizione;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	public int getImage() {
		return image;
	}
	public void setImage(int image) {
		this.image = image;
	}
	public int getIdDip() {
		return idDip;
	}
	public void setIdDip(int idDip) {
		this.idDip = idDip;
	}
	
	
	
}
