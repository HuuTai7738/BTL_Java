/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author Personal
 */
public class KyLuat {
    private String maKL;
    private String tenKL;
    private int diemTru;

    public KyLuat() {
    }

    public KyLuat(String maKL, String tenKL, int diemTru) {
        this.maKL = maKL;
        this.tenKL = tenKL;
        this.diemTru = diemTru;
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
}
