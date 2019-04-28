package com.app.foodapp.Logic.Settings;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.app.foodapp.Data.AddressContract;
import com.app.foodapp.Data.AddressDBHelper;
import com.app.foodapp.Logic.MainActivityMenu.Menu;
import com.app.foodapp.Logic.OrderConfirmed.OrderConfirmed;
import com.app.foodapp.R;

public class Settings extends AppCompatActivity {

    SQLiteDatabase writeableDB;
    SQLiteDatabase readableDB;

    EditText etCity;
    EditText etStreet;
    EditText etHouseNum;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        etCity = findViewById(R.id.etSettingsCity);
        etStreet = findViewById(R.id.etSettingsStreet);
        etHouseNum = findViewById(R.id.etSettingsNumber);


        AddressDBHelper addressDBHelper = new AddressDBHelper(this);
        writeableDB = addressDBHelper.getWritableDatabase();
        readableDB = addressDBHelper.getReadableDatabase();

        read();

        final Button button = findViewById(R.id.btnSettingsBackToMenu);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), Menu.class);

                deleteAllAddresses();
                insertAddress();
                startActivity(intent);
            }
        });

    }

    private void insertAddress() {
        ContentValues values = new ContentValues();
        values.put(AddressContract.AddressEntry.COLUMN_CITY_NAME, etCity.getText().toString());
        values.put(AddressContract.AddressEntry.COLUMN_STREET_NAME, etStreet.getText().toString());
        values.put(AddressContract.AddressEntry.COLUMN_HOUSE_NUMBER, etHouseNum.getText().toString());
        writeableDB.insert(AddressContract.AddressEntry.TABLE_NAME, null, values);

    }

    private void read() {
        AddressDBHelper addressDBHelper = new AddressDBHelper(this);
        SQLiteDatabase db = addressDBHelper.getReadableDatabase();

        String[] projection = {
                AddressContract.AddressEntry._ID,
                AddressContract.AddressEntry.COLUMN_CITY_NAME,
                AddressContract.AddressEntry.COLUMN_STREET_NAME,
                AddressContract.AddressEntry.COLUMN_HOUSE_NUMBER};

        Cursor cursor = db.query(
                AddressContract.AddressEntry.TABLE_NAME, projection, null, null, null, null, null);

        try {

            while (cursor.moveToNext()) {
                int currentID = cursor.getInt(cursor.getColumnIndex(AddressContract.AddressEntry._ID));
                String city = cursor.getString(cursor.getColumnIndex(AddressContract.AddressEntry.COLUMN_CITY_NAME));
                String street = cursor.getString(cursor.getColumnIndex(AddressContract.AddressEntry.COLUMN_STREET_NAME));
                String houseNum = cursor.getString(cursor.getColumnIndex(AddressContract.AddressEntry.COLUMN_HOUSE_NUMBER));

                etCity.setText(city);
                etStreet.setText(street);
                etHouseNum.setText(houseNum);

            }
        } finally {
            cursor.close();
        }
    }

    private void deleteAllAddresses() {
        writeableDB.delete(AddressContract.AddressEntry.TABLE_NAME, null, null);
    }

}
