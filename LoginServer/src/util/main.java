package util;

import database.connection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class main {
    public static void main(String[] args) {
        connection  data=new connection();
        ResultSet rs;
        car_list list=new car_list();
        List<car> list1=new ArrayList();

        try {
            rs=data.showdata();
            car[] cars=new car[5];
            /*for(int i=0;i<5;i++){
                car c1=new car();
                c1.setMaker("c"+i);
                c1.setModel("cm"+i);
                c1.setReg("cr"+i);
                c1.setPrice(i);
                cars[i]=c1;
                list1.add(c1);
                //System.out.println(c);

            }*/
            while(rs.next()){
                car c=new car();
                System.out.println(rs.getString("reg")+rs.getString("maker"));
                c.setPrice(Integer.parseInt(rs.getString("price")));
                c.setReg(rs.getString("reg"));
                c.setMaker(rs.getString("maker"));
                c.setModel(rs.getString("model"));
                list1.add(c);

            }
            for(car c1: list1){
                System.out.println(c1);
            }
            //System.out.println(list);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
