package com.ug.air.alrite.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ug.air.alrite.Models.Patient;
import com.ug.air.alrite.R;

import java.util.List;

public class PatientAdapter extends RecyclerView.Adapter<PatientAdapter.PatientHolder> {

    Context context;
    List<Patient> patients;
    private OnItemClickListener listener;

    public PatientAdapter(Context context, List<Patient> patients) {
        this.context = context;
        this.patients = patients;
    }

    public interface OnItemClickListener {
        void onItemClick(Patient patient);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        this.listener = listener;
    }


    @NonNull
    @Override
    public PatientHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.patient, parent, false);
        PatientHolder patientHolder = new PatientHolder(view);
        return patientHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull PatientHolder holder, int position) {
        Patient patient = patients.get(position);
        holder.name.setText(patient.getName());
        holder.number.setText(patient.getNumber());
        holder.initials.setText(patient.getInitial());

    }

    @Override
    public int getItemCount() {
        return patients.size();
    }

    public class PatientHolder extends RecyclerView.ViewHolder {

        TextView name, number, initials;

        public PatientHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.patient_name);
            number = itemView.findViewById(R.id.patient_no);
            initials = itemView.findViewById(R.id.initials);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION){
                        listener.onItemClick(patients.get(position));
                    }
                }
            });
        }
    }
}
