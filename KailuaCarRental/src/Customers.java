import java.util.Scanner;

public class Customers {
    // Instances
    Scanner scanner = new Scanner( System.in);

    // Methods
    public void printCustomers(Controller controller) {
        controller.setupNCon("SELECT customer_fullname, customer_address FROM customer");
    }

    public void deleteCustomer(Controller controller) {
        controller.setupNCon("SELECT customer_fullname FROM customer");
        System.out.println("Type the index of which customer you want deleted: ");
        String input = scanner.nextLine();
        controller.delete("DELETE FROM customer WHERE customer_id LIKE" + "\'" + input + "\'");
    }
}
