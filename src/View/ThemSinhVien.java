package View;

import Controllers.DBConnection;
import Model.SinhVien;
import Model.TaiKhoan;
import Model.ThongTinGD;
import Model.ThongTinSV;
import java.awt.HeadlessException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.JTextComponent;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Admin
 */
public class ThemSinhVien extends javax.swing.JFrame {

    /**
     * Creates new form Them
     */
    ArrayList<SinhVien> listSV;
    Workbook wb;
    Sheet sheet;
    DBConnection conn = new DBConnection();

    public ThemSinhVien() {
        initComponents();
        conn.getConnection();
        this.setLocationRelativeTo(null);
        this.setTitle("Nhập mới sinh viên");
    }

    public void loadTable() {
        tblBangDuLieu.setModel(new SVCustomTable(listSV));
    }

    public void xoaTrang() {
        txtMaSV.requestFocus();
        txtMaSV.setText("");
        txtTenSV.setText("");
        txtNgaySinh.setText("");
        txtKhoa.setText("");
        txtLop.setText("");
        txtQuocTich.setText("");
        txtDanToc.setText("");
        txtTonGiao.setText("");
        txtSoCCCD.setText("");
        txtEmail.setText("");
        txtSoDienThoai.setText("");
        txtNoiThuongTru.setText("");
        txtNoiTamTru.setText("");
        txtMaBHYT.setText("");
        txtTenNganHang.setText("");
        txtSoTaiKhoan.setText("");
        txtHoTenCha.setText("");
        txtNamSinhCha.setText("");
        txtNgheNghiepCha.setText("");
        txtDiaChiCha.setText("");
        txtSDTCha.setText("");
        txtHoTenMe.setText("");
        txtNamSinhMe.setText("");
        txtNgheNghiepMe.setText("");
        txtDiaChiMe.setText("");
        txtSDTMe.setText("");
        txtHoTenChuHo.setText("");
        txtTenTaiKhoan.setText("");
        txtMatKhau.setText(""); 
    }

    public void docDSSV(String filename) throws FileNotFoundException, IOException {
        //doc file 
        listSV = new ArrayList<>();
        FileInputStream file = new FileInputStream(filename);
        HSSFWorkbook workbook = new HSSFWorkbook(file);
        HSSFSheet sheets = workbook.getSheetAt(0);

        Iterator<Row> rowIterator = sheets.iterator();
        rowIterator.next();
        while (rowIterator.hasNext()) {
            Row rows = rowIterator.next();
            SinhVien sv = new SinhVien();
            TaiKhoan tk = new TaiKhoan();
            SimpleDateFormat date = new SimpleDateFormat("dd/MM/yyyy");
            try {
                double ma = rows.getCell(1).getNumericCellValue();
                Integer masv = (int) ma;
                if (!conn.kiemTra(masv.toString())) {
                    sv.setMaSV(masv.toString());
                    sv.setTenSV(rows.getCell(2).getStringCellValue());
                    sv.setKhoaQL(rows.getCell(3).getStringCellValue());
                    sv.setLop(rows.getCell(4).getStringCellValue());

                    ThongTinSV ttsv = new ThongTinSV();
                    date.parse(rows.getCell(5).getStringCellValue());
                    ttsv.setNgaySinh(rows.getCell(5).getStringCellValue());
                    ttsv.setSdtSV(rows.getCell(6).getStringCellValue());
                    ttsv.setEmailSV(rows.getCell(7).getStringCellValue());
                    ttsv.setQuocTich(rows.getCell(8).getStringCellValue());
                    ttsv.setNoiThuongTru(rows.getCell(9).getStringCellValue());
                    ttsv.setNoiTamTru(rows.getCell(10).getStringCellValue());
                    ttsv.setDanToc(rows.getCell(11).getStringCellValue());
                    ttsv.setTonGiao(rows.getCell(12).getStringCellValue());
                    ttsv.setSoCCCD(rows.getCell(13).getStringCellValue());
                    ttsv.setTenNganHang(rows.getCell(14).getStringCellValue());
                    ttsv.setStkNganHang(rows.getCell(15).getStringCellValue());
                    ttsv.setMaBHYT(rows.getCell(16).getStringCellValue());
                    sv.setThongTinSV(ttsv);

                    ThongTinGD ttgd = new ThongTinGD();
                    ttgd.setHoTenCha(rows.getCell(17).getStringCellValue());
                    double nscha = rows.getCell(18).getNumericCellValue();
                    Integer cha = (int) nscha;
                    ttgd.setNamSinhCha(Integer.parseInt(cha.toString()));
                    ttgd.setDienThoaiCha(rows.getCell(19).getStringCellValue());
                    ttgd.setNgheNghiepCha(rows.getCell(20).getStringCellValue());
                    ttgd.setDiaChiLienHeCha(rows.getCell(21).getStringCellValue());
                    ttgd.setHoTenMe(rows.getCell(22).getStringCellValue());
                    double nsme = rows.getCell(23).getNumericCellValue();
                    Integer me = (int) nsme;
                    ttgd.setNamSinhMe(Integer.parseInt(me.toString()));
                    ttgd.setDienThoaiMe(rows.getCell(24).getStringCellValue());
                    ttgd.setNgheNghiepMe(rows.getCell(25).getStringCellValue());
                    ttgd.setDiaChiLienHeMe(rows.getCell(26).getStringCellValue());
                    ttgd.setHoTenChuHo(rows.getCell(27).getStringCellValue());
                    sv.setThongTinGD(ttgd);

                    tk.setMaSV(masv.toString());
                    tk.setMatKhau(rows.getCell(28).getStringCellValue());

                    conn.themSinhVien(sv, tk);
                    listSV.add(sv);
                } else {
                    JOptionPane.showMessageDialog(this, "Sinh viên có mã " + masv.toString() + " đã tồn tại!", "Lỗi!", JOptionPane.ERROR_MESSAGE);
                }
            } catch (ParseException ex) {
                JOptionPane.showMessageDialog(rootPane, "Ngày không hợp lệ(DD/MM/YYYY)", "Lỗi!", JOptionPane.ERROR_MESSAGE);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(rootPane, "Định dạng năm sinh của cha / mẹ không đúng!", "Lỗi!", JOptionPane.ERROR_MESSAGE);
            } catch (SQLException | HeadlessException e) {
                JOptionPane.showMessageDialog(rootPane, e.getMessage(), "Lỗi!", JOptionPane.ERROR_MESSAGE);
            }
        }
        file.close();
        if(listSV.size() > 0){
            loadTable();
            JOptionPane.showMessageDialog(rootPane, "Thêm sinh viên thành công!", "Thông báo!", JOptionPane.INFORMATION_MESSAGE);
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

        lblTitle = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtMaSV = new javax.swing.JTextField();
        txtTenSV = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtNgaySinh = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtKhoa = new javax.swing.JTextField();
        txtLop = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtQuocTich = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtDanToc = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txtSoCCCD = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        txtNoiThuongTru = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        txtNoiTamTru = new javax.swing.JTextField();
        txtTonGiao = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        txtHoTenCha = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        txtSoTaiKhoan = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        txtTenNganHang = new javax.swing.JTextField();
        txtNamSinhCha = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        txtNgheNghiepCha = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        txtDiaChiCha = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        txtSDTCha = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        txtHoTenMe = new javax.swing.JTextField();
        txtNamSinhMe = new javax.swing.JTextField();
        txtNgheNghiepMe = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel26 = new javax.swing.JLabel();
        txtDiaChiMe = new javax.swing.JTextField();
        jLabel27 = new javax.swing.JLabel();
        txtSDTMe = new javax.swing.JTextField();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        lableTK = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        txtMatKhau = new javax.swing.JTextField();
        jSeparator3 = new javax.swing.JSeparator();
        btnThem = new javax.swing.JButton();
        btnQuayLai = new javax.swing.JButton();
        txtHoTenChuHo = new javax.swing.JTextField();
        jLabel33 = new javax.swing.JLabel();
        txtSoDienThoai = new javax.swing.JTextField();
        jLabel34 = new javax.swing.JLabel();
        txtMaBHYT = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblBangDuLieu = new javax.swing.JTable();
        jLabel35 = new javax.swing.JLabel();
        btnChonFile = new javax.swing.JButton();
        lblDuongDan = new javax.swing.JLabel();
        txtTenTaiKhoan = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        lblTitle.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        lblTitle.setText("THÊM THÔNG TIN SINH VIÊN");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("Thông tin cơ bản");

        jLabel3.setText("Mã sinh viên:");

        txtMaSV.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtMaSVCaretUpdate(evt);
            }
        });

        jLabel4.setText("Tên sinh viên:");

        jLabel5.setText("Ngày sinh:");

        jLabel6.setText("Khoa:");

        jLabel7.setText("Lớp:");

        jLabel8.setText("Quốc tịch:");

        jLabel9.setText("Dân tộc:");

        jLabel10.setText("Số CCCD:");

        jLabel11.setText("Email:");

        jLabel12.setText("Nơi thường trú:");

        jLabel13.setText("Nơi tạm trú:");

        jLabel14.setText("Tôn giáo:");

        jLabel15.setText("Tên ngân hàng:");

        jLabel16.setText("Số tài khoản:");

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel17.setText("Thông tin gia đình");

        jLabel18.setText("Họ tên cha:");

        jLabel19.setText("Năm sinh:");

        jLabel20.setText("Nghề nghiệp:");

        jLabel21.setText("Địa chỉ:");

        jLabel22.setText("Số điện thoại:");

        jLabel23.setText("Họ tên mẹ:");

        jLabel24.setText("Năm sinh:");

        jLabel25.setText("Nghề nghiệp:");

        jLabel26.setText("Địa chỉ:");

        jLabel27.setText("Số điện thoại:");

        jLabel28.setText("Họ tên chủ hộ:");

        jLabel29.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel29.setText("Tài khoản");

        lableTK.setText("Tên tài khoản:");

        jLabel31.setText("Mật khẩu:");

        btnThem.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnThem.setText("Thêm");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        btnQuayLai.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnQuayLai.setText("Quay lại");
        btnQuayLai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnQuayLaiActionPerformed(evt);
            }
        });

        jLabel33.setText("Số điện thoại:");

        jLabel34.setText("Mã BHYT:");

        tblBangDuLieu.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "STT", "Mã sinh viên", "Họ tên", "Khoa", "Lớp"
            }
        ));
        jScrollPane1.setViewportView(tblBangDuLieu);
        if (tblBangDuLieu.getColumnModel().getColumnCount() > 0) {
            tblBangDuLieu.getColumnModel().getColumn(0).setMinWidth(50);
            tblBangDuLieu.getColumnModel().getColumn(0).setMaxWidth(50);
            tblBangDuLieu.getColumnModel().getColumn(1).setMinWidth(150);
            tblBangDuLieu.getColumnModel().getColumn(1).setMaxWidth(150);
        }

        jLabel35.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel35.setText("Nhập theo danh sách:");

        btnChonFile.setText("Chọn file");
        btnChonFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChonFileActionPerformed(evt);
            }
        });

        lblDuongDan.setText("Vui lòng chọn file");

        txtTenTaiKhoan.setEnabled(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 357, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(257, 257, 257))
            .addGroup(layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lableTK)
                                .addGap(28, 28, 28)
                                .addComponent(txtTenTaiKhoan, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel17)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel21)
                                    .addComponent(jLabel26))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(265, 265, 265)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel27)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jLabel22)
                                                .addComponent(jLabel19)
                                                .addComponent(jLabel24)))
                                        .addGap(18, 18, 18)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(txtSDTMe, javax.swing.GroupLayout.DEFAULT_SIZE, 198, Short.MAX_VALUE)
                                            .addComponent(txtNamSinhCha)
                                            .addComponent(txtSDTCha)
                                            .addComponent(txtNamSinhMe))
                                        .addGap(34, 34, 34)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel20)
                                            .addComponent(jLabel25))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtNgheNghiepCha, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtNgheNghiepMe, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(267, 267, 267)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel31)
                                                .addGap(35, 35, 35)
                                                .addComponent(txtMatKhau, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addComponent(lblDuongDan)))))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(txtDiaChiMe, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtDiaChiCha, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel18)
                                    .addGap(40, 40, 40)
                                    .addComponent(txtHoTenCha, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel23)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtHoTenMe, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel28)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtHoTenChuHo, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(jLabel35)
                                .addGap(18, 18, 18)
                                .addComponent(btnChonFile))
                            .addComponent(jSeparator3, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel29, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator2)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel6)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel8))
                                    .addComponent(jLabel10)
                                    .addComponent(jLabel12)
                                    .addComponent(jLabel15))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtMaSV, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtKhoa, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtQuocTich, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtSoCCCD, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtNoiThuongTru, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtTenNganHang, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(26, 26, 26)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel9)
                                    .addComponent(jLabel11)
                                    .addComponent(jLabel13)
                                    .addComponent(jLabel16))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtSoTaiKhoan, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtLop, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                .addComponent(txtEmail, javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(txtTenSV, javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(txtDanToc, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addComponent(txtNoiTamTru, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(39, 39, 39)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel34)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(txtMaBHYT, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jLabel5)
                                                    .addComponent(jLabel14))
                                                .addGap(19, 19, 19)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(txtTonGiao, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(txtNgaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel33)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(txtSoDienThoai)))))))
                        .addGap(0, 30, Short.MAX_VALUE))))
            .addGroup(layout.createSequentialGroup()
                .addGap(267, 267, 267)
                .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(269, 269, 269)
                .addComponent(btnQuayLai)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTitle)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtMaSV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTenSV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(txtNgaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtKhoa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(txtLop, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtQuocTich, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(jLabel9)
                    .addComponent(txtDanToc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14)
                    .addComponent(txtTonGiao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSoCCCD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10)
                    .addComponent(jLabel11)
                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel33)
                    .addComponent(txtSoDienThoai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNoiThuongTru, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12)
                    .addComponent(jLabel13)
                    .addComponent(txtNoiTamTru, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel34)
                    .addComponent(txtMaBHYT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTenNganHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15)
                    .addComponent(jLabel16)
                    .addComponent(txtSoTaiKhoan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel17)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtHoTenCha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18)
                    .addComponent(txtNamSinhCha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel19)
                    .addComponent(jLabel20)
                    .addComponent(txtNgheNghiepCha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtDiaChiCha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel22)
                    .addComponent(txtSDTCha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel21))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtHoTenMe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel23)
                    .addComponent(jLabel24)
                    .addComponent(txtNamSinhMe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel25)
                    .addComponent(txtNgheNghiepMe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSDTMe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel27)
                    .addComponent(jLabel26)
                    .addComponent(txtDiaChiMe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtHoTenChuHo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel28))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel29)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTenTaiKhoan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lableTK)
                    .addComponent(jLabel31)
                    .addComponent(txtMatKhau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel35)
                    .addComponent(btnChonFile)
                    .addComponent(lblDuongDan))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThem)
                    .addComponent(btnQuayLai))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnChonFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChonFileActionPerformed
        // TODO add your handling code here:
        FileNameExtensionFilter excelFilter = new FileNameExtensionFilter("Excel", "xls", "xlsx");
        JFileChooser file = new JFileChooser();
        file.setFileFilter(excelFilter);
        file.setMultiSelectionEnabled(false);
        int x = file.showDialog(this, "Chọn file");
        if (x == JFileChooser.APPROVE_OPTION) {
            File f = file.getSelectedFile();
            lblDuongDan.setText(file.getSelectedFile().getAbsolutePath());
            String duongdan = f.toString();
            try {
                docDSSV(duongdan);
                loadTable();
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(rootPane, "Thêm sinh viên không thành công!", "Lỗi!", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_btnChonFileActionPerformed

    private void btnQuayLaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnQuayLaiActionPerformed
        // TODO add your handling code here:
        new TrangQuanTri().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnQuayLaiActionPerformed

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        // TODO add your handling code here:
        SinhVien sv = new SinhVien();
        TaiKhoan tk = new TaiKhoan();
        SimpleDateFormat date = new SimpleDateFormat("dd/MM/yyyy");
        JTextComponent text[] = {txtMaSV, txtMaSV, txtTenSV, txtNgaySinh, txtKhoa, txtLop, txtQuocTich, txtDanToc, txtTonGiao, txtSoCCCD, txtEmail, txtSoDienThoai, txtNoiThuongTru, txtNoiTamTru, txtMaBHYT, txtTenNganHang, txtSoTaiKhoan, txtHoTenCha, txtNamSinhCha, txtNgheNghiepCha, txtDiaChiCha, txtSDTCha, txtHoTenMe, txtNamSinhMe, txtNgheNghiepMe, txtDiaChiMe, txtSDTMe, txtHoTenChuHo, txtTenTaiKhoan, txtMatKhau};
        try {
            for (JTextComponent txt : text) {
                if (txt.getText().trim().isEmpty()) {
                    throw new Exception("Không được để trống!");
                }
            }
            if (!conn.kiemTra(txtMaSV.getText())) {
                sv.setMaSV(txtMaSV.getText());
                sv.setTenSV(txtTenSV.getText());
                sv.setKhoaQL(txtKhoa.getText());
                sv.setLop(txtLop.getText());

                ThongTinSV ttsv = new ThongTinSV();
                date.parse(txtNgaySinh.getText());
                ttsv.setNgaySinh(txtNgaySinh.getText());

                ttsv.setSdtSV(txtSoDienThoai.getText());
                ttsv.setEmailSV(txtEmail.getText());
                ttsv.setQuocTich(txtQuocTich.getText());
                ttsv.setNoiThuongTru(txtNoiThuongTru.getText());
                ttsv.setNoiTamTru(txtNoiTamTru.getText());
                ttsv.setDanToc(txtDanToc.getText());
                ttsv.setTonGiao(txtTonGiao.getText());
                ttsv.setSoCCCD(txtSoCCCD.getText());
                ttsv.setTenNganHang(txtTenNganHang.getText());
                ttsv.setStkNganHang(txtSoTaiKhoan.getText());
                ttsv.setMaBHYT(txtMaBHYT.getText());
                sv.setThongTinSV(ttsv);

                ThongTinGD ttgd = new ThongTinGD();
                ttgd.setHoTenCha(txtHoTenCha.getText());
                ttgd.setNamSinhCha(Integer.parseInt(txtNamSinhCha.getText()));
                ttgd.setDienThoaiCha(txtSDTCha.getText());
                ttgd.setNgheNghiepCha(txtNgheNghiepCha.getText());
                ttgd.setDiaChiLienHeCha(txtDiaChiCha.getText());
                ttgd.setHoTenMe(txtHoTenMe.getText());
                ttgd.setNamSinhMe(Integer.parseInt(txtNamSinhMe.getText()));
                ttgd.setDienThoaiMe(txtSDTMe.getText());
                ttgd.setNgheNghiepMe(txtNgheNghiepMe.getText());
                ttgd.setDiaChiLienHeMe(txtDiaChiMe.getText());
                ttgd.setHoTenChuHo(txtHoTenChuHo.getText());
                sv.setThongTinGD(ttgd);

                tk.setMaSV(txtMaSV.getText());
                tk.setMatKhau(txtMatKhau.getText());

                try {
                    conn.themSinhVien(sv, tk);
                    listSV = new ArrayList<>();
                    listSV.add(sv);
                    loadTable();
                    xoaTrang();
                    JOptionPane.showMessageDialog(this, "Thêm sinh viên thành công!", "Thông báo!", JOptionPane.INFORMATION_MESSAGE);
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(this, "Lỗi cơ sở dữ liệu", "Lỗi!", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Mã sinh viên này đã tồn tại", "Lỗi!", JOptionPane.ERROR_MESSAGE);
            }
        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(rootPane, "Ngày không hợp lệ(DD/MM/YYYY)", "Lỗi!", JOptionPane.ERROR_MESSAGE);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(rootPane, "Định dạng năm sinh của cha / mẹ không đúng!", "Lỗi!", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(rootPane, ex.getMessage(), "Lỗi!", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnThemActionPerformed

    private void txtMaSVCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtMaSVCaretUpdate
        // TODO add your handling code here:
        txtTenTaiKhoan.setText(txtMaSV.getText());
    }//GEN-LAST:event_txtMaSVCaretUpdate

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
            java.util.logging.Logger.getLogger(ThemSinhVien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ThemSinhVien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ThemSinhVien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ThemSinhVien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ThemSinhVien().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnChonFile;
    private javax.swing.JButton btnQuayLai;
    private javax.swing.JButton btnThem;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JLabel lableTK;
    private javax.swing.JLabel lblDuongDan;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JTable tblBangDuLieu;
    private javax.swing.JTextField txtDanToc;
    private javax.swing.JTextField txtDiaChiCha;
    private javax.swing.JTextField txtDiaChiMe;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtHoTenCha;
    private javax.swing.JTextField txtHoTenChuHo;
    private javax.swing.JTextField txtHoTenMe;
    private javax.swing.JTextField txtKhoa;
    private javax.swing.JTextField txtLop;
    private javax.swing.JTextField txtMaBHYT;
    private javax.swing.JTextField txtMaSV;
    private javax.swing.JTextField txtMatKhau;
    private javax.swing.JTextField txtNamSinhCha;
    private javax.swing.JTextField txtNamSinhMe;
    private javax.swing.JTextField txtNgaySinh;
    private javax.swing.JTextField txtNgheNghiepCha;
    private javax.swing.JTextField txtNgheNghiepMe;
    private javax.swing.JTextField txtNoiTamTru;
    private javax.swing.JTextField txtNoiThuongTru;
    private javax.swing.JTextField txtQuocTich;
    private javax.swing.JTextField txtSDTCha;
    private javax.swing.JTextField txtSDTMe;
    private javax.swing.JTextField txtSoCCCD;
    private javax.swing.JTextField txtSoDienThoai;
    private javax.swing.JTextField txtSoTaiKhoan;
    private javax.swing.JTextField txtTenNganHang;
    private javax.swing.JTextField txtTenSV;
    private javax.swing.JTextField txtTenTaiKhoan;
    private javax.swing.JTextField txtTonGiao;
    // End of variables declaration//GEN-END:variables
}
