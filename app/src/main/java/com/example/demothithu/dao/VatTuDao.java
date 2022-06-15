package com.example.demothithu.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.demothithu.database.MyHelper;
import com.example.demothithu.model.VatTu;

import java.util.ArrayList;
import java.util.List;

public class VatTuDao {
    private SQLiteDatabase sqLiteDatabase;
    private MyHelper myHelper;

    public VatTuDao(Context context) {
        myHelper = new MyHelper(context);
    }

    public List<VatTu> getListAllVatTu() {
        List<VatTu> list = new ArrayList<>();
        String sql = "Select * from VatTu";
        sqLiteDatabase = myHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(sql, null);
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                VatTu vatTu = new VatTu();
                vatTu.setId(cursor.getInt(0));
                vatTu.setName(cursor.getString(1));
                vatTu.setGia(cursor.getInt(2));
                list.add(vatTu);
                cursor.moveToNext();
            }
        }
        cursor.close();
        sqLiteDatabase.close();
        return list;
    }

    public boolean addVatTu(VatTu vatTu) {
        sqLiteDatabase = myHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", vatTu.getName());
        contentValues.put("gia", vatTu.getGia());
        long insert = sqLiteDatabase.insert("VatTu", null, contentValues);
        if (insert <= 0) {
            return false;
        }
        return true;
    }

    public boolean deleteVatTu(int id){
        sqLiteDatabase = myHelper.getWritableDatabase();
        return sqLiteDatabase.delete("VatTu" , "idVatTu=?" , new String[]{String.valueOf(id)}) > 0;
    }

    public boolean editVatTu(VatTu vatTu){
        sqLiteDatabase = myHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", vatTu.getName());
        contentValues.put("gia", vatTu.getGia());
        long insert = sqLiteDatabase.update("VatTu",  contentValues ,"idVatTu=?" ,new String[]{String.valueOf(vatTu.getId())}  );
        if (insert <= 0) {
            return false;
        }
        return true;
    }


}
