package com.example.doan;

import java.text.Format;

public class BaiXe {
    private String TenBaiXe;
    private String DiaChi;
    private String HinhAnh;
    private float SoChoDo;
    private String Key;
    public BaiXe() {
    }

    private String TrangThai;
    private float Gia;

    public BaiXe(String tenBaiXe, String diaChi, String hinhAnh, float soChoDo, String trangThai, float gia) {
        this.TenBaiXe = tenBaiXe;
        this.DiaChi = diaChi;
        this.HinhAnh = hinhAnh;
        this.SoChoDo = soChoDo;
        this.TrangThai = trangThai;
        this.Gia = gia;
    }

    public String getKey() {
        return Key;
    }

    public void setKey(String key) {
        Key = key;
    }

    public String getTenBaiXe() {
        return TenBaiXe;
    }

    public void setTenBaiXe(String tenBaiXe) {
        TenBaiXe = tenBaiXe;
    }

    public String getDiaChi() {
        return DiaChi;
    }

    public void setDiaChi(String diaChi) {
        DiaChi = diaChi;
    }

    public String getHinhAnh() {
        return HinhAnh;
    }

    public void setHinhAnh(String hinhAnh) {
        HinhAnh = hinhAnh;
    }
    public float getSoChoDo() {
        return SoChoDo;
    }

    public void setSoChoDo(float soChoDo) {
        SoChoDo = soChoDo;
    }

    public String getTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(String trangThai) {
        TrangThai = trangThai;
    }

    public float getGia() {
        return Gia;
    }

    public void setGia(float gia) {
        Gia = gia;
    }
}
