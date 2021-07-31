package sample;

import javafx.application.Platform;
import util.LoginDTO;
import util.car;
import util.car_list;
import util.rqst_class;

import java.io.IOException;
import java.util.ArrayList;

public class ReadThread implements Runnable {
    private final Thread thr;
    private final Main main;

    public ReadThread(Main main) {
        this.main = main;
        this.thr = new Thread(this);
        thr.start();
    }

    public void run() {
        try {
            while (true) {
                Object o = main.getNetworkUtil().read();
                if (o != null) {
                    if (o instanceof LoginDTO) {
                        LoginDTO loginDTO = (LoginDTO) o;
                        System.out.println(loginDTO.getUserName());
                        System.out.println(loginDTO.isStatus());
                        // the following is necessary to update JavaFX UI components from user created threads
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                if(loginDTO.getUserName().equalsIgnoreCase("viewer")){
                                    try {
                                        main.Show_customer();
                                    }catch (Exception e){
                                        System.out.println(e);
                                    }
                                }
                                else if (loginDTO.isStatus()) {
                                    try {
                                        main.showHomePage(loginDTO.getUserName());
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }
                                else {
                                    main.showAlert();
                                }

                            }
                        });
                    }
                   else if(o instanceof car_list) {
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                car_list list=(car_list) o;
                                //System.out.println(cars);
                                try {
                                    if(list.getSource().equalsIgnoreCase("customer"))
                                    {
                                        main.Show_all_cars_v(list);
                                    }
                                    else if(list.getSource().equalsIgnoreCase("seller")){
                                        main.show_all_cars_seller(list);

                                    }

                                } catch (IOException e) {
                                    e.printStackTrace();
                                }

                            }
                        });

                    }
                   else if(o instanceof rqst_class){
                       Platform.runLater(new Runnable() {
                           @Override
                           public void run() {
                               rqst_class rq=(rqst_class)o;
                               if(rq.getRqst().equalsIgnoreCase("customer")){
                                   try {
                                       main.customermsg(rq.getText());
                                   } catch (IOException e) {
                                       e.printStackTrace();
                                   }
                               }
                               else if(rq.getRqst().equalsIgnoreCase("seller")){
                                   if(rq.getText().equalsIgnoreCase("updated")){
                                       try {
                                           main.sellermsg("Successfully Updated,Seller");
                                       } catch (IOException e) {
                                           e.printStackTrace();
                                       }
                                   }
                                   else if(rq.getText().equalsIgnoreCase("added")){
                                       try {
                                           main.sellermsg("Successfully Added,Seller");
                                       } catch (IOException e) {
                                           e.printStackTrace();
                                       }
                                   }
                                   else if (rq.getText().equalsIgnoreCase("failed")){
                                       main.showAlert("Adding failed reg no already exist");
                                   }
                                   else if (rq.getText().equalsIgnoreCase("deletefailed")){
                                       main.showAlert("Delete failed reg no does not exist");
                                   }

                                   else if(rq.getText().equalsIgnoreCase("removed")){
                                       try {
                                           main.sellermsg("Successfully Removed,Seller");
                                       } catch (IOException e) {
                                           e.printStackTrace();
                                       }
                                   }
                               }
                           }
                       });
                    }

                }
            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            try {
                main.getNetworkUtil().closeConnection();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}



