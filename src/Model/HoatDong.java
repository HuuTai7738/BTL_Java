/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.Objects;

/**
 *
 * @author Personal
 */
public class HoatDong {
    private String maSV;
    private String maHD;
    private String tenHD;
    private int diemCong;

    public HoatDong() {
    }

    public HoatDong(String maSV, String maHD) {
        this.maSV = maSV;
        this.maHD = maHD;
    }

    public HoatDong(String maSV, String maHD, String tenHD, int diemCong) {
        this.maSV = maSV;
        this.maHD = maHD;
        this.tenHD = tenHD;
        this.diemCong = diemCong;
    }

    public String getMaSV() {
        return maSV;
    }

    public void setMaSV(String maSV) {
        this.maSV = maSV;
    }

    public String getMaHD() {
        return maHD;
    }

    public void setMaHD(String maHD) {
        this.maHD = maHD;
    }

    public String getTenHD() {
        return tenHD;
    }

    public void setTenHD(String tenHD) {
        this.tenHD = tenHD;
    }

    public int getDiemCong() {
        return diemCong;
    }

    public void setDiemCong(int diemCong) {
        this.diemCong = diemCong;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 31 * hash + Objects.hashCode(this.maSV);
        hash = 31 * hash + Objects.hashCode(this.maHD);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final HoatDong other = (HoatDong) obj;
        if (!Objects.equals(this.maSV, other.maSV)) {
            return false;
        }
        if (!Objects.equals(this.maHD, other.maHD)) {
            return false;
        }
        return true;
    }

    
}
