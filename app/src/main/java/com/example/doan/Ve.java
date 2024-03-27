package com.example.doan;

public class Ve {
        String tenNguoiDat;
        String emailNguoiDat;
        String tenBaiXe;
        float thoiGian;
        float tongTien;

    public Ve(String tenNguoiDat, String emailNguoiDat, String tenBaiXe, float thoiGian, float tongTien) {
        this.tenNguoiDat = tenNguoiDat;
        this.emailNguoiDat = emailNguoiDat;
        this.tenBaiXe = tenBaiXe;
        this.thoiGian = thoiGian;
        this.tongTien = tongTien;
    }

    public String getTenNguoiDat() {
        return tenNguoiDat;
    }

    public void setTenNguoiDat(String tenNguoiDat) {
        this.tenNguoiDat = tenNguoiDat;
    }

    public String getEmailNguoiDat() {
        return emailNguoiDat;
    }

    public void setEmailNguoiDat(String emailNguoiDat) {
        this.emailNguoiDat = emailNguoiDat;
    }

    public String getTenBaiXe() {
        return tenBaiXe;
    }

    public void setTenBaiXe(String tenBaiXe) {
        this.tenBaiXe = tenBaiXe;
    }

    public float getThoiGian() {
        return thoiGian;
    }

    public void setThoiGian(float thoiGian) {
        this.thoiGian = thoiGian;
    }

    public float getTongTien() {
        return tongTien;
    }

    public void setTongTien(float tongTien) {
        this.tongTien = tongTien;
    }

    public Ve() {
    }
}
