import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Main {
    // Attributes
    public static final String database_url = "jdbc:mysql://localhost:3306/kailuacarrental";
    public java.sql.Connection con;
    private boolean isRunning;

    // Instances
    Scanner scanner = new Scanner(System.in);
    Menu menu = new Menu("Kailua Car Rental", "Please choose: ", new String[]{"1. Cars",
            "2. Customers", "3. Rentals", "9. Exit"});

    // Methods
    public void run() {
        do {
            menu.printMenu();
            int choice = menu.readChoice();
            switch (choice) {
                case 1 -> printCars();
                case 2 -> printCarType();
                case 3 -> printCustomers();
                case 4 -> chooseCar();
                case 9 -> isRunning = false;
            }
        } while (isRunning);
    }

    public void setup(String sqlQuery, String p1, String p2, String columnLabel1, String columnLabel2) {
        try {
            con = DriverManager.getConnection(database_url, "root", "Sommersko2008");

            Statement s = con.createStatement();
            ResultSet rs = s.executeQuery(sqlQuery);

            if (rs != null) {
                while (rs.next()) {
                    System.out.print(p1 + rs.getString(columnLabel1));
                    System.out.println(" " + p2 + rs.getString(columnLabel2));
                }
            }
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
            System.exit(1);
        }
    }

    public void printCars() {
      setup("SELECT car_model, car_brand FROM cars","Car brand: ","Car model: ",
              "car_brand","car_model");
    }

    public void printCustomers() {
        setup("SELECT customer_fullname, customer_address FROM customer", "Customer name: ",
                "Customer address: ", "customer_fullname","customer_address");
    }

    public void printCarType() {
        System.out.println("Which brand would you like to see: \n 1. Audi \n 2. BMW \n 3. Mercedes");
        int userInput = scanner.nextInt();

        if (userInput == 1) {
            setup("SELECT car_model, car_brand FROM cars WHERE car_brand LIKE 'Audi'",
                    "Car brand: ", "Car model: ", "car_brand","car_model");
        }
        if (userInput == 2) {
            setup("SELECT car_model, car_brand FROM cars WHERE car_brand LIKE 'BMW'",
                    "Car brand: ", "Car model: ", "car_brand","car_model");
        }
        if (userInput == 3) {
            setup("SELECT car_model, car_brand FROM cars WHERE car_brand LIKE 'Mercedes'",
                    "Car brand: ", "Car model: ", "car_brand","car_model");
        }
    }

    public void chooseCar(){
        try {
            con = DriverManager.getConnection(database_url, "root", "Sommersko2008");
            int i = 1;
            String sqlQuery = "SELECT car_brand, car_model FROM cars";

            Statement s = con.createStatement();
            ResultSet rs = s.executeQuery(sqlQuery);

            System.out.println("Please choose which car you want to inspect: ");
            if (rs != null) {
                while (rs.next()) {
                    System.out.print(i++ + " " + "Car brand: " + rs.getString("car_brand"));
                    System.out.println(" " + "Car model: " + rs.getString("car_model"));
                }
                int userInput = scanner.nextInt();

                if (userInput == 1) {
                   
                }
            }
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
            System.exit(1);
        }
    }

    public static void main(String[] args) {
        new Main().run();
    }
}

