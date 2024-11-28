package fpt.anhdhph.anh_anhdhph25329_ass.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import fpt.anhdhph.anh_anhdhph25329_ass.R;
import fpt.anhdhph.anh_anhdhph25329_ass.dao.AccDAO;
import fpt.anhdhph.anh_anhdhph25329_ass.database.DbHelper;
import fpt.anhdhph.anh_anhdhph25329_ass.model.Acc;

public class FragReg extends Fragment {

    EditText edtFullname, edtUsername, edtEmail, edtPassword;
    Button btnSignup;

    AccDAO accDAO;
    DbHelper dbHelper;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.layout_frag_reg, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        edtFullname = view.findViewById(R.id.edtFullname);
        edtEmail = view.findViewById(R.id.edtEmailS);
        edtUsername = view.findViewById(R.id.edtUsernameS);
        edtPassword = view.findViewById(R.id.edtPasswordS);
        btnSignup = view.findViewById(R.id.btnSignup);
        dbHelper = new DbHelper(getContext());
        accDAO = new AccDAO(getContext());

        //regex
        String fullnamePattern = "^[a-zA-ZÀ-ỹ\\s]+$";
        String usernamePattern = "^[a-zA-Z0-9_]{4,20}$";
        String emailPattern = "^[a-zA-Z0-9._%+-]+@gmail\\.com$";
        String passPattern = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,20}$";

        String fullname = edtFullname.getText().toString();
        String username = edtUsername.getText().toString().trim();
        String email = edtEmail.getText().toString().trim();
        String password = edtPassword.getText().toString();

        btnSignup.setOnClickListener(view1 -> {

            if (fullname.isEmpty() || username.isEmpty() || email.isEmpty() || password.isEmpty()){
                Toast.makeText(getContext(), "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                return;
            }

            //xu ly dang ky
            if (!edtFullname.getText().toString().matches(fullnamePattern)){
                edtFullname.setError("Tên không hợp lệ");
            }else if (!edtUsername.getText().toString().matches(usernamePattern)){
                edtUsername.setError("Tên đăng nhập phải từ 4 đến 20 ký tự và không chứa ký tự đặc biệt");
            }else if (!edtEmail.getText().toString().matches(emailPattern)){
                edtEmail.setError("Email không hợp lệ");
            }else if (!edtPassword.getText().toString().matches(passPattern)){
                edtPassword.setError("Mật khẩu phải từ 8 đến 20 ký tự, có ít nhất 1 chữ hoa, 1 chữ thường, 1 số và 1 ký tự đặc biệt");
            }else {
                //kiểm tra username và email đã tồn tại chưa
                if (accDAO.checkUsernameExists(username)){
                    edtUsername.setError("Username đã tồn tại");
                } else
                    if (accDAO.checkEmailExists(email)) {
                    edtEmail.setError("Email đã tồn tại");
                } else{
                    boolean kq = accDAO.register(new Acc(fullname, username, email, password));
                    if (kq){
                        Toast.makeText(getContext(), "Đăng ký thành công", Toast.LENGTH_SHORT).show();
                        edtFullname.setText("");
                        edtUsername.setText("");
                        edtEmail.setText("");
                        edtPassword.setText("");
                    }else {
                        Toast.makeText(getContext(), "Đăng ký thất bại", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });

    }
}
