package application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

    /**
     * Created by Яна on 06.05.2017.
     */
    public class ComponentsController implements Initializable {
        public ComponentsModel componentsModel=new ComponentsModel();

        @FXML
        private Label userLabel;
        @FXML
        private Button logOutButton;
        @FXML
        private Button addComponentButton;
        @FXML
        private Button deleteComponentButton;
        @FXML
        private TableView componentsTable;
        @FXML
        private Label isConnected;
        @FXML
        private TableColumn<ComponentsModel,String> listOfComponents;
        @FXML
        private TextArea addComponentTextField;
        @FXML
        private Button nextButton;

        //initialize ObservableList to hold out database data
        private ObservableList<ComponentsModel> componentsData,selectedComponent;
        private SqliteConnection sqliteConnection;
        Connection conn=sqliteConnection.connector();



        @Override
        public void initialize(URL location, ResourceBundle resources) {
            sqliteConnection = new SqliteConnection();

            if (componentsModel.isDbConnected()) {
                isConnected.setText("");
            } else {
                isConnected.setText("Database is not connected");
            }
            try {
                loadComponentsFromDatabase();

            } catch (Exception e) {
                System.out.print("Cannot load data from components table");
            }
        }
        @FXML
        private void loadComponentsFromDatabase(){
            try {
                componentsData= FXCollections.observableArrayList();
                ResultSet rs = conn.createStatement().executeQuery("select * from components");
                while (rs.next()){
                    componentsData.add(new ComponentsModel(rs.getString(2 )));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            //set cell value factory to tableview
            listOfComponents.setCellValueFactory(new PropertyValueFactory<>("name"));
            componentsTable.setItems(null);
            componentsTable.setItems(componentsData);
            addComponentTextField.clear();
            addComponentTextField.setEditable(true);

        }

        public void getUser(String user){
            userLabel.setText("Welcome, "+ user+"! Firstly, create ingredients for your beverages here");
        }
        @FXML
        public void addComponent(ActionEvent event){

            PreparedStatement preparedStatement=null;
            String query="Insert into components (name) Values (?)";
            String newComponent=addComponentTextField.getText();
            try{
                preparedStatement=conn.prepareStatement(query);
                preparedStatement.setString(1,newComponent);
                preparedStatement.executeUpdate();
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                try {
                    preparedStatement.close();
                    loadComponentsFromDatabase();

                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            addComponentTextField.clear();
            addComponentTextField.setEditable(true);
        }
        @FXML
        private void setCellValueFromTableToTextField(){
            componentsTable.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    ComponentsModel cm= (ComponentsModel) componentsTable.getItems().get(componentsTable.getSelectionModel().getSelectedIndex());
                    addComponentTextField.setText(cm.getName());
                }
            });
        }
//    @FXML
//    private void updateComponent(ActionEvent event){
//        PreparedStatement preparedStatement=null;
//        String query="Update components set name=? where name=?";
//        try {
//            preparedStatement.setString(1,addComponentTextField.getText());
//            preparedStatement.setString(2,addComponentTextField.getText());
//            preparedStatement=conn.prepareStatement(query);
//            int i=preparedStatement.executeUpdate();
//            if(i==1){
//                System.out.print("Update successful");
//            }
//        }catch (Exception e){
//            System.out.print("Update not successful");
//            e.printStackTrace();
//        }
//    }


    @FXML
    public void deleteComponent(ActionEvent event){
        PreparedStatement preparedStatement=null;
        String query="Delete from components where name = ?";
        String deletedComponentName=addComponentTextField.getText();
        try {
            preparedStatement=conn.prepareStatement(query);
            preparedStatement.setString(1,deletedComponentName);
            int i=preparedStatement.executeUpdate();
            if(i==1){
                System.out.print("Delete successful");
                loadComponentsFromDatabase();
            }
        }catch (Exception e){
            System.out.print("Delete not successful");
            e.printStackTrace();
        }
    }

    public void signOut(ActionEvent event){
        try {
            ((Node) event.getSource()).getScene().getWindow().hide();//to hide stage
            Stage primaryStage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            Pane root;
            root = loader.load(getClass().getResource("/application/login.fxml").openStream());
            Scene loginScene = new Scene(root);
            loginScene.getStylesheets().add(getClass().getResource("coffee.css").toExternalForm());
            primaryStage.setScene(loginScene);
            primaryStage.show();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void nextPage(ActionEvent event){
        try {
            ((Node) event.getSource()).getScene().getWindow().hide();//to hide stage
            Stage primaryStage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            Pane root;
            root = loader.load(getClass().getResource("/application/beverages.fxml").openStream());
            Scene beveragesScene = new Scene(root);
            beveragesScene.getStylesheets().add(getClass().getResource("coffee.css").toExternalForm());
            primaryStage.setScene(beveragesScene);
            primaryStage.setTitle("Beverages");

            primaryStage.show();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
