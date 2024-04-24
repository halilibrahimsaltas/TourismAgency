package view;

import business.HotelManager;
import business.PensionManager;
import core.ComboItem;
import core.Helper;
import entity.Hotel;
import entity.Pension;

import javax.swing.*;

public class PensionView extends Layout {
    // Components in the view
    private JPanel container;
    private JPanel pnl_header;
    private JLabel lbl_header;
    private JPanel pnl_body;
    private JLabel lbl_hotel;
    private JComboBox<ComboItem> cmb_hotel_id;
    private JLabel lbl_pension;
    private JTextField fld_pension;
    private JButton btn_save;
    private JButton brn_logout;
    private JComboBox<Pension.type> cmb_pension_type;

    // Business logic manager for Pension operations
    private  final PensionManager pensionManager;

    private  final Pension pension;

    private Hotel hotel;

    private HotelManager hotelManager;

    // Constructor for PensionView
    public PensionView(Pension pension) {
        this.add(container);
        this.pension=pension;
        this.pensionManager=new PensionManager();
        this.hotelManager= new HotelManager();
        this.guiInitilaze(400,400);

        // Set combo box model for pension type
        this.cmb_pension_type.setModel(new DefaultComboBoxModel<>(Pension.type.values()));

        // Populate combo box with hotels
        for (Hotel hotel: this.hotelManager.findAll()){
            this.cmb_hotel_id.addItem( new ComboItem(hotel.getId(), hotel.getName()));
        }

        // If pension exists, set default values in the form fields
        if(this.pension.getId() != 0){
            ComboItem defaultHotel = new ComboItem(this.pension.getHotel().getId(),this.pension.getHotel().getName());
            this.cmb_hotel_id.getModel().setSelectedItem(defaultHotel);
            this.cmb_pension_type.getModel().setSelectedItem(this.pension.getType());
        }
        // Action listener for the save button
        btn_save.addActionListener(e -> {
            if (Helper.isFieldListEmpty(new JTextField[]{})) {
                Helper.showMsg("fill");
            }else{
                // Get the selected hotel from the combo box
                boolean result=false;
                ComboItem selectedHotel = (ComboItem) cmb_hotel_id.getSelectedItem();
                this.pension.setPensionHotelId(selectedHotel.getKey());
                this.pension.setType((Pension.type) cmb_pension_type.getSelectedItem());
                if (this.pension.getId() !=0){
                    result= this.pensionManager.update(this.pension);

                }else {
                    result = this.pensionManager.save(this.pension);
                }
                // Show appropriate message based on the result
                if(result){
                    Helper.showMsg("done");
                    dispose();
                }else {
                    Helper.showMsg("error");
                }
            }

        });

    }
}
