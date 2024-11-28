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
    SQLiteDatabase sqLiteDatabase;
    Context context;
    public JobDAO(Context context) {
        this.context = context;
        dbHelper = new DbHelper(context);
        sqLiteDatabase = dbHelper.getWritableDatabase();
    }

    public int addJob(Job job){
        ContentValues values = new ContentValues();
        values.put("Name", job.getName());
        values.put("Content", job.getContent());
        values.put("Status", job.getStatus());
        values.put("Start Day", job.getStartDay());
        values.put("End Day", job.getEndDay());
        int kq = (int) sqLiteDatabase.insert("tb_job", null, values);
        return kq;
    }

    public ArrayList<Job> getList(){
        ArrayList<Job> list = new ArrayList<>();
        String sql = "SELECT id, name FROM tb_cat";
        Cursor cursor = sqLiteDatabase.rawQuery(sql, null);
        if (cursor != null && cursor.getCount() > 0){
            //lấy được dữ liệu
            cursor.moveToFirst();

            //duyệt vòng lặp
            do {
                //thứ tự cột: id là 0, name là 1
                int id = cursor.getInt(0);
                String name = cursor.getString(1);
                String content = cursor.getString(2);
                int status = cursor.getInt(3);
                String startDay = cursor.getString(4);
                String endDay = cursor.getString(5);


                Job job = new Job();
                job.setId(id);
                job.setName(name);
                job.setContent(content);
                job.setStatus(status);
                job.setStartDay(startDay);
                job.setEndDay(endDay);
                //thêm vào list
                list.add(job);
            }while (cursor.moveToNext());

        }else {
            Log.d("zzz", "JobDAO::getList: Không lấy được dữ liệu");
        }
        return list;
    }
}
