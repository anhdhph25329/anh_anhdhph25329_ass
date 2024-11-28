package fpt.anhdhph.anh_anhdhph25329_ass.screen;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import fpt.anhdhph.anh_anhdhph25329_ass.R;
import fpt.anhdhph.anh_anhdhph25329_ass.dao.AccDAO;

public class RecoverPass1 extends AppCompatActivity {

    EditText edtUsername, edtEmail;
    Button btnConfirm;

    AccDAO accountDAO;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_recover_pass1);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        anhXa();

        confirm();

    }

    void anhXa(){
        edtUsername = findViewById(R.id.edtUsername);
        edtEmail = findViewById(R.id.edtEmail);
        btnConfirm = findViewById(R.id.btnConfirm1);
        accountDAO = new AccDAO(this);
    }

    void confirm(){
        btnConfirm.setOnClickListener(view -> {
            String username = edtUsername.getText().toString();
            String email = edtEmail.getText().toString();

            if (username.isEmpty() || email.isEmpty()) {
                Toast.makeText(this, "Vui lòng nhập đầy đủ thông tin!", Toast.LENGTH_SHORT).show();
                return;
            }

            boolean isValid = accountDAO.checkUsernameAndEmail(username, email);
            if (isValid) {
                Intent intent = new Intent(RecoverPass1.this, RecoverPass2.class);
                intent.putExtra("username", username);
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(this, "Tên đăng nhập hoặc email không khớp!", Toast.LENGTH_SHORT).show();
            }
        });
    }

}