### Textile Management System POS

This is a Java-based Textile Management System Point of Sale (POS) application. The application provides a comprehensive solution for managing textile inventory, sales, and user authentication.

## Table of Contents

- [Features](#features)
- [Installation](#installation)
- [Usage](#usage)
- [Contributing](#contributing)
- [License](#license)

## Features

- **User Authentication**: Secure login and registration for users.
- **Inventory Management**: Add, update, and delete items in the inventory.
- **Sales Management**: Process sales and generate reports.
- **Database Operations**: Connect to and interact with MySQL databases.

## Installation

### Prerequisites

- Java Development Kit (JDK) 8 or higher
- Apache Ant
- MySQL Database

### Steps

1. **Clone the Repository**

   ```sh
   git clone https://github.com/ahiru60/Textile_Management_System_POS.git
   cd Textile_Management_System_POS
   ```

2. **Setup MySQL Database**

   - Create the necessary databases and tables as per the application requirements. 
   - You can use the following command to create databases:

     ```sql
     CREATE DATABASE clothmgt_userLogin;
     CREATE DATABASE clothmgt_brands;
     CREATE DATABASE clothmgt_reports;
     ```

3. **Configure Database Connection**

   - Update the database connection URLs in `src/Model/DatabaseOperations.java` and `src/Model/DatabaseConnection.java` to match your MySQL setup.

4. **Build the Project**

   - Use Apache Ant to build the project:

     ```sh
     ant clean build
     ```

## Usage

1. **Run the Application**

   - After building the project, you can run the application using the following command:

     ```sh
     java -jar dist/TextileManagementSystem.jar
     ```

2. **Login/Register**

   - Use the login form to log in with your credentials. If you do not have an account, you can register a new one.

3. **Manage Inventory**

   - Navigate to the inventory management section to add, update, or delete items.

4. **Process Sales**

   - Use the sales management section to process sales and generate sales reports.

## Code Overview

### Main Class

The main entry point of the application is `start.java`, which initializes the GUI and opens the login form.

```java
package TextileManagementSystem;

import View.Login;

public class start extends javax.swing.JFrame {
    public start() {
        initComponents();
    }
    
    private void initComponents() {
        // Initialization code here
    }
    
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new start().setVisible(false);
                new Login().setVisible(true);
            }
        });
    }
}
```

### Database Connection

`DatabaseConnection.java` handles the database connection and provides a method to get a `Statement` object for executing SQL queries.

```java
package Model;
import java.sql.*;

public class DatabaseConnection {
    static Connection connection;
    static Statement state;
    
    public Statement getStatementConnection(String uRL) {
        try {
            connection = DriverManager.getConnection(uRL, "root", "");
            state = connection.createStatement();
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        return state;
    }
    
    public static void closeCon() throws SQLException {
        connection.close();
    }
}
```

### Database Operations

`DatabaseOperations.java` contains various methods for performing database operations like getting login information, checking passwords, and managing inventory items.

```java
package Model;
import java.sql.*;

public class DatabaseOperations {
    private static String userLoginDB = "jdbc:mysql://localhost:3306/clothmgt_userLogin";
    private static String BrandsDB = "jdbc:mysql://localhost:3306/clothmgt_brands";
    private static String reportsDB = "jdbc:mysql://localhost:3306/clothmgt_reports";
    
    public static ResultSet getLoginSet(String userName) {
        Statement state;
        ResultSet rs = null;
        try {
            state = new DatabaseConnection().getStatementConnection(userLoginDB);
            rs = state.executeQuery("SELECT * FROM passmgt where user_name='" + userName + "'");
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        return rs;
    }
    
    // Other database operations here
}
```

### Login Controller

`LoginController.java` manages user login and registration processes.

```java
package Controller;
import Model.*;
import View.*;
import java.sql.*;
import javax.swing.JOptionPane;

public class LoginController {
    public static void login(String userName, String password) {
        ResultSet rs;
        String user = null;
        String pass = null;
        try {
            rs = new DatabaseOperations().getLoginSet(userName);
            while(rs.next()) {
                user = rs.getString("user_name");
                pass = rs.getString("password");
            }
            if(user != null && pass != null) {
                if(user.equals(userName) && pass.equals(password)) {
                    Login.getFrames()[0].dispose();
                    new Home().setVisible(true);
                    System.out.println("Login Successful!");
                } else {
                    JOptionPane.showMessageDialog(null, "Your login attempt failed. Please check your username and password again!");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Your login attempt failed. Please check your username and password again!");
            }
            DatabaseConnection.closeCon();
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }
    
    // Other methods for registration and validation
}
```

## Contributing

Contributions are welcome! Please feel free to submit a Pull Request.

## License
This project is licensed under the [MIT License](LICENSE).
