package com.example.nicenicenice.projectpam.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.nicenicenice.projectpam.R;
import com.example.nicenicenice.projectpam.model.Achivement;

import io.realm.Realm;

public class AddAchivement extends AppCompatActivity {
    Realm realm = null;

    EditText editTextID;
    EditText editTextTitle;
    EditText editTextDescription;
    EditText editTextEXP;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_achivement);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        try{
            realm = Realm.getDefaultInstance();
        }catch (Exception e)
        {
            Toast.makeText(getApplicationContext(),
                    e.getMessage(),Toast.LENGTH_LONG).show();
        }

        editTextID = (EditText)findViewById(R.id.etID);
        editTextTitle = (EditText)findViewById(R.id.etTitle);
        editTextDescription = (EditText)findViewById(R.id.etDescription);
        editTextEXP = (EditText)findViewById(R.id.etEXP);

        Button btnSubmit = (Button)findViewById(R.id.btnSubmit);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    realm.executeTransaction(new Realm.Transaction() {
                        @Override
                        public void execute(Realm realm) {
                            int idAchivement = Integer.parseInt(editTextID.getText().toString());
                            String title = editTextTitle.getText().toString();
                            String description = editTextDescription.getText().toString();
                            String exp = editTextEXP.getText().toString();

                            Achivement achivement = realm.createObject(Achivement.class, idAchivement);
                            achivement.setTitleAchivement(title);
                            achivement.setDescription(description);
                            achivement.setXp(exp);

                            Toast.makeText(getApplicationContext(), "Achivement Added", Toast.LENGTH_SHORT).show();
                            clearForm();
                        }
                    });
                }catch (Exception e){
                    Toast.makeText(getApplicationContext(),
                            e.getMessage(),Toast.LENGTH_LONG).show();
                }finally {

                }
            }
        });

        Button btnMyAch = (Button) findViewById(R.id.btnAllAchivement);
        btnMyAch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent iAllAch = new Intent(getApplicationContext(), ShowAchivement.class);
                startActivity(iAllAch);
            }
        });
    }

    void clearForm()
    {
        editTextID.setText("");
        editTextTitle.setText("");
        editTextDescription.setText("");
        editTextEXP.setText("");
    }

}
