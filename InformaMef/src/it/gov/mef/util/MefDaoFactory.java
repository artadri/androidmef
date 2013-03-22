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

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MefDaoFactory extends SQLiteOpenHelper  {

	SQLiteDatabase mDb;
	Context mContext;
	private static final String DB_NAME = "informamef";// nome del db
	private static final int DB_VERSION = 1; // numero di versione del nostro db

	
	
	public MefDaoFactory(Context context) {
		super(context, DB_NAME, null, DB_VERSION);
		
	}
	
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		Log.d("DBHelper", "richiamato metodo oncreate");
		
		db.execSQL(ITEM_RSS_TABLE_CREATE);
		db.execSQL(RSS_TABLE_CREATE);
		
			
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		
	}
	
	
	/**
     * Creates a empty database on the system and rewrites it with your own
     * database.
     *
     * @throws java.io.IOException io exception
     */
    private void createDataBase() throws IOException {
 
        boolean dbExist = checkDataBase();
 
        if (dbExist) {
            // do nothing - database already exist
        } else {
 
            // By calling this method an empty database will be created into
            // the default system path
            // of your application so we are gonna be able to overwrite that
            // database with our database.
        	mDb =  this.getWritableDatabase();

        }
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
//        	TODO
            String myPath = "" + DB_NAME;
            checkDB = SQLiteDatabase.openDatabase(myPath, null,
                    SQLiteDatabase.OPEN_READONLY);
 
        } catch (SQLiteException e) {
 
            // database doesn't exist yet.
        	Log.d(MefDaoFactory.class + "" + this.toString(), "Data base non esiste");
 
        }
 
        if (checkDB != null) {
 
            checkDB.close();
 
        }
 
        return checkDB != null;
    }
 
 
    public void openDataBase(boolean write) throws SQLException {
 
    	if (write)
    		mDb =  this.getWritableDatabase();
    	else 
    		mDb = this.getReadableDatabase();
    }
    
    
    public void closeDataBase() throws SQLException {
    	 
    	if (mDb != null)
    		mDb.close();
    
    }
	


	public void insertRSSItem(int id_url, String guid, String title, String description,
			String pubDate, String link, String category) { // metodo per
															// inserire i dati
		ContentValues cv = new ContentValues();
		cv.put(RSSMetaData.ID_URL, id_url);
		cv.put(RSSMetaData.GUID, guid);
		cv.put(RSSMetaData.TITLE, title);
		cv.put(RSSMetaData.DESCRIPTION, description);
		cv.put(RSSMetaData.DATE, pubDate);
		cv.put(RSSMetaData.LINK, link);
		cv.put(RSSMetaData.CATEGORY, category);
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
			insertRSSItem(itemRSS.getIdUrl(), itemRSS.getGuid(), itemRSS.getTitle(),
					itemRSS.getDescription(), itemRSS.getPubDate().toString(),
					itemRSS.getLink(), itemRSS.getCategory());
		}

	}

	
	
	public void updateRSS(int rowId , String date ){
		
		  ContentValues args = new ContentValues();
	      args.put(RSSMetaData.DATE_LAST_UPDATE, date);
	      mDb.update(RSSMetaData.RSS_TABLE, args, RSSMetaData.ID_URL + "=" + rowId, null);
	}

	
	
	public void insertRSS(int id_url, String url, String desc_url, String dateUpdate,
			String nume_elem_new) { // metodo per inserire i dati
		ContentValues cv = new ContentValues();
		cv.put(RSSMetaData.ID_URL, id_url);
		cv.put(RSSMetaData.URL, url);
		cv.put(RSSMetaData.DESC_URL_RSS, desc_url);
		cv.put(RSSMetaData.DATE_LAST_UPDATE, dateUpdate);
		cv.put(RSSMetaData.NUM_ELEM_NEW, 0);
		mDb.insert(RSSMetaData.RSS_TABLE, null, cv);
	}
	
	
	
	public void updateRSS(String rowId , String dateUpdate, String nume_elem_new ){
		
		  ContentValues args = new ContentValues();
	      args.put(RSSMetaData.DATE_LAST_UPDATE, dateUpdate);
	      args.put(RSSMetaData.NUM_ELEM_NEW, nume_elem_new);
	      mDb.update(RSSMetaData.RSS_TABLE, args, RSSMetaData.ID_URL + "=" + rowId, null);
	}

	public Cursor fetchItemRSS() { // metodo per fare la query di tutti i dati
		return mDb.query(RSSMetaData.RSS_ITEM_TABLE, null, null, null, null,
				null, null);
	}
	
	public Cursor fetchRSS() { // metodo per fare la query di tutti i dati
		return mDb.query(RSSMetaData.RSS_TABLE, null, null, null, null,
				null, null);
	}

	static class RSSMetaData { // i metadati della tabella, accessibili ovunque
		static final String RSS_ITEM_TABLE = "item_rss";
		static final String ID = "id_item";
		static final String GUID = "guid";
		static final String TITLE = "title";
		static final String DESCRIPTION = "description";
		static final String DATE = "pubDate";
		static final String LINK = "link";
		static final String CATEGORY = "category";

		static final String RSS_TABLE = "rss";
		static final String ID_URL = "id_url";
		static final String URL = "url";
		static final String DESC_URL_RSS = "desc_url";
		static final String DATE_LAST_UPDATE = "dateUpdate";
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
			+ RSSMetaData.DATE
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
			+ " datetime ,"
			+ RSSMetaData.NUM_ELEM_NEW + " integer default 0);";



	public String getRSSUrlById(int rowId) {
		Cursor cursor = mDb.query(RSSMetaData.RSS_TABLE, null, RSSMetaData.ID_URL + "=" + rowId, null, null,
				null, null);
		String feed=null;
		 if (cursor.moveToNext()){
			 feed = cursor.getString(cursor.getColumnIndex( RSSMetaData.URL ));
			 
		 }
		 
		 cursor.close();
		 
		return feed;
	}


	public List<RSSItem> getRSSList(int rowId) {
		// TODO Auto-generated method stub
		
		Cursor cursor = mDb.query(RSSMetaData.RSS_ITEM_TABLE, null, RSSMetaData.ID_URL + "=" + rowId, null, null,
				null, null);
		List<RSSItem> listRSS = new ArrayList<RSSItem>();
				
		while(cursor.moveToNext())
			{
			Log.d("url", DateUtil.parseDateToDb(cursor.getString(cursor.getColumnIndex(RSSMetaData.DATE))).toString());
			
			
			listRSS.add(new RSSItem(
					cursor.getString(cursor.getColumnIndex(RSSMetaData.TITLE)),
					cursor.getString(cursor.getColumnIndex(RSSMetaData.DESCRIPTION)),
					DateUtil.parseDateToDb(cursor.getString(cursor.getColumnIndex(RSSMetaData.DATE))) ,	
				
					cursor.getString(cursor.getColumnIndex(RSSMetaData.LINK)), 
					cursor.getInt(cursor.getColumnIndex(RSSMetaData.ID)),
					cursor.getString(cursor.getColumnIndex(RSSMetaData.GUID)),
					cursor.getString(cursor.getColumnIndex(RSSMetaData.CATEGORY)),
					cursor.getInt((cursor.getColumnIndex(RSSMetaData.ID_URL))
					)));
			
			}
		
		
		cursor.close();
		
		
		return listRSS;
	}


	public int getTotRSS(int idUrl) {
		
		Cursor mCount = mDb.rawQuery("SELECT count(1) FROM item_rss where id_url = '"+ idUrl + "'", null);
		mCount.moveToFirst();
		int count= mCount.getInt(0);
		
		mCount.close();
		return count;
		
	}





}
