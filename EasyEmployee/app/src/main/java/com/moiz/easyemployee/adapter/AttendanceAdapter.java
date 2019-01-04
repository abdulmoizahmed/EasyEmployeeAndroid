package com.moiz.easyemployee.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.moiz.easyemployee.R;
import com.moiz.easyemployee.model.SearchResult;

import java.util.List;

/**
 * Created by Moiz-IHS on 7/28/2018.
 */

public class AttendanceAdapter extends RecyclerView.Adapter<AttendanceAdapter.ViewHolder> {

    List<SearchResult> results;
    OnItemClickListener listener;

    public AttendanceAdapter(List<SearchResult> searchResult,OnItemClickListener listener) {
        results = searchResult;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.calendar_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.day.setText(results.get(position).getDay().substring(0, 3));
        holder.date.setText(results.get(position).getDate().substring(results.get(position).getDate().length() - 2));

        holder.layout.setOnClickListener(new CustomOnClickListener(position));
    }

    @Override
    public int getItemCount() {
        return results.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public LinearLayout layout;
        public TextView day;
        public TextView date;


        public ViewHolder(View itemView) {
            super(itemView);
            day = (TextView) itemView.findViewById(R.id.day);
            date = (TextView) itemView.findViewById(R.id.date);

            layout = (LinearLayout) itemView.findViewById(R.id.layout);
        }
    }

    private class CustomOnClickListener implements View.OnClickListener {
        int position;

        public CustomOnClickListener(int position) {
            this.position = position;
        }

        @Override
        public void onClick(View view) {
            listener.onItemClicked(position);
        }
    }
}
