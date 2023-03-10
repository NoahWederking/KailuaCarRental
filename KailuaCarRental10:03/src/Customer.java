import java.util.Scanner;

public class Customer {
    // Instances
    Scanner scanner = new Scanner(System.in);

    // Methods
    public void printCustomers(Controller controller) {
        controller.setupNCon("SELECT customer_fullname, customer_address, customer_mobile FROM customer ORDER BY customer_id");
    }

    public void deleteCustomer(Controller controller) {
        controller.setupNCon("SELECT customer_fullname FROM customer");
        System.out.println("Type the index of which customer you want deleted: ");
        String input = scanner.nextLine();
        controller.modify("DELETE FROM customer WHERE customer_id LIKE" + "\'" + input + "\'");
    }

    public void updateCustomer(Controller controller) {
        controller.setupNCon("SELECT customer_fullname FROM customer ORDER BY customer_id");
        System.out.println("Type the index of which customer you want to update: ");
        int customerId = scanner.nextInt();
        scanner.nextLine();
        String[] fields = {"fullname", "address", "city", "phone", "mobile", "email",
                "licence_number", "licence_date"};
        for (int i = 0; i < fields.length; i++) {
            System.out.println((i + 1) + ". " + fields[i]);
        }
        controller.setupNCon("SELECT customer_fullname, customer_address, customer_city," +
                "customer_phone, customer_mobile, customer_email, customer_licence_number, customer_licence_date" +
                "FROM customer WHERE customer_id LIKE" + "\'" + customerId + "\'" + "ORDER BY customer_id");
        System.out.println("Choose which part you want to change about the customer: ");
        int fieldIndex = scanner.nextInt() - 1;
        scanner.nextLine();
        System.out.println("What do you want to change it to?");
        String newValue = scanner.nextLine();
        String field = fields[fieldIndex];
        String sql = String.format("UPDATE customer SET customer_%s='%s' WHERE customer_id=%d",
                field, newValue, customerId);
        controller.modify(sql);
    }

    public void addCustomer(Controller controller) {
        System.out.println("Please input customers Full name");
        String fullName = scanner.nextLine();
        System.out.println("Please input Customer address");
        String customer_address = scanner.nextLine();
        System.out.println("Please input Customer city");
        String customer_city = scanner.nextLine();
        System.out.println("Please input Customer mobile number");
        String customer_mobile = scanner.nextLine();
        System.out.println("Please input Customer phone number");
        String customer_phone = scanner.nextLine();
        System.out.println("Please input Customer mail");
        String customer_email = scanner.nextLine();
        System.out.println("Please input Customer Driver licence number");
        String customer_licenceNumber = scanner.nextLine();
        System.out.println("Please input Customer licence Date YYYY-MM-DD");
        String customer_licencedate = scanner.nextLine();
        controller.modify("INSERT INTO customer VALUES (NULL, " + "\'" + fullName + "\'" + ", "
                + "\'" + customer_address + "\'" + ", " + "\'"+ customer_city + "\'" +  ", "
                + customer_mobile + ", " + customer_phone + ", " + "\'" + customer_email + "\'" + ", "
                + customer_licenceNumber + ", " + "\'" + customer_licencedate + "\')");
    }
}
