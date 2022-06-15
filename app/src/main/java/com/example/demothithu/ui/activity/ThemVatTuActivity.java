package com.example.demothithu.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.demothithu.R;
import com.example.demothithu.dao.VatTuDao;
import com.example.demothithu.model.VatTu;

public class ThemVatTuActivity extends AppCompatActivity {
    private EditText nameVatTu;
    private EditText giaVatTu;
    private Button add;
    private VatTuDao dao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_vat_tu);


        nameVatTu = (EditText) findViewById(R.id.nameVatTu);
        giaVatTu = (EditText) findViewById(R.id.giaVatTu);
        add = (Button) findViewById(R.id.add);
        dao = new VatTuDao(this);

        add.setOnClickListener(v->{
            String name = nameVatTu.getText().toString();
            String gia = giaVatTu.getText().toString();
            VatTu vatTu = new VatTu();
            vatTu.setName(name);
            vatTu.setGia(Integer.parseInt(gia));

            if(dao.addVatTu(vatTu) == true){
                Toast.makeText(this, "Them Thanh Cong", Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(this, "Them bai", Toast.LENGTH_SHORT).show();
            }

        });
    }
}