package com.example.nicenicenice.projectpam.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.ListViewCompat;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import io.realm.Realm;
import io.realm.RealmRecyclerViewAdapter;
import io.realm.RealmResults;

import com.example.nicenicenice.projectpam.MainActivity;
import com.example.nicenicenice.projectpam.R;
import com.example.nicenicenice.projectpam.adapter.AchivementAdapter;
import com.example.nicenicenice.projectpam.model.Achivement;

public class ShowAchivement extends AppCompatActivity {

    private Realm realm;
    RecyclerView recyclerView = null;
    public AchivementAdapter achivementAdapter;
    Button btnAddAchivement;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_achivement);

        realm = Realm.getDefaultInstance();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recyclerView = (RecyclerView)findViewById(R.id.recycler_view);
        setUpRecyclerView();

        btnAddAchivement = (Button)findViewById(R.id.btnAddAchivement);
        btnAddAchivement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAddAchivementActivity();
            }
        });

    }

    private void setUpRecyclerView()
    {
        RealmResults<Achivement> hasil = realm.where(Achivement.class).findAll();

        achivementAdapter = new AchivementAdapter(hasil);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(achivementAdapter);
        recyclerView.setHasFixedSize(true);
    }

    public void openAddAchivementActivity()
    {
        Intent intent = new Intent(ShowAchivement.this, AddAchivement.class);
        startActivity(intent);
    }

}
