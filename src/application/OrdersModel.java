package application;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by Яна on 08.05.2017.
 */
public class OrdersModel {
    Connection connection;


    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public Integer getOrderCount() {
        return orderCount;
    }

    public void setOrderCount(Integer orderCount) {
        this.orderCount = orderCount;
    }

    public String getBeverageNameColumn() {
        return beverageNameColumn;
    }

    public void setBeverageNameColumn(String beverageNameColumn) {
        this.beverageNameColumn = beverageNameColumn;
    }

    public Integer getBeverageQuantityColumn() {
        return beverageQuantityColumn;
    }

    public void setBeverageQuantityColumn(Integer beverageQuantityColumn) {
        this.beverageQuantityColumn = beverageQuantityColumn;
    }

    public Double getBeveragePriceColumn() {
        return beveragePriceColumn;
    }

    public void setBeveragePriceColumn(Double beveragePriceColumn) {
        this.beveragePriceColumn = beveragePriceColumn;
    }

    public String getAdditionNameColumn() {
        return additionNameColumn;
    }

    public void setAdditionNameColumn(String additionNameColumn) {
        this.additionNameColumn = additionNameColumn;
    }

    public Integer getAdditionQuantityColumn() {
        return additionQuantityColumn;
    }

    public void setAdditionQuantityColumn(Integer additionQuantityColumn) {
        this.additionQuantityColumn = additionQuantityColumn;
    }

    public Double getAdditionPriceColumn() {
        return additionPriceColumn;
    }

    public void setAdditionPriceColumn(Double additionPriceColumn) {
        this.additionPriceColumn = additionPriceColumn;
    }

    public String getSyrupNameColumn() {
        return syrupNameColumn;
    }

    public void setSyrupNameColumn(String syrupNameColumn) {
        this.syrupNameColumn = syrupNameColumn;
    }

    public Integer getSyrupQuantityColumn() {
        return syrupQuantityColumn;
    }

    public void setSyrupQuantityColumn(Integer syrupQuantityColumn) {
        this.syrupQuantityColumn = syrupQuantityColumn;
    }

    public Double getSyrupPriceColumn() {
        return syrupPriceColumn;
    }

    public void setSyrupPriceColumn(Double syrupPriceColumn) {
        this.syrupPriceColumn = syrupPriceColumn;
    }

    public String getDateColumn() {
        return dateColumn;
    }

    public void setDateColumn(String dateColumn) {
        this.dateColumn = dateColumn;
    }

    public Double getTotalOrderPrice() {
        return totalOrderPrice;
    }

    public void setTotalOrderPrice(Double totalOrderPrice) {
        this.totalOrderPrice = totalOrderPrice;
    }

    private int orderCount;

    private String beverageNameColumn;

    private int beverageQuantityColumn;

    private Double beveragePriceColumn;

    private String additionNameColumn;

    private int additionQuantityColumn;

    private Double additionPriceColumn;

    private String syrupNameColumn;

    private int syrupQuantityColumn;

    private Double syrupPriceColumn;
    private String dateColumn;

    private Double totalOrderPrice;

    public OrdersModel(
            int orderCount,
                       String beverageNameColumn,
                       int beverageQuantityColumn,
                       Double beveragePriceColumn,
                       String additionNameColumn,
                       int additionQuantityColumn,
                       Double additionPriceColumn,
                       String syrupNameColumn,
                       int syrupQuantityColumn,
                       Double syrupPriceColumn,
                       String dateColumn,
                       Double totalOrderPrice) {
        this.orderCount = orderCount;
        this.beverageNameColumn = beverageNameColumn;
        this.beverageQuantityColumn = beverageQuantityColumn;
        this.beveragePriceColumn = beveragePriceColumn;
        this.additionNameColumn = additionNameColumn;
        this.additionQuantityColumn = additionQuantityColumn;
        this.additionPriceColumn = additionPriceColumn;
        this.syrupNameColumn = syrupNameColumn;
        this.syrupQuantityColumn = syrupQuantityColumn;
        this.syrupPriceColumn = syrupPriceColumn;
        this.dateColumn = dateColumn;
        this.totalOrderPrice = totalOrderPrice;
    }



    public OrdersModel(){
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
