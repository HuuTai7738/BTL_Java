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
        return list.size();
        
    }

    @Override
    public int getColumnCount() {
        return name.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
         switch (columnIndex) {
            case 0: return rowIndex+1;
            case 1 : return list.get(rowIndex).getMaSV();
            case 2 : return list.get(rowIndex).getTenSV();
            case 3 : return list.get(rowIndex).getKhoaQL();
            case 4 : return list.get(rowIndex).getLop();
            default : return null;
        }
    }

    @Override
    public String getColumnName(int column) {
        return name[column]; //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
