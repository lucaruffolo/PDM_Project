package com.example.pdm_project;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Database extends SQLiteOpenHelper {
    private Context context;
    private static final String DATABASE_NAME = "Userdata.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "my_gym";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_DATA = "data";
    private static final String COLUMN_NOME_ALLENAMENTO = "nome_allenamento";
    private static final String COLUMN_ESERCIZIO = "nome_esercizio";
    private static final String COLUMN_PESO_RIPOSO = "kg_riposo";
    private static final String COLUMN_DURATA_ALLENAMENTO = "durata_allenamento";

    Database(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME + " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_DATA + " TEXT NOT NULL, " +
                COLUMN_NOME_ALLENAMENTO + " TEXT NOT NULL, " +
                COLUMN_ESERCIZIO + " TEXT NOT NULL, " +
                COLUMN_PESO_RIPOSO + " TEXT, " +
                COLUMN_DURATA_ALLENAMENTO + " INTEGER);";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    void addAllenamento(String data, String nomeAllenamento, String esercizio, String pesoRiposo, int durataAllenamento ){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        //Log.d("MainActivity", data);

        String convertDate = convertDateFormat(data);

        //Log.d("MainActivity", outputDate);

        cv.put(COLUMN_DATA, convertDate);
        cv.put(COLUMN_NOME_ALLENAMENTO,nomeAllenamento);
        cv.put(COLUMN_ESERCIZIO,esercizio);
        cv.put(COLUMN_PESO_RIPOSO,pesoRiposo);
        cv.put(COLUMN_DURATA_ALLENAMENTO,durataAllenamento);
        long result = db.insert(TABLE_NAME, null, cv);
        if (result == -1){
            Toast.makeText(context, "Errore insert DB", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Inserito con successo!", Toast.LENGTH_SHORT).show();
        }

    }


    Cursor readAllData(){
        String query = "SELECT * FROM " + TABLE_NAME + " ORDER BY "+ COLUMN_DATA + " DESC";
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

    public Cursor getDataEsercizi(){
        String query = "SELECT "+ COLUMN_ESERCIZIO +" FROM " + TABLE_NAME ;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

    void updateData(String row_id,String nome, String data, String esercizio, String riposo, String durata){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_NOME_ALLENAMENTO, nome);
        cv.put(COLUMN_DATA, data);
        cv.put(COLUMN_ESERCIZIO, esercizio);
        cv.put(COLUMN_PESO_RIPOSO, riposo);
        cv.put(COLUMN_DURATA_ALLENAMENTO, durata);

        long result = db.update(TABLE_NAME, cv, "_id=?", new String[]{row_id});
        if(result == -1){
            Toast.makeText(context, "Aggiornamento fallito ", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "Allenamento aggiornato!", Toast.LENGTH_SHORT).show();
        }

    }
    void deleteOneRow(String row_id){
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(TABLE_NAME, "_id=?", new String[]{row_id});
        if(result == -1){
            Toast.makeText(context, "Errore elimina.", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Eliminato con successo!", Toast.LENGTH_SHORT).show();
        }
    }

    void deleteAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_NAME);
    }
    public static String convertDateFormat(String inputDate) {
        String outputDate = "";

        try {
            // Definisci il formato di input e di output
            SimpleDateFormat inputFormat = new SimpleDateFormat("dd/MM/yyyy");
            SimpleDateFormat outputFormat = new SimpleDateFormat("yyyyMMdd");

            // Parsa la data di input
            Date date = inputFormat.parse(inputDate);

            // Formatta la data nel nuovo formato
            outputDate = outputFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return outputDate;
    }
}

