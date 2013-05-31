package it.gov.mef.util;

import java.util.ArrayList;
import java.util.List;

import android.app.Application;


public class NavigationBean extends Application {

	private int dipartimento = 1;
	private int numDipartimenti = MefConstants.NUMERO_DIPARTIMENTI;
	
	private int currentRSS = 0;
	private int maxRSS = 0;
	private List<RSSHomeItem> itemHomeList= new ArrayList<RSSHomeItem>();
	
	private RSSItem itemRSS = null;
	private int currentItemRSS = 0;
	private int maxItemRSS = 0;
	private List<RSSItem> listRSS = new ArrayList<RSSItem>();
	
	
	
	
	public List<RSSHomeItem> getItemHomeList() {
		return itemHomeList;
	}
	public void setItemHomeList(List<RSSHomeItem> itemHomeList) {
		this.itemHomeList = itemHomeList;
	}
	public List<RSSItem> getListRSS() {
		return listRSS;
	}
	public void setListRSS(List<RSSItem> listRSS) {
		this.listRSS = listRSS;
	}
	
	public int getMaxItemRSS() {
		return maxItemRSS;
	}
	public void setMaxItemRSS(int maxItemRSS) {
		this.maxItemRSS = maxItemRSS;
	}
	public int getCurrentItemRSS() {
		return currentItemRSS;
	}
	public void setCurrentItemRSS(int cURRENT_ITEM_RSS) {
		currentItemRSS = cURRENT_ITEM_RSS;
	}
	public RSSItem getItemRSS() {
		return itemRSS;
	}
	public void setItemRSS(RSSItem itemRSS) {
		this.itemRSS = itemRSS;
	}
	public int getCurrentRSS() {
		return currentRSS;
	}
	public void setCurrentRSS(int rSS) {
		currentRSS = rSS;
	}
	public int getMaxRSS() {
		return maxRSS;
	}
	public void setMaxRSS(int maxRSS) {
		this.maxRSS = maxRSS;
	}
	
	
	
	
	
	public int getDipartimento() {
		return dipartimento;
	}
	public void setDipartimento(int dipartimento) {
		this.dipartimento = dipartimento;
	}
	public int getNumDipartimenti() {
		return numDipartimenti;
	}
	public void setNumDipartimenti(int numDipartimenti) {
		this.numDipartimenti = numDipartimenti;
	}
	
	
}
