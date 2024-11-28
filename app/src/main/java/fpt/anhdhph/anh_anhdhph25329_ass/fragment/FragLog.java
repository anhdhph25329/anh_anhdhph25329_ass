package fpt.anhdhph.anh_anhdhph25329_ass.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import fpt.anhdhph.anh_anhdhph25329_ass.R;
import fpt.anhdhph.anh_anhdhph25329_ass.dao.AccDAO;
import fpt.anhdhph.anh_anhdhph25329_ass.screen.JobManage;
import fpt.anhdhph.anh_anhdhph25329_ass.screen.RecoverPass1;

public class FragLog extends Fragment {
    EditText edtUsermail, edtPassword;
    CheckBox cbRemember;
    Button btnLogin;
    TextView tvForgotPass;

    AccDAO accDAO;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.layout_frag_log, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        edtUsermail = view.findViewById(R.id.edtUsermail);
        edtPassword = view.findViewById(R.id.edtPasswordL);
        btnLogin = view.findViewById(R.id.btnLogin);
        tvForgotPass = view.findViewById(R.id.tvForgotPass);
        accDAO = new AccDAO(getContext());

        btnLogin.setOnClickListener(view1 -> {
            String usermail = edtUsermail.getText().toString().trim();
            String password = edtPassword.getText().toString().trim();
            
            if (usermail.isEmpty() || password.isEmpty()) {
                Toast.makeText(getContext(), "Vui lòng nhập đầy đủ thông tin đăng nhập!", Toast.LENGTH_SHORT).show();
                return;
            }

            boolean isSuccess = accDAO.login(usermail, password);
            if (isSuccess) {
                Toast.makeText(getContext(), "Đăng nhập thành công!", Toast.LENGTH_SHORT).show();
                // Chuyển đến màn hình chính hoặc màn hình khác
                Intent intent = new Intent(getContext(), JobManage.class);
                startActivity(intent);
                getActivity().finish();
            } else {
                Toast.makeText(getContext(), "Tên đăng nhập, email hoặc mật khẩu không đúng", Toast.LENGTH_SHORT).show();
            }

        });

        btnLogin.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                Intent intent = new Intent(getContext(), JobManage.class);
                startActivity(intent);
                getActivity().finish();
                return false;
            }
        });

        tvForgotPass.setOnClickListener(view1 -> {
            //xu ly forgot pass
            Intent intent = new Intent(getContext(), RecoverPass1.class);
            startActivity(intent);
        });

    }

}
