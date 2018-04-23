package com.aungzinphyo.borrowsample.ListItems;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.aungzinphyo.borrowsample.Db.BorrowModel;
import com.aungzinphyo.borrowsample.R;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.RecyclerViewHolder>{

    private List<BorrowModel> borrowModelList;
    private View.OnLongClickListener longClickListener;

    static class RecyclerViewHolder extends RecyclerView.ViewHolder{

        public TextView itemTextView;
        public TextView nameTextView;
        public TextView dateTextView;

        public RecyclerViewHolder(View itemView) {
            super(itemView);
            itemTextView = itemView.findViewById(R.id.itemTextView);
            nameTextView = itemView.findViewById(R.id.nameTextView);
            dateTextView = itemView.findViewById(R.id.dateTextView);
        }
    }

    public RecyclerViewAdapter(List<BorrowModel> borrowModelList, View.OnLongClickListener longClickListener){
        this.borrowModelList = borrowModelList;
        this.longClickListener = longClickListener;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new RecyclerViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item,parent,false));
    }

    @Override
    public void onBindViewHolder(final RecyclerViewHolder holder, int position) {
        BorrowModel borrowModel = borrowModelList.get(position);
        holder.itemTextView.setText(borrowModel.getItemName());
        holder.nameTextView.setText(borrowModel.getPersonName());
        holder.dateTextView.setText(borrowModel.getBorrowDate().toLocaleString().substring(0,11));
        holder.itemView.setTag(borrowModel);
        holder.itemView.setOnLongClickListener(longClickListener);
    }

    @Override
    public int getItemCount() {
        return borrowModelList.size();
    }

    public void addItems(List<BorrowModel> borrowModelList){
        this.borrowModelList = borrowModelList;
        notifyDataSetChanged();
    }


}
