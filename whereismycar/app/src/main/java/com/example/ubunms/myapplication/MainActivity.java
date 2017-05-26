package com.example.ubunms.myapplication;

import android.app.Activity;
import android.app.Dialog;
import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Date;
import java.util.concurrent.ExecutionException;

import static com.example.ubunms.myapplication.R.id.car_where;
import static com.example.ubunms.myapplication.R.id.textview2;


//import com.example.ubunms.myapplication.SendData;

public class MainActivity extends AppCompatActivity {

    EditText editText;
    EditText editText1;
    EditText editText2;
    EditText editText3;
    Button button;
    String car_number;
    //Serverconnect car_server_info;
    int[] images;
    String[] car_find_string={"차량글자"};   //초기값 선언해줌
    String[] car_find_number={"차량넘버"};
    String[] car_find_where={"차량위치"};
    String[] car_find_url={"차량url"};
    //SendData sendData;
    CarAdapter carAdapter;
    String status;

    @Override




    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        ImageButton f_bu =(ImageButton)findViewById(R.id.fir);
        ImageButton t_bu =(ImageButton)findViewById(R.id.sec);
        ImageButton th_bu =(ImageButton)findViewById(R.id.third);
        ImageButton fo_bu =(ImageButton)findViewById(R.id.four);
        ImageButton fiv_bu =(ImageButton)findViewById(R.id.five);
        ImageButton s_bu =(ImageButton)findViewById(R.id.six);
        ImageButton se_bu =(ImageButton)findViewById(R.id.seven);
        ImageButton e_bu =(ImageButton)findViewById(R.id.eight);
        ImageButton n_bu =(ImageButton)findViewById(R.id.nine);
        ImageButton zero =(ImageButton)findViewById(R.id.zero);
        ImageButton back =(ImageButton)findViewById(R.id.back);


       final TextView car_num =(TextView)findViewById(R.id.textview2);

        ImageButton next =(ImageButton)findViewById(R.id.next);


        f_bu.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {

                car_num.append("1");

            }
        });
        t_bu.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {

                car_num.append("2");

            }
        });

        th_bu.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {

                car_num.append("3");
            }
        });

        fo_bu.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {

                car_num.append("4");
            }
        });

        fiv_bu.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                car_num.append("5");

            }
        });

        s_bu.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {

                car_num.append("6");
            }
        });

        se_bu.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {

                car_num.append("7");

            }
        });

        e_bu.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                car_num.append("8");

            }
        });

               n_bu.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {

                car_num.append("9");

            }
        });

        zero.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {

                car_num.append("0");

            }
        });


        back.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(car_num.length() <1)

                {
                    car_num.setText("");
                }

                else {
                    String edival = car_num.getText().toString();
                    edival = edival.substring(0, car_num.length() - 1);
                    car_num.setText(edival);

                }


            }
        });




        //ListView listView =(ListView)findViewById(R.id.listview);
        //TextView =(TextView)findViewById(R.id.textView);

        carAdapter = new CarAdapter(this,car_find_string,car_find_number,car_find_where,car_find_url);
        //carAdapter.notifyDataSetChanged();
        //listView.setAdapter(carAdapter);
        //carAdapter.




        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                car_number = car_num.getText().toString();
                Log.i("박민수",car_number);

                try {
                    connectcheck();

                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Log.i("박민수","실행되었군!");

            }
        });

        //Log.i("create",car_find_number[0]);



    }



    private void connectcheck() throws ExecutionException, InterruptedException { // 네트워크 상태 check


        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected()) {
                Log.i("박민수","conect check");

                refine(car_number);

        }

        else {
            // display error
            Toast.makeText(this, "네트워크 상태를 확인하십시오", Toast.LENGTH_SHORT).show();
        }


    }


    public void refine(String car_num_num) throws ExecutionException, InterruptedException {      // car_info split 해서 할 것


        final ProgressDialog dialog;
        dialog = new ProgressDialog(MainActivity.this);
        dialog.show();

        String car_in = new Serverconnect(car_num_num).new SendData().execute().get(); //이게 오류남
        Log.d("박민수","여기되려나");
        Log.d("car_in",car_in);

        if(car_in.equals("0")) {
            Toast.makeText(getApplicationContext(),"차량이 없습니다",Toast.LENGTH_SHORT).show();
            dialog.dismiss();
            return;
        }

        else {

                String[] car_fofo = car_in.split("@");
                Log.d("car_fofo",car_fofo[0]); //여기까지 실행됌   골뱅이 중심으로 깨진다.

                car_find_string = new String[car_fofo.length];
                car_find_number = new String[car_fofo.length];
                car_find_where = new String[car_fofo.length];
                car_find_url = new String[car_fofo.length];

                    for(int i=0; i<car_fofo.length;i++) {       // 갯수처럼

                        String[] car_ff = car_fofo[i].split(" ");
                        Log.i("car_ff",car_ff[0]);

                        car_find_string[i] = car_ff[0];
                        car_find_number[i] = car_ff[1];
                        car_find_where[i]  = car_ff[2];
                        car_find_url[i]    = car_ff[3];

                }



            Log.i("박민수",car_fofo[0]);
            carAdapter = new CarAdapter(this,car_find_string,car_find_number,car_find_where,car_find_url);
            carAdapter.notifyDataSetChanged();
            ListView listView =(ListView)findViewById(R.id.listview);
            listView.setAdapter(carAdapter);
            dialog.dismiss();




        }




    }







    }







