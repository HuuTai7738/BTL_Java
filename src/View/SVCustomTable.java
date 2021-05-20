/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Model.SinhVien;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Personal
 */
public class SVCustomTable extends AbstractTableModel{
    String name[] = {"STT","Mã Sinh Viên","Tên Sinh Viên", "Khoa", "Lớp"};
    Class classes[] = {String.class,String.class, String.class,String.class,String.class};
    ArrayList<SinhVien> list = new ArrayList<>();

    public SVCustomTable(ArrayList<SinhVien> dssv) {
        list = dssv;
    }
    
    @Override
    public int getRowCount() {
        return name.length;
    }

    @Override
    public int getColumnCount() {
        return list.size();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return switch (columnIndex) {
            case 0 -> rowIndex;
            case 1 -> list.get(rowIndex).getMaSV();
            case 2 -> list.get(rowIndex).getTenSV();
            case 3 -> list.get(rowIndex).getKhoaQL();
            case 4 -> list.get(rowIndex).getLop();
            default -> null;
        };
    }

    @Override
    public String getColumnName(int column) {
        return name[column]; //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
