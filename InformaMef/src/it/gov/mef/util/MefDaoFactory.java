package it.gov.mef.util;

import it.gov.mef.informamef.R;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import android.content.ClipData.Item;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MefDaoFactory extends SQLiteOpenHelper {

	SQLiteDatabase mDb;
	Context mContext;
	private static final String DB_NAME = "informamef";// nome del db
	private static final int DB_VERSION = 1; // numero di versione del nostro db

	public MefDaoFactory(Context context)  {
		super(context, DB_NAME, null, DB_VERSION);
		mContext = context;
	}

	@Override
	public void onCreate(SQLiteDatabase db) throws SQLException {
		Log.d("DBHelper", "richiamato metodo oncreate");
//		openDataBase();

			try {
//				Creo il database vuoto
//				openDataBase(true);
				mDb = db;
//				Creo le tabelle
				execSQL(ITEM_RSS_TABLE_CREATE);
				execSQL(RSS_TABLE_CREATE);
//				Inserisco gli  RSS
				insertRSS(MefConstants.idRSS1,"http://www.mef.gov.it/rss/rss.asp?t=4", MefConstants.DESC_MEF, "", "");
				insertRSS(MefConstants.idRSS2,"http://www.mef.gov.it/rss/rss.asp?t=3", MefConstants.DESC_MEF, "", "");
				insertRSS(MefConstants.idRSS3,"http://www.mef.gov.it/rss/rss.asp?t=5", MefConstants.DESC_MEF, "", "");
				insertRSS(MefConstants.idRSS4,"http://www.mef.gov.it/rss/rss.asp?t=8&c=200",	 MefConstants.DESC_MEF, "", "");
				insertRSS(MefConstants.idRSS5,"http://www.mef.gov.it/rss/rss.asp?t=8&c=210", MefConstants.DESC_MEF, "", "");
				insertRSS(MefConstants.idRSS6,"http://www.dt.mef.gov.it/it/news/index.html?output=rss", MefConstants.DESC_DT, "", "");
				insertRSS(MefConstants.idRSS7,"http://www.dt.mef.gov.it/it/eventi/index.html?output=rss", MefConstants.DESC_DT, "", "");
				insertRSS(MefConstants.idRSS8,"http://www.dt.mef.gov.it/it/calendario/index.html?output=rss",	 MefConstants.DESC_DT, "", "");
				insertRSS(MefConstants.idRSS9,"http://www.dt.mef.gov.it/it/debito_pubblico/emissioni_titoli_di_stato_interni/programma_trimestrale_emissione/index.html?output=rss", MefConstants.DESC_DT, "", "");
				insertRSS(MefConstants.idRSS10,"http://www.dt.mef.gov.it/it/debito_pubblico/emissioni_titoli_di_stato_interni/comunicazioni_emissioni_ctz/index.html?output=rss", MefConstants.DESC_DT, "", "");
				insertRSS(MefConstants.idRSS11,"http://www.dt.mef.gov.it/it/debito_pubblico/emissioni_titoli_di_stato_interni/comunicazioni_emissioni_btpei/index.html?output=rss", MefConstants.DESC_DT, "", "");
				insertRSS(MefConstants.idRSS12,"http://www.dt.mef.gov.it/it/debito_pubblico/emissioni_titoli_di_stato_interni/comunicazioni_emissioni_medio_lungo_termine/index.html?output=rss",	 MefConstants.DESC_DT, "", "");
				insertRSS(MefConstants.idRSS13,"http://www.dt.mef.gov.it/it/debito_pubblico/dati_statistici/titoli_scadenza_prossimi_12_mesi/index.html?output=rss", MefConstants.DESC_DT, "", "");
				insertRSS(MefConstants.idRSS14,"http://www.dag.mef.gov.it/rss/rss.notizie.xml", MefConstants.DESC_DAG, "", "");
				insertRSS(MefConstants.idRSS15,"http://www.rgs.mef.gov.it/VERSIONE-I/RSS/rssRGS.xml", MefConstants.DESC_RGS, "", "");
				insertRSS(MefConstants.idRSS17,"https://intranetdag.tesoro.it/rss/rss.html?t=12002", MefConstants.DESC_INTRANET_DAG, "", "");
				insertRSS(MefConstants.idRSS16,"https://intranetdag.tesoro.it/rss/rss.html?t=12003", MefConstants.DESC_INTRANET_DAG, "", "");
				insertRSS(MefConstants.idRSS18,"https://intranetdag.tesoro.it/rss/rss.html?t=12004", MefConstants.DESC_INTRANET_DAG, "", "");

				
				
				
				
				
				
				
				int count = updateRSSItem(MefConstants.idRSS1);
				Log.d(this.toString(),count +"");
				count += updateRSSItem(MefConstants.idRSS2);
				Log.d(this.toString(),count +"");
				count += updateRSSItem(MefConstants.idRSS3);
				Log.d(this.toString(),count +"");
				count += updateRSSItem(MefConstants.idRSS4);
				Log.d(this.toString(),count +"");
				count += updateRSSItem(MefConstants.idRSS5);
				Log.d(this.toString(),count +"");
				count += updateRSSItem(MefConstants.idRSS6);
				Log.d(this.toString(),count +"");
				count += updateRSSItem(MefConstants.idRSS7);
				Log.d(this.toString(),count +"");
				count += updateRSSItem(MefConstants.idRSS8);
				Log.d(this.toString(),count +"");
				count += updateRSSItem(MefConstants.idRSS9);
				Log.d(this.toString(),count +"");
				count += updateRSSItem(MefConstants.idRSS10);
				Log.d(this.toString(),count +"");
				count += updateRSSItem(MefConstants.idRSS11);
				Log.d(this.toString(),count +"");
				count += updateRSSItem(MefConstants.idRSS12);
				Log.d(this.toString(),count +"");
				count += updateRSSItem(MefConstants.idRSS13);
				Log.d(this.toString(),count +"");
				count += updateRSSItem(MefConstants.idRSS14);
				Log.d(this.toString(),count +"");
				count += updateRSSItem(MefConstants.idRSS15);
				Log.d(this.toString(),count +"");
				count += updateRSSItem(MefConstants.idRSS16);
				Log.d(this.toString(),count +"");
				count += updateRSSItem(MefConstants.idRSS17);
				Log.d(this.toString(),count +"");
				count += updateRSSItem(MefConstants.idRSS18);
				Log.d(this.toString(),count +"");
				
				
				
			} catch (Exception e) {
				
				e.printStackTrace();
			}
		

		
	}
	

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO da completare

	}

	/**
	 * Creates a empty database on the system and rewrites it with your own
	 * database.
	 * 
	 * @throws java.io.IOException
	 *             io exception
	 */
	private void createDataBase() throws IOException {

		mDb = this.getWritableDatabase();
//		
//		boolean dbExist = checkDataBase();
//
//		if (dbExist) {
//			// do nothing - database already exist
//		} else {
//
//			// By calling this method an empty database will be created into
//			// the default system path
//			// of your application so we are gonna be able to overwrite that
//			// database with our database.
//			mDb = this.getWritableDatabase();
//
//		}
	}

	/**
	 * Check if the database already exist to avoid re-copying the file each
	 * time you open the application.
	 * 
	 * @return true if it exists, false if it doesn't
	 */
	private boolean checkDataBase() {

		SQLiteDatabase checkDB = null;

		try {
		
			String myPath = (mContext.getDatabasePath(DB_NAME)!= null ? mContext.getDatabasePath(DB_NAME).getPath() :"");
			if (!"".equals(myPath)){
				checkDB = SQLiteDatabase.openDatabase(myPath, null,
					SQLiteDatabase.OPEN_READONLY);
			}

		} catch (SQLiteException e) {

			// database doesn't exist yet.
			Log.d(MefDaoFactory.class + "" + this.toString(),
					"Data base non esiste");

		}

		if (checkDB != null) {

			checkDB.close();

		}

		return checkDB != null;
	}

	public void openDataBase(boolean write) throws SQLException {

		if (write)
			mDb = this.getWritableDatabase();
		else
			mDb = this.getReadableDatabase();
	}

	public void closeDataBase() throws SQLException {

		if (mDb != null)
			mDb.close();

	}

	public void insertRSSItem(int id_url, String guid, String title,
			String description, String pubDate, String link, String category,
			String date_read, String date_update) { // metodo per
		// inserire i dati
		ContentValues cv = new ContentValues();
		cv.put(RSSMetaData.ID_URL, id_url);
		cv.put(RSSMetaData.GUID, guid);
		cv.put(RSSMetaData.TITLE, title);
		cv.put(RSSMetaData.DESCRIPTION, description);
		cv.put(RSSMetaData.PUB_DATE, pubDate);
		cv.put(RSSMetaData.LINK, link);
		cv.put(RSSMetaData.CATEGORY, category);
		if (date_read != null)
			cv.put(RSSMetaData.DATE_READ, date_read);
		if (date_update != null)
			cv.put(RSSMetaData.DATE_UPDATE, date_update);

		mDb.insert(RSSMetaData.RSS_ITEM_TABLE, null, cv);
	}

	public void insertRSSItem(List<RSSItem> listRSS) {

		for (Iterator<RSSItem> iterator = listRSS.iterator(); iterator
				.hasNext();) {
			RSSItem rssItem = (RSSItem) iterator.next();
			insertRSSItem(rssItem);
		}

	}

	public void insertRSSItem(RSSItem itemRSS) {

		if (itemRSS != null) {
			insertRSSItem(itemRSS.getIdUrl(), itemRSS.getGuid(),
					itemRSS.getTitle(), itemRSS.getDescription(), itemRSS
							.getPubDate().toString(), itemRSS.getLink(),
					itemRSS.getCategory(),
					(itemRSS.getDate_read() != null ? itemRSS.getDate_read()
							.toString() : null),
					(itemRSS.getDate_update() != null ? itemRSS
							.getDate_update().toString() : null));
		}

	}

	public void updateRSS(int rowId, String date, int elem_new) {

		ContentValues args = new ContentValues();
		args.put(RSSMetaData.DATE_LAST_UPDATE, date);
		args.put(RSSMetaData.NUM_ELEM_NEW, elem_new);
		mDb.update(RSSMetaData.RSS_TABLE, args, RSSMetaData.ID_URL + "="
				+ rowId, null);
	}

	public void insertRSS(int id_url, String url, String desc_url,
			String dateUpdate, String nume_elem_new) { // metodo per inserire i
														// dati
		ContentValues cv = new ContentValues();
		cv.put(RSSMetaData.ID_URL, id_url);
		cv.put(RSSMetaData.URL, url);
		cv.put(RSSMetaData.DESC_URL_RSS, desc_url);
		cv.put(RSSMetaData.DATE_LAST_UPDATE, dateUpdate);
		cv.put(RSSMetaData.NUM_ELEM_NEW, 0);
		mDb.insert(RSSMetaData.RSS_TABLE, null, cv);
	}

	public void updateRSS(String rowId, String dateUpdate, String nume_elem_new) {

		ContentValues args = new ContentValues();
		args.put(RSSMetaData.DATE_LAST_UPDATE, dateUpdate);
		args.put(RSSMetaData.NUM_ELEM_NEW, nume_elem_new);
		mDb.update(RSSMetaData.RSS_TABLE, args, RSSMetaData.ID_URL + "="
				+ rowId, null);
	}

	public Cursor fetchItemRSS() { // metodo per fare la query di tutti i dati
		return mDb.query(RSSMetaData.RSS_ITEM_TABLE, null, null, null, null,
				null, null);
	}

	public Cursor fetchRSS() { // metodo per fare la query di tutti i dati
		return mDb.query(RSSMetaData.RSS_TABLE, null, null, null, null, null,
				null);
	}
	

	static class RSSMetaData { // i metadati della tabella, accessibili ovunque
		static final String RSS_ITEM_TABLE = "item_rss";
		static final String ID = "id_item";
		static final String GUID = "guid";
		static final String TITLE = "title";
		static final String DESCRIPTION = "description";
		static final String PUB_DATE = "pub_date";
		static final String LINK = "link";
		static final String CATEGORY = "category";
		static final String DATE_UPDATE = "date_update";
		static final String DATE_READ = "date_read";

		static final String RSS_TABLE = "rss";
		static final String ID_URL = "id_url";
		static final String URL = "url";
		static final String DESC_URL_RSS = "desc_url";
		static final String DATE_LAST_UPDATE = "date_update";
		static final String NUM_ELEM_NEW = "nume_elem_new";

		static final String PRODUCT_NAME_KEY = "name";
		static final String PRODUCT_PRICE_KEY = "price";

	}

	private static final String ITEM_RSS_TABLE_CREATE = "CREATE TABLE IF NOT EXISTS " // codice
																						// sql
																						// di
																						// creazione
																						// della
																						// tabella
			+ RSSMetaData.RSS_ITEM_TABLE
			+ " ("
			+ RSSMetaData.ID
			+ " integer primary key autoincrement, "
			+ RSSMetaData.ID_URL
			+ " integer not null ,"
			+ RSSMetaData.GUID
			+ " ,"
			+ RSSMetaData.TITLE
			+ " ,"
			+ RSSMetaData.DESCRIPTION
			+ " ,"
			+ RSSMetaData.PUB_DATE
			+ " datetime ,"
			+ RSSMetaData.DATE_LAST_UPDATE
			+ " datetime ,"
			+ RSSMetaData.DATE_READ
			+ " datetime ,"
			+ RSSMetaData.LINK
			+ " ,"
			+ RSSMetaData.CATEGORY + " );";

	private static final String RSS_TABLE_CREATE = "CREATE TABLE IF NOT EXISTS " // codice
																					// sql
																					// di
																					// creazione
																					// della
																					// tabella
			+ RSSMetaData.RSS_TABLE
			+ " ("
			+ RSSMetaData.ID_URL
			+ " integer primary key , "
			+ RSSMetaData.URL
			+ " text not null,"
			+ RSSMetaData.DESC_URL_RSS
			+ " ,"
			+ RSSMetaData.DATE_LAST_UPDATE
			+ " datetime ," + RSSMetaData.NUM_ELEM_NEW + " integer default 0);";

	public String getRSSUrlById(int rowId) {
		Cursor cursor = mDb.query(RSSMetaData.RSS_TABLE, null,
				RSSMetaData.ID_URL + "=" + rowId, null, null, null, null);
		String feed = null;
		if (cursor.moveToNext()) {
			feed = cursor.getString(cursor.getColumnIndex(RSSMetaData.URL));

		}

		cursor.close();

		return feed;
	}
	
	public int getRSSUrlId(int rowId) {
		Cursor cursor = mDb.query(RSSMetaData.RSS_TABLE, null,
				RSSMetaData.ID_URL + "=" + rowId, null, null, null, null);
		int feed = 0;
		if (cursor.moveToNext()) {
			feed = cursor.getInt((cursor.getColumnIndex(RSSMetaData.ID_URL)));

		}

		cursor.close();

		return feed;
	}
	
	
	
	public List<Integer> getRSSListURL() {
		Cursor cursor = mDb.query(RSSMetaData.RSS_TABLE, null,
				null, null, null, null, null);
		List<Integer> feed = new ArrayList<Integer>();
		while (cursor.moveToNext()) {
			feed.add(cursor.getInt(cursor.getColumnIndex(RSSMetaData.ID_URL)));

		}
		
		
		cursor.close();

		return feed;
	}

	
	public List<Integer> getRSSListURLByDesc(String dip) {
		Cursor cursor = mDb.query(RSSMetaData.RSS_TABLE, null,
				RSSMetaData.DESC_URL_RSS + "=\"" + dip + "\"", null, null, null, null);
		List<Integer> feed = new ArrayList<Integer>();
		
		while (cursor.moveToNext()) {
			feed.add(cursor.getInt(cursor.getColumnIndex(RSSMetaData.ID_URL)));

		}
		
		
		cursor.close();

		return feed;
	}
	
	
	
	
	
	
	public List<RSSItem> getRSSList(int rowId) {
		// TODO Auto-generated method stub

		Cursor cursor = mDb.rawQuery("SELECT * FROM item_rss WHERE id_url="+ rowId + 
				" ORDER BY date(" + RSSMetaData.PUB_DATE + ") DESC" ,null); 
//				mDb.query(RSSMetaData.RSS_ITEM_TABLE, null, 
//				RSSMetaData.ID_URL + "=" + rowId, null, null, null, null);
		List<RSSItem> listRSS = new ArrayList<RSSItem>();

		
		
		while (cursor.moveToNext()) {
//			Log.d("url",
//					DateUtil.parseDateToDb(
//							cursor.getString(cursor
//									.getColumnIndex(RSSMetaData.PUB_DATE)))
//							.toString());

			listRSS.add(new RSSItem(cursor.getString(cursor
					.getColumnIndex(RSSMetaData.TITLE)), cursor
					.getString(cursor.getColumnIndex(RSSMetaData.DESCRIPTION)),
					DateUtil.parseDateToDb(cursor.getString(cursor
							.getColumnIndex(RSSMetaData.PUB_DATE))),

					cursor.getString(cursor.getColumnIndex(RSSMetaData.LINK)),
					cursor.getInt(cursor.getColumnIndex(RSSMetaData.ID)),
					cursor.getString(cursor.getColumnIndex(RSSMetaData.GUID)),
					cursor.getString(cursor
							.getColumnIndex(RSSMetaData.CATEGORY)),
					cursor.getInt((cursor.getColumnIndex(RSSMetaData.ID_URL)))));

		}

		cursor.close();

		return listRSS;
	}

	public int getTotRSSItemByIdURL(int idUrl) {

		Cursor mCount = mDb.rawQuery(
				"SELECT count(1) FROM item_rss where id_url = '" + idUrl + "'",
				null);
		mCount.moveToFirst();
		int count = mCount.getInt(0);

		mCount.close();
		return count;

	}

	public int getTotRSSItemNotRead(int idUrl) {

		Cursor mCount = mDb.rawQuery("SELECT count(1) FROM item_rss where  "
				+ RSSMetaData.ID_URL + " = '" + idUrl + "' and "
				+ RSSMetaData.DATE_READ + " <> \"\"", null);
		mCount.moveToFirst();
		int count = mCount.getInt(0);

		mCount.close();
		return count;

	}

	public void execSQL(String query) {
		mDb.execSQL(query);

	}

	public int updateDateItemRss(int idItem, Date date) {
		// TODO Auto-generated method stub
		ContentValues args = new ContentValues();
		args.put(RSSMetaData.DATE_READ, date.toString());
		// int result = mDb.update(RSSMetaData.RSS_ITEM_TABLE,args,
		// RSSMetaData.ID +"=" + idItem + " and " + RSSMetaData.DATE_READ +
		// " = \"\"", null);
		int result = mDb.update(RSSMetaData.RSS_ITEM_TABLE, args,
				RSSMetaData.ID + "=" + idItem, null);
		Log.d("numero di righe aggiornate", result + "");
		return result;

	}

	public int updateRSSItem(int id){
		
		int count = 0;
		List<String> listLocalRSSGuid = getRSSListGuid(id);
		String feed = getRSSUrlById(id);
		Log.d("################", feed);
		ParsingRSS parseRSS = new ParsingRSS();
		List<RSSItem> listRemoteRSS = parseRSS.parseUrlRSS(feed, id);
		Iterator<RSSItem> it = listRemoteRSS.iterator();
		

		
		while (it.hasNext()) {
			RSSItem rssItem = (RSSItem) it.next();
			if (listLocalRSSGuid.size() > 0 && listLocalRSSGuid.contains(rssItem.getGuid()) ){
//				Log.d(this.toString(), "elemento già presente - " + rssItem.getTitle());
			} else {
//				Log.d(this.toString(), "elemento non presente" + rssItem.getTitle());
				insertRSSItem(rssItem);
				count++;
			}
			
		}
		
		Log.d(this.toString(), "Elementi nuovi" + count);
		
		if (count > 0){
//			Aggiorno la data di ultimo aggiornamento della lista
			updateRSS(id,new Date().toString(), count);
			
		}
			
	
		return count;
	}

	private List<String> getRSSListGuid(int id) {
		Cursor cursor = mDb.query(RSSMetaData.RSS_ITEM_TABLE, null,
				RSSMetaData.ID_URL + "=" + id, null, null, null, null);
		List listRSS = new ArrayList();

		while (cursor.moveToNext()) {
//			Log.d("url",
//					DateUtil.parseDateToDb(
//							cursor.getString(cursor
//									.getColumnIndex(RSSMetaData.PUB_DATE)))
//							.toString());
//			Log.d("MefDaoFactory",cursor.getString(cursor
//					.getColumnIndex(RSSMetaData.GUID)));

			listRSS.add(cursor.getString(cursor
					.getColumnIndex(RSSMetaData.GUID)));

		}

		cursor.close();

		return listRSS;

	}

	public Date getRSSLastUpdate(int idUrl) {
		Cursor mCount = mDb.rawQuery("SELECT " + RSSMetaData.DATE_LAST_UPDATE +" FROM rss where  "
				+ RSSMetaData.ID_URL + " = '" + idUrl + "' ", null);
		
		Date date = null;
		if (!mCount.isAfterLast()){
			mCount.moveToFirst();
			date = DateUtil.parseDateToDb(mCount.getString(0));
		 
		}
		mCount.close();
		
		return date;
	}

}
