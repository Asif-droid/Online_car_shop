package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.AccessibleAction;
import javafx.scene.control.Label;

import java.io.IOException;

public class CustomerMsg {
    private Main main;

    @FXML
    private Label msg;
    public void setMain(Main main){
        this.main=main;
    }
    public void setMsg(String ms){
        msg.setText(ms);
    }
    public void back(ActionEvent event){
        try {
            main.Show_customer();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
