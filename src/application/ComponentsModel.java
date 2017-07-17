package application;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by Яна on 07.05.2017.
 */
public class ComponentsModel {
    private StringProperty name;
    Connection connection;
    public ComponentsModel(String name){
        this.name=new SimpleStringProperty(name);
    }
    public String getName() {
        return name.get();
    }
    public void setName(String value) {
        name.set(value);
    }
    public StringProperty nameProperty(){
        return name;
    }


    public ComponentsModel() {
        connection= SqliteConnection.connector();
        if (connection==null){
            System.out.print("Connection not successful");
            System.exit(1);
        }
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
