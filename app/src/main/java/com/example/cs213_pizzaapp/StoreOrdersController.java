package com.example.cs213_pizzaapp;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

/**
 * StoreOrdersController controls the view of StoreOrder by allowing capabilities to cancel a store order, export a store
 * order, and browse through all orders in store order. Also, displays order total including tax.
 * @author Alexis Wilson, James Alba
 */
public class StoreOrdersController {

    @FXML
    TextField orderTotal;
    @FXML
    ObservableList orderList;

    @FXML
    ComboBox<Integer> orderNumber;

    @FXML
    ListView<Pizza> storeOrderOutput;

    private MainController mainController;

    /**
     * Sets main controller object to the current store order controller and sets combo box containing all order number
     * and related order information.
     * @param mainController passed in maincontroller object from main controller
     */
    public void setMainController(MainController mainController) {
        this.mainController = mainController;
        setComboBox();
        orderTotal.appendText("$0.00");
    }

    /**
     * Private helper method that sets all the order numbers into the combo box to be viewed.
     */
    private void setComboBox() {
        orderList = FXCollections.observableArrayList();
        for (int i = 0; i < mainController.getStoreOrders().getSize(); i++) {
            orderList.add(mainController.getStoreOrders().getOrder(i).getOrderNumber());
        }
        orderNumber.setItems(orderList);
    }

    /**
     * Selects an order number from combo box to be viewed and used.
     */
    @FXML
    protected void selectOrderNumber() {
        if(orderNumber.getValue() != null) {
            int currentOrderID = orderNumber.getSelectionModel().getSelectedIndex();
            if (currentOrderID != -1) displayOrderInfo(currentOrderID);
        }
    }

    /**
     * Private helper method that displays all the order info from the order number selected from combo box.
     */
    private void displayOrderInfo(int orderID) {
        ObservableList<Pizza> currentOrder = FXCollections.observableArrayList();
        currentOrder.setAll(mainController.getStoreOrders().getOrder(orderID).getOrderList());
        storeOrderOutput.setItems(currentOrder);
        orderTotal.clear();
        orderTotal.appendText(String.format("$%,.2f", mainController.getStoreOrders().getOrder(orderID).orderTotalPrice()));
    }

    /**
     * Exports all the store orders into a text file and saved in the desired location by the user.
     */
    @FXML
    protected void exportStoreOrders() {
        if (!mainController.getStoreOrderObservableList().isEmpty()) {
            FileChooser chooseExport = new FileChooser();
            chooseExport.setTitle("Choose where to export to");
            chooseExport.getExtensionFilters().add(new FileChooser.ExtensionFilter("ExportOrder", "*.txt"));
            Stage stage = new Stage();
            File file = chooseExport.showSaveDialog(stage);
            showExportResult(file);
        }
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Cannot export no orders.");
            alert.showAndWait();
        }
    }

    /**
     * Cancels the selected order from the store orders.
     */
    @FXML
    protected void storeCancelOrders() {
        if(orderNumber.getValue() == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("No order selected, cannot remove.");
            alert.showAndWait();
        }
        else {
            removeOrder();
        }
    }

    /**
     * Private helper method that removes the selected order from store orders.
     */
    private void removeOrder() {
        orderTotal.clear();
        orderTotal.appendText("$0.00");
        int selectedOrderID = orderNumber.getSelectionModel().getSelectedItem();
        mainController.getStoreOrders().remove(mainController.getStoreOrders().getID(selectedOrderID));
        storeOrderOutput.getItems().clear();
        orderNumber.getItems().clear();
        setComboBox();
    }

    /**
     * Private helper method that displays whether the export of store order was successful or not.
     */
    private void showExportResult(File file){
        Alert result = new Alert(Alert.AlertType.INFORMATION);
        if (mainController.getStoreOrders().export(file)) {
            result.setHeaderText("Export Order Success");
            result.setContentText("Successfully exported orders.");
        }
        else {
            result.setHeaderText("Error");
            result.setContentText("There was a problem exporting orders.");
        }
        result.show();
    }
}
