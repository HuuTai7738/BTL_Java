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
public class ThongTinSV {
    private String ngaySinh;
    private String sdtSV;
    private String emailSV;
    private String quocTich;
    private String noiThuongTru;
    private String noiTamTru;
    private String danToc;
    private String tonGiao;
    private String soCCCD;
    private String tenNganHang;
    private String stkNganHang;
    private String maBHYT;

    public ThongTinSV() {
    }

    public ThongTinSV(String ngaySinh, String sdtSV, String emailSV, String quocTich, String noiThuongTru, String noiTamTru, String danToc, String tonGiao, String soCCCD, String tenNganHang, String stkNganHang, String maBHYT) {
        this.ngaySinh = ngaySinh;
        this.sdtSV = sdtSV;
        this.emailSV = emailSV;
        this.quocTich = quocTich;
        this.noiThuongTru = noiThuongTru;
        this.noiTamTru = noiTamTru;
        this.danToc = danToc;
        this.tonGiao = tonGiao;
        this.soCCCD = soCCCD;
        this.tenNganHang = tenNganHang;
        this.stkNganHang = stkNganHang;
        this.maBHYT = maBHYT;
    }

    public String getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(String ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public String getSdtSV() {
        return sdtSV;
    }

    public void setSdtSV(String sdtSV) {
        this.sdtSV = sdtSV;
    }

    public String getEmailSV() {
        return emailSV;
    }

    public void setEmailSV(String emailSV) {
        this.emailSV = emailSV;
    }

    public String getQuocTich() {
        return quocTich;
    }

    public void setQuocTich(String quocTich) {
        this.quocTich = quocTich;
    }

    public String getNoiThuongTru() {
        return noiThuongTru;
    }

    public void setNoiThuongTru(String noiThuongTru) {
        this.noiThuongTru = noiThuongTru;
    }

    public String getNoiTamTru() {
        return noiTamTru;
    }

    public void setNoiTamTru(String noiTamTru) {
        this.noiTamTru = noiTamTru;
    }

    public String getDanToc() {
        return danToc;
    }

    public void setDanToc(String danToc) {
        this.danToc = danToc;
    }

    public String getTonGiao() {
        return tonGiao;
    }

    public void setTonGiao(String tonGiao) {
        this.tonGiao = tonGiao;
    }

    public String getSoCCCD() {
        return soCCCD;
    }

    public void setSoCCCD(String soCCCD) {
        this.soCCCD = soCCCD;
    }

    public String getTenNganHang() {
        return tenNganHang;
    }

    public void setTenNganHang(String tenNganHang) {
        this.tenNganHang = tenNganHang;
    }

    public String getStkNganHang() {
        return stkNganHang;
    }

    public void setStkNganHang(String stkNganHang) {
        this.stkNganHang = stkNganHang;
    }

    public String getMaBHYT() {
        return maBHYT;
    }

    public void setMaBHYT(String maBHYT) {
        this.maBHYT = maBHYT;
    }
}
