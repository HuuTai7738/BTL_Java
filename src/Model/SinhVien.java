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
    private TaiKhoan taiKhoan;
    private ThongTinGD thongTinGD;
    private ArrayList<HoatDong> listHoatDong=new ArrayList<>();
    private ArrayList<KyLuat> listKyLuat=new ArrayList<>();
}
