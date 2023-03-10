import java.sql.*;

public class Controller {

    // Attributes
    public final String database_url = "jdbc:mysql://localhost:3306/carrental";
    public java.sql.Connection con;
    public final String database_url_Noah = "jdbc:mysql://localhost:3306/carrental";

    // Methods
    public void setupNCon(String sqlQuery) {
        try {
            //Connecting to database
            con = DriverManager.getConnection(database_url_Noah, "root", "sesame80");
            Statement s = con.createStatement();
            //executeQuery
            ResultSet rs = s.executeQuery(sqlQuery);
            //MetaData gets columnname, number of columns, column type
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnsNumber = rsmd.getColumnCount();
            int j = 1;
            while (rs.next()) {
                System.out.print(j + ". ");
                j++;
                //Makes it print beatyfull
                for (int i = 1; i <= columnsNumber; i++) {
                    if (i > 1) System.out.print(", ");
                    String columnValue = rs.getString(i);
                    //Tager rsmd.getColumnName og smider i at array og tager derefter det sidste index og printer
                    String columnName = rsmd.getColumnName(i) ;
                    String[] arr = columnName.split("_");
                    System.out.print(arr[1] + " " + columnValue);
                }
                System.out.println("");
            }
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
        }
    }

    public void setupNConNoIndex(String sqlQuery) {
        try {
            //Connecting to database
            con = DriverManager.getConnection(database_url, "root", "Sommersko2008");
            Statement s = con.createStatement();
            ResultSet rs = s.executeQuery(sqlQuery);
            //MetaData gets columnname, number of columns, column type
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnsNumber = rsmd.getColumnCount();
            int j = 1;
            while (rs.next()) {
                //Makes it print beatyfull
                for (int i = 1; i <= columnsNumber; i++) {
                    if (i > 1) System.out.print(", ");
                    String columnValue = rs.getString(i);
                    //Tager rsmd.getColumnName og smider i at array og tager derefter det sidste index og printer
                    String columnName = rsmd.getColumnName(i) ;
                    String[] arr = columnName.split("_");
                    System.out.print(arr[1] + " " + columnValue);

                }
                System.out.println("");
            }



        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
        }
    }

    public void modify(String sqlQuery) {
        try {
            con = DriverManager.getConnection(database_url, "root", "sesame80");
            Statement s = con.createStatement();
            //execute Update skal bruges her fordi det opdateres
            s.executeUpdate(sqlQuery);
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
        }
    }
}
