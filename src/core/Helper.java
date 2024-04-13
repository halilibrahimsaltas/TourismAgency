package core;

import javax.swing.*;
import java.awt.*;

public class Helper {
    public static void setTheme(){

        for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
            if ("Nimbus".equals(info.getName())) {
                try {
                    UIManager.setLookAndFeel(info.getClassName());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            }
        }
    }
    public  static void showMsg(String str){
        String msg;
        String title;
        switch (str) {
            case "fill" -> {
                msg = "Fill All Area !";
                title = "Error!";
            }
            case "done" -> {
                msg = "Done !";
                title = "Result";
            }
            case "notFound" -> {
                msg = "Not Found !";
                title = "Not Found";
            }
            case "error" -> {
                msg = "Wrong Order !";
                title = "Error!";
            }
            default -> {
                msg = str;
                title = "Message";
            }
        }
        JOptionPane.showMessageDialog(null,msg,title,JOptionPane.INFORMATION_MESSAGE);

    }

    public  static boolean isFieldEmpty(JTextField field){
        return field.getText().trim().isEmpty();
    }

    public static boolean isFieldListEmpty(JTextField[] filedList){
        for (JTextField field : filedList){
            if (isFieldEmpty(field)) return true;
        }
        return false;
    }
    public static  int getLocationPoint(String type , Dimension size){
        switch (type){
            case "x":
                return (Toolkit.getDefaultToolkit().getScreenSize().width - size.width) / 2;
            case "y":
                return (Toolkit.getDefaultToolkit().getScreenSize().height - size.height) / 2;
            default:
                return  0;
        }
    }

    public  static  boolean confirm ( String str){
        String msg;
        if(str.equals("sure")){
            msg="Are you sure you want to do this?";
        }else {
            msg = str;
        }

        return JOptionPane.showConfirmDialog(null,msg,"Are you sure ?",JOptionPane.YES_NO_CANCEL_OPTION) ==0;
    }

}