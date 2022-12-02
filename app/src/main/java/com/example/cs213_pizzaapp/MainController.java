package com.example.cs213_pizzaapp;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import java.io.IOException;

/**
 * MainController creates the main menu, holds methods for navigating to different windows, and methods for managing
 *  orders and pizzas.
 * @author Alexis Wilson, James Alba
 */
public class MainController {
    private int uniqueOrderNumber = 1;
    private final StoreOrder storeOrders = new StoreOrder();
    private final Order totalOrder = new Order();
    @FXML
    private Button newYorkPizzaSelection;
    @FXML
    private Button chicagoPizzaSelection;
    @FXML
    private Button storeOrdersSelection;
    @FXML
    private Button myOrderSelection;

    /**
     * showCurrentOrders() creates a new stage and sets root to proper fxml file for Current Order window.
     * Catches exceptions if unable to open stage.
     */
    @FXML
    protected void showCurrentOrders() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("Current Order View.fxml"));
            Parent root = fxmlLoader.load();
            CurrentOrderController currentOrderController = fxmlLoader.getController();
            currentOrderController.createMainController(this);
            Stage stage = new Stage();
            stage.setTitle("Current Orders");
            stage.setScene(new Scene(root));
            disableAllButtons();
            stage.show();
            stage.setOnCloseRequest(eventCalled -> enableAllButtons());
        } catch (Exception e) {
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setHeaderText("Error");
            errorAlert.setContentText(e.getMessage());
            errorAlert.showAndWait();
        }
    }

    /**
     * showStoresOrders() creates a new stage and sets root to proper fxml file for Store Order window.
     * Catches exception if unable to open window.
     */
    @FXML
    protected void showStoreOrders() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("Store orders View.fxml"));
            Parent root2 = fxmlLoader.load();
            StoreOrdersController storeOrdersController = fxmlLoader.getController();
            storeOrdersController.setMainController(this);
            Stage stage = new Stage();
            stage.setTitle("Store Orders");
            stage.setScene(new Scene(root2));
            disableAllButtons();
            stage.show();
            stage.setOnCloseRequest(eventCalled -> enableAllButtons());
        } catch (Exception e) {
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setHeaderText("Error");
            errorAlert.setContentText(e.getMessage());
            errorAlert.showAndWait();
        }
    }

    /**
     * openNewYorkPizza() creates a new stage and sets root to proper fxml file for New York Style Pizza Ordering View
     * window. Catches exception if unable to open window.
     */
    @FXML
    protected void openNewYorkPizza() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("NYStylePizzaOrderingView.fxml"));
            Parent root1 = fxmlLoader.load();
            NYStylePizzaController nyPizzaController = fxmlLoader.getController();
            nyPizzaController.createMainController(this);
            Stage stage = new Stage();
            stage.setTitle("NY Style Pizza Order");
            stage.setScene(new Scene(root1));
            disableAllButtons();
            stage.show();
            stage.setOnCloseRequest(eventCalled -> enableAllButtons());
        } catch (Exception e) {
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setHeaderText("Error");
            errorAlert.setContentText(e.getMessage());
            errorAlert.showAndWait();
        }
    }

    /**
     * Gets Order object for current total order
     * @return
     */
    public Order getTotalOrder() {
        return totalOrder;
    }

    /**
     * openChicagoPizza() creates a new stage and sets root to proper fxml file for Chicago Style Pizza Ordering View
     * window. Catches exception if unable to open window.
     */
    @FXML
    protected void openChicagoPizza() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("Chicago Style Pizza Ordering View.fxml"));
            Parent root1 = fxmlLoader.load();
            ChicagoStylePizzaController chicagoPizzaController = fxmlLoader.getController();
            chicagoPizzaController.createMainController(this);
            Stage stage = new Stage();
            stage.setTitle("Chicago Style Pizza Order");
            stage.setScene(new Scene(root1));
            disableAllButtons();
            stage.show();
            stage.setOnCloseRequest(eventCalled -> enableAllButtons());
        } catch (Exception e) {
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setHeaderText("Error");
            errorAlert.setContentText(e.getMessage());
            errorAlert.showAndWait();
        }
    }

    /**
     * This returns an observable list of total order using the getOrder method.
     * @return An ObservableList of the pizza object.
     */
    public ObservableList<Pizza> getOrderObservableList() {
        return totalOrder.getOrder();
    }

    /**
     * This enables all the buttons in the main view fxml file. This is so that the user is able to use the main menu after its been disabled.
     */
    public void enableAllButtons() {
        newYorkPizzaSelection.setDisable(false);
        chicagoPizzaSelection.setDisable(false);
        storeOrdersSelection.setDisable(false);
        myOrderSelection.setDisable(false);
    }

    /**
     * This disables all the buttons in the main view fxml file. This is to prevent any possible errors from occurring by
     * the user opening multiple windows of different functions.
     */
    public void disableAllButtons() {
        newYorkPizzaSelection.setDisable(true);
        chicagoPizzaSelection.setDisable(true);
        storeOrdersSelection.setDisable(true);
        myOrderSelection.setDisable(true);
    }

    /**
     * This is a helper function that returns an order number to be used when called.
     * @return Integer representing a unique order number.
     */
    public int getOrderNumber() {
        return uniqueOrderNumber;
    }

    /**
     * This is a helper function that increases the order number in order to maintain uniqueness. This is used when an
     * order is added to the store orders and a new order number is needed.
     */
    public void addOrderNumber() {
        uniqueOrderNumber++;
    }

    /**
     * This is a helper function that returns the store orders object in this class.
     * @return StoreOrder object representing the store orders.
     */
    public StoreOrder getStoreOrders() {
        return storeOrders;
    }

    /**
     * This is a helper function that returns the store orders in an observable list form.
     * @return ObservableList of the Order object representing an observable list of the store orders.
     */
    public ObservableList<Order> getStoreOrderObservableList() {
        return storeOrders.getStoreOrderList();
    }

    /**
     * This is a helper function that returns the current order in an observable list form.
     * @return ObservableList of the pizza object representing the list of pizzas that are currently being ordered.
     */
    public ObservableList<Pizza> getPizzaOrdersObservableList() {
        return totalOrder.getOrderList();
    }
}