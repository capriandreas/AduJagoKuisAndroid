package com.example.nicenicenice.projectpam.activity;

import android.content.Intent;
import android.support.annotation.BoolRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.nicenicenice.projectpam.MainActivity;
import com.example.nicenicenice.projectpam.R;
import com.example.nicenicenice.projectpam.sqlite.DataBaseHelper;

public class ContactActivity extends AppCompatActivity {

    DataBaseHelper myDb;
    EditText txtName, txtComment;
    Button btnSubmitContact, btnViewContact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        myDb = new DataBaseHelper(this);
        initializeWidgets();

        btnSubmitContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SubmitButtonContact();
                clearForm();
            }
        });

        btnViewContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewContactHistory();
            }
        });
    }

    public void initializeWidgets()
    {
        txtName = (EditText)findViewById(R.id.textInputEditTextContactName);
        txtComment = (EditText)findViewById(R.id.textInputEditTextContactComment);
        btnSubmitContact = (Button)findViewById(R.id.loading_btn_submit_contact);
        btnViewContact = (Button)findViewById(R.id.btn_view_contact_history);
    }

    public void viewContactHistory()
    {
        Intent intent = new Intent(ContactActivity.this, ViewContactActivity.class);
        startActivity(intent);
    }

    public void SubmitButtonContact()
    {
        String name = txtName.getText().toString();
        String comment = txtComment.getText().toString();

        Boolean result = myDb.insertData(name, comment);
        if(result == true)
        {
            Toast.makeText(this, "Data Inserted Successfully", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "Data Insertion Failed", Toast.LENGTH_SHORT).show();
        }
    }

    public void clearForm()
    {
        txtName.setText("");
        txtComment.setText("");
    }
}
