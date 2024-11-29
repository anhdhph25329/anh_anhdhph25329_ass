package fpt.anhdhph.anh_anhdhph25329_ass.screen;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import fpt.anhdhph.anh_anhdhph25329_ass.R;
import fpt.anhdhph.anh_anhdhph25329_ass.dao.JobDAO;
import fpt.anhdhph.anh_anhdhph25329_ass.model.Job;

public class EditJobScreen extends AppCompatActivity {

    EditText edtJobnameE, edtContentE, edtStartdayE, edtEnddayE;
    Spinner spStatusE;
    Button btnSaveE;
    JobDAO jobDAO;
    int jobId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_edit_job_screen);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        anhXa();

        // Nhận dữ liệu từ Intent
        Intent intent = getIntent();
        jobId = intent.getIntExtra("id", -1);
        edtJobnameE.setText(intent.getStringExtra("name"));
        edtContentE.setText(intent.getStringExtra("content"));
        edtStartdayE.setText(intent.getStringExtra("startDay"));
        edtEnddayE.setText(intent.getStringExtra("endDay"));

        int status = intent.getIntExtra("status", 0);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this, R.array.status_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spStatusE.setAdapter(adapter);
        spStatusE.setSelection(status);

        editJob();

    }

    void anhXa(){
        edtJobnameE = findViewById(R.id.edtJobnameE);
        edtContentE = findViewById(R.id.edtContentE);
        edtStartdayE = findViewById(R.id.edtStartdayE);
        edtEnddayE = findViewById(R.id.edtEnddayE);
        spStatusE = findViewById(R.id.spStatusE);
        btnSaveE = findViewById(R.id.btnSaveE);
        jobDAO = new JobDAO(this);
    }

    void editJob(){
        btnSaveE.setOnClickListener(view -> {
            String name = edtJobnameE.getText().toString();
            String content = edtContentE.getText().toString();
            int status = spStatusE.getSelectedItemPosition();
            String startDay = edtStartdayE.getText().toString();
            String endDay = edtEnddayE.getText().toString();

            if (name.isEmpty() || content.isEmpty() || startDay.isEmpty() || endDay.isEmpty()) {
                Toast.makeText(this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                return;
            }

            Job job = new Job();
            job.setId(jobId);
            job.setName(name);
            job.setContent(content);
            job.setStatus(status);
            job.setStartDay(startDay);
            job.setEndDay(endDay);

            boolean isUpdated = jobDAO.updateJob(job);

            if (isUpdated) {
                Toast.makeText(EditJobScreen.this, "Job updated successfully", Toast.LENGTH_SHORT).show();
                finish();  // Quay về màn hình trước đó
            } else {
                Toast.makeText(EditJobScreen.this, "Failed to update job", Toast.LENGTH_SHORT).show();
            }
        });
    }

}