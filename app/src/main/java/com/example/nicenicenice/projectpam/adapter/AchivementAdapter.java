package com.example.nicenicenice.projectpam.adapter;

/**
 * Created by capri on 5/13/2017.
 */
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.nicenicenice.projectpam.R;
import com.example.nicenicenice.projectpam.model.Achivement;

import io.realm.OrderedRealmCollection;
import io.realm.RealmRecyclerViewAdapter;
import io.realm.RealmResults;

public class AchivementAdapter extends RealmRecyclerViewAdapter<Achivement, AchivementAdapter.ViewHolder>
{
    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView titleAch;
        TextView descriptionAch;
        TextView expAch;

        public ViewHolder(View view)
        {
            super(view);
            titleAch = (TextView)view.findViewById(R.id.text_achivement_title);
            descriptionAch = (TextView)view.findViewById(R.id.text_achivement_description);
            expAch = (TextView)view.findViewById(R.id.text_achivement_exp);
        }
    }

    public AchivementAdapter(RealmResults<Achivement>data){
        super(data, true);
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.tampilan_achivement, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position)
    {
        final Achivement obj = getItem(position);

        int idAchivement = obj.getIdAvhivement();
        String title = obj.getTitleAchivement();
        String description = obj.getDescription();
        String exp = obj.getXp();

        holder.titleAch.setText(" "+title);
        holder.descriptionAch.setText(" "+description);
        holder.expAch.setText(" "+exp);
    }
}
