import java.util.Scanner;

public class Contract {
    // Instances
    Scanner scanner = new Scanner(System.in);

    public void printNotRentedCars(Controller controller) {
        controller.setupNCon("SELECT car_brand, car_model FROM cars WHERE car_isrented = 0");
    }

    public void printRentedCars(Controller controller) {
        controller.setupNCon("SELECT car_brand, car_model FROM car JOIN model ON" +
                " car.car_id = model.car_id WHERE car_isrented = 1");
        System.out.println("Select the index of which car you want to see the contract of: ");
        int userInput = scanner.nextInt();
        String sql = String.format("SELECT * FROM contract WHERE contract_id=%d", userInput);
        controller.setupNCon(sql);
    }

    public void printContracts(Controller controller) {
        controller.setupNCon("SELECT car.car_brand, model.car_model, customer_licence_number, contract_date_from," +
                "contract_max_km, contract_km_driven, car.car_plate FROM contract JOIN car " +
                "ON car.car_id = contract.contract_id JOIN model ON car.car_id=model.car_id");
    }

    public void updateContract(Controller controller) {
        controller.setupNConNoIndex("SELECT contract_id, car_brand, car_model FROM car JOIN model" +
                " ON car.car_id = model.car_id JOIN contract ON car.car_id = contract.contract_id" +
                " WHERE car_isrented = 1");
        System.out.println("Type the index of which contract you want to update: ");
        int contractId = scanner.nextInt();
        scanner.nextLine();
        String[] fields = {"licence_number", "date_from", "date_to", "max_km", "km_driven", "plate"};
        for (int i = 0; i < fields.length; i++) {
            System.out.println((i + 1) + ". " + fields[i]);
        }
        controller.setupNCon("SELECT * FROM contract  WHERE contract_id =" + "\'" + contractId + "\'");
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

    }
}
