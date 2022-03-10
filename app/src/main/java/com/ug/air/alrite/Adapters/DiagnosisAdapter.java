package com.ug.air.alrite.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ug.air.alrite.Models.Diagnosis;
import com.ug.air.alrite.R;

import java.util.List;

public class DiagnosisAdapter extends RecyclerView.Adapter<DiagnosisAdapter.DiagnosisViewHolder> {

    private List<Diagnosis> diagnosisList;
    private RecyclerView.RecycledViewPool viewPool = new RecyclerView.RecycledViewPool();

    public DiagnosisAdapter(List<Diagnosis> diagnosisList) {
        this.diagnosisList = diagnosisList;
    }

    @NonNull
    @Override
    public DiagnosisViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.diagnosis, parent, false);
        DiagnosisViewHolder diagnosisViewHolder = new DiagnosisViewHolder(view);
        return diagnosisViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull DiagnosisViewHolder holder, int position) {
        Diagnosis diagnosis = diagnosisList.get(position);
        holder.txtDiagnosis.setText(diagnosis.getDiagnosis());

        LinearLayoutManager layoutManager = new LinearLayoutManager(
                holder.recyclerView2.getContext(),
                LinearLayoutManager.VERTICAL,
                false);

        layoutManager.setInitialPrefetchItemCount(diagnosis.getAssessmentList().size());

        AssessmentAdapter assessmentAdapter = new AssessmentAdapter(diagnosis.getAssessmentList());

        holder.recyclerView2.setLayoutManager(layoutManager);
        holder.recyclerView2.setAdapter(assessmentAdapter);
        holder.recyclerView2.setRecycledViewPool(viewPool);
    }

    @Override
    public int getItemCount() {
        return diagnosisList.size();
    }

    public class DiagnosisViewHolder extends RecyclerView.ViewHolder {

        private TextView txtDiagnosis;
        private RecyclerView recyclerView2;
        private ImageView imageView;

        public DiagnosisViewHolder(@NonNull View itemView) {
            super(itemView);

            txtDiagnosis = itemView.findViewById(R.id.title);
            recyclerView2 = itemView.findViewById(R.id.recyclerView);
            imageView = itemView.findViewById(R.id.accordion);
        }
    }
}
