package com.ug.air.alrite.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ug.air.alrite.Models.Summary;
import com.ug.air.alrite.R;

import java.util.List;

public class SummaryAdapter extends RecyclerView.Adapter<SummaryAdapter.SummaryViewHolder> {

    List<Summary> summaryList;

    public SummaryAdapter(List<Summary> summaryList) {
        this.summaryList = summaryList;
    }

    @NonNull
    @Override
    public SummaryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.summary, parent, false);
        SummaryViewHolder summaryViewHolder = new SummaryViewHolder(view);
        return summaryViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull SummaryViewHolder holder, int position) {
        Summary summary = summaryList.get(position);
        holder.txtTitle.setText(summary.getTitle());
        holder.txtValue.setText(summary.getValue());
    }

    @Override
    public int getItemCount() {
        return summaryList.size();
    }

    public class SummaryViewHolder extends RecyclerView.ViewHolder {

        TextView txtTitle, txtValue;

        public SummaryViewHolder(@NonNull View itemView) {
            super(itemView);

            txtTitle = itemView.findViewById(R.id.title);
            txtValue = itemView.findViewById(R.id.value);
        }
    }
}
