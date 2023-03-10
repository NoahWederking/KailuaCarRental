public class Main {

    MenuHandler menuHandler = new MenuHandler();

    public void startProgram() {
        menuHandler.run();
    }

    public static void main(String[] args) {
        new Main().startProgram();
    }
}

