package com.bag.hindugodsymbology;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DataBaseHelper extends SQLiteOpenHelper {
	// CC's copy
	// The Android's default system path of your application database.
	private static String DB_PATH = "/data/data/com.bag.hindugodsymbology/databases/";

	private static String DB_NAME = "temp3.db";

	private SQLiteDatabase myDataBase;

	private final Context myContext;

	/**
	 * Constructor Takes and keeps a reference of the passed context in order to
	 * access to the application assets and resources.
	 * 
	 * @param context
	 */
	public DataBaseHelper(Context context) {

		super(context, DB_NAME, null, 1);
		this.myContext = context;
	}

	/**
	 * Creates a empty database on the system and rewrites it with your own
	 * database.
	 * */
	public void createDataBase() throws IOException {

		boolean dbExist = checkDataBase();

		if (dbExist) {
			// do nothing - database already exist
		} else {

			// By calling this method and empty database will be created into
			// the default system path
			// of your application so we are gonna be able to overwrite that
			// database with our database.
			this.getReadableDatabase();

			try {

				copyDataBase();

			} catch (IOException e) {

				throw new Error("Error copying database");

			}
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
			String myPath = DB_PATH + DB_NAME;
			checkDB = SQLiteDatabase.openDatabase(myPath, null,
					SQLiteDatabase.OPEN_READONLY);

		} catch (SQLiteException e) {

			// database does't exist yet.

		}

		if (checkDB != null) {

			checkDB.close();

		}

		return checkDB != null ? true : false;
	}

	/**
	 * Copies your database from your local assets-folder to the just created
	 * empty database in the system folder, from where it can be accessed and
	 * handled. This is done by transfering bytestream.
	 * */
	
	private void copyDataBase() throws IOException {

		// Open your local db as the input stream
		InputStream myInput = myContext.getAssets().open(DB_NAME);

		// Path to the just created empty db
		String outFileName = DB_PATH + DB_NAME;

		// Open the empty db as the output stream
		OutputStream myOutput = new FileOutputStream(outFileName);

		// transfer bytes from the inputfile to the outputfile
		byte[] buffer = new byte[1024];
		int length;
		while ((length = myInput.read(buffer)) > 0) {
			myOutput.write(buffer, 0, length);
		}

		// Close the streams
		myOutput.flush();
		myOutput.close();
		myInput.close();

	}

	public void openDataBase() throws SQLException {

		// Open the database
		String myPath = DB_PATH + DB_NAME;
		myDataBase = SQLiteDatabase.openDatabase(myPath, null,
				SQLiteDatabase.OPEN_READONLY);

	}

	@Override
	public synchronized void close() {

		if (myDataBase != null)
			myDataBase.close();

		super.close();

	}

	@Override
	public void onCreate(SQLiteDatabase db) {

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

	}

	// Add your public helper methods to access and get content from the
	// database.
	// You could return cursors by doing "return myDataBase.query(....)" so it'd
	// be easy
	// to you to create adapters for your views.
	// Getting All Translates
	public List<God_Bean> getAllGodNames() {
		List<God_Bean> NameList = new ArrayList<God_Bean>();
		// Select All Query
		String selectQuery = "SELECT * FROM Gods order by God_Name asc;";

		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);

		

		int c = 0;
		// looping through all rows and adding to list
		if (cursor.moveToFirst()) {
			do {
				
				c++;
				// Adding Translate to list
				God_Bean dao = new God_Bean();
				HashMap<String, String> MarkerSet = new HashMap<String, String>();
				for (int i = 0; i < 46; i += 2) 
				{
					//Log.d("This One" + c, cursor.getColumnName(i) + ","
					//		+ cursor.getString(i));
					if (cursor.getString(i) == null) 
					{

					}
					else 
					{
						String MarkerSign = cursor.getString(i);
						String MarkerCoord = cursor.getString(i + 1);
						Log.d("HERE",i+","+c+","+MarkerSign+","+MarkerCoord);
						MarkerSet.put(MarkerCoord, MarkerSign);
						

					}

				}

				dao.setMarkers(MarkerSet);
				dao.setMarker_Image(cursor.getString(46));
				dao.setMain_Image(cursor.getString(47));
				dao.setSummary(cursor.getString(48));
				dao.setSymbology(cursor.getString(49));
				dao.set_id(cursor.getString(50));
				dao.setGod_Name(cursor.getString(51));
				dao.setClassification(cursor.getString(52));

				String folderName = dao.getGod_Name().replace(' ', '_');

				dao.setMarker_Image("www/gods/"
						+ folderName.toLowerCase().toLowerCase() + "/"
						+ dao.getMarker_Image() + ".gif");
				dao.setMain_Image("www/gods/"
						+ folderName.toLowerCase().toLowerCase() + "/"
						+ dao.getMain_Image() + ".jpg");
				dao.setSummary("www/gods/" + folderName.toLowerCase() + "/"
						+ dao.getSummary() + ".html");
				dao.setSymbology("www/gods/" + folderName.toLowerCase() + "/"
						+ dao.getSymbology() + ".html");

				NameList.add(dao);
				// NameList.add("file:///android_asset/www/gods/"+dao.getGod_Name().toLowerCase()+"/"+cursor.getString(0));
			} while (cursor.moveToNext());
		}

		// return Translate list
		return NameList;
	}

}