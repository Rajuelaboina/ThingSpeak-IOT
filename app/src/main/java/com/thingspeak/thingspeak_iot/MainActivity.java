package com.thingspeak.thingspeak_iot;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class MainActivity extends AppCompatActivity {

    private ProgressBar progressBar;
    private TextView tvName,tv_lat,tv_log;
    private  RecyclerView recyclerView;
    private final List<Feeds> list=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressBar=findViewById(R.id.progressBar2);
        tvName=findViewById(R.id.textView_Name);
        tv_lat=findViewById(R.id.textView_latitude);
        tv_log=findViewById(R.id.textView_longitude);
        recyclerView=findViewById(R.id.recyclerView_feeds);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        ExecutorService executor = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());
       //onPreExecute() before to get into executor, as below
      //  progressBar_main_activity.setVisibility(View.VISIBLE);
        executor.execute(() -> {

            //Background work here
            runbackground();

            //UI Thread work here
            handler.post(this::update_UI);
        });
    }

    private void update_UI() {


    }

    private void runbackground() {

        String url = "https://api.thingspeak.com/channels/1984207/status.json?api_key=DAZGXH8X5X7HJUTX";
        StringRequest stringRequest= new StringRequest(url, response -> {
            try {
                if (!response.isEmpty()){
                    progressBar.setVisibility(View.INVISIBLE);

                JSONObject jsonObject=new JSONObject(response);
                JSONObject   jb=jsonObject.getJSONObject("channel");
                tvName.setText(jb.getString("name"));
                tv_lat.setText(jb.getString("latitude"));
                tv_log.setText(jb.getString("longitude"));
                JSONArray jsonArray=jsonObject.getJSONArray("feeds");
                for (int i = 0 ; i < jsonArray.length(); i++){
                    JSONObject js=jsonArray.getJSONObject(i);
                    Log.e("js<><><><><<","js: "+" i "+js);
                    list.add(new Feeds(
                            js.getString("created_at"),
                            js.getString("entry_id"),
                            js.getString("status")
                    ));

                }

                //call to adapter
                ChannelAdapter adapter=new ChannelAdapter(list,getApplicationContext());
                recyclerView.setAdapter(adapter);
                //adapter.notifyDataSetChanged();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }, error -> {

        });
        RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);
    }
}