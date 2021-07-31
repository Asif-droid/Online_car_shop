package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import org.w3c.dom.events.MouseEvent;
import util.car;

import java.io.IOException;
import java.util.ArrayList;

public class All_cars_seller_controller {
    private Main main;
    @FXML
    private ListView list;
    @FXML
    private TextField selection;
    @FXML
    private TextField reg;
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
    private TextField count;
    public void setSelection(ActionEvent event){
        String choice= String.valueOf(list.getSelectionModel().getSelectedItems());
        selection.setText(choice);
    }
    public void back(ActionEvent event){
        try {
            main.showHomePage("Seller");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void update(ActionEvent event){
        car car=new car();
        String s=selection.getText();
        int strt=s.indexOf("= ");
        int end=s.indexOf(" maker");
        String txt=s.substring(strt+2,end);
        System.out.println(txt);
        car.setReg(txt);
        String smaker=maker.getText();
        String smodel=model.getText();
        String scolor1=color1.getText();
        String scolor2=color2.getText();
        String scolor3=color3.getText();
        String sprice=price.getText();
        String sc=count.getText();
        int price;
        int count;

        if(sprice.length()<2)
           price=0;

        else
           price=Integer.parseInt(sprice);
        System.out.println(price);
        if(sc.length()<1){
           count=0;
        }
        else{
            count=Integer.parseInt(sc);
        }
        car.setRq("edit");

        car.setMaker(smaker);
        car.setModel(smodel);
        car.setColor1(scolor1);
        car.setColor2(scolor2);
        car.setColor3(scolor3);
        car.setPrice(price);
        car.setCount(count);
        System.out.println(car);
        try {
            main.getNetworkUtil().write(car);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public void setMain(Main main){
        this.main=main;
    }
    public void setlist(ArrayList<car> cars){
        for(car c: cars){
            list.getItems().add(c.toString());
        }

    }
}
