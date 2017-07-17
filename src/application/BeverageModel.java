package application;

import java.util.List;

/**
 * Created by Яна on 03.05.2017.
 */
public class BeverageModel {
    private  String name;
    private Double price;
    private String components;

    public BeverageModel(String name) {
     this.name=name;
    }


    public String getComponents() {
        return components;
    }

    public void setComponents(String components) {
        this.components = components;
    }

    @Override
    public String toString() {
        return "BeverageModel{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", components=" + components +
                '}';
    }

//    public String getComponentsArray() {
//        return componentsArray;
//    }
//
//    public void setComponentsArray(String componentsArray) {
//        this.componentsArray = componentsArray;
//    }

    public BeverageModel(){
        this.name="";
        this.price=00.00;
        this.components="";

    }
    public BeverageModel(String name, Double price, String components){
        this.name=name;
        this.price=price;
        this.components=components;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }

}
