package com.ug.air.alrite.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
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

    @SuppressLint("UseCompatLoadingForDrawables")
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == 0){
            Patient patient = (Patient) items.get(position).getObject();
            PatientHolder holder1 = (PatientHolder) holder;
            holder1.initials.setText(patient.getInitial());
            holder1.initial2.setText(patient.getParent());
            holder1.age.setText(patient.getAge());
            holder1.sex.setText(patient.getSex());
            holder1.date.setText(patient.getDate());

            boolean reassess = patient.isReassess();
            if (reassess){
                holder1.imageView.setVisibility(View.VISIBLE);
            }else{
                holder1.imageView.setVisibility(View.GONE);
            }
        }else {
            History history = (History) items.get(position).getObject();
            HistoryHolder holder1 = (HistoryHolder) holder;
            holder1.initials.setText(history.getInitial());
            holder1.initial2.setText(history.getParent());
            holder1.age.setText(history.getAge());
            holder1.sex.setText(history.getSex());
            holder1.date.setText(history.getDate());

            String pending = history.getPending();
            if (pending.equals("pending")){
                holder1.imageView.setVisibility(View.VISIBLE);
                holder1.imageView.setImageDrawable(context.getResources().getDrawable(R.drawable.pending));
            }

            String incomplete = history.getIncomplete();
            if (incomplete.equals("incomplete")){
                holder1.imageView.setVisibility(View.VISIBLE);
                holder1.imageView.setImageDrawable(context.getResources().getDrawable(R.drawable.incomplete));
            }
        }


    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class PatientHolder extends RecyclerView.ViewHolder {

        TextView initials, initial2, age, sex, date;
        ImageView imageView;

        public PatientHolder(@NonNull View itemView) {
            super(itemView);

            initials = itemView.findViewById(R.id.initials);
            initial2 = itemView.findViewById(R.id.patient_parent);
            age = itemView.findViewById(R.id.patient_age);
            sex = itemView.findViewById(R.id.patient_sex);
            date = itemView.findViewById(R.id.date);
            imageView = itemView.findViewById(R.id.ready);

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

        TextView initials, initial2, age, sex, date;
        ImageView imageView;

        public HistoryHolder(@NonNull View itemView) {
            super(itemView);

            initials = itemView.findViewById(R.id.initials);
            initial2 = itemView.findViewById(R.id.patient_parent);
            age = itemView.findViewById(R.id.patient_age);
            sex = itemView.findViewById(R.id.patient_sex);
            date = itemView.findViewById(R.id.date);
            imageView = itemView.findViewById(R.id.ready);

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
}
