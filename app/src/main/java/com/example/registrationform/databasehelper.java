package com.example.registrationform;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.strictmode.SqliteObjectLeakedViolation;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class databasehelper extends SQLiteOpenHelper {
    private Context context;
    public static final String TABLE_NAME = "list_penyewa";

    public static final String id = "id";
    public static final String nama = "name";
    public static final String no_telp ="no_telp";
    public static final String alamat = "alamat";
    public static final String gender = "gender";
    public static final String olahraga = "olahraga";
    public static final String waktu = "waktu";

    static final String DB_NAME = "db_registrasi";
    static final int DB_VERSION = 1;

    private static final String CREATE_TABLE = "create table " + TABLE_NAME + "(" + id
            + " INTEGER PRIMARY KEY AUTOINCREMENT, " + nama + " TEXT NOT NULL, " + no_telp + " TEXT NOT NULL, " + alamat + " TEXT NOT NULL, " + gender + " TEXT NOT NULL, "
        + olahraga + " TEXT NOT NULL, " + waktu + " TEXT NOT NULL);";


    databasehelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    void addBook (String nama, String no_telp, String alamat, String gender, String olahraga, String waktu){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValue = new ContentValues();
        contentValue.put(databasehelper.nama, nama);
        contentValue.put(databasehelper.no_telp, no_telp);
        contentValue.put(databasehelper.alamat, alamat);
        contentValue.put(databasehelper.gender, gender);
        contentValue.put(databasehelper.olahraga, olahraga);
        contentValue.put(databasehelper.waktu, waktu);
        long result = db.insert(databasehelper.TABLE_NAME, null, contentValue);
        if (result==-1){
            Toast.makeText(context, "Tambah Data Gagal", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "Tambah Data Berhasil", Toast.LENGTH_SHORT).show();
        }
    }
    Cursor readData(){
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if (db != null){
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }
    void updateData(String row_id, String nama, String no_telp, String alamat, String gender, String olahraga, String waktu){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(databasehelper.nama, nama);
        contentValues.put(databasehelper.no_telp, no_telp);
        contentValues.put(databasehelper.alamat, alamat);
        contentValues.put(databasehelper.gender, gender);
        contentValues.put(databasehelper.olahraga, olahraga);
        contentValues.put(databasehelper.waktu, waktu);

        long result = db.update(TABLE_NAME, contentValues, "id=?", new String[] {row_id});
        if (result==-1){
            Toast.makeText(context, "Update Data Gagal", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "Update Data Berhasil", Toast.LENGTH_SHORT).show();
        }
    }
    void deleteData(String row_id){
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(TABLE_NAME, "id=?", new String[] {row_id});

        if (result == -1){
            Toast.makeText(context, "Delete Data Gagal", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "Delete Data Berhasil", Toast.LENGTH_SHORT).show();
        }


    }
}
