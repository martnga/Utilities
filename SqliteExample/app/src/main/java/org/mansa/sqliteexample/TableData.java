package org.mansa.sqliteexample;

import android.provider.BaseColumns;

/**
 * Created by mansa on 12/11/15.
 */
public class TableData  {

    public TableData(){}

    public static abstract class TableInfo implements BaseColumns
    {

        public static final String USER_NAME = "user_name";
        public static final String COLUMN_NAME_ENTRY_ID = "entryid";
        public static final String USER_PASS = "user_pass";
        public static final String DATABASE_NAME = "user_info";
        public static final String TABLE_NAME = "reg_info";

    }


}
