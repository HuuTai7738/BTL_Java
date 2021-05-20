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
            String s = "SELECT * FROM SINHVIEN JOIN THONGTINGIADINH ON SINHVIEN.MASV = THONGTINGIADIDNH.MASV JOIN THONGTINSINHVIEN ON THONGTINSINHVIEN.MASV = SINHVIEN.MASV ";
            rs = st.executeQuery(s);
            
        } catch (SQLException ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }
    
}
