package com.kenanozdamar.android.demo.rigelhub.search_results.recycle_adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kenanozdamar.android.demo.rigelhub.R;
import com.kenanozdamar.android.demo.rigelhub.search_results.fragments.FragmentCallbacks;
import com.kenanozdamar.android.demo.rigelhub.search_results.models.SearchResult;

import java.util.ArrayList;
import java.util.List;

public class ResultsRecycleAdapter
        extends RecyclerView.Adapter<ResultsRecycleAdapter.ResultViewHolder> {

    // region TAG.
    @SuppressWarnings("unused")
    private static final String TAG = ResultsRecycleAdapter.class.getSimpleName();
    // endregion

//    String[] categories = {"Cat 1", "Cat 2", "Cat 3", "Cat 4" ,"Cat 5" ,"Cat 6","Cat 7",
//            "Dog 1","Dog 2","Dog 3","Dog 4","Dog 5",
//            "Deer 1","Deer 2","Deer 3","Deer 4",
//            " Parrot 1"," Parrot 2"," Parrot 3"," Parrot 4"," Parrot 5"," Parrot 6"," Parrot 7"," Parrot 8"};

    private List<SearchResult> data = new ArrayList<>();
    private Context context;
    private FragmentCallbacks callbacks;

    public ResultsRecycleAdapter(@NonNull Context context) {
        this.context = context;
    }

    public void register(FragmentCallbacks callbacks) {
        this.callbacks = callbacks;
    }

    public void setData(List<SearchResult> items) {
        data.clear();
        data.addAll(items);
    }

    public List<SearchResult> getData() {
        return data;
    }

    public SearchResult getDataItem(int position) {
        return data.get(position);
    }

    @NonNull
    @Override
    public ResultViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view  = LayoutInflater.from(context).inflate(R.layout.list_item, viewGroup, false);
        return new ResultViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ResultViewHolder viewHolder, int position) {
        viewHolder.title.setText(data.get(position).getRepositoryName());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class ResultViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView title;

        public ResultViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.title_tv);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Log.d(TAG,"Item clicked" + data.get(getAdapterPosition()).getWebUrl());
            callbacks.onListItemSelected(getAdapterPosition());

        }
    }
}
