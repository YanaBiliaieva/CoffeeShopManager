package application;

import javafx.application.Application;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;


public class Main extends Application {
    //Stage window;
    Scene productScene;
    BorderPane layout;
    TableView<BeverageModel> productTableView;
    TextField nameInput, priceInput, quantityInput;

    //database connection settings
    //private static final String url="jdbc:postgresql://127.0.0.1:5432/coffeeshopdb";
    //private static final String user="postgres";
    //private static final String password="rout";


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        try {
            Parent root;
            root = FXMLLoader.load(getClass().getResource("/application/login.fxml"));
            Scene loginScene=new Scene(root);
            loginScene.getStylesheets().add(getClass().getResource("coffee.css").toExternalForm());
            primaryStage.setScene(loginScene);
            primaryStage.setTitle("Welcome to Coffee Shop Manager. Please, Log In");
            primaryStage.show();

        }catch (Exception e){
            e.printStackTrace();
        }
        //window =primaryStage;
        //window.setTitle("Coffee Shop Manager. Login");


//        GridPane gridPane= new GridPane();
//        gridPane.setPadding(new Insets(10,10,10,10));
//        gridPane.setVgap(8);
//        gridPane.setHgap(10);
//        //  "Welcome to Coffee Shop Manager"
//        Label welcomeLabel=new Label("Welcome to Coffee Shop Manager");
//        welcomeLabel.setId("bold-label");
//        GridPane.setConstraints(welcomeLabel,2,0);
//
//        //name label
//        Label nameLabel=new Label("Name:");
//        GridPane.setConstraints(nameLabel,1,1);
//
//        //name input
//        TextField nameTextField=new TextField("Barista");
//        GridPane.setConstraints(nameTextField,2,1);
//
//        //password label
//        Label passwordLabel=new Label("Password:");
//        GridPane.setConstraints(passwordLabel,1,2);
//
//        //password input
//        TextField passwordTextField=new TextField();
//        passwordTextField.setPromptText("password");
//        GridPane.setConstraints(passwordTextField,2,2);
//
//        //login button
//        Button loginButton=new Button("Log In");
//        GridPane.setConstraints(loginButton, 2,4);
//        loginButton.setOnAction(e-> {
//           // window.setScene(productScene);
//           // window.setTitle("BeverageModel List");
//        });
//        gridPane.getChildren().addAll(welcomeLabel,nameLabel,nameTextField,passwordLabel,passwordTextField,loginButton);
//
//        //Scene loginScene;
//       // loginScene =new Scene(gridPane, 300,200);
//        // -----Second window------------
//
//        //Program menu
//        Menu programMenu=new Menu("_Program");
//        //Program menu items
//        MenuItem logOut=new MenuItem("Log out...");
//        logOut.setOnAction(e -> {
//         //   window.setScene(loginScene);
//         //   window.setTitle("Log In");
//        });
//        programMenu.getItems().add(logOut);
//        MenuItem exit=new MenuItem("Exit");
//       // exit.setOnAction(event ->  window.close());
//        programMenu.getItems().add(new SeparatorMenuItem());
//        programMenu.getItems().add(exit);
//        //Edit menu
//        Menu editMenu=new Menu("_Edit");
//        editMenu.getItems().add(new MenuItem("Edit components..."));
//        editMenu.getItems().add(new MenuItem("Edit Beverages..."));
//        editMenu.getItems().add(new MenuItem("Edit AdditionsModel..."));
//        editMenu.getItems().add(new MenuItem("Edit Syrups..."));
//        //Help menu
//        Menu helpMenu=new Menu("_Help");
//        helpMenu.getItems().add(new MenuItem("Help"));
//        helpMenu.getItems().add(new MenuItem("About"));
//
//        //Main menu bar
//        MenuBar menuBar=new MenuBar();
//        menuBar.getMenus().addAll(programMenu,editMenu,helpMenu);
//
//        //Name column
//        TableColumn<BeverageModel,String> nameColumn=new TableColumn<>("Name");//just the header
//        nameColumn.setMinWidth(200);
//        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));//has to be the exact name of your property
//
//        //Price column
//        TableColumn<BeverageModel,Double> priceColumn=new TableColumn<>("Price");
//        priceColumn.setMinWidth(100);
//        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
//
//        //Quantity column
//        TableColumn<BeverageModel,Integer> quantityColumn=new TableColumn<>("Quantity");
//        quantityColumn.setMinWidth(100);
//        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
//
//
//        //Name input
//        nameInput = new TextField();
//        nameInput.setPromptText("name");
//        nameInput.setMinWidth(100);
//        //Price input
//        priceInput = new TextField();
//        priceInput.setPromptText("price");
//
//        //Quanity input
//        quantityInput = new TextField();
//        quantityInput.setPromptText("quantity");
//
//        //Buttons
//        Button addButton=new Button("Add");
//        addButton.setStyle("-fx-background-color: aquamarine");
//        addButton.setOnAction(event -> addButtonClicked());
//        Button deleteButton=new Button("Delete");
//        deleteButton.setStyle("-fx-background-color: tomato");
//        deleteButton.setOnAction(event -> deleteButtonClicked());
//        HBox hBox=new HBox();
//        hBox.setPadding(new Insets(10,10,10,10));
//        hBox.setSpacing(10);
//        hBox.getChildren().addAll(nameInput,priceInput,quantityInput,addButton,deleteButton);
//
//
//
//
//        //-------
//        productTableView=new TableView<>();
//        productTableView.setItems(getProduct());
//        productTableView.getColumns().addAll(nameColumn,priceColumn,quantityColumn);
//        VBox productBox=new VBox();
//        productBox.getChildren().addAll(productTableView,hBox);
//
//        layout=new BorderPane();
//        layout.setTop(menuBar);
//        layout.setCenter(productBox);
//        productScene= new Scene(layout);
//        //productScene= new Scene(productBox);
//      //  loginScene.getStylesheets().add("coffee.css");
//        productScene.getStylesheets().add("coffee.css");
//      //  window.setScene(loginScene);
//       // window.show();
//
//    }
//
//    //Add button clicked
//    public void addButtonClicked(){
//        BeverageModel beverage=new BeverageModel();
//        beverage.setName(nameInput.getText());
//        beverage.setPrice(Double.parseDouble(priceInput.getText()));
//        beverage.setQuantity(Integer.parseInt(quantityInput.getText()));
//        productTableView.getItems().add(beverage);
//        nameInput.clear();
//        priceInput.clear();
//        quantityInput.clear();
//    }
//
//    //Delete button clicked
//    public void deleteButtonClicked(){
//    ObservableList<BeverageModel> beveragesSelected, allBeverages;
//    allBeverages=productTableView.getItems();
//    beveragesSelected=productTableView.getSelectionModel().getSelectedItems();
//        beveragesSelected.forEach(allBeverages::remove);
//
//    }
//
//
//
//    //get all of the products
//    public ObservableList<BeverageModel> getProduct(){
//        ObservableList<BeverageModel> beverages = FXCollections.observableArrayList();//TODO need to be a database
//        beverages.add(new BeverageModel("Latte", 18, 1));
//        beverages.add(new BeverageModel("Espresso", 18, 2));
//        beverages.add(new BeverageModel("Cappuchino", 18, 1));
//        beverages.add(new BeverageModel("Mocachino", 18, 3));
//        beverages.add(new BeverageModel("Americano", 18, 1));
//        return beverages;
//    }





//    private boolean shortText(TextField nameInput, String name) {
//
//        if(name.length()>=40){
//            return false;
//
//        }
//        else return true;
   }

}





























