import java.sql.*;

public class Controller {

    // Attributes
    public static final String database_url = "jdbc:mysql://localhost:3306/carrental";
    public java.sql.Connection con;

    // Methods
    public void setupNCon(String sqlQuery) {
        try {
            // Connecting to database
            con = DriverManager.getConnection(database_url, "root", "Sommersko2008");
            Statement s = con.createStatement();
            ResultSet rs = s.executeQuery(sqlQuery);
            // MetaData gets column name, number of columns, column type
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnsNumber = rsmd.getColumnCount();

            // Print column headers
            for (int i = 1; i <= columnsNumber; i++) {
                String columnName = rsmd.getColumnName(i);
                String[] arr = columnName.split("_");
                System.out.printf("%-27s", arr[1]);
            }
            System.out.println();

            // Print each row of results
            int j = 1;
            while (rs.next()) {
                System.out.printf("%-8s", j + ".");
                j++;
                // Makes it print
                for (int i = 1; i <= columnsNumber; i++) {
                    String columnValue = rs.getString(i);
                    System.out.printf("%-23s", columnValue);
                }
                System.out.println();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void printNoIndex(String sqlQuery) {
        try {
            // Connecting to database
            con = DriverManager.getConnection(database_url, "root", "Sommersko2008");
            Statement s = con.createStatement();
            ResultSet rs = s.executeQuery(sqlQuery);
            // MetaData gets column name, number of columns, column type
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnsNumber = rsmd.getColumnCount();
            // Print column headers
            for (int i = 1; i <= columnsNumber; i++) {
                String columnName = rsmd.getColumnName(i);
                String[] arr = columnName.split("_");
                System.out.printf("%-8s", arr[1]);
            }
            System.out.println();

            // Print each row of results
            while (rs.next()) {
                // Makes it print
                for (int i = 1; i <= columnsNumber; i++) {
                    String columnValue = rs.getString(i);
                    System.out.printf("%-8s", columnValue);
                }
                System.out.println();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void modify(String sqlQuery) {
        try {
            con = DriverManager.getConnection(database_url, "root", "Sommersko2008");
            Statement s = con.createStatement();
            // Execute to update query
            s.executeUpdate(sqlQuery);
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
        }
    }
}
