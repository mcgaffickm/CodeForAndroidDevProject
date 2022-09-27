package com.example.secretsofthecursedcastle;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class DecodeAdapter extends BaseAdapter {
    //List declaration
    Context myContext;
    List<String> inputs;
    List<String> outputs;

    //Constructor
    public DecodeAdapter(Context myContext, List<String> inputs, List<String> outputs) {
        this.myContext = myContext;
        this.inputs = inputs;
        this.outputs = outputs;
    }

    //Stuff I didnt use, but was required
    @Override
    public int getCount() {
        return inputs.size();
    }

    //Stuff I didnt use, but was required
    @Override
    public Object getItem(int position) {
        return inputs.get(position);
    }

    //Stuff I didnt use, but was required
    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(myContext).inflate(R.layout.sample_decode_list, null);

        //Fills the list view with everything that is needed
        TextView input = convertView.findViewById(R.id.txtEnter);
        TextView output = convertView.findViewById(R.id.txtExit);

        //Sets the listview text
        input.setText(inputs.get(position));
        output.setText(outputs.get(position));

        return convertView;
    }
}
