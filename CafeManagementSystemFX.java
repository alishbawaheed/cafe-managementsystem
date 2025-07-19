
package cafeProject;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.*;

public class CafeManagementSystemFX extends Application {

    private final Map<Integer, MenuItem> menuItems = new LinkedHashMap<>();
    private final Map<MenuItem, Integer> orderItems = new LinkedHashMap<>();
    private final VBox orderSummaryBox = new VBox(5);
    private final Label totalLabel = new Label("Total Bill: Rs. 0.00");

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        initializeMenu();

        TabPane tabPane = new TabPane();

        Tab menuTab = new Tab("Menu", createMenuTab());
        Tab calculatorTab = new Tab("Calculator", createCalculatorTab());
        Tab orderTab = new Tab("Place Order", createOrderTab());

        tabPane.getTabs().addAll(menuTab, orderTab, calculatorTab);
        tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);

        // Create MenuBar with Exit option
        MenuBar menuBar = new MenuBar();
        javafx.scene.control.Menu fileMenu = new javafx.scene.control.Menu("File");
        javafx.scene.control.MenuItem exitItem = new javafx.scene.control.MenuItem("Exit");

        exitItem.setOnAction(_ -> primaryStage.close());

        fileMenu.getItems().add(exitItem);
        menuBar.getMenus().add(fileMenu);

        // Combine MenuBar and TabPane
        VBox root = new VBox(menuBar, tabPane);

        Scene scene = new Scene(root, 500, 500);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Cafe Management System - JavaFX");
        primaryStage.show();
    }

    private void initializeMenu() {
        menuItems.put(1, new MenuItem("Tea", 50));
        menuItems.put(2, new MenuItem("Coffee", 70));
        menuItems.put(3, new MenuItem("Burger", 200));
        menuItems.put(4, new MenuItem("Pizza", 350));
        menuItems.put(5, new MenuItem("Fries", 120));
    }

    private VBox createMenuTab() {
        VBox box = new VBox(10);
        box.setPadding(new Insets(15));
        box.getChildren().add(new Label("Cafe Menu:"));

        for (Map.Entry<Integer, MenuItem> entry : menuItems.entrySet()) {
            MenuItem item = entry.getValue();
            box.getChildren().add(new Label(entry.getKey() + ". " + item.getName() + " - Rs. " + item.getPrice()));
        }

        return box;
    }

    private VBox createOrderTab() {
        VBox box = new VBox(10);
        box.setPadding(new Insets(15));

        ComboBox<String> itemCombo = new ComboBox<>();
        for (MenuItem item : menuItems.values()) {
            itemCombo.getItems().add(item.getName());
        }

        Spinner<Integer> quantitySpinner = new Spinner<>(1, 100, 1);
        Button addButton = new Button("Add to Order");
        addButton.setDisable(true);

        // Enable addButton only if an item is selected
        itemCombo.valueProperty().addListener((obs, oldVal, newVal) -> {
            addButton.setDisable(newVal == null);
        });

        addButton.setOnAction(e -> {
            String selectedName = itemCombo.getValue();
            if (selectedName == null) return;

            MenuItem selectedItem = menuItems.values().stream()
                    .filter(i -> i.getName().equals(selectedName))
                    .findFirst().orElse(null);

            if (selectedItem != null) {
                int qty = quantitySpinner.getValue();
                orderItems.put(selectedItem, orderItems.getOrDefault(selectedItem, 0) + qty);
                updateOrderSummary();

                // Reset controls
                itemCombo.getSelectionModel().clearSelection();
                quantitySpinner.getValueFactory().setValue(1);
            }
        });

        Button clearOrderButton = new Button("Clear Order");
        clearOrderButton.setOnAction(e -> {
            orderItems.clear();
            updateOrderSummary();
        });

        HBox orderControls = new HBox(10, new Label("Item:"), itemCombo,
                new Label("Qty:"), quantitySpinner, addButton, clearOrderButton);
        orderControls.setPadding(new Insets(10));

        box.getChildren().addAll(orderControls, new Label("Order Summary:"), orderSummaryBox, totalLabel);
        return box;
    }

    private void updateOrderSummary() {
        orderSummaryBox.getChildren().clear();
        double total = 0;

        for (Map.Entry<MenuItem, Integer> entry : orderItems.entrySet()) {
            MenuItem item = entry.getKey();
            int qty = entry.getValue();
            double itemTotal = item.getPrice() * qty;

            orderSummaryBox.getChildren().add(new Label(item.getName() + " x " + qty + " = Rs. " + String.format("%.2f", itemTotal)));
            total += itemTotal;
        }

        totalLabel.setText("Total Bill: Rs. " + String.format("%.2f", total));
    }

    private VBox createCalculatorTab() {
        VBox box = new VBox(10);
        box.setPadding(new Insets(15));

        TextField num1Field = new TextField();
        TextField num2Field = new TextField();
        ComboBox<String> operatorBox = new ComboBox<>();
        operatorBox.getItems().addAll("+", "-", "*", "/");
        Button calcButton = new Button("Calculate");
        Label resultLabel = new Label("Result: ");

        calcButton.setOnAction(e -> {
            try {
                double a = Double.parseDouble(num1Field.getText());
                double b = Double.parseDouble(num2Field.getText());
                String op = operatorBox.getValue();

                double result;
                switch (op) {
                    case "+" -> result = a + b;
                    case "-" -> result = a - b;
                    case "*" -> result = a * b;
                    case "/" -> {
                        if (b == 0) {
                            resultLabel.setText("Cannot divide by zero!");
                            return;
                        }
                        result = a / b;
                    }
                    default -> {
                        resultLabel.setText("Invalid operator.");
                        return;
                    }
                }

                resultLabel.setText(String.format("Result: %.2f %s %.2f = %.2f", a, op, b, result));
            } catch (NumberFormatException ex) {
                resultLabel.setText("Please enter valid numbers.");
            }
        });

        box.getChildren().addAll(new Label("Enter first number:"), num1Field,
                new Label("Enter second number:"), num2Field,
                new Label("Choose operator:"), operatorBox,
                calcButton, resultLabel);

        return box;
    }

    // Clean MenuItem class without any JavaFX control methods
    static class MenuItem {
        private final String name;
        private final double price;

        public MenuItem(String name, double price) {
            this.name = name;
            this.price = price;
        }

        public String getName() { return name; }
        public double getPrice() { return price; }
    }
}