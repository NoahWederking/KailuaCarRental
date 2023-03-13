import java.util.Scanner;

public class Contract {
    // Instances
    Scanner scanner = new Scanner(System.in);

    // Methods
    public void printNotRentedCars(Controller controller) {
        controller.setupNCon("SELECT car_brand, car_model, car_plate FROM car JOIN model ON" +
                " car.car_id = model.car_id WHERE car_isrented = 0");
    }

    public void printRentedCars(Controller controller) {
        controller.setupNCon("SELECT car_brand, car_model, car_plate FROM car JOIN model ON" +
                " car.car_id = model.car_id WHERE car_isrented = 1");
    }

    public void printContracts(Controller controller) {
        controller.setupNCon("SELECT car.car_brand, model.car_model, customer_licence_number, contract_date_from," +
                "contract_max_km, contract_km_driven, car.car_plate FROM contract JOIN car " +
                "ON car.car_plate = contract.car_plate JOIN model ON car.car_id=model.car_id");
    }

    public void updateContract(Controller controller) {
        controller.printNoIndex("SELECT contract_id, car_brand, car_model FROM car JOIN model" +
                " ON car.car_id = model.car_id JOIN contract ON car.car_plate = contract.car_plate" +
                " WHERE car_isrented = 1");
        System.out.println("Type the ID of which contract you want to update: ");
        int contractId = scanner.nextInt();
        scanner.nextLine();
        String[] fields = {"licence_number", "date_from", "date_to", "max_km", "km_driven", "plate"};
        for (int i = 0; i < fields.length; i++) {
            System.out.println((i + 1) + ". " + fields[i]);
        }
        controller.setupNCon("SELECT * FROM contract  WHERE contract_id =" + "'" + contractId + "'");
        System.out.println("Choose which part you want to change about the contract: ");
        int fieldIndex = scanner.nextInt() - 1;
        scanner.nextLine();
        System.out.println("What do you want to change it to?");
        String newValue = scanner.nextLine();
        String field = fields[fieldIndex];
        String sql = String.format("UPDATE contract SET contract_%s='%s' WHERE contract_id=%d",
                field, newValue, contractId);
        controller.modify(sql);
    }

    public void makeContract(Controller controller) {
        System.out.println("Please insert licence number: ");
        int licenceNumber = scanner.nextInt();
        System.out.println("Please insert start date YYYY-MM-DD: ");
        scanner.nextLine(); // Scanner bug
        String dateFrom = scanner.nextLine();
        System.out.println("Please insert end date YYYY-MM-DD: ");
        String dateTo = scanner.nextLine();
        System.out.println("Please insert max km allowed on contract: ");
        int maxKm = scanner.nextInt();
        System.out.println("Please insert starting km driven: ");
        int kmDriven = scanner.nextInt();
        System.out.println("Please insert the cars plate: ");
        scanner.nextLine(); // Scanner bug
        String carPlate = scanner.nextLine();

        String sql = String.format("INSERT INTO contract (contract_id, customer_licence_number," +
                " contract_date_from, contract_date_to, contract_max_km, contract_km_driven, car_plate)" +
                " VALUES (null, %d, '%s', '%s', %d, %d, '%s')", licenceNumber, dateFrom, dateTo, maxKm, kmDriven, carPlate);
        controller.modify(sql);
        String carSql = String.format("UPDATE car SET car_isrented = 1 WHERE car_plate = '%s'", carPlate);
        controller.modify(carSql);
    }
}
