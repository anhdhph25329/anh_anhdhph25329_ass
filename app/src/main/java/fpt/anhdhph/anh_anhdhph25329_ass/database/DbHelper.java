package fpt.anhdhph.anh_anhdhph25329_ass.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHelper extends SQLiteOpenHelper {

    Context context;
    public DbHelper(Context context) {
        super(context, "QLCV.db", null, 15);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //tao bang tai khoan
        String sqlAcc = "CREATE TABLE tb_acc (\n" +
                "    ID       INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "    Fullname TEXT    NOT NULL,\n" +
                "    Username TEXT    UNIQUE\n" +
                "                     NOT NULL,\n" +
                "    Email    TEXT    UNIQUE\n" +
                "                     NOT NULL,\n" +
                "    Password TEXT    NOT NULL\n" +
                ");";
        sqLiteDatabase.execSQL(sqlAcc);

        //tao bang cong viec
        String sqlJob = "CREATE TABLE tb_job (\n" +
                "    ID          INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "    Name        TEXT    UNIQUE\n" +
                "                        NOT NULL,\n" +
                "    Content     TEXT    NOT NULL,\n" +
                "    Status      INTEGER,\n" +
                "    [Start Day] TEXT    NOT NULL,\n" +
                "    [End Day]   TEXT    NOT NULL\n" +
                ");";
        sqLiteDatabase.execSQL(sqlJob);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        if (i < i1){
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS tb_acc");
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS tb_job");
            onCreate(sqLiteDatabase);
        }
    }
}
