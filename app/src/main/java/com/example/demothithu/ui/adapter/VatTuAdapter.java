package com.example.demothithu.ui.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.demothithu.R;
import com.example.demothithu.dao.VatTuDao;
import com.example.demothithu.model.VatTu;

import java.util.List;

public class VatTuAdapter extends BaseAdapter {
    private List<VatTu> data;
    private TextView nameVatTu;
    private TextView giaVatTu;
    private TextView edit;
    private TextView delete;
    private VatTuDao dao;
    private Callback callback;

    public VatTuAdapter(List<VatTu> data, Callback callback) {
        this.data = data;
        this.callback = callback;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return data == null ? 0 : data.size();
    }

    @Override
    public Object getItem(int i) {
        return data.get(i);
    }

    @Override
    public long getItemId(int i) {
        return data.get(i).getId();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View viewVatTu;
        if (view == null) {
            viewVatTu = View.inflate(viewGroup.getContext(), R.layout.item_vattu, null);
        } else {
            viewVatTu = view;
        }
        nameVatTu = (TextView) viewVatTu.findViewById(R.id.nameVatTu);
        giaVatTu = (TextView) viewVatTu.findViewById(R.id.giaVatTu);
        edit = (TextView) viewVatTu.findViewById(R.id.edit);
        delete = (TextView) viewVatTu.findViewById(R.id.delete);

        VatTu vatTu = (VatTu) getItem(i);

        nameVatTu.setText(vatTu.getName());
        giaVatTu.setText(String.valueOf(vatTu.getGia()));

        edit.setOnClickListener(v -> {
            callback.edit(vatTu);
        });

        delete.setOnClickListener(v -> {
            callback.delete(vatTu);
        });
        return viewVatTu;
    }

    public interface Callback {
        void delete(VatTu vatTu);

        void edit(VatTu vatTu);
    }
}
