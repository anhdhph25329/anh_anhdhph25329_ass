package fpt.anhdhph.anh_anhdhph25329_ass.screen;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import fpt.anhdhph.anh_anhdhph25329_ass.R;

public class AddJobScreen extends AppCompatActivity {

    EditText edtJobname, edtContent, edtStartday, edtEndday;
    Spinner spnStatus;
    Button btnAdd;

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

    }

    public void anhXa(){
        edtJobname = findViewById(R.id.edtJobname);
        edtContent = findViewById(R.id.edtContent);
        spnStatus = findViewById(R.id.spStatus);
        edtStartday = findViewById(R.id.edtStartday);
        edtEndday = findViewById(R.id.edtEndday);
    }

}