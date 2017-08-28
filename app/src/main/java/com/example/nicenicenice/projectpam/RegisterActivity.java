package com.example.nicenicenice.projectpam;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Typeface;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.nicenicenice.projectpam.sqlite.SQLiteDBHelper;

public class RegisterActivity extends AppCompatActivity {

    //Loading button initialization
    private Button lb;
    private TextView toLogin;

    private EditText editTextName;
    private EditText editTextEmail;
    private EditText editTextPassword;
    private EditText editTextCpassword;

    SQLiteOpenHelper openHelper;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_register);
        initializeWidgets();
        openHelper = new SQLiteDBHelper(this);

        toLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openLogin();
            }
        });

        lb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerAction();
            }
        });

    }

    public void initializeWidgets()
    {
        lb = (Button)findViewById(R.id.loading_btn_register);
        toLogin = (TextView)findViewById(R.id.appCompatTextViewLoginLink);
        editTextName = (EditText)findViewById(R.id.textInputEditTextName);
        editTextEmail = (EditText)findViewById(R.id.textInputEditTextEmail);
        editTextPassword = (EditText)findViewById(R.id.textInputEditTextPassword);
        editTextCpassword = (EditText)findViewById(R.id.textInputEditTextConfirmPassword);
    }

    private void openLogin()
    {
        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
        startActivity(intent);
    }

    private void registerAction()
    {
        db = openHelper.getWritableDatabase();

        String fullname = editTextName.getText().toString();
        String email = editTextEmail.getText().toString();
        String pass = editTextPassword.getText().toString();
        String cpass = editTextCpassword.getText().toString();

        if(pass.matches(cpass))
        {

            //Calling InsertData Method - Defined below
            InsertData(fullname, email, pass);

            //Alert dialog after clicking the Register Account
            final AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
            builder.setTitle("Information");
            builder.setMessage("Your Account is Successfully Created.");
            builder.setPositiveButton("Okey", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                    //Finishing the dialog and removing Activity from stack.
                    dialogInterface.dismiss();
                    finish();
                }
            });

            AlertDialog dialog = builder.create();
            dialog.show();

        }
        else{
            final AlertDialog.Builder builder2 = new AlertDialog.Builder(RegisterActivity.this);
            builder2.setTitle("Alert");
            builder2.setMessage("Password doesn't match");
            builder2.setPositiveButton("Okey", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                    //Finishing the dialog and removing Activity from stack.
                    dialogInterface.dismiss();
                    //finish();
                }
            });

            AlertDialog dialog2 = builder2.create();
            dialog2.show();
        }
    }

    //Inserting Data into database - Like INSERT INTO QUERY.
    public void InsertData(String fullName, String email, String password ) {

        ContentValues values = new ContentValues();
        values.put(SQLiteDBHelper.COLUMN_FULLNAME,fullName);
        values.put(SQLiteDBHelper.COLUMN_EMAIL,email);
        values.put(SQLiteDBHelper.COLUMN_PASSWORD,password);
        long id = db.insert(SQLiteDBHelper.TABLE_NAME,null,values);

    }

}
