package com.example.nicenicenice.projectpam;


import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.Image;
import android.net.Uri;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MenuInflater;
import android.widget.Toast;

import com.example.nicenicenice.projectpam.activity.AllContacts;
import com.example.nicenicenice.projectpam.activity.ContactActivity;
import com.example.nicenicenice.projectpam.activity.ShowAchivement;
import com.example.nicenicenice.projectpam.interfaces.APIHasilLeaderboard;
import com.example.nicenicenice.projectpam.activity.ViewContactActivity;
import com.example.nicenicenice.projectpam.controller.*;
import com.example.nicenicenice.projectpam.model.*;
import com.example.nicenicenice.projectpam.activity.*;
import com.example.nicenicenice.projectpam.singleton.RESTClient;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import com.example.nicenicenice.projectpam.model.AppVersion;

public class MainActivity extends FragmentActivity {

    private DatabaseReference mRootRef;
    FirebaseDatabase database = FirebaseDatabase.getInstance();

    ImageView imgBtnContact;
    TextView textUnameDashboard;
    ImageView imgBtnMyContact;
    ImageView imgBtnMyAchivement;
    ImageView imgBtnMyLeaderboard;

    APIHasilLeaderboard ApiHasilLeaderboard;
    Context myContext;
    ProgressDialog progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Firebase
                DatabaseReference mRootRef = database.getReference();
                ValueEventListener appListener = new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                         AppVersion appVersion = dataSnapshot.getValue(AppVersion.class);
                        Toast.makeText(getApplicationContext(), "Version: " +
                                appVersion.getVersion() + "\nApp Name: " + appVersion.getAppName() + "\nLast Update: " +
                                appVersion.getLastUpdate(), Toast.LENGTH_LONG).show();
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                    }
                };
                mRootRef.addValueEventListener(appListener);

        initializeWidgets();

        getExplicitIntentUname();

        myContext = getApplicationContext();
        progress = ProgressDialog.show(MainActivity.this, "Inisialisasi Data", "Sedang Mengunduh Data Untuk Aplikasi", true);
        Callback<ResponseLeaderboard> hasiltandingbola = new
                Callback<ResponseLeaderboard>() {
                    @Override
                    public void onResponse(Call<ResponseLeaderboard> call,
                                           Response<ResponseLeaderboard> response) {
                        if (response.isSuccessful()) {
                            List<ModelHasilLeaderboard> hasilBola =
                                    response.body().getHasil();
                            int jumlahData = response.body().getHasil().size();
                            if (jumlahData > 0) {
                                ControllerLeaderboard chb = new ControllerLeaderboard(
                                        myContext);
                                chb.open();
                                chb.deleteData();
                                for (int y = 0; y < jumlahData; y++) {
                                    ModelHasilLeaderboard tmpHasil = hasilBola.get(y);
                                    chb.insertData(
                                            tmpHasil.getId(), tmpHasil.getNama_user(), tmpHasil.getPoint(),
                                            tmpHasil.getStatus());
                                }
                                chb.close();
                            } else {
                                Toast.makeText(getApplicationContext(), "DATA SEDANG TIDAK TERSEDIA", Toast.LENGTH_LONG).show();
                            }
                            progress.dismiss();
                        } else {
                            Log.e("onResponse failure", "Code: " + response.code() +
                                    " , Message: " + response.message());
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseLeaderboard> call, Throwable t) {
// TODO Auto-generated method stub
                        progress.dismiss();
                        Toast.makeText(getApplicationContext(), "AKSES KE SERVER GAGAL" + t.getMessage(), Toast.LENGTH_LONG).show();
                    }
                };
//consuming web service here
        ApiHasilLeaderboard = RESTClient.get();
        Call<ResponseLeaderboard> callHasilBola = ApiHasilLeaderboard.getHasilLeaderboard();
        callHasilBola.enqueue(hasiltandingbola);

        this.registerReceiver(this.myBatteryReceiver, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));

        imgBtnContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openContactActivity();
            }
        });

        imgBtnMyContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMyContactActivity();
            }
        });

        imgBtnMyAchivement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            openMyAchivementActivity();
            }
        });

        imgBtnMyLeaderboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMyLeaderboardActivity();
            }
        });

    }

    public void initializeWidgets()
    {
        imgBtnContact = (ImageView)findViewById(R.id.imageButtonContact);
        textUnameDashboard = (TextView)findViewById(R.id.unameDashboard);
        imgBtnMyContact = (ImageView)findViewById(R.id.imageButtonMyContact);
        imgBtnMyAchivement = (ImageView)findViewById(R.id.imageButtonAchivement);
        imgBtnMyLeaderboard = (ImageView)findViewById(R.id.imageButtonLeaderboard);
    }

    public void openContactActivity()
    {
        Intent intent = new Intent(MainActivity.this, ContactActivity.class);
        startActivity(intent);
    }

    public void openMyContactActivity()
    {
        Intent intent = new Intent(MainActivity.this, AllContacts.class);
        startActivity(intent);
    }

    public void openMyAchivementActivity()
    {
        Intent intent = new Intent(MainActivity.this, ShowAchivement.class);
        startActivity(intent);
    }

    public void openMyLeaderboardActivity()
    {
        Intent sendIntent = new Intent(myContext, HasilLeaderboard.class);
        startActivity(sendIntent);
    }

    public void getExplicitIntentUname()
    {
        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        textUnameDashboard.setText(name);
    }

    public void openCIS(View view) {
        Uri uri = Uri.parse("http://cis.del.ac.id");
        Intent i = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(i);
    }

    private BroadcastReceiver myBatteryReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            int lvl = intent.getIntExtra("level", 0);
            if (lvl == 100) {
                Toast.makeText(context, "Battery Full.",
                        Toast.LENGTH_LONG).show();
            }

        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item1 : {
                Toast.makeText(getApplicationContext(), "Anda memilih menu General Quiz",
                        Toast.LENGTH_SHORT).show();
                break;
            }
            case R.id.item2 : {
                Toast.makeText(getApplicationContext(), "Anda memilih menu Science Quiz",
                        Toast.LENGTH_SHORT).show();
                break;
            }
            case R.id.item3 : {
                Toast.makeText(getApplicationContext(), "Anda memilih menu Sports Quiz",
                        Toast.LENGTH_SHORT).show();
                break;
            }
        }
        return super.onOptionsItemSelected(item);
    }
}
