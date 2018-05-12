package com.example.ahmedmar3y.sqlitebasic;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.List;
import java.util.stream.Collectors;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // insert call

        DatabaseHelper databaseHelper = new DatabaseHelper(this);
        databaseHelper.insertIntoDatabase(new person("ahmed", "112", "tanta"));
        databaseHelper.insertIntoDatabase(new person("mohamed", "987", "cairo"));


        // select call
        List<person> persons = databaseHelper.getPersons();
        for (person person : persons) {

            Log.d("Name : ", person.getName());
            Log.d("phone : ", person.getPhoneNumber());
            Log.d("address : ", person.getAddress());

        }

        // update call
        databaseHelper.UpdatePerson(new person(1, "esmail", "213", "shebin"));

        // delete call
        databaseHelper.deletePerson(2);

        // select call
        List<person> persons1 = databaseHelper.getPersons();
        for (person person : persons1) {

            Log.d("Name : ", person.getName());
            Log.d("phone : ", person.getPhoneNumber());
            Log.d("address : ", person.getAddress());

        }


    }
}
