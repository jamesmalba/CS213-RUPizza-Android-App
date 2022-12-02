package com.example.cs213_pizzaapp;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

/**
 * CurrentOrderController controls the view of Order by allowing capabilities to place an order, clear an order, and
 * remove pizzas. Also, displays tax, subtotal, and order total.
 * @author Alexis Wilson, James Alba
 */
public class CurrentOrderController {
    @FXML
    private ListView<Pizza> totalOrderOutput;
    @FXML
    TextField subTotal;
    @FXML
    TextField salesTax;
    @FXML
    TextField orderTotal;
    @FXML
    TextField orderNumber;

    private MainController mainController;
    public static final double SALES_TAX = 0.06625;
    public static final double SALES_TAX_MULTIPLIER = 1.06625;

    /**
     * Sets main controller object to the current order controller and sets order number and related order information.
     * @param mainController passed in maincontroller object from main controller
     */
    public void createMainController(MainController mainController) {
        this.mainController = mainController;
        totalOrderOutput.setItems(mainController.getPizzaOrdersObservableList());
        if (mainController.getPizzaOrdersObservableList().size() != 0) {
            orderNumber.setText(String.valueOf(mainController.getOrderNumber()));
            updateOrders();
        }
    }

    /**
     * Updates the information in the ListView and TextFields to relevant information after changing.
     */
    public void updateOrders() {
        ObservableList<Pizza> newOrders = FXCollections.observableArrayList();
        newOrders.setAll(mainController.getOrderObservableList());
        totalOrderOutput.setItems(newOrders);
        if (mainController.getPizzaOrdersObservableList().size() != 0) {
            salesTax.setText((String.format("%.2f", mainController.getTotalOrder().orderTotalPrice() * SALES_TAX)));
            subTotal.setText((String.format("%.2f",  mainController.getTotalOrder().orderTotalPrice())));
            orderTotal.setText((String.format("%.2f", mainController.getTotalOrder().orderTotalPrice() * SALES_TAX_MULTIPLIER)));
        }
        else {
            orderNumber.clear();
            salesTax.clear();
            subTotal.clear();
            orderTotal.clear();
        }
    }

    /**
     * Places an order by sending order to store orders and instantiating another order object
     */
    @FXML
    protected void placeOrder() {
        if (mainController.getOrderObservableList().isEmpty()) {
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setHeaderText("Empty Order");
            errorAlert.setContentText("You must add pizzas in the order to place the order!");
            errorAlert.showAndWait();
        } else {
            Order addOrder = new Order();
            ObservableList<Pizza> pizzaList = mainController.getPizzaOrdersObservableList();
            for (Pizza pizza : pizzaList) {
                addOrder.getOrderList().add(pizza);
            }
            addOrder.setOrderNumber(mainController.getOrderNumber());
            mainController.addOrderNumber();
            mainController.getStoreOrders().add(addOrder);
            mainController.getTotalOrder().getOrder().clear();
            updateOrders();
        }
    }

    /**
     * Clears the pizzas from the object and ListView
     */
    @FXML
    protected void clearOrder() {
        if (mainController.getOrderObservableList().isEmpty()) {
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setHeaderText("Empty Order");
            errorAlert.setContentText("You must add pizzas in the order to clear the order!");
            errorAlert.showAndWait();
        } else {
            mainController.getTotalOrder().getOrder().clear();
            updateOrders();
        }
    }

    /**
     * Removes a selected pizza from the order and updates ListView
     */
    @FXML
    protected void removePizza() {
        if (totalOrderOutput.getSelectionModel().getSelectedItem() != null){
            mainController.getTotalOrder().remove(totalOrderOutput.getSelectionModel().getSelectedItem());
            updateOrders();
        }
        else {
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setHeaderText("No Pizza Selected");
            errorAlert.setContentText("You must select a pizza for it to be removed.");
            errorAlert.showAndWait();
        }
    }
}
