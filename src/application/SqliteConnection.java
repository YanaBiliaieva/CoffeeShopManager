package application;


import java.sql.*;
/**
 * Created by Яна on 06.05.2017.
 */
public class SqliteConnection {
    public static Connection connector(){
    try {
        Class.forName("org.sqlite.JDBC");
        Connection conn =DriverManager.getConnection("jdbc:sqlite:coffeeshopdb.sqlite");
        return conn;
    }catch (Exception e){
        System.out.println(e);
        return null;
    }
    }
}
