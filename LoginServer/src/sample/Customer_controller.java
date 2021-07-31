package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import util.rqst_class;

import java.io.IOException;

public class Customer_controller {
    private Main main;

    @FXML
    private ImageView im;
    @FXML
    private TextField maker_id;
    @FXML
    private TextField model_id;
    @FXML
    private TextField reg;
    private refresh_thread t;
    public void init(Main main) {
        this.main=main;
        im.setImage(new Image(Main.class.getResourceAsStream("showroom.jpg")));
    }
    public void view_car(ActionEvent event){
        rqst_class rq=new rqst_class();
        rq.setRqst("all");
        rq.setText("customer");
        //t=new refresh_thread(main,rq);
         try{
            main.getNetworkUtil().write(rq);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("from customer controller ");
        }
    }
    public void src_reg(ActionEvent event){
        rqst_class rq=new rqst_class();
        rq.setRqst("searchbyreg");
        String g_reg=reg.getText();
        if(g_reg.length()<2){
            main.showAlert("Registration can not be empty");
        }
        else{
            rq.setText(g_reg);

            try {
                main.getNetworkUtil().write(rq);

            }catch (IOException e) {
                e.printStackTrace();
            }
            //t=new refresh_thread(main,rq);

        }


    }
    public void src_maker(ActionEvent event){
        String maker=maker_id.getText();
        String model=model_id.getText();
        maker=maker.toLowerCase();
        model=model.toLowerCase();
        if(maker.length()<2||model.length()<2){
            try{
                main.showAlert("maker or model can not be empty ");
            }catch (Exception e){

            }

        }
        else {
            rqst_class rq=new rqst_class();
            rq.setRqst("searchbymaker");
            rq.setText(maker+","+model);
            try
            {
                main.getNetworkUtil().write(rq);
            }catch (Exception e){
                System.out.println("network issue from customer"+e);
            }
        }
    }

}
