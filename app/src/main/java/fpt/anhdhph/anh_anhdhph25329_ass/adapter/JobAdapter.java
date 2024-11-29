package fpt.anhdhph.anh_anhdhph25329_ass.adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import fpt.anhdhph.anh_anhdhph25329_ass.R;
import fpt.anhdhph.anh_anhdhph25329_ass.dao.JobDAO;
import fpt.anhdhph.anh_anhdhph25329_ass.model.Job;
import fpt.anhdhph.anh_anhdhph25329_ass.screen.EditJobScreen;

public class JobAdapter extends RecyclerView.Adapter<JobAdapter.JobViewHolder> {

    Context context;
    ArrayList<Job> list;
    JobDAO jobDAO;
    public JobAdapter(Context context, ArrayList<Job> list) {
        this.context = context;
        this.list = list;
        jobDAO = new JobDAO(context);
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
        holder.tvStatus.setText(getStatusText(job.getStatus()));
        holder.tvStartDay.setText(job.getStartDay());
        holder.tvEndDay.setText(job.getEndDay());

        holder.btnEdit.setOnClickListener(view -> {
            Intent intent = new Intent(context, EditJobScreen.class);

            intent.putExtra("id", job.getId());
            intent.putExtra("name", job.getName());
            intent.putExtra("content", job.getContent());
            intent.putExtra("status", job.getStatus());
            intent.putExtra("startDay", job.getStartDay());
            intent.putExtra("endDay", job.getEndDay());

            context.startActivity(intent);
        });

        holder.btnDel.setOnClickListener(view -> {
            new AlertDialog.Builder(context)
                    .setTitle("Delete Job")
                    .setMessage("Are you sure you want to delete this job?")
                    .setPositiveButton("Yes", (dialog, which) -> {
                        if (jobDAO.deleteJob(job.getId())) {
                            list.remove(position);
                            notifyItemRemoved(position);
                            notifyItemRangeChanged(position, list.size());
                        } else {
                            Toast.makeText(context, "Failed to delete job!", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .setNegativeButton("No", (dialog, which) -> dialog.dismiss())
                    .show();
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    private String getStatusText(int status) {
        switch (status) {
            case 0: return "0";
            case 1: return "1";
            case 2: return "2";
            case 3: return "-1";
            default: return "Không xác định";
        }
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
