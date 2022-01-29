package com.ug.air.alrite.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ug.air.alrite.Models.History;
import com.ug.air.alrite.Models.Item;
import com.ug.air.alrite.Models.Patient;
import com.ug.air.alrite.R;

import java.util.List;

public class PatientAdapter extends RecyclerView.Adapter {

    Context context;
    List<Item> items;
    private OnItemClickListener listener;

    public PatientAdapter(Context context, List<Item> items) {
        this.context = context;
        this.items = items;
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    @Override
    public int getItemViewType(int position) {
        return items.get(position).getType();
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        this.listener = listener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(viewType == 0){
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.patient, parent, false);
            PatientHolder patientHolder = new PatientHolder(view);
            return patientHolder;
        }else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.patient_history, parent, false);
            HistoryHolder historyHolder = new HistoryHolder(view);
            return historyHolder;
        }

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == 0){
            Patient patient = (Patient) items.get(position).getObject();
            PatientHolder holder1 = (PatientHolder) holder;
            holder1.name.setText(patient.getName());
            holder1.number.setText(patient.getNumber());
            holder1.initials.setText(patient.getInitial());
        }else {
            History history = (History) items.get(position).getObject();
            HistoryHolder holder1 = (HistoryHolder) holder;
            holder1.title.setText(history.getDiagnosis());
            holder1.subtitle.setText(history.getDate());
        }


    }

    @Override
    public int getItemCount() {
        return items.size();
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
                        listener.onItemClick(position);
                    }
                }
            });
        }
    }

    public class HistoryHolder extends RecyclerView.ViewHolder {

        TextView title, subtitle;

        public HistoryHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.diagnosis);
            subtitle = itemView.findViewById(R.id.date);
        }
    }
}
