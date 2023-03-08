import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Cars {
    // Instances
    Scanner scanner = new Scanner(System.in);
    public static final String database_url = "jdbc:mysql://localhost:3306/kailuacarrental";
    public java.sql.Connection con;

    // Methods
    public void printCars(Controller controller) {
        controller.setupNCon("SELECT car_brand, car_model FROM cars");
    }

    public void deleteCar(Controller controller) {
        controller.setupNCon("SELECT car_brand, car_model FROM cars");
        System.out.println("Type the index of which car you want deleted: ");
        String input = scanner.nextLine();
        controller.delete("DELETE FROM cars WHERE car_id LIKE" + "\'" + input + "\'");
    }

    public void updateCar(Controller controller) {
        controller.setupNCon("SELECT car_brand, car_model FROM cars");
        System.out.println("Type the index of which car you want to update: ");
        String input = scanner.nextLine();
        controller.setupNCon("SELECT car_brand, car_model, car_fueltype, car_plate, car_registration," +
                " car_miles FROM cars WHERE car_id LIKE" + "\'" + input + "\'");

    }

    public void printCarType(Controller controller) {
        System.out.println("Which brand would you like to see: ");
        String input = scanner.nextLine();

        controller.setupNCon("SELECT car_brand, car_model FROM cars WHERE car_brand LIKE" + "\'" + input + "%\'");
    }

    public void createCar() {
        try {

           // String script = "INSERT INTO cars VALUES (null,);
            con = DriverManager.getConnection(database_url, "root", "Sommersko2008");
            Statement s = con.createStatement();
           // ResultSet rs = s.executeQuery(script);


        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
        }
    }

}
