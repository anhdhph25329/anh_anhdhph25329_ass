package fpt.anhdhph.anh_anhdhph25329_ass.screen;

import android.Manifest;
import android.app.Notification;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Date;

import fpt.anhdhph.anh_anhdhph25329_ass.MainActivity;
import fpt.anhdhph.anh_anhdhph25329_ass.R;
import fpt.anhdhph.anh_anhdhph25329_ass.config.AddNotifyConfig;
import fpt.anhdhph.anh_anhdhph25329_ass.dao.JobDAO;
import fpt.anhdhph.anh_anhdhph25329_ass.model.Job;

public class AddJobScreen extends AppCompatActivity {

    EditText edtJobname, edtContent, edtStartday, edtEndday;
    Spinner spnStatus;
    Button btnSave;
    JobDAO jobDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_job_screen);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        anhXa();

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this, R.array.status_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnStatus.setAdapter(adapter);

        addJob();

    }

    public void anhXa(){
        edtJobname = findViewById(R.id.edtJobname);
        edtContent = findViewById(R.id.edtContent);
        spnStatus = findViewById(R.id.spStatus);
        edtStartday = findViewById(R.id.edtStartday);
        edtEndday = findViewById(R.id.edtEndday);
        btnSave = findViewById(R.id.btnSave);
        jobDAO = new JobDAO(this);
    }

    public void addJob(){
        String dayPattern = "^[0-9]{2}/[0-9]{2}$";

        btnSave.setOnClickListener(v -> {
            String name = edtJobname.getText().toString();
            String content = edtContent.getText().toString();
            String startDay = edtStartday.getText().toString();
            String endDay = edtEndday.getText().toString();
            int status = spnStatus.getSelectedItemPosition();

            if (name.isEmpty() || content.isEmpty() || startDay.isEmpty() || endDay.isEmpty()) {
                Toast.makeText(this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                return;
            }


            if (!startDay.matches(dayPattern) || !endDay.matches(dayPattern)) {
                Toast.makeText(this, "Ngày phải được nhập theo định dạng dd/mm", Toast.LENGTH_SHORT).show();
                return;
            }


            Job newJob = new Job();
            newJob.setName(name);
            newJob.setContent(content);
            newJob.setStatus(status);
            newJob.setStartDay(startDay);
            newJob.setEndDay(endDay);

            boolean isAdded = jobDAO.addJob(newJob);
            if (isAdded) {
                Toast.makeText(this, "Thêm thành công", Toast.LENGTH_SHORT).show();
                setResult(RESULT_OK);
                GuiThongBao();
                finish();
            }else {
                Toast.makeText(this, "Thêm thất bại", Toast.LENGTH_SHORT).show();
            }

        });
    }

    void GuiThongBao (){
        // Khai bao Intent chay activity Chitiet khi nguoi dung bam vao notify
        // su dung getApplicationContext
        Intent intentChitiet = new Intent(getApplicationContext(), LogReg.class);
        intentChitiet.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);

        // Tạo stack để chứa các activity khi gọi notify (chitietActvity)
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        stackBuilder.addNextIntentWithParentStack( intentChitiet );

        // su dung pendingIntent de gui notify
        PendingIntent pendingIntent = stackBuilder.getPendingIntent(
                0,
                PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE);
        // taoj layout cho notify
        Notification notify = new NotificationCompat.Builder(this, AddNotifyConfig.CHANEL_ID )
                .setSmallIcon( android.R.drawable.ic_menu_view ) // bieeur tuowng nho
                .setContentTitle("New Job Added!")
                .setContentText(edtJobname.getText().toString() + " has been added to the list")
                .setContentIntent( pendingIntent) // goi activity chitiet
                .build();
        NotificationManagerCompat notificationManagerCompat =
                NotificationManagerCompat.from(this);

        // kiem tra quyen gui thong bao
        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED){
            // chua duoc cap quyen
            // yeu cau cap quyen
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.POST_NOTIFICATIONS},
                    999);
            return;
        }else{
            // da duoc cap quyen
            int id_notify = (int) new Date().getTime();// tao ra chuoi so tranh trung lap
            // hien thi notify
            notificationManagerCompat.notify(id_notify, notify);
        }

    }


}