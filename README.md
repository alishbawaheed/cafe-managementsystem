# cafe-managementsystem

A Java-based desktop application developed using OOP and JavaFX to manage café operations. Features include menu display, order placement, bill calculation, and basic UI interactions for café staff and customers. Designed as part of my Object-Oriented Programming coursework.

---

## 👩‍💻 Developed By
  
   Alishba Waheed

---

## ✨ Features

- **Tabbed Interface:** Menu, Place Order, Calculator
- **Dynamic Menu Display:** View all items with prices
- **Simple Order Placement:** Select items and quantities easily
- **Live Total Calculation:** Bill updates in real time
- **Built-in Calculator:** Quick math for convenience
- **Modern, Clean UI:** Easy to use for everyone

---

## Screenshots

### 1. Menu Interface
![Menu](screenshots/menu.png)

### 2. Place Order Interface
![Place Order](screenshots/place order.png)

### 3. Calculator Interface
![Calculator](screenshots/calculator.png)


## 🚀 Getting Started

### Prerequisites

- Java **24** or newer ([Download Java](https://www.oracle.com/java/technologies/downloads/))
- [JavaFX SDK](https://gluonhq.com/products/javafx/)
- **IntelliJ IDEA** or Visual Studio Code (Recommended)

### How to Run

1. **Clone this repository:**
   ```bash
   git clone https://github.com/barer12357/cafe-management-system.git
   ```
2. **Open in Vs Code:**
   - Go to `File > Open` and select the `cafe-management-system` folder.
3. **Set up JavaFX:**
   - Add JavaFX SDK to Project Structure > Libraries.
   - Set VM options (e.g.):
     ```
     --module-path /path/to/javafx-sdk-XX/lib --add-modules javafx.controls,javafx.fxml
     ```
4. **Run the App:**
   - Right-click `CafeManagementSystemFX.java` and choose **Run**.

---

## 🗂️ Project Structure

```
cafeProject/
├── src/
│   └── main/
│       ├── java/
│       │   └── com/
│       │       └── cafe/
│       │           ├── CafeManagementSystemFX.java
│       │           ├── controller/
│       │           ├── model/
│       │           └── view/
│       └── resources/
│           ├── images/
│           └── styles/
├── README.md
├── .gitignore
└── [Other project files]
```

---

## 💡 Usage

- **Menu Tab:** Browse items and prices.
- **Place Order Tab:** Select items, set quantity, see summary and total.
- **Calculator Tab:** Perform arithmetic as needed.

---

## 🛠️ Technologies Used

- Java 24
- JavaFX
- Vs Code
