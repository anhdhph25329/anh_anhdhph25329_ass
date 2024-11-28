package fpt.anhdhph.anh_anhdhph25329_ass.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import fpt.anhdhph.anh_anhdhph25329_ass.database.DbHelper;
import fpt.anhdhph.anh_anhdhph25329_ass.model.Acc;

public class AccDAO {

    DbHelper dbHelper;
    Context context;

    public AccDAO(Context context) {
        this.context = context;
        dbHelper = new DbHelper(context);
    }

    // Đăng ký
    public boolean register(Acc account) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("Fullname", account.getFullname());
        values.put("Username", account.getUsername());
        values.put("Email", account.getEmail());
        values.put("Password", account.getPassword());

        long result = db.insert("tb_acc", null, values);
        db.close();
        return result != -1;
    }

    // Đăng nhập
    public boolean login(String identifier, String password) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(
                "SELECT * FROM tb_acc WHERE (Username = ? OR Email = ?) AND Password = ?",
                new String[]{identifier, identifier, password}
        );

        boolean isLoggedIn = cursor.getCount() > 0; // Nếu có kết quả, thông tin hợp lệ
        cursor.close();
        return isLoggedIn;
    }

    // Kiểm tra Username đã tồn tại chưa
    public boolean checkUsernameExists(String username) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String query = "SELECT * FROM tb_acc WHERE Username = ?";
        Cursor cursor = db.rawQuery(query, new String[]{username});
        boolean exists = cursor.getCount() > 0;
        cursor.close();
        db.close();
        return exists;
    }

    // Kiểm tra Email đã tồn tại chưa
    public boolean checkEmailExists(String email) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String query = "SELECT * FROM tb_acc WHERE Email = ?";
        Cursor cursor = db.rawQuery(query, new String[]{email});
        boolean exists = cursor.getCount() > 0;
        cursor.close();
        db.close();
        return exists;
    }

    // Kiểm tra Username và Email có khớp với tài khoản không
    public boolean checkUsernameAndEmail(String username, String email) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(
                "SELECT * FROM tb_acc WHERE Username = ? AND Email = ?",
                new String[]{username, email}
        );

        boolean exists = cursor.getCount() > 0; // Nếu tồn tại bản ghi thì trả về true
        cursor.close();
        return exists;
    }

    // Cập nhật mật khẩu mới
    public boolean updatePassword(String username, String newPassword) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("Password", newPassword);

        int rowsAffected = db.update("tb_acc", values, "Username = ?", new String[]{username});
        return rowsAffected > 0; // Nếu có ít nhất 1 dòng bị ảnh hưởng, cập nhật thành công
    }

}
