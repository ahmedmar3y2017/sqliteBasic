package com.example.ahmedmar3y.sqlitebasic;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        DatabaseHelper databaseHelper = new DatabaseHelper(this);
        databaseHelper.insertIntoDatabase(new person("ahmed", "112", "tanta"));
        databaseHelper.insertIntoDatabase(new person("mohamed", "987", "cairo"));


        List<person> persons = databaseHelper.getPersons();
//
//
        Log.d("Size is : ", persons.size() + "");

    }
}
