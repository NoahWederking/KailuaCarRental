import java.util.Scanner;

public class Car {

    // Instances
    Scanner scanner = new Scanner(System.in);

    // Methods
    public void printCars(Controller controller) {
        controller.setupNCon("SELECT car_brand, car_model FROM car JOIN model ON car.car_id = model.car_id " +
                "ORDER BY car.car_brand");
    }

    public void deleteCar(Controller controller) {
        controller.setupNCon("SELECT car_brand, car_model FROM car JOIN model ON car.car_id = model.car_id");
        System.out.println("Type the index of which car you want deleted: ");
        String input = scanner.nextLine();
        controller.modify("DELETE FROM car WHERE car_id =" + input);
        controller.modify("DELETE FROM model WHERE car_id =" + input);
    }

    public void updateCar(Controller controller) {
        controller.setupNCon("SELECT car_brand, car_model FROM car JOIN model ON car.car_id = model.car_id");
        System.out.println("Type the index of which car you want to update: ");
        int carId = scanner.nextInt() - 1;
        scanner.nextLine();
        String[] fields = {"brand", "model", "fueltype", "plate", "registration", "miles"};
        for (int i = 0; i < fields.length; i++) {
            System.out.println((i + 1) + ". " + fields[i]);
        }
        controller.setupNCon("SELECT car_brand, car_model, car_fueltype, car_plate, car_registration" +
                " FROM car JOIN model ON car.car_id = model.car_id WHERE car.car_id =" + "'" + carId + "'");
        System.out.println("Choose which part you want to change about the car: ");
        int fieldIndex = scanner.nextInt() - 1;
        scanner.nextLine();
        System.out.println("What do you want to change it to?");
        String newValue = scanner.nextLine();
        String field = fields[fieldIndex];
        if (fieldIndex != 2) {
            String sql = String.format("UPDATE car SET car_%s='%s' WHERE car_id=%d", field, newValue, carId);
            controller.modify(sql);
        } else {
            String sql = String.format("UPDATE model SET model_%s='%s' WHERE car_id=%d", field, newValue, carId);
            controller.modify(sql);
        }
    }

    public void printCarType(Controller controller) {
        System.out.println("Which brand would you like to see: ");
        String input = scanner.nextLine();

        controller.setupNCon(" SELECT car_brand, car_model, accessorie.car_type, car_plate " +
                " FROM car "
                + "JOIN model ON car.car_id = model.car_id " +
                "JOIN accessorie ON model.car_id = accessorie.car_ID " +
                //% er et wildcard til MySQL
                " WHERE car_brand LIKE " + "'" + input + "'");
    }

    public void addCar(Controller controller) {
        System.out.println("What is the car brand?");
        String car_brand = scanner.nextLine();
        System.out.println("What is the car model?");
        String car_model = scanner.nextLine();
        System.out.println("What kind of geartype? Automatic/Manuel");
        String car_gear_type = scanner.nextLine();
        System.out.println("Does the car have aircondition? 1/0");
        String car_aircondition = scanner.nextLine();
        System.out.println("Does the car have cruise control? 1/0");
        String car_cruisecontrol = scanner.nextLine();
        System.out.println("Does the car have leather seats? 1/0");
        String car_leather_seat = scanner.nextLine();
        System.out.println("How many seat does the car have?");
        String car_seat_number = scanner.nextLine();
        System.out.println("How many horsepower does the car have");
        String car_horse_power = scanner.nextLine();
        System.out.println("What kind of fueltype is the car using? Diesel/Benzin/Electric/Hybrid");
        String car_fueltype = scanner.nextLine();
        System.out.println("What is the license plate? ");
        String car_license_plate = scanner.nextLine();
        System.out.println("What date did the car get registered? YYYY-MM-DD");
        String car_registration = scanner.nextLine();
        System.out.println("How far has the car been driving ");
        String car_miles = scanner.nextLine();
        System.out.println("Is the car rented? 1/0");
        String car_isRented = scanner.nextLine();
        String carTypeGenerator = carTypeGenerator(car_gear_type, car_leather_seat, car_aircondition);

        controller.modify("INSERT INTO accessorie VALUES (NULL, " + "'" + car_gear_type + "'," +
                car_aircondition + "," + car_cruisecontrol
                + "," + car_leather_seat + "," + car_seat_number + "," + car_horse_power + ",'" + carTypeGenerator + "');");

        controller.modify("INSERT INTO car VALUES (NULL, '" + car_brand + "','" + car_fueltype + "','" +
                car_license_plate + "','" + car_registration + "'," + car_miles + "," + car_isRented + ");");

        controller.modify("INSERT INTO model VALUES (NULL, " + "'" + car_model + "')");
    }

    // Method for generating car type
    public String carTypeGenerator(String gearType, String seatType, String carAircondition) {
        String result;
        if (gearType.equals("automatic") && seatType.equals("1")) {
            result = "luxury";
        } else if (gearType.equals("manual") && carAircondition.equals("1")) {
            result = "family";
        } else if (gearType.equals("manual") && carAircondition.equals("0")) {
            result = "sport";
        } else {
            result = "unknown";
        }
        return result;
    }
}
