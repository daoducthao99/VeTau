package com.example.tau;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;



import androidx.annotation.Nullable;

import java.util.ArrayList;

public class VeTauDB extends SQLiteOpenHelper {

    public static final String TableName = "ContactTable";
    public static final String Ma = "Ma";
    public static final String GaDi = "GaDi";
    public static final String GaDen = "GaDen";
    public static final String DonGia= "DonGia";
    public static final String Khuhoi= "KhuHoi";
    public VeTauDB(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }



    @Override
    public void onCreate(SQLiteDatabase db) {
        String sqlCreate = "Create table if not exists " + TableName + "("
                + Ma + " Integer Primary key autoincrement, "
                + GaDi + " Text, "
                + GaDen + " Text, "
                + DonGia + " Text, "
                + Khuhoi + " Boolean)";
        //chạy câu truy vấn SQL để tạo bảng
        db.execSQL(sqlCreate);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("Drop table if exists " + TableName);
        //tạo lại
        onCreate(db);
    }

    public ArrayList<VeTau> getAllVeTau()
    {
        ArrayList<VeTau> list = new ArrayList<>();
        //câu truy vấn
        String sql = "Select * from " + TableName;
        //lấy đối tượng csdl sqlite
        SQLiteDatabase db = this.getReadableDatabase();
        //chạy câu truy vấn trả về dạng Cursor
        Cursor cursor = db.rawQuery(sql,null);
        //tạo ArrayList<Contact> để trả về;
        if(cursor!=null)
            while (cursor.moveToNext())
            {
                VeTau restaurant = new VeTau(cursor.getInt(0),
                        cursor.getString(1), cursor.getString(2),
                        cursor.getDouble(3),cursor.getInt(4)>0);
                list.add(restaurant);
            }
        return list;
    }

    //hàm thêm một contact vào bảng TableContact
    public void addVeTau(VeTau veTau)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues value = new ContentValues();
        value.put(Ma, veTau.getMa());
        value.put(GaDi, veTau.getGaDi());
        value.put(GaDen,veTau.getGaDen());
        value.put(DonGia, veTau.getDonGia());
        value.put(Khuhoi, veTau.isKhuhoi());
        db.insert(TableName,null,value);
        db.close();
    }
    public void updateVeTau(int id, VeTau veTau)
    {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues value = new ContentValues();
        value.put(Ma, veTau.getMa());
        value.put(GaDi, veTau.getGaDi());
        value.put(GaDen,veTau.getGaDen());
        value.put(DonGia, veTau.getDonGia());
        value.put(Khuhoi, veTau.isKhuhoi());
        db.update(TableName, value,
                Ma + "=?",
                new String[]{String.valueOf(id)});
        db.close();
    }
    public void deleteRestaurant(int id)
    {
        SQLiteDatabase db = getWritableDatabase();
        String sql = "Delete From " + TableName + " Where ID=" + id;
        //db.delete(TableName, Ms + "=?",new String[]{String.valueOf(id)});
        db.execSQL(sql);
        db.close();
    }
}
