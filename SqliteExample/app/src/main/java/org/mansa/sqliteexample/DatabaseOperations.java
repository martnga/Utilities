package org.mansa.sqliteexample;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by mansa on 12/11/15.
 */
public class DatabaseOperations extends SQLiteOpenHelper {

    public static final int database_version = 1;
    public String CREATE_QUERY = "CREATE TABLE " + TableData.TableInfo.TABLE_NAME + "(" + TableData.TableInfo.USER_NAME + " TEXT," + TableData.TableInfo.USER_PASS + " TEXT );";

    public DatabaseOperations(Context context) {
        super(context, TableData.TableInfo.DATABASE_NAME, null, database_version);
        Log.d("Database Operation", "Database created");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(CREATE_QUERY);

        Log.d("Database Operation", "Table created");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void saveInfo(DatabaseOperations dop, String name, String password) {

        SQLiteDatabase SQ = dop.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(TableData.TableInfo.USER_NAME, name);
        cv.put(TableData.TableInfo.USER_PASS, password);
        long k = SQ.insert(TableData.TableInfo.TABLE_NAME, null, cv);

        Log.d("Database Operation", "One row inserted");

    }

    public Cursor getInformation(DatabaseOperations dop) {

        SQLiteDatabase SQ = dop.getReadableDatabase();
        String[] columns = {TableData.TableInfo.USER_NAME, TableData.TableInfo.USER_PASS};
	    // How you want the results sorted in the resulting Cursor
	    String sortOrder =  TableData.TableInfo.USER_NAME + " DESC";

        Cursor CR = SQ.query(TableData.TableInfo.TABLE_NAME, // The table to query
                columns,            // The columns to return
                null,               // The columns for the WHERE clause
                null,               // The values for the WHERE clause
                null,               // don't group the rows
                null,               // don't filter by row groups
                sortOrder            // The sort order
        );

        return CR;
    }


    public Cursor getUSerPassword(DatabaseOperations DOP, String user) {
        SQLiteDatabase SQ = DOP.getReadableDatabase();
        String selection = TableData.TableInfo.USER_NAME + " LIKE ?";
        String columns[] = {TableData.TableInfo.USER_PASS};
        String args[] = {user};
        Cursor CR = SQ.query(TableData.TableInfo.TABLE_NAME, columns, selection, args, null, null, null, null);

        return CR;
    }

    public void deleteUser(DatabaseOperations DOP, String user, String password){

        String selection = TableData.TableInfo.USER_NAME+ " LIKE ? AND " + TableData.TableInfo.USER_PASS + " LIKE ?";
        //String columns[] = {TableData.TableInfo.USER_PASS};
        String args[] = {user, password};
        SQLiteDatabase SQ = DOP.getWritableDatabase();
        SQ.delete(TableData.TableInfo.TABLE_NAME, selection, args);
    }


    /*
    Get the Column Index
    cursor.moveToFirst();
    long itemId = cursor.getLong(
            cursor.getColumnIndexOrThrow(FeedEntry._ID)
    );*/


    public void updateUser(DatabaseOperations  DOP, String newUserName, String oldUserName){
        SQLiteDatabase db = DOP.getReadableDatabase();

        // New value for one column
        ContentValues values = new ContentValues();
        values.put(TableData.TableInfo.USER_NAME, newUserName);

        /*// Which row to update, based on the ID
        String selection =  TableData.TableInfo.COLUMN_NAME_ENTRY_ID + " LIKE ?";
        String[] selectionArgs = { String.valueOf(rowId) };*/

        // Which row to update, based on the ID
        String selection =  TableData.TableInfo.USER_NAME + " LIKE ?";
        String[] selectionArgs = { oldUserName };

        int count = db.update(
                TableData.TableInfo.TABLE_NAME,
                values,
                selection,
                selectionArgs);
    }
}
