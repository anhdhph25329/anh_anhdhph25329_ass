package fpt.anhdhph.anh_anhdhph25329_ass.screen;

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
        String dayPattern = "^(0[1-9]|[12][0-9]|3[01])/(0[1-9]|1[0-2])/\\\\d{4}$";

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


//            if (!startDay.matches(dayPattern) || !endDay.matches(dayPattern)) {
//                Toast.makeText(this, "Ngày phải được nhập theo định dạng dd/mm/yyyy", Toast.LENGTH_SHORT).show();
//                return;
//            }


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
                finish();
            }else {
                Toast.makeText(this, "Thêm thất bại", Toast.LENGTH_SHORT).show();
            }

        });
    }

}