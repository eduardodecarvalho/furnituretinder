package com.furniturecombiner.model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.furniturecombiner.R;

import java.util.List;

public class arrayAdapter extends ArrayAdapter<Item> {

    public arrayAdapter(Context context, int resourceId, List<Item> items){
        super(context, resourceId, items);
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        Item card_item = getItem(position);

        if (convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item, parent, false);
        }

        TextView name = (TextView) convertView.findViewById(R.id.name);
        name.setText(card_item.getName());
        TextView description = (TextView) convertView.findViewById(R.id.description);
        description.setText(card_item.getDescription());
        TextView price = (TextView) convertView.findViewById(R.id.price);
        price.setText(card_item.getPrice());
        ImageView image = (ImageView) convertView.findViewById(R.id.picture);

        if ("default".equals(card_item.getPicture())) {
            Glide.with(convertView.getContext()).load(R.mipmap.ic_launcher).into(image);
        } else {
            image.setImageResource(card_item.getImageResource());
//            Glide.clear(image);
//            Glide.with(convertView.getContext()).load(card_item.getPicture()).into(image);
        }
        return convertView;
    }
}
