package com.example.nicenicenice.projectpam.activity;

import android.database.Cursor;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nicenicenice.projectpam.R;
import com.example.nicenicenice.projectpam.sqlite.DataBaseHelper;

import org.w3c.dom.Text;

public class ViewContactActivity extends AppCompatActivity {

    TextView txtResult;
    DataBaseHelper myDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_contact);
        myDb = new DataBaseHelper(this);
        initializeWidgets();
        viewDataContact();
    }

    public void initializeWidgets()
    {
        txtResult=(TextView)findViewById(R.id.idResult);
    }

    public void viewDataContact()
    {
        Cursor res = myDb.getAllData();
        StringBuffer stringBuffer = new StringBuffer();

        if(res!=null && res.getCount()>0)
        {
            while (res.moveToNext())
            {
                stringBuffer.append("Nama : "+res.getString(1)+"\n");
                stringBuffer.append("Komentar : "+res.getString(2)+"\n");
            }
            txtResult.setText(stringBuffer.toString());

            Toast.makeText(this, "Data Retrieved Successfully", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(this, "Data Failed", Toast.LENGTH_SHORT).show();
    }

    }
}
