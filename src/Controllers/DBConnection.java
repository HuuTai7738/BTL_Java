/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;
import Model.HoatDong;
import Model.KyLuat;
import Model.SinhVien;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Personal
 */
public class DBConnection {
    private String url = "jdbc:derby://localhost:1527/QLYSINHVIEN";
    private String user = "Administrator";
    private String password = "123";
    
    private Connection cont;
    public Connection getConnection(){
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            
            cont = (Connection)DriverManager.getConnection(url, user, password);
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
    public boolean kiemTra(String maSV){
        try {
            String sql="select * from SINHVIEN where maSV='"+maSV+"'";
            Statement st=cont.createStatement();
            ResultSet rs=st.executeQuery(sql);
            if(rs.next())
                return true;
            else return false;
        } catch (SQLException ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
         
    }
    public ArrayList<HoatDong> layDanhSachHoatDong(){
        ArrayList<HoatDong> list=new ArrayList<>();
        try {
            String sql="select * from HOATDONG";
            Statement st=cont.createStatement();
            ResultSet rs=st.executeQuery(sql);
            while(rs.next()){
                HoatDong hD=new HoatDong();
                hD.setMaSV(rs.getString("maSV"));
                hD.setMaHD(rs.getString("maHD"));
                hD.setTenHD(rs.getString("tenHD"));
                hD.setDiemCong(rs.getInt("diemCong"));
                list.add(hD);
            }
            closeConnection();
        } catch (SQLException ex) {
            System.out.println("Không thể thực hiện truy vấn");
        }
        return list;
    }
    public int themHoatDong(HoatDong hD) throws SQLException {
        String sql="insert into HOATDONG values(?,?,?,?)";
        PreparedStatement ps=cont.prepareStatement(sql);
        ps.setString(1,hD.getMaSV());
        ps.setString(2,hD.getMaHD());
        ps.setString(3,hD.getTenHD());
        ps.setInt(4,hD.getDiemCong());
        return ps.executeUpdate();
           
    }
    public int xoaHoatDong(String maSV,String maHD) throws SQLException{
        String sql="Delete from HOATDONG where maSV=? and maHD=?";
        PreparedStatement ps=cont.prepareStatement(sql);
        ps.setString(1,maSV);
        ps.setString(2, maHD);
        return ps.executeUpdate();
    }
    public ArrayList<KyLuat> layDanhSachKyLuat(){
        ArrayList<KyLuat> list=new ArrayList<>();
        try {
            String sql="select * from KILUAT";
            Statement st=cont.createStatement();
            ResultSet rs=st.executeQuery(sql);
            while(rs.next()){
                KyLuat kL=new KyLuat();
                kL.setMaSV(rs.getString("maSV"));
                kL.setMaKL(rs.getString("maKL"));
                kL.setTenKL(rs.getString("tenKL"));
                kL.setDiemTru(rs.getInt("diemTru"));
                list.add(kL);
            }
        } catch (SQLException ex) {
            System.out.println("Không thể thực hiện truy vấn");
        }
        return list;
    }
    public int themKyLuat(KyLuat kL) throws SQLException{
        String sql="insert into KILUAT values(?,?,?,?)";
        PreparedStatement ps=cont.prepareStatement(sql);
        ps.setString(1,kL.getMaSV());
        ps.setString(2,kL.getMaKL());
        ps.setString(3,kL.getTenKL());
        ps.setInt(4,kL.getDiemTru());
        return ps.executeUpdate();
        
    }
    public int xoaKyLuat(String maSV,String maKL) throws SQLException{
        String sql="Delete from HOATDONG where maSV=? and maKL=?";
        PreparedStatement ps=cont.prepareStatement(sql);
        ps.setString(1,maSV);
        ps.setString(2, maKL);
        return ps.executeUpdate();
    }
    public ResultSet tongKetHoatDong() throws SQLException{
        
        ResultSet rs=null;
        String sql = "select SINHVIEN.maSV,SINHVIEN.tenSV,SUM(COALESCE(diemCong,0))\n" +
        "from SINHVIEN left join HOATDONG on SINHVIEN.maSV=HOATDONG.maSV\n" +
        "group by SINHVIEN.maSV,SINHVIEN.tenSV";
        PreparedStatement ps = cont.prepareStatement(sql);
        rs = ps.executeQuery();
        return rs;
    }
    public ResultSet tongKetKyLuat() throws SQLException{
        
        ResultSet rs=null;
        String sql = "select SINHVIEN.maSV,SINHVIEN.tenSV,SUM(COALESCE(diemTru,0))\n" +
        "from SINHVIEN left join kiluat on SINHVIEN.maSV=kiluat.maSV\n" +
        "group by SINHVIEN.maSV,SINHVIEN.tenSV";
        PreparedStatement ps = cont.prepareStatement(sql);
        rs = ps.executeQuery();
        return rs;
    }
    
    public void xoaSinhVien(String maSV) throws SQLException{
        PreparedStatement ps;
        String xoaTK = "DELETE FROM TAIKHOAN WHERE MASV = ?";
        String xoaThongTinGD = "DELETE FROM THONGTINGIADINH WHERE MASV = ?";
        String xoathongTinSV = "DELETE FROM THONGTINSINHVIEN WHERE MASV = ?";
        String xoaHoatDong = "DELETE FROM HOATDONG WHERE MASV = ?";
        String xoaKiLuat = "DELETE FROM KILUAT WHERE MASV = ?";
        String xoaSinhVien = "DELETE FROM SINHVIEN WHERE MASV = ?";
       
        
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
        
    }
    public String layMatKhau(String maSV){
        String matkhau = null;
        String sql = "SELECT MATKHAU FROM TAIKHOAN WHERE MASV = ?";
        try {
            PreparedStatement ps = cont.prepareStatement(sql);
            ps.setString(1, maSV);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
            matkhau = rs.getString("MATKHAU");
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
            return matkhau;
    }
    public void suaSinhVien(SinhVien sv, String matKhau) throws SQLException{
        String suaTaiKhoan = "UPDATE TAIKHOAN SET MATKHAU = ? WHERE MASV = ?";
        String suaThongTinGD = "UPDATE THONGTINGIADINH SET HOTENCHA = ?, NAMSINHCHA = ?, DIENTHOAICHA = ?, NGHENGHIEPCHA = ?, DIACHILIENHECHA = ?, HOTENME = ?, NAMSINHME = ?, DIENTHOAIME = ?,NGHENGHIEPME = ?, DIACHILIENHEME = ?,HOTENCHUHO = ? WHERE MASV = ?";
        String suaThongTinSV = "UPDATE THONGTINSINHVIEN SET NGAYSINH = ?, SDTSV = ?, EMAILSV = ?, QUOCTICH = ?, NOITHUONGTRU = ?, NOITAMTRU = ?, DANTOC = ?, TONGIAO = ?, SOCCCD = ?, TENNGANHANG = ?, STKNGANHANG = ? , MABHYT = ? WHERE MASV = ?";
        String suaSV = "UPDATE SINHVIEN SET TENSV = ?, KHOA = ?,LOP = ? WHERE MASV = ?";
        
        PreparedStatement ps;
        ps = cont.prepareStatement(suaTaiKhoan);
        ps.setString(1, matKhau);
        ps.setString(2, sv.getMaSV());
        ps.executeUpdate();
        
        ps = cont.prepareStatement(suaThongTinGD);
        ps.setString(1, sv.getThongTinGD().getHoTenCha());
        ps.setInt(2, sv.getThongTinGD().getNamSinhCha());
        ps.setString(3, sv.getThongTinGD().getDienThoaiCha());
        ps.setString(4, sv.getThongTinGD().getNgheNghiepCha());
        ps.setString(5, sv.getThongTinGD().getDiaChiLienHeCha());
        ps.setString(6, sv.getThongTinGD().getHoTenMe());
        ps.setInt(7, sv.getThongTinGD().getNamSinhMe());
        ps.setString(8, sv.getThongTinGD().getDienThoaiMe());
        ps.setString(9, sv.getThongTinGD().getNgheNghiepMe());
        ps.setString(10, sv.getThongTinGD().getDiaChiLienHeMe());
        ps.setString(11, sv.getThongTinGD().getHoTenChuHo());
        ps.setString(12, sv.getMaSV());
        ps.executeUpdate();
        
        ps = cont.prepareStatement(suaThongTinSV);
        SimpleDateFormat dt = new SimpleDateFormat("dd/MM/yyyy");
        
        
        
        try {
            ps.setDate(1, new Date(dt.parse(sv.getThongTinSV().getNgaySinh()).getTime()));
        } catch (ParseException ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        ps.setString(2, sv.getThongTinSV().getSdtSV());
        ps.setString(3, sv.getThongTinSV().getEmailSV());
        ps.setString(4, sv.getThongTinSV().getQuocTich());
        ps.setString(5, sv.getThongTinSV().getNoiThuongTru());
        ps.setString(6, sv.getThongTinSV().getNoiTamTru());
        ps.setString(7, sv.getThongTinSV().getDanToc());
        ps.setString(8, sv.getThongTinSV().getTonGiao());
        ps.setString(9, sv.getThongTinSV().getSoCCCD());
        ps.setString(10, sv.getThongTinSV().getSoCCCD());
        ps.setString(11, sv.getThongTinSV().getTenNganHang());
        ps.setString(12, sv.getThongTinSV().getStkNganHang());
        ps.setString(13, sv.getMaSV());
        ps.executeUpdate();
        
        ps = cont.prepareStatement(suaSV);
        ps.setString(1, sv.getTenSV());
        ps.setString(2, sv.getKhoaQL());
        ps.setString(3, sv.getLop());
        ps.setString(4, sv.getMaSV());
        ps.executeUpdate();
        
        
    }
}
