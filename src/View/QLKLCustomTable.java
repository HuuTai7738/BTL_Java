/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Model.HoatDong;
import Model.KyLuat;
import Model.SinhVien;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Personal
 */
public class QLKLCustomTable extends AbstractTableModel{
    String name[] = {"STT","Mã sinh viên","Mã kỷ luật", "Tên kỷ luật", "Điểm trừ"};
    Class classes[] = {String.class,String.class, String.class,String.class,Integer.class};
    ArrayList<KyLuat> list = new ArrayList<>();

    public QLKLCustomTable(ArrayList<KyLuat> dssv) {
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
            case 2 : return list.get(rowIndex).getMaKL();
            case 3 : return list.get(rowIndex).getTenKL();
            case 4 : return list.get(rowIndex).getDiemTru();
            default : return null;
        }
    }

    @Override
    public String getColumnName(int column) {
        return name[column]; //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
