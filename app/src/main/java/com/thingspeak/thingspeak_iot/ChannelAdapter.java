package com.thingspeak.thingspeak_iot;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ChannelAdapter extends RecyclerView.Adapter<ChannelAdapter.MyViewHolder> {
    List<Feeds> list;
    Context mContext;
    /*public ChannelAdapter(List<Feeds> list, Context applicationContext) {
        this.list=list;
        this.mContext=applicationContext;
    }
*/
    public ChannelAdapter(List<Feeds> list, Context applicationContext) {
        this.list=list;
        this.mContext=applicationContext;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
          holder.tv1.setText(list.get(position).getCreated_at());
        holder.tv2.setText(list.get(position).getEntry_id());
        holder.tv3.setText(list.get(position).getStatus());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    protected static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView tv1,tv2,tv3;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tv1=itemView.findViewById(R.id.textView_created_at);
            tv2=itemView.findViewById(R.id.textView_entry_id);
            tv3=itemView.findViewById(R.id.textView_status);
        }
    }
}
