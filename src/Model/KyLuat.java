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
public class KyLuat {
    private String maSV;
    private String maKL;
    private String tenKL;
    private int diemTru;

    public KyLuat() {
    }

    public KyLuat(String maSV, String maKL) {
        this.maSV = maSV;
        this.maKL = maKL;
    }

    public KyLuat(String maSV, String maKL, String tenKL, int diemTru) {
        this.maSV = maSV;
        this.maKL = maKL;
        this.tenKL = tenKL;
        this.diemTru = diemTru;
    }

    public String getMaSV() {
        return maSV;
    }

    public void setMaSV(String maSV) {
        this.maSV = maSV;
    }

    public String getMaKL() {
        return maKL;
    }

    public void setMaKL(String maKL) {
        this.maKL = maKL;
    }

    public String getTenKL() {
        return tenKL;
    }

    public void setTenKL(String tenKL) {
        this.tenKL = tenKL;
    }

    public int getDiemTru() {
        return diemTru;
    }

    public void setDiemTru(int diemTru) {
        this.diemTru = diemTru;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 41 * hash + Objects.hashCode(this.maSV);
        hash = 41 * hash + Objects.hashCode(this.maKL);
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
        final KyLuat other = (KyLuat) obj;
        if (!Objects.equals(this.maSV, other.maSV)) {
            return false;
        }
        if (!Objects.equals(this.maKL, other.maKL)) {
            return false;
        }
        return true;
    }

    
}
