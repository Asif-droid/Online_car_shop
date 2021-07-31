package sample;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import util.NetworkUtil;
import util.car;
import util.car_list;

import java.io.IOException;
import java.util.ArrayList;

public class Main extends Application {

    private Stage stage;
    private NetworkUtil networkUtil;

    public Stage getStage() {
        return stage;
    }

    public NetworkUtil getNetworkUtil() {
        return networkUtil;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        stage = primaryStage;
        connectToServer();
        showLoginPage();
    }

    private void connectToServer() throws IOException {
        String serverAddress = "127.0.0.1";
        int serverPort = 33333;
        networkUtil = new NetworkUtil(serverAddress, serverPort);
        new ReadThread(this);
    }

    public void showLoginPage() throws Exception {
        // XML Loading using FXMLLoader
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("login.fxml"));
        Parent root = loader.load();

        // Loading the controller
        LoginController controller = loader.getController();
        controller.setMain(this);

        // Set the primary stage
        stage.setTitle("Login");
        stage.setScene(new Scene(root, 400, 250));
        stage.show();
    }

    public void showHomePage(String userName) throws Exception {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("home.fxml"));
        Parent root = loader.load();

        // Loading the controller
        HomeController controller = loader.getController();
        controller.init(userName);
        controller.setMain(this);

        // Set the primary stage
        stage.setTitle("Home");
        stage.setScene(new Scene(root, 400, 300));
        stage.show();
    }
    public void Show_viewer_page( String name) throws IOException {
        FXMLLoader loader=new FXMLLoader();
        loader.setLocation(getClass().getResource("viewer_page1.fxml"));
        Parent root=loader.load();

        viewer_page1Controller controller=loader.getController();
        controller.set_main(this);
        controller.setName(name);
        stage.setTitle("viewer page");
        stage.setScene(new Scene(root,600,500));
        stage.show();
    }
    public void Show_customer() throws IOException {
        FXMLLoader loader=new FXMLLoader();
        loader.setLocation(getClass().getResource("customer.fxml"));
        Parent root=loader.load();
        Customer_controller controller=loader.getController();
        controller.init(this);
        stage.setTitle("Customer view");
        stage.setScene(new Scene(root,600,400));
        stage.show();

    }
    public void Show_all_cars_v(car_list list) throws IOException {
        FXMLLoader loader=new FXMLLoader();
        loader.setLocation(getClass().getResource("All_cars.fxml"));
        Parent root=loader.load();
        All_cars_controller controller=loader.getController();
        controller.setMain(this);
        controller.setlist(list.getCars());
        controller.refresh(list.getSearch_history());
        stage.setTitle("ALL CARS");
        stage.setScene(new Scene(root,600,400));
        stage.show();

    }
    public void show_all_cars_seller(car_list list) throws IOException {
        FXMLLoader loader=new FXMLLoader();
        loader.setLocation(getClass().getResource("All_cars_seller.fxml"));
        Parent root=loader.load();
        All_cars_seller_controller controller=loader.getController();
        controller.setMain(this);
        controller.setlist(list.getCars());
        stage.setTitle("All Cars");
        stage.setScene(new Scene(root,600,450));
        stage.show();

    }
    public void delete() throws IOException {
        FXMLLoader loader=new FXMLLoader();
        loader.setLocation(getClass().getResource("delete_car.fxml"));
        Parent root=loader.load();
        delete_car controller=loader.getController();
        controller.setMain(this);
        stage.setTitle("Delete");
        stage.setScene(new Scene(root,300,200));
        stage.show();
    }
    public void customermsg(String s) throws IOException {
        FXMLLoader loader=new FXMLLoader();
        loader.setLocation(getClass().getResource("customer_msg.fxml"));
        Parent root=loader.load();
        CustomerMsg controller=loader.getController();
        controller.setMain(this);
        controller.setMsg(s);
        stage.setTitle("Confirmation");
        stage.setScene(new Scene(root,300,200));
        stage.show();
    }
    public void sellermsg(String s) throws IOException {
        FXMLLoader loader=new FXMLLoader();
        loader.setLocation(getClass().getResource("seller_msg.fxml"));
        Parent root=loader.load();
        seller_msg_controller controller=loader.getController();
        controller.setMain(this);
        String[] sa=s.split(",");
        controller.setMsg(sa[0]);
        controller.setName(sa[1]);
        stage.setTitle("Confirmation");
        stage.setScene(new Scene(root,300,200));
        stage.show();
    }

    public void showAlert() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Incorrect Credentials");
        alert.setHeaderText("Incorrect Credentials");
        alert.setContentText("The username and password you provided is not correct.");
        alert.showAndWait();
    }
    public void showAlert(String s) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Incorrect Credentials");
        alert.setHeaderText("Incorrect Credentials");
        alert.setContentText(s);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        // This will launch the JavaFX application
        launch(args);
    }
}
