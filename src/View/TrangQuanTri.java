/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controllers.DBConnection;
import Model.SinhVien;
import Model.ThongTinGD;
import Model.ThongTinSV;
import java.util.ArrayList;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import org.apache.commons.collections4.comparators.ComparableComparator;

/**
 *
 * @author Admin
 */
public class TrangQuanTri extends javax.swing.JFrame {

    /**
     * Creates new form TrangQuanTri
     */
    ArrayList<SinhVien> list = new ArrayList<>();
    ArrayList<SinhVien> listTimKiem;
    DBConnection dbconnection = new DBConnection();
    Connection cnt;

    public TrangQuanTri() {
        initComponents();
        this.setLocationRelativeTo(null);
        this.setTitle("Hệ thống quản lý sinh viên");
        cnt = dbconnection.getConnection();
        loadCSDL();
        loadTable();
        //dung customtable thi tblSinhVien.setModel(new SVCustomTable(list));
    }

    public void loadTable() {
        sapXep();
        tblSinhVien.setModel(new SVCustomTable(list));
    }


    public void loadCSDL() {
        ResultSet rs = dbconnection.layThongTinSinhVien();
        try {
            while (rs.next()) {
                SinhVien x = new SinhVien();
                x.setMaSV(rs.getString("MASV"));
                x.setTenSV(rs.getString("TENSV"));
                x.setKhoaQL(rs.getString("KHOA"));
                x.setLop(rs.getString("LOP"));

                ThongTinSV ttsv = new ThongTinSV();
                SimpleDateFormat date = new SimpleDateFormat("dd/MM/yyyy");
                String dt = date.format(rs.getDate("NGAYSINH"));
                ttsv.setNgaySinh(dt);
                ttsv.setSdtSV(rs.getString("SDTSV"));
                ttsv.setEmailSV(rs.getString("EMAILSV"));
                ttsv.setQuocTich(rs.getString("QUOCTICH"));
                ttsv.setNoiThuongTru(rs.getString("NOITHUONGTRU"));
                ttsv.setNoiTamTru(rs.getString("NOITAMTRU"));
                ttsv.setDanToc(rs.getString("DANTOC"));

                ttsv.setTonGiao(rs.getString("TONGIAO"));
                ttsv.setSoCCCD(rs.getString("SOCCCD"));
                ttsv.setTenNganHang(rs.getString("TENNGANHANG"));
                ttsv.setStkNganHang(rs.getString("STKNGANHANG"));
                ttsv.setMaBHYT(rs.getString("MABHYT"));
                x.setThongTinSV(ttsv);

                ThongTinGD ttgd = new ThongTinGD();
                ttgd.setHoTenCha(rs.getString("HOTENCHA"));
                ttgd.setNamSinhCha(rs.getInt("NAMSINHCHA"));
                ttgd.setDienThoaiCha(rs.getString("DIENTHOAICHA"));
                ttgd.setNgheNghiepCha(rs.getString("NGHENGHIEPCHA"));
                ttgd.setDiaChiLienHeCha(rs.getString("DIACHILIENHECHA"));
                ttgd.setHoTenMe(rs.getString("HOTENME"));
                ttgd.setNamSinhMe(rs.getInt("NAMSINHME"));
                ttgd.setDienThoaiMe(rs.getString("DIENTHOAIME"));
                ttgd.setNgheNghiepMe(rs.getString("NGHENGHIEPME"));
                ttgd.setDiaChiLienHeMe(rs.getString("DIACHILIENHEME"));
                ttgd.setHoTenChuHo(rs.getString("HOTENCHUHO"));
                x.setThongTinGD(ttgd);

                list.add(x);
            }
        } catch (SQLException ex) {
            Logger.getLogger(TrangQuanTri.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(rootPane, "Lỗi kết nối với database!", "Lỗi!", JOptionPane.ERROR_MESSAGE);
        }
    }
    public void sapXep(){
        Collections.sort(list, new Comparator<SinhVien>() {
            @Override
            public int compare(SinhVien o1, SinhVien o2) {
                if(o1.getLop().compareToIgnoreCase(o2.getLop())== 0){
                    return o1.getTenSV().compareToIgnoreCase(o2.getTenSV());
                }
                else
                    return o1.getLop().compareToIgnoreCase(o2.getLop());
            }
        });
    }
    
    public void loadCSDLTimKiem() {
        ResultSet rs = dbconnection.layThongTinSinhVien();
        try {
            while (rs.next()) {
                SinhVien x = new SinhVien();
                x.setMaSV(rs.getString("MASV"));
                x.setTenSV(rs.getString("TENSV"));
                x.setKhoaQL(rs.getString("KHOA"));
                x.setLop(rs.getString("LOP"));

                ThongTinSV ttsv = new ThongTinSV();
                SimpleDateFormat date = new SimpleDateFormat("dd/MM/yyyy");
                String dt = date.format(rs.getDate("NGAYSINH"));
                ttsv.setNgaySinh(dt);
                ttsv.setSdtSV(rs.getString("SDTSV"));
                ttsv.setEmailSV(rs.getString("EMAILSV"));
                ttsv.setQuocTich(rs.getString("QUOCTICH"));
                ttsv.setNoiThuongTru(rs.getString("NOITHUONGTRU"));
                ttsv.setNoiTamTru(rs.getString("NOITAMTRU"));
                ttsv.setDanToc(rs.getString("DANTOC"));

                ttsv.setTonGiao(rs.getString("TONGIAO"));
                ttsv.setSoCCCD(rs.getString("SOCCCD"));
                ttsv.setTenNganHang(rs.getString("TENNGANHANG"));
                ttsv.setStkNganHang(rs.getString("STKNGANHANG"));
                ttsv.setMaBHYT(rs.getString("MABHYT"));
                x.setThongTinSV(ttsv);

                ThongTinGD ttgd = new ThongTinGD();
                ttgd.setHoTenCha(rs.getString("HOTENCHA"));
                ttgd.setNamSinhCha(rs.getInt("NAMSINHCHA"));
                ttgd.setDienThoaiCha(rs.getString("DIENTHOAICHA"));
                ttgd.setNgheNghiepCha(rs.getString("NGHENGHIEPCHA"));
                ttgd.setDiaChiLienHeCha(rs.getString("DIACHILIENHECHA"));
                ttgd.setHoTenMe(rs.getString("HOTENME"));
                ttgd.setNamSinhMe(rs.getInt("NAMSINHME"));
                ttgd.setDienThoaiMe(rs.getString("DIENTHOAIME"));
                ttgd.setNgheNghiepMe(rs.getString("NGHENGHIEPME"));
                ttgd.setDiaChiLienHeMe(rs.getString("DIACHILIENHEME"));
                ttgd.setHoTenChuHo(rs.getString("HOTENCHUHO"));
                x.setThongTinGD(ttgd);

                if (txtMaTenSV.getText().equals(x.getMaSV()) || txtMaTenSV.getText().equals(x.getTenSV())) {
                    list.add(x);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(TrangQuanTri.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    void capNhatSinhVien(SinhVien sv, String matKhau, int selectedRow) {
        try {
            dbconnection.suaSinhVien(sv, matKhau);
            list.set(selectedRow, sv);
            loadTable();
            JOptionPane.showMessageDialog(rootPane, "Sửa thành công!", "Thành công!", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException ex) {
            Logger.getLogger(TrangQuanTri.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(rootPane, "Sửa thất bại! ", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPopUpTblSV = new javax.swing.JPopupMenu();
        jMenuChiTiet = new javax.swing.JMenuItem();
        jMenuXoa = new javax.swing.JMenuItem();
        jMenuSua = new javax.swing.JMenuItem();
        jLabel1 = new javax.swing.JLabel();
        jMaTenSV = new javax.swing.JLabel();
        txtMaTenSV = new javax.swing.JTextField();
        btnTimKiem = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblSinhVien = new javax.swing.JTable();
        btnLoadTable = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuThemSinhVien = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuQuanLyHoatDong = new javax.swing.JMenuItem();
        jMenuXemDanhSachKhenThuong = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMenuQuanLyKyLuat = new javax.swing.JMenuItem();
        jMenuXemDanhSachKyLuat = new javax.swing.JMenuItem();
        DangXuat = new javax.swing.JMenu();

        jPopUpTblSV.setComponentPopupMenu(jPopUpTblSV);

        jMenuChiTiet.setText("Chi tiết");
        jMenuChiTiet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuChiTietActionPerformed(evt);
            }
        });
        jPopUpTblSV.add(jMenuChiTiet);

        jMenuXoa.setText("Xóa");
        jMenuXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuXoaActionPerformed(evt);
            }
        });
        jPopUpTblSV.add(jMenuXoa);

        jMenuSua.setText("Sửa thông tin");
        jMenuSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuSuaActionPerformed(evt);
            }
        });
        jPopUpTblSV.add(jMenuSua);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setText("DANH SÁCH SINH VIÊN");

        jMaTenSV.setText("Mã / Tên sinh viên:");

        btnTimKiem.setText("Tìm kiếm");
        btnTimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimKiemActionPerformed(evt);
            }
        });

        tblSinhVien.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tblSinhVien.setComponentPopupMenu(jPopUpTblSV);
        jScrollPane2.setViewportView(tblSinhVien);

        btnLoadTable.setText("Load Table");
        btnLoadTable.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoadTableActionPerformed(evt);
            }
        });

        jMenu1.setText("Nhập mới sinh viên");

        jMenuThemSinhVien.setText("Thêm sinh viên");
        jMenuThemSinhVien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuThemSinhVienActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuThemSinhVien);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Hoạt động");

        jMenuQuanLyHoatDong.setText("Quản lý hoạt động");
        jMenuQuanLyHoatDong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuQuanLyHoatDongActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuQuanLyHoatDong);

        jMenuXemDanhSachKhenThuong.setText("Xem danh sách khen thưởng");
        jMenuXemDanhSachKhenThuong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuXemDanhSachKhenThuongActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuXemDanhSachKhenThuong);

        jMenuBar1.add(jMenu2);

        jMenu3.setText("Kỷ luật");

        jMenuQuanLyKyLuat.setText("Quản lý kỷ luật");
        jMenuQuanLyKyLuat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuQuanLyKyLuatActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuQuanLyKyLuat);

        jMenuXemDanhSachKyLuat.setText("Xem danh sách kỷ luật");
        jMenuXemDanhSachKyLuat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuXemDanhSachKyLuatActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuXemDanhSachKyLuat);

        jMenuBar1.add(jMenu3);

        DangXuat.setText("Đăng xuất");
        DangXuat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                DangXuatMouseClicked(evt);
            }
        });
        jMenuBar1.add(DangXuat);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(380, 380, 380))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 906, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(241, 241, 241)
                        .addComponent(jMaTenSV)
                        .addGap(18, 18, 18)
                        .addComponent(txtMaTenSV, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnTimKiem)
                        .addGap(18, 18, 18)
                        .addComponent(btnLoadTable)))
                .addContainerGap(34, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jMaTenSV)
                    .addComponent(txtMaTenSV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTimKiem)
                    .addComponent(btnLoadTable))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 307, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32))
        );

        btnLoadTable.getAccessibleContext().setAccessibleName("");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuChiTietActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuChiTietActionPerformed
        // TODO add your handling code here:
        int selectedRow = tblSinhVien.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(null, "Hãy chọn một sinh viên!", "Lỗi!", JOptionPane.ERROR_MESSAGE);
        } else {
            new ChiTietSinhVien(this, rootPaneCheckingEnabled, list.get(selectedRow)).setVisible(rootPaneCheckingEnabled);
        }
    }//GEN-LAST:event_jMenuChiTietActionPerformed

    private void jMenuXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuXoaActionPerformed
        // TODO add your handling code here:
        int selectedRow = tblSinhVien.getSelectedRow();
        try {
            if (selectedRow == -1) {
                throw new Exception("Hãy chọn một dòng!");
            } else {
                int choice = JOptionPane.showConfirmDialog(null, "Bạn chắc chắn muốn xóa sinh viên này?", "Xác nhận", JOptionPane.YES_NO_OPTION);
                if (choice == JOptionPane.YES_OPTION) {
                    dbconnection.xoaSinhVien(list.get(selectedRow).getMaSV());
                    list.remove(selectedRow);
                    loadTable();
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(rootPane, "Xóa thất bại!", "Lỗi!", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(rootPane, ex.getMessage(), "Lỗi!", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jMenuXoaActionPerformed

    private void jMenuThemSinhVienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuThemSinhVienActionPerformed
        // TODO add your handling code here:
        new ThemSinhVien().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jMenuThemSinhVienActionPerformed

    private void btnTimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimKiemActionPerformed
        // TODO add your handling code here:
        dbconnection.getConnection();
        String matensv = txtMaTenSV.getText();
        if (matensv.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập mã sinh viên để tìm kiếm");
        } else if (!dbconnection.kiemTra(matensv) && !dbconnection.kiemTraTenSV(matensv)) {
            JOptionPane.showMessageDialog(this, "Mã sinh viên không tồn tại");
        } else {
            list.removeAll(list);
            loadCSDLTimKiem();
            loadTable();
        }
    }//GEN-LAST:event_btnTimKiemActionPerformed

    private void jMenuQuanLyHoatDongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuQuanLyHoatDongActionPerformed
        // TODO add your handling code here:
        new QuanLyHoatDong().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jMenuQuanLyHoatDongActionPerformed

    private void jMenuXemDanhSachKhenThuongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuXemDanhSachKhenThuongActionPerformed
        // TODO add your handling code here:
        new XemDanhSachKhenThuong().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jMenuXemDanhSachKhenThuongActionPerformed

    private void jMenuQuanLyKyLuatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuQuanLyKyLuatActionPerformed
        // TODO add your handling code here:
        new QuanLyKyLuat().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jMenuQuanLyKyLuatActionPerformed

    private void jMenuXemDanhSachKyLuatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuXemDanhSachKyLuatActionPerformed
        // TODO add your handling code here:
        new XemDanhSachKyLuat().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jMenuXemDanhSachKyLuatActionPerformed
    private void jMenuSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuSuaActionPerformed
        // TODO add your handling code here:
        int selectedRow = tblSinhVien.getSelectedRow();
        try {
            if (selectedRow == -1) {
                throw new Exception("Hãy chọn một dòng!");
            } else {
            new SuaThongTinSinhVien(this, rootPaneCheckingEnabled, list.get(selectedRow), dbconnection.layMatKhau(list.get(selectedRow).getMaSV()), selectedRow).setVisible(rootPaneCheckingEnabled);
            }
            } catch (Exception ex) {
            JOptionPane.showMessageDialog(rootPane, ex.getMessage(), "Lỗi!", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jMenuSuaActionPerformed

    private void DangXuatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_DangXuatMouseClicked
        // TODO add your handling code here:
        int exit = JOptionPane.showConfirmDialog(rootPane, "Bạn có chắc chắn muốn đăng xuất", "Xác nhân đăng xuất", JOptionPane.YES_NO_OPTION);
        if (exit == JOptionPane.YES_OPTION) {
            this.dispose();
            new Dangnhap().setVisible(true);
        }

    }//GEN-LAST:event_DangXuatMouseClicked

    private void btnLoadTableActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoadTableActionPerformed
        // TODO add your handling code here:
        list.removeAll(list);
        loadCSDL();
        loadTable();
        txtMaTenSV.setText("");
    }//GEN-LAST:event_btnLoadTableActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TrangQuanTri.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TrangQuanTri.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TrangQuanTri.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TrangQuanTri.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TrangQuanTri().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu DangXuat;
    private javax.swing.JButton btnLoadTable;
    private javax.swing.JButton btnTimKiem;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jMaTenSV;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuChiTiet;
    private javax.swing.JMenuItem jMenuQuanLyHoatDong;
    private javax.swing.JMenuItem jMenuQuanLyKyLuat;
    private javax.swing.JMenuItem jMenuSua;
    private javax.swing.JMenuItem jMenuThemSinhVien;
    private javax.swing.JMenuItem jMenuXemDanhSachKhenThuong;
    private javax.swing.JMenuItem jMenuXemDanhSachKyLuat;
    private javax.swing.JMenuItem jMenuXoa;
    private javax.swing.JPopupMenu jPopUpTblSV;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tblSinhVien;
    private javax.swing.JTextField txtMaTenSV;
    // End of variables declaration//GEN-END:variables

}
