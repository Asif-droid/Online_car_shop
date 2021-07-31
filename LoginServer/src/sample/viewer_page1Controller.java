package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import util.car;

import java.io.IOException;

public class viewer_page1Controller {
    private String name;

    private  Main main;
    @FXML
    private TextField maker;
    @FXML
    private TextField model;
    @FXML
    private TextField color1;
    @FXML
    private TextField color2;
    @FXML
    private TextField color3;
    @FXML
    private TextField price;
    @FXML
    private Label username;
    @FXML
    private TextField reg;
    @FXML
    private TextField count;


    public void send(ActionEvent event){
        car c=new car();
        c.setReg(reg.getText());
        String smaker=maker.getText();
        smaker=smaker.toLowerCase();
        c.setMaker(smaker);
        String smodel=model.getText();
        smodel=smodel.toLowerCase();
        c.setModel(smodel);
        c.setColor1(color1.getText());
        c.setColor2(color2.getText());
        c.setColor3(color3.getText());
        c.setPrice(Integer.parseInt(price.getText()));
        c.setCount(Integer.parseInt(count.getText()));
        c.setRq("add");
        System.out.println(c);
        try {
            main.getNetworkUtil().write(c);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public void setName(String name){
        this.name=name;
        username.setText(name);
    }
    public void back() throws Exception {
        main.showHomePage(name);
    }
    void set_main(Main main){this.main=main;

    }
}
