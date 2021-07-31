package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import util.rqst_class;

import java.io.IOException;

public class delete_car {
    private  Main main;

    @FXML
    private TextField selection;
    public void  setMain(Main main){
        this.main=main;
    }
    public void back(ActionEvent event){
        try {
            main.showHomePage("Seller");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void delete(ActionEvent event){
        String s=selection.getText();
        rqst_class rq=new rqst_class();
        rq.setRqst("delete");
        rq.setText(s);
        try {
            main.getNetworkUtil().write(rq);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //System.out.println(txt);
    }
}
