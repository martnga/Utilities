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
public class DatabaseOPerations extends SQLiteOpenHelper {

    public static final int database_version = 1;
    public String CREATE_QUERY = "CREATE TABLE " + TableData.TableInfo.TABLE_NAME + "(" + TableData.TableInfo.USER_NAME + " TEXT," + TableData.TableInfo.USER_PASS + " TEXT );";

    public DatabaseOPerations(Context context) {
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

    public void saveInfo(DatabaseOPerations dop, String name, String password) {

        SQLiteDatabase SQ = dop.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(TableData.TableInfo.USER_NAME, name);
        cv.put(TableData.TableInfo.USER_PASS, password);
        long k = SQ.insert(TableData.TableInfo.TABLE_NAME, null, cv);

        Log.d("Database Operation", "One row inserted");

    }

    public Cursor getInformation(DatabaseOPerations dop) {

        SQLiteDatabase SQ = dop.getReadableDatabase();
        String[] columns = {TableData.TableInfo.USER_NAME, TableData.TableInfo.USER_PASS};
        Cursor CR = SQ.query(TableData.TableInfo.TABLE_NAME, columns, null, null, null, null, null);
        return CR;
    }


    public Cursor getUSerPassword(DatabaseOPerations DOP, String user) {
        SQLiteDatabase SQ = DOP.getReadableDatabase();
        String selection = TableData.TableInfo.USER_NAME + " LIKE ?";
        String columns[] = {TableData.TableInfo.USER_PASS};
        String args[] = {user};
        Cursor CR = SQ.query(TableData.TableInfo.TABLE_NAME, columns, selection, args, null, null, null, null);
        return CR;
    }

    public void deleteUser(DatabaseOPerations DOP, String user, String password){

        String selection = TableData.TableInfo.USER_NAME+ " LIKE ? AND " + TableData.TableInfo.USER_PASS + " LIKE ?";
        //String columns[] = {TableData.TableInfo.USER_PASS};
        String args[] = {user, password};
        SQLiteDatabase SQ = DOP.getWritableDatabase();
        SQ.delete(TableData.TableInfo.TABLE_NAME, selection, args);
    }
}
