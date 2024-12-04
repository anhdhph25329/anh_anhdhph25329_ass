package fpt.anhdhph.anh_anhdhph25329_ass.adapter;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Notification;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Date;

import fpt.anhdhph.anh_anhdhph25329_ass.R;
import fpt.anhdhph.anh_anhdhph25329_ass.config.AddNotifyConfig;
import fpt.anhdhph.anh_anhdhph25329_ass.dao.JobDAO;
import fpt.anhdhph.anh_anhdhph25329_ass.model.Job;
import fpt.anhdhph.anh_anhdhph25329_ass.screen.EditJobScreen;
import fpt.anhdhph.anh_anhdhph25329_ass.screen.LogReg;

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


                            Intent intentChitiet = new Intent(context, LogReg.class);
                            intentChitiet.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);

                            // Tạo stack để chứa các activity khi gọi notify (chitietActvity)
                            TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);
                            stackBuilder.addNextIntentWithParentStack( intentChitiet );

                            // su dung pendingIntent de gui notify
                            PendingIntent pendingIntent = stackBuilder.getPendingIntent(
                                    0,
                                    PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE);
                            // taoj layout cho notify
                            Notification notify = new NotificationCompat.Builder(context, AddNotifyConfig.CHANEL_ID )
                                    .setSmallIcon( android.R.drawable.ic_menu_view ) // bieeur tuowng nho
                                    .setContentTitle("Job Removed!")
                                    .setContentText(holder.tvName.getText().toString() + " has been removed!")
                                    .setContentIntent( pendingIntent) // goi activity chitiet
                                    .build();
                            NotificationManagerCompat notificationManagerCompat =
                                    NotificationManagerCompat.from(context);

                            // kiem tra quyen gui thong bao
                            if(ActivityCompat.checkSelfPermission(context, Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED){
                                // chua duoc cap quyen
                                // yeu cau cap quyen
                                ActivityCompat.requestPermissions((Activity) context,
                                        new String[]{Manifest.permission.POST_NOTIFICATIONS},
                                        999);
                                return;
                            }else{
                                // da duoc cap quyen
                                int id_notify = (int) new Date().getTime();// tao ra chuoi so tranh trung lap
                                // hien thi notify
                                notificationManagerCompat.notify(id_notify, notify);
                            }

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
