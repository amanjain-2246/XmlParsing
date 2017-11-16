package com.example.amanj.xmlparsing;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by amanj on 10/28/2017.
 */

public class FlowerAdaptor extends ArrayAdapter<Flower>
{

    private Context context;
    private List<Flower> flowerList;

    public FlowerAdaptor(Context context, int resource, List<Flower> objects)

    {
        super(context, resource, objects);
        this.context = context;
        this.flowerList = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.one_row, parent, false);
        Flower flower = flowerList.get(position);
        ImageView photo=(ImageView)view.findViewById(R.id.imageView);
        TextView name = (TextView) view.findViewById(R.id.name);
        TextView category = (TextView) view.findViewById(R.id.category);
        TextView price = (TextView) view.findViewById(R.id.price);
        TextView instruction = (TextView) view.findViewById(R.id.instruction);
        name.setText(flower.getName());
        category.setText(flower.getCategory());
        price.setText(flower.getPrice());
        instruction.setText(flower.getInstructions());

        Glide.with(context).load("http://services.hanselandpetal.com/photos/"+flower.getPhoto()).into(photo);
        return view;

    }
}
