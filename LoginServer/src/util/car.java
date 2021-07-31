package util;

import java.io.Serializable;

public class car implements Serializable {
    String reg;
    String maker;
    String model;
    String color1;
    String color2;
    String color3;
    int price=0;
    int count=0;
    String rq;
    public car(){

    }
    public car(String reg,
            String maker,
            String model,
            String color1,
            String color2,
            String color3,
            int price,int count){
        this.reg=reg;
        this.maker=maker;
        this.model=model;
        this.color1=color1;
        this.color2=color2;
        this.color3=color3;
        this.price=price;
        this.count=count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getCount() {
        return count;
    }

    public void setReg(String reg) {
        this.reg = reg;
    }

    public String getReg() {
        return reg;
    }

    public String getMaker() {
        return maker;
    }

    public String getColor3() {
        return color3;
    }

    public String getColor2() {
        return color2;
    }

    public String getColor1() {
        return color1;
    }

    public int getPrice() {
        return price;
    }

    public String getModel() {
        return model;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setColor1(String color1) {
        this.color1 = color1;
    }

    public void setColor2(String color2) {
        this.color2 = color2;
    }

    public void setColor3(String color3) {
        this.color3 = color3;
    }

    public void setMaker(String maker) {
        this.maker = maker;
    }

    public void setRq(String rq) {
        this.rq = rq;
    }

    public String getRq() {
        return rq;
    }

    @Override
    public String toString() {
        return "car{" +
                "reg= " + reg +
                " maker='" + maker + '\'' +
                ", model='" + model + '\'' +
                ", color1='" + color1 + '\'' +
                ", color2='" + color2 + '\'' +
                ", color3='" + color3 + '\'' +
                ", price=" + price +
                ", Left=" + count +
                '}';
    }
}
