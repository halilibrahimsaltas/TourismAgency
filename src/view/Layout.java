package view;

import core.Helper;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class Layout extends JFrame {

    // Method to initialize the GUI with given width and height
    public void guiInitilaze(int width , int height){
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setTitle(("Tourism Agency"));
        this.setSize(width,height);
        this.setLocation(Helper.getLocationPoint("x",this.getSize()),Helper.getLocationPoint("y",this.getSize()));
        this.setVisible(true);
    }

    // Method to create a table with given model, columns, and rows
    public void createTable(DefaultTableModel model, JTable table, Object[] columns, ArrayList<Object[]> rows){
        model.setColumnIdentifiers(columns);
        table.setModel(model);
        table.getTableHeader().setReorderingAllowed(false);
        table.getColumnModel().getColumn(0).setMaxWidth(75);
        table.setEnabled(false);

        // Set maximum width for the first column (assuming it's the ID column)
        DefaultTableModel clearModel = (DefaultTableModel)  table.getModel();
        clearModel.setRowCount(0);

        if(rows == null){
            rows= new ArrayList<>();
        }

        // Add rows to the table model
        for ( Object[] row : rows){
            model.addRow(row);
        }
    }

    // Method to get the ID of the selected row in a table
    public  int getTableSelectedRow(JTable table, int index){

        return Integer.parseInt(table.getValueAt(table.getSelectedRow(),0).toString());

    }

    // Method to handle row selection in a table
    public void tableRowSelected(JTable table){
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int selected_row =table.rowAtPoint(e.getPoint());
                table.setRowSelectionInterval(selected_row,selected_row);
            }
        });
    }

}