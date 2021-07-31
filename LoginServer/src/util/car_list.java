package util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class car_list implements Serializable {
    private ArrayList<car> list;
    private String source;
    private String search_history;
    public car_list(){
        list=new ArrayList();
        source=null;
        search_history=null;
    }
    //private car c;


    public void setSource(String source) {
        this.source = source;
    }

    public String getSource() {
        return source;
    }

    public void setSearch_history(String search_history) {
        this.search_history = search_history;
    }

    public String getSearch_history() {
        return search_history;
    }

    public void addcar(car car){
        System.out.println(car);
        list.add(car);
    }
    public ArrayList<car> getCars(){

        System.out.println(list);
        System.out.println("from car_list");
        return list;
    }

    @Override
    public String toString() {
        return "car_list{" +
                "cars=" + list +
                ", c=" +
                '}';
    }
}
