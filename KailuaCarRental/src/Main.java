import java.sql.SQLException;

public class Main {

    MenuHandler menuHandler = new MenuHandler();

    public void run() throws SQLException {
        menuHandler.run();
    }

    public static void main(String[] args) throws SQLException {
        new Main().run();
    }
}

