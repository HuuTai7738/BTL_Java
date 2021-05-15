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
public class HoatDong {
    private String maHD;
    private String tenHD;
    private int diemCong;

    public HoatDong() {
    }

    public HoatDong(String maHD, String tenHD, int diemCong) {
        this.maHD = maHD;
        this.tenHD = tenHD;
        this.diemCong = diemCong;
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
}
