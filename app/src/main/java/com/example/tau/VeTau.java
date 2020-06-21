package com.example.tau;

public class VeTau {
    int Ma;
    String GaDi;
    String GaDen;
    double DonGia;
    boolean khuhoi;

    public VeTau() {
    }

    public double getDonGia() {
        return DonGia;
    }

    public void setDonGia(double donGia) {
        DonGia = donGia;
    }

    public VeTau(int ma, String gaDi, String gaDen, double donGia, boolean khuhoi) {
        Ma = ma;
        GaDi = gaDi;
        GaDen = gaDen;
        DonGia = donGia;
        this.khuhoi = khuhoi;
    }

    public int getMa() {
        return Ma;
    }

    public void setMa(int ma) {
        Ma = ma;
    }

    public String getGaDi() {
        return GaDi;
    }

    public void setGaDi(String gaDi) {
        GaDi = gaDi;
    }

    public String getGaDen() {
        return GaDen;
    }

    public void setGaDen(String gaDen) {
        GaDen = gaDen;
    }

    public boolean isKhuhoi() {
        return khuhoi;
    }

    public void setKhuhoi(boolean khuhoi) {
        this.khuhoi = khuhoi;
    }
}
