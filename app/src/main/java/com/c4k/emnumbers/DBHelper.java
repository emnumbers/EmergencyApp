package com.c4k.emnumbers;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {
    private static String DB_NAME = "EmergencyNumbers.db";
    private static String DB_PATH = "";
    private static final int DB_VERSION = 1;

    private SQLiteDatabase sqLiteDatabase;
    private final Context context;

    DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);

        this.context = context;
        DB_PATH = context.getApplicationInfo().dataDir + "/databases/";
        copyDataBase();
    }

    private void copyDataBase() {
        this.getReadableDatabase();
        try {
            copyDBFile();
            close();
        } catch (IOException mIOException) {
            throw new Error("ErrorCopyingDataBase");
        }
    }

    private void copyDBFile() throws IOException {
        InputStream mInput = context.getAssets().open(DB_NAME);
        OutputStream mOutput = new FileOutputStream(DB_PATH + DB_NAME);
        byte[] buffer = new byte[1024];
        int length;
        while ((length = mInput.read(buffer)) > 0) {
            mOutput.write(buffer, 0, length);
        }
        mOutput.flush();
        mOutput.close();
        mInput.close();
    }

    public List<ENumbersTable> GetAll() {
        sqLiteDatabase = getReadableDatabase();
        List<ENumbersTable> eNumbersTableList = new ArrayList<>();
        Cursor c = sqLiteDatabase.query
                ("ENumbersTable",null,null,null,null,null,null);
        while (c.moveToNext()) {
            ENumbersTable eNumbersTable = new ENumbersTable(c.getInt(0), c.getLong(1), c.getString(2));
            eNumbersTableList.add(eNumbersTable);
        }
        c.close();
        sqLiteDatabase.close();
        return eNumbersTableList;
    }

    @Override
    public synchronized void close() {
        if (sqLiteDatabase != null)
            sqLiteDatabase.close();
        super.close();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}