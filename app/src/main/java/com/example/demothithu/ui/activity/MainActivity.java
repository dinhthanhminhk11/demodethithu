package com.example.demothithu.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.demothithu.R;
import com.example.demothithu.dao.VatTuDao;
import com.example.demothithu.model.VatTu;
import com.example.demothithu.ui.adapter.VatTuAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements VatTuAdapter.Callback {
    private ListView listview;
    private List<VatTu> data;
    private VatTuAdapter vatTuAdapter;
    private VatTuDao vatTuDao;
    private EditText nameVatTu;
    private EditText giaVatTu;
    private Button edit1;
    private Button cancel;
    private Button them;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        them = (Button) findViewById(R.id.them);


        listview = (ListView) findViewById(R.id.listview);
        data = new ArrayList<>();
        them.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, ThemVatTuActivity.class));
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        showData();
    }

    private void showData() {
        vatTuDao = new VatTuDao(this);
        data = vatTuDao.getListAllVatTu();
        vatTuAdapter = new VatTuAdapter(data, this);
        listview.setAdapter(vatTuAdapter);
    }

    @Override
    public void delete(VatTu vatTu) {

        vatTuDao.deleteVatTu(vatTu.getId());
        Toast.makeText(this, "Xoa thanh coong", Toast.LENGTH_SHORT).show();
        showData();
    }

    @Override
    public void edit(VatTu vatTu) {
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_edit);

        dialog.setCancelable(false);

        Window window = dialog.getWindow();
        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        if (dialog != null && dialog.getWindow() != null) {
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }

        nameVatTu = (EditText) dialog.findViewById(R.id.nameVatTu);
        giaVatTu = (EditText) dialog.findViewById(R.id.giaVatTu);
        edit1 = (Button) dialog.findViewById(R.id.edit);
        cancel = (Button) dialog.findViewById(R.id.cancel);

        edit1.setOnClickListener(v -> {
            String name = nameVatTu.getText().toString();
            String gia = giaVatTu.getText().toString();

            VatTu vatTu1 = new VatTu(vatTu.getId(), name, Integer.parseInt(gia));

            if (vatTuDao.editVatTu(vatTu1) == true) {
                Toast.makeText(this, "Sua thanh cong", Toast.LENGTH_SHORT).show();
                showData();
                dialog.cancel();
            } else {
                Toast.makeText(this, "Sua bai", Toast.LENGTH_SHORT).show();
            }
        });

        cancel.setOnClickListener(v -> {
            dialog.cancel();
        });

        dialog.show();


    }








}