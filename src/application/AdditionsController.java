package application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 * Created by Яна on 11.05.2017.
 */
public class AdditionsController implements Initializable {
    AdditionsModel additionsModel=new AdditionsModel();
    SyrupModel syrupModel=new SyrupModel();
    @FXML
    private Button nextButton;
    @FXML
    private Button backButton;
    @FXML
    private TableView additionsTable;
    @FXML
    private TableColumn<AdditionsModel,String> nameColumn;
    @FXML
    private TableColumn<AdditionsModel,Double> priceColumn;
    @FXML
    private Label isConnected;
    @FXML
    private TableView syrupsTable;
    @FXML
    private TableColumn<SyrupModel,String> syrupNameColumn;
    @FXML
    private TableColumn<SyrupModel,Double> syrupPriceColumn;

    private ObservableList<AdditionsModel> additionsData;
    private ObservableList<SyrupModel> syrupsData;
    private SqliteConnection sqliteConnection;
    private SqliteConnection sqliteSyrupConnection;
    Connection conn=sqliteConnection.connector();
    Connection syrupComm=sqliteSyrupConnection.connector();
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        sqliteConnection = new SqliteConnection();
        sqliteSyrupConnection=new SqliteConnection();
        if (additionsModel.isDbConnected()) {
            isConnected.setText("");
        } else {
            isConnected.setText("Database is not connected");
        }
        try {
            loadAdditionsFromDatabase();
            loadSyrupsFromDatabase();

        } catch (Exception e) {
            System.out.print("Cannot load data");
        }

    }
    @FXML
    private void nextButtonClicked(ActionEvent event){
        try {
            ((Node) event.getSource()).getScene().getWindow().hide();//to hide stage
            Stage primaryStage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            Pane root;
            root = loader.load(getClass().getResource("/application/orders.fxml").openStream());
            Scene ordersScene = new Scene(root);
            ordersScene.getStylesheets().add(getClass().getResource("coffee.css").toExternalForm());
            primaryStage.setScene(ordersScene);
            primaryStage.setTitle("Orders");
            primaryStage.show();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    private void addAddition(){} //TODO
    private void deleteAddition(){}
    private void addSyrup(){}
    private void deleteSyrup(){}

    private void loadAdditionsFromDatabase(){
        additionsData= FXCollections.observableArrayList();
        ResultSet rs = null;
        try {
            rs = conn.createStatement().executeQuery("select * from additions");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            while (rs.next()){
                additionsData.add(new AdditionsModel(rs.getString(2 ), rs.getDouble(3)));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("Name"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("Price"));
        additionsTable.setItems(null);
        additionsTable.setItems(additionsData);
    }

    private void loadSyrupsFromDatabase(){
        syrupsData= FXCollections.observableArrayList();
        ResultSet rs = null;
        try {
            rs = syrupComm.createStatement().executeQuery("select * from syrups");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            while (rs.next()){
                syrupsData.add(new SyrupModel((rs.getString(2 )), rs.getDouble(3)));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        syrupNameColumn.setCellValueFactory(new PropertyValueFactory<>("Name"));
        syrupPriceColumn.setCellValueFactory(new PropertyValueFactory<>("Price"));
        syrupsTable.setItems(null);
        syrupsTable.setItems(syrupsData);
    }
    private void setCellValueFromTableToAdditionTextField(){}
    private void setCellValueFromTableToSyrupTextField(){}
    @FXML
    private void backButtonClicked(ActionEvent event){
        try {
            ((Node) event.getSource()).getScene().getWindow().hide();//to hide stage
            Stage primaryStage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            Pane root;
            root = loader.load(getClass().getResource("/application/beverages.fxml").openStream());
            Scene beveragesScene = new Scene(root);
            beveragesScene.getStylesheets().add(getClass().getResource("coffee.css").toExternalForm());
            primaryStage.setScene(beveragesScene);

            primaryStage.show();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
