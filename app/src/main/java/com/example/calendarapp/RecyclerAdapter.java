package com.example.calendarapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    private final ArrayList<String> notes;
    private final ArrayList<String> titles;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView textView1;
        private final TextView textView2;

        public ViewHolder(View view) {
            super(view);
            textView1 = (TextView) view.findViewById(R.id.textView1);
            textView2 = (TextView) view.findViewById(R.id.textView2);
        }

        public TextView getTitleView() {
            return textView1;
        }

        public TextView getContentView() {
            return textView2;
        }
    }

    public RecyclerAdapter(ArrayList<String> titles, ArrayList<String> notes) {
        this.titles = titles;
        this.notes = notes;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.recycler_view_item, viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        viewHolder.getTitleView().setText(titles.get(position));
        viewHolder.getContentView().setText(notes.get(position));
    }

    @Override
    public int getItemCount() {
        return titles.size();
    }
}
