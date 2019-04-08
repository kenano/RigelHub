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
        SearchResult result = data.get(position);
        if (result ==  null) return;
        viewHolder.title.setText(result.getRepositoryName());
        viewHolder.starCount.setText(String.valueOf(result.getStarCount()));
        viewHolder.description.setText(result.getDescription());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class ResultViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView title;
        TextView description;
        TextView starCount;

        public ResultViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.title_tv);
            description = itemView.findViewById(R.id.repo_description_tv);
            starCount = itemView.findViewById(R.id.star_count_tv);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Log.d(TAG,"Item clicked" + data.get(getAdapterPosition()).getWebUrl());
            callbacks.onListItemSelected(getAdapterPosition());

        }
    }
}
