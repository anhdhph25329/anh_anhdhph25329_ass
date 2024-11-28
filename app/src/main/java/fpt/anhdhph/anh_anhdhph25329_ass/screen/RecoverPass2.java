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

public class RecoverPass2 extends AppCompatActivity {

    EditText edtNewpass, edtConfirmPass;
    Button btnConfirm;

    AccDAO accountDAO;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_recover_pass2);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        anhXa();

        confirm();

    }

    void anhXa(){
        edtNewpass = findViewById(R.id.edtNewpass);
        edtConfirmPass = findViewById(R.id.edtConfirmPass);
        btnConfirm = findViewById(R.id.btnConfirm2);
        accountDAO = new AccDAO(this);
    }

    void confirm(){
        String newPassword = edtNewpass.getText().toString();
        String confirmPassword = edtConfirmPass.getText().toString();
        String username = getIntent().getStringExtra("username");

        btnConfirm.setOnClickListener(view -> {
             // Lấy Username từ màn hình trước
            if (newPassword.isEmpty() || confirmPassword.isEmpty()) {
                Toast.makeText(this, "Vui lòng nhập đầy đủ thông tin!", Toast.LENGTH_SHORT).show();
                return;
            }

            if (!newPassword.equals(confirmPassword)) {
                Toast.makeText(this, "Mật khẩu xác nhận không khớp!", Toast.LENGTH_SHORT).show();
                return;
            }

            // Cập nhật mật khẩu mới
            boolean isValid = accountDAO.updatePassword(username, newPassword);
            if (isValid) {
                Toast.makeText(this, "Đặt lại mật khẩu thành công!", Toast.LENGTH_SHORT).show();
                finish();
            } else {
                Toast.makeText(this, "Đặt lại mật khẩu thất bại!", Toast.LENGTH_SHORT).show();
            }

        });
    }

}