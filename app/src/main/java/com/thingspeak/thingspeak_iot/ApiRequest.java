package com.thingspeak.thingspeak_iot;

import com.google.gson.JsonObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiRequest {

 // https://api.thingspeak.com/channels/1984207/fields/1.json?api_key=DAZGXH8X5X7HJUTX&results=2
 // @GET("/channels/1984207/fields/1.json?api_key=DAZGXH8X5X7HJUTX&results=2")
  //https://api.thingspeak.com/channels/1984207/status.json?api_key=DAZGXH8X5X7HJUTX
  @GET("/channels/1984207/status.json?api_key=DAZGXH8X5X7HJUTX")
  //@GET("/channels/1984207/status.json")
  //@FormUrlEncoded
   // Call<JsonObject> getthinkData();
  Call<Channel_Model> getthinkData();
}
//https://api.thingspeak.com/channels/1984207/feeds.json?api_key=DAZGXH8X5X7HJUTX&results=2