public class MenuHandler {
    // Attributes
    private boolean isRunning = true;
    Menu menu = new Menu("====Kailua Car Rental====", "Please choose: ", new String[]{"1. Print all Cars",
            "2. Print all customers", "3. CarType", "4. Modify", "9. Exit"});

    Menu costumerMenu = new Menu("===Customer Menu===","Please Choose", new String[]{"1. ADD Customer",
    "DELETE Customer","Update Customer"});

    Controller controller = new Controller();
    Cars cars = new Cars();
    Customers customers = new Customers();

    public void run() {
        do {
            menu.printMenu();
            switch (menu.readChoice()) {
                case 1 -> cars.printCars(controller);
                case 2 -> customers.printCustomers(controller);
                case 3 -> cars.printCarType(controller);
                case 4 -> System.out.println("modify");
                case 9 -> isRunning = false;
            }
        } while (isRunning);
    }


    public void customer(){
        /*do {
            costumerMenu.printMenu();
            int choice = menu.readChoice();

        }*/
    }
}
