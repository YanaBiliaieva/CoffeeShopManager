package application;

import java.sql.*;

/**
 * Created by Яна on 06.05.2017.
 */
public class LoginModel {
    Connection connection;

    public LoginModel() {
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
    public boolean isLogin(String user, String password) throws SQLException {
        PreparedStatement preparedStatement=null;
        ResultSet resultSet=null;
        String query="select * from users where username=? and password=?";
        try{
            preparedStatement=connection.prepareStatement(query);
            preparedStatement.setString(1,user);
            preparedStatement.setString(2,password);
            resultSet=preparedStatement.executeQuery();
            if(resultSet.next()){
                return true;
            }else {
                return false;
            }
        }catch (Exception e){
            return false;
        }finally {
            preparedStatement.close();
            resultSet.close();
        }
    }

}
