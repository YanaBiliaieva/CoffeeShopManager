package application;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by Яна on 05.05.2017.
 */
public class SyrupModel {
    private  String name;
    private Double price;
    Connection connection;

    public SyrupModel() {
        connection= SqliteConnection.connector();
        if (connection==null){
            System.out.print("Connection not successful");
            System.exit(1);
        }
    }

    public SyrupModel(String name, Double price) {
        this.name = name;
        this.price = price;

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

    public void setPrice(Double price) {
        this.price = price;
    }

    public boolean isDbConnected(){
        try {
            return !connection.isClosed();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
