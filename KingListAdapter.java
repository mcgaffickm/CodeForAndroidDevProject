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
import java.util.List;

public class KingListAdapter extends BaseAdapter {

    //Declares context, list, activity, variable
    Context myContext;
    List <Crest> crests;
    MainActivity mainActivity;
    int total = 0;

    //Constructor
    public KingListAdapter(Context myContext, List<Crest> crests) {
        this.myContext = myContext;
        this.crests = crests;
    }

    //Inserts the activity
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

        //Finds the views
        TextView name = convertView.findViewById(R.id.txtKingName);
        TextView date = convertView.findViewById(R.id.txtKingDate);
        TextView description = convertView.findViewById(R.id.txtKingDescription);

        ImageView img = convertView.findViewById(R.id.imgKingCrest);

        //Sets everything up
        name.setText(crests.get(position).getName());
        date.setText(crests.get(position).getDate());
        description.setText(crests.get(position).getName());
        img.setImageResource(crests.get(position).getImageId());

        final int iPosition = position;

        //Radio group to determine what was checked
        RadioGroup rg = convertView.findViewById(R.id.rgCrest);
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                int currentChange = 0;

                //The best ending change
                //Change button 20 times when best path is active
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

                //Radio buttons to determine correct answer
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

                //Calls something in main to determine if you get right answer
               mainActivity.kingPuzzlesChange(iPosition, currentChange == crests.get(iPosition).getCorrectAnswer());
            }
        });

        return convertView;
    }

}
