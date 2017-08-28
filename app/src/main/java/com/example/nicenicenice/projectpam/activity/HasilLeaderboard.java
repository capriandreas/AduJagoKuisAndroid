package com.example.nicenicenice.projectpam.activity;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.nicenicenice.projectpam.R;
import com.example.nicenicenice.projectpam.model.ModelHasilLeaderboard;
import com.example.nicenicenice.projectpam.controller.ControllerLeaderboard;

import java.util.List;

public class HasilLeaderboard extends AppCompatActivity {

    ListView grdData;
    // Holder class used to efficiently recycle view positions
    private static final class Holder {
        public TextView tvNamaUser;
        public TextView tvPoint;
        public TextView tvStatus;
    }

    public HasilLeaderboard(){}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hasil_leaderboard);

        grdData = (ListView) findViewById(R.id.grdData);
        ControllerLeaderboard myData = new ControllerLeaderboard(this);
        myData.open();
        myData.getData();
        HasilLeaderboardAdapter adapter = new HasilLeaderboardAdapter(this,
                android.R.layout.simple_list_item_1, myData.getData());
        grdData.setAdapter(adapter);
        myData.close();
    }

    private class HasilLeaderboardAdapter extends ArrayAdapter<ModelHasilLeaderboard> {
        private LayoutInflater mInflater;
        public HasilLeaderboardAdapter(Context context, int textViewResourceId,
                                       List<ModelHasilLeaderboard> objects) {
            super(context, textViewResourceId, objects);
// TODO Auto-generated constructor stub
            mInflater = (LayoutInflater)
                    context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        @Override
        public View getView(int position, View convertView, ViewGroup parent){
            View view = convertView;
            Holder holder;
            if (view == null) {
// View doesn't exist so create it and create the holder
                view = mInflater.inflate(R.layout.custom_leaderboard, parent,
                        false);
                holder = new Holder();
                holder.tvNamaUser = (TextView) view.findViewById(R.id.tvNamaUser);
                holder.tvPoint = (TextView) view.findViewById(R.id.tvPoint);
                holder.tvStatus = (TextView) view.findViewById(R.id.tvStatus);
                view.setTag( holder);
            } else {
// Just get our existing holder
                holder = (Holder) view.getTag();
            }
// Populate via the holder for speed
            ModelHasilLeaderboard stream = getItem( position);
// Populate the item contents
            holder.tvNamaUser.setText(stream.getNama_user());
            holder.tvPoint.setText(Integer.toString(stream.getPoint()));
            holder.tvStatus.setText(stream.getStatus());
            return view;
        }
    }
}

