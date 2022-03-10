package com.ug.air.alrite.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ug.air.alrite.Models.Assessment;
import com.ug.air.alrite.R;

import java.util.List;

public class AssessmentAdapter extends RecyclerView.Adapter<AssessmentAdapter.AssessmentViewHolder> {

    List<Assessment> assessmentList;

    public AssessmentAdapter(List<Assessment> assessmentList) {
        this.assessmentList = assessmentList;
    }

    @NonNull
    @Override
    public AssessmentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.instructions_layout, parent, false);
        AssessmentViewHolder assessmentViewHolder = new AssessmentViewHolder(view);
        return assessmentViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull AssessmentViewHolder holder, int position) {
        Assessment assessment = assessmentList.get(position);
        holder.txtInstructions.setText(assessment.getInstruction());
    }

    @Override
    public int getItemCount() {
        return assessmentList.size();
    }

    public static class AssessmentViewHolder extends RecyclerView.ViewHolder{

        TextView txtInstructions;

        public AssessmentViewHolder(@NonNull View itemView) {
            super(itemView);

            txtInstructions = itemView.findViewById(R.id.instruction);
        }
    }
}
