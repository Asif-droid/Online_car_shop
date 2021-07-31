package database;

import util.car;

import java.sql.*;

public class connection{
    private static Connection con;
    private static boolean hasdata=false;
    public ResultSet showdata() throws SQLException, ClassNotFoundException {
        if(con==null){
            getConnection();
        }
        Statement statement=con.createStatement();
        ResultSet resultSet=statement.executeQuery("SELECT * from CARS");
        return resultSet;
    }
    public void getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("org.sqlite.JDBC");
        con= DriverManager.getConnection("jdbc:sqlite:cars.db");
        initialise();
    }

    private void initialise() throws SQLException {
        if(!hasdata){
            hasdata=true;
            Statement st=con.createStatement();
            ResultSet rs=st.executeQuery("SELECT name from sqlite_master WHERE type='table' and name='CARS' ");
            if(!rs.next()) {
                System.out.println("database is creating");
                Statement st2 = con.createStatement();
                st2.execute("CREATE TABLE CARS (id integer, " +
                        "reg varchar(60), " +
                        "maker varchar(60), " +
                        "model varchar(60), " +
                        "color1 varchar(60), " +
                        "color2 varchar(60), " +
                        "color3 varchar(60), " +
                        "price integer," +
                        "primary key (id));");


                PreparedStatement pse = con.prepareStatement("INSERT INTO CARS VALUES (?,?,?,?,?,?,?,?);");
                pse.setString(2, "reg1");
                pse.setString(3, "toyota");
                pse.setString(4, "alion");
                pse.setString(5, "red");
                pse.setString(6, "");
                pse.setString(7, "black");
                pse.setInt(8, 210000);
                pse.execute();
                PreparedStatement pse2 = con.prepareStatement("INSERT INTO CARS VALUES (?,?,?,?,?,?,?,?);");
                pse2.setString(2, "reg2");
                pse2.setString(3, "tata");
                pse2.setString(4, "nano");
                pse2.setString(5, "red");
                pse2.setString(6, "green");
                pse2.setString(7, "black");
                pse2.setInt(8, 25000);
                pse2.execute();
            }

        }
    }
    public void add(car c) throws SQLException, ClassNotFoundException {
        if(con==null){
            getConnection();
        }
        System.out.println("add called");
        PreparedStatement pse=con.prepareStatement("INSERT INTO CARS VALUES (?,?,?,?,?,?,?,?,?);");
        pse.setString(2,c.getReg());
        pse.setString(3,c.getMaker());
        pse.setString(4,c.getModel());
        pse.setString(5,c.getColor1());
        pse.setString(6,c.getColor2());
        pse.setString(7,c.getColor3());
        pse.setInt(8,c.getPrice());
        pse.setInt(9,c.getCount());
        pse.execute();

    }
    public void addcol() throws SQLException, ClassNotFoundException {
        if(con==null){
            getConnection();
        }
        PreparedStatement pse=con.prepareStatement("ALTER TABLE CARS ADD COLUMN count integer ");
        pse.execute();
    }
    public void update_count(String reg) throws SQLException, ClassNotFoundException {
        if(con==null){
            getConnection();
        }
        Statement st=con.createStatement();

        ResultSet rx=st.executeQuery("SELECT * from CARS WHERE reg='"+reg+"'");
        int x=Integer.parseInt(rx.getString("count"));
        x-=1;
        if(x==0){
            removeData(reg);
        }
        else{
            PreparedStatement pse=con.prepareStatement("UPDATE CARS SET count = ? "+"WHERE reg = ?");
            pse.setInt(1,x);
            pse.setString(2,reg);
            pse.execute();

        }
        System.out.println(x);

    }
    public void update(car c) throws SQLException, ClassNotFoundException {
        if(con==null){
            getConnection();
        }
        PreparedStatement pse;
        if(c.getMaker().length()>1){
            pse=con.prepareStatement("UPDATE CARS SET maker = ? "+"WHERE reg = ?");
            pse.setString(1,c.getMaker());
            pse.setString(2,c.getReg());
            pse.executeUpdate();
        }
        if(c.getModel().length()>1){
            pse=con.prepareStatement("UPDATE CARS SET model = ? "+"WHERE reg = ?");
            pse.setString(1,c.getModel());
            pse.setString(2,c.getReg());
            pse.executeUpdate();

        }
        if(c.getColor1().length()>2){
            pse=con.prepareStatement("UPDATE CARS SET color1 = ? "+"WHERE reg = ?");
            pse.setString(1,c.getColor1());
            pse.setString(2,c.getReg());
            pse.executeUpdate();
        }
        if(c.getColor2().length()>2){
            pse=con.prepareStatement("UPDATE CARS SET color2 = ? "+"WHERE reg = ?");
            pse.setString(1,c.getColor2());
            pse.setString(2,c.getReg());
            pse.executeUpdate();
        }
        if(c.getColor3().length()>2){
            pse=con.prepareStatement("UPDATE CARS SET color3 = ? "+"WHERE reg = ?");
            pse.setString(1,c.getColor3());
            pse.setString(2,c.getReg());
            pse.executeUpdate();
        }
        if (c.getPrice()!=0){
            pse=con.prepareStatement("UPDATE CARS SET price = ? "+"WHERE reg = ?");
            pse.setInt(1,c.getPrice());
            pse.setString(2,c.getReg());
            pse.executeUpdate();
        }
        if(c.getCount()!=0){
            pse=con.prepareStatement("UPDATE CARS SET count = ? "+"WHERE reg = ?");
            pse.setInt(1,c.getCount());
            pse.setString(2,c.getReg());
            pse.executeUpdate();
        }




        //pse.executeUpdate();


    }
    public ResultSet showdata(String reg) throws SQLException, ClassNotFoundException {
        if(con==null){
            getConnection();
        }
        Statement st=con.createStatement();
        ResultSet rs=st.executeQuery("SELECT * from CARS WHERE reg='"+reg+"'");
        return rs;

    }
    public ResultSet showdata(String maker,String model) throws SQLException, ClassNotFoundException {
        if(con==null){
            getConnection();
        }
        Statement st=con.createStatement();
        ResultSet rs;
       // maker=maker.toLowerCase();
        //model=model.toLowerCase();
        if (maker.equalsIgnoreCase("any")) {
            rs=st.executeQuery("SELECT * from CARS WHERE model='"+model+"'");
        }
        else if(model.equalsIgnoreCase("any")){
            rs=st.executeQuery("SELECT * from CARS WHERE maker='"+maker+"'");
        }
        else{
            rs=st.executeQuery("SELECT * from CARS WHERE maker='"+maker+"' AND model='"+model+"'");
        }


        return rs;

    }
    public void removeData(String reg) throws SQLException, ClassNotFoundException {
        if(con==null){
            getConnection();
        }
        Statement st=con.createStatement();
        st.execute("DELETE from CARS WHERE reg='"+reg+"'");
    }
}
