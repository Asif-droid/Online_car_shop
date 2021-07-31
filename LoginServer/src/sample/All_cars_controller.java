package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import org.w3c.dom.events.MouseEvent;
import util.car;
import util.rqst_class;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class All_cars_controller {
    private Main main;
    @FXML
    private ListView list;
    @FXML
    private TextField selection;

    public void setSelection(ActionEvent event){
        String choice= String.valueOf(list.getSelectionModel().getSelectedItems());
        if(choice.length()<5){
            main.showAlert("Select your chosen car ");
        }
        selection.setText(choice);
    }
    public void go_back(ActionEvent event){
        try {
            main.Show_customer();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void buy_car(ActionEvent event){
        String s=selection.getText();
        int strt=s.indexOf("= ");
        int end=s.indexOf(" maker");
        String txt=s.substring(strt+2,end);
        rqst_class rq=new rqst_class();
        rq.setRqst("buy");
        rq.setText(txt);
        try {
            main.getNetworkUtil().write(rq);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(txt);
    }
    public void setMain(Main main){
        this.main=main;
    }
    public void setlist(ArrayList<car> cars){




        for(car c: cars){
            list.getItems().add(c.toString());
        }
        //new refresh_thread(main);

    }
    public void refresh(String s){
        rqst_class rq=new rqst_class();
        String []s_a=s.split(",");
        if(s_a[0].equalsIgnoreCase("all")){
            rq.setRqst("all");
            rq.setText("customer");
        }
        else if(s_a[0].equalsIgnoreCase("searchbyreg")){
            rq.setRqst(s_a[0]);
            rq.setText(s_a[1]);
        }
        else if(s_a[0].equalsIgnoreCase("searchbymaker")){
            rq.setRqst(s_a[0]);
            rq.setText(s_a[1]+","+s_a[2]);
        }
        //new refresh_thread(main,rq);


    }
}
