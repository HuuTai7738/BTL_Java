/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.Objects;

/**
 *
 * @author Admin
 */
public class TaiKhoan {
    private String maSV;
    private String matKhau;
    
    public TaiKhoan(){
        this.maSV = null;
        this.matKhau = null;
    }
    public TaiKhoan(String maSV, String matKhau) {
        this.maSV = maSV;
        this.matKhau = matKhau;
    }

    public String getMaSV() {
        return maSV;
    }

    public String getMatKhau() {
        return matKhau;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.maSV);
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
        final TaiKhoan other = (TaiKhoan) obj;
        if (!Objects.equals(this.maSV, other.maSV)) {
            return false;
        }
        return true;
    }

    public void setMaSV(String maSV) {
        this.maSV = maSV;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }
    
    
}
