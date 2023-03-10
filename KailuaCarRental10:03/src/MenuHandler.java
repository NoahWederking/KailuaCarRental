public class MenuHandler {
    // Attributes
    private boolean isRunning = true;
    Menu menu = new Menu("====Kailua Car Rental====", "Please choose: ", new String[]{"1. Cars menu",
            "2. Customer menu ", "3. Contract menu", "9. Exit"});

    Menu costumerMenu = new Menu("====Customer Menu====", "Please Choose: ", new String[]{
            "1. Add customer", "2. Delete customer", "3. Update customer", "4. Print all", "9. Go back"});

    Menu carMenu = new Menu("====Car Menu====", "Please Choose: ", new String[]{"1. Add car",
            "2. Delete car", "3. Update car", "4. Print all", "5. Print car type", "9. Go back"});

    Menu contractMenu = new Menu("====Contract Menu====", "Please Choose: ",
            new String[]{"1. Print rented cars", "2. Cars ready for rent", "3. Update contract",
                    "4. Make contract", "5. Print contracts", "9. Go back"});

    Controller controller = new Controller();
    Car cars = new Car();
    Customer customers = new Customer();
    Contract contract = new Contract();

    public void run() {
        do {
            menu.printMenu();
            switch (menu.readChoice()) {
                case 1 -> car();
                case 2 -> costumer();
                case 3 -> contract();
                case 9 -> isRunning = false;
            }
        } while (isRunning);
    }

    public void costumer() {
        do {
            costumerMenu.printMenu();
            switch (costumerMenu.readChoice()) {
                case 1 -> customers.addCustomer(controller);
                case 2 -> customers.deleteCustomer(controller);
                case 3 -> customers.updateCustomer(controller);
                case 4 -> customers.printCustomers(controller);
                case 9 -> run();
            }
        } while (isRunning);
    }

    public void car() {
        do {
            carMenu.printMenu();
            switch (carMenu.readChoice()) {
                case 1 -> cars.addCar(controller);
                case 2 -> cars.deleteCar(controller);
                case 3 -> cars.updateCar(controller);
                case 4 -> cars.printCars(controller);
                case 5 -> cars.printCarType(controller);
                case 9 -> run();
            }
        } while (isRunning);
    }

    public void contract() {
        do {
            contractMenu.printMenu();
            switch (contractMenu.readChoice()) {
                case 1 -> contract.printRentedCars(controller);
                case 2 -> contract.printNotRentedCars(controller);
                case 3 -> contract.updateContract(controller);
                case 4 -> contract.makeContract(controller);
                case 5 -> contract.printContracts(controller);
                case 9 -> run();
            }
        } while (isRunning);
    }
}
