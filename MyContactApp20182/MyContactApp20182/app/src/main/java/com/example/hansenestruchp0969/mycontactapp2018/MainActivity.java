package com.example.hansenestruchp0969.mycontactapp2018;

import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    DatabaseHelper myDb;
    EditText editName;
    EditText editPhone;
    EditText editAddress;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editName = findViewById(R.id.editText_Name);
        editPhone = findViewById(R.id.editText_Phone);
        editAddress = findViewById(R.id.editText_Address);

        myDb = new DatabaseHelper(this);
        Log.d("MyContactApp2018", "MainActivity: instantiated DatabaseHelper");
    }

    public void addData(View view) {
        Log.d("MyContactApp2018", "MainActivity: Add contact button pressed");

        boolean isInserted = myDb.insertData(editName.getText().toString(), editPhone.getText().toString(), editAddress.getText().toString());

        if (isInserted == true) {
            Toast.makeText(MainActivity.this, "Success - contact inserted", Toast.LENGTH_LONG).show();
        }
        else if (isInserted == false) {
            Toast.makeText(MainActivity.this, "Failure - contact not inserted", Toast.LENGTH_LONG).show();
        }
    }

    public void viewData(View view){
        Cursor res = myDb.getAllData();
        Log.d("MyContactApp2018",  "MainActivity: viewData: recieved cursor " + res.getCount());
        if (res.getCount() == 0){
            showMessage("Error", "No Data found in database");
            return;
        }

        StringBuffer buffer = new StringBuffer();
        while(res.moveToNext()){
            for (int i = 0; i < 4; i++) {
                buffer.append(res.getColumnName(i) + ": " + res.getString(i) + "\n");
            }
            buffer.append("\n");
        }

        showMessage("Data", buffer.toString());

        Log.d("MyContactApp2018",  "MainActivity: viewData: assembled string buffer");
        showMessage("Error","No Data Found in Database");

    }

    public void showMessage(String title, String message) {
        Log.d("MyContactApp2018",  "MainActivity: viewData: building alert dialog");

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }

    public static final String EXTRA_MESSAGE = "com.example.hansenestruchp0969.mycontactapp2018";
    public void SearchRecord(View view){
        Intent intent = new Intent(this, SearchActivity.class);
        intent.putExtra(EXTRA_MESSAGE, getRecords());
        startActivity(intent);
    }

    private String getRecords() {
        Cursor res = myDb.getAllData();
        Log.d("MyContactApp", "MainActivity: getRecords: received cursor");
        StringBuffer sb = new StringBuffer();
        int counter = 0;
        while (res.moveToNext()) {
            if (res.getString(1).equals(editName.getText().toString())) {
                for (int i = 1; i < 4; i++) {
                    sb.append(res.getColumnName(i) + ": " + res.getString(i) + "\n");
                }
                sb.append("\n");
                counter++;
            }
        }


        return sb.toString();

    }


}