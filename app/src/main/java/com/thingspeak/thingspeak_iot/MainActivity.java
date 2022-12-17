package com.thingspeak.thingspeak_iot;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


public class MainActivity extends AppCompatActivity {

    //String url="https://api.thingspeak.com/channels/1984207/status.json?api_key=DAZGXH8X5X7HJUTX";
    ProgressBar progressBar;
    TextView tvName,tv_lat,tv_log;
    RecyclerView recyclerView;
    List<Feeds> list=new ArrayList<>();
    ApiRequest apiRequest;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressBar=findViewById(R.id.progressBar2);
        tvName = findViewById(R.id.textView_Name);
        tv_lat = findViewById(R.id.textView_latitude);
        tv_log = findViewById(R.id.textView_longitude);
        recyclerView = findViewById(R.id.recyclerView_feeds);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
         Retrofit retrofit= RetrofitRequest.getInstance();
         apiRequest=retrofit.create(ApiRequest.class);


        // ==========  using from Retrofit =====///
        ExecutorService executor = Executors.newSingleThreadExecutor();
       // Handler handler = new Handler(Looper.getMainLooper());
        //onPreExecute() before to get into executor, as below
        //  progressBar_main_activity.setVisibility(View.VISIBLE);
        //Background work here
        //UI Thread work here
        // handler.post(this::update_UI);
        executor.execute(this::runbackground);
    }

    private void runbackground() {
        Call<Channel_Model> call=apiRequest.getthinkData();
        call.enqueue(new Callback<Channel_Model>() {
            @Override
            public void onResponse(@NonNull Call<Channel_Model> call, @NonNull Response<Channel_Model> response) {
                progressBar.setVisibility(View.INVISIBLE);
                if (response.isSuccessful()) {
                   // Log.e("name", "><<<<<" + response.body().getChannel().name);

                    assert response.body() != null;
                    tvName.setText(response.body().getChannel().getName());
                    tv_lat.setText(response.body().getChannel().getLatitude());
                    tv_log.setText(response.body().getChannel().getLongitude());

                    list.addAll(response.body().getFeeds());
                    ChannelAdapter adapter = new ChannelAdapter(list, getApplicationContext());
                    recyclerView.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(@NonNull Call<Channel_Model> call, @NonNull Throwable t) {
                progressBar.setVisibility(View.INVISIBLE);
            }
        });
    }


    /*private void update_UI() {


    }*/
    // =========  using from volley =======//
   /* private void runbackground() {

        StringRequest stringRequest= new StringRequest(url, response -> {
            try {
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

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }, error -> {

        });
        RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);
    }*/


}