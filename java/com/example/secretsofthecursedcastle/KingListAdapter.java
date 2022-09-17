package com.example.secretsofthecursedcastle;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.List;
import java.util.zip.Inflater;

public class KingListAdapter extends BaseAdapter {

    Context myContext;
    List <Crest> crests;

    MainActivity mainActivity;
    int total = 0;

    public KingListAdapter(Context myContext, List<Crest> crests) {
        this.myContext = myContext;
        this.crests = crests;
    }

    public void insertActvity(MainActivity mainActivity)
    {
        this.mainActivity = mainActivity;
    }

    @Override
    public int getCount() {
        return crests.size();
    }

    @Override
    public Object getItem(int position) {
        return crests.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(myContext).inflate(R.layout.sample_crest_list, null);
        TextView name = convertView.findViewById(R.id.txtKingName);
        TextView date = convertView.findViewById(R.id.txtKingDate);
        TextView description = convertView.findViewById(R.id.txtKingDescription);

        ImageView img = convertView.findViewById(R.id.imgKingCrest);

        name.setText(crests.get(position).getName());
        date.setText(crests.get(position).getDate());
        description.setText(crests.get(position).getName());
        img.setImageResource(crests.get(position).getImageId());

        final int iPosition = position;

        RadioGroup rg = convertView.findViewById(R.id.rgCrest);

        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                int currentChange = 0;
                if(mainActivity.isBest())
                {
                    total++;
                    if(!mainActivity.kingRoomBest) {
                        if (total == 20) {
                            Toast.makeText(myContext, "Setting Course for New History.", Toast.LENGTH_SHORT).show();
                            mainActivity.checkCount();
                            mainActivity.kingRoomBest = true;
                        }
                    }
                }
                switch (checkedId) {
                    case R.id.rbAxe:
                       currentChange = 3;
                        break;
                    case R.id.rbHelm:
                        currentChange = 4;
                        break;
                    case R.id.rbSword:
                        currentChange = 1;
                        break;
                    case R.id.rbShield:
                        currentChange = 2;
                        break;
                }
               mainActivity.kingPuzzlesChange(iPosition, currentChange == crests.get(iPosition).getCorrectAnswer());
            }
        });

        return convertView;
    }

}
