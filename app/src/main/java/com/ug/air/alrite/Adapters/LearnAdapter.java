package com.ug.air.alrite.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ug.air.alrite.Models.Learn;
import com.ug.air.alrite.R;

import java.util.List;

public class LearnAdapter extends RecyclerView.Adapter<LearnAdapter.LearnViewHolder> {

    private List<Learn> learnList;
    private OnItemClickListener mListener;

    public LearnAdapter(List<Learn> learnList) {
        this.learnList = learnList;
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        mListener = listener;
    }

    public interface OnItemClickListener {
        void onClick(int position);
    }

    @NonNull
    @Override
    public LearnViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new LearnViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.learn_layout, parent, false), mListener);
    }

    @Override
    public void onBindViewHolder(@NonNull LearnViewHolder holder, int position) {
        Learn learn = learnList.get(position);
        holder.title.setText(learn.getTitle());
        holder.definition.setText(learn.getDefinition());
    }

    @Override
    public int getItemCount() {
        return learnList.size();
    }

    public class LearnViewHolder extends RecyclerView.ViewHolder {

        TextView title, definition;

        public LearnViewHolder(@NonNull View itemView, OnItemClickListener listener ) {
            super(itemView);

            title = itemView.findViewById(R.id.title);
            definition = itemView.findViewById(R.id.definition);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION){
                        listener.onClick(position);
                    }
                }
            });
        }
    }
}
