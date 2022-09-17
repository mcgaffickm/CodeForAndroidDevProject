package com.example.secretsofthecursedcastle;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

public class DecodeAdapter extends BaseAdapter {
    Context myContext;
    List<String> inputs;
    List<String> outputs;

    public DecodeAdapter(Context myContext, List<String> inputs, List<String> outputs) {
        this.myContext = myContext;
        this.inputs = inputs;
        this.outputs = outputs;
    }

    @Override
    public int getCount() {
        return inputs.size();
    }

    @Override
    public Object getItem(int position) {
        return inputs.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(myContext).inflate(R.layout.sample_decode_list, null);
        TextView input = convertView.findViewById(R.id.txtEnter);
        TextView output = convertView.findViewById(R.id.txtExit);

        input.setText(inputs.get(position));
        output.setText(outputs.get(position));

        return convertView;
    }
}
