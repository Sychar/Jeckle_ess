package com.felhr.serialportexample.Controller;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.felhr.serialportexample.DatenBank.Kennline_text;
import com.felhr.serialportexample.IO.OnloadMoreListener;
import com.felhr.serialportexample.R;

import java.util.List;

public class KennlineAdapter_Gas extends RecyclerView.Adapter {
    private final int ViEW_REPLAY = 0;
    private final int VIEW_ITEM = 1;
    private int lastVisibleItem, totalItemCount;
    private boolean loading;
    private OnloadMoreListener onLoadMoreListener;
    static int first;
    static int last;
    static  int x;
    List<Kennline_text> kennline_texts;

    public static class KennlineHolder extends RecyclerView.ViewHolder {
        Button button;

        KennlineHolder(View view) {
            super(view);
            button = view.findViewById(R.id.listButton);
        }
    }

    public KennlineAdapter_Gas(List<Kennline_text> kennline_texts, RecyclerView recyclerView) {
        this.kennline_texts = kennline_texts;
        if (recyclerView.getLayoutManager() instanceof LinearLayoutManager) {

            final LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
            recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
                @Override
                public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                    super.onScrolled(recyclerView, dx, dy);

                    totalItemCount = linearLayoutManager.getItemCount();
                    first=linearLayoutManager.findFirstVisibleItemPosition();
                    lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition();
                    last=lastVisibleItem;
                    if (!loading && (totalItemCount <= totalItemCount)) {

                        if (onLoadMoreListener != null) {
                            onLoadMoreListener.onLoadMore();
                        }
                        loading = true;
                    }

                }
            });
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        RecyclerView.ViewHolder myViewholder;
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_for_kennlinie, viewGroup, false);
        myViewholder = new KennlineAdapter_Gas.KennlineHolder(v);

        return myViewholder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        if (viewHolder instanceof KennlineHolder) {
            ((KennlineHolder) viewHolder).button.setText(kennline_texts.get(i).getTitel());
            x =(last +first)/2;

            if(i==x){
                ((KennlineHolder) viewHolder).button.setTextColor(Color.GREEN);
            }else {
                ((KennlineHolder) viewHolder).button.setTextColor(Color.GRAY);
            }

        }
    }
    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @Override
    public int getItemCount() {
        return kennline_texts.size();
    }
    public void setLoaded() {
        loading = false;
    }

    @Override
    public int getItemViewType(int position) {
        return kennline_texts.get(position) != null ?VIEW_ITEM : ViEW_REPLAY;
    }

    public void setOnLoadMoreListener(OnloadMoreListener onLoadMoreListener){
        this.onLoadMoreListener=onLoadMoreListener;
    }


}

