package com.example.hansenestruchp0969.mycontactapp;
import android.app.AlertDialog;
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

        boolean isInserted = myDb.insertData(editName.getText().toString(),editPhone.getText().toString(),editAddress.getText().toString());

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
        }

        StringBuffer buffer = new StringBuffer();
        while(res.moveToNext()){
            //Append stuff
        }
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


}