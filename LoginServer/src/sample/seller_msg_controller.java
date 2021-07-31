package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.io.IOException;

public class seller_msg_controller {

    private String name;
    private Main main;

    @FXML
    private Label msg;
    public void setMain(Main main){
        this.main=main;
    }
    public void setMsg(String ms){
        msg.setText(ms);
    }
    public void setName(String name){
        this.name=name;
    }
    public void back(ActionEvent event){
        try {
            main.showHomePage(name);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
