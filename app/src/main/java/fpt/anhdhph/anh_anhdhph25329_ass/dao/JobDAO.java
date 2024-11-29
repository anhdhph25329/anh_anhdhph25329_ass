package fpt.anhdhph.anh_anhdhph25329_ass.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

import fpt.anhdhph.anh_anhdhph25329_ass.database.DbHelper;
import fpt.anhdhph.anh_anhdhph25329_ass.model.Job;

public class JobDAO {
    DbHelper dbHelper;
    Context context;
    public JobDAO(Context context) {
        this.context = context;
        dbHelper = new DbHelper(context);
    }

    // Thêm job vào cơ sở dữ liệu
    public boolean addJob(Job job) {
        if (job.getName() == null || job.getContent() == null || job.getStartDay() == null || job.getEndDay() == null) {
            Log.e("JobDAO", "Dữ liệu không hợp lệ. Không thể thêm công việc.");
            return false;
        }

        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("Name", job.getName());
        values.put("Content", job.getContent());
        values.put("Status", job.getStatus());
        values.put("[Start Day]", job.getStartDay());
        values.put("[End Day]", job.getEndDay());

        long result = sqLiteDatabase.insert("tb_job", null, values);
        return result != -1;
    }

    // Cập nhật job trong cơ sở dữ liệu
    public boolean updateJob(Job job) {
        ContentValues values = new ContentValues();
        values.put("Name", job.getName());
        values.put("Content", job.getContent());
        values.put("Status", job.getStatus());
        values.put("[Start Day]", job.getStartDay());
        values.put("[End Day]", job.getEndDay());

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        int rowsAffected = db.update("tb_job", values, "ID = ?", new String[]{String.valueOf(job.getId())});
        return rowsAffected > 0;
    }

    // Xóa job khỏi cơ sở dữ liệu
    public boolean deleteJob(int jobId) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        int rowsAffected = db.delete("tb_job", "ID = ?", new String[]{String.valueOf(jobId)});
        return rowsAffected > 0; // Trả về true nếu có dòng bị xóa
    }

    // Lấy danh sách job từ cơ sở dữ liệu
    public ArrayList<Job> getList(){
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        ArrayList<Job> list = new ArrayList<>();
        String sql = "SELECT ID, Name, Content, Status, [Start Day], [End Day] FROM tb_job";
        Cursor cursor = db.rawQuery(sql, null);

        if (cursor != null && cursor.getCount() > 0) {
            cursor.moveToFirst();
            do {
                Job job = new Job();
                job.setId(cursor.getInt(0));
                job.setName(cursor.getString(1));
                job.setContent(cursor.getString(2));
                job.setStatus(cursor.getInt(3));
                job.setStartDay(cursor.getString(4));
                job.setEndDay(cursor.getString(5));
                list.add(job);
            } while (cursor.moveToNext());
        } else {
            Log.d("JobDAO", "Không lấy được dữ liệu công việc");
        }

        cursor.close();
        return list;
    }
}
