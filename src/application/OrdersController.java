package application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 * Created by Яна on 08.05.2017.
 */
public class OrdersController implements Initializable{
    OrdersModel ordersModel=new OrdersModel();

    @FXML
    private TableView ordersTable;
    @FXML
    private TableColumn<OrdersModel,Integer> orderNumber;
    @FXML
    private TableColumn<OrdersModel,String> beverageNameColumn;
    @FXML
    private TableColumn<OrdersModel,Integer> beverageQuantityColumn;
    @FXML
    private TableColumn<OrdersModel,Double> beveragePriceColumn;
    @FXML
    private TableColumn<OrdersModel,String> additionNameColumn;
    @FXML
    private TableColumn<OrdersModel,Integer> additionQuantityColumn;
    @FXML
    private TableColumn<OrdersModel,Double> additionPriceColumn;
    @FXML
    private TableColumn<OrdersModel,String> syrupNameColumn;
    @FXML
    private TableColumn<OrdersModel,Integer> syrupQuantityColumn;
    @FXML
    private TableColumn<OrdersModel,Double> syrupPriceColumn;
    @FXML
    private TableColumn<OrdersModel,String> dateColumn;
    @FXML
    private TableColumn<OrdersModel,Double> totalOrderPrice;
    @FXML
    private ChoiceBox beverageChoiceBox;
    @FXML
    private ChoiceBox additionChoiceBox;
    @FXML
    private ChoiceBox syrupChoiceBox;
    @FXML
    private ChoiceBox beveragesQuantityChoiceBox;
    @FXML
    private ChoiceBox quantityAdditionChoiceBox;
    @FXML
    private ChoiceBox syrupQuantityChoiceBox;
    @FXML
    private Button addOrderButton;
    @FXML
    private Button deleteOrderButton;
    @FXML
    private Button saveOrdersButton;
    @FXML
    private Label totalOrdersLabel;





    @FXML
    private Label isConnected;

    private SqliteConnection sqliteConnection;
    private ObservableList<OrdersModel> ordersData;
    Connection conn=sqliteConnection.connector();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        sqliteConnection = new SqliteConnection();

        if (ordersModel.isDbConnected()) {
            isConnected.setText("");
        } else {
            isConnected.setText("Database is not connected");
        }
        try {
            loadOrdersFromDatabase();
            getTotalOrdersPrice();
        } catch (Exception e) {
            System.out.print("Cannot load data");
        }
    }
    private void addOrder(){}//TODO
    private void deleteOrder(){}
    private void deleteAllOrders(){}
    private void saveOrdersToFile(){}
    private void logOut(){}
    @FXML
    private void loadOrdersFromDatabase()  {

        try {
            ordersData= FXCollections.observableArrayList();
            ResultSet rs = conn.createStatement().executeQuery("select * from orders");
            while (rs.next()){
                ordersData.add(new OrdersModel(rs.getInt(1), rs.getString(2), rs.getInt(3),
                        rs.getDouble(4),rs.getString(5), rs.getInt(6), rs.getDouble(7),
                        rs.getString(8), rs.getInt(9),rs.getDouble(10),rs.getString(11),
                        rs.getDouble(12)));
                System.out.print("rs  while");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //catch (SQLException e) {
           // e.printStackTrace();
    System.out.print(" catch while (rs.next())");


        orderNumber.setCellValueFactory(new PropertyValueFactory<>("orderCount"));System.out.print(" 11111");
        beverageNameColumn.setCellValueFactory(new PropertyValueFactory<>("beverageNameColumn"));
        System.out.print(" 11111");
        beverageQuantityColumn.setCellValueFactory(new PropertyValueFactory<>("beverageQuantityColumn"));
        beveragePriceColumn.setCellValueFactory(new PropertyValueFactory<>("beveragePriceColumn"));
        additionNameColumn.setCellValueFactory(new PropertyValueFactory<>("additionNameColumn"));
        additionQuantityColumn.setCellValueFactory(new PropertyValueFactory<>("additionQuantityColumn"));
        additionPriceColumn.setCellValueFactory(new PropertyValueFactory<>("additionPriceColumn"));
        syrupNameColumn.setCellValueFactory(new PropertyValueFactory<>("syrupNameColumn"));
        syrupQuantityColumn.setCellValueFactory(new PropertyValueFactory<>("syrupQuantityColumn"));
        syrupPriceColumn.setCellValueFactory(new PropertyValueFactory<>("syrupPriceColumn"));
       dateColumn.setCellValueFactory(new PropertyValueFactory<>("dateColumn"));
        totalOrderPrice.setCellValueFactory(new PropertyValueFactory<>("totalOrderPrice"));
        ordersTable.setItems(null);
        ordersTable.setItems(ordersData);


        ObservableList<String> beveragesData;
        beveragesData= FXCollections.observableArrayList();
        try {


            ResultSet rs = conn.createStatement().executeQuery("select * from beverages");
            while (rs.next()){
                beveragesData.add(new String(rs.getString(2)));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        beverageChoiceBox.setItems(beveragesData);
    }
    public int getTotalOrdersPrice(){

totalOrdersLabel.setText("Total Price: 140");

        return 0;
    }
}
