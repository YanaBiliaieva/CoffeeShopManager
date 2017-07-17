package application;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by Яна on 07.05.2017.
 */
public class BeveragesController implements Initializable {
public BeverageModel beverageModel=new BeverageModel();
public ComponentsModel componentsModel=new ComponentsModel();
    public ObservableList<String> selectedItems;
    @FXML
    private ListView componentsListView;
    @FXML
    private Label listViewClickedLablel;
    @FXML
    private Button addBeverageButton;
    @FXML
    private TextField addBeverageTextField;
    @FXML
    private TextField addBeveragePriceTextField;
    @FXML
    private TableView beveragesTableView;
    @FXML
    private TableColumn<BeverageModel,String> nameColumn;
    @FXML
    private TableColumn<BeverageModel,Double> priceColumn;
    @FXML
    private TableColumn<ComponentsModel,String> componentsColumn;
    @FXML
    private Button ordersButton;
    @FXML
    private Button nextButton;


    private SqliteConnection sqliteConnection;
    Connection conn=sqliteConnection.connector();
    private ObservableList<String> componentsData;
    private ObservableList<BeverageModel> beveragesData;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        sqliteConnection = new SqliteConnection();
        try {
            loadComponentsFromDatabase();
            loadBeveragesFromDatabase();

        } catch (Exception e) {
            System.out.print("Cannot load data from components table");
        }
    }
    @FXML
    private void componentsListViewSelection(){

             selectedItems =  componentsListView.getSelectionModel().getSelectedItems();
//            for(String s : selectedItems){
//                System.out.println("selected item " + s);
//            }
        listViewClickedLablel.setText("Hold Ctrl to select several components");

    }
    @FXML
    private void loadComponentsFromDatabase(){
        try {
            componentsData= FXCollections.observableArrayList();
            ResultSet rs = conn.createStatement().executeQuery("select * from components");
            while (rs.next()){
                componentsData.add(new String(rs.getString(2)));
            }
        } catch (SQLException e) {
            System.out.print("Cannot load data from components table");
            e.printStackTrace();
        }
        componentsListView.setItems(null);
        componentsListView.setItems(componentsData);
        componentsListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    }
    @FXML
    private void loadBeveragesFromDatabase(){
       // try {
            beveragesData= FXCollections.observableArrayList();
            //ArrayList<String> latteComponents;
            //latteComponents=new ArrayList<String>();
            //latteComponents.add("espresso");
           // latteComponents.add("milk");
            BeverageModel latte=new BeverageModel("Latte",18.00, "espresso, milk" );
            beveragesData.add(latte);
            //ArrayList<String> espressoComponents;
            //espressoComponents=new ArrayList<String>();
           // espressoComponents.add("espresso");
            BeverageModel espresso=new BeverageModel("Espresso",9.00, "espresso");
            beveragesData.add(espresso);
            //ArrayList<String> americanoComponents;
            //americanoComponents=new ArrayList<String>();
            //americanoComponents.add("espresso");
            //americanoComponents.add("cream");
            BeverageModel americano=new BeverageModel("Americano with cream",15.00, "americano, cream" );
            beveragesData.add(americano);
            //ArrayList<String> cappuchinoComponents;
            //cappuchinoComponents=new ArrayList<String>();
            //cappuchinoComponents.add("espresso");
            //cappuchinoComponents.add("cream");
            BeverageModel cappuchino=new BeverageModel("Cappuchino",17.00, "espresso, cream" );
            beveragesData.add(cappuchino);

            //ResultSet rs = conn.createStatement().executeQuery("select * from beverages  ");//where recipes.beverage_id=recipes.component_id
            //while (rs.next()){
                //beveragesData.add(new BeverageModel(rs.getString(2),rs.getDouble(3)),(rs.getArray(4)).toString());
                //beveragesData.add(new BeverageModel(rs.getDouble(3)));
           // }
            //записать в массив
        //} catch (SQLException e) {
        //    System.out.print("Cannot load data from components table");
        //    e.printStackTrace();
       // }
        //set cell value factory to tableview
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        componentsColumn.setCellValueFactory(new PropertyValueFactory<>("components"));
        beveragesTableView.setItems(null);
        beveragesTableView.setItems(beveragesData);

    }
    @FXML
    private void addNewBeverage(){
        PreparedStatement preparedStatement=null;
        String query="Insert into beverages (name,price) Values (?,?)";
        String newBeverageName=addBeverageTextField.getText();
        Double newBeveragePrice= Double.valueOf(addBeveragePriceTextField.getText());
        try {
            preparedStatement=conn.prepareStatement(query);
            preparedStatement.setString(1,newBeverageName);
            preparedStatement.setDouble(2,newBeveragePrice);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                preparedStatement.close();
                loadComponentsFromDatabase();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    private void deleteBeverage(){}


    public void nextPage(ActionEvent event){
        try {
            ((Node) event.getSource()).getScene().getWindow().hide();//to hide stage
            Stage primaryStage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            Pane root;
            root = loader.load(getClass().getResource("/application/additions.fxml").openStream());
            Scene additionsScene = new Scene(root);
            additionsScene.getStylesheets().add(getClass().getResource("coffee.css").toExternalForm());
            primaryStage.setScene(additionsScene);
            primaryStage.setTitle("Additions and Syrups");
            primaryStage.show();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
