package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import util.rqst_class;

import java.io.IOException;

public class HomeController {
    private String name;

    private Main main;

    @FXML
    private Label message;

    @FXML
    private ImageView image;

    @FXML
    private Button button;

    public void init(String msg) {
        name=msg;
        message.setText(msg);
        Image img = new Image(Main.class.getResourceAsStream("1.png"));
        image.setImage(img);
    }
    public void view_all_car(ActionEvent event){
        rqst_class rq=new rqst_class();
        rq.setRqst("all");
        rq.setText("seller");
        try {
            main.getNetworkUtil().write(rq);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("from customer controller ");
        }
    }
    public void add_car(ActionEvent event){
        try {
            main.Show_viewer_page(name);
        }
        catch (Exception e){

        }

    }
    public void edit_car(ActionEvent event){

    }
    public void delete_car(ActionEvent event){
        try {
            main.delete();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void logoutAction(ActionEvent event) {
        try {
            main.showLoginPage();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void setMain(Main main) {
        this.main = main;
    }

}
