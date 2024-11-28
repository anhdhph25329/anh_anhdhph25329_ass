package fpt.anhdhph.anh_anhdhph25329_ass.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import fpt.anhdhph.anh_anhdhph25329_ass.R;
import fpt.anhdhph.anh_anhdhph25329_ass.model.Job;

public class JobAdapter extends RecyclerView.Adapter<JobAdapter.JobViewHolder> {

    Context context;
    ArrayList<Job> list;
    public JobAdapter(Context context, ArrayList<Job> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public JobViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View view = inflater.inflate(R.layout.layout_job_item, parent, false);
        return new JobViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull JobViewHolder holder, int position) {
        Job job = list.get(position);
        holder.tvId.setText(String.valueOf(job.getId()));
        holder.tvName.setText(job.getName());
        holder.tvContent.setText(job.getContent());
        holder.tvStatus.setText(job.getStatus() == 0 ? "new" : job.getStatus() == 1 ? "working" : job.getStatus() == 2 ? "finish" : "remove");
        holder.tvStartDay.setText(job.getStartDay());
        holder.tvEndDay.setText(job.getEndDay());

        holder.btnEdit.setOnClickListener(view -> {
            //xu ly edit
        });

        holder.btnDel.setOnClickListener(view -> {
            //xu ly delete
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public static class JobViewHolder extends RecyclerView.ViewHolder {
        //ánh xạ các view trong layout item
        TextView tvId, tvName, tvContent, tvStatus, tvStartDay, tvEndDay;
        ImageView btnEdit, btnDel;
        public JobViewHolder(View itemView) {
            super(itemView);
            tvId = itemView.findViewById(R.id.tvId);
            tvName = itemView.findViewById(R.id.tvName);
            tvContent = itemView.findViewById(R.id.tvContent);
            tvStatus = itemView.findViewById(R.id.tvStatus);
            tvStartDay = itemView.findViewById(R.id.tvStartDay);
            tvEndDay = itemView.findViewById(R.id.tvEndDay);
            btnEdit = itemView.findViewById(R.id.btnEdit);
            btnDel = itemView.findViewById(R.id.btnDel);
        }
    }
}
