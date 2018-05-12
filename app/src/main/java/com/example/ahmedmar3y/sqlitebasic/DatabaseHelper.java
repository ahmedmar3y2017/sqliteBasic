package com.example.ahmedmar3y.sqlitebasic;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ahmed mar3y on 12/05/2018.
 */

public class DatabaseHelper extends SQLiteOpenHelper {


    private static final int DATABASE_VERSION = 1;

    private static String DATABASE_NAME = "task";
    private static String TABLE_NAME = "person";

    private static String KEY_ID = "id";
    private static String KEY_NAME = "name";
    private static String KEY_PHONE = "phone";
    private static String KEY_ADDRESS = "address";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    //create table
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String createTable = "CREATE TABLE  " + TABLE_NAME + " ( " + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT , "
                + KEY_NAME + " TEXT , "
                + KEY_PHONE + " TEXT , "
                + KEY_ADDRESS + " TEXT )";


        sqLiteDatabase.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);


        onCreate(sqLiteDatabase);

    }


    // insert into table
    public void insertIntoDatabase(person person) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_NAME, person.getName());
        contentValues.put(KEY_PHONE, person.getPhoneNumber());
        contentValues.put(KEY_ADDRESS, person.getAddress());


        sqLiteDatabase.insert(TABLE_NAME, null, contentValues);
        sqLiteDatabase.close();

    }

    // get one result
    public person getPerson(int id) {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();

        Cursor cursor = sqLiteDatabase.query(TABLE_NAME, new String[]{KEY_ID,
                        KEY_NAME, KEY_PHONE, KEY_ADDRESS}, KEY_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        person person = new person(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3));


        return person;
    }

    // get all persons
    public List<person> getPersons() {


        ArrayList<person> persons = new ArrayList<>();

        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + TABLE_NAME, null);

        if (cursor.moveToFirst()) {

            do {

                person person = new person(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3));
                persons.add(person);

            } while (cursor.moveToNext());


        }
        return persons;

    }

    // get count of person List
    public int getPersonCount() {

        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        cursor.close();


        return cursor.getCount();

    }


    // update person

    public int UpdatePerson(person person) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();


        ContentValues contentValues = new ContentValues();
        contentValues.put("name", person.getName());
        contentValues.put("phone", person.getPhoneNumber());
        contentValues.put("address", person.getAddress());

        int updated = sqLiteDatabase.update(TABLE_NAME, contentValues, "id=?", new String[]{String.valueOf(person.getId())});


        return updated;
    }


    // delete person

    public void deletePerson(int id) {

        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        sqLiteDatabase.delete(TABLE_NAME, "id=?", new String[]{String.valueOf(sqLiteDatabase)});
        sqLiteDatabase.close();

    }


}
