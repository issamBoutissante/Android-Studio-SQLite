package com.example.sqlite;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class CarsListAdapter extends BaseAdapter {
    private ArrayList<Car> cars;
    private Context context;
    public CarsListAdapter(Context context,ArrayList<Car> cars) {
        this.cars = cars;
        this.context=context;
    }

    @Override
    public int getCount() {
        return cars.size();
    }

    @Override
    public Object getItem(int position) {
        return cars.get(position);
    }

    @Override
    public long getItemId(int position) {
        return cars.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view=convertView;
        if(view==null){
            view=LayoutInflater.from(context).inflate(R.layout.cars_liste_item,null,false);
        }
        TextView idTxt= view.findViewById(R.id.idTXT);
        TextView modelTxt=view.findViewById(R.id.modelTXT);
        TextView colorTxt=view.findViewById(R.id.colorTXT);
        idTxt.setText(cars.get(position).getId().toString());
        modelTxt.setText(cars.get(position).getModel().toString());
        colorTxt.setText(cars.get(position).getColor().toString());
        return view;
    }
}
