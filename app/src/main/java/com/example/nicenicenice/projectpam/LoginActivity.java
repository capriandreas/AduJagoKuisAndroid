package com.example.nicenicenice.projectpam;

import android.app.NotificationManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Typeface;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nicenicenice.projectpam.sqlite.SQLiteDBHelper;

public class LoginActivity extends AppCompatActivity {
    //Loading button initialization
    private Button lb;
    private TextView toRegister;
    private EditText unameEditText;

    SQLiteOpenHelper dbhelper;
    SQLiteDatabase db;
    Cursor cursor;
    EditText emailText;
    EditText passwordText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);
        initializeWidgets();

        //Opening SQLite Pipeline
        dbhelper = new SQLiteDBHelper(this);
        db = dbhelper.getReadableDatabase();

        lb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pushNotification();
                openDashboard();
            }
        });

        toRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openRegister();
            }
        });
    }

    private void pushNotification()
    {
        NotificationCompat.Builder mBuilder =
                (NotificationCompat.Builder) new NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("Login Succeed")
                .setContentText("Adu kecerdasan mu di AduJagoKuis");

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(0, mBuilder.build());
    }

    private void openRegister()
    {
        Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
        startActivity(intent);
    }

    public void initializeWidgets()
    {
        lb = (Button)findViewById(R.id.loading_btn_login);
        toRegister = (TextView)findViewById(R.id.textViewLinkRegister);
        unameEditText = (EditText)findViewById(R.id.textInputEditTextUserEmail);
        passwordText = (EditText)findViewById(R.id.textInputEditTextPassword);
    }

    public void openDashboard()
    {
        String email = unameEditText.getText().toString();
        String pass = passwordText.getText().toString();

        cursor = db.rawQuery("SELECT *FROM "+SQLiteDBHelper.TABLE_NAME+" WHERE "+SQLiteDBHelper.COLUMN_EMAIL+"=? AND "+SQLiteDBHelper.COLUMN_PASSWORD+"=?",new String[] {email,pass});


        if (cursor != null) {
            if(cursor.getCount() > 0) {

                cursor.moveToFirst();
                //Retrieving User FullName and Email after successfull login and passing to LoginSucessActivity
                String _fname = cursor.getString(cursor.getColumnIndex(SQLiteDBHelper.COLUMN_FULLNAME));
                String _email= cursor.getString(cursor.getColumnIndex(SQLiteDBHelper.COLUMN_EMAIL));
                Toast.makeText(LoginActivity.this, "Login Success", Toast.LENGTH_SHORT).show();

                Intent nextScreen = new Intent(getApplicationContext(), MainActivity.class);
                nextScreen.putExtra("name", unameEditText.getText().toString());
                startActivity(nextScreen);

                //Removing MainActivity[Login Screen] from the stack for preventing back button press.
                finish();
            }
            else if (email.isEmpty() && pass.isEmpty())
            {
                AlertDialog.Builder builder2 = new AlertDialog.Builder(LoginActivity.this);
                builder2.setTitle("Alert");
                builder2.setMessage("Check your Username and Password");
                builder2.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        dialogInterface.dismiss();

                    }
                });
                AlertDialog dialog2 = builder2.create();
                dialog2.show();
            }
            else {

                //I am showing Alert Dialog Box here for alerting user about wrong credentials
                AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                builder.setTitle("Alert");
                builder.setMessage("Username or Password is wrong.");
                builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        dialogInterface.dismiss();

                    }
                });

                AlertDialog dialog = builder.create();
                dialog.show();
                //-------Alert Dialog Code Snippet End Here
            }
        }
    }
}
