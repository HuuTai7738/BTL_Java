/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Personal
 */
public class DBConnection {
    private final String url = "jdbc:derby://localhost:1527/QLYSINHVIEN";
    private final String user = "Administrator";
    private final String password = "123";
    
    private Connection cont;
    public Connection getConnection(){
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            
            cont = DriverManager.getConnection(url, user, password);
            return cont;
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public void closeConnection(){
        try {
            cont.close();
        } catch (SQLException ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public ResultSet layThongTinSinhVien(){
        ResultSet rs = null;
        try {
            
            Statement st =  cont.createStatement();
            String s = "SELECT * FROM SINHVIEN JOIN THONGTINGIADINH ON SINHVIEN.MASV = THONGTINGIADINH.MASV JOIN THONGTINSINHVIEN ON SINHVIEN.MASV = THONGTINSINHVIEN.MASV";
            rs = st.executeQuery(s);
            
        } catch (SQLException ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }
    public ResultSet layBangTaiKhoan(){
        getConnection();
        ResultSet rs = null;
        try {
            Statement st = cont.createStatement();
            rs = st.executeQuery("SELECT * FROM TAIKHOAN");
        } catch (SQLException ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        closeConnection();
        return rs;
    }
    
    public ResultSet layHoatDongSinhVien(String s){
        ResultSet rs = null;
        
        
        
        return rs;
    }
    
    public void xoaSinhVien(String maSV){
        PreparedStatement ps;
        String xoaTK = "DELETE FROM TAIKHOAN WHERE MASV = ?";
        String xoaThongTinGD = "DELETE FROM THONGTINGIADINH WHERE MASV = ?";
        String xoathongTinSV = "DELETE FROM THONGTINSINHVIEN WHERE MASV = ?";
        String xoaHoatDong = "DELETE FROM HOATDONG WHERE MASV = ?";
        String xoaKiLuat = "DELETE FROM KILUAT WHERE MASV = ?";
        String xoaSinhVien = "DELETE FROM SINHVIEN WHERE MASV = ?";
        try {
            ps = cont.prepareStatement(xoaTK);
            ps.setString(1, maSV);
            ps.executeUpdate();
            
            ps = cont.prepareStatement(xoaThongTinGD);
            ps.setString(1, maSV);
            ps.executeUpdate();
            
            ps = cont.prepareStatement(xoathongTinSV);
            ps.setString(1, maSV);
            ps.executeUpdate();
            
            ps = cont.prepareStatement(xoaHoatDong);
            ps.setString(1, maSV);
            ps.executeUpdate();
            
            ps = cont.prepareStatement(xoaKiLuat);
            ps.setString(1, maSV);
            ps.executeUpdate();
            
            ps = cont.prepareStatement(xoaSinhVien);
            ps.setString(1, maSV);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
