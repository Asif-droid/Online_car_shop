package database;

import util.car;

import java.sql.ResultSet;
import java.sql.SQLException;

public class main {
    public static void main(String[] args) {
        connection test=new connection();
        ResultSet rs;
        //car car=new car("reg2","renoult","","","","",2500000,5);
        try {
            //test.update(car);
            //test.addcol();
           // test.update_count("reg2");
            //test.removeData("fx3");
            rs=test.showdata();
            //System.out.println("printing");
            while (rs.next()){
                //System.out.println("printing");
                System.out.println(rs.getString("reg")+","+rs.getString("maker")+
                        ","+rs.getString("model")+","+rs.getString("color1")+","
                        +rs.getString("color2")+","+rs.getString("color3")+
                        ","+rs.getString("price")+","+rs.getString("count"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
