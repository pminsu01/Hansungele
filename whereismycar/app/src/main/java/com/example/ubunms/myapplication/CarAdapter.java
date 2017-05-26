package com.example.ubunms.myapplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import android.widget.ImageView;

import org.w3c.dom.Text;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import android.widget.ImageView;
import android.widget.TextView;


/* Created by ubunms on 17. 5. 17.
*/

public class CarAdapter extends BaseAdapter {
    Context context;
    String[] car_where={};
    String[] car_string={};
    String[] car_number={};
    String[] car_url={};


    public CarAdapter(Context context,String[] car_string, String[] car_number,String[] car_where, String[] car_url){

        Log.d("caradapter","도착했어!");

        this.context = context;
        this.car_url =car_url;
        this.car_where =car_where;
        this.car_string = car_string;
        this.car_number = car_number;
        Log.d("Caradpater",car_string[0]);
        Log.d("Caradapter",car_number[0]);
        Log.d("cards",car_where[0]);
        Log.d("dsfsdf",car_url[0]);
    }



    @Override
    public int getCount() {
        return car_string.length;
    }

    @Override
    public Object getItem(int position) {

        return car_string[position];

    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = LayoutInflater.from(context);
        convertView = inflater.inflate(R.layout.image_item,parent,false);
        ImageView imageView = (ImageView)convertView.findViewById(R.id.iv_image);
        TextView string_info= (TextView)convertView.findViewById(R.id.car_String);
        TextView number_info = (TextView)convertView.findViewById(R.id.car_number);
        TextView where_info= (TextView)convertView.findViewById(R.id.car_where);

        Image_View image_view = new Image_View();
        image_view.setImage(imageView,car_url[position]);
        string_info.setText(car_string[position]);
        number_info.setText(car_number[position]);
        where_info.setText(car_where[position]);

        return convertView;

        //car_each_info 0번 차량 string , 1번 차량 넘버, 2번 차량 위치 , 3번 차량 url

    }
}

