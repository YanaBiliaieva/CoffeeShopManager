package application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 * Created by Яна on 06.05.2017.
 */
public class LoginController implements Initializable {
    public LoginModel loginModel=new LoginModel();
    @FXML
    private Label isConnected;
    @FXML
    private TextField txtUsername;
    @FXML
    private TextField txtPassword;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if(loginModel.isDbConnected()){
            isConnected.setText("Database connected. Log In");
        }else {
            isConnected.setText("Not Connected");
        }
    }

    public void login(ActionEvent event){
        try {
            if(loginModel.isLogin(txtUsername.getText(),txtPassword.getText())){
                isConnected.setText("User name and password are correct");
                ((Node)event.getSource()).getScene().getWindow().hide();//to hide stage
                Stage primaryStage=new Stage();
                FXMLLoader loader=new FXMLLoader();
                Pane root;
                root = loader.load(getClass().getResource("/application/components.fxml").openStream());
                ComponentsController componentsController=(ComponentsController)loader.getController();
                componentsController.getUser(txtUsername.getText());
                Scene loginScene=new Scene(root);
                loginScene.getStylesheets().add(getClass().getResource("coffee.css").toExternalForm());
                primaryStage.setScene(loginScene);
                primaryStage.setTitle("Components");
                primaryStage.show();


            }else isConnected.setText("User name and password are not correct");
        } catch (SQLException e) {
            isConnected.setText("User name and password are not correct");
            e.printStackTrace();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
