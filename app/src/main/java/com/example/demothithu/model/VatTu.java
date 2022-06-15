package com.example.demothithu.model;

public class VatTu {
    private int id;
    private String name;
    private int gia;

    public VatTu() {
    }

    public VatTu(int id, String name, int gia) {
        this.id = id;
        this.name = name;
        this.gia = gia;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getGia() {
        return gia;
    }

    public void setGia(int gia) {
        this.gia = gia;
    }
}
