package server;

import database.connection;
import util.*;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class ReadThreadServer implements Runnable {
    private final Thread thr;
    private final NetworkUtil networkUtil;
    public HashMap<String, String> userMap;
    private connection database;
    private ResultSet rs;
    private static final String filename="src/sample/car_seller.txt";



    public ReadThreadServer(HashMap<String, String> map, NetworkUtil networkUtil) {
        this.userMap = map;
        this.networkUtil = networkUtil;
        database=new connection();
        this.thr = new Thread(this);
        thr.start();
    }
   /* public static void updateHash(HashMap<String,String>map){
        userMap=map;
        file_write();
    }*/

    public void run() {
        //Scanner input=new Scanner(System.in);
        try {

            while (true) {
                Object o = networkUtil.read();
                if (o != null) {
                    if (o instanceof LoginDTO) {
                        LoginDTO loginDTO = (LoginDTO) o;
                        if(loginDTO.getUserName().equalsIgnoreCase("viewer")){
                            loginDTO.setStatus(true);
                            networkUtil.write(loginDTO);

                        }
                        else{
                            String password = userMap.get(loginDTO.getUserName());
                            loginDTO.setStatus(loginDTO.getPassword().equals(password));
                            networkUtil.write(loginDTO);

                        }

                    }
                    if(o instanceof car){
                        car c=(car)o;
                        addcar(c);
                        System.out.println(c);
                    }
                    if(o instanceof rqst_class){
                        rqst_class rq=(rqst_class)o;
                        //System.out.println("good from run if");
                        rqst_handell(rq);

                    }
                }

            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            try {
                networkUtil.closeConnection();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public static void file_write(){
        try{
            BufferedWriter bw=new BufferedWriter(new FileWriter(filename));
            for(String s:Server.userMap.keySet()){
                String input=s+","+Server.userMap.get(s);
                bw.write(input);
                bw.write("\n");
            }
            bw.close();

        }catch (Exception e){
            System.out.println("/////Problems updating database////");
        }

    }

    public void rqst_handell(rqst_class rq){


        if(rq.getRqst().equalsIgnoreCase("all")){
            //System.out.println("good from rqst_handel");
            String defining_viewer;
            if(rq.getText().equalsIgnoreCase("seller")){
                defining_viewer="seller";
            }
            else{
                defining_viewer="customer";
            }


            try {
                car_list list=new car_list();
                rs=database.showdata();
                while(rs.next()){
                    car c=new car();
                    /*System.out.println(rs.getString("reg")+","+rs.getString("maker")+
                            ","+rs.getString("model")+","+rs.getString("color1")+","
                            +rs.getString("color2")+","+rs.getString("color3")+
                            ","+rs.getString("price"));*/
                    c.setReg(rs.getString("reg"));
                    c.setMaker(rs.getString("maker"));
                    c.setModel(rs.getString("model"));
                    c.setColor1(rs.getString("color1"));
                    c.setColor2(rs.getString("color2"));
                    c.setColor3(rs.getString("color3"));
                    c.setPrice(Integer.parseInt(rs.getString("price")));
                    c.setCount(Integer.parseInt(rs.getString("count")));

                    list.addcar(c);

                }
                list.setSource(defining_viewer);
                list.setSearch_history("all,none,none");
                //System.out.println(list);

                networkUtil.write(list);
            } catch (SQLException throwables) {
                System.out.println("here u go");
                throwables.printStackTrace();
            } catch (ClassNotFoundException e) {
                System.out.println("problem");
                e.printStackTrace();
            } catch (IOException e) {
                System.out.println("wrong from network");
                e.printStackTrace();
            }

        }
        else if(rq.getRqst().equalsIgnoreCase("buy")||rq.getRqst().equalsIgnoreCase("delete")){
            String reg=rq.getText();
            rqst_class rqfdbk=new rqst_class();
            boolean has=false;
            try {
                rs=database.showdata();
                while (rs.next()){
                    car cx=new car();
                    cx.setReg(rs.getString("reg"));
                    if(cx.getReg().equals(reg)){
                        has = true;
                        break;
                    }

                }
                if(has){


                    if(rq.getRqst().equalsIgnoreCase("buy")){
                        database.update_count(reg);
                        rqfdbk.setRqst("customer");
                        rqfdbk.setText("Successfully Bought");
                    }
                    else
                    {
                        database.removeData(reg);
                        rqfdbk.setRqst("seller");
                        rqfdbk.setText("removed");
                        //System.out.println("called");
                    }
                }
                else{
                    if(rq.getRqst().equalsIgnoreCase("buy")){
                        rqfdbk.setRqst("customer");
                        rqfdbk.setText("failed");
                    }
                    else
                    {
                        rqfdbk.setRqst("seller");
                        rqfdbk.setText("deletefailed");
                    }

                }


                networkUtil.write(rqfdbk);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        else if(rq.getRqst().equalsIgnoreCase("searchbyreg")){
            String reg=rq.getText();
            try {
                rs=database.showdata(reg);
                car_list list=new car_list();
                while (rs.next()){
                    car c=new car();
                    c.setReg(rs.getString("reg"));
                    c.setMaker(rs.getString("maker"));
                    c.setModel(rs.getString("model"));
                    c.setColor1(rs.getString("color1"));
                    c.setColor2(rs.getString("color2"));
                    c.setColor3(rs.getString("color3"));
                    c.setPrice(Integer.parseInt(rs.getString("price")));
                    c.setCount(Integer.parseInt(rs.getString("count")));
                    list.addcar(c);
                }
                list.setSource("customer");
                list.setSearch_history("searchbyreg,"+reg+",none");
                networkUtil.write(list);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if(rq.getRqst().equalsIgnoreCase("searchbymaker")){
            String s=rq.getText();
            String [] s_a=s.split(",");
            try {
                rs=database.showdata(s_a[0],s_a[1]);
                car_list list=new car_list();
                while (rs.next()){
                    car c=new car();
                    c.setReg(rs.getString("reg"));
                    c.setMaker(rs.getString("maker"));
                    c.setModel(rs.getString("model"));
                    c.setColor1(rs.getString("color1"));
                    c.setColor2(rs.getString("color2"));
                    c.setColor3(rs.getString("color3"));
                    c.setPrice(Integer.parseInt(rs.getString("price")));
                    c.setCount(Integer.parseInt(rs.getString("count")));
                    list.addcar(c);
                }
                list.setSource("customer");
                list.setSearch_history("searchbymaker,"+s_a[0]+","+s_a[1]);
                networkUtil.write(list);

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }


    }
    public void addcar(car c){
        rqst_class rqfdbk=new rqst_class();
        try {
            if(c.getRq().equalsIgnoreCase("edit")){
                database.update(c);
                rqfdbk.setRqst("seller");
                rqfdbk.setText("updated");
            }
            else{
                rs=database.showdata();
                boolean has=false;
                while(rs.next()){
                    car cx=new car();
                    cx.setReg(rs.getString("reg"));
                    if(cx.getReg().equals(c.getReg())){
                        has=true;
                        break;
                    }

                }
                if(!has){
                    database.add(c);
                    //System.out.println(c+"from database");
                    rqfdbk.setRqst("seller");
                    rqfdbk.setText("added");

                }
                else
                {
                    rqfdbk.setRqst("seller");
                    rqfdbk.setText("failed");

                }


            }
            networkUtil.write(rqfdbk);

        } catch (SQLException throwables) {
            System.out.println("from serve add car");
            throwables.printStackTrace();

        } catch (ClassNotFoundException e) {
            System.out.println("from serve add car");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}



