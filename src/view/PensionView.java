package view;

import business.HotelManager;
import business.PensionManager;
import core.ComboItem;
import core.Helper;
import entity.Hotel;
import entity.Pension;

import javax.swing.*;

public class PensionView extends Layout {
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

    private  final PensionManager pensionManager;

    private  final Pension pension;

    private Hotel hotel;

    private HotelManager hotelManager;

    public PensionView(Pension pension) {
        this.add(container);
        this.pension=pension;
        this.pensionManager=new PensionManager();
        this.hotelManager= new HotelManager();
        this.guiInitilaze(400,400);

        this.cmb_pension_type.setModel(new DefaultComboBoxModel<>(Pension.type.values()));

        for (Hotel hotel: this.hotelManager.findAll()){
            this.cmb_hotel_id.addItem( new ComboItem(hotel.getId(), hotel.getName()));
        }

        if(this.pension.getId() != 0){
            ComboItem defaultHotel = new ComboItem(this.pension.getHotel().getId(),this.pension.getHotel().getName());
            this.cmb_hotel_id.getModel().setSelectedItem(defaultHotel);
            this.cmb_pension_type.getModel().setSelectedItem(this.pension.getType());
        }
        btn_save.addActionListener(e -> {
            if (Helper.isFieldListEmpty(new JTextField[]{})) {
                Helper.showMsg("fill");
            }else{
                boolean result=false;
                ComboItem selectedHotel = (ComboItem) cmb_hotel_id.getSelectedItem();
                this.pension.setPensionHotelId(selectedHotel.getKey());
                this.pension.setType((Pension.type) cmb_pension_type.getSelectedItem());
                if (this.pension.getId() !=0){
                    result= this.pensionManager.update(this.pension);

                }else {
                    result = this.pensionManager.save(this.pension);
                }
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
