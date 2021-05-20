/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.ArrayList;

/**
 *
 * @author Personal
 */
public class SinhVien {
    private String maSV;
    private String tenSV;
    private String khoaQL;
    private String lop;
    private ThongTinSV thongTinSV;
    private ThongTinGD thongTinGD;
    private ArrayList<HoatDong> listHoatDong;
    private ArrayList<KyLuat> listKyLuat;

    public SinhVien() {
    }

    public SinhVien(String maSV, String tenSV, String khoaQL, String lop, ThongTinSV thongTinSV, ThongTinGD thongTinGD, ArrayList<HoatDong> listHoatDong, ArrayList<KyLuat> listKyLuat) {
        this.maSV = maSV;
        this.tenSV = tenSV;
        this.khoaQL = khoaQL;
        this.lop = lop;
        this.thongTinSV = thongTinSV;
        this.thongTinGD = thongTinGD;
        this.listHoatDong = listHoatDong;
        this.listKyLuat = listKyLuat;
    }

    public String getMaSV() {
        return maSV;
    }

    public String getTenSV() {
        return tenSV;
    }

    public String getKhoaQL() {
        return khoaQL;
    }

    public String getLop() {
        return lop;
    }

    public ThongTinSV getThongTinSV() {
        return thongTinSV;
    }

    public ThongTinGD getThongTinGD() {
        return thongTinGD;
    }

    public ArrayList<HoatDong> getListHoatDong() {
        return listHoatDong;
    }

    public ArrayList<KyLuat> getListKyLuat() {
        return listKyLuat;
    }

    public void setMaSV(String maSV) {
        this.maSV = maSV;
    }

    public void setTenSV(String tenSV) {
        this.tenSV = tenSV;
    }

    public void setKhoaQL(String khoaQL) {
        this.khoaQL = khoaQL;
    }

    public void setLop(String lop) {
        this.lop = lop;
    }

    public void setThongTinSV(ThongTinSV thongTinSV) {
        this.thongTinSV = thongTinSV;
    }

    public void setThongTinGD(ThongTinGD thongTinGD) {
        this.thongTinGD = thongTinGD;
    }

    public void setListHoatDong(ArrayList<HoatDong> listHoatDong) {
        this.listHoatDong = listHoatDong;
    }

    public void setListKyLuat(ArrayList<KyLuat> listKyLuat) {
        this.listKyLuat = listKyLuat;
    }

    
}
