package com.kenanozdamar.android.demo.rigelhub.search_results.recycle_adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kenanozdamar.android.demo.rigelhub.R;

import java.util.Arrays;
import java.util.List;

public class ResultsRecycleAdapter
        extends RecyclerView.Adapter<ResultsRecycleAdapter.ResultViewHolder> {


    String[] categories = {"Cat 1", "Cat 2", "Cat 3", "Cat 4" ,"Cat 5" ,"Cat 6","Cat 7",
            "Dog 1","Dog 2","Dog 3","Dog 4","Dog 5",
            "Deer 1","Deer 2","Deer 3","Deer 4",
            " Parrot 1"," Parrot 2"," Parrot 3"," Parrot 4"," Parrot 5"," Parrot 6"," Parrot 7"," Parrot 8"};

    private List<String> data = Arrays.asList(categories);
    private Context context;

    public ResultsRecycleAdapter(@NonNull Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ResultViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view  = LayoutInflater.from(context).inflate(R.layout.list_item, viewGroup, false);
        return new ResultViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ResultViewHolder viewHolder, int position) {
        viewHolder.setData(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class ResultViewHolder extends RecyclerView.ViewHolder {

        TextView textView;

        public ResultViewHolder(@NonNull View itemView) {
            super(itemView);

            textView = itemView.findViewById(R.id.list_item_tv);
        }

        public void setData(String data) {
            if (textView != null) textView.setText(data);
        }
    }
}
